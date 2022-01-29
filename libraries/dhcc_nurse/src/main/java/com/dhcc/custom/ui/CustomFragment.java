package com.dhcc.custom.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.custom.CustomApiManager;
import com.dhcc.custom.base.BaseCustomFragment;
import com.dhcc.custom.bean.CustomScanInfo;
import com.dhcc.custom.bean.CustomUiConfigBean;
import com.dhcc.res.custom.bean.ActionBarBean;
import com.dhcc.res.custom.bean.BottomViewBean;
import com.dhcc.res.custom.bean.DateTimeViewBean;
import com.dhcc.res.custom.bean.LeftSheetViewBean;
import com.dhcc.res.custom.bean.ListViewBean;
import com.dhcc.res.custom.bean.ScanViewBean;
import com.dhcc.res.custom.bean.TopTabViewBean;
import com.dhcc.module.nurse.R;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.CustomSheetTabView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.custom.CustomBottomView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.HashMap;
import java.util.List;

/**
 * 自定义模块
 * @author:gaoruishan
 * @date:202022/1/27/16:15
 * @email:grs0515@163.com
 */
public class CustomFragment extends BaseCustomFragment {

    private CustomSheetTabView custom_top_tab;
    private CustomDateTimeView custom_date1,custom_date2;
    private CustomSheetListView custom_left_sheet;
    private RecyclerView rv_list;
    private CustomBottomView custom_bottom;
    private CustomScanView custom_scan;
    private CustomUiConfigBean mBean;

    @Override
    protected void initDatas() {
        getUiConfig();
    }

    @Override
    protected void initViews() {
        custom_top_tab = f(R.id.custom_top_tab, CustomSheetTabView.class);
        custom_date1 = f(R.id.custom_date1, CustomDateTimeView.class);
        custom_date2 = f(R.id.custom_date2, CustomDateTimeView.class);
        custom_left_sheet = f(R.id.custom_left_sheet, CustomSheetListView.class);
        custom_bottom = f(R.id.custom_bottom, CustomBottomView.class);
        custom_scan = f(R.id.custom_scan, CustomScanView.class);
        rv_list = f(R.id.rv_list, RecyclerView.class);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_custom;
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        if (custom_scan.getVisibility() == View.VISIBLE) {
            requstScanInfo();
        }
    }

    private void requstScanInfo() {
        String param = mBean.getScanView().getParam();
        String action = mBean.getScanView().getAction();
        HashMap<String, String> map = new HashMap<>();
        map.put(param, scanInfo);
        CustomApiManager.getScanOrdList(action, map, new CommonCallBack<CustomScanInfo>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(CustomScanInfo bean, String type) {
                custom_scan.setVisibility(View.GONE);
                ToastUtils.showShort(bean.getMsg()+" barCodeType="+bean.getBarCodeType());
            }
        });
    }

    @Override
    protected void onUiConfigCallBack(CustomUiConfigBean bean) {
        mBean = bean;
        doScanView(bean.getScanView());
        doActionBar(bean.getActionBar());
        doTopTabView(bean.getTopTabView());
        doDateTimeView(bean.getDateTimeView());
        doLeftSheetView(bean.getLeftSheetView());
        doListView(bean.getListView());
        doBottomView(bean.getBottomView());
    }

    private void doBottomView(BottomViewBean bottomView) {
        custom_bottom.setVisibility(View.GONE);
        if (bottomView != null) {
            custom_bottom.setDataBean(bottomView);
        }
    }


    private void doListView(ListViewBean listView) {

    }


    private void doLeftSheetView(LeftSheetViewBean leftSheetView) {
        custom_left_sheet.setVisibility(View.GONE);
        if (leftSheetView != null) {
            List<SheetListBean> list= BeanUtils.getLeftSheetList(leftSheetView);
            custom_left_sheet.setDatas(list);
            custom_left_sheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String selectCode = list.get(position).getCode();
                    ToastUtils.showShort(selectCode);
                }
            });

        }
    }

    private void doDateTimeView(DateTimeViewBean dateTimeView) {
        custom_date1.setVisibility(View.GONE);
        if (dateTimeView != null) {
            String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
            boolean contains = dateTimeView.getStart().contains(" ");
            custom_date1.setShowTime(contains);
//            custom_date1.showOnlyOne();
            custom_date1.setStartDateTime(TimeUtils.string2Millis(dateTimeView.getStart(),contains? YYYY_MM_DD_HH_MM:YYYY_MM_DD));
            custom_date1.setOnDateSetListener(new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    ToastUtils.showShort(custom_date1.getStartDateTimeText());
                }
            }, new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    ToastUtils.showShort(custom_date1.getEndDateTimeText());
                }
            });
        }
    }

    private void doTopTabView(TopTabViewBean topTabView) {
        custom_top_tab.setVisibility(View.GONE);
        if (topTabView != null) {
            List<SheetListBean> list= BeanUtils.getTopTabList(topTabView);
            custom_top_tab.setDatas(list);
            custom_top_tab.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String selectCode = list.get(position).getCode();
                    ToastUtils.showShort(selectCode);
//                    List<CustomOrdItem> ordItemList = getCustomOrdItems(userTypeListList);
//                    CustomAdapterManager.setCustomOrdLayoutData(mContext, rv_list, ordItemList, null);
                }
            });
        }
    }

    private void doActionBar(ActionBarBean actionBar) {
        if (actionBar != null) {
            setToolbarCenterTitle(actionBar.getTitle());
            if(!TextUtils.isEmpty(actionBar.getRightText1())){
            	addToolBarRightTextView(actionBar.getRightText1(),R.color.white);
            }
        }
    }
    private void doScanView(ScanViewBean scanView) {
        custom_scan.setVisibility(View.GONE);
        if (scanView != null) {
            custom_scan.setVisibility(View.VISIBLE);
            if ("label".equals(scanView.getIcon())) {
                custom_scan.setIcon(R.drawable.dhcc_icon_scan2);
            }
            custom_scan.setTitle(scanView.getTitle()).setWarning(scanView.getContent());
        }
    }
}

