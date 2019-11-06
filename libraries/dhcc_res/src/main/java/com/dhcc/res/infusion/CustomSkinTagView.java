package com.dhcc.res.infusion;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;
import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLLinearLayout;

/**
 * 圆环
 * @author:gaoruishan
 * @date:202019-11-05/14:19
 * @email:grs0515@163.com
 */
public class CustomSkinTagView extends BaseView {

    private TextView tvName;
    private BLLinearLayout blCircleOut, blCircleIn;

    public CustomSkinTagView(Context context) {
        this(context, null);
    }

    public CustomSkinTagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSkinTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tvName = findViewById(R.id.tv_text);
        //圆环
        blCircleOut = findViewById(R.id.bl_circle_out);
        blCircleIn = findViewById(R.id.bl_circle_in);
    }

    public CustomSkinTagView setText(String s) {
        if (!TextUtils.isEmpty(s)) {
            tvName.setText(s);
        }
        return this;
    }
    public CustomSkinTagView setTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            tvName.setTextColor(android.graphics.Color.parseColor(color));
        }
        return this;
    }

    public CustomSkinTagView setCircleColor(String color) {
//        app:bl_stroke_width="@dimen/dp_2"
//        app:bl_corners_radius="@dimen/dp_40"
//        app:bl_stroke_color="@color/blue"
        Drawable drawable = new DrawableCreator.Builder()
                .setStrokeColor(Color.parseColor(color))
                .setStrokeWidth(getDimen(R.dimen.dp_2))
                .setCornersRadius(getDimen(R.dimen.dp_40))
                .build();
//        app:bl_stroke_dashGap="@dimen/dp_5"
//        app:bl_stroke_dashWidth="@dimen/dp_5"
        Drawable drawable2 = new DrawableCreator.Builder()
                .setStrokeColor(Color.parseColor(color))
                .setStrokeWidth(getDimen(R.dimen.dp_2))
                .setCornersRadius(getDimen(R.dimen.dp_40))
                .setStrokeDashGap(getDimen(R.dimen.dp_5))
                .setStrokeDashWidth(getDimen(R.dimen.dp_5))
                .build();
        blCircleOut.setBackground(drawable);
        blCircleIn.setBackground(drawable2);
//文字点击变色
//        tvTest1.setClickable(true);//由于Android源码的原因，必须调用，否则不生效
//        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
//        tvTest1.setTextColor(colors);
        return this;
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_skin_tag_view;
    }
}
