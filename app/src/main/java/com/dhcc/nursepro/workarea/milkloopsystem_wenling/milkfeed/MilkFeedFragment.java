package com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkfeed;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.adapter.MilkFeedAdapter;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.adapter.MilkFreezingScanedAdapter;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFeedExeListBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFreezingBagInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.ScanInfoBean;
import com.dhcc.nursepro.workarea.orderexecute.OrderExecOrderDialog;
import com.dhcc.nursepro.workarea.orderexecute.api.OrderExecuteApiManager;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkfeed
 * <p>
 * author Q
 * Date: 2019/7/5
 * Time:11:10
 */
public class MilkFeedFragment extends BaseFragment {

    private String episodeId = "",scanInfo = "";
    private RecyclerView recFeedList;
    private MilkFeedAdapter milkFeedAdapter;
    private LinearLayout llPat;
    private TextView tvPat;
    private MilkFeedStartDialog milkFeedStartDialog;
    private SPUtils spUtils = SPUtils.getInstance();
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_wl_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_feed));
        setToolbarBottomLineVisibility(false);

        initView(view);
        initAdapter();
        initData();
    }

    private void initView(View view){
        llPat = view.findViewById(R.id.ll_milkfeed_pat);
        tvPat = view.findViewById(R.id.tv_milkfeed_pat);
        llPat.setVisibility(View.GONE);

        recFeedList = view.findViewById(R.id.rec_milkfeed_list);
        //提高展示效率
        recFeedList.setHasFixedSize(true);
        //设置的布局管理
        recFeedList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {
        milkFeedAdapter = new MilkFeedAdapter(new ArrayList<MilkFeedExeListBean.ExeListBean>());
        recFeedList.setAdapter(milkFeedAdapter);
//        milkFeedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            }
//        });
    }

    private void initData(){
        HashMap<String,String> map = new HashMap<>();
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        map.put("exeFlag","0");
        map.put("sttDate",spUtils.getString(SharedPreference.SCHSTDATETIME));
        map.put("endDate",spUtils.getString(SharedPreference.SCHENDATETIME));
        MilkLoopApiManager.getMilkFeedExeList(map, "getExeList", new MilkLoopApiManager.MilkFeedExeListCallback() {
            @Override
            public void onSuccess(MilkFeedExeListBean milkFeedExeListBean) {
                milkFeedAdapter.setNewData(milkFeedExeListBean.getExeList());
                milkFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            scanInfo = bundle.getString("data");
            getScanInfo();

        }
    }


    /**
     * 扫描获取患者信息
     * */
    private void getScanInfo() {

        HashMap<String,String> map = new HashMap<>();
        map.put("barcode",scanInfo);
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        map.put("wardId",spUtils.getString(SharedPreference.WARDID));

        MilkLoopApiManager.getScanInfo(map, "getScanInfoWL", new MilkLoopApiManager.MilkFeedGetScanInfoCallback() {
            @Override
            public void onSuccess(ScanInfoBean scanInfoBean) {
                if (scanInfoBean.getFlag().equals("PAT")){
                    episodeId = scanInfoBean.getPatInfo().getEpisodeID();
                    llPat.setVisibility(View.VISIBLE);
                    tvPat.setText(scanInfoBean.getPatInfo().getName());
                    showToast(scanInfoBean.getFlag());
                }else if (scanInfoBean.getFlag().equals("ORD")){
                    if (episodeId != ""){
                        exeOrderStart(scanInfo);
                    }else {
                        if (scanInfoBean.getStartFlag().equals("1")){
                            if (milkFeedStartDialog != null && milkFeedStartDialog.isShowing()) {
                                milkFeedStartDialog.dismiss();
                            }
                            milkFeedStartDialog = new MilkFeedStartDialog(getActivity());
                            milkFeedStartDialog.setPatInfo(scanInfoBean.getOrders().get(0).getBedCode()+"  "+scanInfoBean.getOrders().get(0).getPatName());
                            milkFeedStartDialog.show();
                            milkFeedStartDialog.setSureOnclickListener(new OrderExecOrderDialog.onSureOnclickListener() {
                                @Override
                                public void onSureClick() {
                                    milkFeedStartDialog.dismiss();
                                    exeOrderEnd(milkFeedStartDialog.getMotherMilk(),milkFeedStartDialog.getOtherMilk());
                                }
                            });
                            milkFeedStartDialog.setCancelOnclickListener(new OrderExecOrderDialog.onCancelOnclickListener() {
                                @Override
                                public void onCancelClick() {
                                    milkFeedStartDialog.dismiss();
                                }
                            });
                        }else {
                            showToast("医嘱未开始执行，无法结束");
                        }

                    }
                }else {
                    showToast("未知条码类型");
                }
            }


            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    /**
     * 执行开始医嘱exeStart
     * */
    private void exeOrderStart(String orderId){
        HashMap<String,String> map = new HashMap<>();
        map.put("oeoreId",orderId);
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        map.put("locId",spUtils.getString(SharedPreference.LOCID));
        MilkLoopApiManager.getMilkOperateResult(map, "exeStart", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkOperatResultBean) {
                showToast(milkOperatResultBean.getMsg());
                llPat.setVisibility(View.GONE);
                initData();
                episodeId = "";
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    /**
     * 执行结束医嘱exeEnd
     * */
    private void exeOrderEnd(String moMilk,String otMilk){
        HashMap<String,String> map = new HashMap<>();
        map.put("oeoreId",scanInfo);
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        map.put("powderAmount",moMilk);
        map.put("breastAmount",otMilk);
        MilkLoopApiManager.getMilkOperateResult(map, "exeEnd", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkOperatResultBean) {
                showToast("结束喂养");
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }
}
