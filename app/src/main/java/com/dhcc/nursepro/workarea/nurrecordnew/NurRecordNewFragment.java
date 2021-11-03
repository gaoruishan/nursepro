package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.constant.RegexConstants;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.module.nurse.ca.CaSignUtil;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.InputDigitLengthFilter;
import com.dhcc.nursepro.workarea.nurrecordnew.api.NurRecordNewApiManager;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.DataSourceBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ElementDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.MEViewLink;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.NurRecordKnowledgeContentBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecDataBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
public class NurRecordNewFragment extends NurRecordNewViewHelper implements CompoundButton.OnCheckedChangeListener {
    //view
    private final HashMap<String, View> viewHashMap = new HashMap<>();
    //inputview
    private final List<String> inputViewFormNameList = new ArrayList<>();
    //saveview
    private final List<String> saveViewFormNameList = new ArrayList<>();

    //id formname 关联
    private final HashMap<String, String> elementIdtoFormName = new HashMap<>();
    private final HashMap<String, List<String>> formNametoElementId = new HashMap<>();

    //item id 关联
    private final HashMap<String, String> itemtoElementId = new HashMap<>();

    //parent child view
    private final HashMap<String, List<String>> pcViewHashMap = new HashMap<>();
    private final HashMap<String, String> cpViewHashMap = new HashMap<>();

    //drop edittext
    private final HashMap<String, Integer[]> dropValue = new HashMap<>();
    //drop id val关联
    private final HashMap<String, List<String>> elementIdtoOprationItemList = new HashMap<>();
    //edit、text statistic计分
    private final List<Integer> etPointList = new ArrayList<>();

    //num 医学表达式关联
    private final List<MEViewLink> meViewLinkList = new ArrayList<>();

    //跳转回传取值关联
    private final HashMap<String, String> elementIdtoDataSourceRef = new HashMap<>();
    private final String emrCodeJump = "";
    private final String guidJump = "";
    private final String recIdJump = "";
    private boolean addLine;
    private LinearLayout llNurrecord;
    private String episodeID = "";
    private String bedNo = "";
    private String patName = "";
    private String emrCode = "";
    private String guid = "";
    private String recId = "";
    private List<String> selectStrList = new ArrayList<>();
    private List<Integer> selectScoreList = new ArrayList<>();
    private int mSingleChoiceID = -1;
    private int itemScore = 0;
    private List<String> dcSelectStrList = new ArrayList<>();
    private List<Integer> dcSelectScoreList = new ArrayList<>();
    private List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements;
    private List<ElementDataBean.DataBean.InputBean.ElementSetsBean> elementSetsBeans;
    private List<ElementDataBean.DataBean.InputBean.StatisticsListBean> statisticsListBeans;
    private List<ElementDataBean.FirstIdListBean> firstIdListBeans = new ArrayList<>();
    private List<ElementDataBean.StrictCodeListBean> strictCodeListBeans = new ArrayList<>();
    private List<ElementDataBean.FunListBean> funListBeans = new ArrayList<>();
    private SPUtils spUtils;
    private String userGroupId = "";
    private String callBackEffects = "";
    private String callBackReturnMapEffects = "";

