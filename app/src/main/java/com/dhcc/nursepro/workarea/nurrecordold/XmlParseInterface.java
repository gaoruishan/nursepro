package com.dhcc.nursepro.workarea.nurrecordold;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
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
import java.util.Arrays;
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
                final int fontsz = Integer.valueOf(fontsize);
                //                final int fontsz = ConvertUtils.dp2px(Float.parseFloat(fontsize)) - 3;
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
                if (contyp.equals("System.Windows.Forms.Label")) // (ReportDesign
                // == true)
                // &&
                {
                    TextView lb = new TextView(context);
                    if (OBJBlankItm != null) {
                        if (OBJBlankItm.containsKey(txtstr)) {
                            // CNHtb.put(CName, lb); ///设置空白栏
                            CNHtb.put(OBJBlankItm.get(txtstr).toString(), lb);
                        }
                    }
                    lb.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    // lb.setGravity(Gravity.CENTER_HORIZONTAL);
                    // lb.setTextSize(TypedValue.COMPLEX_UNIT_PX,22); //22像素
                    if (fontunit.equals("World")) {
                        lb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        lb.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }
                    lb.setText(txtstr);
                    abslayout.addView(lb,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                }

                if (contyp.equals("System.Windows.Forms.GroupBox")) {
                    LinearLayout llGroup = new LinearLayout(context);
                    llGroup.setBackgroundColor(context.getResources().getColor(R.color.nurrecordold_left_bgcolor));
                    abslayout.addView(llGroup,
                            getlayparam(Iwidth, Iheight, Ileft, Itop));
                }

                if ((nod.getName().substring(0, 1).equals("C"))) {
                    TextView btn = new TextView(context);
                    btn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn.setTextColor(android.graphics.Color
                            .parseColor("#ff000000"));
                    btn.setGravity(Gravity.CENTER);
                    btn.setTag(CName);
                    // CNHVal.put(CName, txtstr);
                    if (!Pos.equals(""))
                        CNHLB.put(CName, Pos);
                    // lb.setTextSize(TypedValue.COMPLEX_UNIT_PX,22); //22像素

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

                    // String tagv=(String)dbtn.getTag();
                    CNHtb.put(CName, dbtn);
                    // ConV.put(CName,txtstr);
                    // ConRel.put(i,CName);
                    if (dflag.equals("Y")) {
                        // else if (txtstr.equals(""))//dbtn.setText(
                        // mobilecom.GetDate("d"));
                        dbtn.setText(txtstr);
                        CNHVal.put(CName, dbtn.getText());
                        dbtn.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                ShowDateTime(DATE_DIALOG, context, dbtn);
                            }
                        });

                    }
                    if (tflag.equals("Y")) {
                        // elseif (txtstr.equals(""))dbtn.setText(
                        // mobilecom.GetDate("t"));
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
                    //                    txt.setOnClickListener(new OnClickListener() {
                    //                        public void onClick(View v) {
                    //                            // TODO Auto-generated method stub
                    //                            // MultiChoiceID.clear();
                    //                            try {
                    //                                EditText sp = (EditText) CNHtb.get(txt.getTag());
                    //                                ShowEdit(sp, context);
                    //                            } catch (Exception e) {
                    //
                    //                            }
                    //                            return;
                    //                        }
                    //                    });
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
                    txt.setSingleLine();
                    txt.setEllipsize(TextUtils.TruncateAt.END);
                    if (fontunit.equals("World")) {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }
                    txt.setText(txtstr);

                    abslayout.addView(txt,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    txt.setTag(CName);

                    // String tagv=(String)txt.getTag();
                    CNHVal.put(CName, txtstr);
                    CNHtb.put(CName, txt);
                    // ConV.put(CName,txtstr);
                    // ConRel.put(i,CName);

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
                                            score = score + Integer.valueOf(noditm[1]);
                                        }
                                    }
                                }
                                scoreStatisticsBean.setScoreNew(score + "");
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                            break;
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
                            // TODO Auto-generated method stub
                            // MultiChoiceID.clear();
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

                                // sDatas=itmlist;
                            }

                            return;
                        }
                    });

                }
                if ((nod.getName().substring(0, 1).equals("R"))) {
                    /**/
                    int SingEW = 0;
                    //                    if (SingleEdit != null) {
                    //                        if (SingleEdit.containsKey(CName)) {
                    //                            final TextView btn5 = new TextView(context);
                    //
                    //                            btn5.setText("E");
                    //                            btn5.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    //                            btn5.setTextColor(android.graphics.Color
                    //                                    .parseColor("#ff000000"));
                    //                            btn5.setGravity(Gravity.CENTER);
                    //
                    //                            btn5.setTag(CName);
                    //                            if (fontunit.equals("World")) {
                    //                                btn5.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
                    //                                        (fontsz));//
                    //                            } else {
                    //                                btn5.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                    //                                        (fontsz));//
                    //                            }
                    //
                    //                            SingEW = 40;
                    //                            // ConV.put(CName,txtstr);
                    //                            // if (!txtstr.equals(""))
                    //                            // btn5.setText(getMultiVal(txtstr));
                    //                            // ConRel.put(i,CName);
                    //                            // CNHtb.put(CName, btn5);
                    //                            // IniMultiData(meddatanod.element(nod.getName()),CName,txtstr);
                    //                            abslayout.addView(
                    //                                    btn5,
                    //                                    getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    //                            btn5.setOnClickListener(new OnClickListener() {
                    //                                public void onClick(View v) {
                    //                                    // TODO Auto-generated method stub
                    //                                    // MultiChoiceID.clear();
                    //                                    try {
                    //                                        Button sp = (Button) CNHtb.get(btn5.getTag());
                    //                                        ShowSpinSel(sp, context);
                    //                                    } catch (Exception e) {
                    //
                    //                                    }
                    //                                    return;
                    //                                }
                    //                            });
                    //                        }
                    //                    }

                    final TextView btn6 = new TextView(context);
                    // if (!txtstr.equals("")) btn.setText(txtstr); //by pan
                    // 20140519
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
                                        scoreStatisticsBean.setScoreNew(noditm[1]);
                                        break;
                                    }
                                }
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                            break;
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


                    //                    CNHVal.put(CName, CName + "|" + txtstr + "!" + txtstr);
                    // ConV.put(CName,txtstr);
                    // if (!txtstr.equals("")) btn.setText(txtstr); //by pan
                    // 20140519

                    // ConRel.put(i,CName);
                    CNHtb.put(CName, btn6);
                    IniMultiData(meddatanod.element(nod.getName()), CName, txtstr);
                    abslayout.addView(btn6, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn6.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            // MultiChoiceID.clear();
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
                                // sDatas=itmlist;
                            }

                        }
                    });
                }
                if ((nod.getName().substring(0, 1).equals("O"))) {
                    /**/
                    int SingEW = 0;
                    //                    if (SingleEdit != null) {
                    //                        if (SingleEdit.containsKey(CName)) {
                    //                            final TextView btn5 = new TextView(context);
                    //
                    //                            btn5.setText("E");
                    //                            btn5.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    //                            btn5.setTextColor(android.graphics.Color
                    //                                    .parseColor("#ff000000"));
                    //                            btn5.setGravity(Gravity.CENTER);
                    //
                    //                            btn5.setTag(CName);
                    //                            if (fontunit.equals("World")) {
                    //                                btn5.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
                    //                                        (fontsz));//
                    //                            } else {
                    //                                btn5.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                    //                                        (fontsz));//
                    //                            }
                    //
                    //                            SingEW = 40;
                    //                            // ConV.put(CName,txtstr);
                    //                            // if (!txtstr.equals(""))
                    //                            // btn5.setText(getMultiVal(txtstr));
                    //                            // ConRel.put(i,CName);
                    //                            // CNHtb.put(CName, btn5);
                    //                            // IniMultiData(meddatanod.element(nod.getName()),CName,txtstr);
                    //                            abslayout.addView(
                    //                                    btn5,
                    //                                    getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    //                            btn5.setOnClickListener(new OnClickListener() {
                    //                                public void onClick(View v) {
                    //                                    // TODO Auto-generated method stub
                    //                                    // MultiChoiceID.clear();
                    //                                    try {
                    //                                        Button sp = (Button) CNHtb.get(btn5
                    //                                                .getTag());
                    //                                        ShowSpinSel(sp, context);
                    //                                    } catch (Exception e) {
                    //
                    //                                    }
                    //                                    return;
                    //                                }
                    //                            });
                    //                        }
                    //                    }

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
                                        scoreStatisticsBean.setScoreNew(noditm[1]);
                                        break;
                                    }
                                }
                            }
                            scoreStatistics.add(scoreStatisticsBean);
                            break;
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
                    // IniMultiData(meddatanod.element(nod.getName()),CName,txtstr);
                    abslayout.addView(btn, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            // MultiChoiceID.clear();
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
                                // sDatas=itmlist;
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
            // dialog07.setIcon(R.drawable.qq);
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
                            // Toast.makeText(dialog_demo.this, "你选择了取消",
                            // Toast.LENGTH_LONG).show();
                            // showDialog("你选择了取消");
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
            // dialog07.setIcon(R.drawable.qq);
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
                            // Toast.makeText(dialog_demo.this, "你选择了取消",
                            // Toast.LENGTH_LONG).show();
                            // showDialog("你选择了取消");
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
            // String itmsel=(String) PatIn.get(comname);
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

            // / final CommList listadapter = new CommList(context,sData);
            localBuilder.setSingleChoiceItems(m_Items, mSingleChoiceID,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            mSingleChoiceID = whichButton;
                            // mSingleChoiceID = whichButton;
                            // showDialog("你选择的id为" + whichButton + " , " +
                            // m_Items[whichButton]);
                        }
                    });
            // localBuilder.setAdapter(listadapter, new
            // PopupListener(listadapter));
            localBuilder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // String aa=(String)listadapter.getItem(which);
                            // String bb=aa;
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
                            if (RadioFenZhi != null) {
                                if (FN.containsKey(mSingleChoiceID)) {
                                    RadioFenZhi.put(comname,
                                            FN.get(mSingleChoiceID));
                                    CountSum();
                                }
                            }
                            // Con.setText(itmtxt);
                            // CNHVal.put(comname,
                            // comname+"|"+m_Items[mSingleChoiceID]+"!"+m_Items[mSingleChoiceID]);
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
                                                scoreStatistics.get(i).setScoreNew(noditm[1]);
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

    private void CountSum() {
        try {
            Iterator iter = RadioFenZhi.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
            String itm = "";
            Integer num = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                // Integer key = (Integer)entry.getKey(); //获得key
                num = num + Integer.valueOf(entry.getValue().toString().trim());
            }
            EditText sumtxt = (EditText) CNHtb.get(FenZhiCode);
            sumtxt.setText(num + "");
            EditText level = (EditText) CNHtb.get(LevelCode);
            level.setText(getlevel(num) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Integer getlevel(int num) {
        int level = 3;
        if (num <= 40)
            level = 3;
        if ((num > 40) && (num <= 60))
            level = 2;
        if ((num > 60) && (num <= 99))
            level = 1;
        if (num > 99)
            level = 0;
        return level;

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
                            sum = sum + Integer.valueOf(val);
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

        // / final CommList listadapter = new CommList(context,sData);
        if (dataitmnod != null) {
            System.arraycopy(m_Items1, 0, m_Items2, 0, m_Items1.length);
        } else {
            System.arraycopy(m_Items, 0, m_Items2, 0, m_Items.length);
        }
        localBuilder.setSingleChoiceItems(m_Items2, mSingleChoiceID,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mSingleChoiceID = whichButton;
                        // mSingleChoiceID = whichButton;
                        // showDialog("你选择的id为" + whichButton + " , " +
                        // m_Items[whichButton]);
                    }
                });
        // localBuilder.setAdapter(listadapter, new PopupListener(listadapter));
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
                                            scoreStatistics.get(i).setScoreNew(noditm[1]);
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
                        //自动计算关联元素
                        countRelationVal();

                    }
                });
        localBuilder.setNegativeButton("取消", null);// 设置对话框[否定]按钮

        AlertDialog localAlertDialog = localBuilder.create();
        localAlertDialog.setTitle("选择");
        // localAlertDialog.setCanceledOnTouchOutside(false);
        /*
         * lv=localAlertDialog.getListView();
         * lv.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
         *
         * lv.requestFocus(); lv.setItemsCanFocus(false);
         * lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         *
         * lv.setOnItemClickListener(new OnItemClickListener() {
         *
         * @Override public void onItemClick(AdapterView<?> arg0, View view, int
         * position, long id) { String aa=(String)listadapter.getItem(position);
         * EditText txt=(EditText)view.findViewById(R.id.Desc);
         * lv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
         * txt.requestFocus();
         * ((InputMethodManager)context.getSystemService(context
         * .INPUT_METHOD_SERVICE)) .toggleSoftInput(0,
         * InputMethodManager.HIDE_NOT_ALWAYS);
         * //querytyeadapter.SetCurrPos(position);// 更新当前行 //ViewHolder vHolder
         * = (ViewHolder)view.getTag();
         * //在每次获取点击的item时将对应的checkBox状态改变，同时修改map的值 // vHolder.Radio1.toggle();
         *
         * } });
         */

        // lv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        localAlertDialog.show();

        localAlertDialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        /*
         * Timer timer = new Timer(); timer.schedule(new TimerTask() {
         *
         * @Override public void run() {
         *
         * ((InputMethodManager)context.getSystemService(context.
         * INPUT_METHOD_SERVICE)) .toggleSoftInput(0,
         * InputMethodManager.HIDE_NOT_ALWAYS); } }, Toast.LENGTH_SHORT);
         */

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
        // MultiData.put(Cname, Data);

    }

    private void initimultiRadio(Element itmnod, String Cname, String txtstr) {
        // ee
        List itmlist = itmnod.element("Itms").elements();
        // String itmsel=(String) PatIn.get(comname);
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
                                    // MultiChoiceID.remove(whichButton);
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
                                        // MultiChoiceID.clear();

                                    } else {
                                        AdapterArr.remove(whichButton);
                                        // MultiChoiceID.remove(whichButton);
                                    }

                                }

                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // dealRel(tempList, sourceStatus, tempStatus,
                        // classesId);//更新数据
                        // Iterator iter = AdapterArr.entrySet().iterator()
                        // ;//先获取这个map的set序列，再或者这个序列的迭代器
                        String itm = "";
                        String itmtxt = "";
                        /*
                         * while(iter.hasNext()){ Map.Entry entry =
                         * (Map.Entry)iter.next();
                         * //得到这个序列的映射项，就是set中的类型，HashMap都是Map
                         * .Entry类型（详情见map接口声明） //Integer key =
                         * (Integer)entry.getKey(); //获得key
                         * itm=itm+"^"+entry.getValue();
                         * itmtxt=itmtxt+entry.getValue()+","; }
                         */
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

                        // Toast.makeText(context, itm,Toast.LENGTH_SHORT).show();
                        /*
                         * for (int i=0;i<sData.size();i++) { String
                         * itmname=gtag+"_"+(i+1);
                         *
                         * if(AdapterArr.containsKey(i)) {
                         * itm=itm+itmname+"|"+sData.get(i)+"^";
                         * itmtxt=itmtxt+sData.get(i)+",";
                         * itmval=itmval+sData.get(i)+";"; }else{
                         * itm=itm+itmname+"|^"; itmval=itmval+";"; }
                         *
                         * }
                         */
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
                                                score = score + Integer.valueOf(noditm[1]);
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


                        //                        countRelationVal();
                    }
                }).setNegativeButton("取消", null).create();
        lv = ad.getListView();
        ad.show();

        /*
         * AlertDialog.Builder dialog05 = new AlertDialog.Builder( context); //
         * Builder ad = new
         * AlertDialog.Builder(context).setIcon(R.drawable.a).setTitle("选择省份") ;
         *
         * dialog05.setTitle("请选择");
         *
         *
         * dialog05.setMultiChoiceItems(choiceItems, tempStatus, new
         * DialogInterface.OnMultiChoiceClickListener() { public void
         * onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
         * if (isChecked) { MultiChoiceID.add(whichButton);
         * AdapterArr.put(whichButton, choiceItems[whichButton]);
         *
         * } else { //MultiChoiceID.remove(whichButton);
         * AdapterArr.remove(whichButton);
         *
         * }
         *
         * } }); dialog05.setPositiveButton("全选", new
         * DialogInterface.OnClickListener() {
         *
         * @Override public void onClick(DialogInterface dialog, int which) {
         * for (int i=0;i<sData.size();i++) {
         *
         * } } }); dialog05.setPositiveButton("确定", new
         * DialogInterface.OnClickListener() {
         *
         * @Override public void onClick(DialogInterface dialog, int which) { //
         * dealRel(tempList, sourceStatus, tempStatus, classesId);//更新数据
         * //Iterator iter = AdapterArr.entrySet().iterator()
         * ;//先获取这个map的set序列，再或者这个序列的迭代器 String itm=""; String itmtxt=""; /*
         * while(iter.hasNext()){ Map.Entry entry = (Map.Entry)iter.next();
         * //得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明） //Integer key
         * = (Integer)entry.getKey(); //获得key itm=itm+"^"+entry.getValue();
         * itmtxt=itmtxt+entry.getValue()+","; } String
         * gtag=(String)Con.getTag(); itm=""; String itmval=""; for (int
         * i=0;i<sData.size();i++) { String itmname=gtag+"_"+(i+1);
         *
         * if(AdapterArr.containsKey(i)) { itm=itm+itmname+"|"+sData.get(i)+"^";
         * itmtxt=itmtxt+sData.get(i)+","; itmval=itmval+sData.get(i)+";";
         * }else{ itm=itm+itmname+"|^"; itmval=itmval+";"; }
         *
         * } if (CNHLB.containsKey(gtag)) { // String
         * cname=(String)CNHLB.get(gtag); CNHVal.put(gtag, itm); PatIn.put(gtag,
         * itmval); Con.setText(itmtxt); }
         *
         * } }); dialog05.setNegativeButton("取消", null);// 设置对话框[否定]按钮
         * dialog05.show();
         */
        // Toast.makeText(context, itm,Toast.LENGTH_SHORT).show();

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
                                    // MultiChoiceID.remove(whichButton);
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
                                        // MultiChoiceID.clear();

                                    } else {
                                        AdapterArr.remove(whichButton);
                                        // MultiChoiceID.remove(whichButton);
                                    }

                                }

                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // dealRel(tempList, sourceStatus, tempStatus,
                        // classesId);//更新数据
                        // Iterator iter = AdapterArr.entrySet().iterator()
                        // ;//先获取这个map的set序列，再或者这个序列的迭代器
                        String itm = "";
                        String itmtxt = "";
                        /*
                         * while(iter.hasNext()){ Map.Entry entry =
                         * (Map.Entry)iter.next();
                         * //得到这个序列的映射项，就是set中的类型，HashMap都是Map
                         * .Entry类型（详情见map接口声明） //Integer key =
                         * (Integer)entry.getKey(); //获得key
                         * itm=itm+"^"+entry.getValue();
                         * itmtxt=itmtxt+entry.getValue()+","; }
                         */
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
                            // String cname=(String)CNHLB.get(gtag);
                            CNHVal.put(gtag, gtag + "|" + itmtxt + "!" + itmtxt);

                            // CNHVal.put(gtag, itm);
                            PatIn.put(gtag, itmval);
                            Con.setText(itmtxt);
                        }
                        if (MultiRadio != null) {
                            ifcontaincode(gtag);
                        }
                        countRelationVal();

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
                                // String datestr=DateFormat.format("yyyy-MM-dd",
                                // Calendar.getInstance()).toString();

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
                                // et.setText("您选择了："+hourOfDay+"时"+minute+"分");
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

        // return dialog;
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
            // Integer key = (Integer)entry.getKey(); //获得key
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

    //计算关联元素的值
    private void countRelationVal() {
        int total = 0;
        //儿童跌倒HD评估量表
        if (("DHCNURPGD_HEDDZCFXZHPGB").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item21");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item26");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item31");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item36");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item41");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item46");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item51");
            total += handleRelationVal(EmrCode, button);


            TextView totalTv = (TextView) CNHtb.get("Item56");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));
            //低风险
            List<String> list7 = Arrays.asList("Item61", "Item71", "Item76", "Item81", "Item86", "Item91", "Item96", "Item101", "Item106", "Item111", "Item116");
            //高风险
            List<String> list12 = Arrays.asList("Item66", "Item121", "Item126", "Item131", "Item136", "Item141", "Item146", "Item151");
            if (total >= 0 && total < 7) {
                for (String value : list7) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }

                for (String value : list12) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }
            } else if (total >= 7 && total <= 11) {

                for (String value : list7) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("√");
                }

                for (String value : list12) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }

            } else if (total > 11) {
                for (int i = 0; i < list7.size(); i++) {
                    button = (Button) CNHtb.get(list7.get(i));
                    if (button != null) {
                        if (i == 0)
                            button.setText("");
                        else
                            button.setText("√");
                    }
                }

                for (String value : list12) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("√");
                }
            }

        }
        //儿童压力性损伤Braden-Q
        if (("DHCNURPGD_ETYCFXPGLB").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item15");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item16");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item17");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item18");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item19");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item20");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item118");
            total += handleRelationVal(EmrCode, button);

            TextView totalTv = (TextView) CNHtb.get("Item110");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));

        }
        //压力性损伤Braden
        if (("DHCNURPGD_ycfswxyslhpg").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item15");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item16");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item17");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item18");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item19");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item20");
            total += handleRelationVal(EmrCode, button);

            TextView totalTv = (TextView) CNHtb.get("Item110");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));

        }
        //跌倒Morse危险评分
        if (("DHCNURPGDMorseDD").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item187");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item19");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item24");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item29");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item34");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item39");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item44");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item49");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item54");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item190");
            total += handleRelationVal(EmrCode, button);

            TextView totalTv = (TextView) CNHtb.get("Item60");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));
            //无风险
            List<String> list0 = Arrays.asList("Item74");
            //低风险
            List<String> list25 = Arrays.asList("Item79", "Item176", "Item170", "Item99", "Item104", "Item109", "Item114", "Item119");
            //高风险
            List<String> list45 = Arrays.asList("Item84", "Item124", "Item129", "Item134", "Item139", "Item144", "Item149", "Item154");
            if (total >= 0 && total <= 24) {
                button = (Button) CNHtb.get(list0.get(0));
                if (button != null)
                    button.setText("√");

                for (String value : list25) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }

                for (String value : list45) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }

            } else if (total > 24 && total <= 44) {
                button = (Button) CNHtb.get(list0.get(0));
                if (button != null)
                    button.setText("");

                for (String value : list25) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("√");
                }

                for (String value : list45) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("");
                }
            } else if (total > 44) {
                button = (Button) CNHtb.get(list0.get(0));
                if (button != null)
                    button.setText("");

                for (int i = 0; i < list25.size(); i++) {
                    button = (Button) CNHtb.get(list25.get(i));
                    if (button != null) {
                        if (i == 0)
                            button.setText("");
                        else
                            button.setText("√");
                    }
                }

                for (String value : list45) {
                    button = (Button) CNHtb.get(value);
                    if (button != null)
                        button.setText("√");
                }
            }

        }
        //营养筛查
        if (("DHCNURXHPGD_YYSC").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item101");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item102");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item103");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item104");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item105");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item106");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item107");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item108");
            total += handleRelationVal(EmrCode, button);

            TextView totalTv = (TextView) CNHtb.get("Item109");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));
        }
        //日常生活能力
        if (("DHCNURPGD_rcshzlnlBARTHLEpf").equals(EmrCode)) {
            Button button = (Button) CNHtb.get("Item74");
            total = handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item78");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item82");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item86");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item90");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item94");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item98");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item102");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item106");
            total += handleRelationVal(EmrCode, button);
            button = (Button) CNHtb.get("Item110");
            total += handleRelationVal(EmrCode, button);

            TextView totalTv = (TextView) CNHtb.get("Item114");
            if (totalTv != null)
                totalTv.setText(String.valueOf(total));

            totalTv = (TextView) CNHtb.get("Item118");
            if (totalTv != null) {
                if (total <= 40) {
                    totalTv.setText("重度依赖");
                } else if (total <= 60) {
                    totalTv.setText("中度依赖");
                } else if (total <= 99) {
                    totalTv.setText("轻度依赖");
                } else if (total == 100) {
                    totalTv.setText("无需依赖");
                }

            }
        }


    }

    class PopupListener implements DialogInterface.OnClickListener {
        CommList mAdapter;

        public PopupListener(ListAdapter adapter) {
            mAdapter = (CommList) adapter;

        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Map itm = (Map) mAdapter.getItem(which);
            /*
             * LoginUser.UserGroupID=itm.get("GroupID").toString();
             * LoginUser.UserLoc=itm.get("LocID").toString();
             * LogonLoc.setText(itm.get("LocDesc").toString());
             * LoginUser.WardID=itm.get("WardID").toString(); Drawable draw =
             * getResources().getDrawable(R.drawable.loc_item);
             * //LogonLoc.setBackground(draw);
             * LogonLoc.setBackgroundDrawable(draw);
             */

        }
    }
}
