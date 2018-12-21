package com.dhcc.nursepro.workarea.nurrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecord.adapter.NurRecordModelListAdapter;
import com.dhcc.nursepro.workarea.nurrecord.api.NurRecordManager;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.nurrecord
 * <p>
 * author Q
 * Date: 2018/12/21
 * Time:9:42
 */
public class NurRecordModellistFragmen extends BaseFragment {
    private RecyclerView recMl;
    private NurRecordModelListAdapter  modelListAdapter;
    private List<NurRecordModelListBean.MenuListBean> ModelList = new ArrayList<>();
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nurrecordmodellist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle("模板列表", 0xffffffff, 17);
        initView(view);
        initData();
        initAdapter();

    }
    private void initView(View view) {

        recMl = view.findViewById(R.id.recy_modellist);

        //提高展示效率
        recMl.setHasFixedSize(true);
        //设置的布局管理
        recMl.setLayoutManager(new LinearLayoutManager(getActivity()));

        recMl.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        modelListAdapter = new NurRecordModelListAdapter(new ArrayList<NurRecordModelListBean.MenuListBean>());
        modelListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.tv_modellist) {
                    startFragment(NurRecordFragment.class);
                }
            }
        });
        recMl.setAdapter(modelListAdapter);

    }
    private void initData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("locId", "197");
        map.put("episodeId", "11");

        NurRecordManager.getModelList(map, "getModelList", new NurRecordManager.getModelListCallBack() {
            @Override
            public void onSuccess(NurRecordModelListBean modelDetailBean) {

                ModelList = modelDetailBean.getMenuList();
                modelListAdapter.setNewData(ModelList);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }


}
