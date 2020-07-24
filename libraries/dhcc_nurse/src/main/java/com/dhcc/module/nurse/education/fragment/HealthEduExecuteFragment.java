package com.dhcc.module.nurse.education.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.BundleData;
import com.dhcc.module.nurse.education.HealthEduApiManager;
import com.dhcc.module.nurse.education.adapter.HealthEduItemAdapter;
import com.dhcc.module.nurse.education.bean.EduItemListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.params.SaveEduParams;
import com.dhcc.res.infusion.CustomSelectView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author:gaoruishan
 * @date:202020-07-17/17:41
 * @email:grs0515@163.com
 */
public class HealthEduExecuteFragment extends BaseNurseFragment {

    private CustomSelectView customSelectDate;
    private TextView tvName;
    private TextView tvOk;
    private String desc;
    private String eduDateTime;
    private HealthEduItemAdapter eduItemAdapter;
    private String taskIds;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("宣教执行");
        tvName.setText(desc + "");

        setSelectDateTime(customSelectDate,eduDateTime);

        HealthEduBean json = DataCache.getJson(HealthEduBean.class, HealthEduBean.class.getName());
        if (json != null) {
            //重组数据
            List<EduItemListBean> eduItemList = json.getEduItemList();
            for (EduItemListBean bean : eduItemList) {
                bean.setSheetList(bean.getOptionListBeans());
            }
            eduItemAdapter.setNewData(eduItemList);
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
        taskIds = new BundleData(bundle).getTaskIds();
        eduDateTime = new BundleData(bundle).getDateTime();

        tvName = f(R.id.tv_name, TextView.class);
        tvOk = f(R.id.tv_ok, TextView.class);
        tvOk.setOnClickListener(this);
        customSelectDate = f(R.id.custom_select_date, CustomSelectView.class);

        RecyclerView rvItem = RecyclerViewHelper.get(mContext, R.id.rv_item);
        eduItemAdapter = AdapterFactory.getHealthEduItemAdapter();
        rvItem.setAdapter(eduItemAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //保存
        if (v.getId() == R.id.tv_ok) {
            SaveEduParams.getInstance().EducationDate = customSelectDate.getSelect().substring(0, 10);
            SaveEduParams.getInstance().EducationTime = customSelectDate.getSelect().substring(11, 16);
            SaveEduParams.getInstance().eduTaskIds = taskIds;

            //[{"id":"1","option":"手足/其他/本人/配偶\f444"},{"id":"4","option":"盲文/方言/身体语言"},{"id":"5","option":"其他\f6666"},{"id":"2","option":"不了解"},{"id":"6","option":"高中"}]
            List<SaveEduParams.EduTaskIdBean> eduTaskIds = new ArrayList<>();
            for (EduItemListBean datum : eduItemAdapter.getData()) {
                eduTaskIds.add(datum.getEduTaskBean());
                Log.e(TAG,"(HealthEduExecuteFragment.java:74) "+datum);
            }
            SaveEduParams.getInstance().EduItemList = new Gson().toJson(eduTaskIds);
            saveEdu();
        }
    }

    /**
     * HealthEduContentFragment 和 HealthEduExecuteFragment 都有保存参数
     */
    private void saveEdu() {
        HealthEduApiManager.saveEdu(SaveEduParams.getInstance(), new CommonCallBack<CommResult>() {
            @Override
            public void onFail(String code, String msg) {
                onFailThings();
            }

            @Override
            public void onSuccess(CommResult bean, String type) {
                onSuccessThings(bean);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_execute;
    }
}
