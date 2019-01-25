package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Calendar;
import java.util.Objects;

/**
 * BloodTransfusionTourFragment
 * 输血巡视
 *
 * @author DevLix126
 * created at 2018/9/18 10:36
 */
public class BloodTransfusionTourFragment extends BaseFragment implements OnDateSetListener {
    private TextView tvBloodscantip;
    private ImageView imgBloodbag;
    private View lineBlood1;
    private ImageView imgBloodproduct;
    private View lineBlood2;
    private ImageView imgBloodpatient;

    private LinearLayout llBloodtranstourScan;
    private TextView tvBloodtranstourBloodbaginfo;
    private TextView tvBloodtranstourBloodproductinfo;
    private TextView tvBloodtranstourBloodpatientinfo;
    private LinearLayout llBloodtranstourEdit;
    private LinearLayout llBloodtranstourSelecttime;
    private TextView tvBloodtranstourTranstime;
    private TextView tvBloodtranstourNursename;
    private EditText etBloodtranstourBloodtransrate;
    private TextView tvBloodtranstourIsexist;
    private SwitchCompat switchBloodtranstourIsexist;
    private EditText etBloodtranstourAdversereactions;

    private String bloodbagId;
    private String bloodProductId;
    private BloodInfoBean.BlooInfoBean bloodInfo;

    /**
     * scanOrder
     * 1    血袋
     * 2    血制品
     */
    private int scanOrder = 1;

    private String bloodRowId = "";
    private String recdate = "";
    private String rectime = "";
    private String speed = "";
    private String effect = "无";
    private String situation = "";

