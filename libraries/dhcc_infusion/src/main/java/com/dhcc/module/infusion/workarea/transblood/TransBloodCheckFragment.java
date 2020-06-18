package com.dhcc.module.infusion.workarea.transblood;

import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.CommDialog;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;
import com.dhcc.res.infusion.CustomVitalView;

/**
 * 输血复核
 * @author:gaoruishan
 * @date:202020-04-09/10:48
 * @email:grs0515@163.com
 */
public class TransBloodCheckFragment extends BaseTransBloodFragment {

    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_C;
        scanReceiveList();
    }


    @Override
    protected void setCommBloodListData(TransBloodListBean bean) {
        super.setCommBloodListData(bean);
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
            bloodCheck();
        }
    }

    protected void bloodCheck() {
        params.bloodbagId = bagCode;
        params.bloodProductId = productCode;
        String txt = "当前执行人: " + SPUtils.getInstance().getString(SharedPreference.USERNAME)
                + "(" + SPUtils.getInstance().getString(SharedPreference.USERCODE) + ")";
        String flag = "";
        if (mBean != null) {
            flag = mBean.getReceiveDialogFlag();
        }
        if ("0".equals(flag)) {
            TransBloodApiManager.bloodCheck(params, onCommSuccess());
        } else {
            DialogFactory.showSkinDialog(mContext, "签收核对", txt, "取消", "确定", true, null, new CommDialog.CommClickListener() {
                @Override
                public void data(Object[] args) {
                    super.data(args);
                    params.auditUserId = (String) args[0];
                    params.auditPassword = (String) args[1];
                    TransBloodApiManager.bloodCheck(params, onCommSuccess());
                }
            });
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_receive;
    }
}
