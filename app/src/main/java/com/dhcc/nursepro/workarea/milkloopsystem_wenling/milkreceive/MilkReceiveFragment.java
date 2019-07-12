package com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkreceive;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.MilkOperateResultDialog;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkReceiveBagInfoBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.MilkReceive
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:9:41
 */
public class MilkReceiveFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private SPUtils spUtils = SPUtils.getInstance();

    private TextView tvPatinfo;
    private TextView tvRegno;
    private TextView tvTime,tvType;
    private LinearLayout LlTime,llType;
    private EditText etAmount;
    private RelativeLayout rlScan;

    private String bagNo;
    private String timestr;
    private String datestr;
    private String[] receiveType;

    private View viewright;

    private MilkOperateResultDialog milkOperateResultDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_receive));

        //右上角按钮
        viewright = View.inflate(getActivity(), R.layout.view_save_blue_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                showToast("保存");
                saveData();
            }
        });
        setToolbarRightCustomView(viewright);
        viewright.setVisibility(View.GONE);
        setToolbarBottomLineVisibility(false);

        initView(view);

    }

    private void saveData() {
        if (etAmount.getText().toString().equals("")) {
            showToast("请输入留取量再保存");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("bagNo", bagNo);
        map.put("amount", etAmount.getText().toString());
        map.put("date", datestr);
        map.put("time", timestr);
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        map.put("recType",tvType.getText().toString());
        MilkLoopApiManager.getMilkOperateResult(map, "receiveBagWL", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkReceiveBagBean) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }

                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());

                milkOperateResultDialog.setExecresult("母乳采集成功");
                viewright.setVisibility(View.GONE);

                milkOperateResultDialog.setImgId(R.drawable.icon_popup_sucess);
                milkOperateResultDialog.setSureVisible(View.GONE);
                milkOperateResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        milkOperateResultDialog.dismiss();
                    }
                }, 500);
                etAmount.setText("");
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

    private void initView(View view) {
        rlScan = view.findViewById(R.id.rl_milkreceive_scan);
        tvPatinfo = view.findViewById(R.id.tv_milkreceive_pat);
        tvRegno = view.findViewById(R.id.tv_milkreceive_reg);
        tvTime = view.findViewById(R.id.tv_milkreceive_time);
        tvType = view.findViewById(R.id.tv_milkreceive_type);
        LlTime = view.findViewById(R.id.ll_milkreceive_time);
        llType = view.findViewById(R.id.ll_milkreceive_type);
        etAmount = view.findViewById(R.id.et_milkamount);
        LlTime.setOnClickListener(this);
        llType.setOnClickListener(this);


        //        Calendar calendar = Calendar.getInstance();
        //        long mCurrentMillSeconds = calendar.getTimeInMillis();
        //        Date date = new Date(mCurrentMillSeconds);
        //        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//精确到分钟
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        //        timestr = format1.format(date);
        //        datestr = format.format(date);
        //
        //        tvTime.setText(datestr + " " + timestr);


    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//精确到分钟
        String time = format.format(date);
        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formattime = new SimpleDateFormat("HH:mm");
        datestr = formatdate.format(date);
        timestr = formattime.format(date);
        //        showToast(datestr+"--"+timestr);
        tvTime.setText(time);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                bagNo = bundle.getString("data");
                initData(bundle.getString("data"));
        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_wl_receive, container, false);
    }

    private void initData(String bagNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("bagNo", bagNo);
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        MilkLoopApiManager.getMilkReceiveBagInfo(map, "getMilkBagInfoWL", new MilkLoopApiManager.MilkReceiveBagInfoCallback() {
            @Override
            public void onSuccess(MilkReceiveBagInfoBean milkReceiveBagInfoBean) {
                String bed = milkReceiveBagInfoBean.getPatInfo().getBedCode().equals("") ? "未分床" : milkReceiveBagInfoBean.getPatInfo().getBedCode();
                String name = milkReceiveBagInfoBean.getPatInfo().getPatName();
                tvPatinfo.setText(bed + "   " + name);
                tvRegno.setText(milkReceiveBagInfoBean.getPatInfo().getRegNo());
                rlScan.setVisibility(View.GONE);
                etAmount.setText("");
                viewright.setVisibility(View.VISIBLE);

                 receiveType = milkReceiveBagInfoBean.getRecTyps().split("\\^");
                //默认采集时间为当前时间
                tvTime.setText(DateUtils.getDateTimeFromSystem());
                tvType.setText("");
                datestr = DateUtils.getDateFromSystem();
                timestr = DateUtils.getTimeFromSystem();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_milkreceive_time:
                if (tvTime.getText().toString().contains("0")) {
                    chooseTime(TimeUtils.string2Millis(tvTime.getText().toString() + ":00"));
                } else {
                    chooseTime(TimeUtils.string2Millis(SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).replace("/", "-").replace(",", " ")));
                }
                break;
            case R.id.ll_milkreceive_type:
//                List list = new ArrayList();
//                list.add("冷冻");
//                list.add("冷藏");
//                list.add("常温");
                OptionPicker picker = new OptionPicker(getActivity(),receiveType);
                picker.setCanceledOnTouchOutside(false);
                picker.setDividerRatio(WheelView.DividerConfig.FILL);
                picker.setSelectedIndex(0);
                picker.setCycleDisable(true);
                picker.setTextSize(20);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        tvType.setText(item);
                    }});
                picker.show();
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
                .setTitleStringId("选择日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getFragmentManager(), "ALL");

    }


}
