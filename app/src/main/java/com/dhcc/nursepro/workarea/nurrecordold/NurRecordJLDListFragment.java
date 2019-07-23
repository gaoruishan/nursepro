package com.dhcc.nursepro.workarea.nurrecordold;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.adapter.JLDListDetailAdapter;
import com.dhcc.nursepro.workarea.nurrecordold.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordold.bean.CareRecCommListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NurRecordJLDListFragment
 *
 * @author Devlix126
 * created at 2019/7/20 16:56
 */
public class NurRecordJLDListFragment extends BaseFragment {
    private LinearLayout llJldlistTitle;
    private RecyclerView recyJldlistDetail;

    private JLDListDetailAdapter jldListDetailAdapter;

    private String episodeID;
    private String bedNo;
    private String patName;
    private String emrCode;
    private String modelNum;
    private RecModelListBean.MenuListBean.ModelListBean modelListBean;
    private List<Map> listMap = new ArrayList<>();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_jldlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(getString(R.string.title_nurrecord), 0xffffffff, 17);

        if (getArguments() != null) {
            episodeID = getArguments().getString("EpisodeID");
            bedNo = getArguments().getString("BedNo");
            patName = getArguments().getString("PatName");
            emrCode = getArguments().getString("EMRCode");
            modelNum = getArguments().getString("ModelNum");
            modelListBean = (RecModelListBean.MenuListBean.ModelListBean) getArguments().getSerializable("ModelListBean");
        }

        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  新建   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("EpisodeID", episodeID);
                bundle.putString("RecID", "");
                bundle.putString("EMRCode", emrCode);
                bundle.putString("ModelNum", modelNum);
                bundle.putSerializable("ModelListBean", modelListBean);
                startFragment(NurRecordJLDFragment.class, bundle);
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View view) {

        llJldlistTitle = view.findViewById(R.id.ll_jldlist_title);
        llJldlistTitle.setMinimumWidth(ScreenUtils.getAppScreenWidth());
        recyJldlistDetail = view.findViewById(R.id.recy_jldlist_detail);
        recyJldlistDetail.setHasFixedSize(true);
        recyJldlistDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        jldListDetailAdapter = new JLDListDetailAdapter(new ArrayList<>(), getActivity());
        jldListDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Map itm = (Map) adapter.getItem(position);

                Bundle bundle = new Bundle();
                String RecId = itm.get("par").toString() + "^"
                        + itm.get("rw").toString();

                bundle.putString("CDate", itm.get("CareDate").toString());
                bundle.putString("CTime", itm.get("CareTime").toString());
                bundle.putString("RecID", RecId);
                bundle.putString("BedNo", bedNo);
                bundle.putString("PatName", patName);
                bundle.putString("EMRCode", emrCode);
                bundle.putString("ModelNum", modelNum);
                bundle.putString("EpisodeID", episodeID);
                bundle.putString("listret", "");
                bundle.putString("Pos", "0");
                bundle.putSerializable("ModelListBean", modelListBean);

                bundle.putString("nodeEmrcode", "DHCNUR"
                        + SPUtils.getInstance().getString(SharedPreference.LOCID));

                startFragment(NurRecordJLDFragment.class, bundle);
            }
        });
        recyJldlistDetail.setAdapter(jldListDetailAdapter);

    }

    private void initData() {
        //&parr=640^2019-07-16^0:00^2019-7-22^23:59^DHCNURSSKHL^false
        if (modelListBean != null) {
            String parr = episodeID + "^" + TimeUtils.date2String(TimeUtils.getDate(System.currentTimeMillis(), -6, TimeConstants.DAY), "yyyy-MM-dd") + "^00:00^" + TimeUtils.date2String(TimeUtils.getDate(System.currentTimeMillis(), 0, TimeConstants.DAY), "yyyy-MM-dd") + "^23:59^" + modelListBean.getModelCode() + "^false";
            if (modelListBean.getGetListMth().equals("getCareRecComm")) {

                NurRecordOldApiManager.getCareRecComm(parr, new NurRecordOldApiManager.CareRecCommCallback() {
                    @Override
                    public void onSuccess(CareRecCommListBean careRecCommListBean) {
                        initTitle(careRecCommListBean.getTitleList());
                        jldListDetailAdapter.setListTitle(careRecCommListBean.getTitleList());
                        listMap = (List<Map>) careRecCommListBean.getMap().get("dataList");
                        jldListDetailAdapter.setNewData(listMap);
                        jldListDetailAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        showToast("error" + code + ":" + msg);
                    }
                });
            }
        }
    }


    private void initTitle(List<CareRecCommListBean.TitleListBean> titleListBeans) {
        llJldlistTitle.removeAllViews();
        int width = ConvertUtils.dp2px(80);
        int height = ConvertUtils.dp2px(60);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        for (int i = 0; i < titleListBeans.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setText(titleListBeans.get(i).getTitleDesc());
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            textView.setTextSize(14);
            llJldlistTitle.addView(textView);
        }
    }


}
