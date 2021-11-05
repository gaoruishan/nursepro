package com.base.commlibs.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.R;
import com.blankj.utilcode.util.LogUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

/**
 * @author:gaoruishan
 * @date:202020-06-04/15:33
 * @email:grs0515@163.com
 */
public abstract class BaseWebActivity extends BaseActivity {

    public static final int PROGRESS_OK = 100;
    protected AgentWeb mAgentWeb;
    protected Activity mActivity;
    private com.just.agentweb.WebViewClient mWebViewClient = new com.just.agentweb.WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.e(TAG, "(BaseWebActivity.java:41) onPageStarted  " + url);
            onWebViewPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "(BaseWebActivity.java:47) onPageFinished");
            onWebViewPageFinished(view, url);
        }
    };
    private com.just.agentweb.WebChromeClient mWebChromeClient = new com.just.agentweb.WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.e(TAG, "(BaseWebActivity.java:55) onProgressChanged  " + newProgress);
            onWebChromeProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.e(TAG, "(BaseWebActivity.java:62) onReceivedTitle " + title);
            onWebChromeReceivedTitle(view, title);
        }
    };

    protected abstract void onWebChromeReceivedTitle(WebView view, String title);

    protected abstract void onWebChromeProgressChanged(WebView view, int newProgress);

    protected abstract void onWebViewPageFinished(WebView view, String url);

    protected abstract void onWebViewPageStarted(WebView view, String url, Bitmap favicon);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "(BaseWebActivity.java:80) onDestroy");
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "(BaseWebActivity.java:88) onPause");
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.e(TAG, "(BaseWebActivity.java:97) onResume");
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    /**
     * 标题点击返回
     */
    @Override
    protected void onToolbarBackPressed() {
        goBack(KeyEvent.KEYCODE_BACK);
    }

    /**
     * 初始AgentWeb
     * @param linearLayout
     * @param url
     */
    protected void initWebView(LinearLayout linearLayout, String url) {
        Log.e(TAG, "(BaseWebActivity.java:112) initWebView  " + url);
        mAgentWeb = AgentWeb.with(this)
                //传入AgentWeb的父控件。
                .setAgentWebParent(linearLayout, -1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
//                .useDefaultIndicator(-1, 2)
                //关闭进度条
                .closeIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .interceptUnkownUrl()
                //创建AgentWeb。
                .createAgentWeb()
                //设置 WebSettings。
                .ready()
                //WebView载入该url地址的页面并显示。
                .go(url);
        //注入js
        addJavascriptInterfaceAgentWeb();

        AgentWebConfig.debug();
        // AgentWeb 4.0 开始，删除该类以及删除相关的API
//        DefaultMsgConfig.DownloadMsgConfig mDownloadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownloadMsgConfig();
        //  mDownloadMsgConfig.setCancel("放弃");  // 修改下载提示信息，这里可以语言切换

        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(WebView.OVER_SCROLL_NEVER);

//		mAgentWeb.getWebCreator().getWebView().setOnLongClickListener();


        mAgentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //优先使用网络
        mAgentWeb.getWebCreator().getWebView().getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //将图片调整到适合webview的大小
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        //支持内容重新布局
        mAgentWeb.getWebCreator().getWebView().getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持自动加载图片
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadsImagesAutomatically(true);
        //当webview调用requestFocus时为webview设置节点
        mAgentWeb.getWebCreator().getWebView().getSettings().setNeedInitialFocus(true);
        //自适应屏幕
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        mAgentWeb.getWebCreator().getWebView().getSettings().setDomStorageEnabled(true);
        //支持缩放
        mAgentWeb.getWebCreator().getWebView().getSettings().setBuiltInZoomControls(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setSupportZoom(true);

        //允许webview对文件的操作
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccess(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowUniversalAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 表示按返回键时的操作
                    if (goBack(keyCode)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public boolean goBack(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) {
            // 后退
            mAgentWeb.getWebCreator().getWebView().goBack();
            // webview.goForward();//前进
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mActivity != null) {
                mActivity.finish();
            }
        }
        return false;
    }

    /**
     * 注入js
     */
    protected void addJavascriptInterfaceAgentWeb() {
        Log.e(TAG, "(BaseWebActivity.java:199) 注入对象");
        if (mAgentWeb != null) {
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new JsInterface());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 事件
     * @param content
     * @param type
     */
    protected abstract void onCallEvents(String content, String type);

    /**
     * 请求
     * @param content
     * @param type
     */
    protected abstract void onCallRequest(String content, String type);

    /**
     * 配置
     * @param content
     * @param type
     */
    protected abstract void initConfig(String content, String type);

    private Handler deliver = new Handler(Looper.getMainLooper());

    /**
     * Js和Web交互
     */
    public class JsInterface {

        public static final String REQUEST = "request";
        public static final String CONFIG = "config";

        @JavascriptInterface
        public void callAndroid(String content, String type) {
            LogUtils.e("(JsInterface.java:292)  type= " + type + " ,content=  " + content);
            String finalType = type.toUpperCase();
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    //请求
                    if (finalType.equalsIgnoreCase(REQUEST)) {
                        onCallRequest(content, finalType);
                        return;
                    }
                    //配置
                    if (finalType.equalsIgnoreCase(CONFIG)) {
                        initConfig(content, finalType);
                        return;
                    }
                    //事件
                    onCallEvents(content, finalType);
                }
            });

        }
    }

}
