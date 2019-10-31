package com.dhcc.health;

import android.os.Bundle;

import com.base.commlibs.BaseActivity;
import com.dhcc.module.health.workarea.WorkAreaFragment;
import com.dhcc.res.nurse.CustomBottomBarLayout;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        setContentView(R.layout.activity_main);
        CustomBottomBarLayout cbLayout = findViewById(R.id.cb_layout);
        cbLayout.addTab(new WorkAreaFragment(), "主页", R.drawable.dhcc_workarea_ico, R.drawable.dhcc_workarea_hover_ico)
//                .addTab(new MessageFragment(),"消息",R.drawable.dhcc_message_ico,R.drawable.dhcc_message_hover_ico)
//                .addTab(new SettingFragment(),"设置",R.drawable.dhcc_setting_ico,R.drawable.dhcc_setting_hover_ico)
                .onlyOneFragment(true)
                .init(getSupportFragmentManager(), 0);
    }
}
