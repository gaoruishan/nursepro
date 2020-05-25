package com.dhcc.infusion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.utils.LocalTestManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.dhcc.module.infusion.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LocalTestManager.isTest()) {
                    CommHttp.initBroadcastList();
                    ActivityUtils.startActivity(new Intent(Action.MainActivity));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        },2000);
    }

}
