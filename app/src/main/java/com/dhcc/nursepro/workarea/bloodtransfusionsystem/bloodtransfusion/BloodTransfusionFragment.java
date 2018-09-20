package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationListFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.PatWristInfoBean;

import java.util.Objects;


/**
 * BloodTransfusionFragment
 * 血液输注
 *
 * @author DevLix126
 * created at 2018/9/18 10:34
 */
public class BloodTransfusionFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvPatInfo, tvBloodInfo, tvBloodBag;
    private EditText tvNurse1, tvNurse2;
    private LinearLayout llDevice;
    private TextView tvSure;

    private IntentFilter filter;
    private Receiver mReceiver = null;
    private String episodeId = "";


    private String RegNo = "";
    private String bloodbagId = "";
    private String bloodProductId = "";
    private String nurseId1 = "";
    private String nurseId2 = "";
    private String bloodRowId = "";


    //    private String episodeId = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private String patInfoStr;
    private String bloodInfoStr;

    private BloodOperationResultDialog bloodOperationResultDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodtransfusion));
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "今日列表", Toast.LENGTH_SHORT).show();
                startFragment(BloodOperationListFragment.class);
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);


        initView(view);

        mReceiver = new Receiver();
        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);
    }

    private void initView(View view) {
        tvPatInfo = view.findViewById(R.id.tv_bloodtransfusion_patinfo);
        tvBloodInfo = view.findViewById(R.id.tv_bloodtransfusion_bloodinfo);
        tvBloodBag = view.findViewById(R.id.tv_bloodtransfusion_bloodbag);
        tvNurse1 = view.findViewById(R.id.tv_bloodtransfusion_nurse1);
        tvNurse2 = view.findViewById(R.id.tv_bloodtransfusion_nurse2);
        llDevice = view.findViewById(R.id.ll_bloodtransfusion_device);
        tvSure = view.findViewById(R.id.tv_bloodTransfusion_sure);
        tvSure.setOnClickListener(this);

        tvNurse1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("11111tv1", tvNurse1.getText() + "--" + tvNurse2.getText());
                //如果有输入内容长度大于0那么显示选中
                if (tvNurse1.getText().toString().length() > 0) {
                    tvNurse1.setSelected(true);
                    llDevice.setBackgroundResource(R.drawable.img_device2);
                } else {
                    tvNurse1.setSelected(false);
                    llDevice.setBackgroundResource(R.drawable.img_device1);
                }

                if (tvNurse2.getText().toString().length() > 0) {
                    tvNurse1.setSelected(true);
                    tvNurse2.setSelected(true);
                    llDevice.setBackgroundResource(R.drawable.img_device3);
                }
            }
        });
        tvNurse2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("11111tv1", tvNurse1.getText() + "--" + tvNurse2.getText());
                if (tvNurse2.getText().toString().length() > 0) {
                    tvNurse1.setSelected(true);
                    tvNurse2.setSelected(true);
                    llDevice.setBackgroundResource(R.drawable.img_device3);
                } else if (tvNurse1.getText().toString().length() > 0) {
                    tvNurse2.setSelected(false);
                    llDevice.setBackgroundResource(R.drawable.img_device2);
                } else {
                    tvNurse1.setSelected(false);
                    tvNurse2.setSelected(false);
                    llDevice.setBackgroundResource(R.drawable.img_device1);
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mReceiver != null) {
            getActivity().registerReceiver(mReceiver, filter);
        }
    }

    @Override
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        activity.unregisterReceiver(mReceiver);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion, container, false);
    }

    private void scanPatData(String reg) {

        BloodTSApiManager.getPatWristInfo(reg, new BloodTSApiManager.GetPatWristInfoCallback() {
            @Override
            public void onSuccess(PatWristInfoBean patWristInfoBean) {
                PatWristInfoBean.PatInfoBean patInfo = patWristInfoBean.getPatInfo();
                episodeId = patInfo.getEpisodeID();
                String loc = patInfo.getCtLocDesc().equals("") ? "" : patInfo.getCtLocDesc() + "-";
                String room = patInfo.getRoomDesc().equals("") ? "" : patInfo.getRoomDesc() + "-";
                String bedCode = patInfo.getBedCode().equals("") ? "未分床-" : patInfo.getBedCode() + "床-";
                String name = patInfo.getName();
                patInfoStr = loc + room + bedCode + name;
                tvPatInfo.setText(patInfoStr);
                tvPatInfo.setSelected(true);
            }

            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast(code + ":" + msg);
                tvPatInfo.setSelected(false);
            }
        });

    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bloodTransfusion_sure:
                if (bloodRowId.equals("")) {
                    showToast("请先扫描获取血信息");
                } else if (TextUtils.isEmpty(tvNurse1.getText())) {
                    showToast("请输入第一个护士工号");
                } else if (TextUtils.isEmpty(tvNurse2.getText())) {
                    showToast("请输入第二个护士工号");
                } else {
                    startTransInfusion(tvNurse1.getText().toString(), tvNurse2.getText().toString(), "2");
                }
                break;
            default:
                break;

        }
    }

    private void scanInfusionData(String bloodbag, String bloodProduct) {

        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbagId, bloodProductId, "S", new BloodTSApiManager.GetBloodInfoCallback() {
            @Override
            public void onSuccess(BloodInfoBean bloodInfoBean) {
                BloodInfoBean.BlooInfoBean bloodInfo = bloodInfoBean.getBlooInfo();

                String bloodId = bloodInfo.getBloodProductId().equals("") ? "" : bloodInfo.getBloodProductId() + "-";
                String bloodDesc = bloodInfo.getProductDesc().equals("") ? "" : bloodInfo.getProductDesc() + "-";
                String bloodGroup = bloodInfo.getBloodGroup().equals("") ? "" : bloodInfo.getBloodGroup();
                bloodInfoStr = bloodId + bloodDesc + bloodGroup;
                bloodProductId = bloodId + bloodDesc + bloodGroup;
                tvBloodInfo.setText(bloodInfoStr);
                bloodRowId = bloodInfo.getBloodRowId();
                tvBloodInfo.setSelected(true);
                patInfoStr = patInfoStr + "-" + bloodGroup;
                tvPatInfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                bloodbagId = "";
                bloodProductId = "";
                tvBloodBag.setText("请重新扫描血袋条码");
                tvBloodInfo.setText("请重新扫描血制品条码");
                tvBloodBag.setSelected(false);
                tvBloodInfo.setSelected(false);
                showToast(code + ":" + msg);
            }
        });
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    String dataStr = bundle.getString("data");
                    if (RegNo.equals("")) {
                        RegNo = dataStr;
                        scanPatData(dataStr);
                    } else if (bloodbagId.equals("") && (!episodeId.equals(""))) {
                        bloodbagId = dataStr;
                        tvBloodBag.setText(bloodbagId);
                        tvBloodBag.setSelected(true);
                    } else if (bloodProductId.equals("")) {
                        bloodProductId = dataStr;
                        scanInfusionData(bloodbagId, dataStr);
                    } else if (nurseId1.equals("")) {
                        nurseId1 = dataStr;
                        tvNurse1.setText(nurseId1);
                        tvNurse1.setSelected(true);
                        llDevice.setBackgroundResource(R.drawable.img_device2);

                    } else if (nurseId2.equals("")) {
                        nurseId2 = dataStr;
                        tvNurse2.setText(nurseId2);
                        tvNurse2.setSelected(true);
                        llDevice.setBackgroundResource(R.drawable.img_device3);
                        startTransInfusion(nurseId1, dataStr, "1");
                    }

                    break;
                default:
                    break;
            }
        }
    }



    private void startTransInfusion(String nurse1, String nurse2, String nursType) {

        BloodTSApiManager.bloodTransStart(bloodRowId, nurse1, nurse2, nursType, new BloodTSApiManager.BloodOperationResultCallback() {
            @Override
            public void onSuccess(BloodOperationResultBean bloodOperationResult) {
                llDevice.setBackgroundResource(R.drawable.img_device3);

                if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                    bloodOperationResultDialog.dismiss();
                }

                bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());

                bloodOperationResultDialog.setExecresult("血液输注成功");

                bloodOperationResultDialog.setImgId(R.drawable.icon_popup_sucess);
                bloodOperationResultDialog.setSureVisible(View.GONE);
                bloodOperationResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bloodOperationResultDialog.dismiss();
                    }
                }, 1000);

                cleanAll();
            }

            @Override
            public void onFail(String code, String msg) {

                nurseId1 = "";
                nurseId2 = "";
                tvNurse1.setText("");
                tvNurse2.setText("");
                tvNurse1.setSelected(false);
                tvNurse2.setSelected(false);
                showToast(code + ":" + msg);
                llDevice.setBackgroundResource(R.drawable.img_device1);
            }
        });

    }

    private void cleanAll() {
        RegNo = "";
        tvPatInfo.setText("请扫描患者腕带条码");
        tvPatInfo.setSelected(false);
        bloodbagId = "";
        tvBloodBag.setText("请扫描血袋条码");
        tvBloodBag.setSelected(false);
        bloodProductId = "";
        tvBloodInfo.setText("请扫描血制品条码");
        tvBloodInfo.setSelected(false);
        nurseId1 = "";
        tvNurse1.setText(null);
        tvNurse1.setSelected(false);
        nurseId2 = "";
        tvNurse2.setText(null);
        tvNurse2.setSelected(false);
        llDevice.setBackgroundResource(R.drawable.img_device1);
        episodeId = "";
        bloodRowId = "";
    }

}
