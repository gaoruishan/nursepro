package com.dhcc.module.nurse.nurplan;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.ActivityUtils;
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
import com.dhcc.module.nurse.nurplan.bean.QuestionListBean;
import com.dhcc.module.nurse.nurplan.bean.StatusReasonListBean;
import com.dhcc.module.nurse.nurplan.fragment.NurPlanAddFragment;
import com.dhcc.module.nurse.nurplan.fragment.NurPlanGoalInterveFragment;
import com.dhcc.module.nurse.utils.DialogFactory;
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
    private static final int TYPE_INT_0 = 000;
    private static final int TYPE_INT_1 = 111;
    private static final int TYPE_INT_2 = 222;
    private CustomSheetListView customSheet;
    private List<EduSheetListBean> eduSheetList;
    private CustomDateTimeView customDate;
    /**
     * 请求参数变量
     */
    private String status = "";// 状态
    private String recordId = "";//记录ID串
    private String questionSub;//问题子ID
    private String statusId;


    private RecyclerView rvListPlan;
    private NurPlanAdapter nurPlanAdapter;
    private BaseQuickAdapter<StatusReasonListBean, BaseViewHolder> filterAdapter;
    private NurPlanBean mBean;
    private List<StatusReasonListBean> filterData;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //筛选
        if (v.getId() == R.id.img_toolbar_right1) {
            setMaskShow(contentView, 0.5f);
            if (filterAdapter != null && filterData != null) {
                filterAdapter.setNewData(filterData);
            }
        }
        //添加
        if (v.getId() == R.id.img_toolbar_right2) {
            PlanBundleData instance = new PlanBundleData()
                    .setEpisodeId(episodeId);
            startFragment(NurPlanAddFragment.class, instance.build());
        }
        //目标达成天数
        if (v.getId() == R.id.tv_bottom_plan_day) {

        }
        //评价
        if (v.getId() == R.id.tv_bottom_evaluation) {
            QuestionListBean bean= NurPlanBean.getSaveQuestionComments(nurPlanAdapter.getData());
            if (bean == null) {
                CommDialog.showCommDialog(ActivityUtils.getTopActivity(), "请选择评价的护理问题!", null, 0, null, true);
                return;
            }
            DialogFactory.showPlanDialog(mContext, "问题评价结果:", "取消", "确定", 0, mBean.getResultList(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statusId = (String) v.getTag();//状态ID
                    questionSub = bean.getRecordId().split("\\|\\|")[1];
                    //此护理问题评价为“已解决”提交后，其问题和目标评价结果不可修改!
                    saveQuestionComments();
                }
            }, new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    StatusReasonListBean b = (StatusReasonListBean) adapter.getData().get(position);
                    if ("已解决".equals(b.getText())) {
                        ToastUtils.showLong("评价为“已解决”提交后，其问题和目标评价结果不可修改!");
                    }
                }
            });

        }
    }

    private void saveQuestionComments() {
        NurPlanApiManager.saveQuestionComments(episodeId, questionSub, statusId, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getQuestionRecord();
            }
        });
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

    private void getQuestionRecord() {
        NurPlanApiManager.getQuestionRecord(episodeId, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), status, new CommonCallBack<NurPlanBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NurPlanBean bean, String type) {
                mBean = bean;
                nurPlanAdapter.setNewData(bean.getQuestionList());
                filterAdapter.setNewData(bean.getStatusList());
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan;
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
        //获取频次
        NurPlanApiManager.getInterventionFreq();
    }

    @Override
    public void onResume() {
        super.onResume();
        //方便刷新
        if (episodeId != null) {
            getQuestionRecord();
        }
    }

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
        nurPlanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                recordId = nurPlanAdapter.getItem(position).getRecordId();
                //撤销
                if (view.getId() == R.id.tv_item_ques_undo) {
                    cancelStopQuestion("cancelQuestion", "撤销", mBean.getRevokeReasonList());
                }
                //停止
                if (view.getId() == R.id.tv_item_ques_stop) {
                    cancelStopQuestion("stopQuestion", "停止", mBean.getStopReasonList());
                }
                //复制
                if (view.getId() == R.id.tv_item_ques_copy) {
                    ToastUtils.showShort("复制");
                }
                //勾选-单选
                if (view.getId() == R.id.ll_select) {
                    for (int i = 0; i < nurPlanAdapter.getData().size(); i++) {
                        nurPlanAdapter.getData().get(i).setSelect(i==position);
                    }
                    nurPlanAdapter.notifyDataSetChanged();
                }
                //跳转
                if (view.getId() == R.id.ll_content) {
                    PlanBundleData bundle = new PlanBundleData()
                            .setQuestionList(mBean.getQuestionList())
                            .setPosition(position + "");
                    startFragment(NurPlanGoalInterveFragment.class, bundle.build());
                }

            }
        });
        //初始化筛选
        initFilterView();
    }

    private void cancelStopQuestion(String method, String txt, List<StatusReasonListBean> revokeReasonList) {
        DialogFactory.showPlanDialog(mContext, "确定'" + txt + "'护理问题?", "取消", "确定", 0, revokeReasonList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = (String) v.getTag();//撤销原因ID
                NurPlanApiManager.cancelStopQuestion(method, episodeId, reason, recordId, new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        onFailThings();
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        //刷新
                        getQuestionRecord();
                        onSuccessThings(bean);
                    }
                });
            }
        },null);
    }

    private void initFilterView() {
        contentView = getPopWindowView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyFilterAdapter(TYPE_INT_0, false);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusReasonListBean bean = notifyFilterAdapter(TYPE_INT_2, null);
                status = "";
                if (bean != null) {
                    status = bean.getValue();
                }
                getQuestionRecord();
            }
        });

        RecyclerView recTime = contentView.findViewById(R.id.rec_task_timefilter);
        filterAdapter = new BaseQuickAdapter<StatusReasonListBean, BaseViewHolder>(R.layout.item_status_fliter, null) {
            @Override
            protected void convert(BaseViewHolder helper, StatusReasonListBean item) {
                helper.setText(R.id.tv_txt, item.getText());
                helper.getView(R.id.tv_txt).setSelected(item.isSelect());
            }
        };
        recTime.setAdapter(filterAdapter);
        recTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        filterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                notifyFilterAdapter(TYPE_INT_1, position);
            }
        });
    }

    private StatusReasonListBean notifyFilterAdapter(int type, Object obj) {
        filterData = filterAdapter.getData();
        for (int i = 0; i < filterData.size(); i++) {
            StatusReasonListBean b = filterData.get(i);
            if (type == TYPE_INT_0) {
                b.setSelect((boolean) obj);
            }
            if (type == TYPE_INT_1) {
                b.setSelect(i == (int) obj);
            }
            if (type == TYPE_INT_2) {
                if (b.isSelect()) return b;
            }
        }
        filterAdapter.notifyDataSetChanged();
        return null;
    }


}
