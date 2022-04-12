package com.dhcc.module.nurse.education.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.bean.EduSubjectListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.education.bean.HealthEduContentBean;
import com.dhcc.module.nurse.education.SaveEduParams;
import com.dhcc.res.infusion.CustomSelectView;

import java.util.List;

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
    private List<EduSubjectListBean> eduSubjectList;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("宣教内容");
        tvName.setText(desc + "");
        HealthEduBean json = DataCache.getJson(HealthEduBean.class, HealthEduBean.class.getName());
        if (json != null) {
            eduSubjectList = json.getEduSubjectList();
        }
        getEduContents();

    }


    private void getEduContents() {
        HealthEduApiManager.getEduContents(subjectIds, new CommonCallBack<HealthEduContentBean>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings(msg);
            }

            @Override
            public void onSuccess(HealthEduContentBean bean, String type) {
                if (bean.getEduContents() != null) {
                    String eduContent = "";
                    String eduTitle = "";
                    for (HealthEduContentBean.EduContentsBean eduBean : bean.getEduContents()) {
                        //主题
                        if ("0".equals(eduBean.getPid())) {
                            continue;
                        }
                        eduContent += eduBean.getContent() + "\n";
                        eduTitle += eduBean.getSubjectTitle(eduSubjectList) + "、";
                    }
                    if(!TextUtils.isEmpty(eduTitle)){
                        eduTitle = eduTitle.substring(0, eduTitle.length() - 1);
                    }
                    if(!TextUtils.isEmpty(eduContent)){
                        eduContent = eduContent.substring(0, eduContent.length() - 2);
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
        eduRecordId = new BundleData(bundle).getEduRecordId();


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
                    .setEduRecordId(eduRecordId)
                    .setDateTime(customSelectDate.getSelect());
            startFragment(HealthEduExecuteFragment.class, bundle.build());
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_content;
    }
}
