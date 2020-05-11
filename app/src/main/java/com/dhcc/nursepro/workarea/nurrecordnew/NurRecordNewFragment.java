package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ElementDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecDataBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * NurRecordNFragment
 * 护理病历
 *
 * @author Devlix126
 * created at 2019/9/16 10:54
 */
public class NurRecordNewFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    private final static int DATE_DIALOG = 0;
    private final static int TIME_DIALOG = 1;
    private final int modelNum = 0;
    private final String skipViewCode = "";
    private final String skipViewValue = "";
    private final String skipRecId = "";
    private final int score = 0;
    private final int selectPosition = -1;
    private final Boolean isScore = false;
    private final HashMap<String, View> viewHashMap = new HashMap<>();
    private final HashMap<String, String> valueHashMap = new HashMap<>();
    private LinearLayout llNurrecord;
    private String episodeID = "";
    private String bedNo = "";
    private String patName = "";
    private String emrCode = "";
    private String guid = "";
    private String recId = "";
    private Calendar c = null;
    private List<String> selectStrList = new ArrayList<>();
    private List<Integer> selectScoreList = new ArrayList<>();
    private int mSingleChoiceID = -1;
    private int itemScore = 0;
    private List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements;
    private SPUtils spUtils;

    @SuppressWarnings("ResourceType")
    private static int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spUtils = SPUtils.getInstance();
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            episodeID = bundle.getString("EpisodeID", "");
            bedNo = bundle.getString("BedNo", "");
            patName = bundle.getString("PatName", "");
            recId = bundle.getString("RecID", "");
            guid = bundle.getString("GUID", "");
            emrCode = bundle.getString("EMRCode", "");
        }

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(bedNo + "    " + patName, 0xffffffff, 17);
        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        setToolbarRightCustomView(viewright);
        initview(view);

        initData();

    }

    private void save() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);

            if (stringBuilder.length() > 2) {
                stringBuilder.append(",");
            }
            if ("RadioElement".equals(element.getElementType())) {
                List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
                for (int j = 0; j < radioElementListBeanList.size(); j++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(j);
                    String radioStr = ((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).getText().toString();
                    if (((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).isChecked()) {
                        //"RadioElement_29":[{"NumberValue":"21","Text":"下拉单选2","Value":"2"}]

                        List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean.OprationItemListBean> oprationItemList = radioElementListBean.getOprationItemList();
                        String NumberValueStr = "";
                        String TextStr = "";
                        String ValueStr = "";
                        for (int i1 = 0; i1 < oprationItemList.size(); i1++) {
                            if (oprationItemList.get(i1).getText().equals(radioStr)) {
                                NumberValueStr = oprationItemList.get(i1).getNumberValue();
                                TextStr = oprationItemList.get(i1).getText();
                                ValueStr = oprationItemList.get(i1).getValue();
                                break;
                            }
                        }
                        if (!stringBuilder.toString().endsWith(",")) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append("\"")
                                .append(radioElementListBean.getElementType())
                                .append("_")
                                .append(radioElementListBean.getElementId())
                                .append("\":[{\"NumberValue\":\"")
                                .append(NumberValueStr)
                                .append("\",\"Text\":\"")
                                .append(TextStr)
                                .append("\",\"Value\":\"")
                                .append(ValueStr)
                                .append("\"}]");
                    } else {
                        //"RadioElement_31":[]
                        if (!stringBuilder.toString().endsWith(",")) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append("\"")
                                .append(radioElementListBean.getElementType())
                                .append("_")
                                .append(radioElementListBean.getElementId())
                                .append("\"")
                                .append(":")
                                .append("[]");
                    }

                }
            } else if ("DropRadioElement".equals(element.getElementType())) {
                String dropRadioStr = ((TextView) viewHashMap.get(element.getElementId())).getText().toString();
                if (StringUtils.isTrimEmpty(dropRadioStr)) {
                    //""DropRadioElement_31"":[]
                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\"")
                            .append(":")
                            .append("[]");
                } else {
                    //""DropRadioElement_29"":[{""NumberValue"":""21"",""Text"":""下拉单选2"",""Value"":""2""}]

                    List<ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean> oprationItemList = element.getOprationItemList();
                    String NumberValueStr = "";
                    String TextStr = "";
                    String ValueStr = "";
                    for (int i1 = 0; i1 < oprationItemList.size(); i1++) {
                        if (oprationItemList.get(i1).getText().equals(dropRadioStr)) {
                            NumberValueStr = oprationItemList.get(i1).getNumberValue();
                            TextStr = oprationItemList.get(i1).getText();
                            ValueStr = oprationItemList.get(i1).getValue();
                            break;
                        }
                    }
                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\":[{\"NumberValue\":\"")
                            .append(NumberValueStr)
                            .append("\",\"Text\":\"")
                            .append(TextStr)
                            .append("\",\"Value\":\"")
                            .append(ValueStr)
                            .append("\"}]");


                }
            } else if ("TextElement".equals(element.getElementType()) || "NumberElement".equals(element.getElementType())) {
                //""DateElement_16"":""2020-01-14""
                if (("DHCNURBarthelLR".equals(emrCode) && "32".equals(element.getElementId())) ||
                        ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && "65".equals(element.getElementId())) ||
                        ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode) && "39".equals(element.getElementId())) ||
                        ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode) && "168".equals(element.getElementId()))) {
                    String userStr = "CA" + ((EditText) viewHashMap.get(element.getElementId())).getText().toString() + "*" + spUtils.getString(SharedPreference.USERID);
                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\":\"")
                            .append(userStr)
                            .append("\"");
                } else {
                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\":\"")
                            .append(((EditText) viewHashMap.get(element.getElementId())).getText().toString())
                            .append("\"");
                }

            } else {
                stringBuilder.append("\"")
                        .append(element.getElementType())
                        .append("_")
                        .append(element.getElementId())
                        .append("\":\"")
                        .append(((TextView) viewHashMap.get(element.getElementId())).getText().toString())
                        .append("\"");
            }
        }

        stringBuilder.append(",\"EpisodeID\":\"").append(episodeID).append("\"")
                .append(",\"LOGON.CTLOCID\":\"").append(spUtils.getString(SharedPreference.LOCID)).append("\"")
                .append(",\"LOGON.GROUPDESC\":\"").append(spUtils.getString(SharedPreference.GROUPDESC)).append("\"")
                .append(",\"LOGON.USERID\":\"").append(spUtils.getString(SharedPreference.USERID)).append("\"")
                .append(",\"LOGON.WARDID\":\"").append(spUtils.getString(SharedPreference.WARDID)).append("\"")
                .append(",\"NurMPDataID\":\"").append(recId).append("\"")
                .append(",\"TemporarySave\":\"1\"")
                .append(",\"templateVersionGuid\":\"").append(guid).append("\"}");

        String parr = stringBuilder.toString();

        NurRecordOldApiManager.saveNewEmrData(guid, episodeID, recId, parr, new NurRecordOldApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                showToast("保存成功");
                finish();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initview(View view) {
        llNurrecord = view.findViewById(R.id.ll_nurrecord);

    }

    private void initData() {
        NurRecordOldApiManager.GetXmlValues(emrCode, recId, new NurRecordOldApiManager.GetXmlValuesCallback() {
            @Override
            public void onSuccess(ElementDataBean elementDataBean) {
                elements = elementDataBean.getData().getInput().getElementBases();
                InputViews(elements);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });
    }

    private void InputViews(List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements) {
        for (int i = 0; i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);
            if ("DateElement".equals(element.getElementType())) {
                LinearLayout llDate = getTextView(element);
                TextView tvDate = (TextView) viewHashMap.get(element.getElementId());

                tvDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowDateTime(DATE_DIALOG, getActivity(), tvDate);
                    }
                });
                llNurrecord.addView(llDate);
                llNurrecord.addView(getDashLine());

            } else if ("TimeElement".equals(element.getElementType())) {
                LinearLayout llTime = getTextView(element);
                TextView tvTime = (TextView) viewHashMap.get(element.getElementId());

                tvTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowDateTime(TIME_DIALOG, getActivity(), tvTime);
                    }
                });
                llNurrecord.addView(llTime);
                llNurrecord.addView(getDashLine());
            } else if ("RadioElement".equals(element.getElementType())) {
                LinearLayout llRadio = getRadioView(element);

                llNurrecord.addView(llRadio);
                llNurrecord.addView(getDashLine());

            } else if ("DropRadioElement".equals(element.getElementType())) {

                LinearLayout lldropRadio = getTextView(element);
                TextView tvdropRadio = (TextView) viewHashMap.get(element.getElementId());
                tvdropRadio.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                tvdropRadio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectStrList = new ArrayList<>();
                        selectScoreList = new ArrayList<>();
                        for (int i1 = 0; i1 < element.getOprationItemList().size(); i1++) {
                            selectStrList.add(element.getOprationItemList().get(i1).getText());
                            try {
                                selectScoreList.add(Integer.parseInt(element.getOprationItemList().get(i1).getNumberValue()));
                            } catch (NumberFormatException e) {
                                //                                showToast("分值转换出错");
                                e.printStackTrace();
                            }
                        }
                        selectStrList.add("");
                        selectScoreList.add(0);

                        ShowRadioScore(selectStrList, getActivity(), tvdropRadio);
                    }
                });

                lldropRadio.clearAnimation();

                //跌倒评估
                if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
                    //初始化view显示隐藏
                    if ("72".equals(element.getElementId())) {
                        CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
                        if (checkBox69 != null && checkBox69.isChecked()) {
                            lldropRadio.setVisibility(View.VISIBLE);
                        } else {
                            lldropRadio.setVisibility(View.GONE);
                        }
                    }

                    if ("74".equals(element.getElementId())) {
                        CheckBox checkBox70 = (CheckBox) viewHashMap.get("70");
                        if (checkBox70 != null && checkBox70.isChecked()) {
                            lldropRadio.setVisibility(View.VISIBLE);
                        } else {
                            lldropRadio.setVisibility(View.GONE);
                        }
                    }

                    if ("77".equals(element.getElementId()) ||
                            "79".equals(element.getElementId()) ||
                            "81".equals(element.getElementId()) ||
                            "83".equals(element.getElementId()) ||
                            "85".equals(element.getElementId()) ||
                            "87".equals(element.getElementId()) ||
                            "89".equals(element.getElementId()) ||
                            "91".equals(element.getElementId()) ||
                            "93".equals(element.getElementId()) ||
                            "95".equals(element.getElementId()) ||
                            "97".equals(element.getElementId())) {
                        CheckBox checkBox71 = (CheckBox) viewHashMap.get("71");
                        if (checkBox71 != null && checkBox71.isChecked()) {
                            lldropRadio.setVisibility(View.VISIBLE);
                        } else {
                            lldropRadio.setVisibility(View.GONE);
                        }
                    }
                }

                llNurrecord.addView(lldropRadio);
                llNurrecord.addView(getDashLine());
            } else if ("NumberElement".equals(element.getElementType())) {
                LinearLayout llNumber = getEditText(element);
                EditText edNumber = (EditText) viewHashMap.get(element.getElementId());
                edNumber.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                //Barthel评分总分不可编辑
                if ("DHCNURBarthelLR".equals(emrCode) && "24".equals(element.getElementId())) {
                    edNumber.setEnabled(false);
                }


                //跌倒评估
                if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
                    if ("99".equals(element.getElementId())) {
                        //跌倒评估总分不可编辑
                        edNumber.setEnabled(false);
                        //初始化view显示隐藏
                        CheckBox checkBox71 = (CheckBox) viewHashMap.get("71");
                        if (checkBox71 != null && checkBox71.isChecked()) {
                            llNumber.setVisibility(View.VISIBLE);
                        } else {
                            llNumber.setVisibility(View.GONE);
                        }
                    }
                }

                //压疮评估
                if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode)) {
                    if ("60".equals(element.getElementId())) {
                        //压疮评估总分不可编辑
                        edNumber.setEnabled(false);
                    }
                }

                llNurrecord.addView(llNumber);
                llNurrecord.addView(getDashLine());
            } else if ("TextElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element);
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));


                if ("".equals(recId)) {
                    //Barthel评分总分不可编辑
                    if ("DHCNURBarthelLR".equals(emrCode) && "32".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                    //跌倒评估
                    if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && "65".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                    //管道滑脱
                    if ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode) && "39".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                    //压疮评估
                    if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode) && "168".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                }

                llNurrecord.addView(lledit);
                llNurrecord.addView(getDashLine());
            }

        }
    }

    private LinearLayout getTextView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(ConvertUtils.dp2px(12), ConvertUtils.dp2px(6), ConvertUtils.dp2px(12), ConvertUtils.dp2px(6));
        linearLayout.setLayoutParams(llparams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView tvTitle = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tvparams1.setMargins(0, 0, ConvertUtils.dp2px(12), 0);
        tvTitle.setLayoutParams(tvparams1);
        tvTitle.setTextColor(Color.parseColor("#4a4a4a"));
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setText(element.getNameText());

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        textView.setLayoutParams(tvparams2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#4a4a4a"));
        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
        if (!StringUtils.isEmpty(element.getDefaultValue())) {
            textView.setText(element.getDefaultValue());
        }
        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);

        linearLayout.addView(tvTitle);
        linearLayout.addView(textView);

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);


        return linearLayout;
    }

    protected void ShowDateTime(int id, Context context, final TextView textView) {
        Dialog dialog = null;
        c = Calendar.getInstance();
        String DateTimeStr = textView.getText().toString();
        switch (id) {
            case DATE_DIALOG:
                if (DateTimeStr.split("-").length == 3) {
                    dialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker dp, int year,
                                                      int month, int dayOfMonth) {

                                    DecimalFormat df = new DecimalFormat("00");

                                    String datestr = year + "-" + df.format(month + 1)
                                            + "-" + df.format(dayOfMonth);
                                    textView.setText(datestr);
                                }
                            }, Integer.parseInt(DateTimeStr.split("-")[0]),
                            Integer.parseInt(DateTimeStr.split("-")[1]) - 1,
                            Integer.parseInt(DateTimeStr.split("-")[2])
                    );
                } else {
                    dialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker dp, int year,
                                                      int month, int dayOfMonth) {

                                    DecimalFormat df = new DecimalFormat("00");

                                    String datestr = year + "-" + df.format(month + 1)
                                            + "-" + df.format(dayOfMonth);
                                    textView.setText(datestr);
                                }
                            }, c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    );
                }

                dialog.show();
                break;
            case TIME_DIALOG:
                if (DateTimeStr.split(":").length == 2) {
                    dialog = new TimePickerDialog(context,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    DecimalFormat df = new DecimalFormat("00");
                                    String timeStr = df.format(hourOfDay) + ":" + df.format(minute);
                                    textView.setText(timeStr);
                                }
                            }, Integer.valueOf(DateTimeStr.split(":")[0]), Integer.valueOf(DateTimeStr.split(":")[1]),
                            false);
                } else {
                    dialog = new TimePickerDialog(context,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {
                                    DecimalFormat df = new DecimalFormat("00");
                                    String timeStr = df.format(hourOfDay) + ":" + df.format(minute);
                                    textView.setText(timeStr);
                                }
                            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                            false);
                }

                dialog.show();
                break;
            default:
                break;
        }

    }

    private View getDashLine() {
        View view = new View(getActivity());
        LinearLayout.LayoutParams viewparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(4));
        view.setLayoutParams(viewparams);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        view.setBackground(getResources().getDrawable(R.drawable.line_dash));
        return view;

    }

    private LinearLayout getRadioView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(ConvertUtils.dp2px(12), ConvertUtils.dp2px(6), ConvertUtils.dp2px(12), ConvertUtils.dp2px(6));
        linearLayout.setLayoutParams(llparams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView tvTitle = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tvparams1.setMargins(0, 0, ConvertUtils.dp2px(12), 0);
        tvTitle.setLayoutParams(tvparams1);
        tvTitle.setTextColor(Color.parseColor("#4a4a4a"));
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setText(element.getNameText());

        LinearLayout llRadio = new LinearLayout(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
        llRadio.setLayoutParams(tvparams2);
        llRadio.setOrientation(LinearLayout.VERTICAL);
        llRadio.setGravity(Gravity.CENTER);

        List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
        for (int i = 0; i < radioElementListBeanList.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(i);
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(radioElementListBean.getNameText());
            checkBox.setTag(radioElementListBean.getElementId());
            if (!StringUtils.isTrimEmpty(radioElementListBean.getDefaultValue()) && radioElementListBean.getNameText().equals(radioElementListBean.getDefaultValue())) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }

            checkBox.setOnCheckedChangeListener(this);
            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            llRadio.addView(checkBox);
        }

        linearLayout.addView(tvTitle);
        linearLayout.addView(llRadio);

        return linearLayout;
    }

    //    private LinearLayout getRadioView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
    //        LinearLayout linearLayout = new LinearLayout(getActivity());
    //        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    //        llparams.setMargins(ConvertUtils.dp2px(12), ConvertUtils.dp2px(6), ConvertUtils.dp2px(12), ConvertUtils.dp2px(6));
    //        linearLayout.setLayoutParams(llparams);
    //        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
    //        linearLayout.setGravity(Gravity.CENTER);
    //
    //        TextView tvTitle = new TextView(getActivity());
    //        LinearLayout.LayoutParams tvparams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
    //        tvparams1.setMargins(0, 0, ConvertUtils.dp2px(12), 0);
    //        tvTitle.setLayoutParams(tvparams1);
    //        tvTitle.setTextColor(Color.parseColor("#4a4a4a"));
    //        tvTitle.setGravity(Gravity.CENTER);
    //        tvTitle.setText(element.getNameText());
    //
    //        FlowRadioGroup flowRadioGroup = new FlowRadioGroup(getContext());
    //        LinearLayout.LayoutParams flowradioparams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
    //        flowRadioGroup.setLayoutParams(flowradioparams1);
    //        flowRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
    //
    //
    //        for (int i = 0; i < element.getOprationItemList().size(); i++) {
    //            ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
    //            RadioButton radioButton = new RadioButton(getActivity());
    //            radioButton.setText(oprationItemListBean.getText());
    //
    //
    //            flowRadioGroup.addView(radioButton);
    //        }
    //        textView.setTag(element.getElementId());
    //        viewHashMap.put(element.getElementId(), flowRadioGroup);
    //        linearLayout.addView(tvTitle);
    //        linearLayout.addView(flowRadioGroup);
    //
    //        return linearLayout;
    //    }

    //    private void showRPopupWindow(Context context, List<String> selectStrList, final TextView textView) {
    //        PopupWindow popupWindow = new PopupWindow(context);
    //        popupWindow.setWidth(textView.getWidth());
    //        Drawable drawable = context.getResources().getDrawable(R.drawable.nur_record_btn_bg);
    //        popupWindow.setBackgroundDrawable(drawable);
    //
    //        RecyclerView recyclerView = new RecyclerView(context);
    //        recyclerView.setHasFixedSize(true);
    //        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    //
    //
    //        OPopupWindowAdapter oPopupWindowAdapter = new OPopupWindowAdapter(selectStrList);
    //        oPopupWindowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
    //            @Override
    //            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    //                textView.setText(oPopupWindowAdapter.getItem(position));
    //
    //                if (popupWindow.isShowing()) {
    //                    popupWindow.dismiss();
    //                }
    //            }
    //        });
    //        recyclerView.setAdapter(oPopupWindowAdapter);
    //        popupWindow.setContentView(recyclerView);
    //        popupWindow.setOutsideTouchable(true);
    //
    //
    //        int tvBottom = textView.getBottom();
    //        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    //        DisplayMetrics dm = new DisplayMetrics();
    //        wm.getDefaultDisplay().getMetrics(dm);
    //        int screenBottom = dm.heightPixels;
    //        if (screenBottom - tvBottom > 500) {
    //            popupWindow.showAsDropDown(textView);
    //        } else {
    //            View contentView = popupWindow.getContentView();
    //            //需要先测量，PopupWindow还未弹出时，宽高为0
    //            contentView.measure(makeDropDownMeasureSpec(popupWindow.getWidth()),
    //                    makeDropDownMeasureSpec(popupWindow.getHeight()));
    //            int offsetY = -(popupWindow.getContentView().getMeasuredHeight() + textView.getHeight() + 5);
    //            popupWindow.showAsDropDown(textView, 0, offsetY);
    //        }
    //    }

    private void ShowRadioScore(List<String> selectStrList, Context context, final TextView textView) {
        itemScore = 0;
        mSingleChoiceID = -1;
        String[] m_Items = new String[selectStrList.size()];
        for (int i = 0; i < selectStrList.size(); i++) {
            if (textView.getText().toString().equals(selectStrList.get(i))) {
                mSingleChoiceID = i;
            }
            m_Items[i] = selectStrList.get(i);
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);

        localBuilder.setSingleChoiceItems(m_Items, mSingleChoiceID,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        try {
                            if (mSingleChoiceID != -1) {
                                itemScore = itemScore - selectScoreList.get(mSingleChoiceID);
                                itemScore = itemScore + selectScoreList.get(whichButton);
                            } else {
                                itemScore = itemScore + selectScoreList.get(whichButton);
                            }
                        } catch (Exception e) {
                            //                            showToast("分值计算出错");
                            e.printStackTrace();
                        }
                        mSingleChoiceID = whichButton;
                    }
                });
        localBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(m_Items[mSingleChoiceID]);
                        if (itemScore != 0) {

                            if ("DHCNURBarthelLR".equals(emrCode)) {
                                //                                showToast("分数变化" + itemScore);
                                TextView tvScore = (TextView) viewHashMap.get("24");
                                if (StringUtils.isEmpty(tvScore.getText().toString())) {
                                    tvScore.setText(itemScore + "");
                                } else {
                                    tvScore.setText((Integer.parseInt(tvScore.getText().toString()) + itemScore) + "");
                                }
                            }

                            if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {

                                if ("77".equals(textView.getTag().toString()) ||
                                        "79".equals(textView.getTag().toString()) ||
                                        "81".equals(textView.getTag().toString()) ||
                                        "83".equals(textView.getTag().toString()) ||
                                        "85".equals(textView.getTag().toString()) ||
                                        "87".equals(textView.getTag().toString()) ||
                                        "89".equals(textView.getTag().toString()) ||
                                        "91".equals(textView.getTag().toString()) ||
                                        "93".equals(textView.getTag().toString()) ||
                                        "95".equals(textView.getTag().toString()) ||
                                        "97".equals(textView.getTag().toString())) {
                                    //                                    showToast("分数变化" + itemScore);
                                    TextView tvScore = (TextView) viewHashMap.get("99");
                                    if (StringUtils.isEmpty(tvScore.getText().toString())) {
                                        tvScore.setText(itemScore + "");
                                    } else {
                                        tvScore.setText((Integer.parseInt(tvScore.getText().toString()) + itemScore) + "");
                                    }

                                }

                            }
                        }

                    }
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    private LinearLayout getEditText(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(ConvertUtils.dp2px(12), ConvertUtils.dp2px(6), ConvertUtils.dp2px(12), ConvertUtils.dp2px(6));
        linearLayout.setLayoutParams(llparams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView tvTitle = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tvparams1.setMargins(0, 0, ConvertUtils.dp2px(12), 0);
        tvTitle.setLayoutParams(tvparams1);
        tvTitle.setTextColor(Color.parseColor("#4a4a4a"));
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setText(element.getNameText());

        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams edparams1 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        editText.setLayoutParams(edparams1);
        editText.setGravity(Gravity.CENTER);
        editText.setTextColor(Color.parseColor("#4a4a4a"));
        editText.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
        if (!StringUtils.isEmpty(element.getDefaultValue())) {
            editText.setText(element.getDefaultValue());
        }
        editText.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), editText);

        linearLayout.addView(tvTitle);
        linearLayout.addView(editText);

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        return linearLayout;
    }

    private void ShowRadio(List<String> selectStrList, Context context, final TextView textView) {

        mSingleChoiceID = -1;
        String[] m_Items = new String[selectStrList.size()];
        for (int i = 0; i < selectStrList.size(); i++) {
            if (textView.getText().toString().equals(selectStrList.get(i))) {
                mSingleChoiceID = i;
            }
            m_Items[i] = selectStrList.get(i);
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);

        localBuilder.setSingleChoiceItems(m_Items, mSingleChoiceID,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        mSingleChoiceID = whichButton;
                    }
                });
        localBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(m_Items[mSingleChoiceID]);
                    }
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_new, container, false);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //69  72
        //70  74
        //71  77 79 81 83 85 87 89 91 93 95 97 99
        //跌倒评估
        if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
            CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
            TextView textView72 = (TextView) viewHashMap.get("72");

            CheckBox checkBox70 = (CheckBox) viewHashMap.get("70");
            TextView textView74 = (TextView) viewHashMap.get("74");

            CheckBox checkBox71 = (CheckBox) viewHashMap.get("71");
            TextView textView77 = (TextView) viewHashMap.get("77");
            TextView textView79 = (TextView) viewHashMap.get("79");
            TextView textView81 = (TextView) viewHashMap.get("81");
            TextView textView83 = (TextView) viewHashMap.get("83");
            TextView textView85 = (TextView) viewHashMap.get("85");
            TextView textView87 = (TextView) viewHashMap.get("87");
            TextView textView89 = (TextView) viewHashMap.get("89");
            TextView textView91 = (TextView) viewHashMap.get("91");
            TextView textView93 = (TextView) viewHashMap.get("93");
            TextView textView95 = (TextView) viewHashMap.get("95");
            TextView textView97 = (TextView) viewHashMap.get("97");
            TextView textView99 = (TextView) viewHashMap.get("99");

            LinearLayout linearLayout72 = (LinearLayout) viewHashMap.get("72_ll");

            LinearLayout linearLayout74 = (LinearLayout) viewHashMap.get("74_ll");

            LinearLayout linearLayout77 = (LinearLayout) viewHashMap.get("77_ll");
            LinearLayout linearLayout79 = (LinearLayout) viewHashMap.get("79_ll");
            LinearLayout linearLayout81 = (LinearLayout) viewHashMap.get("81_ll");
            LinearLayout linearLayout83 = (LinearLayout) viewHashMap.get("83_ll");
            LinearLayout linearLayout85 = (LinearLayout) viewHashMap.get("85_ll");
            LinearLayout linearLayout87 = (LinearLayout) viewHashMap.get("87_ll");
            LinearLayout linearLayout89 = (LinearLayout) viewHashMap.get("89_ll");
            LinearLayout linearLayout91 = (LinearLayout) viewHashMap.get("91_ll");
            LinearLayout linearLayout93 = (LinearLayout) viewHashMap.get("93_ll");
            LinearLayout linearLayout95 = (LinearLayout) viewHashMap.get("95_ll");
            LinearLayout linearLayout97 = (LinearLayout) viewHashMap.get("97_ll");
            LinearLayout linearLayout99 = (LinearLayout) viewHashMap.get("99_ll");

            String radiotag = buttonView.getTag().toString();
            if (isChecked) {
                switch (radiotag) {
                    case "69":
                        linearLayout72.setVisibility(View.VISIBLE);
                        cancelCheck("70", "71", "-1");
                        break;
                    case "70":
                        linearLayout74.setVisibility(View.VISIBLE);
                        cancelCheck("69", "71", "-1");
                        break;
                    case "71":
                        linearLayout77.setVisibility(View.VISIBLE);
                        linearLayout79.setVisibility(View.VISIBLE);
                        linearLayout81.setVisibility(View.VISIBLE);
                        linearLayout83.setVisibility(View.VISIBLE);
                        linearLayout85.setVisibility(View.VISIBLE);
                        linearLayout87.setVisibility(View.VISIBLE);
                        linearLayout89.setVisibility(View.VISIBLE);
                        linearLayout91.setVisibility(View.VISIBLE);
                        linearLayout93.setVisibility(View.VISIBLE);
                        linearLayout95.setVisibility(View.VISIBLE);
                        linearLayout97.setVisibility(View.VISIBLE);
                        linearLayout99.setVisibility(View.VISIBLE);
                        cancelCheck("69", "70", "-1");
                        break;
                    default:
                        break;
                }
            } else {
                switch (radiotag) {
                    case "69":
                        textView72.setText("");
                        linearLayout72.setVisibility(View.GONE);
                        break;
                    case "70":
                        textView74.setText("");
                        linearLayout74.setVisibility(View.GONE);
                        break;
                    case "71":
                        textView77.setText("");
                        textView79.setText("");
                        textView81.setText("");
                        textView83.setText("");
                        textView85.setText("");
                        textView87.setText("");
                        textView89.setText("");
                        textView91.setText("");
                        textView93.setText("");
                        textView95.setText("");
                        textView97.setText("");
                        textView99.setText("");
                        linearLayout77.setVisibility(View.GONE);
                        linearLayout79.setVisibility(View.GONE);
                        linearLayout81.setVisibility(View.GONE);
                        linearLayout83.setVisibility(View.GONE);
                        linearLayout85.setVisibility(View.GONE);
                        linearLayout87.setVisibility(View.GONE);
                        linearLayout89.setVisibility(View.GONE);
                        linearLayout91.setVisibility(View.GONE);
                        linearLayout93.setVisibility(View.GONE);
                        linearLayout95.setVisibility(View.GONE);
                        linearLayout97.setVisibility(View.GONE);
                        linearLayout99.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        }

        //压疮评估
        if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode)) {
            TextView tvScore = (TextView) viewHashMap.get("60");

            String radiotag = buttonView.getTag().toString();
            if (isChecked) {
                switch (radiotag) {
                    //压疮风险互斥选项
                    case "245":
                        itemScore = itemScore + 1;
                        cancelCheck("249", "248", "247");
                        break;
                    case "249":
                        itemScore = itemScore + 2;
                        cancelCheck("245", "248", "247");
                        break;
                    case "248":
                        itemScore = itemScore + 3;
                        cancelCheck("245", "249", "247");
                        break;
                    case "247":
                        itemScore = itemScore + 4;
                        cancelCheck("245", "249", "248");
                        break;
                    case "240":
                        itemScore = itemScore + 1;
                        cancelCheck("244", "243", "242");
                        break;
                    case "244":
                        itemScore = itemScore + 2;
                        cancelCheck("240", "243", "242");
                        break;
                    case "243":
                        itemScore = itemScore + 3;
                        cancelCheck("240", "244", "242");
                        break;
                    case "242":
                        itemScore = itemScore + 4;
                        cancelCheck("240", "244", "243");
                        break;
                    case "235":
                        itemScore = itemScore
                                + 1;
                        cancelCheck("239", "238", "237");
                        break;
                    case "239":
                        itemScore = itemScore + 2;
                        cancelCheck("235", "238", "237");
                        break;
                    case "238":
                        itemScore = itemScore + 3;
                        cancelCheck("235", "239", "237");
                        break;
                    case "237":
                        itemScore = itemScore + 4;
                        cancelCheck("235", "239", "238");
                        break;
                    case "230":
                        itemScore = itemScore + 1;
                        cancelCheck("234", "233", "232");
                        break;
                    case "234":
                        itemScore = itemScore + 2;
                        cancelCheck("230", "233", "232");
                        break;
                    case "233":
                        itemScore = itemScore + 3;
                        cancelCheck("230", "234", "232");
                        break;
                    case "232":
                        itemScore = itemScore + 4;
                        cancelCheck("230", "234", "233");
                        break;
                    case "220":
                        itemScore = itemScore + 1;
                        cancelCheck("224", "223", "222");
                        break;
                    case "224":
                        itemScore = itemScore + 2;
                        cancelCheck("220", "223", "222");
                        break;
                    case "223":
                        itemScore = itemScore + 3;
                        cancelCheck("220", "224", "222");
                        break;
                    case "222":
                        itemScore = itemScore + 4;
                        cancelCheck("220", "224", "223");
                        break;
                    case "216":
                        itemScore = itemScore + 1;
                        cancelCheck("219", "218", "-1");
                        break;
                    case "219":
                        itemScore = itemScore + 2;
                        cancelCheck("216", "218", "-1");
                        break;
                    case "218":
                        itemScore = itemScore + 3;
                        cancelCheck("216", "219", "-1");
                        break;
                    default:
                        break;
                }
            } else {
                switch (radiotag) {
                    //压疮风险互斥选项
                    case "245":
                        itemScore = itemScore - 1;
                        break;
                    case "249":
                        itemScore = itemScore - 2;
                        break;
                    case "248":
                        itemScore = itemScore - 3;
                        break;
                    case "247":
                        itemScore = itemScore - 4;
                        break;
                    case "240":
                        itemScore = itemScore - 1;
                        break;
                    case "244":
                        itemScore = itemScore - 2;
                        break;
                    case "243":
                        itemScore = itemScore - 3;
                        break;
                    case "242":
                        itemScore = itemScore - 4;
                        break;
                    case "235":
                        itemScore = itemScore - 1;
                        break;
                    case "239":
                        itemScore = itemScore - 2;
                        break;
                    case "238":
                        itemScore = itemScore - 3;
                        break;
                    case "237":
                        itemScore = itemScore - 4;
                        break;
                    case "230":
                        itemScore = itemScore - 1;
                        break;
                    case "234":
                        itemScore = itemScore - 2;
                        break;
                    case "233":
                        itemScore = itemScore - 3;
                        break;
                    case "232":
                        itemScore = itemScore - 4;
                        break;
                    case "220":
                        itemScore = itemScore - 1;
                        break;
                    case "224":
                        itemScore = itemScore - 2;
                        break;
                    case "223":
                        itemScore = itemScore - 3;
                        break;
                    case "222":
                        itemScore = itemScore - 4;
                        break;
                    case "216":
                        itemScore = itemScore - 1;
                        break;
                    case "219":
                        itemScore = itemScore - 2;
                        break;
                    case "218":
                        itemScore = itemScore - 3;
                        break;
                    default:
                        break;
                }
            }
            //            showToast("分数变化" + itemScore);
            if (StringUtils.isEmpty(tvScore.getText().toString())) {
                tvScore.setText(itemScore + "");
            } else {
                tvScore.setText((Integer.parseInt(tvScore.getText().toString()) + itemScore) + "");
            }
            itemScore = 0;
        }
    }


    //    private void cancelCheck(String check1, String check2, String check3, int cScore1, int cScore2, int cScore3) {
    private void cancelCheck(String check1, String check2, String check3) {
        //        int cScore = 0;
        CheckBox checkBox1 = (CheckBox) viewHashMap.get(check1);
        if (checkBox1 != null && checkBox1.isChecked()) {
            //            if (checkBox1.isChecked()) {
            //                cScore = cScore - cScore1;
            //            }
            checkBox1.setChecked(false);
        }
        CheckBox checkBox2 = (CheckBox) viewHashMap.get(check2);
        if (checkBox2 != null && checkBox2.isChecked()) {
            //            if (checkBox2.isChecked()) {
            //                cScore = cScore - cScore2;
            //            }
            checkBox2.setChecked(false);
        }
        CheckBox checkBox3 = (CheckBox) viewHashMap.get(check3);
        if (checkBox3 != null && checkBox3.isChecked()) {
            //            if (checkBox3.isChecked()) {
            //                cScore = cScore - cScore3;
            //            }
            checkBox3.setChecked(false);
        }
    }

    public class OPopupWindowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public OPopupWindowAdapter(@Nullable List<String> data) {
            super(R.layout.item_opopupwindow_text, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.opopup_text, item);
        }
    }


}
