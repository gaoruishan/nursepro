package com.dhcc.module.infusion.message.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.api.MessageApiManager;
import com.dhcc.module.infusion.message.bean.MessageSkinBean;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.SelectTextView;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;
import com.dhcc.res.infusion.CountView;
import com.dhcc.res.infusion.CustomPatView;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 皮试列表适配器
 * @author:gaoruishan
 * @date:202019-04-23/15:57
 * @email:grs0515@163.com
 */
public class MessageSkinAdapter extends BaseQuickAdapter<MessageSkinBean.SkinTimeListBean, BaseViewHolder> {
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");

    public MessageSkinAdapter(int layoutResId, @Nullable List<MessageSkinBean.SkinTimeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MessageSkinBean.SkinTimeListBean item) {
        helper.getView(R.id.ll_message_content).setSelected(item.isSelect());
        helper.setGone(R.id.ll_skin_test, !TextUtils.isEmpty(item.getSkinTestCtcpDesc()));
        helper.setText(R.id.tv_skin_test, item.getSkinTestCtcpDesc())
                .setText(R.id.tv_skin_test_time, item.getSkinTestDateTime())
                .setText(R.id.tv_skin_test_audit_time, item.getSkinTestAuditDateTime())
                .setText(R.id.tv_skin_test_audit, item.getSkinTestAuditCtcpDesc());
        helper.setText(R.id.tv_message_regno, item.getPatRegNo())
                .setText(R.id.tv_message_name, item.getPatName())
                .setImageResource(R.id.img_message_sex, CustomPatView.getPatSexDrawable(item.getPatSex()));

        RecyclerView rvSkinItemChild = helper.getView(R.id.rv_skin_item_child);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvSkinItemChild, 0);
        rvSkinItemChild.setAdapter(new CommQuickAdapter.ChildAdapter(R.layout.item_posing_child2, item.getOeoreGroup(), false));
        String testStartTime = item.getTestStartTime();
        String observeTime = item.getObserveTime();
        helper.setText(R.id.tv_skin_time_start, testStartTime);
        //observeTime差值是固定的 单位s
        if (!TextUtils.isEmpty(observeTime)) {
            helper.setText(R.id.tv_skin_between, Integer.valueOf(observeTime) / 60 + "分钟");
            SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS);
            formatter.setTimeZone(GMT);
            String formatEndTime = null;
            try {
                Date parse = formatter.parse(testStartTime);
                long endTimeMillis = parse.getTime() + Integer.valueOf(observeTime) * 1000;
                formatEndTime = formatter.format(new Date(endTimeMillis));
                helper.setText(R.id.tv_skin_time_end, formatEndTime + "");
            } catch (ParseException e) {
            }

            SimpleDateFormat formatter1 = new SimpleDateFormat(HH_MM_SS);
            String formatNowTime = formatter1.format(new Date(System.currentTimeMillis()));
            //结束时间> 现在时间
            long offTime = getFormatTime(formatEndTime) - getFormatTime(formatNowTime);
            //现在时间> 开始时间
            long offTime2 = getFormatTime(formatNowTime) - getFormatTime(testStartTime);
            CountView cvCount = helper.getView(R.id.cv_count);
            //没有复核/复核人 并且时间大于0
            boolean isOk = offTime >= 0 && offTime2 >= 0 && TextUtils.isEmpty(item.getSkinTestAuditDateTime())
                    && TextUtils.isEmpty(item.getSkinTestDateTime())
                    && TextUtils.isEmpty(item.getSkinTestAuditCtcpDesc())
                    && TextUtils.isEmpty(item.getSkinTestCtcpDesc());
            helper.setVisible(R.id.ll_count, isOk);
            if (isOk) {
                cvCount.getTitleName().setVisibility(View.GONE);
                cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
                cvCount.getOneDay().setTextSize(mContext.getResources().getDimension(R.dimen.dp_13));
                cvCount.start(offTime, CountView.ONE_DAY);
                cvCount.setOnCountViewStatusListener(new CountView.OnCountViewStatusListener() {
                    @Override
                    public void onStop() {
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.NOTIFY_MESSAGE));
                    }
                });
            }
        }
        final SelectTextView stv1 = helper.getView(R.id.stv1);
        final SelectTextView stv2 = helper.getView(R.id.stv2);

        if ("-".equals(item.getSkinResutl())) {
            //阴性
            setSelectState(stv1, true, false);
            setSelectState(stv2, false, true);
        } else if ("+".equals(item.getSkinResutl())) {
            //阳性
            setSelectState(stv2, true, false);
            setSelectState(stv1, false, true);
        } else {
            setSelectState(stv1, false, true);
            setSelectState(stv2, false, true);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v, item.getPatName(), stv2, stv1);
            }
        };
        stv1.setTag(item.getOeoreId());
        stv2.setTag(item.getOeoreId());
        stv1.setOnSelectorListener(onClickListener);
        stv2.setOnSelectorListener(onClickListener);
    }

    private long getFormatTime(String formatTime) {
        if (!TextUtils.isEmpty(formatTime)) {
            String[] splitEndTime = formatTime.split(":");
            return Integer.parseInt(splitEndTime[0]) * 3600
                    + Integer.parseInt(splitEndTime[1]) * 60 + Integer.parseInt(splitEndTime[2]);
        }
        return 0;
    }

    private void setSelectState(SelectTextView stv, boolean select, boolean enable) {
        stv.setToggle(select);
        stv.setEnabled(enable);
        stv.setClickable(enable);
    }

    private void showDialog(final View v, String content, final SelectTextView stv2, final SelectTextView stv1) {
        // 阳性:Y/+ 阴性:N/-
        final String skinTest = v.getId() == R.id.stv1 ? "N" : "Y";
        String skinName = skinTest.equals("N") ? "阴性" : "阳性";
        //配置布局
        final boolean msgSkinFlag = !TextUtils.isEmpty(SPUtils.getInstance().getString(SharedPreference.MSG_SKIN_FLAG));
        DialogFactory.showSkinDialog(mContext, "皮试结果", content + "的皮试结果为“" + skinName + "”。", "取消", "确定", msgSkinFlag, null, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                super.data(args);
                Object tag = v.getTag();
                if (v.getTag() != null && tag instanceof String) {
                    String OeoreId = (String) tag;
                    String user = (String) args[0];
                    String psw = (String) args[1];
                    setSkinTestResult(OeoreId, skinTest, user, psw, v, stv2, stv1);
                }
            }
        });
    }

    private void setSkinTestResult(String OeoreId, String skinTest, String auditUserId, String auditPassword, final View v, final SelectTextView stv2, final SelectTextView stv1) {
        // 请求 auditUserId
        MessageApiManager.setSkinTestResult(OeoreId, skinTest, auditUserId, auditPassword, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                setSkinResult(v, stv2, stv1);
                EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST));
            }
        });
    }

    private void setSkinResult(View v, SelectTextView stv2, SelectTextView stv1) {
        if (v instanceof SelectTextView) {
            setSelectState((SelectTextView) v, true, false);
        }
        if (v.getId() == R.id.stv1) {
            setSelectState(stv2, false, true);
        }
        if (v.getId() == R.id.stv2) {
            setSelectState(stv1, false, true);
        }
    }
}
