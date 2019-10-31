package com.base.commlibs.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.Utils;

/**
 * 支持剪贴板,扫描光标位置,模拟键盘三种扫码方式
 * @author:gaoruishan
 * @date:202019-08-09/15:11
 * @email:grs0515@163.com
 */
public class EditTextScanHelper {

    private static final String TAG = EditTextScanHelper.class.getSimpleName();
    private static final int DELAY_MILLIS = 300;
    /**
     * 按键监听
     * @return
     */
    public View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                //删除不处理
                return false;
            }
//            dispatchEventListener(keyCode, keyEvent.getAction());
            return false;
        }
    };
    //上次点击的时间
    private long lastClickTime = 0;
    //时间间隔
    private int spaceTime = 300;
    private OnScanListener mListener;
    private EditText editText;
    /**
     * 扫描事件.
     **/
    private MTextWatcher textwatcher;
    private ClipboardManager.OnPrimaryClipChangedListener clipChangedListener;
    private ClipboardManager clipboard;
    private boolean isFocus;
    private boolean isClipBoardText;

    public EditTextScanHelper() {
        textwatcher = new MTextWatcher();
    }

    public boolean isOkClick() {
        long currentTime = System.currentTimeMillis();
        boolean isAllowClick;//是否允许点击
        isAllowClick = currentTime - lastClickTime >= spaceTime;
        lastClickTime = currentTime;
        return isAllowClick;
    }

    /**
     * 在dispatchKeyEvent添加
     * @param keyCode
     * @param action
     * @return
     */
    public void dispatchEventListener(int keyCode, int action) {
        if (keyCode == KeyEvent.KEYCODE_DEL || keyCode == KeyEvent.KEYCODE_BACK) {
            //删除不处理
            return;
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER && !isFocus) {
            //换行符 + editText没有焦点
            return;
        }
        if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            //处理数字输入-谷歌输入法
            return;
        }
        Log.e(TAG, "(EditTextScanHelper.java:58) keyCode=" + keyCode + ",action=" + action);
        if (action == KeyEvent.ACTION_DOWN) {
            //巧夺焦点
            reqFocus();
            String barcode = editText.getText().toString().replaceAll("\n", "");
            if (TextUtils.isEmpty(barcode)) {
                //如果为空,从剪切板获取
                dealClipBoardText();
            }
            //成为PDA按键模拟-结束符(制表,换行)
            if (keyCode == KeyEvent.KEYCODE_TAB || keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.e(TAG, " 模拟键盘");
                setOnResult(barcode);//2,模拟键盘
            }
        }
    }

    private void dealClipBoardText() {
        isClipBoardText = false;
        //先移除
        removePrimaryClipChangedListener();
        clipChangedListener = new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                isClipBoardText = true;
                CharSequence text = ClipboardUtils.getText();
                Log.e(TAG, " 剪贴板" + text);
                setOnResult(text.toString());//1,剪贴板
            }
        };
        clipboard = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        // 添加剪贴板数据改变监听器
        if (clipboard != null) {
            clipboard.addPrimaryClipChangedListener(clipChangedListener);
        }
    }

    /**
     * 回调Scan结果
     * @param s 有数据至少四位
     */
    private void setOnResult(String s) {
        KeyboardUtils.hideSoftInput(editText);
        if (mListener != null && !TextUtils.isEmpty(s) && s.length() > 2) {
            if (isOkClick()) {
                mListener.onResult(s);
            }
        }
        editText.setText("");
    }

    /**
     * 移除剪切板监听
     */
    public void removePrimaryClipChangedListener() {
        if (clipChangedListener != null && clipboard != null) {
            clipboard.removePrimaryClipChangedListener(clipChangedListener);
        }
    }

    public EditText getEditText() {
        return editText;
    }

    /**
     * 添加EditText及监听
     * @param context
     * @param listener
     * @return
     */
    public void addEditTextInputListener(Context context, OnScanListener listener) {
        this.mListener = listener;
        editText = new EditText(context);
        //一个点
        editText.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
//        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //透明
        int color = Color.parseColor("#00000000");
        editText.setCursorVisible(false);
        editText.setBackgroundColor(color);
        editText.setTextColor(color);
        editText.setTextSize(1.0f);
        reqFocus();
        editText.addTextChangedListener(textwatcher);
        editText.setOnKeyListener(onKeyListener);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isFocus = hasFocus;
            }
        });

    }

    /**
     * 强制请求焦点
     */
    private void reqFocus() {
        editText.setFocusable(true);
        editText.setClickable(true);
        editText.requestFocus();
        //隐藏软键盘
        KeyboardUtils.hideSoftInput(editText);
    }

    /**
     * 回调接口
     */
    public interface OnScanListener {

        void onResult(String code);
    }

    private class MTextWatcher implements TextWatcher {
        private boolean isAutoScan;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            /**
             * 三个参数的值
             * start before count
             * 0     0      13 <br>
             * 扫描时触发的 ：start=0，before=0，count=1 <br>
             * 手动输入时，显示的数据 012 023 034 045<br>
             * start=0，before=1，count=2，<br>
             * start=0，before=2，count=3，<br>
             *  手动通过键盘输入:是每个字母都会触发textWatcher事件 扫描是整体触发,不会单个触发. **
             **/
            isAutoScan = false;
            if (start == 0 && before == 0 && count > 1) {
                // 当扫描一个字符时，会出错
//                Log.e(TAG, "(MTextWatcher.java:46) 当扫描事件触发的时候"+ s);
                isAutoScan = true;
            } else {
                // 为手动输入触发的事件.
                isFocus = true;
//                Log.e(TAG, "(MTextWatcher.java:46) 为手动输入触发的事件 " + s);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s) && isAutoScan) {
                Log.e(TAG, " 扫描光标位置" + s.toString());
                setOnResult(s.toString());//3,扫描光标位置
            }
        }
    }

}
