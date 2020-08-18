package com.dhcc.module.nurse.nurplan;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduSheetListBean;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanAdapter;
import com.dhcc.module.nurse.nurplan.bean.NurPlanBean;
import com.dhcc.module.nurse.nurplan.bean.StatusReasonListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.List;

/**
 * 护理计划
 * @author:gaoruishan
 * @date:202020-08-17/10:21
 * @email:grs0515@163.com
 */
public class NurPlanFragment extends BaseNurseFragment {
    private CustomSheetListView customSheet;
    private List<EduSheetListBean> eduSheetList;
    private CustomDateTimeView customDate;
    private String  status="";
    private RecyclerView rvListPlan;
    private NurPlanAdapter nurPlanAdapter;
    private BaseQuickAdapter<StatusReasonListBean, BaseViewHolder> filterAdapter;

    @Override
    protected void initViews() {
        super.initViews();
        addToolBarRightImageView(R.drawable.dhcc_filter_big_write, R.drawable.dhcc_icon_add);
        rvListPlan = RecyclerViewHelper.get(mContext, R.id.rv_list_plan);

        f(R.id.tv_bottom_plan_day).setOnClickListener(this);
        f(R.id.tv_bottom_evaluation).setOnClickListener(this);
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                episodeId = eduSheetList.get(position).getCode();
                getQuestionRecord();
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD));
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD) - CustomDateTimeView.ONE_DAY * 6);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getQuestionRecord();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getQuestionRecord();
            }
        });

        nurPlanAdapter = AdapterFactory.getNurPlanAdapter();
        rvListPlan.setAdapter(nurPlanAdapter);
        nurPlanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_item_ques_undo) {
                    ToastUtils.showShort("撤销");
                }
                if (view.getId() == R.id.tv_item_ques_copy) {
                    ToastUtils.showShort("复制");
                }
            }
        });
        nurPlanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_item_ques_undo) {
                    ToastUtils.showShort("撤销");
                }
                if (view.getId() == R.id.tv_item_ques_copy) {
                    ToastUtils.showShort("复制");
                }
            }
        });

        initFilterView();
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        //腕带
        filterRegNoSheetList();
    }

    private void filterRegNoSheetList() {
        if (eduSheetList == null) return;
        String code = "";
        for (EduSheetListBean b : eduSheetList) {
            if (b.getRegNo().equals(scanInfo)) {
                code = b.getCode();
                break;
            }
        }
        if (!TextUtils.isEmpty(code)) {
            customSheet.setSheetDefCode(code);
            episodeId = code;
            getQuestionRecord();
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        //筛选
        if (v.getId() == R.id.img_toolbar_right1) {
            setMaskShow(contentView);
        }
        //添加
        if (v.getId() == R.id.img_toolbar_right2) {

        }
        //目标达成天数
        if (v.getId() == R.id.tv_bottom_plan_day) {

        }
        //评价
        if (v.getId() == R.id.tv_bottom_evaluation) {

        }
    }

    private void initFilterView() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_task_filter_layout, null);
        contentView.findViewById(R.id.tv_filter_clear_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        contentView.findViewById(R.id.iv_finish_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
            }
        });
        contentView.findViewById(R.id.tv_filter_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaskHind();
            }
        });

        RecyclerView recTime = contentView.findViewById(R.id.rec_task_timefilter);
        filterAdapter = new BaseQuickAdapter<StatusReasonListBean, BaseViewHolder>(R.layout.item_status_fliter,null) {
            @Override
            protected void convert(BaseViewHolder helper, StatusReasonListBean item) {
                helper.setText(R.id.tv_txt, item.getText()).setChecked(R.id.tv_txt,item.isSelect());
            }
        };
        recTime.setAdapter(filterAdapter);
        recTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        filterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    filterAdapter.setSelectItem(position);
                filterAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("护理计划");
        getInWardPatList(new SimpleCallBack<PatientListBean>() {
            @Override
            public void call(PatientListBean bean, int type) {
                eduSheetList = EduSheetListBean.getSheetList(bean.getPatInfoList());
                customSheet.setDatas(eduSheetList);
                getQuestionRecord();
            }
        });
    }

    private void getQuestionRecord() {
        NurPlanApiManager.getQuestionRecord(episodeId, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), status, new CommonCallBack<NurPlanBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NurPlanBean bean, String type) {
                nurPlanAdapter.setNewData(bean.getQuestionList());
                filterAdapter.setNewData(bean.getStatusList());
            }
        });
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan;
    }
}
