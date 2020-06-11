package com.dhcc.nursepro.workarea.nurrecordold;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ScoreStatisticsBean;

import org.dom4j.Element;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202020-06-11/10:25
 * @email:grs0515@163.com
 */
public class XmlClickShowHelper extends XmlConfigItemHelper{

    public final static int DATE_DIALOG = 0;
    public final static int TIME_DIALOG = 1;
    // 单选数据关联 针对跌倒，压疮
    public Map<String, Object> CNHLB = new HashMap<>(); // 控件名称
    // 单选数据关联 针对跌倒，压疮
    public Map<String, Object> CNHVal = new HashMap<>(); // 控件对应值
    public Calendar c = null;
    public  Map<String, Object> SelItm = new HashMap<String, Object>();
    public Map<String, Object> PatIn = new HashMap<>(); // 空件对象
    public ArrayList<Integer> MultiChoiceID = new ArrayList<Integer>();// 记录多选选中的id号
    public Map<Integer, Object> AdapterArr = new HashMap<Integer, Object>();
    public List<CharSequence> sData = null;
    public Element dataitmnod = null;
    public Map<String, String> QtData = new HashMap<>();
    public Map<String, String> SingleData = new HashMap<>(); // 单选使用编辑
    public ListView lv = null;
    public Map<String, String> MultiRadio = new HashMap<>(); // 空件对象//多选单选使用


    public Map<String, String> RadioFenZhi = new HashMap<>(); // 空件对象//计算分值

    public Map<String, String> SingleEdit = new HashMap<>(); // 单选使用编辑

    public Map<String, Integer> SingleSel = new HashMap<String, Integer>();

    public int mSingleChoiceID = -1;

    public Map<String, String> RadioData = new HashMap<>();


    public void IniMultiData(Element itmnod, String Cname, String txtstr) {
        List itmlist = itmnod.element("Itms").elements();
        List<CharSequence> Data = new ArrayList<CharSequence>();
        String[] SelItm = txtstr.split(";");
        Map<String, Object> MSelItm = new HashMap<String, Object>();
        String itm = "";
        for (int i = 0; i < SelItm.length; i++) {
            if (SelItm[i].equals("")) {
                continue;
            }
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

    public void initimultiRadio(Element itmnod, String Cname, String txtstr) {
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

    public String ifcontaincode(String cname) { // 判断是否

        String ret = "";
        try {
            Iterator iter = MultiRadio.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
            // /查询对饮的触发点
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                // Integer key = (Integer)entry.getKey(); //获得key
                String[] arr = entry.getValue().toString().split("\\^");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        continue;
                    }
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
                        if (arr[i].equals("")) {
                            continue;
                        }
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
                    if (arritm[i].equals("")) {
                        continue;
                    }
                    String[] itm = arritm[i].split("\\|");
                    if (itm[2].equals("D")) {
                        Button btn = (Button) CNHtb.get(itm[0]);
                        if (btn.getText().toString().equals("")) {
                            btn.setText(itm[1]);
                        }

                    }
                    if (itm[2].equals("S")) {
                        EditText btn = (EditText) CNHtb.get(itm[0]);
                        if (btn.getText().toString().equals("")) {
                            btn.setText(itm[1]);
                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public String IfConRadio(String cname) {

        String ret = "";
        try {
            if (RadioData == null) {
                return ret;
            }
            Iterator iter = RadioData.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
            // /查询对饮的触发点
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
                // Integer key = (Integer)entry.getKey(); //获得key
                String[] arr = entry.getValue().toString().split("\\^");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        continue;
                    }
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

    protected void ShowDateTime(int id, Context context, final TextView btn) {
        Dialog dialog = null;
        switch (id) {
            case DATE_DIALOG:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
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
                            @Override
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

    public void showMDialog(Element itmnod, final Context context, final TextView Con) {

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
                if (selitm[i].equals("")) {
                    continue;
                }
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
                            @Override
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

    public void showDialog(Element itmnod, final int classesId, final Context context, final TextView Con) {

        List itmlist = itmnod.element("Itms").elements();

        String[] m_Items = new String[itmlist.size() + 1];
        boolean[] flags = new boolean[itmlist.size() + 1];
        String cname = (String) Con.getTag();
        SelItm.clear();
        if (PatIn.containsKey(cname)) {
            String[] selitm = PatIn.get(cname).toString().split(";");
            for (int i = 0; i < selitm.length; i++) {
                if (selitm[i].equals("")) {
                    continue;
                }
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
                            @Override
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
                                        for (int i = 0; i < choiceItems.length; i++) {
                                            lv.setItemChecked(i,false);
                                            tempStatus[i] = false;
                                        }
                                        AdapterArr.clear();
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
                                if (scoreStr == null || scoreStr.equals("")) {
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

    public void ShowRadioDialog(Element itmnod, final Context context, final TextView Con) {
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
            if (m_Items[j].equals(sel)) {
                mSingleChoiceID = j;
            }
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
                        if (SingleData.containsKey(arr[i])) {
                            m_Items[i] = SingleData.get(arr[i]);
                        }
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
                    @Override
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
                                if (scoreStr == null || scoreStr.equals("")) {
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

    public void ShowEdit(final EditText sp, final Context context) {
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
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            sp.setText(txt.getText());

                        }
                    });
            dialog07.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                        }
                    });
            dialog07.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowRadio(Element itmnod, final Context context, final TextView Con) { // Multi
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
                if (noditm.length > 1) {
                    FN.put(j, noditm[1]); // 分值
                }

                m_Items[j] = itmnn;
                if (m_Items[j].equals(sel)) {
                    mSingleChoiceID = j;
                }
                // 设置选项

            }
            m_Items[itmlist.size()] = "";
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
                                    if (scoreStr == null || scoreStr.equals("")) {
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


}
