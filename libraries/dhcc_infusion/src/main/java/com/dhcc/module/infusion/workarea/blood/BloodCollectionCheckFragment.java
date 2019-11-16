package com.dhcc.module.infusion.workarea.blood;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.dhcc.module.infusion.utils.DialogFactory;

/**
 * 采血核对
 * @author:gaoruishan
 * @date:202019-11-15/16:40
 * @email:grs0515@163.com
 */
public class BloodCollectionCheckFragment extends BloodCollectionFragment {
    @Override
    protected void initDatas() {
        super.initDatas();

    }

    /**
     * 只需要重写-执行方法
     */
    @Override
    protected void exeLabOrd() {
        BloodCollectApiManager.auditOrd(scanInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings();
                DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
                getLabOrdList();
            }
        });
    }
}
