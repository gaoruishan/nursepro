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
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
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
    //view
    private final HashMap<String, View> viewHashMap = new HashMap<>();
    //id formname 关联
    private final HashMap<String, String> elementIdtoFormName = new HashMap<>();
    private final HashMap<String, List<String>> formNametoElementId = new HashMap<>();
    //drop edittext
    private final HashMap<String, Integer[]> dropValue = new HashMap<>();
    private final HashMap<String, Integer[]> dcDropValue = new HashMap<>();
    //parent child view
    private final HashMap<String, List<String>> pcViewHashMap = new HashMap<>();


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
    private List<String> dcSelectStrList = new ArrayList<>();
    private boolean[] dcTempStatus;
    private ListView lv;
    private List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements;
    private List<ElementDataBean.DataBean.InputBean.ElementSetsBean> elementSetsBeans;
    private List<ElementDataBean.DataBean.InputBean.StatisticsListBean> statisticsListBeans;


    private SPUtils spUtils;

    private List<ElementDataBean.FirstIdListBean> firstIdListBeans = new ArrayList<>();
    //跳转单据关联View
    private String linkViewCode = "";
    private String linkViewValue = "";
    private String fromEMRCode = "";

    private NurRecordTipDialog tipDialog;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
        } else if (requestCode == 2 && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
        } else if (requestCode == 3 && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");

            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
            int grade = Integer.parseInt(StringUtils.isEmpty(value) ? "-1" : value);

            if (grade > -1) {
                CheckBox checkBox1793 = (CheckBox) viewHashMap.get("1793");
                checkBox1793.setChecked(true);
            }
        } else if ((requestCode == 4 || requestCode == 5 || requestCode == 6) && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
        } else if ((requestCode == 7 || requestCode == 8) && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
        } else if (requestCode == 9 && resultCode == 100) {
            String key = data.getStringExtra("LinkViewCode");
            String value = data.getStringExtra("LinkViewValue");
            EditText editText1 = (EditText) viewHashMap.get(key);
            if (editText1 != null) {
                editText1.setText(value);
            }
            int grade = Integer.parseInt(StringUtils.isEmpty(value) ? "-1" : value);

            if (grade > -1) {
                CheckBox checkBox1892 = (CheckBox) viewHashMap.get("1892");
                checkBox1892.setChecked(true);
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
            fromEMRCode = bundle.getString("FromEMRCode", "");
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
        for (int i = 0; elements != null && i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);

            if (stringBuilder.length() > 2 && !stringBuilder.toString().endsWith(",")) {
                stringBuilder.append(",");

            }
            if ("RadioElement".equals(element.getElementType())) {
                List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
                int j;
                for (j = 0; radioElementListBeanList != null && j < radioElementListBeanList.size(); j++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(j);
                    String radioStr = ((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).getText().toString();
                    if (((CheckBox) viewHashMap.get(radioElementListBean.getElementId())).isChecked()) {
                        //"RadioElement_29":[{"NumberValue":"21","Text":"下拉单选2","Value":"2"}]

                        List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean.OprationItemListBean> oprationItemList = radioElementListBean.getOprationItemList();
                        String NumberValueStr = "";
                        String TextStr = "";
                        String ValueStr = "";
                        for (int i1 = 0; oprationItemList != null && i1 < oprationItemList.size(); i1++) {
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

                        //分隔元素去重 单选
                        String str = "\"" + radioElementListBean.getFormName() + "\"" + ":[]";
                        int index = stringBuilder.indexOf(str);
                        if (index > 0) {
                            stringBuilder.delete(index, index + str.length());
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

                if (radioElementListBeanList != null && j >= radioElementListBeanList.size()) {
                    if ("true".equals(radioElementListBeanList.get(0).getRequired())) {

                        if (radioElementListBeanList.size() == 1 && "其他".equals(radioElementListBeanList.get(0).getOprationItemList().get(0).getText())) {

                        } else {


                            List<String> elementIdList = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
                            List<CheckBox> checkBoxList = new ArrayList<>();
                            boolean oneSelected = false;

                            for (int i1 = 0; i1 < elementIdList.size(); i1++) {
                                CheckBox checkBox = (CheckBox) viewHashMap.get(elementIdList.get(i1));
                                if (checkBox != null && checkBox.isChecked()) {
                                    oneSelected = true;
                                    break;
                                }
                            }
                            if (!oneSelected) {
                                for (int i1 = 0; i1 < elementIdList.size(); i1++) {
                                    CheckBox checkBox = (CheckBox) viewHashMap.get(elementIdList.get(i1));
                                    if (checkBox != null) {
                                        checkBox.setTextColor(Color.parseColor("#FFEA4300"));

                                    }
                                }
                                showToast("请填写必填项之后再保存");
                                return;
                            }
                        }
                    }

                    if (!stringBuilder.toString().contains("\"" + radioElementListBeanList.get(0).getFormName() + "\"")) {
                        stringBuilder.append("\"")
                                .append(radioElementListBeanList.get(0).getFormName())
                                .append("\"")
                                .append(":")
                                .append("[]");
                    }
                }


            } else if ("DropRadioElement".equals(element.getElementType())) {
                String dropRadioStr = ((TextView) viewHashMap.get(element.getElementId())).getText().toString();
                if (StringUtils.isTrimEmpty(dropRadioStr)) {
                    LinearLayout linearLayout = (LinearLayout) viewHashMap.get(element.getElementId() + "_ll");
                    TextView textView = (TextView) viewHashMap.get(element.getElementId());
                    if ("true".equals(element.getRequired()) && linearLayout.getVisibility() == View.VISIBLE) {
                        textView.setBackgroundResource(R.drawable.nur_record_btnerror_bg);
                        showToast("请填写必填项之后再保存");
                        return;
                    }

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
                    for (int i1 = 0; oprationItemList != null && i1 < oprationItemList.size(); i1++) {
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

            } else if ("DropCheckboxElement".equals(element.getElementType())) {


                String dcText = ((TextView) viewHashMap.get(element.getElementId())).getText().toString();
                if (StringUtils.isTrimEmpty(dcText)) {
                    LinearLayout linearLayout = (LinearLayout) viewHashMap.get(element.getElementId() + "_ll");
                    TextView textView = (TextView) viewHashMap.get(element.getElementId());
                    if ("true".equals(element.getRequired()) && linearLayout.getVisibility() == View.VISIBLE) {
                        textView.setBackgroundResource(R.drawable.nur_record_btnerror_bg);
                        showToast("请填写必填项之后再保存");
                        return;
                    }

                    //""DropRadioElement_31"":[]
                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\"")
                            .append(":")
                            .append("[]");
                } else {
                    List<ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean> oprationItemListBeanList = element.getOprationItemList();
                    int j;
                    int checkedCount = 0;
                    if (!stringBuilder.toString().endsWith(",")) {
                        stringBuilder.append(",");
                    }
                    for (j = 0; oprationItemListBeanList != null && j < oprationItemListBeanList.size(); j++) {
                        ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = oprationItemListBeanList.get(j);

                        if (j == 0) {
                            stringBuilder.append("\"")
                                    .append(element.getFormName())
                                    .append("\":[");
                        }
                        String[] dcTextItem = dcText.split(",");

                        for (int i1 = 0; i1 < dcTextItem.length; i1++) {
                            String NumberValueStr = "";
                            String TextStr = "";
                            String ValueStr = "";
                            if (dcTextItem[i1].equals(oprationItemListBean.getText())) {
                                NumberValueStr = oprationItemListBean.getNumberValue();
                                TextStr = oprationItemListBean.getText();
                                ValueStr = oprationItemListBean.getValue();
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
                                break;
                            }


                        }

                        if (j == oprationItemListBeanList.size() - 1) {
                            stringBuilder.append("]");
                        }
                    }

                }

            } else if ("CheckElement".equals(element.getElementType())) {
                List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
                int j;
                int checkedCount = 0;
                for (j = 0; radioElementListBeanList != null && j < radioElementListBeanList.size(); j++) {
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
                        for (int i1 = 0; oprationItemList != null && i1 < oprationItemList.size(); i1++) {
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

                if (radioElementListBeanList != null && "true".equals(radioElementListBeanList.get(0).getRequired()) && checkedCount == 0) {
                    for (int i1 = 0; i1 < radioElementListBeanList.size(); i1++) {
                        CheckBox checkBox = (CheckBox) viewHashMap.get(radioElementListBeanList.get(i1).getElementId());
                        if (checkBox != null) {
                            checkBox.setTextColor(Color.parseColor("#FFEA4300"));
                        }
                    }
                    showToast("请填写必填项之后再保存");
                    return;
                }

            } else if ("TextElement".equals(element.getElementType()) || "NumberElement".equals(element.getElementType()) || "TextareaElement".equals(element.getElementType())) {
                //""DateElement_16"":""2020-01-14""
//                if (("DHCNURBarthelLR".equals(emrCode) && "32".equals(element.getElementId())) ||
//                        ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && "65".equals(element.getElementId())) ||
//                        ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode) && "39".equals(element.getElementId())) ||
//                        ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode) && "168".equals(element.getElementId())) ||
//                        ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode) && "1803".equals(element.getElementId())) ||
//                        ("DHCNURHLJLDLR".equals(emrCode) && "44".equals(element.getElementId())) ||
//                        ("DHCNURFKPGDLR".equals(emrCode) && "1803".equals(element.getElementId())) ||
//                        ("DHCNUREKHLPGDLR".equals(emrCode) && "580".equals(element.getElementId())) ||
//                        ("DHCNUREKDDFXPGLBHDLR".equals(emrCode) && "29".equals(element.getElementId())) ||
//                        ("DHCNUREKYCHLJL".equals(emrCode) && "168".equals(element.getElementId())) ||
//                        ("DHCNUREKRCSHNLADLLBBZSLR".equals(emrCode) && "34".equals(element.getElementId()))) {
//
//                    String userStr = "CA" + ((EditText) viewHashMap.get(element.getElementId())).getText().toString() + "*" + spUtils.getString(SharedPreference.USERCODE);
//                    stringBuilder.append("\"")
//                            .append(element.getElementType())
//                            .append("_")
//                            .append(element.getElementId())
//                            .append("\":\"")
//                            .append(userStr)
//                            .append("\"");
//                } else {
                EditText editText = (EditText) viewHashMap.get(element.getElementId());
                if (editText != null) {
//                        LinearLayout linearLayout = (LinearLayout) viewHashMap.get(element.getElementId() + "_ll");
//                        if ("true".equals(element.getRequired()) && StringUtils.isTrimEmpty(editText.getText().toString()) && linearLayout.getVisibility() == View.VISIBLE) {
//                            editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
//                            showToast("请填写必填项之后再保存");
//                            return;
//                        }

                    stringBuilder.append("\"")
                            .append(element.getElementType())
                            .append("_")
                            .append(element.getElementId())
                            .append("\":\"")
                            .append(editText.getText().toString())
                            .append("\"");
                }
//                }

            } else {
                if (viewHashMap.get(element.getElementId()) instanceof TextView) {
                    TextView textView = (TextView) viewHashMap.get(element.getElementId());
                    if (textView != null) {
//                        LinearLayout linearLayout = (LinearLayout) viewHashMap.get(element.getElementId() + "_ll");
//                        if ("true".equals(element.getRequired()) && StringUtils.isTrimEmpty(textView.getText().toString()) && linearLayout.getVisibility() == View.VISIBLE) {
//                            textView.setBackgroundResource(R.drawable.nur_record_btnerror_bg);
//                            showToast("请填写必填项之后再保存");
//                            return;
//                        }

                        stringBuilder.append("\"")
                                .append(element.getElementType())
                                .append("_")
                                .append(element.getElementId())
                                .append("\":\"")
                                .append(textView.getText().toString())
                                .append("\"");
                    }
                }
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
                            if (fromEMRCode.equals("DHCNURRYZKHLPGDCRHZLR")) {
                                intent.putExtra("LinkViewCode", "1788");
                            } else if (fromEMRCode.equals("DHCNURFKPGDLR")) {
                                intent.putExtra("LinkViewCode", "1890");
                            }
                        } else if ("DHCNUREKRCSHNLADLLBBZSLR".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "550");
                        } else if ("DHCNUREKYCHLJL".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "557");
                        } else if ("DHCNUREKDDFXPGLBHDLR".equals(emrCode)) {
                            intent.putExtra("LinkViewCode", "565");
                        }
                        intent.putExtra("LinkViewValue", linkViewValue);
                        Objects.requireNonNull(getActivity()).setResult(100, intent);
                    }
                }
                Objects.requireNonNull(getActivity()).finish();
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
//                            if (fromEMRCode.equals("DHCNURRYZKHLPGDCRHZLR")) {
//                                intent.putExtra("LinkViewCode", "1788");
//                            } else if (fromEMRCode.equals("DHCNURFKPGDLR")) {
//                                intent.putExtra("LinkViewCode", "1890");
//                            }
//
////                            CheckBox checkBox69 = (CheckBox) viewHashMap.get("69");
////                            CheckBox checkBox70 = (CheckBox) viewHashMap.get("70");
////
////                            if (checkBox69.isChecked()) {
////                                linkViewValue = "低风险";
////                            }
////                            if (checkBox70.isChecked()) {
////                                linkViewValue = "高风险";
////                            }
//
//                        } else if ("DHCNUREKRCSHNLADLLBBZSLR".equals(emrCode)) {
//                            intent.putExtra("LinkViewCode", "550");
//                        } else if ("DHCNUREKYCHLJL".equals(emrCode)) {
//                            intent.putExtra("LinkViewCode", "557");
//                        } else if ("DHCNUREKDDFXPGLBHDLR".equals(emrCode)) {
//                            intent.putExtra("LinkViewCode", "565");
//                        }
//                        intent.putExtra("LinkViewValue", linkViewValue);
//                        Objects.requireNonNull(getActivity()).setResult(100, intent);
//                    }
//                }
//                Objects.requireNonNull(getActivity()).finish();
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
                elementSetsBeans = elementDataBean.getData().getInput().getElementSets();
                statisticsListBeans = elementDataBean.getData().getInput().getStatisticsList();
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
        for (int i = 0; elements != null && i < elements.size(); i++) {
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

                llNurrecord.addView(llCheck);
                llNurrecord.addView(getDashLine());

            } else if ("DropRadioElement".equals(element.getElementType())) {

                LinearLayout lldropRadio = getTextView(element);
                TextView tvdropRadio = (TextView) viewHashMap.get(element.getElementId());
                tvdropRadio.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                tvdropRadio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvdropRadio.setBackgroundResource(R.drawable.nur_record_btn_bg);
                        selectStrList = new ArrayList<>();
                        selectScoreList = new ArrayList<>();
                        for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                            try {
                                selectStrList.add(element.getOprationItemList().get(i1).getText());
                                int numberValue;
                                if ("√".equals(element.getOprationItemList().get(i1).getNumberValue())) {
                                    numberValue = 0;
                                } else {
                                    numberValue = Integer.parseInt(element.getOprationItemList().get(i1).getNumberValue());
                                }
                                selectScoreList.add(numberValue);
                            } catch (NumberFormatException e) {
                                showToast("分值转换出错");
                                e.printStackTrace();
                            }
                        }
                        selectStrList.add("");
                        selectScoreList.add(0);

                        ShowRadioScore(selectStrList, getActivity(), tvdropRadio);
                    }
                });

                lldropRadio.clearAnimation();

                llNurrecord.addView(lldropRadio);
                llNurrecord.addView(getDashLine());
            } else if ("DropCheckboxElement".equals(element.getElementType())) {
                LinearLayout lldropCheck = getTextView(element);
                TextView tvdropCheck = (TextView) viewHashMap.get(element.getElementId());
                tvdropCheck.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                tvdropCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvdropCheck.setBackgroundResource(R.drawable.nur_record_btn_bg);
                        dcSelectStrList = new ArrayList<>();
                        for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                            dcSelectStrList.add(element.getOprationItemList().get(i1).getText());
                        }
                        dcSelectStrList.add("全选/取消全选");

                        ShowDropCheck(dcSelectStrList, getActivity(), tvdropCheck);
                    }
                });

                lldropCheck.clearAnimation();

                llNurrecord.addView(lldropCheck);
                llNurrecord.addView(getDashLine());

            } else if ("NumberElement".equals(element.getElementType())) {
                LinearLayout llNumber = getEditText(element, "NumberElement");
                EditText edNumber = (EditText) viewHashMap.get(element.getElementId());
                edNumber.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                llNurrecord.addView(llNumber);
                llNurrecord.addView(getDashLine());
            } else if ("TextElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element, "TextElement");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                if ("true".equals(element.getSignatureAuto())) {
                    if ("CommonNOReplace".equals(element.getSignature())) {
                        if ("".equals(recId)) {
                            edText.setText(spUtils.getString(SharedPreference.USERNAME));
                        }
                    } else if ("Common".equals(element.getSignature())) {
                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
                    }
                }

