package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation;


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

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter.DrugPreOrderAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter.DrugPreTakeOrdersAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.api.DrugPreApiManager;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetOrdInfoBean;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetTakeOrdListBean;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.TakeOrdBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * DrugPreparationFragment
 * 取药复核
 *
 * @author Devlix126
 * created at 2019/5/22 16:43
 */
public class DrugPreparationFragment extends BaseFragment implements OnDateSetListener {
    private TextView tvRight;

    private LinearLayout llDrugpreparationType;
    private TextView tvDrugpreparationTake;
    private TextView tvDrugpreparationReview;
    private View lineDrugpreparationTake;
    private View lineDrugpreparationReview;

    private LinearLayout llDrugpreparationHis;
    private TextView tvDrugpreStartdate;
    private TextView tvDrugpreEnddate;
    private RecyclerView recyDrugpreparationList;

    private RelativeLayout rlDrugpreparationScan;
    private TextView tvDrugpreparationWarning;
    private TextView tvDrugpreparationWarningtitle;

    private LinearLayout llDrugpreparationScan;
    private RecyclerView recyDrugpreparationScan;
    private TextView tvDrugpreparationReceivesize;
    private TextView tvDrugpreparationClear;
    private TextView tvDrugpreparationReceive;


    private DrugPreOrderAdapter drugPreOrderAdapter;
    private String oeoreId;
    private String type = "Take";

    private int amount;
    private int select;
    private String selectStr;

    private DrugPreDialog drugPreDialog;

    private String startDate;
    private String endDate;
    private String dateChangeFlag;

    private SPUtils spUtils = SPUtils.getInstance();

