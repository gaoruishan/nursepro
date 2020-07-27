package com.dhcc.nursepro.workarea.ordersearch;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.ordersearch.adapter.OrderInfoDetailAdapter;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderInfoDetailFragment
 * 医嘱详情
 *
 * @author DevLix126
 * created at 2018/8/27 16:55
 */
public class OrderInfoDetailFragment extends BaseFragment {
    private RecyclerView recyOrderinfodetail;

    private OrderInfoDetailAdapter orderInfoDetailAdapter;


    private OrderSearchBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean;
    private List<OrderSearchBean.DetailColumsBean> detailColums;
    private Map infomap =new HashMap();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_info_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle!=null&&bundle.getSerializable("detailcolums") != null) {
            detailColums = (List<OrderSearchBean.DetailColumsBean>) bundle.getSerializable("detailcolums");
        }
        if (bundle!=null&&bundle.getSerializable("patorderinfo") != null) {
            orderInfoBean = (OrderSearchBean.OrdersBean.PatOrdsBean.OrderInfoBean) bundle.getSerializable("patorderinfo");
        }
        if (bundle!=null&&bundle.getSerializable("ord") != null) {
            infomap = (Map) bundle.getSerializable("ord");
        }
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_orderinfodetail));
        setToolbarBottomLineVisibility(false);

        initView(view);
        initAdapter();

    }

    private void initView(View view) {
        recyOrderinfodetail = view.findViewById(R.id.recy_orderinfodetail);
        //提高展示效率
        recyOrderinfodetail.setHasFixedSize(true);
        //设置的布局管理
        recyOrderinfodetail.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        try {
//            orderInfoDetailAdapter = new OrderInfoDetailAdapter(detailColums, objectToMap(orderInfoBean));
            orderInfoDetailAdapter = new OrderInfoDetailAdapter(detailColums, infomap);
            recyOrderinfodetail.setAdapter(orderInfoDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj) instanceof String ? field.get(obj).toString() : "");
        }

        return map;
    }


}
