package com.dhcc.nursepro.workarea.dosingreview;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.dosingreview.adapter.DosingReviewPatientAdapter;
import com.dhcc.nursepro.workarea.dosingreview.api.DosingReviewApiManager;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;
import com.dhcc.nursepro.workarea.dosingreview.bean.PreparedVerifyOrdBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * DosingReviewFragment
 * 配液复核
 *
 * @author DevLix126
 * created at 2018/9/6 10:20
 */
public class DosingReviewFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener, BaseQuickAdapter.RequestLoadMoreListener {
    private TextView tvDosingreviewCurrent;
    private TextView tvDosingreviewUndosing;
    private TextView tvDosingreviewDosing;
    private TextView tvDosingreviewUnreview;
    private TextView tvDosingreviewReview;
    private TextView tvDosingreviewStartdate;
    private TextView tvDosingreviewEnddate;
    private RecyclerView recyDosingreview;
    private LinearLayout llEmpty;

    private List<DosingReViewBean> dosingReViewBeanList;
    private List<DosingReViewBean.OrdersBean> orders;
    private List<DosingReViewBean.OrdersBean> currentOrders = new ArrayList<>();
    private List<DosingReViewBean.TopfilterBean> topfilterBeans;
    private DosingReviewPatientAdapter patientAdapter;

    private SPUtils spUtils = SPUtils.getInstance();
    private String etChangeFlag;
    private String episodeId;
    private String infusionFlag = "";
    private String startDate;
    private String endDate;
    private String pageNo = "1";

    private String orderId = "";

