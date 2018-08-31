package com.dhcc.nursepro.workarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.BedMapFragment;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.labpack.LabPackFragment;
import com.dhcc.nursepro.workarea.labresult.LabPatsFragment;
import com.dhcc.nursepro.workarea.operation.OperationFragment;
import com.dhcc.nursepro.workarea.orderhandle.OrderHandleFragment;
import com.dhcc.nursepro.workarea.ordersearch.OrderSearchFragment;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvWorkareaBedmap;
    private TextView tvWorkareaVitalSign;
    private TextView tvWorkareaEvents;
    private TextView tvWorkAreaOrderSearch,tvWorkAreaOrderHandle,tvWorkAreaCheck,tvWorkAreaLab,tvOperation,tvLabPack;


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
        tvWorkAreaOrderHandle = view.findViewById(R.id.tv_workarea_orderhandle);
        tvWorkAreaOrderHandle.setOnClickListener(this);
        tvWorkAreaLab = view.findViewById(R.id.tv_workarea_lab);
        tvWorkAreaLab.setOnClickListener(this);
        tvWorkAreaCheck = view.findViewById(R.id.tv_workarea_check);
        tvWorkAreaCheck.setOnClickListener(this);
        tvOperation = view.findViewById(R.id.tv_workarea_operation);
        tvOperation.setOnClickListener(this);
        tvLabPack = view.findViewById(R.id.tv_workarea_labpack);
        tvLabPack.setOnClickListener(this);
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
            case R.id.tv_workarea_orderhandle:
                startFragment(OrderHandleFragment.class);
                break;
            case R.id.tv_workarea_check:
//                bundle.putString("episodeId","94");
//                startFragment(VitalSignDetailFragment.class,bundle);
                startFragment(CheckPatsFragment.class);
                break;
            case R.id.tv_workarea_lab:
                startFragment(LabPatsFragment.class);
                break;
            case R.id.tv_workarea_operation:
                startFragment(OperationFragment.class);
                break;
            case R.id.tv_workarea_labpack:
                startFragment(LabPackFragment.class);
                break;
            default:
                break;
        }
    }
}
