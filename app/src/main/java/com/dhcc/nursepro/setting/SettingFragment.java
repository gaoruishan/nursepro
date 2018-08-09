package com.dhcc.nursepro.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.WsTest.ClassificationActivity;
import com.dhcc.nursepro.WsTest.SectionActivity;

public class SettingFragment extends BaseFragment {
    private TextView tvSettingClassification;
    private TextView tvSettingSection;
    private TextView tvgetjson;



    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        hideToolbarNavigationIcon();


        initView(view);

        initData();

    }

    private void initData(){

    }


    private void initView(View view){
        tvSettingClassification = getActivity().findViewById(R.id.setting_classification);
        tvSettingClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentClassification = new Intent(getActivity(), ClassificationActivity.class);
                intentClassification.putExtra("settingId",1);
                startActivity(intentClassification);
            }
        });
        tvSettingSection = getActivity().findViewById(R.id.setting_section);
        tvSettingSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSection = new Intent(getActivity(), SectionActivity.class);
                intentSection.putExtra("settingId",2);
                startActivity(intentSection);
            }
        });

        tvgetjson = getActivity().findViewById(R.id.tvgetjson);
        tvgetjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSection = new Intent(getActivity(), SectionActivity.class);
                startActivity(intentSection);
            }
        });
    }
}
