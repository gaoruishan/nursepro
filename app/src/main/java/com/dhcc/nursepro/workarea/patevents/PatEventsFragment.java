package com.dhcc.nursepro.workarea.patevents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.adapter.PatEventsAdapter;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiManager;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;
import com.dhcc.nursepro.workarea.patevents.bean.ScanGetUserMsgBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatEventsFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyPatevents;
    private RelativeLayout rlscan;
    private TextView tveventuser;
    private List<String> list;
    private PatEventsAdapter patEventsAdapter;
    private List<PatEventsBean.EventListBean> listItem;

    private IntentFilter intentFilter;
    private DataReceiver dataReceiver = null;

    private String episodeIdNow = null,regNo;

    private SPUtils spUtils = SPUtils.getInstance();
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patevents,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_patevents));

        init(view);

        //扫描广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.scanner.broadcast");
        dataReceiver = new DataReceiver();
        getActivity().registerReceiver(dataReceiver,intentFilter);
    }

    private void init(View view){
        rlscan = view.findViewById(R.id.rl_patevents_scan);
        tveventuser = view.findViewById(R.id.tv_event_user);
        Bundle bundle = getArguments();
        if (bundle != null) {
            regNo = bundle.getString("regNo");
            getUserMsg(regNo);
            rlscan.setVisibility(View.GONE);
        }

        recyPatevents = view.findViewById(R.id.recy_patevents);
        recyPatevents.setHasFixedSize(true);
        recyPatevents.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    //扫描或带参数过来显示
    void showToolbarRight(){
        //右上角按钮
        View viewright =  View.inflate(getActivity(),R.layout.view_fratoolbar_right,null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("recId",null);
                bundle.putString("episodeId",episodeIdNow);
                startFragment(PatEventsDetailFragment.class,bundle);
            }
        });
        setToolbarRightCustomView(viewright);
    }


    void  initData(String episodeId){
        showToolbarRight();
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("episodeId",episodeId);
        PatEventsApiManager.GetPatEventsList(map, new PatEventsApiManager.GetEventsSelectCallBack() {
            @Override
            public void onSuccess(PatEventsBean patEventsBean) {

                rlscan.setVisibility(View.GONE);
                listItem = patEventsBean.getEventList();
                patEventsAdapter = new PatEventsAdapter(new ArrayList<PatEventsBean.EventListBean>());
                recyPatevents.setAdapter(patEventsAdapter);
                patEventsAdapter.setNewData(listItem);

            }
            @Override
            public void onFail(String code, String msg) {

                showToast(code+":"+msg);
            }
        });
    }

    //获取病人信息并执行事件查询
    void getUserMsg(String regNo){
        String wardId = spUtils.getString("WARDID");
        HashMap<String,String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo",regNo);
        mapmsg.put("wardId",wardId);
        PatEventsApiManager.GetUserMsg(mapmsg, new PatEventsApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(ScanGetUserMsgBean scanGetUserMsgBean) {
                ScanGetUserMsgBean.PatInfoBean patInfoBean = scanGetUserMsgBean.getPatInfo();
                episodeIdNow = patInfoBean.getEpisodeID();
                String usermsg = patInfoBean.getBedCode()+"床--"+patInfoBean.getName();
                tveventuser.setText(usermsg);
                initData(episodeIdNow);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });

    }

    //扫描腕带获取regNo、wardId
    public class DataReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.scanner.broadcast")){
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                getUserMsg(bundle.getString("data"));

            }
        }
    }

    public class PatEventsAdapter extends BaseQuickAdapter<PatEventsBean.EventListBean,BaseViewHolder> {

        public PatEventsAdapter(@Nullable List<PatEventsBean.EventListBean> data) {
            super(R.layout.item_patevents,data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final PatEventsBean.EventListBean item) {

            helper.setText(R.id.tv_patevents_eventtype,item.getEventDesc())
                    .setText(R.id.tv_patevents_eventmaker,item.getAddUser())
                    .setText(R.id.tv_patevents_eventdate,item.getEventDate())
                    .setText(R.id.tv_patevents_eventtime,item.getEventTime());
            LinearLayout lltoDetail = helper.getView(R.id.messagecontentll);
            lltoDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Bundle bundle = getArguments();
                    Bundle bundle = new Bundle();
                    bundle.putString("recId",item.getRecId());
                    bundle.putString("episodeId",episodeIdNow);
                    bundle.putString("eventDate",item.getEventDate());
                    bundle.putString("eventTime",item.getEventTime());
                    bundle.putString("eventId",item.getEventId());
                    bundle.putString("userId",item.getAddUser());

                    startFragment(PatEventsDetailFragment.class,bundle);
                }
            });

            TextView tvdesc = helper.getView(R.id.tv_patevents_eventtype);
            switch (item.getEventDesc()){
                case "外出":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_1));
                    break;
                case "死亡":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_2));
                    break;
                case "转出":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_3));
                    break;
                case "出院":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_4));
                    break;
                case "分娩":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_5));
                    break;
                case "手术":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_6));
                    break;
                case "转入":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_7));
                    break;
                case "入院":
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_8));
                    break;
                default:
                    tvdesc.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_default));
                    break;

            }

            final String recID = item.getRecId();
            TextView tvdel = helper.getView(R.id.tv_patevents_eventdel);
            tvdel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,String> mapDel = new HashMap<String, String>();
                    mapDel.put("recId",recID);
                    String methodName = "DelEvent";
                    PatEventsApiManager.GetEventsResultMsg(mapDel,methodName, new PatEventsApiManager.GetEventsResultMsgCallBack() {
                        @Override
                        public void onSuccess(String msgs) {
                            showToast(msgs);
                        }

                        @Override
                        public void onFail(String code, String msg) {
                            showToast(code+":"+msg);
                        }
                    });
                    if(listItem.size()>= helper.getAdapterPosition()) {
                        listItem.remove(helper.getAdapterPosition());
                    }
                    patEventsAdapter.notifyDataSetChanged();
                }
            });
        }
    }




    @Override
    public void onClick(View v) {

    }
    @Override
    public void onResume() {
        getActivity().registerReceiver(dataReceiver,intentFilter);
        if (episodeIdNow != null) {
            initData(episodeIdNow);
        }
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(dataReceiver);
    }
}
