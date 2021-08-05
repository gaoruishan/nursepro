package com.dhcc.module.infusion.workarea.needles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.bean.CommInfusionBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;
import com.dhcc.module.infusion.workarea.needles.api.NeedlesApiManager;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanView;

import java.util.List;

/**
 * 拔针
 * @author:gaoruishan
 * @date:202019-04-29/11:34
 * @email:grs0515@163.com
 */
public class NeedlesFragment extends BaseInfusionFragment implements View.OnClickListener {
    private CustomScanView csvScan;
    private CustomPatView cpvPat;
    private PatrolOrdListAdapter commPatrolAdapter;
    private NeedlesBean mBean;
    private RecyclerView rvOrdList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        csvScan = mContainerChild.findViewById(R.id.custom_scan);
        cpvPat = mContainerChild.findViewById(R.id.cpv_pat);
        mContainerChild.findViewById(R.id.tv_ok).setOnClickListener(this);
        setInitWayNo("关闭所有通道");
        showScanLabel();
        rvOrdList = RecyclerViewHelper.get(mContext, R.id.rv_ord_list);
        commPatrolAdapter = AdapterFactory.getCommPatrolOrdList();
        rvOrdList.setAdapter(commPatrolAdapter);
    }

    @Override
    protected void getScanOrdList() {

        getOrdList(scanInfo);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_needles;
    }

    private void getOrdList(final String scanInfo, final boolean... refresh) {
        String regNo = "";
        String curOeoreId = "";
        if (mBean != null) {
            regNo = mBean.getCurRegNo();
            curOeoreId = mBean.getCurOeoreId();
        }
        NeedlesApiManager.getFinishOrdList(regNo, curOeoreId, scanInfo, new CommonCallBack<NeedlesBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(NeedlesBean bean, String type) {
                //检查扫码不包含提示
                checkListOeoreId(bean, PROMPT_NO_ORD);
                mBean = bean;
                csvScan.setVisibility(View.GONE);
                setCustomPatViewData(cpvPat, bean.getPatInfo());
                commPatrolAdapter.replaceData(bean.getOrdList());
                commPatrolAdapter.setCurrentScanInfo(scanInfo);
                scrollToPosition(rvOrdList,bean.getOrdList());
                f(R.id.rl_way).setVisibility(bean.getWayListString().size() > 0 ? View.VISIBLE : View.GONE);
                customSelectChannel.setSelectData(mContext, bean.getWayListString(), null);
                //两次扫码验证
                boolean isOK = auditOrdInfo(bean.getOrdList(), bean.getCurRegNo(), bean.getCurOeoreId());
                if (isOK) {
                    if (refresh != null && refresh.length > 0 && refresh[0]) {
                        // 扫码执行,刷新列表不再执行
                        return;
                    }
                    //不是输液中
                    if (!bean.getCurrentOrdState(OrdState.STATE_3)) {
                        CommDialog.showShort(STR_ORD_NO_END);
                        onFailThings(STR_ORD_NO_END);
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
        if (mBean != null) {
            oeoreId = mBean.getCurOeoreId();
        }
        //通道
        String wayNo = customSelectChannel.getSelect().replace(STR_WAY_NO, "");
        String finishWayFlag = customOnOff.isSelect() ? "1" : "";

        //拔针弹框
        List<OrdListBean> data = commPatrolAdapter.getData();
        boolean hasNoOrder = false;
        for (OrdListBean bean : data) {
            hasNoOrder = OrdState.checkFinish( bean.getOrdState());
            if (hasNoOrder) break;
        }
        if (hasNoOrder && "1".equals(mBean.getLastIfFlag())) {
            String finalOeoreId = oeoreId;
            DialogFactory.showCommOkCancelDialog(getActivity(), "提示", "有'未完成'的输液,是否拔针?", "取消", "确定", null, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    extractOrd(finalOeoreId,wayNo,finishWayFlag);
                }
            });
        } else {
            extractOrd(oeoreId,wayNo,finishWayFlag);
        }
    }


    private void extractOrd(String oeoreId, String wayNo, String finishWayFlag) {
        NeedlesApiManager.extractOrd(oeoreId, wayNo, finishWayFlag, new CommonCallBack<CommResult>() {
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
