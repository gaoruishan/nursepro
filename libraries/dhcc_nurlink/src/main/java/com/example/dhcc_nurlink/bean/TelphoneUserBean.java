package com.example.dhcc_nurlink.bean;

import com.base.commlibs.http.CommResult;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.dhcc_nurlink.bean
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:13: 58
 */
public class TelphoneUserBean extends CommResult {
    private List<UserBean> telListBean = new ArrayList<>();

    public List<UserBean> getTelListBean() {
        return telListBean;
    }

    public void setTelListBean(List<UserBean> telListBean) {
        this.telListBean = telListBean;
    }
}
