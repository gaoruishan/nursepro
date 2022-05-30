package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.ScanBarCodeBean;
import com.grs.dhcc_res.R;

/**
 * 输血扫码页
 * @author:gaoruishan
 * @date:202020-03-18/16:50
 * @email:grs0515@163.com
 */
public class CustomScanBloodView extends BaseView {
    public static String TIP_BAG = "请扫描血袋条码";
    public static String TIP_PRODUCT = "请扫描血制品条码";
    public static String TIP_PAT = "请扫描患者腕带";

    public static String TIP_BAG_PRODUCT_PAT = "请扫描血袋条码/血制品条码/患者腕带";
    public static String TIP_BAG_PRODUCT = "请扫描血袋条码/血制品条码";
    private TextView tvPatCode;
    private TextView tvBloodbaginfo, tvProductinfo, tvPatientinfo;
    private View lineBlood1, lineBlood2;
    private ImageView imgBloodbag, imgBloodproduct, imgPatient;
    private TextView tvTip;
    public static boolean isAddPat;

    public CustomScanBloodView(Context context) {
        this(context, null);
    }

    public CustomScanBloodView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomScanBloodView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(ContextCompat.getColor(context, R.color.dhcc_white));
        //设置统一的id
        setId(R.id.custom_scan_blood);

        tvTip = findViewById(R.id.tv_tip);
        imgBloodbag = findViewById(R.id.img_bloodbag);
        lineBlood1 = findViewById(R.id.line_blood_1);
        tvBloodbaginfo = findViewById(R.id.tv_bloodbaginfo);

        imgBloodproduct = findViewById(R.id.img_bloodproduct);
        lineBlood2 = findViewById(R.id.line_blood_2);
        tvProductinfo = findViewById(R.id.tv_productinfo);

        imgPatient = findViewById(R.id.img_patient);
        tvPatCode = findViewById(R.id.tv_pat_code);
        tvPatientinfo = findViewById(R.id.tv_patientinfo);
        //默认
        setTip(CustomScanBloodView.TIP_BAG_PRODUCT);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_scan_blood_view;
    }

    public void addPatScan() {
        tvPatCode.setText("腕带条码");
        imgPatient.setVisibility(VISIBLE);
        isAddPat = true;
        setTip(CustomScanBloodView.TIP_BAG_PRODUCT_PAT);
    }


    public void setSuccessScanInfo(ScanBarCodeBean bean) {
        if (!TextUtils.isEmpty(bean.getBloodbagId())) {
            setText(tvBloodbaginfo, bean.getBloodbagId());
            imgBloodbag.setSelected(true);
            lineBlood1.setSelected(true);
        }else if (!TextUtils.isEmpty(bean.getBloodProductId())) {
            setText(tvProductinfo, bean.getBloodProductId());
            imgBloodproduct.setSelected(true);
            lineBlood2.setSelected(true);
        }else if (!TextUtils.isEmpty(bean.getRegNo()) && isAddPat) {
            setText(tvPatientinfo, bean.getRegNo());
            imgPatient.setSelected(true);
        }else {
            ToastUtils.showShort(TIP_BAG);
        }
    }

    /**
     * 通过服务器判断
     * @param scanInfo
     */
//    public void setScanInfoWithServer(String scanInfo, final CommonCallBack<ScanBarCodeBean> callBack) {
//        //都赋值为一个
//        if (isAddPat) {
//            TIP_BAG = TIP_BAG_PRODUCT_PAT;
//            TIP_PRODUCT = TIP_BAG_PRODUCT_PAT;
//            TIP_PAT = TIP_BAG_PRODUCT_PAT;
//        } else {
//            TIP_BAG = TIP_BAG_PRODUCT;
//            TIP_PRODUCT = TIP_BAG_PRODUCT;
//        }
//        BaseRequestParams params = new BaseRequestParams();
//        params.bloodbagId = getBloodBagCode();
//        params.bloodProductId = getBloodProductCode();
//        params.regNo = getBloodPatCode();
//        HashMap<String, String> properties =  BaseRequestParams.getProperties(params);
//        properties.put("barCode", scanInfo);
//        properties.put("isPat", isAddPat+"");
//        CommWebService.call("GetBarcodeFlag", properties, new ServiceCallBack() {
//            @Override
//            public void onResult(String jsonStr) {
//                ParserUtil<ScanBarCodeBean> parserUtil = new ParserUtil<>();
//                ScanBarCodeBean bean = parserUtil.parserResult(jsonStr, callBack, ScanBarCodeBean.class);
//                if (bean == null) return;
//                parserUtil.parserStatus(bean, callBack);
//            }
//        });
//    }

    public void setScanInfo(String scanInfo) {
        String s = tvBloodbaginfo.getText().toString();
        //第一个已经扫码了
        if (!TextUtils.isEmpty(s)) {
            //再腕带
            if (isAddPat) {
                setPatInfo(scanInfo, s);
            } else {
                setProductInfo(scanInfo, s);
            }
        } else {
            setBagInfo(scanInfo);
        }
    }

    private void setPatInfo(String scanInfo, String s) {
        String s1 = tvProductinfo.getText().toString();
        if (!TextUtils.isEmpty(s1)) {
            if (checkScanInfo(scanInfo, s1)) {
                return;
            }
            setText(tvPatientinfo, scanInfo);
        } else {
            setProductInfo(scanInfo, s);
        }
    }

    private void setBagInfo(String scanInfo) {
        setText(tvBloodbaginfo, scanInfo);
        imgBloodbag.setSelected(true);
        lineBlood1.setSelected(true);
        setTip(TIP_PRODUCT);
    }

    private void setProductInfo(String scanInfo, String s) {
        if (checkScanInfo(scanInfo, s)) {
            return;
        }
        setText(tvProductinfo, scanInfo);
        imgBloodproduct.setSelected(true);
        lineBlood2.setSelected(true);
        setTip(TIP_PAT);
    }

    private boolean checkScanInfo(String scanInfo, String s) {
        if (s.equals(scanInfo)) {
            ToastUtils.showShort("重复扫描,请扫第二个条码!");
            return true;
        }
        return false;
    }

    public void setClear() {
        tvBloodbaginfo.setText("");
        tvProductinfo.setText("");
        tvPatientinfo.setText("");
        imgBloodbag.setSelected(false);
        lineBlood1.setSelected(false);
        imgBloodproduct.setSelected(false);
        lineBlood2.setSelected(false);
        imgPatient.setSelected(false);
        setTip(TIP_BAG);
    }

    public void setTip(String s) {
        setText(tvTip, s);
    }

    public String getBloodBagCode() {
        return getText(tvBloodbaginfo);
    }

    public String getBloodProductCode() {
        return getText(tvProductinfo);
    }

    public String getBloodPatCode() {
        return getText(tvPatientinfo);
    }
}
