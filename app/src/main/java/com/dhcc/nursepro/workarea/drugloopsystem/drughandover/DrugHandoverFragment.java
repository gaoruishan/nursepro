package com.dhcc.nursepro.workarea.drugloopsystem.drughandover;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter.DrugHandoverScanOrderAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api.DrugHandoverApiManager;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.BatchSaveResult;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.DrugHandOverScanOrderList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DrugHandoverFragment
 * 药品交接
 *
 * @author Devlix126
 * created at 2019/5/22 11:47
 */
public class DrugHandoverFragment extends BaseFragment {
    private RelativeLayout rlDrughandoverScan;
    private RecyclerView recyDrughandoverList;
    private LinearLayout llDrughandoverScan;
    private RecyclerView recyDrughandoverScan;
    private TextView tvDrughandoverReceivesize;
    private TextView tvDrughandoverClear;
    private TextView tvDrughandoverReceive;

    private DrugHandoverScanOrderAdapter scanOrderAdapter;

    private String barCode;
    private int amount;
    private int select;
    private String selectStr;
    private String parr;
    private String type = "";

    private DrugHandoverDialog drugHandoverDialog;
    private DrugReceiveDialog drugReceiveDialog;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_drughandover), 0xffffffff, 17);
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   列表   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyDrughandoverList.getVisibility() == View.GONE) {
                    asyncInitData();
                    recyDrughandoverList.setVisibility(View.VISIBLE);
                    textView.setText("   扫描   ");
                } else {
                    recyDrughandoverList.setVisibility(View.GONE);
                    textView.setText("   列表   ");
                }
            }
        });
        setToolbarRightCustomView(viewright);
        initView(view);
        initAdapter();
        //        view.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                asyncInitData();
        //            }
        //        }, 300);
    }

    /**
     * 加载已交接药品列表
     */
    private void asyncInitData() {
        showToast("获取列表。。。");
    }

    private void initView(View view) {
        rlDrughandoverScan = view.findViewById(R.id.rl_drughandover_scan);
        recyDrughandoverList = view.findViewById(R.id.recy_drughandover_list);
        llDrughandoverScan = view.findViewById(R.id.ll_drughandover_scan);
        recyDrughandoverScan = view.findViewById(R.id.recy_drughandover_scan);
        tvDrughandoverReceivesize = view.findViewById(R.id.tv_drughandover_receivesize);

        tvDrughandoverClear = view.findViewById(R.id.tv_drughandover_clear);
        tvDrughandoverReceive = view.findViewById(R.id.tv_drughandover_receive);

        recyDrughandoverList.setHasFixedSize(true);
        recyDrughandoverList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyDrughandoverScan.setHasFixedSize(true);
        recyDrughandoverScan.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvDrughandoverClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanOrderAdapter.setNewData(new ArrayList<>());
                rlDrughandoverScan.setVisibility(View.VISIBLE);
                llDrughandoverScan.setVisibility(View.GONE);
            }
        });
        tvDrughandoverReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDrugHandoverDialog();
            }
        });

    }

    private void initAdapter() {
        scanOrderAdapter = new DrugHandoverScanOrderAdapter(new ArrayList<>());

        scanOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_drugorder_wrong) {
                    ((DrugHandOverScanOrderList.OrdListBean) (Objects.requireNonNull(adapter.getItem(position)))).setError("W");
                } else if (view.getId() == R.id.tv_drugorder_missing) {
                    ((DrugHandOverScanOrderList.OrdListBean) (Objects.requireNonNull(adapter.getItem(position)))).setError("M");
                } else if (view.getId() == R.id.tv_drugorder_quality) {
                    ((DrugHandOverScanOrderList.OrdListBean) (Objects.requireNonNull(adapter.getItem(position)))).setError("Q");
                }
                adapter.notifyDataSetChanged();
            }
        });

        recyDrughandoverScan.setAdapter(scanOrderAdapter);

    }

    /**
     * 药品确认弹窗
     * 点击接收按钮，判断药品是否已全部接收
     * 若以全部接收，直接弹出接收窗口，若未全部接收，弹出确认窗口
     * 点击确定，无视未接收项目，继续进行接收操作
     * 点击取消，可继续扫码接收药品
     */
    private void showDrugHandoverDialog() {
        if (amount == select) {
            showDrugReceiveDialog();
            return;
        }

        if (drugHandoverDialog != null && drugHandoverDialog.isShowing()) {
            drugHandoverDialog.dismiss();
        }
        drugHandoverDialog = new DrugHandoverDialog(getActivity());
        drugHandoverDialog.show();
        drugHandoverDialog.setSureOnclickListener(new DrugHandoverDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                drugHandoverDialog.dismiss();
                showDrugReceiveDialog();
            }
        });

        drugHandoverDialog.setCancelOnclickListener(new DrugHandoverDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                drugHandoverDialog.dismiss();
            }
        });

    }

    /**
     * 药品接收弹窗
     * 显示已获取信息
     * 扫描运送人员胸牌，点击确认，进行接收操作
     */
    private void showDrugReceiveDialog() {
        if (drugReceiveDialog != null && drugReceiveDialog.isShowing()) {
            drugReceiveDialog.dismiss();
        }
        drugReceiveDialog = new DrugReceiveDialog(getActivity());
        drugReceiveDialog.setBoxCode(barCode);
        drugReceiveDialog.setDrugInfo(getDrugInfo());
        drugReceiveDialog.setSureOnclickListener(new DrugReceiveDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                if (StringUtils.isEmpty(drugReceiveDialog.getCarryUser())) {
                    showToast("请扫描运送人员胸牌");
                } else {
                    String carryUser = drugReceiveDialog.getCarryUser();
                    drugReceiveDialog.dismiss();
                    batchSave(barCode, carryUser);
                }
            }
        });

        drugReceiveDialog.setCancelOnclickListener(new DrugReceiveDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                drugReceiveDialog.dismiss();
            }
        });

        drugReceiveDialog.show();
    }

    /**
     * 获取药品汇总信息
     * "共10袋药品，接收5袋，袋错发1袋，漏发2袋，质量问题3袋"
     *
     * @return
     */
    private String getDrugInfo() {
        List<DrugHandOverScanOrderList.OrdListBean> ordListBeans = scanOrderAdapter.getData();
        StringBuilder stringBuilder = new StringBuilder();

        int all = scanOrderAdapter.getData().size();
        int wrong = 0;
        int miss = 0;
        int quality = 0;
        for (int i = 0; i < all; i++) {
            DrugHandOverScanOrderList.OrdListBean ordListBean = ordListBeans.get(i);

            if ("W".equals(ordListBean.getError())) {
                wrong++;
            } else if ("M".equals(ordListBean.getError())) {
                miss++;
            } else if ("Q".equals(ordListBean.getError())) {
                quality++;
            }
        }

        stringBuilder.append("共").append(all).append("袋药品");

        stringBuilder.append("，接收").append(select).append("袋");

        if (wrong > 0) {
            stringBuilder.append("，错发").append(wrong).append("袋");
        }
        if (miss > 0) {
            stringBuilder.append("，漏发").append(miss).append("袋");
        }
        if (quality > 0) {
            stringBuilder.append("，质量问题").append(quality).append("袋");
        }
        return stringBuilder.toString();
    }

    /**
     * 药品接收
     *
     * @param barCode
     * @param carryUser
     */
    private void batchSave(String barCode, String carryUser) {

        parr = getParr();

        DrugHandoverApiManager.batchSave(parr, carryUser, barCode, type, new DrugHandoverApiManager.BatchSaveCallback() {
            @Override
            public void onSuccess(BatchSaveResult batchSaveResult) {
                if (drugHandoverDialog != null && drugHandoverDialog.isShowing()) {
                    drugHandoverDialog.dismiss();
                }
                drugHandoverDialog = new DrugHandoverDialog(getActivity());
                drugHandoverDialog.setImgId(R.drawable.icon_popup_sucess);
                drugHandoverDialog.setHandoverresult(batchSaveResult.getMsg());
                drugHandoverDialog.setSureVisible(View.GONE);
                drugHandoverDialog.setCancleVisible(View.GONE);
                drugHandoverDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drugHandoverDialog.dismiss();
                        rlDrughandoverScan.setVisibility(View.VISIBLE);
                        llDrughandoverScan.setVisibility(View.GONE);
                        scanOrderAdapter.setNewData(new ArrayList<>());
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (drugHandoverDialog != null && drugHandoverDialog.isShowing()) {
                    drugHandoverDialog.dismiss();
                }
                drugHandoverDialog = new DrugHandoverDialog(getActivity());
                drugHandoverDialog.setHandoverresult(msg);
                drugHandoverDialog.setImgId(R.drawable.icon_popup_error_patient);
                drugHandoverDialog.setSureVisible(View.VISIBLE);
                drugHandoverDialog.setCancleVisible(View.GONE);
                drugHandoverDialog.setSureOnclickListener(new DrugHandoverDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        drugHandoverDialog.dismiss();
                    }
                });
                drugHandoverDialog.show();
            }
        });
    }

    /**
     * 获取接口传输药品汇总信息
     * "194-53-1||Y^194-53-2|Q|N"
     *
     * @return
     */
    private String getParr() {
        List<DrugHandOverScanOrderList.OrdListBean> ordListBeans = scanOrderAdapter.getData();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < ordListBeans.size(); i++) {
            DrugHandOverScanOrderList.OrdListBean ordListBean = ordListBeans.get(i);
            stringBuilder.append(ordListBean.getOeoreId())
                    .append("|").append(ordListBean.getError()).append("|");
            if (ordListBean.getScan()) {
                stringBuilder.append("Y");
            } else {
                stringBuilder.append("N");
            }
            if (i < ordListBeans.size() - 1) {
                stringBuilder.append("^");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取扫码信息，此页为本地判断扫码类型
     * 若药品接收弹窗显示，认为扫码为运送人胸牌
     * 若药品接收弹窗未显示，
     * 若扫码列表已有数据，认为扫码为药品小码
     * 若扫码列表无数据，认为扫码为药品大码
     *
     * @param intent
     */
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                String scanInfo = bundle.getString("data");

                if (drugReceiveDialog != null && drugReceiveDialog.isShowing()) {

                    drugReceiveDialog.setCarryUser(scanInfo);
                    drugReceiveDialog.setNurseInfo(SPUtils.getInstance().getString(SharedPreference.USERNAME));
                } else {

                    List<DrugHandOverScanOrderList.OrdListBean> ordListBeans = scanOrderAdapter.getData();
                    if (ordListBeans.size() > 0) {
                        for (int i = 0; i < ordListBeans.size(); i++) {
                            if (scanInfo.equals(ordListBeans.get(i).getOeoreId())) {
                                scanOrderAdapter.getData().get(i).setScan(true);
                                break;
                            }
                        }
                        scanOrderAdapter.notifyDataSetChanged();
                        refreshBottom();
                    } else {
                        barCode = scanInfo;
                        DrugHandoverApiManager.getOrdListByBarCode(barCode, new DrugHandoverApiManager.DrugHandoverScanOrderListCallback() {
                            @Override
                            public void onSuccess(DrugHandOverScanOrderList scanOrderList) {
                                List<DrugHandOverScanOrderList.OrdListBean> ordListBeanList = scanOrderList.getOrdList();
                                //向后台传输数据 医嘱中"||"全部替换为"-"
                                //若大码与列表中某条医嘱的oeoreId相同，则这条医嘱直接置为已扫描状态
                                for (int i = 0; i < ordListBeanList.size(); i++) {
                                    ordListBeanList.get(i).setOeoreId(ordListBeanList.get(i).getOeoreId().replaceAll("\\|\\|", "-"));

                                    if (barCode.equals(ordListBeanList.get(i).getOeoreId())) {
                                        ordListBeanList.get(i).setScan(true);
                                    }
                                }
                                scanOrderAdapter.setNewData(ordListBeanList);
                                refreshBottom();
                                llDrughandoverScan.setVisibility(View.VISIBLE);
                                rlDrughandoverScan.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFail(String code, String msg) {
                                showToast("error" + code + ":" + msg);
                            }
                        });
                    }
                }
        }
    }

    /**
     * 刷新底部显示
     * "共10袋，已扫描3袋"
     */
    private void refreshBottom() {
        amount = 0;
        select = 0;
        List<DrugHandOverScanOrderList.OrdListBean> ordListBeans = scanOrderAdapter.getData();
        amount = ordListBeans.size();
        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                if (ordListBeans.get(i).getScan()) {
                    select++;
                }
            }

            selectStr = "共" + amount + "袋，已扫描" + select + "袋";
            tvDrughandoverReceivesize.setText(selectStr);
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drughandover, container, false);
    }

}
