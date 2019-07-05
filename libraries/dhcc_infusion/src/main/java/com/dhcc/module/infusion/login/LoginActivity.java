package com.dhcc.module.infusion.login;

import android.content.Intent;
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
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.db.GreenDaoHelper;
import com.dhcc.module.infusion.db.InfusionInfo;
import com.dhcc.module.infusion.greendao.DaoSession;
import com.dhcc.module.infusion.login.api.LoginApiManager;
import com.dhcc.module.infusion.login.bean.BroadcastListBean;
import com.dhcc.module.infusion.login.bean.LoginBean;
import com.dhcc.module.infusion.login.bean.ScanCodeBean;
import com.dhcc.module.infusion.login.windowpicker.Ward;
import com.dhcc.module.infusion.login.windowpicker.Window;
import com.dhcc.module.infusion.login.windowpicker.WindowPicker;
import com.dhcc.module.infusion.utils.AppUtil;
import com.dhcc.module.infusion.utils.TransBroadcastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.widget.WheelView;

import static com.base.commlibs.wsutils.BaseWebServiceUtils.DEFAULT_IP;
import static com.base.commlibs.wsutils.BaseWebServiceUtils.DTHEALTH_WEB;

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
    private List<InfusionInfo> nurseInfoList;
    private InfusionInfo loginNurseInfo;
    private String userCode;
    private String password;
    private String logonWardId;

    private boolean remem = false;
    private String rememUserCode;

    private SPUtils spUtils = SPUtils.getInstance();

    private String LocJson = "";
    private String WinJson = "";

    private TextView tvIp;
    private String IpStr;
    private SetIPDialog showDialog;
    private WindowPicker picker;

    private Map mapaaa = new HashMap();
    private List listaaa = new ArrayList();

    private String defaultwindow = "";
    private String scanInfo;
    private String scanFlag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_infusion);
        setToolbarType(ToolbarType.HIDE);
        IpStr = spUtils.getString(SharedPreference.WEBIP, "noIp");
        defaultwindow = spUtils.getString(SharedPreference.DEFAULTWINDOW, "");
        if ("noIp".equals(IpStr)) {
            spUtils.put(SharedPreference.WEBIP, DEFAULT_IP);
        }
        String webPath = spUtils.getString(SharedPreference.WEBPATH);
        if(TextUtils.isEmpty(webPath)){
            spUtils.put(SharedPreference.WEBPATH, DTHEALTH_WEB);
        }
        spUtils.put(SharedPreference.LOGONLOCTYPE, "E");
        nurseInfoList = daoSession.getInfusionInfoDao().queryBuilder().list();
        initView();
        //注册扫码监听
        registerScanMsgReceiver();
        //获取广播码
        getBroadcastList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    private void getBroadcastList() {
        LoginApiManager.getBroadcastList(new CommonCallBack<ScanCodeBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(ScanCodeBean bean, String type) {
                TransBroadcastUtil.setScanActionList(bean.getBroadcastList());
            }
        });
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
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        scanInfo = doScanInfo(intent);
        if (scanInfo != null) {
            etLoginUsercode.setText("");
            etLoginUsercode.setText(scanInfo);
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
                        InfusionInfo nurseInfo = nurseInfoList.get(i);
                        if (userCode.equals(nurseInfo.getUserCode())) {
                            tvLoginWard.setText(nurseInfo.getLocDesc() + " " + AppUtil.getWindowName());
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
        if (v.getId() == R.id.tv_login_setip) {
            showDialog = new SetIPDialog(this);
            showDialog.setTitle("结果");
            showDialog.setMessage(spUtils.getString(SharedPreference.WEBIP),spUtils.getString(SharedPreference.WEBPATH));
            showDialog.setYesOnclickListener("确定", new SetIPDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    String ip = showDialog.getIp();
                    if (isIP(ip)) {
                        spUtils.put(SharedPreference.WEBIP, ip);
                        if (!TextUtils.isEmpty(showDialog.getAddr())) {
                            spUtils.put(SharedPreference.WEBPATH, showDialog.getAddr());
                        }
                        //重写请求
                        getBroadcastList();
                        showDialog.dismiss();
                    } else {
                        showToast("IP格式不正确，请重新输入");
                    }
                }
            });
            showDialog.show();
        }
        if (v.getId() == R.id.tv_login_ward) {
            if (TextUtils.isEmpty(userCode)) {
                Toast.makeText(this, "请输入护士工号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            initData("ward", null);
        }
        if (v.getId() == R.id.tv_login_login) {
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

    private void initData(final String action, final InfusionInfo nurseInfo) {
        nurseInfoList = daoSession.getInfusionInfoDao().queryBuilder().list();
        LoginApiManager.getLogin(userCode, password, logonWardId, scanFlag,new CommonCallBack<LoginBean>() {
            @Override
            public void onSuccess(final LoginBean loginBean, String type) {
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
                LocJson = gson.toJson(list);
                WinJson = gson.toJson(mapaaa);
                spUtils.put(SharedPreference.LOCSLISTJSON, LocJson);
                spUtils.put(SharedPreference.WINSLISTJSON, WinJson);

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
                            saveToAcitvity(loginBean);
                        }

                        //本地数据库未保存用户信息，数据库添加用户数据，SP设置用户数据，跳转页面
                        if (k >= nurseInfoList.size()) {
                            //                            Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                            daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                            saveToAcitvity(loginBean);
                        }
                    } else {
                        //本地数据库未存储用户登录数据，数据库添加用户数据，SP设置用户数据，跳转页面
                        //                        Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                        saveToAcitvity(loginBean);
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(LoginActivity.this, "error" + code + ":" + msg, Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void saveToAcitvity(LoginBean loginBean) {
        List<BroadcastListBean> broadcastList = loginBean.getBroadcastList();
        TransBroadcastUtil.setScanActionList(broadcastList);
        saveUserInfo();
        startActivity(new Intent(Action.MainActivity));
        finish();
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
     * @param nurseInfo
     * @param loginBean
     */
    public void showLocPicker(final InfusionInfo nurseInfo, final LoginBean loginBean) {

        final List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();

        //登陆成功返回的可登录病区列表，提取病区描述
        String[] locDesc = new String[locsBeanList.size()];
        for (int i = 0; i < locsBeanList.size(); i++) {
            locDesc[i] = locsBeanList.get(i).getLocDesc();
        }
        picker = new WindowPicker(this, listaaa, mapaaa);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
//        picker.setSelectedIndex(selectDocIndex, selectNurIndex);
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
//                showToast(first.getName()+"----"+second.getWindId());
                spUtils.put(SharedPreference.WINDOWNAME, second.getName());
                {
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
                                //本地数据库包含当前用户登录数据，设置id，更新数据库数据
                                //                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
                                loginNurseInfo.setId(nurseInfo1.getId());
                                daoSession.getInfusionInfoDao().update(loginNurseInfo);
                                initData("login", loginNurseInfo);
                                break;
                            }
                        }

                        if (j >= nurseInfoList.size()) {
                            //本地数据库不包含当前用户登录数据，新增数据库数据
                            //                                    Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                            daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                            initData("login", loginNurseInfo);
                        }

                    } else {
                        //本地数据库为空，直接新增数据库数据
                        //                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                        initData("login", loginNurseInfo);
                    }
                }
            }
        });
        picker.show();
    }
}
