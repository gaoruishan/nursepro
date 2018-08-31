package com.dhcc.nursepro.workarea.orderhandle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.dhcc.nursepro.workarea.orderhandle.adapter.OrderHandleOrderTypeAdapter;
import com.dhcc.nursepro.workarea.orderhandle.adapter.OrderHandlePatOrdersAdapter;
import com.dhcc.nursepro.workarea.orderhandle.api.OrdersHandleApiManager;
import com.dhcc.nursepro.workarea.orderhandle.bean.OrderHandleBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class OrderHandleFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private RecyclerView recyOrderhandleOrdertype;
    private TextView tvOrderhandleStartdatetime;
    private TextView tvOrderhandleEnddatetime;
    private RecyclerView recyOrderhandlePatorder,recyclerView;
    private String etChangeFlag;

    //    private List<OrderSearchBean.ButtonsBean> buttons;
    private List<List<OrderHandleBean.OrdersBean.PatOrdsBean>> orders;
    private List<OrderHandleBean.SheetListBean> sheetList;
    private List<OrderHandleBean.DetailColumsBean> detailColums;
    private OrderHandleOrderTypeAdapter orderTypeAdapter;
    private OrderHandlePatOrdersAdapter patOrdersAdapter;

    private SPUtils spUtils = SPUtils.getInstance();
    private String bedStr = "";
    private String regNo = "";
    private String sheetCode = "";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String pageNo = "1";


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_handle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(("医嘱处理"), 0xffffffff, 17);
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        ImageView imageView = viewright.findViewById(R.id.img_toolbar_right);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(BedSelectFragment.class,1);
            }
        });
//        setToolbarRightCustomView(viewright);

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


    private void initView(View view) {
        recyOrderhandleOrdertype = view.findViewById(R.id.recy_orderhandle_ordertype);
        tvOrderhandleStartdatetime = view.findViewById(R.id.tv_orderhandle_startdatetime);
        tvOrderhandleEnddatetime = view.findViewById(R.id.tv_orderhandle_enddatetime);
        recyOrderhandlePatorder = view.findViewById(R.id.recy_orderhandle_patorder);

        tvOrderhandleStartdatetime.setText(startDate + " " + startTime);
        tvOrderhandleEnddatetime.setText(endDate + " " + endTime);

        tvOrderhandleStartdatetime.setOnClickListener(this);
        tvOrderhandleEnddatetime.setOnClickListener(this);
        //提高展示效率
        recyOrderhandleOrdertype.setHasFixedSize(true);
        //设置的布局管理
        recyOrderhandleOrdertype.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recyOrderhandlePatorder.setHasFixedSize(true);
        //设置的布局管理
        recyOrderhandlePatorder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        orderTypeAdapter = new OrderHandleOrderTypeAdapter(new ArrayList<OrderHandleBean.SheetListBean>());
        orderTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sheetCode = ((OrderHandleBean.SheetListBean) adapter.getItem(position)).getCode();
                pageNo = "1";
                asyncInitData();

                //左侧刷新分类选中状态显示
                orderTypeAdapter.setSelectedPostion(position);
                orderTypeAdapter.notifyDataSetChanged();

            }
        });
        recyOrderhandleOrdertype.setAdapter(orderTypeAdapter);

        patOrdersAdapter = new OrderHandlePatOrdersAdapter(new ArrayList<List<OrderHandleBean.OrdersBean.PatOrdsBean>>());
//        patientAdapter.setOnLoadMoreListener(this, recyOrderhandlePatorder);
        patOrdersAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CheckBox checkBox = view.findViewById(R.id.cb_orderhandle);
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                }else {
                    checkBox.setChecked(true);
                }
            }
        });



        recyOrderhandlePatorder.setAdapter(patOrdersAdapter);

    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo","0000000129");
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("bedStr", bedStr);
//        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);
        properties.put("pageNo", pageNo);

        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);

        OrdersHandleApiManager.GetOrdersMsg(properties,new OrdersHandleApiManager.GetOrdersMsgCallBack() {
            @Override
            public void onSuccess(OrderHandleBean orderHandleBean) {
                hideLoadingTip();
                sheetList = orderHandleBean.getSheetList();
                detailColums = orderHandleBean.getDetailColums();
                if ((orderHandleBean.getOrders().size()>0)){
                    orders = orderHandleBean.getOrders().get(0).getPatOrds();
                }else {
                    orders = null;
                }
                patOrdersAdapter.setNewData(orders);
                orderTypeAdapter.setNewData(sheetList);
//                if ("1".equals(pageNo)) {
//                    orderTypeAdapter.setNewData(sheetList);
////                    patientAdapter.setDetailColums(detailColums);
//                    patientAdapter.setNewData(orders);
//                    if (orders.size() == 0) {
//                        patientAdapter.loadMoreEnd();
//                    } else {
//                        patientAdapter.loadMoreComplete();
//                    }
//                } else {
//                    if (orders.size() == 0) {
//                        patientAdapter.loadMoreEnd();
//                    } else {
//                        patientAdapter.addData(orders);
//                        patientAdapter.loadMoreComplete();
//                    }
//                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                patOrdersAdapter.loadMoreFail();
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

            tvOrderhandleStartdatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        } else {
            if (!date.equals(endDate) || !time.equals(endTime)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                endTime = time;
                pageNo = "1";
                asyncInitData();
            }

            tvOrderhandleEnddatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
        }

    }

//    @Override
//    public void onLoadMoreRequested() {
//        pageNo = Integer.valueOf(pageNo) + 1 + "";
//        asyncInitData();
//    }

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
            case R.id.tv_orderhandle_startdatetime:
                etChangeFlag = "START";
                chooseTime();

                break;
            case R.id.tv_orderhandle_enddatetime:
                etChangeFlag = "END";
                chooseTime();
                break;
            default:
                break;
        }
    }


}
