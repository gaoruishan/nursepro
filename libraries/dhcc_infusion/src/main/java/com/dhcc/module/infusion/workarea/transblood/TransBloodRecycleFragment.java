package com.dhcc.module.infusion.workarea.transblood;

import android.text.TextUtils;

import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;

/**
 * 输血模块-回收
 * @author:gaoruishan
 * @date:202020-02-24/20:19
 * @email:grs0515@163.com
 */
public class TransBloodRecycleFragment extends BaseTransBloodFragment {

    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_RE;
        scanReceiveList();
    }

    @Override
    protected void setCommBloodListData(TransBloodListBean bean) {
        super.setCommBloodListData(bean);
        //二次扫码 执行回收
        if (!TextUtils.isEmpty(params.bloodbagId)) {
            bloodRecycle();
        }
    }

    private void bloodRecycle() {
        params.bloodbagId = bagCode;
        TransBloodApiManager.recycleBloodbag(params, onCommSuccess());
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_recycle;
    }
}
