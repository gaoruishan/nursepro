package com.dhcc.nursepro.workarea.infusiondrugreceive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.infusiondrugreceive.adapter.DrugReceivedAdapter;
import com.dhcc.nursepro.workarea.infusiondrugreceive.api.DrugReceiveApiManager;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.BatchIFSaveResultBean;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.IfOrdListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive
 * <p>
 * author Q
 * Date: 2020/3/10
 * Time:9:03
 */
public class DrugReceiveFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recIfOrd;
    private DrugReceivedAdapter drugReceivedAdapter = new DrugReceivedAdapter(new ArrayList<>());

    private String startDate;
    private String endDate;

    private TextView tvStDate,tvEndDate;
    private LinearLayout llEmpty;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drugreceive, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_drugreceive), 0xffffffff, 17);
        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("    ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        setToolbarRightCustomView(viewright);
        startDate = DateUtils.getDateTimeAgo(SPUtils.getInstance().getString(SharedPreference.CURDATETIME),1).substring(0,10);
        endDate = SPUtils.getInstance().getString(SharedPreference.CURDATETIME).substring(0,10);
        initView(view);
        initData();
    }

    private void initView(View view){

        tvStDate = view.findViewById(R.id.tv_iford_startdatetime);
        tvEndDate = view.findViewById(R.id.tv_iford_enddatetime);
        tvStDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        tvStDate.setText(startDate);
        tvEndDate.setText(endDate);

        llEmpty = view.findViewById(R.id.ll_iford_empty);

        recIfOrd = view.findViewById(R.id.rec_drureceive_iford);

        recIfOrd.setHasFixedSize(true);
        recIfOrd.setLayoutManager(new LinearLayoutManager(getActivity()));
        recIfOrd.setAdapter(drugReceivedAdapter);
    }

    private void initData(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("sttDate", startDate);
        map.put("endDate", endDate);
//        map.put("locId", "197");
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        DrugReceiveApiManager.getIfOrDList(map, "getIFOrdListByBarCode", new DrugReceiveApiManager.ifOrdLIstCallback() {
            @Override
            public void onSuccess(IfOrdListBean ifOrdListBean) {
                hideLoadFailTip();
                drugReceivedAdapter.setNewData(ifOrdListBean.getOrdList());
                if (ifOrdListBean.getOrdList().size()<1){
                    llEmpty.setVisibility(View.VISIBLE);
                }else {
                    llEmpty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void saveIfOrdByBarcode(String barcode){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("OeoreId", barcode);
        map.put("userId", spUtils.getString(SharedPreference.USERID));
//        map.put("locId", "197");
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        DrugReceiveApiManager.ordSaveMsg(map, "BatchIFSave", new DrugReceiveApiManager.ordSaveResultCallback() {
            @Override
            public void onSuccess(BatchIFSaveResultBean batchIFSaveResultBean) {
                hideLoadFailTip();
                showToast("接收成功");
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast( msg);
            }
        });
    }

    private void chooseTime(long currentTimeMillis,String etChangeFlag) {
        DateUtils.chooseDate(currentTimeMillis,getContext(), getFragmentManager(), new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                String date = TimeUtils.millis2String(millseconds).substring(0, 10);
                String time = TimeUtils.millis2String(millseconds).substring(11, 16);

                if ("START".equals(etChangeFlag)) {
                    startDate = TimeUtils.millis2String(millseconds);
                    initData();

                    tvStDate.setText(TimeUtils.millis2String(millseconds).substring(0, 10));
                } else {
                    endDate = TimeUtils.millis2String(millseconds);
                    initData();

                    tvEndDate.setText(TimeUtils.millis2String(millseconds).substring(0, 10));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_iford_startdatetime:
                chooseTime(TimeUtils.string2Millis(tvStDate.getText().toString() + " 00:00:00"),"START");
                break;
            case R.id.tv_iford_enddatetime:
                chooseTime(TimeUtils.string2Millis(tvEndDate.getText().toString() + " 00:00:00"),"END");
                break;

            default:
                break;
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();

            saveIfOrdByBarcode( bundle.getString("data"));

        }

    }
}
