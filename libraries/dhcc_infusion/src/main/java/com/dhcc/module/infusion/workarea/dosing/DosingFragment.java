package com.dhcc.module.infusion.workarea.dosing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    private String scanInfo;
    private List<String> listId = new ArrayList<>();

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
        String scanInfo = doScanInfo(intent);
        if (scanInfo != null) {
            getOrdList(scanInfo);
        }
    }

    private void getOrdList(String oeoreId) {
        scanInfo = oeoreId;
        DosingApiManager.getOrdList("", oeoreId, new CommonCallBack<DosingBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(DosingBean bean, String type) {
                //检验
                boolean isContain = false;
                String s = "";
                for (OrdListBean b : bean.getOrdList()) {
                    listId.add(b.getOeoreId());
                    s += b.getOeoreId() + ", ";
                    if (b.getOeoreId().equals(scanInfo)) {
                        isContain = true;
                    }
                }
                if (!isContain) {
                    ToastUtils.showShort("瓶贴不匹配,请换一个扫描" );
                    return;
                }
                mContainerChild.findViewById(R.id.csv).setVisibility(View.GONE);
                tvOk.setVisibility(View.VISIBLE);
                commDosingAdapter.replaceData(bean.getOrdList());
                // 当前
                commDosingAdapter.setCurrentScanInfo(scanInfo);
                boolean equals = DosingApiManager.OrdState_1.equals(bean.getOrdState());
                // 隐藏复核输入
//                mContainerChild.findViewById(R.id.ll_review).setVisibility(equals ? View.GONE : View.VISIBLE);
                reqType = equals ? DosingApiManager.Despensing : DosingApiManager.Audit;
            }
        });
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dosing, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            despensingOrd();
        }
        //自动配液
        if (v.getId() == R.id.tv_ok_all) {
            despensingOrdAll();
        }
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
                DialogFactory.showCommDialog(getActivity(), msg, "确定", R.drawable.icon_popup_error_patient, null);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                String successInfo = "配液成功";
                if (DosingApiManager.Audit.equals(reqType)) {
                    successInfo = "复核成功";
                    f(R.id.pass_word, EditText.class).setText("");
                    f(R.id.user_code, EditText.class).setText("");
//                    f(R.id.csv).setVisibility(View.VISIBLE);
                }
                // 刷新
                getOrdList(scanInfo);
                DialogFactory.showCommDialog(getActivity(), successInfo, "", 0, null, true);
            }
        });
    }

    private void despensingOrdAll() {
        final boolean[] isOnce = {false};
        final List<Object> temp = new ArrayList<>();
        for (int i = 0; i < listId.size(); i++) {
            final String scanInfo = listId.get(i);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    postDelay(isOnce, temp, scanInfo);
                }
            }, 300);
        }
    }

    private void postDelay(final boolean[] isOnce, final List<Object> temp, String scanInfo) {
        DosingApiManager.despensingOrd(scanInfo, DosingApiManager.Despensing, "", "", new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                if (!isOnce[0]) {
                    isOnce[0] = true;
                    DialogFactory.showCommDialog(getActivity(), msg, "确定", R.drawable.icon_popup_error_patient, null);
                }
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                temp.add(type);
                String successInfo = "配液成功";
                // 刷新
                if (temp.size() == listId.size()) {
                    getOrdList(listId.get(0));
                    DialogFactory.showCommDialog(getActivity(), successInfo, "", 0, null, true);
                }
            }
        });
    }
}
