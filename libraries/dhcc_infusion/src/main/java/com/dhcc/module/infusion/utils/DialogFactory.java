package com.dhcc.module.infusion.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.CommDialog;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;


/**
 * 获取各种Dialog
 * @author:gaoruishan
 * @date:202019-04-25/15:17
 * @email:grs0515@163.com
 */
public class DialogFactory extends CommDialog {


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
        setText(title, view, R.id.tv_title);
        setText(txt, view, R.id.tv_message);
        setCommButtonClick(cancel, cancelClick, dialog, view, R.id.btn_no);
        setCommButtonClick(ok, okClick, dialog, view, R.id.btn_yes);
        showCenterWindow(dialog, view);
        return dialog;
    }

    public static Dialog showSkinDialog(Context context, String title, String txt, String cancel, String ok, @Nullable final View.OnClickListener cancelClick, @Nullable final CommClickListener okClick) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        final View view = LayoutInflater.from(context).inflate(R.layout.msg_skin_dialog_layout, null);
        setText(title, view, R.id.tv_title);
        setText(txt, view, R.id.tv_message);
        View llAudit = view.findViewById(R.id.ll_audit);
        //配置布局
        final boolean msgSkinFlag = !TextUtils.isEmpty(SPUtils.getInstance().getString(SharedPreference.MSG_SKIN_FLAG));
        llAudit.setVisibility(msgSkinFlag ? View.VISIBLE : View.GONE);
        setCommButtonClick(cancel, cancelClick, dialog, view, R.id.btn_no);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = getText(view, R.id.et_skin_num);
                String pwd = getText(view, R.id.et_skin_pwd);
                if (TextUtils.isEmpty(text) && msgSkinFlag) {
                    ToastUtils.showShort("请输入账户");
                    return;
                }
                if (TextUtils.isEmpty(pwd) && msgSkinFlag) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (okClick != null) {
                    okClick.data(new Object[]{text, pwd});
                    dialog.cancel();
                }
            }
        };
        setCommButtonClick(ok, onClickListener, view, R.id.btn_yes);
        showCenterWindow(dialog, view);
        return dialog;
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
        View view = getView(context, R.layout.comm_dialog_layout);
        Dialog dialog = getCommDialog(context, view);
        setImage(iv, view, R.id.iv_comm);
        setText(txt, view, R.id.tv_txt_comm);
        setCommTextViewClick(ok, l, dialog, view, R.id.tv_ok_comm);
        // 3秒自动消失
        autoDialogDismiss(dialog, autoDismiss);
        return dialog;
    }

    public static Dialog showCommDialog(Context context, String txt, String ok, @Nullable final View.OnClickListener l, boolean... autoDismiss) {
        View view = getView(context, R.layout.comm_dialog_layout);
        Dialog dialog = getCommDialog(context, view);
        // 可取消
        dialog.setCanceledOnTouchOutside(true);
        view.findViewById(R.id.iv_comm).setVisibility(View.GONE);
        setText(txt, view, R.id.tv_txt_comm);
        setCommTextViewClick(ok, l, dialog, view, R.id.tv_ok_comm);
        // 3秒自动消失
        autoDialogDismiss(dialog, autoDismiss);
        return dialog;
    }


}
