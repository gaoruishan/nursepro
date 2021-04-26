package com.dhcc.module.nurse.task.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.adapter.TaskNurRateAdapter;
import com.dhcc.module.nurse.task.bean.NurRateTaskBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

/**
 * 护理评估
 * @author:gaoruishan
 * @date:202020-09-03/09:23
 * @email:grs0515@163.com
 */
public class NurRateFragment extends BaseNurseFragment {

    private String emrCode;
    private String Name;
    private String ModelNum;
    private String CodeId;
    private String bedNo;
    private String patName;
    private String bedStr;
    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private TaskNurRateAdapter taskNurRateAdapter;
    private List<NurRateTaskBean.TempCodeListBean> tempCodeList;

    @Override
    protected void initViews() {
        super.initViews();
        Name = bundle.getString("Name");
        emrCode = bundle.getString("EMRCode");
        ModelNum = bundle.getString("ModelNum");
        CodeId = bundle.getString("CodeId");

        addToolBarRightImageView(R.drawable.dhcc_filter_big_write, R.drawable.dhcc_icon_bed_select);

        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                emrCode = tempCodeList.get(position).getCode();
                getNurRateTaskList();
            }
        });
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 00:00", YYYY_MM_DD_HH_MM));
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 23:59", YYYY_MM_DD_HH_MM));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNurRateTaskList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNurRateTaskList();
            }
        });
        ImgResetView imgReset = f(R.id.img_resetview, ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                bedStr = "";
                getNurRateTaskList();
            }
        });
        taskNurRateAdapter = AdapterFactory.getTaskNurRateAdapter();
        RecyclerViewHelper.get(mContext, R.id.rv_list_ord).setAdapter(taskNurRateAdapter);

        taskNurRateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NurRateTaskBean.RecDataBean recDataBean = taskNurRateAdapter.getData().get(position);
                NurRateTaskBean.RecDataBean.RecordScoreBean scoreBean = recDataBean.getRecordScore().get(0);
                String strName = "com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment";
                Bundle bundle = new Bundle();
                bundle.putString("EpisodeID", recDataBean.getEpisodeID());
                bundle.putString("BedNo", recDataBean.getBedCode());
                bundle.putString("PatName", recDataBean.getPatientName());
                bundle.putString("EMRCode", emrCode);
                bundle.putString("GUID", "");
                bundle.putString("RecID", "");
                startFragment(strName, bundle);
            }
        });
    }

    private void getNurRateTaskList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getNeedEmr(bedStr, customDate.getStartDateText(), new CommonCallBack<NurRateTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurRateTaskBean bean, String type) {
                hideLoadingTip();
                taskNurRateAdapter.setInitData(bean.getRecData(),emrCode);
                tempCodeList = bean.getTempCodeList();
                customSheet.setDatas(tempCodeList);
                customSheet.setSheetDefCode(emrCode);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right2) {
            startFragment(FRAGMENT_BED_SELECT,null,1);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            Log.e(TAG,"(NurRateFragment.java:52) "+bedStr);
            getNurRateTaskList();
        }
    }
    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("护理评估");
        getNurRateTaskList();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_rate;
    }
}
