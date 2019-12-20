package com.dhcc.nursepro.workarea.operation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.operation.adapter.OperationAdapter;
import com.dhcc.nursepro.workarea.operation.api.OperationApiManager;
import com.dhcc.nursepro.workarea.operation.bean.OperationBean;
import com.dhcc.nursepro.workarea.orderexecute.OrderHandleTypeFragment;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recOperation;
    private LinearLayout llEmtpy;
    private OperationAdapter operationAdapter;
    private List<OperationBean.OpListBean> listOp =new ArrayList<>();

    private TextView tvOrderexecuteStartdatetime;
    private TextView tvOrderexecuteEnddatetime;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
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
        startDate = SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        startTime =  SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).substring(11, 16);
        endDate = SPUtils.getInstance().getString(SharedPreference.SCHENDATETIME).substring(0, 10);
        endTime = SPUtils.getInstance().getString(SharedPreference.SCHENDATETIME).substring(11, 16);
        tvOrderexecuteStartdatetime = view.findViewById(R.id.tv_orderexecute_startdatetime);
        tvOrderexecuteEnddatetime = view.findViewById(R.id.tv_orderexecute_enddatetime);
        tvOrderexecuteStartdatetime.setText(startDate + " " + startTime);
        tvOrderexecuteEnddatetime.setText(endDate + " " + endTime);

        tvOrderexecuteStartdatetime.setOnClickListener(this);
        tvOrderexecuteEnddatetime.setOnClickListener(this);
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
        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_orderexecute_startdatetime:
                chooseTime(TimeUtils.string2Millis(tvOrderexecuteStartdatetime.getText().toString() + ":00"),"START");
                break;
            case R.id.tv_orderexecute_enddatetime:
                chooseTime(TimeUtils.string2Millis(tvOrderexecuteEnddatetime.getText().toString() + ":00"),"END");
                break;

            default:
                break;
        }
    }

    private void chooseTime(long currentTimeMillis,String etChangeFlag) {
        DateUtils.chooseDateTime(currentTimeMillis,getContext(), getFragmentManager(), new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                String date = TimeUtils.millis2String(millseconds).substring(0, 10);
                String time = TimeUtils.millis2String(millseconds).substring(11, 16);

                if ("START".equals(etChangeFlag)) {
                    if (!date.equals(startDate) || !time.equals(startTime)) {
                        //日期时间发生改变，需重新请求数据
                        startDate = date;
                        startTime = time;
                        initData();
                    }
                    tvOrderexecuteStartdatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
                } else {
                    if (!date.equals(endDate) || !time.equals(endTime)) {
                        //日期时间发生改变，需重新请求数据
                        endDate = date;
                        endTime = time;
                        initData();
                    }
                    tvOrderexecuteEnddatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
                }
            }
        });

    }

}
