package com.dhcc.nursepro.workarea.vitalsign;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignRecordBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignRecordFragment extends BaseFragment implements View.OnClickListener,OnDateSetListener {

    private Map patientInfo;

    private TextView et_time;

    private TextView tv_pre;
    private TextView tv_next;

    private String timepoint;

    private String curEpisodeId;

    private LinearLayout recordContentView;

    private VitalSignRecordBean recordInfo;

    private List patientList;

    private int patientIndex;

    private boolean waiting = false;

    private HashMap<String,View> viewItemMap;

    private List timeFilterList;
    private String timeFilterStr = "";
    private String dateFilterStr = "";

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

    private void initView(View view){
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
                saveTempValue();
            }
        });

        setToolbarRightCustomView(viewright);

    }

    private void initData(){
        Bundle bundle = getArguments();

//        timepoint = bundle.getString("timepoint");
        patientList = (List)bundle.getSerializable("list");

        timeFilterList = bundle.getStringArrayList("timeList");

        dateFilterStr = bundle.getString("date");

        timeFilterStr = bundle.getString("time");

        timepoint = dateFilterStr + " "+ timeFilterStr;

        patientIndex = 0;

        viewItemMap = new HashMap<>();

    }

    private void asyncGetVitalSignItems(){

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_vital_sign_record_time:
                chooseTime();
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
     * 病人切换相关
     */
    private void nextPatient(){

        if (waiting){
            showToast("正在获取数据，请稍后");
            return;
        }

        patientIndex ++;
        switchPatient();

    }

    private void prePatient(){

        if (waiting){
            showToast("正在获取数据，请稍后");
            return;
        }

        patientIndex --;
        switchPatient();
    }

    private void switchPatient(){

        if (patientIndex == 0) {
            tv_pre.setEnabled(false);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if (patientIndex == patientList.size() - 1){
            tv_next.setEnabled(false);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_index_null_color));
        }
        if(patientIndex > 0 && patientIndex < patientList.size()){
            tv_next.setEnabled(true);
            tv_next.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_next_color));
            tv_pre.setEnabled(true);
            tv_pre.setBackgroundColor(getResources().getColor(R.color.vital_sign_record_pre_color));
        }

        waiting = true;
        patientInfo = (Map)patientList.get(patientIndex);
        curEpisodeId = (String)patientInfo.get("episodeId");
        String title = patientInfo.get("bedCode") + "床 " + patientInfo.get("name");
        setToolbarCenterTitle(title,0xffffffff,17);
        recordContentView.removeAllViews();
        asyncGetVitalSignItems();
    }



    /**
     * 绘制UI相关
     */

    public void drawInputItems(){

        LinearLayout layout = new LinearLayout(getContext());


        int size = recordInfo.getTempConfig().size();

        for(int i = 0; i < size; i ++){

            if (i != 0 && i % 3 == 0){
                recordContentView.addView(layout);
                layout = new LinearLayout(getContext());
                int height = 120;
                int width = ScreenUtils.getScreenWidth();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            }

            VitalSignRecordBean.TempConfigBean config = recordInfo.getTempConfig().get(i);

            LinearLayout item = drawItem(config);

            layout.addView(item);
        }

        if (size % 3 != 0){
            for (int i = 0; i < size % 3 + 1; i ++){
                layout.addView(dramEmptyItem());
            }
            recordContentView.addView(layout);
        }
    }

    private LinearLayout drawItem(VitalSignRecordBean.TempConfigBean config){

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(110);
        int width = 0;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.weight = 1;

        layout.setLayoutParams(params);


        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getDesc());
