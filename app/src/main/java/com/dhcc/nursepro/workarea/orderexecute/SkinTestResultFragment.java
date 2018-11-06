package com.dhcc.nursepro.workarea.orderexecute;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;

/**
 * OrderHandleTypeFragment
 * 处理类型弹窗
 *
 * @author DevLix126
 * created at 2018/9/4 11:22
 */
public class SkinTestResultFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llPopupPositive;
    private LinearLayout llPopupNegative;

    private Intent intent = new Intent();


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skin_test_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        llPopupPositive = view.findViewById(R.id.ll_popup_positive);
        llPopupNegative = view.findViewById(R.id.ll_popup_negative);

        llPopupPositive.setOnClickListener(this);
        llPopupNegative.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_popup_positive:
                llPopupPositive.setSelected(true);
                llPopupNegative.setSelected(false);
                intent.setAction(Action.SKIN_TEST_YANG);
                break;
            case R.id.ll_popup_negative:
                llPopupPositive.setSelected(false);
                llPopupNegative.setSelected(true);
                intent.setAction(Action.SKIN_TEST_YIN);
                break;
            default:
                break;
        }
        getActivity().sendBroadcast(intent);

    }
}
