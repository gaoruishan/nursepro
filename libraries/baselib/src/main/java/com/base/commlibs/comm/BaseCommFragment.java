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
import com.base.commlibs.constant.Action;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.BaseHelper;

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
    protected BaseHelper helper;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getActivity();
        helper = new BaseHelper(mContext);
        setCommToolBar();
        initViews();
        initConfig();
        initDatas();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (helper != null) {
            helper.removeView();
        }
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
        //进行初始化等...
        initPlay();
    }

    /**
     * 初始化提示音
     */
    protected void initPlay() {
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
    public void setCommToolBar() {
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        setToolbarBottomLineVisibility(true);
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        mContext.findViewById(R.id.toolbar_bottom_line).setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue_dark2));
        //设置底部背景
        mContainer.setBackgroundResource(R.color.color_base_content);
    }

    public void setCommToolBar2() {
        setStatusBarBackgroundViewVisibility(true, 0xff4C95EF);
        setToolbarBackground(new ColorDrawable(0xff4C95EF));
        setToolbarBottomLineVisibility(true);
        showToolbarNavigationIcon(R.drawable.icon_back_white);
        setToolbarCenterTitle("",0xffffffff,17);
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
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction()) && scanInfo != null) {
            getScanOrdList();
        }
    }

    /**
     * 扫码Action
     */
    protected void getScanOrdList() {

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
