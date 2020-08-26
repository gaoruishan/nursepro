package com.dhcc.module.nurse.bloodsugar;

import android.graphics.Color;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.adapter.BloodSugarNotelistAdapter;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarNotelistBean;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.StatusListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.module.nurse.bloodsugar
 * <p>
 * author Q
 * Date: 2020/8/21
 * Time:16:12
 */
public class BloodSugarNoteListFragment  extends BaseNurseFragment {

    private CustomDateTimeView customDate;
    private BloodSugarNotelistAdapter bloodSugarNotelistAdapter;
    private ArrayList<BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean> listBeans =new ArrayList<>();
    private BloodSugarNotelistBean bloodSugarNotelistBean;
    @Override
    protected void initViews() {
        super.initViews();
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        bloodSugarNotelistAdapter = AdapterFactory.getBloodSugarNotelistAdapter();
        RecyclerViewHelper.get(mContext, R.id.recy_notelist).setAdapter(bloodSugarNotelistAdapter);
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

    }

    @Override
    protected void initDatas() {
        super.initDatas();
//        setToolbarCenterTitle("血糖采集");
        if (bundle!=null){
            setToolbarCenterTitle(bundle.getString("patInfo"));
            episodeId = bundle.getString("episodeId");
        }
        addToolBarRightImageView(0, R.drawable.dhcc_filter_big_write);
        setbStatus(true);
        setbTime(false);
        addToolBarRightPopWindow();
        setbCheckbox(true);
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {

            }

            @Override
            public void statusSure(String code) {
                String[] str = code.split(";");
                listFilter(str);
            }
        });

        getBloodSugarPats();
    }

    private void listFilter(String[] str){
        ArrayList<BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean> list = new ArrayList<>();
        for (int i = 0; i < listBeans.size(); i++) {
            for (int j = 0; j < str.length; j++) {
                if (str[j].equals(listBeans.get(i).getCode())){
                    list.add(listBeans.get(i));
                }
            }
        }
        bloodSugarNotelistAdapter.setNewData(list);
        if (str.length==1){
            bloodSugarNotelistAdapter.setNewData(listBeans);
        }
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
                listBeans = new ArrayList<>();
                for (int i = 0; i < bean.getSugarInfoList().size(); i++) {
                    for (int j = 0; j < bean.getSugarInfoList().get(i).getSugarData().size(); j++) {
                        bean.getSugarInfoList().get(i).getSugarData().get(j).setDate(bean.getSugarInfoList().get(i).getDate());
                        listBeans.add(bean.getSugarInfoList().get(i).getSugarData().get(j));
                    }
                }
                bloodSugarNotelistAdapter.setNewData(listBeans);

                List<StatusListBean> listBeans = new ArrayList<>();

                for (int i = 0; i < bloodSugarNotelistBean.getFilterList().size(); i++) {
                    StatusListBean statusListBean = new StatusListBean();
                    statusListBean.setText(bloodSugarNotelistBean.getFilterList().get(i).getDesc());
                    statusListBean.setValue("code"+i);
                    listBeans.add(statusListBean);
                }

                setStatusListData(listBeans);
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right2) {
            setMaskShow();
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
        return R.layout.fragment_blood_sugar_notelist;
    }

}