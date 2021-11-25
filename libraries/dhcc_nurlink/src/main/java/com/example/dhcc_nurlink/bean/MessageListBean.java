package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.List;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/12/21
 * Time:11:44
 */
public class MessageListBean extends CommResult {
    private List<MesListBean> MesList;

    public List<MesListBean> getMesList() {
        return MesList;
    }

    public void setMesList(List<MesListBean> MesList) {
        this.MesList = MesList;
    }

    public static class MesListBean {
        /**
         * ActionCode : YY
         * ActionDesc : 语音留言
         * ActionLevelType : I
         * AudioContent : 您有一条语音留言
         * AuditDateTime :
         * AuditUser :
         * ContentId :
         * Context : 留言
         * IsExec : N
         * MesStatus : 未读
         * MessageId :
         * MsgAddress :
         * MsgFromLoc : 测试科室
         * MsgFromUserCode : 5671
         * ReadDateTime :
         * ReadUser :
         * RecDateTime : 2021-01-14 09:33:18
         * RefuseDateTime : 2021-01-14 00:00:00
         * RefuseUser :
         * RowId : 28
         * UserCode : 0144
         * UserCodes : 0144
         */

        private String ActionCode;
        private String ActionDesc;
        private String ActionLevelType;
        private String AudioContent;
        private String AuditDateTime;
        private String AuditUser;
        private String ContentId;
        private String Context;
        private String IsExec;
        private String MesStatus;
        private String MessageId;
        private String MsgAddress;
        private String MsgFromLoc;
        private String MsgFromUserCode;
        private String ReadDateTime;
        private String ReadUser;
        private String RecDateTime;
        private String RefuseDateTime;
        private String RefuseUser;
        private String RowId;
        private String UserCode;
        private String UserCodes;
        private String MsgFromBed;
        private String MsgFromUserName;

        public String getMsgFromBed() {
            return MsgFromBed;
        }

        public void setMsgFromBed(String msgFromBed) {
            MsgFromBed = msgFromBed;
        }

        public String getMsgFromUserName() {
            return MsgFromUserName;
        }

        public void setMsgFromUserName(String msgFromUserName) {
            MsgFromUserName = msgFromUserName;
        }

        public String getActionCode() {
            return ActionCode;
        }

        public void setActionCode(String ActionCode) {
            this.ActionCode = ActionCode;
        }

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

        public String getAuditDateTime() {
            return AuditDateTime;
        }

        public void setAuditDateTime(String AuditDateTime) {
            this.AuditDateTime = AuditDateTime;
        }

        public String getAuditUser() {
            return AuditUser;
        }

        public void setAuditUser(String AuditUser) {
            this.AuditUser = AuditUser;
        }

        public String getContentId() {
            return ContentId;
        }

        public void setContentId(String ContentId) {
            this.ContentId = ContentId;
        }

        public String getContext() {
            return Context;
        }

        public void setContext(String Context) {
            this.Context = Context;
        }

        public String getIsExec() {
            return IsExec;
        }

        public void setIsExec(String IsExec) {
            this.IsExec = IsExec;
        }

        public String getMesStatus() {
            return MesStatus;
        }

        public void setMesStatus(String MesStatus) {
            this.MesStatus = MesStatus;
        }

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String MessageId) {
            this.MessageId = MessageId;
        }

        public String getMsgAddress() {
            return MsgAddress;
        }

        public void setMsgAddress(String MsgAddress) {
            this.MsgAddress = MsgAddress;
        }

        public String getMsgFromLoc() {
            return MsgFromLoc;
        }

        public void setMsgFromLoc(String MsgFromLoc) {
            this.MsgFromLoc = MsgFromLoc;
        }

        public String getMsgFromUserCode() {
            return MsgFromUserCode;
        }

        public void setMsgFromUserCode(String MsgFromUserCode) {
            this.MsgFromUserCode = MsgFromUserCode;
        }

        public String getReadDateTime() {
            return ReadDateTime;
        }

        public void setReadDateTime(String ReadDateTime) {
            this.ReadDateTime = ReadDateTime;
        }

        public String getReadUser() {
            return ReadUser;
        }

        public void setReadUser(String ReadUser) {
            this.ReadUser = ReadUser;
        }

        public String getRecDateTime() {
            return RecDateTime;
        }

        public void setRecDateTime(String RecDateTime) {
            this.RecDateTime = RecDateTime;
        }

        public String getRefuseDateTime() {
            return RefuseDateTime;
        }

        public void setRefuseDateTime(String RefuseDateTime) {
            this.RefuseDateTime = RefuseDateTime;
        }

        public String getRefuseUser() {
            return RefuseUser;
        }

        public void setRefuseUser(String RefuseUser) {
            this.RefuseUser = RefuseUser;
        }

        public String getRowId() {
            return RowId;
        }

        public void setRowId(String RowId) {
            this.RowId = RowId;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public String getUserCodes() {
            return UserCodes;
        }

        public void setUserCodes(String UserCodes) {
            this.UserCodes = UserCodes;
        }
    }
}
