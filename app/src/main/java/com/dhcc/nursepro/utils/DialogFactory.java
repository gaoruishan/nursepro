package com.dhcc.nursepro.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.SelectTextView;
import com.nex3z.flowlayout.FlowLayout;


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
        setText(title, view, R.id.tv_title);
        setText(txt, view, R.id.tv_message);
        setCommButton(cancel, cancelClick, dialog, view, R.id.btn_no);
        setCommButton(ok, okClick, dialog, view, R.id.btn_yes);
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
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(false);// 不可取消
        View view = LayoutInflater.from(context).inflate(R.layout.comm_dialog_layout, null);
        ImageView ivComm = view.findViewById(R.id.iv_comm);
        if (iv > 0) {
            ivComm.setImageResource(iv);
        }

        setText(txt, view, R.id.tv_txt_comm);
        setCommTextView(ok, l, dialog, view,R.id.tv_ok_comm);
        // 3秒自动消失
        autoDialogDismiss(dialog, autoDismiss);
        //dialog 居中显示
        showCenterWindow(dialog, view);
        return dialog;
    }

    /**
     * 自动销毁Dialog
     * @param dialog
     * @param autoDismiss
     */
    private static void autoDialogDismiss(Dialog dialog, boolean[] autoDismiss) {
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
    }

    /**
     * 设置TextView文字及点击事件
     * @param ok
     * @param l
     * @param dialog
     * @param view
     * @param id
     */
    private static void setCommTextView(String ok, @Nullable View.OnClickListener l, Dialog dialog, View view,int id) {
        TextView tvOkComm = view.findViewById(id);
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
    }

    /**
     * 设置TextView文字
     * @param txt
     * @param view
     * @param id
     */
    private static void setText(String txt, View view, int id) {
        TextView tvTitle = view.findViewById(id);
        if (!TextUtils.isEmpty(txt)) {
            tvTitle.setText(txt);
        }
    }

    /**
     * 获取TextView或EditText的文本
     * @param id
     * @return
     */
    public static String getText(View v,@IdRes int id) {
        View view = v.findViewById(id);
        if (view != null) {
            if (view instanceof TextView && ((TextView) view).getText() != null) {
                return ((TextView) view).getText().toString();
            }
        }
        return "";
    }
    private static void setCommButton(String btnTxt, @Nullable View.OnClickListener okClick, Dialog dialog, View view, int btnId) {
        Button btnYes = view.findViewById(btnId);
        if (!TextUtils.isEmpty(btnTxt)) {
            btnYes.setVisibility(View.VISIBLE);
            btnYes.setText(btnTxt);
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
     * 皮试计时
     * @param context
     * @param okClick
     */
    public static void showCountTime(Context context, CommClickListener okClick) {
        String[] arr = {"15分钟", "20分钟","30分钟" ,"45分钟", "1小时", "1.5小时", "2小时","3小时"};
        TextView[] tvArr = new TextView[arr.length];
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(context).inflate(R.layout.count_time_dialog_layout, null);
        FlowLayout flLayout = view.findViewById(R.id.fl_layout);
        EditText etSkinNum = view.findViewById(R.id.et_skin_num);
        for (int i = 0; i < arr.length; i++) {
            View child = LayoutInflater.from(context).inflate(R.layout.count_time_dialog_item_tv, null);
            TextView tvName = child.findViewById(R.id.tv_name);
            tvName.setText(arr[i]);
            tvArr[i] = tvName;
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (View view1 : tvArr) {
                        view1.setSelected(false);
                    }
                    v.setSelected(true);
                }
            });
            flLayout.addView(child);
        }
        setText("设置皮试时间", view, R.id.tv_title);
        setCommButton("取消", null, dialog, view, R.id.btn_no);
        Button btnYes = view.findViewById(R.id.btn_yes);
        btnYes.setVisibility(View.VISIBLE);
        btnYes.setText("确定");
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etSkinNum.getText().toString();
                if (okClick != null) {
                    dialog.cancel();
                    okClick.onClick(v);
                    String txt = null;
                    for (TextView view1 : tvArr) {
                        if (view1.isSelected()) {
                            txt= view1.getText().toString();
                            break;
                        }
                    }
                    okClick.data(new Object[]{txt,s});
                } else {
//                    ToastUtils.showShort("请输入批号");
                }
            }
        });
        showCenterWindow(dialog, view);
    }

    /**
     * 弹出设置皮试结果
     * @param context
     * @param skinResutl
     * @param testStartTime
     * @param testBetweenTime
     * @param testEndTime
     * @param clickListener
     * @return
     */
    public static Dialog showSetSkinDialog(Context context, String skinResutl, String testStartTime, String testBetweenTime, String testEndTime, @Nullable final CommClickListener clickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(context).inflate(R.layout.skin_dialog_layout, null);
        setText(testStartTime,view,R.id.tv_skin_time_start);
        setText(testBetweenTime,view,R.id.tv_skin_between);
        setText(testEndTime,view,R.id.tv_skin_time_end);
        final SelectTextView stv1 = view.findViewById(R.id.stv1);
        final SelectTextView stv2 = view.findViewById(R.id.stv2);

        if ("-".equals(skinResutl)) {//阴性
            setSelectState(stv1, true, false);
            setSelectState(stv2, false, true);
        } else if ("+".equals(skinResutl)) {//阳性
            setSelectState(stv2, true, false);
            setSelectState(stv1, false, true);
        } else {
            setSelectState(stv1, false, true);
            setSelectState(stv2, false, true);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setSkinResult(v, stv2, stv1);
                 String skinTest = v.getId() == R.id.stv1 ? "N" : "Y";
                String text = getText(view, R.id.et_skin_num);
                String pwd = getText(view, R.id.et_skin_pwd);
                if (clickListener != null) {

                    if (text.length()<1 || pwd.length()<1){
                        ((BaseActivity)context).showToast("请输入复核账号和密码");
                    }else {
                        clickListener.data(new Object[]{skinTest,text,pwd});
                        dialog.cancel();
                    }

                }
            }
        };
        stv1.setOnSelectorListener(onClickListener);
        stv2.setOnSelectorListener(onClickListener);
        showCenterWindow(dialog, view);
        return dialog;
    }

    private static void  setSelectState(SelectTextView stv, boolean select, boolean enable) {
        stv.setToggle(select);
        stv.setEnabled(enable);
        stv.setClickable(enable);
    }
    /**
     * 统一接口回调
     */
    public abstract   static class CommClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }

        public void data(Object[] args) {

        }

        public void data(int i,String s,Object obj) {

        }
    }
}
