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
import android.widget.TextView;
import android.widget.Toast;

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
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "今日列表", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);

        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);

        initView(view);


    }

    private void initView(View view) {
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
        tvBloodbagrecyclingBloodbaginfo.setText("请扫描血袋条码");
        tvBloodbagrecyclingBloodbaginfo.setSelected(false);
        tvBloodbagrecyclingBloodproductinfo.setText("请扫描血制品条码");
        tvBloodbagrecyclingBloodproductinfo.setSelected(false);
        tvBloodbagrecyclingBloodpatientinfo.setText("血液信息中获取");
        tvBloodbagrecyclingBloodpatientinfo.setSelected(false);
        scanOrder = 1;
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {

                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    String scanStr = bundle.getString("data");
                    //                    if (scanOrder == 0) {
                    //                        regNo = scanStr;
                    //
                    //                        BloodTSApiManager.getPatWristInfo(regNo, new BloodTSApiManager.GetPatWristInfoCallback() {
                    //                            @Override
                    //                            public void onSuccess(PatWristInfoBean patWristInfoBean) {
                    //                                PatWristInfoBean.PatInfoBean patInfoBean = patWristInfoBean.getPatInfo();
                    //
                    //                                tvBloodsignBloodpatientinfo.setText(patInfoBean.getWardDesc()+"-"+patInfoBean.getRoomDesc()+"-"+patInfoBean.getBedCode()+"床-"+patInfoBean.getName());
                    //                                scanOrder++;
                    //                            }
                    //
                    //                            @Override
                    //                            public void onFail(String code, String msg) {
                    //                                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
                    //                            }
                    //                        });
                    //
                    //                    }

                    if (scanOrder == 1) {
                        scanOrder++;
                        bloodbagId = scanStr;
                        tvBloodbagrecyclingBloodbaginfo.setText(bloodbagId);
                        tvBloodbagrecyclingBloodbaginfo.setSelected(true);
                    } else if (scanOrder == 2) {
                        scanOrder++;
                        bloodProductId = scanStr;
                        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbagId, bloodProductId, "RE", new BloodTSApiManager.GetBloodInfoCallback() {
                            @Override
                            public void onSuccess(BloodInfoBean bloodInfoBean) {
                                bloodInfo = bloodInfoBean.getBlooInfo();
                                tvBloodbagrecyclingBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                                tvBloodbagrecyclingBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getBloodGroup());
                                tvBloodbagrecyclingBloodproductinfo.setSelected(true);
                                tvBloodbagrecyclingBloodpatientinfo.setSelected(true);

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
