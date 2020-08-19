package com.dhcc.module.nurse.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.StatusReasonListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-08-18/14:47
 * @email:grs0515@163.com
 */
public class DialogFactory extends CommDialog {


    private static Dialog commDialog;

    public static Dialog showPlanDialog(Context context, String txt, String cancel, String ok, @DrawableRes int iv, List<StatusReasonListBean> revokeReasonList, @Nullable final View.OnClickListener l,String... s) {
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
            }
        });
        setImage(iv, view, R.id.iv_comm);
        setText(txt, view, R.id.tv_txt_comm);
        setCommTextViewClick(ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resonId = "";
                for (int i = 0; i < revokeReasonList.size(); i++) {
                    StatusReasonListBean bean = revokeReasonList.get(i);
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

    public static void showCommentsDialog() {

    }
}
