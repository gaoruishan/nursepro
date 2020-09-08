package com.dhcc.module.nurse.bloodsugar;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.adapter.BloodSugarPatAdapter;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarValueAndItemBean;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.module.nurse.bloodsugar
 * <p>
 * author Q
 * Date: 2020/8/20
 * Time:11:29
 */
public class BloodSugarRecordFragment extends BaseNurseFragment {

    private CustomDateTimeView customDate;
    private FlowLayout flOp;
    private TextView tvState;
    private EditText etValue,etNote;
    private String episodeId = "",rowId="",sugarRowId="";
    private ArrayList<TextView> tvList = new ArrayList<>();
    private BloodSugarValueAndItemBean itemBean;

    @Override
    protected void initViews() {
        super.initViews();
        flOp = f(R.id.fl_option, FlowLayout.class);
        tvState = f(R.id.tv_sugar_state, TextView.class);
        etValue = f(R.id.et_sugar_value, EditText.class);
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etValue.setBackgroundResource(R.drawable.vital_sign_input_bg);
                if (itemBean!=null && itemBean.getSugarList().size()>0){
                    for (int i = 0; i < itemBean.getSugarList().size(); i++) {
                        if (itemBean.getSugarList().get(i).getDesc().equals(tvState.getText().toString())){
                            if (isNumber(s.toString()) && itemBean.getSugarList().get(i).getNormalValueRangFrom() != null) {
                                if (itemBean.getSugarList().get(i).getErrorValueLowTo() != null) {
                                    if (isNumber(etValue.getText().toString() + "")) {
                                        double edDou = Double.parseDouble(etValue.getText().toString());
                                        if (edDou >= Double.parseDouble(itemBean.getSugarList().get(i).getErrorValueHightFrom()) || edDou <= Double.parseDouble(itemBean.getSugarList().get(i).getErrorValueLowTo())) {
                                            etValue.setBackground(getResources().getDrawable(R.drawable.vital_sign_inputerror_bg));
                                        } else if ((Double.parseDouble(itemBean.getSugarList().get(i).getErrorValueLowTo()) < edDou && edDou < Double.parseDouble(itemBean.getSugarList().get(i).getNormalValueRangFrom()))
                                                || (edDou < Double.parseDouble(itemBean.getSugarList().get(i).getErrorValueHightFrom()) && edDou > Double.parseDouble(itemBean.getSugarList().get(i).getNormalValueRangTo()))) {
                                            etValue.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                        } else {
                                            etValue.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                        }
                                    } else {
                                        etValue.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                        if (itemBean.getSugarList().get(i).getSymbol() != null) {
                                            if (itemBean.getSugarList().get(i).getSymbol().size() > 0) {
                                                etValue.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                            }
                                        }
                                    }
                                } else {
                                    if (isNumber(etValue.getText().toString() + "")) {
                                        double edDou = Double.parseDouble(etValue.getText().toString());
                                        if (edDou < Double.parseDouble(itemBean.getSugarList().get(i).getNormalValueRangFrom())
                                                || edDou > Double.parseDouble(itemBean.getSugarList().get(i).getNormalValueRangTo())) {
                                            etValue.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                        } else {
                                            etValue.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                        }
                                    } else {
                                        etValue.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                        if (itemBean.getSugarList().get(i).getSymbol() != null) {
                                            if (itemBean.getSugarList().get(i).getSymbol().size() > 0) {
                                                etValue.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        });

        etValue.setRawInputType(Configuration.KEYBOARD_QWERTY);
        etNote = f(R.id.et_note, EditText.class);
        tvState.setOnClickListener(this);
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.showOnlyOne();
        customDate.setStartDateTime(SchDateTimeUtil.getCurDateTime());
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarRecord();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarRecord();
            }
        });

    }

    @Override
    protected void initDatas() {
        super.initDatas();
        addToolBarRightTextView("保存", R.color.white);
        if (bundle!=null){
            setToolbarCenterTitle(bundle.getString("patInfo"));
            episodeId = bundle.getString("episodeId");
        }
        getBloodSugarRecord();
    }


    private void getBloodSugarRecord(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BloodSugarApiManager.getBloodSugarValueAndItem(customDate.getStartDateTimeText(), episodeId, new CommonCallBack<BloodSugarValueAndItemBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(BloodSugarValueAndItemBean bean, String type) {
                hideLoadingTip();

                if (!TextUtils.isEmpty(bean.getCurDateTime())){
                    if (bean.getCurDateTime().length() == 16) {
                        customDate.setStartDateTime(TimeUtils.string2Millis(bean.getCurDateTime(),"yyyy-MM-dd HH:mm"));
                    }

                }
                itemBean = bean;
                tvState.setText(bean.getSugarList().get(0).getDesc());
                etValue.setText(bean.getSugarList().get(0).getValue());
                rowId = bean.getSugarList().get(0).getRowId();
                sugarRowId = bean.getSugarList().get(0).getSugarRowId();
                setOptions(0);
            }
        });
    }

    private void saveBloodSugar(String sugarInfo){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BloodSugarApiManager.getSaveBloodSugarResult(sugarInfo, new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                hideLoadingTip();
                ToastUtils.showShort(bean.getMsg());
                finish();
            }
        });
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        TaskViewApiManager.getScaninfo(scanInfo, new CommonCallBack<ScanResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }
            @Override
            public void onSuccess(ScanResultBean bean, String type) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_toolbar_right) {
            Map sugarMap = new HashMap();
            sugarMap.put("sugarRowID",sugarRowId);
            sugarMap.put("episodeId",episodeId);
            sugarMap.put("rowId",rowId);
            sugarMap.put("value",etValue.getText().toString());
            sugarMap.put("user", SPUtils.getInstance().getString(SharedPreference.USERID));
            sugarMap.put("userLocId",SPUtils.getInstance().getString(SharedPreference.LOCID));
            sugarMap.put("date",customDate.getStartDateTimeText().substring(0,10));
            sugarMap.put("time",customDate.getStartDateTimeText().substring(11,16));
            sugarMap.put("obsNote",etNote.getText().toString());

            saveBloodSugar(GsonUtils.toJson(sugarMap));
        }
        if (v.getId() == R.id.tv_sugar_state){
            String[] items = new String[itemBean.getSugarList().size()];
            for (int i = 0; i < itemBean.getSugarList().size(); i++) {
                items[i] = itemBean.getSugarList().get(i).getDesc();
            }
            if (items.length>0){
                OptionPicker picker = new OptionPicker((Activity) mContext, items);
                picker.setCanceledOnTouchOutside(false);
                picker.setDividerRatio(WheelView.DividerConfig.FILL);
                picker.setSelectedIndex(0);
                picker.setCycleDisable(true);
                picker.setTextSize(20);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        tvState.setText(item);
                        etValue.setText(itemBean.getSugarList().get(index).getValue());
                        rowId = itemBean.getSugarList().get(index).getRowId();
                        sugarRowId = itemBean.getSugarList().get(index).getSugarRowId();
                        setOptions(index);
                    }
                });
                picker.show();
            }
        }
    }

    private void setOptions(int index){
        tvList = new ArrayList<>();
        flOp.removeAllViews();
        for (int i = 0; i < itemBean.getSugarList().get(index).getOptions().size(); i++) {
            TextView tvButton = new TextView(getContext());
            tvButton.setText(itemBean.getSugarList().get(index).getOptions().get(i));
            tvButton.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(60), ConvertUtils.dp2px(30));
            titleParams.setMargins(0, 5, 15, 0);//4个参数按顺序分别是左上右下
            tvButton.setLayoutParams(titleParams);
            tvButton.setTextColor(Color.GRAY);
            tvButton.setBackgroundResource(R.drawable.dhcc_bg_selected_gray);
            tvButton.setTextSize(15);
            tvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < tvList.size(); j++) {
                        tvList.get(j).setTextColor(Color.GRAY);
                        tvList.get(j).setBackgroundResource(R.drawable.dhcc_bg_selected_gray);
                    }
                    tvButton.setTextColor(getResources().getColor(R.color.blue));
                    tvButton.setBackgroundResource(R.drawable.bg_text_area);
                    etValue.setText(tvButton.getText().toString());

                }
            });
            flOp.addView(tvButton);
            tvList.add(tvButton);
        }

    }
    public boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_sugar_record;
    }
}

