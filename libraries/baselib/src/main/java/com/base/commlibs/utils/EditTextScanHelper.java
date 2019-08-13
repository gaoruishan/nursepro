package com.base.commlibs.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
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
    private OnScanListener mListener;
    private EditText editText;
    /**
     * 扫描事件.
     **/
    private MTextWatcher textwatcher = new MTextWatcher();

    /**
     * 在dispatchKeyEvent添加
     * @param keyCode
     * @param action
     * @return
     */
    public void dispatchEventListener(int keyCode, int action) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            //删除不处理
            return;
        }
        if (action == KeyEvent.ACTION_DOWN) {
            //巧夺焦点
            editText.setFocusable(true);
            editText.setClickable(true);
            editText.requestFocus();
            String barcode = editText.getText().toString().replaceAll("\n", "");
            if (TextUtils.isEmpty(barcode)) {//如果为空,从剪切板获取
                ClipboardUtils.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                    @Override
                    public void onPrimaryClipChanged() {
                        CharSequence text = ClipboardUtils.getText();
                        setOnResult(text.toString());
                    }
                });
            }
            //成为PDA按键模拟-结束符(制表,换行)
            if (keyCode == KeyEvent.KEYCODE_TAB || keyCode == KeyEvent.KEYCODE_ENTER) {
                setOnResult(barcode);
            }
        }
    }

    private void setOnResult(String s) {
        if (mListener != null && !TextUtils.isEmpty(s)) {
            mListener.onResult(s);
            EditTextScanHelper.this.editText.setText("");
        }
    }

    /**
     * 添加EditText及监听
     * @param context
     * @param listener
     * @return
     */
    public EditText addEditTextInputListener(Activity context, OnScanListener listener) {
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
        editText.setFocusable(true);
        editText.setClickable(true);
        editText.requestFocus();
        editText.addTextChangedListener(textwatcher);
        editText.setOnKeyListener(onKeyListener);
        return editText;
    }

    public void addEditTextInputListener(final EditText editText, OnScanListener listener) {
        this.mListener = listener;
        this.editText = editText;
        this.editText.setFocusable(true);
        this.editText.setClickable(true);
        this.editText.requestFocus();
        this.editText.addTextChangedListener(textwatcher);
        this.editText.setOnKeyListener(onKeyListener);
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
//                Log.e(TAG, "(MTextWatcher.java:46) 当扫描事件触发的时候");
                isAutoScan = true;
            } else {
                // 为手动输入触发的事件.
//                Log.e(TAG, "(MTextWatcher.java:46) 为手动输入触发的事件");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s) && isAutoScan) {
                setOnResult(s.toString());
            }
        }
    }

}
