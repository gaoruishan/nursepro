package com.dhcc.nursepro.workarea.nurrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
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
    private RecyclerView remodeldetail;
    TextView tvsend;

    String string = "";
    private NurRecordAdapter modelDetailAdapter;
    private List<NurRecordBean.ModelListBean> listBeans;
    private NurRecordBean nurRecordBean;
    private FlowLayout recordContentView;
    private HashMap<String,View> viewItemMap;
    private TextView textViewSel;
    private String dt = "date";
    private Map patInfoMap=new HashMap<String,String>();
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
        initAdapter();
        initData();

    }
    private void initview(View view) {
        tvsend = view.findViewById(R.id.tv_send);

        tvsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        recordContentView = view.findViewById(R.id.ll_vitalsign_record_content);
        viewItemMap = new HashMap<>();

        remodeldetail = view.findViewById(R.id.rec_modeltail);
        //提高展示效率
        remodeldetail.setHasFixedSize(true);
        //设置的布局管理
        remodeldetail.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    private void initAdapter() {

        modelDetailAdapter = new NurRecordAdapter(new ArrayList<NurRecordBean.ModelListBean>(),getContext());
        remodeldetail.setAdapter(modelDetailAdapter);
        modelDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.messagerightmenu) {
                    initData();
                }
            }
        });
    }
    private void initData() {

        HashMap<String, String> map = new HashMap<>();
//        map.put("carryNo", carryNo);
//        if (!saveFlag.equals("")) {
            map.put("locId", "197");
            map.put("EmrCode", "DHCNURANHUI2");
            map.put("episodeId", "11");
//        }
        NurRecordManager.getModelDetailListMsg(map, "getModelDetail", new NurRecordManager.getLabOutCallBack() {
            @Override
            public void onSuccess(NurRecordBean modelDetailBean) {
                nurRecordBean = modelDetailBean;
                listBeans = modelDetailBean.getModelList();
                modelDetailAdapter.setNewData(listBeans);
                Gson gson = new Gson();
                String result = gson.toJson(nurRecordBean.getPatInfo());
                patInfoMap= gson.fromJson(result, HashMap.class);
                drawInputItems();
                inputItemsValue();
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }



    /**
     * 绘制UI相关
     */

    public void drawInputItems(){

        LinearLayout layout = null;

        int size = listBeans.size();

        for(int i = 0; i < size; i ++){
            layout = new LinearLayout(getContext());
            recordContentView.addView(layout);
            int height = 180;
            int width = ScreenUtils.getScreenWidth();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            NurRecordBean.ModelListBean config = listBeans.get(i);

            LinearLayout item = drawItem(config);
            layout.addView(item);
        }
    }


    private LinearLayout drawItem(NurRecordBean.ModelListBean config){

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
        int width = ConvertUtils.dp2px(Float.parseFloat(config.getWidth()));;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,height);
        params.setMargins(0,ConvertUtils.dp2px(20),0,0);
//        params.weight = 1;

        layout.setLayoutParams(params);


        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getItemDesc());
//        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        titleTV.setTextSize(Float.parseFloat(config.getFontSize()));
        //判断是否必填，必填的话字体变红
        if (config.getMustFill().equals("1")){
            titleTV.setTextColor(getResources().getColor(R.color.lab_warning_red));
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.setMargins(ConvertUtils.dp2px(5),0,ConvertUtils.dp2px(5),0);
        titleTV.setLayoutParams(titleParams);
        titleTV.setGravity(Gravity.CENTER_VERTICAL);
//        titleTV.setBackgroundColor(getResources().getColor(R.color.blue_light));

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!config.getToastStr().equals("")){
                    showToast(config.getToastStr());
                }
            }
        });
        layout.addView(titleTV);

        if (config.getItemType().equals("E")){
            //输入框

            EditText edText = new EditText(getContext());
            edText.setGravity(Gravity.LEFT);
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            edText.setTextSize(Float.parseFloat(config.getFontSize()));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            edText.setLayoutParams(layoutParams);
            //判断基本信息是否为空，为空则可手动编辑，不为空，将信息填入默认值，并不可编辑
            if (config.getEditFlag().equals("1")) {
                edText.setTextColor(getResources().getColor(R.color.black));
            }else {
                edText.setCursorVisible(false);
                edText.setFocusable(false);
                edText.setFocusableInTouchMode(false);
                edText.setTextColor(getResources().getColor(R.color.divider_line_color));
                edText.setText(config.getPatInfo());
            }
            if (config.getPatInfo().equals("")){
                edText.setText(config.getItemdeValue());
            }else {

//                Gson gson = new Gson();
//                String result = gson.toJson(nurRecordBean.getPatInfo());
//                showToast(result);
                //将bean转为map，config.getPatInfo())作为键获取值
                edText.setText((patInfoMap.get(config.getPatInfo())+""));
            }
            layout.addView(edText);

        }else if (config.getItemType().equals("C")){
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0,ConvertUtils.dp2px(20),0,0);
            layout.setLayoutParams(params1);
            FlowLayout flowCheckGroup = new FlowLayout(getContext());
            String[] split = config.getItemValue().split(";");
            for (int i = 0;i<split.length;i++){
                CheckBox cb = new CheckBox(getContext());
                cb.setText(split[i]+"");
                cb.setTextSize(Float.parseFloat(config.getFontSize()));
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb.isChecked()){
                            showToast(cb.getText()+"----");
                        }else {

                        }

                    }
                });
                flowCheckGroup.addView(cb);
            }
            layout.addView(flowCheckGroup);
        }else if (config.getItemType().equals("R")){

//            TextView tvalue = new TextView(getContext());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            tvalue.setLayoutParams(layoutParams);
//            tvalue.setText("此处是链接");
//            tvalue.setTextColor(getResources().getColor(R.color.blue));
//            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
//            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
//            tvalue.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
//            LinearLayout layout1 = new LinearLayout(getContext());
//            layout1.setLayoutParams(params1);
//            layout1.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            params2.weight = 1;
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params2);
            String[] split = config.getItemValue().split("!");
            for (int i = 0;i<split.length;i++){
                RadioButton rb = new RadioButton(getContext());
                rb.setTextSize(Float.parseFloat(config.getFontSize()));
                rb.setText(split[i]+"");
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(rb.getText()+"----");
                    }
                });
                radioGroup.addView(rb);

            }
