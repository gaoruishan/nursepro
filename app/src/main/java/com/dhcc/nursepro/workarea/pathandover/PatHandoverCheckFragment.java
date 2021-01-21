package com.dhcc.nursepro.workarea.pathandover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.NurRecordNewFragment;
import com.dhcc.nursepro.workarea.pathandover.api.PatHandoverApiManager;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectAndPatBean;
import com.dhcc.nursepro.workarea.pathandover.bean.GetNurseInfoBean;
import com.dhcc.nursepro.workarea.pathandover.bean.SaveConnectSubBean;
import com.dhcc.nursepro.workarea.pathandover.dialog.PathandoverConfirmDialog;

import java.util.ArrayList;
import java.util.List;


public class PatHandoverCheckFragment extends BaseFragment {
    private Spinner spCheckSubtype;
    private TextView tvPathandoverRegno;
    private TextView tvPathandoverPatname;
    private TextView tvPathandoverSex;
    private TextView tvPathandoverAge;
    private TextView tvPathandoverLoc;
    private TextView tvPathandoverWard;
    private TextView tvPathandoverBedno;
    private TextView tvPathandoverCarryusersign;
    private TextView tvPathandoverReceiveusersign;


    private String regNo = "";
    private String type = "";
    private String typeCode = "";
    private String parentTypeCode = "";
    private GetConnectAndPatBean getConnectAndPatBean;
    private List<String> connectSubType = new ArrayList<>();
    private String subType = "";
    private String subTypeCode = "";

    private String firstUser = "";
    private String secondUser = "";
    private String thirdUser = "";
    private PathandoverConfirmDialog confirmDialog;
    private SPUtils spUtils = SPUtils.getInstance();
    private GetConnectAndPatBean.PatInfoBean patInfo;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_patienthandover), 0xffffffff, 17);

        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("  保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConnectSub();
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            regNo = bundle.getString("RegNo");
            type = bundle.getString("Type");
            typeCode = bundle.getString("TypeCode");
            parentTypeCode = bundle.getString("ParentTypeCode");
            getConnectAndPatBean = (GetConnectAndPatBean) bundle.getSerializable("GetConnectAndPatBean");
            String[] subTypeStr = getConnectAndPatBean.getAllChildRec().split("\\^");
            connectSubType.clear();
            for (int i = 0; i < subTypeStr.length; i++) {
                connectSubType.add(subTypeStr[i]);
                if (getConnectAndPatBean.getChildRec().equals(subTypeStr[i].split(":")[1])) {
                    subType = subTypeStr[i].split(":")[0];
                }
            }
            initAdapter();
            initData();
        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_handover_check, container, false);
    }

    private void saveConnectSub() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.saveConnectSub(parentTypeCode, regNo, subTypeCode, firstUser, secondUser, thirdUser, new PatHandoverApiManager.SaveConnectSubCallback() {
            @Override
            public void onSuccess(SaveConnectSubBean saveConnectSubBean) {
                hideLoadingTip();
                showToast(saveConnectSubBean.getMsg());
                jumpToNurRecord();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void initView(View view) {
        spCheckSubtype = view.findViewById(R.id.sp_checksubtype);
        tvPathandoverRegno = view.findViewById(R.id.tv_pathandover_regno);
        tvPathandoverPatname = view.findViewById(R.id.tv_pathandover_patname);
        tvPathandoverSex = view.findViewById(R.id.tv_pathandover_sex);
        tvPathandoverAge = view.findViewById(R.id.tv_pathandover_age);
        tvPathandoverLoc = view.findViewById(R.id.tv_pathandover_loc);
        tvPathandoverWard = view.findViewById(R.id.tv_pathandover_ward);
        tvPathandoverBedno = view.findViewById(R.id.tv_pathandover_bedno);

        tvPathandoverCarryusersign = view.findViewById(R.id.tv_pathandover_carryusersign);
        tvPathandoverReceiveusersign = view.findViewById(R.id.tv_pathandover_receiveusersign);
        tvPathandoverReceiveusersign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDiaolg();
            }
        });
    }

    private void initAdapter() {
        List<String> spiList = new ArrayList<>();
        for (int i = 0; i < connectSubType.size(); i++) {
            spiList.add(connectSubType.get(i).split(":")[0]);
        }

        ArrayAdapter<String> arr_adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spiList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return setCentered(super.getView(position, convertView, parent));
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return setCentered(super.getDropDownView(position, convertView, parent));
            }

            private View setCentered(View view) {
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextSize(18);
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setGravity(Gravity.CENTER);
                return view;
            }
        };
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCheckSubtype.setAdapter(arr_adapter);
        if (spiList.size() > 0) {
            spCheckSubtype.setSelection(0);
        }

        for (int i = 0; i < connectSubType.size(); i++) {
            if (subType.equals(connectSubType.get(i))) {
                spCheckSubtype.setSelection(i);
            }
        }
        spCheckSubtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spCheckSubtype.setSelection(position);
                subTypeCode = connectSubType.get(position).split(":")[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData() {
        patInfo = getConnectAndPatBean.getPatInfo();

        tvPathandoverRegno.setText(patInfo.getRegNo());
        tvPathandoverPatname.setText(patInfo.getName());
        tvPathandoverSex.setText(patInfo.getSex());
        tvPathandoverAge.setText(patInfo.getAge());
        tvPathandoverLoc.setText(patInfo.getCtLocDesc());
        tvPathandoverWard.setText(patInfo.getWardDesc());
        tvPathandoverBedno.setText(patInfo.getBedCode());

        tvPathandoverCarryusersign.setText(spUtils.getString(SharedPreference.USERNAME));
        firstUser = spUtils.getString(SharedPreference.USERID);
    }

    private void jumpToNurRecord() {

        Bundle bundle = new Bundle();
        bundle.putString("EpisodeID", patInfo.getEpisodeID());
        bundle.putString("BedNo", patInfo.getBedCode());
        bundle.putString("PatName", patInfo.getName());
        bundle.putString("EMRCode", getConnectAndPatBean.getEmrCode());
        bundle.putString("GUID", getConnectAndPatBean.getGuid());
        bundle.putString("RecID", StringUtils.isEmpty(getConnectAndPatBean.getNurEmrId()) ? "" : getConnectAndPatBean.getNurEmrId());
        startFragment(NurRecordNewFragment.class, bundle);

    }

    private void showConfirmDiaolg() {
        confirmDialog = new PathandoverConfirmDialog(getActivity());
        confirmDialog.setSureOnclickListener(new PathandoverConfirmDialog.onSureOnclickListener() {
            @Override
            public void onSureClick() {
                confirmDialog.dismiss();
                getNurseInfo(confirmDialog.getNurName(), confirmDialog.getNurPass());
            }
        });

        confirmDialog.setCancelOnclickListener(new PathandoverConfirmDialog.onCancelOnclickListener() {
            @Override
            public void onCancelClick() {
                confirmDialog.dismiss();
            }
        });
        confirmDialog.show();
    }

    private void getNurseInfo(String nurName, String nurPass) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        PatHandoverApiManager.getNurseInfo(nurName, nurPass, new PatHandoverApiManager.getNurseInfoCallback() {
            @Override
            public void onSuccess(GetNurseInfoBean getNurseInfoBean) {
                hideLoadingTip();
                tvPathandoverReceiveusersign.setText(getNurseInfoBean.getNurseName());
                secondUser = getNurseInfoBean.getUserId();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }
}