//                if ("".equals(recId)) {
//
//                    //护士签名
//                    //Barthel评分
//                    if ("DHCNURBarthelLR".equals(emrCode) && "32".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //跌倒评估
//                    if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && "65".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //管道滑脱
//                    if ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode) && "39".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //压疮评估
//                    if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode) && "168".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //入院评估
//                    if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode) && "1803".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //护理记录单
//                    if ("DHCNURHLJLDLR".equals(emrCode) && "44".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //产科入院评估
//                    if ("DHCNURFKPGDLR".equals(emrCode) && "1803".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //儿科入院评估
//                    if ("DHCNUREKHLPGDLR".equals(emrCode) && "580".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //儿科跌倒评估
//                    if ("DHCNUREKDDFXPGLBHDLR".equals(emrCode) && "29".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //儿科压疮评估
//                    if ("DHCNUREKYCHLJL".equals(emrCode) && "168".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                    //儿科日常生活ADL评估
//                    if ("DHCNUREKRCSHNLADLLBBZSLR".equals(emrCode) && "34".equals(element.getElementId())) {
//                        edText.setText(spUtils.getString(SharedPreference.USERNAME));
//                    }
//                }

                lledit.clearAnimation();
                //成人入院评估
                if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode)) {

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
                                bundle.putString("FromEMRCode", "DHCNURRYZKHLPGDCRHZLR");
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
                                bundle.putString("FromEMRCode", "DHCNURRYZKHLPGDCRHZLR");
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
                                bundle.putString("FromEMRCode", "DHCNURRYZKHLPGDCRHZLR");
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

                //儿科入院评估
                if ("DHCNUREKHLPGDLR".equals(emrCode)) {

                    //关联跳转
                    //跳转ADL
                    if ("550".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(10);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("FromEMRCode", "DHCNUREKHLPGDLR");
                                bundle.putString("LinkViewCode", "29");
                                startFragment(NurRecordNewFragment.class, bundle, 4);
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
                    if ("557".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(9);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("FromEMRCode", "DHCNUREKHLPGDLR");
                                bundle.putString("LinkViewCode", "60");
                                startFragment(NurRecordNewFragment.class, bundle, 5);
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
                    if ("565".equals(element.getElementId())) {
                        TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                        String title = tvTitle.getText().toString();
                        SpannableString spannableString = new SpannableString(title);

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                ElementDataBean.FirstIdListBean firstIdListBean = firstIdListBeans.get(8);
                                Bundle bundle = new Bundle();
                                bundle.putString("EpisodeID", episodeID);
                                bundle.putString("BedNo", bedNo);
                                bundle.putString("PatName", patName);
                                bundle.putString("EMRCode", firstIdListBean.getEmrCode());
                                bundle.putString("GUID", firstIdListBean.getGuId());
                                bundle.putString("RecID", firstIdListBean.getRecId());
                                bundle.putString("FromEMRCode", "DHCNUREKHLPGDLR");
                                bundle.putString("LinkViewCode", "21");
                                startFragment(NurRecordNewFragment.class, bundle, 6);
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
                //产科入院评估
                if ("DHCNURFKPGDLR".equals(emrCode)) {

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
                                bundle.putString("FromEMRCode", "DHCNURFKPGDLR");
                                bundle.putString("LinkViewCode", "24");
                                startFragment(NurRecordNewFragment.class, bundle, 7);
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
                                bundle.putString("FromEMRCode", "DHCNURFKPGDLR");
                                bundle.putString("LinkViewCode", "60");
                                startFragment(NurRecordNewFragment.class, bundle, 8);
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
                    if ("1890".equals(element.getElementId())) {
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
                                bundle.putString("FromEMRCode", "DHCNURFKPGDLR");
                                bundle.putString("LinkViewCode", "99");
                                startFragment(NurRecordNewFragment.class, bundle, 9);
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

        setViews("", "");
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

        if (!StringUtils.isEmpty(element.getToolTipText())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipDialog = new NurRecordTipDialog(getActivity());
                    tipDialog.setNurRecordTip(element.getToolTipText());
                    tipDialog.setSureOnclickListener(new NurRecordTipDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            tipDialog.dismiss();
                        }
                    });
                    tipDialog.show();
                }
            });
        }

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        textView.setLayoutParams(tvparams2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#4a4a4a"));
        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
        if (!StringUtils.isEmpty(element.getDefaultValue())) {
            textView.setText(element.getDefaultValue());
            if ("DropRadioElement".equals(element.getElementType())) {
                int defaultScore = 0;
                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
                    if (element.getDefaultValue().equals(oprationItemListBean.getText())) {
                        defaultScore = Integer.parseInt(oprationItemListBean.getValue());
                        break;
                    }
                }
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            }
        } else {
            if ("DropRadioElement".equals(element.getElementType())) {

                int defaultScore = 0;
                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
                    if ("true".equals(oprationItemListBean.getIsSelect())) {
                        textView.setText(oprationItemListBean.getText());
                        defaultScore = Integer.parseInt(oprationItemListBean.getValue());
                        break;
                    }
                }
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            }
        }

        if ("true".equals(element.getDisable())) {
            textView.setClickable(false);
        }

        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);

        linearLayout.addView(tvTitle);
        linearLayout.addView(textView);

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(element.getParentId())) {
            List<String> childElementIdList = new ArrayList<>();
            childElementIdList.add(element.getElementId());
            if (pcViewHashMap.containsKey(element.getParentId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(element.getParentId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(element.getParentId(), childElementIdList);
        }


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

        if (!StringUtils.isEmpty(element.getToolTipText())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipDialog = new NurRecordTipDialog(getActivity());
                    tipDialog.setNurRecordTip(element.getToolTipText());
                    tipDialog.setSureOnclickListener(new NurRecordTipDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            tipDialog.dismiss();
                        }
                    });
                    tipDialog.show();
                }
            });
        }

        LinearLayout llRadio = new LinearLayout(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
        llRadio.setLayoutParams(tvparams2);
        llRadio.setOrientation(LinearLayout.VERTICAL);
        llRadio.setGravity(Gravity.CENTER);


        List<String> viewElementIdList = new ArrayList<>();
        List<String> childElementIdList = new ArrayList<>();
        List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
        for (int i = 0; radioElementListBeanList != null && i < radioElementListBeanList.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(i);
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(radioElementListBean.getNameText());
            checkBox.setTag(radioElementListBean.getElementId());
            if (!StringUtils.isTrimEmpty(radioElementListBean.getDefaultValue()) && radioElementListBean.getNameText().equals(radioElementListBean.getDefaultValue())) {
                checkBox.setChecked(true);
            } else {
                if ("1".equals(radioElementListBean.getIsSelect())) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            }

            checkBox.setOnCheckedChangeListener(this);

            if ("true".equals(radioElementListBean.getDisable())) {
                checkBox.setClickable(false);
            }

            if ("true".equals(element.getIsHide())) {
                checkBox.clearAnimation();
                checkBox.setVisibility(View.GONE);
            }
            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue(), checkBox);
            elementIdtoFormName.put(radioElementListBean.getElementId(), radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue());
            viewElementIdList.add(radioElementListBean.getElementId());
            childElementIdList.add(radioElementListBean.getElementId());

            llRadio.addView(checkBox);
        }
        if (formNametoElementId.containsKey(radioElementListBeanList.get(0).getFormName())) {
            List<String> viewElementIdListExist = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
            viewElementIdList.addAll(viewElementIdListExist);
        }
        formNametoElementId.put(radioElementListBeanList.get(0).getFormName(), viewElementIdList);

        linearLayout.addView(tvTitle);
        linearLayout.addView(llRadio);
        viewHashMap.put(radioElementListBeanList.get(0).getFormName() + "_ll", linearLayout);
        if (!StringUtils.isEmpty(radioElementListBeanList.get(0).getParentId())) {
            if (pcViewHashMap.containsKey(radioElementListBeanList.get(0).getParentId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(radioElementListBeanList.get(0).getParentId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(radioElementListBeanList.get(0).getParentId(), childElementIdList);
        }

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

        if (!StringUtils.isEmpty(element.getToolTipText())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipDialog = new NurRecordTipDialog(getActivity());
                    tipDialog.setNurRecordTip(element.getToolTipText());
                    tipDialog.setSureOnclickListener(new NurRecordTipDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            tipDialog.dismiss();
                        }
                    });
                    tipDialog.show();
                }
            });
        }

        LinearLayout llCheck = new LinearLayout(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
        llCheck.setLayoutParams(tvparams2);
        llCheck.setOrientation(LinearLayout.VERTICAL);
        llCheck.setGravity(Gravity.CENTER);

        List<String> viewElementIdList = new ArrayList<>();
        List<String> childElementIdList = new ArrayList<>();
        List<ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean> radioElementListBeanList = element.getRadioElementList();
        for (int i = 0; radioElementListBeanList != null && i < radioElementListBeanList.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElementListBean = radioElementListBeanList.get(i);
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(radioElementListBean.getNameText());
            checkBox.setTag(radioElementListBean.getElementId());
            if (!StringUtils.isTrimEmpty(radioElementListBean.getDefaultValue()) && radioElementListBean.getNameText().equals(radioElementListBean.getDefaultValue())) {
                checkBox.setChecked(true);
            } else {
                if ("1".equals(radioElementListBean.getIsSelect())) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            }

            checkBox.setOnCheckedChangeListener(this);

            if ("true".equals(radioElementListBean.getDisable())) {
                checkBox.setClickable(false);
            }

            if ("true".equals(element.getIsHide())) {
                checkBox.clearAnimation();
                checkBox.setVisibility(View.GONE);
            }
            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue(), checkBox);
            elementIdtoFormName.put(radioElementListBean.getElementId(), radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue());
            viewElementIdList.add(radioElementListBean.getElementId());
            childElementIdList.add(radioElementListBean.getElementId());

            llCheck.addView(checkBox);
        }

        if (formNametoElementId.containsKey(radioElementListBeanList.get(0).getFormName())) {
            List<String> viewElementIdListExist = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
            viewElementIdList.addAll(viewElementIdListExist);
        }
        formNametoElementId.put(radioElementListBeanList.get(0).getFormName(), viewElementIdList);

        linearLayout.addView(tvTitle);
        linearLayout.addView(llCheck);

        viewHashMap.put(radioElementListBeanList.get(0).getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(radioElementListBeanList.get(0).getParentId())) {
            if (pcViewHashMap.containsKey(radioElementListBeanList.get(0).getParentId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(radioElementListBeanList.get(0).getParentId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(radioElementListBeanList.get(0).getParentId(), childElementIdList);
        }

        return linearLayout;
    }

    private void ShowRadioScore(List<String> selectStrList, Context context, final TextView textView) {
        itemScore = 0;
        mSingleChoiceID = -1;
        String[] mItems = new String[selectStrList.size()];
        for (int i = 0; i < selectStrList.size(); i++) {
            if (textView.getText().toString().equals(selectStrList.get(i))) {
                mSingleChoiceID = i;
                itemScore = selectScoreList.get(i);
                dropValue.put(textView.getTag().toString(), new Integer[]{dropValue.get(textView.getTag().toString()) == null ? 0 : dropValue.get(textView.getTag().toString())[1], itemScore});
            }
            mItems[i] = selectStrList.get(i);
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);

        localBuilder.setSingleChoiceItems(mItems, mSingleChoiceID,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        try {
//                            if (mSingleChoiceID != -1) {
//                                itemScore = itemScore - selectScoreList.get(mSingleChoiceID);
//                                itemScore = itemScore + selectScoreList.get(whichButton);
//                            } else {
//                                itemScore = itemScore + selectScoreList.get(whichButton);
//                            }
                            itemScore = selectScoreList.get(whichButton);
                            mSingleChoiceID = whichButton;
                        } catch (Exception e) {
                            //                            showToast("分值计算出错");
                            e.printStackTrace();
                        }
                    }
                });
        localBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(mItems[mSingleChoiceID]);
                        dropValue.put(textView.getTag().toString(), new Integer[]{dropValue.get(textView.getTag().toString()) == null ? 0 : dropValue.get(textView.getTag().toString())[1], itemScore});
                        setViews(textView.getTag().toString(), "drop");
                    }
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    private void ShowDropCheck(List<String> dcSelectStrList, Context context, final TextView textView) {
        CharSequence[] dcmItems = new CharSequence[dcSelectStrList.size()];
        dcTempStatus = new boolean[dcSelectStrList.size()];
        for (int i = 0; i < dcSelectStrList.size(); i++) {
            dcmItems[i] = dcSelectStrList.get(i);
            String[] dcText = textView.getText().toString().split(",");
            dcTempStatus[i] = false;
            for (int i1 = 0; i1 < dcText.length; i1++) {
                if (dcText[i1].equals(dcSelectStrList.get(i))) {
                    dcTempStatus[i] = true;
                    break;
                }
            }
        }

        AlertDialog ad = new AlertDialog.Builder(context)
                .setTitle("选择")
                .setMultiChoiceItems(dcmItems, dcTempStatus, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (which == (dcmItems.length - 1)) {
                            for (int i = 0; i < dcmItems.length; i++) {
                                lv.setItemChecked(i, isChecked);
                                dcTempStatus[i] = isChecked;
                            }
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String itmtxt = "";
                        for (int i = 0; i < (dcmItems.length - 1); i++) {
                            if (lv.getCheckedItemPositions().get(i)) {
                                itmtxt = itmtxt + dcSelectStrList.get(i) + ",";
                            }
                        }
                        if (itmtxt.endsWith(",")) {
                            itmtxt = itmtxt.substring(0, itmtxt.length() - 1);
                        }
                        textView.setText(itmtxt);
                    }
                }).setNegativeButton("取消", null).create();
        lv = ad.getListView();
        ad.show();
        ad.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
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

        if (!StringUtils.isEmpty(element.getToolTipText())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipDialog = new NurRecordTipDialog(getActivity());
                    tipDialog.setNurRecordTip(element.getToolTipText());
                    tipDialog.setSureOnclickListener(new NurRecordTipDialog.onSureOnclickListener() {
                        @Override
                        public void onSureClick() {
                            tipDialog.dismiss();
                        }
                    });
                    tipDialog.show();
                }
            });
        }

        viewHashMap.put(element.getElementId() + "_title", tvTitle);

        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams edparams1 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        LinearLayout.LayoutParams edparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(70f), 2.0f);
        if ("TextareaElement".equals(elementType)) {
            editText.setLayoutParams(edparams2);
        } else {
            editText.setLayoutParams(edparams1);
        }
        if ("NumberElement".equals(elementType)) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        editText.setGravity(Gravity.CENTER);
        editText.setTextColor(Color.parseColor("#4a4a4a"));
        editText.setTextSize(14f);
        editText.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
        if (!StringUtils.isEmpty(element.getDefaultValue())) {
            editText.setText(element.getDefaultValue());
        }
        editText.setTag(element.getElementId());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setViews(editText.getTag().toString(), "");
            }
        });

        if ("true".equals(element.getDisable())) {
            editText.setEnabled(false);
        }

        elementIdtoFormName.put(element.getElementId(), element.getFormName());
        List<String> elementList = new ArrayList<>();
        elementList.add(element.getElementId());
        formNametoElementId.put(element.getFormName(), elementList);
        viewHashMap.put(element.getElementId(), editText);


        linearLayout.addView(tvTitle);
        linearLayout.addView(editText);

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(element.getParentId())) {
            List<String> childElementIdList = new ArrayList<>();
            childElementIdList.add(element.getElementId());
            if (pcViewHashMap.containsKey(element.getParentId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(element.getParentId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(element.getParentId(), childElementIdList);
        }

        return linearLayout;
    }

    private void setViews(String viewElementId, String isChecked) {
        //界面初始化
        if (StringUtils.isEmpty(viewElementId)) {
            for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);
                // check 控制 view
                if (elementSetsBean.getFormName().startsWith("RadioElement_")) {
                    CheckBox checkBox = (CheckBox) viewHashMap.get(elementSetsBean.getFormName() + "^" + elementSetsBean.getVal());
                    if (checkBox != null && checkBox.isChecked()) {
                        for (int i1 = 0; elementSetsBean.getChangeList() != null && i1 < elementSetsBean.getChangeList().size(); i1++) {
                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = elementSetsBean.getChangeList().get(i1);
                            LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");
                            if (linearLayout != null) {
                                if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                    EditText editText = (EditText) viewHashMap.get(changeListBean.getId());
                                    String type = changeListBean.getType();

                                    if (type.contains("Enable") || type.contains("DisEnable")) {
                                        if (editText != null) {
                                            if (type.contains("Enable")) {
                                                editText.setEnabled(true);
                                            } else {
                                                editText.setText("");
                                                editText.setEnabled(false);
                                            }
                                        }
                                    }

                                    if (type.contains("Show")) {
                                        linearLayout.setVisibility(View.VISIBLE);
                                    } else if (type.contains("Hide")) {
                                        if (editText != null) {
                                            editText.setText("");
                                            editText.setEnabled(false);
                                        }
                                        linearLayout.setVisibility(View.GONE);
                                    }
                                } else if (viewHashMap.get(changeListBean.getId()) instanceof TextView) {
                                    TextView textView = (TextView) viewHashMap.get(changeListBean.getId());
                                    String type = changeListBean.getType();

                                    if (type.contains("Enable") || type.contains("DisEnable")) {
                                        if (textView != null) {
                                            if (type.contains("Enable")) {
                                                textView.setClickable(true);
                                            } else {
                                                textView.setText("");
                                                textView.setClickable(false);
                                            }
                                        }
                                    }

                                    if (type.contains("Show")) {
                                        linearLayout.setVisibility(View.VISIBLE);
                                    } else if (type.contains("Hide")) {
                                        if (textView != null) {
                                            textView.setText("");
                                            textView.setClickable(false);
                                        }
                                        linearLayout.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                List<String> childElementIdList = pcViewHashMap.get(changeListBean.getId());
                                if (childElementIdList != null && childElementIdList.size() > 0) {
                                    for (int i2 = 0; i2 < childElementIdList.size(); i2++) {
                                        if (viewHashMap.get(childElementIdList.get(i2)) instanceof EditText) {
                                            EditText editTextChild = (EditText) viewHashMap.get(childElementIdList.get(i2));
                                            String type = changeListBean.getType();

                                            if (type.contains("Enable") || type.contains("DisEnable")) {
                                                if (editTextChild != null) {
                                                    if (type.contains("Enable")) {
                                                        editTextChild.setEnabled(true);
                                                    } else {
                                                        editTextChild.setText("");
                                                        editTextChild.setEnabled(false);
                                                    }
                                                }
                                            }
                                            LinearLayout linearLayoutChild = (LinearLayout) viewHashMap.get(childElementIdList.get(i2) + "_ll");
                                            if (type.contains("Show")) {
                                                if (linearLayoutChild != null) {
                                                    linearLayoutChild.setVisibility(View.VISIBLE);
                                                }
                                            } else if (type.contains("Hide")) {
                                                if (editTextChild != null) {
                                                    editTextChild.setText("");
                                                    editTextChild.setEnabled(false);
                                                }
                                                if (linearLayoutChild != null) {
                                                    linearLayoutChild.setVisibility(View.GONE);
                                                }
                                            }
                                        } else if (viewHashMap.get(childElementIdList.get(i2)) instanceof TextView) {
                                            TextView textViewChild = (TextView) viewHashMap.get(childElementIdList.get(i2));
                                            String type = changeListBean.getType();

                                            if (type.contains("Enable") || type.contains("DisEnable")) {
                                                if (textViewChild != null) {
                                                    if (type.contains("Enable")) {
                                                        textViewChild.setClickable(true);
                                                    } else {
                                                        textViewChild.setText("");
                                                        textViewChild.setClickable(false);
                                                    }
                                                }
                                            }

                                            LinearLayout linearLayoutChild = (LinearLayout) viewHashMap.get(childElementIdList.get(i2) + "_ll");
                                            if (type.contains("Show")) {
                                                if (linearLayoutChild != null) {
                                                    linearLayoutChild.setVisibility(View.VISIBLE);
                                                }
                                            } else if (type.contains("Hide")) {
                                                if (textViewChild != null) {
                                                    textViewChild.setText("");
                                                    textViewChild.setClickable(false);
                                                }
                                                if (linearLayoutChild != null) {
                                                    linearLayoutChild.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
            }
            //操作联动
        } else {
            String formName = elementIdtoFormName.get(viewElementId) == null ? null : elementIdtoFormName.get(viewElementId).split("\\^")[0];

            if ("true".equals(isChecked)) {

                //radio互斥
                if (formName != null && formName.startsWith("RadioElement_")) {

                    //例外
                    //跌倒评估
                    if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode) && formName.equals("RadioElement_69")) {

                    } else {
                        List<String> viewElementIdList = formNametoElementId.get(formName);
                        if (viewElementIdList != null && viewElementIdList.size() > 0) {

                            for (int i = 0; i < viewElementIdList.size(); i++) {
                                if (!viewElementId.equals(viewElementIdList.get(i))) {
                                    CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementIdList.get(i));
                                    if (checkBox != null && checkBox.isChecked()) {
                                        checkBox.setChecked(false);
                                    }
                                }
                            }
                        }
                    }

                }

                //check 控制 view 显隐 选中
                for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);

                    if (elementSetsBean.getFormName().equals(formName)) {
                        if (elementSetsBean.getFormName().startsWith("RadioElement_")) {
                            CheckBox checkBox = (CheckBox) viewHashMap.get(elementSetsBean.getFormName() + "^" + elementSetsBean.getVal());
                            if (checkBox != null && checkBox.isChecked() && elementSetsBean.getChangeList() != null && elementSetsBean.getChangeList().size() > 0) {
                                for (int i1 = 0; i1 < elementSetsBean.getChangeList().size(); i1++) {
                                    ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = elementSetsBean.getChangeList().get(i1);
                                    LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");
                                    if (linearLayout != null) {
                                        if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                            EditText editText = (EditText) viewHashMap.get(changeListBean.getId());
                                            String type = changeListBean.getType();

                                            if (type.contains("Enable") || type.contains("DisEnable")) {
                                                if (editText != null) {
                                                    if (type.contains("Enable")) {
                                                        editText.setEnabled(true);
                                                    } else {
                                                        editText.setText("");
                                                        editText.setEnabled(false);
                                                    }
                                                }
                                            }

                                            if (type.contains("Show")) {
                                                linearLayout.setVisibility(View.VISIBLE);
                                            } else if (type.contains("Hide")) {
                                                if (editText != null) {
                                                    editText.setText("");
                                                    editText.setEnabled(false);
                                                }
                                                linearLayout.setVisibility(View.GONE);
                                            }
                                        } else if (viewHashMap.get(changeListBean.getId()) instanceof TextView) {
                                            TextView textView = (TextView) viewHashMap.get(changeListBean.getId());
                                            String type = changeListBean.getType();

                                            if (type.contains("Enable") || type.contains("DisEnable")) {
                                                if (textView != null) {
                                                    if (type.contains("Enable")) {
                                                        textView.setClickable(true);
                                                    } else {
                                                        textView.setText("");
                                                        textView.setClickable(false);
                                                    }
                                                }
                                            }

                                            if (type.contains("Show")) {
                                                linearLayout.setVisibility(View.VISIBLE);
                                            } else if (type.contains("Hide")) {
                                                if (textView != null) {
                                                    textView.setText("");
                                                    textView.setClickable(false);
                                                }
                                                linearLayout.setVisibility(View.GONE);
                                            }
                                        }
                                    } else {
                                        List<String> childElementIdList = pcViewHashMap.get(changeListBean.getId());
                                        if (childElementIdList != null && childElementIdList.size() > 0) {
                                            for (int i2 = 0; i2 < childElementIdList.size(); i2++) {
                                                if (viewHashMap.get(childElementIdList.get(i2)) instanceof EditText) {
                                                    EditText editTextChild = (EditText) viewHashMap.get(childElementIdList.get(i2));
                                                    String type = changeListBean.getType();

                                                    if (type.contains("Enable") || type.contains("DisEnable")) {
                                                        if (editTextChild != null) {
                                                            if (type.contains("Enable")) {
                                                                editTextChild.setEnabled(true);
                                                            } else {
                                                                editTextChild.setText("");
                                                                editTextChild.setEnabled(false);
                                                            }
                                                        }
                                                    }
                                                    LinearLayout linearLayoutChild = (LinearLayout) viewHashMap.get(childElementIdList.get(i2) + "_ll");
                                                    if (type.contains("Show")) {
                                                        if (linearLayoutChild != null) {
                                                            linearLayoutChild.setVisibility(View.VISIBLE);
                                                        }
                                                    } else if (type.contains("Hide")) {
                                                        if (editTextChild != null) {
                                                            editTextChild.setText("");
                                                            editTextChild.setEnabled(false);
                                                        }
                                                        if (linearLayoutChild != null) {
                                                            linearLayoutChild.setVisibility(View.GONE);
                                                        }
                                                    }
                                                } else if (viewHashMap.get(childElementIdList.get(i2)) instanceof TextView) {
                                                    TextView textViewChild = (TextView) viewHashMap.get(childElementIdList.get(i2));
                                                    String type = changeListBean.getType();

                                                    if (type.contains("Enable") || type.contains("DisEnable")) {
                                                        if (textViewChild != null) {
                                                            if (type.contains("Enable")) {
                                                                textViewChild.setClickable(true);
                                                            } else {
                                                                textViewChild.setText("");
                                                                textViewChild.setClickable(false);
                                                            }
                                                        }
                                                    }

                                                    LinearLayout linearLayoutChild = (LinearLayout) viewHashMap.get(childElementIdList.get(i2) + "_ll");
                                                    if (type.contains("Show")) {
                                                        if (linearLayoutChild != null) {
                                                            linearLayoutChild.setVisibility(View.VISIBLE);
                                                        }
                                                    } else if (type.contains("Hide")) {
                                                        if (textViewChild != null) {
                                                            textViewChild.setText("");
                                                            textViewChild.setClickable(false);
                                                        }
                                                        if (linearLayoutChild != null) {
                                                            linearLayoutChild.setVisibility(View.GONE);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //分数 控制 选中
            for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);

                if (elementSetsBean.getFormName().equals(formName)) {
                    if (elementSetsBean.getFormName().startsWith("TextElement_") || elementSetsBean.getFormName().startsWith("NumberElement_")) {
                        EditText editText = (EditText) viewHashMap.get(viewElementId);
                        int edTextInt = 0;
                        try {
                            edTextInt = Integer.parseInt(StringUtils.isEmpty(editText.getText().toString()) ? "-1" : editText.getText().toString());

                            if (edTextInt > -1) {
                                if ("EqNumber".equals(elementSetsBean.getSign())) {
                                    int val = Integer.parseInt(elementSetsBean.getVal());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt == val && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }

                                        }
                                    }
                                } else if ("LeEqNumber".equals(elementSetsBean.getSign())) {
                                    int val = Integer.parseInt(elementSetsBean.getVal());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt <= val && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if ("LeNumber".equals(elementSetsBean.getSign())) {
                                    int val = Integer.parseInt(elementSetsBean.getVal());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt < val && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if ("GrEqNumber".equals(elementSetsBean.getSign())) {
                                    int val = Integer.parseInt(elementSetsBean.getVal());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt >= val && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if ("GrNumber".equals(elementSetsBean.getSign())) {
                                    int val = Integer.parseInt(elementSetsBean.getVal());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt > val && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if ("GrEqNumber1LeEqNumber2".equals(elementSetsBean.getSign())) {
                                    int val1 = Integer.parseInt(elementSetsBean.getVal());
                                    int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt >= val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }

                                        }
                                    }
                                } else if ("GrNumber1LeEqNumber2".equals(elementSetsBean.getSign())) {
                                    int val1 = Integer.parseInt(elementSetsBean.getVal());
                                    int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt > val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }

                                        }
                                    }
                                } else if ("GrEqNumber1LeNumber2".equals(elementSetsBean.getSign())) {
                                    int val1 = Integer.parseInt(elementSetsBean.getVal());
                                    int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt >= val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }

                                        }
                                    }
                                } else if ("GrNumber1LeNumber2".equals(elementSetsBean.getSign())) {
                                    int val1 = Integer.parseInt(elementSetsBean.getVal());
                                    int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                    if (edTextInt > val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
                                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = changeListBeans.get(i1);
                                            CheckBox checkBox = (CheckBox) viewHashMap.get("RadioElement_" + changeListBean.getId() + "^" + changeListBean.getItems());
                                            if (checkBox != null) {
                                                if (changeListBean.getType().contains("HasData")) {
                                                    checkBox.setChecked(true);
                                                }
                                            }
                                            if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                                                EditText editText1 = (EditText) viewHashMap.get(changeListBean.getId());
                                                if (editText1 != null && !StringUtils.isTrimEmpty(changeListBean.getVal())) {
                                                    if (changeListBean.getType().contains("HasData")) {
                                                        editText1.setText(changeListBean.getVal());
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            showToast("分数数值不规范");
                            e.printStackTrace();
                        }
                    }
                }
            }

            //计分
            for (int i = 0; statisticsListBeans != null && i < statisticsListBeans.size(); i++) {
                ElementDataBean.DataBean.InputBean.StatisticsListBean statisticsListBean = statisticsListBeans.get(i);
                String[] idStr = statisticsListBean.getEffects().split(",");
                for (int i1 = 0; idStr != null && i1 < idStr.length; i1++) {
                    if (idStr[i1].equals(viewElementId)) {
                        EditText editText = (EditText) viewHashMap.get(statisticsListBean.getId());
                        int score = Integer.parseInt(StringUtils.isEmpty(editText.getText().toString()) ? "0" : editText.getText().toString());

                        if (isChecked.equals("true") || isChecked.equals("false")) {
                            int changeScore = Integer.parseInt(elementIdtoFormName.get(viewElementId).split("\\^")[1]);
                            CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementId);
                            if (checkBox.isChecked()) {
                                score = score + changeScore;
                            } else {
                                score = score - changeScore;
                            }
                        } else if (isChecked.equals("drop")) {
                            Integer[] scoreInt = dropValue.get(viewElementId);
                            score = score - scoreInt[0] + scoreInt[1];
                        }

                        editText.setText(score + "");
                    }
                }
            }

        }

        //必填项填入内容之后还原view
        if ("true".equals(isChecked)) {
            String viewFormName = elementIdtoFormName.get(viewElementId) == null ? "" : elementIdtoFormName.get(viewElementId);
            if (viewFormName.contains("RadioElement") || viewFormName.contains("CheckElement")) {
                List<String> elementIdList = formNametoElementId.get(viewFormName.split("\\^")[0]);
                for (int i = 0; elementIdList != null && i < elementIdList.size(); i++) {
                    CheckBox checkBox = (CheckBox) viewHashMap.get(elementIdList.get(i));
                    if (checkBox != null) {
                        checkBox.setTextColor(Color.parseColor("#FF4A4A4A"));
                    }
                }

            }
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_new, container, false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            setViews(buttonView.getTag().toString(), "true");
        } else {
            setViews(buttonView.getTag().toString(), "false");
        }
    }
}
