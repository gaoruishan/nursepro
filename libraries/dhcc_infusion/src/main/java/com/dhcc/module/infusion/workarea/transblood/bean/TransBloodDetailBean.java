package com.dhcc.module.infusion.workarea.transblood.bean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-03-09/11:06
 * @email:grs0515@163.com
 */
public class TransBloodDetailBean extends CommTransBloodBean {


    /**
     * bloodInfo : {"agreeStatus":"同","bedCode":"01","bldTyp":"AB型","bloodBagNo":"8673777362","bloodPref":"6","bloodProducDesc":"新鲜冰冻血浆 400cc","bloodProductNo":"6","bloodRowId":"6","endType":"Z","episodeId":"94","operateFlag":"","patBldTyp":"AB型","patName":"王伟测试","patWard":"5","reciveDate":"2018-09-20","reciveTime":"09:03:53","reciveUser":"护士01","sex":"男","status":"输血结束","stopReason":"其他","tStartReason":"","transEdDate":"2018-09-20","transEdTime":"09:04:37","transEdUser":"innurse","transEndFlag":"","transFirstUser":"护士01","transRecFlag":"","transRecycleDate":"","transRecycleTime":"","transRecycleUser":"","transSecondUser":"护士02","transStFlag":"","transStartDate":"2018-09-20","transStartTime":"09:04:19"}
     * patrolRecord : [{"date":"2018-09-29","effect":"无","id":"16","situation":"","speed":"20","time":"10:50","user":"护士01"},{"date":"2018-09-29","effect":"有","id":"17","situation":"qertyy","speed":"21","time":"10:51","user":"护士01"}]
     */

    private BloodInfoBean bloodInfo;
    private List<PatrolRecordBean> patrolRecord;

    public BloodInfoBean getBloodInfo() {
        return bloodInfo;
    }

    public void setBloodInfo(BloodInfoBean bloodInfo) {
        this.bloodInfo = bloodInfo;
    }

    public List<PatrolRecordBean> getPatrolRecord() {
        return patrolRecord;
    }

    public void setPatrolRecord(List<PatrolRecordBean> patrolRecord) {
        this.patrolRecord = patrolRecord;
    }

}
