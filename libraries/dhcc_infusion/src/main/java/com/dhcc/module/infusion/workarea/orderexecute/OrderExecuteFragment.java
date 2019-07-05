package com.dhcc.module.infusion.workarea.orderexecute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.orderexecute.api.OrderExecuteApiManager;

/**
 * @author:gaoruishan
 * @date:202019-07-04/10:23
 * @email:grs0515@163.com
 */
public class OrderExecuteFragment extends BaseInfusionFragment {

    private String episodeId;
    private String regNo;
    private String patInfo;
    private String patSaveInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("医嘱执行");
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList();
        }
    }

    private void getOrdList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OrderExecuteApiManager.getScanMsg("", scanInfo, new CommonCallBack<OrdExecuteBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(OrdExecuteBean bean, String type) {
                //PAT 扫腕带返回患者信息
                //ORD 扫医嘱条码返回医嘱信息
                if ("PAT".equals(bean.getFlag())) {
                    f(R.id.rl_orderexecute_scan).setVisibility(View.GONE);
                    OrdExecuteBean.PatInfoBean patInfoBean = bean.getPatInfo();
                    episodeId = patInfoBean.getEpisodeID();
                    regNo = patInfoBean.getRegNo();
                    f(R.id.tv_orderexecute_patinfo, TextView.class).setText("".equals(patInfoBean.getBedCode()) ? "未分床  " + patInfoBean.getName() : patInfoBean.getBedCode() + "  " + patInfoBean.getName());
                    patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();
                    patSaveInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName();
                    asyncInitData();
                }
            }

        });
    }

    private void asyncInitData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.dhcc_fragment_order_execute;
    }
}
