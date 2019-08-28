package com.dhcc.nursepro.workarea.orderexecute;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseFragment;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.Action;

/**
 * OrderHandleTypeFragment
 * 处理类型弹窗
 *
 * @author DevLix126
 * created at 2018/9/4 11:22
 */
public class OrderHandleTypeFragment extends BaseFragment {
    private LinearLayout lltype;

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
        lltype = view.findViewById(R.id.ll_handle_types);
        lltype.removeAllViews();
        for (int i = 0; i <OrderExecuteFragment.typelist.size() ; i++) {
            View item_view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_handle_type_item, null);
            TextView tvtype = item_view.findViewById(R.id.tv_handle_type);
            String stAll = OrderExecuteFragment.typelist.get(i).toString();
            String sttype =  stAll.substring(stAll.indexOf("|")+1,stAll.length());
            String stcode = stAll.substring(0,stAll.indexOf("|"));
            tvtype.setText(sttype);
            LinearLayout llpoptype = item_view.findViewById(R.id.ll_pop_type);
            llpoptype.setSelected(false);
            tvtype.setTag(stcode);
            llpoptype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llpoptype.setSelected(true);
                    Bundle bundle = new Bundle();
                    bundle.putString("handledesc",tvtype.getText().toString());
                    bundle.putString("handlecode",tvtype.getTag().toString());
                    intent.putExtras(bundle);
                    intent.setAction(Action.ORDER_HANDLE_TYPE);
                    getActivity().sendBroadcast(intent);
                }
            });

            lltype.addView(item_view);
        }
    }
}
