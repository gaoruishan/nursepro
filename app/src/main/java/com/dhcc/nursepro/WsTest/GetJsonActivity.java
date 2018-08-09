package com.dhcc.nursepro.WsTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class GetJsonActivity extends AppCompatActivity {
    private TextView tvgetjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getjson);

        initView();
    }

    private void initView() {
        tvgetjson = findViewById(R.id.tv_getjson);
        tvgetjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init3();
            }
        });
    }
    private void init3() {
//        final ListView mCityList = view;
        //添加参数
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("curVersion", "7.2");
        //显示进度条
//        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "GetNewVersion", properties, new WebServiceUtils.WebServiceCallBack() {

            @Override
            public void callBack(String result) {
//                ProgressDialogUtils.dismissProgressDialog();
                if (result != null) {
                    tvgetjson.setText(result);
                } else {
                    Toast.makeText(GetJsonActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
