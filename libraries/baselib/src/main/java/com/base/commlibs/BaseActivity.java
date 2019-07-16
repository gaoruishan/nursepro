package com.base.commlibs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.base.BaseBottomLoadingView;
import com.base.commlibs.base.BaseFullLoadingView;
import com.base.commlibs.base.BasePushDialog;
import com.base.commlibs.base.BaseTopLoadingView;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.noober.background.BackgroundLibrary;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by levis on 2018/6/5.
 */

public class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener,
        ViewTreeObserver.OnGlobalLayoutListener {


    public static final String TAG = BaseActivity.class.getSimpleName();
    public static final int RequestPermissionTimeout = 1000;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 68;
    private static final int REQUEST_CODE_CHECK_LOGIN = 101;
    // 记录下页面打开的所有DialogFragment
    private static List<SoftReference<DialogFragment>> mShowingDialogs;
    private static Map<String, Long> RequestPermissionTimeMap = new HashMap<>();
    // 加载FullLoadingView
    protected BaseFullLoadingView mFullLoadingView;
    // 加载TopLoadingTip
    protected BaseTopLoadingView mTopLoadingView;
    // 加载BottomLoadingTip
    protected BaseBottomLoadingView mBottomLoadingView;
    // 状态栏背景填充
    protected View mStatusBarBackgroundView;
    // 每个页面中包含一个Toolbar,子类可控制其是否显示并设置其功能按钮等
    protected Toolbar mToolbar;
    protected FrameLayout mToolbarCenterContainer;
    protected FrameLayout mToolbarLeftContainer;
    protected FrameLayout mToolbarRightContainer;
    // 子类通过setContentView方法设置的View会添加到这个容器里面
    protected FrameLayout mContainer;
    // mContainer中子类的设置的View
    protected View mContainerChild;
    // mContainer中上面的浮层View
    protected FrameLayout mContainerMaskContainer;
    // 调整4.4系统状态栏背景颜色使用
    protected View mStatusBarTintView;
    // 记录当前Activity是否正处于显示状态
    boolean isResume;
    // 加载LoadingDialog的类型
    private LoadingType mLoadingType = LoadingType.FULL;
    // 记录是否显示了LoadingTip
    private boolean isLoadingTipShowing;
    // 记录是否显示了LoadFailTip
    private boolean isLoadFailTipShowing;
    private boolean isSoftKeyboardOpened = false;
    private boolean canCloseSoftKeyboard = true;
    // 用于启动登录回调
    private Runnable mCallbackAfterLoginSuccess;
    // =========================================================
    // 操作UI时,尽量通过UIHandler进行
    private UIHandler mUIHandler;
    private int mToolbarHeight;
    private boolean mToolbarBottomLineVisibility;
    // Toolbar的类型,默认不显示Toolbar
    private ToolbarType mToolbarType = ToolbarType.TOP;
    // 用于监听网络改变
    private NetworkStatusReceiver mNetworkStatusReceiver;
    private String mRequestTag;

    public static LinkedList<Activity> sAllActivitys = new LinkedList<Activity>();

    // 都是static声明的变量，避免被实例化多次；因为整个app只需要一个计时任务就可以了。
    private static Timer mTimer; // 计时器，每1秒执行一次任务
    private static MyTimerTask mTimerTask; // 计时任务，判断是否未操作时间到达5s
    private static long mLastActionTime; // 上一次操作时间
    public BroadcastReceiver mReceiver;

    // =========================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        // 生成请求数据的标记
        mRequestTag = getClass().getName() + "@" + UUID.randomUUID();
        // 实例化并注册网络状态监听器
        mNetworkStatusReceiver = new NetworkStatusReceiver(this);
        // [TRY]5.0以上系统默认[SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN]
        tryStatusBarTransparent();
        mUIHandler = new UIHandler(Looper.getMainLooper());
        //        // 判断是否支持右滑退出手势
        //        if (checkSldeable()) {
        //            super.setContentView(initSlideViews(R.layout.activity_base));
        //            //tryFullScreenAboveLollipop();
        //        } else {
        //            // 因为重写了setContentView,所以这里需要调用super
        //            super.setContentView(R.layout.activity_base);
        //        }

        // 因为重写了setContentView,所以这里需要调用super
        super.setContentView(R.layout.activity_base);

        mShowingDialogs = new ArrayList<>();

        setStatusBarBackgroundViewVisibility(false, 0x00000000);
        setStatusBarBackgroundColor(0x00000000);

        //mShowingDialogs = new ArrayList<>();
        mStatusBarBackgroundView = findViewById(R.id.status_bar_background);
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setContentInsetsAbsolute(0, 0);
        }
        mToolbarCenterContainer = findViewById(R.id.toolbar_center_container);
        mToolbarLeftContainer = findViewById(R.id.toolbar_left_container);
        mToolbarRightContainer = findViewById(R.id.toolbar_right_container);
        mContainer = findViewById(R.id.container);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        initStatusBarBackgroundView();
        initBackAction();
        // 5.0以上系统默认[SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN]
        tryChangeRootViewRectAboveLollipop();
        // 依赖Toolbar高度的操作需要在这个回调里面进行 !!!
        getToolbarHeight(new Runnable() {
            @Override
            public void run() {
                mUIHandler.updateLayoutWithToolbarType();
            }
        });
        sAllActivitys.add(this);
    }

    // 每当用户接触了屏幕，都会执行此方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mLastActionTime = System.currentTimeMillis();
