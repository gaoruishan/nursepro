package com.dhcc.nursepro.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.dhcc.nursepro.R;


/**
 *
 * <p>
 * author Q
 * Date: 2019/5/22
 * Time:17:36
 */
public class KeyBoardUtil {

    private KeyboardView keyboardView;
    private EditText editText;
    private Keyboard keyboard;// 键盘

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyBoardUtil(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;

        this.keyboard = new Keyboard(editText.getContext(), R.xml.key);

        //显示光标
        if (android.os.Build.VERSION.SDK_INT <= 10) {//4.0以下
            this.editText.setInputType(InputType.TYPE_NULL);
        }else {
            this.editText.setShowSoftInputOnFocus(false);
        }
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.keyboardView.setKeyboard(keyboard);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {

        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {

            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            editable.insert(start, text);
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
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
                    break;
                default:
                    editable.insert(start, Character.toString((char) primaryCode));
                    break;
            }
        }
    };

    // Activity中点击触发时，显示出键盘
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }
}
