package com.dhcc.nursepro.workarea.ordersearch;


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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.ordersearch.adapter.OrderSearchOrderTypeAdapter;
import com.dhcc.nursepro.workarea.ordersearch.api.OrderSearchApiManager;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 医嘱查询
 *
 * @author DevLix126
 * created at 2018/8/23 09:00
 */
public class OrderSearchFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recyOrdersearchOrdertype;
    private TextView tvOrdersearchStartdatetime;
    private TextView tvOrdersearchEnddatetime;
    private RecyclerView recyOrdersearchPatorder;

    //    private List<OrderSearchBean.ButtonsBean> buttons;
    private List<OrderSearchBean.OrdersBean> orders;
    private List<OrderSearchBean.SheetListBean> sheetList;

    private OrderSearchOrderTypeAdapter orderTypeAdapter;

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
                Toast.makeText(getContext(), "床位图", Toast.LENGTH_SHORT).show();
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


    private void initView(View view) {
        recyOrdersearchOrdertype = view.findViewById(R.id.recy_ordersearch_ordertype);
        tvOrdersearchStartdatetime = view.findViewById(R.id.tv_ordersearch_startdatetime);
        tvOrdersearchEnddatetime = view.findViewById(R.id.tv_ordersearch_enddatetime);
        recyOrdersearchPatorder = view.findViewById(R.id.recy_ordersearch_patorder);

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
                asyncInitData();


                //左侧刷新分类选中状态显示
                orderTypeAdapter.setSelectedPostion(position);
                orderTypeAdapter.notifyDataSetChanged();

            }
        });
        recyOrdersearchOrdertype.setAdapter(orderTypeAdapter);



    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderSearchApiManager.getOrder(bedStr, regNo, sheetCode, pageNo, startDate, startTime, endDate, endTime, new OrderSearchApiManager.GetOrderCallback() {
            @Override
            public void onSuccess(OrderSearchBean orderSearchBean) {
                hideLoadingTip();
                sheetList = orderSearchBean.getSheetList();
                orders = orderSearchBean.getOrders();
                if ("1".equals(pageNo)) {
                    orderTypeAdapter.setNewData(sheetList);

                } else {

                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();

            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ordersearch_startdatetime:
                break;
            case R.id.tv_ordersearch_enddatetime:
                break;
            default:
                break;
        }
    }
}
