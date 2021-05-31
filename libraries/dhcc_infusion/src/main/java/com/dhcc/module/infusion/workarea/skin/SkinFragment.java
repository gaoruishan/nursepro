package com.dhcc.module.infusion.workarea.skin;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.UserUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.message.api.MessageApiManager;
import com.dhcc.module.infusion.utils.AdapterFactory;
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
    private SkinListBean mBean;
    private boolean onlyScanFlag;

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        bottomView = f(R.id.custom_bottom, CustomOrdExeBottomView.class);
        customPat = f(R.id.custom_pat, CustomPatView.class);
        showScanPatHand();
        //皮试时间差值
        long startTime = System.currentTimeMillis() - UserUtil.getSkinTimeOffset();
        customDate.setEndDateTime(System.currentTimeMillis())
                .setStartDateTime(startTime)
                .setOnDateSetListener(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        refreshSkinOrdList();
                    }
                }, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        refreshSkinOrdList();
                    }
                });
    }

    private void refreshSkinOrdList() {
        if (mBean != null && mBean.getPatInfo() != null) {
            getSkinList(mBean.getPatInfo().getPatRegNo());
        }
    }

    @Override
    protected void initDatas() {
        super.initDatas();

        skinAdapter = AdapterFactory.getSkinAdapter();
        skinAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(skinAdapter);
        bottomView.setNoSelectVisibility(skinAdapter.getSelectBean() == null);
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("皮试计时", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSkinCount();
            }
        }));
        beans.add(new ClickBean("置皮试结果", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exeOrderSkin();
            }
        }));
        bottomView.addBottomView(beans);
    }

    private void showSkinCount() {
        final SkinListBean.OrdListBean selectBean = skinAdapter.getSelectBean();
        if (selectBean == null) {
            return;
        }
        DialogFactory.showCountTime(mContext, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                SkinApiManager.skinTime(selectBean.getOeoriId(), (String) args[0], (String) args[1], new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        onFailThings(msg);
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        onSuccessThings(bean);
                        refreshSkinOrdList();
                    }
                });
            }
        });
    }

    private void exeOrderSkin() {
        final SkinListBean.OrdListBean selectBean = skinAdapter.getSelectBean();
        if (selectBean == null) {
            return;
        }
        DialogFactory.showSkinYinYangDialog(mContext, "置皮试结果", "", "", null, new CommDialog.CommClickListener() {
            @Override
            public void data(Object[] args) {
                MessageApiManager.setSkinTestResult(selectBean.getOeoriId(), (String) args[2], (String) args[0], (String) args[1], new CommonCallBack<CommResult>() {
                    @Override
                    public void onFail(String code, String msg) {
                        onFailThings(msg);
                    }

                    @Override
                    public void onSuccess(CommResult bean, String type) {
                        onSuccessThings(bean);
                        refreshSkinOrdList();
                    }
                });
            }
        });
    }

    @Override
    protected void getScanOrdList() {
        //第二次 扫瓶贴
        if (mBean != null) {
            checkScanInfo();
        } else {
            getSkinList(scanInfo);
        }

    }

    private void checkScanInfo() {
        if (scanInfo.contains("||")) {
            if (isContain()) {
                skinAdapter.setCurrentScanInfo(scanInfo);
                recyclerView.scrollToPosition(skinAdapter.getSelectBeanPosition());
                refreshBottomView();
            }
        }else {
            showToast(SCAN_LABEL);
        }
    }

    /**
     * 是否包含
     * @return
     */
    private boolean isContain() {
        boolean isContain = false;
        for (SkinListBean.OrdListBean bean : skinAdapter.getData()) {
            if (bean.getOeoriId().equals(scanInfo)) {
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            showToast(PAT_NO_ORD);
        }
        return isContain;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_skin;
    }

    private void refreshBottomView() {
        SkinListBean.OrdListBean selectBean = skinAdapter.getSelectBean();
        bottomView.setNoSelectVisibility(selectBean == null);
        if (selectBean != null) {
            skinAdapter.setHideSelectButton(onlyScanFlag);
            bottomView.setVisibility(View.VISIBLE);
            bottomView.setSelectText("已选择1个");
        }
    }

    private void getSkinList(String regNo) {
        SkinApiManager.getSkinList(regNo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), "", new CommonCallBack<SkinListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(SkinListBean bean, String type) {
                if (bean.getOrdList() != null && bean.getOrdList().size() > 0) {
                    mBean = bean;
                }
                hideScanView();
                skinAdapter.setNewData(bean.getOrdList());
                setCustomPatViewData(customPat, bean.getPatInfo());
                onlyScanFlag = "1".equals(bean.getOnlyScanFlag());
                boolean hideSelectButton = "1".equals(bean.getSkinFlag());
                skinAdapter.setHideSelectButton(hideSelectButton);
                bottomView.setVisibility(hideSelectButton?View.GONE:View.VISIBLE);
                refreshBottomView();
            }
        });
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
            refreshBottomView();
        }
    }
}
