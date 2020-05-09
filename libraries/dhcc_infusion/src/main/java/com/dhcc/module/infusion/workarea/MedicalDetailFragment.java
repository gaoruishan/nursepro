package com.dhcc.module.infusion.workarea;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.comm.adapter.DetailLogAdapter;
import com.dhcc.module.infusion.workarea.comm.api.WorkAreaApiManager;
import com.dhcc.module.infusion.workarea.comm.bean.OrdInfoBean;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;
import com.dhcc.res.infusion.CustomOrdStateView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 医嘱详情
 * @author:gaoruishan
 * @date:202019-04-30/11:42
 * @email:grs0515@163.com
 */
public class MedicalDetailFragment extends BaseInfusionFragment {
    public final static String STATE_1 = "未配液";
    public final static String[] STATE_2 = {"已配液", "已复核"};
    public final static String STATE_3 = "输液中";
    public final static String[] STATE_4 = {"已完成", "已输完"};
    public final static String[] STATE_5 = {"异常结束", "异常"};
    private CustomOrdStateView cosvState;
    private String OeoreId;
    private DetailLogAdapter detailLogAdapter;
    private BaseHelper helper;
    private RecyclerView rvOrdList;
    @Override
    protected void addHandInputToToolbarRight() {
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_medical_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("医嘱详情");
        Bundle bundle = getArguments();
        if (bundle != null) {
            OeoreId = bundle.getString("id");
        }
        helper = new BaseHelper(this.getActivity());
        cosvState = mContainerChild.findViewById(R.id.cosv_state);
        rvOrdList = RecyclerViewHelper.get(mContext, R.id.rv_ord_list);
        RecyclerView rvStepLog = RecyclerViewHelper.get(mContext, R.id.rv_step_log);
        detailLogAdapter = AdapterFactory.getDetailLogAdapter();
        rvStepLog.setAdapter(detailLogAdapter);
        WorkAreaApiManager.getOrdInfo(OeoreId, new CommonCallBack<OrdInfoBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(OrdInfoBean bean, String type) {
                OrdInfoBean.OrdInfoArrBean ordInfoArr = bean.getOrdInfoArr();
                helper.setTextData(R.id.tv_name_person, ordInfoArr.getDoctor());
                helper.setTextData(R.id.tv_name_time, ordInfoArr.getCreateDateTime());
                helper.setTextData(R.id.tv_name_use, ordInfoArr.getPhcinDesc());
                helper.setTextData(R.id.tv_name_rate, ordInfoArr.getPhcfrCode());
                helper.setTextData(R.id.tv_notes_detail, ordInfoArr.getNotes());
                helper.setVisible(R.id.ll_notes_detail, !TextUtils.isEmpty(ordInfoArr.getNotes()));
                rvOrdList.setAdapter(new CommQuickAdapter.ChildAdapter(R.layout.item_posing_child2, ordInfoArr.getOeoreGroup(), false));

                List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> groupOrdWork = getGroupOrdWork(ordInfoArr.getOrdWorkList());
                detailLogAdapter.replaceData(groupOrdWork);

                if (STATE_1.equals(ordInfoArr.getOeoreState())) {
                    cosvState.setBgColor(R.color.screen_bg_color).setTextName(ordInfoArr.getOeoreState())
                            .setImageIcon(R.drawable.ic_ord_state_undosing);
                }
                if (Arrays.asList(STATE_2).contains(ordInfoArr.getOeoreState())) {
                    cosvState.setBgColor(R.color.bg_blue_light).setTextName(ordInfoArr.getOeoreState())
                            .setImageIcon(R.drawable.ic_ord_state_dosing);
                }
                if (STATE_3.equals(ordInfoArr.getOeoreState())) {
                    long time = 0;
                    String overTime = ordInfoArr.getOverTime();
                    if (!TextUtils.isEmpty(overTime)) {
                        //12 * 60 * 60 * 1000L
                        if (overTime.contains(":")) {
                            String[] arr = overTime.split(":");
                            time = Integer.valueOf(arr[0]) * 60 * 60 * 1000L + Integer.valueOf(arr[1]) * 60 * 1000L;
                        } else {
                            time += Integer.valueOf(overTime) * 60 * 1000L;
                        }
                    }
                    cosvState.setBgColor(R.color.bg_blue_light).setTextName(ordInfoArr.getOeoreState())
                            .setImageIcon(R.drawable.ic_ord_state_infusioning);
                    if (time > 0) {
                        cosvState.setCountStart(time);
                    }
                }
                if (Arrays.asList(STATE_4).contains(ordInfoArr.getOeoreState())) {
                    cosvState.setBgColor(R.color.bg_blue_light).setTextName(ordInfoArr.getOeoreState())
                            .setImageIcon(R.drawable.ic_ord_state_infusioned);
                }
                if (Arrays.asList(STATE_5).contains(ordInfoArr.getOeoreState())) {
                    cosvState.setBgColor(R.color.bg_err).setTextName(ordInfoArr.getOeoreState())
                            .setTextColor(R.color.ic_ord_state_unexpect)
                            .setImageIcon(R.drawable.ic_ord_state_unexpect);
                }
            }
        });
    }

    @Override
    protected void getScanOrdList() {

    }

    private List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> getGroupOrdWork(List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> ordWorkList) {
        List<String> attrIdSet = new ArrayList<>();
        for (OrdInfoBean.OrdInfoArrBean.OrdWorkListBean bean : ordWorkList) {
            if (!attrIdSet.contains(bean.getWorkCode())) {
                attrIdSet.add(bean.getWorkCode());
            }
        }
        List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> allOrdWorkList = new ArrayList<>();
        for (String code : attrIdSet) {
            OrdInfoBean.OrdInfoArrBean.OrdWorkListBean ordWorkListBean = new OrdInfoBean.OrdInfoArrBean.OrdWorkListBean();
            List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> childOrdWorkList = new ArrayList<>();
            for (OrdInfoBean.OrdInfoArrBean.OrdWorkListBean bean : ordWorkList) {
                if (code.equals(bean.getWorkCode())) {
                    childOrdWorkList.add(bean);
                }
            }
            ordWorkListBean.setDateTime(childOrdWorkList.get(0).getDateTime());
            ordWorkListBean.setWorkCode(childOrdWorkList.get(0).getWorkCode());
            ordWorkListBean.setWorkType(childOrdWorkList.get(0).getWorkType());
            ordWorkListBean.setWorkUser(childOrdWorkList.get(0).getWorkUser());
            ordWorkListBean.setChildOrdWorkListBean(childOrdWorkList);
            allOrdWorkList.add(ordWorkListBean);
        }
        return allOrdWorkList;
    }
}
