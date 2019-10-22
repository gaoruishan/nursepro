package com.dhcc.res.nurse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.dhcc.res.nurse.bean.WardBean;

import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.LinkagePicker;

/**
 * 封装自定义选择器
 * @author:gaoruishan
 * @date:202019-10-21/17:16
 * @email:grs0515@163.com
 */
public class CustomSelectPicker {

    /**
     * 设置登录科室
     * @param activity
     * @param bean
     * @param listener
     */
    public static void onWardPicker(Activity activity, WardBean bean, LinkagePicker.OnStringPickListener listener) {
        onLinkagePicker(activity, bean.getFirstList(), bean.getFirstIndex(),bean.getSecondIndex(), bean.getFirstLabel(), bean.getSecondLabel(), bean.getListMap(), listener);
    }

    /**
     * 二级联动
     * @param activity
     * @param firstList
     * @param listMap
     * @param listener
     */
    public static void onLinkagePicker(Activity activity, final List<String> firstList, int firstIndex, int secondIndex, String firstLabel, String secondLabel, final Map<String, List<String>> listMap, LinkagePicker.OnStringPickListener listener) {
        if (firstList == null || listMap == null) {
            return;
        }
        if (firstList.size() != listMap.size()) {
            return;
        }
        //联动选择器的更多用法，可参见AddressPicker和CarNumberPicker
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @NonNull
            @Override
            public List<String> provideFirstData() {
                return firstList;
            }

            @NonNull
            @Override
            public List<String> provideSecondData(int firstIndex) {
                String key = firstList.get(firstIndex);
                return listMap.get(key);
            }

            @Nullable
            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(activity, provider);
        picker.setCycleDisable(true);
        //设置标签
        if(!TextUtils.isEmpty(firstLabel)&&!TextUtils.isEmpty(secondLabel)){
            picker.setLabel(firstLabel, secondLabel);
        }
        picker.setSelectedIndex(firstIndex, secondIndex);
        picker.setOnStringPickListener(listener);
        picker.show();
    }
}
