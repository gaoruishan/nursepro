package com.base.commlibs.listener;

import android.text.TextWatcher;

/**
 * EditText 文本观察器 (省去重写两个方法)
 * @author:gaoruishan
 * @date:202021/3/26/09:49
 * @email:grs0515@163.com
 */
public abstract class CustomTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}
