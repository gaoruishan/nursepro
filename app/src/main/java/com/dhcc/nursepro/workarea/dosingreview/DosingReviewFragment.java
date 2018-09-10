package com.dhcc.nursepro.workarea.dosingreview;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.dosingreview.adapter.DosingReviewPatientAdapter;
import com.dhcc.nursepro.workarea.dosingreview.api.DosingReviewApiManager;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecResultDialog;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    private IntentFilter filter;
    private Receiver mReceiver = new Receiver();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_dosingreview), 0xffffffff, 17);

        filter = new IntentFilter();
        filter.addAction(Action.DOSING_REVIEW);
        filter.addAction("com.scanner.broadcast");
        getActivity().registerReceiver(mReceiver, filter);

        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);

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
                    } else {
                        patientAdapter.loadMoreComplete();
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
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
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
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        activity.unregisterReceiver(mReceiver);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dosing_review, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dosingreview_current:
                setTopFilterSelect(tvDosingreviewCurrent);
                patientAdapter.setNewData(currentOrders);
                patientAdapter.loadMoreEnd();
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
                chooseTime();

                break;
            case R.id.tv_dosingreview_enddate:
                etChangeFlag = "END";
                chooseTime();

                break;
            default:
                break;
        }
    }

    private void chooseTime() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
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

    private void getScanInfo(String scanInfo){
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        if (!episodeId.equals("")){
            properties.put("episodeId",episodeId);
        }
        properties.put("barcode",scanInfo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId","");
//        OrderExecuteApiManager.GetScanMsg1(properties, new OrderExecuteApiManager.GetScanCallBack1() {
//            @Override
//            public void onSuccess(ScanResultBean scanPatBean) {
//                if (scanPatBean.getFlag().equals("PAT")){
//                    episodeId = scanPatBean.getPatInfo().getEpisodeID();
//                    regNo = scanPatBean.getPatInfo().getRegNo();
//                    rlOrderexecuteScan.setVisibility(View.GONE);
//                    //                    tvPat.setText(scanPatBean.getPatInfo().getBedCode()+"床   "+scanPatBean.getPatInfo().getName());
//                    tvOrderexecutePatinfo.setText("".equals(scanPatBean.getPatInfo().getBedCode()) ? "未分"+ "床  " + scanPatBean.getPatInfo().getName() : scanPatBean.getPatInfo().getBedCode() + "床  " + scanPatBean.getPatInfo().getName());
//                    getView().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            asyncInitData();
//                        }
//                    }, 300);
//
//                }else {
//                    if (execResultDialog != null && execResultDialog.isShowing()) {
//                        execResultDialog.dismiss();
//                    }
//                    execResultDialog = new OrderExecResultDialog(getActivity());
//                    execResultDialog.setExecresult("扫码执行成功");
//                    execResultDialog.setImgId(R.drawable.icon_popup_sucess);
//                    execResultDialog.setSureVisible(View.GONE);
//                    execResultDialog.show();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            execResultDialog.dismiss();
//                            asyncInitData();
//                        }
//                    }, 1000);
//
//                }
//            }
//
//            @Override
//            public void onFail(String code, String msg) {
//
//                //                if (episodeId == ""){
//                //                    msg = "请先扫描病人腕带";
//                //                }
//                if (execResultDialog != null && execResultDialog.isShowing()) {
//                    execResultDialog.dismiss();
//                }
//                execResultDialog = new OrderExecResultDialog(getActivity());
//                execResultDialog.setExecresult(msg);
//                execResultDialog.setImgId(R.drawable.icon_popup_error_patient);
//                execResultDialog.setSureVisible(View.VISIBLE);
//                execResultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
//                    @Override
//                    public void onSureClick() {
//                        execResultDialog.dismiss();
//                    }
//                });
//                execResultDialog.show();
//            }
//        });

    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.DOSING_REVIEW:
                    String orderId = intent.getStringExtra("orderId");
                    Toast.makeText(context, orderId, Toast.LENGTH_SHORT).show();
                    break;
                case "com.scanner.broadcast":
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    getScanInfo(bundle.getString("data"));
                    //                    if (execResultDialog != null && execResultDialog.isShowing()) {
                    //                        execResultDialog.dismiss();
                    //                    }
                    break;
                default:
                    break;
            }
        }
    }
}
