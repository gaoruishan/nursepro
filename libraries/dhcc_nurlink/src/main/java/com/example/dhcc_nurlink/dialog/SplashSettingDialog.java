

package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SplashSettingDialog extends Dialog {

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
    private String messagePath;

    public SplashSettingDialog(Context context) {
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
        setContentView(R.layout.splash_setting_dialog);
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
        String ip = SharedPreference.HIS_ADDRESS;
        String port = SharedPreference.HIS_PORT;
        String path = SharedPreference.HIS_PATH;
        messageIP.setText(SPUtils.getInstance().getString(SharedPreference.NUR_LINK_IP_HIS, ip));
        messagePort.setText(SPUtils.getInstance().getString(SharedPreference.NUR_LINK_PORT_HIS, port));
        messageRes.setText(SPUtils.getInstance().getString(SharedPreference.NUR_LINK_PATH_HIS, path));
        findViewById(R.id.tv_cancle).setOnClickListener(v -> dismiss());
        findViewById(R.id.tv_update).setOnClickListener(v ->update());
    }
    private void update(){
        yesOnclickListener.onYesClick();
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
                if (isIP(getIp())) {
                    if (TextUtils.isEmpty(getAddr())) {
                        ToastUtils.showLong("资源路径不能为空");
                    } else {
                        SPUtils.getInstance().put(SharedPreference.NUR_LINK_IP_HIS, messageIP.getText().toString());
                        SPUtils.getInstance().put(SharedPreference.NUR_LINK_PORT_HIS, messagePort.getText().toString());
                        SPUtils.getInstance().put(SharedPreference.NUR_LINK_PATH_HIS, messageRes.getText().toString());
                        ToastUtils.showLong("设置成功");

                    }

                } else {
                    ToastUtils.showLong("IP格式不正确，请重新输入");
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
    public int search(String str, String strRes) {//查找字符串里与指定字符串相同的个数
        int n = 0;
        while (str.indexOf(strRes) != -1) {
            int i = str.indexOf(strRes);
            n++;
            str = str.substring(i + 1);
        }
        return n;
    }

    public boolean isIP(String addr) {

        //判断"."的个数，大于3个返回错误
        int pointNum = search(addr, ".");
        if (pointNum > 3) {
            return false;
        }

        String ipStr = addr;
        if (addr.contains(":")) {

            //判断":"个数，大于1个返回错误
            int containNum = search(addr, ":");
            if (containNum > 1) {
                return false;
            }

            //判断":"后面的内容，空的话返回错误，有数字外其他字符也返回错误
            String lastStr = addr.substring(addr.indexOf(":") + 1);
            if (lastStr.contains(".") || lastStr.length() < 1) {
                return false;
            }
            ipStr = addr.substring(0, addr.indexOf(":"));
        }

        if (ipStr.length() < 7 || ipStr.length() > 15 || "".equals(ipStr)) {
            return false;
        }
        // 判断IP格式是否规范
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ipStr);
        boolean ipAddress = mat.find();
        //============对之前的ip判断的bug在进行判断
        if (ipAddress) {
            String[] ips = ipStr.split("\\.");
            if (ips.length == 4) {
                try {
                    for (String ip : ips) {
                        if (Integer.parseInt(ip) < 0 || Integer.parseInt(ip) > 255) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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