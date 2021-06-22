package com.dhcc.nursepro.workarea.labout.bean;

import com.base.commlibs.http.CommResult;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.SheetPatListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/6/22/15:33
 * @email:grs0515@163.com
 */
public class LabUnOutListBean extends CommResult {

    /**
     * labList : [{"bedCode":"02","name":"闭环演示(勿动)","patOrds":[[{"orderInfo":{"EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"178||262||1","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","admLoc":"内分泌科","age":"48岁","arcimDesc":"血常规","bedCode":"02","cancelReason":"","containerInfo":{"ID":"6","barCodeNumber":"0","code":"CO3","color":"#8e7cc3","desc":"紫色管","imageUrl":"../image/labcontainerimage/CO3.PNG","notes":"","notice":"","requireNotes":"false","volumn":"2"},"createDateTime":"2021-06-22 15:07:41","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","disposeStatdesc":"Exec","doseQtyUnit":"1 ","examInfo":{},"exeStColor":"#7B7B7B","exeStatus":"执","execCtcpDesc":"护士01","execDateTime":"06-22 15:10","execID":"178||262||1","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","icuFlag":"1","labNo":"0000002680","loopInfo":[{"loopDate":"2021-06-22","loopTime":"15:07:41","workType":"开立","workUser":"医生01"},{"loopDate":"2021-06-22","loopTime":"15:10:00","workType":"处理","workUser":"护士01"}],"medCareNo":"100063","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检验","ordDep":"内分泌科","ordID":"178||262","ordStatDesc":"核实","ordTyp":"L","patIdentity":"全自费","patName":"闭环演示(勿动)","placerNo":"","preparedFlag":"","printFlag":"P","reclocDesc":"临检室","regNo":"0000000134 ","seeOrderUserDateTime":"2021-06-22 15:10:00","seeOrderUserName":"护士01","seqNo":"178||262","specCollDateTime":"","specDesc":"血清","sttDate":"06-22","sttDateTime":"2021-06-22 15:07","sttTime":"15:07","tubeColor":"Purple","tubeColorCode":"#8e7cc3","tubeColorDesc":"紫色管","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"178||264||1","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","admLoc":"内分泌科","age":"48岁","arcimDesc":"肝功能","bedCode":"02","cancelReason":"","containerInfo":{"ID":"6","barCodeNumber":"1","code":"CO3","color":"#8e7cc3","desc":"紫色管","imageUrl":"../image/labcontainerimage/CO3.PNG","notes":"","notice":"","requireNotes":"false","volumn":"2"},"createDateTime":"2021-06-22 15:07:41","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","disposeStatdesc":"Exec","doseQtyUnit":"1 ","examInfo":{},"exeStColor":"#7B7B7B","exeStatus":"执","execCtcpDesc":"护士01","execDateTime":"06-22 15:10","execID":"178||264||1","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","icuFlag":"1","labNo":"0000002682","loopInfo":[{"loopDate":"2021-06-22","loopTime":"15:07:41","workType":"开立","workUser":"医生01"},{"loopDate":"2021-06-22","loopTime":"15:10:00","workType":"处理","workUser":"护士01"}],"medCareNo":"100063","no":"3","notes":"  测试","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检验","ordDep":"内分泌科","ordID":"178||264","ordStatDesc":"核实","ordTyp":"L","patIdentity":"全自费","patName":"闭环演示(勿动)","placerNo":"","preparedFlag":"","printFlag":"P","reclocDesc":"生化室","regNo":"0000000134 ","seeOrderUserDateTime":"2021-06-22 15:10:00","seeOrderUserName":"护士01","seqNo":"178||264","specCollDateTime":"","specDesc":"血清","sttDate":"06-22","sttDateTime":"2021-06-22 15:07","sttTime":"15:07","tubeColor":"Purple","tubeColorCode":"#8e7cc3","tubeColorDesc":"紫色管","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]},{"bedCode":"03","name":"张三","patOrds":[[{"orderInfo":{"EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"14||333||1","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","admLoc":"内分泌科","age":"27岁","arcimDesc":"血常规","bedCode":"03","cancelReason":"","containerInfo":{"ID":"6","barCodeNumber":"0","code":"CO3","color":"#8e7cc3","desc":"紫色管","imageUrl":"../image/labcontainerimage/CO3.PNG","notes":"","notice":"","requireNotes":"false","volumn":"2"},"createDateTime":"2021-06-22 15:08:17","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","disposeStatdesc":"Exec","doseQtyUnit":"1 ","examInfo":{},"exeStColor":"#7B7B7B","exeStatus":"执","execCtcpDesc":"护士01","execDateTime":"06-22 15:10","execID":"14||333||1","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","icuFlag":"1","labNo":"0000002684","loopInfo":[{"loopDate":"2021-06-22","loopTime":"15:08:17","workType":"开立","workUser":"医生01"},{"loopDate":"2021-06-22","loopTime":"15:10:00","workType":"处理","workUser":"护士01"}],"medCareNo":"100006","no":"2","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检验","ordDep":"内分泌科","ordID":"14||333","ordStatDesc":"核实","ordTyp":"L","patIdentity":"全自费","patName":"张三","placerNo":"","preparedFlag":"","printFlag":"P","reclocDesc":"临检室","regNo":"0000000014 ","seeOrderUserDateTime":"2021-06-22 15:10:00","seeOrderUserName":"护士01","seqNo":"14||333","specCollDateTime":"","specDesc":"血清","sttDate":"06-22","sttDateTime":"2021-06-22 15:08","sttTime":"15:08","tubeColor":"Purple","tubeColorCode":"#8e7cc3","tubeColorDesc":"紫色管","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]}]
     * labNum : 3
     * msg : 成功
     * msgcode : 999999
     * status : 0
     * taskSheetList : [{"sheetCode":"DefaultSee","sheetDesc":"需处理医嘱","sheetOrdNum":"8","sheetPatList":[{"bedCode":"02","patName":"闭环演示(勿动)","patRegNo":"0000000134","patSex":"女","sheetCode":"DefaultSee","sheetDesc":"需处理医嘱","sheetPatOrdNum":"4"},{"bedCode":"03","patName":"张三","patRegNo":"0000000014","patSex":"女","sheetCode":"DefaultSee","sheetDesc":"需处理医嘱","sheetPatOrdNum":"4"}]},{"sheetCode":"JYD","sheetDesc":"检验单","sheetOrdNum":"2","sheetPatList":[{"bedCode":"02","patName":"闭环演示(勿动)","patRegNo":"0000000134","patSex":"女","sheetCode":"JYD","sheetDesc":"检验单","sheetPatOrdNum":"1"},{"bedCode":"03","patName":"张三","patRegNo":"0000000014","patSex":"女","sheetCode":"JYD","sheetDesc":"检验单","sheetPatOrdNum":"1"}]},{"sheetCode":"Default","sheetDesc":"全部医嘱","sheetOrdNum":"4","sheetPatList":[{"bedCode":"02","patName":"闭环演示(勿动)","patRegNo":"0000000134","patSex":"女","sheetCode":"Default","sheetDesc":"全部医嘱","sheetPatOrdNum":"2"},{"bedCode":"03","patName":"张三","patRegNo":"0000000014","patSex":"女","sheetCode":"Default","sheetDesc":"全部医嘱","sheetPatOrdNum":"2"}]}]
     */

