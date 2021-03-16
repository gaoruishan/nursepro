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
    //是否http更新
    private String httpUpdateFlag;
    //是否手动输入
    private String handInputFlag;
    //是否Logcat
    private String globalLogcatFlag;
    //是否当前用户工作量
    private String showCurUserWorkload;
    //是否开启网络日志 1-是 ""-否
    private String isNetLog;
    //是否开启webSocket
    private String webSocketFlag;
    //显示弹框时间 毫秒
    private String showDialogTime;
    //统一接口
    private String apiJson;

    public String getApiJson() {
        return apiJson == null ? "" : apiJson;
    }

    public String getShowDialogTime() {
        return showDialogTime == null ? "" : showDialogTime;
    }

    public String getWebSocketFlag() {
        return webSocketFlag == null ? "" : webSocketFlag;
    }


    public String getIsNetLog() {
        return isNetLog == null ? "" : isNetLog;
    }

    public String getShowCurUserWorkload() {
        return showCurUserWorkload == null ? "" : showCurUserWorkload;
    }

    public String getGlobalLogcatFlag() {
        return globalLogcatFlag == null ? "" : globalLogcatFlag;
    }


    public String getHandInputFlag() {
        return handInputFlag == null ? "" : handInputFlag;
    }

    public String getHttpUpdateFlag() {
        return httpUpdateFlag == null ? "" : httpUpdateFlag;
    }

    public String getLocalTestFlag() {
        return localTestFlag == null ? "" : localTestFlag;
    }

    public String getUpdateUrl() {
        return updateUrl == null ? "" : updateUrl;
    }

    public String getSkinDateOffset() {
        return skinDateOffset == null ? "" : skinDateOffset;
    }

    public String getBloodCheckFlag() {
        return bloodCheckFlag == null ? "" : bloodCheckFlag;
    }

    public String getOrdStateFlag() {
        return ordStateFlag == null ? "" : ordStateFlag;
    }

    public String getGlobalViewFlag() {
        return globalViewFlag == null ? "" : globalViewFlag;
    }

    public String getMsgNoticeFlag() {
        return msgNoticeFlag == null ? "" : msgNoticeFlag;
    }

    public String getLogFlag() {
        return logFlag == null ? "" : logFlag;
    }

    public String getMsgSkinFlag() {
        return msgSkinFlag == null ? "" : msgSkinFlag;
    }

}
