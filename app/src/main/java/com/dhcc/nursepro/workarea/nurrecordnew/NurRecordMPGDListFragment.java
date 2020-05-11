package com.dhcc.nursepro.workarea.nurrecordnew;


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
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.adapter.MPGDListDetailAdapter;
import com.dhcc.nursepro.workarea.nurrecordnew.api.NurRecordOldApiManager;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecModelListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.CareRecCommListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NurRecordMPGDListFragment
 * 护理病历-评估单列表
 *
 * @author Devlix126
 * created at 2019/7/24 10:21
 */
public class NurRecordMPGDListFragment extends BaseFragment {
    private LinearLayout llMPgdlistTitle;
    private RecyclerView recyMPgdlistDetail;

    private MPGDListDetailAdapter mpgdListDetailAdapter;

    private String episodeID = "";
    private String bedNo = "";
    private String patName = "";

    private String emrCode = "";
    private String guid = "";
    private String modelNum = "";

    private RecModelListBean.MenuListBean.ModelListBean modelListBean;
    private List<Map> listMap = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            episodeID = bundle.getString("EpisodeID", "");
            bedNo = bundle.getString("BedNo", "");
            patName = bundle.getString("PatName", "");
            emrCode = bundle.getString("EMRCode", "");
            guid = bundle.getString("GUID", "");
            modelNum = bundle.getString("ModelNum", "");
            modelListBean = (RecModelListBean.MenuListBean.ModelListBean) bundle.getSerializable("ModelListBean");
        }

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        setToolbarCenterTitle(bedNo + "    " + patName, 0xffffffff, 17);


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
                bundle.putString("BedNo", bedNo);
                bundle.putString("PatName", patName);
                bundle.putString("EMRCode", emrCode);
                bundle.putString("GUID", guid);
                bundle.putString("RecID", "");
                startFragment(NurRecordNewFragment.class, bundle);
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initAdapter();
    }

    private void initView(View view) {

        llMPgdlistTitle = view.findViewById(R.id.ll_mpgdlist_title);
        llMPgdlistTitle.setMinimumWidth(ScreenUtils.getAppScreenWidth());
        recyMPgdlistDetail = view.findViewById(R.id.recy_mpgdlist_detail);
        recyMPgdlistDetail.setHasFixedSize(true);
        recyMPgdlistDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        mpgdListDetailAdapter = new MPGDListDetailAdapter(new ArrayList<>(), getActivity());
        mpgdListDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Map itm = (Map) adapter.getItem(position);

                Bundle bundle = new Bundle();
                String RecId = itm.get("par").toString();
                bundle.putString("EpisodeID", episodeID);
                bundle.putString("BedNo", bedNo);
                bundle.putString("PatName", patName);
                bundle.putString("EMRCode", emrCode);
                bundle.putString("GUID", guid);
                bundle.putString("RecID", RecId);

                startFragment(NurRecordNewFragment.class, bundle);
            }
        });
        recyMPgdlistDetail.setAdapter(mpgdListDetailAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nur_record_mpgdlist, container, false);
    }

    private void initData() {
        //&parr=640^2019-07-16^0:00^2019-7-22^23:59^DHCNURSSKHL^false
        if (modelListBean != null) {
            String parr = episodeID + "^" + TimeUtils.date2String(TimeUtils.getDate(System.currentTimeMillis(), -6, TimeConstants.DAY), "yyyy-MM-dd") + "^00:00^" + TimeUtils.date2String(TimeUtils.getDate(System.currentTimeMillis(), 0, TimeConstants.DAY), "yyyy-MM-dd") + "^23:59^" + modelListBean.getModelCode() + "^false";

            //            NurRecordOldApiManager.getN(parr, modelListBean.getGetListMth(), new NurRecordOldApiManager.CareRecCommCallback() {
            NurRecordOldApiManager.getNewEmrList(parr, new NurRecordOldApiManager.CareRecCommCallback() {
                @Override
                public void onSuccess(CareRecCommListBean careRecCommListBean) {
                    initTitle(careRecCommListBean.getTitleList());
                    mpgdListDetailAdapter.setListTitle(careRecCommListBean.getTitleList());
                    listMap = (List<Map>) careRecCommListBean.getMap().get("dataList");
                    mpgdListDetailAdapter.setNewData(listMap);
                    mpgdListDetailAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(String code, String msg) {
                    showToast("error" + code + ":" + msg);
                }
            });

        }
    }


    private void initTitle(List<CareRecCommListBean.TitleListBean> titleListBeans) {
        llMPgdlistTitle.removeAllViews();
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
            llMPgdlistTitle.addView(textView);
        }
    }


}