    private String labNum;
    /**
     * bedCode : 02
     * name : 闭环演示(勿动)
     * patOrds : [[{"orderInfo":{"EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"178||262||1","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","admLoc":"内分泌科","age":"48岁","arcimDesc":"血常规","bedCode":"02","cancelReason":"","containerInfo":{"ID":"6","barCodeNumber":"0","code":"CO3","color":"#8e7cc3","desc":"紫色管","imageUrl":"../image/labcontainerimage/CO3.PNG","notes":"","notice":"","requireNotes":"false","volumn":"2"},"createDateTime":"2021-06-22 15:07:41","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","disposeStatdesc":"Exec","doseQtyUnit":"1 ","examInfo":{},"exeStColor":"#7B7B7B","exeStatus":"执","execCtcpDesc":"护士01","execDateTime":"06-22 15:10","execID":"178||262||1","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","icuFlag":"1","labNo":"0000002680","loopInfo":[{"loopDate":"2021-06-22","loopTime":"15:07:41","workType":"开立","workUser":"医生01"},{"loopDate":"2021-06-22","loopTime":"15:10:00","workType":"处理","workUser":"护士01"}],"medCareNo":"100063","no":"1","notes":"","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检验","ordDep":"内分泌科","ordID":"178||262","ordStatDesc":"核实","ordTyp":"L","patIdentity":"全自费","patName":"闭环演示(勿动)","placerNo":"","preparedFlag":"","printFlag":"P","reclocDesc":"临检室","regNo":"0000000134 ","seeOrderUserDateTime":"2021-06-22 15:10:00","seeOrderUserName":"护士01","seqNo":"178||262","specCollDateTime":"","specDesc":"血清","sttDate":"06-22","sttDateTime":"2021-06-22 15:07","sttTime":"15:07","tubeColor":"Purple","tubeColorCode":"#8e7cc3","tubeColorDesc":"紫色管","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}],[{"orderInfo":{"EncryptLevel":"","FuHeDate":"","FuHeTime":"","FuHeUser":"","ID":"178||264||1","PatLevel":"","PeiYeDate":"","PeiYeTime":"","PeiYeUser":"","admLoc":"内分泌科","age":"48岁","arcimDesc":"肝功能","bedCode":"02","cancelReason":"","containerInfo":{"ID":"6","barCodeNumber":"1","code":"CO3","color":"#8e7cc3","desc":"紫色管","imageUrl":"../image/labcontainerimage/CO3.PNG","notes":"","notice":"","requireNotes":"false","volumn":"2"},"createDateTime":"2021-06-22 15:07:41","ctcpDesc":"医生01","disposeStatCode":"已执行^#b4a89a","disposeStatdesc":"Exec","doseQtyUnit":"1 ","examInfo":{},"exeStColor":"#7B7B7B","exeStatus":"执","execCtcpDesc":"护士01","execDateTime":"06-22 15:10","execID":"178||264||1","execXDateTime":"","execXUserDesc":"","filteFlagExtend":"","icuFlag":"1","labNo":"0000002682","loopInfo":[{"loopDate":"2021-06-22","loopTime":"15:07:41","workType":"开立","workUser":"医生01"},{"loopDate":"2021-06-22","loopTime":"15:10:00","workType":"处理","workUser":"护士01"}],"medCareNo":"100063","no":"3","notes":"  测试","notifyClinician":"N","oecprDesc":"临时医嘱","orcatDesc":"检验","ordDep":"内分泌科","ordID":"178||264","ordStatDesc":"核实","ordTyp":"L","patIdentity":"全自费","patName":"闭环演示(勿动)","placerNo":"","preparedFlag":"","printFlag":"P","reclocDesc":"生化室","regNo":"0000000134 ","seeOrderUserDateTime":"2021-06-22 15:10:00","seeOrderUserName":"护士01","seqNo":"178||264","specCollDateTime":"","specDesc":"血清","sttDate":"06-22","sttDateTime":"2021-06-22 15:07","sttTime":"15:07","tubeColor":"Purple","tubeColorCode":"#8e7cc3","tubeColorDesc":"紫色管","verifyFlag":"","xCtcpDesc":"","xDateTime":""},"type":"main"}]]
     */
    private List<OrderExecuteBean.OrdersBean> labList;
    /**
     * sheetCode : DefaultSee
     * sheetDesc : 需处理医嘱
     * sheetOrdNum : 8
     * sheetPatList : [{"bedCode":"02","patName":"闭环演示(勿动)","patRegNo":"0000000134","patSex":"女","sheetCode":"DefaultSee","sheetDesc":"需处理医嘱","sheetPatOrdNum":"4"},{"bedCode":"03","patName":"张三","patRegNo":"0000000014","patSex":"女","sheetCode":"DefaultSee","sheetDesc":"需处理医嘱","sheetPatOrdNum":"4"}]
     */

