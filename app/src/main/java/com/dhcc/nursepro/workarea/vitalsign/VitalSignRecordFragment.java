package com.dhcc.nursepro.workarea.vitalsign;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.KeyBoardUtil;
import com.base.commlibs.voiceUtils.bean.VoiceBean;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignPatBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignRecordBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignChartsDetailFragment;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignDetailFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class VitalSignRecordFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {


    private TextView et_time;

    private TextView tv_pre;
    private TextView tv_next;
    private TextView tvVisList, tvVisPreview;

    private String timepoint;

    private String curEpisodeId;

    private LinearLayout recordContentView;

    private VitalSignRecordBean recordInfo;

    private List<VitalSignPatBean> patList = new ArrayList<>();
    private VitalSignPatBean patientInfo;

    private int patientIndex;

    private boolean waiting = false;

    private HashMap<String, View> viewItemMap;

    private List timeFilterList = new ArrayList<>();
    private String timeFilterStr = "";
    private String dateFilterStr = "";

    private int SAVE_TEMP_VALUE_NORMAL = 0;
    private int SAVE_TEMP_VALUE_NEXT = 1;
    private int SAVE_TEMP_VALUE_PRE = 2;
    private int SAVE_TEMP_VALUE_SCAN = 3;

    private LinearLayout llPreNex;
    private KeyboardView mKeyboard;

    private Map oldMap = new HashMap<String, String>();
    private Map newMap = new HashMap<String, String>();

    private Boolean ifSave = false;

    private int EDTEXT_ID = 10001;

    private Map<Integer, EditText> editTextMap = new HashMap<>();
    private ArrayList<EditText> listView = new ArrayList<>();
    private ArrayList<OptionView> listOptionView = new ArrayList<>();

    private boolean fromTask = false;
    private String patInfo = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        if (isSingleModel) {
            setToolbarCenterTitle("体征录入", 0xffffffff, 17);
        }
        initData();

        initView(view);

        if (fromTask) {
            et_time.setClickable(false);
            setToolbarCenterTitle(patInfo, 0xffffffff, 17);
            view.findViewById(R.id.ll_vitalsign_prenex).setVisibility(View.GONE);
            asyncGetVitalSignItems();
        } else if (isSingleModel) {
            //            et_time.setClickable(false);
            view.findViewById(R.id.ll_vitalsign_prenex).setVisibility(View.GONE);
            asyncGetVitalSignItems();
        } else {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switchPatient();
                }
            }, 300);
        }

    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = null;
            if (bundle != null) {
                scanInfo = bundle.getString("data");
            }
            //            patientIndex
            for (int i = 0; i < patList.size(); i++) {
                if (scanInfo != null && scanInfo.equals(patList.get(i).getRegNo())) {
                    patientIndex = i;
                    break;
                }
            }
            saveTempValue(SAVE_TEMP_VALUE_SCAN);
        }
    }

    /**
     * 保存生命体征数据
     */
    private void saveTempValue(int type) {

        ArrayList<HashMap<String, String>> resList = new ArrayList();

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
            VitalSignRecordBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
            String code = temp.getCode();
            String defValue = temp.getValue();
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
            if (!value.equals(defValue)) {
                resList.add(resItem);
            }
        }

        Gson gson = new Gson();
        String result = gson.toJson(resList);

        waiting = true;
        if (oldMap.equals(newMap)) {
            waiting = false;
            if (ifSave) {
                ifSave = false;
                showToast("没有可保存的内容");
            }
            if (type == SAVE_TEMP_VALUE_NEXT) {
                patientIndex++;
                switchPatient();
            } else if (type == SAVE_TEMP_VALUE_PRE) {
                patientIndex--;
                switchPatient();
            } else if (type == SAVE_TEMP_VALUE_SCAN) {
                switchPatient();
            }
            return;
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.saveTempData(curEpisodeId, timepoint, result, new VitalSignApiManager.SaveTempDataCallback() {
            @Override
            public void onSuccess(VitalSignSaveBean bean) {
                hideLoadFailTip();
                showToast("保存成功");
                if (fromTask) {
                    Bundle bundle = new Bundle();
                    bundle.putString("bedselectinfoStr", "saveTemp");
                    finish(bundle);
                }
                oldMap.putAll(newMap);
                waiting = false;
                if (type == SAVE_TEMP_VALUE_NEXT) {
                    patientIndex++;
                    switchPatient();
                } else if (type == SAVE_TEMP_VALUE_PRE) {
                    patientIndex--;
                    switchPatient();
                } else if (type == SAVE_TEMP_VALUE_SCAN) {
                    switchPatient();
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                waiting = false;
                showToast("error:" + msg);
            }
        });

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

    private void switchPatient() {

        if (patientIndex == 0) {
            tv_pre.setEnabled(false);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if (patientIndex == patList.size() - 1) {
            tv_next.setEnabled(false);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if (patientIndex > 0 && patientIndex < patList.size() - 1) {
            tv_next.setEnabled(true);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_next_color));
            tv_pre.setEnabled(true);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_pre_color));
        }

        waiting = true;
        patientInfo = patList.get(patientIndex);
        curEpisodeId = patientInfo.getEpisodeId();
        String title = patientInfo.getBedCode() + " " + patientInfo.getPatName();
        setToolbarCenterTitle(title, 0xffffffff, 17);
        setScene("体征录入");
        recordContentView.removeAllViews();
        asyncGetVitalSignItems();
    }

    private void asyncGetVitalSignItems() {
        recordContentView.removeAllViews();
        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.getVitalSignItems(curEpisodeId, timepoint, new VitalSignApiManager.GetVitalSignItemCallback() {
            @Override
            public void onSuccess(VitalSignRecordBean bean) {
                recordInfo = bean;


                oldMap = new HashMap<String, String>();
                newMap = new HashMap<String, String>();
                drawInputItems();
                inputItemsValue();

                hideLoadFailTip();
                waiting = false;

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                waiting = false;
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

            VitalSignRecordBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
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
                recordContentView.addView(layout);
            }
        }

    }

    private void inputItemsValue() {

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
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

    private LinearLayout drawItem(VitalSignRecordBean.TempConfigBean config) {

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(110);
        int width = 0;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;

        layout.setLayoutParams(params);

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            if (config.getCode().equals(recordInfo.getTempList().get(i).getCode())) {
                oldMap.put(config.getCode(), recordInfo.getTempList().get(i).getValue());
                newMap.put(config.getCode(), recordInfo.getTempList().get(i).getValue());
            }
        }

        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getDesc());
        titleTV.setTextColor(Color.parseColor("#000000"));
        //        if (config.getCode()recordInfo.getNeedMeasureCode().get(0).get)
        for (int i = 0; i < recordInfo.getNeedMeasureCode().size(); i++) {
            if (recordInfo.getNeedMeasureCode().get(i).getItemcode().equals(config.getCode())) {
                if (recordInfo.getNeedMeasureCode().get(i).getNeedFlag().equals("1")) {
                    String needMea = "*" + config.getDesc();
                    SpannableStringBuilder builder = new SpannableStringBuilder(needMea);

                    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#FFEA4300"));
                    ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.parseColor("#000000"));

                    builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    builder.setSpan(blackSpan, 1, needMea.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    titleTV.setText(builder);
                }
            }
        }
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

            edText.setId(EDTEXT_ID++);
            editTextMap.put(edText.getId(), edText);
            edText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            edText.setSingleLine(true);
            edText.setNextFocusForwardId(EDTEXT_ID);
            if (config.getValueType() != null && config.getValueType().equals("N")) {
                //edText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                edText.setRawInputType(Configuration.KEYBOARD_QWERTY);
            }
            //            if (config.getValidate().equals("true")){
            edText.setRawInputType(Configuration.KEYBOARD_QWERTY);
            //            }
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
            //自定义键盘
            //            edText.setOnTouchListener(new View.OnTouchListener() {
            //                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            //                @Override
            //                public boolean onTouch(View v, MotionEvent event) {
            //                    if (titleTV.getText().toString().contains("℃")) {
            //                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            //                        imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);
            //                        new KeyBoardUtil(mKeyboard, edText).showKeyboard();
            //                    } else {
            //                        mKeyboard.setVisibility(View.GONE);
            //
            //                    }
            //                    return false;
            //                }
            //            });
            edText.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if (v.hasFocus()) {
                        if (titleTV.getText().toString().contains("℃")) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);

                            KeyBoardUtil keyBoardUtil = new KeyBoardUtil(mKeyboard, edText, edText.getId());
                            keyBoardUtil.setOngetNextFocusListener(new KeyBoardUtil.getNextFocusListener() {
                                @Override
                                public void getNextFocus(int id) {
                                    EditText nextEditText = editTextMap.get(id + 1);
                                    if (nextEditText != null) {
                                        nextEditText.requestFocus();
                                    } else {
                                        edText.clearFocus();
                                    }
                                }
                            });
                            keyBoardUtil.showKeyboard();
                        } else {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(edText, 0);
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


                            KeyBoardUtil keyBoardUtil = new KeyBoardUtil(mKeyboard, edText, edText.getId());
                            keyBoardUtil.setOngetNextFocusListener(new KeyBoardUtil.getNextFocusListener() {
                                @Override
                                public void getNextFocus(int id) {
                                    EditText nextEditText = editTextMap.get(id + 1);
                                    if (nextEditText != null) {
                                        nextEditText.requestFocus();
                                    } else {
                                        edText.clearFocus();
                                    }
                                }
                            });
                            keyBoardUtil.showKeyboard();


                        } else {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(edText, 0);
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
                    newMap.put(config.getCode(), edText.getText().toString());
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

            edText.setTag(config.getDesc());
            listView.add(edText);
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
                                newMap.put(config.getCode(), optionView.getText().toString());
                            } else {
                                optionView.setText(item);
                                newMap.put(config.getCode(), optionView.getText().toString());
                            }
                        }
                    });
                    optionView.showPicker();
                }
            });

            layout.addView(optionView);
            optionView.setTag(config.getDesc());
            listOptionView.add(optionView);
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

    @Override
    public void setMsgToSingleBaseFragment(String msg) {
        super.setMsgToSingleBaseFragment(msg);
        timepoint = msg;
        if (et_time != null) {
            et_time.setText(timepoint);
        }
        dateFilterStr = timepoint.substring(0, 10);
        timeFilterStr = timepoint.substring(11, 16);
        asyncGetVitalSignItems();
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_record, container, false);
    }

    @Override
    public void getVoiceResult(VoiceBean voiceBean) {
        Log.i("onResult json4", "jsonResult:" + voiceBean.getLast());
        if (voiceBean.getForm() != null && voiceBean.getForm().getData() != null && voiceBean.getForm().getData().size() > 0) {
            for (int i = 0; i < listView.size(); i++) {
                String viewTag = listView.get(i).getTag().toString();
                for (int j = 0; j < voiceBean.getForm().getData().size(); j++) {
                    if (viewTag.contains(voiceBean.getForm().getData().get(j).getKey())) {
                        listView.get(i).setText(voiceBean.getForm().getData().get(j).getValue());
                    }
                }
            }
            for (int i = 0; i < listOptionView.size(); i++) {
                String viewTag = listOptionView.get(i).getTag().toString();
                for (int j = 0; j < voiceBean.getForm().getData().size(); j++) {
                    if (viewTag.contains(voiceBean.getForm().getData().get(j).getKey())) {
                        listOptionView.get(i).setText(voiceBean.getForm().getData().get(j).getValue());
                    }
                }

            }
        } else if (voiceBean.getLast()) {
            String bedNoStr = "";
            if (voiceBean.getCommand().getBedNo() != null) {
                bedNoStr = voiceBean.getCommand().getBedNo();
            }
            String actionStr = "";
            if (voiceBean.getCommand().getAction() != null) {
                actionStr = voiceBean.getCommand().getAction();
            }
            int tempIndex = 0;
            if (actionStr.equals("生命体征") && (!bedNoStr.isEmpty())) {
                actionStr = "";
            }
            if (actionStr.isEmpty() && (!bedNoStr.isEmpty())) {
                boolean isBed = false;
                for (int i = 0; i < patList.size(); i++) {
                    if (bedNoStr.equals(patList.get(i).getBedCode())) {
                        tempIndex = i;
                        isBed = true;
                        break;
                    }
                }
                if (isBed) {
                    patientIndex = tempIndex;
                    saveTempValue(SAVE_TEMP_VALUE_SCAN);
                } else {
                    showToast(bedNoStr + "床位不存在");
                }

            } else {
                if (actionStr.equals("保存")) {
                    ifSave = true;
                    saveTempValue(SAVE_TEMP_VALUE_NORMAL);
                } else if (actionStr.equals("清屏")) {
                    for (int i = 0; i < listView.size(); i++) {
                        listView.get(i).setText("");
                    }

                } else {
                    if (SPUtils.getInstance().getString(SharedPreference.SINGLEMODEL).equals("1")) {
                        if (basfrgmentToSingleActivityListner != null) {
                            basfrgmentToSingleActivityListner.voiceMsg(bedNoStr, actionStr);
                        }
                    } else {
                        voiceUtil.startFragmentByVoice(bedNoStr, actionStr);
                    }

                }
            }
        }

    }

    private void initData() {

        //取出sp中的列表
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<VitalSignPatBean>>() {
        }.getType();
        patList = new ArrayList<>();
        String displayListJsonStr = SPUtils.getInstance().getString(SharedPreference.DISPLAYLIST);
        patList = gson.fromJson(displayListJsonStr, type);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("fromTask") != null && "Y".equals(bundle.getString("fromTask"))) {
                curEpisodeId = bundle.getString("episodeId");
                timepoint = bundle.getString("timekey");
                patInfo = bundle.getString("patInfo");
                fromTask = true;
            } else {
                timeFilterList = bundle.getStringArrayList("timeList");
                dateFilterStr = bundle.getString("date");
                timeFilterStr = bundle.getString("time");
                timepoint = dateFilterStr + " " + timeFilterStr;
                patientIndex = bundle.getInt("index");
            }

        } else {
            curEpisodeId = singleEpisodeId;
            dateFilterStr = SPUtils.getInstance().getString(SharedPreference.CURDATETIME).substring(0, 10);
            timeFilterStr = SPUtils.getInstance().getString(SharedPreference.CURDATETIME).substring(11, 16);
            timepoint = SPUtils.getInstance().getString(SharedPreference.CURDATETIME).substring(0, 16);
            //            fromTask = true;
        }

        viewItemMap = new HashMap<>();

    }

    private void initView(View view) {
        mKeyboard = view.findViewById(R.id.ky_keyboard);
        et_time = view.findViewById(R.id.et_vital_sign_record_time);
        et_time.setOnClickListener(this);
        et_time.setText(timepoint);

        recordContentView = view.findViewById(R.id.ll_vitalsign_record_content);

        if (isSingleModel) {
            view.findViewById(R.id.ll_vitalsign_patient_skinorder).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.ll_vitalsign_patient_skinorder).setVisibility(View.GONE);
        }
        tv_next = view.findViewById(R.id.tv_vitalsign_record_next);
        tv_next.setOnClickListener(this);
        tv_pre = view.findViewById(R.id.tv_vitalsign_record_pre);
        tv_pre.setOnClickListener(this);
        tvVisList = view.findViewById(R.id.tv_vitalsign_record_list);
        tvVisList.setOnClickListener(this);
        tvVisPreview = view.findViewById(R.id.tv_vitalsign_tmp_preview);
        tvVisPreview.setOnClickListener(this);


        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifSave = true;
                saveTempValue(SAVE_TEMP_VALUE_NORMAL);
            }
        });

        setToolbarRightCustomView(viewright);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.et_vital_sign_record_time:
                chooseTime(TimeUtils.string2Millis(dateFilterStr + " " + timeFilterStr + ":00"));
                break;
            case R.id.tv_vitalsign_record_next:
                nextPatient();
                break;
            case R.id.tv_vitalsign_record_pre:
                prePatient();
                break;
            case R.id.tv_vitalsign_record_list:
                bundle.putString("episodeId", singleEpisodeId);
                bundle.putString("patInfo", singlePatInfo);
                startFragment(VitalSignDetailFragment.class, bundle, 1);
                break;
            case R.id.tv_vitalsign_tmp_preview:
                bundle.putString("episodeId", singleEpisodeId);
                bundle.putString("patInfo", singlePatInfo);
                startFragment(VitalSignChartsDetailFragment.class, bundle);
                break;
            default:
                break;
        }
    }

    /**
     * 选择时间
     */
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
        //        mDialogAll.settype(1);
        //        //取时间前两个字符转为int（02，06...）
        //
        //        List<String> timeList = new ArrayList();
        //        for (int i = 0; i < timeFilterList.size(); i++) {
        //            String str = (String) ((Map) timeFilterList.get(i)).get("time");
        //            timeList.add(str);
        //        }
        //        mDialogAll.setmHourMinute(timeList);

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    /**
     * 病人切换相关
     */
    private void nextPatient() {

        if (waiting) {
            showToast("正在获取数据，请稍后");
            return;
        }

        //当前上一位下一位需要保存数据，顾讲病人切换移动到保存数据成功的回调中
        saveTempValue(SAVE_TEMP_VALUE_NEXT);

    }

    private void prePatient() {

        if (waiting) {
            showToast("正在获取数据，请稍后");
            return;
        }

        //当前上一位下一位需要保存数据，顾讲病人切换移动到保存数据成功的回调中
        saveTempValue(SAVE_TEMP_VALUE_PRE);

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 11);
        String time = TimeUtils.millis2String(millseconds).substring(11, 16);

        if (!date.equals(dateFilterStr) || !time.equals(timeFilterStr)) {
            //日期发生改变，需重新请求数据
            dateFilterStr = date;
            timeFilterStr = time;
            recordContentView.removeAllViews();
            timepoint = dateFilterStr + " " + timeFilterStr;
            asyncGetVitalSignItems();
        }

        et_time.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
    }
}
