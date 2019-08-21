package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * 医嘱执行底部按钮
 * @author:gaoruishan
 * @date:202019-08-20/14:21
 * @email:grs0515@163.com
 */
public class CustomOrdExeBottomView extends BaseView implements View.OnClickListener {

    private LinearLayout llOrderexecuteNoselectbottom;
    private LinearLayout llOrderexecuteSelectbottom;
    private LinearLayout llorderexecuteselectnum;
    private TextView tvBottomNoselecttext;
    private TextView tvBottomSelecttext;
    public TextView tvBottomHandletype;
    private View viewBottomHandleline;
    private ImageView imgBottomHandlesure;
    public TextView tvBottomUndo;
    public TextView tvBottomTodo;


    public CustomOrdExeBottomView(Context context) {
        super(context);
    }

    public CustomOrdExeBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomOrdExeBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        llOrderexecuteNoselectbottom = view.findViewById(R.id.ll_orderexecute_noselectbottom);
        llorderexecuteselectnum = view.findViewById(R.id.ll_orderexecute_selectnum);
        tvBottomNoselecttext = view.findViewById(R.id.tv_bottom_noselecttext);
        llOrderexecuteSelectbottom = view.findViewById(R.id.ll_orderexecute_selectbottom);
        tvBottomSelecttext = view.findViewById(R.id.tv_bottom_selecttext);
        tvBottomHandletype = view.findViewById(R.id.tv_bottom_handletype);
//        tvBottomHandletype.setOnClickListener(this);
        viewBottomHandleline = view.findViewById(R.id.view_bottom_handleline);
        imgBottomHandlesure = view.findViewById(R.id.img_bottom_handlesure);
        tvBottomUndo = view.findViewById(R.id.tv_bottom_undo);
        tvBottomTodo = view.findViewById(R.id.tv_bottom_todo);
//        tvBottomUndo.setOnClickListener(this);
//        tvBottomTodo.setOnClickListener(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_ord_exe_buttom_view;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public boolean isRefreshButtom() {
        return llOrderexecuteSelectbottom.getVisibility() == View.VISIBLE || llOrderexecuteNoselectbottom.getVisibility() == View.VISIBLE;
    }

    public void isVisibilitySelectBottomLayout(int noselectbottom, int selectbottom) {
        llOrderexecuteNoselectbottom.setVisibility(noselectbottom);
        llOrderexecuteSelectbottom.setVisibility(selectbottom);
    }

    public void isVisibilitySelectNum(boolean gone) {
        llorderexecuteselectnum.setVisibility(gone ? View.GONE : VISIBLE);
    }

    public void setTvBottomNoSelectText(String s) {
        tvBottomNoselecttext.setText(s);
    }

    public void setTvBottomSelectText(String s) {
        tvBottomSelecttext.setText(s);
    }

    @Override
    public void onClick(View v) {

    }

    public void setTvBottomHandleType(String s) {
        if (!TextUtils.isEmpty(s)) {
            tvBottomHandletype.setVisibility(View.VISIBLE);
            tvBottomHandletype.setText(s);
        }
    }

    public void setVisibilityTvBottomHandleType(int visibale) {
        tvBottomHandletype.setVisibility(visibale);
    }

    public void setVisibilityBottomHandleLine(int visible) {
        viewBottomHandleline.setVisibility(visible);
    }

    public void setVisibilityImgBottomHandleSure(int visible) {
        imgBottomHandlesure.setVisibility(visible);
    }

    public void setBottomTodo(boolean undoVisibility, String undoTxt, boolean todoVisibility, String todoTxt) {
        tvBottomUndo.setVisibility(undoVisibility ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(undoTxt)) {
            tvBottomUndo.setText(undoTxt);
        }
        tvBottomTodo.setVisibility(todoVisibility ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(todoTxt)) {
            tvBottomTodo.setText(todoTxt);
        }
    }
}
