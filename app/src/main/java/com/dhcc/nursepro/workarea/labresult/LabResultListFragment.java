package com.dhcc.nursepro.workarea.labresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.adapter.LabResultListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabResultListFragment extends BaseFragment {

    private RecyclerView recLabList;
    private LinearLayout llEmtpy;
    private LabResultListAdapter resultListAdapter;
    private List<LabResultListBean.LabOrderListBean> listBeans =new ArrayList<>();
    private String episodeId, patmsg;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lablist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId");
        patmsg = bundle.getString("patmsg");
        setToolbarCenterTitle(patmsg, 0xffffffff, 17);

        initview(view);
        initAdapter();
        initData();
    }

    private void initview(View view) {

        llEmtpy = view.findViewById(R.id.ll_lablist_empty);
        recLabList = view.findViewById(R.id.recy_lablist);
        recLabList.setHasFixedSize(true);
        recLabList.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        resultListAdapter = new LabResultListAdapter(new ArrayList<LabResultListBean.LabOrderListBean>());
        recLabList.setAdapter(resultListAdapter);
        resultListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_labreport) {
                    if (listBeans.get(position).getResultStatus().equals("Y")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("oeordId", listBeans.get(position).getOeordId());
                        bundle.putString("orderName", listBeans.get(position).getOrderName());
                        startFragment(LabResultDetailFragment.class, bundle);
                    }
                }
            }
        });
    }

    private void initData() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("episodeId", episodeId);
        LabApiManager.getLabListMsg(map, "getLabOrdList", new LabApiManager.GeLabListCallback() {
            @Override
            public void onSuccess(LabResultListBean labResultListBean) {
                listBeans = labResultListBean.getLabOrderList();
                if (listBeans.size() == 0) {
                    llEmtpy.setVisibility(View.VISIBLE);
                } else {
                    llEmtpy.setVisibility(View.GONE);
                }
                resultListAdapter.setNewData(listBeans);
                resultListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }


}
