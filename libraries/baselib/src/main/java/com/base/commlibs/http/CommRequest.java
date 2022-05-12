package com.base.commlibs.http;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.ReflectUtil;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202020-05-11/16:00
 * @email:grs0515@163.com
 */
public abstract class CommRequest {
    //版本
    public String version = "";
    public String id = "";
    public String title = "";

    public String locId = "";
    public String userId = "";
    public String wardId = "";
    public String hospitalId = "";
    public String groupId = "";

    /**
     * 获取变量
     * @return
     */
    public static HashMap<String, String> getProperties(CommRequest params) {
        return ReflectUtil.getPublicFieldsToMap(params);
    }

    public static HashMap<String, String> getCommProperties(CommRequest params) {
        params.addLocId().addUserId();
        return ReflectUtil.getPublicFieldsToMap(params);
    }
    /**
     * 添加UserId,LocId 等
     * @return
     */
    public  HashMap<String, String> getProperties(){
        return ReflectUtil.getPublicFieldsToMap(this);
    };

    protected void addCommParams(CommRequest params) {
        //添加公共参数
        //params.version = AppUtils.getAppVersionCode()+"";
    }

    /**
     * 清楚变量
     */
    public void clearAll() {
        ReflectUtil.clearPublicFields(this);
    }

    /**
     * 添加科室ID
     * @return
     */
    public CommRequest addLocId() {
        this.locId = SPUtils.getInstance().getString(SharedPreference.LOCID);
        return this;
    }

    /**
     * 添加用户ID
     * @return
     */
    public CommRequest addUserId() {
        this.userId = SPUtils.getInstance().getString(SharedPreference.USERID);
        return this;
    }

    /**
     * 添加病房ID
     * @return
     */
    public CommRequest addWardId() {
        this.wardId = SPUtils.getInstance().getString(SharedPreference.WARDID);
        return this;
    }

    /**
     * 添加医院ID
     * @return
     */
    public CommRequest addHospitalId() {
        this.hospitalId = SPUtils.getInstance().getString(SharedPreference.HOSPITALROWID);
        return this;
    }

    /**
     * 添加组ID
     * @return
     */
    public CommRequest addGroupId() {
        this.groupId = SPUtils.getInstance().getString(SharedPreference.GROUPID);
        return this;
    }
}
