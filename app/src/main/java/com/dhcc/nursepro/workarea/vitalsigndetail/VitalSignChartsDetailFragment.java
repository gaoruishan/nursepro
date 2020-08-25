package com.dhcc.nursepro.workarea.vitalsigndetail;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.view.MyMarkerView;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsigndetail.api.VitalSignDetailApiManager;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VitalSignChartsDetailFragment
 *
 * @author Devlix126
 * created at 2020/2/19 16:51
 */
public class VitalSignChartsDetailFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private TextView tvStdate;
    private TextView tvEndate;
    private LineChart vitalsignChartsdetail;

    private Typeface tfRegular;
    private Typeface tfLight;

    private SPUtils spUtils = SPUtils.getInstance();

    //以今天为最后一天，查询之前7天的数据
    private String stDate;
    private String enDate;
    private String episodeId;
    private String datestr;

    private List<Map> listMap = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsignchartsdetail), 0xffffffff, 17);

        //        tfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        //        tfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        enDate = spUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);
        long enDateMillion = TimeUtils.string2Millis(enDate + "  00:00:00");
        stDate = TimeUtils.date2String(TimeUtils.getDate(enDateMillion, -7L, TimeConstants.DAY)).substring(0, 10);
        Bundle bundle = getArguments();

        if (bundle != null) {
            episodeId = bundle.getString("episodeId");
        }

        initView(view);


        initData();
    }

    private void initView(View view) {
        tvStdate = view.findViewById(R.id.tv_stdate);
        tvEndate = view.findViewById(R.id.tv_endate);
        tvStdate.setOnClickListener(this);
        tvEndate.setOnClickListener(this);
        tvStdate.setText(stDate);
        tvEndate.setText(enDate);
        vitalsignChartsdetail = view.findViewById(R.id.vitalsign_chartsdetail);


        // no description text
        vitalsignChartsdetail.getDescription().setEnabled(false);

        // enable touch gestures
        vitalsignChartsdetail.setTouchEnabled(true);

        vitalsignChartsdetail.setDragDecelerationFrictionCoef(0.9f);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(vitalsignChartsdetail);
        vitalsignChartsdetail.setMarker(mv);

        // enable scaling and dragging
        vitalsignChartsdetail.setDragEnabled(true);
        vitalsignChartsdetail.setScaleEnabled(true);
        vitalsignChartsdetail.setDrawGridBackground(false);
        vitalsignChartsdetail.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        vitalsignChartsdetail.setPinchZoom(true);

        // set an alternative background color
//        vitalsignChartsdetail.setBackgroundColor(Color.LTGRAY);

        vitalsignChartsdetail.animateX(1500);

        //是否在折线图上添加边框
        vitalsignChartsdetail.setDrawBorders(true);

        // get the legend (only possible after setting data)
        Legend l = vitalsignChartsdetail.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(tfLight);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //        l.setYOffset(11f);

        XAxis xAxis = vitalsignChartsdetail.getXAxis();
//        xAxis.setTypeface(tfLight);
//        xAxis.setTextSize(11f);
//        xAxis.setTextColor(Color.WHITE);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
        xAxis.setEnabled(false);

        YAxis leftAxis = vitalsignChartsdetail.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMaximum(43f);
        leftAxis.setAxisMinimum(34f);
        leftAxis.setLabelCount(10,false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = vitalsignChartsdetail.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setTextSize(12f);
        rightAxis.setAxisMaximum(180f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setLabelCount(10,false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }

    private void initData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("episodeId", episodeId);
        map.put("stDate", stDate);
        map.put("endDate", enDate);
        VitalSignDetailApiManager.getVitalSignDetail(map, new VitalSignDetailApiManager.GetEventsResultMsgCallBack() {
            @Override
            public void onSuccess(VitalSignDetailBean vitalSignDetailBean) {

                listMap = (List) vitalSignDetailBean.getMap().get("tempDataList");

                setData(listMap);

                // redraw
                vitalsignChartsdetail.invalidate();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void setData(List<Map> listMap) {
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        LineDataSet set1, set2;
        int count = 0;

        for (int i = 0; i < listMap.size(); i++) {
            Map map = listMap.get(i);
            float temperature;
            float pulse;

            if ("".equals(map.get("temperature"))) {
                temperature = -1f;
            }else{
                temperature = Float.parseFloat((String) map.get("temperature"));
            }

            if ("".equals(map.get("pulse"))) {
                pulse = -1f;
            }else{
                pulse = Float.parseFloat((String) map.get("pulse"));
            }

            if (temperature > 0) {
                values1.add(new Entry(count, temperature));
            }
            if (pulse > 0) {
                values2.add(new Entry(count, pulse));
            }

            if (temperature > 0 || pulse > 0) {
                count++;
            }
        }

        if (vitalsignChartsdetail.getData() != null &&
                vitalsignChartsdetail.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) vitalsignChartsdetail.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) vitalsignChartsdetail.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            set1.setDrawValues(false);
            set2.setDrawValues(false);
            vitalsignChartsdetail.getData().notifyDataChanged();
            vitalsignChartsdetail.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "体温/℃");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);


            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "脉搏/次");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            // create a data object with the data sets
            set1.setDrawValues(false);
            set2.setDrawValues(false);
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(10f);

            // set data
            vitalsignChartsdetail.setData(data);
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_charts_detail, container, false);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        //精确到day
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        if (datestr.equals("start")) {
            tvStdate.setText(time);
            stDate = time;
        } else {
            tvEndate.setText(time);
            enDate = time;
        }
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_stdate:
                datestr = "start";
                pickTime(TimeUtils.string2Millis(tvStdate.getText().toString() + " 00:00:00"));
                break;
            case R.id.tv_endate:
                datestr = "end";
                pickTime(TimeUtils.string2Millis(tvEndate.getText().toString() + " 00:00:00"));
                break;
            default:
                break;
        }
    }

    private void pickTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getActivity().getSupportFragmentManager(), "ALL");
    }
}