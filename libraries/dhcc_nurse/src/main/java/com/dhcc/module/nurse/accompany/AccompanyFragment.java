package com.dhcc.module.nurse.accompany;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.adapter.AccompanyAdapter;
import com.dhcc.module.nurse.accompany.bean.AccompanyBean;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;
import com.dhcc.module.nurse.accompany.fragment.AccompanyInputFragment;
import com.dhcc.module.nurse.accompany.fragment.AccompanySeeFragment;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.res.infusion.CustomSheetTabView;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 陪护
 * @author:gaoruishan
 * @date:202021/8/16/10:54
 * @email:grs0515@163.com
 */
public class AccompanyFragment extends BaseNurseFragment {

    private CustomSheetTabView customSheetTab;
    private RecyclerView rvList;
    private AccompanyAdapter accompanyAdapter;
    private List<AccompanyConfigBean> configTEMP;
    private List<AccompanyBean.AccompanyListBean> accompanyList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_accompany;
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheetTab = f(R.id.custom_sheet_tab, CustomSheetTabView.class);
        rvList = f(R.id.rv_list, RecyclerView.class);


    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("陪护人体温记录");

        List<SheetListBean> mSheetListBeanList = new ArrayList<>();
        mSheetListBeanList.add(new SheetListBean("all", "全区"));
        mSheetListBeanList.add(new SheetListBean("manage", "管辖"));
        customSheetTab.setDatas(mSheetListBeanList);
        customSheetTab.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "(AccompanyFragment.java:51) " + position);
                if (position == 1) {
                    List<AccompanyBean.AccompanyListBean> mList = new ArrayList<>();
                    for (AccompanyBean.AccompanyListBean accompanyListBean : accompanyList) {
                        if ("1".equals(accompanyListBean.getManageInBed())) {
                            mList.add(accompanyListBean);
                        }
                    }
                    accompanyAdapter.setNewData(mList);
                } else {
                    accompanyAdapter.setNewData(accompanyList);
                }
            }
        });
        accompanyAdapter = AdapterFactory.getAccompanyAdapter();
        rvList.setAdapter(accompanyAdapter);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, 0);
        accompanyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AccompanyBean.AccompanyListBean bean = accompanyAdapter.getData().get(position);
                //录入
                if (view.getId() == R.id.tv_input) {
                    BundleData bundle = new BundleData();
                    bundle.setPosition(position + "")
                            .setAccompanyList(accompanyAdapter.getData())
                            .setConfigTEMPList(configTEMP);
                    startFragment(AccompanyInputFragment.class, bundle.build());
                }
                //查看
                if (view.getId() == R.id.tv_see) {
                    BundleData bundle = new BundleData();
                    bundle.setNCPARRowIDs(bean.getNCPARRowID())
                            .setConfigTEMPList(configTEMP);
                    startFragment(AccompanySeeFragment.class, bundle.build());
                }
            }
        });

        //获取数据
        AccompanyApiManager.getNCPAccompanyList("", "", new CommonCallBack<AccompanyBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(AccompanyBean bean, String type) {
                configTEMP = bean.getConfigTEMP();
                accompanyList = bean.getAccompanyList();
                //序号
                for (int i = 0; i < accompanyList.size(); i++) {
                    String index = "" + (i + 1);
                    if (i < 9) {
                        index = "0" + (i + 1);
                    }
                    accompanyList.get(i).setBedCode("" + index);
                }
                accompanyAdapter.setNewData(accompanyList);
            }
        });
    }
}
