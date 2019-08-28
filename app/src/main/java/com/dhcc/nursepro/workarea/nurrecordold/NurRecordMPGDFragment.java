package com.dhcc.nursepro.workarea.nurrecordold;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordold.bean.ItemConfigbyEmrCodeBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecDataBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.SyncEmrData;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * NurRecordMPGDFragment
 * 护理病历-多次评估单
 *
 * @author Devlix126
 * created at 2019/7/24 10:21
 */
public class NurRecordMPGDFragment extends BaseFragment implements View.OnClickListener {

    public String threeDataStr = "";
    public String scanUserId = "";
    private ScrollIndicatorView nurrecordIndicator;
    private ViewPager nurrecordViewPager;
    private IndicatorViewPager indicatorViewPager;
    private XmlParseInterface xmlParseInterface = new XmlParseInterface();
    private String episodeID = "";
    private RecModelListBean.MenuListBean.ModelListBean modelListBean;

    private String bedNo = "";
    private String patName = "";
    private String emrCode = "";
    private String recId = "";

    private Map<String, Object> PatRelItm = new HashMap<>();
    private Map<String, Object> PatIn = new HashMap<>();
    private Map<String, Object> CNHVal = new HashMap<>();
    private Map<String, Object> CNHtb = new HashMap<>();
    private Map<String, Object> CNHLB = new HashMap<>();

