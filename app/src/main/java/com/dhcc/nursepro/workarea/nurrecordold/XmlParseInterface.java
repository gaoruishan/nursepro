package com.dhcc.nursepro.workarea.nurrecordold;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.adapter.CommList;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ItemConfigbyEmrCodeBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ScoreStatisticsBean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@SuppressLint("UseSparseArrays")
public class XmlParseInterface implements Serializable {
    private static final int handle_SaveOk = 4;
    private final static int DATE_DIALOG = 0;
    private final static int TIME_DIALOG = 1;
    public Map<String, String> SingleEdit = new HashMap<>(); // 单选使用编辑
    public Map<String, String> SingleData = new HashMap<>(); // 单选使用编辑
    public Map<String, String> RadioData = new HashMap<>();
    public Map<String, String> QtData = new HashMap<>();
    public Map<String, String> MultiRadio = new HashMap<>(); // 空件对象//多选单选使用
    public Map<String, String> RadioFenZhi = new HashMap<>(); // 空件对象//计算分值
    public Integer SumNum = 0; // 分值
    public String FenZhiCode = "";
    public String LevelCode = ""; // 分级Item13
    public Map<String, Object> MultiData = new HashMap<>(); // 空件对象
    public Map<String, Object> OBJBlankItm = new HashMap<>(); // 生命体征空白栏
    public Map<String, Object> PatRelItm = new HashMap<>(); // 空件对象
    public Map<String, Object> PatIn = new HashMap<>(); // 空件对象
    public Map<String, Object> CNHtb = new HashMap<>(); // 空件对象
    // 单选数据关联 针对跌倒，压疮
    public Map<String, Object> CNHLB = new HashMap<>(); // 控件名称
    // 单选数据关联 针对跌倒，压疮
    public Map<String, Object> CNHVal = new HashMap<>(); // 控件对应值
    public String RecId = "";
    public String EmrCode = "";
    public String EpisodeID = ""; // 腕带
    public Handler handler = null;
    public String RetData = null;
    public List<ItemConfigbyEmrCodeBean.ItemSetListBean> itemSetList = new ArrayList<>();
    public List<ScoreStatisticsBean> scoreStatistics = new ArrayList<>();
    ArrayList<Integer> MultiChoiceID = new ArrayList<Integer>();// 记录多选选中的id号
    Map<Integer, Object> AdapterArr = new HashMap<Integer, Object>();
    Map<String, String> ConV = new HashMap<String, String>();
    Map<Integer, String> ConRel = new HashMap<Integer, String>();
    Map<String, Object> SelItm = new HashMap<String, Object>();
    Context mContext;
    private List<CharSequence> sData = null;
    private ArrayAdapter<CharSequence> sAd = null;
    private int mSingleChoiceID = -1;
    private Calendar c = null;
    private ListView lv = null;
    private Map<String, Integer> SingleSel = new HashMap<String, Integer>();
    private Element dataitmnod = null;

    private AbsoluteLayout.LayoutParams getlayparam(int w, int h, int x, int y) {
        AbsoluteLayout.LayoutParams lp0 = new AbsoluteLayout.LayoutParams(w, h,
                x, y);
        return lp0;

    }


