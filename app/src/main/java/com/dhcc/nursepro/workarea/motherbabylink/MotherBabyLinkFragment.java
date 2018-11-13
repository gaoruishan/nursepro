package com.dhcc.nursepro.workarea.motherbabylink;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.workarea.motherbabylink.api.LinkApiManager;
import com.dhcc.nursepro.workarea.motherbabylink.bean.LinkResultBean;
import com.dhcc.nursepro.workarea.motherbabylink.bean.ScanMotherInfoBean;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.motherbabylink
 * <p>
 * author Q
 * Date: 2018/10/17
 * Time:9:41
 */
public class MotherBabyLinkFragment extends BaseFragment {

    private TextView tvMomMsg;
    private ImageView imgMom;
    private View viewLine;
    private ImageView imgBaby;
    private TextView tvBabyMsg;
    private TextView tvScan;


    private View viewright;
    private SPUtils spUtils = SPUtils.getInstance();
    private String motherEpisodeId = "";

    private LinkResultDialog linkResultDialog;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_motherbaby_link, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle(getString(R.string.title_motherbaby_link));

        //右上角按钮
        viewright = View.inflate(getActivity(), R.layout.view_save_blue_right, null);
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("保存");
//                saveData();
            }
        });
        setToolbarBottomLineVisibility(false);

        initView(view);

        mReceiver = new Receiver();
        getActivity().registerReceiver(mReceiver, mfilter);
    }

    private void initView(View view){
        tvMomMsg = view.findViewById(R.id.tv_motherbabylink_mother);
        imgMom = view.findViewById(R.id.icon_motherbabylink_mother);
        viewLine = view.findViewById(R.id.view_motherbabylink_mother);
        imgBaby = view.findViewById(R.id.icon_motherbabylink_baby);
        tvBabyMsg = view.findViewById(R.id.tv_motherbabylink_baby);
        tvScan = view.findViewById(R.id.tv_motherbabylink_scantips);

    }



    private void initScanMomMsg(String regNo) {

        cleanAll();
        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo", regNo);
        mapmsg.put("wardId", wardId);
        LinkApiManager.getMotherMsg(mapmsg, "getPatWristInfo", new LinkApiManager.GetMotherMsgCallBack() {
            @Override
            public void onSuccess(ScanMotherInfoBean scanMotherInfoBean) {
                ScanMotherInfoBean.PatInfoBean patInfoBean = scanMotherInfoBean.getPatInfo();
                motherEpisodeId = patInfoBean.getEpisodeID();
                String name = patInfoBean.getName();
                String age = patInfoBean.getAge();
                String roomDesc = patInfoBean.getRoomDesc();
                String motherRegNo = patInfoBean.getRegNo();
                tvMomMsg.setText(name+"-"+age+"-"+roomDesc+"-"+motherRegNo);
                imgMom.setSelected(true);
                viewLine.setSelected(true);
                tvScan.setText("请扫描婴儿腕带");

            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
                cleanAll();
            }
        });

    }

    private void getLinkMsg(String babyRegNo){
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("motherEpisodeId", motherEpisodeId);
        mapmsg.put("babyRegNo", babyRegNo);

        LinkApiManager.getLinkResultMsg(mapmsg, "motherRelationBaby", new LinkApiManager.GetLinkMsgCallback() {
            @Override
            public void onSuccess(LinkResultBean linkResultBean) {

                imgBaby.setSelected(true);
                tvBabyMsg.setText(linkResultBean.getPatInfo().getName()+"-"+linkResultBean.getPatInfo().getAge()+"-"+linkResultBean.getPatInfo().getRoomDesc()+"-"+linkResultBean.getPatInfo().getRegNo());


                if (linkResultDialog != null && linkResultDialog.isShowing()) {
                    linkResultDialog.dismiss();
                }

                linkResultDialog = new LinkResultDialog(getActivity());

                linkResultDialog.setExecresult("匹配成功");


                linkResultDialog.setImgId(R.drawable.icon_popup_sucess);
                linkResultDialog.setSureVisible(View.GONE);
                linkResultDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        linkResultDialog.dismiss();
                        motherEpisodeId = "";
                        tvScan.setText("扫描母亲腕带继续查询");
                    }
                }, 500);
            }

            @Override
            public void onFail(String code, String msg) {
                if (linkResultDialog != null && linkResultDialog.isShowing()) {
                    linkResultDialog.dismiss();
                }
                linkResultDialog = new LinkResultDialog(getActivity());
                linkResultDialog.setExecresult(msg);
                linkResultDialog.setImgId(R.drawable.icon_popup_error_patient);
                linkResultDialog.setSureVisible(View.VISIBLE);
                linkResultDialog.setSureOnclickListener(new LinkResultDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        linkResultDialog.dismiss();
                    }
                });
                linkResultDialog.show();
                cleanAll();
            }
        });
    }

    private void cleanAll(){
        motherEpisodeId = "";
        tvMomMsg.setText("");
        imgMom.setSelected(false);
        viewLine.setSelected(false);
        imgBaby.setSelected(false);
        tvBabyMsg.setText("");
        tvScan.setText("请扫描母亲腕带");

    }


    //扫描腕带获取regNo、wardId
    public class Receiver extends BaseReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Action.DEVICE_SCAN_CODE)) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                if (motherEpisodeId.equals("")){
                    initScanMomMsg(bundle.getString("data"));
                }else {
                    getLinkMsg(bundle.getString("data"));
                }


            }
        }
    }


}
