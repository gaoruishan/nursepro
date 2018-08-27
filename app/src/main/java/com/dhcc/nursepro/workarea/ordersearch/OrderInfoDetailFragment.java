package com.dhcc.nursepro.workarea.ordersearch;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

/**
 * OrderInfoDetailFragment
 * 医嘱详情
 *
 * @author DevLix126
 * created at 2018/8/27 16:55
 */
public class OrderInfoDetailFragment extends BaseFragment {


    private OrderSearchBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_info_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        orderInfoBean = (OrderSearchBean.OrdersBean.PatOrdsBean.OrderInfoBean) bundle.getSerializable("patorderinfo");

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_orderinfodetail));
        setToolbarBottomLineVisibility(false);

        initView(view);


    }

    private void initView(View view) {

    }

}
