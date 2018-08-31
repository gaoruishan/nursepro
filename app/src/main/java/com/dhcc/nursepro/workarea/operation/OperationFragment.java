package com.dhcc.nursepro.workarea.operation;

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
import com.dhcc.nursepro.workarea.operation.adapter.OperationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationFragment extends BaseFragment {

    private RecyclerView recOperation;
    private OperationAdapter operationAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_operation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("手术查询",0xffffffff,17);

        initview(view);

    }
    private void initview(View view){
        recOperation = view.findViewById(R.id.rec_operation);

        //提高展示效率
        recOperation.setHasFixedSize(true);
        //设置的布局管理
        recOperation.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recOperation.setHasFixedSize(true);
        //设置的布局管理
        recOperation.setLayoutManager(new LinearLayoutManager(getActivity()));

        operationAdapter = new OperationAdapter(new ArrayList<Map>());
        recOperation.setAdapter(operationAdapter);
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
        operationAdapter.setNewData(list);
    }

}
