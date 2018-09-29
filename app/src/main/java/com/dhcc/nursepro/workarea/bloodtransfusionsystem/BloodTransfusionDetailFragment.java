package com.dhcc.nursepro.workarea.bloodtransfusionsystem;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.adapter.BloodTransfusionTourlistAdapter;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodTransDetailBean;

import java.lang.annotation.ElementType;
import java.util.ArrayList;

/**
 * BloodTransfusionDetailFragment
 * 输血详情
 *
 * @author DevLix126
 * created at 2018/9/27 15:24
 */
public class BloodTransfusionDetailFragment extends BaseFragment {
    private TextView bloodtransdetailPatinfo;
    private TextView bloodtransdetailBloodinfo;
    private LinearLayout llBlooddetailSign;
    private TextView tvBlooddetailSigndate;
    private TextView tvBlooddetailSigntime;
    private TextView tvBlooddetailSignnurse;
    private LinearLayout llBlooddetailTrans;
    private TextView tvBlooddetailTransdate;
    private TextView tvBlooddetailTranstime;
    private TextView tvBlooddetailTransnurse1;
    private TextView tvBlooddetailTransnurse2;
    private LinearLayout llBlooddetailTransend;
    private TextView tvBlooddetailTransenddate;
    private TextView tvBlooddetailTransendtime;
    private TextView tvBlooddetailTransendnurse;
    private LinearLayout llBlooddetailTransenderror;
    private TextView tvBlooddetailTransenderrordate;
    private TextView tvBlooddetailTransenderrortime;
    private TextView tvBlooddetailTransenderrornurse;
    private LinearLayout llBlooddetailRecycling;
    private TextView tvBlooddetailRecyclingdate;
    private TextView tvBlooddetailRecyclingtime;
    private TextView tvBlooddetailRecyclingnurse;
    private RecyclerView recyBlooddetailTour;
    private TextView tvBlooddetailTransenderrorReason;
    private LinearLayout llBlooddetailTourempty;

    private String bloodRowId;

