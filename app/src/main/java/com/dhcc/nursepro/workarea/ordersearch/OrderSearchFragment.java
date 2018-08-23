package com.dhcc.nursepro.workarea.ordersearch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderSearchFragment extends BaseFragment {


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.title_ordersearch),0xffffffff,17);
        View viewright =  View.inflate(getActivity(),R.layout.view_toolbar_img_right,null);
        ImageView imageView = viewright.findViewById(R.id.img_toolbar_right);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initAdapter();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);


    }



    private void initView(View view) {

    }

    private void initAdapter() {

    }

    private void asyncInitData() {


    }

}