//        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        titleTV.setTextSize(16);
        titleTV.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0,ConvertUtils.dp2px(20),0,0);//4个参数按顺序分别是左上右下
        titleTV.setLayoutParams(titleParams);

        layout.addView(titleTV);


        if (config.getSelect().equals("false")){
            //非选择框

            EditText edText = new EditText(getContext());
            edText.setGravity(Gravity.CENTER);
            edText.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            if (config.getValueType().equals("N")){
                edText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(27),ConvertUtils.dp2px(11),ConvertUtils.dp2px(27),ConvertUtils.dp2px(15));//4个参数按顺序分别是左上右下

            edText.setLayoutParams(layoutParams);

            edText.setPadding(10,10,10,10);

            layout.addView(edText);

            viewItemMap.put(config.getCode(),edText);



        }else{
            //选择框

            final OptionView optionView = new OptionView(getActivity(),config.getOptions());

            optionView.setTextSize(16);
            optionView.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            optionView.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(10),ConvertUtils.dp2px(11),ConvertUtils.dp2px(10),45);//4个参数按顺序分别是左上右下
            optionView.setLayoutParams(layoutParams);

            optionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    optionView.showPicker();
                }
            });

            layout.addView(optionView);
            viewItemMap.put(config.getCode(),optionView);

        }


        return layout;
    }

    private LinearLayout dramEmptyItem(){
        LinearLayout layout = new LinearLayout(getContext());

        int height = ConvertUtils.dp2px(120);
        int width = ConvertUtils.dp2px(0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.weight = 1;

        layout.setLayoutParams(params);

        return layout;
    }

    private void inputItemsValue(){

        for (int i = 0; i < recordInfo.getTempList().size(); i ++){
            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
            View view =  viewItemMap.get(temp.getCode());
            if (view instanceof EditText){
                EditText ed = (EditText)view;
                ed.setText(temp.getValue());
            }else{
                OptionView op = (OptionView)view;
                op.setText(temp.getValue());
            }
        }
    }

    /**
     * 保存生命体征数据
     */
    private void saveTempValue(){

        ArrayList<HashMap<String,String>> resList = new ArrayList();

        for (int i = 0; i < recordInfo.getTempList().size(); i ++){
            VitalSignRecordBean.TempListBean temp = recordInfo.getTempList().get(i);
            String code = temp.getCode();
            View view =  viewItemMap.get(temp.getCode());
            String value = "";

            if (view instanceof EditText){
                EditText ed = (EditText)view;
                value = ed.getText().toString();
            }else{
                OptionView op = (OptionView)view;
                value = op.getText().toString();
            }

            value = value.trim();
            if (value.length() == 0){
                continue;
            }

            HashMap<String,String> resItem = new HashMap<>();
            resItem.put("code",code);
            resItem.put("value",value);

            resList.add(resItem);
        }

        Gson gson = new Gson();
        String result = gson.toJson(resList);

        showLoadingTip(BaseActivity.LoadingType.FULL);

        waiting = true;

        VitalSignApiManager.saveTempData(curEpisodeId,timepoint,result, new VitalSignApiManager.SaveTempDataCallback() {
            @Override
            public void onSuccess(VitalSignSaveBean bean) {
                hideLoadFailTip();
                waiting = false;
                showToast("保存成功 ");
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
     * 选择时间
     */
    private void chooseTime(){
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
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.settype(1);
        //取时间前两个字符转为int（02，06...）

        List<String> hours = new ArrayList();
        for (int i = 0; i < timeFilterList.size(); i ++){
            String str = (String)((Map)timeFilterList.get(i)).get("time");
            hours.add(str);
        }

        mDialogAll.setintHour(hours);

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0,11);
        String time = TimeUtils.millis2String(millseconds).substring(11,16);

        if (!date.equals(dateFilterStr)){
            //日期发生改变，需重新请求数据
            dateFilterStr = date;
            recordContentView.removeAllViews();
            asyncGetVitalSignItems();
        }

        if (!time.equals(timeFilterStr)){
            timeFilterStr = time;
            recordContentView.removeAllViews();
            asyncGetVitalSignItems();
        }

        et_time.setText(TimeUtils.millis2String(millseconds).substring(0,16));
    }
}
