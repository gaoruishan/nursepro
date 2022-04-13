package com.dhcc.nursepro.login;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.listener.CustomTextWatcher;
import com.base.commlibs.log.NurLogFragment;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.base.commlibs.utils.UserUtil;
import com.base.commlibs.voiceUtils.SetVoiceIPDialog;
import com.base.commlibs.voiceUtils.voiceprint.VoicePrintUtil;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.nurse.ca.CaLoginActivity;
import com.dhcc.nursepro.Activity.MainActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.api.LoginApiManager;
import com.dhcc.nursepro.login.bean.BroadCastListBean;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.dhcc.nursepro.login.bean.NurseInfo;
import com.dhcc.nursepro.utils.NurLinkUtil;
import com.dhcc.nursepro.workarea.workareautils.WorkareaMainConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * LoginActivity
 */
public class LoginActivity extends CaLoginActivity implements View.OnClickListener {

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private EditText etLoginUsercode;
    private ImageView imgLoginUsercodeClear;
    private EditText etLoginPassword;
    private ImageView imgLoginPasswordClear;
    private ImageView imgLoginPasswordShowhide;

    private TextView tvLoginWard;
    private TextView tvLoginLogin;
    private LinearLayout llLoginRememberme;
    private List<NurseInfo> nurseInfoList;
    private NurseInfo loginNurseInfo;
    private String userCode = "";
    private String password = "";
    private String logonWardId = "";
    private String loginByVoice = "0";

    private boolean remem = false;
    private String rememUserCode;

    private SPUtils spUtils = SPUtils.getInstance();

    private String LocJson = "";

    private TextView tvIp;
    private TextView tvVoice;
    private TextView tvVoiceIp;
    private String IpStr;
    private SetIPDialog showDialog;
    private OptionPicker picker;

    private boolean hasBroadCastConfig = false;
    private LoginReceiver loginReceiver = new LoginReceiver();
    private IntentFilter loginFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hindMap();
        /**
         * 新版本 使用activity_login_nurse
         */
//        setContentView(R.layout.activity_login);
        setToolbarType(ToolbarType.HIDE);
        UserUtil.checkWebIp();
        UserUtil.checkWebPath();
        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();
        initView();
        CommHttp.getNurseConfig();
        getBroadCastConfig();

        verifyAudioPermissions(this);

