package com.dhcc.module.infusion.workarea.patrol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.patrol.adapter.InfusionTourAdapter;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;
import com.dhcc.module.infusion.workarea.patrol.api.PatrolApiManager;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionReasonListBean;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionStateListBean;
import com.dhcc.module.infusion.workarea.patrol.bean.PatrolBean;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

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
        csvScan = mContainerChild.findViewById(R.id.custom_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        csvSelectTime = mContainerChild.findViewById(R.id.csv_select_time);
        csvSelectStatus = mContainerChild.findViewById(R.id.csv_select_status);
        csvSelectReason = mContainerChild.findViewById(R.id.csv_select_reason);
        llMeasure = mContainerChild.findViewById(R.id.ll_measure);
        edMeasure = mContainerChild.findViewById(R.id.ed_measure);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        rvOrdList = RecyclerViewHelper.get(mContext, R.id.rv_ord_list);
        ordListAdapter = AdapterFactory.getCommPatrolOrdList();
        rvOrdList.setAdapter(ordListAdapter);
        rvPatrolStatus = RecyclerViewHelper.get(mContext, R.id.rv_patrol_status);
        tourAdapter = AdapterFactory.getInfusionTour();
        rvPatrolStatus.setAdapter(tourAdapter);
        showScanLabel();
    }

    @Override
    protected void getScanOrdList() {

        getOrdList(scanInfo);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_patrol;
    }

    private void getOrdList(final String scanInfo, final boolean... refresh) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        PatrolApiManager.getTourOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<PatrolBean>() {


            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(final PatrolBean bean, String type) {
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                //两次验证
//                auditOrdInfo(bean.getOrdList(),bean.getCurRegNo(),bean.getCurOeoreId());
                setCustomPatViewData(cpvPat, bean.getPatInfo());
                //设置滴速 点击计时预计时间
                csvSpeed.setSpeed(bean.getDefautSpeed()).setOnSpeedClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int speed = csvSpeed.getSpeed();
                        String doseTime = bean.computeDoseTime(speed);
                        csvSelectTime.setSelect(doseTime);
                    }
                });
                csvSelectTime.setTitle("预计结束时间").setSelectTime(getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
                List<InfusionStateListBean> stateList = bean.getInfusionStateList();
                if (bean.getInfusionStateList() != null) {
                    setInfusionSate(bean, stateList);
                }
                ordListAdapter.setCurrentScanInfo(scanInfo);
                ordListAdapter.replaceData(bean.getOrdList());
                boolean isShow = bean.getInfusionTourList() == null || bean.getInfusionTourList().size() == 0;
                mContainerChild.findViewById(R.id.ll_patrol_status).setVisibility(isShow ? View.GONE : View.VISIBLE);
                tourAdapter.replaceData(bean.getInfusionTourList());

                if (checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
                    if (refresh != null && refresh.length > 0 && refresh[0]) {
                        // 扫码执行,刷新列表不再执行
                        return;
                    }
                    if (CommInfusionBean.SCAN_EXE.equals(bean.getScanFlag())) {
                        clickExtractOrd();
                    }
                }
            }
        });
    }

    private void setInfusionSate(PatrolBean bean, List<InfusionStateListBean> stateList) {
        List<String> list = new ArrayList<>();
        for (InfusionStateListBean b : stateList) {
            list.add(b.getInfusionState());
        }
        csvSelectStatus.setTitle("输液情况").setSelectData(getActivity(), list, new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                InfusionStateListBean infusionStateListBean = stateList.get(index);
                csvSelectReason.setVisibility(View.GONE);
                llMeasure.setVisibility(View.GONE);
                if (PatrolBean.State_Pause.equals(item) || "1".equals(infusionStateListBean.getInfusionStateFlag())) {
                    List<InfusionReasonListBean> reasonList = bean.getInfusionReasonList();
                    if (reasonList != null) {
                        List<String> list = new ArrayList<>();
                        for (InfusionReasonListBean b : reasonList) {
                            list.add(b.getInfusionReason());
                        }
                        llMeasure.setVisibility(View.VISIBLE);
                        csvSelectReason.setTitle(item + "原因").setSelectData(getActivity(), list, null).setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            clickExtractOrd();
        }
    }

    private void clickExtractOrd() {
        String oeoreId = "";
        if (mBean != null) {
            oeoreId = mBean.getCurOeoreId();
        }
        if (csvSpeed.isNotSpeed()) {
            return;
        }
        final String distantTime = csvSelectTime.getSelect();

        String tourContent = "WorkInfusionState|" + csvSelectStatus.getSelect();

        if (PatrolBean.State_Pause.equals(csvSelectStatus.getSelect())|| llMeasure.getVisibility()==View.VISIBLE) {
            tourContent = tourContent + "^WorkInfusionReason|" + csvSelectReason.getSelect();
            if (!TextUtils.isEmpty(edMeasure.getText())) {
                tourContent = tourContent + "^WorkInfusionMeasure|" + edMeasure.getText().toString();
            }
        }
        //异常结束-弹框
        if (PatrolBean.State_Finsh.equals(csvSelectStatus.getSelect())) {
            final String finalOeoreId = oeoreId;
            final String finalTourContent = tourContent;
            DialogFactory.showCommOkCancelDialog(getActivity(), "提示", "您确定'结束'输液?", "取消", "确定", null, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tourOrd(finalOeoreId, csvSpeed.getSpeed(), distantTime, finalTourContent);
                }
            });
        } else {
            tourOrd(oeoreId, csvSpeed.getSpeed(), distantTime, tourContent);
        }
    }

    private void tourOrd(String oeoreId, int speed, String distantTime, String tourContent) {
        PatrolApiManager.tourOrd(oeoreId, distantTime, speed + "", "", tourContent, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
//                    csvScan.setVisibility(View.VISIBLE);
                if (scanInfo != null) {
                    getOrdList(scanInfo,true);
                }
                DialogFactory.showCommDialog(getActivity(), bean.getMsg(), "", 0, null, true);
                onSuccessThings();
            }
        });
    }
}
