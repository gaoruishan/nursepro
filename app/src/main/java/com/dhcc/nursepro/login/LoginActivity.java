package com.dhcc.nursepro.login;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.api.LoginApiManager;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.dhcc.nursepro.login.bean.NurseInfo;

import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private EditText etLoginUsercode;
    private EditText etLoginPassword;
    private TextView tvLoginWard;
    private TextView tvLoginLogin;
    private String version;

    private List<NurseInfo> nurseInfoList;
    private NurseInfo loginNurseInfo;

    private boolean logincheck = false;
    private String userCode;
    private String password;
    private String logonWardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();
        version = getVersion();
        initView();
    }

    //查询当前版本
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            // return this.getString(R.string.version_name) + version;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取当前版本号";
        }
    }

    private void initView() {
        etLoginUsercode = findViewById(R.id.et_login_usercode);
        etLoginPassword = findViewById(R.id.et_login_password);
        tvLoginWard = findViewById(R.id.tv_login_ward);
        tvLoginWard.setOnClickListener(this);
        tvLoginLogin = findViewById(R.id.tv_login_login);
        tvLoginLogin.setOnClickListener(this);

        etLoginUsercode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = s.toString();

                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    for (int i = 0; i < nurseInfoList.size(); i++) {
                        NurseInfo nurseInfo = nurseInfoList.get(i);
                        if (code.equals(nurseInfo.getUserCode())) {
                            tvLoginWard.setText(nurseInfo.getLocDesc());
                            logonWardId = nurseInfo.getWardId();
                            loginNurseInfo = nurseInfo;
                        }
                    }
                }
            }
        });

        Toast.makeText(this, "" + version, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        userCode = etLoginUsercode.getText().toString();
        password = etLoginPassword.getText().toString();
        switch (v.getId()) {
            case R.id.tv_login_ward:
                if (TextUtils.isEmpty(userCode)) {
                    Toast.makeText(this, "请输入护士工号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                initData("ward",null);
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
                initData("login", null);
                break;


            default:
                break;
        }
    }

    private void initData(final String action, final NurseInfo nurseInfo) {
        LoginApiManager.getLogin(userCode, password, logonWardId, new LoginApiManager.GetLoginCallback() {
            @Override
            public void onSuccess(final LoginBean loginBean) {

                //选择科室
                if ("ward".equals(action)) {
                    final List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();

                    String[] locDesc = new String[locsBeanList.size()];
                    for (int i = 0; i < locsBeanList.size(); i++) {
                        locDesc[i] = locsBeanList.get(i).getLocDesc();
                    }

                    final OptionPicker picker = new OptionPicker(LoginActivity.this, locDesc);
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
                                loginNurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());

                            } else {
                                loginNurseInfo.setGroupDesc(locsBean.getGroupDesc());
                                loginNurseInfo.setGroupId(locsBean.getGroupId());
                                loginNurseInfo.setHospitalRowId(locsBean.getHospitalRowId());
                                loginNurseInfo.setLinkLoc(locsBean.getLinkLoc());
                                loginNurseInfo.setLocDesc(locsBean.getLocDesc());
                                loginNurseInfo.setLocId(locsBean.getLocId());
                                loginNurseInfo.setWardId(locsBean.getWardId());
                            }

                            if (nurseInfoList != null && nurseInfoList.size() > 0) {
                                for (int j = 0; j < nurseInfoList.size(); j++) {
                                    NurseInfo nurseInfo1 = nurseInfoList.get(j);
                                    if (userCode.equals(nurseInfo1.getUserCode())) {
                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
                                        loginNurseInfo.setId(nurseInfo1.getId());
                                        daoSession.getNurseInfoDao().update(loginNurseInfo);
                                        initData("login", loginNurseInfo);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                                        initData("login", loginNurseInfo);
                                    }
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                                daoSession.getNurseInfoDao().insert(loginNurseInfo);
                                initData("login", loginNurseInfo);
                            }
                        }
                    });
                    picker.show();



                //登录
                } else if ("login".equals(action)) {
                    LoginBean.LocsBean locsBean = loginBean.getLocs().get(0);

                    if (nurseInfo == null) {
                        loginNurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());
                    }
                    NurseInfo nurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());

                    if (nurseInfoList != null && nurseInfoList.size() > 0) {
                        for (int j = 0; j < nurseInfoList.size(); j++) {
                            NurseInfo nurseInfo1 = nurseInfoList.get(j);
                            if (userCode.equals(nurseInfo1.getUserCode())) {
                                Toast.makeText(LoginActivity.this, "login----已存在,使用已保存数据", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                                daoSession.getNurseInfoDao().insert(nurseInfo);
                                finish();
                            }
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "login----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getNurseInfoDao().insert(nurseInfo);
                        finish();
                    }
                }

            }

            @Override
            public void onFail(String code, String msg) {
                logincheck = false;
                Toast.makeText(LoginActivity.this, code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    //初次登录获取科室列表并将默认科室保存，再次点击登录就可获取默认科室
    //    private void getLocs() {
    //
    //    }
    //
    //    //请求后台查看是否有新版本
    //    public void ifUpdate() {
    //        //如果有新版本，进行强制操作，否则无法进行下一步操作
    //        if (true) {
    //            new WSPresenter(this).init();
    //        }
    //
    //    }
    //
    //
    //    @Override
    //    public void showLoading() {
    //
    //    }
    //
    //    @Override
    //    public void showStudents(List<String> list, String wsCode) {
    //
    //    }
}
