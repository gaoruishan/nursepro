package com.dhcc.module.infusion.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commlibs.UniversalActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.ActivityUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.bean.MainConfigBean;
import com.dhcc.res.nurse.bean.ConfigBean;
import com.noober.background.view.BLTextView;


/**
 * 动态加载全局的View
 * @author:gaoruishan
 * @date:202019-11-04/11:26
 * @email:grs0515@163.com
 */
public class ViewGlobal {
    private static final String TAG = ViewGlobal.class.getSimpleName();
    private static final String ACTIVITY = "com.base.commlibs.UniversalActivity";

    /**
     * 添加
     * @param context
     * @return
     */
    public static View createInfusionGlobal(final Context context) {
        //判断是否开启
        if (!UserUtil.isShowGlobalView()) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        if (json != null && json.getMainList() != null) {
            for (final ConfigBean bean : json.getMainList()) {
                BLTextView tvName = (BLTextView) LayoutInflater.from(context).inflate(R.layout.dhcc_infusion_global_view,linearLayout,false);
                tvName.setText(bean.getName());
                linearLayout.addView(tvName);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAndFinishFragment(bean.getFragment(), context);
                    }
                });
            }
        }

        return linearLayout;
    }

    /**
     * 结束其他,再启动
     * @param tag
     * @param context
     */
    public static void startAndFinishFragment(String tag, Context context) {
        try {
            //fragment 不为空, 且必须继承BaseFragment
            if (!TextUtils.isEmpty(tag)) {
                //结束其他com.base.commlibs.UniversalActivity
                for (Activity activity : ActivityUtils.getActivityList()) {
                    if (ACTIVITY.equals(activity.getLocalClassName())) {
                        activity.finish();
                    }
                }
                Intent intent = new Intent(context, UniversalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(UniversalActivity.RootFragmentClassName, tag);
                Bundle bundle = new Bundle();
                intent.putExtra(UniversalActivity.RootFragmentArgs, bundle);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
