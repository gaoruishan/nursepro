package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend;


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
import android.widget.EditText;
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
 * BloodTransfusionEndFragment
 * 输血结束
 *
 * @author DevLix126
 * created at 2018/9/18 10:35
 */
public class BloodTransfusionEndFragment extends BaseFragment {
    private TextView tvBloodSure;
    private TextView tvBloodscantip;
    private ImageView imgBloodpatient;
    private View lineBlood1;
    private ImageView imgBloodbag;
    private View lineBlood2;
    private ImageView imgBloodproduct;
    private View lineBlood3;
    private ImageView imgBloodnurse;

    private TextView tvPatinfo, tvBag, tvBlood;
    private BloodNursePassView blNurseEnd;

    private String episodeId = "";


    private String RegNo = "";
    private String bloodbagId = "";
    private String bloodProductId = "";
    private String nurseId = "";
    private String bloodRowId = "";
    private String type = "";

    private String stopType = "", stopReason = "";


    //    private String episodeId = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private String patInfoStr;
    private String bloodInfoStr;

    private BloodOperationResultDialog bloodOperationResultDialog;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_end, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodtransfusionend));
        //右上角确定按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        tvBloodSure = viewright.findViewById(R.id.tv_bloodtoobar_right);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "2";
                nurseId = blNurseEnd.getNurseText();
                if (bloodRowId.equals("")) {
                    showToast("请先扫描获取血液信息");
                } else if (TextUtils.isEmpty(blNurseEnd.getNurseText())||TextUtils.isEmpty(blNurseEnd.getPassText())) {
                    showToast("请输入护士工号和密码");
                } else {
                    tvBloodscantip.setText("请选择结束类型");
                    showdialog();
                }
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);


        initView(view);
    }

    private void showdialog() {
        final EndSucessDialog showDialog = new EndSucessDialog(getActivity());
        showDialog.setYesOnclickListener("确定", new EndSucessDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                stopType = showDialog.getEndType();
                stopReason = showDialog.getReason();
                showDialog.dismiss();
                endTransInfusion(stopType, stopReason);
            }
        });
        showDialog.show();
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

        tvPatinfo = view.findViewById(R.id.tv_bloodend_patinfo);
        tvBag = view.findViewById(R.id.tv_bloodend_bloodbag);
        tvBlood = view.findViewById(R.id.tv_bloodend_bloodinfo);
        addTvChangListner(tvPatinfo);
        addTvChangListner(tvBag);
        addTvChangListner(tvBlood);
        blNurseEnd = view.findViewById(R.id.bl_nurseinfo_end);
        blNurseEnd.setListener(new BloodNursePassView.Listener() {
            @Override
            public void update(String string) {
                changeScanTipText();
            }
        });

    }

    private void endTransInfusion(String stopType, String stopReason) {

        BloodTSApiManager.bloodTransEnd(bloodRowId, nurseId, stopReason, stopType, type,blNurseEnd.getPassText(), new BloodTSApiManager.BloodOperationResultCallback() {
            @Override
            public void onSuccess(BloodOperationResultBean bloodOperationResult) {
                if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                    bloodOperationResultDialog.dismiss();
                }

                bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());

                bloodOperationResultDialog.setExecresult("输注结束成功");

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
                            blNurseEnd.setNurseText("");
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

    private void cleanAll() {
        RegNo = "";
        tvPatinfo.setText("");
        bloodbagId = "";
        tvBag.setText("");
        bloodProductId = "";
        tvBlood.setText("");
        nurseId = "";
        blNurseEnd.setNurseText(null);
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

    private void scanPatData(String reg) {

        BloodTSApiManager.getPatWristInfo(reg, new BloodTSApiManager.GetPatWristInfoCallback() {
            @Override
            public void onSuccess(PatWristInfoBean patWristInfoBean) {
                PatWristInfoBean.PatInfoBean patInfo = patWristInfoBean.getPatInfo();
                episodeId = patInfo.getEpisodeID();
                //                String loc = patInfo.getCtLocDesc().equals("") ? "" : patInfo.getCtLocDesc() + "-";
                //                String room = patInfo.getRoomDesc().equals("") ? "" : patInfo.getRoomDesc() + "-";
                //                String bedCode = patInfo.getBedCode().equals("") ? "未分床-" : patInfo.getBedCode() + "-";
                //                String name = patInfo.getName();
                //                patInfoStr = loc + room + bedCode + name;
                patInfoStr = patInfo.getCtLocDesc() + "-" + patInfo.getBedCode() + "-" + patInfo.getName() + "-" + patInfo.getRegNo();

                tvPatinfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void scanInfusionData(String bloodbag, String bloodProduct) {

        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbag, bloodProduct, "E", new BloodTSApiManager.GetBloodInfoCallback() {
            @Override
            public void onSuccess(BloodInfoBean bloodInfoBean) {
                BloodInfoBean.BlooInfoBean blooInfo = bloodInfoBean.getBlooInfo();
                String bloodId = blooInfo.getBloodProductId().equals("") ? "" : blooInfo.getBloodProductId() + "-";
                String bloodDesc = blooInfo.getProductDesc().equals("") ? "" : blooInfo.getProductDesc() + "-";
                String bloodGroup = blooInfo.getBloodGroup().equals("") ? "" : blooInfo.getBloodGroup();
                bloodInfoStr = bloodId + bloodDesc + bloodGroup;
                bloodProductId = bloodId + bloodDesc + bloodGroup;
                tvBlood.setText(bloodInfoStr);
                bloodRowId = blooInfo.getBloodRowId();
                patInfoStr = patInfoStr + "-" + bloodGroup;
                tvPatinfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                bloodbagId = "";
                bloodProductId = "";
                tvBag.setText("");
                tvBlood.setText("");
                showToast("error" + code + ":" + msg);
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
                    tvBag.setText(bloodbagId);
                    imgBloodbag.setSelected(true);
                    lineBlood2.setSelected(true);
                    //                        tvBloodscantip.setText("请扫描血制品编号");
                    scanInfusionData(bloodbagId, "");
                } else if (nurseId.equals("")) {
                    nurseId = dataStr;
                    blNurseEnd.setNurseText(nurseId);
                    blNurseEnd.setPassText(" ");
                    tvBloodscantip.setText("请选择结束类型");
                    type = "1";
                    showdialog();
                }
            } else if (SPUtils.getInstance().getString(SharedPreference.BLOODSCANTIMES, "2").equals("2")) {
                if (bloodbagId.equals("") && (!episodeId.equals(""))) {
                    bloodbagId = dataStr;
                    tvBag.setText(bloodbagId);
                } else if (bloodProductId.equals("")) {
                    bloodProductId = dataStr;
                    scanInfusionData(bloodbagId, dataStr);
                } else if (nurseId.equals("")) {
                    nurseId = dataStr;
                    blNurseEnd.setNurseText(nurseId);
                    blNurseEnd.setPassText(" ");
                    tvBloodscantip.setText("请选择结束类型");
                    type = "1";
                    showdialog();
                }
            }

        }
    }
    private void changeScanTipText() {
        tvBloodSure.setSelected(false);
        if (!tvPatinfo.getText().toString().equals("")){
            imgBloodpatient.setSelected(true);
            lineBlood1.setSelected(true);
        }else {
            imgBloodpatient.setSelected(false);
            lineBlood1.setSelected(false);
            tvBloodscantip.setText("请扫描患者腕带");
            return;
        }
        if (!tvBag.getText().toString().equals("")){
            imgBloodbag.setSelected(true);
            lineBlood2.setSelected(true);
        }else {
            tvBloodscantip.setText("请扫描血袋条码");
            imgBloodbag.setSelected(false);
            lineBlood2.setSelected(false);
            return;
        }
        if (!tvBlood.getText().toString().equals("")){
            imgBloodproduct.setSelected(true);
            lineBlood3.setSelected(true);
        }else {
            imgBloodproduct.setSelected(false);
            lineBlood3.setSelected(false);
            tvBloodscantip.setText("请扫描血制品条码");
            return;
        }

        if (blNurseEnd.getNurseText().length() > 0 && blNurseEnd.getPassText().length() > 0) {
            imgBloodnurse.setSelected(true);
            tvBloodSure.setSelected(true);
            tvBloodscantip.setText("请点击确定开始血液输注");
        } else{
            imgBloodnurse.setSelected(false);
            imgBloodnurse.setSelected(false);
            tvBloodscantip.setText("请扫描/输入护士工牌");
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