//        Log.e("nurseprotime", "user action");
        return super.dispatchTouchEvent(ev);
    }

    private static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
//            Log.e("nurseprotime", "check time");
            // 5s未操作
            if (System.currentTimeMillis() - mLastActionTime > SPUtils.getInstance().getInt(SharedPreference.EXITTIME)) {
                // 停止计时任务
                stopTimer();
                exit();
            }
        }
    }

    // 登录成功，开始计时
    protected static void startTimer() {
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        // 初始化上次操作时间为登录成功的时间
        mLastActionTime = System.currentTimeMillis();
        // 每过1s检查一次
        mTimer.schedule(mTimerTask, 0, SPUtils.getInstance().getInt(SharedPreference.CHECKTIME));
//        Log.e("nurseprotime", "start timer");
    }

    // 停止计时任务
    protected static void stopTimer() {
        mTimer.cancel();
//        Log.e("nurseprotime", "cancel timer");
    }

    // 在大于等于5.0的系统中使StatusBar半透明效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void tryStatusBarTransparent() {
        if (isAboveLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (Build.VERSION.SDK_INT >= 23) {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            } else {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(initStatusBarBackgroundColor());
        }
    }

    /**
     * 设置StatusBarBackgroundView是否需要显示
     *
     * @param show
     */
    protected void setStatusBarBackgroundViewVisibility(boolean show, int color) {
        if (mStatusBarBackgroundView != null) {
            mStatusBarBackgroundView.setBackgroundColor(color);
            mStatusBarBackgroundView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 在大于等于5.0的系统中，设置的StatusBar的背景色
     */
    protected void setStatusBarBackgroundColor(int color) {
        if (isAboveLollipop()) {
            Window window = getWindow();
            window.setStatusBarColor(color);
        } else {
            setStatusBarColorLowLollipop(color);
        }
    }


    /**
     * 设置Toolbar的背景
     *
     * @param background
     */
    public void setToolbarBackground(Drawable background) {
        mUIHandler.setToolbarBackground(background);
    }

    // 初始化状态栏背景视图
    private void initStatusBarBackgroundView() {
        if (mStatusBarBackgroundView != null) {
            mStatusBarBackgroundView.setBackgroundColor(0xffffffff);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight());
            mStatusBarBackgroundView.setLayoutParams(lp);
            mStatusBarBackgroundView.setVisibility(View.GONE);
        }
    }

    // 添加默认的返回按钮图标
    private void initBackAction() {
        mToolbar.setNavigationIcon(R.drawable.icon_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarBackPressed();
            }
        });
    }

    // 尝试5.0以上系统默认[SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN]
    protected void tryChangeRootViewRectAboveLollipop() {
        boolean isAboveKitkat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        setStatusBarBackgroundViewVisibility(isAboveKitkat, 0xffffffff);
    }

    // 尝试获得Toolbar的高度,并记录在mToolbarHeight中
    private void getToolbarHeight(final Runnable callback) {
        mToolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mToolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mToolbarHeight = mToolbar.getHeight();
                if (callback != null) {
                    callback.run();
                }
            }
        });
    }

    /**
     * 判断是否大于等于LOLLIPOP
     *
     * @return true，表示大于等于LOLLIPOP
     */
    public static boolean isAboveLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 在大于等于5.0的系统中，设置的StatusBar的背景色
     */
    protected int initStatusBarBackgroundColor() {
        return isXiaoMi5OS6() ? 0xff666666 : getResources().getColor(R.color.colorPrimary);
    }

    /**
     * 设置5.0以下系统的状态栏背景颜色
     *
     * @param color 状态栏背景颜色
     */
    private void setStatusBarColorLowLollipop(int color) {
        boolean mStatusBarAvailable = true;

        Window win = getWindow();
        ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // check theme attrs
            int[] attrs = {android.R.attr.windowTranslucentStatus,
                    android.R.attr.windowTranslucentNavigation};
            TypedArray a = obtainStyledAttributes(attrs);
            try {
                mStatusBarAvailable = a.getBoolean(0, false);
            } finally {
                a.recycle();
            }

            // check window flags
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if ((winParams.flags & bits) != 0) {
                mStatusBarAvailable = true;
            }
        }

        if (mStatusBarAvailable) {
            if (mStatusBarTintView != null) {
                decorViewGroup.removeView(mStatusBarTintView);
                mStatusBarTintView = null;
            }
            mStatusBarTintView = new View(this);
            int statusBarHeight = getStatusBarHeight();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
            params.gravity = Gravity.TOP;

            mStatusBarTintView.setLayoutParams(params);
            mStatusBarTintView.setBackgroundColor(color);
            mStatusBarTintView.setVisibility(View.VISIBLE);
            decorViewGroup.addView(mStatusBarTintView);
        }
    }

    /**
     * 获得StatusBar的高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        Resources resources = getResources();
        int resourceId = resources.getIdentifier(
                "status_bar_height", "dimen", "android"
        );
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 点击Toolbar返回按钮的操作
     */
    protected void onToolbarBackPressed() {
        onBackPressed();
    }

    /**
     * 判断手机是否为小米5并且系统为6.0
     * 用于单独适配状态栏背景颜色
     *
     * @return true:
     */
    public static boolean isXiaoMi5OS6() {
        String manufacturer = Build.MANUFACTURER;
        if ("Xiaomi".equalsIgnoreCase(manufacturer)) {
            return true;
        } else return "oppo".equalsIgnoreCase(manufacturer);
    }

    /**
     * 会将设置进来的View添加到mContainer中
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, mContainer, false);
        if (view != null && view.getParent() == null) {
            mContainerChild = view;
            mContainer.addView(view);
        }
    }

    /**
     * 会将设置进来的View添加到mContainer中
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
        if (view != null && view.getParent() == null) {
            mContainerChild = view;
            mContainer.addView(view);
        }
    }

    /**
     * 会将设置进来的View添加到mContainer中
     *
     * @param view
     * @param params
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view != null && view.getParent() == null) {
            mContainerChild = view;
            if (params == null) {
                mContainer.addView(view);
            } else {
                mContainer.addView(view, params);
            }
        }
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        // 解注册网络状态监听器
        if (mNetworkStatusReceiver != null) {
            mNetworkStatusReceiver.unregister();
        }
        super.onDestroy();
        sAllActivitys.remove(this);
    }

    public static void finishAll() {
        for(Activity activity : sAllActivitys) {
            activity.finish();
        }

        sAllActivitys.clear();
    }

    public static void exit() {
        finishAll();
        // 这个主要是用来关闭进程的, 光把所有activity finish的话，进程是不会关闭的
        System.exit(0);
    }

    //-------------------------------

    public boolean isResume() {
        return isResume;
    }

    /**
     * 显示Toolbar的导航按钮
     */
    public void showToolbarNavigationIcon() {
        initBackAction();
    }

    /**
     * 设置Toolbar的导航按钮
     *
     * @param resId
     */
    public void showToolbarNavigationIcon(int resId) {
        initBackAction(resId);
    }

    /**
     * 设置返回按钮图标
     *
     * @param resId
     */
    private void initBackAction(int resId) {
        mToolbar.setNavigationIcon(resId);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarBackPressed();
            }
        });
    }

    /**
     * 隐藏Toolbar的导航按钮
     */
    public void hideToolbarNavigationIcon() {
        mToolbar.setNavigationIcon(null);
    }

    /**
     * 启动登录页面,登录完成后会回调到callbackAfterLoginSuccess
     *
     * @param callbackAfterLoginSuccess 登录成功之后回调到这里
     */
    public void startLogin(Runnable callbackAfterLoginSuccess) {
        mCallbackAfterLoginSuccess = callbackAfterLoginSuccess;
        // 启动登录页面
        //        startFragment(UserLoginFragment.class, REQUEST_CODE_CHECK_LOGIN);
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
     * 使用UniversalActivity启动给定的Fragment
     *
     * @param fragCls     待启动Fragment
     * @param args        传递给Fragment的参数,可空
     * @param requestCode -1表示不使用ForResult
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args, int requestCode) {
        Intent intent = new Intent(this, UniversalActivity.class);
        intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
        intent.putExtra(UniversalActivity.RootFragmentArgs, args);
        if (requestCode >= 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
    }

    /**
     * 启动登录页面,登录完成后会回调到callbackAfterLoginSuccess
     *
     * @param callbackAfterLoginSuccess 登录成功之后回调到这里
     * @param args                      启动登录界面的参数
     */
    public void startLogin(Runnable callbackAfterLoginSuccess, @Nullable Bundle args) {
        mCallbackAfterLoginSuccess = callbackAfterLoginSuccess;
        // 启动登录页面
        //        startFragment(UserLoginFragment.class, args, REQUEST_CODE_CHECK_LOGIN);
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
     * @param fragCls 待启动Fragment
     * @param args    传递给Fragment的参数,可空
     */
    public void startFragment(@NonNull Class<? extends BaseFragment> fragCls,
                              @Nullable Bundle args) {
        startFragment(fragCls, args, -1);
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
        Intent intent = new Intent(this, containerCls);
        intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
        intent.putExtra(UniversalActivity.RootFragmentArgs, args);
        if (requestCode >= 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 如果收到登录页面登录成功的返回后,需要更新用户信息
        if (requestCode == REQUEST_CODE_CHECK_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                onLoginSuccessAfterStartLogin();
                if (mCallbackAfterLoginSuccess != null) {
                    mCallbackAfterLoginSuccess.run();
                }
            } else {
                onLoginFailAfterStartLogin();
            }
        }
        // 兼容在BaseFragment中调用BaseActivity中的startFragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
        //        // 解析扫描返回
        //        if (resultCode == RESULT_OK) {
        //            IntentResult scanResult = IntentIntegrator
        //                    .parseActivityResult(requestCode, resultCode, data);
        //            if (scanResult != null) {
        //                //CommonUtil.sjScanHandler(this, scanResult);
        //            }
        //        }
    }

    @Override
    protected final void onPause() {
        super.onPause();
        //        MobclickAgent.onPause(this);
        isResume = false;
        //        onPause(null);
    }

    @Override
    protected final void onResume() {
        super.onResume();
        //        MobclickAgent.onResume(this);
        isResume = true;
        onResume(null);
    }

    protected void onResume(@Nullable Bundle args) {
        // nothing
    }

    /**
     * 当调用startLogin方法时,如果未登录状态会启动登录页面,
     * 进入登录页面填写登录信息后,登录验证成功后会回调到这里
     * <p>
     * 登录成功后的操作
     */
    protected void onLoginSuccessAfterStartLogin() {
    }

    /**
     * 当调用startLogin方法时,如果未登录状态会启动登录页面,
     * 取消登录或
     * 进入登录页面填写登录信息后,登录验证失败后会回调到这里
     * <p>
     * 取消登录或登录失败后的操作
     */
    protected void onLoginFailAfterStartLogin() {
    }

    /**
     * 返回窗口的Toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 获得窗口Toolbar的显示类型
     *
     * @return
     */
    public ToolbarType getToolbarType() {
        return mToolbarType;
    }

    /**
     * 设置为给定的ToolbarType
     *
     * @param type
     */
    public void setToolbarType(ToolbarType type) {
        mToolbarType = type;
        mUIHandler.updateLayoutWithToolbarType();
    }

    /**
     * 设置Toolbar的右边操作动作按钮
     *
     * @param menuId
     */
    public void setToolbarMenu(@MenuRes int menuId) {
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(menuId);
        mToolbar.setOnMenuItemClickListener(this);
    }

    /**
     * 设置Toolbar居中的标题
     *
     * @param title
     */
    public void setToolbarCenterTitle(CharSequence title) {
        mUIHandler.setToolbarCenterTitle(title, 0xff333333, 17);
    }

    /**
     * 设置Toolbar居中的标题
     *
     * @param title
     * @param color 如:0xffcccccc
     * @param size  单位:DIP
     */
    public void setToolbarCenterTitle(CharSequence title, int color, int size) {
        mUIHandler.setToolbarCenterTitle(title, color, size);
    }

    /**
     * 设置Toolbar居中的自定义视图
     *
     * @param view
     */
    public void setToolbarCenterCustomView(View view) {
        mUIHandler.setToolbarCenterCustomView(view);
    }

    /**
     * 设置Toolbar左边的自定义视图
     *
     * @param view
     */
    public void setToolbarLeftCustomView(View view) {
        mUIHandler.setToolbarLeftCustomView(view);
    }

    /**
     * 设置Toolbar右边的自定义视图
     *
     * @param view
     */
    public void setToolbarRightCustomView(View view) {
        mUIHandler.setToolbarRightCustomView(view);
    }

    /**
     * 获取UIHandler
     *
     * @return
     */
    public Handler getUIHandler() {
        return mUIHandler;
    }

    /**
     * 设置Toolbar-BottomLine是否显示
     *
     * @param show true:显示BottomLine;false:不显示
     */
    public void setToolbarBottomLineVisibility(boolean show) {
        mToolbarBottomLineVisibility = show;
        // 只有ToolBar类型为TOP|FLOAT的时候才可以显示
        if (mToolbarType == ToolbarType.TOP || mToolbarType == ToolbarType.FLOAT) {
            mUIHandler.setToolbarBottomLineVisibility(show);
        }
    }

    /**
     * 配置是否支持右滑退出手势
     *
     * @return
     */
    protected boolean checkSldeable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Drawable background = getWindow().getDecorView().getBackground();
            return background != null && background instanceof ColorDrawable &&
                    ((ColorDrawable) background).getColor() == 0;
        } else {
            getWindow().getDecorView().setBackgroundResource(R.color.color_base_content);
            return false;
        }
    }

    // =========================================================
    // 根据当前的ToolbarType更新为对应的布局
    private void updateLayoutWithToolbarTypeCore() {
        RelativeLayout.LayoutParams containerLp =
                (RelativeLayout.LayoutParams) mContainer.getLayoutParams();
        if (containerLp == null) {
            containerLp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
        }
        switch (mToolbarType) {
            default:
            case HIDE:  // 不显示Toolbar
                mToolbar.setVisibility(View.INVISIBLE);
                setToolbarBottomLineVisibilityCore(false);
                containerLp.topMargin = 0;
                break;
            case TOP:   // Toolbar显示在Container上面,处于同一水平高度
                mToolbar.setVisibility(View.VISIBLE);
                setToolbarBottomLineVisibilityCore(mToolbarBottomLineVisibility);
                containerLp.topMargin = mToolbarHeight;
                break;
            case FLOAT: // Toolbar悬浮在Container上面,在不同水平高度
                mToolbar.setVisibility(View.VISIBLE);
                setToolbarBottomLineVisibilityCore(mToolbarBottomLineVisibility);
                containerLp.topMargin = 0;
                break;
        }
        mContainer.setLayoutParams(containerLp);
    }

    // 设置Toolbar-BottomLine是否显示
    private void setToolbarBottomLineVisibilityCore(boolean show) {
        View bottomLine = findViewById(R.id.toolbar_bottom_line);
        if (bottomLine != null) {
            bottomLine.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    // 设置Toolbar的背景
    private void setToolbarBackgroundCore(Drawable background) {
        mToolbar.setBackground(background);
    }

    // 设置Toolbar中心标题
    private void setToolbarCenterTitleCore(CharSequence title, int color, int size) {
        mToolbarCenterContainer.removeAllViews();
        TextView tv = new TextView(this);
        tv.setLines(1);
        tv.setMaxLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setTextColor(color);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        mToolbarCenterContainer.addView(tv);
    }

    // 设置Toolbar中心自定义视图
    private void setToolbarCenterCustomViewCore(View view) {
        mToolbarCenterContainer.removeAllViews();
        if (view != null && view.getParent() == null) {
            mToolbarCenterContainer.addView(view);
        }
    }

    // 设置Toolbar左边边自定义视图
    private void setToolbarLeftCustomViewCore(View view) {
        mToolbarLeftContainer.removeAllViews();
        if (view != null && view.getParent() == null) {
            mToolbarLeftContainer.addView(view);
        }
    }

    // 设置Toolbar右边自定义视图
    private void setToolbarRightCustomViewCore(View view) {
        mToolbarRightContainer.removeAllViews();
        if (view != null && view.getParent() == null) {
            mToolbarRightContainer.addView(view);
        }
    }

    /**
     * Toolbar右边动作按钮回调
     *
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    // 处理网络断开,广播分发
    private void onNetworkDisconnectedCore() {
        onNetworkDisconnected();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment frag : fragments) {
                if (frag != null && frag instanceof BaseFragment) {
                    ((BaseFragment) frag).onNetworkDisconnected();
                }
            }
        }
    }

    /**
     * 每当网络断开后,回调到这里
     */
    protected void onNetworkDisconnected() {
        //Logger.d(getClass().getName() + ": NetworkDisconnected");
    }

    // 处理网络连接,广播分发
    private void onNetworkConnectedCore(int type) {
        onNetworkConnected(type);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment frag : fragments) {
                if (frag != null && frag instanceof BaseFragment) {
                    ((BaseFragment) frag).onNetworkConnected(type);
                }
            }
        }
    }

    /**
     * 每当网络连接后,回调到这里
     *
     * @param type 连接后的网络类型  one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     *             ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     *             ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     *             types defined by {@link ConnectivityManager}
     */
    protected void onNetworkConnected(int type) {
        //Logger.d(getClass().getName() + ": NetworkConnected Type: " + type);
    }

    /**
     * 显示Toast提示框
     *
     * @param iconId 图标资源ID,Drawable Resource ID
     * @param text   显示的文本
     */
    public void showToast(@DrawableRes final int iconId, final CharSequence text) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                View inflate = LayoutInflater.from(BaseActivity.this)
                        .inflate(R.layout.dialog_toast, mContainer, false);
                ImageView imageView = inflate.findViewById(R.id.icon);
                TextView textView = inflate.findViewById(R.id.text);
                textView.setText(text);
                imageView.setImageDrawable(getResources().getDrawable(iconId));
                Toast toast = new Toast(BaseActivity.this);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(inflate);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    /**
     * 显示Toast提示框
     *
     * @param text 显示的文本
     */
    public void showToast(final CharSequence text) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                View inflate = LayoutInflater.from(BaseActivity.this)
                        .inflate(R.layout.dialog_toast, mContainer, false);
                ImageView imageView = inflate.findViewById(R.id.icon);
                imageView.setVisibility(View.GONE);
                TextView textView = inflate.findViewById(R.id.text);
