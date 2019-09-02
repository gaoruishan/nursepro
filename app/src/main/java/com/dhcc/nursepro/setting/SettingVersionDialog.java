package com.dhcc.nursepro.setting;


import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * SettingVersionDialog
 * 显示版本号
 *
 * @author Devlix126
 * created at 2019/9/2 15:46
 */
public class SettingVersionDialog extends Dialog {
    private Context context;
    private TextView tvSettingversionVersionname;
    private TextView tvSettingversionVersionCode;


    public SettingVersionDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_version_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        //        initEvent();
    }

    private void initView() {
        tvSettingversionVersionname = findViewById(R.id.tv_settingversion_versionname);
        tvSettingversionVersionCode = findViewById(R.id.tv_settingversion_versionCode);

    }

    private void initData() {
        tvSettingversionVersionname.setText(getVersion("VersionName"));
        tvSettingversionVersionCode.setText(getVersion("VersionCode"));

    }

    //查询当前版本
    private String getVersion(String str) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            if (str.equals("VersionName")) {
                return "版本号：V" + info.versionName;
            } else if (str.equals("VersionCode")) {
                return "开发标识：" + info.versionCode;
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取当前版本号";
        }
    }

}