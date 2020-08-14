package com.dhcc.module.nurse.task.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.nex3z.flowlayout.FlowLayout;
import com.noober.background.drawable.DrawableCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/14
 * Time:9:02
 */
public class TaskNurOrdRecordAdapter extends BaseQuickAdapter<NurOrdRecordTaskBean.TaskSetListBean, BaseViewHolder> {

    public TaskNurOrdRecordAdapter(int layoutResId, @Nullable List<NurOrdRecordTaskBean.TaskSetListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurOrdRecordTaskBean.TaskSetListBean item) {
//        setTextStatus(helper, orderInfoBean);
        helper.setText(R.id.tv_name, item.getItemName()+":")
                .setText(R.id.et_count, ""+item.getItemValue())
                .addOnClickListener(R.id.tv_click)
                .addOnClickListener(R.id.tv_click1);
        EditText etCount = helper.getView(R.id.et_count);
        if (item.getWidgetType().equals("4")){
            etCount.setVisibility(View.VISIBLE);
        }else {
            etCount.setVisibility(View.GONE);
        }
        etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    item.setItemValue(etCount.getText().toString());
                    helper.getView(R.id.tv_click1).callOnClick();
            }
        });

        if (item.getData().getNoteList().size()>0){
            if (item.getNoteSelec()==null){
                helper.setText(R.id.tv_click, ""+item.getData().getNoteList().get(0));
                item.setNoteSelec(item.getData().getNoteList().get(0).toString());
            }else {
                helper.setText(R.id.tv_click, ""+item.getNoteSelec());
            }
        }

        FlowLayout llCk = helper.getView(R.id.ll_ck);
        llCk.removeAllViews();
        if (item.getData().getSubItemList().size()>0) {
            helper.setGone(R.id.tv_click,false);
//            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params1.setMargins(0, 0, 0, 0);
//            layout.setLayoutParams(params1);
            //                config.setSendValue(config.getItemCode() + "|" + config.getItemdeValue());
            FlowLayout flowCheckGroup = new FlowLayout(mContext);
            for (int i = 0; i < item.getData().getSubItemList().size(); i++) {
                CheckBox cb = new CheckBox(mContext);
                cb.setText(item.getData().getSubItemList().get(i).getSubItemName());
                if (item.getData().getSubItemList().get(i).getSubSelec().equals("0")){
                    cb.setChecked(false);
                }else {
                    cb.setChecked(true);
                }
                int finalI = i;
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb.isChecked()) {
                            item.getData().getSubItemList().get(finalI).setSubSelec("1");
                        } else {
                            item.getData().getSubItemList().get(finalI).setSubSelec("0");
                        }
                        helper.getView(R.id.tv_click1).callOnClick();
                    }
                });
                llCk.addView(cb);
            }
        }else {
            helper.setGone(R.id.tv_click,item.getData().getNoteList().size()>0?true:false);
        }
    }

}
