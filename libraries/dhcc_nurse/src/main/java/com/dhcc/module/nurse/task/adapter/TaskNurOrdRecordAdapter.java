package com.dhcc.module.nurse.task.adapter;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.nex3z.flowlayout.FlowLayout;
import java.util.List;

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
                .addOnClickListener(R.id.et_count)
                .addOnClickListener(R.id.tv_click1);
        EditText etCount = helper.getView(R.id.et_count);
        etCount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etCount.callOnClick();
                return false;
            }
        });
        etCount.setRawInputType(Configuration.KEYBOARD_QWERTY);
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
        RadioGroup rbGroup = helper.getView(R.id.ll_rb);
        rbGroup.removeAllViews();
        if (item.getData().getSubItemList().size()>0) {
            if (item.getWidgetType().equals("2")){
                helper.setGone(R.id.tv_click,false);
                for (int i = 0; i < item.getData().getSubItemList().size(); i++) {
                    CheckBox cb = new CheckBox(mContext);
                    cb.setText(item.getData().getSubItemList().get(i).getSubItemName());
                    cb.setButtonDrawable(R.drawable.checkbox_nur);
                    cb.setPadding(5,5,10,5);
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
                helper.setGone(R.id.tv_click,false);
                for (int i = 0; i < item.getData().getSubItemList().size(); i++) {
                    RadioButton rb = new RadioButton(mContext);
                    rb.setText(item.getData().getSubItemList().get(i).getSubItemName());
//                    rb.setButtonDrawable(R.drawable.checkbox_nur);
                    rb.setPadding(5,5,10,5);
                    if (item.getData().getSubItemList().get(i).getSubSelec().equals("0")){
                        rb.setChecked(false);
                    }else {
                        rb.setChecked(true);
                    }
                    int finalI = i;
                    rb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < item.getData().getSubItemList().size(); j++) {
                                item.getData().getSubItemList().get(j).setSubSelec("0");
                            }
                            item.getData().getSubItemList().get(finalI).setSubSelec("1");
                            helper.getView(R.id.tv_click1).callOnClick();
                        }
                    });
                    rbGroup.addView(rb);
                }
            }

        }else {
            helper.setGone(R.id.tv_click,item.getData().getNoteList().size()>0?true:false);
        }
    }

}
