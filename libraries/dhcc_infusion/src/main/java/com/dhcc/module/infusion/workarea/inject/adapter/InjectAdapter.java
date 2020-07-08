package com.dhcc.module.infusion.workarea.inject.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.inject.InjectListBean;

import java.util.List;

/**
 * 注射
 * @author:gaoruishan
 * @date:202019-11-14/15:24
 * @email:grs0515@163.com
 */
public class InjectAdapter extends BaseBloodQuickAdapter<BloodOrdListBean, BaseViewHolder> {

    public InjectAdapter(int layoutResId, @Nullable List<BloodOrdListBean> data) {
        super(layoutResId, data);
    }

    public void setInitData(InjectListBean bean) {
        if (bean.getOrdList() != null) {
            setInitData(bean.getOrdList());
        }
    }

    public void setInitData(List<BloodOrdListBean> data ) {
        if (data == null || data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setSelect("0");
        }
        setIfSelShow(true);
        setNewData(data);
    }

    public void refreshData(InjectListBean injectListBean, int position) {
        List<BloodOrdListBean> ordList = injectListBean.getOrdList();
        for (int i = 0; i < ordList.size(); i++) {
            BloodOrdListBean bloodOrdListBean = ordList.get(i);
            if (position == i) { //勾选/取消勾选
                bloodOrdListBean.setSelect("1".equals(bloodOrdListBean.getSelect()) ? "0" : "1");
            }else {
                bloodOrdListBean.setSelect("0");
            }
        }
        replaceData(ordList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodOrdListBean item) {
        setCommData(helper, item);
        setInjectDosingData(helper, item);
        helper.setGone(R.id.tv_lab_no, !TextUtils.isEmpty(item.getLabNo()))
                .setText(R.id.tv_operate, item.getPhcinDesc());

        setInjectChildAdapter(helper, item.getArcimDescList());
    }

}
