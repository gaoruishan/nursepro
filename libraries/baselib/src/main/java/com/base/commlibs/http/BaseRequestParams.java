package com.base.commlibs.http;

/**
 * 通用请求参数
 * @author:gaoruishan
 * @date:202020-03-05/15:01
 * @email:grs0515@163.com
 */
public class BaseRequestParams extends CommRequest {
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

    @Override
    protected void addCommParams(CommRequest params) {
        super.addCommParams(params);
    }
}