        initVoice();
        if (getApplicationContext() != null) {
            //Voip 判断
            Intent i = NurLinkUtil.getMLinkIntent();
            if (i == null) {
                return;
            }
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //android8.0以上通过startForegroundService启动service
                startForegroundService(i);
            } else {
                startService(i);
            }
            // 勿打扰权限-铃声
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                    && !((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).isNotificationPolicyAccessGranted()) {
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
            //悬浮窗
            requestAlertWindowPermission();
        }

    }

    private static final int REQUEST_CODE = 1;

    /**
     * 悬浮窗-允许其他应用上
     */
    private void requestAlertWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                Log.i("xqxinfo", "onActivityResult granted");
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    }

    /**
     * 测试-切换服务器
     * @param view
     */
    public void lgtestServer(View view) {
        String pdaService = BaseWebServiceUtils.getPDAService();
        if (pdaService.contains(BaseWebServiceUtils.NUR_MNIS_SERVICE)) {
            pdaService = BaseWebServiceUtils.NUR_PDA_SERVICE;
        } else {
            pdaService = BaseWebServiceUtils.NUR_MNIS_SERVICE;
        }
        SPStaticUtils.put(SharedPreference.pdaService, pdaService);
        ToastUtils.showShort("切换服务器: " + pdaService);
    }

    /**
     * 打开浏览器测试
     * @param view
     */
    public void testWebView(View view) {
        BaseWebServiceUtils.openBrowser(this, BaseWebServiceUtils.getPDAService());
    }

    /**
     * 打开日志
     * @param view
     */
    public void openLog(View view) {
        startFragment(NurLogFragment.class);
    }

    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        int permission1 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED || permission1 != PackageManager.PERMISSION_GRANTED || permission2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }

    private void initView() {

        etLoginUsercode = findViewById(R.id.et_login_usercode);
        imgLoginUsercodeClear = findViewById(R.id.img_login_usercode_clear);
        imgLoginUsercodeClear.setOnClickListener(this);
        etLoginPassword = findViewById(R.id.et_login_password);
        imgLoginPasswordClear = findViewById(R.id.img_login_password_clear);
        imgLoginPasswordClear.setOnClickListener(this);
        imgLoginPasswordShowhide = findViewById(R.id.img_login_password_showhide);
        imgLoginPasswordShowhide.setOnClickListener(this);

        tvLoginWard = findViewById(R.id.tv_login_ward);
        tvLoginWard.setOnClickListener(this);
        tvLoginLogin = findViewById(R.id.tv_login_login);
        tvLoginLogin.setOnClickListener(this);
        llLoginRememberme = findViewById(R.id.ll_login_rememberme);
        llLoginRememberme.setOnClickListener(this);

        tvIp = findViewById(R.id.tv_login_setip);
        tvIp.setOnClickListener(this);
        etLoginUsercode.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
                etLoginPassword.setText("");
                userCode = s + "";
                if (userCode.length() > 0) {
                    imgLoginUsercodeClear.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(password)) {
                        tvLoginLogin.setSelected(false);
                    } else {
                        tvLoginLogin.setSelected(true);
                    }
                } else {
                    imgLoginUsercodeClear.setVisibility(View.INVISIBLE);
                    tvLoginLogin.setSelected(false);
                }

                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    for (int i = 0; i < nurseInfoList.size(); i++) {
                        NurseInfo nurseInfo = nurseInfoList.get(i);
                        if (userCode.equals(nurseInfo.getUserCode())) {
                            tvLoginWard.setText(nurseInfo.getLocDesc());
                            tvLoginWard.setTextColor(Color.parseColor("#000000"));
                            logonWardId = nurseInfo.getWardId();
                            loginNurseInfo = nurseInfo;
                            break;
                        } else {
                            tvLoginWard.setText("请选择登录病区");
                            tvLoginWard.setTextColor(Color.parseColor("#9b9b9b"));
                        }
                    }
                }
            }
        });

        etLoginPassword.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
                password = s + "";
                if (password.length() > 0) {
                    imgLoginPasswordClear.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(userCode)) {
                        tvLoginLogin.setSelected(false);
                    } else {
                        tvLoginLogin.setSelected(true);
                    }
                } else {
                    imgLoginPasswordClear.setVisibility(View.INVISIBLE);
                    tvLoginLogin.setSelected(false);

                }
            }
        });

    }

    private void initVoice() {
        tvVoiceIp = findViewById(R.id.tv_voice_ip);
        tvVoice = findViewById(R.id.tv_voice);
        tvVoiceIp.setText("语音IPv" + AppUtils.getAppVersionCode());
        tvVoiceIp.setOnClickListener(v -> setVoiceIp());
        VoicePrintUtil voicePrintUtil = new VoicePrintUtil(this, VoicePrintUtil.VOICE_TYPE_LOGIN, tvVoice);
        voicePrintUtil.setVoicePrintResultCallBack(new VoicePrintUtil.VoicePrintResultCallBack() {
            @Override
            public void success(String code) {

                etLoginUsercode.setText(userCode);
                userCode = code;
                loginByVoice = "1";
                initData("login", null);
            }

            @Override
            public void fail() {
                loginByVoice = "0";
            }
        });

    }

    private void setVoiceIp() {
        SetVoiceIPDialog voiceIpDialog = new SetVoiceIPDialog(this);
        voiceIpDialog.setTitle("结果");
        String voiceIp = spUtils.getString(SharedPreference.VOICE_IP);
        String voicePort = spUtils.getString(SharedPreference.VOICE_PORT);
        voiceIpDialog.setMessageIP(voiceIp);
        voiceIpDialog.setMessagePort(voicePort);
        voiceIpDialog.setYesOnclickListener("确定", new SetVoiceIPDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                if (AppUtil.isIP(voiceIpDialog.getIp())) {
                    if (spUtils.getString(SharedPreference.VOICE_IP).equals(voiceIpDialog.getIp()) && spUtils.getString(SharedPreference.VOICE_PORT).equals(voiceIpDialog.getPort())) {
                        showToast("未修改，请重新设置");
                    } else {
                        spUtils.put(SharedPreference.VOICE_IP, voiceIpDialog.getIp());
                        spUtils.put(SharedPreference.VOICE_PORT, voiceIpDialog.getPort());
                        showToast("设置成功，请重新打开APP");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finishAll();
                                System.exit(0);
                            }
                        }, 700);
                    }
                } else {
                    showToast("IP格式不正确，请重新输入");
                }
            }
        });
        voiceIpDialog.show();

    }


    private void getBroadCastConfig() {
        LoginApiManager.getBroadcastConfig(new LoginApiManager.GetBroadCastConfigCallback() {
            @Override
            public void onSuccess(BroadCastListBean broadCastListBean) {
                List<BroadcastListBean> broadcastList = broadCastListBean.getBroadcastList();
                TransBroadcastUtil.setScanActionList(broadcastList);
                hasBroadCastConfig = true;
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //后台 不接收扫码
        if (loginReceiver != null) {
            unregisterReceiver(loginReceiver);
        }
    }

    @Override
    protected void onResume(@Nullable Bundle args) {
        super.onResume(args);
        remem = spUtils.getBoolean(SharedPreference.REMEM);
        if (remem) {
            llLoginRememberme.setSelected(true);
            rememUserCode = spUtils.getString(SharedPreference.REMEM_USERCODE);
            etLoginUsercode.setText(rememUserCode);
            etLoginPassword.requestFocus();
        } else {
            llLoginRememberme.setSelected(false);
        }

        if (loginReceiver != null) {
            loginFilter.addAction(Action.DEVICE_SCAN_CODE);
            registerReceiver(loginReceiver, loginFilter);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_setip:
                showDialog = new SetIPDialog(this);
                showDialog.setTitle("结果");
                showDialog.setMessage(spUtils.getString(SharedPreference.WEBIP), spUtils.getString(SharedPreference.WEBPATH));
                showDialog.setYesOnclickListener("确定", new SetIPDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        if (AppUtil.isIP(showDialog.getIp())) {
                            if (TextUtils.isEmpty(showDialog.getAddr())) {
                                showToast("资源路径不能为空");
                            } else {
                                spUtils.put(SharedPreference.WEBIP, showDialog.getIp());
                                spUtils.put(SharedPreference.WEBPATH, showDialog.getAddr());
                                String ip = showDialog.getIp();
                                UserUtil.setWebIpAndPath(ip, showDialog.getAddr());
                                UserUtil.setWebIpAndPath(ip, showDialog.getAddr());
                                showDialog.dismiss();
                                CommHttp.getNurseConfig();
                                getBroadCastConfig();
                            }

                        } else {
                            showToast("IP格式不正确，请重新输入");
                        }
                    }
                });
                showDialog.show();
                break;
            case R.id.tv_login_ward:
                if (checkUserPass()) {
                    return;
                }
                initData("ward", null);
                break;
            case R.id.tv_login_login:
                if (checkUserPass()) {
                    return;
                }
                if (tvLoginWard.getText().equals("请选择登录病区")) {
                    initData("login", null);
                } else {
                    initData("login", loginNurseInfo);
                }
                break;
            case R.id.img_login_usercode_clear:
                etLoginUsercode.setText("");
                tvLoginWard.setText("请选择登录病区");
                tvLoginWard.setTextColor(Color.parseColor("#9b9b9b"));
                break;
            case R.id.img_login_password_clear:
                etLoginPassword.setText("");
                break;
            case R.id.img_login_password_showhide:
                if (imgLoginPasswordShowhide.isSelected()) {
                    imgLoginPasswordShowhide.setSelected(false);
                    etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    imgLoginPasswordShowhide.setSelected(true);
                    etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.ll_login_rememberme:
                if (llLoginRememberme.isSelected()) {
                    llLoginRememberme.setSelected(false);
                    remem = false;
                } else {
                    llLoginRememberme.setSelected(true);
                    remem = true;
                }
            default:
                break;
        }
    }

    public boolean checkUserPass() {
        if (TextUtils.isEmpty(userCode)) {
            Toast.makeText(this, "请输入护士工号", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void initData(final String action, final NurseInfo nurseInfo) {
        //判断是否存在广播码信息，若无，再次请求广播码
        if (!hasBroadCastConfig) {
            getBroadCastConfig();
        }
        if (checkCA()) {
            return;
        }
        if (nurseInfo != null) {
            spUtils.put(SharedPreference.LOCID, nurseInfo.getLocId());
        }
        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();
        LoginApiManager.getLogin(userCode, password, logonWardId, new LoginApiManager.GetLoginCallback() {
            @Override
            public void onSuccess(final LoginBean loginBean) {
                spUtils.put(SharedPreference.USERID, loginBean.getUserId());
                //设置Pin标识
                setPinFlag(loginBean.getCaFlag(), loginBean.getPinFlag());
                loginByVoice = "0";
                spUtils.put(SharedPreference.BTN_VOICE_SHOW, loginBean.getVoiceFlag().equals("1") ? true : false);

                UserUtil.setUserConfig(loginBean);
                spUtils.put(SharedPreference.CURDATETIME, loginBean.getCurDateTime());
                //保存科室列表，设置界面更换病区会用到
                List<Map<String, String>> list = new ArrayList<>();
                for (int i = 0; i < loginBean.getLocs().size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    map.put("GroupDesc", loginBean.getLocs().get(i).getGroupDesc());
                    map.put("GroupId", loginBean.getLocs().get(i).getGroupId());
                    map.put("HospitalRowId", loginBean.getLocs().get(i).getHospitalRowId());
                    map.put("LinkLoc", loginBean.getLocs().get(i).getLinkLoc());
                    map.put("LocDesc", loginBean.getLocs().get(i).getLocDesc());
                    map.put("LocId", loginBean.getLocs().get(i).getLocId());
                    map.put("WardId", loginBean.getLocs().get(i).getWardId());
                    list.add(map);
                }
                Gson gson = new Gson();
                LocJson = gson.toJson(list);
                spUtils.put(SharedPreference.LOCSLISTJSON, LocJson);

                //选择科室
                if ("ward".equals(action)) {

                    showLocPicker(nurseInfo, loginBean);

                    //登录
                } else if ("login".equals(action)) {
                    LoginSuccess(loginBean, nurseInfo);
                }

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
                loginByVoice = "0";
                if (msg.contains("无此用户")) {
//                    etLoginPassword.clearFocus();
                    etLoginUsercode.requestFocus();
                    etLoginPassword.setText("");
                    InputMethodManager manager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null) {
                        manager.showSoftInput(etLoginUsercode, 0);
                    }
                }
            }
        });
    }

    private void LoginSuccess(LoginBean loginBean, NurseInfo nurseInfo) {
        //保存自动退出时长及检查时间间隔
        if (StringUtils.isEmpty(loginBean.getCloseTime())) {
            spUtils.put(SharedPreference.EXITTIME, 0);
        } else {
            spUtils.put(SharedPreference.EXITTIME, Integer.valueOf(loginBean.getCloseTime()));
        }

        if (StringUtils.isEmpty(loginBean.getCheckTime())) {
            spUtils.put(SharedPreference.CHECKTIME, 1000);
        } else {
            spUtils.put(SharedPreference.CHECKTIME, Integer.valueOf(loginBean.getCheckTime()));
        }

        LoginBean.LocsBean locsBean = loginBean.getLocs().get(0);

        if (nurseInfo == null) {
            loginNurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());
        } else {
            loginNurseInfo = nurseInfo;
            loginNurseInfo.setSchStDateTime(loginBean.getSchStDateTime());
            loginNurseInfo.setSchEnDateTime(loginBean.getSchEnDateTime());
        }

        //本地数据库已存储用户登录数据
        if (nurseInfoList != null && nurseInfoList.size() > 0) {
            int k = 0, l = 0;
            for (k = 0; k < nurseInfoList.size(); k++) {
                NurseInfo nurseInfo1 = nurseInfoList.get(k);
                //判断本地数据库是否已保存用户登录信息
                if (userCode.equals(nurseInfo1.getUserCode())) {
                    //                                Toast.makeText(LoginActivity.this,"login----已存在,使用已保存数据", Toast.LENGTH_SHORT).show();
                    for (l = 0; l < loginBean.getLocs().size(); l++) {
                        //判断本地数据库保存的登录病区是否在登录成功返回的病区列表里面
                        if (nurseInfo1.getLocId().equals(loginBean.getLocs().get(l).getLocId()) && nurseInfo1.getWardId().equals(loginBean.getLocs().get(l).getWardId())) {
                            break;
                        }
                    }
                    //本地数据库保存的登录病区不在登录成功返回的可登录病区列表里面，提示消息，弹窗选择病区
                    if (l >= loginBean.getLocs().size()) {
                        showToast("已取消默认病区登录权限，请重新选择病区登录");
                        showLocPicker(nurseInfo1, loginBean);
                    }
                    break;
                }
            }
            //本地数据库已保存用户信息且用户的登录病区存在于登陆成功返回的可登录病区列表，
            if (k < nurseInfoList.size() && l < loginBean.getLocs().size()) {
                saveStartActivity(loginBean);

            }

            //本地数据库未保存用户信息，数据库添加用户数据，SP设置用户数据，跳转页面
            if (k >= nurseInfoList.size()) {
                //                            Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                daoSession.getNurseInfoDao().insert(loginNurseInfo);
                saveStartActivity(loginBean);

            }
        } else {
            //本地数据库未存储用户登录数据，数据库添加用户数据，SP设置用户数据，跳转页面
            //                        Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
            daoSession.getNurseInfoDao().insert(loginNurseInfo);
            saveStartActivity(loginBean);

        }
    }

    /**
     * 保存并进主页
     */
    private void saveStartActivity(LoginBean loginBean) {
        saveUserInfo();
        spUtils.put(SharedPreference.ORDERSEARCHE_BEDSELECTED, "");
        String singleFlag = spUtils.getString(SharedPreference.SINGLEMODEL, "0");
        if (singleFlag.equals("0")) {
            spUtils.put(SharedPreference.SINGLEMODEL, "0");
        }
        //是否开启CA
        String caFlag = loginBean.getCaFlag();
        if ("1".equals(caFlag)) {
            startCa();
        } else {
            if (singleFlag.equals("1")) {
                startSingleActivity();
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }
    }

    /**
     * 本地存储数据
     */
    private void saveUserInfo() {
        //是否“记住我”
        if (remem) {
            spUtils.put(SharedPreference.REMEM, true);
            spUtils.put(SharedPreference.REMEM_USERCODE, userCode);
        } else {
            spUtils.put(SharedPreference.REMEM, false);
            spUtils.put(SharedPreference.REMEM_USERCODE, "");
        }

        spUtils.put(SharedPreference.USERCODE, userCode);
        spUtils.put(SharedPreference.USERID, loginNurseInfo.getUserId());
        spUtils.put(SharedPreference.USERNAME, loginNurseInfo.getUserName());
        spUtils.put(SharedPreference.HOSPITALROWID, loginNurseInfo.getHospitalRowId());
        spUtils.put(SharedPreference.GROUPID, loginNurseInfo.getGroupId());
        spUtils.put(SharedPreference.GROUPDESC, loginNurseInfo.getGroupDesc());
        spUtils.put(SharedPreference.LINKLOC, loginNurseInfo.getLinkLoc());
        spUtils.put(SharedPreference.LOCID, loginNurseInfo.getLocId());
        spUtils.put(SharedPreference.LOCDESC, loginNurseInfo.getLocDesc());
        spUtils.put(SharedPreference.WARDID, loginNurseInfo.getWardId());
        SchDateTimeUtil.putSchStartEndDateTime(loginNurseInfo.getSchStDateTime(), loginNurseInfo.getSchEnDateTime());
    }

    /**
     * 选择病区弹窗
     * @param nurseInfo
     * @param loginBean
     */
    public void showLocPicker(NurseInfo nurseInfo, LoginBean loginBean) {
        final List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();

        //登陆成功返回的可登录病区列表，提取病区描述
        String[] locDesc = new String[locsBeanList.size()];
        for (int i = 0; i < locsBeanList.size(); i++) {
            locDesc[i] = locsBeanList.get(i).getLocDesc();
        }

        picker = new OptionPicker(LoginActivity.this, locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        Boolean ifLocRem = true;
        for (int i = 0; i < locDesc.length; i++) {
            if (spUtils.getString(SharedPreference.LOCDESC).equals(locDesc[i])) {
                picker.setSelectedIndex(i);
                ifLocRem = false;
            }
        }
        if (ifLocRem) {
            picker.setSelectedIndex(0);
        }
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                LoginBean.LocsBean locsBean = locsBeanList.get(index);
                if (nurseInfo == null) {
                    //loginNurseInfo为空，直接新建数据
                    loginNurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());

                } else {
                    //loginNurseInfo不为空，按照所选病区更新数据
                    loginNurseInfo.setGroupDesc(locsBean.getGroupDesc());
                    loginNurseInfo.setGroupId(locsBean.getGroupId());
                    loginNurseInfo.setHospitalRowId(locsBean.getHospitalRowId());
                    loginNurseInfo.setLinkLoc(locsBean.getLinkLoc());
                    loginNurseInfo.setLocDesc(locsBean.getLocDesc());
                    loginNurseInfo.setLocId(locsBean.getLocId());
                    loginNurseInfo.setWardId(locsBean.getWardId());
                }


                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    //本地数据库不为空，判断是否包含当前用户登录数据
                    int j;
                    for (j = 0; j < nurseInfoList.size(); j++) {
                        NurseInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            //本地数据库包含当前用户登录数据，设置id，更新数据库数据
                            loginNurseInfo.setId(nurseInfo1.getId());
                            daoSession.getNurseInfoDao().update(loginNurseInfo);
                            initData("login", loginNurseInfo);
                            break;
                        }
                    }

                    if (j >= nurseInfoList.size()) {
                        //本地数据库不包含当前用户登录数据，新增数据库数据
                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                        initData("login", loginNurseInfo);
                    }

                } else {
                    //本地数据库为空，直接新增数据库数据
                    daoSession.getNurseInfoDao().insert(loginNurseInfo);
                    initData("login", loginNurseInfo);
                }
            }
        });
        picker.show();
    }

    public class LoginReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();

                etLoginUsercode.setText("");
                etLoginPassword.setText("");

                userCode = bundle.getString("data", "");

                initData("login", null);
            }
        }
    }

    private void startSingleActivity() {
        WorkareaMainConfig workareaMainConfig = new WorkareaMainConfig();
        workareaMainConfig.getMainConfigData(LoginActivity.this);
    }
}
