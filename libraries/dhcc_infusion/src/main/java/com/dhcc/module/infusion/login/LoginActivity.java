package com.dhcc.module.infusion.login;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.bean.LoginBean;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.listener.CustomTextWatcher;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.base.commlibs.utils.UserUtil;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.db.GreenDaoHelper;
import com.dhcc.module.infusion.db.InfusionInfo;
import com.dhcc.module.infusion.login.api.LoginApiManager;
import com.dhcc.module.infusion.login.windowpicker.Ward;
import com.dhcc.module.infusion.login.windowpicker.Window;
import com.dhcc.module.infusion.login.windowpicker.WindowPicker;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.widget.WheelView;

import static com.base.commlibs.http.ParserUtil.ERR_CODE_1;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText etLoginUsercode;
    private ImageView imgLoginUsercodeClear;
    private EditText etLoginPassword;
    private ImageView imgLoginPasswordClear;
    private ImageView imgLoginPasswordShowhide;

    private TextView tvLoginWard;
    private TextView tvLoginLogin;
    private LinearLayout llLoginRememberme;
    private List<InfusionInfo> nurseInfoList;
    private InfusionInfo loginNurseInfo;
    private String userCode;
    private String password;
    private String logonWardId;

    private boolean remem = false;

    private SetIPDialog showDialog;
    private WindowPicker picker;

    private Map mapaaa = new HashMap();
    private List listaaa = new ArrayList();

    private String scanFlag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_infusion);
        setToolbarType(ToolbarType.HIDE);
        UserUtil.checkWebIp();
        UserUtil.checkWebPath();
        UserUtil.setLoginLocType();
        nurseInfoList = GreenDaoHelper.getDaoSession().getInfusionInfoDao().queryBuilder().list();
        initView();
        //获取广播码
        CommHttp.getNurseConfig();
        CommHttp.initBroadcastList();
        openMultiScan(AppUtil.isMultiScan());
        //请求SD卡权限
        PermissionUtils.permission(PermissionConstants.STORAGE).request();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, 0);

        //版本号
        ((TextView) findViewById(R.id.tv_version)).setText(UserUtil.getVersion());
        //版本处理
        Log.e(TAG,"(LoginActivity.java:105) "+PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE));

        if (PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            UserUtil.checkLogVersion();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideSoftInput(this);
    }

    public void openBrowser(View v) {
        BaseWebServiceUtils.openBrowser(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    protected void onResume(@Nullable Bundle args) {
        super.onResume(args);
        //注册扫码监听
        registerScanMsgReceiver();
        remem = SPUtils.getInstance().getBoolean(SharedPreference.REMEM);
        if (remem) {
            llLoginRememberme.setSelected(true);
            String rememUserCode = SPUtils.getInstance().getString(SharedPreference.REMEM_USERCODE);
            etLoginUsercode.setText(rememUserCode);
            etLoginPassword.requestFocus();
        } else {
            llLoginRememberme.setSelected(false);
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        String scanInfo = doScanInfo(intent);
        if (scanInfo != null) {
            etLoginUsercode.setText("");
            etLoginUsercode.setText(scanInfo);
            //隐藏键盘
            KeyboardUtils.hideSoftInput(etLoginUsercode);
            scanFlag = "1";//扫码-scanFlag不为空
            initData("login", null);
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

        View tvIp = findViewById(R.id.tv_login_setip);
        tvIp.setOnClickListener(this);

        etLoginUsercode.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
                userCode = s + "";
                checkInput(userCode, imgLoginUsercodeClear, password);

                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    for (int i = 0; i < nurseInfoList.size(); i++) {
                        InfusionInfo nurseInfo = nurseInfoList.get(i);
                        if (userCode.equals(nurseInfo.getUserCode())) {
                            tvLoginWard.setText(nurseInfo.getLocDesc() + " " + UserUtil.getWindowName());
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
                checkInput(password, imgLoginPasswordClear, userCode);
            }
        });

    }

    protected void checkInput(String userCode, ImageView imgLoginUsercodeClear, String password) {
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
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_login_setip) {
            showDialog = new SetIPDialog(this);
            showDialog.setTitle("结果");
            showDialog.setMessage(SPUtils.getInstance().getString(SharedPreference.WEBIP), SPUtils.getInstance().getString(SharedPreference.WEBPATH));
            showDialog.setYesOnclickListener("确定", new SetIPDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    String ip = showDialog.getIp();
                    if (AppUtil.isIP(ip)) {
                        CommHttp.getNurseConfig();
                        UserUtil.setWebIpAndPath(ip, showDialog.getAddr());
                        //重写请求
                        CommHttp.initBroadcastList();
                        showDialog.dismiss();
                    } else {
                        showToast("IP格式不正确，请重新输入");
                    }
                }
            });
            showDialog.show();
        }
        if (v.getId() == R.id.tv_login_ward) {
            if (checkUserCodePassword()) {
                return;
            }
            initData("ward", null);
        }
        if (v.getId() == R.id.tv_login_login) {
            if (checkUserCodePassword()) {
                return;
            }
            if (tvLoginWard.getText().equals("请选择登录病区")) {
                initData("login", null);
            } else {
                initData("login", loginNurseInfo);
            }
        }
        if (v.getId() == R.id.img_login_usercode_clear) {
            etLoginUsercode.setText("");
            tvLoginWard.setText("请选择登录病区");
            tvLoginWard.setTextColor(Color.parseColor("#9b9b9b"));
        }
        if (v.getId() == R.id.img_login_password_clear) {
            etLoginPassword.setText("");
        }
        if (v.getId() == R.id.img_login_password_showhide) {
            if (imgLoginPasswordShowhide.isSelected()) {
                imgLoginPasswordShowhide.setSelected(false);
                etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                imgLoginPasswordShowhide.setSelected(true);
                etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        }
        if (v.getId() == R.id.ll_login_rememberme) {
            if (llLoginRememberme.isSelected()) {
                llLoginRememberme.setSelected(false);
                remem = false;
            } else {
                llLoginRememberme.setSelected(true);
                remem = true;
            }
        }
    }

    protected boolean checkUserCodePassword() {
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


    private void initData(final String action, final InfusionInfo nurseInfo) {
        showLoadingTip(LoadingType.FULL);
        nurseInfoList = GreenDaoHelper.getDaoSession().getInfusionInfoDao().queryBuilder().list();
        LoginApiManager.getLogin(userCode, password, logonWardId, scanFlag, new CommonCallBack<LoginBean>() {
            @Override
            public void onSuccess(final LoginBean loginBean, String type) {
                hideLoadingTip();
                listaaa = new ArrayList();
                mapaaa = new HashMap();
                //保存科室列表，设置界面更换病区会用到
                List<Map<String, String>> list = new ArrayList<>();
                for (int i = 0; i < loginBean.getLocs().size(); i++) {
                    List listWin = new ArrayList();
                    for (int j = 0; j < loginBean.getLocs().get(i).getWinList().size(); j++) {
                        listWin.add(loginBean.getLocs().get(i).getWinList().get(j).getWinDesc());
                    }
                    mapaaa.put(loginBean.getLocs().get(i).getLocDesc(), listWin);
                    if (mapaaa.get(loginBean.getLocs().get(i).getLocDesc()) == null) {
                        List list1 = new ArrayList();
                        mapaaa.put(loginBean.getLocs().get(i).getLocDesc(), list1);
                    }
                    listaaa.add(loginBean.getLocs().get(i).getLocDesc());
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
                String locjson = gson.toJson(list);
                String winjson = gson.toJson(mapaaa);
                UserUtil.setLocWinListJson(locjson, winjson);

                //选择科室
                if ("ward".equals(action)) {

                    showLocPicker(nurseInfo, loginBean);

                    //登录
                } else if ("login".equals(action)) {
                    LoginBean.LocsBean locsBean = loginBean.getLocs().get(0);

                    if (nurseInfo == null) {
                        loginNurseInfo = new InfusionInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());
                    } else {
                        loginNurseInfo = nurseInfo;
                        loginNurseInfo.setSchStDateTime(loginBean.getSchStDateTime());
                        loginNurseInfo.setSchEnDateTime(loginBean.getSchEnDateTime());
                    }

                    //本地数据库已存储用户登录数据
                    if (nurseInfoList != null && nurseInfoList.size() > 0) {
                        int k = 0, l = 0;
                        for (k = 0; k < nurseInfoList.size(); k++) {
                            InfusionInfo nurseInfo1 = nurseInfoList.get(k);
                            //判断本地数据库是否已保存用户登录信息
                            if (userCode.equals(nurseInfo1.getUserCode())) {
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
                            saveToAcitvity(loginBean);
                        }

                        //本地数据库未保存用户信息，数据库添加用户数据，SP设置用户数据，跳转页面
                        if (k >= nurseInfoList.size()) {
                            GreenDaoHelper.getDaoSession().getInfusionInfoDao().insert(loginNurseInfo);
                            saveToAcitvity(loginBean);
                        }
                    } else {
                        GreenDaoHelper.getDaoSession().getInfusionInfoDao().insert(loginNurseInfo);
                        saveToAcitvity(loginBean);
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                if (ERR_CODE_1.equals(code)) {
                    DialogFactory.showCommDialog(LoginActivity.this,
                            "1,检查【设置IP地址】是否正确\n" +
                                    "2,检查PDA连接是否上指定无线WIFI\n" +
                                    "3,点击【测试】打开浏览器", "测试",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    BaseWebServiceUtils.openBrowser(LoginActivity.this);
                                }
                            });

                }
            }

        });
    }

    private void saveToAcitvity(LoginBean loginBean) {
        List<BroadcastListBean> broadcastList = loginBean.getBroadcastList();
        TransBroadcastUtil.setScanActionList(broadcastList);
        saveUserInfo(loginBean);
        startActivity(new Intent(Action.MainActivity));
        finish();
    }

    /**
     * 本地存储数据
     * @param loginBean
     */
    private void saveUserInfo(LoginBean loginBean) {
        //是否“记住我”
        if (remem) {
            UserUtil.setRememberUserCode(true, userCode);
        } else {
            UserUtil.setRememberUserCode(false, "");
        }
        UserUtil.setUserInfo(loginBean);
        UserUtil.setLocsUserInfo(userCode, loginNurseInfo.getHospitalRowId(), loginNurseInfo.getGroupId(), loginNurseInfo.getGroupDesc()
                , loginNurseInfo.getLinkLoc(), loginNurseInfo.getLocId(), loginNurseInfo.getLocDesc(), loginNurseInfo.getWardId());
    }

    /**
     * 选择病区弹窗
     * @param nurseInfo
     * @param loginBean
     */
    public void showLocPicker(final InfusionInfo nurseInfo, final LoginBean loginBean) {

        final List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();
        picker = new WindowPicker(this, listaaa, mapaaa);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setTextColor(Color.parseColor("#FF62ABFF"));
        picker.setSubmitTextSize(12);
        picker.setSubmitTextColor(Color.parseColor("#FF62ABFF"));
        picker.setDividerColor(Color.parseColor("#FF62ABFF"));
        picker.setCancelTextSize(12);
        picker.setCancelTextColor(Color.parseColor("#FFC8C8C8"));
        picker.setLineSpaceMultiplier(3);
        picker.setOffset(2);
        picker.setOnPickListener(new LinkagePicker.OnPickListener<Ward, Window, Void>() {
            @Override
            public void onPicked(Ward first, Window second, Void third) {
                UserUtil.setWindowName(second.getName());
                int index = picker.getSelectedFirstIndex();
                LoginBean.LocsBean locsBean = locsBeanList.get(index);

                if (nurseInfo == null) {
                    //loginNurseInfo为空，直接新建数据
                    loginNurseInfo = new InfusionInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());

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
                        InfusionInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            loginNurseInfo.setId(nurseInfo1.getId());
                            GreenDaoHelper.getDaoSession().getInfusionInfoDao().update(loginNurseInfo);
                            initData("login", loginNurseInfo);
                            break;
                        }
                    }

                    if (j >= nurseInfoList.size()) {
                        GreenDaoHelper.getDaoSession().getInfusionInfoDao().insert(loginNurseInfo);
                        initData("login", loginNurseInfo);
                    }

                } else {
                    GreenDaoHelper.getDaoSession().getInfusionInfoDao().insert(loginNurseInfo);
                    initData("login", loginNurseInfo);
                }
            }
        });
        picker.show();
    }
}
