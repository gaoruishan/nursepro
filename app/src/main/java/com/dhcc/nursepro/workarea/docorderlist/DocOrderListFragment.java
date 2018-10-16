package com.dhcc.nursepro.workarea.docorderlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.docorderlist.adapter.DocOrderListOrdersAdapter;
import com.dhcc.nursepro.workarea.docorderlist.adapter.DocOrderListPatsAdapter;
import com.dhcc.nursepro.workarea.docorderlist.adapter.DocOrderListPriorityAdapter;
import com.dhcc.nursepro.workarea.docorderlist.adapter.DocOrderListStatusAdapter;
import com.dhcc.nursepro.workarea.docorderlist.adapter.DocOrderListTypeAdapter;
import com.dhcc.nursepro.workarea.docorderlist.api.DocOrderListApiManager;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrdersPatsListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.docorderlist
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:9:36
 */
public class DocOrderListFragment extends BaseFragment {
    private DocOrderListPatsAdapter patsAdapter;
    private DocOrderListTypeAdapter typeAdapter;
    private DocOrderListOrdersAdapter ordersAdapter;
    private DocOrderListPriorityAdapter priorityAdapter;
    private DocOrderListStatusAdapter statusAdapter;

    private List<DocOrdersPatsListBean.PatInfoListBean> patsListBeanList;
    private List<DocOrderListBean.OrdPriorityBean> ordPrioritybeanList;
    private List<DocOrderListBean.OrdTypeBean> ordTypeBeanList;
    private List<DocOrderListBean.OrdStatusBean> ordStatusBeanList;
    private List<List<DocOrderListBean.OrdListBean>>  ordListBeanList;

    private RecyclerView recPats;
    private RecyclerView recPriority;
    private RecyclerView recType;
    private RecyclerView recStatus;
    private RecyclerView recOrders;

    private int prioritySel = 0;
    private int statusSel = 0;
    private int typeSel = 0;
    private String episodId = "";

    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_docorder_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle((getString(R.string.title_docorderlist)), 0xffffffff, 17);

