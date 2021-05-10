package com.dhcc.module.infusion.workarea;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.CommRes;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.view.WebActivity;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.api.WorkAreaApiManager;
import com.dhcc.module.infusion.workarea.comm.bean.MainConfigBean;
import com.dhcc.res.nurse.bean.ConfigBean;
import com.dhcc.res.nurse.bean.ConfigWorkArea;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * @author:gaoruishan
 * @date:202019-05-22/15:16
 * @email:grs0515@163.com
 */
public class WorkAreaFragment extends BaseFragment {
    private RecyclerView recConfig;
    private List<ConfigWorkArea.ListBean> ItemNameList = new ArrayList<>();
    private WorkAreaAdapter patEventsAdapter;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea_infusion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //调用注册广播(虽然继承了baseFragment,但父类不是UniversalActivity)
        onPreActivityCreate(null,null);
        initView(view);
        getMainConfig();
    }

    private void getMainConfig() {
        WorkAreaApiManager.getMainConfig(new CommonCallBack<MainConfigBean>() {
            @Override
            public void onFail(String code, String msg) {
                CommRes.readJson("config_work_area.json", MainConfigBean.class, new CommRes.CallRes<MainConfigBean>() {
                    @Override
                    public void call(MainConfigBean bean, String s) {
                        setData(bean);
                    }
                });
            }

            @Override
            public void onSuccess(MainConfigBean bean, String type) {
                setData(bean);
            }
        });
    }

    private void setData(MainConfigBean bean) {
        DataCache.saveJson(bean, SharedPreference.DATA_MAIN_CONFIG);
        patEventsAdapter.setNewData(bean.getMainList());
        CommRes.loadDrawableOrNetwork(getActivity(),f(R.id.iv_home_pic, ImageView.class),bean.getHomePic());
    }

    private void initView(View view) {
        recConfig = view.findViewById(R.id.recy_workarea);
        recConfig.setHasFixedSize(true);
        recConfig.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        patEventsAdapter = new WorkAreaAdapter(null);
        recConfig.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConfigBean item = patEventsAdapter.getItem(position);
                try {
                    //fragment 不为空, 且必须继承BaseFragment
                    if (item != null && !TextUtils.isEmpty(item.getFragment())) {
                        //兼容web
                        if (item.getFragment().contains(".html")) {
                            WebActivity.start(mActivity, BaseWebServiceUtils.getServiceUrl(item.getFragment()));
                        }else {
                            Class<? extends BaseFragment> aClass = (Class<? extends BaseFragment>) Class.forName(item.getFragment());
                            startFragment(aClass);
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG,e.toString());
                    ToastUtils.showShort("此模块未找到");
                }
            }
        });
    }
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        String scanInfo = doScanInfo(intent);
        //扫码Action
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())
                && !TextUtils.isEmpty(scanInfo)) {
            //医嘱id
            if (scanInfo.contains("-")||scanInfo.contains("||")) {
                scanInfo = scanInfo.replaceAll("-", "\\|\\|");
                Bundle bundle = new Bundle();
                bundle.putString("id", scanInfo);
                startFragment(MedicalDetailFragment.class, bundle);
            }
        }
    }
    public class WorkAreaAdapter extends BaseQuickAdapter<ConfigBean, BaseViewHolder> {
        WorkAreaAdapter(@Nullable List<ConfigBean> data) {
            super(R.layout.item_workarea, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ConfigBean item) {
            Log.e(TAG,"(WorkAreaAdapter.java:136) "+item.getBgColor());
            if(!TextUtils.isEmpty(item.getBgColor())){
                helper.setBackgroundColor(R.id.ll_item, Color.parseColor(item.getBgColor()));
            }else {
                helper.setBackgroundColor(R.id.ll_item, ContextCompat.getColor(mContext,R.color.white));
            }
            TextView tvItem = helper.getView(R.id.tv_workarea);
            ImageView imageView = helper.getView(R.id.icon_workarea);
            tvItem.setText(item.getName());
            CommRes.loadDrawableOrNetwork(mContext,imageView, item.getImage());
        }
    }
}
