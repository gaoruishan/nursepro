package com.dhcc.nursepro.message.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageSkinBean;
import com.dhcc.nursepro.utils.DialogFactory;
import com.dhcc.nursepro.view.CountView;

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


    public MessageSkinAdapter(@Nullable List<MessageSkinBean.SkinTimeListBean> data) {
        super(R.layout.item_message_skin, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MessageSkinBean.SkinTimeListBean item) {
        helper.setVisible(R.id.ll_count, !TextUtils.isEmpty(item.getOverTime()));
        if (!TextUtils.isEmpty(item.getOverTime())) {
            CountView cvCount = helper.getView(R.id.cv_count);
            cvCount.getTitleName().setVisibility(View.GONE);
            cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
            cvCount.getOneDay().setTextSize(mContext.getResources().getDimension(R.dimen.dp_10));
            cvCount.start(Long.valueOf(item.getOverTime()), CountView.ONE_DAY);
        }
        helper.setText(R.id.tv_message_regno, item.getPatRegNo())
                .setText(R.id.tv_message_name, item.getPatName())
                .setImageResource(R.id.img_message_sex, MessageSkinBean.getPatSexDrawable(item.getPatSex()));

        String skinResutl = item.getSkinResutl();
        String testStartTime = item.getTestStartTime();
        String testBetweenTime = null;
        String testEndTime = null;
        if (!TextUtils.isEmpty(item.getObserveTime())) {
            testBetweenTime = Integer.valueOf(item.getObserveTime()) / 60 + "分钟";
            SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS);
            formatter.setTimeZone(GMT);
            try {
                Date parse = formatter.parse(testStartTime);
                long l = parse.getTime() + Integer.valueOf(item.getObserveTime()) * 1000;
                String format = formatter.format(new Date(l));
                testEndTime = format + "";
            } catch (ParseException e) {
            }
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

    private void showDialog(String skinResutl, String testStartTime, String finalTestBetweenTime, String finalTestEndTime, MessageSkinBean.SkinTimeListBean item) {
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
                            ((BaseActivity)mContext).showToast(msg);
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
