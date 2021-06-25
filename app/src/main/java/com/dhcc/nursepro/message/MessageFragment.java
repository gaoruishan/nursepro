package com.dhcc.nursepro.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.adapter.MessageAbnormalAdapter;
import com.dhcc.nursepro.message.adapter.MessageConsultationAdapter;
import com.dhcc.nursepro.message.adapter.MessageNewOrderAdapter;
import com.dhcc.nursepro.message.adapter.MessageSkinAdapter;
import com.dhcc.nursepro.message.adapter.MessagestopAdapter;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.message.bean.ReadMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageFragment
 * 消息页面
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llMessageNeworderTitle;
    private TextView tvMessageNeworderCount;
    private RecyclerView recyMessageNeworder;
    private LinearLayout llMessageStopTitle;
    private TextView tvMessageStopCount;
    private RecyclerView recyMessageStop;
    private LinearLayout llMessageAbnormalTitle;
    private TextView tvMessageAbnormalCount;
    private RecyclerView recyMessageAbnormal;
    private LinearLayout llMessageConsultationTitle;
    private TextView tvMessageConsultationCount;
    private RecyclerView recyMessageConsultation;
    private List<MessageBean.NewOrdPatListBean> newOrdPatList;
    private List<MessageBean.StopListBean> stopOrdPatList;
    private List<MessageBean.AbnormalPatListBean> abnormalPatList;
    private List<MessageBean.ConPatListBean> conPatList;
    private MessageNewOrderAdapter newOrderAdapter = new MessageNewOrderAdapter(new ArrayList<>());
    private MessagestopAdapter stopAdapter = new MessagestopAdapter(new ArrayList<>());
    private MessageAbnormalAdapter abnormalAdapter = new MessageAbnormalAdapter(new ArrayList<>());
    private MessageConsultationAdapter consultationAdapter = new MessageConsultationAdapter(new ArrayList<>());
    private MessageSkinAdapter messageSkinAdapter = new MessageSkinAdapter(new ArrayList<>());
    private RecyclerView recyMessageSkin;

    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_message_neworder_title:
                if (recyMessageNeworder.getVisibility() == View.VISIBLE) {
                    llMessageNeworderTitle.setSelected(true);
                    recyMessageNeworder.setVisibility(View.GONE);
                    tvMessageNeworderCount.setVisibility(View.VISIBLE);
                    tvMessageNeworderCount.setText(newOrderAdapter.getItemCount() + "");
                } else {
                    llMessageNeworderTitle.setSelected(false);
                    tvMessageNeworderCount.setVisibility(View.GONE);
                    recyMessageNeworder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_message_stop_title:
                if (recyMessageStop.getVisibility() == View.VISIBLE) {
                    llMessageStopTitle.setSelected(true);
                    recyMessageStop.setVisibility(View.GONE);
                    tvMessageStopCount.setVisibility(View.VISIBLE);
                    tvMessageStopCount.setText(stopAdapter.getItemCount() + "");
                } else {
                    llMessageStopTitle.setSelected(false);
                    tvMessageStopCount.setVisibility(View.GONE);
                    recyMessageStop.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_message_abnormal_title:
                if (recyMessageAbnormal.getVisibility() == View.VISIBLE) {
                    llMessageAbnormalTitle.setSelected(true);
                    recyMessageAbnormal.setVisibility(View.GONE);
                    tvMessageAbnormalCount.setVisibility(View.VISIBLE);
                    tvMessageAbnormalCount.setText(abnormalAdapter.getItemCount() + "");
                } else {
                    llMessageAbnormalTitle.setSelected(false);
                    tvMessageAbnormalCount.setVisibility(View.GONE);
                    recyMessageAbnormal.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_message_consultation_title:
                if (recyMessageConsultation.getVisibility() == View.VISIBLE) {
                    llMessageConsultationTitle.setSelected(true);
                    recyMessageConsultation.setVisibility(View.GONE);
                    tvMessageConsultationCount.setVisibility(View.VISIBLE);
                    tvMessageConsultationCount.setText(consultationAdapter.getItemCount() + "");
                } else {
                    llMessageConsultationTitle.setSelected(false);
                    tvMessageConsultationCount.setVisibility(View.GONE);
                    recyMessageConsultation.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_message_skin_title:
                switchSkinMessage(f(R.id.recy_message_skin).getVisibility() == View.VISIBLE);
                break;
            default:
                break;

        }
    }

    /**
     * 点击切换
     */
    private void switchSkinMessage(boolean gone) {
        f(R.id.ll_message_skin_title).setSelected(gone);
        f(R.id.tv_message_skin_count).setVisibility(gone ? View.VISIBLE : View.GONE);
        f(R.id.tv_message_skin_count, TextView.class).setText(messageSkinAdapter.getItemCount() + "");
        f(R.id.recy_message_skin).setVisibility(gone ? View.GONE : View.VISIBLE);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
            initData();
        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    private void initData() {
        MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
            @Override
            public void onSuccess(MessageBean msgs) {

                notifyMessage();

                //请求皮试
                //getSkinTestMessage();
                newOrdPatList = msgs.getNewOrdPatList();
                stopOrdPatList = msgs.getStopList();
                abnormalPatList = msgs.getAbnormalPatList();
                conPatList = msgs.getConPatList();

                if (newOrdPatList == null) {
                    newOrdPatList = new ArrayList<>();
                    llMessageNeworderTitle.setVisibility(View.GONE);
                    recyMessageNeworder.setVisibility(View.GONE);
                } else if (newOrdPatList.size() == 0) {
                    llMessageNeworderTitle.setVisibility(View.VISIBLE);
                    llMessageNeworderTitle.setSelected(true);
                    recyMessageNeworder.setVisibility(View.GONE);
                    tvMessageNeworderCount.setVisibility(View.VISIBLE);
                    tvMessageNeworderCount.setText(newOrderAdapter.getItemCount() + "");
                } else {
                    llMessageNeworderTitle.setVisibility(View.VISIBLE);
                    llMessageNeworderTitle.setSelected(false);
                    tvMessageNeworderCount.setVisibility(View.GONE);
                    recyMessageNeworder.setVisibility(View.VISIBLE);
                }

                if (stopOrdPatList == null) {
                    stopOrdPatList = new ArrayList<>();
                    llMessageStopTitle.setVisibility(View.GONE);
                    recyMessageStop.setVisibility(View.GONE);
                } else if (stopOrdPatList.size() == 0) {
                    llMessageStopTitle.setVisibility(View.VISIBLE);
                    llMessageStopTitle.setSelected(true);
                    recyMessageStop.setVisibility(View.GONE);
                    tvMessageStopCount.setVisibility(View.VISIBLE);
                    tvMessageStopCount.setText(stopAdapter.getItemCount() + "");
                } else {
                    llMessageStopTitle.setVisibility(View.VISIBLE);
                    llMessageStopTitle.setSelected(false);
                    tvMessageStopCount.setVisibility(View.GONE);
                    recyMessageStop.setVisibility(View.VISIBLE);
                }


                if (abnormalPatList == null) {
                    abnormalPatList = new ArrayList<>();
                    llMessageAbnormalTitle.setVisibility(View.GONE);
                    recyMessageAbnormal.setVisibility(View.GONE);
                } else if (abnormalPatList.size() == 0) {
                    llMessageAbnormalTitle.setVisibility(View.VISIBLE);
                    llMessageAbnormalTitle.setSelected(true);
                    recyMessageAbnormal.setVisibility(View.GONE);
                    tvMessageAbnormalCount.setVisibility(View.VISIBLE);
                    tvMessageAbnormalCount.setText(abnormalAdapter.getItemCount() + "");
                } else {
                    llMessageAbnormalTitle.setVisibility(View.VISIBLE);
                    llMessageAbnormalTitle.setSelected(false);
                    tvMessageAbnormalCount.setVisibility(View.GONE);
                    recyMessageAbnormal.setVisibility(View.VISIBLE);
                }

                if (conPatList == null) {
                    conPatList = new ArrayList<>();
                    consultationAdapter.setNewData(new ArrayList<>());
                    llMessageConsultationTitle.setVisibility(View.GONE);
                    recyMessageConsultation.setVisibility(View.GONE);
                } else if (conPatList.size() == 0) {
                    llMessageConsultationTitle.setVisibility(View.VISIBLE);
                    llMessageConsultationTitle.setSelected(true);
                    recyMessageConsultation.setVisibility(View.GONE);
                    tvMessageConsultationCount.setVisibility(View.VISIBLE);
                    tvMessageConsultationCount.setText(consultationAdapter.getItemCount() + "");
                } else {
                    llMessageConsultationTitle.setVisibility(View.VISIBLE);
                    llMessageConsultationTitle.setSelected(false);
                    tvMessageConsultationCount.setVisibility(View.GONE);
                    recyMessageConsultation.setVisibility(View.VISIBLE);
                }

                newOrderAdapter.setNewData(newOrdPatList);
                stopAdapter.setNewData(stopOrdPatList);
                abnormalAdapter.setNewData(abnormalPatList);
                consultationAdapter.setNewData(conPatList);
                tvMessageNeworderCount.setText(newOrderAdapter.getItemCount() + "");
                tvMessageStopCount.setText(stopAdapter.getItemCount()+"");
                tvMessageAbnormalCount.setText(abnormalAdapter.getItemCount() + "");
                tvMessageConsultationCount.setText(consultationAdapter.getItemCount() + "");

                if (msgs.getSkinTimeList() != null && msgs.getSkinTimeList().size() > 0) {
                    switchSkinMessage(false);
                } else {
                    switchSkinMessage(true);
                }
                if (msgs.getSkinTimeList() == null) {
                    f(R.id.ll_message_skin_title).setVisibility(View.GONE);
                } else {
                    f(R.id.ll_message_skin_title).setVisibility(View.VISIBLE);
                    messageSkinAdapter.replaceData(msgs.getSkinTimeList());
                }

                int messageNum = newOrdPatList.size() + abnormalPatList.size() + conPatList.size()+stopOrdPatList.size();
                if (msgs.getSkinTimeList()!=null && msgs.getSkinTimeList().size()>0){
                    for (int i = 0; i < msgs.getSkinTimeList().size(); i++) {
                        if (TextUtils.isEmpty(msgs.getSkinTimeList().get(i).getOverTime())){
                            messageNum++;
                        }
                    }
                }
                setMessage(messageNum, msgs.getSoundFlag(), msgs.getVibrateFlag());
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    /**
     * 请求皮试消息
     */
    //    private void getSkinTestMessage() {
    //        //初始化
    //        switchSkinMessage(true);
    //        MessageApiManager.getSkinTestMessage(new CommonCallBack<MessageSkinBean>() {
    //            @Override
    //            public void onFail(String code, String msg) {
    //              ///  showToast("error" + code + ":" + msg);
    //            }
    //
    //            @Override
    //            public void onSuccess(MessageSkinBean bean, String type) {
    //                if (bean.getSkinTimeList() != null && bean.getSkinTimeList().size() > 0) {
    //                    switchSkinMessage(false);
    //                }
    //                messageSkinAdapter.replaceData(bean.getSkinTimeList());
    //            }
    //        });
    //    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hindMap();
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();

        initView(view);
        initAdapter();
        initData();
        //注册事件总线
        EventBus.getDefault().register(this);
    }

    private void initView(View view) {

        if (isSingleModel){
            view.findViewById(R.id.img_back).setVisibility(View.VISIBLE);
            view.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            view.findViewById(R.id.img_back).setVisibility(View.GONE);
        }
        llMessageNeworderTitle = view.findViewById(R.id.ll_message_neworder_title);
        llMessageNeworderTitle.setOnClickListener(this);
        tvMessageNeworderCount = view.findViewById(R.id.tv_message_neworder_count);
        recyMessageNeworder = view.findViewById(R.id.recy_message_neworder);

        llMessageStopTitle = view.findViewById(R.id.ll_message_stop_title);
        llMessageStopTitle.setOnClickListener(this);
        tvMessageStopCount = view.findViewById(R.id.tv_message_stop_count);
        recyMessageStop = view.findViewById(R.id.recy_message_stop);

        llMessageAbnormalTitle = view.findViewById(R.id.ll_message_abnormal_title);
        llMessageAbnormalTitle.setOnClickListener(this);
        tvMessageAbnormalCount = view.findViewById(R.id.tv_message_abnormal_count);
        recyMessageAbnormal = view.findViewById(R.id.recy_message_abnormal);
        llMessageConsultationTitle = view.findViewById(R.id.ll_message_consultation_title);
        llMessageConsultationTitle.setOnClickListener(this);
        tvMessageConsultationCount = view.findViewById(R.id.tv_message_consultation_count);
        recyMessageConsultation = view.findViewById(R.id.recy_message_consultation);
        f(R.id.ll_message_skin_title).setOnClickListener(this);

        //提高展示效率
        recyMessageNeworder.setHasFixedSize(true);
        //设置的布局管理
        recyMessageNeworder.setLayoutManager(new LinearLayoutManager(getActivity()));
        //禁止滑动
        recyMessageNeworder.setNestedScrollingEnabled(false);


        //提高展示效率
        recyMessageStop.setHasFixedSize(true);
        //设置的布局管理
        recyMessageStop.setLayoutManager(new LinearLayoutManager(getActivity()));
        //禁止滑动
        recyMessageStop.setNestedScrollingEnabled(false);

        //提高展示效率
        recyMessageAbnormal.setHasFixedSize(true);
        //设置的布局管理
        recyMessageAbnormal.setLayoutManager(new LinearLayoutManager(getActivity()));
        //禁止滑动
        recyMessageAbnormal.setNestedScrollingEnabled(false);

        //提高展示效率
        recyMessageConsultation.setHasFixedSize(true);
        //设置的布局管理
        recyMessageConsultation.setLayoutManager(new LinearLayoutManager(getActivity()));
        //禁止滑动
        recyMessageConsultation.setNestedScrollingEnabled(false);


    }

    private void initAdapter() {
        newOrderAdapter = new MessageNewOrderAdapter(new ArrayList<MessageBean.NewOrdPatListBean>());
        stopAdapter = new MessagestopAdapter(new ArrayList<MessageBean.StopListBean>());

        abnormalAdapter = new MessageAbnormalAdapter(new ArrayList<MessageBean.AbnormalPatListBean>());
        abnormalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                if (view.getId() == R.id.ll_message_rightmenu) {
                    final MessageBean.AbnormalPatListBean abnormalPatListBean = (MessageBean.AbnormalPatListBean) adapter.getItem(position);

                    MessageApiManager.readMessage(abnormalPatListBean.getEpisodeId(), new MessageApiManager.ReadMessageCallback() {
                        @Override
                        public void onSuccess(ReadMessageBean readMessageBean) {
                            showToast("已读操作成功");
                            abnormalPatList.remove(position);
                            adapter.notifyDataSetChanged();
                            notifyMessage();
                        }

                        @Override
                        public void onFail(String code, String msg) {
                            showToast("error" + code + ":" + msg);
                        }
                    });

                }
            }
        });
        consultationAdapter = new MessageConsultationAdapter(new ArrayList<MessageBean.ConPatListBean>());

        recyMessageNeworder.setAdapter(newOrderAdapter);
        recyMessageStop.setAdapter(stopAdapter);
        recyMessageAbnormal.setAdapter(abnormalAdapter);
        recyMessageConsultation.setAdapter(consultationAdapter);

        recyMessageSkin = RecyclerViewHelper.get(getActivity(), R.id.recy_message_skin);
        messageSkinAdapter = new MessageSkinAdapter(new ArrayList<>());
        recyMessageSkin.setAdapter(messageSkinAdapter);
    }

    public void notifyMessage() {
        {
            MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
                @Override
                public void onSuccess(MessageBean msgs) {
                    int messageNum = (msgs.getNewOrdPatList() != null ? msgs.getNewOrdPatList().size() : 0)
                            + (msgs.getStopList() != null ? msgs.getStopList().size() : 0)
                            + (msgs.getAbnormalPatList() != null ? msgs.getAbnormalPatList().size() : 0)
                            + (msgs.getConPatList() != null ? msgs.getConPatList().size() : 0);
                    if (msgs.getSkinTimeList()!=null && msgs.getSkinTimeList().size()>0){
                        for (int i = 0; i < msgs.getSkinTimeList().size(); i++) {
                            if (TextUtils.isEmpty(msgs.getSkinTimeList().get(i).getOverTime())){
                                messageNum++;
                            }
                        }
                    }
                    setMessage(messageNum, msgs.getSoundFlag(), msgs.getVibrateFlag());
                }

                @Override
                public void onFail(String code, String msg) {
                    showToast("error" + code + ":" + msg);
                }
            });
        }
    }

    /**
     * 接收事件- 更新数据
     *
     * @param event
     */
    @Subscribe
    public void updateList(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateText:" + event.getType());
        if (event.getType() == MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST) {
            initData();
        }
    }
}
