package com.dhcc.nursepro.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.dhcc.nursepro.R;


/**
 * 筛选TextView
 *
 * @author:gaoruishan
 * @date:2018/8/16/16:41
 * @email:grs0515@163.com
 */
public class SelectTextView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private Context mContext;
    private Paint mPaint;
    private Paint mPaintLabel;
    private Bitmap bitmap;
    private float strokeWidth = 2;
    private boolean isSelect = false;
    private float cornersRadius = 10;
    private int labelWidth = dpTpPx(38);
    private int labelHeight = dpTpPx(40);
    //文本颜色
    private int unSelectTextColor = 0xff333333;
    private int selectedTextColor = 0xffFE4C35;
    //tab选中图标
    private int selectedIcon;
    private int selectedBg = 0;
    private int unSelectedBg = 0;
    //id
    private int id;
    //类型
    private String type;
    private OnClickListener l;
    private SelectTextView stv2;
    private boolean alwaysBold;

    public SelectTextView(Context context) {
        this(context, null);
    }

    public SelectTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectTextView);
        unSelectTextColor = a.getColor(R.styleable.SelectTextView_unSelectTextColor, unSelectTextColor);
        selectedTextColor = a.getColor(R.styleable.SelectTextView_selectedTextColor, selectedTextColor);
        selectedIcon = a.getResourceId(R.styleable.SelectTextView_selectedIcon, selectedIcon);
        selectedBg = a.getResourceId(R.styleable.SelectTextView_selectedBg, selectedBg);
        unSelectedBg = a.getResourceId(R.styleable.SelectTextView_unSelectedBg, unSelectedBg);
        //居中
        setGravity(Gravity.CENTER);
//		setTextSize(dpTpPx(R.dimen.sp_13));
        setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.sp_13));

        //单行-有问题???
//		setLines(2);

        //上/下边距
        setPadding(0, dpTpPx(3), 0, dpTpPx(3));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        params.setMargins(0, dpTpPx(5), dpTpPx(10), dpTpPx(5));
        setLayoutParams(params);
        //画笔-背景
//		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setStrokeWidth(strokeWidth);
        //画笔-图标
        mPaintLabel = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLabel.setStyle(Paint.Style.STROKE);
        if (selectedIcon != 0) {
            bitmap = BitmapFactory.decodeResource(getResources(), selectedIcon);
        }
        //监听
        setOnClickListener(this);
        invalidate();
    }

    public int dpTpPx(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelect) {
            //画圆角
            if (selectedTextColor != 0) {
                setTextColor(selectedTextColor);
            }
            setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            if (selectedBg != 0) {
                setBackground(getResources().getDrawable(selectedBg));
            }
            //画指定drawable->总宽/高 减去图标宽/高
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, getWidth() - labelWidth, getHeight() - labelHeight, mPaintLabel);
            }
        } else {
            if (unSelectTextColor != 0) {
                setTextColor(unSelectTextColor);
            }
            setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            if (alwaysBold) {
                setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
            if (unSelectedBg != 0) {
                setBackground(getResources().getDrawable(unSelectedBg));
            }
        }
    }

    /**
     * 截取5个字符
     *
     * @param text
     * @return
     */
    @Nullable
    public CharSequence getSpiltText(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            text = text.toString().replace(" ", "");
            if (text.length() > 6) {
                text = text.toString().substring(0, 5) + "...";
            }
        }
        return text;
    }

    public int getSelectId() {
        return id;
    }

    public void setSelectId(int memberId) {
        this.id = memberId;
    }

    public void setUnSelectTextColor(int unSelectTextColor) {
        this.unSelectTextColor = unSelectTextColor;
        invalidate();
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        invalidate();
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public void setSelectedBg(int selectedBg) {
        this.selectedBg = selectedBg;
        invalidate();
    }

    public void setUnSelectedBg(int unSelectedBg) {
        this.unSelectedBg = unSelectedBg;
        invalidate();
    }

    /**
     * 选中
     */
    public void onSelected() {
        isSelect = true;
        invalidate();
    }

    /**
     * 未选中
     */
    public void unSelect() {
        isSelect = false;
        invalidate();
    }

    public boolean isSelect() {
        return isSelect;
    }

    /**
     * 选择监听
     *
     * @param l
     */
    public void setOnSelectorListener(@Nullable final OnClickListener l) {
        this.l = l;

    }

    @Override
    public void onClick(View v) {
        if (l != null) {
            l.onClick(v);
        } else {
            isSelect = !isSelect;
            invalidate();
        }
    }

    /**
     * 切换
     */
    public void toggle() {
        isSelect = !isSelect;
        invalidate();
    }

    public void setToggle(boolean isSelect) {
        this.isSelect = isSelect;
        invalidate();
    }

    /**
     * 一直加粗
     *
     * @param alwaysBold
     */
    public void setAlwaysBold(boolean alwaysBold) {
        this.alwaysBold = alwaysBold;
    }
}
