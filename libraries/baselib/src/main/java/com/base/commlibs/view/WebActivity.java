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

import com.base.commlibs.R;
import com.base.commlibs.base.BaseWebActivity;
import com.base.commlibs.utils.CommFile;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONObject;

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
            Log.e(TAG,"(WebActivity.java:47) "+isReload+", "+localUrl);
            mAgentWeb.getUrlLoader().loadUrl(localUrl);
        }
    };

    private void saveFile(String s) {

        CommFile.writeFile( s, mHtml);
    }

    private String getLocalUrl() {
        return "file:///mnt/sdcard/dhc/"+ getUrlName();
    }

    private boolean isReload;

    private String getUrlName() {
        return url.replaceAll("/", "-").replaceAll(":","-")+".html";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xff4C95EF);
        setToolbarBackground(new ColorDrawable(0xff4C95EF));
        setContentView(R.layout.activity_web);
        //设置标题
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
                Log.e(TAG,"(WebActivity.java:95) 加载本地:"+localUrl);
                initWebView(linearLayout, localUrl);
            }else {
                initWebView(linearLayout, url);
            }
        }

        isReload = false;
        //调用js
        callJsInterface();
    }

    /**
     * js交互
     */

    private void callJsInterface() {
        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //无参数调用
//            mAgentWeb.getJsAccessEntrace().quickCallJs("callByAndroid");
                //有参数调用
//            mAgentWeb.getJsAccessEntrace().quickCallJs("callByAndroidParam","Hello ! Agentweb");
                //多参数调用,并有返回值
                mAgentWeb.getJsAccessEntrace().quickCallJs("callByAndroidMoreParams", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e("Info","value:"+value);
                        ToastUtils.showShort(value);
                    }
                },getJson(),"say:", " Hello! Agentweb");
            }
        });
    }

    private String getJson(){

        String result="";
        try {

            JSONObject mJSONObject=new JSONObject();
            mJSONObject.put("id",1);
            mJSONObject.put("name","Agentweb");
            mJSONObject.put("age",18);
            result= mJSONObject.toString();
        }catch (Exception e){

        }

        return result;
    }

    private void sendJavascript(WebView webView) {
        if (!url.startsWith(VIEW_SOURCE)){
            return;
        }
        String dhc = CommFile.ROOT_PATH + getUrlName();
        if (FileUtils.isFileExists(dhc)) {
            return;
        }
        if (isReload) {
            return;
        }
        String js="document.execCommand('selectall');"+
                "var txt;" +
                "if (window.getSelection) {" +
                "txt = window.getSelection().toString();" +
                "} else if (window.document.getSelection) {" +
                "txt = window.document.getSelection().toString();" +
                "} else if (window.document.selection) {" +
                "txt = window.document.selection.createRange().text;" +
                "}"+
                //网页格式
                "var charactersets = document.characterSet;"+
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
                    Log.e(TAG,"(WebActivity.java:343) onReceiveValue="+value);
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
            setToolbarCenterTitle(title, 0xff000000, 17);
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
        Log.e(TAG,"(WebActivity.java:222) title="+title);
        if (!TextUtils.isEmpty(title)) {
            setToolbarCenterTitle(title, 0xffffffff, 17);
        }
        sendJavascript(view);
    }

    @Override
    protected void onWebViewPageStarted(WebView webView, String url, Bitmap favicon) {
//        injectJs(webView);
    }

    private void injectJs(WebView webView) {
        String tmp = "function sayHi() { " +
                "        var element1 = document.getElementById(\"j_username\");\n" +
                "        element1.style.height = \"150px\";\n" +
                "        element1.style.background = \"green\";\n" +
                "}";
        // 先注入
        //webView.loadUrl(tmp);
        evaluateJavascript(webView,tmp);
        // 因为js已经注入了，就可以直接调用了
        evaluateJavascript(webView,"sayHi();");
    }

    @Override
    protected void onHtmlSource(String html, String charactersets) {
        mHtml = html;
        mCharactersets = charactersets;
        mHandler.sendEmptyMessage(100);
    }

    @Override
    protected void onCallEvents(String content, String type) {
        Log.e("Info", "main Thread:" + Thread.currentThread());
        ToastUtils.showShort(content);
    }


}
