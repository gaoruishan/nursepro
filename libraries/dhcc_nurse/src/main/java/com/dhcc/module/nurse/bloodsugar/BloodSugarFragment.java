package com.dhcc.module.nurse.bloodsugar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.adapter.BloodSugarPatAdapter;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 血糖采集
 * @author:gaoruishan
 * @date:202020-08-17/10:47
 * @email:grs0515@163.com
 */
public class BloodSugarFragment extends BaseNurseFragment {

    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private LinearLayout llTopFilter;
    private BloodSugarPatsBean bloodSugarPatsBean;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private BloodSugarPatAdapter bloodSugarPatAdapter;
    private String topFilterCode="";
    private List<TextView> textViewList = new ArrayList<>();

    @Override
    protected void initViews() {
        super.initViews();
        llTopFilter = f(R.id.ll_top_filter,LinearLayout.class);
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(false);
        customDate.showOnlyOne();
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10), YYYY_MM_DD));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarPats();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getBloodSugarPats();
            }
        });

        bloodSugarPatAdapter = AdapterFactory.getBloodSugarPatAdapter();
        RecyclerViewHelper.get(mContext, R.id.rv_list_ord).setAdapter(bloodSugarPatAdapter);
        bloodSugarPatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) { Bundle bundle = new Bundle();
                bundle.putString("episodeId",bloodSugarPatAdapter.getItem(position).getEpisodeId());
                bundle.putString("patInfo",bloodSugarPatAdapter.getItem(position).getBedCode()+" "+bloodSugarPatAdapter.getItem(position).getName());

                if (view.getId()==R.id.tv_sugar_record){
                    startFragment(BloodSugarRecordFragment.class,bundle);
                }
                if (view.getId()==R.id.tv_sugar_list){
                    startFragment(BloodSugarNoteListFragment.class,bundle);
                }
                if (view.getId()==R.id.tv_sugar_preview){
                    startFragment(BloodSugarValueMapFragment.class,bundle);
                }
            }
        });
        bloodSugarPatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("血糖采集");
        getBloodSugarPats();
    }


    private void getBloodSugarPats(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BloodSugarApiManager.getBloodSugarPatsList(customDate.getStartDateTimeText(), new CommonCallBack<BloodSugarPatsBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(BloodSugarPatsBean bean, String type) {
                hideLoadingTip();
                bloodSugarPatsBean = bean;
                mSheetListBeanList = new ArrayList<>();
                for (int i = 0; i < bean.getLeftFilter().size(); i++) {
                    mSheetListBeanList.add(new SheetListBean(bean.getLeftFilter().get(i).getCode(),bean.getLeftFilter().get(i).getDesc()));
                }
                customSheet.setDatas(mSheetListBeanList);
                bloodSugarPatAdapter.setNewData(bean.getPatInfoList());

                llTopFilter.removeAllViews();
                textViewList = new ArrayList<>();

                for (int i = 0; i < bean.getTopFilter().size(); i++) {
                    TextView tvButton = new TextView(getContext());
                    tvButton.setText(bean.getTopFilter().get(i).getDesc());
                    tvButton.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(100), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleParams.setMargins(0, 25, 0, 25);//4个参数按顺序分别是左上右下
                    titleParams.weight = 1;
                    tvButton.setLayoutParams(titleParams);
                    tvButton.setTextColor(Color.parseColor("#000000"));
                    if (topFilterCode.equals("")){
                        tvButton.setTextColor(Color.parseColor("#4A90E2"));
                        topFilterCode = bean.getTopFilter().get(0).getCode();
                    }else if (topFilterCode.equals(bean.getTopFilter().get(i).getCode())){
                        tvButton.setTextColor(Color.parseColor("#4A90E2"));
                    }
                    tvButton.setTextSize(15);
                    tvButton.setTag(R.string.key_vis_toptext,bean.getTopFilter().get(i).getCode());
                    tvButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < textViewList.size(); j++) {
                                textViewList.get(j).setTextColor(Color.parseColor("#000000"));
                            }
                            tvButton.setTextColor(Color.parseColor("#4A90E2"));
                            topFilterCode = tvButton.getTag(R.string.key_vis_toptext).toString();
                            topFilter();
                        }
                    });
                    textViewList.add(tvButton);
                    llTopFilter.addView(tvButton);
                }
                topFilter();
            }
        });
    }

    private void topFilter(){
        ArrayList<BloodSugarPatsBean.PatInfoListBean> listBeans = new ArrayList<>();
        for (int i = 0; i < bloodSugarPatsBean.getPatInfoList().size(); i++) {
            Map map = GsonUtils.fromJson(GsonUtils.toJson(bloodSugarPatsBean.getPatInfoList().get(i)), HashMap.class);
            if (map != null && map.get(topFilterCode)!=null &&"1".equals(map.get(topFilterCode))){
                listBeans.add(bloodSugarPatsBean.getPatInfoList().get(i));
            }
        }
        bloodSugarPatAdapter.setNewData(listBeans);
    }


    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        TaskViewApiManager.getScaninfo(scanInfo, new CommonCallBack<ScanResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }
            @Override
            public void onSuccess(ScanResultBean bean, String type) {

            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_blood_sugar;
    }
}