    private BloodOperationResultDialog bloodOperationResultDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodtransfusiontour));
        setToolbarRightView(0);
        setToolbarBottomLineVisibility(false);

        initView(view);
    }

    private void setToolbarRightView(int i) {
        if (i == 0) {
            //右上角按钮
            //            View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
            //            viewright.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) {
            //                    showToast("今日列表");
            //                }
            //            });
            //            setToolbarRightCustomView(viewright);
            setToolbarRightCustomView(null);
        } else {
            //右上角保存按钮
            View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
            TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
            textView.setTextSize(15);
            textView.setText("   保存   ");
            textView.setTextColor(getResources().getColor(R.color.blue_dark));
            viewright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speed = etBloodtranstourBloodtransrate.getText().toString();
                    situation = etBloodtranstourAdversereactions.getText().toString();
                    effect = tvBloodtranstourIsexist.getText().toString();
                    if (TextUtils.isEmpty(recdate) || TextUtils.isEmpty(rectime)) {
                        showToast("请选择输血巡视时间");
                        return;
                    }

                    if (TextUtils.isEmpty(speed)) {
                        showToast("请输入输血速度");
                        return;
                    }

                    if ("有".equals(effect) && TextUtils.isEmpty(situation)) {
                        showToast("请填写患者不良反应状况");
                        return;
                    }

                    BloodTSApiManager.bloodPatrol(bloodRowId, recdate, rectime, speed, effect, situation, new BloodTSApiManager.BloodOperationResultCallback() {
                        @Override
                        public void onSuccess(BloodOperationResultBean bloodOperationResult) {
                            showToast("输血巡视保存成功");
                            setToolbarRightView(0);
                            clearEditInfo();
                            llBloodtranstourScan.setVisibility(View.VISIBLE);
                            llBloodtranstourEdit.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFail(String code, String msg) {
                            showToast("error" + code + ":" + msg);

                        }
                    });


                }
            });
            setToolbarRightCustomView(viewright);
        }
    }

    private void initView(View view) {
        tvBloodscantip = view.findViewById(R.id.tv_bloodscantip);
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag = view.findViewById(R.id.img_bloodbag);
        lineBlood1 = view.findViewById(R.id.line_blood_1);
        imgBloodproduct = view.findViewById(R.id.img_bloodproduct);
        lineBlood2 = view.findViewById(R.id.line_blood_2);
        imgBloodpatient = view.findViewById(R.id.img_bloodpatient);

        llBloodtranstourScan = view.findViewById(R.id.ll_bloodtranstour_scan);
        tvBloodtranstourBloodbaginfo = view.findViewById(R.id.tv_bloodtranstour_bloodbaginfo);
        tvBloodtranstourBloodproductinfo = view.findViewById(R.id.tv_bloodtranstour_bloodproductinfo);
        tvBloodtranstourBloodpatientinfo = view.findViewById(R.id.tv_bloodtranstour_bloodpatientinfo);
        llBloodtranstourEdit = view.findViewById(R.id.ll_bloodtranstour_edit);

        llBloodtranstourSelecttime = view.findViewById(R.id.ll_bloodtranstour_selecttime);
        llBloodtranstourSelecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvBloodtranstourTranstime.getText().toString().contains("0")) {
                    chooseTime(TimeUtils.string2Millis(tvBloodtranstourTranstime.getText().toString() + " 00:00:00"));
                } else {
                    chooseTime(TimeUtils.string2Millis(SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).replace("/", "-").replace(",", " ")));
                }
            }
        });
        tvBloodtranstourTranstime = view.findViewById(R.id.tv_bloodtranstour_transtime);
        tvBloodtranstourNursename = view.findViewById(R.id.tv_bloodtranstour_nursename);
        etBloodtranstourBloodtransrate = view.findViewById(R.id.et_bloodtranstour_bloodtransrate);
        tvBloodtranstourIsexist = view.findViewById(R.id.tv_bloodtranstour_isexist);
        switchBloodtranstourIsexist = view.findViewById(R.id.switch_bloodtranstour_isexist);
        switchBloodtranstourIsexist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvBloodtranstourIsexist.setText("有");
                    tvBloodtranstourIsexist.setSelected(true);
                    etBloodtranstourAdversereactions.setEnabled(true);
                } else {
                    tvBloodtranstourIsexist.setText("无");
                    tvBloodtranstourIsexist.setSelected(false);
                    etBloodtranstourAdversereactions.setEnabled(false);
                }
            }
        });
        etBloodtranstourAdversereactions = view.findViewById(R.id.et_bloodtranstour_adversereactions);

    }

    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    private void clearScanInfo() {
        tvBloodscantip.setText("请扫描血袋条码");
        imgBloodbag.setSelected(false);
        lineBlood1.setSelected(false);
        imgBloodproduct.setSelected(false);
        lineBlood2.setSelected(false);
        imgBloodpatient.setSelected(false);
        scanOrder = 1;
        tvBloodtranstourBloodbaginfo.setText("");
        tvBloodtranstourBloodproductinfo.setText("");
        tvBloodtranstourBloodpatientinfo.setText("");
    }

    private void clearEditInfo() {
        tvBloodtranstourTranstime.setText("请点击选择时间");
        etBloodtranstourBloodtransrate.setText("");
        tvBloodtranstourIsexist.setSelected(false);
        switchBloodtranstourIsexist.setChecked(false);
        etBloodtranstourAdversereactions.setText("");
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        recdate = TimeUtils.millis2String(millseconds).substring(0, 10);
        rectime = TimeUtils.millis2String(millseconds).substring(11, 16);
        tvBloodtranstourTranstime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        switch (Objects.requireNonNull(intent.getAction())) {

            case Action.DEVICE_SCAN_CODE:
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                String scanStr = bundle.getString("data");

                if (scanOrder == 1) {
                    scanOrder++;
                    bloodbagId = scanStr;
                    tvBloodtranstourBloodbaginfo.setText(bloodbagId);
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
                            bloodRowId = bloodInfo.getBloodRowId();
                            tvBloodtranstourBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                            tvBloodtranstourBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getBloodGroup());
                            tvBloodscantip.setText("请填写输血巡视内容");
                            imgBloodproduct.setSelected(true);
                            lineBlood2.setSelected(true);
                            imgBloodpatient.setSelected(true);

                            if (bloodOperationResultDialog != null && bloodOperationResultDialog.isShowing()) {
                                bloodOperationResultDialog.dismiss();
                            }

                            bloodOperationResultDialog = new BloodOperationResultDialog(getActivity());

                            bloodOperationResultDialog.setExecresult("血袋信息获取成功");

                            bloodOperationResultDialog.setImgId(R.drawable.icon_popup_sucess);
                            bloodOperationResultDialog.setSureVisible(View.GONE);
                            bloodOperationResultDialog.show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    llBloodtranstourEdit.setVisibility(View.VISIBLE);
                                    setToolbarRightView(1);
                                    tvBloodtranstourNursename.setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
                                    llBloodtranstourScan.setVisibility(View.GONE);
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

                break;
            default:
                break;
        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_tour, container, false);
    }
}
