package com.dhcc.nursepro.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * com.dhcc.nursepro.view
 * <p>
 * author Q
 * Date: 2020/6/1
 * Time:11:45
 */
public class CareLevelTextView extends android.support.v7.widget.AppCompatTextView {
    public CareLevelTextView(Context context) {
        super(context,null);
    }
    @SuppressLint("ResourceAsColor")
    public CareLevelTextView(Context contex, AttributeSet attrs) {
        super(contex, attrs);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setTextColor(R.color.bedmap_text_color);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text.length()>0){
            super.setText(text.subSequence(0,1), type);
        }else {
            super.setText(text, type);
        }
        setPadding(20,10,20,10);
        GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.bg_carelevel1);
        String careLevelDesc = text.toString();
        if ("".equals(careLevelDesc)) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
            setTextColor(Color.WHITE);
            if (careLevelDesc.contains("特级")) {
//                setTextColor(Color.parseColor("#FF8C00"));
                drawable.setColor(getResources().getColor(R.color.dhcc_red));
                setBackground(drawable);
            } else if(careLevelDesc.contains("一级")){
                drawable.setColor(getResources().getColor(R.color.dhcc_lightpink));
//                setTextColor(Color.parseColor("#FF0000"));
                setBackground(getResources().getDrawable(R.drawable.bg_carelevel1));
            } else if (careLevelDesc.contains("二级")) {
//                setTextColor(Color.parseColor("#0000FF"));
                drawable.setColor(getResources().getColor(R.color.blue));
                setBackground(getResources().getDrawable(R.drawable.bg_carelevel1));
            } else {
//                setTextColor(Color.parseColor("#00BD4C"));
                drawable.setColor(getResources().getColor(R.color.bg_green));
                setBackground(getResources().getDrawable(R.drawable.bg_carelevel1));
            }
        }
    }
}
