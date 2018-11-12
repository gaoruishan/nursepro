package com.dhcc.nursepro.workarea.labout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.labout.adapter.LabOutDetailAdapter;
import com.dhcc.nursepro.workarea.labout.api.LabOutApiManager;
import com.dhcc.nursepro.workarea.labout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LabOutDetailFragment extends BaseFragment {

    private View viewright;
    private TextView textView;
    private TextView tvLaboutScan;
    private TextView tvLaboutSend;
    private EditText etLaboutContainer;

    private RecyclerView recaddLabOut;
    private LabOutDetailAdapter labOutDetailAdapter;
    private List<LabOutDetailBean.DetailListBean> listBeans;

    private String carryNo = "", carryLocDr = "", carryLabNo = "", DelSend = "", saveFlag = "", carryFlag = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private IntentFilter intentFilter;
    private DataReceiver dataReceiver = null;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            carryNo = bundle.getString("CarryNo");
        }
        setToolbarCenterTitle(carryNo, 0xffffffff, 17);

        initview(view);
        initAdapter();
        initData();


        //扫描广播
        intentFilter = new IntentFilter();
        intentFilter.addAction(Action.DEVICE_SCAN_CODE);
        dataReceiver = new DataReceiver();
        getActivity().registerReceiver(dataReceiver, intentFilter);

    }

    private void initview(View view) {

        tvLaboutScan = view.findViewById(R.id.tv_layout_scannum);
        tvLaboutSend = view.findViewById(R.id.tv_layout_send);
        tvLaboutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delOrExchange();
            }
        });
        etLaboutContainer = view.findViewById(R.id.et_labout_container);

        recaddLabOut = view.findViewById(R.id.rec_addlabout);
        //提高展示效率
        recaddLabOut.setHasFixedSize(true);
        //设置的布局管理
        recaddLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recaddLabOut.setHasFixedSize(true);
        //设置的布局管理
        recaddLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {

        labOutDetailAdapter = new LabOutDetailAdapter(new ArrayList<LabOutDetailBean.DetailListBean>());
        recaddLabOut.setAdapter(labOutDetailAdapter);
        labOutDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.messagerightmenu) {
                    //                    DelSend = "del";
                    saveFlag = "0";
                    carryLocDr = listBeans.get(position).getCarryLoc();
                    carryLabNo = listBeans.get(position).getCarryLabNo();
                    initData();
                }
            }
        });
    }

    private void initData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("carryNo", carryNo);
        if (!saveFlag.equals("")) {
            map.put("labNo", carryLabNo);
            map.put("locDr", carryLocDr);
            map.put("saveFlag", saveFlag);
        }
        LabOutApiManager.getLabOutDetailMsg(map, "getLabOutDetail", new LabOutApiManager.getLabOutDetailCallBack() {
            @Override
            public void onSuccess(LabOutDetailBean labOutDetailBean) {
                //                setToolbarCenterTitle("检验打包",0xffffffff,17);
                saveFlag = "";
                carryFlag = labOutDetailBean.getCarryFlag();
                if (carryFlag.equals("1")) {
                    tvLaboutSend.setText("  撤销发送  ");
                } else {
                    tvLaboutSend.setText("  发送    ");
                }
                etLaboutContainer.setText(labOutDetailBean.getTransContainer());

                listBeans = labOutDetailBean.getDetailList();
                tvLaboutScan.setText("已扫描 " + listBeans.size() + " 个");
                labOutDetailAdapter.setNewData(listBeans);
            }

            @Override
            public void onFail(String code, String msg) {
                saveFlag = "";
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void delOrExchange() {
        if (listBeans.size() == 0) {
            showToast("检验包为空，无法进行操作");
            return;
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);

        HashMap<String, String> map = new HashMap<>();
        map.put("carryNo", carryNo);
        if ("0".equals(carryFlag)) {
            map.put("containerNo", etLaboutContainer.getText().toString());
            map.put("transUserId", "3");
        }
        LabOutApiManager.delOrdMsg(map, "delOrExchange", new LabOutApiManager.delOrdCallBack() {
            @Override
            public void onSuccess(DelOrderBean delOrderBean) {
                hideLoadFailTip();
                showToast(delOrderBean.getMsg());
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    @Override
    public void onResume() {
        getActivity().registerReceiver(dataReceiver, intentFilter);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(dataReceiver);

    }

    //扫描腕带获取CarryNo
    public class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                carryLocDr = spUtils.getString(SharedPreference.LOCID);
                carryLabNo = bundle != null ? bundle.getString("data") : "";
                saveFlag = "1";
                initData();

            }
        }
    }

}
