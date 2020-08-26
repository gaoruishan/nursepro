package com.dhcc.module.nurse.bloodsugar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.view.MyMarkerView;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.adapter.BloodSugarNotelistAdapter;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarNotelistBean;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.module.nurse
 * <p>
 * author Q
 * Date: 2020/8/20
 * Time:11:30
 */
public class BloodSugarValueMapFragment   extends BaseNurseFragment {

    private CustomDateTimeView customDate;
    private ChartLabelView chartLabelView;
    private LinearLayout llAll,llCancle;
    private View viewAll,viewCancle;
    private BloodSugarNotelistAdapter bloodSugarNotelistAdapter;
    private LineChart bloodSugarChartsdetail;
    private BloodSugarNotelistBean bloodSugarNotelistBean;

    private ArrayList<String> listCode = new ArrayList();
    private HashMap<String, Integer> mapColor = new HashMap<String, Integer>();
    private HashMap<String,ArrayList<Entry>> mapValue = new HashMap<>();
    private Typeface tfRegular;
    private Typeface tfLight;

    @Override
    protected void initViews() {
        super.initViews();
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        chartLabelView = f(R.id.chart_label, ChartLabelView.class);
        llAll = f(R.id.ll_select_all, LinearLayout.class);
        llAll.setOnClickListener(this::onClick);
        llCancle = f(R.id.ll_select_cancle, LinearLayout.class);
        llCancle.setOnClickListener(this::onClick);
        viewAll = f(R.id.view_ck_all, View.class);
        viewCancle = f(R.id.view_ck_cancle, View.class);
        bloodSugarNotelistAdapter = AdapterFactory.getBloodSugarNotelistAdapter();
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(false);
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10), YYYY_MM_DD));
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate.substring(0,10), YYYY_MM_DD));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarPats();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarPats();
            }
        });
        bloodSugarChartsdetail = f(R.id.bloodsugar_chartsdetail,LineChart.class);

        // no description text
        bloodSugarChartsdetail.getDescription().setEnabled(false);

        // enable touch gestures
        bloodSugarChartsdetail.setTouchEnabled(true);

        bloodSugarChartsdetail.setDragDecelerationFrictionCoef(0.9f);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(bloodSugarChartsdetail);
        bloodSugarChartsdetail.setMarker(mv);

        // enable scaling and dragging
        bloodSugarChartsdetail.setDragEnabled(true);
        bloodSugarChartsdetail.setScaleEnabled(true);
        bloodSugarChartsdetail.setDrawGridBackground(false);
        bloodSugarChartsdetail.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        bloodSugarChartsdetail.setPinchZoom(true);

        // set an alternative background color
//        vitalsignChartsdetail.setBackgroundColor(Color.LTGRAY);

        bloodSugarChartsdetail.animateX(1500);

        //是否在折线图上添加边框
        bloodSugarChartsdetail.setDrawBorders(false);

        // get the legend (only possible after setting data)
        Legend l = bloodSugarChartsdetail.getLegend();

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

        XAxis xAxis = bloodSugarChartsdetail.getXAxis();
