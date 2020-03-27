package com.base.commlibs.http;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.ReflectUtil;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * 通用请求参数
 * @author:gaoruishan
 * @date:202020-03-05/15:01
 * @email:grs0515@163.com
 */
public class BaseRequestParams {
    //版本
    public String version = "";
    public String id = "";
    public String title = "";
    //当前登记号 OeoreId
    public String regNo = "";
    public String curOeoreId = "";
    //扫码
    public String barCode = "";
    //血袋号
    public String bloodbagId = "";
    //血制品号
    public String bloodProductId = "";
    //输血rowId
    public String bloodRowId = "";
    //血制品血型
    public String bloodGroup = "";
    //病人血型
    public String patBldGroup = "";
    //病人episodeId
    public String episodeId = "";
    //血制品描述
    public String productDesc = "";

    //输血type: 签收-R 输注-S 结束-E 终止-Z 回收-Re
    public String bloodType = "";

    //输注type: 扫码-1(无密码) 手输-2
    public String infusionType = "";
    //输注状态: 完成-E  终止-Z
    public String endType;
    //终止原因
    public String stopReasonDesc;

    //复核人和密码
    public String auditUserId = "";
    public String auditPassword = "";

    //预计结束时间
    public String distantTime = "";
    //滴速
    public String ifSpeed = "";
    //穿刺部位
    public String puncturePart = "";
    //不良反应
    public String effect = "";
    //反应症状
    public String situation = "";
    //巡视类型
    public String tourType = "";

    //体温 脉搏 舒张压 收缩压
    public String temperature;
    public String pulse;
    public String sysPressure;
    public String diaPressure;

    /**
     * 公共参数
     * @return
     */
    public static HashMap<String, String> getCommParams() {
        return getProperties(new BaseRequestParams());
    }
    /**
     * 获取变量
     * @param params
     * @return
     */
    public static HashMap<String, String> getProperties(BaseRequestParams params) {
        /**
         * 添加公共参数
         */
        params.version = AppUtils.getAppVersionCode()+"";
        params.title = SPUtils.getInstance().getString(SharedPreference.MODULE_TITLE);
        params.id = SPUtils.getInstance().getString(SharedPreference.MODULE_ID);
        HashMap<String, String> properties = ReflectUtil.getPublicFieldsToMap(params);
        CommWebService.addLocId(properties);
        CommWebService.addUserId(properties);
        return properties;
    }

    /**
     * 清楚变量
     */
    public void clearAll() {
        ReflectUtil.clearPublicFields(this);
    }
}
