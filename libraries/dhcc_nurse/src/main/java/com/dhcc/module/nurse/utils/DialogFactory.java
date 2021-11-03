package com.dhcc.module.nurse.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.base.commlibs.utils.AppUtil;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.StatusReasonListBean;
import com.dhcc.res.infusion.CustomSelectView;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-18/14:47
 * @email:grs0515@163.com
 */
public class DialogFactory extends CommDialog {

    /**
     * 护理问题-评价/停止/撤销
     * @param context
     * @param txt
     * @param cancel
     * @param ok
     * @param iv
     * @param revokeReasonList
     * @param l
     * @param listener
     * @return
     */
    public static Dialog showPlanDialog(Context context, String txt, String cancel, String ok, @DrawableRes int iv, List<StatusReasonListBean> revokeReasonList, @Nullable final View.OnClickListener l,BaseQuickAdapter.OnItemClickListener listener) {
        if (revokeReasonList == null||revokeReasonList.size()==0) {
            return null;
        }
        if (commDialog != null) {
            commDialog.cancel();
        }
        View view = getView(context, R.layout.dialog_layout_nur_plan);
        commDialog = getCommDialog(context, view);
        RecyclerView rvContent = (RecyclerView) getView(view, R.id.rv_content);
        //默认第一个
        revokeReasonList.get(0).setSelect(true);
        BaseQuickAdapter<StatusReasonListBean, BaseViewHolder> filterAdapter = new BaseQuickAdapter<StatusReasonListBean, BaseViewHolder>(R.layout.item_status_fliter, revokeReasonList) {
            @Override
            protected void convert(BaseViewHolder helper, StatusReasonListBean item) {
                helper.setText(R.id.tv_txt, item.getText());
                helper.getView(R.id.tv_txt).setSelected(item.isSelect());
            }
        };
        rvContent.setAdapter(filterAdapter);
        RecyclerViewHelper.setGridRecyclerView(context, rvContent, 2, false);
//        rvContent.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        filterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < revokeReasonList.size(); i++) {
                    revokeReasonList.get(i).setSelect(i == position);
                }
                filterAdapter.notifyDataSetChanged();
                if (listener != null) {
                    listener.onItemClick(adapter,view,position);
                }
            }
        });
        setImage(iv, view, R.id.iv_comm);
        setText(txt, view, R.id.tv_txt_comm);
        setCommButtonClick(ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resonId = "";
                for (int i = 0; i < revokeReasonList.size(); i++) {
                    if (revokeReasonList.get(i).isSelect()) {
                        resonId = revokeReasonList.get(i).getValue();
                        break;
                    }
                }
                if (l != null) {
                    //保存tag中
                    v.setTag(resonId);
                    l.onClick(v);
                }
            }
        }, commDialog, view, R.id.tv_ok_comm);
        setCommTextViewClick(cancel, null, commDialog, view, R.id.tv_cancel);
        return commDialog;
    }

    /**
     * 护理措施-停止/撤销
     * @param context
     * @param curDateTime
     * @param callBack
     * @return
     */
    public static Dialog showStopInterveDialog(Activity context, String curDateTime,SimpleCallBack<String> callBack) {
        if (commDialog != null) {
            commDialog.cancel();
        }
        View view = getView(context, R.layout.dialog_layout_nur_plan_interve);
        commDialog = getCommDialog(context, view);
        if(TextUtils.isEmpty(curDateTime)){
            curDateTime = TimeUtils.getNowString();
        }
        String date = curDateTime.split(" ")[0];
        String time = curDateTime.split(" ")[1];
        CustomSelectView customSelectView = view.findViewById(R.id.custom_select_date);
        Fragment fragment = AppUtil.getCurActivityFragment();
        if (fragment != null) {
            customSelectView.setCustomSelectTime(fragment.getFragmentManager(), date, time, null);
        }
        setText("提示", view, com.base.commlibs.R.id.tv_title);
        setText("停止时间", view, com.base.commlibs.R.id.tv_message);
        setCommButtonClick("取消", null, commDialog, view, com.base.commlibs.R.id.btn_no);
        setCommButtonClick("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.call(customSelectView.getSelect(),0);
                }
            }
        }, commDialog, view,R.id.btn_yes);
        showCenterWindow(commDialog, view);
        return commDialog;
    }

    public static Dialog showCancelInterveDialog(Activity context,SimpleCallBack<String> callBack) {
        if (commDialog != null) {
            commDialog.cancel();
        }
        View view = getView(context, R.layout.dialog_layout_nur_plan_interve2);
        commDialog = getCommDialog(context, view);
        EditText etInput = view.findViewById(R.id.et_input);
        setText("提示", view, com.base.commlibs.R.id.tv_title);
        setText("确认作废护理措施? \n措施作废后,关联护理任务将自动删除,是否继续?", view, com.base.commlibs.R.id.tv_message);
        setCommButtonClick("取消", null, commDialog, view, com.base.commlibs.R.id.btn_no);
        setCommButtonClick("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etInput.getText().toString();
                if(TextUtils.isEmpty(s)){
                    ToastUtils.showShort("请填写作废原因!");
                    return;
                }
                cancel(commDialog);
                if (callBack != null) {
                    callBack.call(s,0);
                }
            }
        }, view, com.base.commlibs.R.id.btn_yes);
        showCenterWindow(commDialog, view);
        return commDialog;
    }

    /**
     * 弹框显示二维码
     * @param context
     * @param qrCode
     * @param callBack
     * @return
     */
    public static Dialog showCaLogin(Activity context,String qrCode,SimpleCallBack<String> callBack) {
//        if (commDialog != null) {
//            commDialog.cancel();
//        }
        View view = getView(context, R.layout.dialog_layout_ca_login_qrcode);
        Dialog commDialog = getCommDialog(context, view);
//        commDialog.setCanceledOnTouchOutside(true);// 可取消
        ImageView imageView = view.findViewById(R.id.iv_qrcode);

//        String base64 = "data:image/png;"+qrCode;
        String base64 = qrCode;
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        showCenterWindow(commDialog, view);
        return commDialog;
    }
}
