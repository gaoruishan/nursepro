package com.dhcc.nursepro.workarea.ordersearch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.bedselect.BedSelectFragment;
import com.dhcc.nursepro.workarea.ordersearch.adapter.OrderSearchOrderTypeAdapter;
import com.dhcc.nursepro.workarea.ordersearch.adapter.OrderSearchPatientAdapter;
import com.dhcc.nursepro.workarea.ordersearch.api.OrderSearchApiManager;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 医嘱查询
 *
 * @author DevLix126
 * created at 2018/8/23 09:00
 */
public class OrderSearchFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener, BaseQuickAdapter.RequestLoadMoreListener {
    private RecyclerView recyOrdersearchOrdertype;
    private TextView tvOrdersearchStartdatetime;
    private TextView tvOrdersearchEnddatetime;
    private RecyclerView recyOrdersearchPatorder;
    private String etChangeFlag;

    //    private List<OrderSearchBean.ButtonsBean> buttons;
    private List<OrderSearchBean.OrdersBean> orders;
    private List<OrderSearchBean.SheetListBean> sheetList;
    private List<OrderSearchBean.DetailColumsBean> detailColums;
    private OrderSearchOrderTypeAdapter orderTypeAdapter;
    private OrderSearchPatientAdapter patientAdapter;

    private SPUtils spUtils = SPUtils.getInstance();
    private String bedStr = "";
    private String regNo = "";
    private String sheetCode = "";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String pageNo = "1";

    private int askCount = 0;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_ordersearch), 0xffffffff, 17);
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        ImageView imageView = viewright.findViewById(R.id.img_toolbar_right);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(BedSelectFragment.class,1);
            }
        });
        setToolbarRightCustomView(viewright);

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            pageNo = "1";
            if (askCount == 0) {
                askCount++;
                asyncInitData();
            }
        }
    }

    private void initView(View view) {
        recyOrdersearchOrdertype = view.findViewById(R.id.recy_ordersearch_ordertype);
        tvOrdersearchStartdatetime = view.findViewById(R.id.tv_ordersearch_startdatetime);
        tvOrdersearchEnddatetime = view.findViewById(R.id.tv_ordersearch_enddatetime);
        recyOrdersearchPatorder = view.findViewById(R.id.recy_ordersearch_patorder);

        tvOrdersearchStartdatetime.setText(startDate + " " + startTime);
        tvOrdersearchEnddatetime.setText(endDate + " " + endTime);

        tvOrdersearchStartdatetime.setOnClickListener(this);
        tvOrdersearchEnddatetime.setOnClickListener(this);
        //提高展示效率
        recyOrdersearchOrdertype.setHasFixedSize(true);
        //设置的布局管理
        recyOrdersearchOrdertype.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recyOrdersearchPatorder.setHasFixedSize(true);
        //设置的布局管理
        recyOrdersearchPatorder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        orderTypeAdapter = new OrderSearchOrderTypeAdapter(new ArrayList<OrderSearchBean.SheetListBean>());
        orderTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sheetCode = ((OrderSearchBean.SheetListBean) adapter.getItem(position)).getCode();
                pageNo = "1";
                asyncInitData();

                //左侧刷新分类选中状态显示
                orderTypeAdapter.setSelectedPostion(position);
                orderTypeAdapter.notifyDataSetChanged();

            }
        });
        recyOrdersearchOrdertype.setAdapter(orderTypeAdapter);

        patientAdapter = new OrderSearchPatientAdapter(new ArrayList<OrderSearchBean.OrdersBean>());
        patientAdapter.setOnLoadMoreListener(this, recyOrdersearchPatorder);

        recyOrdersearchPatorder.setAdapter(patientAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderSearchApiManager.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, new OrderSearchApiManager.GetOrderCallback() {
            @Override
            public void onSuccess(OrderSearchBean orderSearchBean) {
                hideLoadingTip();
                sheetList = orderSearchBean.getSheetList();
                detailColums = orderSearchBean.getDetailColums();
                orders = orderSearchBean.getOrders();
                if ("1".equals(pageNo)) {
                    orderTypeAdapter.setNewData(sheetList);
                    patientAdapter.setDetailColums(detailColums);
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

                askCount = 0;

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                patientAdapter.loadMoreFail();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });

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

            tvOrdersearchStartdatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        } else {
            if (!date.equals(endDate) || !time.equals(endTime)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                endTime = time;
                pageNo = "1";
                asyncInitData();
            }

            tvOrdersearchEnddatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        }

    }

    @Override
    public void onLoadMoreRequested() {
        pageNo = Integer.valueOf(pageNo) + 1 + "";
        asyncInitData();
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
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ordersearch_startdatetime:
                etChangeFlag = "START";
                chooseTime();

                break;
            case R.id.tv_ordersearch_enddatetime:
                etChangeFlag = "END";
                chooseTime();
                break;
            default:
                break;
        }
    }


}