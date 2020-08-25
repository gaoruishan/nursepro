package com.dhcc.nursepro.utils;

import android.text.InputFilter;
import android.text.Spanned;


/**
 * InputNumLengthFilter
 * 限制输入框输入小数位数
 *
 * @author DevLix126
 * created at 2020/8/24 11:39
 */

public class InputDigitLengthFilter implements InputFilter {

    private int digit;

    public InputDigitLengthFilter(int digit) {
        this.digit = digit;
    }


    /**
     * @param source 将要插入的字符串，来自键盘输入、粘贴
     * @param start  source的起始位置，为0（暂时没有发现其它值的情况）
     * @param end    source的长度
     * @param dest   EditText中已经存在的字符串
     * @param dstart 插入点的位置
     * @param dend   插入点的结束位置，一般情况下等于dstart；如果选中一段字符串（这段字符串将会被替换），dstart的值就插入点的结束位置
     * @return ""|null
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        /*
          这个方法将会在使用[start,end)来替换[dstart,dend)时被调用
          返回值是你最终想要添加的文本，若返回""则表示放弃此次改变，若返回null则应用此次改变
          当发生删除操作时source的长度将会是0
          不要直接修改dest的内容，它的内容只是用来查看的。
         */

        // 删除等特殊字符，直接返回
        if (nullFilter(source)) {
            return null;
        }
        int length = dest.toString().length();
        int pointIndex = 0;
        //若小数位长度大于限制位数则放弃更改
        for (int i = 0; i < length; i++) {
            if (dest.charAt(i) == '.') {
                pointIndex = i;
            }
        }

        if (pointIndex != 0 && dend > pointIndex) {
            if (length - pointIndex > digit) {
                return "";
            }
        }
        return null;
    }

    private boolean nullFilter(CharSequence source) {
        return source.toString().isEmpty();
    }
}