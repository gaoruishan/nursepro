package com.dhcc.nursepro.workarea.checkresult;

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
import com.dhcc.nursepro.workarea.labresult.LabResultDetailFragment;
import com.dhcc.nursepro.workarea.labresult.adapter.LabResultListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckResultListFragment extends BaseFragment {

    private RecyclerView recLabList;
    private LabResultListAdapter resultListAdapter;
    private List<LabResultListBean.LabOrderListBean> listBeans;
    private String episodeId;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lablist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("检验列表");

        initview(view);
        initData();

    }

    private void initview(View view){


        recLabList = view.findViewById(R.id.recy_lablist);
        recLabList.setHasFixedSize(true);
        recLabList.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultListAdapter = new LabResultListAdapter(new ArrayList<LabResultListBean.LabOrderListBean>());
        recLabList.setAdapter(resultListAdapter);
        resultListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFragment(LabResultDetailFragment.class);
            }
        });


    }

    private void initData(){

        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId");
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("episodeId",episodeId);
        LabApiManager.getLabListMsg(map, "getLabOrdList", new LabApiManager.GeLabListCallback() {
            @Override
            public void onSuccess(LabResultListBean labResultListBean) {
                listBeans = labResultListBean.getLabOrderList();
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
