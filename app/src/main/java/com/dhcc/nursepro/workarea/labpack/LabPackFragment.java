package com.dhcc.nursepro.workarea.labpack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.workarea.labpack.adapter.LabPackAdapter;
import com.dhcc.nursepro.workarea.operation.adapter.OperationAdapter;
import com.dhcc.nursepro.workarea.patevents.PatEventsDetailFragment;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.widget.WheelView;

public class LabPackFragment extends BaseFragment implements View.OnClickListener,OnDateSetListener {

    private RecyclerView recLabPack;
    private TextView tvBuild,tvSend,tvSome,tvAll,tvDate;
    private View show1,show2,show3,show4;
    private LabPackAdapter labPackAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_labpack, container, false);
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
                startFragment(PatEventsDetailFragment.class);
            }
        });
        setToolbarRightCustomView(viewright);

        initview(view);

    }
    private void initview(View view){

        tvBuild = view.findViewById(R.id.tv_labpack_build);
        tvBuild.setOnClickListener(this);
        tvSend = view.findViewById(R.id.tv_labpack_send);
        tvSend.setOnClickListener(this);
        tvSome = view.findViewById(R.id.tv_labpack_some);
        tvSome.setOnClickListener(this);
        tvAll = view.findViewById(R.id.tv_labpack_all);
        tvAll.setOnClickListener(this);
        tvDate = view.findViewById(R.id.tv_labpack_date);
        tvDate.setOnClickListener(this);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//精确到分钟
        String today = format.format(date);
        tvDate.setText(today);


        show1 = view.findViewById(R.id.view_labpack_show1);
        show2 = view.findViewById(R.id.view_labpack_show2);
        show3 = view.findViewById(R.id.view_labpack_show3);
        show4 = view.findViewById(R.id.view_labpack_show4);

        setTopFilterSelect(tvBuild);
        showgone(show1);


        recLabPack = view.findViewById(R.id.recy_labpack);
        //提高展示效率
        recLabPack.setHasFixedSize(true);
        //设置的布局管理
        recLabPack.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recLabPack.setHasFixedSize(true);
        //设置的布局管理
        recLabPack.setLayoutManager(new LinearLayoutManager(getActivity()));

        labPackAdapter = new LabPackAdapter(new ArrayList<Map>());
        labPackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFragment(AddLabPackFragment.class);
            }
        });
        recLabPack.setAdapter(labPackAdapter);
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
        labPackAdapter.setNewData(list);

//        operationAdapter = new OperationAdapter(new ArrayList<Map>());
//        recOperation.setAdapter(operationAdapter);
//        List<Map> list = new ArrayList<>();
//        Map map = new HashMap();
//        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
//        operationAdapter.setNewData(list);


        //双选护士医生
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < locsBeanList.size(); i++) {
//            locDesc[i] = locsBeanList.get(i).getLocDesc();
//            list.add(locsBeanList.get(i).getLocDesc())
//        }
//
//        final DoublePicker picker = new DoublePicker(LoginActivity.this, list,list);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setDividerRatio(WheelView.DividerConfig.FILL);
//        picker.setSelectedIndex(0,0);
//        picker.setCycleDisable(true);
//        picker.setTextSize(20);
//        picker.setOnPickListener
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_labpack_build:
                setTopFilterSelect(tvBuild);
                showgone(show1);
                break;
            case R.id.tv_labpack_send:
                setTopFilterSelect(tvSend);
                showgone(show2);
                break;
            case R.id.tv_labpack_some:
                setTopFilterSelect(tvSome);
                showgone(show3);
                break;
            case R.id.tv_labpack_all:
                setTopFilterSelect(tvAll);
                showgone(show4);
                break;
            case R.id.tv_labpack_date:
                chooseTime();
                break;
            default:
               break;
        }

    }


    private void setTopFilterSelect(View view) {
        tvBuild.setSelected(view == tvBuild);
        tvSend.setSelected(view == tvSend);
        tvSome.setSelected(view == tvSome);
        tvAll.setSelected(view == tvAll);
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
        tvDate.setText(time);
    }

}
