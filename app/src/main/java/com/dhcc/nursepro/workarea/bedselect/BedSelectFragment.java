package com.dhcc.nursepro.workarea.bedselect;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedselect.adapter.BedGroupListAdapter;
import com.dhcc.nursepro.workarea.bedselect.api.BedListApiManager;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedselect.BedSelectFragment
 * 床位选择
 *
 * @author DevLix126
 * created at 2018/8/28 10:02
 */
public class BedSelectFragment extends BaseFragment {
    private LinearLayout llBedselectAllselect;
    private RecyclerView recyBedselect;

    private List<BedSelectListBean.BedListBean> bedList = new ArrayList<>();
    private BedGroupListAdapter bedGroupListAdapter;
    private String bedsSelected = "";
    private String bedsSelectedSave = "";

    //是否全选状态，暂不关联各组变更状态
    private boolean selectAll;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bed_select, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_ordersearchbedselect));
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("bedselectinfoStr", getBedSelectInfoStr());
                SPUtils.getInstance().put(SharedPreference.ORDERSEARCHE_BEDSELECTED,bedsSelectedSave);
                finish(bundle);
            }
        });
        setToolbarRightCustomView(viewright);

        bedsSelected = SPUtils.getInstance().getString(SharedPreference.ORDERSEARCHE_BEDSELECTED)+"";
        initView(view);
        initAdapter();
        initData();

    }

    private void initView(View view) {

        llBedselectAllselect = view.findViewById(R.id.ll_bedselect_allselect);
        llBedselectAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bedGroupListAdapter.setIfGroupClick(true);
                if (selectAll) {
                    for (int i = 0; i < bedList.size(); i++) {
                        bedList.get(i).setSelect("0");
                    }
                } else {
                    for (int i = 0; i < bedList.size(); i++) {
                        bedList.get(i).setSelect("1");
                    }
                }
                selectAll = !selectAll;
                llBedselectAllselect.setSelected(selectAll);
                bedGroupListAdapter.notifyDataSetChanged();
            }
        });

        recyBedselect = view.findViewById(R.id.recy_bedselect);

        //提高展示效率
        recyBedselect.setHasFixedSize(true);
        //设置的布局管理
        recyBedselect.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyBedselect.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        bedGroupListAdapter = new BedGroupListAdapter(new ArrayList<BedSelectListBean.BedListBean>());
        bedGroupListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BedSelectListBean.BedListBean bedListBean = (BedSelectListBean.BedListBean) adapter.getItem(position);

                if (view.getId() == R.id.ll_bedselect_group) {
                    bedGroupListAdapter.setIfGroupClick(true);
                    if ("0".equals(bedListBean.getSelect())) {
                        bedListBean.setSelect("1");
                    } else {
                        bedListBean.setSelect("0");
                    }
                    bedGroupListAdapter.notifyItemChanged(position);

                    //                    if (view.isSelected()) {
                    //                        view.setSelected(false);
                    //                        for (int i = 0; i < groupList.size(); i++) {
                    //                            groupList.get(i).setSelect("0");
                    //                        }
                    //                    } else {
                    //                        view.setSelected(true);
                    //                        for (int i = 0; i < groupList.size(); i++) {
                    //                            groupList.get(i).setSelect("1");
                    //                        }
                    //                    }
                }
            }
        });
        recyBedselect.setAdapter(bedGroupListAdapter);

    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        BedListApiManager.getBedList(new BedListApiManager.GetBedListCallback() {
            @Override
            public void onSuccess(BedSelectListBean bedSelectListBean) {
                bedList = bedSelectListBean.getBedList();
                for (int i = 0; i < bedList.size(); i++) {
                    bedList.get(i).setSelect("0");
                }
                hideLoadingTip();
                bedGroupListAdapter.setNewData(bedList);
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private String getBedSelectInfoStr() {
        StringBuffer bedselectinfoStr = new StringBuffer();
        for (int i = 0; i < bedList.size(); i++) {
            List<BedSelectListBean.BedListBean.GroupListBean> groupListBeanListLocal = bedList.get(i).getGroupList();

            for (int j = 0; j < groupListBeanListLocal.size(); j++) {
                BedSelectListBean.BedListBean.GroupListBean groupListBean = groupListBeanListLocal.get(j);
                if (groupListBean.getSelect().equals("1")) {

                    bedsSelectedSave = bedsSelectedSave+";"+groupListBean.getBedId();
                    if ("".equals(bedselectinfoStr.toString())) {
                        bedselectinfoStr.append("{\"bedList\":{\"" + groupListBean.getBedId() + "\":\"1\"");
                    } else {
                        bedselectinfoStr.append(",\"" + groupListBean.getBedId() + "\":\"1\"");
                    }

                }
            }


        }
        if (!"".equals(bedselectinfoStr.toString()) && !bedselectinfoStr.toString().endsWith("}}")) {
            bedselectinfoStr.append("}}");
        }

        return bedselectinfoStr.toString();
    }


}
