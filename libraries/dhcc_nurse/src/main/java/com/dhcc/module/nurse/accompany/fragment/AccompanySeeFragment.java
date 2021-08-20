package com.dhcc.module.nurse.accompany.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.AccompanyApiManager;
import com.dhcc.module.nurse.accompany.adapter.AccompanySeeAdapter;
import com.dhcc.module.nurse.accompany.adapter.AccompanySheetAdapter;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;
import com.dhcc.module.nurse.accompany.bean.AccompanyInputBean;
import com.dhcc.module.nurse.accompany.bean.ConfigSheetBean;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 体温查看
 * @author:gaoruishan
 * @date:202021/8/16/14:20
 * @email:grs0515@163.com
 */
public class AccompanySeeFragment extends BaseNurseFragment {
    public static final String INT_No = "index";
    private String ncparRowIDs;
    private List<AccompanyConfigBean> configTEMPList;
    private CustomDateTimeView customDate;
    private RecyclerView rvList;
    private RecyclerView rvSheet;
    private AccompanySeeAdapter seeAdapter;

    @Override
    protected void initViews() {
        super.initViews();
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        rvSheet = f(R.id.rv_sheet, RecyclerView.class);
        rvList = f(R.id.rv_list, RecyclerView.class);
        ncparRowIDs = new BundleData(bundle).getNCPARRowIDs();
        configTEMPList = new BundleData(bundle).getConfigTEMPList();
        //修改为序号
        if (configTEMPList != null && configTEMPList.size() > 0) {
            configTEMPList.get(0).setTitle("序号");
        }
        OnDateSetListener onDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNCPAccompanySub();
            }
        };
        customDate.setOnDateSetListener(onDateSetListener,onDateSetListener);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_accompany_see;
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        AccompanySheetAdapter sheetAdapter = AdapterFactory.getAccompanySheetAdapter();
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvSheet, 0, LinearLayoutManager.HORIZONTAL);
        rvSheet.setAdapter(sheetAdapter);
        //父类 需要转一下
        List<ConfigSheetBean> mConfigSheetBeanList = new ArrayList<>();
        for (AccompanyConfigBean bean : configTEMPList) {
            mConfigSheetBeanList.add(new ConfigSheetBean(bean.getField(), bean.getTitle()));
        }
        sheetAdapter.setNewData(mConfigSheetBeanList);

        seeAdapter = AdapterFactory.getAccompanySeeAdapter();
        seeAdapter.setConfigTemp(mConfigSheetBeanList);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, 0, LinearLayoutManager.VERTICAL);
        rvList.setAdapter(seeAdapter);

        //请求数据
        getNCPAccompanySub();

    }

    private void getNCPAccompanySub() {
        AccompanyApiManager.getNCPAccompanySub(ncparRowIDs, customDate.getStartDateText(), customDate.getEndDateText(), new CommonCallBack<AccompanyInputBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(AccompanyInputBean bean, String type) {
                if (bean.getAccompanySub() != null && bean.getAccompanySub().size() > 0) {
                    setToolbarCenterTitle("" + bean.getAccompanySub().get(0).get("NCPAInfo1"));
                }
                List<Map> accompanySub = bean.getAccompanySub();
                for (int i = 0; i < accompanySub.size(); i++) {
                    accompanySub.get(i).put(INT_No, i + 1);
                }
                seeAdapter.setNewData(accompanySub);
            }
        });
    }
}
