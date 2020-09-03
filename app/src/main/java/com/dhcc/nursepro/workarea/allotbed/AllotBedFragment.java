package com.dhcc.nursepro.workarea.allotbed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.uiplugs.OptionView;
import com.base.commlibs.utils.KeyBoardUtil;
import com.dhcc.nursepro.workarea.allotbed.adapter.EmptyBedListAdapter;
import com.dhcc.nursepro.workarea.allotbed.api.AllotBedApiManager;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
import com.dhcc.nursepro.workarea.allotbed.bean.FirstBeHosTemDataBean;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.dhcc.nursepro.workarea.allotbed.bean.IsFirstToBedBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.nursepro.workarea.beddistribution
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:9:41
 */
public class AllotBedFragment extends BaseFragment implements View.OnClickListener {
    private ScrollView scrollBedallot;
    private RecyclerView recAllotBed;
    private EmptyBedListAdapter emptyBedListAdapter;
    private List<AllotBedInfoBean.EmptyBedListBean> listBeans = new ArrayList<>();
    private List<AllotBedInfoBean.DoctorListBean> listDocBeans = new ArrayList<>();
    private List<AllotBedInfoBean.NurseListBean> listNurBeans = new ArrayList<>();
    private String selectBedId = "", selectDoc = "", selectNur = "";
    private int selectNurIndex = 0, selectDocIndex = 0, selectPosition = -1;
    private TextView tvMainDoc, tvMainNur, tvPatInfo, tvRegNo;
    private ImageView imgSex;
    private RelativeLayout rlAllotBedScan;
    private LinearLayout llFirstbehos;
    private LinearLayout llVitalsignRecord;


    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeIdNow = null;

    private String regNoNow = "";

    private View viewright;

