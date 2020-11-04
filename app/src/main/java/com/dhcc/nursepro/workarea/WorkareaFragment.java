package com.dhcc.nursepro.workarea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.LocalTestManager;
import com.base.commlibs.utils.SchDateTimeUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareaadapter.WorkAreaAdapter;
import com.dhcc.nursepro.workarea.workareaapi.WorkareaApiManager;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;
import com.dhcc.nursepro.workarea.workareautils.WorkareaMainConfig;
import com.dhcc.nursepro.workarea.workareautils.WorkareaOrdExeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private WorkAreaAdapter workAreaAdapter;
    private WorkareaMainConfig workareaMainConfig = new WorkareaMainConfig();
    private ArrayList listMainConfig = new ArrayList();
    private WorkAreaReceiver workAreaReceiver = new WorkAreaReceiver();
    private IntentFilter workAreaFilter = new IntentFilter();
    private SPUtils spUtils = SPUtils.getInstance();
    private String locId = "";
    private String groupId = "";
    private WorkareaOrdExeUtil workareaOrdExeUtil;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
//        hindMap();


        initView(view);
        initData();
        workareaOrdExeUtil = new WorkareaOrdExeUtil(getActivity());
    }

    private void initView(View view) {
        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAreaAdapter = new WorkAreaAdapter(new ArrayList<HashMap>());
        recConfig.setAdapter(workAreaAdapter);
        workAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Map map = (Map) adapter.getData().get(position);
                    if (map.get("fragName")==null){
                        showToast("该功能暂未开发");
                    }else {
                        Class<? extends BaseFragment> OrderExecuteFragmentClass = (Class<? extends BaseFragment>) Class.forName(map.get("fragName").toString());
                        startFragment(OrderExecuteFragmentClass);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        locId = spUtils.getString(SharedPreference.LOCID);
        groupId = spUtils.getString(SharedPreference.GROUPID);
        WorkareaApiManager.getMainConfig(new WorkareaApiManager.GetMainconfigCallback() {
            @Override
            public void onSuccess(MainConfigBean mainConfigBean) {
                if (mainConfigBean.getSchStDateTime() != null && mainConfigBean.getSchEnDateTime() != null) {
                    SchDateTimeUtil.putSchStartEndDateTime(mainConfigBean.getSchStDateTime(),mainConfigBean.getSchEnDateTime());

                    if (StringUtils.isEmpty(mainConfigBean.getCurDateTime())) {
                        spUtils.put(SharedPreference.CURDATETIME, mainConfigBean.getSchEnDateTime());
                    } else {
                        spUtils.put(SharedPreference.CURDATETIME, mainConfigBean.getCurDateTime());
                    }
                }
                SharedPreference.FRAGMENTARY = new ArrayList();
                Map map = new HashMap();
                map.put("code","Main");
                map.put("desc","主页");
                map.put("fragName",WorkareaFragment.class.getName());
                map.put("fragicon",R.drawable.icon_workarea);
                SharedPreference.FRAGMENTARY.add(map);

                listMainConfig = workareaMainConfig.getMainCoinfgList(mainConfigBean);
                workAreaAdapter.setNewData(listMainConfig);
                SPUtils.getInstance().put(SharedPreference.BLOODSCANTIMES, mainConfigBean.getScantimes());
                DataCache.saveJson(mainConfigBean, SharedPreference.DATA_MAIN_CONFIG);
            }
            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            workareaOrdExeUtil = new WorkareaOrdExeUtil(getActivity());
            if (!(locId.equals(SPUtils.getInstance().getString(SharedPreference.LOCID)) && groupId.equals(SPUtils.getInstance().getString(SharedPreference.GROUPID)))) {
                initData();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (workAreaReceiver != null) {
            workAreaFilter.addAction(Action.DEVICE_SCAN_CODE);
            Objects.requireNonNull(getContext()).registerReceiver(workAreaReceiver, workAreaFilter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (workAreaReceiver != null) {
            Objects.requireNonNull(getContext()).unregisterReceiver(workAreaReceiver);
        }
    }

    public class WorkAreaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                workareaOrdExeUtil.setScanInfo(bundle.getString("data"));
                workareaOrdExeUtil.getScanInfo();
            }
        }
    }
}
