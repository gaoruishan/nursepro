package com.dhcc.module.nurse.education.fragment;

import android.support.v7.widget.RecyclerView;
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
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.dhcc.module.nurse.params.SaveEduParams;
import com.dhcc.res.infusion.CustomSelectView;

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

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("宣教执行");
        tvName.setText(desc + "");

        setSelectDateTime(customSelectDate,eduDateTime);

        HealthEduBean json = DataCache.getJson(HealthEduBean.class, HealthEduBean.class.getName());
        if (json != null) {
            eduItemAdapter.setNewData(json.getEduItemList());
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        desc = new BundleData(bundle).getDesc();
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

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_health_education_execute;
    }
}