    private AllotBedResultDialog allotBedResultDialog;
    private FirstBeHosTemDataBean recordInfo;
    private HashMap<String, View> viewItemMap = new HashMap<>();
    private KeyboardView mKeyboard;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_allotbeds));
        setToolbarBottomLineVisibility(false);

        //右上角保存按钮
        viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   确认   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectBedId == "") {
                    showToast("请选择床位再进行分床");
                } else {
                    allotBed();
                }
            }
        });

        initView(view);
        initAdapter();

    }

    private void allotBed() {

        String result = "";
        if (recordInfo != null) {
            ArrayList<HashMap<String, String>> resList = new ArrayList();

            for (int i = 0; i < recordInfo.getTempList().size(); i++) {
                FirstBeHosTemDataBean.TempListBean temp = recordInfo.getTempList().get(i);
                FirstBeHosTemDataBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
                String code = temp.getCode();
                View view = viewItemMap.get(temp.getCode());
                String value = "";

                if (view instanceof EditText) {
                    EditText edText = (EditText) view;
                    value = edText.getText().toString();

                    //检查输入数据范围
                    if (config.getNormalValueRangFrom() != null) {
                        if (config.getErrorValueLowTo() != null) {
                            if (isNumber(edText.getText().toString() + "")) {
                                double edDou = Double.parseDouble(edText.getText().toString());
                                if (edDou > Double.parseDouble(config.getErrorValueHightFrom()) || edDou < Double.parseDouble(config.getErrorValueLowTo())) {
                                    showToast("请检查输入数值");
                                    return;
                                }
                            }
                        }
                    }

                } else {
                    OptionView op = (OptionView) view;
                    value = op.getText().toString();
                }

                value = value.trim();

                HashMap<String, String> resItem = new HashMap<>();
                resItem.put("code", code);
                resItem.put("value", value);

                resList.add(resItem);
            }

            Gson gson = new Gson();
            result = gson.toJson(resList);
        }


        HashMap<String, String> map = new HashMap<>();
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        map.put("linkLocId", spUtils.getString(SharedPreference.LINKLOC));
        map.put("episodeId", episodeIdNow);
        map.put("bedId", selectBedId);
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        map.put("mainDoc", selectDoc);
        map.put("mainNurse", selectNur);
        map.put("dataArr", result);
        AllotBedApiManager.getAllotBedResult(map, "allotBed", new AllotBedApiManager.getAllotBedResultCallBack() {
            @Override
            public void onSuccess(AllotBedInfoBean allotBedResultBean) {
                listBeans = allotBedResultBean.getEmptyBedList();
                listDocBeans = allotBedResultBean.getDoctorList();
                listNurBeans = allotBedResultBean.getNurseList();
                emptyBedListAdapter.setNewData(listBeans);
                emptyBedListAdapter.notifyDataSetChanged();
                initScanMsg(regNoNow);
                emptyBedListAdapter.setSelectItem(-1);
                emptyBedListAdapter.notifyDataSetChanged();
                selectBedId = "";

                String resultStr = "";

                if (allotBedResultDialog != null && allotBedResultDialog.isShowing()) {
                    allotBedResultDialog.dismiss();
                }

                if (allotBedResultBean.getSet().equals("0")) {
                    llFirstbehos.setVisibility(View.VISIBLE);
                    resultStr = "分床成功\n生命体征未保存成功，请重试";
                    allotBedResultDialog = new AllotBedResultDialog(getActivity());
                    allotBedResultDialog.setExecresult(resultStr);
                    allotBedResultDialog.setImgId(R.drawable.icon_popup_sucess);
                    allotBedResultDialog.setSureVisible(View.VISIBLE);
                    allotBedResultDialog.setSureOnclickListener(new AllotBedResultDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            allotBedResultDialog.dismiss();

                        }
                    });
                    allotBedResultDialog.show();
                } else {
                    if (allotBedResultBean.getSet().equals("")) {
                        llFirstbehos.setVisibility(View.GONE);
                        resultStr = "分床成功";
                    } else if (allotBedResultBean.getSet().equals("1")) {
                        llFirstbehos.setVisibility(View.GONE);
                        resultStr = "分床成功\n生命体征保存成功";
                    }

                    allotBedResultDialog = new AllotBedResultDialog(getActivity());
                    allotBedResultDialog.setExecresult(resultStr);
                    allotBedResultDialog.setImgId(R.drawable.icon_popup_sucess);
                    allotBedResultDialog.setSureVisible(View.GONE);
                    allotBedResultDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            allotBedResultDialog.dismiss();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initView(View view) {
        rlAllotBedScan = view.findViewById(R.id.rl_allotbed_scan);
        scrollBedallot = view.findViewById(R.id.scroll_bedallot);
        tvMainDoc = view.findViewById(R.id.tv_allotbed_maindoc);
        tvMainDoc.setOnClickListener(this);
        tvMainNur = view.findViewById(R.id.tv_allotbed_mainnur);
        tvMainNur.setOnClickListener(this);
        tvPatInfo = view.findViewById(R.id.tv_allotbed_pat);
        imgSex = view.findViewById(R.id.img_allotbed_sex);
        tvRegNo = view.findViewById(R.id.tv_allotbed_regno);

        recAllotBed = view.findViewById(R.id.recy_allotbed);
        //提高展示效率
        recAllotBed.setHasFixedSize(true);
        //设置的布局管理
        recAllotBed.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mKeyboard = view.findViewById(R.id.ky_keyboard);
        llFirstbehos = view.findViewById(R.id.ll_firstbehos);
        llVitalsignRecord = view.findViewById(R.id.ll_vitalsign_record);

    }

    private void initAdapter() {
        emptyBedListAdapter = new EmptyBedListAdapter(new ArrayList<AllotBedInfoBean.EmptyBedListBean>());
        emptyBedListAdapter.setSelectItem(-1);
        recAllotBed.setAdapter(emptyBedListAdapter);

        emptyBedListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = view.findViewById(R.id.tv_bed_allot);

                //              选中后可选其他，不可取消
                if (listBeans.get(position).getNoClickFlag().equals("1")){
                    showToast(listBeans.get(position).getNote());
                }else {
                    emptyBedListAdapter.setSelectItem(position);
                    selectBedId = listBeans.get(position).getBedId();
                    emptyBedListAdapter.notifyDataSetChanged();
                }

                //选择后再次点击可取消选择
                //                if (position == selectPosition){
                //                    if (textView.isSelected()){
                //                        emptyBedListAdapter.setSelectItem(-1);
                //                        selectBedId = "";
                //                    }else {
                //                        emptyBedListAdapter.setSelectItem(position);
                //                        selectPosition = position;
                //                        selectBedId = listBeans.get(position).getBedId();
                //                    }
                //                }else {
                //                    emptyBedListAdapter.setSelectItem(position);
                //                    selectPosition = position;
                //                    selectBedId = listBeans.get(position).getBedId();
                //                }
                //                emptyBedListAdapter.notifyDataSetChanged();

            }
        });

    }

    //获取展示空床
    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        map.put("linkLocId", spUtils.getString(SharedPreference.LINKLOC));
        map.put("episodeId", episodeIdNow);
        AllotBedApiManager.getAllotBed(map, "getAllotInfo", new AllotBedApiManager.getAllotBedCallBack() {
            @Override
            public void onSuccess(AllotBedInfoBean allotBedInfoBean) {

                listBeans = allotBedInfoBean.getEmptyBedList();
                listDocBeans = allotBedInfoBean.getDoctorList();
                listNurBeans = allotBedInfoBean.getNurseList();

                emptyBedListAdapter.setNewData(listBeans);

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initScanMsg(String regNo) {

        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo", regNo);
        mapmsg.put("wardId", wardId);
        regNoNow = regNo;
        AllotBedApiManager.getUserMsg(mapmsg, "getPatWristInfo", new AllotBedApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(GetScanPatsBean getScanPatsBean) {
                rlAllotBedScan.setVisibility(View.GONE);
                scrollBedallot.setVisibility(View.VISIBLE);
                setToolbarRightCustomView(viewright);

                ScanGetUserMsgBean.PatInfoBean patInfoBean = getScanPatsBean.getPatInfo();
                episodeIdNow = patInfoBean.getEpisodeID();
                initData();
                tvPatInfo.setText("".equals(patInfoBean.getBedCode()) ? "未分床  " + patInfoBean.getName() : patInfoBean.getBedCode() + "  " + patInfoBean.getName());
                tvMainDoc.setText("".equals(patInfoBean.getMainDoctor()) ? "请选择" : patInfoBean.getMainDoctor());
                tvMainNur.setText("".equals(patInfoBean.getMainNurse()) ? "请选择" : patInfoBean.getMainNurse());
                tvRegNo.setText(getScanPatsBean.getPatInfo().getRegNo());

                if (patInfoBean.getMainDoctorID().size() > 0) {
                    selectDoc = patInfoBean.getMainDoctorID().get(0);
                } else {
                    selectDoc = "";
                }
                if (patInfoBean.getMainNurseID().size() > 0) {
                    selectNur = patInfoBean.getMainNurseID().get(0);
                } else {
                    selectNur = "";
                }
                if (patInfoBean.getSex().equals("男")) {
                    imgSex.setImageDrawable(getResources().getDrawable(R.drawable.sex_male));
                } else {
                    imgSex.setImageDrawable(getResources().getDrawable(R.drawable.sex_female));
                }

                isFirstToBed(episodeIdNow);

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });


    }

    private void isFirstToBed(String episodeId) {
        AllotBedApiManager.isFirstToBed(episodeId, new AllotBedApiManager.isFirstToBedCallBack() {
            @Override
            public void onSuccess(IsFirstToBedBean isFirstToBedBean) {
                if (isFirstToBedBean.getFirstToBed().equals("0")) {
                    llFirstbehos.setVisibility(View.GONE);
                } else {
                    llFirstbehos.setVisibility(View.VISIBLE);
                    firstBeHosTemData(episodeId);
                }

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void firstBeHosTemData(String episodeId) {
        AllotBedApiManager.firstBeHosTemData(episodeId, new AllotBedApiManager.FirstBeHosTemDataBeanCallBack() {
            @Override
            public void onSuccess(FirstBeHosTemDataBean firstBeHosTemDataBean) {
                if (recordInfo != null) {
                    llVitalsignRecord.removeAllViews();
                    viewItemMap.clear();
                }
                recordInfo = firstBeHosTemDataBean;
                drawInputItems();
                inputItemsValue();
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

        LinearLayout layout = null;

        int size = recordInfo.getTempConfig().size();

        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                layout = new LinearLayout(getContext());
            }

            FirstBeHosTemDataBean.TempConfigBean config = recordInfo.getTempConfig().get(i);
            LinearLayout item = drawItem(config);
            layout.addView(item);

            if (i == size - 1 && i % 3 == 0) {
                layout.addView(dramEmptyItem());
                layout.addView(dramEmptyItem());
            }

            if (i == size - 1 && i % 3 == 1) {
                layout.addView(dramEmptyItem());
            }

            if (i == size - 1 || i % 3 == 2) {
                llVitalsignRecord.addView(layout);
            }
        }

    }

    private void inputItemsValue() {

        for (int i = 0; i < recordInfo.getTempList().size(); i++) {
            FirstBeHosTemDataBean.TempListBean temp = recordInfo.getTempList().get(i);
            View view = viewItemMap.get(temp.getCode());
            if (view instanceof EditText) {
                EditText ed = (EditText) view;
                ed.setText(temp.getValue());
            } else {
                OptionView op = (OptionView) view;
                op.setText(temp.getValue());
            }
        }
    }

    private LinearLayout drawItem(FirstBeHosTemDataBean.TempConfigBean config) {

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(110);
        int width = 0;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;

        layout.setLayoutParams(params);


        TextView titleTV = new TextView(getContext());
        titleTV.setText(config.getDesc());
        //        titleTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        if (config.getDesc().length() > 7) {
            titleTV.setTextSize(12);
        } else {
            titleTV.setTextSize(16);
        }

        titleTV.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, ConvertUtils.dp2px(20), 0, 0);//4个参数按顺序分别是左上右下
        titleTV.setLayoutParams(titleParams);

        layout.addView(titleTV);


        if (config.getSelect().equals("false")) {
            //非选择框

            EditText edText = new EditText(getContext());
            edText.setGravity(Gravity.CENTER);
            edText.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            edText.setBackgroundResource(R.drawable.vital_sign_input_bg);

            if (config.getValueType() != null && config.getValueType().equals("N")) {
                edText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(10), ConvertUtils.dp2px(11), ConvertUtils.dp2px(10), ConvertUtils.dp2px(15));//4个参数按顺序分别是左上右下

            edText.setLayoutParams(layoutParams);

            edText.setPadding(10, 10, 10, 10);

            if (config.getSymbol() != null) {
                if (config.getSymbol().size() > 0) {
                    edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                    edText.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            symbol((ArrayList<String>) config.getSymbol(), edText);
                            return false;
                        }
                    });
                }
            }
            //自定义键盘
            edText.setOnTouchListener(new View.OnTouchListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (titleTV.getText().toString().contains("℃")) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edText.getWindowToken(), 0);
                        new KeyBoardUtil(mKeyboard, edText).showKeyboard();
                    } else {
                        mKeyboard.setVisibility(View.GONE);

                    }
                    return false;
                }
            });
            edText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (config.getNormalValueRangFrom() != null) {
                        if (config.getErrorValueLowTo() != null) {
                            if (isNumber(edText.getText().toString() + "")) {
                                double edDou = Double.parseDouble(edText.getText().toString());
                                if (edDou > Double.parseDouble(config.getErrorValueHightFrom()) || edDou < Double.parseDouble(config.getErrorValueLowTo())) {
                                    edText.setBackground(getResources().getDrawable(R.drawable.vital_sign_inputerror_bg));
                                } else if ((Double.parseDouble(config.getErrorValueLowTo()) < edDou && edDou < Double.parseDouble(config.getNormalValueRangFrom()))
                                        || (edDou < Double.parseDouble(config.getErrorValueHightFrom()) && edDou > Double.parseDouble(config.getNormalValueRangTo()))) {
                                    edText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                } else {
                                    edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                }
                            } else {
                                edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                if (config.getSymbol() != null) {
                                    if (config.getSymbol().size() > 0) {
                                        edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                    }
                                }
                            }
                            ;
                        } else {
                            if (isNumber(edText.getText().toString() + "")) {
                                double edDou = Double.parseDouble(edText.getText().toString());
                                if (edDou < Double.parseDouble(config.getNormalValueRangFrom())
                                        || edDou > Double.parseDouble(config.getNormalValueRangTo())) {
                                    edText.setBackgroundResource(R.drawable.vital_sign_inputwarning_bg);
                                } else {
                                    edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                }
                            } else {
                                edText.setBackgroundResource(R.drawable.vital_sign_input_bg);
                                if (config.getSymbol() != null) {
                                    if (config.getSymbol().size() > 0) {
                                        edText.setBackgroundResource(R.drawable.vital_sign_input_bggreen);
                                    }
                                }
                            }
                        }


                    }

                }
            });

            layout.addView(edText);

            viewItemMap.put(config.getCode(), edText);


        } else {
            //选择框
            config.getOptions().add("删除");
            final OptionView optionView = new OptionView(getActivity(), config.getOptions());

            optionView.setTextSize(16);
            optionView.setTextColor(getResources().getColor(R.color.vital_sign_record_next_color));
            optionView.setBackgroundResource(R.drawable.vital_sign_input_bg);
            optionView.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(ConvertUtils.dp2px(10), ConvertUtils.dp2px(11), ConvertUtils.dp2px(10), 45);//4个参数按顺序分别是左上右下
            optionView.setLayoutParams(layoutParams);

            optionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKeyboard.setVisibility(View.GONE);
                    optionView.picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                        @Override
                        public void onOptionPicked(int index, String item) {
                            //                            config.setSendValue(config.getItemCode() + "|" + item);
                            if (item.equals("删除")) {
                                optionView.setText("");
                            } else {
                                optionView.setText(item);
                            }
                        }
                    });
                    optionView.showPicker();
                }
            });

            layout.addView(optionView);
            viewItemMap.put(config.getCode(), optionView);

        }


        return layout;
    }

    private LinearLayout dramEmptyItem() {
        LinearLayout layout = new LinearLayout(getContext());

        int height = ConvertUtils.dp2px(120);
        int width = ConvertUtils.dp2px(0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;

        layout.setLayoutParams(params);

        return layout;
    }

    private void symbol(ArrayList<String> list, EditText editText) {
        String[] locDesc = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            locDesc[i] = list.get(i);
        }

        final OptionPicker picker = new OptionPicker(getActivity(), locDesc);
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
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (intent.getAction().equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            initScanMsg(bundle.getString("data"));

        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_allot, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_allotbed_maindoc:
                selectDocAndNur();
                break;
            case R.id.tv_allotbed_mainnur:
                selectDocAndNur();
                break;
            default:
                break;
        }
    }

    private void selectDocAndNur() {
        //        双选护士医生,显示姓名，对应index的编号
        final List<String> listDocName = new ArrayList<>();
        final List<String> listDocCode = new ArrayList<>();
        final List<String> listNurName = new ArrayList<>();
        final List<String> listNurCode = new ArrayList<>();

        listDocName.add("请选择");
        listDocCode.add("");
        listNurName.add("请选择");
        listNurCode.add("");
        //      遍历listDocBeans,获取医生姓名list，根据已分医生的id（selectDoc)获取当前显示值（selectDocIndex)
        for (int i = 0; i < listDocBeans.size(); i++) {
            listDocName.add(listDocBeans.get(i).getName());
            if (selectDoc.equals(listDocBeans.get(i).getID())) {
                selectDocIndex = i + 1;
            }
            listDocCode.add(listDocBeans.get(i).getID());
        }
        for (int i = 0; i < listNurBeans.size(); i++) {
            listNurName.add(listNurBeans.get(i).getName());
            if (selectNur.equals(listNurBeans.get(i).getID())) {
                selectNurIndex = i + 1;
            }
            listNurCode.add(listNurBeans.get(i).getID());
        }

        final DoublePicker picker = new DoublePicker(getActivity(), listDocName, listNurName);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(selectDocIndex, selectNurIndex);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setTextColor(Color.parseColor("#FF62ABFF"));
        picker.setSubmitTextSize(12);
        picker.setSubmitTextColor(Color.parseColor("#FF62ABFF"));
        picker.setDividerColor(Color.parseColor("#FF62ABFF"));
        picker.setCancelTextSize(12);
        picker.setCancelTextColor(Color.parseColor("#FFC8C8C8"));
        picker.setLineSpaceMultiplier(3);
        picker.setOffset(2);
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                selectDoc = listDocCode.get(selectedFirstIndex);
                selectNur = listNurCode.get(selectedSecondIndex);
                tvMainDoc.setText(listDocName.get(selectedFirstIndex));
                tvMainNur.setText(listNurName.get(selectedSecondIndex));
            }
        });
        picker.show();
    }

    /**
     * 保存生命体征数据
     */
    private void saveTempValue(int type) {


        showLoadingTip(BaseActivity.LoadingType.FULL);


        //        VitalSignApiManager.saveTempData(curEpisodeId, timepoint, result, new VitalSignApiManager.SaveTempDataCallback() {
        //            @Override
        //            public void onSuccess(VitalSignSaveBean bean) {
        //                hideLoadFailTip();
        //                waiting = false;
        //                showToast("保存成功 ");
        //                if (type == SAVE_TEMP_VALUE_NEXT) {
        //                    patientIndex++;
        //                    switchPatient();
        //                } else if (type == SAVE_TEMP_VALUE_PRE) {
        //                    patientIndex--;
        //                    switchPatient();
        //                }
        //            }
        //
        //            @Override
        //            public void onFail(String code, String msg) {
        //                hideLoadFailTip();
        //                waiting = false;
        //                showToast("error:" + msg);
        //            }
        //        });

    }


}
