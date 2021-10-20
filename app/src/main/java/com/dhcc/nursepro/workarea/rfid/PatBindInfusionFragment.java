package com.dhcc.nursepro.workarea.rfid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.dhcc.nursepro.workarea.rfid.api.RfidBindApiManager;
import com.dhcc.nursepro.workarea.rfid.bean.InfusionPatBean;
import com.dhcc.nursepro.workarea.rfid.bean.RfidPatBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 输液绑定
 * @author:gaoruishan
 * @date:202021/10/19/17:06
 * @email:grs0515@163.com
 */
public class PatBindInfusionFragment extends PatBindRfidFragment {

    private List<InfusionPatBean.BedListBean> bedList;
    private String deviceNo;
    private String mRegNo;

    @Override
    public void initRifdBindDialog(int position) {
        super.initRifdBindDialog(position);
        mRegNo = bedList.get(position).getPatInfo().getRegNo();
        deviceNo = bedList.get(position).getPatInfo().getDeviceno();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarCenterTitle("输液绑定");
    }

    @Override
    public void initData() {
        mRegNo = "";
        deviceNo = "";
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap();
        map.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        RfidBindApiManager.getDeviceList(map, "GetDeviceList", new CommonCallBack<InfusionPatBean>() {
            @Override
            public void onSuccess(InfusionPatBean rfidPatBean, String type) {
                hideLoadingTip();
                bedList = rfidPatBean.getBedList();
                List<RfidPatBean.PatInfoListBean> data = new ArrayList<>();
                for (InfusionPatBean.BedListBean bedListBean : bedList) {
                    RfidPatBean.PatInfoListBean bean = new RfidPatBean.PatInfoListBean();
                    bean.setName(bedListBean.getPatInfo().getPatName());
                    bean.setBedCode(bedListBean.getBedCode());
                    bean.setSex(bedListBean.getPatInfo().getPatSex());
                    String isBind = !TextUtils.isEmpty(bedListBean.getPatInfo().getDeviceno()) ? "1" : "";
                    bean.setIfBind(isBind);
                    bean.setRegNo(bedListBean.getPatInfo().getRegNo());
                    data.add(bean);
                }
                rfidPatAdapter.setNewData(data);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
                hideLoadingTip();
            }

        });

    }

    @Override
    public void bindRfid(String regNo) {

        HashMap map = new HashMap();
        map.put("regNo", regNo);
        map.put("deviceNo", deviceNo);
        RfidBindApiManager.getDeviceList(map, "BindDevice", new CommonCallBack<InfusionPatBean>() {
            @Override
            public void onSuccess(InfusionPatBean rfidPatBean, String type) {
                showToast("绑定成功");
                if (rfidBindDialog != null && rfidBindDialog.isShowing()) {
                    rfidBindDialog.dismiss();
                }
                ViewUtils.runOnUiThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                },1000);

            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });

    }

    @Override
    public void unbindRfid() {

        HashMap map = new HashMap();
        map.put("deviceNo", deviceNo);
        RfidBindApiManager.getDeviceList(map, "UnBindDevice", new CommonCallBack<InfusionPatBean>() {
            @Override
            public void onSuccess(InfusionPatBean rfidPatBean, String type) {
                showToast("解绑成功");
                if (rfidBindDialog != null && rfidBindDialog.isShowing()) {
                    rfidBindDialog.dismiss();
                }
                ViewUtils.runOnUiThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                },1000);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });

    }

    @Override
    public void showToast(CharSequence text) {
        super.showToast(text);
        mRegNo = "";
        deviceNo = "";
    }

    @Override
    public void getScanMsg(Intent intent) {
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = bundle.getString("data");
            if(TextUtils.isEmpty(scanInfo)){
                ToastUtils.showShort("扫码未空!");
                return;
            }
            //点击患者绑定
            if (rfidBindDialog != null && rfidBindDialog.isShowing() && !deviceNo.isEmpty()) {
                deviceNo = bundle.getString("data");
                if (!isBind) {// 未绑定 点击的regNo
                    bindRfid(mRegNo);
                }
            } else {
                //两次扫码绑定
                if (scanInfo.contains("REG") || scanInfo.length() == 10) {
                    mRegNo = scanInfo;
                    if (TextUtils.isEmpty(deviceNo)) {
                        ToastUtils.showShort("请扫码设备码!");
                    }
                }else {
                    deviceNo = scanInfo;
                    if (TextUtils.isEmpty(mRegNo)) {
                        ToastUtils.showShort("请腕带!");
                    }
                }
                if (!TextUtils.isEmpty(mRegNo) && !TextUtils.isEmpty(deviceNo)) {
                    bindRfid(mRegNo);
                }
            }
        }

    }
}
