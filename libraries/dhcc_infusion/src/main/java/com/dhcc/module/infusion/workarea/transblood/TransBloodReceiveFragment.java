package com.dhcc.module.infusion.workarea.transblood;

import android.view.View;

import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.OrdState;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;

/**
 * 输血模块-签收
 * @author:gaoruishan
 * @date:202020-02-24/20:19
 * @email:grs0515@163.com
 */
public class TransBloodReceiveFragment extends BaseTransBloodFragment {


    @Override
    protected void initViews() {
        super.initViews();
        initBloodListAdapter();
    }

    @Override
    protected void getScanTransBloodList() {
        params.bloodType = OrdState.TYPE_BLOOD_R;
        scanReceiveList();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            params.bloodbagId = bagCode;
            params.bloodProductId = productCode;
            TransBloodApiManager.bloodReceive(params, onCommSuccess());
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_receive;
    }
}
