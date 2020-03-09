package com.dhcc.module.health.workarea.orderlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderexecute.OrderExecuteApiManager;
import com.dhcc.module.health.workarea.orderlist.adapter.DocPatListAdapter;
import com.dhcc.module.health.workarea.orderlist.adapter.DocPatOrdersAdapter;
import com.dhcc.module.health.workarea.orderlist.api.OrderListManager;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatListBean;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatOrdersBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者列表
 * @author:gaoruishan
 * @date:202019-10-23/17:00
 * @email:grs0515@163.com
 */
public class DocPatientListFragment extends BaseCommFragment{

    private List<DocPatListBean.PatInfoListBean> patsList = new ArrayList<>();
    private List<DocPatOrdersBean.CureInfoListBean> ordersList = new ArrayList<>();
    private RecyclerView recPatList,recOrderList;
    private TextView tvOrderNum,tvSure;
    DocPatListAdapter patListAdapter = new DocPatListAdapter(new ArrayList<DocPatListBean.PatInfoListBean>());
    DocPatOrdersAdapter patOrdersAdapter = new DocPatOrdersAdapter(new ArrayList<DocPatOrdersBean.CureInfoListBean>());
    @Override
    public void setCommToolBar() {
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("医嘱单", 0xffffffff, 17);
    }

    @Override
    protected void initDatas() {
        initData();
        initAdapter();
    }

    @Override
    protected void initViews() {
        f(R.id.ll_sure, LinearLayout.class).setVisibility(View.GONE);
        recPatList = f(R.id.rec_pat_list,RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recPatList, 0);
        recPatList.setAdapter(patListAdapter);

        recOrderList = f(R.id.rec_order_list,RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recOrderList, 0);
        recOrderList.setAdapter(patOrdersAdapter);

        tvOrderNum = f(R.id.tv_sel_num,TextView.class);
        tvSure = f(R.id.tv_execu_sure,TextView.class);
        tvSure.setOnClickListener(this);

    }

    private void initData(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderListManager.getPatList(new CommonCallBack<DocPatListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(DocPatListBean bean, String type) {
                hideLoadingTip();
                patsList = bean.getPatInfoList();
                if (patsList.size()>0){
                    patsList.get(0).setSelect("1");
                    patListAdapter.setNewData(patsList);
                }
                getOrders();
            }
        });
    }

    private void getOrders(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        String appStr = null;
        for (int i = 0; i <patsList.size() ; i++) {
            if (patsList.get(i).getSelect().equals("1")){
                if (appStr==null){
                    appStr = patsList.get(i).getAppstr();
                }else {
                    appStr = appStr+"^"+patsList.get(i).getAppstr();
                }
            }
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderListManager.getPatOrdersList(appStr,new CommonCallBack<DocPatOrdersBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(DocPatOrdersBean bean, String type) {
                hideLoadingTip();
                ordersList = new ArrayList<>();
                ordersList = bean.getCureInfoList();
                patOrdersAdapter.setNewData(ordersList);
                tvOrderNum.setText("已选择0条医嘱");
            }
        });
    }

    private void initAdapter(){
        patListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i <patsList.size() ; i++) {
                    patsList.get(i).setSelect("0");
                }
                patsList.get(position).setSelect("1");
                patListAdapter.setNewData(patsList);
                getOrders();
            }
        });

        patOrdersAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ordersList.get(position).getSelect().equals("1")){
                    ordersList.get(position).setSelect("0");
                }else {
                    ordersList.get(position).setSelect("1");
                }
                patOrdersAdapter.setNewData(ordersList);
                int num = 0;
                for (int i = 0; i <ordersList.size() ; i++) {
                    if (ordersList.get(i).getSelect().equals("1")){
                        num++;
                    }
                }
                tvOrderNum.setText("已选择"+num+"条医嘱");
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_execu_sure) {
            String appStrs = null;
            int patNum = 0;
            for (int i = 0; i <patsList.size() ; i++) {
                if (patsList.get(i).getSelect().equals("1")){
                    patNum++;
                }
            }
            if (patNum<1){
               showToast("未选择患者，无法执行医嘱");
            }else {
                for (int i = 0; i <ordersList.size() ; i++) {
                    if (ordersList.get(i).getSelect().equals("1")){
                        if (appStrs == null){
                            appStrs = ordersList.get(i).getDCAOEORIDR();
                        }else {
                            appStrs = appStrs+"^"+ordersList.get(i).getDCAOEORIDR();
                        }
                    }
                }
            }
            if (appStrs ==null){
                showToast("未选择医嘱，无法执行医嘱");
            }else {
//                showToast(appStrs);
                showLoadingTip(BaseActivity.LoadingType.FULL);
                OrderExecuteApiManager.getHealthExecuResult(appStrs, new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        hideLoadingTip();
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        hideLoadingTip();
                        showToast(bean.getMsg());
                        getOrders();
                    }
                });

            }

        }
    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_patient_list;
    }
}