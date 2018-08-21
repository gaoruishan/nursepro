package com.dhcc.nursepro.workarea.patevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiManager;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.TimeWheel;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.data.WheelCalendar;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatEventsDetailFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyPatevents;
    private List<String> list;
    private PatEventsAdapter patEventsAdapter;
    private List<EventItemBean.EventItemListBean> listItem;
    private int isel = 0;
    private String eventId;
    private TimeWheel mTimeWheel;
    private String recId,episodeId,eventDate,eventTime,buneventId,userId;
    private Calendar calendar;

    private String userIdNow;
    private SPUtils spUtils = SPUtils.getInstance();
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pateventdetail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);

        initview(view);
        initData();


    }


    private void initview(View view){

        userIdNow = spUtils.getString("USERID");

        Bundle bundle = getArguments();
        if (bundle != null) {
            recId = bundle.getString("recId");
            episodeId = bundle.getString("episodeId");
            eventDate = bundle.getString("eventDate");
            eventTime = bundle.getString("eventTime");
            eventId = bundle.getString("eventId");
            userId = bundle.getString("userId");
        }
        if(recId == null){
            setToolbarCenterTitle(getStringSafe(R.string.title_addpatevents));
        }else {
            setToolbarCenterTitle(getStringSafe(R.string.title_changepatevents));
        }


        recyPatevents = view.findViewById(R.id.recy_pateventsdetail);
        recyPatevents.setHasFixedSize(true);
        recyPatevents.setLayoutManager(new GridLayoutManager(getActivity(),4));

        //右上角保存按钮
        View viewright =  View.inflate(getActivity(),R.layout.view_fratoolbar_right,null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureClicked();
            }
        });
        setToolbarRightCustomView(viewright);
        //时间控件
        View viewtime = view.findViewById(R.id.ll_timepicker);
        TextView cancel = (TextView) view.findViewById(com.jzxiang.pickerview.R.id.tv_cancel);
        cancel.setOnClickListener(this);
        TextView sure = (TextView) view.findViewById(com.jzxiang.pickerview.R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = (TextView) view.findViewById(com.jzxiang.pickerview.R.id.tv_title);
        View toolbar = view.findViewById(com.jzxiang.pickerview.R.id.toolbar);
        PickerConfig mPickerConfig = new PickerConfig();
        title.setText(mPickerConfig.mTitleString);
        cancel.setText(mPickerConfig.mCancelString);
        sure.setText(mPickerConfig.mSureString);
        mPickerConfig.mWheelTVSelectorColor = getResources().getColor(R.color.blue);
        mPickerConfig.mThemeColor = getResources().getColor(R.color.blue);

        if (recId != null) {
            calendar = Calendar.getInstance();
            String[] arrdate = eventDate.split("-");
            String[] arrtime = eventTime.split(":");
            calendar.set(Integer.parseInt(arrdate[0]), Integer.parseInt(arrdate[1]) - 1, Integer.parseInt(arrdate[2]), Integer.parseInt(arrtime[0]), Integer.parseInt(arrtime[1]));
            mPickerConfig.mCurrentCalendar = new WheelCalendar(calendar.getTimeInMillis());
        }
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        mTimeWheel = new TimeWheel(viewtime, mPickerConfig);

    }

    void sureClicked() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
        calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
        calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
        calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());

        //时间
        long mCurrentMillSeconds = calendar.getTimeInMillis();
        Date date = new Date(mCurrentMillSeconds);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//精确到分钟
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        String timestr = format1.format(date);
        String datestr = format.format(date);


        HashMap<String,String> map = new HashMap<String, String>();
        if (recId != null){
        map.put("recId",recId);
        }
        map.put("episodeId",episodeId);
        map.put("eventDate",datestr);
        map.put("eventTime",timestr);
        map.put("eventId",eventId);
        map.put("userId",userIdNow);

        String methodName = "saveEvent";
        PatEventsApiManager.GetEventsResultMsg(map,methodName, new PatEventsApiManager.GetEventsResultMsgCallBack() {
            @Override
            public void onSuccess(String msgs) {
                showToast(msgs);
                finish();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast(code+":"+msg);
            }
        });



    }

    void  initData(){

         PatEventsApiManager.GetEventsTypes(null, new PatEventsApiManager.GetEventsTypesCallBack() {
             @Override
             public void onSuccess(EventItemBean eventItemBean) {
                 listItem = eventItemBean.getEventItemList();
                 patEventsAdapter = new PatEventsAdapter(new ArrayList<EventItemBean.EventItemListBean>());
                 recyPatevents.setAdapter(patEventsAdapter);
                 patEventsAdapter.setNewData(listItem);
             }

             @Override
             public void onFail(String code, String msg) {
                 showToast(code+":"+msg);
             }
         });
    }

    public class PatEventsAdapter extends BaseQuickAdapter<EventItemBean.EventItemListBean,BaseViewHolder> {

        public PatEventsAdapter(@Nullable List<EventItemBean.EventItemListBean> data) {
            super(R.layout.item_pateventsdetail,data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final EventItemBean.EventItemListBean item) {

            helper.setText(R.id.tv_patevents_eventtype,item.getDesc());
            //修改事件，判断原有type
            //默认选中第一个
            if (eventId == null){
                eventId = item.getId();
            }
            if (recId != null){
//                isel = Integer.parseInt(eventId);
                if (item.getId().equals(eventId)){
                    isel = helper.getAdapterPosition();
                }else {
                    isel = -1;
                }
            }
            final TextView tvsel = helper.getView(R.id.tv_patevents_eventtype);
            if (isel ==helper.getAdapterPosition()){
                switch (item.getDesc()){
                    case "外出":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_1));
                        break;
                    case "死亡":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_2));
                        break;
                    case "转出":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_3));
                        break;
                    case "出院":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_4));
                        break;
                    case "分娩":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_5));
                        break;
                    case "手术":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_6));
                        break;
                    case "转入":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_7));
                        break;
                    case "入院":
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_8));
                        break;
                    default:
                        tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_default));
                        break;

                }
                tvsel.setTextColor(getResources().getColor(R.color.white));
            }else {
                tvsel.setBackground(getResources().getDrawable(R.drawable.bg_eventcircle_unselect));
                tvsel.setTextColor(getResources().getColor(R.color.patevents_tv_unsel_color));
            }
            tvsel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getActivity(),helper.getAdapterPosition()+"---"+helper.getPosition()+"---"+helper.getLayoutPosition()+"---"+helper.getOldPosition(),Toast.LENGTH_LONG).show();

                    eventId = item.getId();
                    isel = helper.getAdapterPosition();
                    patEventsAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

    }
}