    private DrugPreTakeOrdersAdapter drugPreTakeOrdersAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_drugpreparation), 0xffffffff, 17);
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        tvRight = viewright.findViewById(R.id.tv_fratoobar_right);
        tvRight.setTextSize(15);
        tvRight.setText("   历史记录   ");
        tvRight.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llDrugpreparationHis.getVisibility() == View.GONE) {
                    asyncInitData();
                    llDrugpreparationType.setVisibility(View.GONE);
                    llDrugpreparationHis.setVisibility(View.VISIBLE);
                    tvRight.setText("   扫描箱码   ");
                } else {
                    llDrugpreparationType.setVisibility(View.VISIBLE);
                    llDrugpreparationHis.setVisibility(View.GONE);
                    tvRight.setText("   历史记录   ");
                }
            }
        });
        setToolbarRightCustomView(viewright);
        startDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        endDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);

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
     * 获取历史记录
     */
    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        DrugPreApiManager.getTakeOrdList(startDate, endDate, new DrugPreApiManager.GetTakeOrdListCallback() {
            @Override
            public void onSuccess(GetTakeOrdListBean getTakeOrdListBean) {
                hideLoadingTip();
                drugPreTakeOrdersAdapter.setNewData(getTakeOrdListBean.getOrdList());

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initView(View view) {
        llDrugpreparationType = view.findViewById(R.id.ll_drugpreparation_type);
        tvDrugpreparationTake = view.findViewById(R.id.tv_drugpreparation_take);
        tvDrugpreparationReview = view.findViewById(R.id.tv_drugpreparation_review);
        lineDrugpreparationTake = view.findViewById(R.id.line_drugpreparation_take);
        lineDrugpreparationReview = view.findViewById(R.id.line_drugpreparation_review);

        tvDrugpreparationTake.setSelected(true);
        tvDrugpreparationReview.setSelected(false);
        lineDrugpreparationTake.setVisibility(View.VISIBLE);
        lineDrugpreparationReview.setVisibility(View.INVISIBLE);


        llDrugpreparationHis = view.findViewById(R.id.ll_drugpreparation_his);
        tvDrugpreStartdate = view.findViewById(R.id.tv_drugpre_startdate);
        tvDrugpreEnddate = view.findViewById(R.id.tv_drugpre_enddate);
        recyDrugpreparationList = view.findViewById(R.id.recy_drugpreparation_list);

        tvDrugpreStartdate.setText(startDate);
        tvDrugpreEnddate.setText(endDate);
        tvDrugpreStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvDrugpreStartdate.getText().toString() + " 00:00:00"));
            }
        });
        tvDrugpreEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvDrugpreEnddate.getText().toString() + " 00:00:00"));
            }
        });

        rlDrugpreparationScan = view.findViewById(R.id.rl_drugpreparation_scan);
        tvDrugpreparationWarning = view.findViewById(R.id.tv_drugpreparation_warning);
        tvDrugpreparationWarningtitle = view.findViewById(R.id.tv_drugpreparation_warningtitle);

        llDrugpreparationScan = view.findViewById(R.id.ll_drugpreparation_scan);
        recyDrugpreparationScan = view.findViewById(R.id.recy_drugpreparation_scan);
        tvDrugpreparationReceivesize = view.findViewById(R.id.tv_drugpreparation_receivesize);
        tvDrugpreparationClear = view.findViewById(R.id.tv_drugpreparation_clear);
        tvDrugpreparationReceive = view.findViewById(R.id.tv_drugpreparation_receive);


        recyDrugpreparationList.setHasFixedSize(true);
        recyDrugpreparationList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyDrugpreparationScan.setHasFixedSize(true);
        recyDrugpreparationScan.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvDrugpreparationTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Take";
                tvDrugpreparationTake.setSelected(true);
                tvDrugpreparationReview.setSelected(false);
                lineDrugpreparationTake.setVisibility(View.VISIBLE);
                lineDrugpreparationReview.setVisibility(View.INVISIBLE);
                tvDrugpreparationReceive.setText("取药");
                if (llDrugpreparationHis.getVisibility() == View.VISIBLE) {
                    asyncInitData();
                }
            }
        });

        tvDrugpreparationReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Audit";
                tvDrugpreparationTake.setSelected(false);
                tvDrugpreparationReview.setSelected(true);
                lineDrugpreparationTake.setVisibility(View.INVISIBLE);
                lineDrugpreparationReview.setVisibility(View.VISIBLE);
                tvDrugpreparationReceive.setText("复核");
                if (llDrugpreparationHis.getVisibility() == View.VISIBLE) {
                    asyncInitData();
                }
            }
        });

        tvDrugpreparationClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drugPreOrderAdapter.setSize(0);
                drugPreOrderAdapter.setNewData(new ArrayList<>());
                rlDrugpreparationScan.setVisibility(View.VISIBLE);
                llDrugpreparationScan.setVisibility(View.GONE);
            }
        });
        tvDrugpreparationReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Take")) {
                    showDrugPreDialog();
                } else {
                    takeOrd();
                }
            }
        });
    }

    private void initAdapter() {
        drugPreOrderAdapter = new DrugPreOrderAdapter(new ArrayList<>());
        recyDrugpreparationScan.setAdapter(drugPreOrderAdapter);

        drugPreTakeOrdersAdapter = new DrugPreTakeOrdersAdapter(new ArrayList<>());
        recyDrugpreparationList.setAdapter(drugPreTakeOrdersAdapter);
    }

    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    /**
     * 取备用药确认弹窗
     * 点击确认按钮，判断药品是否已全部取到
     * 若已全部取到，直接弹出取药窗口，若未全部接收，弹出确认窗口
     * 点击确定，无视未取到项目，继续进行取药操作
     * 点击取消，可继续扫码取药
     */
    private void showDrugPreDialog() {
        if (amount == select) {
            takeOrd();
            return;
        }

        if (drugPreDialog != null && drugPreDialog.isShowing()) {
            drugPreDialog.dismiss();
        }
        drugPreDialog = new DrugPreDialog(getActivity());
        drugPreDialog.show();
        drugPreDialog.setSureOnclickListener(new DrugPreDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                drugPreDialog.dismiss();
                takeOrd();
            }
        });

        drugPreDialog.setCancelOnclickListener(new DrugPreDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                drugPreDialog.dismiss();

            }
        });
    }

    private void takeOrd() {
        DrugPreApiManager.takeOrd(oeoreId, type, new DrugPreApiManager.TakeOrdCallback() {
            @Override
            public void onSuccess(TakeOrdBean takeOrdBean) {
                if (drugPreDialog != null && drugPreDialog.isShowing()) {
                    drugPreDialog.dismiss();
                }
                drugPreDialog = new DrugPreDialog(getActivity());
                drugPreDialog.setImgId(R.drawable.icon_popup_sucess);
                drugPreDialog.setDrugPreResult(takeOrdBean.getMsg());
                drugPreDialog.setSureVisible(View.GONE);
                drugPreDialog.setCancleVisible(View.GONE);
                drugPreDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drugPreDialog.dismiss();
                        drugPreOrderAdapter.setSize(0);
                        drugPreOrderAdapter.setNewData(new ArrayList<>());
                        rlDrugpreparationScan.setVisibility(View.VISIBLE);
                        llDrugpreparationScan.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (drugPreDialog != null && drugPreDialog.isShowing()) {
                    drugPreDialog.dismiss();
                }
                drugPreDialog = new DrugPreDialog(getActivity());
                drugPreDialog.setImgId(R.drawable.icon_popup_error_patient);
                drugPreDialog.setDrugPreResult(msg);
                drugPreDialog.setSureVisible(View.VISIBLE);
                drugPreDialog.setCancleVisible(View.GONE);
                drugPreDialog.setSureOnclickListener(new DrugPreDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        drugPreDialog.dismiss();
                    }
                });
                drugPreDialog.show();
            }
        });
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

            if (llDrugpreparationHis.getVisibility() == View.VISIBLE) {
                llDrugpreparationType.setVisibility(View.VISIBLE);
                llDrugpreparationHis.setVisibility(View.GONE);
                tvRight.setText("   历史记录   ");
            }

            List<GetOrdInfoBean.OeoreGroupBean> oeoreGroupBeanList = drugPreOrderAdapter.getData();
            if (oeoreGroupBeanList.size() > 0) {
                if (type.equals("Take")) {
                    for (int i = 0; i < oeoreGroupBeanList.size(); i++) {
                        if (scanInfo.equals(oeoreGroupBeanList.get(i).getArcimCode())) {
                            drugPreOrderAdapter.getData().get(i).setScan(true);
                            break;
                        }
                    }
                    drugPreOrderAdapter.notifyDataSetChanged();
                    refreshBottom();
                }
            } else {
                showLoadingTip(BaseActivity.LoadingType.FULL);
                oeoreId = scanInfo;
                DrugPreApiManager.getOrdInfo(oeoreId, new DrugPreApiManager.GetOrdInfoCallback() {
                    @Override
                    public void onSuccess(GetOrdInfoBean getOrdInfoBean) {
                        hideLoadingTip();
                        drugPreOrderAdapter.setSize(getOrdInfoBean.getOeoreGroup().size());
                        drugPreOrderAdapter.setNewData(getOrdInfoBean.getOeoreGroup());
                        refreshBottom();
                        rlDrugpreparationScan.setVisibility(View.GONE);
                        llDrugpreparationScan.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        hideLoadingTip();
                        showToast("error" + code + ":" + msg);
                    }
                });
            }


        }
    }

    /**
     * 刷新底部显示
     * "共10袋，已扫描3袋"
     */
    private void refreshBottom() {
        if (type.equals("Take")) {
            tvDrugpreparationReceivesize.setVisibility(View.VISIBLE);
            amount = 0;
            select = 0;
            List<GetOrdInfoBean.OeoreGroupBean> oeoreGroupBeanList = drugPreOrderAdapter.getData();
            amount = oeoreGroupBeanList.size();
            if (amount > 0) {
                for (int i = 0; i < amount; i++) {
                    if (oeoreGroupBeanList.get(i).isScan()) {
                        select++;
                    }
                }

                selectStr = "共" + amount + "袋，已扫描" + select + "袋";
                tvDrugpreparationReceivesize.setText(selectStr);
            }
        } else {
            tvDrugpreparationReceivesize.setVisibility(View.GONE);
        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drug_preparation, container, false);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);

        if ("START".equals(dateChangeFlag)) {
            tvDrugpreStartdate.setText(date);
            if (!date.equals(startDate)) {
                //日期时间发生改变，需重新请求数据
                startDate = date;
                asyncInitData();
            }

        } else {
            tvDrugpreEnddate.setText(date);
            if (!date.equals(endDate)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                asyncInitData();
            }
        }
    }
}
