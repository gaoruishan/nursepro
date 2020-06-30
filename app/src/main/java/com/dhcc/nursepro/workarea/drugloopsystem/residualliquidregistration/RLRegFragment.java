package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration;


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
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.adapter.RLRegPatsAdapter;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.api.RLRegApiManager;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLRegResultBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Objects;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * RLRegFragment
 * 余液登记
 *
 * @author Devlix126
 * created at 2019/5/22 16:45
 */
public class RLRegFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {
    private TextView tvRlregStartdate;
    private TextView tvRlregEnddate;
    private RecyclerView recyRlregPatorder;

    private String startDate;
    private String endDate;
    private String dateChangeFlag;

    private SPUtils spUtils = SPUtils.getInstance();

    private RLRegPatsAdapter patsAdapter;

    private RlRegEditDialog rlRegEditDialog;
    private RlRegResultDialog rlRegResultDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_residualliquidregistration), 0xffffffff, 17);

        startDate = SchDateTimeUtil.getStartDate();
        endDate = SchDateTimeUtil.getEndDate();

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);

    }

    private void initView(View view) {
        tvRlregStartdate = view.findViewById(R.id.tv_rlreg_startdate);
        tvRlregEnddate = view.findViewById(R.id.tv_rlreg_enddate);
        recyRlregPatorder = view.findViewById(R.id.recy_rlreg_patorder);

        tvRlregStartdate.setText(startDate);
        tvRlregEnddate.setText(endDate);
        tvRlregStartdate.setOnClickListener(this);
        tvRlregEnddate.setOnClickListener(this);

        recyRlregPatorder.setHasFixedSize(true);
        recyRlregPatorder.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        patsAdapter = new RLRegPatsAdapter(new ArrayList<>());
        recyRlregPatorder.setAdapter(patsAdapter);
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        RLRegApiManager.getResidualQtyList(startDate, endDate, new RLRegApiManager.RLPatOrdCallback() {
            @Override
            public void onSuccess(RLPatOrdBean rlPatOrdBean) {
                hideLoadingTip();
                patsAdapter.setNewData(rlPatOrdBean.getPatOrdList());
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rlreg_startdate:
                dateChangeFlag = "START";
                chooseTime(TimeUtils.string2Millis(tvRlregStartdate.getText().toString() + " 00:00:00"));

                break;
            case R.id.tv_rlreg_enddate:
                dateChangeFlag = "END";
                chooseTime(TimeUtils.string2Millis(tvRlregEnddate.getText().toString() + " 00:00:00"));

                break;
            default:
                break;
        }
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

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        switch (Objects.requireNonNull(intent.getAction())) {
            case Action.DRUG_RLREG:
                Bundle bundle = intent.getExtras();
                RLPatOrdBean.PatOrdListBean patOrdListBean = (RLPatOrdBean.PatOrdListBean) bundle.getSerializable("PatOrdListBean");
                int position = bundle.getInt("position");
                showRLRegEditDialog(patOrdListBean, position);
                break;
            default:
                break;

        }
    }

    private void showRLRegEditDialog(RLPatOrdBean.PatOrdListBean patOrdListBean, int position) {
        if (rlRegEditDialog != null && rlRegEditDialog.isShowing()) {
            rlRegEditDialog.dismiss();
        }

        RLPatOrdBean.PatOrdListBean.PatOrdsBean patOrdsBean = patOrdListBean.getPatOrds().get(position);
        rlRegEditDialog = new RlRegEditDialog(getActivity());
        rlRegEditDialog.setBedCode(patOrdListBean.getPatBed());
        rlRegEditDialog.setPatName(patOrdListBean.getPatName());
        rlRegEditDialog.setOeoreGroupBeanList(patOrdsBean.getOeoreGroup());
        rlRegEditDialog.setOrderexinfo(patOrdsBean.getSttdateTime() + "   " + patOrdsBean.getMethDesc());
        rlRegEditDialog.setRlCount(patOrdsBean.getResidualQty());
        rlRegEditDialog.setWhereGo(patOrdsBean.getWhereGo());
        rlRegEditDialog.setUnitOnclickListener(new RlRegEditDialog.onUnitOnclickListener() {
            @Override
            public void onUnitClick() {
                showUnitPicker(patOrdsBean.getUnitDescComb());
            }
        });
        rlRegEditDialog.setSureOnclickListener(new RlRegEditDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                rlRegEditDialog.dismiss();
                rlReg(patOrdsBean, rlRegEditDialog.getRlCount(), rlRegEditDialog.getRlUnit(), rlRegEditDialog.getType());
            }
        });
        rlRegEditDialog.setCancelOnclickListener(new RlRegEditDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                rlRegEditDialog.dismiss();
            }
        });
        rlRegEditDialog.show();

    }

    private void showUnitPicker(String unitDescComb) {
        String[] unitDesc = unitDescComb.split("\\^");

        final OptionPicker picker = new OptionPicker(getActivity(), unitDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                rlRegEditDialog.setRlUnit(item);
            }
        });
        picker.show();
    }

    private void rlReg(RLPatOrdBean.PatOrdListBean.PatOrdsBean patOrdsBean, String rlCount, String rlUnit, String type) {

        String oeoreId = patOrdsBean.getJmOeoreId();

        RLRegApiManager.residualQtyReg(oeoreId, "", "", rlCount, rlUnit, type, new RLRegApiManager.RLRegResultCallback() {
            @Override
            public void onSuccess(RLRegResultBean rlRegResultBean) {

                if (rlRegResultDialog != null && rlRegResultDialog.isShowing()) {
                    rlRegResultDialog.dismiss();
                }
                rlRegResultDialog = new RlRegResultDialog(getActivity());
                rlRegResultDialog.setSureVisible(View.GONE);
                rlRegResultDialog.setCancleVisible(View.GONE);
                rlRegResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rlRegResultDialog.dismiss();
                        asyncInitData();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String code, String msg) {
                if (rlRegResultDialog != null && rlRegResultDialog.isShowing()) {
                    rlRegResultDialog.dismiss();
                }
                rlRegResultDialog = new RlRegResultDialog(getActivity());
                rlRegResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                rlRegResultDialog.setRlRegResult(msg);
                rlRegResultDialog.setSureVisible(View.VISIBLE);
                rlRegResultDialog.setCancleVisible(View.GONE);
                rlRegEditDialog.setSureOnclickListener(new RlRegEditDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        rlRegResultDialog.dismiss();
                    }
                });

                rlRegResultDialog.show();
            }
        });
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rlreg, container, false);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 10);

        if ("START".equals(dateChangeFlag)) {
            tvRlregStartdate.setText(date);
            if (!date.equals(startDate)) {
                //日期时间发生改变，需重新请求数据
                startDate = date;
                asyncInitData();
            }

        } else {
            tvRlregEnddate.setText(date);
            if (!date.equals(endDate)) {
                //日期时间发生改变，需重新请求数据
                endDate = date;
                asyncInitData();
            }
        }
    }
}