    private DosingReviewResultDialog dosingReviewResultDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_dosingreview), 0xffffffff, 17);

        startDate = DateUtils.getDateTimeAgo(spUtils.getString(SharedPreference.CURDATETIME),1).substring(0, 10);
        endDate = spUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);


    }

    private void initView(View view) {
        llEmpty = view.findViewById(R.id.ll_dosingreview_empty);
        tvDosingreviewCurrent = view.findViewById(R.id.tv_dosingreview_current);
        tvDosingreviewCurrent.setOnClickListener(this);
        tvDosingreviewUndosing = view.findViewById(R.id.tv_dosingreview_undosing);
        tvDosingreviewUndosing.setOnClickListener(this);
        tvDosingreviewDosing = view.findViewById(R.id.tv_dosingreview_dosing);
        tvDosingreviewDosing.setOnClickListener(this);
        tvDosingreviewUnreview = view.findViewById(R.id.tv_dosingreview_unreview);
        tvDosingreviewUnreview.setOnClickListener(this);
        tvDosingreviewReview = view.findViewById(R.id.tv_dosingreview_review);
        tvDosingreviewReview.setOnClickListener(this);
        tvDosingreviewStartdate = view.findViewById(R.id.tv_dosingreview_startdate);
        tvDosingreviewStartdate.setOnClickListener(this);
        tvDosingreviewEnddate = view.findViewById(R.id.tv_dosingreview_enddate);
        tvDosingreviewEnddate.setOnClickListener(this);
        recyDosingreview = view.findViewById(R.id.recy_dosingreview);

        tvDosingreviewStartdate.setText(startDate);
        tvDosingreviewEnddate.setText(endDate);

        setTopFilterSelect(tvDosingreviewUndosing);

        //提高展示效率
        recyDosingreview.setHasFixedSize(true);
        //设置的布局管理
        recyDosingreview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        patientAdapter = new DosingReviewPatientAdapter(new ArrayList<DosingReViewBean.OrdersBean>());
        patientAdapter.setOnLoadMoreListener(this, recyDosingreview);

        recyDosingreview.setAdapter(patientAdapter);
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        DosingReviewApiManager.getInfusionOrdList(infusionFlag, startDate, endDate, pageNo, new DosingReviewApiManager.DosingReviewCallback() {
            @Override
            public void onSuccess(DosingReViewBean dosingReViewBean) {
                hideLoadingTip();
                topfilterBeans = dosingReViewBean.getTopfilter();
                orders = dosingReViewBean.getOrders();

                if ("1".equals(pageNo)) {
                    patientAdapter.setNewData(orders);
                    if (orders.size() == 0) {
                        patientAdapter.loadMoreEnd();
                        llEmpty.setVisibility(View.VISIBLE);
                    } else {
                        patientAdapter.loadMoreComplete();
                        llEmpty.setVisibility(View.GONE);
                    }
                } else {
                    if (orders.size() == 0) {
                        patientAdapter.loadMoreEnd();
                    } else {
                        patientAdapter.addData(orders);
                        patientAdapter.loadMoreComplete();
                    }
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void setTopFilterSelect(View view) {
        tvDosingreviewCurrent.setSelected(view == tvDosingreviewCurrent);
        tvDosingreviewUndosing.setSelected(view == tvDosingreviewUndosing);
        tvDosingreviewDosing.setSelected(view == tvDosingreviewDosing);
        tvDosingreviewUnreview.setSelected(view == tvDosingreviewUnreview);
        tvDosingreviewReview.setSelected(view == tvDosingreviewReview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dosingreview_current:
                setTopFilterSelect(tvDosingreviewCurrent);
                patientAdapter.setNewData(currentOrders);
                patientAdapter.loadMoreEnd();
                if (currentOrders.size() < 1) {
                    llEmpty.setVisibility(View.VISIBLE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_dosingreview_undosing:
                setTopFilterSelect(tvDosingreviewUndosing);
                infusionFlag = topfilterBeans.get(1).getCode();
                pageNo = "1";
                asyncInitData();
                break;
            case R.id.tv_dosingreview_dosing:
                setTopFilterSelect(tvDosingreviewDosing);
                infusionFlag = topfilterBeans.get(2).getCode();
                pageNo = "1";
                asyncInitData();
                break;
            case R.id.tv_dosingreview_unreview:
                setTopFilterSelect(tvDosingreviewUnreview);
                infusionFlag = topfilterBeans.get(3).getCode();
                pageNo = "1";
                asyncInitData();
                break;
            case R.id.tv_dosingreview_review:
                setTopFilterSelect(tvDosingreviewReview);
                infusionFlag = topfilterBeans.get(4).getCode();
                pageNo = "1";
                asyncInitData();
                break;
            case R.id.tv_dosingreview_startdate:
                etChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvDosingreviewStartdate.getText().toString() + " 00:00:00"));

                break;
            case R.id.tv_dosingreview_enddate:
                etChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvDosingreviewEnddate.getText().toString() + " 00:00:00"));

                break;
            default:
                break;
        }
    }

    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);

        if ("START".equals(etChangeFlag)) {
            if (!date.equals(startDate)) {
                //日期时间发生改变，需重新请求数据
                startDate = date;
                pageNo = "1";
                asyncInitData();
            }

            tvDosingreviewStartdate.setText(TimeUtils.millis2String(millseconds).substring(0, 10));
        } else {
            if (!date.equals(endDate)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                pageNo = "1";
                asyncInitData();
            }

            tvDosingreviewEnddate.setText(TimeUtils.millis2String(millseconds).substring(0, 10));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        pageNo = Integer.valueOf(pageNo) + 1 + "";
        asyncInitData();
    }

    /**
     * Action.DOSING_REVIEW
     * 撤销
     * 请求ID 刷新列表
     * <p>
     * <p>
     * Action.DEVICE_SCAN_CODE
     * 扫码配液/复核
     * 请求ID current列表添加返回单条医嘱
     *
     * @author DevLix126
     * created at 2018/9/12 14:51
     */
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DOSING_REVIEW.equals(intent.getAction())) {
            showLoadingTip(BaseActivity.LoadingType.FULL);
            orderId = intent.getStringExtra("orderId");
            pageNo = "1";
            DosingReviewApiManager.getInfusionOrdListAfterCancel(infusionFlag, orderId, startDate, endDate, pageNo, new DosingReviewApiManager.DosingReviewCallback() {
                @Override
                public void onSuccess(DosingReViewBean dosingReViewBean) {
                    hideLoadingTip();

                    orders = dosingReViewBean.getOrders();
                    patientAdapter.setNewData(orders);
                    if (orders.size() == 0) {
                        patientAdapter.loadMoreEnd();
                    } else {
                        patientAdapter.loadMoreComplete();
                    }

                    if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                        dosingReviewResultDialog.dismiss();
                    }
                    dosingReviewResultDialog = new DosingReviewResultDialog(getActivity());
                    dosingReviewResultDialog.setDRresult(dosingReViewBean.getMsg());
                    dosingReviewResultDialog.setImgId(R.drawable.icon_popup_sucess);
                    dosingReviewResultDialog.setSureVisible(View.GONE);
                    dosingReviewResultDialog.setCancleVisible(View.GONE);
                    dosingReviewResultDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                                dosingReviewResultDialog.dismiss();
                            }
                        }
                    }, 1000);

                }

                @Override
                public void onFail(String code, String msg) {
                    hideLoadingTip();
                    if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                        dosingReviewResultDialog.dismiss();
                    }
                    dosingReviewResultDialog = new DosingReviewResultDialog(getActivity());
                    dosingReviewResultDialog.setDRresult(msg);
                    dosingReviewResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                    dosingReviewResultDialog.setSureVisible(View.VISIBLE);
                    dosingReviewResultDialog.setCancleVisible(View.GONE);
                    dosingReviewResultDialog.setSureOnclickListener(new DosingReviewResultDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            dosingReviewResultDialog.dismiss();
                        }
                    });
                    dosingReviewResultDialog.show();
                }
            });

        }

            if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {

                showLoadingTip(BaseActivity.LoadingType.FULL);
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                orderId = bundle.getString("data");
                DosingReviewApiManager.preparedVerifyOrd(orderId, "1", new DosingReviewApiManager.PrepareVeriftyCallback() {
                    @Override
                    public void onSuccess(PreparedVerifyOrdBean preparedVerifyOrdBean) {
                        hideLoadingTip();

                        String bedCode = "";
                        String name = "";
                        String type = "";
                        Gson gson = new Gson();

                        //  pv----  返回值内容
                        //  dr----  新建加入current列表

                        PreparedVerifyOrdBean.OrdersBean pvordersBean = preparedVerifyOrdBean.getOrders();
                        bedCode = pvordersBean.getBedCode();
                        name = pvordersBean.getName();

                        List<PreparedVerifyOrdBean.OrdersBean.PatOrdsBean> pvpatOrdsBeanList = pvordersBean.getPatOrds();
                        List<DosingReViewBean.OrdersBean.PatOrdsBean> drpatOrdsBeanList = new ArrayList<>();

                        for (int i = 0; i < pvpatOrdsBeanList.size(); i++) {
                            PreparedVerifyOrdBean.OrdersBean.PatOrdsBean pvpatOrdsBean = pvpatOrdsBeanList.get(i);

                            PreparedVerifyOrdBean.OrdersBean.PatOrdsBean.OrderInfoBean pvorderInfoBean = pvpatOrdsBean.getOrderInfo();
                            type = pvpatOrdsBean.getType();

                            DosingReViewBean.OrdersBean.PatOrdsBean drpatOrdsBean = new DosingReViewBean.OrdersBean.PatOrdsBean();
                            drpatOrdsBean.setCancelGone(true);
                            drpatOrdsBean.setType(type);
                            drpatOrdsBean.setOrderInfo(gson.fromJson(gson.toJson(pvorderInfoBean), DosingReViewBean.OrdersBean.PatOrdsBean.OrderInfoBean.class));
                            drpatOrdsBeanList.add(drpatOrdsBean);
                        }

                        List<List<DosingReViewBean.OrdersBean.PatOrdsBean>> drpatOrds = new ArrayList<>();

                        drpatOrds.add(drpatOrdsBeanList);

                        DosingReViewBean.OrdersBean drordersBean = new DosingReViewBean.OrdersBean();

                        drordersBean.setName(name);
                        drordersBean.setBedCode(bedCode);
                        drordersBean.setPatOrds(drpatOrds);

                        currentOrders.add(drordersBean);

                        setTopFilterSelect(tvDosingreviewCurrent);
                        patientAdapter.setNewData(currentOrders);
                        if (currentOrders.size() < 1) {
                            llEmpty.setVisibility(View.VISIBLE);
                        } else {
                            llEmpty.setVisibility(View.GONE);
                        }

                        if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                            dosingReviewResultDialog.dismiss();
                        }
                        dosingReviewResultDialog = new DosingReviewResultDialog(getActivity());
                        dosingReviewResultDialog.setDRresult(preparedVerifyOrdBean.getMsg());
                        dosingReviewResultDialog.setImgId(R.drawable.icon_popup_sucess);
                        dosingReviewResultDialog.setSureVisible(View.GONE);
                        dosingReviewResultDialog.setCancleVisible(View.GONE);
                        dosingReviewResultDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                                    dosingReviewResultDialog.dismiss();
                                }
                            }
                        }, 1000);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        hideLoadingTip();
                        if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
                            dosingReviewResultDialog.dismiss();
                        }
                        dosingReviewResultDialog = new DosingReviewResultDialog(getActivity());
                        dosingReviewResultDialog.setDRresult(msg);
                        dosingReviewResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                        dosingReviewResultDialog.setSureVisible(View.VISIBLE);
                        dosingReviewResultDialog.setCancleVisible(View.GONE);
                        dosingReviewResultDialog.setSureOnclickListener(new DosingReviewResultDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                dosingReviewResultDialog.dismiss();
                            }
                        });
                        dosingReviewResultDialog.show();
                    }
                });
        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dosing_review, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (dosingReviewResultDialog != null && dosingReviewResultDialog.isShowing()) {
            dosingReviewResultDialog.dismiss();
        }
    }
}
