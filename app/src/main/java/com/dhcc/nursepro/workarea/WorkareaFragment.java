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

/**
 * WorkareaFragment
 * 主页
 *
 */
public class WorkareaFragment extends BaseFragment {
    private TextView tvWorkareaBedmap;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workarea,container,false);
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
        tvWorkareaBedmap = view.findViewById(R.id.tv_workarea_bedmap);
        tvWorkareaBedmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(BedMapFragment.class);
            }
        });
    }

}
