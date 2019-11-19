package com.dhcc.nursepro.workarea.shift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.WorkareaFragment;
import com.dhcc.nursepro.workarea.shift.adapter.ShiftDetailAdapter;
import com.dhcc.nursepro.workarea.shift.adapter.ShiftTopAdapter;
import com.dhcc.nursepro.workarea.shift.api.ShiftApiManager;
import com.dhcc.nursepro.workarea.shift.bean.ShiftBean;
import com.dhcc.nursepro.workarea.shift.bean.ShiftDetailBean;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.shift
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:15:32
 */
public class ShiftFragment extends BaseFragment {

    private List<ShiftBean.ShiftDataBean> listShift = new ArrayList<ShiftBean.ShiftDataBean>();
    private List<TextView> listViews = new ArrayList<TextView>();
    private List listShiftTop = new ArrayList();
    private LinearLayout llName,llDetTitle;
    private ShiftTopAdapter shiftTopAdapter = new ShiftTopAdapter(new ArrayList<>());
    private ShiftDetailAdapter shiftDetailAdapter = new ShiftDetailAdapter(new ArrayList<>());
    private RecyclerView recShift,recShiftDetail;
    private TextView tvShiftDate;
    private String shiftDate = "";
    private int shiftTypeIndex = 0;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shift, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //状态栏 背景 默认蓝色
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        showToolbarNavigationIcon(R.drawable.icon_back_white);
        //toolbar 中间标题 默认黑色
        setToolbarCenterTitle(getString(R.string.title_shift), 0xffffffff, 17);
        initView(view);
//        initData();

        initDataShift();
    }

    private void initView(View view) {
        llName = view.findViewById(R.id.ll_shift_name_list);
        recShift = view.findViewById(R.id.rec_shift);
        recShiftDetail = view.findViewById(R.id.recy_shiftdetail);
        recShift.setHasFixedSize(true);
        recShift.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recShift.setAdapter(shiftTopAdapter);
        recShiftDetail.setHasFixedSize(true);
        recShiftDetail.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recShiftDetail.setAdapter(shiftDetailAdapter);
        llDetTitle = view.findViewById(R.id.ll_shiftdetail_title);

        tvShiftDate = view.findViewById(R.id.tv_shiftdate);
        shiftDate = SPUtils.getInstance().getString(SharedPreference.SCHSTDATETIME).substring(0,10);
        tvShiftDate.setText(shiftDate);
        tvShiftDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long curMill = TimeUtils.string2Millis(shiftDate+" 00:00:00");
                DateUtils.chooseDate(curMill,getActivity(), getFragmentManager(), new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        tvShiftDate.setText(DateUtils.getDateByMillisecond(millseconds));
                        shiftDate = DateUtils.getDateByMillisecond(millseconds);
                        initDataShift();
                    }
                });

            }
        });

    }
    private void initDataShift(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap<String,String>();
        map.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        map.put("schDate", shiftDate);
        ShiftApiManager.getShiftList(map, "getShiftData", new ShiftApiManager.GetShiftTopListCallback() {
            @Override
            public void onSuccess(ShiftBean shiftBean) {
                hideLoadFailTip();
                listShift = shiftBean.getShiftData();
                addBtns();
                shiftTopAdapter.setNewData(listShift.get(shiftTypeIndex).getData());
                shiftTopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
            }
        });
    }

    private void addBtns(){
        llName.removeAllViews();
        listViews = new ArrayList<>();
                for (int i = 0; i <listShift.size() ; i++) {
                    TextView tvButton = new TextView(getContext());
                    tvButton.setText(listShift.get(i).getName());
//                    listShiftTop = new ArrayList();
//                    listShiftTop = (List) mapShift.get("data");
                    tvButton.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(80), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleParams.setMargins(2, 0, 2, 0);//4个参数按顺序分别是左上右下
                    tvButton.setLayoutParams(titleParams);
                    tvButton.setTextColor(getResources().getColor(R.color.black));
                    tvButton.setBackgroundResource(R.drawable.bg_shift_selector);
                    llName.addView(tvButton);
                    listViews.add(tvButton);
                    if (i == shiftTypeIndex){
                        tvButton.setSelected(true);
                        initDataShiftDetail(listShift.get(shiftTypeIndex).getType());
                    }else {
                        tvButton.setSelected(false);
                    }
//                    tvButton.setTag(1,i);
                    tvButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            showToast(tvButton.getText().toString());
                            for (int j = 0; j <listViews.size() ; j++) {
                                listViews.get(j).setSelected(false);
                            }
                            tvButton.setSelected(true);
                            for (int j = 0; j < listShift.size(); j++) {
//                                Map mapShift1 = (Map) listShift.get(j);
                                if (listShift.get(j).getName().equals(tvButton.getText().toString())){
                                    shiftTopAdapter.setNewData(listShift.get(j).getData());
                                    shiftTopAdapter.notifyDataSetChanged();
                                    initDataShiftDetail(listShift.get(j).getType());
                                    shiftTypeIndex = j;
                                }

                            }
                        }
                    });
                }
    }
    private void initDataShiftDetail(String shiftId){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap<String,String>();
        map.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        map.put("schDate", shiftDate);
        map.put("id", shiftId);
        ShiftApiManager.getShiftDataDetail(map, "getShiftDataDetail", new ShiftApiManager.GetShiftDetailListCallback() {
            @Override
            public void onSuccess(ShiftDetailBean shiftBean) {
                hideLoadFailTip();
                llDetTitle.setVisibility(View.VISIBLE);
                shiftDetailAdapter.setNewData(shiftBean.getDetailList());
                shiftDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error:"+code+msg);
            }
        });
    }
}