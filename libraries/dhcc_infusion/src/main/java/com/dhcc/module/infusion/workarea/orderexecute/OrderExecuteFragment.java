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
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.orderexecute.adapter.OrderExecutePatOrderAdapter;
import com.dhcc.module.infusion.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrdScanInfoBean;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.res.infusion.CustomDateTimeView;
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
    private List<List<OrderExecuteBean.OrdersBean.PatOrdsBean>> patOrders = null;
    private List<OrderExecuteBean.ButtonsBean> buttons;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("医嘱执行");
        showScanPatHand();
        setDateTime();
        sheetListView = f(R.id.custom_sheet_list, CustomSheetListView.class);
        sheetListView.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SheetListBean item = (SheetListBean) adapter.getItem(position);
                sheetCode = item.getCode();
                asyncInitData();
            }
        });
        patientOrderAdapter = new OrderExecutePatOrderAdapter(null);
        RecyclerView recyOrderexecutePatorder = RecyclerViewHelper.get(mContext, R.id.recy_orderexecute_patorder);
        recyOrderexecutePatorder.setAdapter(patientOrderAdapter);
        patientOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = patOrders.get(position).get(0);
                if (view.getId() == R.id.ll_oepat_orderselect) {
                    if ("PSD".equals(sheetCode)) {
                        f(R.id.ll_select_num).setVisibility(View.GONE);
                    } else {
                        f(R.id.ll_select_num).setVisibility(View.VISIBLE);
                    }

                    if (patOrdsBean.getSelect() == null || "0".equals(patOrdsBean.getSelect()) || "".equals(patOrdsBean.getSelect())) {
                        patOrdsBean.setSelect("1");
                    } else {
                        patOrdsBean.setSelect("0");
                    }
                    patientOrderAdapter.notifyItemChanged(position);
                    if (f(R.id.ll_select_num).getVisibility() == View.VISIBLE || f(R.id.ll_no_select).getVisibility() == View.VISIBLE) {
                    }

                }
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
        if (!TextUtils.isEmpty(string)) {
            endDate = string.substring(0, 10);
            endTime = string.substring(11, 16);
        }
    }

    private void getStartDateTime(String string) {
        if (!TextUtils.isEmpty(string)) {
            startDate = string.substring(0, 10);
            startTime = string.substring(11, 16);
        }
    }

    @Override
    protected void getScanOrdList() {
        OrderExecuteApiManager.getScanInfo("", scanInfo, new CommonCallBack<OrdScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(OrdScanInfoBean bean, String type) {
                f(R.id.custom_scan).setVisibility(View.GONE);
                if ("PAT".equals(bean.getFlag())) {
                    OrdScanInfoBean.PatInfoBean patInfoBean = bean.getPatInfo();
                    episodeId = patInfoBean.getEpisodeID();
                    regNo = patInfoBean.getRegNo();
                    f(R.id.tv_orderexecute_patinfo, TextView.class).setText("".equals(patInfoBean.getBedCode()) ? "未分床  " + patInfoBean.getName() : patInfoBean.getBedCode() + "  " + patInfoBean.getName());
                    patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                    patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                    asyncInitData();
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
                buttons = bean.getButtons();
                //左侧列表判断有无默认值，有的话滑动到默认值类型
                if (!"".equals(bean.getSheetDefCode())) {
                    sheetListView.setSheetDefCode(bean.getSheetDefCode());
                }
                List<OrderExecuteBean.ButtonsBean> buttons = bean.getButtons();

                if (buttons.size() == 0) {
                    f(R.id.ll_no_select).setVisibility(View.GONE);
                    f(R.id.ll_select).setVisibility(View.GONE);
                } else {
                    f(R.id.ll_no_select).setVisibility(View.VISIBLE);
                    f(R.id.ll_select).setVisibility(View.GONE);
                    int exectype = 1;
                    String handleCode = "Y";
                    if (buttons.get(0).getDesc().contains("处理")) {
                        f(R.id.tv_no_select_text, TextView.class).setText("请您选择需处理的医嘱");
                        exectype = 0;
                        handleCode = "A";
                        f(R.id.tv_type, TextView.class).setText("接受");
                        setVisible(View.VISIBLE);
                    } else {
                        f(R.id.tv_no_select_text, TextView.class).setText("请您选择需执行的医嘱");
                        if ("PSD".equals(sheetCode)) {
                            f(R.id.tv_type, TextView.class).setText("阳性");
                            setVisible(View.VISIBLE);
                        } else {
                            setVisible(View.GONE);
                        }
                    }
                }
                patOrders = null;
                if (bean.getOrders().size() > 0) {
                    OrderExecuteBean.OrdersBean patient = bean.getOrders().get(0);
                    patOrders = patient.getPatOrds();
                }
                if (patOrders != null) {
                    patientOrderAdapter.setSize(patOrders.size());
                    patientOrderAdapter.setDetailColums(bean.getDetailColums());
                }
                patientOrderAdapter.setNewData(patOrders);
                patientOrderAdapter.loadMoreEnd();

            }
        });
    }


    private void setVisible(int visible) {
        f(R.id.view_line).setVisibility(visible);
        f(R.id.tv_type).setVisibility(visible);
        f(R.id.img_sure).setVisibility(visible);
    }


}
