package com.dhcc.nursepro.workarea.milkloopsystem.milkcoldstorage;

import android.content.Intent;
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

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.milkloopsystem.MilkOperateResultDialog;
import com.dhcc.nursepro.workarea.milkloopsystem.adapter.MilkColdScanedAdapter;
import com.dhcc.nursepro.workarea.milkloopsystem.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkColdPatinfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkOperatResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.MilkReceive
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:9:41
 */
public class MilkColdstorageFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recMilk;
    private RelativeLayout rlScan;
    private TextView tvScanNo;
    private TextView tvSure;

    private MilkColdScanedAdapter milkAdapter;
    private List<MilkColdPatinfoBean> listMilk = new ArrayList<>();
    private List<String> listBottleNo = new ArrayList<String>();
    private String st = "jj";

    private SPUtils spUtils = SPUtils.getInstance();

    private String bottleNo;

    private MilkOperateResultDialog milkOperateResultDialog;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_coldstorage, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_coldstorage));
        setToolbarBottomLineVisibility(false);


        initView(view);
        initAdapter();
    }

    private void initView(View view) {


        rlScan = view.findViewById(R.id.rl_milkcold_scan);
        tvScanNo = view.findViewById(R.id.tv_milkcold_selected);
        tvScanNo.setText("已扫码" + listMilk.size() + "个");
        tvSure = view.findViewById(R.id.tv_milkcold_sure);
        tvSure.setOnClickListener(this);
        recMilk = view.findViewById(R.id.recy_milkcold_scaned);

        //提高展示效率
        recMilk.setHasFixedSize(true);
        //设置的布局管理
        recMilk.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        milkAdapter = new MilkColdScanedAdapter(new ArrayList<MilkColdPatinfoBean>());
        recMilk.setAdapter(milkAdapter);
        milkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_milkcold_right) {
                    listMilk.remove(position);
                    milkAdapter.setNewData(listMilk);
                    milkAdapter.notifyDataSetChanged();
                    listBottleNo.remove(position);
                    tvScanNo.setText("已扫码" + listMilk.size() + "个");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        String strBags = "";
        for (int i = 0; i < listMilk.size(); i++) {
            if (i == 0) {
                strBags = listMilk.get(i).getPatInfo().getBagNo();
            } else {
                strBags = strBags + "^" + listMilk.get(i).getPatInfo().getBagNo();
            }
        }

        if (listMilk.size() <= 0) {
            showToast("请先添加奶瓶");
        } else {
            initMilkColdSure(strBags);
        }
    }

    private void initMilkColdSure(String strBags) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("bottleNo", strBags);
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        MilkLoopApiManager.getMilkOperateResult(map, "coldStorage", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkOperatResultBean) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }

                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());

                milkOperateResultDialog.setExecresult("母乳冷藏成功");

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
                tvScanNo.setText("已扫码" + listMilk.size() + "个");
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

    private void initBottle(final String bottleNo) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("bottleNo", bottleNo);
        //            map.put("wardId",spUtils.getString(SharedPreference.WARDID));
        MilkLoopApiManager.getMilkColdstorageInfo(map, "getcoldInfo", new MilkLoopApiManager.MilkColdstorageInfoCallback() {
            @Override
            public void onSuccess(MilkColdPatinfoBean milkColdPatinfoBean) {

                rlScan.setVisibility(View.GONE);
                listMilk.add(milkColdPatinfoBean);
                tvScanNo.setText("已扫码" + listMilk.size() + "个");
                listBottleNo.add(bottleNo);
                milkAdapter.setNewData(listMilk);
                milkAdapter.notifyDataSetChanged();
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
                bottleNo = bundle.getString("data");
                for (int i = 0; i < listBottleNo.size(); i++) {
                    if (listBottleNo.get(i).equals(bottleNo)) {
                        showToast("重复扫码");
                        return;
                    }
                }
                initBottle(bottleNo);
        }

    }
}