    private List<TaskSheetListBean> taskSheetList;

    public String getLabNum() {
        return labNum == null ? "" : labNum;
    }

    public void setLabNum(String labNum) {
        this.labNum = labNum;
    }

    public List<OrderExecuteBean.OrdersBean> getLabList() {
        return labList;
    }

    public void setLabList(List<OrderExecuteBean.OrdersBean> labList) {
        this.labList = labList;
    }

    public List<TaskSheetListBean> getTaskSheetList() {
        return taskSheetList;
    }

    public void setTaskSheetList(List<TaskSheetListBean> taskSheetList) {
        this.taskSheetList = taskSheetList;
    }


    public static class TaskSheetListBean {
        private String sheetCode;
        private String sheetDesc;
        private String sheetOrdNum;
        /**
         * bedCode : 02
         * patName : 闭环演示(勿动)
         * patRegNo : 0000000134
         * patSex : 女
         * sheetCode : DefaultSee
         * sheetDesc : 需处理医嘱
         * sheetPatOrdNum : 4
         */

        private List<SheetPatListBean> sheetPatList;

        public String getSheetCode() {
            return sheetCode;
        }

        public void setSheetCode(String sheetCode) {
            this.sheetCode = sheetCode;
        }

        public String getSheetDesc() {
            return sheetDesc;
        }

        public void setSheetDesc(String sheetDesc) {
            this.sheetDesc = sheetDesc;
        }

        public String getSheetOrdNum() {
            return sheetOrdNum;
        }

        public void setSheetOrdNum(String sheetOrdNum) {
            this.sheetOrdNum = sheetOrdNum;
        }

        public List<SheetPatListBean> getSheetPatList() {
            return sheetPatList;
        }

        public void setSheetPatList(List<SheetPatListBean> sheetPatList) {
            this.sheetPatList = sheetPatList;
        }

    }
}
