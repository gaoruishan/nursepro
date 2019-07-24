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
import com.dhcc.nursepro.utils.XmlParseInterface;
import com.dhcc.nursepro.workarea.nurrecordold.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecDataBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;
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
 * NurRecordJLDFragment
 * 护理病历-记录单
 *
 * @author Devlix126
 * created at 2019/7/22 14:10
 */
public class NurRecordJLDFragment extends BaseFragment implements View.OnClickListener {
    private ScrollIndicatorView nurrecordJldIndicator;
    private ViewPager nurrecordJldViewPager;
    private IndicatorViewPager indicatorViewPager;

    private String episodeID = "";
    private RecModelListBean.MenuListBean.ModelListBean modelListBean;

    private String bedNo = "";
    private String patName = "";
    private String emrCode = "";
    private String recId = "";

    private Map<String, Object> PatRelItm = new HashMap<>();
    private Map<String, Object> PatIn = new HashMap<>();
    private Map<String, Object> CNHtb = new HashMap<>();
    private Map<String, Object> CNHLB = new HashMap<>();
    private Map<String, Object> CNHVal = new HashMap<>();


    private List<NurRecordViewPagerFragment> fragmentList = new ArrayList<>();
    private int modelNum = 0;

    private XmlParseInterface xmlParseInterface = new XmlParseInterface();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_jld, container, false);
    }

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


        initView(view);
        initAdapter();

        xmlParseInterface.CNHLB = CNHLB;
        xmlParseInterface.CNHtb = CNHtb;
        xmlParseInterface.CNHVal = CNHVal;
        //        bedno=intent.getExtras().getString("bedno");
        //        PatName=intent.getExtras().getString("PatName");
        if (StringUtils.isEmpty(recId)) {
            getEmrPatInfo();
        } else {
            GetJLDVal(recId);
        }


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

    private void GetJLDVal(String recId) {
        String par = recId.split("\\^")[0];
        String rw = recId.split("\\^")[1];
        NurRecordOldApiManager.getJLDVal(par, rw, new NurRecordOldApiManager.RecDataCallback() {
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
                xmlParseInterface.CNHVal = CNHVal;
                xmlParseInterface.RetData = recData;
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

    private void initView(View view) {


        nurrecordJldIndicator = view.findViewById(R.id.nurrecord_jld_indicator);
        nurrecordJldViewPager = view.findViewById(R.id.nurrecord_jld_viewPager);
        float unSelectSize = 13;
        float selectSize = unSelectSize * 1.2f;
        nurrecordJldIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF62ABFF, Color.GRAY).setSize(selectSize, unSelectSize));

        nurrecordJldIndicator.setScrollBar(new ColorBar(getActivity(), 0xFF62ABFF, 4));

        nurrecordJldViewPager.setOffscreenPageLimit(10);
        indicatorViewPager = new IndicatorViewPager(nurrecordJldIndicator, nurrecordJldViewPager);
    }

    private void initAdapter() {
        indicatorViewPager.setAdapter(new MyAdapter(getFragmentManager()));
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

    public void getcon() {
        Iterator iter = CNHLB.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
        String itm = "";
        String itmtxt = "";
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
            itmtxt = "|" + entry.getKey().toString() + "^" + entry.getValue();
            itm = (String) entry.getValue();
            if (itm.equals("S101")) // 测试用
            {
                showToast(itmtxt);
                EditText ed = (EditText) CNHtb.get(entry.getKey().toString());
                if (!ed.getText().toString().equals("")) {
                    showToast(ed.getText());
                }
            }

        }

    }

    @Override
    public void onClick(View v) {
        //点击页签
        //        if(v.getTag() instanceof Integer) {
        //            Integer ttag = (Integer) v.getTag();
        //            orientateItemView(ttag-1);
        //            return;
        //        }
        if ((v.getTag() instanceof String)) {
            String ttag = (String) v.getTag();
            if (ttag.equals("btnSave")) {
                if (recId.equals("")) {
                    v.setClickable(false);
                }
                Sure(SPUtils.getInstance().getString(SharedPreference.USERID));
                return;
            }
            if (ttag.equals("btnCancel")) {
                this.finish();
                return;
            }
        }

    }

    public void Sure(String userId) {
        Iterator iter = CNHLB.entrySet().iterator();// 先获取这个map的set序列，再或者这个序列的迭代器
        String itm = "";
        String ret = "";
        String Parr = "";
        String checkret = "";
        String comboret = "";
        String rw = "", par = "";
        /*
         * O现在不是用“a!a”那么存的,直接存的是a M还是val1;val2;val3;...
         */
        if (recId.equals("")) {
        } else {
            String[] aa = recId.split("\\^");
            rw = aa[1];
            par = aa[0];
            ret = ret + "rw|" + rw + "^par|" + par + "^";
        }
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next(); // 得到这个序列的映射项，就是set中的类型，HashMap都是Map.Entry类型（详情见map接口声明）
            String cname = entry.getKey().toString();

            itm = (String) entry.getValue();
            if (itm.substring(0, 1).equals("B"))
                continue;
            if (itm.substring(0, 1).equals("S") || itm.substring(0, 1).equals("G")) {
                EditText ed = (EditText) CNHtb.get(entry.getKey().toString());
                if (entry.getKey().toString().equals("User")) {
                    CNHVal.put(cname, SPUtils.getInstance().getString(SharedPreference.USERID));
                } else {
                    CNHVal.put(cname, ed.getText());
                }

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
                // Toast.makeText(context, itmtxt, 100).show();
                ret = ret + cname + "|" + GetComVal(CNHVal.get(cname).toString()) + "^";
            }
            if (itm.substring(0, 1).equals("M") || itm.substring(0, 1).equals("I") || itm.substring(0, 1).equals("O"))
                continue;
            if (CNHVal.containsKey(cname)) {
				/*if (cname.equals("CareDate")) {
					ret = ret + cname + "|" + MobileCom.ChangeDate(CNHVal.get(cname).toString()) + "^";
				} else {*/
                ret = ret + cname + "|" + CNHVal.get(cname).toString() + "^";
                //}
            }

        }

        String parr = ret + "RecBed|" + bedNo + "^RecLoc|" + SPUtils.getInstance().getString(SharedPreference.LOCID);

        NurRecordOldApiManager.saveJLDData(parr, episodeID, emrCode, new NurRecordOldApiManager.RecDataCallback() {
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

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NUR_RECORD_XML_VIEW.equals(intent.getAction())) {
            hideLoadingTip();
            searchcontrol(Objects.requireNonNull(getActivity()).findViewById(intent.getIntExtra("viewId", -1)));
        }

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
