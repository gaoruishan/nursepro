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
public class OrderHandleTypeFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llPopupAccept;
    private LinearLayout llPopupRefuse;
    private LinearLayout llPopupComplete;

    private Intent intent = new Intent();


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_handle_type, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        llPopupAccept = view.findViewById(R.id.ll_popup_accept);
        llPopupRefuse = view.findViewById(R.id.ll_popup_refuse);
        llPopupComplete = view.findViewById(R.id.ll_popup_complete);

        llPopupAccept.setOnClickListener(this);
        llPopupRefuse.setOnClickListener(this);
        llPopupComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_popup_accept:
                llPopupAccept.setSelected(true);
                llPopupRefuse.setSelected(false);
                llPopupComplete.setSelected(false);
                intent.setAction(Action.ORDER_HANDLE_ACCEPT);
                break;
            case R.id.ll_popup_refuse:
                llPopupAccept.setSelected(false);
                llPopupRefuse.setSelected(true);
                llPopupComplete.setSelected(false);
                intent.setAction(Action.ORDER_HANDLE_REFUSE);
                break;
            case R.id.ll_popup_complete:
                llPopupAccept.setSelected(false);
                llPopupRefuse.setSelected(false);
                llPopupComplete.setSelected(true);
                intent.setAction(Action.ORDER_HANDLE_COMPLETE);
                break;
            default:
                break;
        }
        getActivity().sendBroadcast(intent);

    }
}
