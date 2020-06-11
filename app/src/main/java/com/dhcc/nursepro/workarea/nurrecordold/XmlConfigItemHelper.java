package com.dhcc.nursepro.workarea.nurrecordold;

import android.text.InputType;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ItemConfigbyEmrCodeBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ScoreStatisticsBean;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202020-06-11/14:11
 * @email:grs0515@163.com
 */
public class XmlConfigItemHelper {

    public List<ScoreStatisticsBean> scoreStatistics = new ArrayList<>();
    public Map<String, Object> CNHtb = new HashMap<>(); // 空件对象
    public List<ItemConfigbyEmrCodeBean.ItemSetListBean> itemSetList = new ArrayList<>();


    public AbsoluteLayout.LayoutParams getlayparam(int w, int h, int x, int y) {
        AbsoluteLayout.LayoutParams lp0 = new AbsoluteLayout.LayoutParams(w, h,
                x, y);
        return lp0;

    }

    public String getMultiTxt(String stritm) {
        String[] itm = stritm.split(",");
        String ret = "";
        for (int i = 0; i < itm.length; i++) {
            if (itm[i].equals("")) {
                continue;
            }
            if ((i + 1) == itm.length) {
                ret = ret + itm[i];
            } else {
                ret = ret + itm[i] + ",";
            }
        }
        return ret;
    }

    public String GetComVal(String itm) {
        String ret = "";
        if (itm == "") {
            return "";
        }
        String[] aa = itm.split("\\|");
        String[] val = aa[1].split("!");
        if (val.length == 0) {
            return "";
        }
        ret = val[0];
        return ret;
    }

    public String getmultival(String itm) {
        if (itm == null) {
            return "";
        }
        String[] aa = itm.split(";");
        String ret = "";
        for (int i = 0; i < aa.length; i++) {
            if (!aa[i].trim().equals("")) {
                ret = aa[i];
            }
        }
        return ret;
    }

    public String getRadioVal(String itm) {
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

    public String getMultiVal(String stritm) {
        String[] itm = stritm.split(";");
        String ret = "";
        for (int i = 0; i < itm.length; i++) {
            if (itm[i].equals("")) {
                continue;
            }
            if ((i + 1) == itm.length) {
                ret = ret + itm[i];
            } else {
                ret = ret + itm[i] + ",";
            }
        }
        return ret;
    }

    public void configEditInputType(String CName, EditText txt) {
        for (int j = 0; j < itemSetList.size(); j++) {
            ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
            if (itemSetListBean.getLinkType().equals("5") && CName.equals(itemSetListBean.getItemCode())) {
                txt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
        }
    }

    /**
     * config  S  G 元素
     */
    public void configSGEditLinkNote(String s, EditText txt) {
        for (int j = 0; j < itemSetList.size(); j++) {
            ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
            if (itemSetListBean.getLinkType().equals("11") && txt.getTag().equals(itemSetListBean.getItemCode())) {
                String linkNote = itemSetListBean.getLinkNote();
                TextView tvLinked = (TextView) CNHtb.get(itemSetListBean.getLinkCode());
                if (tvLinked != null) {
                    tvLinked.setText("");
                    String[] notes = linkNote.split("!");
                    for (int k = 0; k < notes.length; k++) {
                        String[] noteChilds = notes[k].split("\\|");
                        if (noteChilds.length == 2) {
                            int low = Integer.parseInt(noteChilds[0].split("-")[0]);
                            int high = Integer.parseInt(noteChilds[0].split("-")[1]);
                            int text = 0;
                            if (!StringUtils.isEmpty(s)) {
                                text = Integer.parseInt(s);
                            }

                            if (text >= low && text <= high) {
                                tvLinked.setText(noteChilds[1]);
                                break;
                            }

                        }
                    }
                }
            }
        }
    }
    /**
     * config  M 元素
     */
    public void configMTextScoreStatistic(Element meddatanod, Element nod, String txtstr, String CName) {
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
    }
    /**
     * config  R  O 元素
     */
    public void configROTextSorceStatistic(Element meddatanod, Element nod, String txtstr, String CName, String replace) {
        for (int j = 0; j < itemSetList.size(); j++) {
            ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = itemSetList.get(j);
            if (itemSetListBean.getLinkType().equals("2") && CName.equals(itemSetListBean.getItemCode())) {
                ScoreStatisticsBean scoreStatisticsBean = new ScoreStatisticsBean(itemSetListBean.getItemCode(), itemSetListBean.getLinkCode());
                String txtstrr = txtstr.replace(replace, "");
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
    }

}
