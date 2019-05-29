package com.base.commlibs.annotations;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注入工具类
 * @author:gaoruishan
 * @date:202019-05-09/11:43
 * @email:grs0515@163.com
 */
public class FindInject {

    private static final String TAG = FindInject.class.getSimpleName();

    /**
     * Activity注入
     * @param activity 当前Activity
     */
    public static void bind(Activity activity) {
        injectView(activity, null);
    }

    private static void injectView(Object obj, View v) {
        long start = System.currentTimeMillis();
        Field[] fArray = obj.getClass().getDeclaredFields();
        for (Field field : fArray) {
            findView(obj, field, v);
            findFragment(obj, field);
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method m : methods) {
            findEvent(obj, m, v);
        }
        Log.e(TAG,"耗时: "+(System.currentTimeMillis() - start)+"毫秒");
    }

    /**
     * 寻找View注入
     * @param obj
     * @param field
     */
    private static void findView(Object obj, Field field, View v) {
        try {
            FindView annotation = field.getAnnotation(FindView.class);
            if (annotation != null) {
                int viewId = annotation.value();
                View view;
                if (v != null) {
                    view = findById(v, viewId);
                } else {
                    view = findById(obj, viewId);
                }
                if (view != null) { // 赋值
                    field.setAccessible(true);
                    field.set(obj, view);
                }
                // FindViewUtil: Field: android.widget.Button,button
                Log.e(TAG, "Field: " + field.getType().getName() + "," + field.getName());
            }
        } catch (Exception e) {
            error(e);
        }
    }

    /**
     * 寻找fragment注入
     * @param obj
     * @param field
     */
    private static void findFragment(Object obj, Field field) {
        String name = field.getType().getName();
        FindFragment annotation = field.getAnnotation(FindFragment.class);
        if (annotation != null) {
            int viewId = annotation.value();
            Fragment fragment = addFragment((FragmentActivity) obj, viewId, name);
            try {
                if (fragment != null) {// 赋值
                    field.setAccessible(true);
                    field.set(obj, fragment);
                }
            } catch (IllegalAccessException e) {
                error(e);
            }
            Log.e(TAG, "Fragment: " + field.getType().getName() + "," + field.getName());
        }
    }

    /**
     * 寻找Event注入
     * @param obj
     * @param m
     * @param v
     */
    private static void findEvent(final Object obj, final Method m, View v) {
        try {
            FindEvent annotation = m.getAnnotation(FindEvent.class);
            if (annotation != null) {
                Log.e(TAG, "Method: " + m.getName());
                int viewId = annotation.value();
                View view;
                if (v != null) {
                    view = findById(v, viewId);
                } else {
                    view = findById(obj, viewId);
                }
                if (view != null) {
                    View.OnClickListener onClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {// 调用'方法'
                                m.invoke(obj);
                            } catch (Exception e) {
                                try {
                                    m.invoke(obj, v);
                                } catch (Exception e1) {
                                    error(e);
                                }
                            }
                        }
                    };
                    view.setOnClickListener(onClickListener);
                }
            }
        } catch (Exception e) {
            error(e);
        }


    }

    /**
     * 根据类型findViewById
     * @param o
     * @param viewId
     * @return
     */
    private static View findById(Object o, int viewId) {
        if (o instanceof Activity) {
            return ((Activity) o).findViewById(viewId);
        }
        if (o instanceof View) {
            return ((View) o).findViewById(viewId);
        }
        return null;
    }

    private static void error(Exception e) {
        Log.e(TAG, "Exception: " + e.getMessage());
    }

    /**
     * 跳转指定fragment
     * @param activity     需要继承FragmentActivity
     * @param fragmentName Fragment类名
     */
    public static Fragment addFragment(Activity activity, @IdRes int containerViewId, String fragmentName, Bundle... args) {
        if (!TextUtils.isEmpty(fragmentName) && activity != null) {
            try {
                Class<?> aClass = Class.forName(fragmentName);
                Fragment fragment = addFragment(activity, containerViewId, aClass, args);
                return fragment;
            } catch (Exception e) {
                error(e);
            }
        }
        return null;
    }

    /**
     * 添加Fragment
     * @param activity        当前Activity
     * @param containerViewId FragmentLayout布局的Id
     * @param aClass          Fragment.class
     * @param args            Bundle数据
     * @return
     */
    public static Fragment addFragment(Activity activity, @IdRes int containerViewId, Class<?> aClass, Bundle... args) {
        try {
            Object o = aClass.newInstance();
            if (o instanceof Fragment) {
                Fragment fragment = (Fragment) o;
                if (args != null && args.length > 0) {
                    fragment.setArguments(args[0]);
                }
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                            .add(containerViewId, fragment, fragment.getClass().getName())
                            .commitAllowingStateLoss();
                }
                return fragment;
            }
        } catch (Exception e) {
            error(e);
        }
        return null;
    }

    /**
     * Fragment注入
     * @param fragment 当前Fragment
     * @param view Fragment的根View
     */
    public static void bind(Fragment fragment, View view) {
        injectView(fragment, view);
    }

}
