package com.dhcc.nursepro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.base.commlibs.http.CommHttp;
import com.base.commlibs.utils.LocalTestManager;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.workarea.workareautils.WorkareaMainConfig;

public class SplashActivity extends AppCompatActivity {

    private long delayMillis =2000 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (LocalTestManager.isTest()) {
                    CommHttp.getNurseConfig();
                    CommHttp.initBroadcastList();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    WorkareaMainConfig workareaMainConfig = new WorkareaMainConfig();
//                    workareaMainConfig.getMainConfigData(SplashActivity.this);
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();

            }
        },delayMillis);
    }

}
