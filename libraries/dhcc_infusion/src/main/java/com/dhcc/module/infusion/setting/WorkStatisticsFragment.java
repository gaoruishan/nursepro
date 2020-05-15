package com.dhcc.module.infusion.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BaseHelper;
import com.base.commlibs.utils.CommonUtil;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.setting.adapter.WorkStatisticsAdapter;
import com.dhcc.module.infusion.setting.api.SettingApiManeger;
import com.dhcc.module.infusion.setting.bean.WorkStatisticsBean;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.res.infusion.CustomBarChart;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 工作统计
 * @author:gaoruishan
 * @date:202019-04-22/15:43
 * @email:grs0515@163.com
 */
public class WorkStatisticsFragment extends BaseFragment{
    private static final long ONE_DAY = 24 * 60 * 60 * 1000L;
    private BaseHelper helper;
    private WorkStatisticsAdapter workStatisticsAdapter;
    private CustomBarChart cbcChart;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle("输液工作统计");
        helper = new BaseHelper(this.getActivity());
        RecyclerView rv = RecyclerViewHelper.get(getActivity(), R.id.rv);
        workStatisticsAdapter = AdapterFactory.geWorkStatistics();
        rv.setAdapter(workStatisticsAdapter);
        cbcChart = mContainerChild.findViewById(R.id.cbc_chart);

        setDateTime();

        getWorkLoad();
    }

    private void setDateTime() {
        String dayStart = CommonUtil.longTimeToDay(System.currentTimeMillis() - ONE_DAY);
        helper.setTextData(R.id.tv_choose_date_start, dayStart);
        String dayEnd = CommonUtil.longTimeToDay(System.currentTimeMillis());
        helper.setTextData(R.id.tv_choose_date_end, dayEnd);
        CustomDateTimeView customDateTimeView = f(R.id.custom_cdtv, CustomDateTimeView.class);
        customDateTimeView.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                helper.setTextData(R.id.tv_choose_date_start, TimeUtils.millis2String(millseconds).substring(0, 10));
                getWorkLoad();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                helper.setTextData(R.id.tv_choose_date_end, TimeUtils.millis2String(millseconds).substring(0, 10));
                getWorkLoad();
            }
        });
    }

    private void getWorkLoad() {
        String dayStart = helper.getTextData(R.id.tv_choose_date_start);
        String dayEnd = helper.getTextData(R.id.tv_choose_date_end);
        helper.setTextData(R.id.tv_date_range, " ("+dayStart.split("-")[1] + "月" + dayStart.split("-")[2]+"日"
                + " - " + dayEnd.split("-")[1] + "月" + dayEnd.split("-")[2]+"日) ");
        SettingApiManeger.getWorkload(dayStart, dayEnd, new CommonCallBack<WorkStatisticsBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(WorkStatisticsBean bean, String type) {
                List<WorkStatisticsBean.TypeListBean> typeList = bean.getTypeList();
                if (typeList == null || typeList.size() <= 0) {
                    ToastUtils.showLong("暂无数据,请重新选择日期");
                    return;
                }
                //过滤用户
                if (UserUtil.showCurUserWorkload()) {
                    List<WorkStatisticsBean.UserListBean> listBeans = new ArrayList<>();
                    for (WorkStatisticsBean.UserListBean listBean : bean.getUserList()) {
                        if (listBean.getUserName().equals(SPUtils.getInstance().getString(SharedPreference.USERNAME))) {
                            listBeans.add(listBean);
                            setWorkTypeChartData(listBean.getUserTypeList());
                            break;
                        }
                    }
                    workStatisticsAdapter.replaceData(listBeans);
                }else {
                    workStatisticsAdapter.replaceData(bean.getUserList());
                    setWorkTypeChartData(bean.getTypeList());
                }

            }
        });
    }

    private void setWorkTypeChartData(List<WorkStatisticsBean.TypeListBean> beanTpeList) {
        // 反转
        Collections.reverse(beanTpeList);
        List<String> mStList = new ArrayList<>();
        List<Integer> mIntegerList = new ArrayList<>();
        int allTimes = 0;
        for (WorkStatisticsBean.TypeListBean b : beanTpeList) {
            if (!TextUtils.isEmpty(b.getWorkTypeNum())) {
                mStList.add(b.getWorkTypeDesc());
                int num = Integer.valueOf(b.getWorkTypeNum());
                mIntegerList.add(num);
                allTimes += num;
            }
        }
        cbcChart.updateAllChart(mStList, mIntegerList);
        helper.setTextData(R.id.tv_all_times, "" + allTimes);
    }

}
