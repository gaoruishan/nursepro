package com.dhcc.module.nurse.custom.ui;

import com.dhcc.res.custom.bean.LeftSheetViewBean;
import com.dhcc.res.custom.bean.TopTabViewBean;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202022/1/28/15:59
 * @email:grs0515@163.com
 */
public class BeanUtils {

    public static List<SheetListBean> getTopTabList(TopTabViewBean topTabView) {
        List<SheetListBean> list = new ArrayList<>();
        for (TopTabViewBean.ListBean listBean : topTabView.getList()) {
            list.add(new SheetListBean(listBean.getCode(),listBean.getTitle()));
        }
        return list;
    }

    public static List<SheetListBean> getLeftSheetList(LeftSheetViewBean leftSheetView) {
        List<SheetListBean> list = new ArrayList<>();
        for (LeftSheetViewBean.ListBean listBean : leftSheetView.getList()) {
            list.add(new SheetListBean(listBean.getCode(),listBean.getTitle()));
        }
        return list;
    }
}
