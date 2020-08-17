package com.base.commlibs.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.base.commlibs.R;


/**
 * <p>
 * author Q
 * Date: 2019/5/22
 * Time:17:36
 */
public class KeyBoardUtil {

    private KeyboardView keyboardView;
    private EditText editText;
    private int id = -1;
    private Keyboard keyboard;// 键盘
    private getNextFocusListener getNextFocusListener;
    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE:
                    if (editable != null && editable.length() > 0) {
                        if (start > 0) {
                            editable.delete(start - 1, start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_DONE:
                    keyboardView.setVisibility(View.GONE);
                    if (id != -1) {
                        getNextFocusListener.getNextFocus(id);
                    }
                    break;
                default:
                    editable.insert(start, Character.toString((char) primaryCode));
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {

            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            editable.insert(start, text);
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeUp() {
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyBoardUtil(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;

        this.keyboard = new Keyboard(editText.getContext(), R.xml.key);

        //显示光标
        if (android.os.Build.VERSION.SDK_INT <= 10) {//4.0以下
            this.editText.setInputType(InputType.TYPE_NULL);
        } else {
            this.editText.setShowSoftInputOnFocus(false);
        }
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.keyboardView.setKeyboard(keyboard);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyBoardUtil(KeyboardView keyboardView, EditText editText,int id) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.id = id;

        this.keyboard = new Keyboard(editText.getContext(), R.xml.key);

        //显示光标
        if (android.os.Build.VERSION.SDK_INT <= 10) {//4.0以下
            this.editText.setInputType(InputType.TYPE_NULL);
        } else {
            this.editText.setShowSoftInputOnFocus(false);
        }
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.keyboardView.setKeyboard(keyboard);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
    }

    // Activity中点击触发时，显示出键盘
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void setOngetNextFocusListener(getNextFocusListener getNextFocusListener) {
        this.getNextFocusListener = getNextFocusListener;
    }

    /**
     * 获取焦点回调
     */
    public interface getNextFocusListener {
        void getNextFocus(int id);
    }
}
