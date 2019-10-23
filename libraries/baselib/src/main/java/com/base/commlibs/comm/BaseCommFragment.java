package com.base.commlibs.comm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.R;
import com.base.commlibs.utils.AppUtil;

import java.util.List;

/**
 * 主页-各个模块的父类
 * @author:gaoruishan
 * @date:202019-06-20/16:38
 * @email:grs0515@163.com
 */
public abstract class BaseCommFragment extends BaseFragment implements View.OnClickListener {

    protected static final String PROMPT_NO_ORD = "本次接单任务无此瓶贴,请核对!";
    protected String scanInfo;
    protected Activity mContext;
    protected List<String> listId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getActivity();
        setCommToolBar();
        initViews();
        initConfig();
        initDatas();
    }

    /**
     * 初始化配置-子类重写方法 添加额外方法;
     * 例如: initPlay()提示音
     */
    protected void initConfig() {
        //默认是关闭的
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).openMultiScan(AppUtil.isMultiScan());
        }
        //进行初始化等... initPlay()
    }

    /**
     * 初始化提示音
     */
    private void initPlay() {
        AppUtil.initPlay(mContext, 0, R.raw.operate_success);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 初始化View
     */
    protected abstract void initViews();

    /**
     * 设置统一标题
     */
    private void setCommToolBar() {
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        setToolbarBottomLineVisibility(true);
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        mContext.findViewById(R.id.toolbar_bottom_line).setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue_dark2));
        //设置底部背景
        mContainer.setBackgroundResource(R.color.color_base_content);
    }

    /**
     * 操作成功提示音
     */
    protected void onSuccessThings() {
        AppUtil.playSound(mContext, R.raw.operate_success);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        scanInfo = doScanInfo(intent);
        if (scanInfo != null && scanInfo.contains("-")) {
            scanInfo = scanInfo.replaceAll("-", "\\|\\|");
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() != 0) {
            return inflater.inflate(setLayout(), container, false);
        }
        return null;
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int setLayout();

    /**
     * 错误提示音
     */
    protected void onFailThings() {
        AppUtil.playSound(mContext, 0);
    }

    /**
     * 设置ToolBar 右侧-患者列表
     */
    protected void addPatListToToolbarRight() {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_custom_view, null);
//        setToolbarRightCustomView(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startFragment(PatListFragment.class);
//            }
//        });
    }
}
