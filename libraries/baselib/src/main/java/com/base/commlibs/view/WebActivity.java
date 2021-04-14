package com.base.commlibs.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.base.commlibs.R;
import com.base.commlibs.base.BaseWebActivity;
import com.base.commlibs.bean.RequestParam;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.CommFile;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * 对webView封装
 * https://github.com/Justson/AgentWeb
 * @author:gaoruishan
 * @date:2019/1/29/14:25
 * @email:grs0515@163.com
 */
public class WebActivity extends BaseWebActivity {

    public static final String HTTP = "http";
    public static final String FILE = "file";
    public static final String VIEW_SOURCE = "view-source";
    public static final String URL = "URL";
    private static String TAG = WebActivity.class.getSimpleName();

    private String mHtml;
    private String mCharactersets;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = getUrlName();
            saveFile(s);
            String localUrl = getLocalUrl();
            isReload = true;
            Log.e(TAG, "(WebActivity.java:47) " + isReload + ", " + localUrl);
            mAgentWeb.getUrlLoader().loadUrl(localUrl);
        }
    };
    private ProgressBar loadingProgress;

    private void saveFile(String s) {

        CommFile.writeFile(s, mHtml);
    }

    private String getLocalUrl() {
        return "file:///mnt/sdcard/dhc/" + getUrlName();
    }

    private boolean isReload;

    private String getUrlName() {
        return url.replaceAll("/", "-").replaceAll(":", "-") + ".html";
    }

    private String url;

    /***
     * 开启Web
     * @param context
     * @param url
     */
    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("URL", url);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册扫码监听
        registerScanMsgReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        String scanInfo = doScanInfo(intent);
        Log.e(TAG,"(WebActivity.java:105) getScanMsg="+scanInfo);
        if (!TextUtils.isEmpty(scanInfo)) {
            if (mAgentWeb != null) {
                mAgentWeb.getJsAccessEntrace().quickCallJs("getScanMsg", scanInfo);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置标题
        setStatusBarBackgroundViewVisibility(true, 0xff4C95EF);
        setToolbarBackground(new ColorDrawable(0xff4C95EF));
        //清空缓存
//        AgentWebConfig.clearDiskCache(this);
        //广播码
//        List<BroadcastListBean> broadcastListBeans = new ArrayList<>();
//        BroadcastListBean broadcastListBean = new BroadcastListBean();
//        broadcastListBean.setAction("com.scanner.broadcast");
//        broadcastListBean.setDecode("data");
//        broadcastListBean.setName("成为");
//        broadcastListBeans.add(broadcastListBean);
//        TransBroadcastUtil.setScanActionList(broadcastListBeans);

        setContentView(R.layout.activity_web);
        loadingProgress = findViewById(R.id.loading_progress);
        LinearLayout linearLayout = findViewById(R.id.ll_web);
        url = getIntent().getStringExtra(URL);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        // 富文本
        if (url.startsWith(HTTP) || url.startsWith(FILE)) {
            initWebView(linearLayout, url);
        }
        if (url.startsWith(VIEW_SOURCE)) {
            String dhc = CommFile.ROOT_PATH + getUrlName();
            if (FileUtils.isFileExists(dhc)) {
                String localUrl = getLocalUrl();
                Log.e(TAG, "(WebActivity.java:95) 加载本地:" + localUrl);
                initWebView(linearLayout, localUrl);
            } else {
                initWebView(linearLayout, url);
            }
        }

        isReload = false;
    }


    private void sendJavascript(WebView webView) {
        if (!url.startsWith(VIEW_SOURCE)) {
            return;
        }
        String dhc = CommFile.ROOT_PATH + getUrlName();
        if (FileUtils.isFileExists(dhc)) {
            return;
        }
        if (isReload) {
            return;
        }
        String js = "document.execCommand('selectall');" +
                "var txt;" +
                "if (window.getSelection) {" +
                "txt = window.getSelection().toString();" +
                "} else if (window.document.getSelection) {" +
                "txt = window.document.getSelection().toString();" +
                "} else if (window.document.selection) {" +
                "txt = window.document.selection.createRange().text;" +
                "}" +
                //网页格式
                "var charactersets = document.characterSet;" +
                //获取<link />
//                "var linkObj = document.querySelectorAll('link[rel=\"stylesheet\"]');"+
//                "var linkStr= document.querySelector('link[rel=\"stylesheet\"]').getAttribute('href');" +
//                "linkObj.forEach(function(item){linkStr = linkStr + item.href +',' });"+
                // JsInterface是定义的obj, getHtmlSource是回调方法
                "android.callAndroid(txt,charactersets);";
        // 获取页面内容
//        js = "window.JsInterface.getHtmlSource(document.getElementsByTagName('html')[0].innerHTML,s);";
        // 获取解析<meta name="share-description" content="获取到的值">
//        String  jsDes ="window.JsInterface.showDescription("
//                + "document.querySelector('link[rel=\"stylesheet\"]').getAttribute('href')"
//                + ");";
        //注入
        evaluateJavascript(webView, js);
    }

    protected void evaluateJavascript(WebView webView, String js) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("javascript:" + js, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.e(TAG, "(WebActivity.java:343) onReceiveValue=" + value);
                }
            });
            Log.e(TAG, "evaluateJavascript-javascript");
        } else {
            webView.loadUrl("javascript:" + js);
            Log.e(TAG, "loadUrl-javascript");
        }
    }

    @Override
    protected void onWebChromeReceivedTitle(WebView view, String title) {
        if (!TextUtils.isEmpty(title)) {
            setToolbarCenterTitle(title, 0xffffffff, 17);
        }
    }

    @Override
    protected void onWebChromeProgressChanged(WebView view, int newProgress) {
        if (newProgress == PROGRESS_OK) {
            onWebViewPageFinished(view, view.getUrl());
        }
    }

    @Override
    protected void onWebViewPageFinished(WebView view, String url) {
        String title = view.getTitle();
        Log.e(TAG, "(WebActivity.java:222) title=" + title);
        if (!TextUtils.isEmpty(title)) {
            setToolbarCenterTitle(title, 0xffffffff, 17);
        }
        sendJavascript(view);
    }

    @Override
    protected void onWebViewPageStarted(WebView webView, String url, Bitmap favicon) {
    }

    @Override
    protected void onHtmlSource(String html, String charactersets) {
        mHtml = html;
        mCharactersets = charactersets;
        mHandler.sendEmptyMessage(100);
    }

    @Override
    protected void onCallEvents(String content, String type) {
        Log.e("onCallEvents", "main Thread:" + Thread.currentThread());
        if ("toast".equalsIgnoreCase(type)) {
            ToastUtils.showShort(content);
        }
        if ("loading".equalsIgnoreCase(type)) {
            boolean b = "show".equalsIgnoreCase(content);
            loadingProgress.setVisibility(b ? View.VISIBLE : View.GONE);
        }
        if ("dialog".equalsIgnoreCase(type)) {
            if (content.contains("fail")) {
                CommDialog.showCommDialog(ActivityUtils.getTopActivity(), content, "", R.drawable.icon_popup_error_patient, null, true);
            }
        }
    }

    @Override
    protected void onCallRequest(String content, String type) {
        Log.e("onCallRequest", "main Thread:" + Thread.currentThread());
//        showLoadingTip(LoadingType.FULL);
        /**
         * 模拟
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        RequestParam requestParam = GsonUtils.fromJson(content, RequestParam.class);
        HashMap<String, String> properties = new HashMap<>();

        HashMap<String, String> proParams = requestParam.getParams();
        //解决=的乱码问题
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        //统一添加公共参数
        CommWebService.addGroupId(proParams);
        CommWebService.addHospitalId(proParams);
        CommWebService.addLocId(proParams);
        CommWebService.addUserId(proParams);
        CommWebService.addWardId(proParams);

        properties.put(BaseWebServiceUtils.PARAMS, gson.toJson(proParams));
        properties.put(BaseWebServiceUtils.VERSION, requestParam.getVersion());
        properties.put(BaseWebServiceUtils.METHOD, requestParam.getMethod());
        BaseWebServiceUtils.callWebOPPDAService(BaseWebServiceUtils.REQUST_METHOD, properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void callBack(String result) {
                Log.e("callWebOPPDAService", "main Thread:" + Thread.currentThread());
//                hideLoadingTip();
//                new ParserUtil<>().parserCommResult(result);
                if (mAgentWeb != null) {
                    mAgentWeb.getJsAccessEntrace().quickCallJs(requestParam.getMethod(), result, "ok");
                }
            }
        });
    }


}
