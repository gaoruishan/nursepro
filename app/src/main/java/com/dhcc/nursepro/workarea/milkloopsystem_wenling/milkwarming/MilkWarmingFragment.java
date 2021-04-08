package com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkwarming;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.Action;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkWarmingBaginfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkWarmingSttBean;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.milkwarming
 * <p>
 * author Q
 * Date: 2019/7/5
 * Time:14:52
 */
public class MilkWarmingFragment extends BaseFragment implements View.OnClickListener {


    private String bagNo = "";
    private TextView tvPat,tvBag,tvStatu;
    private LinearLayout llEnd;
    private Button btnCancle,btnEnd;
    private ImageView imgPat,imgBag,imgMsg;
    private View line1,line2;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_wl_warming, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);

        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_warming));
        setToolbarBottomLineVisibility(false);


        initView(view);

//        initData();

    }

    private void initView(View view){
        tvPat = view.findViewById(R.id.tv_milkwarming_pat);
        tvBag = view.findViewById(R.id.tv_milkwarming_bag);
        tvStatu = view.findViewById(R.id.tv_milkwarming_status);
        llEnd = view.findViewById(R.id.ll_warming_end);
        btnCancle = view.findViewById(R.id.btn_warming_cancle);
        btnCancle.setOnClickListener(this);
        btnEnd  = view.findViewById(R.id.btn_warming_end);
        btnEnd.setOnClickListener(this);
        llEnd.setVisibility(View.GONE);
        imgPat = view.findViewById(R.id.icon_milkwarming_pat);
        imgBag = view.findViewById(R.id.icon_milkwarming_bag);
        imgMsg = view.findViewById(R.id.icon_milkwarming_msg);
        line1 = view.findViewById(R.id.line_milkwarming_1);
        line2 = view.findViewById(R.id.line_milkwarming_2);
    }

    private void initData(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("bagNo",bagNo);
        MilkLoopApiManager.getMilkWarminginfo(map, NurseAPI.getHeatInfo, new MilkLoopApiManager.MilkWarmingBagInfoCallback() {
            @Override
            public void onSuccess(MilkWarmingBaginfoBean milkWarmingBaginfoBean) {

                imgLineHind(true);
                tvPat.setText(milkWarmingBaginfoBean.getPatInfo().getBedCode()+"  "+milkWarmingBaginfoBean.getPatInfo().getPatName());
                tvBag.setText(milkWarmingBaginfoBean.getPatInfo().getBagNo()+"  "+milkWarmingBaginfoBean.getPatInfo().getAmount()+" ml");
                tvStatu.setText(milkWarmingBaginfoBean.getPatInfo().getHeatSttDateTime());
                if (milkWarmingBaginfoBean.getPatInfo().getStatus().equals("cold")){
                    initDataStt();
                }else if (milkWarmingBaginfoBean.getPatInfo().getStatus().equals("heatstt")){
                    llEnd.setVisibility(View.VISIBLE);
                }else {
                    tvStatu.setText("当前状态：结束温奶"+"\n开始时间："+milkWarmingBaginfoBean.getPatInfo().getHeatSttDateTime()+"\n结束时间："
                            +milkWarmingBaginfoBean.getPatInfo().getHeatEndDateTime()+"\n温奶时常："+milkWarmingBaginfoBean.getPatInfo().getHeatOverTime());
                }
            }

            @Override
            public void onFail(String code, String msg) {
                imgLineHind(false);
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void initDataStt(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("bagNo",bagNo);
        map.put("userId","3");
        MilkLoopApiManager.getMilkWarmingStt(map,NurseAPI.bagHeatStt, new MilkLoopApiManager.MilkWarmingSttCallback() {
            @Override
            public void onSuccess(MilkWarmingSttBean milkWarmingSttBean) {

                tvPat.setText(milkWarmingSttBean.getPatinfo().getBedCode()+"  "+milkWarmingSttBean.getPatinfo().getPatName());
                tvBag.setText(milkWarmingSttBean.getPatinfo().getBagNo()+"  "+milkWarmingSttBean.getPatinfo().getAmount()+" ml");
                tvStatu.setText("当前状态：正在温奶"+"\n开始时间："+milkWarmingSttBean.getPatinfo().getHeatSttDateTime()+"\n结束时间："
                        +milkWarmingSttBean.getPatinfo().getHeatEndDateTime()+"\n温奶时常："+milkWarmingSttBean.getPatinfo().getHeatOverTime());
                showToast("开始温奶");
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }
    private void initDataEnd(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("bagNo",bagNo);
        map.put("userId","3");
        MilkLoopApiManager.getMilkWarmingStt(map,NurseAPI.bagHeatEnd, new MilkLoopApiManager.MilkWarmingSttCallback() {
            @Override
            public void onSuccess(MilkWarmingSttBean milkWarmingSttBean) {

                tvPat.setText(milkWarmingSttBean.getPatinfo().getBedCode()+"  "+milkWarmingSttBean.getPatinfo().getPatName());
                tvBag.setText(milkWarmingSttBean.getPatinfo().getBagNo()+"  "+milkWarmingSttBean.getPatinfo().getAmount()+" ml");
                tvStatu.setText("当前状态：温奶完成"+"\n开始时间："+milkWarmingSttBean.getPatinfo().getHeatSttDateTime()+"\n结束时间："
                        +milkWarmingSttBean.getPatinfo().getHeatEndDateTime()+"\n温奶时常："+milkWarmingSttBean.getPatinfo().getHeatOverTime());
                showToast("温奶结束");
                llEnd.setVisibility(View.GONE);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_warming_cancle:
                imgLineHind(false);
                cancleMsg();
                break;
            case R.id.btn_warming_end:
                initDataEnd();
                break;
            default:

                break;
        }

    }

    private void cancleMsg(){
        tvPat.setText("");
        tvBag.setText("");
        tvStatu.setText("");
        llEnd.setVisibility(View.GONE);
    }

    private void imgLineHind(Boolean b){
        imgPat.setSelected(b);
        imgBag.setSelected(b);
        imgMsg.setSelected(b);
        line1.setSelected(b);
        line2.setSelected(b);
    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.DEVICE_SCAN_CODE.equals(intent.getAction())) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            bagNo = bundle.getString("data");
            cancleMsg();
            initData();
        }
    }
}
