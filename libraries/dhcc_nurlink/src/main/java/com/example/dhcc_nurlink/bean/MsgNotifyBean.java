package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

/**
 * com.example.nurlink
 * <p>
 * author Q
 * Date: 2021/1/20
 * Time:14:25
 */
public class MsgNotifyBean extends CommResult {
    /**
     * ActionDesc : 会诊
     * ActionLevelType : G
     * AudioContent :
     */

    private String ActionDesc;
    private String ActionLevelType;
    private String AudioContent;

    public String getActionDesc() {
        return ActionDesc;
    }

    public void setActionDesc(String ActionDesc) {
        this.ActionDesc = ActionDesc;
    }

    public String getActionLevelType() {
        return ActionLevelType;
    }

    public void setActionLevelType(String ActionLevelType) {
        this.ActionLevelType = ActionLevelType;
    }

    public String getAudioContent() {
        return AudioContent;
    }

    public void setAudioContent(String AudioContent) {
        this.AudioContent = AudioContent;
    }
}
