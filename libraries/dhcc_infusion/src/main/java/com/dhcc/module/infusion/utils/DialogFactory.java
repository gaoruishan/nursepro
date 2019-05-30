package com.dhcc.module.infusion.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhcc.module.infusion.R;


/**
 * 获取各种Dialog
 * @author:gaoruishan
 * @date:202019-04-25/15:17
 * @email:grs0515@163.com
 */
public class DialogFactory {

    /**
     * 显示一般的'确认/取消'
     * @param context
     * @param title
     * @param txt
     * @param cancel
     * @param ok
     * @param cancelClick
     * @param okClick
     * @return
     */
    public static Dialog showCommOkCancelDialog(Context context, String title, String txt, String cancel, String ok, @Nullable final View.OnClickListener cancelClick, @Nullable final View.OnClickListener okClick) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(context).inflate(R.layout.comm_ok_cancel_dialog_layout, null);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        TextView tvMessage = view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(txt)) {
            tvMessage.setText(txt);
        }
        Button btnNo = view.findViewById(R.id.btn_no);
        if (!TextUtils.isEmpty(cancel)) {
            btnNo.setVisibility(View.VISIBLE);
            btnNo.setText(cancel);
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    if (cancelClick != null) {
                        cancelClick.onClick(v);
                    }
                }
            });
        }
        Button btnYes = view.findViewById(R.id.btn_yes);
        if (!TextUtils.isEmpty(ok)) {
            btnYes.setVisibility(View.VISIBLE);
            btnYes.setText(ok);
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    if (okClick != null) {
                        okClick.onClick(v);
                    }
                }
            });
        }
        showCenterWindow(dialog, view);

        return dialog;
    }

    /**
     * dialog 居中显示
     * @param dialog
     * @param view
     */
    private static void showCenterWindow(Dialog dialog, View view) {
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                //设置dialog 在布局中的位置
                attr.gravity = Gravity.CENTER;
            }
        }
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 简单的
     * @param context
     * @param txt
     * @param ok
     * @param iv
     * @param l
     */
    @SuppressLint("ResourceType")
    public static Dialog showCommDialog(Context context, String txt, String ok, @DrawableRes int iv, @Nullable final View.OnClickListener l, boolean... autoDismiss) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(false);// 不可取消
        View view = LayoutInflater.from(context).inflate(R.layout.comm_dialog_layout, null);
        ImageView ivComm = view.findViewById(R.id.iv_comm);
        if (iv > 0) {
            ivComm.setImageResource(iv);
        }
        TextView tvTxtComm = view.findViewById(R.id.tv_txt_comm);
        if (!TextUtils.isEmpty(txt)) {
            tvTxtComm.setText(txt);
        }
        TextView tvOkComm = view.findViewById(R.id.tv_ok_comm);
        if (!TextUtils.isEmpty(ok)) {
            tvOkComm.setVisibility(View.VISIBLE);
            tvOkComm.setText(ok);
            tvOkComm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    if (l != null) {
                        l.onClick(v);
                    }
                }
            });
        }
        // 3秒自动消失
        if (autoDismiss != null && autoDismiss.length > 0 && autoDismiss[0]) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                }
            }, 1500);

        }
        //dialog 居中显示
        showCenterWindow(dialog, view);
        return dialog;
    }


}
