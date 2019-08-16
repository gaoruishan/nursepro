package com.dhcc.nursepro.workarea.vitalsign;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.utils.KeyBoardUtil;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignRecordBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
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

    private Map patientInfo;

    private TextView et_time;

    private TextView tv_pre;
    private TextView tv_next;

    private String timepoint;

    private String curEpisodeId;

    private LinearLayout recordContentView;

    private VitalSignRecordBean recordInfo;

    private List<Map<String, Object>> patientList;

    private int patientIndex;

    private boolean waiting = false;

    private HashMap<String, View> viewItemMap;

    private List timeFilterList = new ArrayList<>();
    private String timeFilterStr = "";
    private String dateFilterStr = "";

    private int SAVE_TEMP_VALUE_NORMAL = 0;
    private int SAVE_TEMP_VALUE_NEXT = 1;
    private int SAVE_TEMP_VALUE_PRE = 2;

    private LinearLayout llPreNex;
    private KeyboardView mKeyboard;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_record, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

        initView(view);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchPatient();
            }
        }, 300);

    }

    private void initData() {

        //取出sp中的列表
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        patientList = new ArrayList<>();
        String displayListJsonStr = SPUtils.getInstance().getString(SharedPreference.DISPLAYLIST);
        patientList = gson.fromJson(displayListJsonStr, type);

        Bundle bundle = getArguments();
        if (bundle != null) {
            timeFilterList = bundle.getStringArrayList("timeList");
            dateFilterStr = bundle.getString("date");
            timeFilterStr = bundle.getString("time");
            timepoint = dateFilterStr + " " + timeFilterStr;
            patientIndex = bundle.getInt("index");
        }

        viewItemMap = new HashMap<>();

    }

    private void initView(View view) {
        llPreNex = view.findViewById(R.id.ll_vitalsign_prenex);
        mKeyboard = view.findViewById(R.id.ky_keyboard);
        et_time = view.findViewById(R.id.et_vital_sign_record_time);
        et_time.setOnClickListener(this);
        et_time.setText(timepoint);

        recordContentView = view.findViewById(R.id.ll_vitalsign_record_content);

        tv_next = view.findViewById(R.id.tv_vitalsign_record_next);
        tv_next.setOnClickListener(this);
        tv_pre = view.findViewById(R.id.tv_vitalsign_record_pre);
        tv_pre.setOnClickListener(this);

        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTempValue(SAVE_TEMP_VALUE_NORMAL);
            }
        });

        setToolbarRightCustomView(viewright);

    }

    @Override
    public void onClick(View v) {
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
                .setHourText("：00")
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
        mDialogAll.settype(1);
        //取时间前两个字符转为int（02，06...）

        List<String> hours = new ArrayList();
        for (int i = 0; i < timeFilterList.size(); i++) {
            String str = (String) ((Map) timeFilterList.get(i)).get("time");
            hours.add(str);
        }

        mDialogAll.setintHour(hours);

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

    /**
     * 保存生命体征数据
     */
    private void saveTempValue(int type) {

        ArrayList<HashMap<String, String>> resList = new ArrayList();

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
            String code = temp.getCode();
            View view = viewItemMap.get(temp.getCode());
            String value = "";

            if (view instanceof EditText) {
                EditText ed = (EditText) view;
                value = ed.getText().toString();
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
        String result = gson.toJson(resList);

        showLoadingTip(BaseActivity.LoadingType.FULL);

        waiting = true;

        VitalSignApiManager.saveTempData(curEpisodeId, timepoint, result, new VitalSignApiManager.SaveTempDataCallback() {
            @Override
            public void onSuccess(VitalSignSaveBean bean) {
                hideLoadFailTip();
                waiting = false;
                showToast("保存成功 ");
                if (type == SAVE_TEMP_VALUE_NEXT) {
                    patientIndex++;
                    switchPatient();
                } else if (type == SAVE_TEMP_VALUE_PRE) {
                    patientIndex--;
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

    private void switchPatient() {

        if (patientIndex == 0) {
            tv_pre.setEnabled(false);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if (patientIndex == patientList.size() - 1) {
            tv_next.setEnabled(false);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if (patientIndex > 0 && patientIndex < patientList.size() - 1) {
            tv_next.setEnabled(true);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_next_color));
            tv_pre.setEnabled(true);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_pre_color));
        }

        waiting = true;
        patientInfo = patientList.get(patientIndex);
        curEpisodeId = (String) patientInfo.get("episodeId");
        String title = patientInfo.get("bedCode") + " " + patientInfo.get("name");
        setToolbarCenterTitle(title, 0xffffffff, 17);
        recordContentView.removeAllViews();
        asyncGetVitalSignItems();
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

    private void asyncGetVitalSignItems() {

        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.getVitalSignItems(curEpisodeId, timepoint, new VitalSignApiManager.GetVitalSignItemCallback() {
            @Override
            public void onSuccess(VitalSignRecordBean bean) {
                recordInfo = bean;

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
            //自定义键盘
            edText.setOnTouchListener(new View.OnTouchListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (titleTV.getText().toString().contains("℃")) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);
                        new KeyBoardUtil(mKeyboard, edText).showKeyboard();
                    } else {
                        mKeyboard.setVisibility(View.GONE);

                    }
                    return false;
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

}