    private BloodTransfusionTourlistAdapter tourlistAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_bloodtransfusiondetail));
        setToolbarBottomLineVisibility(false);
        Bundle bundle = getArguments();
        bloodRowId = bundle.getString("bloodRowId");
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
        bloodtransdetailPatinfo = view.findViewById(R.id.bloodtransdetail_patinfo);
        bloodtransdetailBloodinfo = view.findViewById(R.id.bloodtransdetail_bloodinfo);
        llBlooddetailSign = view.findViewById(R.id.ll_blooddetail_sign);
        tvBlooddetailSigndate = view.findViewById(R.id.tv_blooddetail_signdate);
        tvBlooddetailSigntime = view.findViewById(R.id.tv_blooddetail_signtime);
        tvBlooddetailSignnurse = view.findViewById(R.id.tv_blooddetail_signnurse);
        llBlooddetailTrans = view.findViewById(R.id.ll_blooddetail_trans);
        tvBlooddetailTransdate = view.findViewById(R.id.tv_blooddetail_transdate);
        tvBlooddetailTranstime = view.findViewById(R.id.tv_blooddetail_transtime);
        tvBlooddetailTransnurse1 = view.findViewById(R.id.tv_blooddetail_transnurse1);
        tvBlooddetailTransnurse2 = view.findViewById(R.id.tv_blooddetail_transnurse2);
        llBlooddetailTransend = view.findViewById(R.id.ll_blooddetail_transend);
        tvBlooddetailTransenddate = view.findViewById(R.id.tv_blooddetail_transenddate);
        tvBlooddetailTransendtime = view.findViewById(R.id.tv_blooddetail_transendtime);
        tvBlooddetailTransendnurse = view.findViewById(R.id.tv_blooddetail_transendnurse);
        llBlooddetailTransenderror = view.findViewById(R.id.ll_blooddetail_transenderror);
        tvBlooddetailTransenderrordate = view.findViewById(R.id.tv_blooddetail_transenderrordate);
        tvBlooddetailTransenderrortime = view.findViewById(R.id.tv_blooddetail_transenderrortime);
        tvBlooddetailTransenderrornurse = view.findViewById(R.id.tv_blooddetail_transenderrornurse);
        llBlooddetailRecycling = view.findViewById(R.id.ll_blooddetail_recycling);
        tvBlooddetailRecyclingdate = view.findViewById(R.id.tv_blooddetail_recyclingdate);
        tvBlooddetailRecyclingtime = view.findViewById(R.id.tv_blooddetail_recyclingtime);
        tvBlooddetailRecyclingnurse = view.findViewById(R.id.tv_blooddetail_recyclingnurse);
        recyBlooddetailTour = view.findViewById(R.id.recy_blooddetail_tour);
        tvBlooddetailTransenderrorReason = view.findViewById(R.id.tv_blooddetail_transenderror_reason);
        llBlooddetailTourempty = view.findViewById(R.id.ll_blooddetail_tourempty);

        //提高展示效率
        recyBlooddetailTour.setHasFixedSize(true);
        //设置的布局管理
        recyBlooddetailTour.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyBlooddetailTour.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        tourlistAdapter = new BloodTransfusionTourlistAdapter(new ArrayList<BloodTransDetailBean.PatrolRecordBean>());
        recyBlooddetailTour.setAdapter(tourlistAdapter);

    }

    private void asyncInitData() {
        BloodTSApiManager.getBloodPatrolRecord(bloodRowId, new BloodTSApiManager.BloodTransDetailCallback() {
            @Override
            public void onSuccess(BloodTransDetailBean bloodTransDetailBean) {
                bloodtransdetailPatinfo.setText(bloodTransDetailBean.getBedCode() + "床-" + bloodTransDetailBean.getPatName() + "-" + bloodTransDetailBean.getPatBldTyp());
                bloodtransdetailBloodinfo.setText(bloodTransDetailBean.getBloodProducDesc() + "-" + bloodTransDetailBean.getBldTyp());
                if (TextUtils.isEmpty(bloodTransDetailBean.getStopReason())) {
                    tvBlooddetailTransenderrorReason.setVisibility(View.GONE);
                    llBlooddetailTransenderror.setVisibility(View.GONE);
                    llBlooddetailTransend.setVisibility(View.VISIBLE);
                } else {
                    llBlooddetailTransend.setVisibility(View.GONE);
                    llBlooddetailTransenderror.setVisibility(View.VISIBLE);
                    tvBlooddetailTransenderrorReason.setVisibility(View.VISIBLE);
                }

                if (TextUtils.isEmpty(bloodTransDetailBean.getReciveDate())) {
                    llBlooddetailSign.setSelected(false);
                } else {
                    llBlooddetailSign.setSelected(true);
                    tvBlooddetailSigndate.setText(bloodTransDetailBean.getReciveDate());
                    tvBlooddetailSigntime.setText(bloodTransDetailBean.getReciveTime());
                    tvBlooddetailSignnurse.setText(bloodTransDetailBean.getReciveUser());
                }
                if (TextUtils.isEmpty(bloodTransDetailBean.getTransStartDate())) {
                    llBlooddetailTrans.setSelected(false);
                } else {
                    llBlooddetailTrans.setSelected(true);
                    tvBlooddetailTransdate.setText(bloodTransDetailBean.getTransStartDate());
                    tvBlooddetailTranstime.setText(bloodTransDetailBean.getTransStartTime());
                    tvBlooddetailTransnurse1.setText(bloodTransDetailBean.getTransFirstUser());
                    tvBlooddetailTransnurse2.setText(bloodTransDetailBean.getTransSecondUser());
                }
                if (TextUtils.isEmpty(bloodTransDetailBean.getTransEdDate())) {
                    llBlooddetailTransend.setSelected(false);
                    llBlooddetailTransenderror.setSelected(false);
                } else {
                    llBlooddetailTransend.setSelected(true);
                    llBlooddetailTransenderror.setSelected(true);
                    if (TextUtils.isEmpty(bloodTransDetailBean.getStopReason())) {
                        tvBlooddetailTransenddate.setText(bloodTransDetailBean.getTransEdDate());
                        tvBlooddetailTransendtime.setText(bloodTransDetailBean.getTransEdTime());
                        tvBlooddetailTransendnurse.setText(bloodTransDetailBean.getTransEdUser());
                    } else {
                        tvBlooddetailTransenderrordate.setText(bloodTransDetailBean.getTransEdDate());
                        tvBlooddetailTransenderrortime.setText(bloodTransDetailBean.getTransEdTime());
                        tvBlooddetailTransenderrornurse.setText(bloodTransDetailBean.getTransEdUser());
                        tvBlooddetailTransenderrorReason.setText("终止原因："+bloodTransDetailBean.getStopReason());
                    }
                }
                if (TextUtils.isEmpty(bloodTransDetailBean.getTransRecycleDate())) {
                    llBlooddetailRecycling.setSelected(false);
                } else {
                    llBlooddetailRecycling.setSelected(true);
                    tvBlooddetailRecyclingdate.setText(bloodTransDetailBean.getTransRecycleDate());
                    tvBlooddetailRecyclingtime.setText(bloodTransDetailBean.getTransRecycleTime());
                    tvBlooddetailRecyclingnurse.setText(bloodTransDetailBean.getTransRecycleUser());
                }

                tourlistAdapter.setNewData(bloodTransDetailBean.getPatrolRecord());
                if (bloodTransDetailBean.getPatrolRecord() != null && bloodTransDetailBean.getPatrolRecord().size() > 0) {
                    recyBlooddetailTour.setVisibility(View.VISIBLE);
                    llBlooddetailTourempty.setVisibility(View.GONE);
                } else {
                    recyBlooddetailTour.setVisibility(View.GONE);
                    llBlooddetailTourempty.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
