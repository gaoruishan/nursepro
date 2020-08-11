package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.CustomPopLayout;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 医嘱执行底部按钮
 * @author:gaoruishan
 * @date:202019-08-20/14:21
 * @email:grs0515@163.com
 */
public class CustomOrdExeBottomView extends BaseView {

    public TextView tvType;
    private LinearLayout llBottom;
    private LinearLayout llNoSelect;
    private LinearLayout llSelect;
    private TextView tvNoSelectText;
    private TextView tvSelectText;
    private SimpleCallBack<View> listener;

    public CustomOrdExeBottomView(Context context) {
        this(context, null);
    }

    public CustomOrdExeBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOrdExeBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setBottomViewClickListener(SimpleCallBack<View> listener) {
        this.listener = listener;
    }

    /**
     * 添加底部按钮
     * @param list
     */
    public void addBottomView(List<? extends ClickBean> list) {
        if (list != null && list.size() > 0) {
            llBottom.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                final ClickBean bean = list.get(i);
                TextView tv = inflate(R.layout.dhcc_ord_exe_buttom_tv, llBottom, TextView.class);
                tv.setText(bean.getText());
                final int finalI = i;
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.call(v, finalI);
                        }
                        if (bean.getListener() != null) {
                            bean.getListener().onClick(v);
                        }
                    }
                });
                if (i % 2 == 0) {
                    setBackGroundColor(tv, R.color.blue_dark);
                } else {
                    setBackGroundColor(tv, R.color.blue);
                }
                llBottom.addView(tv);
            }
        }
    }


    @Override
    protected int setContentView() {
        return R.layout.custom_ord_exe_buttom_view;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        llNoSelect = view.findViewById(R.id.ll_no_select);
        tvNoSelectText = view.findViewById(R.id.tv_no_select_text);
        llSelect = view.findViewById(R.id.ll_select);
        tvSelectText = view.findViewById(R.id.tv_select_text);
        tvType = view.findViewById(R.id.tv_type);
        llBottom = view.findViewById(R.id.ll_bottom);
    }
    public void setExeTypeVisible(int visible){
        view.findViewById(R.id.ll_type).setVisibility(visible);
    }


    public void setNoSelectVisibility(boolean noSelect) {
        llNoSelect.setVisibility(noSelect ? VISIBLE : GONE);
        llSelect.setVisibility(noSelect ? GONE : VISIBLE);
    }

    public void setNoSelectText(String s) {
        tvNoSelectText.setText(s);
    }

    public void setSelectText(String s) {
        tvSelectText.setText(s);
    }


    public void setType(String s) {
        if (!TextUtils.isEmpty(s)) {
            tvType.setVisibility(View.VISIBLE);
            tvType.setText(s);
        }
    }


    public String getExeCode() {
        Object tag = tvType.getTag();
        if (tag != null) {
            return (String) tag;
        }
        return "";
    }

    public void adBottomType(final List<SheetListBean> listBeans) {
        if (listBeans != null && listBeans.size() > 0) {
            tvType.setText(listBeans.get(0).getDesc());
            tvType.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    show(listBeans);
                }
            });
        }
    }

    private void show(List<SheetListBean> listBeans) {
        CustomPopLayout customPopLayout = new CustomPopLayout(mContext);
        customPopLayout.setPopLayoutData(listBeans, tvType.getText().toString(), new SimpleCallBack<SheetListBean>() {
            @Override
            public void call(SheetListBean result, int type) {
                if (result != null) {
                    tvType.setText(result.getDesc());
                    tvType.setTag(result.getCode());
                }
            }
        }).showBottom(llSelect);
    }
}
