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
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
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
public class BloodTransfusionEndFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvPatinfo, tvBag, tvBlood;
    private EditText tvNurse;
    private TextView tvSure;

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


        initView(view);

        mReceiver = new Receiver();
        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);
    }

    private void initView(View view) {

        tvPatinfo = view.findViewById(R.id.tv_bloodend_patinfo);
        tvBag = view.findViewById(R.id.tv_bloodend_bloodbag);
        tvBlood = view.findViewById(R.id.tv_bloodend_bloodinfo);
        tvNurse = view.findViewById(R.id.tv_bloodend_nurse);
        tvSure = view.findViewById(R.id.tv_bloodend_sure);
        tvSure.setOnClickListener(this);

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
                    tvNurse.setSelected(true);
                } else {
                    tvNurse.setSelected(false);
                }
            }
        });


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
                tvPatinfo.setSelected(true);
            }

            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast(code + ":" + msg);
                tvPatinfo.setSelected(false);
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
                tvBlood.setSelected(true);
                patInfoStr = patInfoStr + "-" + bloodGroup;
                tvPatinfo.setText(patInfoStr);
            }

            @Override
            public void onFail(String code, String msg) {
                bloodbagId = "";
                bloodProductId = "";
                tvBag.setText("请重新扫描血袋条码");
                tvBlood.setText("请重新扫描血制品条码");
                tvBag.setSelected(false);
                tvBlood.setSelected(false);
                showToast(code + ":" + msg);
            }
        });
    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bloodend_sure:
                type = "2";
                nurseId = tvNurse.getText().toString();
                if (bloodRowId.equals("")) {
                    showToast("请先扫描获取血信息");
                } else if (TextUtils.isEmpty(tvNurse.getText())) {
                    showToast("请输入护士工号");
                } else {
                    showdialog();
                }
                break;
            default:
                break;

        }
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
                        tvBag.setSelected(true);
                    } else if (bloodProductId.equals("")) {
                        bloodProductId = dataStr;
                        scanInfusionData(bloodbagId, dataStr);
                    } else if (nurseId.equals("")) {
                        nurseId = dataStr;
                        tvNurse.setText(nurseId);
                        type = "1";
                        showdialog();
                    }

                    break;
                default:
                    break;
            }
        }
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

                nurseId = "";
                tvNurse.setText("");
                tvNurse.setSelected(false);
                showToast(code + ":" + msg);
            }
        });

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


    private void cleanAll() {
        RegNo = "";
        tvPatinfo.setText("请扫描患者腕带条码");
        tvPatinfo.setSelected(false);
        bloodbagId = "";
        tvBag.setText("请扫描血袋条码");
        tvBag.setSelected(false);
        bloodProductId = "";
        tvBlood.setText("请扫描血制品条码");
        tvBlood.setSelected(false);
        nurseId = "";
        tvNurse.setText(null);
        tvNurse.setSelected(false);
        episodeId = "";
        bloodRowId = "";
    }

}
