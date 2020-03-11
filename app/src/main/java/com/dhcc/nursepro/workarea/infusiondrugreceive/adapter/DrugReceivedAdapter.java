package com.dhcc.nursepro.workarea.infusiondrugreceive.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.base.commlibs.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.IfOrdListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive.adapter
 * <p>
 * author Q
 * Date: 2020/3/10
 * Time:9:04
 */
public class DrugReceivedAdapter extends BaseQuickAdapter<IfOrdListBean.OrdListBean, BaseViewHolder> {

    public DrugReceivedAdapter(@Nullable List<IfOrdListBean.OrdListBean> data) {
        super(R.layout.item_drugreceived, data);
    }
    @Override
    protected void convert(BaseViewHolder helper,IfOrdListBean.OrdListBean item) {
        helper.setText(R.id.tv_patinfo,item.getOeoreGroup().size()>0?item.getOeoreGroup().get(0).getBedCode()+" "+item.getOeoreGroup().get(0).getPatName():"")
                .setText(R.id.tv_sttdatetime,item.getOeoreGroup().size()>0?item.getOeoreGroup().get(0).getSttDateTime():"");
        RecyclerView rec = helper.getView(R.id.recy_iford);

        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(mContext));
        DrugReceivedChildAdapter drugReceivedChildAdapter = new DrugReceivedChildAdapter(new ArrayList<>());
        rec.setAdapter(drugReceivedChildAdapter);
        drugReceivedChildAdapter.setNewData(item.getOeoreGroup());
    }
}