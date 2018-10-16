package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend;


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
    private EditText tvNurse;

    private IntentFilter filter;
    private Receiver mReceiver = null;
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
                nurseId = tvNurse.getText().toString();
                if (bloodRowId.equals("")) {
                    showToast("请先扫描获取血液信息");
                } else if (TextUtils.isEmpty(tvNurse.getText())) {
                    showToast("请输入护士工号");
                } else {
                    tvBloodscantip.setText("请选择结束类型");
                    showdialog();
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
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        getActivity().unregisterReceiver(mReceiver);
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
        tvNurse = view.findViewById(R.id.tv_bloodend_nurse);

        tvNurse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvNurse.getText().toString().length() > 0) {
                    imgBloodnurse.setSelected(true);
                    tvNurse.setSelected(true);
                    tvBloodSure.setSelected(true);
                    tvBloodscantip.setText("请点击确定结束血液输注");
                } else {
                    nurseId = "";
                    imgBloodnurse.setSelected(false);
                    tvNurse.setSelected(false);
                    tvBloodSure.setSelected(false);
                    tvBloodscantip.setText("请扫描/输入护士工牌");
                }
            }
        });


    }

    private void endTransInfusion(String stopType, String stopReason) {

        BloodTSApiManager.bloodTransEnd(bloodRowId, nurseId, stopReason, stopType, type, new BloodTSApiManager.BloodOperationResultCallback() {
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
                        cleanAll();
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
        tvNurse.setText(null);
        tvNurse.setSelected(false);
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
                String loc = patInfo.getCtLocDesc().equals("") ? "" : patInfo.getCtLocDesc() + "-";
                String room = patInfo.getRoomDesc().equals("") ? "" : patInfo.getRoomDesc() + "-";
                String bedCode = patInfo.getBedCode().equals("") ? "未分床-" : patInfo.getBedCode() + "床-";
                String name = patInfo.getName();
                patInfoStr = loc + room + bedCode + name;
                tvPatinfo.setText(patInfoStr);
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

        BloodTSApiManager.getInfusionBloodInfo(episodeId, bloodbagId, bloodProductId, "E", new BloodTSApiManager.GetBloodInfoCallback() {
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

                imgBloodproduct.setSelected(true);
                lineBlood3.setSelected(true);
                tvBloodscantip.setText("请扫描/输入护士工牌");
            }

            @Override
            public void onFail(String code, String msg) {
                bloodbagId = "";
                bloodProductId = "";
                tvBag.setText("");
                tvBlood.setText("");
                showToast("error" + code + ":" + msg);

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
                        tvBag.setText(bloodbagId);
                        imgBloodbag.setSelected(true);
                        lineBlood2.setSelected(true);
                        tvBloodscantip.setText("请扫描血制品编号");
                    } else if (bloodProductId.equals("")) {
                        bloodProductId = dataStr;
                        scanInfusionData(bloodbagId, dataStr);
                    } else if (nurseId.equals("")) {
                        nurseId = dataStr;
                        tvNurse.setText(nurseId);
                        tvNurse.setSelected(true);
                        imgBloodnurse.setSelected(true);
                        tvBloodscantip.setText("请选择结束类型");
                        type = "1";
                        showdialog();
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
