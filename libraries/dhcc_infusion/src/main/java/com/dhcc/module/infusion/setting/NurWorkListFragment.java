package com.dhcc.module.infusion.setting;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.InfusionBundleData;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.setting.bean.TypeListBean;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.res.infusion.CustomSheetTabView;
import com.dhcc.res.infusion.CustomTagSelectLayout;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.item.CustomAdapterManager;
import com.dhcc.res.item.bean.CustomOrdItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/3/8/09:42
 * @email:grs0515@163.com
 */
public class NurWorkListFragment extends BaseInfusionFragment {

    private RecyclerView rv_list;
    private CustomSheetTabView customSheetTag;
    private String selectCode;

    @Override
    protected void addGlobalView() {

    }

    @Override
    protected void addHandInputToToolbarRight() {
    }

    @Override
    protected void getScanOrdList() {

    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheetTag = f(R.id.custom_sheet_tag, CustomSheetTabView.class);
        rv_list = f(R.id.rv_list, RecyclerView.class);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("护士工作量");
        List<TypeListBean> typeListList = new InfusionBundleData(bundle).getTypeListList();
        List<TypeListBean> userTypeListList = new InfusionBundleData(bundle).getUserTypeListList();
        List<SheetListBean> list = new ArrayList<>();
        for (TypeListBean typeListBean : typeListList) {
            list.add(new SheetListBean(typeListBean.getWorkType(),typeListBean.getWorkTypeDesc()));
        }

        customSheetTag.setDatas(list);
        customSheetTag.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectCode = list.get(position).getCode();
                List<CustomOrdItem> ordItemList = getCustomOrdItems(userTypeListList);
                CustomAdapterManager.setCustomOrdLayoutData(mContext, rv_list, ordItemList, null);
            }
        });
        selectCode = list.get(0).getCode();
        List<CustomOrdItem> ordItemList = getCustomOrdItems(userTypeListList);
        CustomAdapterManager.setCustomOrdLayoutData(mContext, rv_list, ordItemList, null);
    }

    private List<CustomOrdItem> getCustomOrdItems(List<TypeListBean> userTypeListList) {
        List<CustomOrdItem> ordItemList = new ArrayList<>();
        for (TypeListBean bean : userTypeListList) {
            if (bean.getWorkType().equalsIgnoreCase(selectCode)) {
                CustomOrdItem b = new CustomOrdItem();
                b.setTvTag(bean.getWorkTypeDesc());
                List<CustomOrdItem.OeoreGroupBean> l = new ArrayList<>();
                l.add(new CustomOrdItem.OeoreGroupBean(bean.getWorkOrdDesc(),bean.getWorkDateTime(),""));
                b.setList(l);
                ordItemList.add(b);
            }
        }
        return ordItemList;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_work_list;
    }
}
