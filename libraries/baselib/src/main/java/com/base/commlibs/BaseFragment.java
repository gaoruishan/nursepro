package com.base.commlibs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.base.BaseBottomLoadingView;
import com.base.commlibs.base.BaseFullLoadingView;
import com.base.commlibs.base.BasePushDialog;
import com.base.commlibs.base.BaseTopLoadingView;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.base.commlibs.BaseActivity.LoadingType.FULL;

/**
 * Created by levis on 2018/6/5.
 */
public class BaseFragment extends Fragment {
    protected static final String TAG = BaseFragment.class.getSimpleName();
    // 视图组,负责管理加载、加载失败等视图的显隐
    protected FrameLayout mContainer;
    protected LinearLayout linearLayoutTitle,llTitTop,llTitRight;
    protected TextView tvName;
    protected View mContainerChild;
    protected FrameLayout mContainerMaskContainer;
    // 加载FullLoadingView
    protected BaseFullLoadingView mFullLoadingView;
    // 加载TopLoadingTip
    protected BaseTopLoadingView mTopLoadingView;
    // 加载BottomLoadingTip
    protected BaseBottomLoadingView mBottomLoadingView;
    protected BaseReceiver mReceiver = new BaseReceiver();
    protected IntentFilter mfilter = new IntentFilter();
    // 用于发起数据请求时的标记
    private String mRequestTag;
    // 加载LoadingDialog的类型
    private BaseActivity.LoadingType mLoadingType = FULL;
    // 记录是否显示了LoadingTip
    private boolean isLoadingTipShowing;
    // 记录是否显示了LoadFailTip
    private boolean isLoadFailTipShowing;


    private Intent broadCastIntent = new Intent();
    public String singleEpisodeId = "";
    public String singleRegNo = "";
    public ArrayList<String> listRegNo = new ArrayList<>();
    public Boolean bSingleModel = SPUtils.getInstance().getString(Action.SINGLEMODEL).equals("1");

    /**
     * 判断是否大于等于LOLLIPOP
     *
     * @return true，表示大于等于LOLLIPOP
     */
    public static boolean isAboveLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setlitener();

