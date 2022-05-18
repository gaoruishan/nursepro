package com.dhcc.module.infusion.workarea.comm;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.http.BaseRequestParams;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.bean.PatInfoBean;
import com.dhcc.module.infusion.workarea.transblood.adapter.BaseTransBloodAdapter;
import com.dhcc.module.infusion.workarea.transblood.api.TransBloodApiManager;
import com.dhcc.module.infusion.workarea.transblood.bean.BloodInfoBean;
import com.dhcc.module.infusion.workarea.transblood.bean.TransBloodListBean;
import com.dhcc.module.infusion.workarea.transblood.bean.VitalSignsBean;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomScanBloodView;
import com.dhcc.res.infusion.CustomSelectView;
import com.dhcc.res.infusion.CustomSpeedView;
import com.dhcc.res.infusion.CustomVitalView;
import com.dhcc.res.infusion.bean.ScanBarCodeBean;

import java.util.HashMap;
import java.util.List;

import static com.dhcc.res.infusion.CustomScanBloodView.TIP_BAG_PRODUCT;
import static com.dhcc.res.infusion.CustomScanBloodView.TIP_BAG_PRODUCT_PAT;

/**
 * 输血-各个模块的父类
 * @author:gaoruishan
 * @date:202020-03-04/11:11
 * @email:grs0515@163.com
 */
public abstract class BaseTransBloodFragment extends BaseInfusionFragment implements View.OnClickListener {

    protected BaseRequestParams params;
    protected CustomPatView customPat;
    protected BaseTransBloodAdapter bloodListAdapter;
    protected BaseTransBloodAdapter tourListAdapter;

    protected TransBloodListBean mBean;
    protected CustomSpeedView customSpeed;
    protected CustomSelectView customTime;
    protected CustomScanBloodView scanView;
    protected String bagCode;
    protected String productCode;

    @Override
    protected void initViews() {
        super.initViews();
        showScanPatHand();
        //容错
        scanView = f(R.id.custom_scan_blood, CustomScanBloodView.class);
        if (scanView != null) {
            scanView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        customPat = f(R.id.custom_pat, CustomPatView.class);
        params = new BaseRequestParams();
    }

    @Override
    protected void setCustomPatViewData(CustomPatView customPat, PatInfoBean bean) {
        //重写
        if (bean != null && customPat != null) {
            customPat.setRegNo(bean.getPatRegNo()).setPatName(bean.getPatName()).setPatSex(bean.getPatSex()).setAge(bean.getPatAge())
                    .setBldType(bean.getPatBldTyp()).setSeat(bean.getBedCode());
        }
    }

    @Override
    protected void showScanPatHand() {
        SCAN_HAND = "请扫描腕带/输血标签";
        SCAN_PAT_HAND = "请您使用扫码设备，扫描病人腕带/输血标签";
        super.showScanPatHand();
    }

    protected abstract void getScanTransBloodList();

    /**
     * 输注/结束
     */
    protected void scanInfusionList() {
        params.barCode = scanInfo;
        if (!TextUtils.isEmpty(scanView.getBloodBagCode())
                && !TextUtils.isEmpty(scanView.getBloodProductCode())
                && !TextUtils.isEmpty(scanView.getBloodPatCode())) {
            params.bloodbagId = scanView.getBloodBagCode();
            params.bloodProductId = scanView.getBloodProductCode();
            params.regNo = scanView.getBloodPatCode();
            getTransBloodList();
            scanView.setClear();
        }
    }

    /**
     * 签收/巡视/回收
     */
    protected void scanReceiveList() {
        params.barCode = scanInfo;
        if (!TextUtils.isEmpty(scanView.getBloodBagCode())
                && !TextUtils.isEmpty(scanView.getBloodProductCode())) {
            params.bloodbagId = scanView.getBloodBagCode();
            params.bloodProductId = scanView.getBloodProductCode();
            getTransBloodList();
            scanView.setClear();
        }
    }
    @Override
    protected void getScanOrdList() {
        if (scanView != null) {
            setScanInfoWithServer(scanInfo,new CommonCallBack<ScanBarCodeBean>() {
                @Override
                public void onFail(String code, String msg) {

                }

                @Override
                public void onSuccess(ScanBarCodeBean bean, String type) {
                    scanView.setSuccessScanInfo(bean);
                    getScanTransBloodList();
                }
            });
        }
        //所有扫码-清参数
        params.clearAll();
    }

    /**
     * 通过服务器判断
     * @param scanInfo
     */
    public void setScanInfoWithServer(String scanInfo, final CommonCallBack<ScanBarCodeBean> callBack) {
        //都赋值为一个
        if (CustomScanBloodView.isAddPat) {
            CustomScanBloodView.TIP_BAG = TIP_BAG_PRODUCT_PAT;
            CustomScanBloodView.TIP_PRODUCT = TIP_BAG_PRODUCT_PAT;
            CustomScanBloodView.TIP_PAT = TIP_BAG_PRODUCT_PAT;
        } else {
            CustomScanBloodView.TIP_BAG = TIP_BAG_PRODUCT;
            CustomScanBloodView.TIP_PRODUCT = TIP_BAG_PRODUCT;
        }
        BaseRequestParams params = new BaseRequestParams();
        params.bloodbagId = scanView.getBloodBagCode();
        params.bloodProductId = scanView.getBloodProductCode();
        params.regNo = scanView.getBloodPatCode();
        HashMap<String, String> properties =  BaseRequestParams.getProperties(params);
        properties.put("barCode", scanInfo);
        properties.put("isPat", CustomScanBloodView.isAddPat+"");
        CommWebService.call("GetBarcodeFlag", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanBarCodeBean> parserUtil = new ParserUtil<>();
                ScanBarCodeBean bean = parserUtil.parserResult(jsonStr, callBack, ScanBarCodeBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 初始化输血适配器
     */
    protected void initBloodListAdapter() {
        bloodListAdapter = AdapterFactory.getTransBloodListAdapter();
        RecyclerView recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        recyclerView.setAdapter(bloodListAdapter);
    }

    /**
     * 获取输血列表
     */
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
                if (scanView != null) {
                    scanView.setVisibility(View.GONE);
                }
                setCommBloodListData(bean);
            }
        });
    }

