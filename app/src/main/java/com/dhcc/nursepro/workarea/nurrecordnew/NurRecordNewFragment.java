package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.InputDigitLengthFilter;
import com.dhcc.nursepro.workarea.nurrecordnew.api.NurRecordNewApiManager;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.DataSourceBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ElementDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.NurRecordKnowledgeContentBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecDataBean;

import java.util.ArrayList;
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
    //id formname 关联
    private final HashMap<String, String> elementIdtoFormName = new HashMap<>();
    private final HashMap<String, List<String>> formNametoElementId = new HashMap<>();
    //drop edittext
    private final HashMap<String, Integer[]> dropValue = new HashMap<>();
    //drop id val关联
    private final HashMap<String, List<String>> elementIdtoOprationItemList = new HashMap<>();
    //parent child view
    private final HashMap<String, List<String>> pcViewHashMap = new HashMap<>();

    //跳转回传取值关联
    private final HashMap<String, String> elementIdtoDataSourceRef = new HashMap<>();


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
    private List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements;
    private List<ElementDataBean.DataBean.InputBean.ElementSetsBean> elementSetsBeans;
    private List<ElementDataBean.DataBean.InputBean.StatisticsListBean> statisticsListBeans;


    private SPUtils spUtils;

    private List<ElementDataBean.FirstIdListBean> firstIdListBeans = new ArrayList<>();

    private String emrCodeJump = "";
    private String guidJump = "";
    private String recIdJump = "";
    private String callBackEffects = "";
    private String callBackReturnMapEffects = "";

    private NurRecordTipDialog tipDialog;
    private NurRecordQuoteDialog quoteDialog;
    private NurRecordSaveErrorDialog errorDialog;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && resultCode == 20002) {
            String callbackElementId = data.getStringExtra("CallBackEffects");
            String callbackElementFormName = data.getStringExtra("CallBackReturnMapEffects");
            if (!StringUtils.isEmpty(callbackElementId) && callbackElementId.split(",").length > 0) {
                String[] callbackElementIds = callbackElementId.split(",");
                for (String elementId : callbackElementIds) {
                    String dataSourceRef = elementIdtoDataSourceRef.get(elementId);
                    if (!StringUtils.isEmpty(dataSourceRef)) {
                        setCallBackViewText(elementId, dataSourceRef, episodeID);
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
        viewright.setOnClickListener(v -> save());
        //        setToolbarRightCustomView(viewright);
        if (isSingleModel) {
            hindMap();
            setToolbarRightCustomViewSingleShow(viewright);
        } else {
            setToolbarRightCustomView(viewright);
        }
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
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
     */
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
        showLoadingTip(BaseActivity.LoadingType.FULL);
        NurRecordNewApiManager.saveNewEmrData(guid, episodeID, recId, parr, new NurRecordNewApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                hideLoadFailTip();
                showToast("保存成功");
                if (!StringUtils.isEmpty(callBackEffects)) {
                    Intent intent = new Intent();
                    intent.putExtra("CallBackEffects", callBackEffects);
                    intent.putExtra("CallBackReturnMapEffects", callBackReturnMapEffects);
                    Objects.requireNonNull(getActivity()).setResult(20002, intent);
                }
                Objects.requireNonNull(getActivity()).finish();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
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

    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        NurRecordNewApiManager.GetXmlValues(episodeID, emrCode, recId, new CommonCallBack<ElementDataBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(ElementDataBean elementDataBean, String type) {
                hideLoadFailTip();
                elements = elementDataBean.getData().getInput().getElementBases();
                elementSetsBeans = elementDataBean.getData().getInput().getElementSets();
                statisticsListBeans = elementDataBean.getData().getInput().getStatisticsList();
                firstIdListBeans = elementDataBean.getFirstIdList();
                InputViews(elements);
            }
        });
    }

    /**
     * 添加各类型元素
     */
    private void InputViews(List<ElementDataBean.DataBean.InputBean.ElementBasesBean> elements) {
        for (int i = 0; elements != null && i < elements.size(); i++) {
            ElementDataBean.DataBean.InputBean.ElementBasesBean element = elements.get(i);
            if ("DateElement".equals(element.getElementType())) {
                LinearLayout llDate = getTextView(element);
                TextView tvDate = (TextView) viewHashMap.get(element.getElementId());

                tvDate.setOnClickListener(v -> ShowDateTime(DATE_DIALOG, getActivity(), tvDate));
                llNurrecord.addView(llDate);
                llNurrecord.addView(getDashLine());

            } else if ("TimeElement".equals(element.getElementType())) {
                LinearLayout llTime = getTextView(element);
                TextView tvTime = (TextView) viewHashMap.get(element.getElementId());

                tvTime.setOnClickListener(v -> ShowDateTime(TIME_DIALOG, getActivity(), tvTime));
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

                llNurrecord.addView(lldropRadio);
                llNurrecord.addView(getDashLine());
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
                    selectStrList.add("");
                    selectScoreList.add(0);

                    ShowRadioScore(selectStrList, getActivity(), tvdropRadio);
                });

                lldropRadio.clearAnimation();

                llNurrecord.addView(lldropRadio);
                llNurrecord.addView(getDashLine());
            } else if ("DropCheckboxElement".equals(element.getElementType())) {
                LinearLayout lldropCheck = getTextView(element);
                TextView tvdropCheck = (TextView) viewHashMap.get(element.getElementId());
                tvdropCheck.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));

                tvdropCheck.setOnClickListener(v -> {
                    tvdropCheck.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    dcSelectStrList = new ArrayList<>();
                    for (int i1 = 0; element.getOprationItemList() != null && i1 < element.getOprationItemList().size(); i1++) {
                        dcSelectStrList.add(element.getOprationItemList().get(i1).getText());
                    }
                    dcSelectStrList.add("全选/取消全选");

                    ShowDropCheck(dcSelectStrList, getActivity(), tvdropCheck);
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
                TextView tvTitle = (TextView) viewHashMap.get(element.getElementId() + "_title");
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

                lledit.clearAnimation();

                llNurrecord.addView(lledit);
                llNurrecord.addView(getDashLine());
            } else if ("TextareaElement".equals(element.getElementType())) {
                LinearLayout lledit = getEditText(element, "TextareaElement");
                EditText edText = (EditText) viewHashMap.get(element.getElementId());
                edText.setBackground(getResources().getDrawable(R.drawable.nur_record_input_bg));

                llNurrecord.addView(lledit);
                llNurrecord.addView(getDashLine());
            } else if ("ButtonElement".equals(element.getElementType())) {
                if (!StringUtils.isEmpty(element.getBindingTemplateID()) && !"true".equals(element.getIsHide())) {
                    LinearLayout llButton = getButtonView(element);

                    llNurrecord.addView(llButton);
                    llNurrecord.addView(getDashLine());
                }
            }

        }

        setViews("", "");
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

    /**
     * 单据跳转元素
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
        tvTitle.setText("跳转单据");

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
            }
        });


        if ("true".equals(element.getDisable())) {
            textView.setClickable(false);
        }

        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);
        elementIdtoFormName.put(element.getElementId(), element.getFormName());

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

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams tvparams2 = new LinearLayout.LayoutParams(0, ConvertUtils.dp2px(40f), 2.0f);
        textView.setLayoutParams(tvparams2);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#4a4a4a"));
        textView.setBackground(getResources().getDrawable(R.drawable.nur_record_btn_bg));
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
            }
        }

        if ("true".equals(element.getDisable())) {
            textView.setClickable(false);
        }

        textView.setTag(element.getElementId());
        viewHashMap.put(element.getElementId(), textView);
        elementIdtoFormName.put(element.getElementId(), element.getFormName());
        elementIdtoDataSourceRef.put(element.getElementId(), element.getDataSourceRef());

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
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getNumberValue(), checkBox);
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

        linearLayout.addView(tvTitle);
        linearLayout.addView(llRadio);

        if ("true".equals(radioElementListBeanList.get(0).getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

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
                checkBox.setClickable(false);
            }

            viewHashMap.put(radioElementListBean.getElementId(), checkBox);
            viewHashMap.put(radioElementListBean.getFormName() + "^" + radioElementListBean.getOprationItemList().get(0).getNumberValue(), checkBox);
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

        linearLayout.addView(tvTitle);
        linearLayout.addView(llCheck);

        if ("true".equals(radioElementListBeanList.get(0).getIsHide())) {
            linearLayout.clearAnimation();
            linearLayout.setVisibility(View.GONE);
        }

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
                    setViews(textView.getTag().toString(), "drop");
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
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
                setViews(editText.getTag().toString(), "");
            }
        });

        if ("true".equals(element.getDisable())) {
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
        List<String> elementList = new ArrayList<>();
        elementList.add(element.getElementId());
        formNametoElementId.put(element.getFormName(), elementList);
        viewHashMap.put(element.getElementId(), editText);


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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            setViews(buttonView.getTag().toString(), "true");
        } else {
            setViews(buttonView.getTag().toString(), "false");
        }
    }

    /**
     * View级联控制 显示隐藏 是否可编辑 选中未选中 评分
     *
     * @param viewElementId
     * @param isChecked
     */
    private void setViews(String viewElementId, String isChecked) {
        if (StringUtils.isEmpty(viewElementId)) {
            //界面初始化
            for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);

                // check 控制 view（可能单一元素，可能一组元素） 是否可编辑 显示隐藏
                if (elementSetsBean.getFormName().startsWith("RadioElement_")) {
                    CheckBox checkBox = (CheckBox) viewHashMap.get(elementSetsBean.getFormName() + "^" + elementSetsBean.getVal());
                    if (checkBox != null && checkBox.isChecked()) {
                        for (int i1 = 0; elementSetsBean.getChangeList() != null && i1 < elementSetsBean.getChangeList().size(); i1++) {
                            ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = elementSetsBean.getChangeList().get(i1);
                            LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");

                            setEditTextorTextView(changeListBean, linearLayout);
                        }
                    }


                }
            }
        } else {
            //操作联动
            String formName = elementIdtoFormName.get(viewElementId) == null ? null : elementIdtoFormName.get(viewElementId).split("\\^")[0];

            //check选中控制
            if ("true".equals(isChecked)) {

                //radio互斥
                if (formName != null && formName.startsWith("RadioElement_")) {

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

                //check 控制 view 显隐 选中
                for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);

                    if (elementSetsBean.getFormName().equals(formName) && elementSetsBean.getFormName().startsWith("RadioElement_")) {
                        CheckBox checkBox = (CheckBox) viewHashMap.get(elementSetsBean.getFormName() + "^" + elementSetsBean.getVal());
                        if (checkBox != null && checkBox.isChecked() && elementSetsBean.getChangeList() != null && elementSetsBean.getChangeList().size() > 0) {
                            for (int i1 = 0; i1 < elementSetsBean.getChangeList().size(); i1++) {
                                ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = elementSetsBean.getChangeList().get(i1);
                                LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");
                                setEditTextorTextView(changeListBean, linearLayout);
                            }
                        }
                    }
                }
            } else if ("drop".equals(isChecked)) {
                //drop 控制 view 显隐
                for (int i = 0; elementSetsBeans != null && i < elementSetsBeans.size(); i++) {
                    ElementDataBean.DataBean.InputBean.ElementSetsBean elementSetsBean = elementSetsBeans.get(i);

                    if (elementSetsBean.getFormName().equals(formName) && (elementSetsBean.getFormName().startsWith("DropRadioElement_") || elementSetsBean.getFormName().startsWith("DropListElement_"))) {
                        TextView textView = (TextView) viewHashMap.get(viewElementId);
                        if (textView != null && !StringUtils.isEmpty(textView.getText().toString()) && elementSetsBean.getChangeList() != null && elementSetsBean.getChangeList().size() > 0) {
                            String tvStr = textView.getText().toString();
                            String numberValue = "";
                            List<String> dropTextandNumberValue = elementIdtoOprationItemList.get(viewElementId);
                            for (int i1 = 0; dropTextandNumberValue != null && i1 < dropTextandNumberValue.size(); i1++) {
                                String[] TN = dropTextandNumberValue.get(i1).split("\\^");
                                if (TN != null && TN.length == 2) {
                                    if (tvStr.equals(TN[0])) {
                                        numberValue = TN[1];
                                    }
                                }
                            }

                            for (int i1 = 0; i1 < elementSetsBean.getChangeList().size(); i1++) {
                                if (numberValue.equals(elementSetsBean.getVal())) {
                                    ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean = elementSetsBean.getChangeList().get(i1);
                                    LinearLayout linearLayout = (LinearLayout) viewHashMap.get(changeListBean.getId() + "_ll");
                                    setEditTextorTextView(changeListBean, linearLayout);
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
                        if (!StringUtils.isEmpty(editText.getText().toString())) {
                            if ("EqUnEmptyText".equals(elementSetsBean.getSign())) {
                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
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
                                            if ("EqNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt == val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("NEqNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt != val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("LeEqNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt <= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("LeNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt < val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrNumber".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt > val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqNumber1LeEqNumber2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrNumber1LeEqNumber2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt > val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqNumber1LeNumber2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrNumber1LeNumber2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt > val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                                //Text
                                            } else if ("Equal".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt == val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("NEqText".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt != val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("LeEqText".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt <= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("LeText".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt < val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqText".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrText".equals(elementSetsBean.getSign())) {
                                                int val = Integer.parseInt(elementSetsBean.getVal());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt > val && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqText1LeEqText2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrText1LeEqText2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt > val1 && edTextInt <= val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrEqText1LeText2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                                if (edTextInt >= val1 && edTextInt < val2 && changeListBeans != null && changeListBeans.size() > 0) {
                                                    ValSetView(changeListBeans);
                                                }
                                            } else if ("GrText1LeText2".equals(elementSetsBean.getSign())) {
                                                int val1 = Integer.parseInt(elementSetsBean.getVal());
                                                int val2 = Integer.parseInt(elementSetsBean.getVal2());
                                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
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
                                    if ("ContainsText".equals(elementSetsBean.getSign())) {
                                        String val = elementSetsBean.getVal();
                                        List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                        if (edTextStr.contains(val) && changeListBeans != null && changeListBeans.size() > 0) {
                                            ValSetView(changeListBeans);
                                        }
                                    } else if ("NContainsText".equals(elementSetsBean.getSign())) {
                                        String val = elementSetsBean.getVal();
                                        List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                        if (!edTextStr.contains(val) && changeListBeans != null && changeListBeans.size() > 0) {
                                            ValSetView(changeListBeans);
                                        }
                                    }
                                }
                            }

                        } else {
                            if ("EqEmptyText".equals(elementSetsBean.getSign())) {
                                List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans = elementSetsBean.getChangeList();
                                if (changeListBeans != null && changeListBeans.size() > 0) {
                                    ValSetView(changeListBeans);
                                }
                            }
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

                        if ("true".equals(isChecked) || "false".equals(isChecked)) {
                            int changeScore = Integer.parseInt(elementIdtoFormName.get(viewElementId).split("\\^")[1]);
                            CheckBox checkBox = (CheckBox) viewHashMap.get(viewElementId);
                            if (checkBox.isChecked()) {
                                score = score + changeScore;
                            } else {
                                score = score - changeScore;
                            }
                        } else if ("drop".equals(isChecked)) {
                            Integer[] scoreInt = dropValue.get(viewElementId);
                            score = score - scoreInt[0] + scoreInt[1];
                        }

                        editText.setText(String.valueOf(score));
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

    /**
     * check drop 控制 edit text 显隐编辑点击
     *
     * @param changeListBean
     * @param linearLayout
     */
    private void setEditTextorTextView(ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean changeListBean, LinearLayout linearLayout) {
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

                String type = changeListBean.getType();
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
                    }
                    linearLayout.setVisibility(View.GONE);
                }
            }
        } else {
            //控制一组元素
            List<String> childElementIdList = pcViewHashMap.get(changeListBean.getId());
            if (childElementIdList != null && childElementIdList.size() > 0) {
                for (int i2 = 0; i2 < childElementIdList.size(); i2++) {
                    if (viewHashMap.get(childElementIdList.get(i2)) instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) viewHashMap.get(childElementIdList.get(i2));
                        String type = changeListBean.getType();

                        if (type.contains("Enable") || type.contains("DisEnable")) {
                            if (checkBox != null) {
                                checkBox.setClickable(type.contains("Enable"));
                            }
                        }

                        LinearLayout linearLayoutChild = (LinearLayout) viewHashMap.get(childElementIdList.get(i2) + "_ll");
                        if (type.contains("Show")) {
                            if (linearLayoutChild != null) {
                                linearLayoutChild.setVisibility(View.VISIBLE);
                            }
                        } else if (type.contains("Hide")) {
                            if (checkBox != null) {
                                checkBox.setChecked(false);
                            }
                            if (linearLayoutChild != null) {
                                linearLayoutChild.setVisibility(View.GONE);
                            }
                        }
                    } else if (viewHashMap.get(childElementIdList.get(i2)) instanceof EditText) {
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

    /**
     * 分数控制view选中 赋值
     *
     * @param changeListBeans
     */
    public void ValSetView(List<ElementDataBean.DataBean.InputBean.ElementSetsBean.ChangeListBean> changeListBeans) {
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
}
