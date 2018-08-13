package com.dhcc.nursepro.login;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.login.api.LoginApiManager;
import com.dhcc.nursepro.login.bean.LoginBean;
import com.dhcc.nursepro.utils.wsutils.BaseView;
import com.dhcc.nursepro.utils.wsutils.WSPresenter;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,BaseView {

    private EditText etLoginUsercode;
    private EditText etLoginPassword;
    private TextView tvLoginWard;
    private TextView tvLoginLogin;

    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        version = getVersion();
        initView();
        initData();
    }

    private void initView() {
        etLoginUsercode = findViewById(R.id.et_login_usercode);
        etLoginPassword = findViewById(R.id.et_login_password);
        tvLoginWard = findViewById(R.id.tv_login_ward);
        tvLoginWard.setOnClickListener(this);
        tvLoginLogin = findViewById(R.id.tv_login_login);
        tvLoginLogin.setOnClickListener(this);

        Toast.makeText(this, ""+version, Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        LoginApiManager.getLogin(new LoginApiManager.GetLoginCallback() {
            @Override
            public void onSuccess(LoginBean loginBean) {

            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(LoginActivity.this, code+":"+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.tv_login_ward:
            break;
        case R.id.tv_login_login:
            break;

//        case R.id.login_btn_login:
//            //如果已经登录过科室，默认还登录之前登录的科室
//            if (true){
//
//            }else {
//                getLocs();
//            }
//            break;
            default:break;
    }
    }



    //初次登录获取科室列表并将默认科室保存，再次点击登录就可获取默认科室
   private void getLocs(){

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
    //请求后台查看是否有新版本
    public void ifUpdate() {
        //如果有新版本，进行强制操作，否则无法进行下一步操作
        if(true){
            new WSPresenter(this).init();
        }

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showStudents(List<String> list,String wsCode) {

    }
}
