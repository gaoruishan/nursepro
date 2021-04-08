package com.dhcc.nursepro.workarea.patevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.adapter.PatEventsAdapter;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiManager;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatEventsFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyPatevents;
    private RelativeLayout rlscan;
    private TextView tveventuser;
    private List<String> list =new ArrayList<>();
    private PatEventsAdapter patEventsAdapter;
    private List<PatEventsBean.EventListBean> listItem =new ArrayList<>();

    private String episodeIdNow = null;

    private SPUtils spUtils = SPUtils.getInstance();
    private String patInfo = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patevents, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_patevents), 0xffffffff, 17);

        initView(view);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mReceiver != null) {
            getActivity().registerReceiver(mReceiver, mfilter);
        }
        if (episodeIdNow != null) {
            initData(episodeIdNow);
        }
    }
    private void initView(View view) {
        rlscan = view.findViewById(R.id.rl_patevents_scan);
        tveventuser = view.findViewById(R.id.tv_event_user);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String regNo = bundle.getString("regNo");
            getUserMsg(regNo);
            rlscan.setVisibility(View.GONE);
        }else if (!TextUtils.isEmpty(singleRegNo)){
            getUserMsg(singleRegNo);
            rlscan.setVisibility(View.GONE);
        }

        recyPatevents = view.findViewById(R.id.recy_patevents);
        recyPatevents.setHasFixedSize(true);
        recyPatevents.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initAdapter() {
        patEventsAdapter = new PatEventsAdapter(new ArrayList<PatEventsBean.EventListBean>());
        recyPatevents.setAdapter(patEventsAdapter);
        patEventsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.messagecontentll) {
                    Bundle bundle = new Bundle();
                    bundle.putString("recId", listItem.get(position).getRecId());
                    bundle.putString("episodeId", episodeIdNow);
                    bundle.putString("eventDate", listItem.get(position).getEventDate());
                    bundle.putString("eventTime", listItem.get(position).getEventTime());
                    bundle.putString("eventId", listItem.get(position).getEventId());
                    bundle.putString("userId", listItem.get(position).getAddUser());
                    if (isSingleModel){
                        patInfo = singlePatInfo;
                    }
                    bundle.putString("patInfo", patInfo);
                    startFragment(PatEventsDetailFragment.class, bundle);
                } else if (view.getId() == R.id.tv_patevents_eventdel) {
                    HashMap<String, String> mapDel = new HashMap<String, String>();
                    mapDel.put("recId", listItem.get(position).getRecId());
                    String methodName = NurseAPI.DelEvent;
                    PatEventsApiManager.getEventsResultMsg(mapDel, methodName, new PatEventsApiManager.GetEventsResultMsgCallBack() {
                        @Override
                        public void onSuccess(String msgs) {
                            showToast(msgs);
                        }

                        @Override
                        public void onFail(String code, String msg) {
                            showToast("error" + code + ":" + msg);
                        }
                    });
                    if (listItem.size() >= position) {
                        listItem.remove(position);
                    }
                    patEventsAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    //获取病人信息并执行事件查询
    private void getUserMsg(String regNo) {
        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo", regNo);
        mapmsg.put("wardId", wardId);
        PatEventsApiManager.getUserMsg(mapmsg, new PatEventsApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(ScanGetUserMsgBean scanGetUserMsgBean) {
                ScanGetUserMsgBean.PatInfoBean patInfoBean = scanGetUserMsgBean.getPatInfo();
                patInfo = patInfoBean.getBedCode() +" "+patInfoBean.getName();
                episodeIdNow = patInfoBean.getEpisodeID();
                String usermsg = patInfoBean.getBedCode() + "--" + patInfoBean.getName();
                tveventuser.setText(usermsg);
                initData(episodeIdNow);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initData(String episodeId) {
        showToolbarRight();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("episodeId", episodeId);
        PatEventsApiManager.getPatEventsList(map, new PatEventsApiManager.GetEventsSelectCallBack() {
            @Override
            public void onSuccess(PatEventsBean patEventsBean) {
                rlscan.setVisibility(View.GONE);
                listItem = patEventsBean.getEventList();
                patEventsAdapter.setNewData(listItem);
                patEventsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String code, String msg) {

                showToast("error" + code + ":" + msg);
            }
        });
    }

    //扫描或带参数过来显示
    private void showToolbarRight() {
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("recId", null);
                bundle.putString("episodeId", episodeIdNow);
                if (isSingleModel){
                    patInfo = singlePatInfo;
                }
                bundle.putString("patInfo", patInfo);
                startFragment(PatEventsDetailFragment.class, bundle);
            }
        });
        setToolbarRightCustomView(viewright);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (intent.getAction().equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            getUserMsg(bundle.getString("data"));

        }

    }
}
