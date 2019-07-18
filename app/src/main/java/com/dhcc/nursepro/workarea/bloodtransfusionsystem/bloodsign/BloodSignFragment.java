package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodsign;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;

/**
 * BloodSignFragment
 * 血袋签收
 *
 * @author DevLix126
 * created at 2018/9/18 10:34
 */
public class BloodSignFragment extends BaseFragment {
    private TextView tvBloodSure;

    private TextView tvBloodscantip;
    private ImageView imgBloodbag;
    private View lineBlood1;
    private ImageView imgBloodproduct;
    private View lineBlood2;
    private ImageView imgBloodpatient;

    private TextView tvBloodsignBloodbaginfo;
    private TextView tvBloodsignBloodproductinfo;
    private TextView tvBloodsignBloodpatientinfo;

    private String bloodbagId;
    private String bloodProductId;
    private BloodInfoBean.BlooInfoBean bloodInfo;

    /**
     * scanOrder
     * 1    血袋
     * 2    血制品
     */
    private int scanOrder = 1;

    private BloodOperationResultDialog bloodOperationResultDialog;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodsign));
        //右上角确定按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        tvBloodSure = viewright.findViewById(R.id.tv_bloodtoobar_right);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanOrder < 3) {
                    showToast("请完成扫码再点击确定");
                    return;
                }
                BloodTSApiManager.bloodReceive(bloodInfo.getBloodbagId(), bloodInfo.getBloodProductId(), bloodInfo.getBloodGroup(), bloodInfo.getPatBldGroup(), bloodInfo.getEpisodeId(), bloodInfo.getProductDesc(), bloodInfo.getRowId(), new BloodTSApiManager.BloodOperationResultCallback() {
                    @Override
                    public void onSuccess(BloodOperationResultBean bloodOperationResultBean) {
                        if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                            bloodOperationResultDialog.dismiss();
                        }
                        bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());
                        bloodOperationResultDialog.setExecresult("血袋签收成功");
                        bloodOperationResultDialog.setImgId(R.drawable.icon_popup_sucess);
                        bloodOperationResultDialog.setSureVisible(View.GONE);
                        bloodOperationResultDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearScanInfo();
                                bloodOperationResultDialog.dismiss();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                            bloodOperationResultDialog.dismiss();
                        }
                        bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());
                        bloodOperationResultDialog.setExecresult(msg);
                        bloodOperationResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                        bloodOperationResultDialog.setSureVisible(View.VISIBLE);
                        bloodOperationResultDialog.setSureOnclickListener(new BloodOperationResultDialog.onSureOnclickListener() {
                            @Override
                            public void onSureClick() {
                                clearScanInfo();
                                bloodOperationResultDialog.dismiss();
                            }
                        });
                        bloodOperationResultDialog.show();
                    }
                });
            }
        });
        setToolbarRightCustomView(viewright);
        setToolbarBottomLineVisibility(false);

        initView(view);


    }

    private void clearScanInfo() {
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag.setSelected(false);
        lineBlood1.setSelected(false);
        imgBloodproduct.setSelected(false);
        lineBlood2.setSelected(false);
        imgBloodpatient.setSelected(false);
        tvBloodSure.setSelected(false);
        scanOrder = 1;
        tvBloodsignBloodbaginfo.setText("");
        tvBloodsignBloodproductinfo.setText("");
        tvBloodsignBloodpatientinfo.setText("");
    }

    private void initView(View view) {
        tvBloodscantip = view.findViewById(R.id.tv_bloodscantip);
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag = view.findViewById(R.id.img_bloodbag);
        lineBlood1 = view.findViewById(R.id.line_blood_1);
        imgBloodproduct = view.findViewById(R.id.img_bloodproduct);
        lineBlood2 = view.findViewById(R.id.line_blood_2);
        imgBloodpatient = view.findViewById(R.id.img_bloodpatient);


        tvBloodsignBloodbaginfo = view.findViewById(R.id.tv_bloodsign_bloodbaginfo);
        tvBloodsignBloodproductinfo = view.findViewById(R.id.tv_bloodsign_bloodproductinfo);
        tvBloodsignBloodpatientinfo = view.findViewById(R.id.tv_bloodsign_bloodpatientinfo);
    }


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_sign, container, false);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    String scanStr = bundle.getString("data");

                    String scanTimes = SPUtils.getInstance().getString(SharedPreference.BLOODSCANTIMES,"2");
                    if (scanTimes.equals("1")){
                        scanOrder = 3;
                        BloodTSApiManager.getBloodInfo(scanStr, "", new BloodTSApiManager.GetBloodInfoCallback() {
                            @Override
                            public void onSuccess(BloodInfoBean bloodInfoBean) {
                                tvBloodsignBloodbaginfo.setText(scanStr);
                                imgBloodbag.setSelected(true);
                                lineBlood1.setSelected(true);

                                bloodInfo = bloodInfoBean.getBlooInfo();
                                tvBloodsignBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                                tvBloodsignBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getBloodGroup());
                                tvBloodscantip.setText("请点击确认签收血袋");
                                imgBloodproduct.setSelected(true);
                                lineBlood2.setSelected(true);
                                imgBloodpatient.setSelected(true);
                                tvBloodSure.setSelected(true);
                            }

                            @Override
                            public void onFail(String code, String msg) {
                                showToast("error" + code + ":" + msg);
                                clearScanInfo();
                            }
                        });

                    }else {
                        if (scanOrder == 1) {
                            scanOrder++;
                            bloodbagId = scanStr;
                            tvBloodsignBloodbaginfo.setText(bloodbagId);
                            tvBloodscantip.setText("请扫描血制品条码");
                            imgBloodbag.setSelected(true);
                            lineBlood1.setSelected(true);


                        } else if (scanOrder == 2) {
                            scanOrder++;
                            bloodProductId = scanStr;
                            BloodTSApiManager.getBloodInfo(bloodbagId, bloodProductId, new BloodTSApiManager.GetBloodInfoCallback() {
                                @Override
                                public void onSuccess(BloodInfoBean bloodInfoBean) {
                                    bloodInfo = bloodInfoBean.getBlooInfo();
                                    tvBloodsignBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                                    tvBloodsignBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getBloodGroup());
                                    tvBloodscantip.setText("请点击确认签收血袋");
                                    imgBloodproduct.setSelected(true);
                                    lineBlood2.setSelected(true);
                                    imgBloodpatient.setSelected(true);
                                    tvBloodSure.setSelected(true);
                                }

                                @Override
                                public void onFail(String code, String msg) {
                                    showToast("error" + code + ":" + msg);
                                    clearScanInfo();
                                }
                            });
                        }
                    }

            }
    }
}
