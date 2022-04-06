package com.dhcc.nursepro.workarea.nurrecord;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecord.api.NurRecordManager;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nex3z.flowlayout.FlowLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * com.dhcc.nursepro.workarea.nurrecord
 * <p>
 * author Q
 * Date: 2018/12/3
 * Time:9:49
 */
public class LinkModelFragment extends BaseFragment implements OnDateSetListener {
    private FlowLayout recordContentView;
    private TextView tvsend;

    private TextView textViewChooseDateTime;
    private String dt = "date";

    private List<NurRecordBean.ModelListBean> listBeans;
    private NurRecordBean nurRecordBean;
    private HashMap<String, View> viewItemMap;
    private Map patInfoMap = new HashMap<String, String>();
    private String strSend = "";

    private ItemValueDialog showDialog;

    private String emrCode = "";
    private String episodeId = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modeldetaillink, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle("护理病历二级模板", 0xffffffff, 17);

        Bundle bundle = getArguments();
        emrCode = bundle.getString("EmrCode");
        episodeId = bundle.getString("episodeId");

        initview(view);
        initData();

    }

    private void initview(View view) {
        tvsend = view.findViewById(R.id.tv_save);

        tvsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSend = "";
                for (int i = 0; i < listBeans.size(); i++) {
                    if (listBeans.get(i).getItemCode().startsWith("Item")) {
                        strSend = strSend + "^" + listBeans.get(i).getSendValue();
                    }
                    if ("1".equals(listBeans.get(i).getMustFill())) {
                        if (StringUtils.isEmpty(listBeans.get(i).getSendValue())) {
                            showToast(listBeans.get(i).getItemDesc() + "--未填写");
                            return;
                        }
                    }
                    if (i == listBeans.size() - 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("linkstrsend", strSend);
                        finish(bundle);
                    }
                }
            }
        });
        recordContentView = view.findViewById(R.id.fl_modellink);
        viewItemMap = new HashMap<>();
    }

    private void initData() {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        map.put("EmrCode", emrCode);
        map.put("episodeId", episodeId);

        NurRecordManager.getModelDetailListMsg(map, "getModelDetail", new NurRecordManager.getModelDetailCallBack() {
            @Override
            public void onSuccess(NurRecordBean modelDetailBean) {
                nurRecordBean = modelDetailBean;
                listBeans = modelDetailBean.getModelList();
                Gson gson = new Gson();
                String result = gson.toJson(nurRecordBean.getPatInfo());
                patInfoMap = gson.fromJson(result, HashMap.class);
                drawInputItems();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }


    /**
     * 绘制UI相关
     */
    public void drawInputItems() {
        for (int i = 0; i < listBeans.size(); i++) {
            NurRecordBean.ModelListBean config = listBeans.get(i);
            recordContentView.addView(drawItem(config));
        }
    }

    /**
     * 区分不同View类型，添加进容器
     *
     * @param config
     * @return
     */
    private LinearLayout drawItem(NurRecordBean.ModelListBean config) {

        int height = ConvertUtils.dp2px(Float.parseFloat(config.getHeight()));
        int width = ConvertUtils.dp2px(Float.parseFloat(config.getWidth()));

        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);

        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        //统一名称textview
        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getItemDesc());
        titleTV.setTextSize(Float.parseFloat(config.getFontSize()));

        //判断是否必填，必填的话字体变红
        if ("1".equals(config.getMustFill())) {
            titleTV.setTextColor(ContextCompat.getColor(getActivity(), R.color.nurrecord_text_mustfill_color));
        } else {
            titleTV.setTextColor(ContextCompat.getColor(getActivity(), R.color.nurrecord_text_normal_color));
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.setMargins(ConvertUtils.dp2px(5), 0, 5, 0);
        titleTV.setLayoutParams(titleParams);
        titleTV.setGravity(Gravity.TOP);

        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(config.getToastStr())) {
                    showToast(config.getToastStr());
                }
            }
        });
        layout.addView(titleTV);

        if ("E".equals(config.getItemType())) {
            //输入框
            if ("0".equals(config.getTitleHiddeFlag())) {
                titleTV.setVisibility(View.GONE);
            }
            EditText edText = new EditText(getContext());
            int sw = ScreenUtils.getScreenWidth();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            edText.setLayoutParams(layoutParams);
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
            edText.setPadding(ConvertUtils.dp2px(5), 0, ConvertUtils.dp2px(5), 0);
            edText.setTextSize(Float.parseFloat(config.getFontSize()));
            edText.setSingleLine();
            viewItemMap.put(config.getItemCode(), edText);
            edText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDialog = new ItemValueDialog(getActivity(), edText.hasFocusable());
                    showDialog.setTitle(config.getItemDesc());
                    showDialog.setMessage(edText.getText() + "");
                    showDialog.setYesOnclickListener("确定", new ItemValueDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            edText.setText(showDialog.getMessage());
                            edText.setSelection(edText.getText().length());
                            showDialog.dismiss();
                        }
                    });
                    showDialog.show();
                    return false;
                }
            });
            //是否可编辑
            if ("1".equals(config.getEditFlag())) {
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_normal_color));
            } else {
                edText.setFocusable(false);
                edText.setTextColor(getResources().getColor(R.color.nurrecord_edit_defaultvalue_color));
                edText.setText(config.getPatInfo());
            }
            //根据默认内容优先级填入默认值
            if (StringUtils.isEmpty(config.getPatInfo())) {
                edText.setText(config.getItemdeValue());
                config.setSendValue(config.getItemdeValue() + "");
            } else {
                edText.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
            }
            edText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(edText.getText().toString() + "----");
                }
            });


            layout.addView(edText);
            viewItemMap.put(config.getItemCode(), edText);
            if (!StringUtils.isEmpty(config.getUnit())) {
                TextView tvUnit = new TextView(getContext());
                LinearLayout.LayoutParams unitParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvUnit.setLayoutParams(unitParams);
                tvUnit.setText(config.getUnit() + "");
                layout.addView(tvUnit);
            }


        } else if ("C".equals(config.getItemType())) {
            //多选
            if ("0".equals(config.getSingleCheck())) {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 0, 0);
                layout.setLayoutParams(params1);
                config.setSendValue(config.getItemdeValue() + "");
                FlowLayout flowCheckGroup = new FlowLayout(getContext());
                String[] split = config.getItemValue().split(";");
                String[] devalue = config.getItemdeValue().split(",");

                List listCk = new ArrayList();
                for (int i = 0; i < split.length; i++) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(split[i] + "");
                    Map mapCk = new HashMap();
                    mapCk.put("value", cb.getText());
                    mapCk.put("isSel", "false");
                    for (int j = 0; j < devalue.length; j++) {
                        if (split[i].equals(devalue[j])) {
                            cb.setChecked(true);
                            if (cb.getText().equals(devalue[j])) {
                                mapCk.put("isSel", "true");
                            }
                        }
                    }
                    cb.setTextSize(Float.parseFloat(config.getFontSize()));
                    cb.setHeight(height);

                    listCk.add(mapCk);
                    if ("false".equals(config.getEditFlag())) {
                        cb.setEnabled(false);
                        //                        config.setEditFlag("true");
                    } else {
                        cb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String allSel = "";
                                if (cb.isChecked()) {
                                    mapCk.put("isSel", "true");
                                } else {
                                    mapCk.put("isSel", "false");
                                }
                                config.setSendValue(getckvalue((ArrayList<HashMap>) listCk) + "");
                                showToast(getckvalue((ArrayList<HashMap>) listCk));
                                if (config.getLinkInfo().size() > 0) {
                                    linkView(config.getLinkInfo(), getckvalue((ArrayList<HashMap>) listCk) + "", cb.isChecked(), "isC");
                                }
                            }
                        });
                    }
                    flowCheckGroup.addView(cb);
                }
                layout.addView(flowCheckGroup);
                viewItemMap.put(config.getItemCode(), flowCheckGroup);
                //单选
            } else {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                radioGroup.setLayoutParams(params2);

                if (!StringUtils.isEmpty(config.getPatInfo())) {
                    config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
                } else {
                    config.setSendValue(config.getItemdeValue() + "");
                }

                config.setSendValue(config.getItemdeValue() + "");
                String[] split = config.getItemValue().split(";");
                for (int i = 0; i < split.length; i++) {
                    RadioButton rb = new RadioButton(getContext());
                    rb.setId(i);
                    rb.setTextSize(Float.parseFloat(config.getFontSize()));
                    rb.setText(split[i] + "");
                    if ("false".equals(config.getEditFlag())) {
                        rb.setEnabled(false);
                    } else {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast(rb.getText() + "");
                                config.setSendValue(rb.getText() + "");
                                if (config.getLinkInfo().size() > 0) {
                                    linkView(config.getLinkInfo(), rb.getText() + "", rb.isChecked(), "isR");
                                }
                            }
                        });
                    }
                    radioGroup.addView(rb);
                    if (split[i].equals(patInfoMap.get(config.getPatInfo()))) {
                        radioGroup.check(rb.getId());
                    } else if (split[i].equals(config.getItemdeValue())) {
                        radioGroup.check(rb.getId());
                    }
                }
                layout.addView(radioGroup);

                viewItemMap.put(config.getItemCode(), radioGroup);

            }
        } else if ("R".equals(config.getItemType())) {
            //单选
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params1);
            FlowRadioGroup radioGroup = new FlowRadioGroup(getContext());
            radioGroup.setOrientation(LinearLayout.HORIZONTAL);
            radioGroup.setLayoutParams(params1);


            if (!StringUtils.isEmpty(config.getPatInfo())) {
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
            } else {
                config.setSendValue(config.getItemdeValue() + "");
            }

            String[] split = config.getItemValue().split("!");
            for (int i = 0; i < split.length; i++) {
                RadioButton rb = new RadioButton(getContext());
                rb.setId(i);
                rb.setHeight(height);
                rb.setTextSize(Float.parseFloat(config.getFontSize()));
                rb.setText(split[i] + "");
                //判断是否可编辑
                if ("false".equals(config.getEditFlag())) {
                    rb.setEnabled(false);
                } else {
                    rb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showToast(rb.getText() + "----");
                            config.setSendValue(rb.getText() + "");
                            if (config.getLinkInfo().size() > 0) {
                                linkView(config.getLinkInfo(), rb.getText() + "", rb.isChecked(), "isR");
                            }
                        }
                    });
                }
                radioGroup.addView(rb);
                if (split[i].equals(patInfoMap.get(config.getPatInfo()))) {
                    radioGroup.check(rb.getId());
                } else if (split[i].equals(config.getItemdeValue())) {
                    radioGroup.check(rb.getId());
                }

            }


            layout.addView(radioGroup);
            viewItemMap.put(config.getItemCode(), radioGroup);


        } else if ("T".equals(config.getItemType())) {
            //textview额外设置

            //判断是否可点击跳转其他病例填充表格
            if (config.getLinkInfo().size() > 0) {
                titleTV.setTextColor(getResources().getColor(R.color.blue));
                layout.setBackgroundColor(0);
                titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击进入新的模板" + config.getLinkInfo().get(0).getLinkModel() + "给:" + config.getLinkInfo().get(0).getLinkItemCode());
                        View view = viewItemMap.get("Item81");
                        if (view instanceof EditText) {
                            EditText ed = (EditText) view;
                            ed.setText("from__" + config.getItemCode());
                        }

                    }
                });
            }
            viewItemMap.put(config.getItemCode(), titleTV);

            //判断是否单行显示
            if ("0".equals(config.getTitleHiddeFlag())) {
                TextView tvalue = new TextView(getContext());
                //如果后面有图片，不单独占一行
                if (StringUtils.isEmpty(config.getImageName())) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleTV.setLayoutParams(layoutParams);
                }
            } else {
                //                TextView tvalue = new TextView(getContext());
                //                tvalue.setText(config.getItemdeValue() + "===");
                //                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                //                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                //                layout.addView(tvalue);
            }
        } else if ("TN".equals(config.getItemType())) {
            //textview额外设置

            //判断是否可点击跳转其他病例填充表格
            if (config.getLinkInfo().size() > 0) {
                titleTV.setTextColor(getResources().getColor(R.color.blue));
                layout.setBackgroundColor(0);
                titleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击进入新的模板" + config.getLinkInfo().get(0).getLinkModel() + "给:" + config.getLinkInfo().get(0).getLinkItemCode());
                        View view = viewItemMap.get("Item81");
                        if (view instanceof EditText) {
                            EditText ed = (EditText) view;
                            ed.setText("from__" + config.getItemCode());
                        }

                    }
                });
            }
            viewItemMap.put(config.getItemCode(), titleTV);

            //判断是否单行显示
            if ("0".equals(config.getTitleHiddeFlag())) {
                //                TextView tvalue = new TextView(getContext());
                //                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(height, ViewGroup.LayoutParams.MATCH_PARENT);
                //                titleTV.setLayoutParams(layoutParams);
            } else {
                //                TextView tvalue = new TextView(getContext());
                //                tvalue.setText(config.getItemdeValue() + "===");
                //                //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //                tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
                //                tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
                //                layout.addView(tvalue);
            }
        } else if ("D".equals(config.getItemType())) {
            //日期选择
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "2018-12-11");
            if (StringUtils.isEmpty(config.getPatInfo())) {
                tvalue.setText(config.getItemdeValue());
                config.setSendValue(config.getItemdeValue() + "");
            } else {
                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
            }
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(tvalue.getText() + "");
            //判断是否可编辑
            if ("false".equals(config.getEditFlag())) {
                tvalue.setEnabled(false);
            } else {
                tvalue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textViewChooseDateTime = tvalue;
                        dt = "date";
                        //                        if (textViewChooseDateTime.getText().toString().contains("-")) {
                        //                            chooseDate(TimeUtils.string2Millis(textViewChooseDateTime.getText().toString() + " 00:00:00"));
                        //                        } else {
                        //                            chooseDate(TimeUtils.string2Millis(SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).replace("/", "-").replace(",", " ")));
                        //                        }
                        chooseDate();
                    }
                });
            }

            tvalue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(tvalue.getText().toString() + "----");
                }
            });


            layout.addView(tvalue);
            viewItemMap.put(config.getItemCode(), tvalue);
        } else if ("Ti".equals(config.getItemType())) {
            //时间选择
            TextView tvalue = new TextView(getContext());
            tvalue.setText(config.getItemdeValue() + "11:11");
            if (StringUtils.isEmpty(config.getPatInfo())) {
                tvalue.setText(config.getItemdeValue());
                config.setSendValue(config.getItemdeValue() + "");
            } else if (!StringUtils.isEmpty(patInfoMap.get(config.getPatInfo()) + "")) {
                tvalue.setText((patInfoMap.get(config.getPatInfo()) + ""));
                config.setSendValue((patInfoMap.get(config.getPatInfo()) + ""));
            }
            tvalue.setTextSize(Float.parseFloat(config.getFontSize()));
            tvalue.setGravity(Gravity.CENTER_HORIZONTAL);
            config.setSendValue(tvalue.getText() + "");
            //判断是否可编辑
            if ("false".equals(config.getEditFlag())) {
                tvalue.setEnabled(false);
            } else {
                tvalue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textViewChooseDateTime = tvalue;
                        dt = "time";
                        chooseTime();
                    }
                });
            }

            tvalue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    config.setSendValue(tvalue.getText().toString() + "----");
                }
            });
            layout.addView(tvalue);
            viewItemMap.put(config.getItemCode(), tvalue);
        } else {
            showToast("出现未知类型控件，请联系后台进行数据修复或更新应用");
            //            //选择框
            //            List ll = new ArrayList();
            //            ll.add("1");
            //            ll.add("2");
            //            final OptionView optionView = new OptionView(getActivity(), ll);
            //            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 50);
            //            optionView.setLayoutParams(layoutParams);
            //            optionView.setTextSize(16);
            //            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            //            optionView.setGravity(Gravity.CENTER);
            //
            //            optionView.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) {
            //                    optionView.showPicker();
            //                }
            //            });
            //
            //            layout.addView(optionView);
            //            viewItemMap.put(config.getItemCode(), optionView);
        }

        //判断是否与图片，有的话加载
        if (!StringUtils.isEmpty(config.getImageName())) {
            LinearLayout.LayoutParams paramsimg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(paramsimg);
            //            downImage(imageView,config.getImageName());
            downImage(imageView, "http://10.1.5.87/dhcmg/2229.gif");
            layout.addView(imageView);
        }
        return layout;
    }

    private String getckvalue(ArrayList<HashMap> list) {
        String strck = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("isSel").equals("true")) {
                if ("".equals(strck)) {
                    strck = list.get(i).get("value") + "";
                } else {
                    strck = strck + "," + list.get(i).get("value") + "";
                }
            }
        }
        return strck;
    }

    private void linkView(List<NurRecordBean.ModelListBean.LinkInfoBean> LinkInfo, String selRadio, Boolean isSel, String isRorC) {

        if (isRorC.equals("isC")) {

            //多选评分
            if (LinkInfo.size() > 0) {
                int num = 0;
                String[] split = selRadio.split(",");
                for (int i = 0; i < LinkInfo.size(); i++) {
                    if (selRadio.contains(LinkInfo.get(i).getLinkRangeCon())) {
                        num = num + Integer.parseInt(LinkInfo.get(i).getReValue());
                        showToast(num + "---");
                    }
                }
                View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());
                if (view instanceof EditText) {
                    EditText ed = (EditText) view;
                    ed.setText(num + "");
                }
                return;
            }


            //单选判断是否可变为可编辑
            String rangcon = LinkInfo.get(0).getLinkRangeCon();
            if ((selRadio).contains("其它")) {
                View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());


                if (view instanceof EditText) {
                    EditText ed = (EditText) view;
                    if (isSel) {
                        ed.setText("此时可编辑" + LinkInfo.size());
                        ed.setFocusable(true);
                    } else {
                        ed.setText("此时不可编辑" + LinkInfo.size());
                        ed.setFocusable(false);
                    }
                }
            }
            //单选判断点击的值是否关联值，再判断是否点击，都符合的话关联成功
        } else if (isRorC.equals("isR")) {
            String rangcon = LinkInfo.get(0).getLinkRangeCon();
            View view = viewItemMap.get(LinkInfo.get(0).getLinkItemCode());
            if (view instanceof EditText) {
                EditText ed = (EditText) view;
                if ("其它".equals(selRadio) && isSel) {
                    ed.setText("此时可编辑" + LinkInfo.size());
                    ed.setFocusable(true);
                    ed.setFocusableInTouchMode(true);
                } else {
                    ed.setText("此时不可编辑" + LinkInfo.size());
                    ed.setFocusable(false);
                    ed.setFocusableInTouchMode(false);
                }
            }
            //赋值判断关联项如何编辑
        } else if (isRorC.equals("isE")) {

        }
    }

    /**
     * 选择日期---年月日
     */
    private void chooseDate() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(SchDateTimeUtil.getCurDateTime() - tenYears)
                .setCurrentMillseconds(SchDateTimeUtil.getCurDateTime())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");

    }

    /**
     * 选择时间---时分
     */
    private void chooseTime() {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getFragmentManager(), "ALL");
    }

    //加载图片
    private void downImage(ImageView view, String strUrl) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request
                .Builder()
                .get()
                .url(strUrl)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showToast("下载图片失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                //将图片显示到ImageView中
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        });
    }

    /**
     * 选择日期---年月日
     */
    private void chooseDate(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
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

    private void changeView(View view) {
        if (view instanceof EditText) {
            EditText ed = (EditText) view;
        } else if (view instanceof RadioGroup) {

        } else if (view instanceof TextView) {

            //判断后面多选框是否可编辑，更改可编辑状态
        } else if (view instanceof FlowLayout) {
            int cknum = ((FlowLayout) view).getChildCount();
            for (int i = 0; i < cknum; i++) {
                if ((((FlowLayout) view).getChildAt(i)) instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) ((FlowLayout) view).getChildAt(i);
                    Log.v("11111ck", checkBox.getText().toString());
                }
            }

        }
    }

    /**
     * 日期时间选择回调
     *
     * @param timePickerView
     * @param millseconds
     */
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String date = TimeUtils.millis2String(millseconds).substring(0, 11);
        String time = TimeUtils.millis2String(millseconds).substring(11, 16);
        if ("date".equals(dt)) {
            textViewChooseDateTime.setText(date + "");

        } else {
            textViewChooseDateTime.setText(time + "");

        }
    }
}
