package com.dhcc.nursepro.workarea.nurrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.workarea.nurrecord.adapter.NurRecordAdapter;
import com.dhcc.nursepro.workarea.nurrecord.api.NurRecordManager;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.nurrecord
 * <p>
 * author Q
 * Date: 2018/12/3
 * Time:9:49
 */
public class NurRecordFragment extends BaseFragment implements OnDateSetListener {
    private FlowLayout recordContentView;
    private TextView tvsend;

    private TextView textViewChooseDateTime;
    private String dt = "date";

    private List listCk = new ArrayList();
    private NurRecordAdapter modelDetailAdapter;
    private List<NurRecordBean.ModelListBean> listBeans;
    private NurRecordBean nurRecordBean;
    private HashMap<String, View> viewItemMap;
    private Map patInfoMap = new HashMap<String, String>();
    private long mExitTime;
    private String strSend = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modeldetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle("护理病历", 0xffffffff, 17);
        initview(view);
        initData();

    }

    private void initview(View view) {
        tvsend = view.findViewById(R.id.tv_send);

        tvsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSend = "";
                for (int i = 0; i < listBeans.size(); i++) {
                    if (listBeans.get(i).getItemCode().startsWith("Item")) {
                        strSend = strSend + "^" + listBeans.get(i).getSendValue();
                    }
                    if (listBeans.get(i).getMustFill().equals("1")) {
                        if (listBeans.get(i).getSendValue().equals("")) {
                            showToast(listBeans.get(i).getItemDesc() + "--未填写");
                            return;
                        }
                    }
                    if (i == listBeans.size() - 1) {

                        showToast(strSend);
                        Log.v("111send", strSend);
                    }
                }
            }
        });
        recordContentView = view.findViewById(R.id.ll_vitalsign_record_content);
        viewItemMap = new HashMap<>();
    }

    private void initData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("locId", "197");
        map.put("EmrCode", "DHCNURANHUI2");
        map.put("episodeId", "11");

        NurRecordManager.getModelDetailListMsg(map, "getModelDetail", new NurRecordManager.getLabOutCallBack() {
            @Override
            public void onSuccess(NurRecordBean modelDetailBean) {
                nurRecordBean = modelDetailBean;
                listBeans = modelDetailBean.getModelList();
                Gson gson = new Gson();
                String result = gson.toJson(nurRecordBean.getPatInfo());
                patInfoMap = gson.fromJson(result, HashMap.class);
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
        for (int i = 0; i < listBeans.size(); i++) {
            NurRecordBean.ModelListBean config = listBeans.get(i);
            recordContentView.addView(drawItem(config));
        }
    }

    private void inputItemsValue() {

        //        for (int i = 0; i < recordInfo.getTempList().size(); i ++){
        //            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
        //            View view =  viewItemMap.get(temp.getCode());
        //            if (view instanceof EditText){
        //                EditText ed = (EditText)view;
        //                ed.setText(temp.getValue());
        //            }else{
        //                OptionView op = (OptionView)view;
        //                op.setText(temp.getValue());
        //            }
        //        }
    }

    /**
     * 区分不同View类型，添加进容器
     *
     * @param config
     * @return
     */
    private LinearLayout drawItem(NurRecordBean.ModelListBean config) {

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
        int width = ConvertUtils.dp2px(Float.parseFloat(config.getWidth()));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        params.setMargins(0, 0, 0, 0);
        //        params.weight = 1;

        layout.setLayoutParams(params);


        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getItemDesc());
        //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        titleTV.setTextSize(Float.parseFloat(config.getFontSize()));
        //判断是否必填，必填的话字体变红
        if ("1".equals(config.getMustFill())) {
            titleTV.setTextColor(getResources().getColor(R.color.nurrecord_text_mustfill_color));
        } else {
            titleTV.setTextColor(getResources().getColor(R.color.nurrecord_text_normal_color));
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.setMargins(ConvertUtils.dp2px(5), 5, ConvertUtils.dp2px(5), 0);
        titleTV.setLayoutParams(titleParams);
        titleTV.setGravity(Gravity.TOP);
        //        titleTV.setBackgroundColor(getResources().getColor(R.color.blue_light));

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(config.getToastStr())) {
                    showToast(config.getToastStr());
                }
            }
        });
        layout.addView(titleTV);

        if ("E".equals(config.getItemType())) {
            //输入框

            EditText edText = new EditText(getContext());
            edText.setGravity(Gravity.START);
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            edText.setTextSize(Float.parseFloat(config.getFontSize()));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            edText.setLayoutParams(layoutParams);
            edText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (System.currentTimeMillis() - mExitTime > 1000) {
                        mExitTime = System.currentTimeMillis();
                    } else {
                        showToast("双击进入dialog");
                    }
                }
            });
            //是否可编辑
            if ("1".equals(config.getEditFlag())) {
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_normal_color));
            } else {
                edText.setEnabled(false);
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_defaultvalue_color));
                edText.setText(config.getPatInfo());
            }
            //根据默认内容优先级填入默认值
            if ("".equals(config.getPatInfo())) {
                edText.setText(config.getItemdeValue());
                config.setSendValue(config.getItemdeValue() + "");
            } else {
                edText.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
            }
            edText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(edText.getText().toString() + "----");
                    showToast(edText.getText().toString());
                }
            });


            layout.addView(edText);

        } else if ("C".equals(config.getItemType())) {
            //多选
            if ("0".equals(config.getSingleCheck())) {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, ConvertUtils.dp2px(20), 0, 0);
                layout.setLayoutParams(params1);
                config.setSendValue(config.getItemdeValue() + "");
                FlowLayout flowCheckGroup = new FlowLayout(getContext());
                String[] split = config.getItemValue().split(";");
                for (int i = 0; i < split.length; i++) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(split[i] + "");
                    cb.setTextSize(Float.parseFloat(config.getFontSize()));
                    Map mapCk = new HashMap();
                    mapCk.put("value", cb.getText());
                    mapCk.put("isSel", "false");
                    listCk.add(mapCk);
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cb.isChecked()) {
                                mapCk.put("isSel", "true");
                                config.setSendValue(getckvalue((ArrayList<HashMap>) listCk) + "");
                                showToast(getckvalue((ArrayList<HashMap>) listCk));
                            } else {
                                mapCk.put("isSel", "false");
                                config.setSendValue(getckvalue((ArrayList<HashMap>) listCk) + "");
                                showToast(getckvalue((ArrayList<HashMap>) listCk));
                            }

                        }
                    });
                    flowCheckGroup.addView(cb);
                }
                layout.addView(flowCheckGroup);
                //单选
            } else {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                radioGroup.setLayoutParams(params2);
                String[] split = config.getItemValue().split(";");
                for (int i = 0; i < split.length; i++) {
                    RadioButton rb = new RadioButton(getContext());
                    rb.setTextSize(Float.parseFloat(config.getFontSize()));
                    rb.setText(split[i] + "");
                    rb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showToast(rb.getText() + "");
                            config.setSendValue(rb.getText() + "");
                        }
                    });
                    radioGroup.addView(rb);
                }
                layout.addView(radioGroup);

            }
        } else if ("R".equals(config.getItemType())) {

            //            TextView tvalue = new TextView(getContext());
            //            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //            tvalue.setLayoutParams(layoutParams);
            //            tvalue.setText("此处是链接");
            //            tvalue.setTextColor(getResources().getColor(R.color.blue));
            //            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            //            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            //            tvalue.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
            //            LinearLayout layout1 = new LinearLayout(getContext());
            //            layout1.setLayoutParams(params1);
            //            layout1.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //            params2.weight = 1;
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params2);

            config.setSendValue(config.getItemdeValue() + "");
            String[] split = config.getItemValue().split("!");
            for (int i = 0; i < split.length; i++) {
                RadioButton rb = new RadioButton(getContext());
                rb.setTextSize(Float.parseFloat(config.getFontSize()));
                rb.setText(split[i] + "");
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(rb.getText() + "----");
                        config.setSendValue(rb.getText() + "");
                    }
                });
                radioGroup.addView(rb);

            }
            //            layout.addView(radioGroup);

            //            layout1.addView(radioGroup);
            //            layout1.addView(tvalue);
            layout.addView(radioGroup);

        } else if ("T".equals(config.getItemType())) {
            if (config.getLinkInfo().size() > 0) {
                titleTV.setTextColor(getResources().getColor(R.color.blue));
                layout.setBackgroundColor(0);
                titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击进入新的模板");
                    }
                });
            }
            if ("0".equals(config.getTitleHiddeFlag())) {
                TextView tvalue = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                tvalue.setLayoutParams(layoutParams);
                tvalue.setText(config.getItemdeValue() + "");
                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                layout.addView(tvalue);
            } else {
                TextView tvalue = new TextView(getContext());
                tvalue.setText(config.getItemdeValue() + "");
                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                layout.addView(tvalue);
            }
        } else if ("D".equals(config.getItemType())) {
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "2018-12-11");
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(tvalue.getText() + "");
            tvalue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewChooseDateTime = tvalue;
                    dt = "date";
                    chooseDate();
                }
            });
            layout.addView(tvalue);
        } else if ("Ti".equals(config.getItemType())) {
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "11:11");
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(tvalue.getText() + "");
            tvalue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewChooseDateTime = tvalue;
                    dt = "time";
                    chooseTime();
                }
            });

            layout.addView(tvalue);
        } else {

            //选择框
            List ll = new ArrayList();
            ll.add("1");
            ll.add("2");
            final OptionView optionView = new OptionView(getActivity(), ll);

            optionView.setTextSize(16);
            //            optionView.setTextColor(getResources().getColor(R.color.blue_light));
            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            optionView.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 50);
            //            layoutParams.setMargins(ConvertUtils.dp2px(10),ConvertUtils.dp2px(11),ConvertUtils.dp2px(10),45);//4个参数按顺序分别是左上右下
            optionView.setLayoutParams(layoutParams);

            optionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    optionView.showPicker();
                }
            });

            layout.addView(optionView);
            viewItemMap.put(config.getItemCode(), optionView);
        }

        return layout;
    }

    private String getckvalue(ArrayList<HashMap> list) {
        String strck = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("isSel").equals("true")) {
                strck = strck + "！" + list.get(i).get("value") + "";
            }
        }
        return strck;
    }

    /**
     * 选择日期---年月日
     */
    private void chooseDate() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");

    }

    /**
     * 选择时间---时分
     */
    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");
    }

    /**
     * 日期时间选择监听；
     *
     * @param timePickerView
     * @param millseconds
     */
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 11);
        String time = TimeUtils.millis2String(millseconds).substring(11, 16);
        if ("date".equals(dt)) {
            textViewChooseDateTime.setText(date + "");

        } else {
            textViewChooseDateTime.setText(time + "");

        }
    }
}
