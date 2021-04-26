package com.dhcc.module.infusion.workarea.transblood;

import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.BaseTransBloodFragment;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;

/**
 * 扫码签收
 * @author:gaoruishan
 * @date:202020-03-27/16:54
 * @email:grs0515@163.com
 */
public class TransBloodScanFragment extends BaseTransBloodFragment {

    @Override
    protected void getScanTransBloodList() {
        scanReceiveList();
    }

    @Override
    protected void getTransBloodList() {
        //保存一下血制品码
        bagCode = params.bloodbagId;
        productCode = params.bloodProductId;
        TransBloodApiManager.getTransBloodList(params, new CommonCallBack<TransBloodListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(TransBloodListBean bean, String type) {
                onSuccessThings(bean);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_trans_blood_receive;
    }
}
