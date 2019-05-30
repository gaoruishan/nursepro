package com.dhcc.module.infusion.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.noober.background.drawable.DrawableCreator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.base.commlibs.utils.CommonUtil.dip2px;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;


/**
 * @author:gaoruishan
 * @date:2018/8/23/17:08
 * @email:grs0515@163.com
 */
public class ViewUtil {


    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int CYAN = 0xff80ffff;
    private static final int GREEN = 0xff80ff80;
    private static final String TAG = ViewUtil.class.getSimpleName();
    static long lastClickTime = 0;//上次点击的时间
    static int spaceTime = 1000;//时间间隔

    /**
     * 隐藏/显示布局
     * @param itemView
     * @param isVisible
     */
    public static void setItemVisibility(View itemView, boolean isVisible) {
        ViewGroup.LayoutParams param = itemView.getLayoutParams();
        if (param == null) {
            return;
        }
        if (isVisible) {
            param.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            // 这里注意使用自己布局的根布局类型
            param.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            // 这里注意使用自己布局的根布局类型
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }

    /**
     * 显示小红点
     * @param context
     * @param textView
     * @param show
     */
    public static void setBackGroundRedSpot(Context context, TextView textView, boolean show) {
        Drawable rightDrawale = null;
        if (show) {
            textView.setCompoundDrawablePadding(dpTpPx(context, 1));
            // TODO 添加图片
//            rightDrawale = ContextCompat.getDrawable(context, R.drawable.bg_oval_red);
            // 这一步必须要做,否则不会显示.
            rightDrawale.setBounds(0, 0, rightDrawale.getMinimumWidth(), rightDrawale.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, rightDrawale, null);
    }

    public static int dpTpPx(Context context, float value) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }

    /**
     * 设置text变大
     * @param textView
     * @param s
     * @param start
     * @param end
     */
    public static void setTextRelativeSizeSpan(TextView textView, String s, int start, int end) {
        SpannableString spannableString = new SpannableString(s);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.5f);
        spannableString.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    /**
     * 设置拼接￥.00字符
     * @param textView
     * @param str
     */
    public static void setDollarText(TextView textView, String str) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        String s = "￥" + str + ".00";
        SpannableString spannableString = new SpannableString(s);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.5f);
        spannableString.setSpan(sizeSpan, 1, s.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    public static void setDollarText2(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            String s;
            if (!str.contains(".")) {
                s = "￥" + str + ".00";
            } else {
                s = "￥" + str;
            }
            String[] split = str.split("\\.");
            int l = split[1].length() + 1;
            SpannableString spannableString = new SpannableString(s);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.2f);
            spannableString.setSpan(sizeSpan, 1, s.length() - l, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.setText(spannableString);
        }
    }

    /**
     * if(ViewUtil.isFastClick()) {
     *  return;
     * }
     * //正常点击的逻辑
     * .....
     * @return
     */
    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();//当前系统时间
        boolean isAllowClick;//是否允许点击
        if (currentTime - lastClickTime > spaceTime) {
            isAllowClick = false;
        } else {
            isAllowClick = true;
        }
        lastClickTime = currentTime;
        return isAllowClick;
    }

    /**
     * 过滤HTML里去除p、span外的所有标签* @param str* @return* @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "(?!<(p|span).*?>)<.*?>";
        Pattern p_html = compile(regEx, CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(str);
        str = m_html.replaceAll("");
        return str.trim();

    }

    /**
     * 加载HTML富文本
     * @param webview
     * @param content
     */
    public static void setWebView(WebView webview, String content) {
        content = stringFilterSpace(content);
        //支持js
        setWebSetting(webview);
        webview.loadData(content, "text/html; charset=UTF-8", null);
    }

    /**
     * 解决空白问题的代码
     * @param content
     * @return
     */
    private static String stringFilterSpace(String content) {
        if (!content.contains("html")) {
            String head = "<head>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                    "<style>*{margin:0;padding:0;}img{max-width: 100%; width:auto; height:auto;}</style>" +
                    "</head>";
            return "<html>" + head + "<body>" + content + "</body></html>";
        }

        return content;
    }

    /**
     * 设置web属性
     * @param webview
     */
    public static void setWebSetting(WebView webview) {
        //支持js
        webview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
        // 显示放大缩小
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //取消滚动条白边效果
        webview.setWebChromeClient(new WebChromeClient());
//        webview.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode,
//                                        String description, String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//            }
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                //super.onReceivedSslError(view, handler, error);
//                handler.proceed();
//            }
//        });

        webview.setWebViewClient(new WebViewClient());
        //设置默认为utf-8
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.getSettings().setBlockNetworkImage(false);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);

        // 解决对某些标签的不支持出现白屏
        webview.getSettings().setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //注意安卓5.0以上的权限
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    public static void setWebViewUrl(WebView webview, String content) {
        setWebSetting(webview);
        webview.loadUrl(content);
    }

    /**
     * 释放webView
     * @param webView
     */
    public static void setReleaseWebView(WebView webView) {
        if (webView != null) {
            webView.pauseTimers();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }

    /**
     * 设置中文过滤器
     * @param et_custom_name
     */
    public static void setChineseFilters(EditText et_custom_name) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!isChinese(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        //如果想要再实现输入字符数量的限制，可以这么写,如果限制字符数为6，就在LengthFilter中传入参数6
        et_custom_name.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(6)});
    }

    /**
     * 判定输入汉字
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    // 隐藏输入法
    public static void hideKeyBoard(AppCompatActivity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 显示输入
    public static void showKeyBoard(FragmentActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * 设置背景圆角
     * @param view
     * @param dp
     * @param color
     */
    public static void setBgRadiusColor(View view,int dp, String color) {
        Drawable drawable3 = new DrawableCreator.Builder()
                .setCornersRadius(dip2px(dp))
                .setSolidColor(Color.parseColor(color))
                .build();
        view.setBackground(drawable3);
    }
    public static void setBgRadiusColor(TextView view,int dp, String color,String textColor) {
        setBgRadiusColor(view,dp,color);
        view.setTextColor(Color.parseColor(textColor));
    }

    /**
     * 设置背景
     * @param view
     */
    public void setBackground(View view) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

}
