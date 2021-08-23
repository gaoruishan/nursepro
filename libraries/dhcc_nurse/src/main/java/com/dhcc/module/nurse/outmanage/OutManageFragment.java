package com.dhcc.module.nurse.outmanage;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.outmanage.adapter.OutManageAdapter;
import com.dhcc.module.nurse.outmanage.bean.ConfigTraceBean;
import com.dhcc.module.nurse.outmanage.bean.OutManageBean;
import com.dhcc.module.nurse.outmanage.fragment.OutManageInputFragment;
import com.dhcc.module.nurse.outmanage.fragment.OutManageSeeFragment;
import com.dhcc.res.infusion.CustomSheetTabView;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 外出管理
 * @author:gaoruishan
 * @date:202021/8/16/10:51
 * @email:grs0515@163.com
 */
public class OutManageFragment extends BaseNurseFragment {
    private CustomSheetTabView customSheetTab;
    private RecyclerView rvList;
    private OutManageAdapter outManageAdapter;
    private OutManageBean mBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_out_manage;
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheetTab = f(R.id.custom_sheet_tab, CustomSheetTabView.class);
        rvList = f(R.id.rv_list, RecyclerView.class);

    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        if (mBean != null) {
            for (OutManageBean.PatInfoListBean patInfoListBean : mBean.getPatInfoList()) {
                //存在
                if (patInfoListBean.getRegNo().equals(scanInfo)) {
                    //1 外出
                    String type = "1".equals(patInfoListBean.getOutManageFlag()) ? OutManageBean.TYPE_IN : OutManageBean.TYPE_OUT;
                    startOutManageInputFragment(type, patInfoListBean);
                    return;
                }
            }
            ToastUtils.showShort("当前列表未找到此患者!");
        }
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("外出管理");

        List<SheetListBean> mSheetListBeanList = new ArrayList<>();
        mSheetListBeanList.add(new SheetListBean("all", "全区"));
        mSheetListBeanList.add(new SheetListBean("manage", "管辖"));
        customSheetTab.setDatas(mSheetListBeanList);
        customSheetTab.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 1) { //管辖
                    List<OutManageBean.PatInfoListBean> mList = new ArrayList<>();
                    for (OutManageBean.PatInfoListBean bean : mBean.getPatInfoList()) {
                        if ("1".equals(bean.getManageInBed())) {
                            mList.add(bean);
                        }
                    }
                    outManageAdapter.setNewData(mList);
                } else {
                    outManageAdapter.setNewData(mBean.getPatInfoList());
                }
            }
        });
        outManageAdapter = AdapterFactory.getOutManageAdapter();
        rvList.setAdapter(outManageAdapter);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvList, 0);
        outManageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OutManageBean.PatInfoListBean bean = outManageAdapter.getData().get(position);
                //出病区/回病区
                if (view.getId() == R.id.tv_input || view.getId() == R.id.tv_see) {
                    String type = view.getId() == R.id.tv_see ? OutManageBean.TYPE_IN :OutManageBean.TYPE_OUT ;

                    startOutManageInputFragment(type, bean);
                }
                //外出记录
                if (view.getId() == R.id.rl_parent) {
                    BundleData bundle = new BundleData();
                    bundle.setEpisodeId(bean.getEpisodeId());
                    startFragment(OutManageSeeFragment.class, bundle.build());
                }
            }
        });

        getOutManageList();
    }

    private void startOutManageInputFragment(String type, OutManageBean.PatInfoListBean bean) {
        BundleData bundle = new BundleData();
        bundle.setType(type)
                .setEpisodeId(bean.getEpisodeId())
                .setDesc(bean.getBedCode() + "    " + bean.getName())
                .setDateTime(bean.getOutDateTime()+","+bean.getInDateTime());
        startFragment(OutManageInputFragment.class, bundle.build());
    }

    private void getOutManageList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        OutManageApiManager.getOutManageList(new CommonCallBack<OutManageBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
                hideLoadingTip();
            }

            @Override
            public void onSuccess(OutManageBean bean, String type) {
                hideLoadingTip();
                mBean = bean;
                DataCache.setJsonList(bean.getConfigTrace(), ConfigTraceBean.class.getSimpleName());
                outManageAdapter.setNewData(bean.getPatInfoList());
            }
        });
    }
}