        mfilter.addAction(Action.DEVICE_SCAN_CODE);
        mfilter.addAction(Action.NEWMESSAGE_SERVICE);
        mfilter.addAction(Action.ORDER_HANDLE_TYPE);
//        mfilter.addAction(Action.ORDER_HANDLE_REFUSE);
//        mfilter.addAction(Action.ORDER_HANDLE_COMPLETE);
        mfilter.addAction(Action.SKIN_TEST_YANG);
        mfilter.addAction(Action.SKIN_TEST_YIN);
        mfilter.addAction(Action.DOSING_REVIEW);
        mfilter.addAction(Action.TOUR_DOSINGID);
        mfilter.addAction(Action.DRUG_RLREG);
        mfilter.addAction(Action.NUR_RECORD_XML_VIEW);
    }

    /**
     * 通过Class名 结束Fragment
     * @param cls
     */
    public void finishFragment(Class<? extends BaseFragment> cls) {
        for (Activity activity : ActivityUtils.getActivityList()) {
            if (activity instanceof AppCompatActivity) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
                List<Fragment> fragments = appCompatActivity.getSupportFragmentManager().getFragments();
                for (Fragment fg : fragments) {
                    if (fg.getActivity() != null && fg.getClass().getName().equals(cls.getName())) {
                        fg.getActivity().finish();
                        return;
                    }
                }
            }
        }
    }
    public void getScanMsg(Intent intent) {

        if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = bundle.getString("data");
            //               .replace("||","-");
            intent.putExtra("data", scanInfo);
            setMsgToSingleActivity(scanInfo);
        }

    }

    /**
     * BaseFragment通过广播向SingleMainActivity传递msg
     */
    public void setMsgToSingleActivity(String msg) {
        broadCastIntent.setAction(Action.SETSINGLEMSG);
        broadCastIntent.putExtra("data", msg);
        getActivity().sendBroadcast(broadCastIntent);
    }

    /**
     * SIngleMainActivity通过此方法向BaseFragment单人模式传递数据
     */
    public void setMsgToSingleBaseFragment(String episodeId) {
        showToast(episodeId);
    }
    /**
     * 所属Activity中onCreate之前调用(只适用于UniversalActivity及其子类)
     *
     * @param activity           所属Activity(只适用于UniversalActivity及其子类)
     * @param savedInstanceState onCreate中的savedInstanceState
     */
    protected void onPreActivityCreate(@NonNull UniversalActivity activity,
                                       @Nullable Bundle savedInstanceState) {
        // 所属UniversalActivity中onCreate之前调用


        mfilter.addAction(Action.DEVICE_SCAN_CODE);
        mfilter.addAction(Action.NEWMESSAGE_SERVICE);
        mfilter.addAction(Action.ORDER_HANDLE_TYPE);
//        mfilter.addAction(Action.ORDER_HANDLE_REFUSE);
//        mfilter.addAction(Action.ORDER_HANDLE_COMPLETE);
        mfilter.addAction(Action.SKIN_TEST_YANG);
        mfilter.addAction(Action.SKIN_TEST_YIN);
        mfilter.addAction(Action.DOSING_REVIEW);
        mfilter.addAction(Action.TOUR_DOSINGID);
        mfilter.addAction(Action.DRUG_RLREG);
        mfilter.addAction(Action.NUR_RECORD_XML_VIEW);
    }

    public Toolbar getToolbar() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
           return  ((BaseActivity) activity).getToolbar();
        }
        return null;
    }

    /**
     * 设置StatusBarBackgroundView是否需要显示
     *
     * @param show
     * @param color
     */
    public void setStatusBarBackgroundViewVisibility(boolean show, int color) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setStatusBarBackgroundViewVisibility(show, color);
        }
    }

    /**
     * 设置Toolbar的背景
     *
     * @param background
     */
    public void setToolbarBackground(Drawable background) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarBackground(background);
        }
    }

    /**
     * 设置Toolbar的导航按钮
     *
     * @param resId
     */
    public void showToolbarNavigationIcon(int resId) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToolbarNavigationIcon(resId);
        }
    }

    /**
     * 显示Toolbar的导航按钮
     */
    public void showToolbarNavigationIcon() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToolbarNavigationIcon();
        }
    }

    /**
     * 隐藏Toolbar的导航按钮
     */
    public void hideToolbarNavigationIcon() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideToolbarNavigationIcon();
        }
    }

    /**
     * 设置为给定的ToolbarType
     *
     * @param type
     */
    public void setToolbarType(BaseActivity.ToolbarType type) {
        if (bSingleModel){
            type = BaseActivity.ToolbarType.HIDE;
            llTitTop.setVisibility(View.VISIBLE);
        }else {
            llTitTop.setVisibility(View.GONE);
        }
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarType(type);
        }
    }

    /**
     * 设置Toolbar的右边操作动作按钮
     *
     * @param menuId
     */
    public void setToolbarMenu(@MenuRes int menuId) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarMenu(menuId);
        }
    }

    /**
     * 设置Toolbar居中的标题
     *
     * @param title
     */
    public void setToolbarCenterTitle(CharSequence title) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarCenterTitle(title);
        }
    }

    /**
     * 设置Toolbar居中的标题
     *
     * @param title
     * @param color 如:0xffcccccc
     * @param size  单位:DIP
     */
    public void setToolbarCenterTitle(CharSequence title, int color, int size) {
        if (bSingleModel){
            tvName.setLines(1);
            tvName.setMaxLines(1);
            tvName.setEllipsize(TextUtils.TruncateAt.END);
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size-2);
            tvName.setText(title);
            tvName.setGravity(Gravity.CENTER);
        }
        setMsgToSingleActivity(title.toString());
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarCenterTitle(title, color, size);
        }
    }

    public void setlitener() {
        Activity activity = getActivity();
        String fragName = this.getClass().getName();
        SharedPreference.Fragment_show = fragName;
        if (fragName.contains("WorkareaFragment")||fragName.contains("MessageFragment")||fragName.contains("SettingFragment")){
            SharedPreference.LastActivity = null;
        }else {
            if (activity != null && activity instanceof BaseActivity) {
                ((BaseActivity) activity).setListener(new BaseActivity.Listener() {
                    @Override
                    public void changMap(String map) {
                        if (bSingleModel){
                            broadCastIntent.setAction(Action.SINGLEMAP);
                            broadCastIntent.putExtra("data", map);
                            getActivity().sendBroadcast(broadCastIntent);
                        }else {
                            try {
                                Class<? extends BaseFragment> aClass = (Class<? extends BaseFragment>) Class.forName(map);
                                if (!aClass.getName().contains("WorkareaFragment")){
                                    startFragment(aClass);
                                }
                                if (SharedPreference.LastActivity == null){
                                    SharedPreference.LastActivity = (BaseActivity) activity;
                                }else {
                                    SharedPreference.LastActivity.finish();
                                    SharedPreference.LastActivity = (BaseActivity) activity;
                                }
                                if (aClass.getName().contains("WorkareaFragment")){
                                    startActivity(new Intent(getActivity(),Class.forName("com.dhcc.nursepro.Activity.MainActivity")));
                                    SharedPreference.LastActivity = null;
                                    finish();
                                };
//                        finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }

        }

    }
    public void hindMap() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).hindMap();
        }
    }
    public void setHindBottm(int bottom) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setTvHindMapBottom(bottom);
        }
    }
    /**
     * 设置Toolbar居中的自定义视图
     *
     * @param view
     */
    public void setToolbarCenterCustomView(View view) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarCenterCustomView(view);
        }
    }

    /**
     * 设置Toolbar左边自定义视图
     *
     * @param view
     */
    public void setToolbarLeftCustomView(View view) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarLeftCustomView(view);
        }
    }

    /**
     * 设置Toolbar右边自定义视图
     *
     * @param view
     */
    public void setToolbarRightCustomView(View view) {
        if (bSingleModel){
            llTitRight.removeAllViews();
            llTitRight.addView(view);
        }
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarRightCustomView(view);
        }
    }
    /**
     * 设置Toolbar右边自定义视图-单患者模式下也可以显示
     *
     * @param view
     */
    public void setToolbarRightCustomViewSingleShow(View view) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarRightCustomView(view);
        }
    }
    /**
     * 设置Toolbar-BottomLine是否显示
     *
     * @param show true:显示BottomLine;false:不显示
     */
    public void setToolbarBottomLineVisibility(boolean show) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setToolbarBottomLineVisibility(show);
        }
    }

    /**
     * 所属的Activity(仅支持UniversalActivity及其子类)Finish之前时调用
     * 在这个方法里面,如果调用了activity.setResult,
     * 将会替换到finish(Bundle data)中设置的Result数据
     */
    public void onPreFinish(UniversalActivity activity) {
        // nothing
    }

    /**
     * Toolbar右边动作按钮回调
     *
     * @param item
     * @return
     */

    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    /**
     * 每当网络断开后,回调到这里
     */
    public void onNetworkDisconnected() {
        //Logger.d(getClass().getName() + ": NetworkDisconnected");
    }

    /**
     * 每当网络连接后,回调到这里
     *
     * @param type 连接后的网络类型  one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     *             ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     *             ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     *             types defined by {@link ConnectivityManager}
     */
    public void onNetworkConnected(int type) {
        //Logger.d(getClass().getName() + ": NetworkConnected Type: " + type);
    }

    /**
     * 安全方式调用getString
     *
     * @param resId
     * @return
     */
    public String getStringSafe(@StringRes int resId, Object... formatArgs) {
        if (getActivity() == null) {
            return "";
        } else {
            return getString(resId, formatArgs);
        }
    }

    public void onKeyBoardOpen(int heightDiff) {
    }

    public void onKeyBoardClose() {
    }

    /**
     * 查找View
     *
     * @param resId View的id
     * @param t     指定View类类型
     * @return
     */
    @SafeVarargs
    protected final <T extends View> T f(@IdRes int resId, Class<T>... t) {
        if (mContainerChild != null) {
            return mContainerChild.findViewById(resId);
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRequestTag = getClass().getName() + "@" + UUID.randomUUID();
        mContainer = new FrameLayout(getActivity());
        linearLayoutTitle = (LinearLayout) View.inflate(getActivity(), R.layout.activity_base_linelayout, null);
        llTitTop = linearLayoutTitle.findViewById(R.id.ll_title_top);
        llTitRight = linearLayoutTitle.findViewById(R.id.ll_title_right);
        tvName = linearLayoutTitle.findViewById(R.id.tv_title_name);

        mContainerChild = onCreateViewByYM(inflater, container, savedInstanceState);
        if (mContainerChild != null && mContainerChild.getParent() == null) {
            linearLayoutTitle.addView(mContainerChild,
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT));
            mContainer.addView(linearLayoutTitle);
        }
        Log.e(TAG,"("+this.getClass().getSimpleName()+".java:35) ");
        return mContainer;
    }

    @Override
    public void onDestroyView() {
        //取消注册
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        String fragName = this.getClass().getName();
        if (fragName.contains("WorkareaFragment")||fragName.contains("MessageFragment")||fragName.contains("SettingFragment")){
            SharedPreference.LastActivity = null;
        }
        if (mReceiver != null) {
            getActivity().registerReceiver(mReceiver, mfilter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mReceiver != null) {
            getActivity().unregisterReceiver(mReceiver);
        }

    }

    /**
     * 替代原有的onCreateView方法,子类必须用这个方法设置其View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateViewByYM(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        return null;
    }

    /**
     * 显示Toast提示框
     *
     * @param text 显示的文本
     */
    public void showToast(CharSequence text) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(text);
        }
    }

    /**
     * 显示Toast提示框
     *
     * @param iconId 图标资源ID,Drawable Resource ID
     * @param text   显示的文本
     */
    public void showToast(@DrawableRes int iconId, CharSequence text) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(iconId, text);
        }
    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     *
     * @param fragCls 待启动Fragment
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls) {
        startFragment(fragCls, null, -1);
    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     *
     * @param fragCls     待启动Fragment
     * @param args        传递给Fragment的参数,可空
     * @param requestCode -1表示不使用ForResult
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args, int requestCode) {
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, UniversalActivity.class);
            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
            if (requestCode >= 0) {
                startActivityForResult(intent, requestCode);
            } else {
                startActivity(intent);
            }
        }
    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     *
     * @param fragCls 待启动Fragment
     * @param args    传递给Fragment的参数,可空
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args) {
        startFragment(fragCls, args, -1);
    }
    public void startFragment(@NonNull String fragName,
                              @Nullable Bundle args, int requestCode) {
        // 判断
        if (TextUtils.isEmpty(fragName) || !fragName.contains("com.dhcc")) {
            return;
        }
        try {
            Class<? extends BaseFragment> fragClass = (Class<? extends BaseFragment>) Class.forName(fragName);
            startFragment(fragClass, args,requestCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startFragment(@NonNull String fragName, @Nullable Bundle args) {
        startFragment(fragName, args,-1);
    }

    /**
     * 使用UniversalActivity启动给定的Fragment
     *
     * @param fragCls     待启动Fragment
     * @param requestCode -1表示不使用ForResult
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls,
                              int requestCode) {
        startFragment(fragCls, null, requestCode);
    }

    /**
     * 使用UniversalActivity及其子类启动给定的Fragment
     *
     * @param containerCls UniversalActivity及其子类
     * @param fragCls      待启动Fragment
     */
    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
                              @NonNull Class<? extends BaseFragment> fragCls) {
        startFragment(containerCls, fragCls, null, -1);
    }

    /**
     * 使用UniversalActivity及其子类启动给定的Fragment
     *
     * @param containerCls UniversalActivity及其子类
     * @param fragCls      待启动Fragment
     * @param args         传递给Fragment的参数,可空
     * @param requestCode  -1表示不使用ForResult
     */
    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
                              @NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args, int requestCode) {
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, containerCls);
            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
            if (requestCode >= 0) {
                startActivityForResult(intent, requestCode);
            } else {
                startActivity(intent);
            }
        }
    }

    /**
     * 使用UniversalActivity及其子类启动给定的Fragment
     *
     * @param containerCls UniversalActivity及其子类
     * @param fragCls      待启动Fragment
     * @param args         传递给Fragment的参数,可空
     */
    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
                              @NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args) {
        startFragment(containerCls, fragCls, args, -1);
    }

    /**
     * 显示一个从底部动画推上来的对话框
     *
     * @param contentFragment 要显示的内容Fragment
     * @return 如果返回null, 说明没有正常显示对话框
     */
    public BasePushDialog showPushDialog(BaseFragment contentFragment) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            return ((BaseActivity) activity).showPushDialog(contentFragment);
        } else {
            return null;
        }
    }

    final void requestPermissionsResult(String permission, int grantResult) {
        // 通知子类
        onRequestPermissionsResult(permission, grantResult == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * 权限请求结果回调
     *
     * @param permission 权限名称
     * @param granted    是否请求成功
     */
    public void onRequestPermissionsResult(String permission, boolean granted) {
        // nothing
    }

    /**
     * 显示LoadingTip
     *
     * @param type 显示加载提示框的样式类型
     */
    public void showLoadingTip(BaseActivity.LoadingType type) {
        showLoadingTip(type, false);
    }

    /**
     * 显示LoadingTip
     *
     * @param type       显示加载提示框的样式类型
     * @param cancelable 是否可以点击后隐藏;TOP类型时无效
     */
    public void showLoadingTip(final BaseActivity.LoadingType type, final boolean cancelable) {

        //        return;


        hideLoadingTip();
        if (getActivity() == null) {
            return;
        }
        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
            @Override
            public void run() {
                mLoadingType = type;
                if (mContainerMaskContainer != null) {
                    mContainer.removeView(mContainerMaskContainer);
                    mContainerMaskContainer = null;
                }
                if (getActivity() == null) {
                    return;
                }
                mContainerMaskContainer = new FrameLayout(getActivity());
                mContainerMaskContainer.setId(R.id.base_container_mask_container);
                mContainer.addView(mContainerMaskContainer);
                mContainerMaskContainer.setVisibility(View.VISIBLE);
                switch (mLoadingType) {
                    default:
                    case FULL:
                        if (mFullLoadingView == null) {
                            mFullLoadingView = onCreateFullLoadingView();
                            mContainerMaskContainer.addView(mFullLoadingView);
                        }
                        mFullLoadingView.setCancelable(cancelable, new Runnable() {
                            @Override
                            public void run() {
                                if (mContainerMaskContainer != null) {
                                    mContainer.removeView(mContainerMaskContainer);
                                    mContainerMaskContainer = null;
                                }
                            }
                        });
                        if (!mFullLoadingView.isShowing()) {
                            mFullLoadingView.show(null);
                        }
                        break;
                    case TOP:
                        if (mTopLoadingView == null) {
                            mTopLoadingView = onCreateTopLoadingView();
                            mContainerMaskContainer.addView(mTopLoadingView);
                        }
                        if (!mTopLoadingView.isShowing()) {
                            mTopLoadingView.show(null);
                        }
                        break;
                    case BOTTOM:
                        if (mBottomLoadingView == null) {
                            mBottomLoadingView = onCreateBottomLoadingView();
                            mContainerMaskContainer.addView(mBottomLoadingView, new FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.WRAP_CONTENT,
                                    Gravity.BOTTOM));
                        }
                        if (!mBottomLoadingView.isShowing()) {
                            mBottomLoadingView.show(null);
                        }
                        break;
                }
                isLoadingTipShowing = true;
            }
        });
    }

    /**
     * 隐藏LoadingTip
     */
    public void hideLoadingTip() {
        if (getActivity() == null) {
            return;
        }
        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
            @Override
            public void run() {
                switch (mLoadingType) {
                    default:
                    case FULL:
                        if (mFullLoadingView != null) {
                            final BaseFullLoadingView tmp = mFullLoadingView;
                            mFullLoadingView = null;
                            tmp.hide(new Runnable() {
                                @Override
                                public void run() {
                                    if (mContainerMaskContainer != null) {
                                        mContainer.removeView(mContainerMaskContainer);
                                        mContainerMaskContainer = null;
                                    }
                                }
                            });
                        }
                        break;
                    case TOP:
                        if (mTopLoadingView != null) {
                            final BaseTopLoadingView tmp = mTopLoadingView;
                            mTopLoadingView = null;
                            tmp.hide(new Runnable() {
                                @Override
                                public void run() {
                                    if (mContainerMaskContainer != null) {
                                        mContainer.removeView(mContainerMaskContainer);
                                        mContainerMaskContainer = null;
                                    }
                                }
                            });
                        }
                        break;
                    case BOTTOM:
                        if (mBottomLoadingView != null) {
                            final BaseBottomLoadingView tmp = mBottomLoadingView;
                            mBottomLoadingView = null;
                            tmp.hide(new Runnable() {
                                @Override
                                public void run() {
                                    if (mContainerMaskContainer != null) {
                                        mContainer.removeView(mContainerMaskContainer);
                                        mContainerMaskContainer = null;
                                    }
                                }
                            });
                        }
                        break;
                }
                isLoadingTipShowing = false;
            }
        });
    }

    /**
     * 创建一个FullLoadingView
     *
     * @return 返回创建的FullLoadingView
     */
    protected BaseFullLoadingView onCreateFullLoadingView() {
        BaseFullLoadingView fullLoadingView = (BaseFullLoadingView) LayoutInflater.from(getActivity())
                .inflate(R.layout.view_base_full_loading, null);
        return fullLoadingView;
    }

    /**
     * 创建一个TopLoadingView
     *
     * @return
     */
    protected BaseTopLoadingView onCreateTopLoadingView() {
        BaseTopLoadingView topLoadingView = (BaseTopLoadingView) LayoutInflater.from(getActivity())
                .inflate(R.layout.view_base_top_loading, null);
        return topLoadingView;
    }

    /**
     * 创建一个TopLoadingView
     *
     * @return
     */
    protected BaseBottomLoadingView onCreateBottomLoadingView() {
        BaseBottomLoadingView bottomLoadingView = (BaseBottomLoadingView) LayoutInflater.from(getActivity())
                .inflate(R.layout.view_base_bottom_loading, null);
        return bottomLoadingView;
    }

    /**
     * 关闭所属的Activity
     */
    public void finish() {
        finish(null);
    }

    /**
     * 关闭所属的Activity
     *
     * @param data setResult中的返回数据
     */
    public void finish(Bundle data) {
        Intent intent = null;
        if (data != null) {
            intent = new Intent();
            intent.putExtras(data);
        }
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            if (intent != null) {
                activity.setResult(Activity.RESULT_OK, intent);
            }
            activity.finish();
        }
    }

    /**
     * 显示网络加载失败的提示
     */
    public void showNetworkFailTip() {
        if (getActivity() == null) {
            return;
        }
        showLoadFailTip(R.drawable.wuwangluo_ico, getStringSafe(R.string.str_base_network_fail_tip));
    }

    /**
     * 显示LoadFailTip
     *
     * @param iconResId 提示图标DrawableResID
     * @param tip       错误提示信息
     */
    public void showLoadFailTip(final @DrawableRes int iconResId, final CharSequence tip) {
        hideLoadFailTip();
        if (getActivity() == null) {
            return;
        }
        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mContainerMaskContainer != null) {
                    mContainer.removeView(mContainerMaskContainer);
                    mContainerMaskContainer = null;
                }
                if (getActivity() == null) {
                    return;
                }
                mContainerMaskContainer = new FrameLayout(getActivity());
                mContainerMaskContainer.setId(R.id.base_container_mask_container);
                mContainerMaskContainer.addView(
                        LayoutInflater.from(getActivity()).inflate(
                                R.layout.no_net_show_layout, mContainerMaskContainer, false),
                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT));
                ImageView imageView = mContainerMaskContainer
                        .findViewById(R.id.noNet_showView_imageView);
                imageView.setImageResource(iconResId);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLoadFailRetryClicked();
                    }
                });
                TextView textView = mContainerMaskContainer
                        .findViewById(R.id.noNet_showView_textView);
                if (TextUtils.isEmpty(tip)) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setText(tip);
                    textView.setVisibility(View.VISIBLE);
                }
                mContainer.addView(mContainerMaskContainer, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                mContainerMaskContainer.setVisibility(View.VISIBLE);
                if (mContainerChild != null) {
                    mContainer.setBackgroundResource(R.color.color_base_content);
                    mContainerChild.setVisibility(View.INVISIBLE);
                }
                isLoadFailTipShowing = true;
            }
        });
    }

    /**
     * 安全方式调用getString
     *
     * @param resId
     * @return
     */
    public String getStringSafe(@StringRes int resId) {
        if (getActivity() == null) {
            return "";
        } else {
            return getString(resId);
        }
    }

    /**
     * 隐藏LoadFailTip
     */
    public void hideLoadFailTip() {
        if (getActivity() == null) {
            return;
        }
        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mContainerChild != null) {
                    mContainer.setBackgroundResource(android.R.color.transparent);
                    mContainerChild.setVisibility(View.VISIBLE);
                }
                if (mContainerMaskContainer != null) {
                    mContainer.removeView(mContainerMaskContainer);
                    mContainerMaskContainer = null;
                }
                isLoadFailTipShowing = false;
            }
        });
    }

    /**
     * 加载失败提示中【点击重试】后,回调到这里
     */
    protected void onLoadFailRetryClicked() {
        //        Logger.d(getClass().getName() + ": LoadFailRetryClicked");
    }

    /**
     * 更新消息信息
     */
    public void setMessage(int messageNum,String soundFlag,String vibrateFlag) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).setmessage(messageNum,soundFlag,vibrateFlag);
        }
    }

    /**
     * 处理扫描数据
     *
     * @param intent
     * @return
     */
    protected String doScanInfo(Intent intent) {
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            String scanInfo = bundle.getString("data");
            if (scanInfo != null) {
                // 只有数字和横杆
                //                Matcher m = Pattern.compile("[^0-9\\-]").matcher(scanInfo);
                //                return m.replaceAll("").trim();
                Log.e(TAG, "scanInfo: " + scanInfo);
                return scanInfo.replaceAll(" ", "").trim();
            }
        }
        return null;
    }

    public class BaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG,"(BaseReceiver.java:910) "+intent.toString());
            boolean bRegNo = false;
            String strScan = "";
            if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                String scanInfo = bundle.getString("data");
                for (int i = 0; i < listRegNo.size(); i++) {
                    if (scanInfo.equals(listRegNo.get(i))){
                        bRegNo = true;
                        strScan = scanInfo;
                    }
                }
            }

            if (bRegNo){
                setMsgToSingleActivity(strScan);
            }else {
                getScanMsg(intent);
            }
        }
    }
}