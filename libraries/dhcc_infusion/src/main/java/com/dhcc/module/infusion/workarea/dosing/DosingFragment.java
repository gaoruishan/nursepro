package com.dhcc.module.infusion.workarea.dosing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.module.infusion.workarea.dosing.api.DosingApiManager;
import com.dhcc.module.infusion.workarea.dosing.bean.DosingBean;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 配液
 * @author:gaoruishan
 * @date:202019-04-24/14:13
 * @email:grs0515@163.com
 */
public class DosingFragment extends BaseInfusionFragment implements View.OnClickListener {

    private DosingApiManager manager;
    private RecyclerView rvDosing;
    private CommDosingAdapter commDosingAdapter;
    private View tvOk;
    private String reqType;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("配液");
//        addPatListToToolbarRight();
        tvOk = f(R.id.tv_ok);
        tvOk.setOnClickListener(this);
//        f(R.id.tv_ok_all).setOnClickListener(this);
        rvDosing = RecyclerViewHelper.get(this.getActivity(), R.id.rv_dosing);
        commDosingAdapter = AdapterFactory.getCommDosingOrdList();
        rvDosing.setAdapter(commDosingAdapter);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList(scanInfo);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_dosing;
    }

    private void getOrdList(String oeoreId, final boolean... refresh) {
        scanInfo = oeoreId;
        DosingApiManager.getOrdList("", oeoreId, new CommonCallBack<DosingBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(DosingBean bean, String type) {
                mContainerChild.findViewById(R.id.csv).setVisibility(View.GONE);
//                tvOk.setVisibility(View.VISIBLE);
                commDosingAdapter.replaceData(bean.getOrdList());
                // 当前
                commDosingAdapter.setCurrentScanInfo(scanInfo);
                boolean equals = DosingBean.OrdState_1.equals(bean.getOrdState());
                // 隐藏复核输入
//                mContainerChild.findViewById(R.id.ll_review).setVisibility(equals ? View.GONE : View.VISIBLE);
                reqType = equals ? DosingBean.Despensing : DosingBean.Audit;
                //校验-true隐藏'确认'按钮
                if (!checkListOeoreId(bean.getOrdList(), PROMPT_NO_ORD)) {
//                    return;
                    scanDespensingOrd(bean, refresh);
                }
            }
        });
    }

    private void scanDespensingOrd(DosingBean bean, boolean... refresh) {
        if (refresh != null && refresh.length > 0 && refresh[0]) {
            // 扫码执行,刷新列表不再执行
            return;
        }
        if (DosingBean.All.equals(bean.getScanFlag())) {
            despensingOrdAll(bean);
        }
        if (DosingBean.Single.equals(bean.getScanFlag())) {
            despensingOrd();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            despensingOrd();
        }
    }

    private void despensingOrdAll(DosingBean bean) {
        if (bean.getOrdList() == null) {
            return;
        }
        final List<String> listIdOnDos = new ArrayList<>();
        final List<String> listIdUnDos = new ArrayList<>();
        for (OrdListBean b : bean.getOrdList()) {
            // 不是已复核
            if (!DosingBean.OrdState_3.equals(b.getOrdState())) {
                if (DosingBean.OrdState_1.equals(b.getOrdState())) {
                    listIdUnDos.add(b.getOeoreId());
                }
                if (DosingBean.OrdState_2.equals(b.getOrdState())) {
                    listIdOnDos.add(b.getOeoreId());
                }
            }
        }
        final ArrayList<String> temp = new ArrayList<>();
        final boolean[] isOnce = {false};
        if (listIdUnDos.size() > 0) {
            reqType = DosingBean.Despensing;
            for (int i = 0; i < listIdUnDos.size(); i++) {
                final String scanInfo = listIdUnDos.get(i);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        postDelay(isOnce, temp, scanInfo, listIdUnDos.size());
                    }
                }, 300);
            }
        } else if (listIdOnDos.size() > 0) {
            reqType = DosingBean.Audit;
            for (int i = 0; i < listIdOnDos.size(); i++) {
                final String scanInfo = listIdOnDos.get(i);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        postDelay(isOnce, temp, scanInfo, listIdOnDos.size());
                    }
                }, 300);
            }
        } else {
            ToastUtils.showShort("已全部复核");
        }
    }

    private void postDelay(final boolean[] isOnce, final List<String> temp, final String scanInfo, final int size) {
        DosingApiManager.despensingOrd(scanInfo, reqType, "", "", new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                if (!isOnce[0]) {
                    isOnce[0] = true;
                    onFailThings();
                    DialogFactory.showCommDialog(getActivity(), msg, "确定", R.drawable.icon_popup_error_patient, null);
                }
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                temp.add(type);
                if (temp.size() == size) {
                    getOrdList(scanInfo, true);
                    onSuccessThings();
                    DialogFactory.showCommDialog(getActivity(), "操作成功", "", 0, null, true);
                }
            }
        });
    }

    private void despensingOrd() {
        /*
        String passWord = ((EditText) mContainerChild.findViewById(R.id.pass_word)).getText().toString();
        String userCode = ((EditText) mContainerChild.findViewById(R.id.user_code)).getText().toString();
        // 复核
        if (DosingApiManager.Audit.equals(reqType) && TextUtils.isEmpty(passWord) && TextUtils.isEmpty(userCode)) {
            DialogFactory.showCommDialog(getActivity(), "未输入复核信息，请复核", "确定", R.drawable.icon_popup_error_repeat, null);
            return;
        }
        */
        DosingApiManager.despensingOrd(scanInfo, reqType, "", "", new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
                DialogFactory.showCommDialog(getActivity(), msg, "确定", R.drawable.icon_popup_error_patient, null);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                String successInfo = "配液成功";
                if (DosingBean.Audit.equals(reqType)) {
                    successInfo = "复核成功";
//                    f(R.id.pass_word, EditText.class).setText("");
//                    f(R.id.user_code, EditText.class).setText("");
//                    f(R.id.csv).setVisibility(View.VISIBLE);
                }
                // 刷新
                getOrdList(scanInfo, true);
                DialogFactory.showCommDialog(getActivity(), successInfo, "", 0, null, true);
                onSuccessThings();
            }
        });
    }
}
