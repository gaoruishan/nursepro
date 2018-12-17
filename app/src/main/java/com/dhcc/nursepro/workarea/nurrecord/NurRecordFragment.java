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
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
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

    private NurRecordAdapter modelDetailAdapter;
    private List<NurRecordBean.ModelListBean> listBeans;
    private NurRecordBean nurRecordBean;
    private HashMap<String, View> viewItemMap;
    private Map patInfoMap = new HashMap<String, String>();
    private long mExitTime;
    private String strSend = "";

    private ItemValueDialog showDialog;
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
                    if ("1".equals(listBeans.get(i).getMustFill())) {
                        if (StringUtils.isEmpty(listBeans.get(i).getSendValue())) {
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

        int height = ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
        int width = ConvertUtils.dp2px(Float.parseFloat(config.getWidth()));

        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        //统一名称textview
        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getItemDesc());
        titleTV.setTextSize(Float.parseFloat(config.getFontSize()));

        //判断是否必填，必填的话字体变红
        if ("1".equals(config.getMustFill())) {
            titleTV.setTextColor(getResources().getColor(R.color.nurrecord_text_mustfill_color));
        } else {
            titleTV.setTextColor(getResources().getColor(R.color.nurrecord_text_normal_color));
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.setMargins(ConvertUtils.dp2px(5), 0, 5, 0);
        titleTV.setLayoutParams(titleParams);
        titleTV.setGravity(Gravity.TOP);

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
            if ("0".equals(config.getTitleHiddeFlag())) {
                titleTV.setVisibility(View.GONE);
            }
            EditText edText = new EditText(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            edText.setLayoutParams(layoutParams);
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            edText.setPadding(ConvertUtils.dp2px(5), 0, ConvertUtils.dp2px(5), 0);
            edText.setTextSize(Float.parseFloat(config.getFontSize()));
            edText.setSingleLine();
            viewItemMap.put(config.getItemCode(),edText);
            edText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDialog = new ItemValueDialog(getActivity());
                    showDialog.setTitle(config.getItemDesc());
                    showDialog.setMessage(edText.getText()+"");
                    showDialog.setYesOnclickListener("确定", new ItemValueDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            edText.setText(showDialog.getMessage());
                            showDialog.dismiss();
                        }
                    });
                    showDialog.show();
                    return false;
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
                }
            });


            layout.addView(edText);
            viewItemMap.put(config.getItemCode(), edText);
            if (!StringUtils.isEmpty(config.getUnit())) {
                TextView tvUnit = new TextView(getContext());
                LinearLayout.LayoutParams unitParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvUnit.setLayoutParams(unitParams);
                tvUnit.setText(config.getUnit() + "");
                layout.addView(tvUnit);
            }


        } else if ("C".equals(config.getItemType())) {
            //多选
            if ("0".equals(config.getSingleCheck())) {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 0, 0);
                layout.setLayoutParams(params1);
                config.setSendValue(config.getItemdeValue() + "");
                FlowLayout flowCheckGroup = new FlowLayout(getContext());
                String[] split = config.getItemValue().split(";");
                String[] devalue = config.getItemdeValue().split(",");

                List listCk = new ArrayList();
                for (int i = 0; i < split.length; i++) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(split[i] + "");
                    Map mapCk = new HashMap();
                    mapCk.put("value", cb.getText());
                    mapCk.put("isSel", "false");
                    for (int j = 0; j < devalue.length; j++) {
                        if (split[i].equals(devalue[j])) {
                            cb.setChecked(true);
                            if (cb.getText().equals(devalue[j])) {
                                mapCk.put("isSel", "true");
                            }
                        }
                    }
                    cb.setTextSize(Float.parseFloat(config.getFontSize()));
                    cb.setHeight(height);

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
                viewItemMap.put(config.getItemCode(), flowCheckGroup);
                //单选
            } else {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                radioGroup.setLayoutParams(params2);

                config.setSendValue(config.getItemdeValue() + "");
                String[] split = config.getItemValue().split(";");
                for (int i = 0; i < split.length; i++) {
                    RadioButton rb = new RadioButton(getContext());
                    rb.setId(i);
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
                    if (split[i].equals(config.getItemdeValue())) {
                        radioGroup.check(rb.getId());
                    }
                }
                layout.addView(radioGroup);

                viewItemMap.put(config.getItemCode(), radioGroup);

            }
        } else if ("R".equals(config.getItemType())) {
            //单选
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params1);

            config.setSendValue(config.getItemdeValue() + "");
            String[] split = config.getItemValue().split("!");
            for (int i = 0; i < split.length; i++) {
                RadioButton rb = new RadioButton(getContext());
                rb.setId(i);
                rb.setHeight(height);
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
                if (split[i].equals(config.getItemdeValue())) {
                    radioGroup.check(rb.getId());
                }

            }


            layout.addView(radioGroup);
            viewItemMap.put(config.getItemCode(), radioGroup);

        } else if ("T".equals(config.getItemType())){
            //textview额外设置

            //判断是否可点击跳转其他病例填充表格
            if (config.getLinkInfo().size() > 0) {
                titleTV.setTextColor(getResources().getColor(R.color.blue));
                layout.setBackgroundColor(0);
                titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击进入新的模板"+config.getLinkInfo().get(0).getLinkModel()+"给:"+config.getLinkInfo().get(0).getLinkItemCode());
                        View view = viewItemMap.get("Item81");
                        if (view instanceof EditText){
                            EditText ed = (EditText)view;
                            ed.setText("from__"+config.getItemCode());
                        }

                    }
                });
            }
            viewItemMap.put(config.getItemCode(), titleTV);

            //判断是否单行显示
            if ("0".equals(config.getTitleHiddeFlag())) {
                TextView tvalue = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                titleTV.setLayoutParams(layoutParams);
            } else {
//                TextView tvalue = new TextView(getContext());
//                tvalue.setText(config.getItemdeValue() + "===");
//                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
//                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
//                layout.addView(tvalue);
            }
        }  else if ("D".equals(config.getItemType())) {
            //日期选择
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
            viewItemMap.put(config.getItemCode(), tvalue);
        } else if ("Ti".equals(config.getItemType())) {
            //时间选择
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
            viewItemMap.put(config.getItemCode(), tvalue);
        } else {
            showToast("出现未知类型控件，请联系后台进行数据修复或更新应用");
//            //选择框
//            List ll = new ArrayList();
//            ll.add("1");
//            ll.add("2");
//            final OptionView optionView = new OptionView(getActivity(), ll);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 50);
//            optionView.setLayoutParams(layoutParams);
//            optionView.setTextSize(16);
//            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
//            optionView.setGravity(Gravity.CENTER);
//
//            optionView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    optionView.showPicker();
//                }
//            });
//
//            layout.addView(optionView);
//            viewItemMap.put(config.getItemCode(), optionView);
        }

        return layout;
    }

    private String getckvalue(ArrayList<HashMap> list) {
        String strck = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("isSel").equals("true")) {
                strck = strck + "," + list.get(i).get("value") + "";
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
     * 日期时间选择回调
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