    private NurRecordTipDialog tipDialog;
    private NurRecordQuoteDialog quoteDialog;
    private NurRecordSaveErrorDialog errorDialog;
    private final HashMap<String, String[]> editTextConvertMap = new HashMap<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && resultCode == 20002) {
            String callbackElementId = data.getStringExtra("CallBackEffects");
            String callbackElementFormName = data.getStringExtra("CallBackReturnMapEffects");
            if (!StringUtils.isEmpty(callbackElementId) && callbackElementId.split(",").length > 0) {
                String[] callbackElementIds = callbackElementId.split(",");
                for (int i = 0; i < callbackElementIds.length; i++) {
                    String elementId = callbackElementIds[i];
                    String dataSourceRef = elementIdtoDataSourceRef.get(elementId);
                    if (!StringUtils.isEmpty(dataSourceRef)) {
                        setCallBackViewText(elementId, dataSourceRef, episodeID);
                    } else {
                        if (!StringUtils.isEmpty(callbackElementFormName) && callbackElementFormName.split(",").length == callbackElementIds.length) {
                            String[] callbackElementFormNameStr = callbackElementFormName.split(",");

                            if (callbackElementFormNameStr[i].split("\\^").length == 3) {
                                TextView textView = (TextView) viewHashMap.get(elementId);
                                if (textView != null) {
                                    textView.setText(callbackElementFormNameStr[i].split("\\^")[2]);
                                }
                            }
                        }
                    }
                }

            }

        }

    }

    /**
     * 跳转其他单据保存后赋值
     *
     * @param elementId
     * @param dataSourceRef
     * @param episodeID
     */
    private void setCallBackViewText(String elementId, String dataSourceRef, String episodeID) {
        NurRecordNewApiManager.getDataSource(dataSourceRef, episodeID, new NurRecordNewApiManager.GetDataSourceCallback() {
            @Override
            public void onSuccess(DataSourceBean dataSourceBean) {
                String dataSource = dataSourceBean.getDataSource();
                TextView textView = (TextView) viewHashMap.get(elementId);
                if (dataSource != null && textView != null) {
                    textView.setText(dataSource);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                errorDialog = new NurRecordSaveErrorDialog(getActivity());
                errorDialog.setExecresult(msg);
                errorDialog.setImgId(R.drawable.icon_popup_error_patient);
                errorDialog.setSureVisible(View.VISIBLE);
                errorDialog.setCancleVisible(View.GONE);
                errorDialog.setSureOnclickListener(() -> errorDialog.dismiss());
                errorDialog.show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spUtils = SPUtils.getInstance();
        userGroupId = spUtils.getString(SharedPreference.GROUPID, "");

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            episodeID = bundle.getString("EpisodeID", "");
            bedNo = bundle.getString("BedNo", "");
            patName = bundle.getString("PatName", "");
            emrCode = bundle.getString("EMRCode", "");
            guid = bundle.getString("GUID", "");
            recId = bundle.getString("RecID", "");
            callBackEffects = bundle.getString("CallBackEffects", "");
            callBackReturnMapEffects = bundle.getString("CallBackReturnMapEffects", "");
        }

        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(bedNo + "    " + patName, 0xffffffff, 17);

        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(v -> save(new ArrayList<>()));
        //        setToolbarRightCustomView(viewright);

        setToolbarRightCustomView(viewright);

        initview(view);

        initData();

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (intent.getAction().equals(Action.NURRECORD_KONWLEDGETREEID)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            getKnowledgeContent(bundle.getString("knowledgeTreeId"));
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_new, container, false);
    }

    private void getKnowledgeContent(String knowledgeTreeId) {
        NurRecordNewApiManager.getKnowledgeContent(knowledgeTreeId, new NurRecordNewApiManager.GetKnowledgeContentCallback() {
            @Override
            public void onSuccess(NurRecordKnowledgeContentBean knowledgeContentBean) {
                StringBuilder stringBuilder = new StringBuilder();
                List<List<NurRecordKnowledgeContentBean.KnowledgeContentBean>> contentBean = knowledgeContentBean.getKnowledgeContent();
                for (int i = 0; i < contentBean.size(); i++) {
                    if (contentBean.get(i) != null) {
                        if (contentBean.get(i).size() > 0) {
                            if ("FreeText".equals(contentBean.get(i).get(0).getType())) {
                                stringBuilder.append(contentBean.get(i).get(0).getTitle());
                            }
                        }
                    }
                }
                quoteDialog.setKnowledgeContent(stringBuilder.toString());
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }
        });

    }

    /**
     * 保存护理病历
     *
     * @param saveViewFormNameList
     */
    private void save(List<String> saveViewFormNameList) {
        String auditUserCode = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; elements != null && i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);

            String saveFormName = "";
            saveFormName = element.getFormName();
            if (StringUtils.isEmpty(saveFormName) && element.getRadioElementList() != null && element.getRadioElementList().size() > 0) {
                saveFormName = element.getRadioElementList().get(0).getFormName();
            }

            if (saveViewFormNameList.size() > 0 && !saveViewFormNameList.contains(saveFormName)) {
                continue;
            }

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

                        if (!(radioElementListBeanList.size() == 1 && "其他".equals(radioElementListBeanList.get(0).getOprationItemList().get(0).getText()))) {

                            List<String> elementIdList = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
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

            } else if ("DropListElement".equals(element.getElementType())) {
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

                        for (String s : dcTextItem) {
                            String NumberValueStr;
                            String TextStr;
                            String ValueStr;
                            if (s.equals(oprationItemListBean.getText())) {
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

                EditText editText = (EditText) viewHashMap.get(element.getElementId());
                if (editText != null) {
                    //                        LinearLayout linearLayout = (LinearLayout) viewHashMap.get(element.getElementId() + "_ll");
                    //                        if ("true".equals(element.getRequired()) && StringUtils.isTrimEmpty(editText.getText().toString()) && linearLayout.getVisibility() == View.VISIBLE) {
                    //                            editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                    //                            showToast("请填写必填项之后再保存");
                    //                            return;
                    //                        }

                    if ("NumberElement".equals(element.getElementType())) {
                        try {
                            double edDou = Double.parseDouble(editText.getText().toString());
                            boolean minError = !StringUtils.isEmpty(element.getMinError()) && edDou < Double.parseDouble(element.getMinError());
                            boolean maxError = !StringUtils.isEmpty(element.getMaxError()) && edDou > Double.parseDouble(element.getMaxError());
                            if (minError || maxError) {
                                showToast("请检查输入数值");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    if ("TextElement".equals(element.getElementType()) && "true".equals(element.getSignatureAuto())) {
                        auditUserCode = ((EditText) viewHashMap.get(element.getElementId())).getText().toString();
                        String userStr = "CA" + ((EditText) viewHashMap.get(element.getElementId())).getText().toString() + "*" + spUtils.getString(SharedPreference.USERCODE);
                        stringBuilder.append("\"")
                                .append(element.getElementType())
                                .append("_")
                                .append(element.getElementId())
                                .append("\":\"")
                                .append(userStr)
                                .append("\"");
                    } else if ("TextElement".equals(element.getElementType()) && "Common".equals(element.getSignature())) {
                        String text = onEditTextSave(editText);
                        stringBuilder.append("\"")
                                .append(element.getElementType())
                                .append("_")
                                .append(element.getElementId())
                                .append("\":\"")
                                .append(text)
                                .append("\"");
                    } else {

                        stringBuilder.append("\"")
                                .append(element.getElementType())
                                .append("_")
                                .append(element.getElementId())
                                .append("\":\"")
                                .append(editText.getText().toString())
                                .append("\"");

                    }
                }

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

        StringBuilder stringBuilder1 = new StringBuilder();
        //"^{""LOGON.CTLOCID"":""688"",""LOGON.WARDID"":""70"",""LOGON.GROUPDESC"":""住院护士"",""LOGON.USERID"":""2961"",""SubjectionTemplateGuid"":""58595b9a61064ba18cc82bd82db00540""}")
        stringBuilder1.append("^{")
                .append("\"LOGON.CTLOCID\":\"").append(spUtils.getString(SharedPreference.LOCID)).append("\"")
                .append(",\"LOGON.WARDID\":\"").append(spUtils.getString(SharedPreference.WARDID)).append("\"")
                .append(",\"LOGON.GROUPDESC\":\"").append(spUtils.getString(SharedPreference.GROUPDESC)).append("\"")
                .append(",\"LOGON.USERID\":\"").append(spUtils.getString(SharedPreference.USERID)).append("\"")
                .append(",\"SubjectionTemplateGuid\":\"").append(guid).append("\"}");

        String printTemplateEmrCode = stringBuilder1.toString();

        showLoadingTip(BaseActivity.LoadingType.FULL);
        String finalAuditUserCode = auditUserCode;
        NurRecordNewApiManager.saveNewEmrData(guid, episodeID, recId, parr, printTemplateEmrCode, new NurRecordNewApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                hideLoadingTip();
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                if (!StringUtils.isEmpty(callBackEffects)) {
                    Intent intent = new Intent();

                    String[] effects = callBackEffects.split(",");
                    String[] returnMap = callBackReturnMapEffects.split(",");

                    //returnMap赋值
                    if (returnMap.length > 0 && returnMap.length == effects.length) {
                        for (int i = 0; i < returnMap.length; i++) {
                            String[] returnMapItem = returnMap[i].split("\\^");
                            if (returnMapItem.length == 2) {
                                String[] returnMapItemStr = returnMapItem[0].split("_");
                                if (returnMapItemStr.length == 2 && returnMapItemStr[1].equals(effects[i])) {
                                    List<String> returnElementId = formNametoElementId.get(returnMapItem[1]);
                                    if (returnElementId != null && returnElementId.size() == 1) {
                                        TextView textView = (TextView) viewHashMap.get(returnElementId.get(0));
                                        if (textView != null) {
                                            String value = textView.getText().toString();
                                            callBackReturnMapEffects = callBackReturnMapEffects.replace(returnMap[i], returnMap[i] + "^" + value);
                                        }
                                    }
                                }
                            }
                        }
                    }


                    intent.putExtra("CallBackEffects", callBackEffects);
                    intent.putExtra("CallBackReturnMapEffects", callBackReturnMapEffects);
                    Objects.requireNonNull(getActivity()).setResult(20002, intent);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Objects.requireNonNull(getActivity()).finish();
                    }
                }, 1000);
                //Ca签名
                if ("1".equals(recDataBean.getCaFlag())) {
                    CaSignUtil.recordCaSign(getActivity(), episodeID,patName,emrCode,recDataBean.getRetData(), finalAuditUserCode);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                if (errorDialog != null && errorDialog.isShowing()) {
                    errorDialog.dismiss();
                }
                errorDialog = new NurRecordSaveErrorDialog(getActivity());
                errorDialog.setExecresult(msg);
                errorDialog.setImgId(R.drawable.icon_popup_error_patient);
                errorDialog.setSureVisible(View.VISIBLE);
                errorDialog.setCancleVisible(View.GONE);
                errorDialog.setSureOnclickListener(() -> errorDialog.dismiss());
                errorDialog.show();
            }
        });

    }

    private void initview(View view) {
        llNurrecord = view.findViewById(R.id.ll_nurrecord);
        llNurrecord.clearAnimation();
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        NurRecordNewApiManager.GetXmlValues(episodeID, emrCode, recId, new NurRecordNewApiManager.GetXmlValuesCallback() {
            @Override
            public void onSuccess(ElementDataBean elementDataBean) {
                hideLoadingTip();
                elements = elementDataBean.getData().getInput().getElementBases();
                elementSetsBeans = elementDataBean.getData().getInput().getElementSets();
                statisticsListBeans = elementDataBean.getData().getInput().getStatisticsList();
                firstIdListBeans = elementDataBean.getFirstIdList();

                strictCodeListBeans = elementDataBean.getStrictCodeList();
                funListBeans = elementDataBean.getFunList();

                InputViews(elements);
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }
        });
    }

    /**
     * 添加各类型元素
     *
     * @param elements
     */
    private void InputViews(List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements) {
        for (int i = 0; elements != null && i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);
            if ("ContainerElement".equals(element.getElementType())) {
                LinearLayout llContainer = getContainerView(element);
                llNurrecord.addView(llContainer);
            } else if ("LableElement".equals(element.getElementType())) {
                LinearLayout llLable = getLableView(element);
                addView(element, llLable);
            } else if ("DateElement".equals(element.getElementType())) {
                LinearLayout llDate = getTextView(element);
                addView(element, llDate);
            } else if ("TimeElement".equals(element.getElementType())) {
                LinearLayout llTime = getTextView(element);
                addView(element, llTime);
            } else if ("RadioElement".equals(element.getElementType())) {
                LinearLayout llRadio = getRadioView(element);
                addView(element, llRadio);
            } else if ("CheckElement".equals(element.getElementType())) {
                LinearLayout llCheck = getCheckView(element);
                addView(element, llCheck);
            } else if ("DropRadioElement".equals(element.getElementType())) {

                LinearLayout lldropRadio = getTextView(element);
                TextView tvdropRadio = (TextView) viewHashMap.get(element.getElementId());
                tvdropRadio.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                List<String> dropItremTextandNumberValue = new ArrayList<>();
                for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                    dropItremTextandNumberValue.add(element.getOprationItemList().get(i1).getText() + "^" + element.getOprationItemList().get(i1).getNumberValue());
                }
                elementIdtoOprationItemList.put(element.getElementId(), dropItremTextandNumberValue);

                tvdropRadio.setOnClickListener(v -> {
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
                });

                lldropRadio.clearAnimation();

                addView(element, lldropRadio);
            } else if ("DropListElement".equals(element.getElementType())) {

                LinearLayout lldropRadio = getTextView(element);
                TextView tvdropRadio = (TextView) viewHashMap.get(element.getElementId());
                tvdropRadio.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                List<String> dropItremTextandNumberValue = new ArrayList<>();
                for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                    dropItremTextandNumberValue.add(element.getOprationItemList().get(i1).getText() + "^" + element.getOprationItemList().get(i1).getNumberValue());
                }
                elementIdtoOprationItemList.put(element.getElementId(), dropItremTextandNumberValue);

                tvdropRadio.setOnClickListener(v -> {
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

                    //添加一个选项 用来清空数据，可根据医院需求去掉
                    selectStrList.add("");
                    selectScoreList.add(0);

                    ShowRadioScore(selectStrList, getActivity(), tvdropRadio);
                });

                lldropRadio.clearAnimation();

                addView(element, lldropRadio);
            } else if ("DropCheckboxElement".equals(element.getElementType())) {
                LinearLayout lldropCheck = getTextView(element);
                TextView tvdropCheck = (TextView) viewHashMap.get(element.getElementId());
                tvdropCheck.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                tvdropCheck.setOnClickListener(v -> {
                    tvdropCheck.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    dcSelectStrList = new ArrayList<>();
                    dcSelectScoreList = new ArrayList<>();
                    for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                        dcSelectStrList.add(element.getOprationItemList().get(i1).getText());
                        dcSelectScoreList.add(Integer.parseInt(element.getOprationItemList().get(i1).getNumberValue()));
                    }
                    dcSelectStrList.add("全选/取消全选");

                    ShowDropCheck(dcSelectStrList, getActivity(), tvdropCheck);
                });

                lldropCheck.clearAnimation();
                addView(element, lldropCheck);
            } else if ("NumberElement".equals(element.getElementType())) {
                LinearLayout llNumber = getEditText(element, "NumberElement");
                EditText edNumber = (EditText) viewHashMap.get(element.getElementId());
                edNumber.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                llNumber.clearAnimation();
                addView(element, llNumber);
            } else if ("TextElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element, "TextElement");
                TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                if ("true".equals(element.getSignatureAuto())) {

                    boolean mode = false;
                    boolean sign = false;
                    if (strictCodeListBeans != null && strictCodeListBeans.size() > 0) {
                        mode = true;
                        ElementDataBean.StrictCodeListBean.RoseListBean roseListBean = new ElementDataBean.StrictCodeListBean.RoseListBean();
                        ElementDataBean.StrictCodeListBean.InputsListBean inputsListBean = new ElementDataBean.StrictCodeListBean.InputsListBean();
                        roseListBean.setCode(userGroupId);
                        inputsListBean.setCode(element.getFormName());

                        for (int i1 = 0; i1 < strictCodeListBeans.size(); i1++) {
                            List<ElementDataBean.StrictCodeListBean.RoseListBean> roseListBeans = strictCodeListBeans.get(i1).getRoseList();
                            List<ElementDataBean.StrictCodeListBean.InputsListBean> inputsListBeans = strictCodeListBeans.get(i1).getInputsList();

                            if (roseListBeans.contains(roseListBean) && inputsListBeans.contains(inputsListBean)) {
                                sign = true;
                            }
                        }
                    }

                    if (!mode || (mode && sign)) {
                        if ("CommonNOReplace".equals(element.getSignature())) {
                            if ("".equals(recId)) {
                                edText.setText(spUtils.getString(SharedPreference.USERNAME));
                            }
                        } else if ("Common".equals(element.getSignature())) {
                            edText.setText(spUtils.getString(SharedPreference.USERNAME));
                        }
                    }
                } else if ("Common".equals(element.getSignature())) {
                    onEditTextAdd(edText,edText.getText().toString(), "user");
                    onEditTextEnter(edText, "user");
                }

                lledit.clearAnimation();
                addView(element, lledit);
            } else if ("TextareaElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element, "TextareaElement");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));
                lledit.clearAnimation();
                addView(element, lledit);
            } else if ("ButtonElement".equals(element.getElementType())) {
//                if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
                LinearLayout llButton = getButtonView(element);
                llButton.clearAnimation();
                addView(element, llButton);
//                }
            }

        }

        initViewSet("", "");

        initViewMode();

        initMELinkViewList();
    }

    private void initMELinkViewList() {
        for (int i = 0; i < meViewLinkList.size(); i++) {
            for (int j = 0; j < funListBeans.size(); j++) {
                if (meViewLinkList.get(i).getLinkName().equals(funListBeans.get(j).getName())) {
                    meViewLinkList.get(i).setLinkContent(funListBeans.get(j).getContent());
                }
            }
        }
    }

    /**
     * @param element
     * @return
     */
    private LinearLayout getLableView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llparams.setMargins(ConvertUtils.dp2px(12), ConvertUtils.dp2px(6), ConvertUtils.dp2px(12), ConvertUtils.dp2px(6));
        linearLayout.setLayoutParams(llparams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView tvLable = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLable.setLayoutParams(tvparams1);
        tvLable.setTextColor(Color.parseColor("#4a4a4a"));
        tvLable.setGravity(Gravity.START);
        tvLable.setText(element.getNameText());
        linearLayout.addView(tvLable);

        linearLayout.clearAnimation();
//        if ("true".equals(element.getIsHide())) {
//            linearLayout.setVisibility(View.VISIBLE);
//            addLine = true;
//        } else {
//            linearLayout.setVisibility(View.GONE);
//            addLine = false;
//        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        return linearLayout;
    }

    /**
     * 严格模式配置
     */
    private void initViewMode() {
        if (strictCodeListBeans != null && strictCodeListBeans.size() > 0) {
            for (int i = 0; i < strictCodeListBeans.size(); i++) {
                List<ElementDataBean.StrictCodeListBean.RoseListBean> roseListBeans = strictCodeListBeans.get(i).getRoseList();
                ElementDataBean.StrictCodeListBean.RoseListBean roseListBean = new ElementDataBean.StrictCodeListBean.RoseListBean();
                roseListBean.setCode(userGroupId);
                if (roseListBeans != null && roseListBeans.contains(roseListBean)) {

                    List<ElementDataBean.StrictCodeListBean.InputsListBean> inputsListBeans = strictCodeListBeans.get(i).getInputsList();
                    if (inputsListBeans != null && inputsListBeans.size() > 0) {
                        for (int j = 0; j < inputsListBeans.size(); j++) {
                            String inputViewFormName = inputsListBeans.get(j).getCode();
                            if (inputViewFormNameList.contains(inputViewFormName)) {
                                List<String> viewElementIds = formNametoElementId.get(inputViewFormName);
                                for (int k = 0; k < viewElementIds.size(); k++) {
                                    String viewElementId = viewElementIds.get(k);
                                    if (viewHashMap.get(viewElementId) instanceof CheckBox) {
                                        CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementIds.get(k));
                                        checkBox.setEnabled(true);
                                        checkBox.setClickable(true);
                                    } else if (viewHashMap.get(viewElementId) instanceof EditText) {
                                        EditText editText = (EditText) viewHashMap.get(viewElementIds.get(k));
                                        editText.setEnabled(true);
                                        editText.setFocusable(true);
                                        editText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));
                                    } else if (viewHashMap.get(viewElementId) instanceof TextView) {
                                        TextView textView = (TextView) viewHashMap.get(viewElementIds.get(k));
                                        textView.setEnabled(true);
                                        textView.setClickable(true);
                                        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
                                    }

                                    if (cpViewHashMap.containsKey(viewElementId)) {
                                        String containerId = cpViewHashMap.get(viewElementId);
                                        if (viewHashMap.get(containerId + "_containerll") instanceof LinearLayout) {
                                            LinearLayout llContainer = (LinearLayout) viewHashMap.get(containerId + "_containerll");
                                            if (i % 2 == 0) {
                                                llContainer.setBackgroundColor(Color.parseColor("#FDF5E6"));
                                            } else {
                                                llContainer.setBackgroundColor(Color.parseColor("#BBFFFF"));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                } else {
                    List<ElementDataBean.StrictCodeListBean.InputsListBean> inputsListBeans = strictCodeListBeans.get(i).getInputsList();
                    if (inputsListBeans != null && inputsListBeans.size() > 0) {
                        for (int j = 0; j < inputsListBeans.size(); j++) {
                            String inputViewFormName = inputsListBeans.get(j).getCode();
                            if (inputViewFormNameList.contains(inputViewFormName)) {
                                List<String> viewElementIds = formNametoElementId.get(inputViewFormName);
                                for (int k = 0; k < viewElementIds.size(); k++) {
                                    String viewElementId = viewElementIds.get(k);
                                    if (viewHashMap.get(viewElementId) instanceof CheckBox) {
                                        CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementIds.get(k));
                                        checkBox.setClickable(false);
                                        checkBox.setEnabled(false);
                                    } else if (viewHashMap.get(viewElementId) instanceof EditText) {
                                        EditText editText = (EditText) viewHashMap.get(viewElementIds.get(k));
                                        editText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg_uneditable));
                                        editText.setFocusable(false);
                                        editText.setEnabled(false);
                                    } else if (viewHashMap.get(viewElementId) instanceof TextView) {
                                        TextView textView = (TextView) viewHashMap.get(viewElementIds.get(k));
                                        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg_unclickable));
                                        textView.setClickable(false);
                                        textView.setEnabled(false);
                                    }

                                    if (cpViewHashMap.containsKey(viewElementId)) {
                                        String parentId = cpViewHashMap.get(viewElementId);
                                        if (viewHashMap.get(parentId + "_containerll") instanceof LinearLayout) {
                                            LinearLayout llContainer = (LinearLayout) viewHashMap.get(parentId + "_containerll");
                                            if (i % 2 == 0) {
                                                llContainer.setBackgroundColor(Color.parseColor("#FDF5E6"));
                                            } else {
                                                llContainer.setBackgroundColor(Color.parseColor("#BBFFFF"));
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

    /**
     * 容器
     *
     * @param element
     * @return
     */
    private LinearLayout getContainerView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(llparams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_containerll", linearLayout);

        return linearLayout;
    }

    /**
     * 文本框
     *
     * @param element
     * @return
     */
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
            tvTitle.setOnClickListener(v -> {
                tipDialog = new NurRecordTipDialog(getActivity());
                tipDialog.setNurRecordTip(element.getToolTipText());
                tipDialog.setSureOnclickListener(() -> tipDialog.dismiss());
                tipDialog.show();
            });
        }

        if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(v -> {
                String emrCode = element.getBindingTemplateID();
                String guid = "";
                String recId = "";
                for (int i = 0; i < firstIdListBeans.size(); i++) {
                    if (firstIdListBeans.get(i).getEmrCode().equals(emrCode)) {
                        guid = firstIdListBeans.get(i).getGuId();
                        recId = firstIdListBeans.get(i).getRecId();
                        break;
                    }
                }

                if (!StringUtils.isEmpty(guid)) {
                    callBackEffects = element.getCallBackEffects();
                    callBackReturnMapEffects = element.getCallBackReturnMapEffects();
                    Bundle bundle = new Bundle();
                    bundle.putString("EpisodeID", episodeID);
                    bundle.putString("BedNo", bedNo);
                    bundle.putString("PatName", patName);
                    bundle.putString("EMRCode", emrCode);
                    bundle.putString("GUID", guid);
                    bundle.putString("RecID", recId);
                    bundle.putString("CallBackEffects", callBackEffects);
                    bundle.putString("CallBackReturnMapEffects", callBackReturnMapEffects);
                    startFragment(NurRecordNewFragment.class, bundle, 10001);
                }
            });

        }

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        textView.setLayoutParams(tvparams2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#4a4a4a"));
        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

        if ("DateElement".equals(element.getElementType())) {
            textView.setOnClickListener(v -> ShowDateTime(DATE_DIALOG, getActivity(), textView));
        } else if ("TimeElement".equals(element.getElementType())) {
            textView.setOnClickListener(v -> ShowDateTime(TIME_DIALOG, getActivity(), textView));
        }
        if (!StringUtils.isEmpty(element.getDefaultValue())) {
            textView.setText(element.getDefaultValue());
            if ("DropRadioElement".equals(element.getElementType()) || "DropListElement".equals(element.getElementType())) {
                int defaultScore = 0;
                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
                    if (element.getDefaultValue().equals(oprationItemListBean.getText())) {
                        defaultScore = Integer.parseInt(oprationItemListBean.getNumberValue());
                        break;
                    }
                }
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            } else if ("DropCheckboxElement".equals(element.getElementType())) {
                int defaultScore = 0;
                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
                    String[] valueStr = element.getDefaultValue().split(",");
                    for (String s : valueStr) {
                        if (s.equals(oprationItemListBean.getText())) {
                            defaultScore = defaultScore + Integer.parseInt(oprationItemListBean.getNumberValue());
                            break;
                        }
                    }
                }
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            }
        } else {
            if ("DropRadioElement".equals(element.getElementType()) || "DropListElement".equals(element.getElementType())) {

                int defaultScore = 0;
                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);
                    if ("true".equals(oprationItemListBean.getIsSelect())) {
                        textView.setText(oprationItemListBean.getText());
                        defaultScore = Integer.parseInt(oprationItemListBean.getNumberValue());
                        break;
                    }
                }
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            } else if ("DropCheckboxElement".equals(element.getElementType())) {
                int defaultScore = 0;
                StringBuilder textStr = new StringBuilder();

                for (int i = 0; element.getOprationItemList() != null && i < element.getOprationItemList().size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementBasesBean.OprationItemListBean oprationItemListBean = element.getOprationItemList().get(i);

                    if ("true".equals(oprationItemListBean.getIsSelect())) {
                        if (textStr.length() > 0) {
                            textStr.append(",");
                        }
                        textStr.append(oprationItemListBean.getText());
                        defaultScore = defaultScore + Integer.parseInt(oprationItemListBean.getNumberValue());
                    }
                }
                textView.setText(textStr.toString());
                dropValue.put(element.getElementId(), new Integer[]{0, defaultScore});
            }
        }

        if ("true".equals(element.getDisable())) {
            textView.setClickable(false);
            textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg_unclickable));
        }

        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);
        elementIdtoFormName.put(element.getElementId(), element.getFormName());
        elementIdtoDataSourceRef.put(element.getElementId(), element.getDataSourceRef());

        inputViewFormNameList.add(element.getFormName());
        List<String> viewElementIdList = new ArrayList<>();
        viewElementIdList.add(element.getElementId());
        formNametoElementId.put(element.getFormName(), viewElementIdList);

        linearLayout.addView(tvTitle);
        linearLayout.addView(textView);

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(element.getContainerId())) {
            List<String> childElementIdList = new ArrayList<>();
            childElementIdList.add(element.getElementId());
            if (pcViewHashMap.containsKey(element.getContainerId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(element.getContainerId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(element.getContainerId(), childElementIdList);
            cpViewHashMap.put(element.getElementId(), element.getContainerId());
        }


        return linearLayout;
    }

    /**
     * 布局中添加view（及分割线）
     *
     * @param element
     * @param linearlayout
     */
    private void addView(ElementDataBean.DataBean.InputBean.ElementBasesBean element, LinearLayout linearlayout) {
        String containerId = "";
        if (StringUtils.isEmpty(element.getContainerId())) {
            if (element.getRadioElementList() != null && element.getRadioElementList().size() > 0) {
                containerId = element.getRadioElementList().get(0).getContainerId();
            }
        } else {
            containerId = element.getContainerId();
        }


        if (StringUtils.isEmpty(containerId)) {
            llNurrecord.addView(linearlayout);
            if (linearlayout.getVisibility() == View.VISIBLE) {
                llNurrecord.addView(getDashLine());
            }
        } else {
            LinearLayout llContainer = (LinearLayout) viewHashMap.get(containerId + "_containerll");

            if (llContainer != null) {
                llContainer.addView(linearlayout);
                if (linearlayout.getVisibility() == View.VISIBLE) {
                    llContainer.addView(getDashLine());
                }
            }
        }
    }

    /**
     * 平铺单选
     *
     * @param element
     * @return
     */
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
            tvTitle.setOnClickListener(v -> {
                tipDialog = new NurRecordTipDialog(getActivity());
                tipDialog.setNurRecordTip(element.getToolTipText());
                tipDialog.setSureOnclickListener(() -> tipDialog.dismiss());
                tipDialog.show();
            });
        }

        ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElement = element.getRadioElementList().get(0);

        if (radioElement != null && !StringUtils.isEmpty(radioElement.getBindingTemplateID()) && !"true".equals(radioElement.getIsHide())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(v -> {
                String emrCode = radioElement.getBindingTemplateID();
                String guid = "";
                String recId = "";
                for (int i = 0; i < firstIdListBeans.size(); i++) {
                    if (firstIdListBeans.get(i).getEmrCode().equals(emrCode)) {
                        guid = firstIdListBeans.get(i).getGuId();
                        recId = firstIdListBeans.get(i).getRecId();
                        break;
                    }
                }

                if (!StringUtils.isEmpty(guid)) {
                    callBackEffects = radioElement.getCallBackEffects();
                    callBackReturnMapEffects = radioElement.getCallBackReturnMapEffects();
                    Bundle bundle = new Bundle();
                    bundle.putString("EpisodeID", episodeID);
                    bundle.putString("BedNo", bedNo);
                    bundle.putString("PatName", patName);
                    bundle.putString("EMRCode", emrCode);
                    bundle.putString("GUID", guid);
                    bundle.putString("RecID", recId);
                    bundle.putString("CallBackEffects", callBackEffects);
                    bundle.putString("CallBackReturnMapEffects", callBackReturnMapEffects);
                    startFragment(NurRecordNewFragment.class, bundle, 10001);
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
                checkBox.setChecked("1".equals(radioElementListBean.getIsSelect()));
            }

            checkBox.setOnCheckedChangeListener(this);

            if ("true".equals(radioElementListBean.getDisable())) {
                checkBox.setClickable(false);
            }

            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue(), checkBox);
            elementIdtoFormName.put(radioElementListBean.getElementId(), radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getNumberValue());
            viewElementIdList.add(radioElementListBean.getElementId());
            childElementIdList.add(radioElementListBean.getElementId());

            llRadio.addView(checkBox);
        }
        if (formNametoElementId.containsKey(radioElementListBeanList.get(0).getFormName())) {
            List<String> viewElementIdListExist = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
            viewElementIdList.addAll(viewElementIdListExist);
        }
        formNametoElementId.put(radioElementListBeanList.get(0).getFormName(), viewElementIdList);
        inputViewFormNameList.add(radioElementListBeanList.get(0).getFormName());

        linearLayout.addView(tvTitle);
        linearLayout.addView(llRadio);

        if ("true".equals(radioElementListBeanList.get(0).getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(radioElementListBeanList.get(0).getElementId() + "_ll", linearLayout);
        if (!StringUtils.isEmpty(radioElementListBeanList.get(0).getContainerId())) {
            if (pcViewHashMap.containsKey(radioElementListBeanList.get(0).getContainerId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(radioElementListBeanList.get(0).getContainerId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(radioElementListBeanList.get(0).getContainerId(), childElementIdList);
            cpViewHashMap.put(radioElementListBeanList.get(0).getElementId(), radioElementListBeanList.get(0).getContainerId());
        }

        return linearLayout;
    }

    /**
     * 平铺多选
     *
     * @param element
     * @return
     */
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
            tvTitle.setOnClickListener(v -> {
                tipDialog = new NurRecordTipDialog(getActivity());
                tipDialog.setNurRecordTip(element.getToolTipText());
                tipDialog.setSureOnclickListener(() -> tipDialog.dismiss());
                tipDialog.show();
            });
        }

        ElementDataBean.DataBean.InputBean.ElementBasesBean.RadioElementListBean radioElement = element.getRadioElementList().get(0);

        if (radioElement != null && !StringUtils.isEmpty(radioElement.getBindingTemplateID()) && !"true".equals(radioElement.getIsHide())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(v -> {
                String emrCode = radioElement.getBindingTemplateID();
                String guid = "";
                String recId = "";
                for (int i = 0; i < firstIdListBeans.size(); i++) {
                    if (firstIdListBeans.get(i).getEmrCode().equals(emrCode)) {
                        guid = firstIdListBeans.get(i).getGuId();
                        recId = firstIdListBeans.get(i).getRecId();
                        break;
                    }
                }

                if (!StringUtils.isEmpty(guid)) {
                    callBackEffects = radioElement.getCallBackEffects();
                    callBackReturnMapEffects = radioElement.getCallBackReturnMapEffects();
                    Bundle bundle = new Bundle();
                    bundle.putString("EpisodeID", episodeID);
                    bundle.putString("BedNo", bedNo);
                    bundle.putString("PatName", patName);
                    bundle.putString("EMRCode", emrCode);
                    bundle.putString("GUID", guid);
                    bundle.putString("RecID", recId);
                    bundle.putString("CallBackEffects", callBackEffects);
                    bundle.putString("CallBackReturnMapEffects", callBackReturnMapEffects);
                    startFragment(NurRecordNewFragment.class, bundle, 10001);
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
                checkBox.setChecked("1".equals(radioElementListBean.getIsSelect()));
            }

            checkBox.setOnCheckedChangeListener(this);

            if ("true".equals(radioElementListBean.getDisable())) {
                llCheck.clearAnimation();
                checkBox.setClickable(false);
            }

            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getValue(), checkBox);
            elementIdtoFormName.put(radioElementListBean.getElementId(), radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getNumberValue());
            viewElementIdList.add(radioElementListBean.getElementId());
            childElementIdList.add(radioElementListBean.getElementId());

            llCheck.addView(checkBox);
        }

        if (formNametoElementId.containsKey(radioElementListBeanList.get(0).getFormName())) {
            List<String> viewElementIdListExist = formNametoElementId.get(radioElementListBeanList.get(0).getFormName());
            viewElementIdList.addAll(viewElementIdListExist);
        }
        formNametoElementId.put(radioElementListBeanList.get(0).getFormName(), viewElementIdList);

        inputViewFormNameList.add(radioElementListBeanList.get(0).getFormName());
        linearLayout.addView(tvTitle);
        linearLayout.addView(llCheck);

        if ("true".equals(radioElementListBeanList.get(0).getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(radioElementListBeanList.get(0).getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(radioElementListBeanList.get(0).getContainerId())) {
            if (pcViewHashMap.containsKey(radioElementListBeanList.get(0).getContainerId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(radioElementListBeanList.get(0).getContainerId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(radioElementListBeanList.get(0).getContainerId(), childElementIdList);
            cpViewHashMap.put(radioElementListBeanList.get(0).getElementId(), radioElementListBeanList.get(0).getContainerId());
        }

        return linearLayout;
    }

    /**
     * 下拉单选弹窗
     *
     * @param selectStrList
     * @param context
     * @param textView
     */
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
                (dialog, whichButton) -> {
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
                });
        localBuilder.setPositiveButton("确定",
                (dialog, which) -> {
                    textView.setText(mItems[mSingleChoiceID]);
                    dropValue.put(textView.getTag().toString(), new Integer[]{dropValue.get(textView.getTag().toString()) == null ? 0 : dropValue.get(textView.getTag().toString())[1], itemScore});
                    initViewSet(textView.getTag().toString(), "drop");
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    /**
     * 下拉多选弹窗
     *
     * @param dcSelectStrList
     * @param context
     * @param textView
     */
    public void ShowDropCheck(List<String> dcSelectStrList, Context context, final TextView textView) {
        itemScore = 0;

        CharSequence[] dcmItems = new CharSequence[dcSelectStrList.size()];
        dcTempStatus = new boolean[dcSelectStrList.size()];
        for (int i = 0; i < dcSelectStrList.size(); i++) {
            dcmItems[i] = dcSelectStrList.get(i);
            String[] dcText = textView.getText().toString().split(",");
            dcTempStatus[i] = false;
            for (String s : dcText) {
                if (s.equals(dcSelectStrList.get(i))) {
                    dcTempStatus[i] = true;
                    itemScore = itemScore + dcSelectScoreList.get(i);
                    break;
                }
            }
        }

        dropValue.put(textView.getTag().toString(), new Integer[]{dropValue.get(textView.getTag().toString()) == null ? 0 : dropValue.get(textView.getTag().toString())[1], itemScore});


        AlertDialog ad = new AlertDialog.Builder(context)
                .setTitle("选择")
                .setMultiChoiceItems(dcmItems, dcTempStatus, (dialog, which, isChecked) -> {
                    if (which == (dcmItems.length - 1)) {
                        for (int i = 0; i < dcmItems.length; i++) {
                            lv.setItemChecked(i, isChecked);
                            dcTempStatus[i] = isChecked;
                        }
                    }
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    itemScore = 0;
                    StringBuilder itmtxt = new StringBuilder();
                    for (int i = 0; i < (dcmItems.length - 1); i++) {
                        if (lv.getCheckedItemPositions().get(i)) {
                            if (itmtxt.length() > 0) {
                                itmtxt.append(",");
                            }
                            itmtxt.append(dcSelectStrList.get(i));
                            itemScore = itemScore + dcSelectScoreList.get(i);
                        }
                    }
                    textView.setText(itmtxt.toString());
                    dropValue.put(textView.getTag().toString(), new Integer[]{dropValue.get(textView.getTag().toString()) == null ? 0 : dropValue.get(textView.getTag().toString())[1], itemScore});
                    initViewSet(textView.getTag().toString(), "drop");

                }).setNegativeButton("取消", null).create();
        lv = ad.getListView();
        ad.show();
        ad.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    /**
     * 输入框
     *
     * @param element
     * @param elementType
     * @return
     */
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
            tvTitle.setOnClickListener(v -> {
                tipDialog = new NurRecordTipDialog(getActivity());
                tipDialog.setNurRecordTip(element.getToolTipText());
                tipDialog.setSureOnclickListener(() -> tipDialog.dismiss());
                tipDialog.show();
            });
        }

        if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
            tvTitle.setTextColor(Color.parseColor("#62ABFF"));
            tvTitle.setOnClickListener(v -> {
                String emrCode = element.getBindingTemplateID();
                String guid = "";
                String recId = "";
                for (int i = 0; i < firstIdListBeans.size(); i++) {
                    if (firstIdListBeans.get(i).getEmrCode().equals(emrCode)) {
                        guid = firstIdListBeans.get(i).getGuId();
                        recId = firstIdListBeans.get(i).getRecId();
                        break;
                    }
                }

                if (!StringUtils.isEmpty(guid)) {
                    callBackEffects = element.getCallBackEffects();
                    callBackReturnMapEffects = element.getCallBackReturnMapEffects();
                    Bundle bundle = new Bundle();
                    bundle.putString("EpisodeID", episodeID);
                    bundle.putString("BedNo", bedNo);
                    bundle.putString("PatName", patName);
                    bundle.putString("EMRCode", emrCode);
                    bundle.putString("GUID", guid);
                    bundle.putString("RecID", recId);
                    bundle.putString("CallBackEffects", callBackEffects);
                    bundle.putString("CallBackReturnMapEffects", callBackReturnMapEffects);
                    startFragment(NurRecordNewFragment.class, bundle, 10001);
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
            if (StringUtils.isEmpty(element.getPointLen()) || "0".equals(element.getPointLen())) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                editText.setFilters(new InputFilter[]{new InputDigitLengthFilter(Integer.parseInt(element.getPointLen()))});
            }

            if (!StringUtils.isEmpty(element.getMEName())) {
                MEViewLink meViewLink = new MEViewLink(element.getMEName(), element.getElementId());
                HashMap<String, String> MEHashMap = new HashMap<>();
                for (int i = 0; i < element.getOprationItemList().size(); i++) {
                    MEHashMap.put(element.getOprationItemList().get(i).getText(), element.getOprationItemList().get(i).getValue());
                }
                meViewLink.setMEHashMap(MEHashMap);
                meViewLinkList.add(meViewLink);
            }
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        //多行显示 在setInputType之后
        editText.setSingleLine(false);
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
                if (isNumber(s.toString())) {
                    double edDou = Double.parseDouble(s.toString());
                    for (int i = 0; i < elements.size(); i++) {

                        //数值输入范围判断提示
                        ElementDataBean.DataBean.InputBean.ElementBasesBean elementBasesBean = elements.get(i);
                        if (editText.getTag().equals(elementBasesBean.getElementId()) && "NumberElement".equals(elementBasesBean.getElementType())) {

                            if (StringUtils.isEmpty(elementBasesBean.getMaxError())) {

                                if (StringUtils.isEmpty(elementBasesBean.getMaxWarning())) {

                                    if (StringUtils.isEmpty(elementBasesBean.getMinWarning())) {

                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            //不做操作
                                            editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                        } else {

                                            if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    } else {

                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {

                                            if (edDou < Double.parseDouble(elementBasesBean.getMinWarning())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {

                                            if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if (Double.parseDouble(elementBasesBean.getMinWarning()) > edDou && edDou >= Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    }
                                } else {
                                    if (StringUtils.isEmpty(elementBasesBean.getMinWarning())) {

                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > (Double.parseDouble(elementBasesBean.getMaxWarning()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > (Double.parseDouble(elementBasesBean.getMaxWarning()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    } else {
                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > (Double.parseDouble(elementBasesBean.getMaxWarning()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinWarning())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > (Double.parseDouble(elementBasesBean.getMaxWarning()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (Double.parseDouble(elementBasesBean.getMinWarning()) > edDou && edDou >= Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (StringUtils.isEmpty(elementBasesBean.getMaxWarning())) {
                                    if (StringUtils.isEmpty(elementBasesBean.getMinWarning())) {
                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    } else {
                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinWarning())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if (Double.parseDouble(elementBasesBean.getMinWarning()) > edDou && edDou >= Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    }
                                } else {
                                    if (StringUtils.isEmpty(elementBasesBean.getMinWarning())) {
                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if ((Double.parseDouble(elementBasesBean.getMaxWarning())) < edDou && edDou <= (Double.parseDouble(elementBasesBean.getMaxError()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if ((Double.parseDouble(elementBasesBean.getMaxWarning())) < edDou && edDou <= (Double.parseDouble(elementBasesBean.getMaxError()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    } else {
                                        if (StringUtils.isEmpty(elementBasesBean.getMinError())) {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if ((Double.parseDouble(elementBasesBean.getMaxWarning())) < edDou && edDou <= (Double.parseDouble(elementBasesBean.getMaxError()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinWarning())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        } else {
                                            if (edDou > Double.parseDouble(elementBasesBean.getMaxError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else if ((Double.parseDouble(elementBasesBean.getMaxWarning())) < edDou && edDou <= (Double.parseDouble(elementBasesBean.getMaxError()))) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (Double.parseDouble(elementBasesBean.getMinWarning()) > edDou && edDou >= Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputwarning_bg);
                                            } else if (edDou < Double.parseDouble(elementBasesBean.getMinError())) {
                                                editText.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                            } else {
                                                editText.setBackgroundResource(R.drawable.nur_record_input_bg);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                initViewSet(editText.getTag().toString(), "");
            }
        });

        if ("true".equals(element.getDisable())) {
            editText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg_uneditable));
            editText.setEnabled(false);
        }

        TextView tvQuote = null;
        if ("TextareaElement".equals(elementType) && "RefMeasure".equals(element.getFoundationJS())) {
            tvQuote = new TextView(getActivity());
            LinearLayout.LayoutParams tvparams3 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 1.0f);
            tvparams3.setMargins(0, 0, 0, 0);
            tvQuote.setLayoutParams(tvparams3);
            tvQuote.setTextColor(Color.parseColor("#62abff"));
            tvQuote.setGravity(Gravity.CENTER);
            tvQuote.setText("引用");
            tvQuote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showQuoteDialog(editText);
                }
            });
        }

        elementIdtoDataSourceRef.put(element.getElementId(), element.getDataSourceRef());

        elementIdtoFormName.put(element.getElementId(), element.getFormName());
        viewHashMap.put(element.getElementId(), editText);

        inputViewFormNameList.add(element.getFormName());
        List<String> viewElementIdList = new ArrayList<>();
        viewElementIdList.add(element.getElementId());
        formNametoElementId.put(element.getFormName(), viewElementIdList);

        if (!StringUtils.isEmpty(element.getSaveField())) {
            itemtoElementId.put(element.getSaveField(), element.getElementId());
        }

        linearLayout.addView(tvTitle);
        linearLayout.addView(editText);

        if ("TextareaElement".equals(elementType) && "RefMeasure".equals(element.getFoundationJS()) && tvQuote != null) {
            linearLayout.addView(tvQuote);
        }

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(element.getContainerId())) {
            List<String> childElementIdList = new ArrayList<>();
            childElementIdList.add(element.getElementId());
            if (pcViewHashMap.containsKey(element.getContainerId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(element.getContainerId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(element.getContainerId(), childElementIdList);
            cpViewHashMap.put(element.getElementId(), element.getContainerId());
        }

        return linearLayout;
    }

    /**
     * 按钮
     * 保存
     * 单据跳转
     *
     * @param element
     * @return
     */
    private LinearLayout getButtonView(ElementDataBean.DataBean.InputBean.ElementBasesBean element) {
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
        if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
            tvTitle.setText("跳转单据");
        }

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        textView.setLayoutParams(tvparams2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#4a4a4a"));
        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
        textView.setText(element.getNameText());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //保存、采集数据保存
                if ("保存".equals(element.getNameText())) {
                    if (!StringUtils.isEmpty(element.getGatherImportMapEffects())) {
                        saveViewFormNameList.clear();
                        saveViewFormNameList.addAll(Arrays.asList(element.getGatherImportMapEffects().replace("^", "").split(",")));
                        save(saveViewFormNameList);
                    } else {
                        save(new ArrayList<>());
                    }
                    //跳转单据
                } else if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
                    String emrCode = element.getBindingTemplateID();
                    String guid = "";
                    String recId = "";
                    for (int i = 0; i < firstIdListBeans.size(); i++) {
                        if (firstIdListBeans.get(i).getEmrCode().equals(emrCode)) {
                            guid = firstIdListBeans.get(i).getGuId();
                            recId = firstIdListBeans.get(i).getRecId();
                            break;
                        }
                    }

                    if (!StringUtils.isEmpty(guid)) {
                        callBackEffects = element.getCallBackEffects();
                        callBackReturnMapEffects = element.getCallBackReturnMapEffects();
                        Bundle bundle = new Bundle();
                        bundle.putString("EpisodeID", episodeID);
                        bundle.putString("BedNo", bedNo);
                        bundle.putString("PatName", patName);
                        bundle.putString("EMRCode", emrCode);
                        bundle.putString("GUID", guid);
                        bundle.putString("RecID", recId);
                        bundle.putString("CallBackEffects", callBackEffects);
                        bundle.putString("CallBackReturnMapEffects", callBackReturnMapEffects);
                        startFragment(NurRecordNewFragment.class, bundle, 10001);
                    }
                    //未配置
                } else {
                    showToast("未配置按钮");
                }
            }
        });


        if ("true".equals(element.getDisable())) {
            textView.setClickable(false);
            textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg_unclickable));

        }

        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);
        elementIdtoFormName.put(element.getElementId(), element.getFormName());

        inputViewFormNameList.add(element.getFormName());
        List<String> viewElementIdList = new ArrayList<>();
        viewElementIdList.add(element.getElementId());
        formNametoElementId.put(element.getFormName(), viewElementIdList);

        linearLayout.addView(tvTitle);
        linearLayout.addView(textView);

        if ("true".equals(element.getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

        viewHashMap.put(element.getElementId() + "_ll", linearLayout);

        if (!StringUtils.isEmpty(element.getContainerId())) {
            List<String> childElementIdList = new ArrayList<>();
            childElementIdList.add(element.getElementId());
            if (pcViewHashMap.containsKey(element.getContainerId())) {
                List<String> childElementIdListExist = pcViewHashMap.get(element.getContainerId());
                if (childElementIdListExist != null && childElementIdListExist.size() > 0) {
                    childElementIdList.addAll(childElementIdListExist);
                }
            }
            pcViewHashMap.put(element.getContainerId(), childElementIdList);
            cpViewHashMap.put(element.getElementId(), element.getContainerId());
        }


        return linearLayout;
    }

    private void showQuoteDialog(EditText edQuote) {
        if (quoteDialog != null && quoteDialog.isShowing()) {
            quoteDialog.dismiss();
        }

        quoteDialog = new NurRecordQuoteDialog(getActivity());
        quoteDialog.setSureOnclickListener(new NurRecordQuoteDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                edQuote.setText(edQuote.getText() + quoteDialog.getKnowledgeContent());
                quoteDialog.dismiss();
            }
        });
        quoteDialog.setCancelOnclickListener(new NurRecordQuoteDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                quoteDialog.dismiss();
            }
        });
        quoteDialog.show();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            initViewSet(buttonView.getTag().toString(), "check");
        } else {
            initViewSet(buttonView.getTag().toString(), "uncheck");
        }

    }

    /**
     * 初始化元素配置
     * 级联元素配置
     *
     * @param viewElementId
     * @param status
     */
    private void initViewSet(String viewElementId, String status) {

        //操作联动
        String formName = elementIdtoFormName.get(viewElementId) == null ? null : elementIdtoFormName.get(viewElementId).split("\\^")[0];

        for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {

            //单元素配置
            if (!StringUtils.isEmpty(formName) && !formName.equals(elementSetsBeans.get(i).getFormName())) {
                continue;
            }

            ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);
            //执行全部配置
            String allSatisfy = elementSetsBean.getAllSatisfyFire();
            //仅执行第一条匹配配置
            String onlySatisfy = elementSetsBean.getOnlySatisfyFire();
            //计数
            int satisfySetCount = 0;

            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean> setDataListBeans = elementSetsBean.getSetDataList();
            for (int j = 0; j < setDataListBeans.size(); j++) {

                ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean setDataListBean = setDataListBeans.get(j);

                //checkbox变更控制其它元素
                if ("check".equals(status) || "uncheck".equals(status) || "".equals(status)) {
                    if (setDataListBean.getFormName().startsWith("RadioElement_")) {
                        //val-选中项
                        if (StringUtils.isEmpty(setDataListBean.getSign())) {
                            CheckBox checkBox = (CheckBox) viewHashMap.get(setDataListBean.getFormName() + "^" + setDataListBean.getVal());
                            if (checkBox != null && checkBox.isChecked()) {
                                satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                            }

                        } else {
                            //sign-无选中项
                            if ("EqEmptyArray".equals(setDataListBean.getSign())) {
                                List<String> elementIdList = formNametoElementId.get(elementSetsBean.getFormName());
                                int k;
                                for (k = 0; elementIdList!=null&&k < elementIdList.size(); k++) {
                                    CheckBox checkBox2 = (CheckBox) viewHashMap.get(elementIdList.get(k));
                                    if (checkBox2 != null && checkBox2.isChecked()) {
                                        break;
                                    }
                                }
                                if (elementIdList!=null&& k >= elementIdList.size()) {
                                    satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                                }
                            }
                        }

                    } else if (setDataListBean.getFormName().startsWith("CheckElement_")) {
                        if (StringUtils.isEmpty(setDataListBean.getSign())) {

                        } else {
                            //sign-无选中项
                            if ("IsEqualArry".equals(setDataListBean.getSign())) {
                                List<String> elementIdList = formNametoElementId.get(elementSetsBean.getFormName());
                                int k;
                                for (k = 0; k < elementIdList.size(); k++) {
                                    CheckBox checkBox2 = (CheckBox) viewHashMap.get(elementIdList.get(k));
                                    if (checkBox2 != null && checkBox2.isChecked()) {
                                        satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                                    }
                                }
                                //sign-有选中项
                            }else
                            //sign-无选中项
                            if ("EqEmptyArray".equals(setDataListBean.getSign())) {
                                List<String> elementIdList = formNametoElementId.get(elementSetsBean.getFormName());
                                int k;
                                for (k = 0; k < elementIdList.size(); k++) {
                                    CheckBox checkBox2 = (CheckBox) viewHashMap.get(elementIdList.get(k));
                                    if (checkBox2 != null && checkBox2.isChecked()) {
                                        break;
                                    }
                                }
                                if (k >= elementIdList.size()) {
                                    satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                                }
                                //sign-有选中项
                            } else if ("EqUnEmptyArray".equals(setDataListBean.getSign())) {
                                List<String> elementIdList = formNametoElementId.get(elementSetsBean.getFormName());
                                int k;
                                for (k = 0; k < elementIdList.size(); k++) {
                                    CheckBox checkBox2 = (CheckBox) viewHashMap.get(elementIdList.get(k));
                                    if (checkBox2 != null && checkBox2.isChecked()) {
                                        break;
                                    }
                                }
                                if (k < elementIdList.size()) {
                                    satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                                }
                                //sign-包含特定选中项
                            } else if ("ContainsAnyArry".equals(setDataListBean.getSign())) {
                                List<String> valList = Arrays.asList(setDataListBean.getVal().split(",").clone());
                                for (int i1 = 0; i1 < valList.size(); i1++) {
                                    CheckBox checkBox = (CheckBox) viewHashMap.get(setDataListBean.getFormName() + "^" + valList.get(i1));
                                    if (checkBox != null && checkBox.isChecked()) {
                                        satisfySetCount = forChangeListSetViewStatus(satisfySetCount, setDataListBean);
                                    }
                                }
                            }
                        }

                    }
                }

                //drop 控制 view 显隐
                if ("drop".equals(status) || "".equals(status)) {

                    if (setDataListBean.getFormName().startsWith("DropRadioElement_") || setDataListBean.getFormName().startsWith("DropListElement_")) {
                        TextView textView = (TextView) viewHashMap.get(viewElementId);
                        if (StringUtils.isEmpty(setDataListBean.getSign())) {
                            if (textView != null && !StringUtils.isEmpty(textView.getText().toString()) && setDataListBean.getChangeList() != null) {
                                String tvStr = textView.getText().toString();
                                String numberValue = "";
                                List<String> dropTextandNumberValue = elementIdtoOprationItemList.get(viewElementId);
                                for (int k = 0; dropTextandNumberValue != null && k < dropTextandNumberValue.size(); k++) {
                                    String[] TN = dropTextandNumberValue.get(k).split("\\^");
                                    if (TN != null && TN.length == 2) {
                                        if (tvStr.equals(TN[0])) {
                                            numberValue = TN[1];
                                        }
                                    }
                                }


                                if (numberValue.equals(setDataListBean.getVal())) {
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeanList = setDataListBean.getChangeList();
                                    for (int l = 0; l < changeListBeanList.size(); l++) {
                                        ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean changeListBean = setDataListBean.getChangeList().get(l);
                                        LinearLayout llContainer = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_containerll");
                                        LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");

                                        setViewStatus(changeListBean, llContainer, linearLayout);
                                        satisfySetCount++;
                                    }

                                }

                            }
                        } else {
                            if ("EqEmptyArray".equals(setDataListBean.getSign())) {
                                if (textView != null && StringUtils.isEmpty(textView.getText().toString()) && setDataListBean.getChangeList() != null) {
                                    for (int l = 0; l < setDataListBean.getChangeList().size(); l++) {
                                        ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean changeListBean = setDataListBean.getChangeList().get(l);
                                        LinearLayout llContainer = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_containerll");
                                        LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");

                                        setViewStatus(changeListBean, llContainer, linearLayout);
                                        satisfySetCount++;
                                    }
                                }
                            }
                        }
                    }
                }

                //分数 控制 选中
                if (setDataListBean.getFormName().startsWith("TextElement_") || setDataListBean.getFormName().startsWith("NumberElement_")) {
                    EditText editText = (EditText) viewHashMap.get(viewElementId);
                    if (editText != null && !StringUtils.isEmpty(editText.getText().toString())) {
                        if ("EqUnEmptyText".equals(setDataListBean.getSign())) {
                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                            if (changeListBeans != null && changeListBeans.size() > 0) {
                                ValSetView(changeListBeans);
                            }
                        } else {
                            if (editText.getText().toString().matches("[0-9]+")) {
                                int edTextInt;
                                try {
                                    edTextInt = Integer.parseInt(StringUtils.isEmpty(editText.getText().toString()) ? "-1" : editText.getText().toString());

                                    if (edTextInt > -1) {
                                        //Number
                                        if ("EqNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt == val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("NEqNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt != val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("LeEqNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt <= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("LeNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt < val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrNumber".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqNumber1LeEqNumber2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrNumber1LeEqNumber2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqNumber1LeNumber2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrNumber1LeNumber2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                            //Text
                                        } else if ("Equal".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt == val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("NEqText".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt != val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("LeEqText".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt <= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("LeText".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt < val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqText".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrText".equals(setDataListBean.getSign())) {
                                            int val = Integer.parseInt(setDataListBean.getVal());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqText1LeEqText2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrText1LeEqText2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrEqText1LeText2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt >= val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        } else if ("GrText1LeText2".equals(setDataListBean.getSign())) {
                                            int val1 = Integer.parseInt(setDataListBean.getVal());
                                            int val2 = Integer.parseInt(setDataListBean.getVal2());
                                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                            if (edTextInt > val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                ValSetView(changeListBeans);
                                            }
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    showToast("分数数值不规范");
                                    e.printStackTrace();
                                }
                            } else {
                                String edTextStr = editText.getText().toString();
                                if ("ContainsText".equals(setDataListBean.getSign())) {
                                    String val = setDataListBean.getVal();
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                    if (edTextStr.contains(val) && changeListBeans != null && changeListBeans.size() > 0) {
                                        ValSetView(changeListBeans);
                                    }
                                } else if ("NContainsText".equals(setDataListBean.getSign())) {
                                    String val = setDataListBean.getVal();
                                    List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                                    if (!edTextStr.contains(val) && changeListBeans != null && changeListBeans.size() > 0) {
                                        ValSetView(changeListBeans);
                                    }
                                }
                            }
                        }

                    } else {
                        if ("EqEmptyText".equals(setDataListBean.getSign())) {
                            List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans = setDataListBean.getChangeList();
                            if (changeListBeans != null && changeListBeans.size() > 0) {
                                ValSetView(changeListBeans);
                            }
                        }
                    }
                }

                if ("true".equals(onlySatisfy) && satisfySetCount > 0) {
                    break;
                }
            }
        }

        if (formName != null && formName.startsWith("RadioElement_") && "check".equals(status)) {
            //radio互斥
            List<String> viewElementIdList = formNametoElementId.get(formName);
            if (viewElementIdList != null && viewElementIdList.size() > 0) {

                for (int l = 0; l < viewElementIdList.size(); l++) {
                    CheckBox checkBox2 = (CheckBox) viewHashMap.get(viewElementIdList.get(l));
                    if (checkBox2 != null) {
                        if (!viewElementId.equals(viewElementIdList.get(l)) && checkBox2.isChecked()) {
                            checkBox2.setChecked(false);
                        }
                    }

                }
            }
        }

        //计分
        for (int i = 0; statisticsListBeans != null && i < statisticsListBeans.size(); i++) {
            ElementDataBean.DataBean.InputBean.StatisticsListBean statisticsListBean = statisticsListBeans.get(i);
            String[] idStr = statisticsListBean.getEffects().split(",");
            etPointList.clear();
            if ("".equals(status) || "drop".equals(status)) {
                for (String s : idStr) {
                    if (viewHashMap.get(s) instanceof CheckBox) {
                        //不做操作
                    } else if (viewHashMap.get(s) instanceof TextView) {
                        // EditText、TextView 存分
                        TextView textView = (TextView) viewHashMap.get(s);
                        if (textView != null) {
                            if (RegexUtils.isMatch(RegexConstants.REGEX_INTEGER, textView.getText().toString())) {
                                etPointList.add(Integer.parseInt(textView.getText().toString()));
                            }
                        }
                    }
                }
            }

            for (String s : idStr) {
                if (s.equals(viewElementId)) {
                    EditText editText = (EditText) viewHashMap.get(statisticsListBean.getId());
                    if (editText != null) {
                        String calType = statisticsListBean.getCalType();
                        if ("Sum".equals(calType)) {
                            int score = Integer.parseInt(StringUtils.isEmpty(editText.getText().toString()) ? "0" : editText.getText().toString());

                            if ("check".equals(status) || "uncheck".equals(status)) {
                                int changeScore = Integer.parseInt(elementIdtoFormName.get(viewElementId).split("\\^")[1]);
                                CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementId);
                                if (checkBox != null && checkBox.isChecked()) {
                                    score = score + changeScore;
                                } else {
                                    score = score - changeScore;
                                }
                            } else if ("drop".equals(status)) {
                                Integer[] scoreInt = dropValue.get(viewElementId);
                                if (scoreInt != null) {
                                    score = score - scoreInt[0] + scoreInt[1];
                                }
                            } else {
                                if (etPointList.size() > 0) {
                                    score = 0;
                                    for (int j = 0; j < etPointList.size(); j++) {
                                        score = score + etPointList.get(j);
                                    }
                                }
                            }
                            editText.setText(String.valueOf(score));
                            /// EH 2021-10-19 统分类型 start
                        } else if ("Max".equals(calType)) {
                            int score = 0;
                            for (String s2 : idStr) {
                                Object element = viewHashMap.get(s2);
                                if (element instanceof  CheckBox) {
                                    CheckBox checkBox = (CheckBox)element;
                                    if (checkBox.isChecked()) {
                                        int changeScore = Integer.parseInt(elementIdtoFormName.get(s2).split("\\^")[1]);
                                        if (changeScore > score) {
                                            score = changeScore;
                                        }
                                    }
                                } else if (element instanceof  TextView || element instanceof  EditText) {
                                    EditText textView = (EditText)element;
                                    int changeScore = Integer.parseInt(StringUtils.isEmpty(textView.getText().toString()) ? "0" : textView.getText().toString());
                                    if (changeScore > score) {
                                        score = changeScore;
                                    }
                                }
                            }
                            editText.setText(String.valueOf(score));
                        } else if ("Min".equals(calType)) {
                            int score = 999;
                            for (String s2 : idStr) {
                                Object element = viewHashMap.get(s2);
                                if (element instanceof  CheckBox) {
                                    CheckBox checkBox = (CheckBox)element;
                                    if (checkBox.isChecked()) {
                                        int changeScore = Integer.parseInt(elementIdtoFormName.get(s2).split("\\^")[1]);
                                        if (changeScore < score) {
                                            score = changeScore;
                                        }
                                    }
                                } else if (element instanceof  TextView || element instanceof  EditText) {
                                    EditText textView = (EditText)element;
                                    int changeScore = Integer.parseInt(StringUtils.isEmpty(textView.getText().toString()) ? "0" : textView.getText().toString());
                                    if (changeScore < score) {
                                        score = changeScore;
                                    }
                                }
                            }
                            editText.setText(String.valueOf(score));
                        }
                        /// EH 2021-10-19 统分类型 end
                    }
                }
            }
        }

        //医学表达式赋值
        for (int i = 0; i < meViewLinkList.size(); i++) {
            MEViewLink meViewLink = meViewLinkList.get(i);

            if (meViewLink.getMEHashMap().containsValue(viewElementId)) {
                if (meViewLink.getLinkName().equals("出入平衡（新）")) {
                    EditText editText1 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item1"));
                    EditText editText2 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item2"));
                    EditText editText3 = (EditText) viewHashMap.get(meViewLink.getResultViewId());

                    if (editText1 != null && editText2 != null && editText3 != null) {
                        try {
                            int num1 = Integer.parseInt(StringUtils.isTrimEmpty(editText1.getText().toString()) ? "0" : editText1.getText().toString());
                            int num2 = Integer.parseInt(StringUtils.isTrimEmpty(editText2.getText().toString()) ? "0" : editText2.getText().toString());
                            int result = num1 - num2;
                            editText3.setText(String.valueOf(result));
                        } catch (NumberFormatException e) {
                            showToast("分数数值不规范");
                            e.printStackTrace();
                        }
                    }
                } else if (meViewLink.getLinkName().equals("BMI指数")) {
                    EditText editText1 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item1"));
                    EditText editText2 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item2"));
                    EditText editText3 = (EditText) viewHashMap.get(meViewLink.getResultViewId());

                    if (editText1 != null && editText2 != null && editText3 != null) {
                        try {
                            int num1 = Integer.parseInt(StringUtils.isTrimEmpty(editText1.getText().toString()) ? "0" : editText1.getText().toString());
                            int num2 = Integer.parseInt(StringUtils.isTrimEmpty(editText2.getText().toString()) ? "0" : editText2.getText().toString());
                            if (num1 == 0) {
                                Toast.makeText(getActivity(), "请填写体重以计算BMI指数", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (num2 == 0) {
                                Toast.makeText(getActivity(), "请填写身高以计算BMI指数", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            double result1 = num2 * num2;
                            double result2 = num1 * 1.0f / result1;
                            BigDecimal bg = new BigDecimal(result2);
                            double result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            editText3.setText(String.valueOf(result));

                        } catch (NumberFormatException e) {
                            showToast("分数数值不规范");
                            e.printStackTrace();
                        }
                    }
                } else if (meViewLink.getLinkName().equals("cm转m")) {
                    EditText editText1 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item1"));
                    EditText editText2 = (EditText) viewHashMap.get(meViewLink.getResultViewId());

                    if (editText1 != null && editText2 != null) {
                        try {
                            int num1 = Integer.parseInt(StringUtils.isTrimEmpty(editText1.getText().toString()) ? "0" : editText1.getText().toString());

                            double result1 = num1 * 1.0f / 100;
                            BigDecimal bg = new BigDecimal(result1);
                            double result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            editText2.setText(String.valueOf(result));

                        } catch (NumberFormatException e) {
                            showToast("分数数值不规范");
                            e.printStackTrace();
                        }
                    }
                } else if (meViewLink.getLinkName().equals("kg转g")) {
                    EditText editText1 = (EditText) viewHashMap.get(meViewLink.getMEHashMap().get("item1"));
                    EditText editText2 = (EditText) viewHashMap.get(meViewLink.getResultViewId());

                    if (editText1 != null && editText2 != null) {
                        try {
                            double num1 = Double.parseDouble(StringUtils.isTrimEmpty(editText1.getText().toString()) ? "0" : editText1.getText().toString());
                            double result = num1 * 1000;
                            editText2.setText(String.valueOf(result));

                        } catch (NumberFormatException e) {
                            showToast("分数数值不规范");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //必填项填入内容之后还原view
        if ("check".equals(status)) {
            String viewFormName = elementIdtoFormName.get(viewElementId) == null ? "" : elementIdtoFormName.get(viewElementId);
            if (viewFormName.contains("RadioElement") || viewFormName.contains("CheckElement")) {
                List<String> elementIdList = formNametoElementId.get(viewFormName.split("\\^")[0]);
                for (int k = 0; elementIdList != null && k < elementIdList.size(); k++) {
                    CheckBox checkBox = (CheckBox) viewHashMap.get(elementIdList.get(k));
                    if (checkBox != null) {
                        checkBox.setTextColor(Color.parseColor("#FF4A4A4A"));
                    }
                }

            }
        }
    }

    /**
     * 遍历SetDataList中的ChangeList 来控制Show或Hide
     * @param satisfySetCount
     * @param setDataListBean
     * @return
     */
    private int forChangeListSetViewStatus(int satisfySetCount, ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean setDataListBean) {
        for (int l = 0; setDataListBean.getChangeList() != null && l < setDataListBean.getChangeList().size(); l++) {
            ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean changeListBean = setDataListBean.getChangeList().get(l);
            LinearLayout llContainer = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_containerll");
            LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");

            setViewStatus(changeListBean, llContainer, linearLayout);
            satisfySetCount++;
        }
        return satisfySetCount;
    }

    /**
     * 控制容器 显示隐藏
     * 控制元素 显示隐藏 是否可编辑 赋值
     *
     * @param changeListBean
     * @param llContainer
     * @param linearLayout
     */
    private void setViewStatus(ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean changeListBean, LinearLayout llContainer, LinearLayout linearLayout) {
        String type = changeListBean.getType();
        if (llContainer != null) {
            //控制容器
            if (type.contains("Show")) {
                llContainer.clearAnimation();
                llContainer.setVisibility(View.VISIBLE);
            }

            if (type.contains("Hide")) {
                llContainer.clearAnimation();
                llContainer.setVisibility(View.GONE);
            }
        }

        if (linearLayout != null) {
            //控制单一元素
            if (viewHashMap.get(changeListBean.getId()) instanceof CheckBox) {
                LinearLayout llCheck = (LinearLayout) linearLayout.getChildAt(1);
                List<CheckBox> checkBoxes = new ArrayList<>();
                if (llCheck != null) {
                    for (int i = 0; i < llCheck.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) llCheck.getChildAt(i);
                        if (checkBox != null) {
                            checkBoxes.add(checkBox);
                        }
                    }
                }

                if (type.contains("Enable")) {
                    if (checkBoxes.size() > 0) {
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            checkBoxes.get(i).setEnabled(true);
                            checkBoxes.get(i).setClickable(true);
                        }
                    }
                } else if (type.contains("DisEnable")) {
                    if (checkBoxes.size() > 0) {
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            checkBoxes.get(i).setClickable(false);
                            checkBoxes.get(i).setEnabled(false);
                        }
                    }
                }

                if (type.contains("Show")) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (type.contains("Hide")) {
                    if (checkBoxes.size() > 0) {
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            checkBoxes.get(i).setChecked(false);
                        }
                    }
                    linearLayout.setVisibility(View.GONE);
                }
            } else if (viewHashMap.get(changeListBean.getId()) instanceof EditText) {
                EditText editText = (EditText) viewHashMap.get(changeListBean.getId());

                if (type.contains("HasData")) {
                    editText.setText(changeListBean.getVal());
                }

                if (type.contains("Enable")) {
                    editText.setFocusable(true);
                    editText.setEnabled(true);
                    editText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));
                } else if (type.contains("DisEnable")) {
                    editText.setText("");
                    editText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg_uneditable));
                    editText.setFocusable(false);
                    editText.setEnabled(false);
                }


                if (type.contains("Show")) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (type.contains("Hide")) {
                    editText.setText("");
                    linearLayout.setVisibility(View.GONE);
                }
            } else if (viewHashMap.get(changeListBean.getId()) instanceof TextView) {
                TextView textView = (TextView) viewHashMap.get(changeListBean.getId());

                if (type.contains("HasData")) {
                    textView.setText(changeListBean.getVal());
                }

                if (type.contains("Enable")) {
                    textView.setClickable(true);
                    textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
                } else if (type.contains("DisEnable")) {
                    textView.setText("");
                    textView.setClickable(false);
                    textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg_unclickable));
                }

                if (type.contains("Show")) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (type.contains("Hide")) {
                    textView.setText("");
                    linearLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 分数控制view选中 赋值
     *
     * @param changeListBeans
     */
    public void ValSetView(List<ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean> changeListBeans) {
        for (int i1 = 0; i1 < changeListBeans.size(); i1++) {
            ElementDataBean.DataBean.InputBean.ElementSetsBean.SetDataListBean.ChangeListBean changeListBean = changeListBeans.get(i1);
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
            } else if (viewHashMap.get(changeListBean.getId()) instanceof TextView) {
                TextView textView1 = (TextView) viewHashMap.get(changeListBean.getId());
                if (textView1 != null && !StringUtils.isTrimEmpty(changeListBean.getSelectItems())) {
                    if (changeListBean.getType().contains("HasData")) {
                        List<String> dropItremTextandNumberValue = elementIdtoOprationItemList.get(changeListBean.getId());

                        for (int i = 0; dropItremTextandNumberValue != null && i < dropItremTextandNumberValue.size(); i++) {
                            if (dropItremTextandNumberValue.get(i).split("\\^").length == 2 && dropItremTextandNumberValue.get(i).split("\\^")[1].equals(changeListBean.getSelectItems())) {
                                textView1.setText(dropItremTextandNumberValue.get(i).split("\\^")[0]);
                            }
                        }
                    }
                }
            }

        }
    }

    /// EH 2021-09-10 签名回车事件 start
    private void onEditTextAdd(EditText editText, String text, String code) {
        onEditTextConvert(editText, text, code, "add", null);
    }

    private void onEditTextConvert(EditText editText, String text, String code, String event, TextWatcher watcher) {
        text = text.replace("\r", "").replace("\n" ,"");
        if (text.equals("") || ifEditTextConvert(text)) {
            return;
        }
        NurRecordNewApiManager.editTextConvert(code, text, event, new NurRecordNewApiManager.EditTextConvertCallback() {
            @Override
            public void onSuccess(com.dhcc.nursepro.workarea.nurrecordnew.bean.EditTextConvertBean editTextConvertBean) {
                String displayText = editTextConvertBean.getDisplayText();
                String convertedValue = editTextConvertBean.getConvertedValue();
                if (displayText != null) {
                    editTextConvertMap.put(editTextConvertBean.getText(), new String[] { displayText,  convertedValue});
                    if (event.equals("change")) {
                        if (!displayText.equals("") && !displayText.equals(editTextConvertBean.getText())) {
                            onEditTextSet(editText, displayText, watcher);
                        }
                    }
                }
            }
            @Override
            public void onFail(String code, String msg) {
                onEditTextConvertFail(code, msg);
            }
        });
    }

    public void onEditTextConvertFail(String code, String msg) {
        errorDialog = new NurRecordSaveErrorDialog(getActivity());
        errorDialog.setExecresult(msg);
        errorDialog.setImgId(R.drawable.icon_popup_error_patient);
        errorDialog.setSureVisible(View.VISIBLE);
        errorDialog.setCancleVisible(View.GONE);
        errorDialog.setSureOnclickListener(() -> errorDialog.dismiss());
        errorDialog.show();
    }

    private void onEditTextSet(EditText editText, String text, TextWatcher watcher) {
        editText.removeTextChangedListener(watcher);
        editText.setText(text);
        editText.setSelection(text.length());
        editText.addTextChangedListener(watcher);
    }

    private void onEditTextEnter(EditText editText, String code) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                TextWatcher watcher = this;
                String text = editable.toString();
                if ((text.indexOf("\r") > -1) || (text.indexOf("\n") > -1)) {
                    text = text.replace("\r", "").replace("\n" ,"");
                    if (text.equals("")) {
                        onEditTextSet(editText,"", watcher);
                        return;
                    } else if (editTextConvertMap.containsKey(text)) {
                        String displayText = editTextConvertMap.get(text)[0];
                        displayText = !displayText.equals("") ? displayText : text;
                        onEditTextSet(editText,displayText, watcher);
                        return;
                    } else {
                        onEditTextSet(editText,text, watcher);
                    }
                    onEditTextConvert(editText,text,code,"change",watcher);
                }
            }
        });
    }

    private boolean ifEditTextConvert(String text) {
        if (!text.equals("")) {
            if (editTextConvertMap.containsKey(text)) {
                return true;
            }
            for(java.util.Map.Entry<String, String[]> entry : editTextConvertMap.entrySet()) {
                if (text.equals(entry.getValue()[0])) {
                    return true;
                }
            }
        }
        return false;
    }

    private String onEditTextSave(EditText editText) {
        String text = editText.getText().toString();
        text = text.replace("\r", "").replace("\n" ,"");
        if (!text.equals("")) {
            for(java.util.Map.Entry<String, String[]> entry : editTextConvertMap.entrySet()) {
                if (text.equals(entry.getKey()) || text.equals(entry.getValue()[0])) {
                    text = entry.getValue()[1];
                    break;
                }
            }
        }
        return text;
    }
    /// EH 2021-09-10 签名回车事件 end

}
