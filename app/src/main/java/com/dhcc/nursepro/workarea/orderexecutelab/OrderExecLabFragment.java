package com.dhcc.nursepro.workarea.orderexecutelab;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedselect.BedSelectFragment;
import com.dhcc.nursepro.workarea.orderexecute.presenter.OrdExeFilterPresenter;
import com.dhcc.nursepro.workarea.orderexecutelab.adapter.OrderSearchPatientAdapter;
import com.dhcc.nursepro.workarea.orderexecutelab.api.OrderExecLabApiManager;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.OrderSearchBean;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.ScanResultBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderExecLabFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener, BaseQuickAdapter.RequestLoadMoreListener {

    private TextView tvOrderexeclabStartdatetime;
    private TextView tvOrderexeclabEnddatetime;
    private RecyclerView recyOrderexeclabPatorder;
    private String etChangeFlag;

    private List<OrderSearchBean.OrdersBean> orders;
    private List<OrderSearchBean.DetailColumsBean> detailColums;
    private OrderSearchPatientAdapter patientAdapter;

    private SPUtils spUtils = SPUtils.getInstance();
    private String bedStr = "";
    private String regNo = "";
    private String sheetCode = "JYDYZX";
    private String sheetCodePresenter = "sheetCodePresenter";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String pageNo = "1";


    private OrdExeFilterPresenter presenter;
    private String screenParts;

    private String episodeId = "";
    private String scanInfo = "";
    private List<String> scanList = new ArrayList<>();
    private String patInfo = "";
    private String orderInfo = "";
    private String orderInfoEx = "";
    private String oeoreId = "";

    private OrdersAddExecDialog ordAddDialog;
    private OrderExecResultDialog resultDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_orderexeclab), 0xffffffff, 17);
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        ImageView imageView = viewright.findViewById(R.id.img_toolbar_right);
        ImageView imgToolbarRightFilter = viewright.findViewById(R.id.img_toolbar_right_filter);
        imgToolbarRightFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPopWindow();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(BedSelectFragment.class, 1);
            }
        });
        setToolbarRightCustomView(viewright);

        Bundle bundle = getArguments();
        if (bundle != null) {
            regNo = bundle.getString("regNo");
        }

        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        startTime = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(11, 16);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
        endTime = spUtils.getString(SharedPreference.SCHENDATETIME).substring(11, 16);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);

        //添加筛选
        presenter = new OrdExeFilterPresenter(this.getActivity());
        presenter.setOnSelectCallBackListener(new OrdExeFilterPresenter.OnSelectCallBackListener() {

            @Override
            public void onSelect(String s) {
                screenParts = s;
                pageNo = "1";
                asyncInitData();
            }
        });
        presenter.setOrdTypeSelectedCode(sheetCode);
    }

    private void initView(View view) {

        tvOrderexeclabStartdatetime = view.findViewById(R.id.tv_orderexeclab_startdatetime);
        tvOrderexeclabEnddatetime = view.findViewById(R.id.tv_orderexeclab_enddatetime);
        recyOrderexeclabPatorder = view.findViewById(R.id.recy_orderexeclab_patorder);

        tvOrderexeclabStartdatetime.setText(startDate + " " + startTime);
        tvOrderexeclabEnddatetime.setText(endDate + " " + endTime);

        tvOrderexeclabStartdatetime.setOnClickListener(this);
        tvOrderexeclabEnddatetime.setOnClickListener(this);

        //提高展示效率
        recyOrderexeclabPatorder.setHasFixedSize(true);
        //设置的布局管理
        recyOrderexeclabPatorder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {

        patientAdapter = new OrderSearchPatientAdapter(new ArrayList<>());
        patientAdapter.setOnLoadMoreListener(this, recyOrderexeclabPatorder);

        recyOrderexeclabPatorder.setAdapter(patientAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecLabApiManager.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, screenParts, new OrderExecLabApiManager.GetOrderCallback() {
            @Override
            public void onSuccess(OrderSearchBean orderSearchBean) {
                hideLoadingTip();
                detailColums = orderSearchBean.getDetailColums();
                orders = orderSearchBean.getOrders();
                if ("1".equals(pageNo)) {

                    patientAdapter.setDetailColums(detailColums);
                    patientAdapter.setNewData(orders);
                    recyOrderexeclabPatorder.scrollToPosition(0);
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
                patientAdapter.loadMoreFail();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            scanInfo = bundle.getString("data");
            if (resultDialog != null && resultDialog.isShowing()) {
                resultDialog.dismiss();
            }
            getScanInfo();
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_exec_lab, container, false);
    }

    private void getScanInfo() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecLabApiManager.getScanMsg(episodeId, scanInfo, new OrderExecLabApiManager.GetScanCallBack() {
            @Override
            public void onSuccess(ScanResultBean scanResultBean) {
                hideLoadingTip();
                if ("1".equals(scanResultBean.getDiagFlag())) {
                    if (ordAddDialog == null) {
                        ordAddDialog = new OrdersAddExecDialog(getActivity());
                        ordAddDialog.setCancelOnclickListener(new OrdersAddExecDialog.onCancelOnclickListener() {
                            @Override
                            public void onCancelClick() {
                                ordAddDialog.dismiss();
                            }
                        });
                        ordAddDialog.setSureOnclickListener(new OrdersAddExecDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                String ordAdd = "";
                                for (int i = 0; i < ordAddDialog.getAddList().size(); i++) {
                                    ordAdd = ordAdd + ordAddDialog.getAddList().get(i).getID() + "^";
                                }
                                if (ordAdd.contains("^")) {
                                    oeoreId = ordAdd.substring(0, ordAdd.length() - 1);
                                }
                                execOrSeeOrder();
                                ordAddDialog.dismiss();
                                pageNo = "1";
                                asyncInitData();
                            }
                        });
                        ordAddDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                ordAddDialog = null;
                                scanList.clear();
                            }
                        });
                    }
                    if ("PAT".equals(scanResultBean.getFlag())) {
                        ScanResultBean.PatInfoBean patInfoBean = scanResultBean.getPatInfo();
                        episodeId = patInfoBean.getEpisodeID();
                        regNo = patInfoBean.getRegNo();
                        patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();

                        ordAddDialog.setPatInfo(patInfo);
                        if (!ordAddDialog.isShowing()) {
                            ordAddDialog.show();
                        }
                    } else {
                        if ("LAB".equals(scanResultBean.getBarCodeType())) {
                            if (resultDialog != null && resultDialog.isShowing()) {
                                resultDialog.dismiss();
                            }
                            if ("exed".equals(scanResultBean.getBtnType()) || "0".equals(scanResultBean.getCanExeFlag())) {
                                resultDialog = new OrderExecResultDialog(getActivity());
                                resultDialog.setExecresult(scanResultBean.getMsg());
                                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                                resultDialog.setSureVisible(View.VISIBLE);
                                resultDialog.setCancleVisible(View.GONE);
                                resultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                                    @Override
                                    public void onSureClick() {
                                        resultDialog.dismiss();
                                    }
                                });
                                resultDialog.show();
                            } else {

                                if (scanList.size() > 0) {
                                    if (scanList.contains(scanInfo)) {
                                        showToast("该条码已扫描");
                                    } else {
                                        scanList.add(scanInfo);
                                        ordAddDialog.setAddList(scanResultBean.getOrders());
                                    }

                                } else {
                                    scanList = new ArrayList<>();
                                    scanList.add(scanInfo);
                                    ordAddDialog.setAddList(scanResultBean.getOrders());
                                }
                                if (!ordAddDialog.isShowing()) {
                                    ordAddDialog.show();
                                }
                            }
                        } else if (ordAddDialog != null && ordAddDialog.isShowing()) {
                            showToast("请执行或取消已扫描医嘱再执行其他医嘱");
                        } else {
                            showToast("仅可操作性检验医嘱批量执行");
                        }
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

    private void execOrSeeOrder() {
        OrderExecLabApiManager.execOrSeeOrder("", "", "", "", "", oeoreId, "F", "", "", new OrderExecLabApiManager.ExecOrSeeOrderCallback() {
            @Override
            public void onSuccess(OrderExecResultBean orderExecResultBean) {

                /**
                 * 操作
                 * execStatusCode (F 执行，C 撤销执行，A 接受，S 完成，R 拒绝)
                 * <p>
                 * F 执行
                 * Y 皮试阳性
                 * N 皮试阴性
                 * C 撤销执行
                 * A 接受
                 * R 拒绝
                 * S 完成
                 * ""撤销处理
                 */
                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }

                resultDialog = new OrderExecResultDialog(getActivity());
                resultDialog.setImgId(R.drawable.icon_popup_sucess);
                resultDialog.setSureVisible(View.GONE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultDialog.dismiss();
                        asyncInitData();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {

                if (resultDialog != null && resultDialog.isShowing()) {
                    resultDialog.dismiss();
                }
                resultDialog = new OrderExecResultDialog(getActivity());
                resultDialog.setExecresult(msg);
                resultDialog.setImgId(R.drawable.icon_popup_error_patient);
                resultDialog.setSureVisible(View.VISIBLE);
                resultDialog.setCancleVisible(View.GONE);
                resultDialog.setSureOnclickListener(new OrderExecResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        resultDialog.dismiss();
                    }
                });
                resultDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            pageNo = "1";
            asyncInitData();
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);
        String time = TimeUtils.millis2String(millseconds).substring(11, 16);

        if ("START".equals(etChangeFlag)) {
            if (!date.equals(startDate) || !time.equals(startTime)) {
                //日期时间发生改变，需重新请求数据
                startDate = date;
                startTime = time;
                pageNo = "1";
                asyncInitData();
            }

            tvOrderexeclabStartdatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        } else {
            if (!date.equals(endDate) || !time.equals(endTime)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                endTime = time;
                pageNo = "1";
                asyncInitData();
            }

            tvOrderexeclabEnddatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        }

    }

    @Override
    public void onLoadMoreRequested() {
        pageNo = Integer.valueOf(pageNo) + 1 + "";
        asyncInitData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_orderexeclab_startdatetime:
                etChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvOrderexeclabStartdatetime.getText().toString() + ":00"));

                break;
            case R.id.tv_orderexeclab_enddatetime:
                etChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvOrderexeclabEnddatetime.getText().toString() + ":00"));
                break;
            default:
                break;
        }
    }

    private void chooseTime(long currentTimeMillis) {
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
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }
}
