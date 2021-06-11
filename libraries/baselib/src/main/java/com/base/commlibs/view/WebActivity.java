package com.base.commlibs.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.commlibs.R;
import com.base.commlibs.base.BaseWebActivity;
import com.base.commlibs.bean.RequestParam;
import com.base.commlibs.bean.WebConfigBean;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.CommFile;
import com.base.commlibs.utils.HttpUtil;
import com.base.commlibs.utils.SimpleCallBack;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWebConfig;

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
    public static final String EVENT_TOAST = "toast";
    public static final String EVENT_LOADING = "loading";
    public static final String EVENT_DIALOG = "dialog";
    public static final String SHOW = "show";
    public static final String FAIL = "fail";
    public static final String URL_SCANLABEL = "scanlabel";
    public static final String URL_SCANPAT = "scanpat";
    public static final String URL_OFFLINE = "offline";
    public static final String URL_CLEAR = "clear";
    private static String TAG = WebActivity.class.getSimpleName();
    private static boolean scanLabel;
    private static boolean scanPat;
    private static boolean offLine;
    private static boolean clear;

    private ProgressBar loadingProgress;
    private LinearLayout linearLayout;
    private XScanView xScan;
    private WebConfigBean configBean;


    private String getLocalUrl() {
        return "file:///mnt/sdcard/dhc/" + getUrlName();
    }


    private String getUrlName() {
        String s = url.replaceAll("/", "-").replaceAll(":", "-");
        if (url.contains(".html")) {
            return s;
        } else {
            return s + ".html";
        }
    }

    private String url;

    /***
     * 开启Web
     * @param context
     * @param url
     */
    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        //判断关键字
        scanLabel = url.contains(URL_SCANLABEL);
        scanPat = url.contains(URL_SCANPAT);
        offLine = url.contains(URL_OFFLINE);
        clear = url.contains(URL_CLEAR);
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
        Log.e(TAG, "(WebActivity.java:125) getScanMsg=" + scanInfo);
        if (!TextUtils.isEmpty(scanInfo)) {
            if (mAgentWeb != null) {
                mAgentWeb.getJsAccessEntrace().quickCallJs("getScanMsg", scanInfo);
            }
            if (xScan != null) {
                xScan.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //设置标题
        setStatusBarBackgroundViewVisibility(true, 0xff4C95EF);
        setToolbarBackground(new ColorDrawable(0xff4C95EF));
        //初始化View
        initView();
        //初始化数据
        initData();

        url = getIntent().getStringExtra(URL);
        //兼容带http的
        String[] split = url.split(BaseWebServiceUtils.getServiceUrl(""));
        String s =split[1];
        if (s.startsWith(HTTP)) {
            url = s;
        }
        if (!offLine) {
            if (url.startsWith(HTTP) || url.startsWith(FILE)) {
                initWebView(linearLayout, url);
            }
        } else {
            String dhc = CommFile.ROOT_PATH + getUrlName();
            boolean fileExists = FileUtils.isFileExists(dhc);
            if (fileExists) {
                String localUrl = getLocalUrl();
                Log.e(TAG, "(WebActivity.java:95) 加载本地:" + localUrl);
                initWebView(linearLayout, localUrl);
            } else {
                //下载
                loadingProgress.setVisibility(View.VISIBLE);
                downReload();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void callJsConfig() {
        if (mAgentWeb != null) {
            mAgentWeb.getJsAccessEntrace().quickCallJs("Config");
        }
    }

    /**
     * 初始化配置
     * @param value
     */
    @Override
    protected void initConfig(String value, String type) {
        if (!TextUtils.isEmpty(value)) {
            try {
                configBean = GsonUtils.fromJson(value, WebConfigBean.class);
                Log.e(TAG,"(WebActivity.java:181) "+configBean.toString());
                setToolbarCenterTitle(configBean.title, 0xffffffff, 17);
                if ("1".equals(configBean.hideToolBar)) {
                    getToolbar().setVisibility(View.GONE);
                }
                if (configBean.toolBar != null) {
                    if ("text".equalsIgnoreCase(configBean.toolBar.type)) {
                        setToolbarRightCustomView(getRightTextView(configBean.toolBar.text, configBean.toolBar.method));
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "(WebActivity.java:187) " + e.toString());
            }

        }
    }

    /**
     * 添加筛选按钮
     * @return
     */
    public View getRightTextView(String txt, String method) {
        TextView textView = new TextView(mActivity);
//        ImageView textView = new ImageView(mContext);
//        textView.setImageResource(R.drawable.dhcc_filter_big_write);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(mActivity.getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(20, 0, 20, 0);
        textView.setTextSize(15);
        textView.setText(txt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAgentWeb != null) {
                    mAgentWeb.getJsAccessEntrace().quickCallJs(method);
                }
            }
        });
        return textView;
    }

    //下载后 重新加载
    private void downReload() {
        HttpUtil.get(url, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                String name = getUrlName();
                CommFile.writeFile(name, result);
                String localUrl = getLocalUrl();
                Log.e(TAG, "(WebActivity.java:47) 下载完成,重新加载 " + localUrl);
                initWebView(linearLayout, localUrl);
                loadingProgress.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        if (scanPat || scanLabel) {
            xScan.setVisibility(View.VISIBLE);
        }
        if (scanPat) {
            xScan.setTitle("请扫描腕带").setWarning("请你使用扫码设备,扫码患者腕带");
        }
        //清空缓存
        if (clear) {
            Log.e(TAG, "(WebActivity.java:253) clear=" + clear);
            AgentWebConfig.clearDiskCache(this);
        }
    }

    private void initView() {
        loadingProgress = findViewById(R.id.loading_progress);
        linearLayout = findViewById(R.id.ll_web);
        xScan = findViewById(R.id.x_scan);
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
        //js回调获取配置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            callJsConfig();
        }
    }

    @Override
    protected void onWebViewPageStarted(WebView webView, String url, Bitmap favicon) {
    }


    @Override
    protected void onCallEvents(String content, String type) {
        Log.e("onCallEvents", "main Thread:" + Thread.currentThread());
        if (EVENT_TOAST.equalsIgnoreCase(type)) {
            ToastUtils.showShort(content);
        }
        if (EVENT_LOADING.equalsIgnoreCase(type)) {
            boolean b = SHOW.equalsIgnoreCase(content);
            loadingProgress.setVisibility(b ? View.VISIBLE : View.GONE);
        }
        if (EVENT_DIALOG.equalsIgnoreCase(type)) {
            if (content.contains(FAIL)) {
                CommDialog.showCommDialog(ActivityUtils.getTopActivity(), content, "", R.drawable.icon_popup_error_patient, null, true);
            }
        }
    }

    @Override
    protected void onCallRequest(String content, String type) {
        Log.e("onCallRequest", "main Thread:" + Thread.currentThread());

        RequestParam requestParam = GsonUtils.fromJson(content, RequestParam.class);

        CommWebService.call(requestParam.getMethod(), requestParam.getParams(), new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                if (mAgentWeb != null) {
                    mAgentWeb.getJsAccessEntrace().quickCallJs(requestParam.getMethod(), jsonStr);
                }
            }
        });
    }


}
