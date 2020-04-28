package com.dhcc.module.infusion.workarea.comm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.BaseHelper;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.SimpleCallBack;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.ViewGlobal;
import com.dhcc.module.infusion.workarea.comm.bean.MainConfigBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;
import com.dhcc.res.infusion.CustomOnOffView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.nurse.bean.ConfigBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页-各个模块的父类
 * @author:gaoruishan
 * @date:202019-06-20/16:38
 * @email:grs0515@163.com
 */
public abstract class BaseInfusionFragment extends BaseFragment {

    public static final String STR_ORD_ING = "当前输液中,不可穿刺";
    public static final String STR_WAY_NO = "通道";

    public static final String SCAN_HAND = "请扫描腕带";
    public static final String SCAN_PAT_HAND = "请您使用扫码设备，扫描病人腕带";

    public static final String SCAN_LABEL = "请扫描贴签";
    public static final String SCAN_DRUG_LABEL = "请您使用扫码设备，扫描药品贴签";

    public static final String SCAN_LABEL_CARD = "请扫描贴签/信息卡";
    public static final String SCAN_LABEL_CARD_INFO = "请您使用扫码设备，扫描药品贴签/信息卡";
    public static final String PAT = "PAT";
    public static final String ORD = "ORD";
    protected static final String PROMPT_NO_ORD = "本次接单任务无此贴签 ,请核对!";
    protected String scanInfo;
    protected String scanInfoTemp;
    protected String episodeId = "";
    protected String regNo = "";
    protected String barCode="";
    //执行开关: 0 未执行; 1已执行
    protected String exeFlag = "0";
    protected Activity mContext;
    protected List<String> listId;
    protected BaseHelper helper;
    protected CustomSelectView customSelectChannel;
    protected CustomOnOffView customOnOff;

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
        //加载配置
        initConfig();
        //手动输入
        if (UserUtil.isHandInput()) {
            addHandInputToToolbarRight();
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

    private void initConfig() {
        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        if (json != null) {// 设置fragment标题名
            String curClass = this.getClass().getSimpleName();
            for (ConfigBean configBean : json.getMainList()) {
                if (configBean.getFragment().contains(curClass)) {
                    setToolbarCenterTitle(configBean.getName());
                    break;
                }
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (helper != null) {
            helper.removeView();
        }
    }

    /**
     * 设置病人标签
     * @param customPat
     * @param bean
     */
    protected void setCustomPatViewData(CustomPatView customPat, PatInfoBean bean) {
        if (bean != null && customPat != null) {
            customPat.setRegNo(bean.getPatRegNo()).setPatName(bean.getPatName())
                    .setAge(bean.getPatAge()).setSeat(bean.getPatSeat()).setPatSex(bean.getPatSex());
        }
    }

    /**
     * 显示瓶贴Scan界面
     */
    protected void showScanLabel() {
        CustomScanView scanView = f(R.id.custom_scan, CustomScanView.class);
        if (scanView != null) {
            scanView.setTitle(SCAN_LABEL).setWarning(SCAN_DRUG_LABEL);
        }
    }

    /**
     * 显示腕带Scan界面
     */
    protected void showScanPatHand() {
        CustomScanView scanView = f(R.id.custom_scan, CustomScanView.class);
        if (scanView != null) {
            scanView.setTitle(SCAN_HAND).setWarning(SCAN_PAT_HAND);
        }
    }
    protected void showScanLabelOrCard() {
        CustomScanView scanView = f(R.id.custom_scan, CustomScanView.class);
        if (scanView != null) {
            scanView.setTitle(SCAN_LABEL_CARD).setWarning(SCAN_LABEL_CARD_INFO);
        }
    }

    /**
     * 隐藏Scan界面
     */
    protected void hideScanView() {
        helper.setVisible(R.id.custom_scan, false);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        String scanInfo = doScanInfo(intent);
        if (scanInfo != null && scanInfo.contains("-")) {
            scanInfo = scanInfo.replaceAll("-", "\\|\\|");
        }
        //扫码Action
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())
                && !TextUtils.isEmpty(scanInfo)) {
            this.scanInfo = scanInfo;
            //获取列表
            getScanOrdList();
        }
    }

    /**
     * 获取扫码Action信息
     */
    protected abstract void getScanOrdList();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContainer != null) {
            mContainer.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.white));
        }
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
     * 刷新-扫码调用一次接口
     * @param bean
     */
    protected void refresh(CommResult bean) {
        onSuccessThings();
        showToast(bean.getMsg());
        getScanOrdList();
    }

    protected void onSuccessThings(CommResult... bean) {
        AppUtil.playSound(mContext, R.raw.operate_success);
        if (bean != null && bean.length > 0) {
            showToast(bean[0].getMsg());
        }
    }

    protected void showToast(String msg) {
        DialogFactory.showCommDialog(getActivity(), msg, null, 0, null, true);
    }
    /**
     * 两次验证
     * @param ordList
     * @param curRegNo
     * @param curOeoreId
     */
    protected boolean auditOrdInfo(List<OrdListBean> ordList, String curRegNo, String curOeoreId) {
        View view = f(R.id.tv_ok);
        if (view!= null) {
            view.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(curRegNo) && !TextUtils.isEmpty(curOeoreId)) {
                view.setVisibility(View.VISIBLE);
                //再次检查
                if (!forIsContain(ordList,curOeoreId)) {
                    view.setVisibility(View.GONE);
                }
            }
            return view.getVisibility() == View.VISIBLE;
        }
        return false;
    }

    /**
     * 初始化通道
     * @param str
     */
    protected void setInitWayNo(String str) {
        customSelectChannel = mContainerChild.findViewById(R.id.custom_select_channel);
        customOnOff = mContainerChild.findViewById(R.id.custom_on_off);
        customOnOff.setSelect(false);
        customOnOff.setShowSelectText(str, str);
        customOnOff.setOnSelectListener(new SimpleCallBack<Boolean>() {
            @Override
            public void call(Boolean result, int type) {
                customSelectChannel.setVisibility(result ? View.GONE : View.VISIBLE);
            }
        });
    }

    /**
     * 滚动到指定位置
     * @param recyclerView
     * @param list
     */
    protected void scrollToPosition(RecyclerView recyclerView,List<OrdListBean> list) {
        int pst = 0;
        for (int i = 0; i < list.size(); i++) {
            OrdListBean b = list.get(i);
            if (b.getOeoreId().equals(scanInfo)) {
                pst = i;
            }
        }
        recyclerView.scrollToPosition(pst);
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
        if (tvOk != null) {
            tvOk.setVisibility(View.VISIBLE);
        }
        if (!isContain) {
            if (!TextUtils.isEmpty(s)) {
                ToastUtils.showShort(s);
                onFailThings();
            }
            //隐藏"确定"按钮
            if (tvOk != null) {
                tvOk.setVisibility(View.GONE);
            }
        }
        return isContain;
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
     * 设置ToolBar
     */
    protected void addHandInputToToolbarRight() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_custom_view, null);
        TextView tv = view.findViewById(R.id.tv_txt);
        tv.setText("手动输入");
        setToolbarRightCustomView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFactory.showTest(mContext, new CommDialog.CommClickListener() {
                    @Override
                    public void data(Object[] args) {
                        if(!TextUtils.isEmpty((String) args[0])){
                            scanInfo = (String) args[0];
                            getScanOrdList();
                        }
                    }
                });
            }
        });
    }
}
