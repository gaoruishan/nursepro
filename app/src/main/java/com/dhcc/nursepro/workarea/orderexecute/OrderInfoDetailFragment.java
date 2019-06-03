package com.dhcc.nursepro.workarea.orderexecute;


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
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderInfoDetailAdapter;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;

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


    private OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean;
    private List<OrderExecuteBean.DetailColumsBean> detailColums;
    private Map<String, String> infomap;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_info_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        detailColums = (List<OrderExecuteBean.DetailColumsBean>) bundle.getSerializable("detailcolums");
        orderInfoBean = (OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean) bundle.getSerializable("patorderinfo");

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
            orderInfoDetailAdapter = new OrderInfoDetailAdapter(detailColums, objectToMap(orderInfoBean));
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
