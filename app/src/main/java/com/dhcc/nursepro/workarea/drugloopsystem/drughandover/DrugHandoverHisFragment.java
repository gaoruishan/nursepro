package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter.DrugHandoverHisAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api.DrugHandoverApiManager;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.GetOrdRecListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DrugHandoverHisFragment
 * 药品交接历史页面
 *
 * @author Devlix126
 * created at 2019/6/12 10:41
 */
public class DrugHandoverHisFragment extends BaseFragment implements OnDateSetListener {
    private TextView tvDrughandoverhisStartdate;
    private TextView tvDrughandoverhisEnddate;
    private RecyclerView recyDrughandoverhisList;

    private String startDate;
    private String endDate;
    private String dateChangeFlag;
    private SPUtils spUtils = SPUtils.getInstance();

    private DrugHandoverHisAdapter drugHandoverHisAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_drughandoverhis));
        setToolbarBottomLineVisibility(true);

        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);

    }

    private void initView(View view) {
        tvDrughandoverhisStartdate = view.findViewById(R.id.tv_drughandoverhis_startdate);
        tvDrughandoverhisEnddate = view.findViewById(R.id.tv_drughandoverhis_enddate);

        tvDrughandoverhisStartdate.setText(startDate);
        tvDrughandoverhisEnddate.setText(endDate);
        tvDrughandoverhisStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvDrughandoverhisStartdate.getText().toString() + " 00:00:00"));
            }
        });
        tvDrughandoverhisEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvDrughandoverhisEnddate.getText().toString() + " 00:00:00"));
            }
        });

        recyDrughandoverhisList = view.findViewById(R.id.recy_drughandoverhis_list);

        recyDrughandoverhisList.setHasFixedSize(true);
        recyDrughandoverhisList.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void initAdapter() {
        drugHandoverHisAdapter = new DrugHandoverHisAdapter(new ArrayList<>());
        drugHandoverHisAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetOrdRecListBean.RecListBean recListBean = (GetOrdRecListBean.RecListBean) adapter.getItem(position);

                List<GetOrdRecListBean.RecListBean.RecSubListBean> recSubListBeanList = recListBean.getRecSubList();
                Bundle bundle = new Bundle();
                bundle.putString("receiveUser", recListBean.getRecieveUser());
                bundle.putString("receiveDateTime", recListBean.getRecieveDateTime());
                bundle.putString("carryUser", recListBean.getCarryUser());
                bundle.putSerializable("data", (Serializable) recSubListBeanList);
                startFragment(DrugHandoverHisDetailFragment.class, bundle);
            }
        });

        recyDrughandoverhisList.setAdapter(drugHandoverHisAdapter);
    }

    /**
     * 加载已交接药品列表
     */
    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        DrugHandoverApiManager.getOrdRecieveList(startDate, endDate, new DrugHandoverApiManager.GetOrdReceiveListCallback() {
            @Override
            public void onSuccess(GetOrdRecListBean getOrdRecListBean) {
                hideLoadingTip();
                drugHandoverHisAdapter.setNewData(getOrdRecListBean.getRecList());
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);

        if ("START".equals(dateChangeFlag)) {
            tvDrughandoverhisStartdate.setText(date);
            if (!date.equals(startDate)) {
                //日期时间发生改变，需重新请求数据
                startDate = date;
                asyncInitData();
            }

        } else {
            tvDrughandoverhisEnddate.setText(date);
            if (!date.equals(endDate)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                asyncInitData();
            }
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drug_handover_his, container, false);
    }
}
