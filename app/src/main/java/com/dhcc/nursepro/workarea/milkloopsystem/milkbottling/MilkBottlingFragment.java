package com.dhcc.nursepro.workarea.milkloopsystem.milkbottling;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.milkloopsystem.MilkOperateResultDialog;
import com.dhcc.nursepro.workarea.milkloopsystem.api.MilkLoopApiManager;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkBottlingInfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkOperatResultBean;
import com.dhcc.nursepro.workarea.milkloopsystem.bean.MilkReceiveBagInfoBean;

import java.util.HashMap;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.MilkReceive
 * <p>
 * author Q
 * Date: 2018/9/20
 * Time:9:41
 */
public class MilkBottlingFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imgBag, imgPat, imgBottle;
    private View view1, view2;
    private TextView tvPat, tvBag, tvBottle, tvScanTip;

    private SPUtils spUtils = SPUtils.getInstance();

    private String patInfo = "", bagCode = "", bottleCode = "";

    private View viewright;
    private TextView tvSure;

    private TextView tvBottleCode, tvLeft;
    private EditText etAmount;
    private TextView tvBottling, tvThrow;
    private LinearLayout llScan;

    private long milkAllAmount = 0, milkBottling = 0, milkLeft = 0;

    private String milkBagOperation = "B";

    private MilkOperateResultDialog milkOperateResultDialog;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_milk_bottling, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_milk_bottling));

        //右上角按钮
        viewright = View.inflate(getActivity(), R.layout.view_save_blue_right, null);
        tvSure = viewright.findViewById(R.id.tv_bloodtoobar_right);
        tvSure.setTextColor(getResources().getColor(R.color.patevents_device_color));
        tvSure.setText("确定");
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转移焦点，隐藏键盘
                llScan.setFocusable(true);
                llScan.setFocusableInTouchMode(true);
                llScan.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etAmount.getWindowToken(), 0);

                if (bottleCode == "") {
                    return;
                }
                if (milkBottling > milkAllAmount) {
                    Toast.makeText(getActivity(), "奶袋奶量不足，请重新输入奶量", Toast.LENGTH_SHORT).show();
                } else {
                    milkBottling();
                }
            }
        });
        setToolbarRightCustomView(viewright);

        setToolbarBottomLineVisibility(false);


        initView(view);

        mReceiver = new Receiver();
        getActivity().registerReceiver(mReceiver, mfilter);

        //        view.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                if(getActivity().getCurrentFocus() != null){
        //                    /**
        //                     * 点击空白位置 隐藏软键盘
        //                     */
        //                    InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        //                    return mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        //                }
        //                return false;
        //            }
        //        });
    }

    private void milkBottling() {
        HashMap<String, String> map = new HashMap<>();
        map.put("bagNo", bagCode);
        map.put("bottleNo", bottleCode);
        map.put("amount", etAmount.getText().toString());
        map.put("userId", spUtils.getString(SharedPreference.USERID));
        map.put("type", milkBagOperation);
        MilkLoopApiManager.getMilkOperateResult(map, "bottling", new MilkLoopApiManager.MilkOperateCallback() {
            @Override
            public void onSuccess(MilkOperatResultBean milkOperatResultBean) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }

                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());

                milkOperateResultDialog.setExecresult("母乳装瓶成功");

                milkOperateResultDialog.setImgId(R.drawable.icon_popup_sucess);
                milkOperateResultDialog.setSureVisible(View.GONE);
                milkOperateResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        milkOperateResultDialog.dismiss();
                    }
                }, 500);
                cleanAll();
            }

            @Override
            public void onFail(String code, String msg) {
                if (milkOperateResultDialog != null && milkOperateResultDialog.isShowing()) {
                    milkOperateResultDialog.dismiss();
                }
                milkOperateResultDialog = new MilkOperateResultDialog(getActivity());
                milkOperateResultDialog.setExecresult(msg);
                milkOperateResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                milkOperateResultDialog.setSureVisible(View.VISIBLE);
                milkOperateResultDialog.setSureOnclickListener(new MilkOperateResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        milkOperateResultDialog.dismiss();
                    }
                });
                milkOperateResultDialog.show();
            }
        });
    }

    private void initView(View view) {
        imgBag = view.findViewById(R.id.icon_milkbottling_bag);
        imgPat = view.findViewById(R.id.icon_milkbottling_pat);
        imgBottle = view.findViewById(R.id.icon_milkbottling_bottle);
        view1 = view.findViewById(R.id.line_milkbottling_1);
        view2 = view.findViewById(R.id.line_milkbottling_2);
        tvPat = view.findViewById(R.id.tv_milkbottling_pat);
        tvBag = view.findViewById(R.id.tv_milkbottling_bag);
        tvBottle = view.findViewById(R.id.tv_milkbottling_bottle);
        tvScanTip = view.findViewById(R.id.tv_milkbottling_scantip);
        tvScanTip.setText("请扫描奶袋条码");

        tvBottleCode = view.findViewById(R.id.tv_milkbottling_bottlecode);
        etAmount = view.findViewById(R.id.et_milkbottling_amount);
        tvLeft = view.findViewById(R.id.tv_milkbottling_left);
        tvBottling = view.findViewById(R.id.tv_milkbottling_bottling);
        tvBottling.setOnClickListener(this);
        tvBottling.setSelected(true);
        tvThrow = view.findViewById(R.id.tv_milkbottling_throw);
        tvThrow.setOnClickListener(this);
        llScan = view.findViewById(R.id.ll_milkbottling_scan);


        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etAmount.getText().toString().length() > 0) {
                    milkBottling = Long.parseLong(etAmount.getText().toString());
                } else {
                    milkBottling = 0;
                }
                if (milkBottling > milkAllAmount) {
                    //                    showToast(Toast.LENGTH_SHORT,"奶袋奶量不足，请重新输入");
                    Toast.makeText(getActivity(), "奶袋奶量不足，请重新输入", Toast.LENGTH_SHORT).show();
                }
                milkLeft = milkAllAmount - milkBottling;
                tvLeft.setText(milkLeft + "");

            }
        });

    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_milkbottling_bottling:
                tvBottling.setSelected(true);
                tvThrow.setSelected(false);
                milkBagOperation = "B";
                break;
            case R.id.tv_milkbottling_throw:
                tvBottling.setSelected(false);
                tvThrow.setSelected(true);
                milkBagOperation = "C";
                break;
            default:
                break;
        }

    }

    private void cleanAll() {
        llScan.setVisibility(View.VISIBLE);
        etAmount.setText("");
        milkBagOperation = "B";
        patInfo = "";
        bagCode = "";
        bottleCode = "";
        tvPat.setText("");
        tvBottleCode.setText("");
        tvBag.setText("");
        imgBottle.setSelected(false);
        imgPat.setSelected(false);
        imgBag.setSelected(false);
        view2.setSelected(false);
        view1.setSelected(false);
        tvSure.setTextColor(getResources().getColor(R.color.patevents_device_color));
        tvScanTip.setText("请扫描奶袋条码");
        //        if(getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
        //            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //        }
    }

    private void getPatInfo(final String bagNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("bagNo", bagNo);
        map.put("wardId", spUtils.getString(SharedPreference.WARDID));
        MilkLoopApiManager.getMilkReceiveBagInfo(map, "getMilkBagInfo", new MilkLoopApiManager.MilkReceiveBagInfoCallback() {
            @Override
            public void onSuccess(MilkReceiveBagInfoBean milkReceiveBagInfoBean) {
                String bed = milkReceiveBagInfoBean.getPatInfo().getBedCode().equals("") ? "未分床" : milkReceiveBagInfoBean.getPatInfo().getBedCode();
                String name = milkReceiveBagInfoBean.getPatInfo().getPatName();
                tvPat.setText(milkReceiveBagInfoBean.getPatInfo().getRegNo() + "-" + bed + "-" + name);
                tvBag.setText(bagNo);
                patInfo = (milkReceiveBagInfoBean.getPatInfo().getRegNo());
                bagCode = bagNo;
                imgBag.setSelected(true);
                imgPat.setSelected(true);
                view1.setSelected(true);
                tvScanTip.setText("请扫描奶瓶条码");
                //                rlScan.setVisibility(View.GONE);
                //                etAmount.setText("");
                //                setToolbarRightCustomView(viewright);
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void getBottlingInfo(final String bottleNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("bagNo", bagCode);
        map.put("bottleNo", bottleNo);
        MilkLoopApiManager.getMilkBottlingInfo(map, "getBottlingInfo", new MilkLoopApiManager.MilkBottlingInfoCallback() {
            @Override
            public void onSuccess(MilkBottlingInfoBean milkBottlingInfoBean) {
                tvSure.setTextColor(getResources().getColor(R.color.blue));
                bottleCode = bottleNo;
                //                tvBottleCode.setText("装瓶（"+bottleNo+"）");
                llScan.setVisibility(View.GONE);
                tvLeft.setText(milkBottlingInfoBean.getBagInfo().getStoreAmount());
                milkAllAmount = Long.parseLong(milkBottlingInfoBean.getBagInfo().getStoreAmount());

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private class Receiver extends BaseReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Action.DEVICE_SCAN_CODE:
                    Bundle bundle = new Bundle();
                    bundle = intent.getExtras();
                    if (patInfo == "") {
                        getPatInfo(bundle.getString("data"));
                    } else if (bottleCode == "") {
                        getBottlingInfo(bundle.getString("data"));
                    }
                    break;
                default:
                    break;
            }
        }
    }


}
