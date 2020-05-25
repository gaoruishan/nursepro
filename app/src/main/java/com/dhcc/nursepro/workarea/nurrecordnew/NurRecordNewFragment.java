package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
import java.util.Objects;

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

    private List<ElementDataBean.FirstIdListBean> firstIdListBeans = new ArrayList<>();
    //跳转单据关联View
    private String linkViewCode = "";
    private String linkViewValue = "";

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 5) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }

            int grade = Integer.parseInt(StringUtils.isEmpty(value) ? "-1" : value);
            CheckBox checkBox1774 = (CheckBox) viewHashMap.get("1774");
            CheckBox checkBox1775 = (CheckBox) viewHashMap.get("1775");
            CheckBox checkBox1776 = (CheckBox) viewHashMap.get("1776");
            CheckBox checkBox1777 = (CheckBox) viewHashMap.get("1777");

            if (grade < 0) {

            } else if (grade < 41) {
                checkBox1777.setChecked(true);
            } else if (grade < 61) {
                checkBox1776.setChecked(true);
            } else if (grade < 100) {
                checkBox1775.setChecked(true);
            } else {
                checkBox1774.setChecked(true);
            }
        } else if (requestCode == 2 && resultCode == 5) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
            int grade = Integer.parseInt(StringUtils.isEmpty(value) ? "-1" : value);
            CheckBox checkBox1781 = (CheckBox) viewHashMap.get("1781");
            CheckBox checkBox1782 = (CheckBox) viewHashMap.get("1782");
            CheckBox checkBox1783 = (CheckBox) viewHashMap.get("1783");
            CheckBox checkBox1784 = (CheckBox) viewHashMap.get("1784");
            CheckBox checkBox1785 = (CheckBox) viewHashMap.get("1785");

            if (grade < 0) {

            } else if (grade < 10) {
                checkBox1785.setChecked(true);
            } else if (grade < 13) {
                checkBox1784.setChecked(true);
            } else if (grade < 15) {
                checkBox1783.setChecked(true);
            } else if (grade < 19) {
                checkBox1782.setChecked(true);
            } else {
                checkBox1781.setChecked(true);
            }
        } else if (requestCode == 3 && resultCode == 5) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");

            if (value.equals("高风险")) {
                EditText editText1 = (EditText) viewHashMap.get(key);
                if (editText1 != null) {
                    editText1.setText("");
                }
                CheckBox checkBox1789 = (CheckBox) viewHashMap.get("1789");
                CheckBox checkBox1815 = (CheckBox) viewHashMap.get("1815");
                checkBox1789.setChecked(true);
                checkBox1815.setChecked(true);

            } else if (value.equals("低风险")) {
                EditText editText1 = (EditText) viewHashMap.get(key);
                if (editText1 != null) {
                    editText1.setText("");
                }
                CheckBox checkBox1789 = (CheckBox) viewHashMap.get("1789");
                CheckBox checkBox1816 = (CheckBox) viewHashMap.get("1816");
                checkBox1789.setChecked(true);
                checkBox1816.setChecked(true);

            } else {
                EditText editText1 = (EditText) viewHashMap.get(key);
                if (editText1 != null) {
                    editText1.setText(value);
                }
                int grade = Integer.parseInt(StringUtils.isEmpty(value) ? "-1" : value);

                CheckBox checkBox1793 = (CheckBox) viewHashMap.get("1793");
                checkBox1793.setChecked(true);

                CheckBox checkBox1818 = (CheckBox) viewHashMap.get("1818");
                CheckBox checkBox1820 = (CheckBox) viewHashMap.get("1820");
                CheckBox checkBox1821 = (CheckBox) viewHashMap.get("1821");

                if (grade < 0) {

                } else if (grade < 6) {
                    checkBox1818.setChecked(true);
                } else if (grade < 14) {
                    checkBox1820.setChecked(true);
                } else {
                    checkBox1821.setChecked(true);
                }
            }

        }

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
            linkViewCode = bundle.getString("LinkViewCode", "");
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
                int j;
                for (j = 0; j < radioElementListBeanList.size(); j++) {
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
                                .append(radioElementListBean.getFormName())
                                .append("\":[{\"NumberValue\":\"")
                                .append(NumberValueStr)
                                .append("\",\"Text\":\"")
                                .append(TextStr)
                                .append("\",\"Value\":\"")
                                .append(ValueStr)
                                .append("\"}]");
                        break;
                    }
                }

                if (j >= radioElementListBeanList.size()) {
                    stringBuilder.append("\"")
                            .append(radioElementListBeanList.get(0).getFormName())
                            .append("\"")
                            .append(":")
                            .append("[]");
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

            } else if ("CheckElement".equals(element.getElementType())) {
                List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
                int j;
                int checkedCount = 0;
                if (!stringBuilder.toString().endsWith(",")) {
                    stringBuilder.append(",");
                }
                for (j = 0; j < radioElementListBeanList.size(); j++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(j);
                    String radioStr = ((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).getText().toString();

                    if (j == 0) {
                        stringBuilder.append("\"")
                                .append(radioElementListBean.getFormName())
                                .append("\":[");
                    }

                    if (((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).isChecked()) {

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
                        if (checkedCount > 0) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append("{\"NumberValue\":\"")
                                .append(NumberValueStr)
                                .append("\",\"Text\":\"")
                                .append(TextStr)
                                .append("\",\"Value\":\"")
                                .append(ValueStr)
                                .append("\"}");

                        checkedCount++;
                    }

                    if (j == radioElementListBeanList.size() - 1) {
                        stringBuilder.append("]");
                    }
                }

            } else if ("TextElement".equals(element.getElementType()) || "NumberElement".equals(element.getElementType()) || "TextareaElement".equals(element.getElementType())) {
                //""DateElement_16"":""2020-01-14""
                if (("DHCNURBarthelLR".equals(emrCode) && "32".equals(element.getElementId())) ||
                        ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && "65".equals(element.getElementId())) ||
                        ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode) && "39".equals(element.getElementId())) ||
                        ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode) && "168".equals(element.getElementId())) ||
                        ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode) && "1803".equals(element.getElementId())) ||
                        ("DHCNURHLJLDLR".equals(emrCode) && "44".equals(element.getElementId()))) {

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
                if (!StringUtils.isTrimEmpty(linkViewCode)) {
                    EditText editText = (EditText) viewHashMap.get(linkViewCode);
                    if (editText != null) {
                        linkViewValue = editText.getText().toString();
                        Intent intent = new Intent();

                        if ("DHCNURBarthelLR".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "1773");
                        } else if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "1780");
                        } else if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "1788");

                            CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
                            CheckBox checkBox70 = (CheckBox) viewHashMap.get("70");

                            if (checkBox69.isChecked()) {
                                linkViewValue = "低风险";
                            }
                            if (checkBox70.isChecked()) {
                                linkViewValue = "高风险";
                            }

                        }
                        intent.putExtra("LinkViewValue", linkViewValue);

                        Objects.requireNonNull(getActivity()).setResult(5, intent);
                    }
                }
                finish();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
                //                if (!StringUtils.isTrimEmpty(linkViewCode)) {
                //                    EditText editText = (EditText) viewHashMap.get(linkViewCode);
                //                    if (editText != null) {
                //                        linkViewValue = editText.getText().toString();
                //                        Intent intent = new Intent();
                //
                //                        if ("DHCNURBarthelLR".equals(emrCode)) {
                //                            intent.putExtra("LinkViewCode", "1773");
                //                        } else if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode)) {
                //                            intent.putExtra("LinkViewCode", "1780");
                //                        } else if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
                //                            intent.putExtra("LinkViewCode", "1788");
                //
                //                            CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
                //                            CheckBox checkBox70 = (CheckBox) viewHashMap.get("70");
                //
                //                            if (checkBox69.isChecked()) {
                //                                linkViewValue = "低风险";
                //                            }
                //                            if (checkBox70.isChecked()) {
                //                                linkViewValue = "高风险";
                //                            }
                //                        }
                //                        intent.putExtra("LinkViewValue", linkViewValue);
                //
                //                        Objects.requireNonNull(getActivity()).setResult(5, intent);
                //                        Objects.requireNonNull(getActivity()).finish();
                //                    }
                //                }
            }
        });

    }

    private void initview(View view) {
        llNurrecord = view.findViewById(R.id.ll_nurrecord);

    }

    private void initData() {
        NurRecordOldApiManager.GetXmlValues(episodeID, emrCode, recId, new NurRecordOldApiManager.GetXmlValuesCallback() {
            @Override
            public void onSuccess(ElementDataBean elementDataBean) {
                elements = elementDataBean.getData().getInput().getElementBases();
                firstIdListBeans = elementDataBean.getFirstIdList();
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

            } else if ("CheckElement".equals(element.getElementType())) {
                LinearLayout llCheck = getCheckView(element);

                llCheck.clearAnimation();
                //入院评估
                if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode)) {
                    //初始化view显示隐藏
                    //                    if ("72".equals(element.getElementId())) {
                    //                        CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
                    //                        if (checkBox69 != null && checkBox69.isChecked()) {
                    //                            llCheck.setVisibility(View.VISIBLE);
                    //                        } else {
                    //                            lldropRadio.setVisibility(View.GONE);
                    //                        }
                    //                    }
                }

                llNurrecord.addView(llCheck);
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
                LinearLayout llNumber = getEditText(element, "NumberElement");
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
                LinearLayout lledit = getEditText(element, "TextElement");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));


                if ("".equals(recId)) {
                    //Barthel评分自理能力等级不可编辑
                    if ("DHCNURBarthelLR".equals(emrCode) && "36".equals(element.getElementId())) {
                        edText.setEnabled(false);
                    }

                    //护士签名
                    //Barthel评分
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
                    //入院评估
                    if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode) && "1803".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                    //护理记录单
                    if ("DHCNURHLJLDLR".equals(emrCode) && "44".equals(element.getElementId())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                }

                lledit.clearAnimation();
                //入院评估
                if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode)) {
                    //初始化view显示隐藏

                    if ("95".equals(element.getElementId())) {
                        CheckBox checkBox92 = (CheckBox) viewHashMap.get("92");
                        if (checkBox92 != null && checkBox92.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }

                    if ("104".equals(element.getElementId())) {
                        CheckBox checkBox103 = (CheckBox) viewHashMap.get("103");
                        if (checkBox103 != null && checkBox103.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("1762".equals(element.getElementId())) {
                        CheckBox checkBox106 = (CheckBox) viewHashMap.get("106");
                        if (checkBox106 != null && checkBox106.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("1764".equals(element.getElementId()) || "1766".equals(element.getElementId())) {
                        CheckBox checkBox1761 = (CheckBox) viewHashMap.get("1761");
                        if (checkBox1761 != null && checkBox1761.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("189".equals(element.getElementId())) {
                        CheckBox checkBox188 = (CheckBox) viewHashMap.get("188");
                        if (checkBox188 != null && checkBox188.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("197".equals(element.getElementId())) {
                        CheckBox checkBox196 = (CheckBox) viewHashMap.get("196");
                        if (checkBox196 != null && checkBox196.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("1769".equals(element.getElementId())) {
                        CheckBox checkBox1768 = (CheckBox) viewHashMap.get("1768");
                        if (checkBox1768 != null && checkBox1768.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("232".equals(element.getElementId()) || "234".equals(element.getElementId()) || "236".equals(element.getElementId())) {
                        CheckBox checkBox229 = (CheckBox) viewHashMap.get("229");
                        if (checkBox229 != null && checkBox229.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }
                    if ("1801".equals(element.getElementId())) {
                        CheckBox checkBox1800 = (CheckBox) viewHashMap.get("1800");
                        if (checkBox1800 != null && checkBox1800.isChecked()) {
                            lledit.setVisibility(View.VISIBLE);
                        } else {
                            lledit.setVisibility(View.GONE);
                        }
                    }


                    //关联跳转
                    //跳转ADL
                    if ("1773".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(0);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("LinkViewCode", "24");
                                startFragment(NurRecordNewFragment.class, bundle, 1);
                            }
                        };
                        spannableString.setSpan(clickableSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#62ABFF"));
                        spannableString.setSpan(colorSpan, 0, title.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        tvTitle.setText(spannableString);
                        tvTitle.setMovementMethod(LinkMovementMethod.getInstance());

                        edText.setEnabled(false);
                    }
                    //跳转压疮
                    if ("1780".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(3);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("LinkViewCode", "60");
                                startFragment(NurRecordNewFragment.class, bundle, 2);
                            }
                        };
                        spannableString.setSpan(clickableSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#62ABFF"));
                        spannableString.setSpan(colorSpan, 0, title.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        tvTitle.setText(spannableString);
                        tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
                        edText.setEnabled(false);
                    }
                    //跳转跌倒
                    if ("1788".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(1);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("LinkViewCode", "99");
                                startFragment(NurRecordNewFragment.class, bundle, 3);
                            }
                        };
                        spannableString.setSpan(clickableSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#62ABFF"));
                        spannableString.setSpan(colorSpan, 0, title.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        tvTitle.setText(spannableString);
                        tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
                        edText.setEnabled(false);
                    }
                }


                llNurrecord.addView(lledit);
                llNurrecord.addView(getDashLine());
            } else if ("TextareaElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element, "TextareaElement");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

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
                            }, Integer.parseInt(DateTimeStr.split(":")[0]), Integer.parseInt(DateTimeStr.split(":")[1]),
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
        viewHashMap.put(element.getFormName() + "_ll", linearLayout);

        return linearLayout;
    }

    private LinearLayout getCheckView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
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

        LinearLayout llCheck = new LinearLayout(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
        llCheck.setLayoutParams(tvparams2);
        llCheck.setOrientation(LinearLayout.VERTICAL);
        llCheck.setGravity(Gravity.CENTER);

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
            llCheck.addView(checkBox);
        }

        linearLayout.addView(tvTitle);
        linearLayout.addView(llCheck);

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

                        //Barthel评分
                        if ("DHCNURBarthelLR".equals(emrCode)) {
                            //                                showToast("分数变化" + itemScore);
                            TextView tvScore = (TextView) viewHashMap.get("24");
                            if (StringUtils.isEmpty(tvScore.getText().toString())) {
                                tvScore.setText(itemScore + "");
                            } else {
                                tvScore.setText((Integer.parseInt(tvScore.getText().toString()) + itemScore) + "");
                            }

                            TextView tvGrade = (TextView) viewHashMap.get("36");
                            int grade = Integer.parseInt(StringUtils.isTrimEmpty(tvScore.getText().toString()) ? "-1" : tvScore.getText().toString());
                            if (grade < 0) {

                            } else if (grade < 41) {
                                tvGrade.setText("重度依赖");
                            } else if (grade < 61) {
                                tvGrade.setText("中度依赖");
                            } else if (grade < 100) {
                                tvGrade.setText("轻度依赖");
                            } else {
                                tvGrade.setText("无需依赖");
                            }
                        }
                        //跌倒评估
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

                        //管道滑脱
                        if ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode)) {
                            if ("7".equals(textView.getTag().toString()) ||
                                    "16".equals(textView.getTag().toString()) ||
                                    "15".equals(textView.getTag().toString())) {
                                //                                    showToast("分数变化" + itemScore);
                                TextView tvScore = (TextView) viewHashMap.get("18");
                                if (StringUtils.isEmpty(tvScore.getText().toString())) {
                                    tvScore.setText(itemScore + "");
                                } else {
                                    tvScore.setText((Integer.parseInt(tvScore.getText().toString()) + itemScore) + "");
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

    private LinearLayout getEditText(ElementDataBean.DataBean.InputBean.ElementBasesBean element, String elementType) {
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

        viewHashMap.put(element.getElementId() + "_title", tvTitle);


        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams edparams1 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        LinearLayout.LayoutParams edparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(70f), 2.0f);
        if ("TextareaElement".equals(elementType)) {
            editText.setLayoutParams(edparams2);
        } else {
            editText.setLayoutParams(edparams1);
        }
        editText.setGravity(Gravity.CENTER);
        editText.setTextColor(Color.parseColor("#4a4a4a"));
        editText.setTextSize(14f);
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
                        cancelCheck("70", "71");
                        break;
                    case "70":
                        linearLayout74.setVisibility(View.VISIBLE);
                        cancelCheck("69", "71");
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
                        cancelCheck("69", "70");
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
                        cancelCheck("219", "218");
                        break;
                    case "219":
                        itemScore = itemScore + 2;
                        cancelCheck("216", "218");
                        break;
                    case "218":
                        itemScore = itemScore + 3;
                        cancelCheck("216", "219");
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

        //入院评估
        if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode)) {

            TextView textView95 = (TextView) viewHashMap.get("95");
            LinearLayout linearLayout95 = (LinearLayout) viewHashMap.get("95_ll");
            TextView textView104 = (TextView) viewHashMap.get("104");
            LinearLayout linearLayout104 = (LinearLayout) viewHashMap.get("104_ll");
            TextView textView1762 = (TextView) viewHashMap.get("1762");
            LinearLayout linearLayout1762 = (LinearLayout) viewHashMap.get("1762_ll");
            TextView textView1764 = (TextView) viewHashMap.get("1764");
            LinearLayout linearLayout1764 = (LinearLayout) viewHashMap.get("1764_ll");
            TextView textView1766 = (TextView) viewHashMap.get("1766");
            LinearLayout linearLayout1766 = (LinearLayout) viewHashMap.get("1766_ll");
            TextView textView189 = (TextView) viewHashMap.get("189");
            LinearLayout linearLayout189 = (LinearLayout) viewHashMap.get("189_ll");
            TextView textView197 = (TextView) viewHashMap.get("197");
            LinearLayout linearLayout197 = (LinearLayout) viewHashMap.get("197_ll");
            TextView textView1769 = (TextView) viewHashMap.get("1769");
            LinearLayout linearLayout1769 = (LinearLayout) viewHashMap.get("1769_ll");
            TextView textView232 = (TextView) viewHashMap.get("232");
            LinearLayout linearLayout232 = (LinearLayout) viewHashMap.get("232_ll");
            TextView textView234 = (TextView) viewHashMap.get("234");
            LinearLayout linearLayout234 = (LinearLayout) viewHashMap.get("234_ll");
            TextView textView236 = (TextView) viewHashMap.get("236");
            LinearLayout linearLayout236 = (LinearLayout) viewHashMap.get("236_ll");
            TextView textView1801 = (TextView) viewHashMap.get("1801");
            LinearLayout linearLayout1801 = (LinearLayout) viewHashMap.get("1801_ll");
            //            CheckBox checkBox1815 = (CheckBox) viewHashMap.get("1815");
            //            CheckBox checkBox1816 = (CheckBox) viewHashMap.get("1816");
            //            LinearLayout linearLayout1815 = (LinearLayout) viewHashMap.get("RadioElement_1815_ll");
            //            CheckBox checkBox1818 = (CheckBox) viewHashMap.get("1818");
            //            CheckBox checkBox1820 = (CheckBox) viewHashMap.get("1820");
            //            CheckBox checkBox1821 = (CheckBox) viewHashMap.get("1821");
            //            LinearLayout linearLayout1818 = (LinearLayout) viewHashMap.get("RadioElement_1818_ll");

            if (isChecked) {
                switch (buttonView.getTag().toString()) {
                    case "44":
                        cancelCheck("46", "47");
                        break;
                    case "46":
                        cancelCheck("44", "47");
                        break;
                    case "47":
                        cancelCheck("44", "46");
                        break;
                    case "48":
                        cancelCheck("50", "51", "52");
                        break;
                    case "50":
                        cancelCheck("48", "51", "52");
                        break;
                    case "51":
                        cancelCheck("48", "50", "52");
                        break;
                    case "52":
                        cancelCheck("48", "50", "51");
                        break;
                    case "53":
                        cancelCheck("55", "56", "57", "58");
                        break;
                    case "55":
                        cancelCheck("53", "56", "57", "58");
                        break;
                    case "56":
                        cancelCheck("53", "55", "57", "58");
                        break;
                    case "57":
                        cancelCheck("53", "55", "56", "58");
                        break;
                    case "58":
                        cancelCheck("53", "55", "56", "57");
                        break;
                    case "87":
                        cancelCheck("89", "90", "91", "92");
                        textView95.setText("");
                        linearLayout95.setVisibility(View.GONE);
                        break;
                    case "89":
                        cancelCheck("87", "90", "91", "92");
                        textView95.setText("");
                        linearLayout95.setVisibility(View.GONE);
                        break;
                    case "90":
                        cancelCheck("87", "89", "91", "92");
                        textView95.setText("");
                        linearLayout95.setVisibility(View.GONE);
                        break;
                    case "91":
                        cancelCheck("87", "89", "90", "92");
                        textView95.setText("");
                        linearLayout95.setVisibility(View.GONE);
                        break;
                    case "92":
                        cancelCheck("87", "89", "90", "91");
                        linearLayout95.setVisibility(View.VISIBLE);
                        break;
                    case "97":
                        cancelCheck("99", "100", "101", "102", "103");
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "99":
                        cancelCheck("97", "100", "101", "102", "103");
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "100":
                        cancelCheck("97", "99", "101", "102", "103");
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "101":
                        cancelCheck("97", "99", "100", "102", "103");
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "102":
                        cancelCheck("97", "99", "100", "101", "103");
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "103":
                        cancelCheck("97", "99", "100", "101", "102");
                        linearLayout104.setVisibility(View.VISIBLE);
                        break;
                    case "106":
                        cancelCheck("1761");
                        linearLayout1762.setVisibility(View.VISIBLE);
                        textView1764.setText("");
                        textView1766.setText("");
                        linearLayout1764.setVisibility(View.GONE);
                        linearLayout1766.setVisibility(View.GONE);
                        break;
                    case "1761":
                        cancelCheck("106");
                        textView1762.setText("");
                        linearLayout1762.setVisibility(View.GONE);
                        linearLayout1764.setVisibility(View.VISIBLE);
                        linearLayout1766.setVisibility(View.VISIBLE);
                        break;
                    case "124":
                        cancelCheck("126", "127", "128", "129", "130");
                        break;
                    case "126":
                        cancelCheck("124", "127", "128", "129", "130");
                        break;
                    case "127":
                        cancelCheck("124", "126", "128", "129", "130");
                        break;
                    case "128":
                        cancelCheck("124", "126", "127", "129", "130");
                        break;
                    case "129":
                        cancelCheck("124", "126", "127", "128", "130");
                        break;
                    case "130":
                        cancelCheck("124", "126", "127", "128", "129");
                        break;
                    case "133":
                        cancelCheck("135", "136", "137", "138", "139", "1759");
                        break;
                    case "135":
                        cancelCheck("133", "136", "137", "138", "139", "1759");
                        break;
                    case "136":
                        cancelCheck("133", "135", "137", "138", "139", "1759");
                        break;
                    case "137":
                        cancelCheck("133", "135", "136", "138", "139", "1759");
                        break;
                    case "138":
                        cancelCheck("133", "135", "136", "137", "139", "1759");
                        break;
                    case "139":
                        cancelCheck("133", "135", "136", "137", "138", "1759");
                        break;
                    case "1759":
                        cancelCheck("133", "135", "136", "137", "138", "139");
                        break;
                    case "146":
                        cancelCheck("148", "149", "150", "151", "152", "153", "1760");
                        break;
                    case "148":
                        cancelCheck("146", "149", "150", "151", "152", "153", "1760");
                        break;
                    case "149":
                        cancelCheck("146", "148", "150", "151", "152", "153", "1760");
                        break;
                    case "150":
                        cancelCheck("146", "148", "149", "151", "152", "153", "1760");
                        break;
                    case "151":
                        cancelCheck("146", "148", "149", "150", "152", "153", "1760");
                        break;
                    case "152":
                        cancelCheck("146", "148", "149", "150", "151", "153", "1760");
                        break;
                    case "153":
                        cancelCheck("146", "148", "149", "150", "151", "152", "1760");
                        break;
                    case "1760":
                        cancelCheck("146", "148", "149", "150", "151", "152", "153");
                        break;
                    case "160":
                        cancelCheck("162");
                        break;
                    case "162":
                        cancelCheck("160");
                        break;
                    case "165":
                        cancelCheck("167", "168", "169", "170");
                        break;
                    case "167":
                        cancelCheck("165", "168", "169", "170");
                        break;
                    case "168":
                        cancelCheck("165", "167", "169", "170");
                        break;
                    case "169":
                        cancelCheck("165", "167", "168", "170");
                        break;
                    case "170":
                        cancelCheck("165", "167", "168", "169");
                        break;
                    case "173":
                        cancelCheck("175", "176", "177");
                        break;
                    case "175":
                        cancelCheck("173", "176", "177");
                        break;
                    case "176":
                        cancelCheck("173", "175", "177");
                        break;
                    case "177":
                        cancelCheck("173", "175", "176");
                        break;
                    case "183":
                        cancelCheck("185", "186", "187", "188");
                        textView189.setText("");
                        linearLayout189.setVisibility(View.GONE);
                        break;
                    case "185":
                        cancelCheck("183", "186", "187", "188");
                        textView189.setText("");
                        linearLayout189.setVisibility(View.GONE);
                        break;
                    case "186":
                        cancelCheck("183", "185", "187", "188");
                        textView189.setText("");
                        linearLayout189.setVisibility(View.GONE);
                        break;
                    case "187":
                        cancelCheck("183", "185", "186", "188");
                        textView189.setText("");
                        linearLayout189.setVisibility(View.GONE);
                        break;
                    case "188":
                        cancelCheck("183", "185", "186", "187");
                        linearLayout189.setVisibility(View.VISIBLE);
                        break;
                    case "191":
                        cancelCheck("193", "194", "195", "196");
                        textView197.setText("");
                        linearLayout197.setVisibility(View.GONE);
                        break;
                    case "193":
                        cancelCheck("191", "194", "195", "196");
                        textView197.setText("");
                        linearLayout197.setVisibility(View.GONE);
                        break;
                    case "194":
                        cancelCheck("191", "193", "195", "196");
                        textView197.setText("");
                        linearLayout197.setVisibility(View.GONE);
                        break;
                    case "195":
                        cancelCheck("191", "193", "194", "196");
                        textView197.setText("");
                        linearLayout197.setVisibility(View.GONE);
                        break;
                    case "196":
                        cancelCheck("191", "193", "194", "195");
                        linearLayout197.setVisibility(View.VISIBLE);
                        break;
                    case "199":
                        cancelCheck("201", "202");
                        break;
                    case "201":
                        cancelCheck("199", "202");
                        break;
                    case "202":
                        cancelCheck("199", "201");
                        break;
                    case "203":
                        cancelCheck("205", "206");
                        break;
                    case "205":
                        cancelCheck("203", "206");
                        break;
                    case "206":
                        cancelCheck("203", "205");
                        break;
                    case "1768":
                        linearLayout1769.setVisibility(View.VISIBLE);
                        break;
                    case "227":
                        cancelCheck("229");
                        textView232.setText("");
                        textView234.setText("");
                        textView236.setText("");
                        linearLayout232.setVisibility(View.GONE);
                        linearLayout234.setVisibility(View.GONE);
                        linearLayout236.setVisibility(View.GONE);
                        break;
                    case "229":
                        cancelCheck("227");
                        linearLayout232.setVisibility(View.VISIBLE);
                        linearLayout234.setVisibility(View.VISIBLE);
                        linearLayout236.setVisibility(View.VISIBLE);
                        break;
                    case "240":
                        cancelCheck("242");
                        break;
                    case "242":
                        cancelCheck("240");
                        break;
                    case "243":
                        cancelCheck("245", "246", "247", "248", "249", "250");
                        break;
                    case "245":
                        cancelCheck("243", "246", "247", "248", "249", "250");
                        break;
                    case "246":
                        cancelCheck("243", "245", "247", "248", "249", "250");
                        break;
                    case "247":
                        cancelCheck("243", "245", "246", "248", "249", "250");
                        break;
                    case "248":
                        cancelCheck("243", "245", "246", "247", "249", "250");
                        break;
                    case "249":
                        cancelCheck("243", "245", "246", "247", "248", "250");
                        break;
                    case "250":
                        cancelCheck("243", "245", "246", "247", "248", "249");
                        break;
                    case "1774":
                        cancelCheck("1775", "1776", "1777");
                        break;
                    case "1775":
                        cancelCheck("1774", "1776", "1777");
                        break;
                    case "1776":
                        cancelCheck("1774", "1775", "1777");
                        break;
                    case "1777":
                        cancelCheck("1774", "1775", "1776");
                        break;
                    case "1781":
                        cancelCheck("1782", "1783", "1784", "1785");
                        break;
                    case "1782":
                        cancelCheck("1781", "1783", "1784", "1785");
                        break;
                    case "1783":
                        cancelCheck("1781", "1782", "1784", "1785");
                        break;
                    case "1784":
                        cancelCheck("1781", "1782", "1783", "1785");
                        break;
                    case "1785":
                        cancelCheck("1781", "1782", "1783", "1784");
                        break;
                    case "1789":
                        cancelCheck("1793", "1818", "1820", "1821");
                        break;
                    case "1815":
                        cancelCheck("1816");
                        break;
                    case "1816":
                        cancelCheck("1815");
                        break;
                    case "1793":
                        cancelCheck("1789", "1815", "1816");
                        break;
                    case "1818":
                        cancelCheck("1820", "1821");
                        break;
                    case "1820":
                        cancelCheck("1818", "1821");
                        break;
                    case "1821":
                        cancelCheck("1818", "1820");
                        break;
                    case "1798":
                        cancelCheck("1799", "1800");
                        textView1801.setText("");
                        linearLayout1801.setVisibility(View.GONE);
                        break;
                    case "1799":
                        cancelCheck("1798", "1800");
                        textView1801.setText("");
                        linearLayout1801.setVisibility(View.GONE);
                        break;
                    case "1800":
                        cancelCheck("1798", "1799");
                        linearLayout1801.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;

                }
            } else {
                switch (buttonView.getTag().toString()) {
                    case "92":
                        textView95.setText("");
                        linearLayout95.setVisibility(View.GONE);
                        break;
                    case "103":
                        textView104.setText("");
                        linearLayout104.setVisibility(View.GONE);
                        break;
                    case "106":
                        textView1762.setText("");
                        linearLayout1762.setVisibility(View.GONE);
                        break;
                    case "1761":
                        textView1764.setText("");
                        textView1766.setText("");
                        linearLayout1764.setVisibility(View.GONE);
                        linearLayout1766.setVisibility(View.GONE);
                        break;
                    case "188":
                        textView189.setText("");
                        linearLayout189.setVisibility(View.GONE);
                        break;
                    case "196":
                        textView197.setText("");
                        linearLayout197.setVisibility(View.GONE);
                        break;
                    case "1768":
                        textView1769.setText("");
                        linearLayout1769.setVisibility(View.GONE);
                        break;
                    case "229":
                        textView232.setText("");
                        textView234.setText("");
                        textView236.setText("");
                        linearLayout232.setVisibility(View.GONE);
                        linearLayout234.setVisibility(View.GONE);
                        linearLayout236.setVisibility(View.GONE);
                        break;
                    case "1800":
                        textView1801.setText("");
                        linearLayout1801.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void cancelCheck(String check1, String check2) {
        cancelCheck(check1, check2, "-1", "-1", "-1", "-1", "-1");
    }

    private void cancelCheck(String check1, String check2, String check3) {
        cancelCheck(check1, check2, check3, "-1", "-1", "-1", "-1");
    }

    private void cancelCheck(String check1, String check2, String check3, String check4) {
        cancelCheck(check1, check2, check3, check4, "-1", "-1", "-1");
    }

    private void cancelCheck(String check1, String check2, String check3, String check4, String check5) {
        cancelCheck(check1, check2, check3, check4, check5, "-1", "-1");
    }

    private void cancelCheck(String check1) {
        cancelCheck(check1, "-1", "-1", "-1", "-1", "-1", "-1");
    }

    private void cancelCheck(String check1, String check2, String check3, String check4, String check5, String check6) {
        cancelCheck(check1, check2, check3, check4, check5, check6, "-1");
    }

    private void cancelCheck(String check1, String check2, String check3, String check4, String check5, String check6, String check7) {

        CheckBox checkBox1 = (CheckBox) viewHashMap.get(check1);
        if (checkBox1 != null && checkBox1.isChecked()) {
            checkBox1.setChecked(false);
        }
        if (!"-1".equals(check2)) {
            CheckBox checkBox2 = (CheckBox) viewHashMap.get(check2);
            if (checkBox2 != null && checkBox2.isChecked()) {
                checkBox2.setChecked(false);
            }
        }
        if (!"-1".equals(check3)) {
            CheckBox checkBox3 = (CheckBox) viewHashMap.get(check3);
            if (checkBox3 != null && checkBox3.isChecked()) {
                checkBox3.setChecked(false);
            }
        }
        if (!"-1".equals(check4)) {
            CheckBox checkBox4 = (CheckBox) viewHashMap.get(check4);
            if (checkBox4 != null && checkBox4.isChecked()) {
                checkBox4.setChecked(false);
            }
        }
        if (!"-1".equals(check5)) {
            CheckBox checkBox5 = (CheckBox) viewHashMap.get(check5);
            if (checkBox5 != null && checkBox5.isChecked()) {
                checkBox5.setChecked(false);
            }
        }
        if (!"-1".equals(check6)) {
            CheckBox checkBox6 = (CheckBox) viewHashMap.get(check6);
            if (checkBox6 != null && checkBox6.isChecked()) {
                checkBox6.setChecked(false);
            }
        }
        if (!"-1".equals(check7)) {
            CheckBox checkBox7 = (CheckBox) viewHashMap.get(check7);
            if (checkBox7 != null && checkBox7.isChecked()) {
                checkBox7.setChecked(false);
            }
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
