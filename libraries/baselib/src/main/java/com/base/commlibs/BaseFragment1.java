//package com.base.commlibs;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.graphics.drawable.Drawable;
//import android.net.ConnectivityManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.IdRes;
//import android.support.annotation.MenuRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.StringRes;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.base.commlibs.base.BaseBottomLoadingView;
//import com.base.commlibs.base.BaseFullLoadingView;
//import com.base.commlibs.base.BasePushDialog;
//import com.base.commlibs.base.BaseTopLoadingView;
//import com.base.commlibs.constant.Action;
//import com.base.commlibs.constant.SharedPreference;
//import com.base.commlibs.utils.SystemTTS;
//import com.base.commlibs.voiceUtils.AsrDialog;
//import com.base.commlibs.voiceUtils.VoiceUtil;
//import com.base.commlibs.voiceUtils.VoiceWebDataUtil;
//import com.base.commlibs.voiceUtils.bean.BedMapBean;
//import com.base.commlibs.voiceUtils.bean.VoiceBean;
//import com.base.commlibs.voiceUtils.bean.VoiceVisalBean;
//import com.blankj.utilcode.util.ActivityUtils;
//import com.blankj.utilcode.util.SPUtils;
//import com.blankj.utilcode.util.ToastUtils;
//import com.google.gson.Gson;
//import com.raisound.speech.AsrResult;
//import com.raisound.speech.SpeechError;
//import com.raisound.speech.SpeechRecognizerManager;
//import com.raisound.speech.http.callback.RequestCallback;
//import com.raisound.speech.http.response.Scene;
//import com.raisound.speech.listener.RecognizerListener;
//import com.raisound.speech.listener.SceneListResponse;
//
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.UUID;
//
//import static com.base.commlibs.BaseActivity.LoadingType.FULL;
//
///**
// * Created by levis on 2018/6/5.
// */
//public class BaseFragment1 extends Fragment {
//    protected static final String TAG = BaseFragment1.class.getSimpleName();
//    // 视图组,负责管理加载、加载失败等视图的显隐
//    protected FrameLayout mContainer;
//    protected LinearLayout linearLayoutTitle,llTitTop,llTitRight;
//    protected TextView tvName;
//    protected View mContainerChild;
//    protected FrameLayout mContainerMaskContainer;
//    // 加载FullLoadingView
//    protected BaseFullLoadingView mFullLoadingView;
//    // 加载TopLoadingTip
//    protected BaseTopLoadingView mTopLoadingView;
//    // 加载BottomLoadingTip
//    protected BaseBottomLoadingView mBottomLoadingView;
//    protected BaseReceiver mReceiver = new BaseReceiver();
//    protected IntentFilter mfilter = new IntentFilter();
//    // 用于发起数据请求时的标记
//    private String mRequestTag;
//    // 加载LoadingDialog的类型
//    private BaseActivity.LoadingType mLoadingType = FULL;
//    // 记录是否显示了LoadingTip
//    private boolean isLoadingTipShowing;
//    // 记录是否显示了LoadFailTip
//    private boolean isLoadFailTipShowing;
//
//
//    private Intent broadCastIntent = new Intent();
//    public String singleEpisodeId = "";
//    public String singleRegNo = "";
//    public String singlePatInfo = "";
//    public ArrayList<String> listRegNo = new ArrayList<>();
//    public Boolean isSingleModel = SPUtils.getInstance().getString(SharedPreference.SINGLEMODEL).equals("1");
//    //判断当前页面是调换患者还是执行
//    public Boolean isChangePat = true;
//
//    //判断患者信息是扫码获得还是点击获得
//    public Boolean isGetPatByScan = false;
//    public FragmentActivity mActivity;
//
//    /**
//     * 判断是否大于等于LOLLIPOP
//     *
//     * @return true，表示大于等于LOLLIPOP
//     */
//    public static boolean isAboveLollipop() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setlitener();
//
//        mfilter.addAction(Action.DEVICE_SCAN_CODE);
//        mfilter.addAction(Action.NEWMESSAGE_SERVICE);
//        mfilter.addAction(Action.ORDER_HANDLE_TYPE);
////        mfilter.addAction(Action.ORDER_HANDLE_REFUSE);
////        mfilter.addAction(Action.ORDER_HANDLE_COMPLETE);
//        mfilter.addAction(Action.SKIN_TEST_YANG);
//        mfilter.addAction(Action.SKIN_TEST_YIN);
//        mfilter.addAction(Action.DOSING_REVIEW);
//        mfilter.addAction(Action.TOUR_DOSINGID);
//        mfilter.addAction(Action.DRUG_RLREG);
//        mfilter.addAction(Action.NUR_RECORD_XML_VIEW);
//        //初始化语音
//        SystemTTS.getInstance(mActivity);
//    }
//
//    /**
//     * 通过Class名 结束Fragment
//     * @param cls
//     */
//    public void finishFragment(Class<? extends BaseFragment1> cls) {
//        for (Activity activity : ActivityUtils.getActivityList()) {
//            if (activity instanceof AppCompatActivity) {
//                AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
//                List<Fragment> fragments = appCompatActivity.getSupportFragmentManager().getFragments();
//                for (Fragment fg : fragments) {
//                    if (fg.getActivity() != null && fg.getClass().getName().equals(cls.getName())) {
//                        fg.getActivity().finish();
//                        return;
//                    }
//                }
//            }
//        }
//    }
//    public void getScanMsg(Intent intent) {
//
//        if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
//            Bundle bundle = new Bundle();
//            bundle = intent.getExtras();
//            String scanInfo = bundle.getString("data");
//            //               .replace("||","-");
//            intent.putExtra("data", scanInfo);
//        }
//
//    }
//
//    /**
//     * BaseFragment通过广播向SingleMainActivity传递msg
//     */
//    public void setMsgToSingleActivity(String msg) {
//        broadCastIntent.setAction(Action.SETSINGLEMSG);
//        broadCastIntent.putExtra("data", msg);
//        getActivity().sendBroadcast(broadCastIntent);
//    }
//
//    /**
//     * SIngleMainActivity通过此方法向BaseFragment单人模式传递数据
//     */
//    public void setMsgToSingleBaseFragment(String msg) {
////        showToast(msg);
//    }
//    /**
//     * 所属Activity中onCreate之前调用(只适用于UniversalActivity及其子类)
//     *
//     * @param activity           所属Activity(只适用于UniversalActivity及其子类)
//     * @param savedInstanceState onCreate中的savedInstanceState
//     */
//    protected void onPreActivityCreate(@NonNull UniversalActivity activity,
//                                       @Nullable Bundle savedInstanceState) {
//        // 所属UniversalActivity中onCreate之前调用
//
//
//        mfilter.addAction(Action.DEVICE_SCAN_CODE);
//        mfilter.addAction(Action.NEWMESSAGE_SERVICE);
//        mfilter.addAction(Action.ORDER_HANDLE_TYPE);
////        mfilter.addAction(Action.ORDER_HANDLE_REFUSE);
////        mfilter.addAction(Action.ORDER_HANDLE_COMPLETE);
//        mfilter.addAction(Action.SKIN_TEST_YANG);
//        mfilter.addAction(Action.SKIN_TEST_YIN);
//        mfilter.addAction(Action.DOSING_REVIEW);
//        mfilter.addAction(Action.TOUR_DOSINGID);
//        mfilter.addAction(Action.DRUG_RLREG);
//        mfilter.addAction(Action.NUR_RECORD_XML_VIEW);
//        mfilter.addAction(Action.NURRECORD_KONWLEDGETREEID);
//    }
//
//    public Toolbar getToolbar() {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//           return  ((BaseActivity) activity).getToolbar();
//        }
//        return null;
//    }
//
//    /**
//     * 设置StatusBarBackgroundView是否需要显示
//     *
//     * @param show
//     * @param color
//     */
//    public void setStatusBarBackgroundViewVisibility(boolean show, int color) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setStatusBarBackgroundViewVisibility(show, color);
//        }
//    }
//
//    /**
//     * 设置Toolbar的背景
//     *
//     * @param background
//     */
//    public void setToolbarBackground(Drawable background) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarBackground(background);
//        }
//    }
//
//    /**
//     * 设置Toolbar的导航按钮
//     *
//     * @param resId
//     */
//    public void showToolbarNavigationIcon(int resId) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).showToolbarNavigationIcon(resId);
//        }
//    }
//
//    /**
//     * 显示Toolbar的导航按钮
//     */
//    public void showToolbarNavigationIcon() {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).showToolbarNavigationIcon();
//        }
//    }
//
//    /**
//     * 隐藏Toolbar的导航按钮
//     */
//    public void hideToolbarNavigationIcon() {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).hideToolbarNavigationIcon();
//        }
//    }
//
//    /**
//     * 设置为给定的ToolbarType
//     *
//     * @param type
//     */
//    public void setToolbarType(BaseActivity.ToolbarType type) {
//        if (isSingleModel){
//            type = BaseActivity.ToolbarType.HIDE;
//            llTitTop.setVisibility(View.VISIBLE);
//        }else {
//            llTitTop.setVisibility(View.GONE);
//        }
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarType(type);
//        }
//    }
//
//    /**
//     * 设置Toolbar的右边操作动作按钮
//     *
//     * @param menuId
//     */
//    public void setToolbarMenu(@MenuRes int menuId) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarMenu(menuId);
//        }
//    }
//
//    /**
//     * 设置Toolbar居中的标题
//     *
//     * @param title
//     */
//    public void setToolbarCenterTitle(CharSequence title) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarCenterTitle(title);
//        }
//    }
//
//    /**
//     * 设置Toolbar居中的标题
//     *
//     * @param title
//     * @param color 如:0xffcccccc
//     * @param size  单位:DIP
//     */
//    public void setToolbarCenterTitle(CharSequence title, int color, int size) {
//        if (isSingleModel){
//            tvName.setLines(1);
//            tvName.setMaxLines(1);
//            tvName.setEllipsize(TextUtils.TruncateAt.END);
//            tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size-2);
//            tvName.setText(title);
//            tvName.setGravity(Gravity.CENTER);
//        }
//        setMsgToSingleActivity(title.toString());
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarCenterTitle(title, color, size);
//        }
//
//        centerTitle = title.toString();
//    }
//
//    public void setlitener() {
//        Activity activity = getActivity();
//        String fragName = this.getClass().getName();
//        SharedPreference.Fragment_show = fragName;
//        if (fragName.contains("WorkareaFragment")||fragName.contains("MessageFragment")||fragName.contains("SettingFragment")){
//            SharedPreference.LastActivity = null;
//        }else {
//            if (activity != null && activity instanceof BaseActivity) {
//                ((BaseActivity) activity).setListener(new BaseActivity.Listener() {
//                    @Override
//                    public void changMap(String map) {
//                        if (isSingleModel){
//                            broadCastIntent.setAction(Action.SINGLEMAP);
//                            broadCastIntent.putExtra("data", map);
//                            getActivity().sendBroadcast(broadCastIntent);
//                        }else {
//                            try {
//                                Class<? extends BaseFragment1> aClass = (Class<? extends BaseFragment1>) Class.forName(map);
//                                if (!aClass.getName().contains("WorkareaFragment")){
//                                    startFragment(aClass);
//                                }
//                                if (SharedPreference.LastActivity == null){
//                                    SharedPreference.LastActivity = (BaseActivity) activity;
//                                }else {
//                                    SharedPreference.LastActivity.finish();
//                                    SharedPreference.LastActivity = (BaseActivity) activity;
//                                }
//                                if (aClass.getName().contains("WorkareaFragment")){
//                                    startActivity(new Intent(getActivity(),Class.forName("com.dhcc.nursepro.Activity.MainActivity")));
//                                    SharedPreference.LastActivity = null;
//                                    finish();
//                                };
////                        finish();
//                            } catch (ClassNotFoundException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                });
//            }
//
//        }
//
//    }
//    public void hindMap() {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).hindMap();
//        }
//    }
//    public void setHindBottm(int bottom) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setTvHindMapBottom(bottom);
//        }
//    }
//    /**
//     * 设置Toolbar居中的自定义视图
//     *
//     * @param view
//     */
//    public void setToolbarCenterCustomView(View view) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarCenterCustomView(view);
//        }
//    }
//
//    /**
//     * 设置Toolbar左边自定义视图
//     *
//     * @param view
//     */
//    public void setToolbarLeftCustomView(View view) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarLeftCustomView(view);
//        }
//    }
//
//    /**
//     * 设置Toolbar右边自定义视图
//     *
//     * @param view
//     */
//    public void setToolbarRightCustomView(View view) {
//        if (isSingleModel){
//            llTitRight.removeAllViews();
//            llTitRight.addView(view);
//        }
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarRightCustomView(view);
//        }
//    }
//    /**
//     * 设置Toolbar右边自定义视图-单患者模式下也可以显示
//     *
//     * @param view
//     */
//    public void setToolbarRightCustomViewSingleShow(View view) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarRightCustomView(view);
//        }
//    }
//    /**
//     * 设置Toolbar-BottomLine是否显示
//     *
//     * @param show true:显示BottomLine;false:不显示
//     */
//    public void setToolbarBottomLineVisibility(boolean show) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setToolbarBottomLineVisibility(show);
//        }
//    }
//
//    /**
//     * 所属的Activity(仅支持UniversalActivity及其子类)Finish之前时调用
//     * 在这个方法里面,如果调用了activity.setResult,
//     * 将会替换到finish(Bundle data)中设置的Result数据
//     */
//    public void onPreFinish(UniversalActivity activity) {
//        // nothing
//    }
//
//    /**
//     * Toolbar右边动作按钮回调
//     *
//     * @param item
//     * @return
//     */
//
//    public boolean onMenuItemClick(MenuItem item) {
//        return false;
//    }
//
//    /**
//     * 每当网络断开后,回调到这里
//     */
//    public void onNetworkDisconnected() {
//        //Logger.d(getClass().getName() + ": NetworkDisconnected");
//    }
//
//    /**
//     * 每当网络连接后,回调到这里
//     *
//     * @param type 连接后的网络类型  one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
//     *             ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
//     *             ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
//     *             types defined by {@link ConnectivityManager}
//     */
//    public void onNetworkConnected(int type) {
//        //Logger.d(getClass().getName() + ": NetworkConnected Type: " + type);
//    }
//
//    /**
//     * 安全方式调用getString
//     *
//     * @param resId
//     * @return
//     */
//    public String getStringSafe(@StringRes int resId, Object... formatArgs) {
//        if (getActivity() == null) {
//            return "";
//        } else {
//            return getString(resId, formatArgs);
//        }
//    }
//
//    public void onKeyBoardOpen(int heightDiff) {
//    }
//
//    public void onKeyBoardClose() {
//    }
//
//    /**
//     * 查找View
//     *
//     * @param resId View的id
//     * @param t     指定View类类型
//     * @return
//     */
//    @SafeVarargs
//    protected final <T extends View> T f(@IdRes int resId, Class<T>... t) {
//        if (mContainerChild != null) {
//            return mContainerChild.findViewById(resId);
//        }
//        return null;
//    }
//
////    @Nullable
////    @Override
////    public View onCreateView(LayoutInflater inflater,
////                             @Nullable ViewGroup container,
////                             @Nullable Bundle savedInstanceState) {
////        mRequestTag = getClass().getName() + "@" + UUID.randomUUID();
////        mActivity = getActivity();
////        mContainer = new FrameLayout(getActivity());
////        linearLayoutTitle = (LinearLayout) View.inflate(getActivity(), R.layout.activity_base_linelayout, null);
////        llTitTop = linearLayoutTitle.findViewById(R.id.ll_title_top);
////        llTitRight = linearLayoutTitle.findViewById(R.id.ll_title_right);
////        tvName = linearLayoutTitle.findViewById(R.id.tv_title_name);
////
////        mContainerChild = onCreateViewByYM(inflater, container, savedInstanceState);
////        if (mContainerChild != null && mContainerChild.getParent() == null) {
////            linearLayoutTitle.addView(mContainerChild,
////                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
////                            FrameLayout.LayoutParams.MATCH_PARENT));
////            mContainer.addView(linearLayoutTitle);
////        }
////        Log.e(TAG,"("+this.getClass().getSimpleName()+".java:35) ");
////        return mContainer;
////    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        mRequestTag = getClass().getName() + "@" + UUID.randomUUID();
//        mActivity = getActivity();
//        mContainer = new FrameLayout(getActivity());
//
//        View  viewRl = View.inflate(getActivity(), R.layout.view_rlcontainer, null);
//        RelativeLayout mRl = viewRl.findViewById(R.id.rl_containner);
//
//        llTitTop = viewRl.findViewById(R.id.ll_title_top);
//        llTitRight = viewRl.findViewById(R.id.ll_title_right);
//        tvName = viewRl.findViewById(R.id.tv_title_name);
//        mContainerChild = onCreateViewByYM(inflater, container, savedInstanceState);
//
//        if (mContainerChild != null && mContainerChild.getParent() == null) {
//            mRl.addView(mContainerChild, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                    RelativeLayout.LayoutParams.MATCH_PARENT));
//            mContainer.addView(viewRl,
//                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                            FrameLayout.LayoutParams.MATCH_PARENT));
//        }
//
//        Log.e(TAG,"("+this.getClass().getSimpleName()+".java:35) ");
//
//
//        btnVoice = viewRl.findViewById(R.id.btn_mov);
//        etTest = viewRl.findViewById(R.id.et_text);
//        if (SPUtils.getInstance().getBoolean(SharedPreference.BTN_VOICE_SHOW,true)){
//            btnVoice.setVisibility(View.VISIBLE);
//            initVoice();
//        }else {
//            btnVoice.setVisibility(View.GONE);
//        }
////        btnMove.setOnTouchListener(shopCarSettleTouch);
//
//
//        return mContainer;
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        //取消注册
//        EventBus.getDefault().unregister(this);
//        SystemTTS.getInstance(mActivity).destroy();
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        String fragName = this.getClass().getName();
//        if (fragName.contains("WorkareaFragment")||fragName.contains("MessageFragment")||fragName.contains("SettingFragment")){
//            SharedPreference.LastActivity = null;
//        }
//        if (mReceiver != null) {
//            getActivity().registerReceiver(mReceiver, mfilter);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mReceiver != null) {
//            getActivity().unregisterReceiver(mReceiver);
//        }
//
//    }
//
//    /**
//     * 替代原有的onCreateView方法,子类必须用这个方法设置其View
//     *
//     * @param inflater
//     * @param container
//     * @param savedInstanceState
//     * @return
//     */
//    public View onCreateViewByYM(LayoutInflater inflater,
//                                 @Nullable ViewGroup container,
//                                 @Nullable Bundle savedInstanceState) {
//        return null;
//    }
//
//    /**
//     * 显示Toast提示框
//     *
//     * @param text 显示的文本
//     */
//    public void showToast(CharSequence text) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).showToast(text);
//        }
//    }
//
//    /**
//     * 显示Toast提示框
//     *
//     * @param iconId 图标资源ID,Drawable Resource ID
//     * @param text   显示的文本
//     */
//    public void showToast(@DrawableRes int iconId, CharSequence text) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).showToast(iconId, text);
//        }
//    }
//
//    /**
//     * 使用UniversalActivity启动给定的Fragment
//     *
//     * @param fragCls 待启动Fragment
//     */
//    public void startFragment(@NonNull Class<? extends BaseFragment1> fragCls) {
//        startFragment(fragCls, null, -1);
//    }
//
//    /**
//     * 使用UniversalActivity启动给定的Fragment
//     *
//     * @param fragCls     待启动Fragment
//     * @param args        传递给Fragment的参数,可空
//     * @param requestCode -1表示不使用ForResult
//     */
//    public void startFragment(@NonNull Class<? extends BaseFragment1> fragCls,
//                              @Nullable Bundle args, int requestCode) {
//        Context context = getActivity();
//        if (context != null) {
//            Intent intent = new Intent(context, UniversalActivity.class);
//            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
//            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
//            if (requestCode >= 0) {
//                startActivityForResult(intent, requestCode);
//            } else {
//                startActivity(intent);
//            }
//        }
//    }
//
//    /**
//     * 使用UniversalActivity启动给定的Fragment
//     *
//     * @param fragCls 待启动Fragment
//     * @param args    传递给Fragment的参数,可空
//     */
//    public void startFragment(@NonNull Class<? extends BaseFragment1> fragCls,
//                              @Nullable Bundle args) {
//        startFragment(fragCls, args, -1);
//    }
//    public void startFragment(@NonNull String fragName,
//                              @Nullable Bundle args, int requestCode) {
//        // 判断
//        if (TextUtils.isEmpty(fragName) || !fragName.contains("com.dhcc")) {
//            return;
//        }
//        try {
//            Class<? extends BaseFragment1> fragClass = (Class<? extends BaseFragment1>) Class.forName(fragName);
//            startFragment(fragClass, args,requestCode);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void startFragment(@NonNull String fragName, @Nullable Bundle args) {
//        startFragment(fragName, args,-1);
//    }
//
//    /**
//     * 使用UniversalActivity启动给定的Fragment
//     *
//     * @param fragCls     待启动Fragment
//     * @param requestCode -1表示不使用ForResult
//     */
//    public void startFragment(@NonNull Class<? extends BaseFragment1> fragCls,
//                              int requestCode) {
//        startFragment(fragCls, null, requestCode);
//    }
//
//    /**
//     * 使用UniversalActivity及其子类启动给定的Fragment
//     *
//     * @param containerCls UniversalActivity及其子类
//     * @param fragCls      待启动Fragment
//     */
//    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
//                              @NonNull Class<? extends BaseFragment1> fragCls) {
//        startFragment(containerCls, fragCls, null, -1);
//    }
//
//    /**
//     * 使用UniversalActivity及其子类启动给定的Fragment
//     *
//     * @param containerCls UniversalActivity及其子类
//     * @param fragCls      待启动Fragment
//     * @param args         传递给Fragment的参数,可空
//     * @param requestCode  -1表示不使用ForResult
//     */
//    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
//                              @NonNull Class<? extends BaseFragment1> fragCls,
//                              @Nullable Bundle args, int requestCode) {
//        Context context = getActivity();
//        if (context != null) {
//            Intent intent = new Intent(context, containerCls);
//            intent.putExtra(UniversalActivity.RootFragmentClassName, fragCls.getName());
//            intent.putExtra(UniversalActivity.RootFragmentArgs, args);
//            if (requestCode >= 0) {
//                startActivityForResult(intent, requestCode);
//            } else {
//                startActivity(intent);
//            }
//        }
//    }
//
//    /**
//     * 使用UniversalActivity及其子类启动给定的Fragment
//     *
//     * @param containerCls UniversalActivity及其子类
//     * @param fragCls      待启动Fragment
//     * @param args         传递给Fragment的参数,可空
//     */
//    public void startFragment(@NonNull Class<? extends UniversalActivity> containerCls,
//                              @NonNull Class<? extends BaseFragment1> fragCls,
//                              @Nullable Bundle args) {
//        startFragment(containerCls, fragCls, args, -1);
//    }
//
//    /**
//     * 显示一个从底部动画推上来的对话框
//     *
//     * @param contentFragment 要显示的内容Fragment
//     * @return 如果返回null, 说明没有正常显示对话框
//     */
//    public BasePushDialog showPushDialog(BaseFragment1 contentFragment) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            return ((BaseActivity) activity).showPushDialog(contentFragment);
//        } else {
//            return null;
//        }
//    }
//
//    final void requestPermissionsResult(String permission, int grantResult) {
//        // 通知子类
//        onRequestPermissionsResult(permission, grantResult == PackageManager.PERMISSION_GRANTED);
//    }
//
//    /**
//     * 权限请求结果回调
//     *
//     * @param permission 权限名称
//     * @param granted    是否请求成功
//     */
//    public void onRequestPermissionsResult(String permission, boolean granted) {
//        // nothing
//    }
//
//    /**
//     * 显示LoadingTip
//     *
//     * @param type 显示加载提示框的样式类型
//     */
//    public void showLoadingTip(BaseActivity.LoadingType type) {
//        showLoadingTip(type, false);
//    }
//
//    /**
//     * 显示LoadingTip
//     *
//     * @param type       显示加载提示框的样式类型
//     * @param cancelable 是否可以点击后隐藏;TOP类型时无效
//     */
//    public void showLoadingTip(final BaseActivity.LoadingType type, final boolean cancelable) {
//
//        //        return;
//
//
//        hideLoadingTip();
//        if (getActivity() == null) {
//            return;
//        }
//        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                mLoadingType = type;
//                if (mContainerMaskContainer != null) {
//                    mContainer.removeView(mContainerMaskContainer);
//                    mContainerMaskContainer = null;
//                }
//                if (getActivity() == null) {
//                    return;
//                }
//                mContainerMaskContainer = new FrameLayout(getActivity());
//                mContainerMaskContainer.setId(R.id.base_container_mask_container);
//                mContainer.addView(mContainerMaskContainer);
//                mContainerMaskContainer.setVisibility(View.VISIBLE);
//                switch (mLoadingType) {
//                    default:
//                    case FULL:
//                        if (mFullLoadingView == null) {
//                            mFullLoadingView = onCreateFullLoadingView();
//                            mContainerMaskContainer.addView(mFullLoadingView);
//                        }
//                        mFullLoadingView.setCancelable(cancelable, new Runnable() {
//                            @Override
//                            public void run() {
//                                if (mContainerMaskContainer != null) {
//                                    mContainer.removeView(mContainerMaskContainer);
//                                    mContainerMaskContainer = null;
//                                }
//                            }
//                        });
//                        if (!mFullLoadingView.isShowing()) {
//                            mFullLoadingView.show(null);
//                        }
//                        break;
//                    case TOP:
//                        if (mTopLoadingView == null) {
//                            mTopLoadingView = onCreateTopLoadingView();
//                            mContainerMaskContainer.addView(mTopLoadingView);
//                        }
//                        if (!mTopLoadingView.isShowing()) {
//                            mTopLoadingView.show(null);
//                        }
//                        break;
//                    case BOTTOM:
//                        if (mBottomLoadingView == null) {
//                            mBottomLoadingView = onCreateBottomLoadingView();
//                            mContainerMaskContainer.addView(mBottomLoadingView, new FrameLayout.LayoutParams(
//                                    FrameLayout.LayoutParams.MATCH_PARENT,
//                                    FrameLayout.LayoutParams.WRAP_CONTENT,
//                                    Gravity.BOTTOM));
//                        }
//                        if (!mBottomLoadingView.isShowing()) {
//                            mBottomLoadingView.show(null);
//                        }
//                        break;
//                }
//                isLoadingTipShowing = true;
//            }
//        });
//    }
//
//    /**
//     * 隐藏LoadingTip
//     */
//    public void hideLoadingTip() {
//        if (getActivity() == null) {
//            return;
//        }
//        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                switch (mLoadingType) {
//                    default:
//                    case FULL:
//                        if (mFullLoadingView != null) {
//                            final BaseFullLoadingView tmp = mFullLoadingView;
//                            mFullLoadingView = null;
//                            tmp.hide(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (mContainerMaskContainer != null) {
//                                        mContainer.removeView(mContainerMaskContainer);
//                                        mContainerMaskContainer = null;
//                                    }
//                                }
//                            });
//                        }
//                        break;
//                    case TOP:
//                        if (mTopLoadingView != null) {
//                            final BaseTopLoadingView tmp = mTopLoadingView;
//                            mTopLoadingView = null;
//                            tmp.hide(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (mContainerMaskContainer != null) {
//                                        mContainer.removeView(mContainerMaskContainer);
//                                        mContainerMaskContainer = null;
//                                    }
//                                }
//                            });
//                        }
//                        break;
//                    case BOTTOM:
//                        if (mBottomLoadingView != null) {
//                            final BaseBottomLoadingView tmp = mBottomLoadingView;
//                            mBottomLoadingView = null;
//                            tmp.hide(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (mContainerMaskContainer != null) {
//                                        mContainer.removeView(mContainerMaskContainer);
//                                        mContainerMaskContainer = null;
//                                    }
//                                }
//                            });
//                        }
//                        break;
//                }
//                isLoadingTipShowing = false;
//            }
//        });
//    }
//
//    /**
//     * 创建一个FullLoadingView
//     *
//     * @return 返回创建的FullLoadingView
//     */
//    protected BaseFullLoadingView onCreateFullLoadingView() {
//        BaseFullLoadingView fullLoadingView = (BaseFullLoadingView) LayoutInflater.from(getActivity())
//                .inflate(R.layout.view_base_full_loading, null);
//        return fullLoadingView;
//    }
//
//    /**
//     * 创建一个TopLoadingView
//     *
//     * @return
//     */
//    protected BaseTopLoadingView onCreateTopLoadingView() {
//        BaseTopLoadingView topLoadingView = (BaseTopLoadingView) LayoutInflater.from(getActivity())
//                .inflate(R.layout.view_base_top_loading, null);
//        return topLoadingView;
//    }
//
//    /**
//     * 创建一个TopLoadingView
//     *
//     * @return
//     */
//    protected BaseBottomLoadingView onCreateBottomLoadingView() {
//        BaseBottomLoadingView bottomLoadingView = (BaseBottomLoadingView) LayoutInflater.from(getActivity())
//                .inflate(R.layout.view_base_bottom_loading, null);
//        return bottomLoadingView;
//    }
//
//    /**
//     * 关闭所属的Activity
//     */
//    public void finish() {
//        finish(null);
//    }
//
//    /**
//     * 关闭所属的Activity
//     *
//     * @param data setResult中的返回数据
//     */
//    public void finish(Bundle data) {
//        Intent intent = null;
//        if (data != null) {
//            intent = new Intent();
//            intent.putExtras(data);
//        }
//        BaseActivity activity = (BaseActivity) getActivity();
//        if (activity != null) {
//            if (intent != null) {
//                activity.setResult(Activity.RESULT_OK, intent);
//            }
//            activity.finish();
//        }
//    }
//
//    /**
//     * 显示网络加载失败的提示
//     */
//    public void showNetworkFailTip() {
//        if (getActivity() == null) {
//            return;
//        }
//        showLoadFailTip(R.drawable.wuwangluo_ico, getStringSafe(R.string.str_base_network_fail_tip));
//    }
//
//    /**
//     * 显示LoadFailTip
//     *
//     * @param iconResId 提示图标DrawableResID
//     * @param tip       错误提示信息
//     */
//    public void showLoadFailTip(final @DrawableRes int iconResId, final CharSequence tip) {
//        hideLoadFailTip();
//        if (getActivity() == null) {
//            return;
//        }
//        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                if (mContainerMaskContainer != null) {
//                    mContainer.removeView(mContainerMaskContainer);
//                    mContainerMaskContainer = null;
//                }
//                if (getActivity() == null) {
//                    return;
//                }
//                mContainerMaskContainer = new FrameLayout(getActivity());
//                mContainerMaskContainer.setId(R.id.base_container_mask_container);
//                mContainerMaskContainer.addView(
//                        LayoutInflater.from(getActivity()).inflate(
//                                R.layout.no_net_show_layout, mContainerMaskContainer, false),
//                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                                FrameLayout.LayoutParams.MATCH_PARENT));
//                ImageView imageView = mContainerMaskContainer
//                        .findViewById(R.id.noNet_showView_imageView);
//                imageView.setImageResource(iconResId);
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        onLoadFailRetryClicked();
//                    }
//                });
//                TextView textView = mContainerMaskContainer
//                        .findViewById(R.id.noNet_showView_textView);
//                if (TextUtils.isEmpty(tip)) {
//                    textView.setVisibility(View.GONE);
//                } else {
//                    textView.setText(tip);
//                    textView.setVisibility(View.VISIBLE);
//                }
//                mContainer.addView(mContainerMaskContainer, new FrameLayout.LayoutParams(
//                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//                mContainerMaskContainer.setVisibility(View.VISIBLE);
//                if (mContainerChild != null) {
//                    mContainer.setBackgroundResource(R.color.color_base_content);
//                    mContainerChild.setVisibility(View.INVISIBLE);
//                }
//                isLoadFailTipShowing = true;
//            }
//        });
//    }
//
//    /**
//     * 安全方式调用getString
//     *
//     * @param resId
//     * @return
//     */
//    public String getStringSafe(@StringRes int resId) {
//        if (getActivity() == null) {
//            return "";
//        } else {
//            return getString(resId);
//        }
//    }
//
//    /**
//     * 隐藏LoadFailTip
//     */
//    public void hideLoadFailTip() {
//        if (getActivity() == null) {
//            return;
//        }
//        ((BaseActivity) getActivity()).getUIHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                if (mContainerChild != null) {
//                    mContainer.setBackgroundResource(android.R.color.transparent);
//                    mContainerChild.setVisibility(View.VISIBLE);
//                }
//                if (mContainerMaskContainer != null) {
//                    mContainer.removeView(mContainerMaskContainer);
//                    mContainerMaskContainer = null;
//                }
//                isLoadFailTipShowing = false;
//            }
//        });
//    }
//
//    /**
//     * 加载失败提示中【点击重试】后,回调到这里
//     */
//    protected void onLoadFailRetryClicked() {
//        //        Logger.d(getClass().getName() + ": LoadFailRetryClicked");
//    }
//
//    /**
//     * 更新消息信息
//     */
//    public void setMessage(int messageNum,String soundFlag,String vibrateFlag) {
//        Activity activity = getActivity();
//        if (activity != null && activity instanceof BaseActivity) {
//            ((BaseActivity) activity).setmessage(messageNum,soundFlag,vibrateFlag);
//        }
//    }
//
//    /**
//     * 处理扫描数据
//     *
//     * @param intent
//     * @return
//     */
//    protected String doScanInfo(Intent intent) {
//        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
//            Bundle bundle = new Bundle();
//            bundle = intent.getExtras();
//            String scanInfo = bundle.getString("data");
//            if (scanInfo != null) {
//                // 只有数字和横杆
//                //                Matcher m = Pattern.compile("[^0-9\\-]").matcher(scanInfo);
//                //                return m.replaceAll("").trim();
//                Log.e(TAG, "scanInfo: " + scanInfo);
//                return scanInfo.replaceAll(" ", "").trim();
//            }
//        }
//        return null;
//    }
//
//    public class BaseReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.e(TAG,"(BaseReceiver.java:910) "+intent.toString());
//            boolean bRegNo = false;
//            String strScan = "";
//            if (Objects.requireNonNull(intent.getAction()).equals(Action.DEVICE_SCAN_CODE)) {
//                Bundle bundle = new Bundle();
//                bundle = intent.getExtras();
//                String scanInfo = bundle.getString("data");
//                for (int i = 0; i < listRegNo.size(); i++) {
//                    if (scanInfo.equals(listRegNo.get(i))){
//                        bRegNo = true;
//                        strScan = scanInfo;
//                    }
//                }
//            }
//            //判断当前扫码是不是患者列表中的一个regNo
//            if (bRegNo){
//                //判断是否为执行页
//                if (isChangePat){
//                    setMsgToSingleActivity(strScan);
//                }else {
//                    //如果在执行页并且当前患者和扫码患者相同的情况下走执行操作
//                    if (singleRegNo.equals(intent.getExtras().getString("data"))){
//                        getScanMsg(intent);
//                    }else {
//                        setMsgToSingleActivity(strScan);
//                    }
//
//                }
//
//            }else {
//                getScanMsg(intent);
//            }
//        }
//    }
//
//
//
//
//    public Button btnVoice;
//    public EditText etTest;
//    //第一次点击不用计时
//    public Boolean firstClick=true;
//    public Long curTime = System.currentTimeMillis();
//
//    public AsrDialog mAsrDialog;
//    public String centerTitle = "";//通过标题或setScene判断当前场景
//    public String meetingId = "";//备忘录用，设置当前语音id
//    public Boolean isStart = true;//备忘录用，startRecord只调用一次
//    public String fileId = "";//服务端生成的id。通过id获取音频地址
//    public Boolean isActionUp = true;
//
//
//    public String bedNoByVoice = "";
//
//
//
//    public void setMeetingId(String meetingId) {
//        this.meetingId = meetingId;
//    }
//
//    private VoiceUtil voiceUtil;
//    private void  initVoice(){
////        btnVoice.setOnTouchListener(btnVoiceTouchListener);
//
//        voiceUtil = new VoiceUtil(getActivity(),this,btnVoice,etTest);
//        voiceUtil.initVoice();
//        voiceUtil.setTemVoiceListener(new VoiceUtil.TempVoiceCallBack() {
//            @Override
//            public void getTempVoice(VoiceBean voiceBean) {
//                getVoiceResult(voiceBean);
//            }
//        });
//    }
//
//    public void actionDown() {
////        getBedMap();
//        VoiceWebDataUtil.getBedMapData();
//        if (firstClick) {
//            firstClick = false;
//        } else {
//            if (System.currentTimeMillis() - curTime < 1000) {
//                ToastUtils.cancel();
//                ToastUtils.showShort("不可频繁点击");
//                return;
//            } else {
//                curTime = System.currentTimeMillis();
//            }
//        }
//        isActionUp = false;
//        SpeechRecognizerManager.switchScene(getScene());
//        SpeechRecognizerManager.setListener(speechListener);
//        Log.e(TAG, "actionDown: 111111" + getScene());
//
//        if (centerTitle.equals("转写场景") && !meetingId.isEmpty() && isStart) {
//            SpeechRecognizerManager.switchScene(getScene());
//            Log.e(TAG, "actionDown: " + getScene());
//            SpeechRecognizerManager.startRecord(meetingId, new RequestCallback() {
//                @Override
//                public void onSuccess(int i, Object o) {
//                    fileId = o.toString();
//                    Log.e("mp3", meetingId + "startRecord fileId:" + fileId + getScene());
//                }
//
//                @Override
//                public void onFailure(int i, String s) {
//                    Log.e("mp3", meetingId + "startRecord error:" + s + getScene());
//                }
//            });
//            isStart = false;
//        }
//
//
//        //开始转写
//        if (centerTitle.equals("转写场景")) {
//            SpeechRecognizerManager.start(meetingId);
//        } else {
//            SpeechRecognizerManager.startOnPush();
//        }
//
//
//        if (mAsrDialog == null) {
//            mAsrDialog = new AsrDialog(getActivity());
//            mAsrDialog.show();
//            mAsrDialog.setCanceledOnTouchOutside(false);
//            mAsrDialog.setCancelable(false);
//            mAsrDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    actionUp();
//                }
//            });
//        }
//    }
//    public void actionUp(){
//        isActionUp = true;
//        //stop,等待转写结果，默认3s，设置请参考设置部分的代码
//        speechStop();
//    }
//
//    public void setScene(String scene){
//        centerTitle = scene;
//        if (voiceUtil!=null){
//            voiceUtil.setScene(scene);
//        }
//    }
//    public String getScene(){
//        String speechScene = "donghua_1_1";
//        if (SharedPreference.SCENE_LIST!=null){
//            if (SharedPreference.SCENE_LIST.size()<1){
//                showToast("未获取场景");
//            }else {
//                speechScene = SharedPreference.SCENE_LIST.get(0).getSceneId();
//                for (int i = 0; i < SharedPreference.SCENE_LIST.size(); i++) {
//                    if (centerTitle.equals(SharedPreference.SCENE_LIST.get(i).getSceneName())){
//                        speechScene = SharedPreference.SCENE_LIST.get(i).getSceneId();
//                    }
//                }
//            }
//        }else {
////            showToastByVoice("未获取场景");
//            SpeechRecognizerManager.getSceneList(new SceneListResponse() {
//                @Override
//                public void onResponse(List<Scene> list, int i, String s) {
//                    if (list.size()>0){
//                        SharedPreference.SCENE_LIST = list;
//                    }
//                }
//            });
//
//        }
//        Log.e(TAG, "getScene: "+centerTitle+"--"+speechScene );
//        return speechScene;
//    }
//
//    public static <T> byte[] concat(byte[] first, byte[] second) {
//        byte[] result = Arrays.copyOf(first, first.length + second.length);
//        System.arraycopy(second, 0, result, first.length, second.length);
//        return result;
//    }
//    private Handler handler=new Handler();
//    private List<byte[]> fileList = new ArrayList<>();
//
//    public int getFileListSize(){
//        return fileList.size();
//    }
//    private byte[] bytesAll;
//    private int testInt=0;
//    private View.OnTouchListener btnVoiceTouchListener = new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            int ea = event.getAction();
//            switch (ea) {
//                case MotionEvent.ACTION_DOWN:
//                    btnVoice.setSelected(true);
//                    etTest.setText("");
//                    testInt = 1;
//                    actionDown();
//                case MotionEvent.ACTION_MOVE:
//                    break;
//                case MotionEvent.ACTION_UP:
//                    btnVoice.setSelected(false);
//                    if (mAsrDialog!=null){
//                        mAsrDialog.setTipText("正在获取结果，请稍后...");
//                    }
//                    actionUp();
//                    handler1.postDelayed(runnable, 3000);
//                    break;
//            }
//            return false;
//        }
//    };
//    public RecognizerListener speechListener = new RecognizerListener() {
//        @Override
//        public void onRecordData(byte[] bytes) {
//            //fileList.add(bytes);//音频保存到本地
//        }
//
//        @Override
//        public void onVolumeChanged(int i) {
//            //返回音量变化，自定义界面展示录音动画
//            showWave(i);
//        }
//
//        @Override
//        public void onBeginOfSpeech() {
//            Log.i(TAG+"json", "开始录音");
//            new Thread(new Runnable() {//创建异步线程环境
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mAsrDialog != null) {
//                                mAsrDialog.setTipText("正在录音,请讲话...");
//                            }
//                        }
//                    });
//                }
//            }).start();
//
//        }
//
//        @Override
//        public void onEndOfSpeech() {
//            Log.i(TAG+"json5", "录音结束");
//            new Thread(new Runnable() {//创建异步线程环境
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            String ss = "/sdcard/" + meetingId+".wav";
//                            File outFile = new File(ss);
//                            if (mAsrDialog != null) {
//                                mAsrDialog.setTipText("正在获取结果，请稍后...");
////                                mAsrDialog.dismiss();
//                            }
//                        }
//                    });
//                }
//            }).start();
//
//        }
//
//        @Override
//        public void onResult(AsrResult asrResult, boolean isLast) {
//            voicResult(asrResult,isLast);
//        }
//
//        @Override
//        public void onError(final SpeechError speechError) {
//            Log.e("onError",speechError.getErrorDescription());
//            //SpeechError speechError.getErrorCode() 错误码请参考接入文档
//            Message message = new Message();
//            message.what = 99;
//            message.obj = speechError.getErrorDescription();
//            speechStop();
//            if (mAsrDialog != null) {
//                Log.e(TAG, "run: 11111"+"3" );
//                mAsrDialog.dismiss();
//                handler1.removeCallbacks(runnable);
//                mAsrDialog = null;
//                showToast(speechError.getErrorDescription());
//            }
//            return;
//        }
//    };
//
//    public Handler handler1=new Handler();
//    public  Runnable runnable=new Runnable() {
//        @Override
//        public void run() {
//            Log.e(TAG, "run: 11111"+"2111" );
//            if (mAsrDialog!=null
//                    &&mAsrDialog.isShowing()){
////                SpeechRecognizerManager.stopNow();
//                Log.e(TAG, "run: 11111"+"2" );
//                mAsrDialog.dismiss();
//                handler1.removeCallbacks(runnable);
//                mAsrDialog=null;
//            }
//        }
//    };
//
//    public void hindBtn(){
//        btnVoice.setVisibility(View.GONE);
//    }
//    public void showBtn(){
//        btnVoice.setVisibility(View.VISIBLE);
//    }
//
//    public void  speechStop(){
//        Log.e(TAG, "speechStop: "+centerTitle );
//        if (centerTitle.equals("转写场景")){
//            SpeechRecognizerManager.stop();
//        }else {
//            SpeechRecognizerManager.stopOnPush();
//        }
//    }
//    public void showWave(int value) {
//        //Log.i(TAG, value+"");
//        if (mAsrDialog != null){
//            new Thread(new Runnable() {//创建异步线程环境
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i("onResult jsonwave","jsonResult:"+value);
//                            if (mAsrDialog!=null){
//                                if (value < 1500) {
//                                    mAsrDialog.setEnergy(R.mipmap.mic0);
//                                } else if (value < 2500) {
//                                    mAsrDialog.setEnergy(R.mipmap.mic1);
//                                } else if (value < 4500) {
//                                    mAsrDialog.setEnergy(R.mipmap.mic2);
//                                } else {
//                                    mAsrDialog.setEnergy(R.mipmap.mic3);
//                                }
//                            }
//                        }
//                    });
//                }
//            }).start();
//        }
//    }
//
//
//    public void voicResult(AsrResult asrResult, boolean isLast){
//
//        Log.i("onResult json1"+centerTitle,asrResult.isResult+"jsonResult:"+asrResult.jsonResult+",isLast:"+isLast);
//        Boolean ifUseResult = asrResult.isResult;
//        if (centerTitle.equals("体征录入")||centerTitle.equals("费用补录")){
//            ifUseResult = true;
//        }
//        //判断是否有结果
//        if (ifUseResult) {
//            try {
//                Message message = new Message();
//                message.what = 100;
//                Bundle bundle = new Bundle();
//                bundle.putString("results", asrResult.results);
//                Log.i("onResult json11"+centerTitle,"results:"+asrResult.results+",isLast:"+isLast);
//                bundle.putString("json", new JSONObject(asrResult.jsonResult).toString());
//                //bundle.putString("json", "{\"command\":{\"action\":\"生命体征\",\"code\":2001},\"form\":{\"code\":200}}");
//                bundle.putBoolean("isLast", isLast);
////                    message.setData(bundle);
//                new Thread(new Runnable() {//创建异步线程环境
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                getTempResultByVoice(bundle);
//                                testInt++;
//                                try {
//                                    etTest.setText(centerTitle+"--"+getScene()+etTest.getText().toString()+"\n"+testInt+"."+asrResult.results+".jsonResult:"
//                                            +new JSONObject(asrResult.jsonResult).toString()
//                                            +",isLast:"+isLast);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                    }
//                }).start();
//
//                new Thread(new Runnable() {//创建异步线程环境
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (isLast&&isActionUp){
//                                    if (mAsrDialog != null) {
//                                        mAsrDialog.dismiss();
//                                        handler1.removeCallbacks(runnable);
//                                        Log.e(TAG, "run: 11111"+"4" );
//                                        mAsrDialog = null;
//                                    }
//                                    Gson gson = new Gson();
//                                    VoiceBean voiceBean = gson.fromJson(bundle.getString("json"),  VoiceBean.class);
//                                    if (voiceBean==null){
//                                        voiceBean=new VoiceBean();
//                                    }else {
//                                        if (voiceBean.getCommand()==null){
//                                            voiceBean.setCommand(new VoiceBean.CommandBean());
//                                        }
//                                        if (voiceBean.getForm()==null){
//                                            voiceBean.setForm(new VoiceBean.FormBean());
//                                        }else {
//                                            if (voiceBean.getForm().getData()==null){
//                                                voiceBean.getForm().setData(new ArrayList<VoiceBean.FormBean.DataBean>());
//                                            }
//                                        }
//                                    }
//                                    voiceBean.setLast(isLast);
//                                    Log.i("onResult json3","jsonResult:"+isLast);
//                                    getVoiceResult(voiceBean);
//                                }else if (needTempScene()){
//                                    Gson gson = new Gson();
//                                    VoiceBean voiceBean = gson.fromJson(bundle.getString("json"),  VoiceBean.class);
//                                    if (voiceBean==null){
//                                        voiceBean=new VoiceBean();
//                                    }else {
//                                        if (voiceBean.getCommand()==null){
//                                            voiceBean.setCommand(new VoiceBean.CommandBean());
//                                        }
//                                        if (voiceBean.getForm()==null){
//                                            voiceBean.setForm(new VoiceBean.FormBean());
//                                        }else {
//                                            if (voiceBean.getForm().getData()==null){
//                                                voiceBean.getForm().setData(new ArrayList<VoiceBean.FormBean.DataBean>());
//                                            }
//                                        }
//                                    }
//                                    voiceBean.setLast(isLast);
//                                    Log.i("onResult json3","jsonResult:"+isLast);
//                                    getVoiceResult(voiceBean);
//                                }
//                            }
//                        });
//                    }
//                }).start();
//                Log.i("onResult json2","jsonResult:"+new JSONObject(asrResult.jsonResult).toString()+",isLast:"+isLast);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }else if (isLast){
//            new Thread(new Runnable() {//创建异步线程环境
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (isLast&&isActionUp){
//                                if (mAsrDialog != null) {
//                                    mAsrDialog.dismiss();
//                                    handler1.removeCallbacks(runnable);
//                                    Log.e(TAG, "run: 11111"+"5" );
//                                    mAsrDialog = null;
//                                }
////                                   showToast(centerTitle+"语音获取结果为空");
//                            }
//                        }
//                    });
//                }
//            }).start();
//
//        }
//    }
//
//    // public Boolean called = false;
//    public void getTempResultByVoice(Bundle bundle){
//
//    }
//    //判断当前场景下是否需要获取语音中间返回数据
//    public Boolean needTempScene(){
//
//        return centerTitle.equals("体征录入")
//                ||centerTitle.equals("日常生活能力")
//                ||centerTitle.equals("Barden压力性皮肤损伤评估录入")
//                ||centerTitle.equals("跌倒评估录入")
//                ||centerTitle.equals("坠床评估录入")
//                ||centerTitle.equals("肌力受损评估录入")
//                ||centerTitle.equals("导管滑落风险评估录入")
//                ||centerTitle.equals("Autar深静脉血栓录入")
//                ||centerTitle.equals("CPOT疼痛评估录入")
//                ||centerTitle.equals("血压监测记录单录入")
//                ||centerTitle.equals("手术科室护理记录单录入")
//                ||centerTitle.equals("费用补录");
//    }
//
//    public void getVoiceResult(VoiceBean voiceBean){
//
//
//        String bedNoStr = "";
//        if (voiceBean.getCommand().getBedNo() != null){
//            bedNoStr = voiceBean.getCommand().getBedNo();
//        }
//        String actionStr = "";
//        if (voiceBean.getCommand().getAction() != null){
//            actionStr = voiceBean.getCommand().getAction();
//        }
//        startFragmentByVoice(bedNoStr,actionStr);
//    }
//    private void startFragmentByClassName(String className){
//        Class<? extends BaseFragment1> aClass = null;
//        try {
//            aClass = (Class<? extends BaseFragment1>) Class.forName(className);
//            startFragment(aClass);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private void startFragmentByClassNameWithBed(String name,String className,String bedNo){
//        Class<? extends BaseFragment1> aClass = null;
////        VoiceBedMapBean voiceBedMapBean = new VoiceBedMapBean();
//        BedMapBean bedMapBean = new BedMapBean();
//        String episodeId = "";
//        String patInfo = "";
//        String inHosDate = "";
//        String jsonMap = "";
//        Bundle bundle = new Bundle();
//        Boolean isBedExist = false;
//        if (bedNo.isEmpty()){
////            showToastByVoice("未读床位，无法跳转到"+name);
//            showToast("未读床位，无法跳转到"+name);
//            return;
//        }
//        try {
//            aClass = (Class<? extends BaseFragment1>) Class.forName(className);
//            Gson gson = new Gson();
////            voiceBedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),VoiceBedMapBean.class);
//            bedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
////            for (int i = 0; i < voiceBedMapBean.getPatInfoList().size(); i++) {
////                if (bedNo.equals(voiceBedMapBean.getPatInfoList().get(i).getBedCode())&&voiceBedMapBean.getPatInfoList().get(i).getInBedAll().equals("1")){
////                    isBedExist = true;
////                    episodeId = voiceBedMapBean.getPatInfoList().get(i).getEpisodeId();
////                    patInfo = voiceBedMapBean.getPatInfoList().get(i).getBedCode()+" "+voiceBedMapBean.getPatInfoList().get(i).getName();
////                    inHosDate = voiceBedMapBean.getPatInfoList().get(i).getRQDDate();
////
////                    bundle.putString("EpisodeID", episodeId);
////                    bundle.putString("BedNo", bedNo);
////                    bundle.putString("PatName", voiceBedMapBean.getPatInfoList().get(i).getName());
////                    bundle.putString("RecID", "");
//            for (int i = 0; i < bedMapBean.getPatInfoList().size(); i++) {
//                if (bedNo.equals(bedMapBean.getPatInfoList().get(i).getBedCode())){
//                    isBedExist = true;
//                    episodeId = bedMapBean.getPatInfoList().get(i).getEpisodeId();
//                    patInfo = bedMapBean.getPatInfoList().get(i).getBedCode()+" "+bedMapBean.getPatInfoList().get(i).getName();
////                    inHosDate = voiceBedMapBean.getPatList().get(i).getRQDDate();
//
//                    bundle.putString("EpisodeID", episodeId);
//                    bundle.putString("BedNo", bedNo);
//                    bundle.putString("PatName", bedMapBean.getPatInfoList().get(i).getName());
//                    bundle.putString("RecID", "");
//
//
//
//                    if (name.equals("基本信息")||name.equals("床位图")){
//                        List<Map<String, String>> patInfoMapList =new ArrayList<>();
//                        Map bedMapMap = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PATINFO_JSON), Map.class);
//                        patInfoMapList = (List<Map<String, String>>) bedMapMap.get("patInfoList");
//                        Map map = patInfoMapList.get(i);
//                        if (bedNo.equals(map.get("bedCode"))){
//                            jsonMap = gson.toJson(map);
//                            bundle.putString("jsonmap",jsonMap);
//                        }
//                        if (name.equals("床位图")){
//                            BedMapBean bedMapBean1 = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
//                            BedMapBean.PatInfoListBean patInfoListBean = bedMapBean1.getPatInfoList().get(i);
//                            bundle.putString("patinfo", gson.toJson(patInfoListBean));
//                        }
//                    } else
//                        if (name.equals("日常生活能力评估")){
//                        bundle.putString("EMRCode", "DHCNURBARLR");
//                        bundle.putString("GUID", "bc1e90b31bb44d8bbc2bbd54a2e03a11");
//                    } else if (name.equals("Barden压力性皮肤损伤评估录入")){
//                        bundle.putString("EMRCode", "DHCNURBRADENLR");
//                        bundle.putString("GUID", "8a5c0674abf943ad8e8fd88ccc0a4865");
//                    }else if (name.equals("跌倒评估录入")){
//                        bundle.putString("EMRCode", "DHCNURDDPGLR");
//                        bundle.putString("GUID", "06675c370a7b42d6b2fd03feaafc8086");
//                    }else if (name.equals("坠床评估录入")){
//                        bundle.putString("EMRCode", "DHCNURZCPGLR");
//                        bundle.putString("GUID", "ffb19e3d0eb54ce4b137db8feb68b8e1");
//                    }else if (name.equals("肌力受损评估录入")){
//                        bundle.putString("EMRCode", "DHCNURJLPGLR");
//                        bundle.putString("GUID", "a87e6c19262748d7a9cac9fce20e7e3b");
//                    }else if (name.equals("导管滑落风险评估录入")){
//                        bundle.putString("EMRCode", "DHCNURDGHTPGLR");
//                        bundle.putString("GUID", "c565fe567705418f8f286fecd6322466");
//                    }else if (name.equals("Autar深静脉血栓录入")){
//                        bundle.putString("EMRCode", "DHCNURAUTARLR");
//                        bundle.putString("GUID", "3cb84f4e8dcf4c46b23de30714230588");
//                    }else if (name.equals("CPOT疼痛评估录入")){
//                        bundle.putString("EMRCode", "DHCNURCOPTLR");
//                        bundle.putString("GUID", "2686bd7d0f024855a9364298625a33e7");
//                    }else if (name.equals("血压监测记录单录入")){
//                        bundle.putString("EMRCode", "DHCNURxyjcjldlr");
//                        bundle.putString("GUID", "f98c9904a8e3426daac4fa26d33609b7");
//                    }else if (name.equals("手术科室护理记录单录入")){
//                        bundle.putString("EMRCode", "DHCNURSSKSLR");
//                        bundle.putString("GUID", "e1baca1f0bb24e90bdec4610cef7f980");
//                    }
//                }
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        bundle.putSerializable("episodeId", episodeId);
//        bundle.putString("patInfo", patInfo);
//        bundle.putString("inHosDate",inHosDate);
//        bundle.putString("patmsg", patInfo);
//        if (isBedExist){
//            startFragment(aClass,bundle);
//        }else {
//            showToast(bedNo.replaceAll("床","")+"床不存在，请确认床号");
////            showToastByVoice(bedNo.replaceAll("床","")+"床不存在，请确认床号");
//        }
//
//
//    }
//    private void startFrag(String startype){
//        for (int i = 0; i < SharedPreference.FRAGMENTARY.size(); i++) {
//            Map map = (Map) SharedPreference.FRAGMENTARY.get(i);
//            if (map.get("fragName").toString().contains(startype)){
//                Class<? extends BaseFragment1> aClass = null;
//                try {
//                    aClass = (Class<? extends BaseFragment1>) Class.forName(map.get("fragName").toString());
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                if (!aClass.getName().contains("WorkareaFragment")){
//                    String regNo = "";
//                    if (bedNoByVoice.isEmpty()){
//                        startFragment(aClass);
//                    }else {
//                        Bundle bundle = new Bundle();
//                        Gson gson = new Gson();
//
//                        VoiceVisalBean voiceVisalBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_VISAL_LIST),VoiceVisalBean.class);
//                        int index = 0;
//                        List timeFilterList = (ArrayList) voiceVisalBean.getMapAll().get("timeSelect");
//                        String dateFilterStr = voiceVisalBean.getDatePoint();
//                        String timeFilterStr = voiceVisalBean.getTimePoint();
//                        if (!bedNoByVoice.isEmpty()){
//                            for (int j = 0; j < voiceVisalBean.getPatInfoList().size(); j++) {
//                                String tempBedCode = voiceVisalBean.getPatInfoList().get(j).getBedCode();
//                                if (bedNoByVoice.equals(tempBedCode)){
//                                    index = j;
//                                }
//                            }
//                        }
//
//                        String patInfo = "";
//                        String episodeId = "";
////                        VoiceBedMapBean voiceBedMapBean = new VoiceBedMapBean();
//                        BedMapBean bedMapBean = new BedMapBean();
//                        bedMapBean = gson.fromJson(SPUtils.getInstance().getString(SharedPreference.VOICE_PAT_LIST),BedMapBean.class);
//                        for (int j = 0; j < bedMapBean.getPatInfoList().size(); j++) {
//                            if (bedNoByVoice.equals(bedMapBean.getPatInfoList().get(j).getBedCode())){
//                                regNo = bedMapBean.getPatInfoList().get(j).getRegNo();
//                                episodeId = bedMapBean.getPatInfoList().get(j).getEpisodeId();
//                            }
//                        }
//
//                        bundle.putSerializable("info", (Serializable) "");
//                        bundle.putString("time", timeFilterStr);
//                        bundle.putString("date", dateFilterStr);
//                        bundle.putInt("index", index);
//                        bundle.putString("regNo", regNo);
//                        bundle.putString("episodeId", episodeId);
//                        bundle.putSerializable("timeList", (Serializable) timeFilterList);
//                        if (regNo.isEmpty()){
//                            showToast(bedNoByVoice.replaceAll("床","")+"床不存在，请确认床号");
////                            showToastByVoice(bedNoByVoice.replaceAll("床","")+"床不存在，请确认床号");
//                        }else {
//                            startFragment(aClass,bundle);
//                        }
//                    }
//
//                }else {
//                    Class<? extends BaseActivity> activityClass = null;
//                    try {
//                        activityClass = (Class<? extends BaseActivity>) Class.forName("com.dhcc.nursepro.Activity.MainActivity");
//                        startActivity(new Intent(getActivity(), activityClass));
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
////        if (startype.equals("VitalSignRecordFragment")){
////            Class<? extends BaseFragment> aClass = null;
////            try {
////                aClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment");
////            } catch (ClassNotFoundException e) {
////                e.printStackTrace();
////            }
////            if (!aClass.getName().contains("WorkareaFragment")){
////                startFragment(aClass);
////            }
////        }
//    }
//    public void startFragmentByVoice(String beNo, String startype){
//        bedNoByVoice = beNo;
//        if (isActionUp){
//            switch (startype){
//                case "床位图":
//                    if (bedNoByVoice.isEmpty()){
//                        startFrag("BedMapFragment");
//                    }else {
//                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.BedMapPatFragment",bedNoByVoice);
//                    }
//                    break;
//                case "医嘱查询":
//                    startFrag("OrderSearchFragment");
//                    break;
//                case "医嘱执行":
//                    startFrag("OrderExecuteFragment");
//                    break;
//                case "入院分床":
//                    startFrag("AllotBedFragment");
//                    break;
//                case "生命体征":
//                    if (bedNoByVoice.isEmpty()){
//                        startFrag("VitalSignFragment");
//                    }else {
//                        startFrag("VitalSignRecordFragment");
//                    }
//                    break;
//                case "事件登记":
//                    startFrag("PatEventsFragment");
//                    break;
//                case "检查报告":
//                    if (bedNoByVoice.isEmpty()){
//                        startFrag("CheckPatsFragment");
//                    }else {
//                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.checkresult.CheckResultListFragment",bedNoByVoice);
//                    }
//                    break;
//                case "医嘱单":
//                    startFrag("DocOrderListFragment");
//                    break;
//                case "手术申请":
//                    startFrag("OperationFragment");
//                    break;
//                case "检验结果":
//                    if (bedNoByVoice.isEmpty()){
//                        startFrag("LabPatsFragment");
//                    }else {
//                        startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.labresult.LabResultListFragment",bedNoByVoice);
//                    }
//                    break;
//                case "检验打包":
//                    startFrag("LabOutListFragment");
//                    break;
//                case "输血系统":
//                    startFrag("BloodTransfusionSystemFragment");
//                    break;
//                case "输液复核":
//                    startFrag("DosingReviewFragment");
//                    break;
//                case "母乳闭环":
//                    startFrag("MilkLoopSystemFragment");
//                    break;
//                case "护理病历":
//                    startFrag("PatNurRecordFragment");
//                    break;
//                case "日常生活能力评估":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "Barden压力性皮肤损伤评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "跌倒评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "坠床评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "导管滑落风险评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "Autar深静脉血栓录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "肌力受损评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "CPOT疼痛评估录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "血压监测记录单录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "手术科室护理记录单录入":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment",bedNoByVoice);
//                    break;
//                case "护理巡视":
//                    startFrag("NurTourFragment");
//                    break;
//                case "静配接收":
//                    startFrag("DrugReceiveFragment");
//                    break;
//                case "主页":
//                    startFrag("WorkareaFragment");
//                    break;
//                case "血液签收":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodsign.BloodSignFragment");
//                    break;
//                case "语音备忘":
//                    startFragmentByClassName("com.dhcc.nursepro.setting.NoteBookWriteFragment");
//                    break;
//                case "血液输注":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusion.BloodTransfusionFragment");
//                    break;
//                case "输血巡视":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusiontour.BloodTransfusionTourFragment");
//                    break;
//                case "输血结束":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodtransfusionend.BloodTransfusionEndFragment");
//                    break;
//                case "血液回收":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodbagrecycling.BloodBagRecyclingFragment");
//                    break;
//                case "血液查询":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.bloodtransfusionsystem.BloodOperationListFragment");
//                    break;
//                case "病理打包":
//                    startFragmentByClassName("com.dhcc.nursepro.workarea.plyout.PlyOutListFragment");
//                    break;
//                case "声纹管理":
////                    Class<? extends BaseActivity> activityClass = null;
////                    try {
////                        activityClass = (Class<? extends BaseActivity>) Class.forName("voice.raisound_vp.VoiceprintManagerActivity");
////                        startActivity(new Intent(getActivity(), activityClass));
////                    } catch (ClassNotFoundException e) {
////                        e.printStackTrace();
////                    }
//                    break;
//                case "所管床设置":
//                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingBedsFragment");
//                    break;
//                case "查询时间":
//                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingDateTimeFragment");
//                    break;
//                case "消息设置":
//                    startFragmentByClassName("com.dhcc.nursepro.setting.SettingWayFragment");
//                    break;
//                case "体征曲线图":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignChartsDetailFragment",bedNoByVoice);
//                    break;
//                case "住院日清单":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.DayPayListFragment",bedNoByVoice);
//                    break;
//                case "基本信息":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.BedMapPatInfoFragment",bedNoByVoice);
//                    break;
//                case "费用补录":
//                    startFragmentByClassNameWithBed(startype,"com.dhcc.nursepro.workarea.bedmap.AfterPayFragment",bedNoByVoice);
//                    break;
//                case "返回":
//                    finishCurFragment();
//                    break;
//                default:
//                    if (startype.contains("体征录入")){
//                        startFrag("VitalSignRecordFragment");
//                    }else {
//                        if (bedNoByVoice.equals("")){
////                            showToastByVoice("语音识别失败");
////                            showToast("语音识别失败");
//                        }else {
//                            showToast(bedNoByVoice);
//                        }
//
//                    }
//                    break;
//            }
//
//        }
//    }
//
//
//    public void finishCurFragment(){
//        finish();
//    }
//}