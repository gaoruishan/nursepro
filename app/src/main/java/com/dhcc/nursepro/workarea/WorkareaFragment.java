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
import com.dhcc.nursepro.workarea.checkandlab.CheckLabPatsFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignDetailFragment;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvWorkareaBedmap;
    private TextView tvWorkareaVitalSign;
    private TextView tvWorkareaEvents;
    private TextView tvWorkAreaOrderSearch,tvWorkAreaCheck,tvWorkAreaLab;


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
        tvWorkareaBedmap = view.findViewById(R.id.tv_workarea_bedmap);
        tvWorkareaBedmap.setOnClickListener(this);
        tvWorkareaVitalSign = view.findViewById(R.id.tv_workarea_vitalsign);
        tvWorkareaVitalSign.setOnClickListener(this);
        tvWorkareaEvents = view.findViewById(R.id.tv_workarea_events);
        tvWorkareaEvents.setOnClickListener(this);
        tvWorkAreaOrderSearch = view.findViewById(R.id.tv_workarea_ordersearch);
        tvWorkAreaOrderSearch.setOnClickListener(this);
        tvWorkAreaLab = view.findViewById(R.id.tv_workarea_lab);
        tvWorkAreaLab.setOnClickListener(this);
        tvWorkAreaCheck = view.findViewById(R.id.tv_workarea_check);
        tvWorkAreaCheck.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        switch (v.getId()) {

            case R.id.tv_workarea_bedmap:
                startFragment(BedMapFragment.class);
                break;
            case R.id.tv_workarea_vitalsign:
                startFragment(VitalSignFragment.class);
                break;
            case R.id.tv_workarea_events:
//                bundle.putString("recId",null);
//                bundle.putString("regNo","0000000129");
//                startFragment(PatEventsFragment.class,bundle);
                startFragment(PatEventsFragment.class);
                break;
            case R.id.tv_workarea_ordersearch:
                startFragment(OrderSearchFragment.class);
                break;
            case R.id.tv_workarea_check:
                bundle.putString("episodeId","94");
                startFragment(VitalSignDetailFragment.class,bundle);
                break;
            case R.id.tv_workarea_lab:
                bundle.putString("checklab","lab");
                startFragment(CheckLabPatsFragment.class,bundle);
                break;
            default:
                break;
        }
    }
}
