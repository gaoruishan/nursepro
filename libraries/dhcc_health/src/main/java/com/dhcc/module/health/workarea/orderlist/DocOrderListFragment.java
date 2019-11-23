package com.dhcc.module.health.workarea.orderlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderlist.adapter.DocOrderListOrdersAdapter;
import com.dhcc.module.health.workarea.orderlist.api.DocOrdListApiManager;
import com.dhcc.module.health.workarea.orderlist.bean.DocOrderListBean;
import com.dhcc.module.health.workarea.orderlist.bean.DocOrdersPatsListBean;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.CustomTagSelectLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 医嘱单
 * @author:gaoruishan
 * @date:202019-10-23/16:43
 * @email:grs0515@163.com
 */
public class DocOrderListFragment extends BaseCommFragment {

    public static final String ALL = "全部";
    private CustomSheetListView customSheetList;
    private CustomTagSelectLayout ctsPriorityLayout;
    private CustomTagSelectLayout ctsStatusLayout;
    private CustomTagSelectLayout ctsTypeLayout;
    private RecyclerView recyOrderList;
    private DocOrderListOrdersAdapter orderListOrdersAdapter;
    private String descOrdPriority = ALL;
    private String descOrdStatus = ALL;
    private String descOrdType = ALL;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle("医嘱单");
        getInWardPatList();
    }

    @Override
    protected void initViews() {
        customSheetList = f(R.id.custom_sheet_list, CustomSheetListView.class);
        ctsPriorityLayout = f(R.id.cts_priority_layout, CustomTagSelectLayout.class);
        ctsStatusLayout = f(R.id.cts_status_layout, CustomTagSelectLayout.class);
        ctsTypeLayout = f(R.id.cts_type_layout, CustomTagSelectLayout.class);
        recyOrderList = f(R.id.recy_order_list, RecyclerView.class);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, recyOrderList, 0);
        orderListOrdersAdapter = new DocOrderListOrdersAdapter(null);
        recyOrderList.setAdapter(orderListOrdersAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.health_fragment_docorder_list;
    }

    private void getInWardPatList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        DocOrdListApiManager.getInWardPatList(new CommonCallBack<DocOrdersPatsListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(DocOrdersPatsListBean bean, String type) {
                hideLoadingTip();
                initSheetListData(bean);
                getDocOrderList(bean.getPatInfoList().get(0).getEpisodeId());
            }
        });
    }

    private void initSheetListData(final DocOrdersPatsListBean bean) {
        customSheetList.setDatas(bean.getSheetList());
        customSheetList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DocOrdersPatsListBean.PatInfoListBean patInfoListBean = bean.getPatInfoList().get(position);
                String episodeId = patInfoListBean.getEpisodeId();
                getDocOrderList(episodeId);
            }
        });
    }

    private void getDocOrderList(String episodeId) {
        DocOrdListApiManager.getDocOrderList(episodeId, new CommonCallBack<DocOrderListBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(final DocOrderListBean bean, String type) {
                ctsPriorityLayout.setTagTitle("优先级").setDatas(bean.getOrdPriority()).setSelectedCode("")
                        .setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                descOrdPriority = bean.getOrdPriority().get(position).getDesc();
                                filterOrdList(bean.getOrdList());
                            }
                        });
                ctsStatusLayout.setTagTitle("状  态").setDatas(bean.getOrdStatus()).setSelectedCode("")
                        .setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                descOrdStatus = bean.getOrdStatus().get(position).getDesc();
                                filterOrdList(bean.getOrdList());
                            }
                        });
                ctsTypeLayout.setTagTitle("类  型").setDatas(bean.getOrdType()).setSelectedCode("")
                        .setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                descOrdType = bean.getOrdType().get(position).getDesc();
                                filterOrdList(bean.getOrdList());
                            }
                        });
                orderListOrdersAdapter.setNewData(bean.getOrdList());
            }
        });
    }

    private void filterOrdList(List<List<DocOrderListBean.OrdListBean>> ordList) {
        List<List<DocOrderListBean.OrdListBean>> newOrdPriority = new ArrayList<>();
        for (List<DocOrderListBean.OrdListBean> ordListBeans : ordList) {
            if (ordListBeans != null && ordListBeans.size() > 0) {
                if (descOrdPriority.equals(ALL) || descOrdPriority.equals(ordListBeans.get(0).getOrdPriority())) {
                    newOrdPriority.add(ordListBeans);
                }
            }
        }
        List<List<DocOrderListBean.OrdListBean>> newOrdStatus = new ArrayList<>();
        for (List<DocOrderListBean.OrdListBean> ordListBeans : newOrdPriority) {
            if (ordListBeans != null && ordListBeans.size() > 0) {
                if (descOrdStatus.equals(ALL) || descOrdStatus.equals(ordListBeans.get(0).getOrdStatus())) {
                    newOrdStatus.add(ordListBeans);
                }
            }
        }
        List<List<DocOrderListBean.OrdListBean>> newOrdType = new ArrayList<>();
        for (List<DocOrderListBean.OrdListBean> ordListBeans : newOrdStatus) {
            if (ordListBeans != null && ordListBeans.size() > 0) {
                if (descOrdType.equals(ALL) || descOrdType.equals(ordListBeans.get(0).getOrdType())) {
                    newOrdType.add(ordListBeans);
                }
            }
        }
        orderListOrdersAdapter.setNewData(newOrdType);
    }
}
