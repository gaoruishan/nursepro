package com.dhcc.module.infusion.workarea.blood.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.bean.ArcimDescListBean;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * 是皮试/采血/配液的父类
 * @author:gaoruishan
 * @date:202019-11-14/15:26
 * @email:grs0515@163.com
 */
public abstract class BaseBloodQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    private String scanInfo;

    public BaseBloodQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    /**
     * 当前扫码信息
     * @param scanInfo
     */
    public void setCurrentScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    /**
     * 设置公共数据-采血/注射
     * @param helper
     * @param item
     */
    protected void setCommData(BaseViewHolder helper, BloodOrdListBean item) {
        helper.setText(R.id.tv_lab_no, item.getLabNo())
                .setText(R.id.tv_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_creator, item.getCtcpDesc())
                .setText(R.id.tv_operate, item.getSpecDesc())
                .setText(R.id.tv_time, item.getCreateDateTime());
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setText(item.getDisposeStatDesc());
        String labColor = item.getLabColor();
        if (TextUtils.isEmpty(item.getLabColor())) {
            labColor = "#62ABFF";
        }
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                .build();
        blTvStatus.setBackground(drawable);
        helper.setGone(R.id.tv_time, !TextUtils.isEmpty(item.getCreateDateTime()));
        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getDisposeStatDesc()));

        //采血复核
        setBloodCheckData(helper,item);
    }

    /**
     * 采血复核
     */
    protected void setBloodCheckData(BaseViewHolder helper, BloodOrdListBean item) {
        helper.setGone(R.id.ll_lab, !TextUtils.isEmpty(item.getAuditLabUser()));
        helper.setText(R.id.tv_status_lab, "核")
                .setText(R.id.tv_lab_user, item.getAuditLabUser())
                .setText(R.id.tv_lab_time, item.getAuditLabDateTime());
    }

    /**
     * 注射配液
     */
    protected void setInjectDosingData(BaseViewHolder helper, BloodOrdListBean item) {
        //选中
        RecyclerView rvItem = helper.getView(R.id.rv_item);
        String oeoriId = item.getOeoriId();
        setBackgroundColor(rvItem, oeoriId, R.color.blue_dark2, R.color.white);
        //配液状态
        String desUser = item.getDesUser();
        String desDateTime = item.getDesDateTime();

        //复核状态
        String auditUser = item.getAuditUser();
        String auditDateTime = item.getAuditDateTime();

        setDosingAuditData(helper, desUser, desDateTime, auditUser, auditDateTime);
    }

    private void setDosingAuditData(BaseViewHolder helper, String desUser, String desDateTime, String auditUser, String auditDateTime) {
        helper.setGone(R.id.ll_dosing, !TextUtils.isEmpty(desUser));
        helper.setText(R.id.tv_status_dosing, "配")
                .setText(R.id.tv_dosing_user, desUser)
                .setText(R.id.tv_dosing_time, desDateTime);
        helper.setGone(R.id.ll_audit, !TextUtils.isEmpty(auditUser));
        helper.setText(R.id.tv_status_audit, "核")
                .setText(R.id.tv_audit_user, auditUser)
                .setText(R.id.tv_audit_time,auditDateTime );
    }

    /**
     * 皮试配液
     */
    protected void setSkinDosingData(BaseViewHolder helper, SkinListBean.OrdListBean item) {
        //选中
        View llBgSelect = helper.getView(R.id.ll_bg_select);
        String oeoriId = item.getOeoriId();
        setBackgroundColor(llBgSelect, oeoriId, R.color.blue_dark2, R.color.white);
        //配液状态
        String desUser = item.getDesUser();
        String desDateTime = item.getDesDateTime();

        //复核状态
        String auditUser = item.getAuditUser();
        String auditDateTime = item.getAuditDateTime();

        setDosingAuditData(helper, desUser, desDateTime, auditUser, auditDateTime);
    }

    private void setBackgroundColor(View llBgSelect, String oeoriId, int p, int p2) {
        if (llBgSelect != null) {
            int select = ContextCompat.getColor(mContext, p);
            int unselect = ContextCompat.getColor(mContext, p2);
            llBgSelect.setBackgroundColor(oeoriId.equals(scanInfo) ? select : unselect);
        }
    }

    /**
     * 设置注射条目
     * @param helper
     * @param arcimDescList
     */
    protected void setInjectChildAdapter(BaseViewHolder helper, List<ArcimDescListBean> arcimDescList) {
        RecyclerView rvItem = helper.getView(R.id.rv_item);
        if (rvItem != null) {
            RecyclerViewHelper.setDefaultRecyclerView(mContext, rvItem, 0, LinearLayoutManager.VERTICAL);
            InjectChildAdapter childAdapter = new InjectChildAdapter(arcimDescList);
            rvItem.setAdapter(childAdapter);
        }
    }

    /**
     * 简单条目(注射)
     */
    public class InjectChildAdapter extends BaseQuickAdapter<ArcimDescListBean, BaseViewHolder> {

        public InjectChildAdapter(@Nullable List<ArcimDescListBean> data) {
            super(R.layout.item_inject_child_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ArcimDescListBean item) {
            helper.setText(R.id.tv_title, item.getArcimDesc())
                    .setText(R.id.tv_content, item.getDoseQtyUnit() + "      " + item.getPhOrdQtyUnit());
        }
    }
}
