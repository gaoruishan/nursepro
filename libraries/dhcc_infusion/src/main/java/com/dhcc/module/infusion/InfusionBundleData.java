package com.dhcc.module.infusion;

import android.os.Bundle;

import com.base.commlibs.comm.BaseBundleData;
import com.dhcc.module.infusion.setting.bean.TypeListBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/3/8/10:06
 * @email:grs0515@163.com
 */
public class InfusionBundleData extends BaseBundleData {
    //List 需要转一下
    public String typeList;
    public String userTypeList;

    public InfusionBundleData() {
    }

    public InfusionBundleData(Bundle bundle) {
        super(bundle);
    }

    public List<TypeListBean> getTypeListList() {
        if (bundle != null) {
            String key = "typeList";
            return getTypeTokenListBean(key, TypeListBean.class);
        }
        return new ArrayList<>();
    }

    public InfusionBundleData setTypeList(List<TypeListBean> typeList) {
        this.typeList = new Gson().toJson(typeList);
        return this;
    }

    public List<TypeListBean> getUserTypeListList() {
        if (bundle != null) {
            String key = "userTypeList";
            return getTypeTokenListBean(key, TypeListBean.class);
        }
        return new ArrayList<>();
    }

    public InfusionBundleData setUserTypeList(List<TypeListBean> userTypeList) {
        this.userTypeList = new Gson().toJson(userTypeList);
        return this;
    }
}
