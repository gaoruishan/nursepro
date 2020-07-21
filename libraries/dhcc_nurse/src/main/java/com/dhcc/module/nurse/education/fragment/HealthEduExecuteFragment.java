package com.dhcc.module.nurse.education.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EducationListBean;
import com.dhcc.res.infusion.CustomSelectView;

import java.io.Serializable;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduExecuteFragment extends BaseNurseFragment {

    private CustomSelectView customSelectDate;
    private TextView tvName;
    private EditText etTitle;
    private EditText etContent;
    private TextView tvOk;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("宣教执行");
        Serializable bean = getArguments().getSerializable(PUT_BEAN);
        String  desc = getArguments().getString(PUT_STR);
        tvName.setText(desc + "");

        if (bean instanceof  EducationListBean.DataBean) {
            EducationListBean.DataBean dataBean = ( EducationListBean.DataBean) bean;
            customSelectDate.setTitle("时间选择").setSelect(dataBean.getEduDateTime());
            long millis = TimeUtils.string2Millis(dataBean.getEduDateTime(), "yyyy-MM-dd HH:mm");
            customSelectDate.setSelectTime(HealthEduExecuteFragment.this.getFragmentManager(), millis, null);
            String eduContent = dataBean.getEduContent().replaceAll("\r","\n");
            etTitle.setText(dataBean.getEduSubject());
            etContent.setText(eduContent);
//            tvOk.setVisibility(View.GONE);
        }
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(PUT_BEAN, bean);
                bundle.putString(PUT_STR, desc);
                startFragment(HealthEduExecuteFragment.class, bundle);
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        tvName = f(R.id.tv_name, TextView.class);
        etTitle = f(R.id.et_title, EditText.class);
        etContent = f(R.id.et_content, EditText.class);
        tvOk = f(R.id.tv_ok, TextView.class);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_execute;
    }
}
