package com.dhcc.nursepro.workarea.labresult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.adapter.LabResultDetailAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabResultDetailFragment extends BaseFragment {
    private RecyclerView reclabdetail;
    private List<LabReslutDetailBean.ResultDetailBean> listbeans =new ArrayList<>();
    private LabResultDetailAdapter labResultDetailAdapter;
    private String oeordId,orderName;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_labdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        Bundle bundle = getArguments();
        oeordId = bundle.getString("oeordId");
        orderName = bundle.getString("orderName");
        setToolbarCenterTitle(orderName,0xffffffff,17);

        initview(view);
        initdata();
    }

    private void initview(View view){
        reclabdetail = view.findViewById(R.id.recy_labdetail);
        reclabdetail.setHasFixedSize(true);
        reclabdetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        labResultDetailAdapter = new LabResultDetailAdapter(new ArrayList<>());
        reclabdetail.setAdapter(labResultDetailAdapter);

    }

    private void initdata(){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("oeoreId",oeordId);
        LabApiManager.getLabDetailMsg(map, "getLabResult", new LabApiManager.GeLabDetailCallback() {
            @Override
            public void onSuccess(LabReslutDetailBean labReslutDetailBean) {
                listbeans = labReslutDetailBean.getResultDetail();
                    labResultDetailAdapter.setNewData(listbeans);
                    labResultDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }
}
