package com.dhcc.module.infusion.utils;

import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.adapter.MessageInfusionAdapter;
import com.dhcc.module.infusion.message.adapter.MessageSkinAdapter;
import com.dhcc.module.infusion.setting.adapter.WorkStatisticsAdapter;
import com.dhcc.module.infusion.workarea.comm.adapter.DetailLogAdapter;
import com.dhcc.module.infusion.workarea.comm.adapter.PatInfoAdapter;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommDosingAdapter;
import com.dhcc.module.infusion.workarea.patrol.adapter.InfusionTourAdapter;
import com.dhcc.module.infusion.workarea.patrol.adapter.PatrolOrdListAdapter;

/**
 * 管理适配器
 * @author:gaoruishan
 * @date:202019-04-28/11:05
 * @email:grs0515@163.com
 */
public class AdapterFactory {
    /**
     * 巡视列表 /拔针列表
     * @return
     */
    public static PatrolOrdListAdapter getCommPatrolOrdList() {
        return new PatrolOrdListAdapter(R.layout.item_comm_patrol, null);
    }
    /**
     * 获取巡视情况
     * @return
     */
    public static InfusionTourAdapter getInfusionTour() {
        return new InfusionTourAdapter(R.layout.item_infusion_tour, null);
    }
    /**
     * 流程列表
     * @return
     */
    public static DetailLogAdapter getDetailLogAdapter() {
        return new DetailLogAdapter(R.layout.item_ord_detail_log, null);
    }
    /**
     * 配液列表 /穿刺列表 /续液列表
     * @return
     */
    public static CommDosingAdapter getCommDosingOrdList() {
        return new CommDosingAdapter(R.layout.item_comm_posing, null);
    }

    /**
     * 治疗列表
     * @return
     */
    public static PatInfoAdapter getPatInfoAdapter() {
        return new PatInfoAdapter(R.layout.item_pat_info, null);
    }

    /**
     * 消息-输液
     * @return
     */
    public static MessageInfusionAdapter getMessageInfusion() {
        return new MessageInfusionAdapter(R.layout.item_message_infusion, null);
    }

    /**
     * 消息-皮试
     * @return
     */
    public static MessageSkinAdapter getMessageSkin() {
        return new MessageSkinAdapter(R.layout.item_message_skin, null);
    }

    /**
     * 工作量统计
     * @return
     */
    public static WorkStatisticsAdapter geWorkStatistics() {
        return new WorkStatisticsAdapter(R.layout.item_work_statistics, null);
    }

}