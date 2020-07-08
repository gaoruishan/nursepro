package com.dhcc.module.infusion.workarea.orderexecute;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;
import com.dhcc.module.infusion.workarea.orderexecute.adapter.OrderExecutePatOrderAdapter;
import com.dhcc.module.infusion.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrdButtonsBean;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-07-04/10:23
 * @email:grs0515@163.com
 */
public class OrderExecuteFragment extends BaseInfusionFragment {

    private String episodeId;
    private String regNo;
    private String patInfo;
    private String patSaveInfo;
    private SPUtils spUtils = SPUtils.getInstance();
    private String startDate = "";
    private String startTime = "";
    private String endDate = "";
    private String endTime = "";
    private String sheetCode = "";
    private CustomSheetListView sheetListView;
    private OrderExecutePatOrderAdapter patientOrderAdapter;
    private List<OrdButtonsBean> buttons;
    private CustomOrdExeBottomView bottomView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("医嘱执行");
        showScanPatHand();
        setDateTime();
        sheetListView = f(R.id.custom_sheet_list, CustomSheetListView.class);
        bottomView = f(R.id.custom_bottom, CustomOrdExeBottomView.class);
        sheetListView.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SheetListBean item = (SheetListBean) adapter.getItem(position);
                sheetCode = item.getCode();
                asyncInitData();
            }
        });
        patientOrderAdapter = AdapterFactory.getOrderExecuteAdapter();
        RecyclerView recyOrderexecutePatorder = RecyclerViewHelper.get(mContext, R.id.recy_orderexecute_patorder);
        recyOrderexecutePatorder.setAdapter(patientOrderAdapter);
        patientOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter instanceof OrderExecutePatOrderAdapter) {
                    BloodOrdListBean patOrdsBean = (BloodOrdListBean) adapter.getData().get(position);
                    patientOrderAdapter.refreshData(adapter.getData(), position);
                    bottomView.setSelectText("已选 1 个");
                }
            }
        });
        bottomView.setBottomViewClickListener(new SimpleCallBack<View>() {
            @Override
            public void call(View result, int type) {

            }
        });
    }

    private void setDateTime() {
        CustomDateTimeView customDateTimeView = f(R.id.custom_cdtv, CustomDateTimeView.class);
        customDateTimeView.setShowTime(true);
        customDateTimeView.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getStartDateTime(TimeUtils.millis2String(millseconds));
                getScanOrdList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getEndDateTime(TimeUtils.millis2String(millseconds));
                getScanOrdList();
            }
        });
        getStartDateTime(spUtils.getString(SharedPreference.SCHSTDATETIME));
        getEndDateTime(spUtils.getString(SharedPreference.SCHENDATETIME));
    }

    private void getEndDateTime(String string) {
        if (TextUtils.isEmpty(string)) {
            string = TimeUtils.millis2String(System.currentTimeMillis());
        }
        endDate = string.substring(0, 10);
        endTime = string.substring(11, 16);
    }

    private void getStartDateTime(String string) {
        if (TextUtils.isEmpty(string)) {
            string = TimeUtils.millis2String(System.currentTimeMillis());
        }
        startDate = string.substring(0, 10);
        startTime = string.substring(11, 16);

    }

    @Override
    protected void getScanOrdList() {
        OrderExecuteApiManager.getScanInfo(regNo, scanInfo, new CommonCallBack<ScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(ScanInfoBean bean, String type) {
                f(R.id.custom_scan).setVisibility(View.GONE);
                //PAT 扫腕带返回患者信息
                if (PAT.equals(bean.getFlag())) {
                    PatInfoBean patInfoBean = bean.getPatInfo();
                    regNo = patInfoBean.getPatRegNo();
                    f(R.id.tv_orderexecute_patinfo, TextView.class).setText(patInfoBean.getPatName() +"  "+ patInfoBean.getPatAge() + "  " + patInfoBean.getPatRegNo());
                    patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getPatName() + "-" + patInfoBean.getPatSex() + "-" + patInfoBean.getPatAge();
                    patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getPatName();
                    asyncInitData();
                }
                //ORD 扫医嘱条码返回医嘱信息
                if (ORD.equals(bean.getFlag())) {
                    //选中扫码的
//                    for (BloodOrdListBean bean1 : injectAdapter.getData()) {
//                        bean1.setSelect(scanInfo.equals(bean1.getOeoriId()) ? "1" : "0");
//                    }
//                    injectAdapter.notifyDataSetChanged();
                    //弹框
//                    if ("1".equals(bean.getDiagFlag())) {
//                        DialogFactory.showPatInfo(mContext, bean, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                exeInjectOrd();
//                            }
//                        });
//                    }else {
//                        exeInjectOrd();
//                    }
//                    Toast.makeText(getContext(),"执行注射操作请点击确定执行",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.dhcc_fragment_order_execute;
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecuteApiManager.getOrder(regNo, sheetCode, startDate, startTime, endDate, endTime, new CommonCallBack<OrderExecuteBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                onFailThings();
            }

            @Override
            public void onSuccess(OrderExecuteBean bean, String type) {
                hideLoadingTip();
                sheetListView.setDatas(bean.getSheetList());
                //左侧列表判断有无默认值，有的话滑动到默认值类型
                bottomView.addBottomView(bean.getButtons());

                patientOrderAdapter.setInitData(bean.getOrdList());
                patientOrderAdapter.loadMoreEnd();
            }
        });
    }



}
