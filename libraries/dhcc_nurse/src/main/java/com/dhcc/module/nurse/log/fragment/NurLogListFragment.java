package com.dhcc.module.nurse.log.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.utils.LocalTestManager;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.log.NurLogFragment;
import com.dhcc.module.nurse.log.adapter.NurLogAdapter;
import com.dhcc.module.nurse.log.adapter.NurLogBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志管理
 * @author:gaoruishan
 * @date:202021/11/25/10:43
 * @email:grs0515@163.com
 */
public class NurLogListFragment extends BaseCommFragment {

    private RecyclerView recyclerView;
    private NurLogAdapter logAdapter;
    private String name;

    @Override
    protected void initDatas() {
        if(TextUtils.isEmpty(name)){
        	return;
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);
        LocalTestManager.getLogFilesInDir(LocalTestManager.getLogPath()+"/"+name,new SimpleCallBack<List<File>>() {
            @Override
            public void call(List<File> result, int type) {
                hideLoadingTip();
                List<NurLogBean> mNurLogBeanList = new ArrayList<>();
                for (File file : result) {
                    String logType = "";
                    if (file.getName().contains("data")) {
                        logType = "正常";
                    }
                    if (file.getName().contains("err")) {
                        logType = "错误";
                    }
                    if (file.getName().contains("null")) {
                        logType = "空数据";
                    }
                    mNurLogBeanList.add(new NurLogBean(logType,file.getName()));
                    Log.e(TAG,"(NurLogFragment.java:31) "+file.getName()+","+file.getPath());
                }

                logAdapter.setNewData(mNurLogBeanList);
            }
        });
    }

    @Override
    protected void initViews() {
        recyclerView = RecyclerViewHelper.get(mContext, R.id.rv_list);
        logAdapter = new NurLogAdapter(null);
        recyclerView.setAdapter(logAdapter);
        setToolbarCenterTitle("日志");
        if (getArguments() != null) {
            name = getArguments().getString(NurLogFragment.NAME);
        }
        logAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NurLogBean nurLogBean = logAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString(NurLogFragment.NAME, name+"/"+nurLogBean.getName());
                startFragment(NurLogDetailFragment.class,bundle);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_log;
    }
}
