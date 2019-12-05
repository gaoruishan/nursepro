package com.dhcc.infusion;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadFailedListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.service.MServiceNewOrd;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.VibrateUtils;
import com.dhcc.infusion.update.BaseDialog;
import com.dhcc.infusion.update.api.UpdateApiManager;
import com.dhcc.infusion.update.bean.UpdateBean;
import com.dhcc.module.infusion.message.MessageFragment;
import com.dhcc.module.infusion.message.api.MessageApiManager;
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, MServiceNewOrd.class);
        stopService(i);
        //注册事件总线
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "(MainActivity.java:297) " + requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mainReceiver != null && !UserUtil.isMsgNoticeFlag()) {
            unregisterReceiver(mainReceiver);
        }
    }

    @Override
    protected void onResume(@Nullable Bundle args) {
        super.onResume(args);
        if (hasRequest == 0) {
            getNewVersion();
        } else if (hasRequest == 1 && canUpdate == 1) {
            getNewVersion();
        }

        if (mainReceiver != null) {
            mainfilter.addAction(Action.NEWMESSAGE_SERVICE);
            registerReceiver(mainReceiver, mainfilter);
        }
    }

    /**
     * 更新
     * <p>
     * https://github.com/AlexLiuSheng/CheckVersionLib/blob/master/README_UN.MD
     */
    private void getNewVersion() {
        wardId = spUtils.getString(SharedPreference.WARDID);
        ip = spUtils.getString(SharedPreference.WEBIP);
        version = getVersion();
        if ("无法获取当前版本号".equals(version)) {
            Toast.makeText(this, "获取当前版本号失败", Toast.LENGTH_SHORT).show();
            canUpdate = 0;
        } else {
            // TODO 需要修改
            UpdateApiManager.getNewVersion(wardId, ip, version, new UpdateApiManager.GetNewVersionCallback() {
                @Override
                public void onSuccess(UpdateBean updateBean) {
                    if (Integer.valueOf(updateBean.getNewVersion()) > Integer.valueOf(version)) {
                        String appUrl = updateBean.getAppAddress();

                        //                    String appUrl = "http://test-1251233192.coscd.myqcloud.com/1_1.apk";
                        DownloadBuilder builder = AllenVersionChecker.getInstance().downloadOnly(crateUIData(appUrl));
                        builder.setCustomVersionDialogListener(createCustomUpdateDialog());
                        builder.setCustomDownloadFailedListener(createCustomDownloadFailedDialog());
                        builder.setShowNotification(false);
                        builder.setDownloadAPKPath(Environment.getExternalStorageDirectory() + "/NurseProAPK/");
                        builder.setApkName("NursePro");
                        builder.setNewestVersionCode(Integer.valueOf(updateBean.getNewVersion()));
                        builder.executeMission(MainActivity.this);
                        hasRequest = 1;
                        canUpdate = 1;

                    } else {
                        Toast.makeText(MainActivity.this, "当前为最新版本", Toast.LENGTH_SHORT).show();
                        hasRequest = 1;
                        canUpdate = 0;
                    }
                }

                @Override
                public void onFail(String code, String msg) {
                    showToast("error" + code + ":" + msg);
                    canUpdate = 0;
                }
            });
        }
    }

    //查询当前版本
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionCode + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取当前版本号";
        }
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData(String appUrl) {
        UIData uiData = UIData.create();
        uiData.setTitle("系统升级");
        uiData.setDownloadUrl(appUrl);
        uiData.setContent("检查到有新版本，请下载使用");
        return uiData;
    }

    /**
     * 务必用库传回来的context 实例化你的dialog
     * 自定义的dialog UI参数展示，使用versionBundle
     * @return
     */
    private CustomVersionDialogListener createCustomUpdateDialog() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.dialog_update);
            TextView title = baseDialog.findViewById(R.id.tv_title);
            title.setText(versionBundle.getTitle());
            TextView content = baseDialog.findViewById(R.id.tv_msg);
            content.setText(versionBundle.getContent());
            baseDialog.setCanceledOnTouchOutside(false);
            baseDialog.setCancelable(false);
            return baseDialog;
        };
    }

    /**
     * 务必用库传回来的context 实例化你的dialog
     * @return
     */
    private CustomDownloadFailedListener createCustomDownloadFailedDialog() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.dialog_update_downloadfailed);
            baseDialog.setCanceledOnTouchOutside(false);
            baseDialog.setCancelable(false);
            return baseDialog;
        };
    }

    @Override
    public void setmessage(int messageNum) {
        super.setmessage(messageNum);


        Drawable drawable;
        if (messageNum < 1) {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_message_selector);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_havemessage_selector);
            drawable.setBounds(8, 0, drawable.getIntrinsicWidth() + 8, drawable.getIntrinsicHeight());
            AppUtil.showNotification(MainActivity.this,new Intent(MainActivity.this, MainActivity.class));
        }
        rbMessage.setCompoundDrawables(null, drawable, null, null);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras() != null) {
            if (intent.getExtras().getBoolean(CLICK_PENDING_INTENT)) {
                onCheckedChanged(R.id.rbMessage);
            }
        }
    }

    private void onCheckedChanged(int checkedId) {
        boolean tabWorkareaChecked = rbWorkarea.isChecked();
        boolean tabMessageChecked = rbMessage.isChecked();
        boolean tabSettingChecked = rbSetting.isChecked();

        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        switch (checkedId) {
            case R.id.rbWorkarea:
                //                setStatusBarBackgroundViewVisibility(false, 0xffffffff);
                //                setToolbarType(BaseActivity.ToolbarType.HIDE);
                switchMainTab(TAB_WORKAREA);
                setRbWorkareaTitle();
                break;

            case R.id.rbMessage:
                //                setStatusBarBackgroundViewVisibility(true, 0xffffffff);
                //                setToolbarType(ToolbarType.TOP);
                switchMainTab(TAB_MESSAGE);
                setRbMessageTitle();
                break;

            case R.id.rbSetting:
                //                setStatusBarBackgroundViewVisibility(true, 0xffffffff);
                ////                setToolbarType(ToolbarType.TOP);
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
                setmessage(messageNum);
            }
        });
    }


    /**
     * 接收事件- 更新数据
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent event) {
        if (event.getType() == MessageEvent.MessageType.NOTIFY_MESSAGE) {
            Log.e(getClass().getSimpleName(), "updateData:" + event.getType());

            AppUtil.playSound(this, R.raw.notice_message);
            VibrateUtils.vibrate(3000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppUtil.showNotification(MainActivity.this,new Intent(MainActivity.this, MainActivity.class));
                }
            }, 2000);
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
                notifyMessage();
            }
        }
    }

}
