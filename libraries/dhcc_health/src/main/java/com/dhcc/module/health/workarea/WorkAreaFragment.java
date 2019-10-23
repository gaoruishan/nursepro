package com.dhcc.module.health.workarea;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.utils.CommRes;
import com.dhcc.module.health.R;
import com.dhcc.res.nurse.CustomWorkAreaLayout;
import com.dhcc.res.nurse.bean.ConfigWorkArea;

/**
 * 主页
 * @author:gaoruishan
 * @date:202019-10-22/17:24
 * @email:grs0515@163.com
 */
public class WorkAreaFragment extends BaseCommFragment {

    private CustomWorkAreaLayout cwaLayout;

    @Override
    protected void initDatas() {
        CommRes.readJson("config_work_area.json", ConfigWorkArea.class, new CommRes.CallRes<ConfigWorkArea>() {
            @Override
            public void call(ConfigWorkArea conf, String s) {
                cwaLayout.initData(WorkAreaFragment.this, conf.getList());
            }
        });
    }

    @Override
    protected void initViews() {
        cwaLayout = f(R.id.cwa_layout, CustomWorkAreaLayout.class);
    }

    @Override
    protected void initConfig() {
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_workarea;
    }
}
