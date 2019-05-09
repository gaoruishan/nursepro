package com.dhcc.nursepro.workarea.nurrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.nurrecord.adapter.NurRecordMenuListAdapter;
import com.dhcc.nursepro.workarea.nurrecord.api.NurRecordManager;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * com.dhcc.nursepro.workarea.nurrecord
 * <p>
 * author Q
 * Date: 2018/12/21
 * Time:9:42
 */
public class NurRecordModellistFragmen extends BaseFragment {
    private RelativeLayout relativeLayout;
    private RecyclerView recMl;
    private NurRecordMenuListAdapter menuListAdapter;
    private List<NurRecordModelListBean.MenuListBean> ModelList = new ArrayList<>();
    private String episodeId = "";
    private String scanInfo = "";

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        switch (Objects.requireNonNull(intent.getAction())) {
            case Action.DEVICE_SCAN_CODE:
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                scanInfo = bundle.getString("data");
                getScanInfo();

                break;
            default:
                break;
        }

    }

    private void getScanInfo() {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> map = new HashMap<>();
        map.put("episodeId", episodeId);
        map.put("barcode", scanInfo);
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        map.put("userDeptId", spUtils.getString(SharedPreference.LOCID));
        NurRecordManager.getScanInfo(map, "getScanInfo", new NurRecordManager.GetScanInfoCallback() {
            @Override
            public void onSuccess(ScanResultBean scanResultBean) {
                relativeLayout.setVisibility(View.GONE);
                episodeId = scanResultBean.getPatInfo().getEpisodeID();
                menuListAdapter.setEpisodeId(episodeId);
                initData();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
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

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nurrecordmodellist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle("护理病历", 0xffffffff, 17);
        initView(view);
        initAdapter();

    }

    private void initView(View view) {

        relativeLayout = view.findViewById(R.id.rl_modellist_scan);

        recMl = view.findViewById(R.id.recy_modellist);

        //提高展示效率
        recMl.setHasFixedSize(true);
        //设置的布局管理
        recMl.setLayoutManager(new LinearLayoutManager(getActivity()));

        recMl.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        menuListAdapter = new NurRecordMenuListAdapter(new ArrayList<>(), this);
        recMl.setAdapter(menuListAdapter);

    }


}
