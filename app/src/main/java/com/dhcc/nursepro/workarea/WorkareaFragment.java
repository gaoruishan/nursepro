package com.dhcc.nursepro.workarea;

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
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvWorkareaLogintest;
    private TextView tvWorkareaBedmap;
    private TextView tvWorkareaVitalSign;
    private TextView tvWorkareaEvents;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        hideToolbarNavigationIcon();

        initView(view);

        initData();

    }

    private void initView(View view) {
        tvWorkareaLogintest = view.findViewById(R.id.tv_workarea_logintest);
        tvWorkareaLogintest.setOnClickListener(this);
        tvWorkareaBedmap = view.findViewById(R.id.tv_workarea_bedmap);
        tvWorkareaBedmap.setOnClickListener(this);
        tvWorkareaVitalSign = view.findViewById(R.id.tv_workarea_vitalsign);
        tvWorkareaVitalSign.setOnClickListener(this);
        tvWorkareaEvents = view.findViewById(R.id.tv_workarea_events);
        tvWorkareaEvents.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_workarea_logintest:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_workarea_bedmap:
                startFragment(BedMapFragment.class);
                break;
            case R.id.tv_workarea_vitalsign:
                startFragment(VitalSignFragment.class);
                break;
            case R.id.tv_workarea_events:
                Bundle bundle = new Bundle();
//                bundle.putString("recId",null);
//                bundle.putString("regNo","0000000129");
//                startFragment(PatEventsFragment.class,bundle);
                startFragment(PatEventsFragment.class);
                break;
            default:
                break;
        }
    }
}
