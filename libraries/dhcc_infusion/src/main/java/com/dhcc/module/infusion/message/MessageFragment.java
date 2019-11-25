package com.dhcc.module.infusion.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommonCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.adapter.MessageInfusionAdapter;
import com.dhcc.module.infusion.message.adapter.MessageSkinAdapter;
import com.dhcc.module.infusion.message.api.MessageApiManager;
import com.dhcc.module.infusion.message.bean.MessageInfusionBean;
import com.dhcc.module.infusion.message.bean.MessageSkinBean;
import com.dhcc.module.infusion.message.bean.NotifyMessageBean;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.PatInfoFragment;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.CustomTabView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * MessageInfusionFragment
 * 消息页面
 */
public class MessageFragment extends BaseFragment implements CustomTabView.OnTabClickLisenter, BaseQuickAdapter.OnItemChildClickListener {
    private RecyclerView[] recyclerViews;
    private MessageInfusionAdapter messageInfusionAdapter;
    private MessageSkinAdapter messageSkinAdapter;
    private int mCurrentPosition;
    private RecyclerView rvMsgInfusion;
    private RecyclerView rvMsgSkin;

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
            initData();
        }
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            String scanInfo = doScanInfo(intent);
            if (messageInfusionAdapter != null) {
                List<MessageInfusionBean.InfusionTimeListBean> data = messageInfusionAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    MessageInfusionBean.InfusionTimeListBean bean = data.get(i);
                    bean.setSelect(false);
                    if (bean.getPatRegNo().equals(scanInfo)) {
                        bean.setSelect(true);
                        rvMsgInfusion.scrollToPosition(i);
                        messageInfusionAdapter.notifyItemChanged(i);
                       // startPatInfoFragment(messageInfusionAdapter,i);
                    }
                }
            }
            if (messageSkinAdapter != null) {
                List<MessageSkinBean.SkinTimeListBean> data = messageSkinAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    MessageSkinBean.SkinTimeListBean bean = data.get(i);
                    bean.setSelect(false);
                    if (bean.getPatRegNo().equals(scanInfo)) {
                        bean.setSelect(true);
                        rvMsgSkin.scrollToPosition(i);
                        messageSkinAdapter.notifyItemChanged(i);
                    }
                }
            }
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_infusion, container, false);
    }

    private void initData() {
        MessageApiManager.getInfusionMessage(new CommonCallBack<MessageInfusionBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(MessageInfusionBean bean, String type) {
                messageInfusionAdapter.replaceData(bean.getInfusionTimeList());
                if (bean.getInfusionTimeList() != null && bean.getInfusionTimeList().size() > 0) {
                    bean.setTypeItem(MessageInfusionBean.TYPE_2);
                    f(R.id.ll_empty).setVisibility(View.GONE);
                } else if (mCurrentPosition == 1) {//输液
                    f(R.id.ll_empty).setVisibility(View.VISIBLE);
                }
            }
        });
        MessageApiManager.getSkinTestMessage(new CommonCallBack<MessageSkinBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(MessageSkinBean bean, String type) {
                List<MessageSkinBean.SkinTimeListBean> skinTimeList = bean.getSkinTimeList();
                messageSkinAdapter.replaceData(skinTimeList);
                if (skinTimeList != null && skinTimeList.size() > 0) {
                    f(R.id.ll_empty).setVisibility(View.GONE);
                } else if (mCurrentPosition == 0) {//皮试
                    f(R.id.ll_empty).setVisibility(View.VISIBLE);
                }
            }
        });
        MessageApiManager.getNotifyMessage(new CommonCallBack<NotifyMessageBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(NotifyMessageBean bean, String type) {
                for (NotifyMessageBean.NotifyMessageListBean b : bean.getNotifyMessageList()) {
                    if ("Infusion".equals(b.getMType())) {//输液
                        f(R.id.ctv,CustomTabView.class).setTabRedSpot(b.getMNum(), 1);
                    }
                    if ("Skin".equals(b.getMType())) {//皮试
                        f(R.id.ctv,CustomTabView.class).setTabRedSpot(b.getMNum(), 0);
                    }
                }
            }
        });
    }

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
        if (getActivity() == null) {
            return;
        }
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        CustomTabView ctv = view.findViewById(R.id.ctv);
        ctv.setTabText(new String[]{"皮试", "输液"});
        ctv.setOnTabClickLisenter(this);
        rvMsgSkin = RecyclerViewHelper.get(getActivity(), R.id.rv_msg_skin);
        rvMsgInfusion = RecyclerViewHelper.get(getActivity(), R.id.rv_msg_infusion);
        recyclerViews = new RecyclerView[]{rvMsgSkin, rvMsgInfusion};
        messageInfusionAdapter = AdapterFactory.getMessageInfusion();
        messageSkinAdapter = AdapterFactory.getMessageSkin();
        rvMsgInfusion.setAdapter(messageInfusionAdapter);
        rvMsgSkin.setAdapter(messageSkinAdapter);
        messageInfusionAdapter.setOnItemChildClickListener(this);
        setSwitchRecycleView(0);
        initData();
        //调用注册广播(虽然继承了baseFragment,但父类不是UniversalActivity)
        onPreActivityCreate(null,null);
        //注册事件总线
        EventBus.getDefault().register(this);
    }
    /**
     * 接收事件- 更新数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateData:" + event.getType());
        if (event.getType() == MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST) {
            initData();
        }
    }
    private void setSwitchRecycleView(int pst) {
        mCurrentPosition = pst;
        f(R.id.ll_empty).setVisibility(View.GONE);
        for (int i = 0; i < recyclerViews.length; i++) {
            recyclerViews[i].setVisibility(pst == i ? View.VISIBLE : View.GONE);
            BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerViews[i].getAdapter();
            boolean isShow = recyclerViews[i].getVisibility() == View.VISIBLE && adapter.getData().size() <= 0;
            if (isShow) {
                f(R.id.ll_empty).setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onTabClick(int pst, View v) {
        setSwitchRecycleView(pst);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof MessageInfusionAdapter) {
            startPatInfoFragment(adapter, position);
        }
    }

    private void startPatInfoFragment(BaseQuickAdapter adapter, int position) {
            Bundle bundle = new Bundle();
            MessageInfusionBean.InfusionTimeListBean bean = (MessageInfusionBean.InfusionTimeListBean) adapter.getData().get(position);
            if (!TextUtils.isEmpty(bean.getPatRegNo())) {
                bundle.putString("id", bean.getPatRegNo());
                CustomPatView.startPatInfoFragment(getContext(), PatInfoFragment.class.getName(), bundle);
            }
        }
}
