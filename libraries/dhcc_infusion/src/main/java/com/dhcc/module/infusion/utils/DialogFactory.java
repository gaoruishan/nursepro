package com.dhcc.module.infusion.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.dhcc.module.infusion.workarea.blood.adapter.PatInfoDialogAdapter;
import com.dhcc.module.infusion.workarea.comm.bean.PatOrdersBean;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;


/**
 * 获取各种Dialog
 * @author:gaoruishan
 * @date:202019-04-25/15:17
 * @email:grs0515@163.com
 */
public class DialogFactory extends CommDialog {


    private static Dialog dialog;

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
        dialog = getDialog(context);
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
        String[] arr = {"15分钟", "20分钟", "30分钟", "45分钟", "1小时", "1.5小时", "2小时", "3小时"};
        final TextView[] tvArr = new TextView[arr.length];
        dialog = getDialog(context);
        dialog.setCanceledOnTouchOutside(true);
        final View view = LayoutInflater.from(context).inflate(R.layout.dhcc_count_time_dialog_layout, null);
        FlowLayout flLayout = view.findViewById(R.id.fl_layout);
        final EditText etSkinTime = view.findViewById(R.id.et_skin_time);
        for (int i = 0; i < arr.length; i++) {
            View child = LayoutInflater.from(context).inflate(R.layout.dhcc_count_time_dialog_item_tv, null);
            TextView tvName = child.findViewById(R.id.tv_name);
            tvName.setText(arr[i]);
            tvName.setTag(i);
            tvArr[i] = tvName;
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pst = (int) v.getTag();
                    for (int j = 0; j < tvArr.length; j++) {
                        if (j != pst) {
                            tvArr[j].setSelected(false);
                        }
                    }
                    boolean selected = v.isSelected();
                    v.setSelected(!selected);
                }
            });
            flLayout.addView(child);
        }
        etSkinTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !TextUtils.isEmpty(s.toString())) {
                    if ("0".equals(s.toString()) || "00".equals(s.toString())) {
                        etSkinTime.setText("");
                        return;
                    }
                    for (int j = 0; j < tvArr.length; j++) {
                        tvArr[j].setSelected(false);
                    }
                }
            }
        });
        setText("设置皮试时间", view, R.id.tv_title);
        setCommButtonClick("取消", null, dialog, view, R.id.btn_no);
        Button btnYes = view.findViewById(R.id.btn_yes);
        btnYes.setVisibility(View.VISIBLE);
        btnYes.setText("确定");
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getText(view, R.id.et_skin_num);
                String etTimeText = getText(view, R.id.et_skin_time);
                if (TextUtils.isEmpty(s)) {
                    s = "";
                }
                if (okClick != null) {
                    String txt = null;
                    for (TextView view1 : tvArr) {
                        if (view1.isSelected()) {
                            txt = view1.getText().toString();
                            break;
                        }
                    }
                    if (txt == null && TextUtils.isEmpty(etTimeText)) {
                        ToastUtils.showShort("请选择或输入计时时间");
                        return;
                    }
                    if (txt == null) {
                        txt = etTimeText + "分钟";
                    }
                    dialog.cancel();
                    okClick.data(new Object[]{txt, s});
                }
            }
        });
        showCenterWindow(dialog, view);
    }

    /**
     * 消息-置皮试结果/采血复核
     * @param context
     * @param title
     * @param txt
     * @param cancel
     * @param ok
     * @param cancelClick
     * @param okClick
     * @return
     */
    public static Dialog showSkinDialog(Context context, String title, String txt, String cancel, String ok, final boolean msgSkinFlag, @Nullable final View.OnClickListener cancelClick, @Nullable final CommClickListener okClick) {
        dialog = getDialog(context);
        dialog.setCanceledOnTouchOutside(true);
        final View view = LayoutInflater.from(context).inflate(R.layout.msg_skin_dialog_layout, null);
        setText(title, view, R.id.tv_title);
        setText(txt, view, R.id.tv_message);
        View llAudit = view.findViewById(R.id.ll_audit);
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
        dialog = getDialog(context);
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
                if (TextUtils.isEmpty(testSkin)) {
                    testSkin = tvSkinYang.isSelected() ? "Y" : "";
                }
                if (TextUtils.isEmpty(testSkin)) {
                    ToastUtils.showShort("请选择皮试结果");
                    return;
                }
                if (okClick != null) {
                    okClick.data(new Object[]{user, pwd, testSkin});
                    dialog.cancel();
                }
            }
        };
        setCommButtonClick(ok, onClickListener, view, R.id.btn_yes);
        showCenterWindow(dialog, view);
        return dialog;
    }

    /**
     * 显示病人信息
     * @param context
     * @param bean
     * @param okClick
     * @return
     */
    public static Dialog showPatInfo(Activity context, ScanInfoBean bean, @Nullable View.OnClickListener okClick) {
        return showPatInfo(context, bean.getOrders(), bean.getMsg(), bean.getCanExeFlag(), okClick);
    }

    public static Dialog showPatInfo(Activity context, List<PatOrdersBean> orders, String msg, String canExeFlag, @Nullable View.OnClickListener okClick) {
        if (orders == null
                || orders.size() == 0) {
            return null;
        }
        dialog = getDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pat_info_layout, null);
        setVisible(view, R.id.ll_msg, !TextUtils.isEmpty(msg));
        setText(msg, view, R.id.tv_popup_msg);
        PatOrdersBean ordersBean = orders.get(0);
        setText(ordersBean.getSttDateTime() + " " + ordersBean.getPhcinDesc() + " " + ordersBean.getCtcpDesc() + ""
                , view, R.id.tv_popup_extra);
        String patInfo = "".equals(ordersBean.getBedCode()) ? ordersBean.getPatName() + "-" + ordersBean.getAge()
                : ordersBean.getBedCode().replace("床", "") + "床-" + ordersBean.getPatName() + "-" + ordersBean.getAge();
        setText(patInfo, view, R.id.tv_pat_info);
        setText("医嘱信息(" + orders.size() + ")", view, R.id.tv_info_name);
        setCommButtonClick("", null, dialog, view, R.id.tv_popup_cancel);
        setCommButtonClick("", okClick, dialog, view, R.id.tv_popup_ok);
        //canExeFlag 0 不能 1 能
        if (!"1".equals(canExeFlag)) {
            getView(view, R.id.tv_popup_ok).setEnabled(false);
            getView(view, R.id.tv_popup_ok).setClickable(false);
            getView(view, R.id.tv_popup_ok).setBackgroundResource(R.drawable.dhcc_bg_dialog_cancel);
        }
        RecyclerView recyclerView = (RecyclerView) getView(view, R.id.rv_popup_child_info);
        RecyclerViewHelper.setDefaultRecyclerView(context, recyclerView);
        recyclerView.setAdapter(new PatInfoDialogAdapter(orders));
        showCenterWindow(dialog, view);
        return dialog;
    }

    private static Dialog getDialog(Context context) {
        if (dialog != null) {
            dialog.cancel();
        }
        return new Dialog(context, R.style.MyDialog);
    }

    public static void showTest(Activity context, final CommClickListener okClick) {
        dialog = getDialog(context);
        dialog.setCanceledOnTouchOutside(true);
        final View view = LayoutInflater.from(context).inflate(R.layout.test_dialog_layout, null);
        TextView btn = view.findViewById(R.id.btn_yes);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    if (okClick != null) {
                        okClick.data(new Object[]{getText(view, R.id.et_txt)});
                    }
                }
            });

        showCenterWindow(dialog, view);
    }
}
