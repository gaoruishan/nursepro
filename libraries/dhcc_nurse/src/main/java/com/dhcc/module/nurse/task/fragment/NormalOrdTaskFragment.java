package com.dhcc.module.nurse.task.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommHttp;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.adapter.HealthEduEndAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduNeedAdapter;
import com.dhcc.module.nurse.education.bean.EduSheetListBean;
import com.dhcc.module.nurse.education.bean.EduTaskListBean;
import com.dhcc.module.nurse.education.bean.EducationListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.fragment.HealthEduAddFragment;
import com.dhcc.module.nurse.education.fragment.HealthEduContentFragment;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.adapter.TaskNormalOrdAdapter;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.OrderExecResultBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TaskBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:10
 */
public class NormalOrdTaskFragment extends BaseNurseFragment {

    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private RecyclerView recNormalOrd;
    private TaskNormalOrdAdapter taskNormalOrdAdapter;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private NormalOrdTaskBean normalOrdTaskBean = new NormalOrdTaskBean();
    private CustomOrdExeBottomView customSelectBottom;
    private ImgResetView imgReset;
    private int taskSheetSelet = 0;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private int askCount = 0;
    private String regNo = "",bedStr = "",sheetCode,patInfoFromTask;
    private Boolean ifFromTask=false;
    private List<TimesListBean> timesListBeans= new ArrayList<>();