//        xAxis.setTypeface(tfLight);
//        xAxis.setTextSize(11f);
//        xAxis.setTextColor(Color.WHITE);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
        xAxis.setEnabled(false);

        YAxis leftAxis = bloodSugarChartsdetail.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMaximum(30f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setLabelCount(10,false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);


        YAxis rightAxis = bloodSugarChartsdetail.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setTextSize(12f);
        rightAxis.setAxisMaximum(180f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setLabelCount(10,false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
//        setToolbarCenterTitle("血糖采集");
        if (bundle!=null){
            setToolbarCenterTitle(bundle.getString("patInfo"));
            episodeId = bundle.getString("episodeId");
        }
        getBloodSugarPats();
    }

    private void setData(List<BloodSugarNotelistBean.SugarInfoListBean> beans) {
        bloodSugarChartsdetail.clear();
        listCode = new ArrayList();
        mapColor = new HashMap<String, Integer>();
        ArrayList<Integer> listColor = new ArrayList<>();
        listColor.add(Color.RED);
        listColor.add(Color.YELLOW);
        listColor.add(Color.GRAY);
        listColor.add(Color.GREEN);
        listColor.add(Color.BLACK);
        listColor.add(Color.BLUE);
        listColor.add(Color.LTGRAY);
        listColor.add(Color.CYAN);
        listColor.add(Color.DKGRAY);
        for (int i = 0; i < bloodSugarNotelistBean.getFilterList().size(); i++) {
            listCode.add(bloodSugarNotelistBean.getFilterList().get(i).getDesc());
            if (i<listColor.size()){
                mapColor.put(bloodSugarNotelistBean.getFilterList().get(i).getDesc(),listColor.get(i));
            }else {
                mapColor.put(bloodSugarNotelistBean.getFilterList().get(i).getDesc(),listColor.get(0));
            }
        }

        mapValue = new HashMap<>();
        HashMap<String,LineDataSet> mapSet = new HashMap<>();
        for (int i = 0; i < listCode.size(); i++) {
            ArrayList<Entry> values = new ArrayList<>();
            mapValue.put(listCode.get(i),values);
            LineDataSet set = null;
            mapSet.put(listCode.get(i),set);
        }
        if (listCode.size()>0){
            llCancle.setVisibility(View.VISIBLE);
            llAll.setVisibility(View.VISIBLE);
        }

        HashMap map = new HashMap();
        for (int i = 0; i < listCode.size(); i++) {
            ArrayList<Float> list = new ArrayList();
            for (int j = 0; j < beans.size(); j++) {
                for (int k = 0; k < beans.get(j).getSugarData().size(); k++) {
                    if (beans.get(j).getSugarData().get(k).getCode().equals(listCode.get(i))){
                        if ("".equals(beans.get(j).getSugarData().get(k).getSugar())){
                            list.add(-1f);
                        }else {
                            try {
                                list.add(Float.parseFloat((String)beans.get(j).getSugarData().get(k).getSugar()));
                            }catch (Exception e){
                                list.add(-1f);
                            }

                        }
                    }
                }
            }
            map.put(listCode.get(i),list);
        }

        for (int i = 0; i < listCode.size(); i++) {
            int count = 0;
            ArrayList<Float> list = (ArrayList<Float>) map.get(listCode.get(i));
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > 0) {
                        mapValue.get(listCode.get(i)).add(new Entry(count, list.get(j)));
                        count++;
                }
            }
        }
            LineData data = new LineData();
            chartLabelView.removeAllViews();
            chartLabelView.setData(listCode,mapColor);
            for (int i = 0; i < listCode.size();  i++) {
                LineDataSet set = new LineDataSet(mapValue.get(listCode.get(i)),listCode.get(i));
                set.setAxisDependency(YAxis.AxisDependency.LEFT);
                set.setColor(mapColor.get(listCode.get(i)));
                set.setCircleColor(mapColor.get(listCode.get(i)));
                set.setLineWidth(2f);
                set.setCircleRadius(3f);
                set.setFillAlpha(65);
                set.setFillColor(mapColor.get(listCode.get(i)));
                set.setHighLightColor(Color.rgb(244, 117, 117));
                set.setDrawCircleHole(false);
                set.setDrawValues(false);
                data.addDataSet(set);
            }
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(100f);
            // set data
            bloodSugarChartsdetail.setData(data);

            chartLabelView.setOnChartClicListner(new ChartLabelView.OnChartClicListner() {
                @Override
                public void sure(ArrayList<String> listCode1) {
                    bloodSugarChartsdetail.clear();
                    LineData data1 = new LineData();
                    for (int i = 0; i < listCode1.size();  i++) {
                        LineDataSet set = new LineDataSet(mapValue.get(listCode1.get(i)),listCode1.get(i));
                        set.setAxisDependency(YAxis.AxisDependency.LEFT);
                        set.setColor(mapColor.get(listCode1.get(i)));
                        set.setCircleColor(mapColor.get(listCode1.get(i)));
                        set.setLineWidth(2f);
                        set.setCircleRadius(3f);
                        set.setFillAlpha(65);
                        set.setFillColor(mapColor.get(listCode1.get(i)));
                        set.setHighLightColor(Color.rgb(244, 117, 117));
                        set.setDrawCircleHole(false);
                        set.setDrawValues(false);
                        data1.addDataSet(set);
                    }
                    data1.setValueTextColor(Color.BLACK);
                    data1.setValueTextSize(100f);
                    // set data
                    bloodSugarChartsdetail.setData(data1);
                    bloodSugarChartsdetail.notifyDataSetChanged();
                }
            });

            llAll.performClick();
    }


    private void getBloodSugarPats(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BloodSugarApiManager.getSugarValueByDate(episodeId, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), new CommonCallBack<BloodSugarNotelistBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(BloodSugarNotelistBean bean, String type) {
                hideLoadingTip();
                bloodSugarNotelistBean = bean;
                ArrayList<BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean> list = new ArrayList<>();
                for (int i = 0; i < bean.getSugarInfoList().size(); i++) {
                    for (int j = 0; j < bean.getSugarInfoList().get(i).getSugarData().size(); j++) {
                        bean.getSugarInfoList().get(i).getSugarData().get(j).setDate(bean.getSugarInfoList().get(i).getDate());
                        list.add(bean.getSugarInfoList().get(i).getSugarData().get(j));
                    }
                }
                bloodSugarNotelistAdapter.setNewData(list);

//                listMap = (List) vitalSignDetailBean.getMap().get("tempDataList");

                if (bean.getSugarInfoList().size()>0){

                    setData(bean.getSugarInfoList());

                    // redraw
                    bloodSugarChartsdetail.invalidate();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==R.id.ll_select_all){
            bloodSugarChartsdetail.clear();
            viewCancle.setSelected(false);
            viewAll.setSelected(true);
            chartLabelView.selectAll();
            chartLabelView.setListCode(listCode);
            LineData data1 = new LineData();
            for (int i = 0; i < listCode.size();  i++) {
                LineDataSet set = new LineDataSet(mapValue.get(listCode.get(i)),listCode.get(i));
                set.setAxisDependency(YAxis.AxisDependency.LEFT);
                set.setColor(mapColor.get(listCode.get(i)));
                set.setCircleColor(mapColor.get(listCode.get(i)));
                set.setLineWidth(2f);
                set.setCircleRadius(3f);
                set.setFillAlpha(65);
                set.setFillColor(mapColor.get(listCode.get(i)));
                set.setHighLightColor(Color.rgb(244, 117, 117));
                set.setDrawCircleHole(false);
                set.setDrawValues(false);
                data1.addDataSet(set);
            }
            data1.setValueTextColor(Color.BLACK);
            data1.setValueTextSize(100f);
            // set data
            bloodSugarChartsdetail.setData(data1);
            bloodSugarChartsdetail.notifyDataSetChanged();
        }
        if (v.getId()==R.id.ll_select_cancle){
            viewCancle.setSelected(true);
            viewAll.setSelected(false);
            chartLabelView.setListCode(new ArrayList<>());
            bloodSugarChartsdetail.clear();
            chartLabelView.cancleAll();
        }
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        TaskViewApiManager.getScaninfo(scanInfo, new CommonCallBack<ScanResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }
            @Override
            public void onSuccess(ScanResultBean bean, String type) {

            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_sugar_detailmap;
    }

}
