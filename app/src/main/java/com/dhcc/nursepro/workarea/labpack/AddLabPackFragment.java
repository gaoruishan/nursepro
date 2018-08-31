package com.dhcc.nursepro.workarea.labpack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labpack.adapter.AddLabPackAdapter;
import com.dhcc.nursepro.workarea.labpack.adapter.LabPackAdapter;
import com.dhcc.nursepro.workarea.patevents.PatEventsDetailFragment;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddLabPackFragment extends BaseFragment {

    private RecyclerView recaddLabPack;
    private AddLabPackAdapter addLabPackAdapter;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addlabpack, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("检验打包",0xffffffff,17);
        //右上角按钮"新建"
        View viewright =  View.inflate(getActivity(),R.layout.view_fratoolbar_right,null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  发送   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startFragment(PatEventsDetailFragment.class);
            }
        });
//        setToolbarRightCustomView(viewright);

        initview(view);

    }
    private void initview(View view){



        recaddLabPack = view.findViewById(R.id.rec_addlabpack);
        //提高展示效率
        recaddLabPack.setHasFixedSize(true);
        //设置的布局管理
        recaddLabPack.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recaddLabPack.setHasFixedSize(true);
        //设置的布局管理
        recaddLabPack.setLayoutManager(new LinearLayoutManager(getActivity()));

        addLabPackAdapter = new AddLabPackAdapter(new ArrayList<Map>());
        recaddLabPack.setAdapter(addLabPackAdapter);
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
        addLabPackAdapter.setNewData(list);

//        operationAdapter = new OperationAdapter(new ArrayList<Map>());
//        recOperation.setAdapter(operationAdapter);
//        List<Map> list = new ArrayList<>();
//        Map map = new HashMap();
//        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
//        operationAdapter.setNewData(list);


        //双选护士医生
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < locsBeanList.size(); i++) {
//            locDesc[i] = locsBeanList.get(i).getLocDesc();
//            list.add(locsBeanList.get(i).getLocDesc())
//        }
//
//        final DoublePicker picker = new DoublePicker(LoginActivity.this, list,list);
//        picker.setCanceledOnTouchOutside(false);
//        picker.setDividerRatio(WheelView.DividerConfig.FILL);
//        picker.setSelectedIndex(0,0);
//        picker.setCycleDisable(true);
//        picker.setTextSize(20);
//        picker.setOnPickListener
    }



}
