package com.dhcc.nursepro.workarea.bedmap;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatInfoDetailAdapter;
import com.dhcc.nursepro.workarea.bedmap.adapter.PatTransRecAdapter;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.nurrecord.FlowRadioGroup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BedMapPatInfoFragment
 * 个人信息
 *
 * @author DevLix126
 * created at 2018/10/11 16:07
 */
public class BedMapPatInfoFragment extends BaseFragment {

    private HashMap<String,String> paMap = new HashMap<>();
    private List<HashMap<String,String>> list = new ArrayList<>();
    private RecyclerView recPatInfo,recTrans;
    private RelativeLayout rlHind;
    private BedMapPatInfoDetailAdapter bedMapPatInfoDetailAdapter;
    private BedMapBean.PatInfoListBean patInfoListBean;
    private PatTransRecAdapter patTransRecAdapter;

    private String jsonStr= "",jsonTrans="";
    private Map jsonMap;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_map_pat_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //状态栏 背景 默认蓝色
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        //toolbar type
//        setToolbarType(BaseActivity.ToolbarType.TOP);

        //toolbar 背景 默认蓝色
        setToolbarBackground(new ColorDrawable(0xffffffff));
        if (isSingleModel){
            hindMap();
        }
        //toolbar 导航按钮隐藏
        //        hideToolbarNavigationIcon();
        //toolbar 导航按钮显示 默认白色返回按钮
        //        showToolbarNavigationIcon();
        //返回按钮img设置
        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        //toolbar 中间标题 默认黑色

        Bundle bundle = getArguments();
        jsonStr = bundle.getString("jsonmap");
        jsonTrans = bundle.getString("jsonTrans");
        Gson gson = new Gson();
        jsonMap= gson.fromJson(jsonStr,Map.class);
        patInfoListBean =  gson.fromJson(jsonTrans,BedMapBean.PatInfoListBean.class);
        String bedinfo = jsonMap.get("bedCode").toString().isEmpty()?"未分床  "+jsonMap.get("name").toString():jsonMap.get("bedCode").toString()+"  "+jsonMap.get("name").toString();
        setToolbarCenterTitle(bedinfo);
        //toolbar 中间标题 内容 颜色 字号默认17
        //        setToolbarCenterTitle(getString(R.string.title_bedmap),0xffffffff,17);
        //toolbar 分割线
        setToolbarBottomLineVisibility(true);


        initView(view);
        initData();


    }

    private void initView(View view) {
        recPatInfo = view.findViewById(R.id.recy_bedmap_patientdetail);
        //提高展示效率
        recPatInfo.setHasFixedSize(true);
        //设置的布局管理
        recPatInfo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        bedMapPatInfoDetailAdapter = new BedMapPatInfoDetailAdapter(new ArrayList<>());
        recPatInfo.setAdapter(bedMapPatInfoDetailAdapter);

        recTrans = view.findViewById(R.id.recy_bedmap_pat_trans);     //提高展示效率
        recTrans.setHasFixedSize(true);
        //设置的布局管理
        recTrans.setLayoutManager(new LinearLayoutManager(getActivity()));
        patTransRecAdapter = new PatTransRecAdapter(new ArrayList<>());
        recTrans.setAdapter(patTransRecAdapter);
        patTransRecAdapter.setNewData(patInfoListBean.getTransRecList());

        rlHind=view.findViewById(R.id.rl_hindpatinfo);
        rlHind.setOnClickListener(v -> patInfoHind() );

    }
    private void patInfoHind(){
        if (recPatInfo.getVisibility()==View.VISIBLE){
            rlHind.setSelected(true);
            recPatInfo.setVisibility(View.GONE);
        }else {
            rlHind.setSelected(false);
            recPatInfo.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        Map<String,Object> map1 =(Map<String,Object>) jsonMap.get("patInfoDetail");
        if (map1 == null) {
            Gson gson = new Gson();
            jsonMap= gson.fromJson(jsonStr,Map.class);
            String regNo = jsonMap.get("regNo").toString();
            com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager.getPatInfoDetail(regNo, new com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager.GetPatDetailCallback() {
                @Override
                public void onSuccess(com.dhcc.nursepro.workarea.bedmap.bean.PatInfoDetailBean patInfoDetailBean) {
                    initPatMap(patInfoDetailBean.getPatInfoDetail());
                }
                @Override
                public void onFail(String code, String msg) {
                    hideLoadingTip();
                    showToast("error" + code + ":" + msg);
                }
            });
        } else {
            initPatMap(map1);
        }
    }

    private void initPatMap(Map<String,Object> map1) {
        for(String key : map1.keySet()){
            String value = map1.get(key).toString();
            paMap = new HashMap<>();
            paMap.put("title",key);
            paMap.put("content",value);
            if (!TextUtils.isEmpty(value)){
                list.add(paMap);
            }

        }
        bedMapPatInfoDetailAdapter.setNewData(list);
        bedMapPatInfoDetailAdapter.notifyDataSetChanged();
    }

    private LinearLayout getView(String key,String value){
        if (TextUtils.isEmpty(value)){
            value="无";
        }
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
//        layout.setBackgroundResource(R.drawable.vital_sign_border);

        int height = ConvertUtils.dp2px(110);
        int width = 0;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.weight = 1;
        params.setMarginStart(50);
        layout.setLayoutParams(params);

        TextView titleTV1 = new TextView(getContext());
        titleTV1.setText(key+":");
        titleTV1.setTextColor(Color.parseColor("#000000"));
        titleTV1.setTextSize(20);
        titleTV1.setGravity(Gravity.START);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, 0, 0, 0);//4个参数按顺序分别是左上右下
        titleTV1.setLayoutParams(titleParams);
        layout.addView(titleTV1);

        TextView titleTV2 = new TextView(getContext());
        titleTV2.setText(value);
        titleTV2.setTextColor(Color.parseColor("#000000"));
        titleTV2.setTextSize(20);
        titleTV2.setGravity(Gravity.START);
        LinearLayout.LayoutParams titleParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams1.setMargins(ConvertUtils.dp2px(10), 0, 0, 0);//4个参数按顺序分别是左上右下
        titleTV2.setLayoutParams(titleParams1);
        layout.addView(titleTV2);

        return layout;
    }

}
