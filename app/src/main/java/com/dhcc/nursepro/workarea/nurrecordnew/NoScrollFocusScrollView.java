package com.dhcc.nursepro.workarea.nurrecordnew;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NoScrollFocusScrollView extends ScrollView {
    public NoScrollFocusScrollView(Context context) {
        super(context);
    }
    public NoScrollFocusScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoScrollFocusScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}
