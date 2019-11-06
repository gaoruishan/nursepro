package com.dhcc.module.infusion.workarea.skin;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.DialogFactory;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.skin.adapter.SkinAdapter;
import com.dhcc.module.infusion.workarea.skin.api.SkinApiManager;
import com.dhcc.module.infusion.workarea.skin.bean.SkinListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomPatView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 皮试
 * @author:gaoruishan
 * @date:202019-11-04/14:59
 * @email:grs0515@163.com
 */
public class SkinFragment extends BaseInfusionFragment implements BaseQuickAdapter.OnItemChildClickListener {

    private RecyclerView recyclerView;
    private CustomDateTimeView customDate;
    private CustomOrdExeBottomView bottomView;
    private SkinAdapter skinAdapter;
    private CustomPatView customPat;


    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("皮试");
        skinAdapter = new SkinAdapter(null);
        skinAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(skinAdapter);
        bottomView.setNoSelectVisibility(getSelectBean() == null);
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("皮试计时", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSkinCount();
            }
        }));
        beans.add(new ClickBean("执行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exeOrderSkin();
            }
        }));
        bottomView.addBottomView(beans);
    }

    private void showSkinCount() {
        DialogFactory.showCountTime(mContext, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                String oeoreId = "";
                SkinApiManager.skinTime(oeoreId, (String) args[0], (String) args[1], new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {

                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        DialogFactory.showCommDialog(getActivity(), bean.getMsg(), null, 0, null, true);
                        getOrdList();//刷新
                    }
                });
            }
        });
    }

    private void exeOrderSkin() {
        SkinListBean.OrdListBean selectBean = getSelectBean();

    }

    private void getOrdList() {
        SkinApiManager.getSkinList(scanInfo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), new CommonCallBack<SkinListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(SkinListBean bean, String type) {
                helper.setVisible(R.id.custom_scan, false);
                skinAdapter.setNewData(bean.getOrdList());
                if (bean.getPatInfo() != null) {
                    customPat.setAge(bean.getPatInfo().getPatAge()).setPatName(bean.getPatInfo().getPatName())
                            .setRegNo(bean.getPatInfo().getPatRegNo()).setPatSex(bean.getPatInfo().getPatSex());
                }
            }
        });
    }

    private SkinListBean.OrdListBean getSelectBean() {
        for (SkinListBean.OrdListBean bean : skinAdapter.getData()) {
            if (bean.isSelect()) {
                return bean;
            }
        }
        return null;
    }

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        bottomView = f(R.id.custom_bottom, CustomOrdExeBottomView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(System.currentTimeMillis())
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        getOrdList();
                    }
                });
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (scanInfo != null) {
            getOrdList();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_skin;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof SkinAdapter) {
            List<SkinListBean.OrdListBean> data = skinAdapter.getData();
            for (int i = 0; i < data.size(); i++) {
                if (i == position) {
                    boolean select = data.get(i).isSelect();
                    data.get(i).setSelect(!select);
                } else {
                    data.get(i).setSelect(false);
                }
            }
            skinAdapter.notifyDataSetChanged();
            SkinListBean.OrdListBean selectBean = getSelectBean();
            bottomView.setNoSelectVisibility(selectBean == null);
            if (selectBean != null) {
                bottomView.setSelectText("已选择1个");
            }
        }
    }
}
