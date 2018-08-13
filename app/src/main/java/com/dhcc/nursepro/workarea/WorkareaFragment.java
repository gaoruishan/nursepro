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

/**
 * WorkareaFragment
 * 主页
 */
public class WorkareaFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvWorkareaLogintest;
    private TextView tvWorkareaBedmap;


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
        tvWorkareaBedmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(BedMapFragment.class);
            }
        });
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
            default:
                break;
        }
    }
}
