package com.dhcc.nursepro.workarea.operation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.dhcc.nursepro.workarea.operation.adapter.OperationAdapter;
import com.dhcc.nursepro.workarea.operation.api.OperationApiManager;
import com.dhcc.nursepro.workarea.operation.bean.OperationBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationFragment extends BaseFragment {

    private RecyclerView recOperation;
    private LinearLayout llEmtpy;
    private OperationAdapter operationAdapter;
    private List<OperationBean.OpListBean> listOp;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_operation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_operation), 0xffffffff, 17);

        initView(view);
        initAdapter();
        initData();

    }

    private void initView(View view) {
        llEmtpy = view.findViewById(R.id.ll_operation_empty);
        recOperation = view.findViewById(R.id.rec_operation);

        //提高展示效率
        recOperation.setHasFixedSize(true);
        //设置的布局管理
        recOperation.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {

        operationAdapter = new OperationAdapter(new ArrayList<OperationBean.OpListBean>());
        recOperation.setAdapter(operationAdapter);
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        OperationApiManager.getOperationList(properties, "getOperationList", new OperationApiManager.GetOperationCallback() {
            @Override
            public void onSuccess(OperationBean operationBean) {
                hideLoadFailTip();
                listOp = operationBean.getOpList();
                operationAdapter.setNewData(listOp);
                operationAdapter.notifyDataSetChanged();
                if (listOp.size() < 1) {
                    llEmtpy.setVisibility(View.VISIBLE);
                } else {
                    llEmtpy.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);

            }
        });


    }

}
