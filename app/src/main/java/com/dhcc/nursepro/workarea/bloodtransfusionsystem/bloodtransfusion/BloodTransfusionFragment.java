package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion;


import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.BloodNursePassView;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.PatWristInfoBean;


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
    private View lineBlood4;
    private ImageView imgBloodnurse2;

    private TextView tvPatInfo, tvBloodInfo, tvBloodBag;

    private String episodeId = "";


    private String RegNo = "";
    private String bloodbagId = "";
    private String bloodProductId = "";
    private String bloodRowId = "";


    //    private String episodeId = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private String patInfoStr;
    private String bloodInfoStr;

    private BloodOperationResultDialog bloodOperationResultDialog;
    BloodNursePassView blNurse1,blNurse2;

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
                } else if (TextUtils.isEmpty(blNurse1.getNurseText())||TextUtils.isEmpty(blNurse1.getPassText())) {
                    showToast("请输入第一个护士工号和密码");
                } else if (TextUtils.isEmpty(blNurse2.getNurseText())||TextUtils.isEmpty(blNurse2.getPassText())) {
                    showToast("请输入第二个护士工号和密码");
                } else {
                    startTransInfusion("2");
                }
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);


        initView(view);
    }


    private void startTransInfusion(String type) {
        String nurse1 = blNurse1.getNurseText();
        String nurse2 = blNurse2.getNurseText();
        BloodTSApiManager.bloodTransStart(bloodRowId, nurse1, nurse2, type,blNurse1.getPassText(),blNurse2.getPassText(), new BloodTSApiManager.BloodOperationResultCallback() {
            @Override
            public void onSuccess(BloodOperationResultBean bloodOperationResult) {

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
                        if (code.equals("100000")){
                            blNurse1.setNurseText("");
                            blNurse2.setNurseText("");
                            imgBloodnurse2.setSelected(false);
                        }else {
                            cleanAll();
                        }
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
        lineBlood4 = view.findViewById(R.id.line_blood_4);
        imgBloodnurse2 = view.findViewById(R.id.img_bloodnurse2);

        tvPatInfo = view.findViewById(R.id.tv_bloodtransfusion_patinfo);
        tvBloodInfo = view.findViewById(R.id.tv_bloodtransfusion_bloodinfo);
        tvBloodBag = view.findViewById(R.id.tv_bloodtransfusion_bloodbag);
        addTvChangListner(tvPatInfo);
        addTvChangListner(tvBloodInfo);
        addTvChangListner(tvBloodBag);

        blNurse1 = view.findViewById(R.id.bl_nurseinfo);
        blNurse1.setListener(new BloodNursePassView.Listener() {
            @Override
            public void update(String string) {
                changeScanTipText();
            }
        });

        blNurse2 = view.findViewById(R.id.bl_nurse2info);
        blNurse2.setListener(new BloodNursePassView.Listener() {
            @Override
            public void update(String string) {
                changeScanTipText();
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
        blNurse1.setNurseText("");
        blNurse2.setNurseText("");
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
        lineBlood4.setSelected(false);
        imgBloodnurse2.setSelected(false);
    }

    private void changeScanTipText() {
        tvBloodSure.setSelected(false);
        if (!tvPatInfo.getText().toString().equals("")){
            imgBloodpatient.setSelected(true);
            lineBlood1.setSelected(true);
        }else {
            imgBloodpatient.setSelected(false);
            lineBlood1.setSelected(false);
            tvBloodscantip.setText("请扫描患者腕带");
            return;
        }
        if (!tvBloodBag.getText().toString().equals("")){
            imgBloodbag.setSelected(true);
            lineBlood2.setSelected(true);
        }else {
            tvBloodscantip.setText("请扫描血袋条码");
            imgBloodbag.setSelected(false);
            lineBlood2.setSelected(false);
            return;
        }
        if (!tvBloodInfo.getText().toString().equals("")){
            imgBloodproduct.setSelected(true);
            lineBlood3.setSelected(true);
        }else {
            imgBloodproduct.setSelected(false);
            lineBlood3.setSelected(false);
            tvBloodscantip.setText("请扫描血制品条码");
            return;
        }
        if (blNurse1.getNurseText().length() > 0 && blNurse1.getPassText().length() > 0) {
            lineBlood4.setSelected(true);
            imgBloodnurse.setSelected(true);
        } else{
            lineBlood4.setSelected(false);
            imgBloodnurse.setSelected(false);
            imgBloodnurse.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
            return;
        }

        if (blNurse2.getNurseText().length() > 0 && blNurse2.getPassText().length() > 0) {
            imgBloodnurse2.setSelected(true);
            tvBloodSure.setSelected(true);
            tvBloodscantip.setText("请点击确定开始血液输注");
        } else{
            imgBloodnurse2.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
        }

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
                patInfoStr = patInfo.getCtLocDesc() + "-" + patInfo.getBedCode() + "-" + patInfo.getName() + "-" + patInfo.getRegNo();
                tvPatInfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void scanInfusionData(String bloodbag, String bloodProduct) {

        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbag, bloodProduct, "S", new BloodTSApiManager.GetBloodInfoCallback() {
            @Override
            public void onSuccess(BloodInfoBean bloodInfoBean) {
                BloodInfoBean.BlooInfoBean bloodInfo = bloodInfoBean.getBlooInfo();

                String bloodId = bloodInfo.getBloodProductId().equals("") ? "" : bloodInfo.getBloodProductId() + "-";
                String bloodDesc = bloodInfo.getProductDesc().equals("") ? "" : bloodInfo.getProductDesc() + "-";
                String bloodGroup = bloodInfo.getBloodGroup().equals("") ? "" : bloodInfo.getBloodGroup();
                String patbloodGroup = bloodInfo.getPatBldGroup().equals("") ? "" : bloodInfo.getPatBldGroup();
                bloodInfoStr = bloodId + bloodDesc + bloodGroup;
                bloodProductId = bloodId + bloodDesc + bloodGroup;
                tvBloodInfo.setText(bloodInfoStr);
                bloodRowId = bloodInfo.getBloodRowId();
                patInfoStr = patInfoStr + "-" + patbloodGroup;
                tvPatInfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);

                bloodbagId = "";
                bloodProductId = "";
                tvBloodBag.setText("");
                tvBloodInfo.setText("");
            }
        });
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String dataStr = bundle.getString("data");
            if (RegNo.equals("")) {
                RegNo = dataStr;
                scanPatData(dataStr);
            } else if (SPUtils.getInstance().getString(SharedPreference.BLOODSCANTIMES, "2").equals("1")) {
                if (bloodbagId.equals("") && (!episodeId.equals(""))) {
                    bloodbagId = dataStr;
                    tvBloodBag.setText(bloodbagId);
                    scanInfusionData(bloodbagId, "");
                } else if (blNurse1.getNurseText().equals("")) {
                    blNurse1.setNurseText(dataStr);

                } else if (blNurse2.getNurseText().equals("")) {
                    blNurse2.setNurseText(dataStr);
                    imgBloodnurse.setSelected(true);
                    startTransInfusion("1");
                }
            } else if (SPUtils.getInstance().getString(SharedPreference.BLOODSCANTIMES, "2").equals("2")) {
                if (bloodbagId.equals("") && (!episodeId.equals(""))) {
                    bloodbagId = dataStr;
                    tvBloodBag.setText(bloodbagId);
                } else if (bloodProductId.equals("")) {
                    bloodProductId = dataStr;
                    scanInfusionData(bloodbagId, dataStr);
                }else if (blNurse1.getNurseText().equals("")) {
                    blNurse1.setNurseText(dataStr);
                    blNurse1.setPassText(" ");
                } else if (blNurse2.getNurseText().equals("")) {
                    blNurse2.setNurseText(dataStr);
                    blNurse2.setPassText(" ");
                    imgBloodnurse.setSelected(true);
                    startTransInfusion("1");
                }
            }
        }
    }

    private void addTvChangListner(TextView tv){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                changeScanTipText();
            }
        });

    }

}
