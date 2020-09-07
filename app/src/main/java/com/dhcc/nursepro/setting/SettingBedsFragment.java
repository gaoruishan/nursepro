package com.dhcc.nursepro.setting;

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
    private SettingBedsGroupAdapter bedGroupListAdapter;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_beds, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
//        setToolbarType(BaseActivity.ToolbarType.TOP);
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
                settingBeds();
            }
        });
        setToolbarRightCustomViewSingleShow(viewright);
        if (isSingleModel){
            hindMap();
        }

        initView(view);
        initAdapter();
        initData();
    }

    private void initView(View view) {

        llBedselectAllselect = view.findViewById(R.id.ll_setting_all);
        llBedselectAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llBedselectAllselect.isSelected()){
                    llBedselectAllselect.setSelected(false);
                    for (int i = 0; i < bedGroupListAdapter.getData().size(); i++) {
                        bedGroupListAdapter.getData().get(i).setSelect("0");
                        for (int j = 0; j < bedGroupListAdapter.getData().get(i).getGroupList().size(); j++) {
                            bedGroupListAdapter.getData().get(i).getGroupList().get(j).setSelect("0");
                        }
                    }
                }else {
                    llBedselectAllselect.setSelected(true);
                    for (int i = 0; i < bedGroupListAdapter.getData().size(); i++) {
                        bedGroupListAdapter.getData().get(i).setSelect("1");
                        for (int j = 0; j < bedGroupListAdapter.getData().get(i).getGroupList().size(); j++) {
                            bedGroupListAdapter.getData().get(i).getGroupList().get(j).setSelect("1");
                        }
                    }
                }
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
                if (view.getId() == R.id.view_callrefresh) {
                    llBedselectAllselect.setSelected(true);
                    for (int i = 0; i < bedGroupListAdapter.getData().size(); i++) {
                        if (bedGroupListAdapter.getData().get(i).getSelect().equals("0")){
                            llBedselectAllselect.setSelected(false);
                        }
                    }
                    bedGroupListAdapter.notifyItemChanged(position);
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
                llBedselectAllselect.setSelected(true);
                for (int i = 0; i < settingBedListBean.getBedList().size(); i++) {
                    if (settingBedListBean.getBedList().get(i).getSelect().equals("0")){
                        llBedselectAllselect.setSelected(false);
                    }
                }
                bedGroupListAdapter.setNewData(settingBedListBean.getBedList());
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
                Bundle bundle = new Bundle();
                bundle.putString("selected", "1");
                finish(bundle);
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
        for (int i = 0; i < bedGroupListAdapter.getData().size(); i++) {
            List<SettingBedListBean.BedListBean.GroupListBean> groupListBeanListLocal = bedGroupListAdapter.getData().get(i).getGroupList();

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
        return mapSelect;
    }


}
