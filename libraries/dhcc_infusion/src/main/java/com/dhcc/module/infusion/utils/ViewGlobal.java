package com.dhcc.module.infusion.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.UniversalActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.UserUtil;
import com.blankj.utilcode.util.ActivityUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.bean.MainConfigBean;
import com.dhcc.res.LogCatLayout;
import com.dhcc.res.nurse.bean.ConfigBean;
import com.noober.background.view.BLTextView;

import java.util.List;


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
     * 门诊全局View
     * @param mContext
     * @return
     */
    public static View createGlobal(Activity mContext) {
        ((BaseActivity) mContext).addGlobalView(ViewGlobal.createInfusionGlobal(mContext));
        View logcatGlobal = createLogcatGlobal(mContext);
        if (logcatGlobal != null) {
            return logcatGlobal;
        }
        View infusionGlobal = createInfusionGlobal(mContext);
        if (infusionGlobal != null) {
            return infusionGlobal;
        }
        return null;
    }

    /**
     * 添加输液右侧导航
     * @param context
     * @return
     */
    public static View createInfusionGlobal(final Context context) {
        //判断是否开启
        if (!UserUtil.isShowGlobalView()) {
            return null;
        }
        String fgName = getCurActivityFragmentName();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        if (json != null && json.getMainList() != null) {
            for (final ConfigBean bean : json.getMainList()) {
                BLTextView tvName = (BLTextView) LayoutInflater.from(context).inflate(R.layout.dhcc_infusion_global_view, linearLayout, false);
                int dimen = bean.getName().length() > 2 ? R.dimen.sp_13 : R.dimen.sp_15;
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(dimen));
                tvName.setText(bean.getName());
                //选中
                tvName.setSelected(fgName.equals(bean.getFragment()));
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
     * 添加Logcat
     * @param mContext
     * @return
     */
    public static View createLogcatGlobal(Activity mContext) {
        //判断是否开启
        if (!UserUtil.isShowLogcatView()) {
            return null;
        }
        LogCatLayout logCatLayout = new LogCatLayout(mContext);
        logCatLayout.setLayoutParams(new LinearLayout.LayoutParams((int) (getScreenSize(mContext).x * 0.6), (int) (getScreenSize(mContext).y * 0.5)));
        return logCatLayout;
    }

    public static String getCurActivityFragmentName() {
        //获取当前Activity的Fragment
        Activity activity = ActivityUtils.getTopActivity();
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            List<Fragment> fragments = appCompatActivity.getSupportFragmentManager().getFragments();
            if (fragments.size() > 0) {
                Fragment fg = fragments.get(0);
                return fg.getClass().getName();
            }
        }
        return "";
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

    /**
     * 获取屏幕尺寸
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return new Point(display.getWidth(), display.getHeight());
        } else {
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }
}
