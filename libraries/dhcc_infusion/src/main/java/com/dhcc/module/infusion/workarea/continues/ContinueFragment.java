package com.dhcc.module.infusion.workarea.continues;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.continues.api.ContinueApiManager;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.res.infusion.CustomOnOffView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

import static com.dhcc.module.infusion.workarea.puncture.PunctureFragment.STR_WAY_NO;

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
    private String scanInfo1;
    private CustomSelectView customSelectTime;
    private CustomOnOffView customOnOff;
    private CustomSelectView customSelectChannel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        csvScan = mContainerChild.findViewById(R.id.custom_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        csvSpeed = mContainerChild.findViewById(R.id.csv_speed);
        customSelectTime = mContainerChild.findViewById(R.id.custom_select_time);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        csvScan.setTitle("请扫描瓶签/信息卡").setWarning("请您使用扫码设备，扫描药品瓶签/信息卡");
        customSelectChannel = mContainerChild.findViewById(R.id.custom_select_channel);
        customOnOff = mContainerChild.findViewById(R.id.custom_on_off);
        customOnOff.setSelect(false);
        customOnOff.setShowSelectText("开启新通道", "开启新通道");
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

    private void getOrdList(final String scanInfo) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        ContinueApiManager.getChangeOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<ContinueBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(ContinueBean bean, String type) {
                if (checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
//                    return;
                }
                // 第一次扫码
                mBean = bean;
                //两次验证
                auditOrdInfo(bean.getOrdList(), bean.getCurRegNo(), bean.getCurOeoreId());
                if (f(R.id.rl_way) != null) {
                    f(R.id.rl_way).setVisibility(bean.getWayListString().size() > 0 ? View.VISIBLE : View.GONE);
                }
                customSelectChannel.setSelectData(mContext, bean.getWayListString(), null);
                customOnOff.setDisEnable(!bean.getIsCurrentWayNo());
                customSelectTime.setTitle("预计结束时间");
                customSelectTime.setSelectTime(ContinueFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
                csvScan.setVisibility(View.GONE);
                setCustomPatViewData(cpvPat, bean.getPatInfo());
                csvSpeed.setSpeed(bean.getDefautSpeed());
                commDosingAdapter.setCurrentScanInfo(scanInfo);
                commDosingAdapter.replaceData(bean.getOrdList());

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            String oeoreId = "";
            String distantTime = "";
            if (mBean != null) {
                oeoreId = mBean.getCurOeoreId();
            }
            distantTime = customSelectTime.getSelect();
            if (TextUtils.isEmpty(distantTime)) {
                distantTime = mBean.getDistantDate() + " " + mBean.getDistantTime();
            }
            int speed = csvSpeed.getSpeed();
            if (speed <= 0) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            //通道
            String wayNo = customSelectChannel.getSelect().replace(STR_WAY_NO, "");
            String newWayFlag = customOnOff.isSelect() ? "1" : "";
            ContinueApiManager.changeOrd(oeoreId, distantTime, speed + "", "", wayNo, newWayFlag, new CommonCallBack<CommResult>() {
                @Override
                public void onFail(String code, String msg) {
                    onFailThings();
                }

                @Override
                public void onSuccess(CommResult bean, String type) {
                    if (scanInfo != null) {
                        getOrdList(scanInfo);
                    }
                    DialogFactory.showCommDialog(getActivity(), "续液成功", "", 0, null, true);
                    onSuccessThings();
                }
            });
        }
    }
}
