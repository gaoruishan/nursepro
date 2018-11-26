package com.dhcc.nursepro.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.Activity.update.BaseDialog;
import com.dhcc.nursepro.Activity.update.api.UpdateApiManager;
import com.dhcc.nursepro.Activity.update.bean.UpdateBean;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.message.MessageFragment;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.setting.SettingFragment;
import com.dhcc.nursepro.workarea.MServiceNewOrd;
import com.dhcc.nursepro.workarea.WorkareaFragment;

import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity implements RadioButton.OnCheckedChangeListener {

    private static final int TAB_WORKAREA = 9001;
    private static final int TAB_MESSAGE = 9002;
    private static final int TAB_SETTING = 9003;
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

    // 新医嘱提示
    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spUtils = SPUtils.getInstance();

        //此处为暂时调用，应该在登录成功后初始化tabviews
        initTabView();

        Intent i = new Intent(this, MServiceNewOrd.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(i);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, MServiceNewOrd.class);
        stopService(i);
        if (mainReceiver != null) {
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
     *
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
     *
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
            //            Qnotify();
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
        mFragments[0] = new WorkareaFragment();
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

    public void notifyMessage() {
        MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
            @Override
            public void onSuccess(MessageBean msgs) {
                int messageNum = msgs.getNewOrdPatList().size() + msgs.getAbnormalPatList().size() + msgs.getConPatList().size();
                setmessage(messageNum);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void Qnotify() {


        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        //新建Notification.Builder对象
        Notification.Builder builder = new Notification.Builder(this);
        //PendingIntent点击通知后所跳转的页面
        PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        builder.setContentTitle("消息");
        builder.setContentText("有新医嘱！");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //执行intent
        builder.setContentIntent(intent);

        builder.setVibrate(new long[]{0, 2000, 500, 2000});

        builder.setDefaults(Notification.DEFAULT_SOUND);
        //        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //        builder.setSound(uri);

        builder.setDefaults(Notification.DEFAULT_LIGHTS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("to-do", "消息",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 2000, 500, 2000});
            channel.setSound(null, null);
            manager.createNotificationChannel(channel);
            builder.setChannelId("to-do");
        }

        //将builder对象转换为普通的notification
        Notification notification = builder.getNotification();
        //点击通知后通知消失
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //运行notification
        manager.notify(1, notification);

    }

    /**
     * radiobutton 点击监听事件
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            onCheckedChanged(buttonView.getId());
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
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MainReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.NEWMESSAGE_SERVICE:
                    notifyMessage();
                    break;
                default:
                    break;
            }

        }
    }

}
