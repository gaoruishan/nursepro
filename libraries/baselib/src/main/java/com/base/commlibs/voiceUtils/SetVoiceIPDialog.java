package com.base.commlibs.voiceUtils;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.R;


/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class SetVoiceIPDialog extends Dialog {

    Boolean b;
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageIP;//IP地址
    private TextView messagePort;//端口
    private String titleStr;//从外界设置的title文本
    private String messageStrIP;//从外界设置的IP
    private String messageStrPort;//外界到处的端口号
    private TextView messageRes;//资源路径
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public SetVoiceIPDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
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
     *
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
        setContentView(R.layout.voice_ip_set_dialog_layout);
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
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param messageIP
     */
    public void setMessageIP(String messageIP) {

        messageStrIP = messageIP;
    }

    public void setMessagePort(String messagePort) {
        messageStrPort = messagePort;
    }


    public String getIp() {
        return messageIP.getText().toString();
    }

    public String getPort() {
        return messagePort.getText().toString();
    }
    public String getAddr() {
        String path = messageRes.getText().toString();
        if (!TextUtils.isEmpty(path)) {// 不包含'/'
            path = path.replaceAll("//", "/");
            if (!"/".equals(path.substring(0, 1))) {
                path = "/" + path;
            }
            if ("/".equals(path.substring(path.length() - 1))) {
                path = path.substring(0, path.length() - 1);
            }
        }
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