//            layout.addView(radioGroup);

//            layout1.addView(radioGroup);
//            layout1.addView(tvalue);
            layout.addView(radioGroup);

        }else if (config.getItemType().equals("T")){
            if (config.getTitleHiddeFlag().equals("0")){
                TextView tvalue = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                tvalue.setLayoutParams(layoutParams);
                tvalue.setText(config.getItemdeValue()+"");
                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                layout.addView(tvalue);
            }else {
                TextView tvalue = new TextView(getContext());
                tvalue.setText(config.getItemdeValue() + "");
//        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                layout.addView(tvalue);
            }
        } else if (config.getItemType().equals("D")){
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue()+"2018-12-11");
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            tvalue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewSel = tvalue;
                    dt = "date";
                    chooseDate();
                }
            });
            layout.addView(tvalue);
        } else if (config.getItemType().equals("Ti")){
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue()+"11:11");
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            tvalue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewSel = tvalue;
                    dt = "time";
                    chooseTime();
                }
            });

            layout.addView(tvalue);
        } else{

            //选择框
            List ll = new ArrayList();
            ll.add("1");
            ll.add("2");
            final OptionView optionView = new OptionView(getActivity(),ll);

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
            viewItemMap.put(config.getItemCode(),optionView);

        }


        return layout;
    }
    private LinearLayout dramEmptyItem(){
        LinearLayout layout = new LinearLayout(getContext());

        int height = ConvertUtils.dp2px(180);
        int width = ConvertUtils.dp2px(0);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.weight = 1;

        layout.setLayoutParams(params);

        return layout;
    }
    private void inputItemsValue(){

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
    private void chooseDate(){
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
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");

    }
    private void chooseTime(){
        Calendar calendar = Calendar.getInstance();

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
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0,11);
        String time = TimeUtils.millis2String(millseconds).substring(11,16);
        if (dt.equals("date")){
            textViewSel.setText(date+"");

        }else {
            textViewSel.setText(time+"");

        }
    }
}
