package com.dhcc.nursepro.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Devlix on 2017/8/14.
 */

public class RotateTextView extends android.support.v7.widget.AppCompatTextView {


    public RotateTextView(Context context) {
        super(context, null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-30, getMeasuredWidth() / 3, getMeasuredHeight() / 3);
        super.onDraw(canvas);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }
}