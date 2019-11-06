package com.dhcc.module.infusion.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.CommDialog;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.module.infusion.R;
import com.nex3z.flowlayout.FlowLayout;


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
    /**
     * 皮试计时
     * @param context
     * @param okClick
     */
    public static void showCountTime(Context context, final CommClickListener okClick) {
        String[] arr = {"15分钟", "20分钟","30分钟" ,"45分钟", "1小时", "1.5小时", "2小时","3小时"};
        final TextView[] tvArr = new TextView[arr.length];
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(context).inflate(R.layout.dhcc_count_time_dialog_layout, null);
        FlowLayout flLayout = view.findViewById(R.id.fl_layout);
        final EditText etSkinNum = view.findViewById(R.id.et_skin_num);
        for (int i = 0; i < arr.length; i++) {
            View child = LayoutInflater.from(context).inflate(R.layout.dhcc_count_time_dialog_item_tv, null);
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
        setCommButtonClick("取消", null, dialog, view, R.id.btn_no);
        Button btnYes = view.findViewById(R.id.btn_yes);
        btnYes.setVisibility(View.VISIBLE);
        btnYes.setText("确定");
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etSkinNum.getText().toString();
                if (okClick != null && !TextUtils.isEmpty(s)) {
                    dialog.cancel();
                    String txt = null;
                    for (TextView view1 : tvArr) {
                        if (view1.isSelected()) {
                            txt= view1.getText().toString();
                            break;
                        }
                    }
                    okClick.data(new Object[]{txt,s});
                } else {
                    ToastUtils.showShort("请输入批号");
                }
            }
        });
        showCenterWindow(dialog, view);
    }

    /**
     * 消息-置皮试结果
     * @param context
     * @param title
     * @param txt
     * @param cancel
     * @param ok
     * @param cancelClick
     * @param okClick
     * @return
     */
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

    public static Dialog showSkinYinYangDialog(Context context, String title, String cancel, String ok, @Nullable final View.OnClickListener cancelClick, @Nullable final CommClickListener okClick) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        final View view = LayoutInflater.from(context).inflate(R.layout.show_skin_dialog_layout, null);
        setText(title, view, R.id.tv_title);
       final View tvSkinYin = view.findViewById(R.id.tv_skin_yin);
       final View tvSkinYang = view.findViewById(R.id.tv_skin_yang);
       tvSkinYin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tvSkinYin.setSelected(true);
               tvSkinYang.setSelected(false);
           }
       });
       tvSkinYang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tvSkinYang.setSelected(true);
               tvSkinYin.setSelected(false);
           }
       });
       View llAudit = view.findViewById(R.id.ll_audit);
        //配置布局
        final boolean msgSkinFlag = !TextUtils.isEmpty(SPUtils.getInstance().getString(SharedPreference.MSG_SKIN_FLAG));
        llAudit.setVisibility(msgSkinFlag ? View.VISIBLE : View.GONE);
        setCommButtonClick(cancel, cancelClick, dialog, view, R.id.btn_no);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = getText(view, R.id.et_skin_num);
                String pwd = getText(view, R.id.et_skin_pwd);
                if (TextUtils.isEmpty(user) && msgSkinFlag) {
                    ToastUtils.showShort("请输入账户");
                    return;
                }
                if (TextUtils.isEmpty(pwd) && msgSkinFlag) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                // 阳性:Y/+ 阴性:N/-
                String testSkin = tvSkinYin.isSelected() ? "N" : "";
                if(TextUtils.isEmpty(testSkin)){
                     testSkin = tvSkinYang.isSelected() ? "Y" : "";
                }
                if(TextUtils.isEmpty(testSkin)){
                    ToastUtils.showShort("请选择皮试结果");
                	return;
                }
                if (okClick != null) {
                    okClick.data(new Object[]{user, pwd,testSkin});
                    dialog.cancel();
                }
            }
        };
        setCommButtonClick(ok, onClickListener, view, R.id.btn_yes);
        showCenterWindow(dialog, view);
        return dialog;
    }



}
