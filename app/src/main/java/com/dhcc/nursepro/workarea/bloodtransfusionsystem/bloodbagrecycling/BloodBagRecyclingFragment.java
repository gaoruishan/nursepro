package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodbagrecycling;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;

import java.util.Objects;

/**
 * BloodBagRecyclingFragment
 * 血袋回收
 *
 * @author DevLix126
 * created at 2018/9/18 10:28
 */
public class BloodBagRecyclingFragment extends BaseFragment {

    private TextView tvBloodscantip;
    private ImageView imgBloodbag;
    private View lineBlood1;
    private ImageView imgBloodproduct;
    private View lineBlood2;
    private ImageView imgBloodpatient;

    private TextView tvBloodbagrecyclingBloodbaginfo;
    private TextView tvBloodbagrecyclingBloodproductinfo;
    private TextView tvBloodbagrecyclingBloodpatientinfo;


    private IntentFilter filter;
    private Receiver mReceiver = new Receiver();

    private String episodeId = "";
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
        setToolbarCenterTitle(getString(R.string.title_bloodbagrecycling));

        setToolbarBottomLineVisibility(false);

        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);

        initView(view);


    }

    private void initView(View view) {
        tvBloodscantip = view.findViewById(R.id.tv_bloodscantip);
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag = view.findViewById(R.id.img_bloodbag);
        lineBlood1 = view.findViewById(R.id.line_blood_1);
        imgBloodproduct = view.findViewById(R.id.img_bloodproduct);
        lineBlood2 = view.findViewById(R.id.line_blood_2);
        imgBloodpatient = view.findViewById(R.id.img_bloodpatient);

        tvBloodbagrecyclingBloodbaginfo = view.findViewById(R.id.tv_bloodbagrecycling_bloodbaginfo);
        tvBloodbagrecyclingBloodproductinfo = view.findViewById(R.id.tv_bloodbagrecycling_bloodproductinfo);
        tvBloodbagrecyclingBloodpatientinfo = view.findViewById(R.id.tv_bloodbagrecycling_bloodpatientinfo);
    }

    @Override
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        activity.unregisterReceiver(mReceiver);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_bag_recycling, container, false);
    }

    private void clearScanInfo() {
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag.setSelected(false);
        lineBlood1.setSelected(false);
        imgBloodproduct.setSelected(false);
        lineBlood2.setSelected(false);
        imgBloodpatient.setSelected(false);
        scanOrder = 1;

        tvBloodbagrecyclingBloodbaginfo.setText("");
        tvBloodbagrecyclingBloodproductinfo.setText("");
        tvBloodbagrecyclingBloodpatientinfo.setText("");
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {

                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    String scanStr = bundle.getString("data");

                    if (scanOrder == 1) {
                        scanOrder++;
                        bloodbagId = scanStr;
                        tvBloodbagrecyclingBloodbaginfo.setText(bloodbagId);
                        tvBloodscantip.setText("请扫描血制品条码");
                        imgBloodbag.setSelected(true);
                        lineBlood1.setSelected(true);
                    } else if (scanOrder == 2) {
                        scanOrder++;
                        bloodProductId = scanStr;
                        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbagId, bloodProductId, "RE", new BloodTSApiManager.GetBloodInfoCallback() {
                            @Override
                            public void onSuccess(BloodInfoBean bloodInfoBean) {
                                bloodInfo = bloodInfoBean.getBlooInfo();
                                tvBloodbagrecyclingBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                                tvBloodbagrecyclingBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getBloodGroup());

                                imgBloodproduct.setSelected(true);
                                lineBlood2.setSelected(true);
                                imgBloodpatient.setSelected(true);

                                BloodTSApiManager.recycleBloodbag(bloodInfo.getBloodRowId(), new BloodTSApiManager.BloodOperationResultCallback() {
                                    @Override
                                    public void onSuccess(BloodOperationResultBean bloodOperationResult) {
                                        if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                                            bloodOperationResultDialog.dismiss();
                                        }

                                        bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());

                                        bloodOperationResultDialog.setExecresult("血袋回收成功");

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

                    break;
                default:
                    break;
            }
        }
    }

}
