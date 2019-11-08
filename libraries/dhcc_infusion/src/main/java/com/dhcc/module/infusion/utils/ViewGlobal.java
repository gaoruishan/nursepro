package com.dhcc.module.infusion.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.UniversalActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.dhcc.module.infusion.R;


/**
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
        LinearLayout view = (LinearLayout) View.inflate(context, R.layout.infusion_global_view, null);
        for (int i = 0; i < view.getChildCount(); i++) {
            TextView child = (TextView) view.getChildAt(i);
            final Object tag = child.getTag();
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAndFinishFragment((String) tag, context);
                }
            });
        }

        return view;
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
