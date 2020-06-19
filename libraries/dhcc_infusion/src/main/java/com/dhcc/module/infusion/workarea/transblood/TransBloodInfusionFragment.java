package com.dhcc.module.infusion.workarea.transblood;

import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.CommDialog;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.puncture.bean.PunturePartListBean;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;

import java.util.ArrayList;
import java.util.List;

/**
 * 输血模块-输注
 * @author:gaoruishan
 * @date:202020-02-24/20:19
 * @email:grs0515@163.com
 */
public class TransBloodInfusionFragment extends BaseTransBloodFragment {

    private CustomSelectView customParts;

    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
        scanView.addPatScan();
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_S;
        scanInfusionList();
    }


    @Override
    protected void setCommBloodListData(TransBloodListBean bean) {
        super.setCommBloodListData(bean);
        //穿刺部位
        setPunturePartsData(bean);
        //滴速/预计结束时间
        setSpeedTimeData(bean);

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
        customTime = f(R.id.custom_select_time, CustomSelectView.class);
        if (customTime != null) {
            customTime.setTitle("预计结束时间");
            customTime.setVisibility(!TextUtils.isEmpty(bean.getDistantDate()) ? View.VISIBLE : View.GONE);
            customTime.setSelectTime(TransBloodInfusionFragment.this.getFragmentManager(), bean.getDistantDate(), bean.getDistantTime(), null);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            startTransfusion();
        }
    }

    private void startTransfusion() {
        params.clearAll();
        params.infusionType = "2";
        params.bloodbagId = bagCode;
        params.bloodProductId = productCode;
        if (customParts != null) {
            if (TextUtils.isEmpty(customParts.getSelect()) && customParts.getVisibility() == View.VISIBLE) {
                ToastUtils.showShort("请选择穿刺部位");
                return;
            }
            params.puncturePart = customParts.getSelect();
        }
        if (customSpeed != null) {
            if (customSpeed.getSpeed() <= 0 && customParts.getVisibility() == View.VISIBLE) {
                ToastUtils.showShort("请调节滴速");
                return;
            }
            params.ifSpeed = customSpeed.getSpeed() + "";
        }
        params.distantTime = customTime.getSelect();
        String txt = "当前执行人: " + SPUtils.getInstance().getString(SharedPreference.USERNAME)
                + "(" + SPUtils.getInstance().getString(SharedPreference.USERCODE) + ")";
        DialogFactory.showSkinDialog(mContext, "输注核对", txt, "取消", "确定", true, null, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                super.data(args);
                params.auditUserId = (String) args[0];
                params.auditPassword = (String) args[1];
                TransBloodApiManager.startTransfusion(params, onCommSuccess());
            }
        });
    }

    private void setPunturePartsData(TransBloodListBean bean) {
        customParts = f(R.id.custom_parts, CustomSelectView.class);
        if (customParts != null) {
            customParts.setTitle("穿刺部位");
            customParts.setVisibility(bean.getPunturePartList() != null ? View.VISIBLE : View.GONE);
            if (bean.getPunturePartList() != null) {
                List<String> list = new ArrayList<>();
                for (PunturePartListBean listBean : bean.getPunturePartList()) {
                    list.add(listBean.getPunturePart());
                }
                customParts.setSelectData(mContext, list, null);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_infusion;
    }
}
