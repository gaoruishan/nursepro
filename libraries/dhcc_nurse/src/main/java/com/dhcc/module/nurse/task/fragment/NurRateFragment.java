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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                for (NurRateTaskBean.RecDataBean.RecordScoreBean recordScoreBean : recDataBean.getRecordScore()) {
                    if (recordScoreBean.getLinkCode().equals(emrCode)) {
                        scoreBean = recordScoreBean;
                    }
                }
                String strName = "com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment";
                Bundle bundle = new Bundle();
                bundle.putString("EpisodeID", recDataBean.getEpisodeID());
                bundle.putString("BedNo", recDataBean.getBedCode());
                bundle.putString("PatName", recDataBean.getPatientName());
                if (TextUtils.isEmpty(emrCode) || hasDigit(emrCode)) {
                    emrCode = scoreBean.getLinkCode();
                }

                bundle.putString("EMRCode", emrCode);
                bundle.putString("GUID", scoreBean.getEmrCode());
                bundle.putString("RecID", "");
                startFragment(strName, bundle);
            }
        });
    }

    // 判断一个字符串是否含有数字
    public boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
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
                //去掉""的对象
                tempCodeList = bean.getTempCodeList();
                if (TextUtils.isEmpty(emrCode)|| hasDigit(emrCode)) {
                    emrCode = tempCodeList.get(0).getCode();
                }
                //emrCode的分类的 emrCode.equals(recDatum.getRecordScore().get(0).getLinkCode()
                taskNurRateAdapter.setInitData(bean.getRecData(),emrCode);
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
