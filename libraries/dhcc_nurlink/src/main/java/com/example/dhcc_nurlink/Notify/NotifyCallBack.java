package com.example.dhcc_nurlink.Notify;

public interface NotifyCallBack {
    public void onConnected();
    public void onClose();
    public void onMsg(String msg);
    public void onConnectFail();
//    public static void onMsg(String msg);
}
