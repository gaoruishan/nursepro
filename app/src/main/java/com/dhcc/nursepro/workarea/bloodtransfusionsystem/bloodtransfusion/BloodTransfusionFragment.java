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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.constant.Action;
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
public class BloodTransfusionFragment extends BaseFragment {
    private TextView tvBloodSure;
    private TextView tvBloodscantip;
    private ImageView imgBloodpatient;
    private View lineBlood1;
    private ImageView imgBloodbag;
    private View lineBlood2;
    private ImageView imgBloodproduct;
    private View lineBlood3;
    private ImageView imgBloodnurse;

    private TextView tvPatInfo, tvBloodInfo, tvBloodBag;
    private EditText tvNurse1, tvNurse2;
    private LinearLayout llDevice;

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
        //右上角确定按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        tvBloodSure = viewright.findViewById(R.id.tv_bloodtoobar_right);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bloodRowId.equals("")) {
                    showToast("请先扫描获取血液信息");
                } else if (TextUtils.isEmpty(tvNurse1.getText())) {
                    showToast("请输入第一个护士工号");
                } else if (TextUtils.isEmpty(tvNurse2.getText())) {
                    showToast("请输入第二个护士工号");
                } else {
                    startTransInfusion("2");
                }
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

    @Override
    public void onResume() {
        super.onResume();
        if (mReceiver != null) {
            getActivity().registerReceiver(mReceiver, filter);
        }
    }

    private void startTransInfusion(String type) {
        String nurse1 = tvNurse1.getText().toString();
        String nurse2 = tvNurse2.getText().toString();
        BloodTSApiManager.bloodTransStart(bloodRowId, nurse1, nurse2, type, new BloodTSApiManager.BloodOperationResultCallback() {
            @Override
            public void onSuccess(BloodOperationResultBean bloodOperationResult) {
                llDevice.setBackgroundResource(R.drawable.img_separate4);

                if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                    bloodOperationResultDialog.dismiss();
                }

                bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());

                bloodOperationResultDialog.setExecresult("血液输注开始");

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
                        cleanAll();
                        bloodOperationResultDialog.dismiss();
                    }
                });
                bloodOperationResultDialog.show();
            }
        });

    }

    private void initView(View view) {
        tvBloodscantip = view.findViewById(R.id.tv_bloodscantip);
        tvBloodscantip.setText("请扫描患者腕带");
        imgBloodpatient = view.findViewById(R.id.img_bloodpatient);
        lineBlood1 = view.findViewById(R.id.line_blood_1);
        imgBloodbag = view.findViewById(R.id.img_bloodbag);
        lineBlood2 = view.findViewById(R.id.line_blood_2);
        imgBloodproduct = view.findViewById(R.id.img_bloodproduct);
        lineBlood3 = view.findViewById(R.id.line_blood_3);
        imgBloodnurse = view.findViewById(R.id.img_bloodnurse);

        tvPatInfo = view.findViewById(R.id.tv_bloodtransfusion_patinfo);
        tvBloodInfo = view.findViewById(R.id.tv_bloodtransfusion_bloodinfo);
        tvBloodBag = view.findViewById(R.id.tv_bloodtransfusion_bloodbag);
        tvNurse1 = view.findViewById(R.id.tv_bloodtransfusion_nurse1);
        tvNurse2 = view.findViewById(R.id.tv_bloodtransfusion_nurse2);
        llDevice = view.findViewById(R.id.ll_bloodtransfusion_device);

        tvNurse1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示选中
                changeNurseLL();
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
                changeNurseLL();
            }
        });


    }

    private void cleanAll() {
        RegNo = "";
        tvPatInfo.setText("");
        bloodbagId = "";
        tvBloodBag.setText("");
        bloodProductId = "";
        tvBloodInfo.setText("");
        nurseId1 = "";
        tvNurse1.setText(null);
        tvNurse1.setSelected(false);
        nurseId2 = "";
        tvNurse2.setText(null);
        tvNurse2.setSelected(false);
        llDevice.setBackgroundResource(R.drawable.img_separate1);
        episodeId = "";
        bloodRowId = "";

        tvBloodscantip.setText("请扫描患者腕带");
        imgBloodpatient.setSelected(false);
        lineBlood1.setSelected(false);
        imgBloodbag.setSelected(false);
        lineBlood2.setSelected(false);
        imgBloodproduct.setSelected(false);
        lineBlood3.setSelected(false);
        imgBloodnurse.setSelected(false);
    }

    private void changeNurseLL() {
        if (tvNurse1.getText().toString().length() > 0 && tvNurse2.getText().toString().length() > 0) {
            tvNurse1.setSelected(true);
            tvNurse2.setSelected(true);
            imgBloodnurse.setSelected(true);
            tvBloodSure.setSelected(true);
            tvBloodscantip.setText("请点击确定开始血液输注");
            llDevice.setBackgroundResource(R.drawable.img_separate4);
        } else if (tvNurse1.getText().toString().length() > 0 && tvNurse2.getText().toString().length() == 0) {
            tvNurse1.setSelected(true);
            tvNurse2.setSelected(false);
            nurseId2 = "";
            imgBloodnurse.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
            llDevice.setBackgroundResource(R.drawable.img_separate2);
        } else if (tvNurse1.getText().toString().length() == 0 && tvNurse2.getText().toString().length() > 0) {
            tvNurse1.setSelected(false);
            tvNurse2.setSelected(true);
            nurseId1 = "";
            imgBloodnurse.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
            llDevice.setBackgroundResource(R.drawable.img_separate3);
        } else if (tvNurse1.getText().toString().length() == 0 && tvNurse2.getText().toString().length() == 0) {
            tvNurse1.setSelected(false);
            tvNurse2.setSelected(false);
            nurseId1 = "";
            nurseId2 = "";
            imgBloodnurse.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
            llDevice.setBackgroundResource(R.drawable.img_separate1);
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
                imgBloodpatient.setSelected(true);
                lineBlood1.setSelected(true);
                tvBloodscantip.setText("请扫描血袋条码");
            }

            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast("error" + code + ":" + msg);
            }
        });

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
                patInfoStr = patInfoStr + "-" + bloodGroup;
                tvPatInfo.setText(patInfoStr);

                imgBloodproduct.setSelected(true);
                lineBlood3.setSelected(true);
                tvBloodscantip.setText("请扫描/输入护士工牌");
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);

                bloodbagId = "";
                bloodProductId = "";
                tvBloodBag.setText("");
                tvBloodInfo.setText("");

                tvBloodscantip.setText("请扫描血袋编号");
                imgBloodbag.setSelected(false);
                lineBlood2.setSelected(false);
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
                        imgBloodbag.setSelected(true);
                        lineBlood2.setSelected(true);
                        tvBloodscantip.setText("请扫描血制品编号");
                    } else if (bloodProductId.equals("")) {
                        bloodProductId = dataStr;
                        scanInfusionData(bloodbagId, dataStr);
                    } else if (nurseId1.equals("")) {
                        nurseId1 = dataStr;
                        tvNurse1.setText(nurseId1);
                        tvNurse1.setSelected(true);
                        llDevice.setBackgroundResource(R.drawable.img_separate2);

                    } else if (nurseId2.equals("")) {
                        nurseId2 = dataStr;
                        tvNurse2.setText(nurseId2);
                        tvNurse2.setSelected(true);
                        llDevice.setBackgroundResource(R.drawable.img_separate4);
                        imgBloodnurse.setSelected(true);
                        startTransInfusion("1");
                    }

                    break;
                default:
                    break;
            }
        }
    }

}
