package com.dhcc.nursepro.message;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.adapter.MessageAbnormalAdapter;
import com.dhcc.nursepro.message.adapter.MessageConsultationAdapter;
import com.dhcc.nursepro.message.adapter.MessageNewOrderAdapter;
import com.dhcc.nursepro.message.adapter.MessageSkinAdapter;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.dhcc.nursepro.message.bean.MessageSkinBean;
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
    //登陆成功后所有的广播信息全部注销了，在此重新注册
    protected BaseReceiver mReceiver = new BaseReceiver();
    protected IntentFilter mfilter = new IntentFilter();
    private LinearLayout llMessageNeworderTitle;
    private TextView tvMessageNeworderCount;
    private RecyclerView recyMessageNeworder;
    private LinearLayout llMessageAbnormalTitle;
    private TextView tvMessageAbnormalCount;
    private RecyclerView recyMessageAbnormal;
    private LinearLayout llMessageConsultationTitle;
    private TextView tvMessageConsultationCount;
    private RecyclerView recyMessageConsultation;
    private List<MessageBean.NewOrdPatListBean> newOrdPatList;
    private List<MessageBean.AbnormalPatListBean> abnormalPatList;
    private List<MessageBean.ConPatListBean> conPatList;
    private MessageNewOrderAdapter newOrderAdapter;
    private MessageAbnormalAdapter abnormalAdapter;
    private MessageConsultationAdapter consultationAdapter;
    private MessageSkinAdapter messageSkinAdapter;
    private RecyclerView recyMessageSkin;

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
        f(R.id.tv_message_skin_count, TextView.class).setText(messageSkinAdapter.getItemCount()+"");
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
                //请求皮试
                getSkinTestMessage();
                newOrdPatList = msgs.getNewOrdPatList();
                abnormalPatList = msgs.getAbnormalPatList();
                conPatList = msgs.getConPatList();

                newOrderAdapter.setNewData(newOrdPatList);
                abnormalAdapter.setNewData(abnormalPatList);
                consultationAdapter.setNewData(conPatList);

                if (newOrdPatList.size() == 0) {
                    llMessageNeworderTitle.setSelected(true);
                    recyMessageNeworder.setVisibility(View.GONE);
                    tvMessageNeworderCount.setVisibility(View.VISIBLE);
                    tvMessageNeworderCount.setText(newOrderAdapter.getItemCount() + "");
                } else {
                    llMessageNeworderTitle.setSelected(false);
                    tvMessageNeworderCount.setVisibility(View.GONE);
                    recyMessageNeworder.setVisibility(View.VISIBLE);
                }

                if (abnormalPatList.size() == 0) {
                    llMessageAbnormalTitle.setSelected(true);
                    recyMessageAbnormal.setVisibility(View.GONE);
                    tvMessageAbnormalCount.setVisibility(View.VISIBLE);
                    tvMessageAbnormalCount.setText(abnormalAdapter.getItemCount() + "");
                } else {
                    llMessageAbnormalTitle.setSelected(false);
                    tvMessageAbnormalCount.setVisibility(View.GONE);
                    recyMessageAbnormal.setVisibility(View.VISIBLE);
                }

                if (conPatList.size() == 0) {
                    llMessageConsultationTitle.setSelected(true);
                    recyMessageConsultation.setVisibility(View.GONE);
                    tvMessageConsultationCount.setVisibility(View.VISIBLE);
                    tvMessageConsultationCount.setText(consultationAdapter.getItemCount() + "");
                } else {
                    llMessageConsultationTitle.setSelected(false);
                    tvMessageConsultationCount.setVisibility(View.GONE);
                    recyMessageConsultation.setVisibility(View.VISIBLE);
                }

                tvMessageNeworderCount.setText(newOrderAdapter.getItemCount() + "");
                tvMessageAbnormalCount.setText(abnormalAdapter.getItemCount() + "");
                tvMessageConsultationCount.setText(consultationAdapter.getItemCount() + "");

                int messageNum = msgs.getNewOrdPatList().size() + msgs.getAbnormalPatList().size() + msgs.getConPatList().size();
                setMessage(messageNum);
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
    private void getSkinTestMessage() {
        //初始化
        switchSkinMessage(true);
        MessageApiManager.getSkinTestMessage(new CommonCallBack<MessageSkinBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(MessageSkinBean bean, String type) {
                if (bean.getSkinTimeList() != null && bean.getSkinTimeList().size() > 0) {
                    switchSkinMessage(false);
                    messageSkinAdapter.replaceData(bean.getSkinTimeList());
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (mReceiver != null) {
                getActivity().unregisterReceiver(mReceiver);
            }
        } else {
            initData();
            mfilter.addAction(Action.NEWMESSAGE_SERVICE);
            if (mReceiver != null) {
                getActivity().registerReceiver(mReceiver, mfilter);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();

        initView(view);
        initAdapter();
        initData();
        getActivity().registerReceiver(mReceiver, mfilter);
        //注册事件总线
        EventBus.getDefault().register(this);
    }

    /**
     * 接收事件- 更新数据
     * @param event
     */
    @Subscribe
    public void updateList(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateText:"+event.getType());
        getSkinTestMessage();
    }

    private void initView(View view) {

        llMessageNeworderTitle = view.findViewById(R.id.ll_message_neworder_title);
        llMessageNeworderTitle.setOnClickListener(this);
        tvMessageNeworderCount = view.findViewById(R.id.tv_message_neworder_count);
        recyMessageNeworder = view.findViewById(R.id.recy_message_neworder);
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
        recyMessageAbnormal.setAdapter(abnormalAdapter);
        recyMessageConsultation.setAdapter(consultationAdapter);

        recyMessageSkin = RecyclerViewHelper.get(getActivity(), R.id.recy_message_skin);
        messageSkinAdapter = new MessageSkinAdapter(null);
        recyMessageSkin.setAdapter(messageSkinAdapter);
    }

    public void notifyMessage() {
        {
            MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
                @Override
                public void onSuccess(MessageBean msgs) {
                    int messageNum = msgs.getNewOrdPatList().size() + msgs.getAbnormalPatList().size() + msgs.getConPatList().size();
                    setMessage(messageNum);
                }

                @Override
                public void onFail(String code, String msg) {
                    Toast.makeText(getActivity(), code + "-消息获取失败：" + msg, Toast.LENGTH_SHORT).show();
                    Log.v("1111111nohid", "222");
                }
            });
        }
    }
}
