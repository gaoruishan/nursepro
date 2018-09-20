package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.BloodTransfusionFragment;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.api.BloodTransfusionApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.GetInfusionBloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.GetPatWristInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.bean.StartTransfusionBean;

import java.util.HashMap;
import java.util.Objects;

/**
 * BloodTransfusionEndFragment
 * 输血结束
 *
 * @author DevLix126
 * created at 2018/9/18 10:35
 */
public class BloodTransfusionEndFragment extends BaseFragment implements View.OnClickListener{
    private TextView tvPatinfo,tvBag,tvBlood;
    private EditText tvNurse;
    private TextView tvSure;

    private IntentFilter filter;
    private Receiver mReceiver = null;
    private String episodeId = "";


    private String RegNo = "";
    private String bloodbagId = "";
    private String bloodProductId = "";
    private String nurseId = "";
    private String bloodRowId="";
    private String type = "";

    private String stopType = "",stopReason = "";


    //    private String episodeId = "";
    private SPUtils spUtils = SPUtils.getInstance();

    private String patInfo;
    private String bloodInfo;

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

        mReceiver  = new Receiver();
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
                if (tvNurse.getText().toString().length()>0){
                    tvNurse.setSelected(true);
                } else {
                    tvNurse.setSelected(false);
                }
            }
        });



    }

    @Override
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
    private void scanPatData(String reg){
        HashMap<String,String> map = new HashMap<>();
        map.put("regNo",reg);
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        BloodTransfusionApiManager.getScanPatsInfo(map, "getPatWristInfo", new BloodTransfusionApiManager.getScanPatsInfoCallBack() {
            @Override
            public void onSuccess(GetPatWristInfoBean getPatWristInfoBean) {
                episodeId = getPatWristInfoBean.getPatInfo().getEpisodeID();
                String loc = getPatWristInfoBean.getPatInfo().getCtLocDesc().equals("")?"": getPatWristInfoBean.getPatInfo().getCtLocDesc()+"-";
                String room = getPatWristInfoBean.getPatInfo().getRoomDesc().equals("")?"": getPatWristInfoBean.getPatInfo().getRoomDesc()+"-";
                String bedCode = getPatWristInfoBean.getPatInfo().getBedCode().equals("")?"未分床-": getPatWristInfoBean.getPatInfo().getBedCode()+"床-";
                String name = getPatWristInfoBean.getPatInfo().getName();
                patInfo = loc+room+bedCode+name;
                tvPatinfo.setText(patInfo);
                tvPatinfo.setSelected(true);
            }
            @Override
            public void onFail(String code, String msg) {
                RegNo = "";
                showToast(code+":"+msg);
                tvPatinfo.setSelected(false);
            }
        });

    }

    private void scanInfusionData(String bloodbag,String bloodProduct){
        HashMap<String,String> map = new HashMap<>();
        map.put("episodeId",episodeId);
        map.put("bloodbagId",bloodbag);
        map.put("bloodProductId",bloodProduct);
        map.put("type","E");
        BloodTransfusionApiManager.getScanInfusionInfo(map, "getInfusionBloodInfo", new BloodTransfusionApiManager.getScanInfusionInfoCallBack() {
            @Override
            public void onSuccess(GetInfusionBloodInfoBean getInfusionBloodInfoBean) {
//                bloodInfo = getInfusionBloodInfoBean.getBlooInfo().getBloodProductId();
                String bloodId = getInfusionBloodInfoBean.getBlooInfo().getBloodProductId().equals("")?"": getInfusionBloodInfoBean.getBlooInfo().getBloodProductId()+"-";
                String bloodDesc = getInfusionBloodInfoBean.getBlooInfo().getProductDesc().equals("")?"": getInfusionBloodInfoBean.getBlooInfo().getProductDesc()+"-";
                String bloodGroup = getInfusionBloodInfoBean.getBlooInfo().getBloodGroup().equals("")?"": getInfusionBloodInfoBean.getBlooInfo().getBloodGroup();
                bloodInfo = bloodId+bloodDesc+bloodGroup;
                bloodProductId = bloodId+bloodDesc+bloodGroup;
                tvBlood.setText(bloodInfo);
                bloodRowId = getInfusionBloodInfoBean.getBlooInfo().getBloodRowId();
                tvBlood.setSelected(true);
                patInfo = patInfo+"-"+bloodGroup;
                tvPatinfo.setText(patInfo);
            }

            @Override
            public void onFail(String code, String msg) {
                bloodbagId = "";
                bloodProductId = "";
                tvBag.setText("请重新扫描血袋条码");
                tvBlood.setText("请重新扫描血制品条码");
                tvBag.setSelected(false);
                tvBlood.setSelected(false);
                showToast(code+":"+msg);
            }
        });
    }

    private void endTransInfusion(String stopType,String stopReason){
        HashMap<String,String> map = new HashMap<>();
        map.put("bloodRowId",bloodRowId);
        map.put("userId",nurseId);
        map.put("StopReasonDesc",stopReason);
        map.put("endType",stopType);
        map.put("type",type);
        BloodTransfusionApiManager.getScanStartInfo(map, "endTransfusion", new BloodTransfusionApiManager.getScanStartCallBack() {
            @Override
            public void onSuccess(StartTransfusionBean startTransfusionBean) {

//                showToast(startTransfusionBean.getMsg());
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
                showToast(code+":"+msg);
            }
        });

    }
    private void showdialog(){
        final EndSucessDialog showDialog = new EndSucessDialog(getActivity());
        showDialog.setYesOnclickListener("确定", new EndSucessDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                stopType = showDialog.getEndType();
                stopReason = showDialog.getReason();
                showDialog.dismiss();
                endTransInfusion(stopType,stopReason);
            }
        });
        showDialog.show();
    }


    private void cleanAll(){
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
        bloodRowId="";
    }

}
