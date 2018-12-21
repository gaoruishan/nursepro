package com.dhcc.nursepro.workarea.nurrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.nurrecord.adapter.NurRecordMenuListAdapter;
import com.dhcc.nursepro.workarea.nurrecord.api.NurRecordManager;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurrecord
 * <p>
 * author Q
 * Date: 2018/12/21
 * Time:9:42
 */
public class NurRecordModellistFragmen extends BaseFragment {
    private RecyclerView recMl;
    private NurRecordMenuListAdapter menuListAdapter;
    private List<NurRecordModelListBean.MenuListBean> ModelList = new ArrayList<>();
    private String episodeId = "11";
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
        menuListAdapter = new NurRecordMenuListAdapter(new ArrayList<NurRecordModelListBean.MenuListBean>(),this,episodeId);
//        menuListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                if (view.getId() == R.id.tv_modellist) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("EmrCode",ModelList.get(position).getMenuCode());
//                    bundle.putString("episodeId",episodeId );
//                    startFragment(NurRecordFragment.class);
//                }
//            }
//        });
        recMl.setAdapter(menuListAdapter);

    }
    private void initData() {

        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("locId", spUtils.getString(SharedPreference.LOCID));
        map.put("episodeId", episodeId);

        NurRecordManager.getModelList(map, "getModelList", new NurRecordManager.getModelListCallBack() {
            @Override
            public void onSuccess(NurRecordModelListBean modelDetailBean) {

                ModelList = modelDetailBean.getMenuList();
                menuListAdapter.setNewData(ModelList);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }


}
