package com.dhcc.infusion;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.log.NurLogFragment;
import com.base.commlibs.service.MServiceNewOrd;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.UserUtil;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.VibrateUtils;
import com.dhcc.infusion.update.UpdateAppUtil;
import com.dhcc.module.infusion.message.MessageFragment;
import com.dhcc.module.infusion.message.api.MessageApiManager;
import com.dhcc.module.infusion.message.bean.MessageSkinBean;
import com.dhcc.module.infusion.message.bean.NotifyMessageBean;
import com.dhcc.module.infusion.setting.SettingFragment;
import com.dhcc.module.infusion.workarea.WorkAreaFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends BaseActivity implements RadioButton.OnCheckedChangeListener {

    private static final int TAB_WORKAREA = 9001;
    private static final int TAB_MESSAGE = 9002;
    private static final int TAB_SETTING = 9003;
    private static final String CLICK_PENDING_INTENT = "ClickPendingIntent";
    private FragmentManager mFragmentManager;
    private Fragment[] mFragments;
    private FragmentTransaction fragmentTransaction;
    private ProgressDialog pd;
    private SharedPreferences sp;
    //    private RelativeLayout loginLayout;
    private RadioButton rbWorkarea;
    private RadioButton rbMessage;
    private RadioButton rbSetting;

    private int connn = 0;
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    //更新参数
    private String wardId;
    private String ip;
    private String version;
    private SPUtils spUtils;

    //是否已请求更新
    private int hasRequest;
    //是否可以更新
    private int canUpdate;

    private MainReceiver mainReceiver = new MainReceiver();
    private IntentFilter mainfilter = new IntentFilter();
    private String warning = "15";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spUtils = SPUtils.getInstance();

        //  addGlobalView(ViewGlobal.createInfusionGlobal(this));
        //此处为暂时调用，应该在登录成功后初始化tabviews
        initTabView();

        Intent i = new Intent(this, MServiceNewOrd.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(i);

        //注册事件总线
        EventBus.getDefault().register(this);
        AppUtil.initPlay(this, 0, R.raw.notice_message);
        UpdateAppUtil.initCanUpdate();

        UserUtil.createMainActivity();
        //检查通知权限
        AppUtil.checkNotification(this);

        if (mainReceiver != null) {
            mainfilter.addAction(Action.NEWMESSAGE_SERVICE);
            registerReceiver(mainReceiver, mainfilter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, MServiceNewOrd.class);
        stopService(i);

        if (mainReceiver != null) {
            unregisterReceiver(mainReceiver);
        }
        //注册事件总线
        EventBus.getDefault().unregister(this);
    }
    /**
     * 测试-切换服务器
     * @param view
     */
    public void testServer(View view) {
        if (BuildConfig.DEBUG) {
            String pdaService = BaseWebServiceUtils.getOPPDAService();
            if (pdaService.contains(BaseWebServiceUtils.NUR_MNIS_SERVICE)) {
                pdaService = BaseWebServiceUtils.NUR_OPPDA_SERVICE;
            }else {
                pdaService = BaseWebServiceUtils.NUR_MOES_SERVICE;
            }
            SPStaticUtils.put(SharedPreference.oppdaService,pdaService);
            ToastUtils.showShort("切换服务器: "+pdaService);
        }else {
            startFragment(NurLogFragment.class);
        }
        startFragment(NurLogFragment.class);
    }
    /**
     * 初始化各模块界面
     */
    private void initTabView() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        List<Fragment> oldFragments = mFragmentManager.getFragments();

        if (oldFragments != null && oldFragments.size() > 0) {
            for (Fragment old : oldFragments) {
                if (old != null) {
                    ft.remove(old);
                }
            }
        }

        mFragments = new Fragment[3];
        mFragments[0] = new WorkAreaFragment();
        mFragments[1] = new MessageFragment();
        mFragments[2] = new SettingFragment();

        ft.add(R.id.fragment, mFragments[0], "workarea");
        ft.add(R.id.fragment, mFragments[1], "message");
        ft.add(R.id.fragment, mFragments[2], "setting");
        ft.hide(mFragments[1]).hide(mFragments[2]);
        ft.commitAllowingStateLoss();

        setFragmentIndicator();

    }

    /**
     * 设置主界面底部栏指示图标
     */
    private void setFragmentIndicator() {
        rbWorkarea = findViewById(R.id.rbWorkarea);
        rbMessage = findViewById(R.id.rbMessage);
        rbSetting = findViewById(R.id.rbSetting);

        rbWorkarea.setOnCheckedChangeListener(this);
        rbMessage.setOnCheckedChangeListener(this);
        rbSetting.setOnCheckedChangeListener(this);
        rbWorkarea.setChecked(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "(MainActivity.java:297) " + requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume(@Nullable Bundle args) {
        super.onResume(args);
        UpdateAppUtil.onResume(MainActivity.this);


        notifyMessage();
    }

    @Override
    public void setmessage(int messageNum, String soundFlag, String vibrateFlag) {
        super.setmessage(messageNum, "1", "1");


        Drawable drawable;
        if (messageNum < 1) {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_message_selector);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_havemessage_selector);
            drawable.setBounds(8, 0, drawable.getIntrinsicWidth() + 8, drawable.getIntrinsicHeight());
//            AppUtil.showNotification(MainActivity.this, new Intent(MainActivity.this, MainActivity.class));
        }
        rbMessage.setCompoundDrawables(null, drawable, null, null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras() != null) {
            if (intent.getExtras().getBoolean(CLICK_PENDING_INTENT)) {
                onCheckedChanged(R.id.rbMessage);
            }
        }
    }

    private void onCheckedChanged(int checkedId) {
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        switch (checkedId) {
            case R.id.rbWorkarea:
                switchMainTab(TAB_WORKAREA);
                setRbWorkareaTitle();
                break;

            case R.id.rbMessage:
                switchMainTab(TAB_MESSAGE);
                setRbMessageTitle();
                break;

            case R.id.rbSetting:
                switchMainTab(TAB_SETTING);
                setRbSettingTitle();
                break;

            default:
                break;
        }

    }

    public void switchMainTab(int targetIndex) {
        fragmentTransaction = mFragmentManager.beginTransaction();
        if (targetIndex == TAB_WORKAREA) {
            fragmentTransaction.show(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).commitAllowingStateLoss();

            rbWorkarea.setChecked(true);
            rbMessage.setChecked(false);
            rbSetting.setChecked(false);

        } else if (targetIndex == TAB_MESSAGE) {
            fragmentTransaction.show(mFragments[1]).hide(mFragments[0]).hide(mFragments[2]).commitAllowingStateLoss();

            rbWorkarea.setChecked(false);
            rbMessage.setChecked(true);
            rbSetting.setChecked(false);
        } else {
            fragmentTransaction.show(mFragments[2]).hide(mFragments[0]).hide(mFragments[1]).commitAllowingStateLoss();

            rbWorkarea.setChecked(false);
            rbMessage.setChecked(false);
            rbSetting.setChecked(true);
        }

    }

    public void setRbWorkareaTitle() {
        setToolbarCenterTitle(getString(R.string.tabbar_workarea));
    }

    public void setRbMessageTitle() {
        setToolbarCenterTitle(getString(R.string.tabbar_message));
    }

    public void setRbSettingTitle() {
        setToolbarCenterTitle(getString(R.string.tabbar_setting));
    }

    /**
     * 消息提醒
     */
    public void notifyMessage() {
        warning = SPStaticUtils.getString(SharedPreference.WARNING_TIME, "15");

        MessageApiManager.getNotifyMessage(new CommonCallBack<NotifyMessageBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(NotifyMessageBean bean, String type) {
                int messageNum = 0;
                for (NotifyMessageBean.NotifyMessageListBean b : bean.getNotifyMessageList()) {
                    try {
                        messageNum += Integer.parseInt(b.getMNum());
                    } catch (Exception e) {
                    }
                }
                setmessage(messageNum, "1", "1");
            }
        });
        MessageApiManager.getSkinTestMessage(new CommonCallBack<MessageSkinBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(MessageSkinBean bean, String type) {
                List<MessageSkinBean.SkinTimeListBean> skinTimeList = bean.getSkinTimeList();

                for (MessageSkinBean.SkinTimeListBean b : skinTimeList) {
                    //预警
                    if (!ObjectUtils.isEmpty(b.getOverTime())) {
                        int off = 0;
                        try {
                            off = Integer.valueOf(b.getOverTime());
                        } catch (Exception e) {

                        }
                        int integer = Integer.valueOf(warning);
                        boolean isNotify = 0 < off && off <= integer;
                        if (isNotify) {
                            String s = "测试=" + warning + "," + b.getOverTime() + "," + isNotify;
                            checkLockAndShowNotification(s);
                        }
                    }
                }
            }
        });
    }

    /**
     * 播放系统默认提示音
     * @return MediaPlayer对象
     * @throws Exception
     */
    public void defaultMediaPlayer(int type) {
        Ringtone r = null;
        try {
            Uri notification = RingtoneManager.getDefaultUri(type);
            r = RingtoneManager.getRingtone(this, notification);
            r.play();
            Ringtone finalR = r;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalR != null) {
                        finalR.stop();
                    }
                }
            }, 3000);
        } catch (Exception e) {
            if (r != null) {
                r.stop();
            }
        }
    }

    /**
     * 检查锁屏状态，如果锁屏先点亮屏幕
     * @param content
     */
    private void checkLockAndShowNotification(String content) {
        //管理锁屏的一个服务
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        //锁屏
        if (km != null && km.inKeyguardRestrictedInputMode()) {
            //获取电源管理器对象
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (pm != null && !pm.isScreenOn()) {
                @SuppressLint("InvalidWakeLockTag")
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                if (wl != null) {
                    wl.acquire();  //点亮屏幕
                    wl.release();  //任务结束后释放
                }
            }
            Log.e(TAG, "(MainActivity.java:352) 亮屏" + content);
            notification();
        } else {
            Log.e(TAG, "(MainActivity.java:352) " + content);
            notification();
        }
    }

    /**
     * 接收事件- 更新数据
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent event) {
        if (event.getType() == MessageEvent.MessageType.NOTIFY_MESSAGE) {
            Log.e(getClass().getSimpleName(), "updateData:" + event.getType());
            checkLockAndShowNotification("updateData:" + event.getType());
        }
    }

    private void notification() {

        Boolean bLight = SPUtils.getInstance().getBoolean(SharedPreference.LIGHT, true);
        Boolean bSound = SPUtils.getInstance().getBoolean(SharedPreference.SOUND, true);
        Boolean bVibrator = SPUtils.getInstance().getBoolean(SharedPreference.VIBRATOR, true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppUtil.showNotification(MainActivity.this, new Intent(MainActivity.this, MainActivity.class));
//                if (bSound) {
//                    defaultMediaPlayer(RingtoneManager.TYPE_RINGTONE);
//                }
            }
        }, 2000);
        if (bSound) {
            try {
                AppUtil.playSound(this, R.raw.notice_message);
            } catch (Exception e) {

            }
        }
        if (bVibrator) {
            VibrateUtils.vibrate(3000);
        }
    }

    /**
     * radiobutton 点击监听事件
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            onCheckedChanged(buttonView.getId());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序
                exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MainReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
                Log.e(TAG, "(MainReceiver.java:379) " + intent.getAction());
                notifyMessage();
            }
        }
    }

}
