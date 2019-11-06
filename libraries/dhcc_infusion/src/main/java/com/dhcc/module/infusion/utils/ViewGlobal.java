package com.dhcc.module.infusion.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.UniversalActivity;
import com.dhcc.module.infusion.R;


/**
 * @author:gaoruishan
 * @date:202019-11-04/11:26
 * @email:grs0515@163.com
 */
public class ViewGlobal {
    private static final String TAG = ViewGlobal.class.getSimpleName();

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
                    try {
                        //fragment 不为空, 且必须继承BaseFragment
                        if (!TextUtils.isEmpty((String) tag)) {
                            Intent intent = new Intent(context, UniversalActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                            intent.putExtra(UniversalActivity.RootFragmentClassName, (String) tag);
                            intent.putExtra(UniversalActivity.RootFragmentArgs, "");
                            context.startActivity(intent);
                        }
//                        for (BaseActivity activity : BaseApplication.getActivities()) {
//                            Log.e(TAG,"(ViewGlobal.java:47) "+activity.getComponentName());
//                            Log.e(TAG,"(ViewGlobal.java:47) "+activity.getLocalClassName());
//                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }
                }
            });
        }

        return view;
    }
}
