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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private EditText etLoginUsercode;
    private EditText etLoginPassword;
    private TextView tvLoginWard;
    private TextView tvLoginLogin;
    private String version;
    private List<NurseInfo> nurseInfoList;
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
                        if (code.equals(nurseInfo.getUserId())) {
                            tvLoginWard.setText(nurseInfo.getLocDesc());
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
                initData("ward");
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
                initData("login");
                break;


            default:
                break;
        }
    }

    private void initData(String action) {
        LoginApiManager.getLogin(userCode, password, logonWardId, new LoginApiManager.GetLoginCallback() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                logincheck = true;

                //                for (int i = 0; i < nurseInfoList.size(); i++) {
                //                    NurseInfo nurseInfo = nurseInfoList.get(i);
                //                    if (userCode.equals(nurseInfo.getUserCode())) {
                //
                //                    }
                //                }

                LoginBean.LocsBean locsBean = loginBean.getLocs().get(0);

                NurseInfo nurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());

                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    for (int j = 0; j < nurseInfoList.size(); j++) {
                        NurseInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            Toast.makeText(LoginActivity.this, "已存在，更新数据", Toast.LENGTH_SHORT).show();
                            nurseInfo.setId(nurseInfo1.getId());
                            daoSession.getNurseInfoDao().update(nurseInfo);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "不存在，插入新数据", Toast.LENGTH_SHORT).show();
                            daoSession.getNurseInfoDao().insert(nurseInfo);
                            finish();
                        }
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "不存在，插入新数据", Toast.LENGTH_SHORT).show();
                    daoSession.getNurseInfoDao().insert(nurseInfo);
                    finish();
                }

                //                Nurse nurse = new Nurse(null,loginBean.getSchEnDateTime(),loginBean.getSchStDateTime(),loginBean.getStatus(),loginBean.getUserId(),loginBean.getUserName(),"");
                //
                //                daoSession.getNurseDao().insert(nurse);
                //
                //                List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();
                //
                //                for (int i = 0; i < locsBeanList.size(); i++) {
                //                    LoginBean.LocsBean locsBean = locsBeanList.get(i);
                //                    Loc loc = new Loc(null, locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());
                //                    List<Loc> locList = daoSession.getLocDao().queryBuilder().where(LocDao.Properties.WardId.eq(loc.getWardId())).list();
                //                    if (locList.size() > 0) {
                //                        Long id = locList.get(0).getId();
                //                        loc.setId(id);
                //                        daoSession.getLocDao().update(loc);
                //                    } else {
                //                        daoSession.getLocDao().insert(loc);
                //                    }
                //                }
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
