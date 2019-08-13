package com.dhcc.module.infusion.workarea.orderexecute;

import android.content.Intent;
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
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.orderexecute.adapter.OrderExecutePatOrderAdapter;
import com.dhcc.module.infusion.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrdScanInfoBean;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

/**
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
        f(R.id.custom_scan, CustomScanView.class).setTitle("请扫描腕带").setWarning("请您使用扫码设备，扫描病人腕带");
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
                        f(R.id.ll_orderexecute_selectnum).setVisibility(View.GONE);
                    } else {
                        f(R.id.ll_orderexecute_selectnum).setVisibility(View.VISIBLE);
                    }

                    if (patOrdsBean.getSelect() == null || "0".equals(patOrdsBean.getSelect()) || "".equals(patOrdsBean.getSelect())) {
                        patOrdsBean.setSelect("1");
                    } else {
                        patOrdsBean.setSelect("0");
                    }
                    patientOrderAdapter.notifyItemChanged(position);
                    if (f(R.id.ll_orderexecute_selectnum).getVisibility() == View.VISIBLE || f(R.id.ll_orderexecute_noselectbottom).getVisibility() == View.VISIBLE) {
                        refreshBottom();
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
                getOrdList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                String end = TimeUtils.millis2String(millseconds);
                endDate = end.substring(0, 10);
                endTime = end.substring(11, 16);
                getOrdList();
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
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList();
        }
    }

    private void getOrdList() {
        OrderExecuteApiManager.getScanInfo("", scanInfo, new CommonCallBack<OrdScanInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(OrdScanInfoBean bean, String type) {
                //PAT 扫腕带返回患者信息
                //ORD 扫医嘱条码返回医嘱信息
                if ("PAT".equals(bean.getFlag())) {
                    f(R.id.custom_scan).setVisibility(View.GONE);
//                    f(R.id.rl_orderexecute_scan).setVisibility(View.GONE);
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
                showToast(msg);
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
                    f(R.id.ll_orderexecute_noselectbottom).setVisibility(View.GONE);
                    f(R.id.ll_orderexecute_selectbottom).setVisibility(View.GONE);
                } else {
                    f(R.id.ll_orderexecute_noselectbottom).setVisibility(View.VISIBLE);
                    f(R.id.ll_orderexecute_selectbottom).setVisibility(View.GONE);
                    int exectype = 1;
                    String handleCode = "Y";
                    if (buttons.get(0).getDesc().contains("处理")) {
                        f(R.id.tv_bottom_noselecttext, TextView.class).setText("请您选择需处理的医嘱");
                        exectype = 0;
                        handleCode = "A";
                        f(R.id.tv_bottom_handletype, TextView.class).setText("接受");
                        setVisible(View.VISIBLE);
                    } else {
                        f(R.id.tv_bottom_noselecttext, TextView.class).setText("请您选择需执行的医嘱");
                        if ("PSD".equals(sheetCode)) {
                            f(R.id.tv_bottom_handletype, TextView.class).setText("阳性");
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

    public void refreshBottom() {
//        sbOeoreId = new StringBuffer();
//        sbOrderSaveInfo = new StringBuffer();
//        sbTimeSaveInfo = new StringBuffer();
        int selectCount = 0;
        for (int i = 0; i < patOrders.size(); i++) {
            if (patOrders.get(i) == null) return;
            String orderDescs = "";
            for (int j = 0; j < patOrders.get(i).size(); j++) {
                if (j == patOrders.get(i).size() - 1) {
                    orderDescs = orderDescs + patOrders.get(i).get(j).getOrderInfo().getArcimDesc();
                } else {
                    orderDescs = orderDescs + patOrders.get(i).get(j).getOrderInfo().getArcimDesc() + "\n";
                }
            }
            if (patOrders.get(i).get(0) != null && patOrders.get(i).get(0).getSelect() != null && "1".equals(patOrders.get(i).get(0).getSelect())) {
                if (selectCount == 0) {
//                    sbOeoreId.append(patOrders.get(i).get(0).getOrderInfo().getID());
//                    sbOrderSaveInfo.append(orderDescs);
//                    sbTimeSaveInfo.append(patOrders.get(i).get(0).getOrderInfo().getSttDateTime());
                } else {
//                    sbOeoreId.append("^" + patOrders.get(i).get(0).getOrderInfo().getID());
//                    sbOrderSaveInfo.append("^" + orderDescs);
//                    sbTimeSaveInfo.append("^" + patOrders.get(i).get(0).getOrderInfo().getSttDateTime());
                }
                selectCount++;
            }
        }
//        oeoreId = sbOeoreId.toString();
//        orderSaveInfo = sbOrderSaveInfo.toString();
//        timeSaveInfo = sbTimeSaveInfo.toString();
        f(R.id.ll_orderexecute_noselectbottom).setVisibility(selectCount == 0 ? View.VISIBLE : View.GONE);
        f(R.id.ll_orderexecute_selectbottom).setVisibility(selectCount == 0 ? View.GONE : View.VISIBLE);
        String desc0 = buttons.get(0).getDesc();
        String desc0Replace = desc0.replace("医嘱", "");
        if (selectCount == 0) {
            if (desc0.contains("执行")) {
                f(R.id.tv_bottom_noselecttext, TextView.class).setText("请您选择需执行的医嘱");
            } else {
                f(R.id.tv_bottom_noselecttext, TextView.class).setText("请您选择需处理的医嘱");
            }
        } else {
            f(R.id.tv_bottom_selecttext, TextView.class).setText("已选择" + selectCount + "个");
            if (buttons.size() == 1) {
                if (desc0.startsWith("撤销")) {
                    //撤销处理/执行
                    setBottomTodo(true, desc0Replace, false, null);
//                    if (exectype == 0) {
//                        execStatusCode = "";
//                    } else {
//                        execStatusCode = "C";
//                    }
                } else {
                    //处理/执行
                    setBottomTodo(false, null, true, desc0Replace);
//                    if (exectype == 0) {
//                        execStatusCode = handleCode;
//                    } else {
//                        if ("PSD".equals(sheetCode)) {
//                            execStatusCode = handleCode;
//                        } else {
//                            execStatusCode = "F";
//                        }
//                    }
                }
            } else if (buttons.size() == 2) {
                setBottomTodo(true, buttons.get(1).getDesc().replace("医嘱", "")
                        , true, desc0Replace);
            } else if (buttons.size() == 3) {
                setBottomTodo(true, buttons.get(1).getDesc().replace("医嘱", "")
                        , true, desc0Replace);
                String singleFlag = buttons.get(2).getSingleFlag();
            }

        }
    }

    private void setVisible(int visible) {
        f(R.id.view_bottom_handleline).setVisibility(visible);
        f(R.id.tv_bottom_handletype).setVisibility(visible);
        f(R.id.img_bottom_handlesure).setVisibility(visible);
    }

    private void setBottomTodo(boolean undo, String undoTxt, boolean todo, String todoTxt) {
        f(R.id.tv_bottom_undo).setVisibility(undo ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(undoTxt)) {
            f(R.id.tv_bottom_undo, TextView.class).setText(undoTxt);
        }
        f(R.id.tv_bottom_todo).setVisibility(todo ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(todoTxt)) {
            f(R.id.tv_bottom_todo, TextView.class).setText(todoTxt);
        }
    }
}
