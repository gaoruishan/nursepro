package com.dhcc.nursepro.workarea.milkloopsystem.milkfreezing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.milkloopsystem.MilkOperateResultDialog;
import com.dhcc.nursepro.workarea.milkloopsystem.adapter.MilkFreezingScanedAdapter;
import com.dhcc.nursepro.workarea.milkloopsystem.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkFreezingBagInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkReceiveBagInfoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.MilkReceive
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:9:41
 */
public class MilkFreezingFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView recMilk;
    private RelativeLayout rlScan;
    private TextView tvScanNo;
    private TextView tvSure;

    private MilkFreezingScanedAdapter milkAdapter;
    private List<MilkFreezingBagInfoBean> listMilk = new ArrayList<>();
    private List<String> listBagNo = new ArrayList<String>();
    private String st="jj";

    private SPUtils spUtils = SPUtils.getInstance();

    private IntentFilter filter;
    private Receiver mReceiver = null;

    private String bagNo;

    private MilkOperateResultDialog milkOperateResultDialog;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_freezing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_freezing));
        setToolbarBottomLineVisibility(false);


        initView(view);
        initAdapter();

        mReceiver  = new Receiver();
        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);

    }

    private void initView(View view){
        rlScan = view.findViewById(R.id.rl_milkfreezing_scan);
        tvScanNo = view.findViewById(R.id.tv_milkfreezing_selected);
        tvScanNo.setText("已扫码"+listMilk.size()+"个");
        tvSure = view.findViewById(R.id.tv_milkfreezing_sure);
        tvSure.setOnClickListener(this);
        recMilk = view.findViewById(R.id.recy_milkfreezing_selected);

        //提高展示效率
        recMilk.setHasFixedSize(true);
        //设置的布局管理
        recMilk.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter(){
        milkAdapter = new MilkFreezingScanedAdapter(new ArrayList<MilkFreezingBagInfoBean>());
        recMilk.setAdapter(milkAdapter);
        milkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_milkfreezing_right){
                    listMilk.remove(position);
                    milkAdapter.setNewData(listMilk);
                    milkAdapter.notifyDataSetChanged();
                    listBagNo.remove(position);
                    tvScanNo.setText("已扫码"+listMilk.size()+"个");
                }
            }
        });
        }

    @Override
    public void onClick(View v) {
        String strBags = "";
        for (int i = 0;i<listMilk.size();i++){
            if (i == 0){
                strBags = listMilk.get(i).getPatInfo().getBagNo();
            }else {
                strBags = strBags + "^"+listMilk.get(i).getPatInfo().getBagNo();
            }
        }

        if (listMilk.size()<=0){
            showToast("请先添加血袋");
        }else {
            initMilkFreezingSure(strBags);
        }
    }

    private void initBag(final String bagNo){
            final HashMap<String,String> map = new HashMap<>();
            map.put("bagNo",bagNo);
//            map.put("wardId",spUtils.getString(SharedPreference.WARDID));
            MilkLoopApiManager.getMilkFreezingBagInfo(map, "getBagFreezingInfo", new MilkLoopApiManager.MilkFreezingBagInfoCallback() {
                @Override
                public void onSuccess(MilkFreezingBagInfoBean milkFreezingBagInfoBean) {

                    rlScan.setVisibility(View.GONE);
                    listMilk.add(milkFreezingBagInfoBean);
                    tvScanNo.setText("已扫码"+listMilk.size()+"个");
                    listBagNo.add(bagNo);
                    milkAdapter.setNewData(listMilk);
                    milkAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(String code, String msg) {
                    showToast(code+":"+msg);
                }
            });

        }
    private void initMilkFreezingSure(String strBags){
        final HashMap<String,String> map = new HashMap<>();
        map.put("bagNo",strBags);
        map.put("userId",spUtils.getString(SharedPreference.USERID));
        map.put("operation","freeze");
        MilkLoopApiManager.getMilkOperateResult(map, "bagFreezing", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkOperatResultBean) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }

                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());

                milkOperateResultDialog.setExecresult("母乳冷冻成功");

                milkOperateResultDialog.setImgId(R.drawable.icon_popup_sucess);
                milkOperateResultDialog.setSureVisible(View.GONE);
                milkOperateResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        milkOperateResultDialog.dismiss();
                    }
                }, 500);
                listMilk = new ArrayList<>();
                milkAdapter.setNewData(listMilk);
                milkAdapter.notifyDataSetChanged();
                tvScanNo.setText("已扫码"+listMilk.size()+"个");
                rlScan.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFail(String code, String msg) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }
                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());
                milkOperateResultDialog.setExecresult(msg);
                milkOperateResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                milkOperateResultDialog.setSureVisible(View.VISIBLE);
                milkOperateResultDialog.setSureOnclickListener(new MilkOperateResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        milkOperateResultDialog.dismiss();
                    }
                });
                milkOperateResultDialog.show();
            }
        });

    }


    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    bagNo =bundle.getString("data");
                    for (int i = 0;i<listBagNo.size();i++){
                        if (listBagNo.get(i).equals(bagNo)){
                            showToast("重复扫码");
                            return;
                        }
                    }
                    initBag(bagNo);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mReceiver != null) {
            getActivity().registerReceiver(mReceiver, filter);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mReceiver);

    }
}