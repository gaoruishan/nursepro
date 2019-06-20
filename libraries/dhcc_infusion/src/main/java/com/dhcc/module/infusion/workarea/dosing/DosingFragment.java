package com.dhcc.module.infusion.workarea.dosing;

import android.content.Intent;
import android.os.Bundle;
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
//    @FindView(R.id.tv_ok)
    private View tvOk;
    private String reqType;
    private String scanInfo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("配液");
        addPatListToToolbarRight();
        tvOk=f(R.id.tv_ok);
        tvOk.setOnClickListener(this);
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

//    @FindEvent(R.id.tv_ok)
    public void onOkClick(View v) {
        despensingOrd();
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            despensingOrd();
        }
    }
}
