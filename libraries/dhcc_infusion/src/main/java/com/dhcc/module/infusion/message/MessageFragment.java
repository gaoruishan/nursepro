package com.dhcc.module.infusion.message;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.adapter.MessageInfusionAdapter;
import com.dhcc.module.infusion.message.adapter.MessageSkinAdapter;
import com.dhcc.module.infusion.message.api.MessageApiManager;
import com.dhcc.module.infusion.message.bean.MessageInfusionBean;
import com.dhcc.module.infusion.message.bean.MessageSkinBean;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.view.CustomPatView;
import com.dhcc.module.infusion.view.CustomTabView;
import com.dhcc.module.infusion.workarea.PatInfoFragment;

import java.util.List;

/**
 * MessageInfusionFragment
 * 消息页面
 */
public class MessageFragment extends BaseFragment implements CustomTabView.OnTabClickLisenter, BaseQuickAdapter.OnItemChildClickListener {
    //登陆成功后所有的广播信息全部注销了，在此重新注册
    protected BaseReceiver mReceiver = new BaseReceiver();
    protected IntentFilter mfilter = new IntentFilter();
    private CustomTabView ctv;
    private RecyclerView[] recyclerViews;
    private MessageInfusionAdapter messageInfusionAdapter;
    private MessageSkinAdapter messageSkinAdapter;

    @SuppressLint("NewApi")
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
            initData();
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
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(MessageInfusionBean bean, String type) {
                if (bean.getInfusionTimeList() != null && bean.getInfusionTimeList().size() > 0) {
                    bean.setTypeItem(MessageInfusionBean.TYPE_2);
                    messageInfusionAdapter.replaceData(bean.getInfusionTimeList());
//                    ctv.setTabRedSpot("1", 1);
                }
            }
        });
        MessageApiManager.getSkinTestMessage(new CommonCallBack<MessageSkinBean>() {
            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onSuccess(MessageSkinBean bean, String type) {
                List<MessageSkinBean.SkinTimeListBean> skinTimeList = bean.getSkinTimeList();
                if (skinTimeList!= null && skinTimeList.size() > 0) {
                    f(R.id.ll_empty).setVisibility(View.GONE);
                    messageSkinAdapter.replaceData(skinTimeList);
                } else {
                    f(R.id.ll_empty).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 隐藏时,解除注册 onDestroy不再添加
            if (mReceiver != null && getActivity() != null) {
                getActivity().getApplicationContext().unregisterReceiver(mReceiver);
            }
        } else {
            initData();
            mfilter.addAction(Action.NEWMESSAGE_SERVICE);
            if (mReceiver != null && getActivity() != null) {
                getActivity().getApplicationContext().registerReceiver(mReceiver, mfilter);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() == null) {
            return;
        }
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        ctv = mContainerChild.findViewById(R.id.ctv);
        ctv.setTabText(new String[]{"皮试", "输液"}).setOnTabClickLisenter(this);
        RecyclerView rvMsgSkin = RecyclerViewHelper.get(getActivity(), R.id.rv_msg_skin);
        RecyclerView rvMsgInfusion = RecyclerViewHelper.get(getActivity(), R.id.rv_msg_infusion);
        recyclerViews = new RecyclerView[]{rvMsgSkin, rvMsgInfusion};
        messageInfusionAdapter = AdapterFactory.getMessageInfusion();
        messageSkinAdapter = AdapterFactory.getMessageSkin();
        rvMsgInfusion.setAdapter(messageInfusionAdapter);
        rvMsgSkin.setAdapter(messageSkinAdapter);
        messageInfusionAdapter.setOnItemChildClickListener(this);
        //默认显示第一个
        setSwitchRecycleView(0);
        initData();
        getActivity().getApplicationContext().registerReceiver(mReceiver, mfilter);
    }

    private void setSwitchRecycleView(int pst) {
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
            Bundle bundle = new Bundle();
            MessageInfusionBean.InfusionTimeListBean bean = (MessageInfusionBean.InfusionTimeListBean) adapter.getData().get(position);
            if (!TextUtils.isEmpty(bean.getPatRegNo())) {
                bundle.putString("id", bean.getPatRegNo());
                CustomPatView.startPatInfoFragment(getContext(), PatInfoFragment.class, bundle);
            }
        }
    }
}
