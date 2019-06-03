package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.setting.adapter.SettingBedsGroupAdapter;
import com.dhcc.nursepro.setting.api.SettingBedsApiManeger;
import com.dhcc.nursepro.setting.bean.SettingBedListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.setting
 * <p>
 * author Q
 * Date: 2018/9/13
 * Time:14:36
 */
public class SettingBedsFragment extends BaseFragment {
    private LinearLayout llBedselectAllselect;
    private RecyclerView recyBedselect;

    private List<SettingBedListBean.BedListBean> bedList = new ArrayList<>();
    private SettingBedsGroupAdapter bedGroupListAdapter;

    //是否全选状态，暂不关联各组变更状态
    private boolean selectAll;


    //    private IntentFilter intentFilter;
    //    private DataReceiver dataReceiver = null;
    //    //扫描腕带获取regNo、wardId
    //    public class DataReceiver extends BroadcastReceiver {
    //        @Override
    //        public void onReceive(Context context, Intent intent) {
    //            if (intent.getAction().equals("SURETHIS")){
    //                int i = intent.getIntExtra("postion",-1);
    //
    //                showToast("---"+i);
    //
    //                bedGroupListAdapter.setPostion(i);
    //                bedGroupListAdapter.notifyItemChanged(i);
    //
    //            }
    //        }
    //    }
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_beds, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle("设置所管床");
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
                //                Bundle bundle = new Bundle();
                //                bundle.putString("bedselectinfoStr", getBedSelectInfoStr());
                //                showToast(getBedSelectInfoStr());
                //                finish(bundle);
                settingBeds();
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initAdapter();
        initData();

        //        //扫描广播
        //        intentFilter = new IntentFilter();
        //        intentFilter.addAction("SURETHIS");
        //        dataReceiver = new DataReceiver();
        //        getActivity().registerReceiver(dataReceiver,intentFilter);
    }

    private void initView(View view) {

        llBedselectAllselect = view.findViewById(R.id.ll_setting_all);
        llBedselectAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bedGroupListAdapter.setFirst(true);
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

        recyBedselect = view.findViewById(R.id.recy_settingbeds);

        //提高展示效率
        recyBedselect.setHasFixedSize(true);
        //设置的布局管理
        recyBedselect.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyBedselect.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        bedGroupListAdapter = new SettingBedsGroupAdapter(new ArrayList<SettingBedListBean.BedListBean>());
        bedGroupListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SettingBedListBean.BedListBean bedListBean = (SettingBedListBean.BedListBean) adapter.getItem(position);

                if (view.getId() == R.id.ll_settingbeds_group) {
                    if ("0".equals(bedListBean.getSelect())) {
                        bedListBean.setSelect("1");
                    } else {
                        bedListBean.setSelect("0");
                    }
                    bedGroupListAdapter.setFirst(true);
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
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        SettingBedsApiManeger.getBedList(properties, "getBedList", new SettingBedsApiManeger.GetBedListCallback() {
            @Override
            public void onSuccess(SettingBedListBean settingBedListBean) {
                bedList = settingBedListBean.getBedList();
                for (int i = 0; i < bedList.size(); i++) {
                    bedList.get(i).setSelect("0");
                }
                bedGroupListAdapter.setNewData(bedList);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void settingBeds() {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = getBedSelectInfoStrs();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        SettingBedsApiManeger.getSettingMsgList(properties, "setManagedBeds", new SettingBedsApiManeger.SettingBedsCallback() {
            @Override
            public void onSuccess(String msg) {
                showToast("所管床设置完成");
                finish();

            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code + "" + msg);
            }
        });


    }


    private HashMap<String, String> getBedSelectInfoStrs() {

        HashMap<String, String> mapSelect = new HashMap<>();
        String bedIdStr = "";
        String statusStr = "";

        StringBuffer bedselectinfoStr = new StringBuffer();
        for (int i = 0; i < bedList.size(); i++) {
            List<SettingBedListBean.BedListBean.GroupListBean> groupListBeanListLocal = bedList.get(i).getGroupList();

            for (int j = 0; j < groupListBeanListLocal.size(); j++) {
                SettingBedListBean.BedListBean.GroupListBean groupListBean = groupListBeanListLocal.get(j);
                if (groupListBean.getSelect().equals("1")) {
                    if (bedIdStr.equals("")) {
                        bedIdStr = groupListBean.getBedId();
                    } else {
                        bedIdStr = bedIdStr + "^" + groupListBean.getBedId();
                    }
                    if (statusStr.equals("")) {
                        statusStr = "1";
                    } else {
                        statusStr = statusStr + "^1";
                    }
                } else {
                    if (bedIdStr.equals("")) {
                        bedIdStr = groupListBean.getBedId();
                    } else {
                        bedIdStr = bedIdStr + "^" + groupListBean.getBedId();
                    }
                    if (statusStr.equals("")) {
                        statusStr = "0";
                    } else {
                        statusStr = statusStr + "^0";
                    }
                }
            }

        }
        mapSelect.put("bedIdStr", bedIdStr);
        mapSelect.put("statusStr", statusStr);
        Log.v("11111bed", bedIdStr);
        Log.v("11111stat", statusStr);
        return mapSelect;
    }


}
