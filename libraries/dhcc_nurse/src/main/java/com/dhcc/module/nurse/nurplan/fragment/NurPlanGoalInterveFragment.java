package com.dhcc.module.nurse.nurplan.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.NurPlanApiManager;
import com.dhcc.module.nurse.nurplan.PlanBundleData;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanGoalAdapter;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanInterveAdapter;
import com.dhcc.module.nurse.nurplan.bean.NurPlanGoalBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanInterveBean;
import com.dhcc.module.nurse.nurplan.bean.QuestionListBean;
import com.dhcc.module.nurse.utils.DialogFactory;

import java.util.List;

/**
 * 目标措施
 * @author:gaoruishan
 * @date:202020-08-20/09:37
 * @email:grs0515@163.com
 */
public class NurPlanGoalInterveFragment extends BaseNurseFragment {

    private TextView tvGoal, tvInterve;
    private RecyclerView rvGoalList, rvInterveList;
    private List<QuestionListBean> questionList;
    private int position;
    /**
     * 参数
     */
    private String planId;//问题就诊ID
    private String questSub;//问题子ID
    private String dataArr;//目的数据
    private String intRecordIds;//措施ID串
    private String stopDate;//停止日期
    private String stopTime;
    private String cancelReason;//撤销原因


    private NurPlanGoalAdapter goalAdapter;
    private NurPlanInterveAdapter interveAdapter;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("护理计划");
        initGoalInterveData();
    }

    @Override
    protected void initViews() {
        super.initViews();
        questionList = new PlanBundleData(bundle).getQuestionList();
        String pst = new PlanBundleData(bundle).getPosition();

        addToolBarRightImageView(0, R.drawable.dhcc_icon_add);
        position = Integer.valueOf(pst);
        tvGoal = f(R.id.tv_goal, TextView.class);
        tvGoal.setOnClickListener(this);
        tvInterve = f(R.id.tv_interve, TextView.class);
        tvInterve.setOnClickListener(this);
        f(R.id.tv_bottom_save).setOnClickListener(this);
        f(R.id.tv_bottom_plan_pre).setOnClickListener(this);
        f(R.id.tv_bottom_plan_next).setOnClickListener(this);
        rvGoalList = RecyclerViewHelper.get(mContext, R.id.rv_list_goal);
        rvInterveList = RecyclerViewHelper.get(mContext, R.id.rv_list_interve);
        tvGoal.callOnClick();
        goalAdapter = AdapterFactory.getNurPlanGoalAdapter();
        rvGoalList.setAdapter(goalAdapter);
        interveAdapter = AdapterFactory.getNurPlanInterveAdapter();
        rvInterveList.setAdapter(interveAdapter);
        goalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NurPlanGoalBean.GoalListBean bean = goalAdapter.getData().get(position);
                bean.setSelect(!bean.isSelect());
                goalAdapter.notifyItemChanged(position);
            }
        });
        interveAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NurPlanInterveBean.InterventionListBean bean = interveAdapter.getData().get(position);
                intRecordIds = bean.getRowID();
                //作废
                if (view.getId() == R.id.tv_item_ques_undo) {
                    DialogFactory.showCancelInterveDialog(mContext, new SimpleCallBack<String>() {
                        @Override
                        public void call(String result, int type) {
                            Log.e(TAG, "(NurPlanGoalInterveFragment.java:101) " + result);
                            if (!TextUtils.isEmpty(result)) {
                                cancelReason = result;
                                cancelInterventions();
                            }
                        }
                    });
                    return;
                }
                //停止
                if (view.getId() == R.id.tv_item_ques_stop) {
                    DialogFactory.showStopInterveDialog(mContext, bean.getCurDateTime(), new SimpleCallBack<String>() {
                        @Override
                        public void call(String result, int type) {
                            Log.e(TAG, "(NurPlanGoalInterveFragment.java:101) " + result);
                            if (!TextUtils.isEmpty(result)) {
                                stopDate = result.split(" ")[0];
                                stopTime = result.split(" ")[1];
                                stopInterventions();
                            }
                        }
                    });
                    return;
                }
                //勾选
                bean.setSelect(!bean.isSelect());
                interveAdapter.notifyItemChanged(position);
            }
        });
    }

    private void cancelInterventions() {
        NurPlanApiManager.cancelInterventions(intRecordIds, cancelReason, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getInterventionByQestId();
            }
        });
    }

    private void stopInterventions() {
        NurPlanApiManager.stopInterventions(intRecordIds, stopDate, stopTime, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getInterventionByQestId();
            }
        });
    }

    private void initGoalInterveData() {
        if (questionList != null) {
            QuestionListBean bean = questionList.get(position);
            planId = bean.getRecordId().split("\\|\\|")[0];
            questSub = bean.getRecordId().split("\\|\\|")[1];
            f(R.id.tv_title, TextView.class).setText("" + bean.getQueName());
            showLoadingTip(BaseActivity.LoadingType.FULL);
            getGoalByQestId();
            getInterventionByQestId();
        }
    }

    private void getGoalByQestId() {
        NurPlanApiManager.getGoalByQestId(planId, questSub, new CommonCallBack<NurPlanGoalBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(NurPlanGoalBean bean, String type) {
                goalAdapter.setNewData(bean.getGoalList());
            }
        });
    }

    private void getInterventionByQestId() {
        NurPlanApiManager.getInterventionByQestId(planId, questSub, new CommonCallBack<NurPlanInterveBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurPlanInterveBean bean, String type) {
                hideLoadingTip();
                interveAdapter.setNewData(bean.getInterventionList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //目标/措施
        if (v.getId() == R.id.tv_goal || v.getId() == R.id.tv_interve) {
            boolean color = v.getId() == R.id.tv_goal;
            rvGoalList.setVisibility(color ? View.VISIBLE : View.GONE);
            rvInterveList.setVisibility(color ? View.GONE : View.VISIBLE);
            tvGoal.setTextColor(ContextCompat.getColor(mContext, color ? R.color.blue : R.color.color_4a));
            tvInterve.setTextColor(ContextCompat.getColor(mContext, !color ? R.color.blue : R.color.color_4a));
        }
        if (v.getId() == R.id.tv_bottom_plan_pre) {
            if (position <= 0) {
                ToastUtils.showShort("已经是第一个问题!");
                return;
            }
            position--;
            initGoalInterveData();
        }
        if (v.getId() == R.id.tv_bottom_plan_next) {
            if (questionList == null) return;
            if (position >= questionList.size() - 1) {
                ToastUtils.showShort("已经是最后一个问题!");
                return;
            }
            position++;
            initGoalInterveData();
        }
        if (v.getId() == R.id.tv_bottom_plan_all) {
            boolean showGoal = rvGoalList.getVisibility() == View.VISIBLE;

        }
        //保存
        if (v.getId() == R.id.tv_bottom_save) {
            boolean showGoal = rvGoalList.getVisibility() == View.VISIBLE;
            if (showGoal) {
                dataArr = NurPlanGoalBean.getSelectBean(goalAdapter.getData());
                if (dataArr == null) {
                    ToastUtils.showShort("请选择护理目标");
                    return;
                }
                saveGoalIntervRecord(NurPlanApiManager.saveGoalRecord);
            } else {
                dataArr = NurPlanInterveBean.getSelectBean(interveAdapter.getData());
                if (dataArr == null) {
                    ToastUtils.showShort("请选择护理措施");
                    return;
                }
                saveGoalIntervRecord(NurPlanApiManager.saveIntervRecord);
            }
        }
        //添加
        if (v.getId() == R.id.img_toolbar_right2) {
            String title = f(R.id.tv_title, TextView.class).getText().toString();
            PlanBundleData instance = new PlanBundleData()
                    .setTitle(title)
                    .setQuestSub(questSub)
                    .setPlanId(planId);
            startFragment(NurPlanAddIntervFragment.class, instance.build());
        }
    }

    private void saveGoalIntervRecord(String method) {
        NurPlanApiManager.saveGoalIntervRecord(method, planId, questSub, dataArr, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                getGoalByQestId();
                getInterventionByQestId();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_plan_goal_interve;
    }
}
