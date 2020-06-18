package com.dhcc.module.infusion.workarea.transblood;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionStateListBean;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.InfusionTypeBean;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;
import com.dhcc.module.infusion.workarea.transblood.bean.VitalSignsBean;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;
import com.dhcc.res.infusion.CustomVitalView;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 输血模块-巡视
 * @author:gaoruishan
 * @date:202020-02-24/20:19
 * @email:grs0515@163.com
 */
public class TransBloodTourFragment extends BaseTransBloodFragment {

    private CustomSelectView customStatus;
    private CustomSelectView customType;

    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
        RecyclerView recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_tour);
        tourListAdapter = AdapterFactory.getTransBloodTourListAdapter();
        recyclerView.setAdapter(tourListAdapter);
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_X;
        scanReceiveList();
    }
    @Override
    protected void setCommBloodListData(TransBloodListBean bean) {
        super.setCommBloodListData(bean);
        // 滴速
        setSpeedTimeData(bean);
        //输血反应
        setInfusionStateData(bean);
        //巡视
        if (bean.getTourList() != null) {
            tourListAdapter.setNewData(bean.getTourList());
        }

    }

    @Override
    protected void setCustomVitalData(VitalSignsBean vitalSigns) {
        super.setCustomVitalData(vitalSigns);
        //巡视必须有生命体征
        f(R.id.custom_vital, CustomVitalView.class).setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            bloodPatrol();
        }
    }

    private void bloodPatrol() {
        params.bloodbagId = bagCode;
        params.bloodProductId = productCode;
        if (customSpeed == null || customSpeed.getSpeed() <= 0) {
            ToastUtils.showShort("请调节滴速");
            return;
        }
        params.ifSpeed = customSpeed.getSpeed() + "";
        params.tourType = customType.getSelect();
        params.effect = customStatus.getSelect();
        params.situation = helper.getTextData(R.id.ed_measure);
        //体温 脉搏 舒张压 收缩压
        CustomVitalView customVitalView = f(R.id.custom_vital, CustomVitalView.class);
        if (customVitalView != null) {
            params.temperature = customVitalView.getTemp();
            params.pulse = customVitalView.getPulse();
            params.sysPressure = customVitalView.getSys();
            params.diaPressure = customVitalView.getDia();
        }
        TransBloodApiManager.bloodPatrol(params, onCommSuccess());
    }

    /**
     * 滴速/预计结束时间
     * @param bean
     */
    protected void setSpeedTimeData(TransBloodListBean bean) {
        customSpeed = f(R.id.custom_speed, CustomSpeedView.class);
        if (customSpeed != null) {
            customSpeed.setSpeed(bean.getDefaultSpeed());
        }
        customType = f(R.id.custom_select_time, CustomSelectView.class);
        if (customType != null) {
            customType.setTitle("巡视类型");
            customType.setVisibility(bean.getTourTypeList() != null ? View.VISIBLE : View.GONE);
            if (bean.getTourTypeList() != null) {
                List<String> list = new ArrayList<>();
                for (InfusionTypeBean b : bean.getTourTypeList()) {
                    list.add(b.getInfusionType());
                }
                customType.setSelectData(mContext, list, null);
            }
        }
    }

    private void setInfusionStateData(final TransBloodListBean bean) {
        if (bean.getInfusionStateList() != null) {
            List<String> list = new ArrayList<>();
            for (InfusionStateListBean b : bean.getInfusionStateList()) {
                list.add(b.getInfusionState());
            }
            customStatus = f(R.id.custom_select_status, CustomSelectView.class);
            customStatus.setTitle("输血反应").setSelectData(getActivity(), list, new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                    String infusionStateFlag = bean.getInfusionStateList().get(index).getInfusionStateFlag();
                    helper.setVisible(R.id.ll_measure, "1".equals(infusionStateFlag));
                    if (TextUtils.isEmpty(infusionStateFlag)) {
                        helper.setTextData(R.id.ed_measure, "");
                    }
                }
            });
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_tour;
    }
}
