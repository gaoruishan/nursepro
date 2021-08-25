package com.dhcc.module.nurse.outmanage.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.adapter.AccompanySeeAdapter;
import com.dhcc.module.nurse.accompany.adapter.AccompanySheetAdapter;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.outmanage.OutManageApiManager;
import com.dhcc.module.nurse.outmanage.bean.OutManageBean;
import com.dhcc.module.nurse.outmanage.bean.OutManageSubBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.Map;

/**
 * 外出记录
 * @author:gaoruishan
 * @date:202021/8/19/08:54
 * @email:grs0515@163.com
 */
public class OutManageSeeFragment extends BaseNurseFragment {

    private CustomDateTimeView customDate;
    private RecyclerView rvSheet;
    private RecyclerView rvList;
    private AccompanySheetAdapter sheetAdapter;
    private AccompanySeeAdapter seeAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_outmanage_see;
    }

    @Override
    protected void initViews() {
        super.initViews();
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        rvSheet = f(R.id.rv_sheet, RecyclerView.class);
        rvList = f(R.id.rv_list, RecyclerView.class);
        episodeId = new BundleData(bundle).getEpisodeId();
        OnDateSetListener onDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getOutManageListSub();
            }
        };
        customDate.setOnDateSetListener(onDateSetListener,onDateSetListener);
    }


    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("外出记录");
        sheetAdapter = AdapterFactory.getAccompanySheetAdapter();
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvSheet, 0, LinearLayoutManager.HORIZONTAL);
        rvSheet.setAdapter(sheetAdapter);

        seeAdapter = AdapterFactory.getAccompanySeeAdapter();
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, 0, LinearLayoutManager.VERTICAL);
        rvList.setAdapter(seeAdapter);
        seeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Map map = seeAdapter.getData().get(position);
                //编辑
                if (view.getId() == R.id.ll_parent) {
                    BundleData bundle = new BundleData();
                    bundle.setType(OutManageBean.TYPE_EDIT)
                            .setDesc(map.get("bedCode") + "    " +map.get("name") )
                            .setUser(map.get("entourage")+"")
                            .setId(map.get("id")+"")
                            .setEpisodeId(episodeId+"")
                            .setDateTime(map.get("outRecordDateTime")+","+map.get("returnRecordDateTime"));
                    startFragment(OutManageInputFragment.class, bundle.build());
                }
            }
        });

        //请求数据
        getOutManageListSub();
    }

    private void getOutManageListSub() {
        OutManageApiManager.getOutManageListSub(episodeId, customDate.getStartDateText(), customDate.getEndDateText(), new CommonCallBack<OutManageSubBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(OutManageSubBean bean, String type) {
                sheetAdapter.setNewData(bean.getConfigList());
                seeAdapter.setConfigTemp(bean.getConfigList());
                seeAdapter.setNewData(bean.getOutManageList());
            }
        });
    }

}
