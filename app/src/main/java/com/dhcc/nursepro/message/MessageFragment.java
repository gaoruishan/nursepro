package com.dhcc.nursepro.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.message.adapter.MessageAbnormalAdapter;
import com.dhcc.nursepro.message.adapter.MessageConsultationAdapter;
import com.dhcc.nursepro.message.adapter.MessageNewOrderAdapter;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;

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


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        hideToolbarNavigationIcon();

        initView(view);
        initAdapter();
        initData();

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
        newOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_message_rightmenu) {
                    MessageBean.NewOrdPatListBean newOrdPatListBean = (MessageBean.NewOrdPatListBean) adapter.getItem(position);
                    Toast.makeText(getActivity(), "" + newOrdPatListBean.getPatName(), Toast.LENGTH_SHORT).show();
                    newOrdPatList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        abnormalAdapter = new MessageAbnormalAdapter(new ArrayList<MessageBean.AbnormalPatListBean>());
        abnormalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_message_rightmenu) {
                    MessageBean.AbnormalPatListBean abnormalPatListBean = (MessageBean.AbnormalPatListBean) adapter.getItem(position);
                    Toast.makeText(getActivity(), "" + abnormalPatListBean.getPatName(), Toast.LENGTH_SHORT).show();
                    abnormalPatList.remove(position);
                    adapter.notifyDataSetChanged();

                }
            }
        });
        consultationAdapter = new MessageConsultationAdapter(new ArrayList<MessageBean.ConPatListBean>());
        consultationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_message_rightmenu) {
                    MessageBean.ConPatListBean conPatListBean = (MessageBean.ConPatListBean) adapter.getItem(position);
                    Toast.makeText(getActivity(), "" + conPatListBean.getPatName(), Toast.LENGTH_SHORT).show();
                    conPatList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        recyMessageNeworder.setAdapter(newOrderAdapter);
        recyMessageAbnormal.setAdapter(abnormalAdapter);
        recyMessageConsultation.setAdapter(consultationAdapter);
    }

    private void initData() {
        MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
            @Override
            public void onSuccess(MessageBean msgs) {
                newOrdPatList = msgs.getNewOrdPatList();
                abnormalPatList = msgs.getAbnormalPatList();
                conPatList = msgs.getConPatList();

                newOrderAdapter.setNewData(newOrdPatList);
                abnormalAdapter.setNewData(abnormalPatList);
                consultationAdapter.setNewData(conPatList);
            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

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
            default:
                break;

        }
    }
}
