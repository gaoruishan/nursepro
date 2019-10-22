package com.dhcc.module.health.login;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.module.health.R;


/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class SetIPDialog extends Dialog {

    private static final String TAG = SetIPDialog.class.getSimpleName();
    Boolean b;
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageIP;//IP地址
    private TextView messagePort;//端口
    private TextView messageRes;//资源路径
    private String titleStr;//从外界设置的title文本
    private String messageStrIP;//从外界设置的IP
    private String messageStrPort;//外界到处的端口号
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private String messagePath;

    public SetIPDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置取消按钮的显示内容和监听
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhcc_ip_set_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        messageIP = findViewById(R.id.message);
        messagePort = findViewById(R.id.message2);
        messageRes = (EditText) findViewById(R.id.message3);

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        //            if (titleStr != null) {
        //                titleTv.setText(titleStr);
        //            }
        if (messageStrIP != null) {
            messageIP.setText(messageStrIP);
        }
        if (messageStrPort != null) {
            messagePort.setText(messageStrPort);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
        if (!TextUtils.isEmpty(messagePath)) {
            messageRes.setText(messagePath);
        }
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 从外界Activity为Dialog设置标题
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     * @param path
     * @param message
     */
    public void setMessage(String message, String path) {
        if (message.contains(":")) {
            //判断":"后面的内容，空的话返回错误，有数字外其他字符也返回错误
            messageStrPort = message.substring(message.indexOf(":") + 1);
            messageStrIP = message.substring(0, message.indexOf(":"));
        } else {
            messageStrIP = message;
            messageStrPort = "";
        }
        messagePath = path;
    }


    public String getIp() {

        if (TextUtils.isEmpty(messagePort.getText())) {
            return messageIP.getText().toString();
        } else {
            return messageIP.getText().toString() + ":" + messagePort.getText().toString();
        }
    }

    public String getAddr() {
        String path = messageRes.getText().toString();
        Log.e(TAG, "(SetIPDialog.java:181) " + path);
        if (!TextUtils.isEmpty(path)) {// 不包含'/'
            path = path.replaceAll("//", "/");
            if (!"/".equals(path.substring(0, 1))) {
                path = "/" + path;
            }
            if ("/".equals(path.substring(path.length() - 1))) {
                path = path.substring(0, path.length() - 1);
            }
        }
        Log.e(TAG, "(SetIPDialog.java:188) " + path);
        return path;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }
}