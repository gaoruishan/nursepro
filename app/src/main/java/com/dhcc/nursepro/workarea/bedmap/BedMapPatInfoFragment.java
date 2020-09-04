package com.dhcc.nursepro.workarea.bedmap;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.adapter.BedMapPatInfoDetailAdapter;
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
    private RecyclerView recPatInfo;
    private BedMapPatInfoDetailAdapter bedMapPatInfoDetailAdapter;

    private String jsonStr= "";
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
        if (bSingleModel){
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
        Gson gson = new Gson();
        jsonMap= gson.fromJson(jsonStr,Map.class);
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
        recPatInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        bedMapPatInfoDetailAdapter = new BedMapPatInfoDetailAdapter(new ArrayList<>());
        recPatInfo.setAdapter(bedMapPatInfoDetailAdapter);

    }

    private void initData() {
        Map<String,Object> map1 =(Map<String,Object>) jsonMap.get("patInfoDetail");
        for(String key : map1.keySet()){
            String value = map1.get(key).toString();
            paMap = new HashMap<>();
            paMap.put("title",key);
            paMap.put("content",value);
            list.add(paMap);
        }
        bedMapPatInfoDetailAdapter.setNewData(list);
        bedMapPatInfoDetailAdapter.notifyDataSetChanged();
    }

}
