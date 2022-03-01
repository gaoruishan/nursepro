package com.base.commlibs.log.fragment;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.R;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.log.NurLogFragment;
import com.base.commlibs.utils.CommFile;
import com.base.commlibs.utils.CrashHandler;
import com.base.commlibs.utils.LocalTestManager;
import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/11/25/13:52
 * @email:grs0515@163.com
 */
public class NurLogDetailFragment extends BaseCommFragment {

    private String name;
    private TextView tvTxt;
    private TextView tvUpload;
    private String nameDir;
    private String mResult;

    @Override
    protected void initDatas() {
        if (TextUtils.isEmpty(name)) {
            return;
        }
        if (name.contains(CrashHandler.DHC_CRASH)) {
            if (name.contains("/")) {
                nameDir = name.split("/")[1];
            }
            CommFile.readFile2String(PathUtils.getExternalStoragePath() + "/" + name, new SimpleCallBack<String>() {
                @Override
                public void call(String result, int type) {
                    mResult = result;
                    String contentRed = "<font color='red'>"+ AppUtils.getAppPackageName()+"</font>";
                    result = result.replaceAll(AppUtils.getAppPackageName() + "", contentRed);
                    tvTxt.setText(Html.fromHtml("" + result));
                }
            });
        }else {
            if (name.contains("/")) {
                nameDir = name.split("/")[0];
            }
            CommFile.read(LocalTestManager.getLogPath() + "/" + name, new SimpleCallBack<String>() {
                @Override
                public void call(String result, int type) {
                    mResult = result;
                    Log.e(TAG, "(NurLogDetailFragment.java:29) " + result);
                    tvTxt.setText("" + result);
                }
            });
        }
    }

    @Override
    protected void initViews() {
        setToolbarCenterTitle("日志");
        if (getArguments() != null) {
            name = getArguments().getString(NurLogFragment.NAME);
        }
        tvTxt = f(R.id.tv_txt, TextView.class);
        tvUpload = f(R.id.tv_upload, TextView.class);
        tvUpload.setOnClickListener(this);
        Log.e(TAG, "(NurLogDetailFragment.java:40) " + name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_upload) {
            uploadLog();
        }
    }

    public void uploadLog() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("method", nameDir);
        properties.put("text", ""+mResult);

        CommWebService.call("Log", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ToastUtils.showShort(""+jsonStr);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_nur_log_detail;
    }
}
