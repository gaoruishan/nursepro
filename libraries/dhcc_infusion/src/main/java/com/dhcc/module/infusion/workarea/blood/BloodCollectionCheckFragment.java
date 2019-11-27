package com.dhcc.module.infusion.workarea.blood;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.UserUtil;
import com.dhcc.module.infusion.utils.DialogFactory;

/**
 * 采血核对
 * @author:gaoruishan
 * @date:202019-11-15/16:40
 * @email:grs0515@163.com
 */
public class BloodCollectionCheckFragment extends BloodCollectionFragment {


    /**
     * 只需要重写-执行方法
     */
    @Override
    protected void exeLabOrd() {
        if (UserUtil.isBloodCheckFlag()) {
            DialogFactory.showSkinDialog(mContext, "采血复核",  "", "取消", "确定", true,null, new CommDialog.CommClickListener() {
                @Override
                public void data(Object[] args) {
                    super.data(args);
                    String user = (String) args[0];
                    String psw = (String) args[1];
                    auditOrd(user,psw);
                }
            });
        }else {
            auditOrd(null, null);
        }
    }

    private void auditOrd(String user, String psw) {
        BloodCollectApiManager.auditOrd(scanInfo,user,psw, new CommonCallBack<CommResult>() {
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
