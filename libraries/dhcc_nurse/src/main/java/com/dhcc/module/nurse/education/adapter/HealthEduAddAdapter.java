package com.dhcc.module.nurse.education.adapter;

import android.support.annotation.Nullable;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.utils.SimpleCallBack;
import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduSubjectListBean;
import com.dhcc.res.infusion.CustomCheckView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-20/15:51
 * @email:grs0515@163.com
 */
public class HealthEduAddAdapter extends BaseQuickAdapter<EduSubjectListBean, BaseViewHolder> {

    public HealthEduAddAdapter(int layoutResId, @Nullable List<EduSubjectListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EduSubjectListBean item) {
        CustomCheckView customCheck = helper.getView(R.id.custom_check);
        customCheck.setShowText(item.getDesc());
        customCheck.setSelect(item.isSelect());
        customCheck.setOnSelectListener(new SimpleCallBack<Boolean>() {
            @Override
            public void call(Boolean result, int type) {
                item.setSelect(result);
                String id = item.getPid();
                boolean allSelect = false;
                for (EduSubjectListBean datum : getData()) {
                    if (datum.getPid().equals(id)) {
                        allSelect = datum.isSelect();
                    }
                }
                MessageEvent messageEvent = new MessageEvent(MessageEvent.MessageType.HEALTH_EDU_ADD_SELECT);
                messageEvent.setMessage(id);
                messageEvent.setSelect(allSelect);
                EventBus.getDefault().post(messageEvent);
            }
        });

        //处理主题隐藏
        if ("0".equals(item.getPid())) {
            ViewUtil.setItemVisibility(helper.getConvertView(), false);
        } else {
            ViewUtil.setItemVisibility(helper.getConvertView(), !item.isHide());
        }
    }
}
