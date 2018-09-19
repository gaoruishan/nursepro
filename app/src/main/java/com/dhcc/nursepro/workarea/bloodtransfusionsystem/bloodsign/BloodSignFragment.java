package com.dhcc.nursepro.workarea.bloodtransfusionsystem.bloodsign;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.constant.Action;

import java.util.Objects;

/**
 * BloodSignFragment
 * 血袋签收
 *
 * @author DevLix126
 * created at 2018/9/18 10:34
 */
public class BloodSignFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout rlBloodScan;
    private TextView tvBloodWarning;
    private TextView tvBloodWarningtitle;
    private TextView tvBloodsignBloodbaginfo;
    private TextView tvBloodsignBloodproductinfo;
    private TextView tvBloodsignBloodpatientinfo;
    private TextView tvBloodsignSure;

    private IntentFilter filter;
    private Receiver mReceiver = new Receiver();
    private String episodeId = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blood_sign, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));

        showToolbarNavigationIcon(R.drawable.icon_back_blue);
        setToolbarCenterTitle(getString(R.string.title_bloodsign));
        //右上角按钮
        View viewright = View.inflate(getActivity(), R.layout.view_bloodtoolbar_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "今日列表", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);

        filter = new IntentFilter();
        filter.addAction(Action.DEVICE_SCAN_CODE);
        getActivity().registerReceiver(mReceiver, filter);

        initView(view);


    }

    @Override
    public void onPreFinish(UniversalActivity activity) {
        super.onPreFinish(activity);
        activity.unregisterReceiver(mReceiver);
    }


    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {

                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    Toast.makeText(context, bundle.getString("data"), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    private void initView(View view) {
        rlBloodScan = view.findViewById(R.id.rl_blood_scan);
        tvBloodWarning = view.findViewById(R.id.tv_blood_warning);
        tvBloodWarningtitle = view.findViewById(R.id.tv_blood_warningtitle);
        tvBloodsignBloodbaginfo = view.findViewById(R.id.tv_bloodsign_bloodbaginfo);
        tvBloodsignBloodproductinfo = view.findViewById(R.id.tv_bloodsign_bloodproductinfo);
        tvBloodsignBloodpatientinfo = view.findViewById(R.id.tv_bloodsign_bloodpatientinfo);
        tvBloodsignSure = view.findViewById(R.id.tv_bloodsign_sure);
        tvBloodsignSure.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bloodsign_sure:
                Toast.makeText(getActivity(), "sure", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
