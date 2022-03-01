package com.base.commlibs.log;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.R;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.log.adapter.NurLogAdapter;
import com.base.commlibs.log.adapter.NurLogBean;
import com.base.commlibs.log.fragment.NurLogDetailFragment;
import com.base.commlibs.log.fragment.NurLogListFragment;
import com.base.commlibs.utils.CommFile;
import com.base.commlibs.utils.CrashHandler;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.LocalTestManager;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 日志管理
 * @author:gaoruishan
 * @date:202021/11/25/10:43
 * @email:grs0515@163.com
 */
public class NurLogFragment extends BaseCommFragment {

    public static final String NAME = "name";
    public static final String TYPE = "type";
    //崩溃路径
    public static final String ROOT_CRASH = PathUtils.getExternalStoragePath() + "/" + CrashHandler.DHC_CRASH;
    private RecyclerView recyclerView;
    private NurLogAdapter logAdapter;
    private TextView tvData;
    private TextView tvErr;
    private TextView tvNull;
    private TextView tvCrash;

    @Override
    protected void initDatas() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        loadIsDir(ROOT_CRASH);
    }

    /**
     * 清空数据
     */
    public void clickClear() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        CommFile.delete(CommFile.ROOT_PATH);
        CommFile.delete(ROOT_CRASH);
        ViewUtils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadingTip();
                List<NurLogBean> mNurLogBeanList = new ArrayList<>();
                logAdapter.setNewData(mNurLogBeanList);
            }
        },1000);

    }
    private void loadData(String tag) {
        loadSp(tag);
//        loadIsDir(tag);
//        loadAll();
    }

    private void loadSp(String tag) {
        List<NurLogBean> mNurLogBeanList = new ArrayList<>();
        Set<String> log = DataCache.getLog();
        try {
            for (String fieldName : log) {
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                fieldName = "v" + AppUtils.getAppVersionCode() + "_" + fieldName;

                String fieldNameData = fieldName + "_" + tag;
                String path = LocalTestManager.getLogPath() + "/" + fieldNameData;
                boolean fileExists = CommFile.isDir(path);
                Log.e(TAG, "(NurLogFragment.java:45) " + path + ",dir=" + fileExists);
                if (fileExists) {
                    String logType = "正常";
                    mNurLogBeanList.add(new NurLogBean(logType, fieldNameData));
                }
            }
            logAdapter.setNewData(mNurLogBeanList);
        } catch (Exception e) {
            Log.e(TAG, "(NurLogFragment.java:57) " + e.toString());
        }
        hideLoadingTip();
    }

    private void loadIsDir(String path) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        CommFile.listFilesInDir(path,new SimpleCallBack<List<File>>() {
            @Override
            public void call(List<File> result, int type) {
                hideLoadingTip();
                List<NurLogBean> mNurLogBeanList = new ArrayList<>();
                if (result != null) {
                    for (File file : result) {
                        mNurLogBeanList.add(new NurLogBean(CrashHandler.DHC_CRASH,file.getName()));
                        Log.e(TAG,"(NurLogFragment.java:31) "+file.getName()+","+file.getPath());
                    }
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
        logAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NurLogBean nurLogBean = logAdapter.getData().get(position);
                if (CrashHandler.DHC_CRASH.equals(nurLogBean.getLogType())) {
                    Bundle bundle = new Bundle();
                    bundle.putString(NurLogFragment.NAME, CrashHandler.DHC_CRASH+"/"+nurLogBean.getName());
                    startFragment(NurLogDetailFragment.class,bundle);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString(NAME, nurLogBean.getName());
                    startFragment(NurLogListFragment.class, bundle);
                }
            }
        });
        f(R.id.ll_parent).setVisibility(View.VISIBLE);
        tvData = f(R.id.tv_data, TextView.class);
        tvData.setOnClickListener(this);
        tvErr = f(R.id.tv_err, TextView.class);
        tvErr.setOnClickListener(this);
        tvNull = f(R.id.tv_null, TextView.class);
        tvNull.setOnClickListener(this);
        tvCrash = f(R.id.tv_crash, TextView.class);
        tvCrash.setOnClickListener(this);
        f(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickClear();
            }
        });


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        tvData.setCompoundDrawables(null, null, null, null);
        tvErr.setCompoundDrawables(null, null, null, null);
        tvNull.setCompoundDrawables(null, null, null, null);
        tvCrash.setCompoundDrawables(null, null, null, null);
        Drawable drawable = getResources().getDrawable(R.drawable.base_img_blueline);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (v.getId() == R.id.tv_data) {
            tvData.setCompoundDrawables(null, null, null, drawable);
            loadData("data");
        }
        if (v.getId() == R.id.tv_err) {
            tvErr.setCompoundDrawables(null, null, null, drawable);
            loadData("err");
        }
        if (v.getId() == R.id.tv_null) {
            tvNull.setCompoundDrawables(null, null, null, drawable);
            loadData("null");
        }
        if (v.getId() == R.id.tv_crash) {
            tvCrash.setCompoundDrawables(null, null, null, drawable);
            loadIsDir(ROOT_CRASH);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_log;
    }
}
