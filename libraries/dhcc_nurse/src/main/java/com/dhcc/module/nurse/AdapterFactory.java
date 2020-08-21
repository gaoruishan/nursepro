package com.dhcc.module.nurse;

import com.dhcc.module.nurse.bloodsugar.adapter.BloodSugarPatAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduAddAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduDrugAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduEndAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduItemAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduNeedAdapter;
import com.dhcc.module.nurse.education.adapter.TaskOverviewAdapter;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanAdapter;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanAddListAdapter;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanGoalAdapter;
import com.dhcc.module.nurse.nurplan.adapter.NurPlanInterveAdapter;
import com.dhcc.module.nurse.task.adapter.StatusFilterAdapter;
import com.dhcc.module.nurse.task.adapter.TaskListAdapter;
import com.dhcc.module.nurse.task.adapter.TaskNormalOrdAdapter;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdAdapter;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdRecordAdapter;
import com.dhcc.module.nurse.task.adapter.TaskTempAdapter;
import com.dhcc.module.nurse.task.adapter.TimeFilterAdapter;

/**
 * 管理适配器
 * @author:gaoruishan
 * @date:202019-04-28/11:05
 * @email:grs0515@163.com
 */
public class AdapterFactory {
    /**
     * 健康宣教(已宣教)
     * @return
     */
    public static HealthEduEndAdapter getHealthEducationAdapter() {
        return new HealthEduEndAdapter(R.layout.item_health_education, null);
    }
    /**
     * 健康宣教(需宣教)
     * @return
     */
    public static HealthEduNeedAdapter getHealthEducationNeedAdapter() {
        return new HealthEduNeedAdapter(R.layout.item_health_education, null);
    }

    /**
     * 添加宣教
     * @return
     */
    public static HealthEduAddAdapter getHealthEduAddAdapter() {
        return new HealthEduAddAdapter(R.layout.item_health_education_add, null);
    }

    /**
     * 宣教执行-Item配置
     * @return
     */
    public static HealthEduItemAdapter getHealthEduItemAdapter() {
        return new HealthEduItemAdapter(null);
    }

    /**
     * 宣教-用药情况
     * @return
     */
    public static HealthEduDrugAdapter getHealthEduDrugAdapter() {
        return new HealthEduDrugAdapter(R.layout.item_health_education_drug,null);
    }
    /**
     * 任务总览
     * @return
     */
    public static TaskOverviewAdapter getTaskOverviewAdapter() {
        return new TaskOverviewAdapter(R.layout.item_health_education_drug,null);
    }
    /**
     * 任务总览-医嘱查询
     * @return
     */
    public static TaskListAdapter getTaskListAdapter() {
        return new TaskListAdapter(R.layout.item_task_view,null);
    }
    /**
     * 任务总览-常规治疗
     * @return
     */
    public static TaskNormalOrdAdapter getTaskNormalOrdAdapter() {
        return new TaskNormalOrdAdapter(R.layout.item_task_normalord,null);
    }
    /**
     * 任务总览-体征查询
     * @return
     */
    public static TaskTempAdapter getTaskTempAdapter() {
        return new TaskTempAdapter(R.layout.item_task_temp,null);
    }
    /**
     * 任务总览-护嘱查询
     * @return
     */
    public static TaskNurOrdAdapter getTaskNurOrdAdapter() {
        return new TaskNurOrdAdapter(R.layout.item_task_nurord,null);
    }
    /**
     * 任务总览-护嘱录入
     * @return
     */
    public static TaskNurOrdRecordAdapter getTaskNurOrdRecordAdapter() {
        return new TaskNurOrdRecordAdapter(R.layout.item_task_nurordrecord,null);
    }
    /**
     * 任务总览-时间筛选
     * @return
     */
    public static TimeFilterAdapter getTimeFilterAdapter() {
        return new TimeFilterAdapter(R.layout.item_task_timefilter,null);
    }
    /**
     * 任务总览-护嘱任务状态筛选
     * @return
     */
    public static StatusFilterAdapter getStatusFilterAdapter() {
        return new StatusFilterAdapter(R.layout.item_task_statusfilter,null);
    }

    /**
     * 护理计划-问题列表
     * @return
     */
    public static NurPlanAdapter getNurPlanAdapter() {
        return new NurPlanAdapter(R.layout.item_nur_plan_question,null);
    }

    /**
     * 护理计划-新增问题列表
     * @return
     */
    public static NurPlanAddListAdapter getNurPlanAddListAdapter() {
        return new NurPlanAddListAdapter(R.layout.item_nur_plan_question_add,null);
    }
    /**
     * 护理计划-目标
     * @return
     */
    public static NurPlanGoalAdapter getNurPlanGoalAdapter() {
        return new NurPlanGoalAdapter(R.layout.item_nur_plan_goal,null);
    }
    /**
     * 护理计划-措施
     * @return
     */
    public static NurPlanInterveAdapter getNurPlanInterveAdapter() {
        return new NurPlanInterveAdapter(R.layout.item_nur_plan_goal,null);
    }
    /**
     * 护理计划-血糖采集患者列表
     * @return
     */
    public static BloodSugarPatAdapter getBloodSugarPatAdapter() {
        return new BloodSugarPatAdapter(R.layout.item_bloodsugar_patient,null);
    }
}