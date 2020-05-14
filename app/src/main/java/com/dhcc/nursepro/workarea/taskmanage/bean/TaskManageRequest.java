package com.dhcc.nursepro.workarea.taskmanage.bean;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommRequest;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-05-11/15:57
 * @email:grs0515@163.com
 */
public class TaskManageRequest {

    private static class OrdRequest extends CommRequest {
        public String regNo = "";
        public String sheetCode = "";
        public String startDate = "";
        public String startTime = "";
        public String endDate = "";
        public String endTime = "";
    }

    public static class GetOrderTasks extends OrdRequest {

        public String bedStr = "";
        public String pageNo = "";
        public String excutedOrderFlag = "";

        @Override
        public HashMap<String, String> getProperties() {
            //添加公共
            addUserId().addWardId().addLocId().addGroupId().addHospitalId();
            return super.getProperties();
        }
    }

    public static class GetOrder extends OrdRequest {

        public String bedStr = "";
        public String pageNo = "1";
        public String screenParts = "";

    }

    public static class ExecOrSeeOrder extends CommRequest {
        public String speed = "";
        public String scanFlag = "";
        public String batch = "";
        public String auditUserCode = "";
        public String auditUserPass = "";
        /**
         * 医嘱id
         * 多个医嘱进行处理，接口依然调用execOrSeeOrder，第一个参数拼串，，每个医嘱的ID 用^分割
         * 11||1^12||1
         */
        public String oeoreId = "";
        /**
         * 操作
         * execStatusCode F 执行，C 撤销执行，A 接受，S 完成，R 拒绝F 执行C 撤销执行A 接受R 拒绝S 完成N 阴性 Y 阳性""撤销处理 ST 皮试计时
         */
        public String execStatusCode = "";
        public String barCode = "";

        public String userDeptId = "";

        @Override
        public HashMap<String, String> getProperties() {
            userDeptId().addUserId().addWardId();
            return super.getProperties();
        }

        public CommRequest userDeptId() {
            this.userDeptId = SPUtils.getInstance().getString(SharedPreference.LOCID);
            return this;
        }
    }


}
