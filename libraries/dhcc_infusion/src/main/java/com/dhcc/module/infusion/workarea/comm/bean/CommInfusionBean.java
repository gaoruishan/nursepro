package com.dhcc.module.infusion.workarea.comm.bean;

import android.text.TextUtils;

import com.base.commlibs.http.CommResult;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;
import com.dhcc.module.infusion.workarea.puncture.PunctureFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 输液公共类
 * @author:gaoruishan
 * @date:202020-01-08/11:15
 * @email:grs0515@163.com
 */
public class CommInfusionBean extends CommResult {

    protected String CurOeoreId;
    protected String CurRegNo;
    protected List<OrdListBean> ordList;
    protected Map wayList;

    /**
     * 是否有通道
     * @return
     */
    public boolean getIsCurrentWayNo() {
        if (ordList != null) {
            for (OrdListBean ordListBean : ordList) {
                if (ordListBean.getOeoreId().equals(CurOeoreId)) {
                    return !TextUtils.isEmpty(ordListBean.getWayNo());
                }
            }
        }
        return false;
    }

    /**
     * 获取当前状态
     * @param state
     * @return
     */
    public boolean getCurrentOrdState(String state) {
        if (ordList != null) {
            for (OrdListBean ordListBean : ordList) {
                if (ordListBean.getOeoreId().equals(CurOeoreId)) {
                    return ordListBean.getOrdState().equals(state);
                }
            }
        }
        return false;
    }

    /**
     * 获取通道
     * @return
     */
    public List<String> getWayListString() {
        List<String> listChannel = new ArrayList<>();
        if (wayList != null) {
            Set set = wayList.keySet();
            for (Object o : set) {
                listChannel.add(PunctureFragment.STR_WAY_NO + o);
            }
        }
        return listChannel;
    }

}
