package com.dhcc.nursepro.workarea.dosingreview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Calendar;

/**
 * DosingReviewFragment
 * 配液复核
 *
 * @author DevLix126
 * created at 2018/9/6 10:20
 */
public class DosingReviewFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private TextView tvDosingreviewAll;
    private TextView tvDosingreviewUndosing;
    private TextView tvDosingreviewDosing;
    private TextView tvDosingreviewUnreview;
    private TextView tvDosingreviewReview;
    private LinearLayout llDosingreviewDate;
    private TextView tvDosingreviewDate;
    private RecyclerView recyDosingreview;

    private String searchDate;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dosing_review, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_dosingreview), 0xffffffff, 17);

        searchDate = SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).substring(0, 10);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);


    }

    private void initView(View view) {
        tvDosingreviewAll = view.findViewById(R.id.tv_dosingreview_all);
        tvDosingreviewAll.setOnClickListener(this);
        tvDosingreviewUndosing = view.findViewById(R.id.tv_dosingreview_undosing);
        tvDosingreviewUndosing.setOnClickListener(this);
        tvDosingreviewDosing = view.findViewById(R.id.tv_dosingreview_dosing);
        tvDosingreviewDosing.setOnClickListener(this);
        tvDosingreviewUnreview = view.findViewById(R.id.tv_dosingreview_unreview);
        tvDosingreviewUnreview.setOnClickListener(this);
        tvDosingreviewReview = view.findViewById(R.id.tv_dosingreview_review);
        tvDosingreviewReview.setOnClickListener(this);
        llDosingreviewDate = view.findViewById(R.id.ll_dosingreview_date);
        llDosingreviewDate.setOnClickListener(this);
        tvDosingreviewDate = view.findViewById(R.id.tv_dosingreview_date);
        recyDosingreview = view.findViewById(R.id.recy_dosingreview);

        tvDosingreviewDate.setText(searchDate);

        setTopFilterSelect(tvDosingreviewAll);

        //提高展示效率
        recyDosingreview.setHasFixedSize(true);
        //设置的布局管理
        recyDosingreview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {

    }

    private void asyncInitData() {
        //        showLoadingTip(BaseActivity.LoadingType.FULL);
        Toast.makeText(getActivity(), "11111", Toast.LENGTH_SHORT).show();
    }

    private void setTopFilterSelect(View view) {
        tvDosingreviewAll.setSelected(view == tvDosingreviewAll);
        tvDosingreviewUndosing.setSelected(view == tvDosingreviewUndosing);
        tvDosingreviewDosing.setSelected(view == tvDosingreviewDosing);
        tvDosingreviewUnreview.setSelected(view == tvDosingreviewUnreview);
        tvDosingreviewReview.setSelected(view == tvDosingreviewReview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dosingreview_all:
                setTopFilterSelect(tvDosingreviewAll);

                asyncInitData();
                break;
            case R.id.tv_dosingreview_undosing:
                setTopFilterSelect(tvDosingreviewUndosing);

                asyncInitData();
                break;
            case R.id.tv_dosingreview_dosing:
                setTopFilterSelect(tvDosingreviewDosing);

                asyncInitData();
                break;
            case R.id.tv_dosingreview_unreview:
                setTopFilterSelect(tvDosingreviewUnreview);

                asyncInitData();
                break;
            case R.id.tv_dosingreview_review:
                setTopFilterSelect(tvDosingreviewReview);

                asyncInitData();
                break;
            case R.id.ll_dosingreview_date:
                chooseTime();

                break;
            default:
                break;
        }
    }

    private void chooseTime() {
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


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);


        if (!date.equals(searchDate)) {
            //日期时间发生改变，需重新请求数据
            searchDate = date;
            asyncInitData();
        }

        tvDosingreviewDate.setText(searchDate);
    }
}
