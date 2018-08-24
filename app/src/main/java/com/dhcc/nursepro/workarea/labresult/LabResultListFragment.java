package com.dhcc.nursepro.workarea.labresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.adapter.LabResultListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.CheckLabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabResultListFragment extends BaseFragment {

    private RecyclerView recLabList;
    private LabResultListAdapter resultListAdapter;
    private List<LabResultListBean.LabOrderListBean> listBeans;
    private String episodeId,patmsg;
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
        setToolbarCenterTitle(patmsg,0xffffffff,17);

        initview(view);
        initData();

    }

    private void initview(View view){


        recLabList = view.findViewById(R.id.recy_lablist);
        recLabList.setHasFixedSize(true);
        recLabList.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultListAdapter = new LabResultListAdapter(new ArrayList<LabResultListBean.LabOrderListBean>());

        recLabList.setAdapter(resultListAdapter);

        resultListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_labreport){
                    if (listBeans.get(position).getResultStatus().equals("Y")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("oeordId", listBeans.get(position).getOeordId());
                        bundle.putString("orderName",listBeans.get(position).getOrderName());
                        startFragment(LabResultDetailFragment.class, bundle);
                    }
                }
            }
        });


    }

    private void initData(){


        HashMap<String,String> map = new HashMap<String, String>();
        map.put("episodeId",episodeId);
        CheckLabApiManager.getLabListMsg(map, "getLabOrdList", new CheckLabApiManager.GeLabListCallback() {
            @Override
            public void onSuccess(LabResultListBean labResultListBean) {
                listBeans = labResultListBean.getLabOrderList();
                if (listBeans.size() == 0){
                    showToast("该患者未进行任何检验项目");
                }
                resultListAdapter.setNewData(listBeans);
                resultListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });
    }



}
