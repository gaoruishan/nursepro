package com.dhcc.nursepro.message.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.utils.DialogFactory;
import com.dhcc.res.util.MessageUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.TimeZone;

/**
 * 皮试列表适配器
 * @author:gaoruishan
 * @date:202019-04-23/15:57
 * @email:grs0515@163.com
 */
public class MessageSkinAdapter extends BaseQuickAdapter<MessageBean.SkinTimeListBean, BaseViewHolder> {
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");


    public MessageSkinAdapter(@Nullable List<MessageBean.SkinTimeListBean> data) {
        super(R.layout.item_message_skin, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MessageBean.SkinTimeListBean item) {
        helper.setGone(R.id.ll_count, !TextUtils.isEmpty(item.getOverTime()))
                .setGone(R.id.ll_overtime,TextUtils.isEmpty(item.getOverTime()))
                .setGone(R.id.tv_overtime_tip,TextUtils.isEmpty(item.getOverTime()));;
//        if (!TextUtils.isEmpty(item.getOverTime())) {
//            CountView cvCount = helper.getView(R.id.cv_count);
//            cvCount.getTitleName().setVisibility(View.GONE);
//            cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
//            cvCount.getOneDay().setTextSize(mContext.getResources().getDimension(R.dimen.dp_10));
//            cvCount.start(Long.valueOf(item.getOverTime()), CountView.ONE_DAY);
//
//        }
        helper.setText(R.id.tv_message_regno, item.getPatRegNo())
                .setText(R.id.tv_message_name, item.getPatName())
                .setImageResource(R.id.img_message_sex, MessageBean.getPatSexDrawable(item.getPatSex()));

        String skinResutl = item.getSkinResutl();
        String testStartTime = item.getTestStartTime();
        String observeTime = item.getObserveTime();
        String testBetweenTime = null;
        String testEndTime = null;
        //observeTime差值是固定的 单位s
        if (!TextUtils.isEmpty(observeTime)) {
            testBetweenTime = Integer.valueOf(observeTime) / 60 + "分钟";
            testEndTime = ViewUtil.getBetweenTime(observeTime, testStartTime, 1000);
            boolean otherOk = true;
//            boolean otherOk =  TextUtils.isEmpty(item.getSkinTestAuditDateTime())
//                    && TextUtils.isEmpty(item.getSkinTestDateTime())
//                    && TextUtils.isEmpty(item.getSkinTestAuditCtcpDesc())
//                    && TextUtils.isEmpty(item.getSkinTestCtcpDesc());

            com.dhcc.res.infusion.CountView cvCount = helper.getView(R.id.cv_count);
            boolean isOk = MessageUtil.setCountTime(mContext,cvCount, testStartTime, testEndTime, otherOk);
            helper.setVisible(R.id.ll_count, isOk);
            //已超时
            helper.setGone(R.id.tv_overtime_tip,!isOk);
        }
        if (TextUtils.isEmpty(item.getOverTime())) {
            helper.setText(R.id.tv_needtime,"需执行时间："+testEndTime);
        }


        //置结果
        String finalTestBetweenTime = testBetweenTime;
        String finalTestEndTime = testEndTime;
        helper.getView(R.id.ll_message_rightmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 阳性:Y/+ 阴性:N/-
                showDialog(skinResutl, testStartTime, finalTestBetweenTime, finalTestEndTime, item);
            }
        });
        //医嘱信息
//        rv_item_msg_skin
    }

    private void showDialog(String skinResutl, String testStartTime, String finalTestBetweenTime, String finalTestEndTime, MessageBean.SkinTimeListBean item) {
        DialogFactory.showSetSkinDialog(mContext, skinResutl, testStartTime, finalTestBetweenTime, finalTestEndTime, new DialogFactory.CommClickListener() {
            @Override
            public void data(Object[] args) {
                String OeoreId = item.getOeoreId();
                String skinTest = (String) args[0];
                String auditUserId = (String) args[1];
                String auditPassword = (String) args[2];
                // 请求
                MessageApiManager.setSkinTestResult(OeoreId, skinTest, auditUserId,auditPassword, new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        if (mContext instanceof BaseActivity) {
//                            ((BaseActivity)mContext).showToast(msg);
                        }
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        DialogFactory.showCommDialog(mContext, bean.getMsg(), null, 0, null, true);
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST));
                    }
                });
            }
        });
    }


}
