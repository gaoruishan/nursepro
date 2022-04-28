package com.dhcc.module.nurse.custom.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.custom.CustomApiManager;
import com.dhcc.module.nurse.custom.CustomOrderAdapter;
import com.dhcc.module.nurse.custom.base.BaseCustomFragment;
import com.dhcc.module.nurse.custom.bean.CustomListData;
import com.dhcc.module.nurse.custom.bean.CustomScanInfo;
import com.dhcc.module.nurse.custom.bean.CustomUiConfigBean;
import com.dhcc.module.nurse.R;
import com.dhcc.res.custom.CustomBottomView;
import com.dhcc.res.custom.bean.ActionBarBean;
import com.dhcc.res.custom.bean.BottomViewBean;
import com.dhcc.res.custom.bean.DateTimeViewBean;
import com.dhcc.res.custom.bean.LeftSheetViewBean;
import com.dhcc.res.custom.bean.ListViewBean;
import com.dhcc.res.custom.bean.ScanViewBean;
import com.dhcc.res.custom.bean.TopTabViewBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.CustomSheetTabView;
import com.dhcc.res.infusion.bean.SheetListBean;
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
    private HashMap<String,String> mTempParam = new HashMap<>();
    private CustomOrderAdapter customOrderAdapter;

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
        RecyclerViewHelper.setDefaultRecyclerView(mContext,rv_list,0);
        customOrderAdapter = new CustomOrderAdapter(null);
        rv_list.setAdapter(customOrderAdapter);
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
        String method = mBean.getScanView().getMethod();
        HashMap<String, String> map = new HashMap<>();
        map.put(param, scanInfo);
        mTempParam.put(param, scanInfo);
        CustomApiManager.getScanOrdList(method, map, new CommonCallBack<CustomScanInfo>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(CustomScanInfo bean, String type) {
                custom_scan.setVisibility(View.GONE);
                //请求list数据
                getListData();
            }
        });
    }

    @Override
    protected void onUiConfigCallBack(CustomUiConfigBean bean) {
        mBean = bean;
        doScanView(bean.getScanView());
        doActionBar(bean.getActionBar());
        doTopTabView(bean.getTopTabView());
        doDateTimeView(custom_date1, bean.getDateTimeView1(), new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                Log.e(TAG,"(CustomFragment.java:112) "+result);
                getListData();
            }
        });
        doLeftSheetView(bean.getLeftSheetView());
        doListView(bean.getListView());
        doBottomView(bean.getBottomView());

        if (custom_scan.getVisibility() == View.GONE) {
            //请求list数据
            getListData();
        }
    }

    protected void getListData() {
        String method = mBean.getListView().getList().getMethod();
        CommDialog.showCommDialog(mContext, "请求参数:" + mTempParam.toString(), "", "", 0, null, true);
        CustomApiManager.getListData(method, mTempParam, new CommonCallBack<CustomListData>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CustomListData bean, String type) {
                if (mBean != null) {
                    customOrderAdapter.setListConfig(mBean.getListView().getList());
                }
                customOrderAdapter.setNewData(bean.getOrdList());
            }
        });
    }

    private void doBottomView(BottomViewBean bottomView) {
        custom_bottom.setVisibility(View.GONE);
        if (bottomView != null) {
            custom_bottom.setDataBean(bottomView);
        }
    }


    private void doListView(ListViewBean listView) {
        custom_date2.setVisibility(View.GONE);
        if (listView != null) {
            doDateTimeView(custom_date2, listView.getDateTimeView2(), new SimpleCallBack<String>() {
                @Override
                public void call(String result, int type) {
                    Log.e(TAG,"(CustomFragment.java:112) "+result);
                    getListData();
                }
            });
        }
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
                    mTempParam.put(leftSheetView.getParam(), selectCode);
                    getListData();
                }
            });

        }
    }

    private void doDateTimeView(CustomDateTimeView custom_date, DateTimeViewBean dateTimeView, SimpleCallBack<String> callBack) {
        custom_date.setVisibility(View.GONE);
        if (dateTimeView != null) {
            String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
            boolean contains = dateTimeView.getStart().contains(" ");
            custom_date.setShowTime(contains);
//            custom_date1.showOnlyOne();
            custom_date.setStartDateTime(TimeUtils.string2Millis(dateTimeView.getStart(),contains? YYYY_MM_DD_HH_MM:YYYY_MM_DD));
            mTempParam.put(dateTimeView.getParam(), custom_date.getStartDateTimeText() + "^" +custom_date.getEndDateTimeText());
            custom_date.setOnDateSetListener(new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    ToastUtils.showShort(custom_date.getStartDateTimeText());
                    mTempParam.put(dateTimeView.getParam(), custom_date.getStartDateTimeText() + "^" +custom_date.getEndDateTimeText());
                    if (callBack != null) {
                        callBack.call(custom_date.getStartDateTimeText(),1);
                    }
                }
            }, new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    ToastUtils.showShort(custom_date.getEndDateTimeText());
                    if (callBack != null) {
                        callBack.call(custom_date.getEndDateTimeText(),2);
                    }
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
                    mTempParam.put(topTabView.getParam(), selectCode);
                    getListData();
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

