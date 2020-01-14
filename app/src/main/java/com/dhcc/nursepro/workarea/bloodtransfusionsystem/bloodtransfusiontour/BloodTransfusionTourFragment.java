package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.utils.KeyBoardUtil;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationResultDialog;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.api.BloodTSApiManager;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodTemDataBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

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
    private Button btnExist;
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


    private HashMap<String, View> viewItemMap = new HashMap<>();
    private LinearLayout llVitalsignRecord;
    private BloodTemDataBean recordInfo;
    private KeyboardView mKeyboard;

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
                    String result = "";
                    if (recordInfo != null) {
                        ArrayList<HashMap<String, String>> resList = new ArrayList();

                        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
                            BloodTemDataBean.TempListBean temp = recordInfo.getTempList().get(i);
                            BloodTemDataBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
                            String code = temp.getCode();
                            View view = viewItemMap.get(temp.getCode());
                            String value = "";

                            if (view instanceof EditText) {
                                EditText edText = (EditText) view;
                                value = edText.getText().toString();

                                //检查输入数据范围
                                if (config.getNormalValueRangFrom() != null) {
                                    if (config.getErrorValueLowTo() != null) {
                                        if (isNumber(edText.getText().toString() + "")) {
                                            double edDou = Double.parseDouble(edText.getText().toString());
                                            if (edDou >= Double.parseDouble(config.getErrorValueHightFrom()) || edDou <= Double.parseDouble(config.getErrorValueLowTo())) {
                                                showToast("请检查输入数值");
                                                return;
                                            }
                                        }
                                    }
                                }

                            } else {
                                OptionView op = (OptionView) view;
                                value = op.getText().toString();
                            }

                            value = value.trim();

                            HashMap<String, String> resItem = new HashMap<>();
                            resItem.put("code", code);
                            resItem.put("value", value);

                            resList.add(resItem);
                        }

                        Gson gson = new Gson();
                        result = gson.toJson(resList);
                    }

                    BloodTSApiManager.bloodPatrol(bloodRowId, recdate, rectime, speed, effect, situation,result, new BloodTSApiManager.BloodOperationResultCallback() {
                        @Override
                        public void onSuccess(BloodOperationResultBean bloodOperationResult) {

                            showToast("输血巡视保存成功");
                            setToolbarRightView(0);
                            clearEditInfo();
                            llBloodtranstourScan.setVisibility(View.VISIBLE);
                            llBloodtranstourEdit.setVisibility(View.GONE);
                            llVitalsignRecord.removeAllViews();
                            viewItemMap.clear();
                            if (mKeyboard != null){
                                mKeyboard.setVisibility(View.GONE);
                            }
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
        llVitalsignRecord = view.findViewById(R.id.ll_vitalsign_record);
        mKeyboard = view.findViewById(R.id.ky_keyboard);

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
                if ("请点击选择时间".equals(tvBloodtranstourTranstime.getText().toString())) {
                    chooseTime(TimeUtils.string2Millis(SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME)+":00"));
                } else {
                    chooseTime(TimeUtils.string2Millis(tvBloodtranstourTranstime.getText().toString() + ":00"));
                }
            }
        });
        tvBloodtranstourTranstime = view.findViewById(R.id.tv_bloodtranstour_transtime);
        tvBloodtranstourTranstime.setText(DateUtils.getDateTimeFromSystem());

        recdate = DateUtils.getDateTimeFromSystem().substring(0, 10);
        rectime = DateUtils.getDateTimeFromSystem().substring(11, 16);
        tvBloodtranstourNursename = view.findViewById(R.id.tv_bloodtranstour_nursename);
        etBloodtranstourBloodtransrate = view.findViewById(R.id.et_bloodtranstour_bloodtransrate);
        tvBloodtranstourIsexist = view.findViewById(R.id.tv_bloodtranstour_isexist);
        switchBloodtranstourIsexist = view.findViewById(R.id.switch_bloodtranstour_isexist);
        btnExist = view.findViewById(R.id.btn_isexist);
        btnExist.setSelected(false);
        etBloodtranstourAdversereactions = view.findViewById(R.id.et_bloodtranstour_adversereactions);
        etBloodtranstourAdversereactions.setFocusable(false);

        btnExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnExist.isSelected()){
                    btnExist.setSelected(false);
                    tvBloodtranstourIsexist.setText("无");
                    tvBloodtranstourIsexist.setSelected(false);
                    etBloodtranstourAdversereactions.setText("");
                    etBloodtranstourAdversereactions.setFocusable(false);
                }else {
                    btnExist.setSelected(true);
                    tvBloodtranstourIsexist.setText("有");
                    tvBloodtranstourIsexist.setSelected(true);
                    etBloodtranstourAdversereactions.setFocusable(true);
                    etBloodtranstourAdversereactions.setFocusableInTouchMode(true);
                }
            }
        });
        switchBloodtranstourIsexist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvBloodtranstourIsexist.setText("有");
                    tvBloodtranstourIsexist.setSelected(true);
                    etBloodtranstourAdversereactions.setFocusable(true);
                    etBloodtranstourAdversereactions.setFocusableInTouchMode(true);
                } else {
                    tvBloodtranstourIsexist.setText("无");
                    tvBloodtranstourIsexist.setSelected(false);
                    etBloodtranstourAdversereactions.setText("");
                    etBloodtranstourAdversereactions.setFocusable(false);
                }
            }
        });

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
        btnExist.setSelected(false);
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
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanStr = bundle.getString("data");

            if (SPUtils.getInstance().getString(SharedPreference.BLOODSCANTIMES).equals("1")) {
                bloodbagId = scanStr;
                tvBloodtranstourBloodbaginfo.setText(bloodbagId);
                //                    tvBloodscantip.setText("请扫描血制品条码");
                imgBloodbag.setSelected(true);
                lineBlood1.setSelected(true);
                getBloodInfo(bloodbagId, "");
            } else {
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
                    getBloodInfo(bloodbagId, bloodProductId);
                }
            }

        }

    }


    private void getBloodInfo(String bloodbag, String bloodProduct) {
        BloodTSApiManager.getBloodInfo(bloodbag, bloodProduct, new BloodTSApiManager.GetBloodInfoCallback() {
            @Override
            public void onSuccess(BloodInfoBean bloodInfoBean) {
                bloodInfo = bloodInfoBean.getBlooInfo();
                bloodRowId = bloodInfo.getBloodRowId();
                tvBloodtranstourBloodproductinfo.setText(bloodProductId + "-" + bloodInfo.getProductDesc() + "-" + bloodInfo.getBloodGroup());
                tvBloodtranstourBloodpatientinfo.setText(bloodInfo.getWardDesc() + "-" + bloodInfo.getBedCode() + "-" + bloodInfo.getPatName() + "-" + bloodInfo.getPatientId() + "-" + bloodInfo.getBloodGroup());
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
                        firstBeHosTemData(bloodInfoBean.getBlooInfo().getEpisodeId());

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

    private void firstBeHosTemData(String episodeId) {
        BloodTSApiManager.getBloodTemData(episodeId, new BloodTSApiManager.BloodTemDataCallback() {
            @Override
            public void onSuccess(BloodTemDataBean bloodTemDataBean) {
                if (recordInfo != null) {
                    llVitalsignRecord.removeAllViews();
                    viewItemMap.clear();
                }
                recordInfo = bloodTemDataBean;
                drawInputItems();
                inputItemsValue();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    /**
     * 绘制UI相关
     */

    public void drawInputItems() {

        LinearLayout layout = null;

        int size = recordInfo.getTempConfig().size();

        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                layout = new LinearLayout(getContext());
            }

            BloodTemDataBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
            LinearLayout item = drawItem(config);
            layout.addView(item);

            if (i == size - 1 && i % 3 == 0) {
                layout.addView(dramEmptyItem());
                layout.addView(dramEmptyItem());
            }

            if (i == size - 1 && i % 3 == 1) {
                layout.addView(dramEmptyItem());
            }

            if (i == size - 1 || i % 3 == 2) {
                llVitalsignRecord.addView(layout);
            }
        }

    }

    private void inputItemsValue() {

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            BloodTemDataBean.TempListBean temp = recordInfo.getTempList().get(i);
            View view = viewItemMap.get(temp.getCode());
            if (view instanceof EditText) {
                EditText ed = (EditText) view;
                ed.setText(temp.getValue());
            } else {
                OptionView op = (OptionView) view;
                op.setText(temp.getValue());
            }
        }
    }

    private LinearLayout drawItem(BloodTemDataBean.TempConfigBean config) {

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(110);
        int width = 0;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;

        layout.setLayoutParams(params);


        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getDesc());
        //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        if (config.getDesc().length() > 7) {
            titleTV.setTextSize(12);
        } else {
            titleTV.setTextSize(16);
        }

        titleTV.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, ConvertUtils.dp2px(20), 0, 0);//4个参数按顺序分别是左上右下
        titleTV.setLayoutParams(titleParams);

        layout.addView(titleTV);


        if (config.getSelect().equals("false")) {
            //非选择框

            EditText edText = new EditText(getContext());
            edText.setGravity(Gravity.CENTER);
            edText.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);

            if (config.getValueType() != null && config.getValueType().equals("N")) {
                edText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(10), ConvertUtils.dp2px(11), ConvertUtils.dp2px(10), ConvertUtils.dp2px(15));//4个参数按顺序分别是左上右下

            edText.setLayoutParams(layoutParams);

            edText.setPadding(10, 10, 10, 10);

            if (config.getSymbol() != null) {
                if (config.getSymbol().size() > 0) {
                    edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                    edText.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            symbol((ArrayList<String>) config.getSymbol(), edText);
                            return false;
                        }
                    });
                }
            }
            edText.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if (v.hasFocus()){
                        if (titleTV.getText().toString().contains("℃")) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);
                            new KeyBoardUtil(mKeyboard, edText).showKeyboard();
                        } else {
                            mKeyboard.setVisibility(View.GONE);
                        }
                    }
                }
            });
            edText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        if (titleTV.getText().toString().contains("℃")) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);
                            new KeyBoardUtil(mKeyboard, edText).showKeyboard();
                        } else {
                            mKeyboard.setVisibility(View.GONE);
                        }
                    }
                }
            });
            edText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (config.getNormalValueRangFrom() != null) {
                        if (config.getErrorValueLowTo() != null) {
                            if (isNumber(edText.getText().toString() + "")) {
                                double edDou = Double.parseDouble(edText.getText().toString());
                                if (edDou >= Double.parseDouble(config.getErrorValueHightFrom()) || edDou <= Double.parseDouble(config.getErrorValueLowTo())) {
                                    edText.setBackground(getResources().getDrawable(R.drawable.vital_sign_inputerror_bg));
                                } else if ((Double.parseDouble(config.getErrorValueLowTo()) < edDou && edDou < Double.parseDouble(config.getNormalValueRangFrom()))
                                        || (edDou < Double.parseDouble(config.getErrorValueHightFrom()) && edDou > Double.parseDouble(config.getNormalValueRangTo()))) {
                                    edText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                } else {
                                    edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                }
                            } else {
                                edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                if (config.getSymbol() != null) {
                                    if (config.getSymbol().size() > 0) {
                                        edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                    }
                                }
                            }
                            ;
                        } else {
                            if (isNumber(edText.getText().toString() + "")) {
                                double edDou = Double.parseDouble(edText.getText().toString());
                                if (edDou < Double.parseDouble(config.getNormalValueRangFrom())
                                        || edDou > Double.parseDouble(config.getNormalValueRangTo())) {
                                    edText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                } else {
                                    edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                }
                            } else {
                                edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                if (config.getSymbol() != null) {
                                    if (config.getSymbol().size() > 0) {
                                        edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                    }
                                }
                            }
                        }


                    }

                }
            });

            layout.addView(edText);

            viewItemMap.put(config.getCode(), edText);


        } else {
            //选择框
            config.getOptions().add("删除");
            final OptionView optionView = new OptionView(getActivity(), config.getOptions());

            optionView.setTextSize(16);
            optionView.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            optionView.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(10), ConvertUtils.dp2px(11), ConvertUtils.dp2px(10), 45);//4个参数按顺序分别是左上右下
            optionView.setLayoutParams(layoutParams);

            optionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKeyboard.setVisibility(View.GONE);
                    optionView.picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                        @Override
                        public void onOptionPicked(int index, String item) {
                            //                            config.setSendValue(config.getItemCode() + "|" + item);
                            if (item.equals("删除")) {
                                optionView.setText("");
                            } else {
                                optionView.setText(item);
                            }
                        }
                    });
                    optionView.showPicker();
                }
            });

            layout.addView(optionView);
            viewItemMap.put(config.getCode(), optionView);

        }


        return layout;
    }

    private LinearLayout dramEmptyItem() {
        LinearLayout layout = new LinearLayout(getContext());

        int height = ConvertUtils.dp2px(120);
        int width = ConvertUtils.dp2px(0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;

        layout.setLayoutParams(params);

        return layout;
    }

    private void symbol(ArrayList<String> list, EditText editText) {
        String[] locDesc = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            locDesc[i] = list.get(i);
        }

        final OptionPicker picker = new OptionPicker(getActivity(), locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                editText.setText(item);
            }
        });
        picker.show();
    }

    public boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_transfusion_tour, container, false);
    }
}
