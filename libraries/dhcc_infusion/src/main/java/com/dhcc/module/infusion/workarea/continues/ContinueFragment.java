package com.dhcc.module.infusion.workarea.continues;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.continues.api.ContinueApiManager;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

/**
 * 续液
 * @author:gaoruishan
 * @date:202019-04-29/09:18
 * @email:grs0515@163.com
 */
public class ContinueFragment extends BaseInfusionFragment implements View.OnClickListener {

    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private CustomSpeedView csvSpeed;
    private CommDosingAdapter commDosingAdapter;
    private ContinueBean mBean;
    private CustomSelectView customSelectTime;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        csvScan = mContainerChild.findViewById(R.id.custom_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        customSelectTime = mContainerChild.findViewById(R.id.custom_select_time);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        showScanLabelOrCard();
        setInitWayNo("开启新通道");
        RecyclerView rvOrdList = RecyclerViewHelper.get(mContext, R.id.rv_ord_list);
        commDosingAdapter = AdapterFactory.getCommDosingOrdList();
        rvOrdList.setAdapter(commDosingAdapter);
    }

    @Override
    protected void getScanOrdList() {

        getOrdList(scanInfo);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_continue;
    }

    private void getOrdList(final String scanInfo,final boolean... refresh) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        ContinueApiManager.getChangeOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<ContinueBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(final ContinueBean bean, String type) {
                //检查扫码不包含提示
                checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD);
                // 第一次扫码
                mBean = bean;
                f(R.id.rl_way).setVisibility(bean.getWayListString().size() > 0 ? View.VISIBLE : View.GONE);
                customSelectChannel.setSelectData(mContext, bean.getWayListString(), null);
                customOnOff.setDisEnable(bean.getIsCurrentWayNo(), STR_ORD_ING);

                customSelectTime.setTitle("预计结束时间");
                customSelectTime.setSelectTime(ContinueFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
                csvScan.setVisibility(View.GONE);
                setCustomPatViewData(cpvPat, bean.getPatInfo());
                //设置滴速 点击计时预计时间
                csvSpeed.setSpeed(bean.getDefautSpeed()).setOnSpeedClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int speed = csvSpeed.getSpeed();
                        String doseTime = bean.computeDoseTime(speed);
                        customSelectTime.setSelect(doseTime);
                    }
                });
                commDosingAdapter.setCurrentScanInfo(scanInfo);
                commDosingAdapter.replaceData(bean.getOrdList());
                //两次扫码验证
                boolean isOk = auditOrdInfo(bean.getOrdList(), bean.getCurRegNo(), bean.getCurOeoreId());
                if (isOk) {
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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            clickExtractOrd();
        }
    }

    private void clickExtractOrd() {
        String oeoreId = "";
        String distantTime = "";
        if (mBean != null) {
            oeoreId = mBean.getCurOeoreId();
        }
        distantTime = customSelectTime.getSelect();
        if (TextUtils.isEmpty(distantTime)) {
            distantTime = mBean.getDistantDate() + " " + mBean.getDistantTime();
        }
        if (csvSpeed.isNotSpeed()) {
            return;
        }
        //通道
        String wayNo = customSelectChannel.getSelect().replace(STR_WAY_NO, "");
        String newWayFlag = customOnOff.isSelect() ? "1" : "";
        if (customOnOff.isSelect()) {
            wayNo = String.valueOf(mBean.getWayListString().size() + 1);
        }
        ContinueApiManager.changeOrd(oeoreId, distantTime, csvSpeed.getSpeed() + "", "", wayNo, newWayFlag, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                mBean = null; //置空
                if (scanInfo != null) {
                    getOrdList(scanInfo,true);
                }
                DialogFactory.showCommDialog(getActivity(), "续液成功", "", 0, null, true);
                onSuccessThings();
            }
        });
    }
}
