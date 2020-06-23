package com.dhcc.nursepro.workarea.rjorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.LabOutListFragment;

/**
 * com.dhcc.nursepro.workarea.rjorder
 * <p>
 * author Q
 * Date: 2020/6/22
 * Time:9:07
 */
public class RjOrderFragment  extends BaseFragment {
    private TextView tvUser,tvLoc,tvWind,tvPat,tvSex,tvReg,tvAge;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rjorder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_rjorder), 0xffffffff, 17);
        //右上角按钮"新建"
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("    切换 >> ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(LabOutListFragment.class);
                finish();
            }
        });
//        setToolbarRightCustomView(viewright);
    }

}
