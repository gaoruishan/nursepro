package com.dhcc.nursepro.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.Activity.MainActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.api.LoginApiManager;
import com.dhcc.nursepro.login.bean.BroadCastListBean;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.dhcc.nursepro.login.bean.NurseInfo;
import com.dhcc.nursepro.utils.TransBroadcastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

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
    private String userCode;
    private String password;
    private String logonWardId;

    private boolean remem = false;
    private String rememUserCode;

    private SPUtils spUtils = SPUtils.getInstance();

    private String LocJson = "";

    private TextView tvIp;
    private String IpStr;
    private SetIPDialog showDialog;
    private OptionPicker picker;

    private boolean hasBroadCastConfig = false;
    private LoginReceiver loginReceiver = new LoginReceiver();
    private IntentFilter loginFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setToolbarType(ToolbarType.HIDE);
        IpStr = spUtils.getString(SharedPreference.WEBIP, "noIp");
        if ("noIp".equals(IpStr)) {
            spUtils.put(SharedPreference.WEBIP, BaseWebServiceUtils.DEFAULT_IP);
        }
        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();
        initView();
        getBroadCastConfig();
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


        etLoginUsercode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
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

        etLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

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

    private void getBroadCastConfig() {
        LoginApiManager.getBroadcastConfig(new LoginApiManager.GetBroadCastConfigCallback() {
            @Override
            public void onSuccess(BroadCastListBean broadCastListBean) {
                List<BroadCastListBean.BroadcastListBean> broadcastList = broadCastListBean.getBroadcastList();
                if (broadcastList != null && broadcastList.size() > 0) {
                    TransBroadcastUtil.setScanActionList(broadcastList);
                    hasBroadCastConfig = true;
                }
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

    public int search(String str, String strRes) {//查找字符串里与指定字符串相同的个数
        int n = 0;
        while (str.indexOf(strRes) != -1) {
            int i = str.indexOf(strRes);
            n++;
            str = str.substring(i + 1);
        }
        return n;
    }

    public boolean isIP(String addr) {

        //判断"."的个数，大于3个返回错误
        int pointNum = search(addr, ".");
        if (pointNum > 3) {
            return false;
        }

        String ipStr = addr;
        if (addr.contains(":")) {

            //判断":"个数，大于1个返回错误
            int containNum = search(addr, ":");
            if (containNum > 1) {
                return false;
            }

            //判断":"后面的内容，空的话返回错误，有数字外其他字符也返回错误
            String lastStr = addr.substring(addr.indexOf(":") + 1);
            if (lastStr.contains(".") || lastStr.length() < 1) {
                return false;
            }
            ipStr = addr.substring(0, addr.indexOf(":"));
        }

        if (ipStr.length() < 7 || ipStr.length() > 15 || "".equals(ipStr)) {
            return false;
        }
        // 判断IP格式是否规范
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ipStr);
        boolean ipAddress = mat.find();
        //============对之前的ip判断的bug在进行判断
        if (ipAddress) {
            String[] ips = ipStr.split("\\.");
            if (ips.length == 4) {
                try {
                    for (String ip : ips) {
                        if (Integer.parseInt(ip) < 0 || Integer.parseInt(ip) > 255) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_setip:
                showDialog = new SetIPDialog(this);
                showDialog.setTitle("结果");
                showDialog.setMessage(spUtils.getString(SharedPreference.WEBIP));
                showDialog.setYesOnclickListener("确定", new SetIPDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        if (isIP(showDialog.getIp())) {
                            spUtils.put(SharedPreference.WEBIP, showDialog.getIp());
                            showDialog.dismiss();
                            getBroadCastConfig();
                        } else {
                            showToast("IP格式不正确，请重新输入");
                        }
                    }
                });
                showDialog.show();
                break;
            case R.id.tv_login_ward:
                if (TextUtils.isEmpty(userCode)) {
                    Toast.makeText(this, "请输入护士工号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                initData("ward", null);
                break;
            case R.id.tv_login_login:
                if (TextUtils.isEmpty(userCode)) {
                    Toast.makeText(this, "请输入护士工号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
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

    private void initData(final String action, final NurseInfo nurseInfo) {

        //判断是否存在广播码信息，若无，再次请求广播码
        if(!hasBroadCastConfig) {
            getBroadCastConfig();
        }

        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();
        LoginApiManager.getLogin(userCode, password, logonWardId, new LoginApiManager.GetLoginCallback() {
            @Override
            public void onSuccess(final LoginBean loginBean) {

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
                            saveUserInfo();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                        //本地数据库未保存用户信息，数据库添加用户数据，SP设置用户数据，跳转页面
                        if (k >= nurseInfoList.size()) {
                            //                            Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                            daoSession.getNurseInfoDao().insert(loginNurseInfo);
                            saveUserInfo();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    } else {
                        //本地数据库未存储用户登录数据，数据库添加用户数据，SP设置用户数据，跳转页面
                        //                        Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                        saveUserInfo();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
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
        /*
          更新SP数据
          userCode
          userId : 3
          userName : innurse
          hospitalRowId : 2
          groupId : 132
          groupDesc : Inpatient Nurse
          linkLoc : 110
          locId : 197
          locDesc : 内分泌科护理单元
          wardId : 5
          schEnDateTime : 13/08/2018,23:59:59
          schStDateTime : 13/08/2018,00:00:00
         */
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
        spUtils.put(SharedPreference.SCHSTDATETIME, loginNurseInfo.getSchStDateTime());
        spUtils.put(SharedPreference.SCHENDATETIME, loginNurseInfo.getSchEnDateTime());
    }

    /**
     * 选择病区弹窗
     *
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
        picker.setSelectedIndex(0);
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
                            //                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
                            loginNurseInfo.setId(nurseInfo1.getId());
                            daoSession.getNurseInfoDao().update(loginNurseInfo);
                            initData("login", loginNurseInfo);
                            break;
                        }
                    }

                    if (j >= nurseInfoList.size()) {
                        //本地数据库不包含当前用户登录数据，新增数据库数据
                        //                                    Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                        initData("login", loginNurseInfo);
                    }

                } else {
                    //本地数据库为空，直接新增数据库数据
                    //                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
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
                    String scanInfo = bundle.getString("data");

                    LoginApiManager.GetUserPwd(scanInfo, new LoginApiManager.GetUserPwdCallback() {
                        @Override
                        public void onSuccess(LoginBean loginBean) {
                            LoginApiManager.ScanLogon(scanInfo, new LoginApiManager.ScanLogonCallback() {
                                @Override
                                public void onSuccess(LoginBean loginBean) {

                                }

                                @Override
                                public void onFail(String code, String msg) {

                                }
                            });
                        }

                        @Override
                        public void onFail(String code, String msg) {
                            Toast.makeText(LoginActivity.this, "error" + code + ":" + msg, Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }
    }
}
