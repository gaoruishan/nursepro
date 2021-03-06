package com.dhcc.res.util;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.noober.background.drawable.DrawableCreator;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.dhcc.res.util.CommonUtil.dip2px;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;


/**
 * @author:gaoruishan
 * @date:2018/8/23/17:08
 * @email:grs0515@163.com
 */
public class ViewUtil {


    public static final String FORMAT_0_00 = "0.00";
    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int CYAN = 0xff80ffff;
    private static final int GREEN = 0xff80ff80;
    private static final String TAG = ViewUtil.class.getSimpleName();
    private static long lastClickTime = 0;//?????????????????????
    private static int spaceTime = 1000;//????????????

    /**
     * ??????/????????????
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
            // ????????????????????????????????????????????????
            param.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            // ????????????????????????????????????????????????
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }

    /**
     * ??????Selector
     * @param context
     * @param idNormal
     * @param idPress
     * @return
     */
    public static Drawable getSelectorDrawable(Context context, int idNormal, int idPress) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = context.getResources().getDrawable(idNormal);
        Drawable press = context.getResources().getDrawable(idPress);
//        drawable.addState(new int[]{android.R.attr.state_pressed}, press);
//        drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
//        drawable.addState(new int[]{android.R.attr.state_selected}, press);
//        drawable.addState(new int[]{-android.R.attr.state_selected}, normal);
        drawable.addState(new int[]{android.R.attr.state_checked}, press);
        drawable.addState(new int[]{-android.R.attr.state_checked}, normal);
        return drawable;
    }
    /**
     * ???????????????
     * @param context
     * @param textView
     * @param show
     */
    public static void setBackGroundRedSpot(Context context, TextView textView, boolean show) {
        Drawable rightDrawale = null;
        if (show) {
            textView.setCompoundDrawablePadding(dpTpPx(context, 1));
            // TODO ????????????
//            rightDrawale = ContextCompat.getDrawable(context, R.drawable.bg_oval_red);
            // ?????????????????????,??????????????????.
            rightDrawale.setBounds(0, 0, rightDrawale.getMinimumWidth(), rightDrawale.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, rightDrawale, null);
    }

    public static int dpTpPx(Context context, float value) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }

    /**
     * ??????text??????
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
     * ???????????????.00??????
     * @param textView
     * @param str
     */
    public static void setDollarText(TextView textView, String str) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        String s = "???" + str + ".00";
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
                s = "???" + str + ".00";
            } else {
                s = "???" + str;
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
     * //?????????????????????
     * .....
     * @return
     */
    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();//??????????????????
        boolean isAllowClick;//??????????????????
        isAllowClick = currentTime - lastClickTime <= spaceTime;
        lastClickTime = currentTime;
        return isAllowClick;
    }

    /**
     * ??????HTML?????????p???span??????????????????* @param str* @return* @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "(?!<(p|span).*?>)<.*?>";
        Pattern p_html = compile(regEx, CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(str);
        str = m_html.replaceAll("");
        return str.trim();

    }

    /**
     * ??????HTML?????????
     * @param webview
     * @param content
     */
    public static void setWebView(WebView webview, String content) {
        content = stringFilterSpace(content);
        //??????js
        setWebSetting(webview);
        webview.loadData(content, "text/html; charset=UTF-8", null);
    }

    /**
     * ???????????????????????????
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
     * ??????web??????
     * @param webview
     */
    public static void setWebSetting(WebView webview) {
        //??????js
        webview.getSettings().setJavaScriptEnabled(true);
        // ????????????????????????
        webview.getSettings().setSupportZoom(true);
        // ??????????????????
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //???????????????????????????
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
        //???????????????utf-8
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.getSettings().setBlockNetworkImage(false);
        //?????????????????????
        webview.getSettings().setUseWideViewPort(true);
        //???????????????
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);

        // ?????????????????????????????????????????????
        webview.getSettings().setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //????????????5.0???????????????
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    public static void setWebViewUrl(WebView webview, String content) {
        setWebSetting(webview);
        webview.loadUrl(content);
    }

    /**
     * ??????webView
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
     * ?????????????????????
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
        //??????????????????????????????????????????????????????????????????,????????????????????????6?????????LengthFilter???????????????6
        et_custom_name.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(6)});
    }

    /**
     * ??????????????????
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    // ???????????????
    public static void hideKeyBoard(AppCompatActivity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // ????????????
    public static void showKeyBoard(FragmentActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * ??????????????????
     * @param view
     * @param dp
     * @param color
     */
    public static void setBgRadiusColor(View view,int dp, String color) {
        Drawable drawable3 = new DrawableCreator.Builder()
                .setCornersRadius(dip2px(view.getContext(),dp))
                .setSolidColor(Color.parseColor(color))
                .build();
        view.setBackground(drawable3);
    }
    public static void setBgRadiusColor(TextView view,int dp, String color,String textColor) {
        setBgRadiusColor(view,dp,color);
        view.setTextColor(Color.parseColor(textColor));
    }

    /**
     * ????????????
     * @param context
     * @param layoutRes
     * @return
     */
    public static View inflate(Context context,@LayoutRes int layoutRes) {
        View view = View.inflate(context,layoutRes, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParams);
        return view;
    }

    /**
     * ????????????map
     * @param object
     * @return
     */
    public static  Map<String, Object> objectToMap(Object object) {
        Map<String, Object> map = null;
        if (object != null) {
            String json = GsonUtils.toJson(object);
            if(!TextUtils.isEmpty(json)){
                Type jsontype = new TypeToken<Map<String, Object>>() {}.getType();
                map = new Gson().fromJson(json, jsontype);
            }
        }
        return map;
    }

    public static void setBetweenTime(TextView observeView, TextView endView,String observeTime, String testStartTime) {
        if (!TextUtils.isEmpty(observeTime)) {
            if (isInteger(observeTime)) {
                observeView.setText(Integer.valueOf(observeTime) / 60 + "??????");
                setBetweenTime(endView, observeTime, testStartTime,1000);
            } else if (observeTime.contains("??????")) {
                observeView.setText(observeTime);
                observeTime = observeTime.replace("??????", "");
                setBetweenTime(endView, observeTime, testStartTime,60000);
            } else {
                observeView.setText(observeTime);
                endView.setText("");
            }
        }
    }

    private static void setBetweenTime(TextView endView, String observeTime, String testStartTime,int off) {
       endView.setText(getBetweenTime(observeTime,testStartTime,off));
    }

    /**
     * ?????????????????? "yyyy-MM-dd HH:mm:ss"
     * @param observeTime
     * @param testStartTime ??????:off=60000  ???:off=1000
     * @param off
     * @return
     */
    public static String getBetweenTime(String observeTime, String testStartTime,int off) {
        SimpleDateFormat formatter;
        if (testStartTime.contains(" ")) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else {
            formatter = new SimpleDateFormat("HH:mm:ss");
        }
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date parse = formatter.parse(testStartTime);
            long l = parse.getTime() + Integer.valueOf(observeTime) * off;
            String format = formatter.format(new Date(l));
            return format;
        } catch (ParseException e) {
        }
        return "";
    }
    /*
     * ?????????????????????
     * @param str ??????????????????
     * @return ???????????????true,????????????false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void setFormatFloat(TextView tv, float f) {
        //???????????????????????????????????????????????????2???,??????0??????.
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_0_00);
        //format ?????????????????????
        if (tv != null && f > 0) {
            String s = decimalFormat.format(f);
            tv.setText(s);
        }
    }

    /**
     * ????????????
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