    @Override
    protected void initDatas() {
        super.initDatas();
        addToolBarRight();
        setHindBottm(50);
        setToolbarCenterTitle("常规治疗");
        if (bundle != null) {
            if (bundle.getString("sheetCode") != null) {
                sheetCode = bundle.getString("sheetCode");
                regNo = bundle.getString("regNo");
                patInfoFromTask = bundle.getString("patInfo");
                bedStr = bundle.getString("bedStr");
                startDate = bundle.getString("sttDateTime").substring(0, 10);
                startTime = bundle.getString("sttDateTime").substring(11, 16);
                endDate = bundle.getString("endDateTime").substring(0, 10);
                endTime = bundle.getString("endDateTime").substring(11, 16);
                timesListBeans = (List<TimesListBean>) bundle.getSerializable("timeList");
                if (timesListBeans.size()>0){
                    setTimeListData(timesListBeans);
                }
                customDate.setStartDateTime(TimeUtils.string2Millis(startDate+" "+startTime, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDate+" "+endTime, YYYY_MM_DD_HH_MM));
                ifFromTask = true;
            }
        }
        imgReset = f(R.id.img_resetview, ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                regNo = "";
                bedStr = "";
                getNormalOrdList();
            }
        });
        if (patInfoFromTask!=null){
            imgReset.setTvPatText(patInfoFromTask);
        }
        getNormalOrdList();
    }
    private void addToolBarRight(){
        addToolBarRightImageView(R.drawable.dhcc_filter_big_write, R.drawable.dhcc_icon_bed_select);

        addToolBarRightPopWindow();
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {
                customDate.setStartDateTime(TimeUtils.string2Millis(sttDt, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDt, YYYY_MM_DD_HH_MM));
                showToast(sttDt);
            }

            @Override
            public void statusSure(String code) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right1) {
            setMaskShow();
        }
        if (v.getId() == R.id.img_toolbar_right2) {
            try {
                Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName(FRAGMENT_BED_SELECT);
                startFragment(BedFragmentClass,1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            if (askCount == 0) {
                askCount++;
                getNormalOrdList();
            }
        }
    }

    private void getNormalOrdList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getNormalTaskList(regNo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(),bedStr, new CommonCallBack<NormalOrdTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                askCount = 0;
                hideLoadingTip();
            }
            @Override
            public void onSuccess(NormalOrdTaskBean bean, String type) {
                askCount = 0;
                hideLoadingTip();
                if (regNo.equals("")){
                    imgReset.setTvPatText("全部患者");
                }
                if (!bedStr.equals("")){
                    imgReset.setTvPatText("选床患者");
                }
                normalOrdTaskBean = bean;
                mSheetListBeanList = new ArrayList<>();
//                if (regNo.equals("")){
//                    imgReset.setTvPatText("全部患者");
//                }
                for (int i = 0; i <bean.getBodyUnExec().size() ; i++) {
                    mSheetListBeanList.add(new SheetListBean(bean.getBodyUnExec().get(i).getCode(),bean.getBodyUnExec().get(i).getName(),bean.getBodyUnExec().get(i).getOrders().size()+""));
                }
                customSheet.setDatas(mSheetListBeanList);
                getLeftFilterTaskList();
            }
        });
    }

    private void exeNormalTask(){
        String oeoreId = "";
        String ordAdd = "";
        for (int i = 0; i < taskNormalOrdAdapter.getData().size(); i++) {
            if (taskNormalOrdAdapter.getData().get(i).get(0).getSelect()){
                for (int j = 0; j < taskNormalOrdAdapter.getData().get(i).size(); j++) {
                    ordAdd = ordAdd+taskNormalOrdAdapter.getData().get(i).get(j).getOrderInfo().getID()+"^";
                }
            }
        }
        if (ordAdd.contains("^")){
            oeoreId = ordAdd.substring(0,ordAdd.length()-1);
        }
        TaskViewApiManager.getNormalTaskExecResult(oeoreId, new CommonCallBack<OrderExecResultBean>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(OrderExecResultBean bean, String type) {
                showToast(bean.getMsg());
                getNormalOrdList();
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSelectBottom = f(R.id.custom_ordnormaltask_select_bottom, CustomOrdExeBottomView.class);
        customSelectBottom.setExeTypeVisible(View.GONE);
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("执行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exeNormalTask();
            }
        }));
        customSelectBottom.addBottomView(beans);
        customSelectBottom.setSelectText("已选 " + 0 + " 个");

        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getLeftFilterTaskList();
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
                getNormalOrdList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNormalOrdList();
            }
        });


        recNormalOrd = RecyclerViewHelper.get(mContext, R.id.rv_list_ord);
        taskNormalOrdAdapter = AdapterFactory.getTaskNormalOrdAdapter();
        recNormalOrd.setAdapter(taskNormalOrdAdapter);

        taskNormalOrdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (taskNormalOrdAdapter.getData().get(position).get(0).getSelect()){
                    taskNormalOrdAdapter.getData().get(position).get(0).setSelect(false);
                }else {
                    taskNormalOrdAdapter.getData().get(position).get(0).setSelect(true);
                }
                taskNormalOrdAdapter.notifyDataSetChanged();
                int taskSelect = 0;
                for (int i = 0; i < taskNormalOrdAdapter.getData().size(); i++) {
                    if (taskNormalOrdAdapter.getData().get(i).get(0).getSelect()){
                        taskSelect++;
                    }
                }
                customSelectBottom.setSelectText("已选 " + taskSelect + " 个");

            }
        });

    }

    private void getLeftFilterTaskList(){
        for (int i = 0; i < normalOrdTaskBean.getBodyUnExec().size(); i++) {
            if (sheetCode!=null&&normalOrdTaskBean.getBodyUnExec().get(i).getCode().equals(sheetCode)){
                customSheet.setSheetDefCode(sheetCode);
                sheetCode = "";
            }
        }
        for (int i = 0; i < taskNormalOrdAdapter.getData().size(); i++) {
            taskNormalOrdAdapter.getData().get(i).get(0).setSelect(false);
        }
        for (int i = 0; i < normalOrdTaskBean.getBodyUnExec().size(); i++) {
            if (normalOrdTaskBean.getBodyUnExec().get(i).getCode().equals(customSheet.getSheetListAdapter().getSelectedCode())){
                taskNormalOrdAdapter.setNewData(normalOrdTaskBean.getBodyUnExec().get(i).getOrders());
            }
        }
        customSelectBottom.setSelectText("已选 " + 0 + " 个");
    };


    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        TaskViewApiManager.getScaninfo(scanInfo, new CommonCallBack<ScanResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }
            @Override
            public void onSuccess(ScanResultBean bean, String type) {
                regNo = bean.getPatInfo().getRegNo();
                bedStr="";
                imgReset.setTvPatText("".equals(bean.getPatInfo().getBedCode()) ? "未分床  " + bean.getPatInfo().getName() + "  " + bean.getPatInfo().getSex() + "  " +
                        "" + bean.getPatInfo().getAge() : bean.getPatInfo().getBedCode().replace("床", "") + "床  " + bean.getPatInfo().getName() + "" +
                        "  " + bean.getPatInfo().getSex() + "  " + bean.getPatInfo().getAge());
                getNormalOrdList();
            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_task_normalord;
    }
}
