package com.dhcc.nursepro.workarea.workareaadapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.view.WebActivity;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.Activity.MainActivity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.List;

public class WorkAreaAdapter extends BaseQuickAdapter<MainConfigBean.MainListBean, BaseViewHolder> {

    public WorkAreaAdapter(@Nullable List<MainConfigBean.MainListBean> data) {
        super(R.layout.item_workmenu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainConfigBean.MainListBean item) {
        helper.setText(R.id.tv_workmenu, item.getMenuName());
        if (item.getMenuName().equals("old")){
            helper.setGone(R.id.tv_workmenu,false);
        }else {
            helper.setGone(R.id.tv_workmenu,true);
        }

        RecyclerView recyclerView = helper.getView(R.id.recy_worksub);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));

        WorkAreaSubAdapter subAdapter = new WorkAreaSubAdapter(item.getMainSubList());

        subAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainConfigBean.MainListBean.MainSubListBean mainSubListBean = (MainConfigBean.MainListBean.MainSubListBean) adapter.getItem(position);

                //兼容web
                if (!StringUtils.isEmpty(mainSubListBean.getModuleUrl())) {
                    WebActivity.start(mContext, BaseWebServiceUtils.getServiceUrl(mainSubListBean.getModuleUrl()));
                    return;
                }
                if (StringUtils.isEmpty(mainSubListBean.getFragmentClassName())) {
                    Toast.makeText(mContext, "该功能暂未开发", Toast.LENGTH_SHORT).show();
                } else {
                    Class<? extends BaseFragment> OrderExecuteFragmentClass = null;
                    try {
                        OrderExecuteFragmentClass = (Class<? extends BaseFragment>) Class.forName(mainSubListBean.getFragmentClassName());
                        ((MainActivity) mContext).startFragment(OrderExecuteFragmentClass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        recyclerView.setAdapter(subAdapter);
    }
}