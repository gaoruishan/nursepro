package com.dhcc.nursepro.Activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadFailedListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.bean.UpdateBean;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.BasePopWindow;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.bloodsugar.BloodSugarFragment;
import com.dhcc.module.nurse.education.HealthEduFragment;
import com.dhcc.module.nurse.nurplan.NurPlanFragment;
import com.dhcc.module.nurse.task.FileterPop;
import com.dhcc.module.nurse.task.TaskOverviewFragment;
import com.dhcc.nursepro.Activity.update.BaseDialog;
import com.dhcc.nursepro.Activity.update.api.UpdateApiManager;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.login.bean.NurseInfo;
import com.dhcc.nursepro.message.MessageFragment;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.setting.SettingBedsFragment;
import com.dhcc.nursepro.setting.SettingWayFragment;
import com.dhcc.nursepro.view.PatIconView;
import com.dhcc.nursepro.workarea.allotbed.AllotBedFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapPatFragment;
import com.dhcc.nursepro.workarea.bedmap.BedMapPatInfoFragment;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatientAdapter;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.bedselect.BedSelectFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodTransfusionSystemFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment;
import com.dhcc.nursepro.workarea.docorderlist.DocOrderListFragment;
import com.dhcc.nursepro.workarea.dosingreview.DosingReviewFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.DrugHandoverFragment;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.RLRegFragment;
import com.dhcc.nursepro.workarea.infusiondrugreceive.DrugReceiveFragment;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.labresult.LabResultListFragment;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkLoopSystemFragment;
import com.dhcc.nursepro.workarea.motherbabylink.MotherBabyLinkFragment;
import com.dhcc.nursepro.workarea.nurrecordnew.PatNurRecordFragment;
import com.dhcc.nursepro.workarea.nurtour.NurTourFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecuteFragment;
import com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.plyout.PlyOutListFragment;
import com.dhcc.nursepro.workarea.rjorder.RjOrderFragment;
import com.dhcc.nursepro.workarea.shift.ShiftFragment;
import com.dhcc.nursepro.workarea.taskmanage.TaskManageFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment;
import com.dhcc.nursepro.workarea.workareautils.MServiceNewOrd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class SingleMainActivity extends BaseActivity implements RadioButton.OnCheckedChangeListener, View.OnClickListener {

    private static final int TAB_WORKAREA = 9001;
    private static final int TAB_MESSAGE = 9002;
    private static final int TAB_SETTING = 9003;
    private FragmentManager mFragmentManager;
    private FragmentTransaction ft;
    private Fragment[] mFragments;
    private BaseFragment curFragment = new BaseFragment();
    private FragmentTransaction fragmentTransaction;
    private ProgressDialog pd;
    private SharedPreferences sp;

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
    private NotificationManager notificationManager;

    private BedMapBean bedBean = new BedMapBean();
    private Map bedMap;
    private TextView tvBedmappatBedno;
    private TextView tvAge;
    private TextView tvBedmappatName;
    private TextView tvNurse,tvTopName;
    private RelativeLayout rlPat,rlHindPat;
    private ImageView imgBedmappatSex;
    private TextView tvBedmappatCarelevel;
    private PatIconView patIconView;
    private TextView tvLoc;

    protected View contentView,contentView1;
    private LinearLayout llPatArea;
    private TextView tvPatArea,tvPrePat,tvNextPat;

    private String fragName;
    private String episodeId = "",regNo = "",strPatArea = "",patInfo = "";
    private Boolean isGetScanPat = false;
    private DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private List<NurseInfo> nurseInfoList;
    private NurseInfo loginNurseInfo;
    private List<Map<String, String>> locsList = new ArrayList<>();
    //所有患者信息
    private List<Map<String, String>> patInfoMapList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_single);
        spUtils = SPUtils.getInstance();

        //此处为暂时调用，应该在登录成功后初始化tabviews
        initTabView();

        Intent i = new Intent(this, MServiceNewOrd.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(i);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //应用无操作定时退出
        int exitTime = spUtils.getInt(SharedPreference.EXITTIME, 0);
        if (exitTime > 0) {
            startTimer();
        }
        //请求SD卡权限
        PermissionUtils.permission(PermissionConstants.STORAGE).request();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, MServiceNewOrd.class);
        stopService(i);
        FileterPop.closePopWindow();
        SharedPreference.FRAGMENTMAP = null;

        if (notificationManager != null) {
            notificationManager.cancel(1);
        }
        if (mainReceiver != null) {
            unregisterReceiver(mainReceiver);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //后台 不接收消息
//        if (mainReceiver != null) {
//            unregisterReceiver(mainReceiver);
//        }
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        BedMapApiManager.getBedMap(new BedMapApiManager.GetBedMapCallback() {
            @Override
            public void onSuccess(BedMapBean bedMapBean, Map bedMapMap) {
                hideLoadingTip();
                bedMap= bedMapMap;
                for (int i = 0; i < bedMapBean.getPatInfoList().size(); i++) {
                    Map map = GsonUtils.fromJson(GsonUtils.toJson(bedMapBean.getPatInfoList().get(i)), HashMap.class);
                    bedMapBean.getPatInfoList().get(i).setPatMap(map);
                }
                patInfoMapList = (List<Map<String, String>>) bedMapMap.get("patInfoList");
                bedBean = bedMapBean;
                if (bedMapPatientAdapter!=null){
                    bedMapPatientAdapter.setNewData(bedBean.getPatInfoList());
                }
                episodeId = bedMapBean.getPatInfoList().get(0).getEpisodeId();
                regNo = bedMapBean.getPatInfoList().get(0).getRegNo();
                patInfo = bedMapBean.getPatInfoList().get(0).getBedCode()+" "+bedMapBean.getPatInfoList().get(0).getName();
                showPatInfo();
                addToolBarRightPopWindow();
                addToolBarLeftPopWindow();
                notifyMessage();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
                SPUtils.getInstance().put(SharedPreference.SINGLEMODEL,"0");
                startActivity(new Intent(SingleMainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void showPatInfo(){
        BedMapBean.PatInfoListBean patInfoListBean = null;        for (int i = 0; i < bedBean.getPatInfoList().size(); i++) {
            if (bedBean.getPatInfoList().get(i).getEpisodeId().equals(episodeId)){
                patInfoListBean = bedBean.getPatInfoList().get(i);
            }
        }
        tvBedmappatBedno.setText(patInfoListBean.getBedCode().isEmpty() ? "未分床" : patInfoListBean.getBedCode().replace("床","")+"床");
        tvAge.setText(patInfoListBean.getAge());
        tvBedmappatName.setText(patInfoListBean.getName());
        tvTopName.setText((patInfoListBean.getBedCode().isEmpty() ? "未分床" : patInfoListBean.getBedCode().replace("床","")+"床")
                +patInfoListBean.getName());
        tvBedmappatCarelevel.setText(patInfoListBean.getCareLevel());
        if ("男".equals(patInfoListBean.getSex())) {
            imgBedmappatSex.setImageResource(R.drawable.sex_male);
        } else {
            imgBedmappatSex.setImageResource(R.drawable.sex_female);
        }
        patIconView.setIconShow(patInfoListBean);
        patIconView.showLeft();
        patIconView.setVisibility(View.VISIBLE);
        if (fragName==null){
            fragName = OrderExecuteFragment.class.getName();
            startNewFragment(new OrderExecuteFragment());
        }else {
            putNewFragment();
            startNewFragment(SharedPreference.FRAGMENTMAP.get(fragName));
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
            mainfilter.addAction(Action.SINGLEMAP);
            mainfilter.addAction(Action.SETSINGLEMSG);
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
                        builder.executeMission(SingleMainActivity.this);
                        hasRequest = 1;
                        canUpdate = 1;

                    } else {
                        Toast.makeText(SingleMainActivity.this, "当前为最新版本", Toast.LENGTH_SHORT).show();
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
    public void setmessage(int messageNum, String soundFlag, String vibrateFlag) {
        super.setmessage(messageNum, soundFlag, vibrateFlag);


        Drawable drawable;
        if (messageNum < 1) {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_message_selector);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            if (contentView1!=null){
                contentView1.findViewById(R.id.view_point_red).setVisibility(View.GONE);
            }
        } else {
            drawable = getResources().getDrawable(R.drawable.tabbar_item_havemessage_selector);
            drawable.setBounds(8, 0, drawable.getIntrinsicWidth() + 8, drawable.getIntrinsicHeight());
            if (contentView1!=null){
                contentView1.findViewById(R.id.view_point_red).setVisibility(View.VISIBLE);
            }
            showNotification(this, soundFlag, vibrateFlag);
        }
    }

    /**
     * 初始化各模块界面
     */
    private void initTabView() {
        tvLoc = findViewById(R.id.tv_setting_loc);
        tvLoc.setOnClickListener(this);
        tvLoc.setText(spUtils.getString(SharedPreference.LOCDESC));

        tvBedmappatBedno = findViewById(R.id.tv_bedmappat_bedno);
        tvAge = findViewById(R.id.tv_bedmappat_age);
        tvBedmappatName = findViewById(R.id.tv_bedmappat_name);
        tvBedmappatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < patInfoMapList.size(); i++) {
                    if (patInfoMapList.get(i).get("episodeId").equals(episodeId)){
                        patInfoMapList = (List<Map<String, String>>) bedMap.get("patInfoList");
                        Gson gson = new Gson();
                        Map map = patInfoMapList.get(i);
                        String jsonMap = gson.toJson(map);
                        Bundle bundle = new Bundle();
                        bundle.putString("jsonmap",jsonMap);
                        startFragment(BedMapPatInfoFragment.class,bundle);
                    }
                }
            }
        });
        tvTopName = findViewById(R.id.tv_top_patinfo);
        tvNurse = findViewById(R.id.tv_setting_username);
        rlHindPat = findViewById(R.id.rl_hindpat);
        rlHindPat.setOnClickListener(this);
        rlPat = findViewById(R.id.rl_patinfo);
        tvNurse.setText(spUtils.getString(SharedPreference.USERNAME));
        imgBedmappatSex = findViewById(R.id.img_bedmappat_sex);
        tvBedmappatCarelevel = findViewById(R.id.tv_bedmappat_carelevel);
        patIconView = findViewById(R.id.paticon_patinfo);
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskLeftShow(contentView1,0.2f);
            }
        });
        findViewById(R.id.tv_patmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskShow();
            }
        });
        asyncInitData();

    }
    private void startNewFragment(BaseFragment baseFragment){
        if ((System.currentTimeMillis() - mExitTime) > 1000) {
            //大于2000ms才可切换
            baseFragment.singleEpisodeId = episodeId;
            baseFragment.singleRegNo = regNo;
            baseFragment.singlePatInfo = patInfo;
            if (isGetScanPat){
                baseFragment.isGetPatByScan = true;
            }
            isGetScanPat = false;
            if (baseFragment.getClass().getName().equals(OrderExecuteFragment.class.getName())){
                baseFragment.isChangePat = false;
            }
            baseFragment.listRegNo = new ArrayList<>();
            for (int i = 0; i < bedBean.getPatInfoList().size(); i++) {
                baseFragment.listRegNo.add(bedBean.getPatInfoList().get(i).getRegNo());
            }

            mFragmentManager = getSupportFragmentManager();
            ft = mFragmentManager.beginTransaction();
            List<Fragment> oldFragments = mFragmentManager.getFragments();

            if (oldFragments != null && oldFragments.size() > 0) {
                for (Fragment old : oldFragments) {
                    if (old != null) {
                        ft.remove(old);
                    }
                }
            }

            mFragments = new Fragment[1];
            mFragments[0] =baseFragment;

            ft.add(R.id.fragment, mFragments[0], "");
            ft.commitAllowingStateLoss();

            setFragmentIndicator();
            curFragment = baseFragment;
            //并记录下本次点击“返回键”的时刻，以便下次进行判断
            mExitTime = System.currentTimeMillis();
        } else {
            isGetScanPat = false;
            showToast("操作过于频繁，请重试");
        }

    }

    /**
     * 设置主界面底部栏指示图标
     */
    private void setFragmentIndicator() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        onParseIntent();

    }

    private void onParseIntent() {
        Intent messageIntent = getIntent();
        if (messageIntent != null) {
            if (messageIntent.getIntExtra("message", 0) == 1) {
                setRbMessageTitle();
            }
        }
    }


    public void setRbMessageTitle() {
        setToolbarCenterTitle(getString(R.string.tabbar_message));
    }

    public void notifyMessage() {
        MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
            @Override
            public void onSuccess(MessageBean msgs) {
                int messageNum = (msgs.getNewOrdPatList() != null ? msgs.getNewOrdPatList().size() : 0)
                        + (msgs.getAbnormalPatList() != null ? msgs.getAbnormalPatList().size() : 0)
                        + (msgs.getConPatList() != null ? msgs.getConPatList().size() : 0);
                setmessage(messageNum, msgs.getSoundFlag(), msgs.getVibrateFlag());

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void showNotification(Context context, String soundFlag, String vibrateFlag) {
        Boolean bLight = spUtils.getBoolean(SharedPreference.LIGHT, true);
//        Boolean bSound = spUtils.getBoolean(SharedPreference.SOUND, true);
//        Boolean bVibrator = spUtils.getBoolean(SharedPreference.VIBRATOR, true);
        Boolean bSound = spUtils.getBoolean(SharedPreference.LIGHT, true) && soundFlag.equals("1");
        Boolean bVibrator = spUtils.getBoolean(SharedPreference.LIGHT, true) && vibrateFlag.equals("1");

        NotificationCompat.Builder builder = null;
        NotificationChannel channel1, channel2;
        if (Build.VERSION.SDK_INT >= 26) {
            if (notificationManager != null) {
                notificationManager.deleteNotificationChannel("mobilenurse1");
                notificationManager.deleteNotificationChannel("mobilenurse2");
            }
            channel1 = new NotificationChannel("mobilenurse1", "响铃消息", NotificationManager.IMPORTANCE_HIGH);
            channel2 = new NotificationChannel("mobilenurse2", "静音消息", NotificationManager.IMPORTANCE_HIGH);


            if (notificationManager != null) {
                //Android8.0消息提示音是否响铃配置 还需处理
                if (bSound) {
                    channel1.getAudioAttributes();
                    notificationManager.createNotificationChannel(channel1);
                    builder = new NotificationCompat.Builder(context, "mobilenurse1");
                } else {
                    channel2.setSound(null, null);
                    notificationManager.createNotificationChannel(channel2);
                    builder = new NotificationCompat.Builder(context, "mobilenurse2");
                }
            }
        } else {
            builder = new NotificationCompat.Builder(context);
        }


        Intent intent = new Intent(context, SingleMainActivity.class);
        intent.putExtra("message", 1);

        /**设置通知左边的大图标**/
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
                /**设置通知右边的小图标**/
                .setSmallIcon(R.drawable.ic_launcher)
                /**通知首次出现在通知栏，带上升动画效果的**/
                .setTicker("通知")
                /**设置通知的标题**/
                .setContentTitle("新消息")
                /**设置通知的内容**/
                .setContentText("请点击进入消息页面查看")
                /**通知产生的时间，会在通知信息里显示**/
                .setWhen(System.currentTimeMillis())
                /**设置该通知优先级**/
                .setPriority(Notification.PRIORITY_DEFAULT)
                /**设置这个标志当用户单击面板就可以让通知将自动取消**/
                .setAutoCancel(true)
                /**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**/
                .setOngoing(false)
                /**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**/
                //                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setContentIntent(PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT));

        Notification notification = builder.build();

        if (bVibrator) {
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (bSound) {
            notification.defaults |= Notification.DEFAULT_SOUND;
        }
        if (bLight) {
            notification.defaults |= Notification.DEFAULT_LIGHTS;
        }
//        defaultMediaPlayer();
        //      发起通知
        if (notificationManager != null) {
            notificationManager.notify(1, notification);
        }
    }

    /**
     * 播放系统默认提示音
     *
     * @return MediaPlayer对象
     * @throws Exception
     */
    public void defaultMediaPlayer() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, notification);
            r.play();
        } catch (Exception e) {

        }
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
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void setTvPatHindMapTop(int Top) {
        if (rlHindPat!=null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlHindPat.getLayoutParams();
            layoutParams.topMargin=dp2px(Top);
            rlHindPat.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onClick(View v) {
        setMaskHind();
        if (v.getId() == R.id.rl_hindpat){
            if (rlHindPat.isSelected()){
                rlHindPat.setSelected(false);
                tvTopName.setVisibility(View.GONE);
                rlPat.setVisibility(View.VISIBLE);
                setTvPatHindMapTop(25);
            }else {
                rlHindPat.setSelected(true);
                tvTopName.setVisibility(View.VISIBLE);
                rlPat.setVisibility(View.GONE);
                setTvPatHindMapTop(55);
            }
        }
        if (v.getId() == R.id.tv_single_msg){
            startFragment(MessageFragment.class);
        }
        if (v.getId() == R.id.tv_single_bed){
            startFragment(SettingBedsFragment.class,1);
        }
        if (v.getId() == R.id.tv_single_notice){
            startFragment(SettingWayFragment.class);
        }
        if (v.getId() == R.id.tv_single_areamodel){
            spUtils.put(SharedPreference.SINGLEMODEL,"0");
            Intent i = new Intent(SingleMainActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        if (v.getId() == R.id.tv_single_relogin){
            Intent i = new Intent(SingleMainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        if (v.getId() == R.id.tv_setting_loc){
            nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();;
            if (nurseInfoList != null && nurseInfoList.size() > 0) {
                String userCode = spUtils.getString(SharedPreference.USERCODE);
                String logonWardId = spUtils.getString(SharedPreference.WARDID);
                for (int i = 0; i < nurseInfoList.size(); i++) {
                    NurseInfo nurseInfo = nurseInfoList.get(i);
                    if (userCode.equals(nurseInfo.getUserCode())) {
                        logonWardId = nurseInfo.getWardId();
                        loginNurseInfo = nurseInfo;
                        changeLoc();
                        break;
                    }
                }
            }
        }

        if (v.getId() == R.id.tv_pat_pre){


        }
        if (v.getId() == R.id.tv_pat_next){

        }
    }



    /**
     * 切换病区
     */
    private void changeLoc() {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        locsList = new ArrayList<>();
        String LocJson = spUtils.getString(SharedPreference.LOCSLISTJSON);
        locsList = gson.fromJson(LocJson, type);
        String[] locDesc = new String[locsList.size()];
        for (int i = 0; i < locsList.size(); i++) {
            locDesc[i] = locsList.get(i).get("LocDesc");
        }
        final OptionPicker picker = new OptionPicker(this, locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        Boolean ifLocRem = true;
        for (int i = 0; i <locDesc.length ; i++) {
            if (spUtils.getString(SharedPreference.LOCDESC).equals(locDesc[i])){
                picker.setSelectedIndex(i);
                ifLocRem = false;
            }
        }
        if (ifLocRem){
            picker.setSelectedIndex(0);
        }
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                loginNurseInfo.setGroupDesc(locsList.get(index).get("GroupDesc"));
                loginNurseInfo.setGroupId(locsList.get(index).get("GroupId"));
                loginNurseInfo.setHospitalRowId(locsList.get(index).get("HospitalRowId"));
                loginNurseInfo.setLinkLoc(locsList.get(index).get("LinkLoc"));
                loginNurseInfo.setLocDesc(locsList.get(index).get("LocDesc"));
                loginNurseInfo.setLocId(locsList.get(index).get("LocId"));
                loginNurseInfo.setWardId(locsList.get(index).get("WardId"));
                //
                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    int j;
                    String userCode = spUtils.getString(SharedPreference.USERCODE);
                    for (j = 0; j < nurseInfoList.size(); j++) {
                        NurseInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            //                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
                            loginNurseInfo.setId(nurseInfo1.getId());
                            daoSession.getNurseInfoDao().update(loginNurseInfo);

                            spUtils.put(SharedPreference.HOSPITALROWID, loginNurseInfo.getHospitalRowId());
                            spUtils.put(SharedPreference.GROUPID, loginNurseInfo.getGroupId());
                            spUtils.put(SharedPreference.GROUPDESC, loginNurseInfo.getGroupDesc());
                            spUtils.put(SharedPreference.LINKLOC, loginNurseInfo.getLinkLoc());
                            spUtils.put(SharedPreference.LOCID, loginNurseInfo.getLocId());
                            spUtils.put(SharedPreference.LOCDESC, loginNurseInfo.getLocDesc());
                            spUtils.put(SharedPreference.WARDID, loginNurseInfo.getWardId());

                            tvLoc.setText(loginNurseInfo.getLocDesc());
                            notifyMessage();
                            asyncInitData();
                            break;
                        }
                    }

                    if (j >= nurseInfoList.size()) {
                        //                                    Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                    }

                } else {
                    //                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                    daoSession.getNurseInfoDao().insert(loginNurseInfo);
                }
            }
        });
        picker.show();
    }

    /**
     * 广播接收
     * NEWMESSAGE_SERVICE：消息刷新
     *SINGLEMAP：切换fragment
     * SETSINGLEMSG：患者切换
     */
    public class MainReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
                notifyMessage();
            }
            if (Action.SINGLEMAP.equals(intent.getAction())) {
                putNewFragment();
                if (SharedPreference.FRAGMENTMAP.get(intent.getStringExtra("data"))!=null){
                    fragName = intent.getStringExtra("data");
                    startNewFragment(SharedPreference.FRAGMENTMAP.get(fragName));
                }
            }
            if (Action.SETSINGLEMSG.equals(intent.getAction())) {
                for (int i = 0; i < bedBean.getPatInfoList().size(); i++) {
                    String msg = intent.getStringExtra("data");
                    if (msg.equals(bedBean.getPatInfoList().get(i).getRegNo())){
                        episodeId = bedBean.getPatInfoList().get(i).getEpisodeId();
                        regNo = bedBean.getPatInfoList().get(i).getRegNo();
                        patInfo = bedBean.getPatInfoList().get(i).getBedCode()+" "+bedBean.getPatInfoList().get(i).getName();
                        isGetScanPat = true;
                        showPatInfo();
                    }
                }
            }
        }
    }

    /**
     * 左右侧popWindow，左侧设置，右侧选床
     */
    private BedMapPatientAdapter bedMapPatientAdapter = new BedMapPatientAdapter(new ArrayList<>());
    public void addToolBarRightPopWindow() {
        contentView = LayoutInflater.from(this).inflate(R.layout.dhcc_task_filter_single_map_layout, null);
        RecyclerView recPat = contentView.findViewById(R.id.rec_task_pat);
        bedMapPatientAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                episodeId = bedMapPatientAdapter.getItem(position).getEpisodeId();
                regNo = bedMapPatientAdapter.getItem(position).getRegNo();
                patInfo = bedBean.getPatInfoList().get(position).getBedCode()+" "+bedBean.getPatInfoList().get(position).getName();
                showPatInfo();
                setMaskHind();
                rlHindPat.setSelected(false);
                tvTopName.setVisibility(View.GONE);
                rlPat.setVisibility(View.VISIBLE);
                setTvPatHindMapTop(25);
            }
        });
        //提高展示效率
        recPat.setHasFixedSize(true);
        //设置的布局管理
        recPat.setLayoutManager(new LinearLayoutManager(this));
        recPat.setAdapter(bedMapPatientAdapter);
        llPatArea = contentView.findViewById(R.id.ll_patarea);
        tvPrePat = contentView.findViewById(R.id.tv_pat_pre);
        tvPrePat.setOnClickListener(this);
        tvNextPat = contentView.findViewById(R.id.tv_pat_next);
        tvNextPat.setOnClickListener(this);
        tvPatArea = contentView.findViewById(R.id.tv_patarea_select);
        tvPatArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llPatArea.setVisibility(llPatArea.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
            }
        });
        llPatArea.removeAllViews();
        if (bedBean.getTopFilter().size()>0){
            strPatArea = bedBean.getTopFilter().get(0).getDesc();
        }
        for (int i = 0; i < bedBean.getTopFilter().size(); i++) {
            TextView tvButton = new TextView(SingleMainActivity.this);
            tvButton.setText(bedBean.getTopFilter().get(i).getDesc());
            tvButton.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(90), ViewGroup.LayoutParams.MATCH_PARENT);
            tvButton.setLayoutParams(titleParams);
            tvButton.setBackgroundResource(R.color.blue);
            tvButton.setTextColor(getResources().getColor(R.color.white));
            tvButton.setPadding(0,30,0,30);

            tvButton.setTag(R.string.key_singleflag,bedBean.getTopFilter().get(i).getCode());

            llPatArea.addView(tvButton);

            tvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llPatArea.setVisibility(View.GONE);
                    ArrayList<BedMapBean.PatInfoListBean> listBeans = new ArrayList<>();
                    for (int j = 0; j < bedBean.getPatInfoList().size(); j++) {
                        if (bedBean.getPatInfoList().get(j).getPatMap().get(tvButton.getTag(R.string.key_singleflag).toString())!=null
                                && "1".equals(bedBean.getPatInfoList().get(j).getPatMap().get(tvButton.getTag(R.string.key_singleflag).toString()))){
                            listBeans.add(bedBean.getPatInfoList().get(j));
                        };
                    }
                    bedMapPatientAdapter.setNewData(listBeans);
                    tvPatArea.setText(tvButton.getText().toString());
                }
            });
        }
    }
    public void setMaskShow(){
        setMaskShow(contentView,0.7f);
    }

    public void setMaskShow(View contentView){
        FileterPop.setMask(this, View.VISIBLE);
        FileterPop.initPopupWindow(this, BasePopWindow.EnumLocation.RIGHT, contentView);
    }

    /**
     * 指定弹框宽度
     * @param contentView
     * @param screenWidth
     */
    public void setMaskShow(View contentView,double screenWidth){
        FileterPop.setMask(this, View.VISIBLE);
        FileterPop.initPopupWindow(this, BasePopWindow.EnumLocation.RIGHT, contentView,screenWidth);
    }

    public void setMaskHind(){
        FileterPop.setMask(this, View.INVISIBLE);
        FileterPop.closePopWindow();
    }
    public void addToolBarLeftPopWindow() {
        contentView1 = LayoutInflater.from(this).inflate(R.layout.dhcc_task_filter_single_settinng, null);
        contentView1.findViewById(R.id.tv_single_msg).setOnClickListener(this);
        contentView1.findViewById(R.id.tv_single_bed).setOnClickListener(this);
        contentView1.findViewById(R.id.tv_single_areamodel).setOnClickListener(this);
        contentView1.findViewById(R.id.tv_single_notice).setOnClickListener(this);
        contentView1.findViewById(R.id.tv_single_relogin).setOnClickListener(this);
    }

    /**
     * 指定弹框宽度
     * @param contentView
     * @param screenWidth
     */
    public void setMaskLeftShow(View contentView,double screenWidth){
//        FileterPop.setMask(this, View.VISIBLE);
        FileterPop.initPopupWindow(this, BasePopWindow.EnumLocation.LEFT, contentView,screenWidth);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data.getStringExtra("datetime") != null){
                curFragment.setMsgToSingleBaseFragment(data.getStringExtra("datetime"));
            }else {
                asyncInitData();
            }

        }
    }
    private void putNewFragment() {
        SharedPreference.FRAGMENTMAP = new HashMap();
        SharedPreference.FRAGMENTMAP.put(BedMapFragment.class.getName(), new BedMapFragment());
        SharedPreference.FRAGMENTMAP.put(VitalSignFragment.class.getName(), new VitalSignFragment());
        SharedPreference.FRAGMENTMAP.put(VitalSignRecordFragment.class.getName(), new VitalSignRecordFragment());
        SharedPreference.FRAGMENTMAP.put(PatEventsFragment.class.getName(), new PatEventsFragment());
        SharedPreference.FRAGMENTMAP.put(OrderSearchFragment.class.getName(), new OrderSearchFragment());
        SharedPreference.FRAGMENTMAP.put(OrderExecuteFragment.class.getName(), new OrderExecuteFragment());
        SharedPreference.FRAGMENTMAP.put(CheckPatsFragment.class.getName(), new CheckPatsFragment());
        SharedPreference.FRAGMENTMAP.put(CheckResultListFragment.class.getName(), new CheckResultListFragment());
        SharedPreference.FRAGMENTMAP.put(LabPatsFragment.class.getName(), new LabPatsFragment());
        SharedPreference.FRAGMENTMAP.put(LabResultListFragment.class.getName(), new LabResultListFragment());
        SharedPreference.FRAGMENTMAP.put(OperationFragment.class.getName(), new OperationFragment());
        SharedPreference.FRAGMENTMAP.put(LabOutListFragment.class.getName(), new LabOutListFragment());
        SharedPreference.FRAGMENTMAP.put(DosingReviewFragment.class.getName(), new DosingReviewFragment());
        SharedPreference.FRAGMENTMAP.put(AllotBedFragment.class.getName(), new AllotBedFragment());
        SharedPreference.FRAGMENTMAP.put(DocOrderListFragment.class.getName(), new DocOrderListFragment());
        SharedPreference.FRAGMENTMAP.put(BloodTransfusionSystemFragment.class.getName(), new BloodTransfusionSystemFragment());
        SharedPreference.FRAGMENTMAP.put(MilkLoopSystemFragment.class.getName(), new MilkLoopSystemFragment());
        SharedPreference.FRAGMENTMAP.put(MotherBabyLinkFragment.class.getName(), new MotherBabyLinkFragment());
        SharedPreference.FRAGMENTMAP.put(PatNurRecordFragment.class.getName(), new PatNurRecordFragment());
        SharedPreference.FRAGMENTMAP.put(NurTourFragment.class.getName(), new NurTourFragment());
        SharedPreference.FRAGMENTMAP.put(DrugHandoverFragment.class.getName(), new DrugHandoverFragment());
        SharedPreference.FRAGMENTMAP.put(RLRegFragment.class.getName(), new RLRegFragment());
        SharedPreference.FRAGMENTMAP.put(ShiftFragment.class.getName(), new ShiftFragment());
        SharedPreference.FRAGMENTMAP.put(DrugReceiveFragment.class.getName(), new DrugReceiveFragment());
        SharedPreference.FRAGMENTMAP.put(TaskManageFragment.class.getName(), new TaskManageFragment());
        SharedPreference.FRAGMENTMAP.put(PlyOutListFragment.class.getName(), new PlyOutListFragment());
        SharedPreference.FRAGMENTMAP.put(RjOrderFragment.class.getName(), new RjOrderFragment());
        SharedPreference.FRAGMENTMAP.put(HealthEduFragment.class.getName(), new HealthEduFragment());
        SharedPreference.FRAGMENTMAP.put(TaskOverviewFragment.class.getName(), new TaskOverviewFragment());
        SharedPreference.FRAGMENTMAP.put(NurPlanFragment.class.getName(), new NurPlanFragment());
        SharedPreference.FRAGMENTMAP.put(BloodSugarFragment.class.getName(), new BloodSugarFragment());
        SharedPreference.FRAGMENTMAP.put(OrderSearchAndExecuteFragment.class.getName(), new OrderSearchAndExecuteFragment());
    }
}