        initView(view);
        initAdapter();
        initDataPats();
    }

    private void initView(View view){
        recPats = view.findViewById(R.id.recy_docorder_pat);
        recPriority = view.findViewById(R.id.rec_docorder_priority);
        recStatus = view.findViewById(R.id.rec_docorder_status);
        recType = view.findViewById(R.id.rec_docorder_type);
        recOrders = view.findViewById(R.id.recy_order_list);


        //提高展示效率
        recPats.setHasFixedSize(true);
        //设置的布局管理
        recPats.setLayoutManager(new LinearLayoutManager(getActivity()));

        //提高展示效率
        recPriority.setHasFixedSize(true);
        //设置的布局管理
        recPriority.setLayoutManager(new GridLayoutManager(getActivity(),4));

        //提高展示效率
        recStatus.setHasFixedSize(true);
        //设置的布局管理
        recStatus.setLayoutManager(new GridLayoutManager(getActivity(),4));

        //提高展示效率
        recType.setHasFixedSize(true);
        //设置的布局管理
        recType.setLayoutManager(new GridLayoutManager(getActivity(),4));

        //提高展示效率
        recOrders.setHasFixedSize(true);
        //设置的布局管理
        recOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter(){
        patsAdapter = new DocOrderListPatsAdapter(new ArrayList<DocOrdersPatsListBean.PatInfoListBean>());
        recPats.setAdapter(patsAdapter);
        patsAdapter.setSelectItem(0);
        patsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                patsAdapter.setSelectItem(position);
                patsAdapter.notifyDataSetChanged();
                episodId = patsListBeanList.get(position).getEpisodeId();
                initDataOrders();
            }
        });

        priorityAdapter = new DocOrderListPriorityAdapter(new ArrayList<DocOrderListBean.OrdPriorityBean>());
        priorityAdapter.setSelectItem(0);
        recPriority.setAdapter(priorityAdapter);
        priorityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                priorityAdapter.setSelectItem(position);
                priorityAdapter.notifyDataSetChanged();
                prioritySel = position;
                filterData();
            }
        });

        statusAdapter = new DocOrderListStatusAdapter(new ArrayList<DocOrderListBean.OrdStatusBean>());
        statusAdapter.setSelectItem(0);
        recStatus.setAdapter(statusAdapter);
        statusAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                statusAdapter.setSelectItem(position);
                statusAdapter.notifyDataSetChanged();
                statusSel = position;
                filterData();
            }
        });

        typeAdapter = new DocOrderListTypeAdapter(new ArrayList<DocOrderListBean.OrdTypeBean>());
        typeAdapter.setSelectItem(0);
        recType.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                typeAdapter.setSelectItem(position);
                typeAdapter.notifyDataSetChanged();
                typeSel = position;
                filterData();
            }
        });

        ordersAdapter = new DocOrderListOrdersAdapter(new ArrayList<List<DocOrderListBean.OrdListBean>>());
        recOrders.setAdapter(ordersAdapter);
    }


    private void initDataOrders(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap<String,String> map = new HashMap<>();
        map.put("episodId",episodId);
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        DocOrderListApiManager.getDocOrderList(map, "getDocOrderList", new DocOrderListApiManager.getDocOrderListCallBack() {
            @Override
            public void onSuccess(DocOrderListBean docOrderListBean) {
                hideLoadingTip();

                ordPrioritybeanList = docOrderListBean.getOrdPriority();
                priorityAdapter.setNewData(ordPrioritybeanList);

                ordStatusBeanList = docOrderListBean.getOrdStatus();
                statusAdapter.setNewData(ordStatusBeanList);

                ordTypeBeanList = docOrderListBean.getOrdType();
                typeAdapter.setNewData(ordTypeBeanList);

                ordListBeanList = docOrderListBean.getOrdList();
//                ordersAdapter.setNewData(ordListBeanList);
                filterData();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(code+":"+msg);
            }
        });
    }

    private void initDataPats(){
        HashMap<String,String> map = new HashMap<>();
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        DocOrderListApiManager.getPatsList(map, "getInWardPatList", new DocOrderListApiManager.getPatsListCallback() {
            @Override
            public void onSuccess(DocOrdersPatsListBean docOrdersPatsListBean) {
                patsListBeanList = docOrdersPatsListBean.getPatInfoList();
                patsAdapter.setNewData(patsListBeanList);
                episodId = patsListBeanList.get(0).getEpisodeId();
                initDataOrders();

                Bundle bundle = getArguments();
                String getEpisodId;
                if (bundle != null) {
                    getEpisodId = bundle.getString("episodeid");
                    for (int i = 0;i<patsListBeanList.size();i++){
                        if ( patsListBeanList.get(i).getEpisodeId().equals(getEpisodId)){
//                    patSel = i;
                            patsAdapter.setSelectItem(i);
                            patsAdapter.notifyDataSetChanged();
                            episodId = patsListBeanList.get(i).getEpisodeId();
//                            recPats.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            recPats.smoothScrollToPosition(i);
                            recPats.scrollToPosition(i);


                            LinearLayoutManager mLayoutManager =
                                    (LinearLayoutManager) recPats.getLayoutManager();
                            mLayoutManager.scrollToPositionWithOffset(i, 0);
                            initDataOrders();
                        }
                    }
                }
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }


    private void filterData(){
        List<List<DocOrderListBean.OrdListBean>> orderListTemp = new ArrayList<>();
        List<List<DocOrderListBean.OrdListBean>> orderListShow = ordListBeanList;
        //过滤优先级
        if (prioritySel != 0) {
            for (int i = 0; i < orderListShow.size(); i++) {
                if (orderListShow.get(i).get(0).getOrdPriority().equals(ordPrioritybeanList.get(prioritySel).getDesc())) {
                    orderListTemp.add((orderListShow.get(i)));
                }
            }
            orderListShow = orderListTemp;
            orderListTemp = new ArrayList<>();
        }
        //过滤状态
        if (statusSel != 0) {
            for (int i = 0; i < orderListShow.size(); i++) {
                if (orderListShow.get(i).get(0).getOrdStatus().equals(ordStatusBeanList.get(statusSel).getDesc())) {
                    orderListTemp.add((orderListShow.get(i)));
                }
            }
            orderListShow = orderListTemp;
            orderListTemp = new ArrayList<>();
        }
        //过滤类别
        if (typeSel != 0){
        for (int i = 0;i<orderListShow.size();i++){
            if (orderListShow.get(i).get(0).getOrdType().equals(ordTypeBeanList.get(typeSel).getDesc())){
                orderListTemp.add((orderListShow.get(i)));
            }
        }
            orderListShow = orderListTemp;
        }

        ordersAdapter.setNewData(orderListShow);
        ordersAdapter.notifyDataSetChanged();


    }

}
