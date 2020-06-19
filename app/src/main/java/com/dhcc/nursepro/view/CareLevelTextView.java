package com.dhcc.nursepro.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
        super.setText(text, type);
        String careLevelDesc = text.toString();
        if ("".equals(careLevelDesc)) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
            if (careLevelDesc.contains("特级")) {
                setTextColor(Color.parseColor("#FF8C00"));
            } else if(careLevelDesc.contains("一级")){
                setTextColor(Color.parseColor("#FF0000"));
            } else if (careLevelDesc.contains("二级")) {
                setTextColor(Color.parseColor("#0000FF"));
            } else {
                setTextColor(Color.parseColor("#00BD4C"));
            }
        }
    }
}
