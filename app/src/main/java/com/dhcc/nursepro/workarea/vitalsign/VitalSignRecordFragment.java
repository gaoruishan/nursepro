package com.dhcc.nursepro.workarea.vitalsign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;

import java.util.Map;

public class VitalSignRecordFragment extends BaseFragment implements View.OnClickListener {

    private Map patientInfo;

    private TextView et_time;

    private String timepoint;

    private String curEpisodeId;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_record, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign_record));

        initData();

        initView(view);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncGetVitalSignItems();
            }
        }, 300);

    }

    private void initView(View view){
        et_time = view.findViewById(R.id.et_vital_sign_record_time);
        et_time.setOnClickListener(this);
        et_time.setText(timepoint);
    }

    private void initData(){
        Bundle bundle = getArguments();
        patientInfo = (Map)bundle.getSerializable("info");
        timepoint = bundle.getString("timepoint");
        curEpisodeId = (String)patientInfo.get("episodeId");
    }

    private void asyncGetVitalSignItems(){

        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.getVitalSignItems(curEpisodeId, timepoint, new VitalSignApiManager.GetVitalSignItemCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {
                Toast.makeText(getActivity(), "体征录入项目请求成功", Toast.LENGTH_SHORT).show();

                hideLoadFailTip();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_vital_sign_record_time:
                break;
            default:
                break;
        }
    }
}
