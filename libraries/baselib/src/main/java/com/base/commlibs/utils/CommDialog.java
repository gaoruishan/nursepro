package com.base.commlibs.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.R;

/**
 * @author:gaoruishan
 * @date:202019-07-04/15:50
 * @email:grs0515@163.com
 */
public class CommDialog {

    protected static final String TAG = CommDialog.class.getSimpleName();

    /**
     * 统一接口回调
     */
    public abstract static class CommClickListener  {

        public void data(Object[] args) {

        }

    }
    /**
     * 查找View
     * @param resId View的id
     * @return
     */
    protected static View getView(View view, @IdRes int resId) {
        if (view != null) {
            view = view.findViewById(resId);
            if (view == null) {
                return null;
            }
        }
        return view;
    }
    /**
     * 隐藏或显示
     * @param viewId
     * @param visible
     */
    public  static void setVisible(View parent,@IdRes int viewId, boolean visible) {
        View view = getView(parent,viewId);
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }
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

    /**
     * 获取一个简单Dialog - 居中/不可取消
     * @param context
     * @param view
     * @return
     */
    public static Dialog getCommDialog(Context context, View view) {
        Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(false);// 不可取消
        //dialog 居中显示
        showCenterWindow(dialog, view);
        return dialog;
    }

    /**
     * dialog 居中显示
     * @param dialog
     * @param view
     */
    public static void showCenterWindow(Dialog dialog, View view) {
        if (dialog == null || view == null) {
            return;
        }
        if (dialog.isShowing()) {
            Log.e(TAG," dialog is showing");
            return;
        }
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

    protected static View getView(Context context, @LayoutRes int resource) {
        return LayoutInflater.from(context).inflate(resource, null);
    }

    /**
     * 设置TextView文字及点击事件
     * @param ok
     * @param l
     * @param dialog
     * @param view
     * @param id
     */
    public static void setCommTextViewClick(String ok, @Nullable View.OnClickListener l, Dialog dialog, View view, int id) {
        if (id == 0 || view == null) {
            return;
        }
        TextView tv = view.findViewById(id);
        if (tv != null) {
            tv.setOnClickListener(new View.OnClickListener() {
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
            if (!TextUtils.isEmpty(ok)) {
                tv.setVisibility(View.VISIBLE);
                tv.setText(ok);
            }
        }
    }

    /**
     * 设置Button文字及点击事件
     * @param btnTxt
     * @param okClick
     * @param dialog
     * @param view
     * @param btnId
     */
    public static void setCommButtonClick(String btnTxt, @Nullable View.OnClickListener okClick, Dialog dialog, View view, int btnId) {
        if (btnId == 0 || view == null) {
            return;
        }
        TextView btn = view.findViewById(btnId);
        if (btn != null) {
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    if (okClick != null) {
                        okClick.onClick(v);
                    }
                }
            });
            if (!TextUtils.isEmpty(btnTxt)) {
                btn.setText(btnTxt);
            }
        }
    }
    public static void setCommButtonClick(String btnTxt, @Nullable View.OnClickListener okClick,View view, int btnId) {
        if (btnId == 0 || view == null) {
            return;
        }
        TextView btn = view.findViewById(btnId);
        if (btn != null) {
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (okClick != null) {
                        okClick.onClick(v);
                    }
                }
            });
            if (!TextUtils.isEmpty(btnTxt)) {
                btn.setText(btnTxt);
            }
        }
    }

    /**
     * 自动销毁Dialog
     * @param dialog
     * @param autoDismiss
     */
    public static void autoDialogDismiss(Dialog dialog, boolean[] autoDismiss) {
        if (autoDismiss != null && autoDismiss.length > 0 && autoDismiss[0]) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                }
            }, 3000);

        }
    }

    /**
     * 设置TextView文字
     * @param txt
     * @param view
     * @param id
     */
    public static void setText(String txt, View view, int id) {
        if (id == 0 || view == null) {
            return;
        }
        TextView tvTitle = view.findViewById(id);
        tvTitle.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(txt)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(txt);
        }
    }
    public static void setImage(@DrawableRes int iv, View view,int id) {
        if (id == 0 || view == null) {
            return;
        }
        ImageView ivComm = view.findViewById(id);
        if (iv != 0) {
            ivComm.setImageResource(iv);
        }
    }
    /**
     * 获取TextView或EditText的文本
     * @param id
     * @return
     */
    public static String getText(View v, @IdRes int id) {
        if (id == 0 || v == null) {
            return "";
        }
        View view = v.findViewById(id);
        if (view != null) {
            if (view instanceof TextView && ((TextView) view).getText() != null) {
                return ((TextView) view).getText().toString();
            }
        }
        return "";
    }
}