    /**
     * 设置公共列表数据
     * @param bean
     */
    protected void setCommBloodListData(TransBloodListBean bean) {
        hideScanView();
        setCustomPatViewData(customPat, bean.getPatInfo());
        setCustomVitalData(bean.getVitalSigns());
        mBean = bean;
        if (bloodListAdapter != null) {
            bloodListAdapter.setNewData(bean.getBloodList());
            bloodListAdapter.setCurrentScanCode(bagCode);
        }
        //校验-'确认'按钮
        checkBloodListOeoreId(bean.getBloodList(), BLOOD_NO_ORD, bagCode);
    }

    /**
     * 生命体征
     * @param vitalSigns
     */
    protected void setCustomVitalData(VitalSignsBean vitalSigns) {
        CustomVitalView customVitalView = f(R.id.custom_vital, CustomVitalView.class);
        if (customVitalView != null) {
            //KeyBoardUtil.show(mContext, f(R.id.diy_keyboard, DIYKeyboardView.class), customVitalView.getEditTemp());
            customVitalView.setVisibility(vitalSigns != null ? View.VISIBLE : View.GONE);
            if (vitalSigns != null) {
                customVitalView.setTemp(vitalSigns.getTemperature()).setPulse(vitalSigns.getPulse())
                        .setDia(vitalSigns.getDiaPressure()).setSys(vitalSigns.getSysPressure());
            }
        }
    }

    /**
     * 检查是否包含
     * @param list
     * @param tips
     * @return false 不包含
     */
    protected boolean checkBloodListOeoreId(List<BloodInfoBean> list, String tips, String scanInfo) {
        if (list == null || list.size() == 0) {
            helper.setVisible(R.id.tv_ok, false);
            return false;
        }
        //包含
        boolean isContain = false;
        for (BloodInfoBean b : list) {
            //匹配规则
            if (scanInfo.contains(b.getBloodBagNo())) {
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            ToastUtils.showShort(tips);
            helper.setVisible(R.id.tv_ok, false);
        } else {
            helper.setOnClickListener(R.id.tv_ok, this);
        }
        return isContain;
    }

    /**
     * 公共回调处理
     * @return
     */
    protected CommonCallBack<CommResult> onCommSuccess() {
        return new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                mBean = null;
                if (scanView != null) {
                    scanView.setVisibility(View.VISIBLE);
                }
                onSuccessThings(bean);
            }
        };
    }


    @Override
    public void onClick(View v) {
        //所有点击-清参数
        params.clearAll();
    }
}
