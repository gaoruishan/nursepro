package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter.DrugHandoverHisOrderAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.GetOrdRecListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * DrugHandoverHisDetailFragment
 * 药品交接历史详情页面
 *
 * @author Devlix126
 * created at 2019/6/12 10:40
 */
public class DrugHandoverHisDetailFragment extends BaseFragment {
    private TextView tvDrughandoverhisdetailReceivedatetime;
    private TextView tvDrughandoverhisdetailReceiveuser;
    private TextView tvDrughandoverhisdetailCarryuser;
    private RecyclerView recyDrughandoverhisdetailOrders;


    private String receiveUser;
    private String receiveDateTime;
    private String carryUser;
    private List<GetOrdRecListBean.RecListBean.RecSubListBean> recSubListBeanList = new ArrayList<>();
    private DrugHandoverHisOrderAdapter drugHandoverHisOrderAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_drughandoverhisdetail));
        setToolbarBottomLineVisibility(true);
        initData();
        initView(view);
        initAdapter();

    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            receiveUser = bundle.getString("receiveUser");
            receiveDateTime = bundle.getString("receiveDateTime");
            carryUser = bundle.getString("carryUser");
            recSubListBeanList = (List<GetOrdRecListBean.RecListBean.RecSubListBean>) bundle.getSerializable("data");
        }
    }

    private void initView(View view) {
        tvDrughandoverhisdetailReceivedatetime = view.findViewById(R.id.tv_drughandoverhisdetail_receivedatetime);
        tvDrughandoverhisdetailReceiveuser = view.findViewById(R.id.tv_drughandoverhisdetail_receiveuser);
        tvDrughandoverhisdetailCarryuser = view.findViewById(R.id.tv_drughandoverhisdetail_carryuser);
        recyDrughandoverhisdetailOrders = view.findViewById(R.id.recy_drughandoverhisdetail_orders);

        recyDrughandoverhisdetailOrders.setHasFixedSize(true);
        recyDrughandoverhisdetailOrders.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvDrughandoverhisdetailReceivedatetime.setText(receiveDateTime);
        tvDrughandoverhisdetailReceiveuser.setText(receiveUser);
        tvDrughandoverhisdetailCarryuser.setText(carryUser);

    }

    private void initAdapter() {
        drugHandoverHisOrderAdapter = new DrugHandoverHisOrderAdapter(recSubListBeanList);
        recyDrughandoverhisdetailOrders.setAdapter(drugHandoverHisOrderAdapter);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drug_handover_his_detail, container, false);
    }

}
