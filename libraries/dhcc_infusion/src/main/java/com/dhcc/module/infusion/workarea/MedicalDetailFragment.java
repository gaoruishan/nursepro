package com.dhcc.module.infusion.workarea;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.res.infusion.CustomOrdStateView;
import com.dhcc.module.infusion.workarea.comm.adapter.DetailLogAdapter;
import com.dhcc.module.infusion.workarea.comm.api.WorkAreaApiManager;
import com.dhcc.module.infusion.workarea.comm.bean.OrdInfoBean;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 医嘱详情
 * @author:gaoruishan
 * @date:202019-04-30/11:42
 * @email:grs0515@163.com
 */
public class MedicalDetailFragment extends BaseFragment {
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
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medical_detail, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("医嘱详情");
        Bundle bundle = getArguments();
        if (bundle != null) {
            OeoreId = bundle.getString("id");
        }
        helper = new BaseHelper(this.getActivity());
        cosvState = mContainerChild.findViewById(R.id.cosv_state);
        rvOrdList = RecyclerViewHelper.get(this.getActivity(), R.id.rv_ord_list);
        RecyclerView rvStepLog = RecyclerViewHelper.get(this.getActivity(), R.id.rv_step_log);
        detailLogAdapter = AdapterFactory.getDetailLogAdapter();
        rvStepLog.setAdapter(detailLogAdapter);
        WorkAreaApiManager.getOrdInfo(OeoreId, new CommonCallBack<OrdInfoBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
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

    private List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> getGroupOrdWork(List<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean> ordWorkList) {
        Set<String> attrIdSet = new HashSet<>();
        for (OrdInfoBean.OrdInfoArrBean.OrdWorkListBean bean : ordWorkList) {
            attrIdSet.add(bean.getWorkCode());
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