//                if (text.toString().contains("error")){
//                    textView.setText(text+"_"+SharedPreference.MethodName);
//                }else {
//                    textView.setText(text);
//                }
                textView.setText(text);
                Toast toast = new Toast(BaseActivity.this);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(inflate);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
    public void showLoadingTip(final LoadingType type, final boolean cancelable) {
        hideLoadingTip();
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoadingType = type;
                if (mContainerMaskContainer != null) {
                    mContainer.removeView(mContainerMaskContainer);
                    mContainerMaskContainer = null;
                }
                mContainerMaskContainer = new FrameLayout(BaseActivity.this);
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
                        if (mToolbarType == ToolbarType.HIDE) {
                            mContainerMaskContainer.setPadding(0, getStatusBarHeight(), 0, 0);
                        } else {
                            mContainerMaskContainer.setPadding(0, 0, 0, 0);
                        }
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
        mUIHandler.post(new Runnable() {
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
        BaseFullLoadingView fullLoadingView = (BaseFullLoadingView) LayoutInflater.from(BaseApplication.getApp())
                .inflate(R.layout.view_base_full_loading, null);
        return fullLoadingView;
    }

    /**
     * 创建一个TopLoadingView
     *
     * @return
     */
    protected BaseTopLoadingView onCreateTopLoadingView() {
        BaseTopLoadingView topLoadingView = (BaseTopLoadingView) LayoutInflater.from(BaseApplication.getApp())
                .inflate(R.layout.view_base_top_loading, null);
        return topLoadingView;
    }

    /**
     * 创建一个BottomLoadingView
     *
     * @return
     */
    protected BaseBottomLoadingView onCreateBottomLoadingView() {
        BaseBottomLoadingView bottomLoadingView = (BaseBottomLoadingView) LayoutInflater.from(BaseApplication.getApp())
                .inflate(R.layout.view_base_bottom_loading, null);
        return bottomLoadingView;
    }

    /**
     * 显示一个从底部动画推上来的对话框
     *
     * @param contentFragment 要显示的内容Fragment
     * @return
     */
    public BasePushDialog showPushDialog(final BaseFragment contentFragment) {
        final BasePushDialog dialog = new BasePushDialog();
        mShowingDialogs.add(new SoftReference<DialogFragment>(dialog));
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                dialog.show(getSupportFragmentManager(), getClass().getName(), contentFragment);
            }
        });
        return dialog;
    }

    /**
     * 请求权限
     *
     * @param permission 申请的权限名称
     */
    public final void requestPermission(final String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !TextUtils.isEmpty(permission)) {
            Long time = RequestPermissionTimeMap.get(permission);
            long requestTime = time == null ? 0 : time;
            boolean canRequest = System.currentTimeMillis() - requestTime > RequestPermissionTimeout;
            if (canRequest) {
                final BaseActivity activity = this;
                ActivityCompat.requestPermissions(activity, new String[]{permission},
                        REQUEST_CODE_ASK_PERMISSIONS);
                RequestPermissionTimeMap.put(permission, System.currentTimeMillis());
            }
        }
    }

    /**
     * 请求权限
     *
     * @param permission     申请的权限名称
     * @param requestMessage 申请权限提示框中提示文本
     */
    public final void requestPermission(final String permission, final String requestMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !TextUtils.isEmpty(permission)) {
            Long time = RequestPermissionTimeMap.get(permission);
            long requestTime = time == null ? 0 : time;
            boolean canRequest = System.currentTimeMillis() - requestTime > RequestPermissionTimeout;
            if (canRequest) {
                final BaseActivity activity = this;
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    showRequestPermissionDialog(requestMessage, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]{permission},
                                    REQUEST_CODE_ASK_PERMISSIONS);
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{permission},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }
                RequestPermissionTimeMap.put(permission, System.currentTimeMillis());
            }
        }
    }

    // 自定义权限申请对话框
    private void showRequestPermissionDialog(
            String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(message)
                .setPositiveButton("继续", okListener)
                .create()
                .show();
    }

    // 请求权限得到返回后回调到这里
    final void requestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length && i < grantResults.length; i++) {
            // 通知子类
            onRequestPermissionsResult(permissions[i],
                    grantResults[i] == PackageManager.PERMISSION_GRANTED);
            // 检查是否包含Fragment,并逐个通知Fragment
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null && fragments.size() > 0) {
                for (Fragment frag : fragments) {
                    if (frag != null && frag instanceof BaseFragment) {
                        ((BaseFragment) frag).requestPermissionsResult(
                                permissions[i], grantResults[i]);
                    }
                }
            }
        }
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

    // 尝试5.0以上系统设置Toolbar的MargeTop为StatusBar的高度
    protected void tryToolbarMargeTopAboveLollipop() {
        if (isAboveLollipop() && mToolbar != null) {
            setStatusBarBackgroundViewVisibility(true, isXiaoMi5OS6() ? 0xff666666 : 0x00000000);
        }
    }

    public void setmessage(int messageNum) {
    }

    /**
     * 定义了显示加载提示框的类型
     * TOP  : 页面上边简单非模态提示
     * FULL : 全屏模态提示
     */
    public enum LoadingType {
        TOP,    // 页面上边简单非模态提示
        BOTTOM, // 页面下边简单非模态提示
        FULL,   // 全屏模态提示
    }

    /**
     * 定义了Toolbar的显示位置
     * HIDE  : 不显示Toolbar
     * TOP   : Toolbar显示在Container上面,处于同一水平高度
     * FLOAT : Toolbar悬浮在Container上面,在不同水平高度
     */
    public enum ToolbarType {
        HIDE,  // 不显示Toolbar
        TOP,   // Toolbar显示在Container上面,处于同一水平高度
        FLOAT, // Toolbar悬浮在Container上面,在不同水平高度
    }

    // =========================================================
    // Network Status
    private static class NetworkStatusReceiver extends BroadcastReceiver {
        private SoftReference<BaseActivity> mActivity;

        /**
         * 实例化广播接收器,并将其注册到activity上
         *
         * @param activity
         */
        public NetworkStatusReceiver(BaseActivity activity) {
            mActivity = new SoftReference<>(activity);
            // 注册该广播接收器
            register();
        }

        private void register() {
            BaseActivity activity = mActivity.get();
            if (activity != null) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                activity.registerReceiver(this, filter);
            }
        }

        /**
         * 解注册该广播接收器
         */
        public void unregister() {
            BaseActivity activity = mActivity.get();
            if (activity != null) {
                activity.unregisterReceiver(this);
            }
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = null;
            if (cm != null) {
                info = cm.getActiveNetworkInfo();
            }
            if (info != null && info.isConnected()) {
                BaseActivity activity = mActivity.get();
                if (activity != null) {
                    activity.onNetworkConnectedCore(info.getType());
                }
            } else {
                BaseActivity activity = mActivity.get();
                if (activity != null) {
                    activity.onNetworkDisconnectedCore();
                }
            }
        }
    }

    // =========================================================
    // UIHandler
    private class UIHandler extends Handler {
        public UIHandler(Looper looper) {
            super(looper);
        }

        /**
         * 发起[根据当前的ToolbarType更新为对应的布局]的操作
         */
        public void updateLayoutWithToolbarType() {
            sendEmptyMessage(100);
        }

        /**
         * 发起[设置Toolbar的背景]的操作
         *
         * @param background
         */
        public void setToolbarBackground(Drawable background) {
            Message msg = obtainMessage(200);
            msg.obj = background;
            sendMessage(msg);
        }

        /**
         * 发起[设置Toolbar中心标题]的操作
         *
         * @param title
         * @param color
         * @param size
         */
        public void setToolbarCenterTitle(CharSequence title, int color, int size) {
            Message msg = obtainMessage(300);
            msg.obj = title;
            msg.arg1 = color;
            msg.arg2 = size;
            sendMessage(msg);
        }

        /**
         * 发起[设置Toolbar中心自定义View]的操作
         *
         * @param view
         */
        public void setToolbarCenterCustomView(View view) {
            Message msg = obtainMessage(400);
            msg.obj = view;
            sendMessage(msg);
        }

        /**
         * 发起[设置Toolbar左边自定义View]的操作
         *
         * @param view
         */
        public void setToolbarLeftCustomView(View view) {
            Message msg = obtainMessage(401);
            msg.obj = view;
            sendMessage(msg);
        }

        /**
         * 发起[设置Toolbar右边自定义View]的操作
         *
         * @param view
         */
        public void setToolbarRightCustomView(View view) {
            Message msg = obtainMessage(410);
            msg.obj = view;
            sendMessage(msg);
        }

        /**
         * 发起[设置Toolbar-BottomLine是否显示]的操作
         *
         * @param show
         */
        public void setToolbarBottomLineVisibility(boolean show) {
            Message msg = obtainMessage(500);
            msg.obj = show;
            sendMessage(msg);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    updateLayoutWithToolbarTypeCore();
                    break;
                case 200:
                    setToolbarBackgroundCore((Drawable) msg.obj);
                    break;
                case 300:
                    setToolbarCenterTitleCore((CharSequence) msg.obj, msg.arg1, msg.arg2);
                    break;
                case 400:
                    setToolbarCenterCustomViewCore((View) msg.obj);
                    break;
                case 401:
                    setToolbarLeftCustomViewCore((View) msg.obj);
                    break;
                case 410:
                    setToolbarRightCustomViewCore((View) msg.obj);
                    break;
                case 500:
                    setToolbarBottomLineVisibilityCore((boolean) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onGlobalLayout() {
        try {
            final View view = getWindow().getDecorView();
            final Rect r = new Rect();
            view.getWindowVisibleDisplayFrame(r);
            final int heightDiff = getWindowManager().getDefaultDisplay().getHeight() - (r.bottom - r.top);

            if (!isSoftKeyboardOpened && heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                isSoftKeyboardOpened = true;
                int offset = heightDiff - getStatusBarHeight() - getToolbarHeight() - dp2px(20);
                onKeyBoardOpen(offset);

                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if (fragment != null && fragment instanceof BaseFragment) {
                            ((BaseFragment) fragment).onKeyBoardOpen(heightDiff);
                        }
                    }
                }
                MessageEvent event = new MessageEvent(001);
                event.setOffSet(offset);
                EventBus.getDefault().post(event);
                canCloseSoftKeyboard = false;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        canCloseSoftKeyboard = true;
                    }
                }, 600);
            } else if (canCloseSoftKeyboard && isSoftKeyboardOpened && heightDiff < 100) {
                isSoftKeyboardOpened = false;
                onKeyBoardClose();
                //            MessageEvent event = new MessageEvent(MessageEvent.MessageType.KEY_BORAD_CLOSE);
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if (fragment != null && fragment instanceof BaseFragment) {
                            ((BaseFragment) fragment).onKeyBoardClose();
                        }
                    }
                }
                //                MessageEvent event = new MessageEvent(002);
                //                EventBus.getDefault().post(event);
            }
        } catch (Exception e) {
            //避免潜在的崩溃问题，有些Fragment，例如ZhWebFragment不能被转换为BaseFragment从而报错
            e.printStackTrace();
        }
    }


    /**
     * 获得Toolbar的高度
     *
     * @return
     */
    public int getToolbarHeight() {
        return mToolbarHeight;
    }


    /**
     * 根据手机的分辨率dp单位转为px像素
     *
     * @param dp
     * @return
     */
    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public void onKeyBoardOpen(int heightDiff) {
    }


    public void onKeyBoardClose() {
    }


    /**
     * 设置Toolbar的高度
     *
     * @param toolbarHeight
     */
    public void setToolbarHeight(int toolbarHeight) {
        this.mToolbarHeight = toolbarHeight;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mToolbar.getLayoutParams();
        params.height = toolbarHeight;
        mToolbar.setLayoutParams(params);
        mToolbar.setMinimumHeight(toolbarHeight);
    }

    /**
     * 注册扫码广播
     */
    public void registerScanMsgReceiver() {
         mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getScanMsg(intent);
            }
        };
        IntentFilter mfilter = new IntentFilter();
        mfilter.addAction(Action.DEVICE_SCAN_CODE);
       registerReceiver(mReceiver, mfilter);
    }
    public void getScanMsg(Intent intent) {
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        String scanInfo = bundle.getString("data");
        intent.putExtra("data", scanInfo);
    }
    /**
     * 处理扫描数据
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
                return scanInfo.replaceAll(" ","").trim();
            }
        }
        return null;
    }
}
