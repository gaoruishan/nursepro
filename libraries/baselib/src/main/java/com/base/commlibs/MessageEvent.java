package com.base.commlibs;

import android.os.Bundle;

/**
 * Created by levis on 2018/6/5.
 */

public class MessageEvent {
    private int tag;
    private String message = "";
    private Bundle args;
    private MessageType type = MessageType.NONE;
    private int postion;//滑动位置
    private int offSet;//偏移位置
    private Object object;//句柄
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public MessageEvent(int tag) {
        this.tag = tag;
    }

    public MessageEvent(MessageType type) {
        this.type = type;
    }

    public MessageEvent(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public MessageEvent(int offSet, int postion, MessageType type, Object object) {
        this.offSet = offSet;
        this.postion = postion;
        this.type = type;
        this.object = object;
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String message, int tag) {
        this.message = message;
        this.tag = tag;
    }

    public MessageEvent(int TAG, String message) {
        this.tag = TAG;
        this.message = message;
    }

    public MessageEvent(int tag, String message, Bundle args) {
        this.tag = tag;
        this.message = message;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public MessageType getType() {
        return type;
    }

    public int getPostion() {
        return postion;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Bundle getArgs() {
        return args;
    }

    public void setArgs(Bundle args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "tag=" + tag +
                ", message='" + message + '\'' +
                ", args=" + args +
                ", type=" + type +
                ", postion=" + postion +
                ", offSet=" + offSet +
                ", object=" + object +
                ", select=" + select +
                '}';
    }

    public  enum MessageType {
        NONE,
        SHOW_LOADING,
        HIDE_LOADING,
        SHOW_COMMEND_AREA,
        UPDATE_TASK_MANAGE_LIST,
        KEY_BORAD_OPEN,
        KEY_BORAD_CLOSE,
        REQUEST_APP_MESSAGE_LIST,
        SCROLL_TO_KEYBORD_TOP,
        REQUEST_REFRESH_BIZ_LIST,
        CHAT_ROOM_CLICK_AVATAR,
        UPDATE_BOTTOM_UNREAD_NUM,
        NOTIFY_MESSAGE,
        HEALTH_EDU_ADD_SELECT,
        HEALTH_EDU_SELECT_ORD
    }
}
