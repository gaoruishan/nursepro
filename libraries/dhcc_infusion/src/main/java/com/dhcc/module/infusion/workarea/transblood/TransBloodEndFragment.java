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
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionReasonListBean;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionStateListBean;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomVitalView;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 输血模块-结束
 * @author:gaoruishan
 * @date:202020-02-24/20:19
 * @email:grs0515@163.com
 */
public class TransBloodEndFragment extends BaseTransBloodFragment {

    private CustomSelectView customStatus;
    private CustomSelectView customReason;

    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
        scanView.addPatScan();
    }

    @Override
    protected void initDatas() {
        super.initDatas();
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_E;
        scanInfusionList();
    }

    @Override
    protected void setCommBloodListData(TransBloodListBean bean) {
        super.setCommBloodListData(bean);
        //输血反应
        setInfusionEndStateData(bean);
        //不可编辑
        CustomVitalView customVitalView = f(R.id.custom_vital, CustomVitalView.class);
        if (customVitalView != null) {
            customVitalView.setViewEnable(false);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            endTransfusion();
        }
    }

    private void endTransfusion() {
        params.infusionType = "2";
        params.bloodbagId = bagCode;
        params.bloodProductId = productCode;
        //输血结果
        if (customStatus != null) {
            if (TextUtils.isEmpty(customStatus.getSelect()) && customStatus.getVisibility() == View.VISIBLE) {
                ToastUtils.showShort("请选择输血结果");
                return;
            }
            params.endType = customStatus.getSelect();
        }
        if (customReason != null && customStatus.getVisibility() == View.VISIBLE) {
            params.stopReasonDesc = customReason.getSelect();
        }

        String txt = "当前执行人: " + SPUtils.getInstance().getString(SharedPreference.USERNAME)
                + "(" + SPUtils.getInstance().getString(SharedPreference.USERCODE) + "), 你确定要结束?";
//        DialogFactory.showCommOkCancelDialog(mContext, "输注结束", txt, "取消", "确定", null, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransBloodApiManager.endTransfusion(params, onCommSuccess());
//            }
//        });
        DialogFactory.showSkinDialog(mContext, "输注核对", txt, "取消", "确定", true, null, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                super.data(args);
                params.auditUserId = (String) args[0];
                params.auditPassword = (String) args[1];
                TransBloodApiManager.endTransfusion(params, onCommSuccess());
            }
        });
    }

    private void setInfusionEndStateData(final TransBloodListBean bean) {
        customStatus = f(R.id.custom_select_status, CustomSelectView.class);
        customStatus.setVisibility(bean.getInfusionEndStateList() != null ? View.VISIBLE : View.GONE);
        if (bean.getInfusionEndStateList() != null) {
            List<String> list = new ArrayList<>();
            for (InfusionStateListBean b : bean.getInfusionEndStateList()) {
                list.add(b.getInfusionState());
            }
            customStatus.setTitle("输血结果").setSelectData(getActivity(), list, new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                    String infusionStateFlag = bean.getInfusionEndStateList().get(index).getInfusionStateFlag();
                    helper.setVisible(R.id.custom_select_reason, !TextUtils.isEmpty(infusionStateFlag));
                    if (!TextUtils.isEmpty(infusionStateFlag)) {
                        if (bean.getInfusionEndReasonList() != null) {
                            List<String> list = new ArrayList<>();
                            for (InfusionReasonListBean b : bean.getInfusionEndReasonList()) {
                                list.add(b.getInfusionReason());
                            }
                            customReason = f(R.id.custom_select_reason, CustomSelectView.class);
                            customReason.setTitle("暂停原因").setSelectData(getActivity(), list, null).setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_finish;
    }
}
