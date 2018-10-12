package com.dhcc.nursepro.workarea.bedmap;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

/**
 * bedMapPatInfoFragment
 * 个人信息
 *
 * @author DevLix126
 * created at 2018/10/11 16:07
 */
public class bedMapPatInfoFragment extends BaseFragment {

    private BedMapBean.PatInfoListBean patInfoListBean;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_map_pat_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        patInfoListBean = (BedMapBean.PatInfoListBean) bundle.getSerializable("patinfo");

        //状态栏 背景 默认蓝色
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        //toolbar type
        setToolbarType(BaseActivity.ToolbarType.TOP);
        //toolbar 背景 默认蓝色
        setToolbarBackground(new ColorDrawable(0xffffffff));
        //toolbar 导航按钮隐藏
        //        hideToolbarNavigationIcon();
        //toolbar 导航按钮显示 默认白色返回按钮
        //        showToolbarNavigationIcon();
        //返回按钮img设置
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        //toolbar 中间标题 默认黑色
        setToolbarCenterTitle("");
        //toolbar 中间标题 内容 颜色 字号默认17
        //        setToolbarCenterTitle(getString(R.string.title_bedmap),0xffffffff,17);
        //toolbar 分割线
        setToolbarBottomLineVisibility(true);


        initView(view);
        initData();


    }

    private void initView(View view) {


    }

    private void initData() {

    }

}
