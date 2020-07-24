package com.dhcc.module.nurse.education.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.bean.HealthEduContentBean;
import com.dhcc.module.nurse.params.SaveEduParams;
import com.dhcc.res.infusion.CustomSelectView;

/**
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduContentFragment extends BaseNurseFragment {

    private TextView tvName;
    private EditText etTitle;
    private EditText etContent;
    private TextView tvOk;
    private CustomSelectView customSelectDate;
    private String desc;
    private String subjectIds;
    private String eduDateTime;
    private String episodeId;
    private String taskIds;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("宣教内容");
        tvName.setText(desc + "");

        getEduContents();

    }


    private void getEduContents() {
        HealthEduApiManager.getEduContents(subjectIds, new CommonCallBack<HealthEduContentBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(HealthEduContentBean bean, String type) {
                if (bean.getEduContents() != null) {
                    String eduContent = "";
                    String eduTitle = "";
                    for (HealthEduContentBean.EduContentsBean eduBean : bean.getEduContents()) {
                        eduContent += eduBean.getContent() + "\n\n";
                        eduTitle += eduBean.getTitle() + "、";
                    }
                    etTitle.setText(replaceRN(eduTitle));
                    eduContent = replaceRN(eduContent);
                    etContent.setText(replaceBrackets(eduContent));
                    String date2String = TimeUtils.date2String(TimeUtils.getNowDate(), YYYY_MM_DD_HH_MM);
                    if (!TextUtils.isEmpty(eduDateTime)) {
                        date2String = eduDateTime;
                    }
                    setSelectDateTime(customSelectDate, date2String);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
        subjectIds = new BundleData(bundle).getSubjectIds();
        taskIds = new BundleData(bundle).getTaskIds();
        episodeId = new BundleData(bundle).getEpisodeId();
        eduDateTime = new BundleData(bundle).getDateTime();


        tvName = f(R.id.tv_name, TextView.class);
        etTitle = f(R.id.et_title, EditText.class);
        etContent = f(R.id.et_content, EditText.class);
        tvOk = f(R.id.tv_ok, TextView.class);
        tvOk.setOnClickListener(this);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_ok) {
            SaveEduParams.getInstance().episodeId = episodeId;
            SaveEduParams.getInstance().Subject = etTitle.getText().toString();
            SaveEduParams.getInstance().Content = etContent.getText().toString();
            SaveEduParams.getInstance().EducationDate = customSelectDate.getSelect().substring(0, 10);
            SaveEduParams.getInstance().EducationTime = customSelectDate.getSelect().substring(11, 16);
            SaveEduParams.getInstance().SubjectIds =  subjectIds;

            BundleData bundle = new BundleData()
                    .setDesc(desc)
                    .setTaskIds(taskIds)
                    .setDateTime(customSelectDate.getSelect());
            startFragment(HealthEduExecuteFragment.class, bundle.build());
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_content;
    }
}
