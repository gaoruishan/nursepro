package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.SharedPreference;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.dhcc.nursepro.setting
 * <p>
 * author Q
 * Date: 2018/9/13
 * Time:14:36
 */
public class SettingDateTimeFragment extends BaseFragment implements View.OnClickListener,OnDateSetListener {

    private TextView tvStart;
    private TextView tvEnd;
    private SPUtils spUtils = SPUtils.getInstance();
    private String dateStr;
    private String startDate="",endDate="";
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_datetime, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("医嘱查询时间");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spUtils.put(SharedPreference.SCHSTDATETIME,startDate);
                spUtils.put(SharedPreference.SCHENDATETIME,endDate);
                finish();
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);

    }

    private void initView(View view){
        tvStart = view.findViewById(R.id.tv_setting_starttime);
        tvStart.setOnClickListener(this);
        tvEnd = view.findViewById(R.id.tv_setting_endtime);
        tvEnd.setOnClickListener(this);

        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME);
        tvStart.setText(startDate);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME);
        tvEnd.setText(endDate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_setting_starttime:
                dateStr = "start";
                chooseTime(TimeUtils.string2Millis(tvStart.getText().toString()+":00"));
                break;
            case R.id.tv_setting_endtime:
                dateStr = "end";
                chooseTime(TimeUtils.string2Millis(tvEnd.getText().toString()+":00"));
                break;
            default:
                break;
        }

    }

    private void chooseTime(long currentTimeMillis){
        long tenYears = 3L * 365 * 1000 * 60 * 60 * 24L;

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
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//精确到分钟
        String time = format.format(date);
        if (dateStr.equals("start")) {
            tvStart.setText(time);
            startDate = time;
        }else {
            tvEnd.setText(time);
            endDate = time;
        }

    }
}
