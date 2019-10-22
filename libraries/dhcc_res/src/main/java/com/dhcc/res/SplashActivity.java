package com.dhcc.res;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.base.commlibs.constant.Action;
import com.grs.dhcc_res.R;

/**
 * 通用启动页
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhcc_activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //清单中 加入Action: com.dhcc.intent.action.login
                startActivity(new Intent(Action.LoginActivity));
                finish();
            }
        },2000);
    }

}