    private List<NurRecordViewPagerFragment> fragmentList = new ArrayList<>();
    private int modelNum = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            episodeID = getArguments().getString("EpisodeID");
            recId = getArguments().getString("RecID");
            emrCode = getArguments().getString("EMRCode");
            modelNum = Integer.parseInt(getArguments().getString("ModelNum"));
            modelListBean = (RecModelListBean.MenuListBean.ModelListBean) getArguments().getSerializable("ModelListBean");
            bedNo = getArguments().getString("BedNo");
            patName = getArguments().getString("PatName");
        }

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(bedNo + "    " + patName, 0xffffffff, 17);

        xmlParseInterface.CNHLB = CNHLB;
        xmlParseInterface.CNHtb = CNHtb;
        xmlParseInterface.CNHVal = CNHVal;

        initview(view);
        initAdapter();

        //获取Item关联配置
        getItemConfigByEmrCode();

        if (StringUtils.isEmpty(recId)) {
            getEmrPatInfo();
        } else {
            getMPGDVal(recId);
        }
    }

    private void initview(View view) {


        nurrecordIndicator = view.findViewById(R.id.nurrecord_mpgd_indicator);
        nurrecordViewPager = view.findViewById(R.id.nurrecord_mpgd_viewPager);

        float unSelectSize = 13;
        float selectSize = unSelectSize * 1.2f;
        nurrecordIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF62ABFF, Color.GRAY).setSize(selectSize, unSelectSize));

        nurrecordIndicator.setScrollBar(new ColorBar(getActivity(), 0xFF62ABFF, 4));

        nurrecordViewPager.setOffscreenPageLimit(10);
        indicatorViewPager = new IndicatorViewPager(nurrecordIndicator, nurrecordViewPager);

    }

    private void initAdapter() {
        indicatorViewPager.setAdapter(new MyAdapter(getFragmentManager()));
    }

    private void getItemConfigByEmrCode() {
        NurRecordOldApiManager.getItemConfigByEmrCode(episodeID, emrCode, new NurRecordOldApiManager.ItemConfigByEmrCodeCallback() {
            @Override
            public void onSuccess(ItemConfigbyEmrCodeBean itemConfigbyEmrCodeBean) {
                xmlParseInterface.itemSetList = itemConfigbyEmrCodeBean.getItemSetList();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void getEmrPatInfo() {
        NurRecordOldApiManager.getEmrPatinfo(episodeID, emrCode, new NurRecordOldApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                String recData = recDataBean.getRetData();
                String[] pat = recData.split("\\^");
                for (int i = 0; i < pat.length; i++) {
                    String[] itm = pat[i].split("\\|");
                    if (itm.length > 1)
                        CNHVal.put(itm[0], itm[1]);
                    else
                        CNHVal.put(itm[0], "");
                    if (itm.length > 1)
                        PatIn.put(itm[0], itm[1]);
                    else
                        PatIn.put(itm[0], "");

                }

                xmlParseInterface.PatIn = PatIn;
                xmlParseInterface.PatRelItm = PatRelItm;
                xmlParseInterface.RetData = recData;
                xmlParseInterface.EmrCode = emrCode;
                xmlParseInterface.EpisodeID = episodeID;
                //为了显示病人信息，赋个值
                xmlParseInterface.RecId = "PatInfo";
                //获得定位位置
                //initPositionNum = NurRecPositionView.getPositionViewInfo(CNHVal,EmrCode);

                for (int i = 0; i < fragmentList.size(); i++) {
                    fragmentList.get(i).setXmlParseInterface(xmlParseInterface);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void getMPGDVal(String recId) {
        String[] rec = recId.split("\\^");
        String par = "";
        String rw = "";
        if (rec.length > 0) {
            par = recId.split("\\^")[0];
        }
        if (rec.length > 1) {
            rw = recId.split("\\^")[1];
        }
        NurRecordOldApiManager.getMPGDVal(par, rw, modelListBean.getGetValMth(), new NurRecordOldApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                String[] pat = recDataBean.getRetData().split("\\^");
                for (int i = 0; i < pat.length; i++) {
                    String[] itm = pat[i].split("\\|");
                    if (itm.length > 1)
                        CNHVal.put(itm[0], itm[1]);
                    else
                        CNHVal.put(itm[0], "");
                    if (itm.length > 1)
                        PatIn.put(itm[0], itm[1]);
                    else
                        PatIn.put(itm[0], "");

                }
                xmlParseInterface.PatIn = PatIn;
                xmlParseInterface.CNHVal = CNHVal;
                //                xmlParseInterface.handler = handler;
                xmlParseInterface.RetData = recDataBean.getRetData();
                xmlParseInterface.EmrCode = emrCode;
                xmlParseInterface.EpisodeID = episodeID;
                xmlParseInterface.RecId = recId;

                for (int i = 0; i < fragmentList.size(); i++) {
                    fragmentList.get(i).setXmlParseInterface(xmlParseInterface);
                }

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ((v.getTag() instanceof String)) {
            String ttag = (String) v.getTag();
            if (ttag.contains("btnLink")) {
                linkEmrData();
            } else if (ttag.contains("btnSkip")) {
//                RecModelListBean.MenuListBean.ModelListBean modelListBeanSkip = new RecModelListBean.MenuListBean.ModelListBean();
//                String skipViewCode = "";
//                for (int i = 0; i < xmlParseInterface.itemSetList.size(); i++) {
//                    ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = xmlParseInterface.itemSetList.get(i);
//
//                    if (itemSetListBean.getItemCode().equals(ttag)) {
//                        modelListBeanSkip.setGetListMth(itemSetListBean.getModeInfo().getGetListMth());
//                        modelListBeanSkip.setGetValMth(itemSetListBean.getModeInfo().getGetValMth());
//                        modelListBeanSkip.setLinkModel(itemSetListBean.getModeInfo().getLinkModel());
//                        modelListBeanSkip.setModelCode(itemSetListBean.getModeInfo().getModelCode());
//                        modelListBeanSkip.setModelName(itemSetListBean.getModeInfo().getModelName());
//                        modelListBeanSkip.setModelNum(itemSetListBean.getModeInfo().getModelNum());
//                        modelListBeanSkip.setModelType(itemSetListBean.getModeInfo().getModelType());
//                        modelListBeanSkip.setSaveMth(itemSetListBean.getModeInfo().getSaveMth());
//                    }
//                    skipViewCode = itemSetListBean.getLinkNote();
//                }
//
//                Bundle bundle = new Bundle();
//                bundle.putString("EpisodeID", episodeID);
//                bundle.putString("BedNo", bedNo);
//                bundle.putString("PatName", patName);
//
//                bundle.putString("EMRCode", modelListBeanSkip.getModelCode());
//                bundle.putString("ModelNum", modelListBeanSkip.getModelNum());
//                bundle.putSerializable("ModelListBean", modelListBeanSkip);
//                bundle.putString("skipViewCode", skipViewCode);
//                bundle.putString("RecID", "");
//                if (Objects.requireNonNull(modelListBeanSkip).getModelType().equals("1")) {
//                    startFragment(NurRecordJLDFragment.class, bundle);
//                } else if (Objects.requireNonNull(modelListBeanSkip).getModelType().equals("2")) {
//                    startFragment(NurRecordPGDFragment.class, bundle);
//                } else if (Objects.requireNonNull(modelListBeanSkip).getModelType().equals("3")) {
//                    startFragment(NurRecordMPGDFragment.class, bundle);
//
//                }
            } else if (ttag.contains("btnSave")) {
                if (recId.equals("")) {
                    v.setClickable(false);
                }
                Sure();
            } else if (ttag.contains("btnCancel")) {
                finish();
            }
        }
    }

    private void linkEmrData() {
        NurRecordOldApiManager.linkEmrData(episodeID, emrCode, new NurRecordOldApiManager.SyncEmrDataCallback() {
            @Override
            public void onSuccess(SyncEmrData syncEmrData) {
                List<SyncEmrData.ItemListBean> syncItemList = syncEmrData.getItemList();
                for (int i = 0; i < syncItemList.size(); i++) {
                    String key = syncItemList.get(i).getItemCode();
                    String value = syncItemList.get(i).getItemValue();
                    if (CNHtb.get(key) != null && CNHtb.get(key) instanceof EditText) {
                        ((EditText) CNHtb.get(key)).setText(value);
                    }
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    public void Sure() {
        Iterator iter = CNHLB.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
        String itm = "";
        String ret = "";
        threeDataStr = "";
        /*
         * O现在不是用“a!a”那么存的,直接存的是a M还是val1;val2;val3;...
         */
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
            String cname = entry.getKey().toString();

            itm = (String) entry.getValue();
            if (itm.substring(0, 1).equals("B"))
                continue;
            if (itm.substring(0, 1).equals("S") || itm.substring(0, 1).equals("G")) {
                EditText ed = (EditText) CNHtb.get(entry.getKey().toString());
                if (entry.getKey().toString().equals("User")) {
                    //                    CNHVal.put(cname, LoginUser.UserDR);
                } else {
                    String edtxt = ed.getText().toString();
                    for (int i = 0; i < xmlParseInterface.itemSetList.size(); i++) {
                        ItemConfigbyEmrCodeBean.ItemSetListBean itemSetListBean = xmlParseInterface.itemSetList.get(i);
                        if (itemSetListBean.getLinkType().equals("4") && cname.equals(itemSetListBean.getItemCode())) {
                            if (StringUtils.isEmpty(edtxt)) {
                                ed.setBackgroundResource(R.drawable.nur_record_inputerror_bg);
                                showToast("请填写必填项数据");
                                return;
                            }
                        }
                    }

                    CNHVal.put(cname, edtxt);
                }
                ///出入院评估单责任护士

            }
            if (itm.substring(0, 1).equals("D")) {
                TextView ed = (TextView) CNHtb.get(entry.getKey().toString());
                if (!ed.getText().toString().equals("")) {
                }
                CNHVal.put(cname, ed.getText());

            }

            if (itm.substring(0, 1).equals("M")) {
                // Toast.makeText(context, itmtxt, 100).show();
                ret = ret + cname + "|" + GetMultiVal(CNHVal.get(cname).toString()) + "^";
            }
            if (itm.substring(0, 1).equals("O") || itm.substring(0, 1).equals("I")) {
                TextView ed = (TextView) CNHtb.get(entry.getKey().toString());
                if (ed != null) {
                    //关联元素自动赋值的
                    CNHVal.put(cname, cname + "|" + ed.getText() + "!" + ed.getText());
                }
                // Toast.makeText(context, itmtxt, 100).show();
                ret = ret + cname + "|" + GetComVal(CNHVal.get(cname).toString()) + "^";
            }

            if (itm.substring(0, 1).equals("R")) {
                ret = ret + cname + "|" + getRadioVal(CNHVal.get(cname).toString()) + "^";
            }

            if (itm.substring(0, 1).equals("M") || itm.substring(0, 1).equals("I") || itm.substring(0, 1).equals("O") || itm.substring(0, 1).equals("R")) {
                continue;
            }

            if (CNHVal.containsKey(cname)) {
                ret = ret + cname + "|" + CNHVal.get(cname).toString() + "^";
            }
        }

        String parr = ret + "EmrUser|" + SPUtils.getInstance().getString(SharedPreference.USERID) + "^EmrCode|" + emrCode + "^EpisodeId|" + episodeID;

        saveMPGDData(parr, recId.trim());
    }

    private String GetMultiVal(String itm) {
        String[] aa = itm.split("\\^");
        String ret = "";
        for (int i = 0; i < aa.length; i++) {
            if (aa[i] == "")
                continue;
            String[] bb = aa[i].split("\\|");
            if (bb.length > 1) {
                if (bb[1] != "")
                    ret = ret + bb[1] + ";";
            }
        }
        return ret;
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

    private String getRadioVal(String itm) {
        String[] aa = itm.split("\\^");
        String ret = "";
        for (int i = 0; i < aa.length; i++) {
            if (aa[i].equals("")) {
                continue;
            }
            String[] bb = aa[i].split("\\|");
            if (bb.length > 1) {
                if (!bb[1].equals("")) {
                    ret = ret + bb[1] + ";";
                }
            } else {
                ret = ret + ";";
            }
        }
        return ret;
    }

    private void saveMPGDData(String parr, String pgdId) {
        NurRecordOldApiManager.saveMPGDData(parr, pgdId, modelListBean.getSaveMth(), new NurRecordOldApiManager.RecDataCallback() {
            @Override
            public void onSuccess(RecDataBean recDataBean) {
                showToast("保存成功");
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void searchcontrol(ViewGroup vv) {
        for (int i = 0; i < vv.getChildCount(); i++) {

            if (vv.getChildAt(i) instanceof TextView) {
                TextView btn = (TextView) vv.getChildAt(i);
                // btn.setWidth(180);
                // btn.setHeight(120);
                String tag = (String) btn.getTag();
                if (tag != null && tag.contains("btn")) {
                    btn.setOnClickListener(this);
                }

            }


            if (vv.getChildAt(i) instanceof LinearLayout) {
                searchcontrol((ViewGroup) vv.getChildAt(i));
            }
            if (vv.getChildAt(i) instanceof RelativeLayout) {
                searchcontrol((ViewGroup) vv.getChildAt(i));
            }
            if (vv.getChildAt(i) instanceof AbsoluteLayout) {
                searchcontrol((ViewGroup) vv.getChildAt(i));
            }
            if (vv.getChildAt(i) instanceof ScrollView) {
                searchcontrol((ViewGroup) vv.getChildAt(i));
            }
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NUR_RECORD_XML_VIEW.equals(intent.getAction())) {
            hideLoadingTip();
            searchcontrol(Objects.requireNonNull(getActivity()).findViewById(intent.getIntExtra("viewId", -1)));

        }

    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_mpgd, container, false);
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {


        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return modelNum;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText("第" + (position + 1) + "页");

            int witdh = getTextWidth(textView);
            int padding = ConvertUtils.dp2px(8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.2f) + padding);

            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            NurRecordViewPagerFragment fragment = NurRecordViewPagerFragment.newInstance(episodeID, emrCode + "_PDA." + (position + 1));
            fragmentList.add(fragment);
            return fragment;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }
    }

}
