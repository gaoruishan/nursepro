package com.dhcc.module.infusion.login.windowpicker;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.LinkagePicker;

/**
 * com.dhcc.infusion.workarea
 * <p>
 * author Q
 * Date: 2019/3/19
 * Time:16:31
 */
public class WindowPicker extends LinkagePicker<Ward, Window, Void> {
//    private static final String[] ABBREVIATIONS = {
//            "京", "津", "冀", "晋", "蒙", "辽", "吉", "黑", "沪",
//            "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘",
//            "粤", "桂", "琼", "渝", "川", "贵", "云", "藏", "陕",
//            "甘", "青", "宁", "新"};
private static  String[] ABBREVIATIONS ;
private static Map map1 = new HashMap();
    private List<Ward> provinces = new ArrayList<>();

    public WindowPicker(Activity activity,List list,Map map) {
        super(activity, new WindowPicker.CarNumberDataProvider(list,map));

    }

    private static class CarNumberDataProvider implements Provider<Ward, Window, Void> {
        private List<Ward> provinces = new ArrayList<>();

        CarNumberDataProvider(List list,Map map) {
            ABBREVIATIONS = new String[list.size()];
            for (int i=0;i<list.size();i++){
                ABBREVIATIONS[i] = (String) list.get(i);
            }
            map1 = map;
            for (String abbreviation : ABBREVIATIONS) {
                this.provinces.add(new Ward(abbreviation));
            }
        }

        @Override
        public boolean isOnlyTwo() {
            return true;
        }

        @Override
        @NonNull
        public List<Ward> initFirstData() {
            return provinces;
        }

        @Override
        @NonNull
        public List<Window> linkageSecondData(int firstIndex) {
            return provinces.get(firstIndex).getSeconds(map1);
        }

        @Override
        @NonNull
        public List<Void> linkageThirdData(int firstIndex, int secondIndex) {
            return new ArrayList<>();
        }

    }

}