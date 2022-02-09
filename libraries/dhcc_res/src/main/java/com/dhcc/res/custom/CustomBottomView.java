package com.dhcc.res.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.dhcc.res.custom.bean.BottomViewBean;
import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202020/12/9/16:46
 * @email:grs0515@163.com
 */
public class CustomBottomView extends BaseView {
    private TextView tv_bottom1;
    private TextView tv_bottom2;

    public CustomBottomView(Context context) {
        this(context,null);
    }

    public CustomBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_bottom_view;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
         tv_bottom1 = findViewById(R.id.tv_bottom1);
         tv_bottom2 = findViewById(R.id.tv_bottom2);
    }

    public void setDataBean(BottomViewBean bean) {
        setVisibility(VISIBLE);
        if ("1".equals(bean.getType())) {
            for (int i = 0; i < bean.getList().size(); i++) {
                BottomViewBean.ListBean listBean = bean.getList().get(i);
                if (i == 0) {
                    setText(tv_bottom1, listBean.getTitle());
                }
                if (i == 1) {
                    setText(tv_bottom2, listBean.getTitle());
                }
            }
        }
    }
}
