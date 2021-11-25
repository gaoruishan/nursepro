package com.example.dhcc_nurlink.Notify;


import java.io.UnsupportedEncodingException;

public class Notifycation {
    static{
        System.loadLibrary("dhcnotify");
    }

    private NotifyCallBack callBack;

    public native void notifyInit(String serverIp,int port,String callBackClass,String device);
    public native void notifyClose();

    public void onConnected() {
        if (callBack != null){
            callBack.onConnected();
        }
    }

    public void onClose() {
        if (callBack != null){
            callBack.onClose();
        }
    }


    public void onMsg(byte buffer[],int length) throws UnsupportedEncodingException {
        String msg = new String(buffer,"UTF-8");
        if (callBack != null){
            callBack.onMsg(msg);
        }
    }

    public void onConnectFail(){
        if (callBack != null){
            callBack.onConnectFail();
        }
    }

    public void setCallBack(NotifyCallBack callBack){
        this.callBack = callBack;
    }
}
