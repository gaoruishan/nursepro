package com.dhcc.module.infusion.workarea.patrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.patrol.adapter.InfusionTourAdapter;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;
import com.dhcc.module.infusion.workarea.patrol.api.PatrolApiManager;
import com.dhcc.module.infusion.workarea.patrol.bean.PatrolBean;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 巡视
 * @author:gaoruishan
 * @date:202019-04-28/08:40
 * @email:grs0515@163.com
 */
public class PatrolFragment extends BaseInfusionFragment implements View.OnClickListener {

    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private CustomSpeedView csvSpeed;
    private CustomSelectView csvSelectTime;
    private CustomSelectView csvSelectStatus;
    private RecyclerView rvOrdList;
    private RecyclerView rvPatrolStatus;
    private PatrolOrdListAdapter ordListAdapter;
    private InfusionTourAdapter tourAdapter;
    private CustomSelectView csvSelectReason;
    private PatrolBean mBean;
    private View llMeasure;
    private EditText edMeasure;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("巡视");
        csvScan = mContainerChild.findViewById(R.id.csv_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        csvSelectTime = mContainerChild.findViewById(R.id.csv_select_time);
        csvSelectStatus = mContainerChild.findViewById(R.id.csv_select_status);
        csvSelectReason = mContainerChild.findViewById(R.id.csv_select_reason);
        llMeasure = mContainerChild.findViewById(R.id.ll_measure);
        edMeasure = mContainerChild.findViewById(R.id.ed_measure);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        rvOrdList = RecyclerViewHelper.get(this.getActivity(), R.id.rv_ord_list);
        ordListAdapter = AdapterFactory.getCommPatrolOrdList();
        rvOrdList.setAdapter(ordListAdapter);
        rvPatrolStatus = RecyclerViewHelper.get(this.getActivity(), R.id.rv_patrol_status);
        tourAdapter = AdapterFactory.getInfusionTour();
        rvPatrolStatus.setAdapter(tourAdapter);
        csvScan.setTitle("请扫描瓶签/信息卡").setWarning("请您使用扫码设备，扫描药品瓶签/信息卡");

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList(scanInfo);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_patrol;
    }

    private void getOrdList(final String scanInfo) {
        PatrolApiManager.getTourOrdList("", "", scanInfo, new CommonCallBack<PatrolBean>() {


            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(final PatrolBean bean, String type) {
                if (checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
//                    return;
                }
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                if (bean.getPatInfo() != null) {
                    cpvPat.setRegNo(bean.getPatInfo().getPatRegNo()).setPatName(bean.getPatInfo().getPatName())
                            .setAge(bean.getPatInfo().getPatAge())
                            .setImgSexResource(CustomPatView.getPatSexDrawable(bean.getPatInfo().getPatSex()));
                }
                csvSpeed.setSpeed(bean.getDefautSpeed());
                csvSelectTime.setTitle("预计结束时间").setSelectTime(getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
                List<PatrolBean.InfusionStateListBean> stateList = bean.getInfusionStateList();
                List<String> list = new ArrayList<>();
                for (PatrolBean.InfusionStateListBean b : stateList) {
                    list.add(b.getInfusionState());
                }
                csvSelectStatus.setTitle("输液情况").setSelectData(getActivity(), list, new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        csvSelectReason.setVisibility(View.GONE);
                        llMeasure.setVisibility(View.GONE);
                        if (PatrolBean.State_Pause.equals(item)) {
                            List<PatrolBean.InfusionReasonListBean> reasonList = bean.getInfusionReasonList();
                            List<String> list = new ArrayList<>();
                            for (PatrolBean.InfusionReasonListBean b : reasonList) {
                                list.add(b.getInfusionReason());
                            }
                            llMeasure.setVisibility(View.VISIBLE);
                            csvSelectReason.setTitle("暂停原因").setSelectData(getActivity(), list, null).setVisibility(View.VISIBLE);
                        }
                    }
                });
                ordListAdapter.setCurrentScanInfo(scanInfo);
                ordListAdapter.replaceData(bean.getOrdList());
                boolean isShow = bean.getInfusionTourList() == null || bean.getInfusionTourList().size() == 0;
                mContainerChild.findViewById(R.id.ll_patrol_status).setVisibility(isShow ? View.GONE : View.VISIBLE);
                tourAdapter.replaceData(bean.getInfusionTourList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            String oeoreId = "";
            if (mBean != null) {
                oeoreId = mBean.getCurOeoreId();
            }
            int speed = csvSpeed.getSpeed();
            if (speed <= 0) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            String distantTime = csvSelectTime.getSelect();

            String tourContent = "WorkInfusionState|" + csvSelectStatus.getSelect();
            if (PatrolBean.State_Pause.equals(csvSelectStatus.getSelect())) {
                tourContent = tourContent + "^WorkInfusionReason|" + csvSelectReason.getSelect();
                if (!TextUtils.isEmpty(edMeasure.getText())) {
                    tourContent = tourContent + "^WorkInfusionMeasure|" + edMeasure.getText().toString();
                }
            }
            PatrolApiManager.tourOrd(oeoreId, distantTime, speed + "", "", tourContent, new CommonCallBack<CommResult>() {
                @Override
                public void onFail(String code, String msg) {
                    ToastUtils.showShort(msg);
                }

                @Override
                public void onSuccess(CommResult bean, String type) {
//                    csvScan.setVisibility(View.VISIBLE);
                    if (scanInfo != null) {
                        getOrdList(scanInfo);
                    }
                    DialogFactory.showCommDialog(getActivity(), "巡视成功", "", 0, null, true);
                }
            });
        }
    }
}