    public void DocParseXml(String xml, final Context context,
                            AbsoluteLayout abslayout) throws DocumentException {

        Document doc;
        try {
            mContext = context;
            doc = DocumentHelper.parseText(xml);

            SpannableString msp = null;

            Element root = doc.getRootElement();// 指向根节点
            // normal解析
            Element instancenod = root.element("InstanceData");
            final Element meddatanod = root.element("MetaData");
            List<Element> listChild = instancenod.elements();// 所有的Item节点
            List<Element> listChildGroup = new ArrayList();//group节点暂存

            Iterator<Element> it = listChild.iterator();
            while (it.hasNext()) {
                Element element = it.next(); // next() 返回下一个元素
                if (element.attributeValue("type") != null && element.attributeValue("type").equals("System.Windows.Forms.GroupBox")) {
                    listChildGroup.add(element);
                    it.remove();// remove() 移除元素
                }
            }

            listChildGroup.addAll(listChild);
            listChild = listChildGroup;

            for (int i = 0; i < listChild.size(); i++) {
                final Element nod = listChild.get(i);
                if (nod.attribute("type") == null)
                    continue;
                Element metaNod = null;
                if (meddatanod.element(nod.getName()) != null)
                    metaNod = meddatanod.element(nod.getName());
                String RelName = "";
                String Pos = "";
                if (metaNod != null) {
                    if (metaNod.attributeValue("RelName") != "") {
                        RelName = metaNod.attributeValue("RelName");
                        Pos = metaNod.attributeValue("Pos");
                    }
                }
                dataitmnod = null;
                String contyp = nod.attributeValue("type");
                String txtstr = nod.attributeValue("text");
                String left = nod.attributeValue("left");
                String top = nod.attributeValue("top");
                String width = nod.attributeValue("width");
                String height = nod.attributeValue("height");
                String tabindex = nod.attributeValue("tabindex");
                String fontsize = nod.attributeValue("fontsize");
                String fontunit = nod.attributeValue("fontunit");
                int Ileft = ConvertUtils.dp2px(Float.parseFloat(left));
                int Itop = ConvertUtils.dp2px(Float.parseFloat(top));
                int Iwidth = ConvertUtils.dp2px(Float.parseFloat(width)) + 10;
                int Iheight = ConvertUtils.dp2px(Float.parseFloat(height)) - 5;
                final int fontsz = Integer.valueOf(fontsize.trim());
                String CName = nod.getName();
                if (RelName != "")
                    CName = RelName;
                CNHVal.put(CName, txtstr);
                if (RecId.equals("")) {
                    if (PatRelItm.containsKey(CName)) {
                        if (PatIn.get(PatRelItm.get(CName)) != null)
                            txtstr = (String) PatIn.get(PatRelItm.get(CName));

                    }
                } else {
                    if (PatIn.containsKey(CName)) {
                        if (PatIn.get(CName) != null)
                            txtstr = PatIn.get(CName).toString();
                    }

                }

                CNHLB.put(CName, nod.getName());
                String a = CName;
                String b = nod.getName();
                if (contyp.equals("System.Windows.Forms.Label")) {
                    TextView lb = new TextView(context);
                    if (OBJBlankItm != null) {
                        if (OBJBlankItm.containsKey(txtstr)) {
                            CNHtb.put(OBJBlankItm.get(txtstr).toString(), lb);
                        }
                    }
                    lb.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));

                    if (fontunit.equals("World")) {
                        lb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        lb.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }
                    lb.setTextIsSelectable(true);
                    lb.setText(txtstr);
                    abslayout.addView(lb,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                }

                if (contyp.equals("System.Windows.Forms.GroupBox")) {
                    LinearLayout llGroup = new LinearLayout(context);
                    llGroup.setBackgroundResource(R.drawable.nur_record_group_bg);
                    abslayout.addView(llGroup,
                            getlayparam(Iwidth, Iheight, Ileft, Itop));
                }

                if (contyp.equals("DesignForm.QVline")) {
                    View vLine = new LinearLayout(context);
                    vLine.setBackgroundColor(Color.parseColor("#DADADA"));
                    abslayout.addView(vLine,
                            getlayparam(ConvertUtils.dp2px(1), Iheight, Ileft, Itop));
                }

                if (contyp.equals("DesignForm.QHLine")) {
                    View hLine = new LinearLayout(context);
                    hLine.setBackgroundColor(Color.parseColor("#DADADA"));
                    abslayout.addView(hLine,
                            getlayparam(Iwidth, ConvertUtils.dp2px(1), Ileft, Itop));
                }

                if ((nod.getName().substring(0, 1).equals("C"))) {
                    TextView btn = new TextView(context);
                    if (CName.contains("btn") || CName.contains("but")) {
                        btn.setBackgroundResource(R.drawable.nur_record_btn_bg_selector);
                    } else {
                        btn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    }
                    btn.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    btn.setGravity(Gravity.CENTER);
                    btn.setTag(CName);

                    if (fontunit.equals("World")) {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    btn.setText(txtstr);

                    abslayout.addView(btn,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));

                }
                if ((nod.getName().substring(0, 1).equals("D"))) {
                    final TextView dbtn = new TextView(context);
                    String dflag = metaNod.attributeValue("DateFlag");
                    String tflag = metaNod.attributeValue("TimeFlag");
                    dbtn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    dbtn.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    dbtn.setGravity(Gravity.CENTER);

                    dbtn.setTag(CName);
                    if (fontunit.equals("World")) {
                        dbtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        dbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }
                    CNHtb.put(CName, dbtn);
                    if (dflag.equals("Y")) {
                        dbtn.setText(txtstr);
                        CNHVal.put(CName, dbtn.getText());
                        dbtn.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                ShowDateTime(DATE_DIALOG, context, dbtn);
                            }
                        });

                    }
                    if (tflag.equals("Y")) {
                        dbtn.setText(txtstr);
                        CNHVal.put(CName, dbtn.getText());
                        dbtn.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                ShowDateTime(TIME_DIALOG, context, dbtn);
                            }
                        });
                    }
                    abslayout.addView(dbtn,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));

                }
                if ((nod.getName().substring(0, 1).equals("S") || (nod
                        .getName().substring(0, 1).equals("G")))) {
                    final EditText txt = new EditText(context);

                    if (nod.getName().substring(0, 1).equals("G")) {
                        txt.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
                                        EditText sp = (EditText) CNHtb.get(txt.getTag());
                                        ShowEdit(sp, context);
                                        return true;
                                    default:
                                        return true;
                                }
                            }
                        });
                    }

                    txt.setBackgroundResource(R.drawable.nur_record_input_bg);
                    txt.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    txt.setGravity(Gravity.CENTER);
                    if (fontunit.equals("World")) {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    for (int j = 0; j < itemSetList.size(); j++) {
                        ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
                        if (itemSetListBean.getLinkType().equals("5") && CName.equals(itemSetListBean.getItemCode())) {
                            txt.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                    }

                    txt.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            txt.setBackgroundResource(R.drawable.nur_record_input_bg);
                        }
                    });

                    txt.setText(txtstr);


                    abslayout.addView(txt,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    txt.setTag(CName);

                    CNHVal.put(CName, txtstr);
                    CNHtb.put(CName, txt);

                }
                if ((nod.getName().substring(0, 1).equals("M"))) {

                    final TextView btn5 = new TextView(context);

                    if (!txtstr.equals(""))
                        btn5.setText(getMultiVal(txtstr));
                    else
                        btn5.setText("请选择");

                    for (int j = 0; j < itemSetList.size(); j++) {
                        ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
                        if (itemSetListBean.getLinkType().equals("2") && CName.equals(itemSetListBean.getItemCode())) {
                            ScoreStatisticsBean scoreStatisticsBean = new ScoreStatisticsBean(itemSetListBean.getItemCode(), itemSetListBean.getLinkCode());
                            String[] txtstrr = {};
                            if (!txtstr.equals("")) {
                                txtstrr = getMultiVal(txtstr).split(",");
                            }
                            if (txtstrr.length > 0) {
                                int score = 0;
                                List itmlist = meddatanod.element(nod.getName()).element("Itms").elements();
                                for (int k = 0; k < itmlist.size(); k++) {
                                    Element nodx = (Element) itmlist.get(k);
                                    String[] noditm = nodx.getStringValue().split("\\|");
                                    for (int l = 0; l < txtstrr.length; l++) {
                                        if (txtstrr[l].equals(noditm[0]) && noditm.length > 1) {
                                            score = score + Integer.valueOf(noditm[1].trim());
                                        }
                                    }
                                }
                                scoreStatisticsBean.setScoreNew(score + "");
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                        }
                    }

                    btn5.setTag(CName);
                    btn5.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn5.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    btn5.setGravity(Gravity.CENTER);

                    if (fontunit.equals("World")) {
                        btn5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn5.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }


                    CNHtb.put(CName, btn5);
                    IniMultiData(meddatanod.element(nod.getName()), CName,
                            txtstr);
                    if (RadioFenZhi != null) {
                        if (RadioFenZhi.containsKey(CName))
                            initimultiRadio(meddatanod.element(nod.getName()),
                                    CName, txtstr);
                    }
                    abslayout.addView(btn5,
                            getlayparam(Iwidth, Iheight, Ileft, Itop));
                    btn5.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Element itmnod = meddatanod.element(nod.getName());
                            String[] m_Items;
                            if (itmnod.element("Itms") != null) {
                                if (MultiRadio != null) {
                                    if (MultiRadio.containsKey(btn5.getTag())) {
                                        ShowRadio(itmnod, context, btn5);
                                    } else {
                                        showDialog(itmnod, 1, context, btn5);
                                    }
                                } else {
                                    showDialog(itmnod, 1, context, btn5);
                                }
                            }

                            return;
                        }
                    });

                }
                if ((nod.getName().substring(0, 1).equals("R"))) {

                    final TextView btn6 = new TextView(context);

                    if (txtstr.replaceAll(";", "").equals("")) {
                        btn6.setText("请选择");
                    } else {
                        btn6.setText(getRadioVal(txtstr));
                    }

                    for (int j = 0; j < itemSetList.size(); j++) {
                        ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
                        if (itemSetListBean.getLinkType().equals("2") && CName.equals(itemSetListBean.getItemCode())) {
                            ScoreStatisticsBean scoreStatisticsBean = new ScoreStatisticsBean(itemSetListBean.getItemCode(), itemSetListBean.getLinkCode());
                            String txtstrr = txtstr.replace(";", "");
                            if (!txtstrr.equals("")) {
                                List itmlist = meddatanod.element(nod.getName()).element("Itms").elements();
                                for (int k = 0; k < itmlist.size(); k++) {
                                    Element nodx = (Element) itmlist.get(k);
                                    String[] noditm = nodx.getStringValue().split("\\|");
                                    if (noditm[0].equals(txtstrr)) {
                                        scoreStatisticsBean.setScoreNew(noditm[1].trim());
                                        break;
                                    }
                                }
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                        }
                    }


                    if (fontunit.equals("World")) {
                        btn6.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn6.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    btn6.setTag(CName);
                    btn6.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn6.setTextColor(android.graphics.Color.parseColor("#ff000000"));
                    btn6.setGravity(Gravity.CENTER);

                    CNHtb.put(CName, btn6);
                    IniMultiData(meddatanod.element(nod.getName()), CName, txtstr);
                    abslayout.addView(btn6, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn6.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            Element itmnod = meddatanod.element(nod.getName());

                            if (itmnod.element("Itms") != null) {
                                String code = IfConRadio(btn6.getTag().toString());

                                if (!code.equals("")) {
                                    dataitmnod = meddatanod.element(code); // 关联数据Radio
                                } else {
                                    dataitmnod = null;
                                }
                                if (dataitmnod == null) {
                                    ShowRadio(itmnod, context, btn6);
                                } else
                                    showMDialog(itmnod, context, btn6);
                            }

                        }
                    });
                }
                if ((nod.getName().substring(0, 1).equals("O"))) {

                    final TextView btn = new TextView(context);

                    if (txtstr.replaceAll("!", "").equals("")) {
                        btn.setText("请选择");
                    } else {
                        btn.setText(txtstr.split("!")[0]);
                    }

                    for (int j = 0; j < itemSetList.size(); j++) {
                        ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
                        if (itemSetListBean.getLinkType().equals("2") && CName.equals(itemSetListBean.getItemCode())) {
                            ScoreStatisticsBean scoreStatisticsBean = new ScoreStatisticsBean(itemSetListBean.getItemCode(), itemSetListBean.getLinkCode());
                            String txtstrr = txtstr.replace("!", "");
                            if (!txtstrr.equals("")) {
                                List itmlist = meddatanod.element(nod.getName()).element("Itms").elements();
                                for (int k = 0; k < itmlist.size(); k++) {
                                    Element nodx = (Element) itmlist.get(k);
                                    String[] noditm = nodx.getStringValue().split("\\|");
                                    if (noditm[0].equals(txtstrr)) {
                                        scoreStatisticsBean.setScoreNew(noditm[1].trim());
                                        break;
                                    }
                                }
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                        }
                    }

                    if (fontunit.equals("World")) {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    btn.setTag(CName);
                    btn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn.setTextColor(android.graphics.Color.parseColor("#ff000000"));
                    btn.setGravity(Gravity.CENTER);

                    CNHVal.put(CName, CName + "|" + txtstr + "!" + txtstr);
                    CNHtb.put(CName, btn);
                    abslayout.addView(btn, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Element itmnod = meddatanod.element(nod.getName());
                            String[] m_Items;
                            if (itmnod.element("Itms") != null) {
                                String code = IfConRadio(btn.getTag().toString());

                                if (!code.equals("")) {
                                    dataitmnod = meddatanod.element(code); // 关联数据Radio
                                } else {
                                    dataitmnod = null;
                                }
                                if (dataitmnod == null) {
                                    ShowRadioDialog(itmnod, context, btn);
                                } else
                                    showMDialog(itmnod, context, btn);
                            }

                        }
                    });
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private String getRadioVal(String itm) {
        String ret = "";
        if (itm.equals("")) {
            return "";
        }
        String[] val = itm.split(";");
        for (int i = 0; i < val.length; i++) {
            if (!val[i].equals("")) {
                ret = val[i];
                break;
            }
        }
        return ret;
    }

    private void ShowEdit(final EditText sp, final Context context) {
        try {
            LayoutInflater factory = LayoutInflater.from(context);
            final View view = factory.inflate(R.layout.edcomm_item, null);// 获得自定义对话框

            AlertDialog.Builder dialog07 = new AlertDialog.Builder(context);
            dialog07.setTitle("编辑");
            dialog07.setView(view);
            final String cname = (String) sp.getTag();
            final EditText txt = view.findViewById(R.id.Desc);

            txt.setText(sp.getText());

            dialog07.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            sp.setText(txt.getText());

                        }
                    });
            dialog07.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                        }
                    });
            dialog07.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void ShowSpinSel(final Button sp, final Context context) {
        try {
            LayoutInflater factory = LayoutInflater.from(context);
            final View view = factory.inflate(R.layout.edcomm_item, null);// 获得自定义对话框

            AlertDialog.Builder dialog07 = new AlertDialog.Builder(context);
            dialog07.setTitle("编辑");
            dialog07.setView(view);
            final String cname = (String) sp.getTag();
            final EditText txt = view.findViewById(R.id.Desc);

            txt.setText(GetComVal(CNHVal.get(cname).toString()));
            txt.setFocusable(true);
            txt.setFocusableInTouchMode(true);
            txt.requestFocus();
            InputMethodManager manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
            if (manager != null)
                manager.showSoftInput(txt, 0);
            dialog07.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            CNHVal.put(cname, cname + "|" + txt.getText() + "!"
                                    + txt.getText());
                            sp.setText(txt.getText());
                            if (SingleEdit.containsKey(cname)) {
                                String val = SingleEdit.get(cname);
                                if (!val.equals(cname)) {
                                    String[] arr = val.split("\\^");
                                    int sel = -1;
                                    if (SingleSel.containsKey(cname))
                                        sel = SingleSel.get(cname);
                                    for (int i = 0; i < arr.length; i++) {
                                        if (sel == i) {
                                            if (SingleData.containsKey(arr[i]))
                                                SingleData.put(arr[i], txt
                                                        .getText().toString());

                                        }

                                    }

                                }
                            }

                        }
                    });
            dialog07.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                        }
                    });
            dialog07.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String GetComVal(String itm) {
        String ret = "";
        if (itm == "")
            return "";
        String[] aa = itm.split("\\|");
        String[] val = aa[1].split("!");
        if (val.length == 0)
            return "";
        ret = val[0];
        return ret;
    }

    private String getmultival(String itm) {
        if (itm == null)
            return "";
        String[] aa = itm.split(";");
        String ret = "";
        for (int i = 0; i < aa.length; i++) {
            if (!aa[i].trim().equals("")) {
                ret = aa[i];
            }
        }
        return ret;
    }

    private void ShowRadio(Element itmnod, final Context context,
                           final TextView Con) { // Multi
        try {
            List itmlist = itmnod.element("Itms").elements();
            final String comname = (String) Con.getTag();
            String sel = (String) PatIn.get(comname);
            sel = getmultival(sel);
            mSingleChoiceID = 0;
            final Map<Integer, String> FN = new HashMap<Integer, String>();
            final String[] m_Items = new String[itmlist.size() + 1];
            for (int j = 0; j < itmlist.size(); j++) {
                Element nodx = (Element) itmlist.get(j);
                String[] noditm = nodx.getStringValue().split("\\|");
                String itmnn = noditm[0];
                if (noditm.length > 1)
                    FN.put(j, noditm[1]); // 分值

                m_Items[j] = itmnn;
                if (m_Items[j].equals(sel))
                    mSingleChoiceID = j;
                // 设置选项

            }
            m_Items[itmlist.size()] = "";
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);

            localBuilder.setSingleChoiceItems(m_Items, mSingleChoiceID,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            mSingleChoiceID = whichButton;
                        }
                    });
            localBuilder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String itm = "";
                            String itmtxt = "";
                            String itmval = "";

                            for (int i = 0; i < (m_Items.length - 1); i++) {
                                String itmname = comname + "_" + (i + 1);
                                if (mSingleChoiceID == i) {
                                    itm = itm + itmname + "|" + m_Items[i]
                                            + "^";
                                } else {
                                    itm = itm + itmname + "|^";

                                }

                            }

                            CNHVal.put(comname, itm);
                            PatIn.put(comname, m_Items[mSingleChoiceID]);

                            Con.setText(m_Items[mSingleChoiceID]);


                            for (int i = 0; i < scoreStatistics.size(); i++) {
                                ScoreStatisticsBean scoreStatisticsBean = scoreStatistics.get(i);

                                if (comname.equals(scoreStatisticsBean.getViewCode())) {
                                    scoreStatistics.get(i).setScoreOld(scoreStatisticsBean.getScoreNew());
                                    if (m_Items[mSingleChoiceID].equals("")) {
                                        scoreStatistics.get(i).setScoreNew("0");
                                    } else {
                                        for (int j = 0; j < itmlist.size(); j++) {
                                            Element nodx = (Element) itmlist.get(j);
                                            String[] noditm = nodx.getStringValue().split("\\|");
                                            if (noditm[0].equals(m_Items[mSingleChoiceID]) && noditm.length > 1) {
                                                scoreStatistics.get(i).setScoreNew(noditm[1].trim());
                                                break;
                                            }
                                        }
                                    }

                                    TextView textView = (TextView) CNHtb.get(scoreStatisticsBean.getResultViewCode());
                                    String scoreStr = (String) CNHVal.get(scoreStatisticsBean.getResultViewCode());
                                    if (scoreStr.equals("")) {
                                        scoreStr = "0";
                                    }
                                    int scoreInt = Integer.valueOf(scoreStr) - Integer.valueOf(scoreStatistics.get(i).getScoreOld()) + Integer.valueOf(scoreStatistics.get(i).getScoreNew());
                                    scoreStr = scoreInt + "";
                                    textView.setText(scoreStr);
                                    CNHVal.put(scoreStatisticsBean.getResultViewCode(), scoreStr);

                                }
                            }

                        }
                    });
            localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

            AlertDialog localAlertDialog = localBuilder.create();
            localAlertDialog.setTitle("选择");
            // localAlertDialog.setCanceledOnTouchOutside(false);
            // localAlertDialog.getListView().setBackgroundColor(Color.WHITE);

            localAlertDialog.show();

            localAlertDialog.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String IfConRadio(String cname) {

        String ret = "";
        try {
            if (RadioData == null)
                return ret;
            Iterator iter = RadioData.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
            // /查询对饮的触发点
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                // Integer key = (Integer)entry.getKey(); //获得key
                String[] arr = entry.getValue().toString().split("\\^");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals(""))
                        continue;
                    if (cname.equals(arr[i])) {
                        ret = entry.getKey().toString();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private String ifcontaincode(String cname) { // 判断是否

        String ret = "";
        try {
            Iterator iter = MultiRadio.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
            // /查询对饮的触发点
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                // Integer key = (Integer)entry.getKey(); //获得key
                String[] arr = entry.getValue().toString().split("\\^");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals(""))
                        continue;
                    if (cname.equals(arr[i])) {
                        ret = entry.getKey().toString();
                        break;
                    }
                }
            }
            if (!ret.equals("")) {
                int sum = 0;
                if (ret.substring(0, 1).equals("I")) {
                    String[] arr = MultiRadio.get(ret).split("\\^");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equals(""))
                            continue;
                        String val = GetComVal(CNHVal.get(arr[i]).toString());
                        if (!val.equals("")) {
                            sum = sum + Integer.valueOf(val.trim());
                        }
                    }
                    EditText txt = (EditText) CNHtb.get(ret);
                    txt.setText(sum + "");
                }
                String[] arritm = RadioFenZhi.get(ret).split("\\^");
                for (int i = 0; i < arritm.length; i++) {
                    if (arritm[i].equals(""))
                        continue;
                    String[] itm = arritm[i].split("\\|");
                    if (itm[2].equals("D")) {
                        Button btn = (Button) CNHtb.get(itm[0]);
                        if (btn.getText().toString().equals(""))
                            btn.setText(itm[1]);

                    }
                    if (itm[2].equals("S")) {
                        EditText btn = (EditText) CNHtb.get(itm[0]);
                        if (btn.getText().toString().equals(""))
                            btn.setText(itm[1]);

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private void ShowRadioDialog(Element itmnod, final Context context,
                                 final TextView Con) {
        List itmlist = itmnod.element("Itms").elements();
        final String comname = (String) Con.getTag();
        String itmsel = (String) CNHVal.get(comname);
        String sel = GetComVal(itmsel);
        mSingleChoiceID = 0;
        final String[] m_Items = new String[itmlist.size() + 1];
        final String[] m_Items1 = new String[itmlist.size() + 1];
        final String[] m_Items2 = new String[itmlist.size() + 1];
        for (int j = 0; j < itmlist.size(); j++) {
            Element nodx = (Element) itmlist.get(j);
            m_Items[j] = nodx.getStringValue().split("\\|")[0];
            if (m_Items[j].equals(sel))
                mSingleChoiceID = j;
            // 设置选项

        }
        if (dataitmnod != null) { // 2013-09-23 qishaoe
            List nolist = dataitmnod.element("Itms").elements();
            for (int j = 1; j < nolist.size(); j++) {
                Element nodx = (Element) nolist.get(j);
                m_Items1[j - 1] = nodx.getStringValue();
                // 设置选项

            }
            m_Items1[nolist.size() - 1] = "/";

        }
        if (SingleEdit != null) {
            if (SingleEdit.containsKey(comname)) {
                String val = SingleEdit.get(comname);
                if (!val.equals(comname)) {
                    String[] arr = val.split("\\^");
                    for (int i = 0; i < arr.length; i++) {
                        if (SingleData.containsKey(arr[i]))
                            m_Items[i] = SingleData.get(arr[i]);
                    }

                }
            }
        }
        m_Items[itmlist.size()] = "";
        m_Items1[itmlist.size()] = "";

        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);

        if (dataitmnod != null) {
            System.arraycopy(m_Items1, 0, m_Items2, 0, m_Items1.length);
        } else {
            System.arraycopy(m_Items, 0, m_Items2, 0, m_Items.length);
        }
        localBuilder.setSingleChoiceItems(m_Items2, mSingleChoiceID,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mSingleChoiceID = whichButton;

                    }
                });
        localBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // String aa=(String)listadapter.getItem(which);
                        // String bb=aa;

                        CNHVal.put(comname, comname + "|"
                                + m_Items[mSingleChoiceID] + "!"
                                + m_Items[mSingleChoiceID]);
                        //FIXME
                        Con.setText(m_Items[mSingleChoiceID].split("\\|")[0]);

                        for (int i = 0; i < scoreStatistics.size(); i++) {
                            ScoreStatisticsBean scoreStatisticsBean = scoreStatistics.get(i);

                            if (comname.equals(scoreStatisticsBean.getViewCode())) {
                                scoreStatistics.get(i).setScoreOld(scoreStatisticsBean.getScoreNew());
                                if (m_Items[mSingleChoiceID].equals("")) {
                                    scoreStatistics.get(i).setScoreNew("0");
                                } else {
                                    for (int j = 0; j < itmlist.size(); j++) {
                                        Element nodx = (Element) itmlist.get(j);
                                        String[] noditm = nodx.getStringValue().split("\\|");
                                        if (noditm[0].equals(m_Items[mSingleChoiceID]) && noditm.length > 1) {
                                            scoreStatistics.get(i).setScoreNew(noditm[1].trim());
                                            break;
                                        }
                                    }
                                }

                                TextView textView = (TextView) CNHtb.get(scoreStatisticsBean.getResultViewCode());
                                String scoreStr = (String) CNHVal.get(scoreStatisticsBean.getResultViewCode());
                                if (scoreStr.equals("")) {
                                    scoreStr = "0";
                                }
                                int scoreInt = Integer.valueOf(scoreStr) - Integer.valueOf(scoreStatistics.get(i).getScoreOld()) + Integer.valueOf(scoreStatistics.get(i).getScoreNew());
                                scoreStr = scoreInt + "";
                                textView.setText(scoreStr);
                                CNHVal.put(scoreStatisticsBean.getResultViewCode(), scoreStr);

                            }
                        }

                        if (SingleEdit != null) {
                            if (SingleEdit.containsKey(comname)) {
                                SingleSel.put(comname, mSingleChoiceID);
                                String val = SingleEdit.get(comname);

                                if (!val.equals(comname)) {
                                    String[] arr = val.split("\\^");
                                    for (int i = 0; i < arr.length; i++) {
                                        if (i == mSingleChoiceID) {
                                            SingleData.put(arr[i], m_Items[i]);
                                            // 赋值
                                        }
                                    }

                                }
                            }
                        }
                        if (MultiRadio != null) {
                            ifcontaincode(comname);
                        }

                    }
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    private void IniMultiData(Element itmnod, String Cname, String txtstr) {
        List itmlist = itmnod.element("Itms").elements();
        List<CharSequence> Data = new ArrayList<CharSequence>();
        String[] SelItm = txtstr.split("\\;");
        Map<String, Object> MSelItm = new HashMap<String, Object>();
        String itm = "";
        for (int i = 0; i < SelItm.length; i++) {
            if (SelItm[i].equals(""))
                continue;
            MSelItm.put(SelItm[i], "");
        }
        for (int j = 0; j < itmlist.size(); j++) {
            Element nodx = (Element) itmlist.get(j);
            String[] noditm = nodx.getStringValue().split("\\|");
            String itmnn = noditm[0];
            Data.add(itmnn);
            String itmname = Cname + "_" + (j + 1);
            if (MSelItm.containsKey(itmnn)) {
                itm = itm + itmname + "|" + itmnn + "^";
            } else {
                itm = itm + itmname + "|^";
            }

        }
        CNHVal.put(Cname, itm);

    }

    private void initimultiRadio(Element itmnod, String Cname, String txtstr) {
        List itmlist = itmnod.element("Itms").elements();
        String sel = (String) PatIn.get(Cname);
        sel = getmultival(sel);
        final String[] m_Items = new String[itmlist.size() + 1];
        for (int j = 0; j < itmlist.size(); j++) {
            Element nodx = (Element) itmlist.get(j);
            String[] noditm = nodx.getStringValue().split("\\|");
            String itmnn = noditm[0];
            m_Items[j] = itmnn;
            if (m_Items[j].equals(sel)) {
                RadioFenZhi.put(Cname, noditm[1]);
            }
            // 设置选项

        }
    }

    public void showDialog(Element itmnod, final int classesId,
                           final Context context, final TextView Con) {

        List itmlist = itmnod.element("Itms").elements();

        String[] m_Items = new String[itmlist.size() + 1];
        boolean[] flags = new boolean[itmlist.size() + 1];
        String cname = (String) Con.getTag();
        SelItm.clear();
        if (PatIn.containsKey(cname)) {
            String[] selitm = PatIn.get(cname).toString().split(";");
            for (int i = 0; i < selitm.length; i++) {
                if (selitm[i].equals(""))
                    continue;
                SelItm.put(selitm[i], "");
            }
        }

        // 定义复选框选项
        final String[] choiceItems = m_Items;
        // 复选框默认值：false=未选;true=选中
        final boolean[] tempStatus = new boolean[flags.length];
        System.arraycopy(flags, 0, tempStatus, 0, flags.length);// 此处必须深复制
        final boolean[] sourceStatus = new boolean[flags.length];
        System.arraycopy(flags, 0, sourceStatus, 0, flags.length);// 此处必须深复制
        MultiChoiceID.clear();
        AdapterArr.clear();

        sData = new ArrayList<CharSequence>();
        for (int j = 0; j < itmlist.size(); j++) {
            Element nodx = (Element) itmlist.get(j);
            String[] noditm = nodx.getStringValue().split("\\|");
            String itmnn = noditm[0];

            sData.add(itmnn);
            m_Items[j] = itmnn;
            // 设置选项
            if (SelItm.containsKey(m_Items[j])) {
                tempStatus[j] = true;
                AdapterArr.put(j, m_Items[j]);
                MultiChoiceID.add(j);
            }

        }
        m_Items[m_Items.length - 1] = "全选";

        AlertDialog ad = new AlertDialog.Builder(context)
                .setMultiChoiceItems(choiceItems, tempStatus,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton, boolean isChecked) {

                                if (isChecked) {
                                    if (whichButton == (choiceItems.length - 1)) {
                                        for (int i = 0; i < choiceItems.length; i++) {
                                            lv.setItemChecked(i, true);
                                            MultiChoiceID.add(i);
                                            AdapterArr.put(i, choiceItems[i]);

                                        }

                                    } else {
                                        MultiChoiceID.add(whichButton);
                                        AdapterArr.put(whichButton,
                                                choiceItems[whichButton]);
                                    }

                                } else {
                                    if (whichButton == (choiceItems.length - 1)) {
                                        AdapterArr.clear();
                                        lv.clearChoices();
                                        for (int i = 0; i < choiceItems.length; i++) {
                                            if (lv.isItemChecked(i)) {
                                                lv.setItemChecked(i, false);
                                            } else {
                                                lv.setItemChecked(i, false);
                                            }
                                        }

                                    } else {
                                        AdapterArr.remove(whichButton);
                                    }

                                }

                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itm = "";
                        String itmtxt = "";
                        String gtag = (String) Con.getTag();
                        itm = "";
                        String itmval = "";

                        for (int i = 0; i < (choiceItems.length - 1); i++) {
                            String itmname = gtag + "_" + (i + 1);
                            if (lv.getCheckedItemPositions().get(i)) {
                                itm = itm + itmname + "|" + sData.get(i) + "^";
                                itmtxt = itmtxt + sData.get(i) + ",";
                                itmval = itmval + sData.get(i) + ";";
                            } else {
                                itm = itm + itmname + "|^";
                                itmval = itmval + ";";

                            }

                        }

                        if (CNHLB.containsKey(gtag)) {
                            // String cname=(String)CNHLB.get(gtag);
                            CNHVal.put(gtag, itm);
                            PatIn.put(gtag, itmval);
                            Con.setText(getMultiTxt(itmtxt));
                        }

                        for (int i = 0; i < scoreStatistics.size(); i++) {
                            ScoreStatisticsBean scoreStatisticsBean = scoreStatistics.get(i);

                            if (gtag.equals(scoreStatisticsBean.getViewCode())) {
                                scoreStatistics.get(i).setScoreOld(scoreStatisticsBean.getScoreNew());

                                if (itmtxt.equals("")) {
                                    scoreStatistics.get(i).setScoreNew("0");
                                } else {
                                    String[] txtstrr = itmtxt.split(",");
                                    int score = 0;
                                    List itmlist = itmnod.element("Itms").elements();
                                    for (int k = 0; k < itmlist.size(); k++) {
                                        Element nodx = (Element) itmlist.get(k);
                                        String[] noditm = nodx.getStringValue().split("\\|");
                                        for (int l = 0; l < txtstrr.length; l++) {
                                            if (txtstrr[l].equals(noditm[0]) && noditm.length > 1) {
                                                score = score + Integer.valueOf(noditm[1].trim());
                                            }
                                        }
                                    }
                                    scoreStatistics.get(i).setScoreNew(score + "");
                                }

                                TextView textView = (TextView) CNHtb.get(scoreStatisticsBean.getResultViewCode());
                                String scoreStr = (String) CNHVal.get(scoreStatisticsBean.getResultViewCode());
                                if (scoreStr.equals("")) {
                                    scoreStr = "0";
                                }
                                int scoreInt = Integer.valueOf(scoreStr) - Integer.valueOf(scoreStatistics.get(i).getScoreOld()) + Integer.valueOf(scoreStatistics.get(i).getScoreNew());
                                scoreStr = scoreInt + "";
                                textView.setText(scoreStr);
                                CNHVal.put(scoreStatisticsBean.getResultViewCode(), scoreStr);

                            }
                        }
                    }
                }).setNegativeButton("取消", null).create();
        lv = ad.getListView();
        ad.show();

    }

    public void showMDialog(Element itmnod, final Context context,
                            final TextView Con) {

        List itmlist = itmnod.element("Itms").elements();

        String[] m_Items = new String[itmlist.size() + 1];
        final String[] m_Items1 = new String[itmlist.size() + 1];
        final String[] m_Items2 = new String[itmlist.size() + 1];

        boolean[] flags = new boolean[itmlist.size() + 1];
        String cname = (String) Con.getTag();

        String itmsel = (String) CNHVal.get(cname);
        String sel = GetComVal(itmsel);

        SelItm.clear();
        if (PatIn.containsKey(cname)) {
            String[] selitm = PatIn.get(cname).toString().split(";");
            for (int i = 0; i < selitm.length; i++) {
                if (selitm[i].equals(""))
                    continue;
                SelItm.put(selitm[i], "");
            }
        }

        // 定义复选框选项
        final String[] choiceItems = m_Items;
        // 复选框默认值：false=未选;true=选中
        final boolean[] tempStatus = new boolean[flags.length];
        System.arraycopy(flags, 0, tempStatus, 0, flags.length);// 此处必须深复制
        final boolean[] sourceStatus = new boolean[flags.length];
        System.arraycopy(flags, 0, sourceStatus, 0, flags.length);// 此处必须深复制
        MultiChoiceID.clear();
        AdapterArr.clear();

        sData = new ArrayList<>();
        for (int j = 0; j < itmlist.size(); j++) {
            Element nodx = (Element) itmlist.get(j);
            String[] noditm = nodx.getStringValue().split("\\|");
            String itmnn = noditm[0];

            sData.add(itmnn);
            m_Items[j] = itmnn;
            // 设置选项
            if (SelItm.containsKey(m_Items[j])) {
                tempStatus[j] = true;
                AdapterArr.put(j, m_Items[j]);
                MultiChoiceID.add(j);
            } else {
                String itm = SelItm.toString();
                if (itm.contains(m_Items[j])) {
                    tempStatus[j] = true;
                    AdapterArr.put(j, m_Items[j]);
                    MultiChoiceID.add(j);
                }
            }

        }

        if (dataitmnod != null) { // 2013-09-23 qishaoe
            List nolist = dataitmnod.element("Itms").elements();
            String nodname = dataitmnod.getName();
            for (int j = 1; j < nolist.size(); j++) {
                Element nodx = (Element) nolist.get(j);
                m_Items1[j - 1] = nodx.getStringValue();
                String nodval = nodx.getStringValue();
                String itm = "";
                String itmtxt = "";
                if (QtData != null) {
                    Iterator iter = QtData.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                        String key = (String) entry.getKey(); // 获得key
                        String[] arr = key.split("\\|");
                        itm = entry.getValue().toString();
                        if (arr[0].equals(nodname)) // A. "O280|A."
                        {
                            if (arr[1].equals(nodval)) {
                                if (SingleData.containsKey(itm)) {
                                    m_Items1[j - 1] = arr[1]
                                            + SingleData.get(itm);
                                }
                            }
                        }

                    }
                }

            }
            m_Items1[nolist.size() - 1] = "/";

        }
        m_Items[m_Items.length - 1] = "全选";
        m_Items1[m_Items.length - 1] = "全选";

        if (dataitmnod != null) {
            System.arraycopy(m_Items1, 0, m_Items2, 0, m_Items1.length);
        } else {
            System.arraycopy(m_Items, 0, m_Items2, 0, m_Items.length);
        }

        AlertDialog ad = new AlertDialog.Builder(context)
                .setMultiChoiceItems(m_Items2, tempStatus,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton, boolean isChecked) {

                                if (isChecked) {
                                    if (whichButton == (choiceItems.length - 1)) {
                                        for (int i = 0; i < choiceItems.length; i++) {
                                            lv.setItemChecked(i, true);
                                            MultiChoiceID.add(i);
                                            AdapterArr.put(i, choiceItems[i]);

                                        }

                                    } else {
                                        MultiChoiceID.add(whichButton);
                                        AdapterArr.put(whichButton,
                                                choiceItems[whichButton]);
                                    }

                                } else {
                                    if (whichButton == (choiceItems.length - 1)) {
                                        AdapterArr.clear();
                                        lv.clearChoices();
                                        for (int i = 0; i < choiceItems.length; i++) {
                                            if (lv.isItemChecked(i)) {
                                                lv.setItemChecked(i, false);
                                            } else {
                                                lv.setItemChecked(i, false);
                                            }
                                        }

                                    } else {
                                        AdapterArr.remove(whichButton);
                                    }

                                }

                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itm = "";
                        String itmtxt = "";
                        String gtag = (String) Con.getTag();
                        itm = "";
                        String itmval = "";

                        for (int i = 0; i < (choiceItems.length - 1); i++) {
                            String itmname = gtag + "_" + (i + 1);
                            if (lv.getCheckedItemPositions().get(i)) {
                                itm = itm + itmname + "|" + sData.get(i) + "^";
                                itmtxt = itmtxt + sData.get(i) + "";
                                itmval = itmval + sData.get(i) + "";
                            } else {
                                itm = itm + itmname + "|^";
                                itmval = itmval + "";

                            }

                        }

                        if (CNHLB.containsKey(gtag)) {
                            CNHVal.put(gtag, gtag + "|" + itmtxt + "!" + itmtxt);

                            PatIn.put(gtag, itmval);
                            Con.setText(itmtxt);
                        }
                        if (MultiRadio != null) {
                            ifcontaincode(gtag);
                        }
                    }
                }).setNegativeButton("取消", null).create();
        lv = ad.getListView();
        ad.show();

    }

    protected void ShowDateTime(int id, Context context, final TextView btn) {
        Dialog dialog = null;
        switch (id) {
            case DATE_DIALOG:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth) {

                                DecimalFormat df = new DecimalFormat("00");

                                String datestr = year + "-" + df.format(month + 1)
                                        + "-" + df.format(dayOfMonth);
                                btn.setText(datestr);
                                CNHVal.put((String) btn.getTag(), datestr);
                            }
                        }, c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                dialog.show();
                break;
            case TIME_DIALOG:
                c = Calendar.getInstance();
                dialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                DecimalFormat df = new DecimalFormat("00");
                                String timeStr = df.format(hourOfDay) + ":" + df.format(minute);
                                btn.setText(timeStr);
                                CNHVal.put((String) btn.getTag(), timeStr);
                            }
                        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                        false);
                dialog.show();
                break;
        }
    }

    private String getMultiVal(String stritm) {
        String[] itm = stritm.split(";");
        String ret = "";
        for (int i = 0; i < itm.length; i++) {
            if (itm[i].equals(""))
                continue;
            if ((i + 1) == itm.length)
                ret = ret + itm[i];
            else
                ret = ret + itm[i] + ",";
        }
        return ret;
    }

    private String getMultiTxt(String stritm) {
        String[] itm = stritm.split(",");
        String ret = "";
        for (int i = 0; i < itm.length; i++) {
            if (itm[i].equals(""))
                continue;
            if ((i + 1) == itm.length)
                ret = ret + itm[i];
            else
                ret = ret + itm[i] + ",";
        }
        return ret;
    }

    private void InitConVal() {
        Iterator iter = CNHVal.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
        String itm = "";
        String itmtxt = "";
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
            itm = itm + "^" + entry.getValue();
            itmtxt = itmtxt + entry.getValue() + ",";
        }

    }

    private Integer handleRelationVal(String EmrCode, Button button) {
        int number = 0;
        if (button != null) {
            String value = button.getText().toString().trim();
            if (("DHCNURPGD_rcshzlnlBARTHLEpf").equals(EmrCode)) {
                if (value.contains("-")) {
                    return 0;
                }
                if (value.contains("分")) {
                    value = value.substring(0, value.length() - 1);
                    try {
                        number = Integer.parseInt(value);
                        return number;
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }
            }
            if (("DHCNURXHPGD_YYSC").equals(EmrCode)) {
                if (value.contains("是")) {
                    return 1;
                }
            }
            if (("DHCNURPGDMorseDD").equals(EmrCode) ||
                    ("DHCNURPGD_ycfswxyslhpg").equals(EmrCode) ||
                    ("DHCNURPGD_ETYCFXPGLB").equals(EmrCode) ||
                    ("DHCNURPGD_HEDDZCFXZHPGB").equals(EmrCode)) {
                try {
                    number = Integer.parseInt(value);
                    return number;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }

        }
        return 0;
    }

    class PopupListener implements DialogInterface.OnClickListener {
        CommList mAdapter;

        public PopupListener(ListAdapter adapter) {
            mAdapter = (CommList) adapter;

        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Map itm = (Map) mAdapter.getItem(which);
        }
    }
}
