package com.dhcc.nursepro.workarea.labout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.labout.adapter.LabOutAdapter;
import com.dhcc.nursepro.workarea.labout.api.LabOutApiManager;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LabOutListFragment extends BaseFragment implements View.OnClickListener,OnDateSetListener {

    private RecyclerView recLabOut;
    private TextView tvType1,tvType2,tvType3,tvType4,tvStartDate,tvEndDate;
    private View show1,show2,show3,show4;
    private LabOutAdapter labOutAdapter;
    private List<LabOutListAllBean.LabOutListBean> listLabAll,listLabNow;
    private String dateStr,CarrayCerate="No",CarrayDel="No",CarrayNo = "",TypeStr = "Type1";
    private List<LabOutListAllBean.TypeListBean> listType;



    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laboutlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("检验打包",0xffffffff,17);
        //右上角按钮"新建"
        View viewright =  View.inflate(getActivity(),R.layout.view_fratoolbar_right,null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  新建   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startFragment(PatEventsDetailFragment.class);
                CarrayCerate = "Yes";
                initData();
                setTopFilterSelect(tvType1);
                showgone(show1);
                TypeStr = "Type1";
                getLabOutList();
            }
        });
        setToolbarRightCustomView(viewright);
        initview(view);
        initData();



    }
    private void initview(View view){

        listType = new ArrayList<LabOutListAllBean.TypeListBean>();



        tvType1 = view.findViewById(R.id.tv_labout_type1);
        tvType1.setOnClickListener(this);
        tvType2 = view.findViewById(R.id.tv_labout_type2);
        tvType2.setOnClickListener(this);
        tvType3 = view.findViewById(R.id.tv_labout_type3);
        tvType3.setOnClickListener(this);
        tvType4 = view.findViewById(R.id.tv_labout_type4);
        tvType4.setOnClickListener(this);
        tvStartDate = view.findViewById(R.id.tv_labout_startdate);
        tvStartDate.setOnClickListener(this);
        tvEndDate = view.findViewById(R.id.tv_labout_enddate);
        tvEndDate.setOnClickListener(this);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//精确到分钟
        String today = format.format(date);
        tvStartDate.setText(today);
        tvEndDate.setText(today);

        show1 = view.findViewById(R.id.view_labout_show1);
        show2 = view.findViewById(R.id.view_labout_show2);
        show3 = view.findViewById(R.id.view_labout_show3);
        show4 = view.findViewById(R.id.view_labout_show4);

        setTopFilterSelect(tvType1);
        showgone(show1);


        recLabOut = view.findViewById(R.id.recy_labout);
        //提高展示效率
        recLabOut.setHasFixedSize(true);
        //设置的布局管理
        recLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recLabOut.setHasFixedSize(true);
        //设置的布局管理
        recLabOut.setLayoutManager(new LinearLayoutManager(getActivity()));

        labOutAdapter = new LabOutAdapter(new ArrayList<LabOutListAllBean.LabOutListBean>());
        recLabOut.setAdapter(labOutAdapter);

        labOutAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()== R.id.tv_lapack_del){
                    CarrayCerate = "Yes";
                    CarrayDel = "Yes";
                    CarrayNo = listLabNow.get(position).getCarryNo();
                    initData();
                }else if (view.getId() == R.id.messagecontentll){
                    String carryNodetail = listLabNow.get(position).getCarryNo();
                    Bundle bundle = new Bundle();
                    bundle.putString("CarryNo",carryNodetail);
                    startFragment(LabOutDetailFragment.class,bundle);
                }

            }
        });

    }

    private void initData(){
        showLoadingTip(BaseActivity.LoadingType.FULL);

        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String,String> map = new HashMap<>();
        map.put("stdate",tvStartDate.getText().toString());
        map.put("enddate",tvEndDate.getText().toString());
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        if (CarrayCerate.equals("Yes")) {
            map.put("userId", spUtils.getString(SharedPreference.USERID));
            if (CarrayDel.equals("Yes")){
                map.put("carryNo",CarrayNo);
            }
        }
//        map.put("carryNo","");

        LabOutApiManager.getLabOutListMsg(map, "getLabOutList", new LabOutApiManager.getLabOutCallBack() {
            @Override
            public void onSuccess(LabOutListAllBean labOutListAllBean) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                if (listType.size() == 0){
                    listType = labOutListAllBean.getTypeList();
                    tvType1.setText(listType.get(0).getDesc());
                    tvType2.setText(listType.get(1).getDesc());
                    tvType3.setText(listType.get(2).getDesc());
                    tvType4.setText(listType.get(3).getDesc());
                }

                listLabAll = labOutListAllBean.getLabOutList();
                getLabOutList();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                CarrayCerate = "No";
                CarrayDel = "No";
                showToast(code+":"+msg);
            }
        });

    }
    private void getLabOutList(){
        listLabNow = new ArrayList<>();
        for (int i = 0;i<listLabAll.size();i++){
            switch (TypeStr){
                case "Type1":
                    if (listLabAll.get(i).getStatus().equals(listType.get(0).getDesc())){
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type2":
                    if (listLabAll.get(i).getStatus().equals(listType.get(1).getDesc())){
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type3":
                    if (listLabAll.get(i).getStatus().equals(listType.get(2).getDesc())){
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                case "Type4":
                    if (listLabAll.get(i).getStatus().equals(listType.get(3).getDesc())){
                        listLabNow.add(listLabAll.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
        labOutAdapter.setNewData(listLabNow);
        labOutAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_labout_type1:
                TypeStr = "Type1";
                setTopFilterSelect(tvType1);
                showgone(show1);
                getLabOutList();
                break;
            case R.id.tv_labout_type2:
                TypeStr = "Type2";
                setTopFilterSelect(tvType2);
                showgone(show2);
                getLabOutList();
                break;
            case R.id.tv_labout_type3:
                TypeStr = "Type3";
                setTopFilterSelect(tvType3);
                showgone(show3);
                getLabOutList();
                break;
            case R.id.tv_labout_type4:
                TypeStr = "Type4";
                setTopFilterSelect(tvType4);
                showgone(show4);
                getLabOutList();
                break;
            case R.id.tv_labout_startdate:
                dateStr = "start";
                chooseTime();
                break;
            case R.id.tv_labout_enddate:
                dateStr = "end";
                chooseTime();
                break;
            default:
               break;
        }

    }




    private void setTopFilterSelect(View view) {
        tvType1.setSelected(view == tvType1);
        tvType2.setSelected(view == tvType2);
        tvType3.setSelected(view == tvType3);
        tvType4.setSelected(view == tvType4);
    }
    private void showgone(View view){
        show1.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show2.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show3.setBackgroundColor(getResources().getColor(R.color.blue_light));
        show4.setBackgroundColor(getResources().getColor(R.color.blue_light));
        view.setBackgroundColor(getResources().getColor(R.color.blue));
    }


    private void chooseTime(){
        long tenYears = 3L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("选择日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

                 mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//精确到分钟
        String time = format.format(date);
        if (dateStr.equals("start")) {
            tvStartDate.setText(time);
        }else {
            tvEndDate.setText(time);
        }
        initData();
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}