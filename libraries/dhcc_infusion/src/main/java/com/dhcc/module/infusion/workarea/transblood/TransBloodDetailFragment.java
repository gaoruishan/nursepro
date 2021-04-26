package com.dhcc.module.infusion.workarea.transblood;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.BloodInfoBean;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodDetailBean;

/**
 * 输血详情
 * @author:gaoruishan
 * @date:202020-03-09/10:11
 * @email:grs0515@163.com
 */
public class TransBloodDetailFragment extends BaseTransBloodFragment {


    @Override
    protected void initViews() {
        super.initViews();
        RecyclerView recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_tour);
        tourListAdapter = AdapterFactory.getTransBloodTourListAdapter();
        recyclerView.setAdapter(tourListAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_detail;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("输血详情");
        Bundle bundle = getArguments();
        params.bloodRowId = bundle.getString("bloodRowId");
        getTransBloodDetail();
    }

    @Override
    protected void getScanTransBloodList() {

    }

    private void getTransBloodDetail() {
        TransBloodApiManager.getTransBloodDetail(params, new CommonCallBack<TransBloodDetailBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(TransBloodDetailBean detailBean, String type) {
                BloodInfoBean bean = detailBean.getBloodInfo();
                tourListAdapter.setNewData(detailBean.getPatrolRecord());

                boolean showTour = detailBean.getPatrolRecord() != null && detailBean.getPatrolRecord().size() > 0;
                helper.setVisible(R.id.rv_tour, showTour).setVisible(R.id.ll_tourempty,!showTour);

                helper.setTextData(R.id.tv_patinfo, bean.getBedCode() + "-" + bean.getPatName() + "-" + bean.getPatBldTyp());
                helper.setTextData(R.id.tv_bloodinfo, bean.getBloodProducDesc() + "-" + bean.getBldTyp());

                boolean isStop = !TextUtils.isEmpty(bean.getStopReason());
                helper.setVisible(R.id.tv_transenderror_reason, isStop)
                        .setVisible(R.id.ll_transenderror, isStop).setVisible(R.id.ll_transend, !isStop);

                helper.setSelect(R.id.ll_sign, !TextUtils.isEmpty(bean.getReciveDate()))
                        .setTextData(R.id.tv_signdate, bean.getReciveDate()).setTextData(R.id.tv_signtime, bean.getReciveTime())
                        .setTextData(R.id.tv_signnurse, bean.getReciveFirstUser());

                helper.setSelect(R.id.ll_trans, !TextUtils.isEmpty(bean.getTransStartDate()))
                        .setTextData(R.id.tv_transdate, bean.getTransStartDate()).setTextData(R.id.tv_transtime, bean.getTransStartTime())
                        .setTextData(R.id.tv_transnurse1, bean.getTransFirstUser()).setTextData(R.id.tv_transnurse2, bean.getTransSecondUser());

                helper.setSelect(R.id.ll_transend, !TextUtils.isEmpty(bean.getTransEdDate()))
                        .setSelect(R.id.ll_transenderror, !TextUtils.isEmpty(bean.getTransEdDate()));

                if ("E".equals(bean.getEndType())) {
                    helper.setTextData(R.id.tv_transenddate, bean.getTransEdDate()).setTextData(R.id.tv_transendtime, bean.getTransEdTime())
                            .setTextData(R.id.tv_transendnurse, bean.getTransEdUser());
                } else if ("Z".equals(bean.getEndType())) {
                    helper.setTextData(R.id.tv_transenderrordate, bean.getTransEdDate()).setTextData(R.id.tv_transenderrortime, bean.getTransEdTime())
                            .setTextData(R.id.tv_transenderrornurse, bean.getTransEdUser()).setTextData(R.id.tv_transenderror_reason, "终止原因：" + bean.getStopReason());
                }
            }
        });
    }
}
