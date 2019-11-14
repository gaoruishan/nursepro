package com.dhcc.module.infusion.workarea.comm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.BaseHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.ViewGlobal;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;
import com.dhcc.res.infusion.CustomScanView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页-各个模块的父类
 * @author:gaoruishan
 * @date:202019-06-20/16:38
 * @email:grs0515@163.com
 */
public abstract class BaseInfusionFragment extends BaseFragment {

    protected static final String PROMPT_NO_ORD = "本次接单任务无此瓶贴,请核对!";
    protected String scanInfo;
    protected String scanInfoTemp;
    protected String episodeId = "";
    //执行开关: 0 未执行; 1已执行
    protected String exeFlag = "0";
    protected Activity mContext;
    protected List<String> listId;
    protected BaseHelper helper;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getActivity();
        setCommToolBar();
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).openMultiScan(AppUtil.isMultiScan());
            ((BaseActivity) mContext).addGlobalView(ViewGlobal.createInfusionGlobal(mContext));
        }
        AppUtil.initPlay(mContext, 0, R.raw.operate_success);
        helper = new BaseHelper(mContext);
        initViews();
        initDatas();
        //容错
        CustomScanView scanView = f(R.id.custom_scan, CustomScanView.class);
        if (scanView != null) {
            scanView.setVisibility(View.VISIBLE);
        }
    }

    private void setCommToolBar() {
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        setToolbarBottomLineVisibility(true);
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        mContext.findViewById(R.id.toolbar_bottom_line).setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue_dark2));
    }

    protected void initViews() {

    }

    protected void initDatas() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (helper != null) {
            helper.removeView();
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        scanInfo = doScanInfo(intent);
        if (scanInfo != null && scanInfo.contains("-")) {
            scanInfo = scanInfo.replaceAll("-", "\\|\\|");
        }
        //获取列表
        if (!TextUtils.isEmpty(scanInfo)) {
            getScanOrdList();
        }
    }

    /**
     * 获取信息列表
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
    protected abstract @LayoutRes
    int setLayout();

    /**
     * 刷新
     * @param bean
     */
    protected void refresh(CommResult bean) {
        onSuccessThings();
        DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
        getScanOrdList();
    }

    protected void onSuccessThings() {
        AppUtil.playSound(mContext, R.raw.operate_success);
    }

    /**
     * 校验列表中的OeoreId
     * @param list
     * @param s
     * @return
     */
    protected boolean checkListOeoreId(List<OrdListBean> list, String s) {
        //对于空List
        if (list == null || list.size() == 0) {
            return false;
        }
        //检验
        boolean isContain = false;
        listId = new ArrayList<>();
        for (OrdListBean b : list) {
            listId.add(b.getOeoreId());
            String bOeoreId = b.getOeoreId();
            //不包含|| 或者-的扫码结果
            if (bOeoreId.equals(scanInfo)) {
                isContain = true;
                break;
            }
            //当前扫码不为空,不包含|| ->腕带
            if (scanInfo != null && !scanInfo.contains("||")) {
                isContain = true;
                break;
            }
        }
        View tvOk = f(R.id.tv_ok);
        if (!isContain) {
            if (!TextUtils.isEmpty(s)) {
                ToastUtils.showShort(s);
                onFailThings();
            }
            //隐藏"确定"按钮
            if (tvOk != null) {
                tvOk.setVisibility(View.GONE);
            }
            return true;
        }
        if (tvOk != null) {
            tvOk.setVisibility(View.VISIBLE);
        }
        return false;
    }

    protected void onFailThings() {
        AppUtil.playSound(mContext, 0);
    }

    /**
     * 处理二次扫码
     * @param list
     * @param scan
     * @return
     */
    protected boolean forIsContain(List<OrdListBean> list, String scan) {
        boolean isContain = false;
        if (!scan.contains("||")) {
            scan = scanInfo;
        }
        for (OrdListBean b : list) {
            String bOeoreId = b.getOeoreId();
            //不包含|| 或者-的扫码结果
            if (bOeoreId.equals(scan)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    /**
     * 设置ToolBar 右侧-患者列表
     */
    protected void addPatListToToolbarRight() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_custom_view, null);
        setToolbarRightCustomView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startFragment(PatListFragment.class);
            }
        });
    }
}
