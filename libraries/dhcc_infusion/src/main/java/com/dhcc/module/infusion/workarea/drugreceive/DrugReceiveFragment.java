package com.dhcc.module.infusion.workarea.drugreceive;

import android.support.v7.widget.RecyclerView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.AdapterFactory;
import com.dhcc.module.infusion.workarea.comm.BaseInfusionFragment;
import com.dhcc.module.infusion.workarea.drugreceive.adapter.DrugReceivedAdapter;
import com.dhcc.res.infusion.CustomDateTimeView;

/**
 * 药品接受
 * @author:gaoruishan
 * @date:202021/1/5/16:26
 * @email:grs0515@163.com
 */
public class DrugReceiveFragment extends BaseInfusionFragment {

    private RecyclerView rvList;
    private CustomDateTimeView customDateTime;
    private DrugReceivedAdapter drugReceiveAdapter;

    @Override
    protected void initDatas() {
        super.initDatas();
        getIFOrdListByBarCode();
    }

    @Override
    protected void addHandInputToToolbarRight() {
    }

    private void getIFOrdListByBarCode() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        DrugReceiveApiManager.getIFOrdListByBarCode(customDateTime.getStartDateTimeText(), customDateTime.getEndDateTimeText(), new CommonCallBack<IfOrdListBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
                hideLoadingTip();
            }

            @Override
            public void onSuccess(IfOrdListBean bean, String type) {
                hideLoadingTip();
                drugReceiveAdapter.setNewData(bean.getOrdList());
            }
        });

    }

    @Override
    protected void initViews() {
        super.initViews();
        rvList = RecyclerViewHelper.get(mContext, R.id.rv_list);
        customDateTime = f(R.id.custom_date, CustomDateTimeView.class);
        drugReceiveAdapter = AdapterFactory.getDrugReceiveAdapter();
        rvList.setAdapter(drugReceiveAdapter);
    }

    @Override
    protected void getScanOrdList() {
        saveIfOrdByBarcode();
    }

    private void saveIfOrdByBarcode() {
        DrugReceiveApiManager.BatchIFSave(scanInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
                //刷新
                getIFOrdListByBarCode();
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_drug_receive;
    }
}
