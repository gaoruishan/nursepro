package com.dhcc.nursepro.workarea.nurrecordold;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@SuppressLint("UseSparseArrays")
public class XmlParseInterface extends XmlClickShowHelper implements Serializable {

    public Map<String, Object> OBJBlankItm = new HashMap<>(); // 生命体征空白栏
    public Map<String, Object> PatRelItm = new HashMap<>(); // 空件对象
    public String RecId = "";
    public String EmrCode = "";
    public String EpisodeID = ""; // 腕带
    public Handler handler = null;
    public String RetData = null;
    public Context mContext;


    public void DocParseXml(String xml, final Context context, AbsoluteLayout abslayout) throws DocumentException {

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
                if (nod.attribute("type") == null) {
                    continue;
                }
                Element metaNod = null;
                if (meddatanod.element(nod.getName()) != null) {
                    metaNod = meddatanod.element(nod.getName());
                }
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
                if (RelName != "") {
                    CName = RelName;
                }
                CNHVal.put(CName, txtstr);
                if (RecId.equals("")) {
                    if (PatRelItm.containsKey(CName)) {
                        if (PatIn.get(PatRelItm.get(CName)) != null) {
                            txtstr = (String) PatIn.get(PatRelItm.get(CName));
                        }

                    }
                } else {
                    if (PatIn.containsKey(CName)) {
                        if (PatIn.get(CName) != null) {
                            txtstr = PatIn.get(CName).toString();
                        }
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
                    lb.setTextColor(Color
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
                    btn.setTextColor(Color
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
                    dbtn.setTextColor(Color
                            .parseColor("#ff000000"));
                    dbtn.setGravity(Gravity.CENTER);

                    dbtn.setTag(CName);
                    if (fontunit.equals("World")) {
                        dbtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));
                    } else {
                        dbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));
                    }
                    CNHtb.put(CName, dbtn);
                    if (dflag.equals("Y")) {
                        dbtn.setText(txtstr);
                        CNHVal.put(CName, dbtn.getText());
                        dbtn.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbtn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                                ShowDateTime(DATE_DIALOG, context, dbtn);
                            }
                        });

                    }
                    if (tflag.equals("Y")) {
                        dbtn.setText(txtstr);
                        CNHVal.put(CName, dbtn.getText());
                        dbtn.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbtn.setBackgroundResource(R.drawable.nur_record_btn_bg);
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
                    txt.setTag(CName);
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
                    txt.setTextColor(Color
                            .parseColor("#ff000000"));
                    txt.setGravity(Gravity.CENTER);
                    if (fontunit.equals("World")) {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    configEditInputType(CName, txt);

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
                            //关联内容
                            configSGEditLinkNote(s.toString(), txt);

                        }
                    });


                    txt.setText(txtstr);


                    abslayout.addView(txt,
                            getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));

                    CNHVal.put(CName, txtstr);
                    CNHtb.put(CName, txt);

                }


                if ((nod.getName().substring(0, 1).equals("M"))) {

                    final TextView btn5 = new TextView(context);

                    if (!txtstr.equals("")) {
                        btn5.setText(getMultiVal(txtstr));
                    } else {
                        btn5.setText("");
                    }
                    //计算分数
                    configMTextScoreStatistic(meddatanod, nod, txtstr, CName);

                    btn5.setTag(CName);
                    btn5.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn5.setTextColor(Color
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
                        if (RadioFenZhi.containsKey(CName)) {
                            initimultiRadio(meddatanod.element(nod.getName()),
                                    CName, txtstr);
                        }
                    }
                    abslayout.addView(btn5,
                            getlayparam(Iwidth, Iheight, Ileft, Itop));
                    btn5.setOnClickListener(new OnClickListener() {
                        @Override
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
                        btn6.setText("");
                    } else {
                        btn6.setText(getRadioVal(txtstr));
                    }
                    //计算分数
                    configROTextSorceStatistic(meddatanod, nod, txtstr, CName, ";");


                    if (fontunit.equals("World")) {
                        btn6.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn6.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    btn6.setTag(CName);
                    btn6.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn6.setTextColor(Color.parseColor("#ff000000"));
                    btn6.setGravity(Gravity.CENTER);

                    CNHtb.put(CName, btn6);
                    IniMultiData(meddatanod.element(nod.getName()), CName, txtstr);
                    abslayout.addView(btn6, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn6.setOnClickListener(new OnClickListener() {
                        @Override
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
                                } else {
                                    showMDialog(itmnod, context, btn6);
                                }
                            }

                        }
                    });
                }


                if ((nod.getName().substring(0, 1).equals("O"))) {

                    final TextView btn = new TextView(context);

                    if (txtstr.replaceAll("!", "").equals("")) {
                        btn.setText("");
                    } else {
                        btn.setText(txtstr.split("!")[0]);
                    }
                    //计算分数
                    configROTextSorceStatistic(meddatanod, nod, txtstr, CName, "!");

                    if (fontunit.equals("World")) {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (fontsz));//
                    } else {
                        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontsz));//
                    }

                    btn.setTag(CName);
                    btn.setBackgroundResource(R.drawable.nur_record_btn_bg);
                    btn.setTextColor(Color.parseColor("#ff000000"));
                    btn.setGravity(Gravity.CENTER);

                    CNHVal.put(CName, CName + "|" + txtstr + "!" + txtstr);
                    CNHtb.put(CName, btn);
                    abslayout.addView(btn, getlayparam(Iwidth, (int) (Iheight * 1.3), Ileft, Itop));
                    btn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn.setBackgroundResource(R.drawable.nur_record_btn_bg);
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
                                } else {
                                    showMDialog(itmnod, context, btn);
                                }
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


}
