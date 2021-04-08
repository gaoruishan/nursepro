package com.dhcc.nursepro.workarea.patevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.adapter.PatEventsDetailAdapter;
import com.dhcc.nursepro.workarea.patevents.api.PatEventsApiManager;
import com.dhcc.nursepro.workarea.patevents.bean.EventItemBean;
import com.jzxiang.pickerview.TimeWheel;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.WheelCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PatEventsDetailFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyPatevents;
    private List<String> list =new ArrayList<>();
    private PatEventsDetailAdapter patEventsDetailAdapter;
    private List<EventItemBean.EventItemListBean> listItem =new ArrayList<>();
    private int isel = 0;
    private String eventId;
    private TimeWheel mTimeWheel;
    private String recId, episodeId, eventDate="", eventTime="", buneventId, userId,patInfo = "";
    private Calendar calendar;
    private long currentTimeMillis;
    private long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

    private String userIdNow;
    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pateventdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setToolbarType(BaseActivity.ToolbarType.TOP);
        if (isSingleModel){
            hindMap();
        }
        setToolbarBottomLineVisibility(true);

        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
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
//        setToolbarRightCustomView(viewright);
        setToolbarRightCustomViewSingleShow(viewright);
        Bundle bundle = getArguments();
        if (bundle != null) {
            recId = bundle.getString("recId");
            episodeId = bundle.getString("episodeId");
            eventDate = bundle.getString("eventDate");
            eventTime = bundle.getString("eventTime");
            if (eventTime != null && eventTime.length()<7){
                eventTime =eventTime +":00";
            }
            eventId = bundle.getString("eventId");
            userId = bundle.getString("userId");
            patInfo = bundle.getString("patInfo");
        }
        if (recId == null) {
            setToolbarCenterTitle(getStringSafe(R.string.title_addpatevents)+"("+patInfo+")", 0xffffffff, 17);
            String schDT = SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME);
            if (schDT.length()<17){
                schDT = schDT + ":00";
            }
            currentTimeMillis = TimeUtils.string2Millis(schDT.replace("/", "-").replace(",", " "));
        } else {
            setToolbarCenterTitle(getStringSafe(R.string.title_changepatevents)+"("+patInfo+")", 0xffffffff, 17);
            currentTimeMillis = TimeUtils.string2Millis(eventDate + " " + eventTime);
        }

        initview(view);
        initAdapter();
        initData();


    }

    private void sureClicked() {
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


        HashMap<String, String> map = new HashMap<String, String>();
        if (recId != null) {
            map.put("recId", recId);
        }
        map.put("episodeId", episodeId);
        map.put("eventDate", datestr);
        map.put("eventTime", timestr);
        map.put("eventId", eventId);
        map.put("userId", userIdNow);

        String methodName = NurseAPI.saveEvent;
        PatEventsApiManager.getEventsResultMsg(map, methodName, new PatEventsApiManager.GetEventsResultMsgCallBack() {
            @Override
            public void onSuccess(String msgs) {
                showToast(msgs);
                finish();
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });


    }

    private void initview(View view) {

        userIdNow = spUtils.getString(SharedPreference.USERID);


        recyPatevents = view.findViewById(R.id.recy_pateventsdetail);
        recyPatevents.setHasFixedSize(true);
        recyPatevents.setLayoutManager(new GridLayoutManager(getActivity(), 4));


        //时间控件
        View viewtime = view.findViewById(R.id.ll_timepicker);
        TextView cancel = view.findViewById(com.jzxiang.pickerview.R.id.tv_cancel);
        cancel.setOnClickListener(this);
        TextView sure = view.findViewById(com.jzxiang.pickerview.R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = view.findViewById(com.jzxiang.pickerview.R.id.tv_title);
        View toolbar = view.findViewById(com.jzxiang.pickerview.R.id.toolbar);
        PickerConfig mPickerConfig = new PickerConfig();
        title.setText(mPickerConfig.mTitleString);
        cancel.setText(mPickerConfig.mCancelString);
        sure.setText(mPickerConfig.mSureString);
        mPickerConfig.mWheelTVSelectorColor = getResources().getColor(R.color.blue);
        mPickerConfig.mThemeColor = getResources().getColor(R.color.blue);
        mPickerConfig.mMinCalendar = new WheelCalendar(currentTimeMillis - tenYears);
        mPickerConfig.mMaxCalendar = new WheelCalendar(currentTimeMillis + tenYears);
        mPickerConfig.mCurrentCalendar = new WheelCalendar(currentTimeMillis);

        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        mTimeWheel = new TimeWheel(viewtime, mPickerConfig);

    }

    private void initAdapter() {
        patEventsDetailAdapter = new PatEventsDetailAdapter(new ArrayList<EventItemBean.EventItemListBean>());
        patEventsDetailAdapter.setEventId(eventId);
        patEventsDetailAdapter.setRecId(recId);
        recyPatevents.setAdapter(patEventsDetailAdapter);
        patEventsDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_patevents_eventtype) {
                    eventId = listItem.get(position).getId();
                    patEventsDetailAdapter.setEventId(eventId);
                    patEventsDetailAdapter.setIsel(position);
                    patEventsDetailAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {

        PatEventsApiManager.getEventsTypes(null, new PatEventsApiManager.GetEventsTypesCallBack() {
            @Override
            public void onSuccess(EventItemBean eventItemBean) {
                listItem = eventItemBean.getEventItemList();
                patEventsDetailAdapter.setNewData(listItem);
                patEventsDetailAdapter.notifyDataSetChanged();
                if (eventId == null) {
                    eventId = listItem.get(0).getId();
                    patEventsDetailAdapter.setEventId(eventId);
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
