package com.base.commlibs.bean;

import com.base.commlibs.http.CommResult;

/**
 * 配置相关类
 * @author:gaoruishan
 * @date:202019-12-04/10:01
 * @email:grs0515@163.com
 */
public class ConfigBean extends CommResult {

    //消息皮试双签配置: 默认是"" 不开启; "1" 开启
    private String msgSkinFlag;
    //输出json: 默认是"" 不开启; "1" 开启
    private String logFlag;
    //开启后台消息通知: 默认是"" 不开启; "1" 开启
    private String msgNoticeFlag;
    //开启全局View: 默认是"" 不开启; "1" 开启
    private String globalViewFlag;
    //输液背景状态: 默认是"" 不开启; "1" 开启
    private String ordStateFlag;
    //采血复核: 默认是"" 不开启; "1" 开启
    private String bloodCheckFlag;
    //皮试日期差值: 默认是"" 当天; "7" 表示7天
    private String skinDateOffset;
    //更新APP的URL
    private String updateUrl;
    //是否本地json测试
    private String localTestFlag;

    public String getLocalTestFlag() {
        return localTestFlag == null ? "" : localTestFlag;
    }

    public void setLocalTestFlag(String localTestFlag) {
        this.localTestFlag = localTestFlag;
    }

    public String getUpdateUrl() {
        return updateUrl == null ? "" : updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getSkinDateOffset() {
        return skinDateOffset == null ? "" : skinDateOffset;
    }

    public void setSkinDateOffset(String skinDateOffset) {
        this.skinDateOffset = skinDateOffset;
    }

    public String getBloodCheckFlag() {
        return bloodCheckFlag == null ? "" : bloodCheckFlag;
    }

    public void setBloodCheckFlag(String bloodCheckFlag) {
        this.bloodCheckFlag = bloodCheckFlag;
    }

    public String getOrdStateFlag() {
        return ordStateFlag == null ? "" : ordStateFlag;
    }

    public void setOrdStateFlag(String ordStateFlag) {
        this.ordStateFlag = ordStateFlag;
    }

    public String getGlobalViewFlag() {
        return globalViewFlag == null ? "" : globalViewFlag;
    }

    public void setGlobalViewFlag(String globalViewFlag) {
        this.globalViewFlag = globalViewFlag;
    }

    public String getMsgNoticeFlag() {
        return msgNoticeFlag == null ? "" : msgNoticeFlag;
    }

    public void setMsgNoticeFlag(String msgNoticeFlag) {
        this.msgNoticeFlag = msgNoticeFlag;
    }

    public String getLogFlag() {
        return logFlag == null ? "" : logFlag;
    }

    public void setLogFlag(String logFlag) {
        this.logFlag = logFlag;
    }

    public String getMsgSkinFlag() {
        return msgSkinFlag == null ? "" : msgSkinFlag;
    }

    public void setMsgSkinFlag(String msgSkinFlag) {
        this.msgSkinFlag = msgSkinFlag;
    }

}
