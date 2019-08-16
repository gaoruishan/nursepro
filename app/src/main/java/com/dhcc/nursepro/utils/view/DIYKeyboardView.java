package com.dhcc.nursepro.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.dhcc.nursepro.R;

import java.util.List;

/**
 *
 * <p>
 * author Q
 * Date: 2019/5/22
 * Time:17:36
 */

public class DIYKeyboardView extends KeyboardView {


    public DIYKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DIYKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        List<Keyboard.Key> keys = keyboard.getKeys();
        if (keys != null && keys.size() > 0) {
            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);
            Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
            paint.setTypeface(font);
            paint.setAntiAlias(true);
            for (Keyboard.Key key : keys) {
                if (key.codes[0] == -4) {
                    Drawable dr = getContext().getResources().getDrawable(R.drawable.bg_rectangle_seagreen);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                }else if (key.codes[0] == -5){
                    if (key.pressed){
                        Drawable dr = getContext().getResources().getDrawable(R.drawable.bg_rectangle_deeporange);
                        dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                        dr.draw(canvas);
                    }else {
                        Drawable dr = getContext().getResources().getDrawable(R.drawable.bg_rectangle_orange);
                        dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                        dr.draw(canvas);
                    }

                }else {
                    if (key.pressed){
                        Drawable dr = getContext().getResources().getDrawable(R.drawable.bg_rectangle_deepblue);
                        dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                        dr.draw(canvas);
                    }else {
                        Drawable dr = getContext().getResources().getDrawable(R.drawable.bg_rectangle_blue);
                        dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                        dr.draw(canvas);
                    }
                }
                if (key.label != null) {
                    if (key.codes[0] == -4 ||
                            key.codes[0] == -5) {
                        paint.setTextSize(28 * 2);
                    } else {
                        paint.setTextSize(29 * 2);
                    }
                    if (key.codes[0] == -4) {
                        paint.setColor(getContext().getResources().getColor(android.R.color.white));
                    } else {
                        paint.setColor(getContext().getResources().getColor(R.color.white));
                    }
                    Rect rect = new Rect(key.x, key.y, key.x + key.width, key.y + key.height);
                    Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                    int baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                    // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
                    paint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(key.label.toString(), rect.centerX(), baseline, paint);
                }
            }
        }
    }
}