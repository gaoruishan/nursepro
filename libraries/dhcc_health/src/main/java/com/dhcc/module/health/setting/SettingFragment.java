package com.dhcc.module.health.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.dhcc.module.health.R;

/**
 * 设置
 * @author:gaoruishan
 * @date:202019-10-22/17:24
 * @email:grs0515@163.com
 */
public class SettingFragment extends BaseFragment {
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.health_fragment_setting, container, false);
    }
}
