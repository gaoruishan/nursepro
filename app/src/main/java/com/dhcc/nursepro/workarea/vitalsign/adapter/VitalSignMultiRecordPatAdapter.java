package com.dhcc.nursepro.workarea.vitalsign.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.KeyBoardUtil;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.bean.GetTempByPatListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class VitalSignMultiRecordPatAdapter extends BaseQuickAdapter<GetTempByPatListBean.PatListBean, BaseViewHolder> {
    private List<GetTempByPatListBean.TempConfigBean> tempConfigBeanList;
    private HashMap<String, HashMap<String, String>> dataMap = new HashMap<>();
    private KeyboardView mKeyboard;

    public VitalSignMultiRecordPatAdapter(@Nullable List<GetTempByPatListBean.PatListBean> data) {
        super(R.layout.item_vitalsign_multirecord_pat, data);
    }

    public void setTempConfigBeanList(List<GetTempByPatListBean.TempConfigBean> tempConfigBeanList) {
        this.tempConfigBeanList = tempConfigBeanList;
    }

    public HashMap<String, HashMap<String, String>> getDataMap() {
        return dataMap;
    }

    public void setmKeyboard(KeyboardView mKeyboard) {
        this.mKeyboard = mKeyboard;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTempByPatListBean.PatListBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        LinearLayout llVitalSign = helper.getView(R.id.ll_vitalsign_content);

        if (tempConfigBeanList != null) {

            tvName.setText(item.getBedCode() + "\n" + item.getPatName());
            llVitalSign.removeAllViews();
            for (int i = 0; i < tempConfigBeanList.size(); i++) {
                GetTempByPatListBean.TempConfigBean config = tempConfigBeanList.get(i);
                EditText editText = new EditText(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(ConvertUtils.dp2px(5), ConvertUtils.dp2px(5), ConvertUtils.dp2px(5), ConvertUtils.dp2px(5));
                editText.setLayoutParams(params);
                editText.setTextColor(mContext.getResources().getColor(R.color.vital_sign_record_next_color));
                editText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                editText.setSingleLine(true);
                editText.setTag(config.getCode());
                editText.setText(item.getTempList().get(i).getValue());
                editText.setGravity(Gravity.CENTER);

                editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                editText.setRawInputType(Configuration.KEYBOARD_QWERTY);

                editText.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        if (v.hasFocus()) {
                            if (config.getDesc().contains("℃")) {
                                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                new KeyBoardUtil(mKeyboard, editText).showKeyboard();
                            } else {
                                mKeyboard.setVisibility(View.GONE);
                            }
                        }
                    }
                });
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            if (config.getDesc().contains("℃")) {
                                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                new KeyBoardUtil(mKeyboard, editText).showKeyboard();
                            } else {
                                mKeyboard.setVisibility(View.GONE);
                            }
                        }
                    }
                });

                if (config.getSymbol() != null) {
                    if (config.getSymbol().size() > 0) {
                        editText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                        editText.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                symbol((ArrayList<String>) config.getSymbol(), editText);
                                return false;
                            }
                        });
                    }
                }


                int vsitem = i;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        item.getTempList().get(vsitem).setValue(s.toString());
                        HashMap<String, String> editData = dataMap.get(item.getEpisodeId() + "^" + item.getPatName());
                        if (editData == null) {
                            editData = new HashMap<>();
                        }
                        editData.put(config.getCode(), s.toString());
                        dataMap.put(item.getEpisodeId() + "^" + item.getPatName(), editData);

                        if (config.getNormalValueRangFrom() != null) {
                            if (config.getErrorValueLowTo() != null) {
                                if (isNumber(s.toString() + "")) {
                                    double edDou = Double.parseDouble(s.toString());
                                    if (edDou >= Double.parseDouble(config.getErrorValueHightFrom()) || edDou <= Double.parseDouble(config.getErrorValueLowTo())) {
                                        editText.setBackground(mContext.getResources().getDrawable(R.drawable.vital_sign_inputerror_bg));
                                    } else if ((Double.parseDouble(config.getErrorValueLowTo()) < edDou && edDou < Double.parseDouble(config.getNormalValueRangFrom()))
                                            || (edDou < Double.parseDouble(config.getErrorValueHightFrom()) && edDou > Double.parseDouble(config.getNormalValueRangTo()))) {
                                        editText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                    } else {
                                        editText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                    }
                                } else {
                                    editText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                    if (config.getSymbol() != null && config.getSymbol().size() > 0) {
                                        editText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);

                                    }
                                }
                            } else {
                                if (isNumber(s.toString() + "")) {
                                    double edDou = Double.parseDouble(s.toString());
                                    if (edDou < Double.parseDouble(config.getNormalValueRangFrom())
                                            || edDou > Double.parseDouble(config.getNormalValueRangTo())) {
                                        editText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                    } else {
                                        editText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                    }
                                } else {
                                    editText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                    if (config.getSymbol() != null && config.getSymbol().size() > 0) {
                                        editText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);

                                    }
                                }
                            }


                        }
                    }
                });

                llVitalSign.addView(editText);

            }

        }

        helper.addOnClickListener(R.id.tv_callmonitor);
        helper.addOnClickListener(R.id.tv_singlerecord);
    }

    private void symbol(ArrayList<String> list, EditText editText) {
        String[] locDesc = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            locDesc[i] = list.get(i);
        }

        final OptionPicker picker = new OptionPicker((Activity) mContext, locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                editText.setText(item);
            }
        });
        picker.show();
    }

    private boolean isNumber(Object obj) {
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
}
