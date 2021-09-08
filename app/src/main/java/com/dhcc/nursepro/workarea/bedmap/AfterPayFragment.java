package com.dhcc.nursepro.workarea.bedmap;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.bean.VoiceBean;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.adapter.AfterPayAdapter;
import com.dhcc.nursepro.workarea.bedmap.adapter.AfterPayNuredAdapter;
import com.dhcc.nursepro.workarea.bedmap.adapter.AfterPayPriceLIstAdapter;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiManager;
import com.dhcc.nursepro.workarea.bedmap.bean.AfterPayBean;
import com.dhcc.nursepro.workarea.bedmap.bean.NurOrdListBean;
import com.dhcc.nursepro.workarea.bedmap.bean.PdaArcListBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:9:46
 */
public class AfterPayFragment extends BaseFragment {

    private TextView tvWrite,tvList;
    private View viewright;
    private String epi= "",patInfo = "";
    private RecyclerView recNote,recNurArc,recNurArcTop,recNuredList;
    private AfterPayAdapter afterPayAdapter = new AfterPayAdapter(new ArrayList<>());
    private AfterPayNuredAdapter nuredAdapter = new AfterPayNuredAdapter(new ArrayList<>());
    private AfterPayPriceLIstAdapter afterPayPriceLIstAdapter = new AfterPayPriceLIstAdapter(new ArrayList<>());
    private List listAdd = new ArrayList<AfterPayBean>();
    private EditText etOrdName,etNum;
    private int nurArcNum = 1;
    private TextView tvPrice,tvTip,tvTip1,tvAddNote;
    private LinearLayout llArcList,llArcList1,llNured;
    private List<PdaArcListBean.ArcItemListBean> listArc = new ArrayList<>();
    private List<PdaArcListBean.ArcItemListBean> listArcAll = new ArrayList<>();
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_afterpay, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //状态栏 背景 默认蓝色
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        //toolbar type
        setToolbarType(BaseActivity.ToolbarType.TOP);
        //toolbar 背景 默认蓝色
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        Bundle bundle = getArguments();
        epi = bundle.getString("episodeId");
        patInfo = bundle.getString("patInfo");
        setToolbarCenterTitle(patInfo,getResources().getColor(R.color.blue),17);
        setToolbarBottomLineVisibility(true);
        //右上角保存按钮
        viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.blue));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAfterPay();
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initData();
        setScene("费用补录");
    }

    private void initView(View view) {
        recNote = view.findViewById(R.id.recy_note);
        recNote.setHasFixedSize(true);
        recNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNote.setAdapter(afterPayAdapter);

        recNurArc = view.findViewById(R.id.recy_nurlist);
        recNurArc.setHasFixedSize(true);
        recNurArc.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNurArc.setAdapter(afterPayPriceLIstAdapter);


        recNurArcTop = view.findViewById(R.id.recy_nurlist1);
        recNurArcTop.setHasFixedSize(true);
        recNurArcTop.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNurArcTop.setAdapter(afterPayPriceLIstAdapter);

        afterPayPriceLIstAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                etOrdName.setText(afterPayPriceLIstAdapter.getData().get(position).getItemDesc());
            }
        });

        recNuredList = view.findViewById(R.id.rec_nured_list);
        recNuredList.setHasFixedSize(true);
        recNuredList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recNuredList.setAdapter(nuredAdapter);

        tvWrite = view.findViewById(R.id.tv_afterpay_whrite);
        tvWrite.setSelected(true);
        tvList = view.findViewById(R.id.tv_afterpay_list);
        tvWrite.setOnClickListener(v -> setAfterPay());
        tvList.setOnClickListener(v -> getAfterPay());
        llNured = view.findViewById(R.id.ll_nured);

        afterPayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_message_rightmenu){
                    List<PdaArcListBean.ArcItemListBean> listBeans = afterPayAdapter.getData();
                    listBeans.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        etOrdName = view.findViewById(R.id.et_ord_name);
        etNum = view.findViewById(R.id.et_num);
        tvPrice = view.findViewById(R.id.tv_price);
        tvTip = view.findViewById(R.id.tv_tip);
        tvTip1 = view.findViewById(R.id.tv_tip1);


        llArcList = view.findViewById(R.id.ll_afterpay_nurlist);
        llArcList1 = view.findViewById(R.id.ll_afterpay_nurlist1);
        llArcList.setVisibility(View.GONE);
        llArcList1.setVisibility(View.GONE);
        etOrdName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterPayAdapter.getData().size()>3){
                    llArcList.setVisibility(View.GONE);
                    if (!etOrdName.getText().toString().isEmpty()){
                        llArcList1.setVisibility(View.VISIBLE);
                    }else {
                        llArcList1.setVisibility(View.GONE);
                    }
                }else {
                    llArcList1.setVisibility(View.GONE);
                    if (!etOrdName.getText().toString().isEmpty()){
                        llArcList.setVisibility(View.VISIBLE);
                    }else {
                        llArcList.setVisibility(View.GONE);
                    }
                }

                arcListFilter(etOrdName.getText().toString());
                Boolean isCotain = false;
                //判断输入的内容是否存在
                for (int i = 0; i < listArcAll.size(); i++) {
                    if (listArcAll.get(i).getItemDesc().equals(etOrdName.getText().toString())){
                        isCotain = true;
                        try {
                            Double l1 = Double.valueOf(listArcAll.get(i).getItemPrice());
                            tvPrice.setText(l1*nurArcNum+"");
                        }catch (Exception e){
                            Log.e(TAG, "afterTextChanged: "+e );
                        }
                    }
                }
                if (!isCotain){
                    tvPrice.setText("");
                }

            }
        });

        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etNum.getText().toString().length()>0){
                    nurArcNum = Integer.parseInt(etNum.getText().toString());
                }else {
                    nurArcNum = 1;
                }
                for (int i = 0; i < listArcAll.size(); i++) {
                    if (listArcAll.get(i).getItemDesc().equals(etOrdName.getText().toString())){
                        try {
                            Double l1 = Double.valueOf(listArcAll.get(i).getItemPrice());
                            tvPrice.setText(l1*nurArcNum+"");
                        }catch (Exception e){
                            Log.e(TAG, "afterTextChanged: "+e );
                        }
                    }
                }

            }
        });

        tvAddNote = view.findViewById(R.id.tv_add_note);
        tvAddNote.setOnClickListener(v -> addArcToList());
        view.findViewById(R.id.view_click3).setOnClickListener(v -> hindList());
        view.findViewById(R.id.view_click4).setOnClickListener(v -> hindList());
        view.findViewById(R.id.ll_afterpay_nurlist1).setOnClickListener(v -> hindList());
        view.findViewById(R.id.view_showlist).setOnClickListener(v -> showList());
        recNote.setOnClickListener(v ->hindList() );
    }
    private void hindList(){
        llArcList1.setVisibility(View.GONE);
        llArcList.setVisibility(View.GONE);
    }
    private void showList(){
        if (afterPayAdapter.getData().size()>3){
            if (llArcList1.getVisibility() == View.VISIBLE){
                llArcList1.setVisibility(View.GONE);
            }else {
                llArcList1.setVisibility(View.VISIBLE);
            }
        }else {
            if (llArcList.getVisibility() == View.VISIBLE){
                llArcList.setVisibility(View.GONE);
            }else {
                llArcList.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initData(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap<String,String>();
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        map.put("groupId", SPUtils.getInstance().getString(SharedPreference.GROUPID));
        map.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        map.put("episodeId", epi);
        map.put("desc", "");

        BedMapApiManager.getArcList(map, "getPdaArcList", new BedMapApiManager.GetArcListCallback() {
            @Override
            public void onSuccess(PdaArcListBean pdaArcListBean) {
                hideLoadingTip();
                listArcAll = pdaArcListBean.getArcItemList();
                afterPayPriceLIstAdapter.setNewData(pdaArcListBean.getArcItemList());
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }
        });
    }


//    @Override
//    public void getTempResultByVoice(Bundle bundle) {
//        super.getTempResultByVoice(bundle);
////        etOrdName.setText(bundle.getString("results"));
//
//    }
    @Override
    public void getVoiceResult(VoiceBean voiceBean) {
        if (voiceBean!=null&&
                voiceBean.getForm()!=null&&
                voiceBean.getForm().getData()!=null&&
                voiceBean.getForm().getData().size()>0){

            List<PdaArcListBean.ArcItemListBean> listBeans = new ArrayList<>();
            listBeans = afterPayAdapter.getData();
            try {
                for (int i = 0; i < voiceBean.getForm().getData().size(); i++) {
                    Boolean isItem = false;
                    //判断该字段在已加入的列表里是否存在
                    for (int j = 0; j < listBeans.size(); j++) {
                        if (voiceBean.getForm().getData().get(i).getKey().equals(listBeans.get(j).getItemDesc())){
                            isItem = true;
                            listBeans.get(j).setItemNum(Integer.parseInt(voiceBean.getForm().getData().get(i).getValue()));
                            afterPayAdapter.notifyItemChanged(j);

                        }
                    }
                    if (!isItem){
                        Boolean isVoiceItem = true;
                        //判断读出的文字是在后台否存在，
                        for (int j = 0; j < listArcAll.size(); j++) {
                            if (listArcAll.get(j).getItemDesc().equals(voiceBean.getForm().getData().get(i).getKey())){
                                PdaArcListBean.ArcItemListBean bean = new PdaArcListBean.ArcItemListBean();
                                bean.setItemDesc(listArcAll.get(j).getItemDesc());
                                if (voiceBean.getForm().getData().get(i).getValue().isEmpty()){
//                                    bean.setItemNum(1);
                                }else {
                                    bean.setItemNum(Integer.parseInt(voiceBean.getForm().getData().get(i).getValue()));
                                    bean.setItemDur(listArcAll.get(j).getItemDur()+"");
                                    bean.setItemPrice(listArcAll.get(j).getItemPrice()+"");
                                    addArcByVoice(bean);
                                }

//                                bean.setItemDur(listArcAll.get(j).getItemDur()+"");
//                                bean.setItemPrice(listArcAll.get(j).getItemPrice()+"");
//                                addArcByVoice(bean);
                            }else {
//                                showToast("bbb");
//                                etOrdName.setText(voiceBean.getForm().getData().get(i).getKey());
//                                etNum.setText(voiceBean.getForm().getData().get(i).getValue());
                            }
                        }
                    }
                }
            }catch (Exception e){
                Log.e(TAG, "getVoiceResult: "+e.toString() );
            }

            Log.i("onResult json4","jsonResult:"+voiceBean.getForm().getData().size());
//            afterPayAdapter.setNewData(listBeans);
//            addArcByVoice(listBeans);
        }else if (voiceBean!=null&&voiceBean.getLast()){
            String bedNoStr = "";
            if (voiceBean.getCommand().getBedNo() != null){
                bedNoStr = voiceBean.getCommand().getBedNo();
            }
            String actionStr = "";
            if (voiceBean.getCommand().getAction() != null){
                actionStr = voiceBean.getCommand().getAction();
            }
            int tempIndex = 0;
//            if (actionStr.equals("生命体征")&&(!bedNoStr.isEmpty())){
//                actionStr = "";
//            }
            if (actionStr.isEmpty()&&(!bedNoStr.isEmpty())){
                Boolean isBed = false;
//                for (int i = 0; i < patientList.size(); i++) {
//                    if (bedNoStr.equals(patientList.get(i).get("bedCode"))) {
//                        tempIndex = i;
//                        isBed = true;
//                        break;
//                    }
//                }
                if (isBed){
//                    patientIndex = tempIndex;
//                    saveTempValue(SAVE_TEMP_VALUE_SCAN);
                }else {
                    showToast(bedNoStr+"床位不存在");
                }

            }else {
                if (actionStr.equals("保存")){
                    saveAfterPay();
                }else if (actionStr.equals("清屏")){
                    
                }else {
                    voiceUtil.startFragmentByVoice(bedNoStr, actionStr);
                }
            }
        }

    }

    private void setAfterPay(){
        setScene("费用补录");
        tvAddNote.setVisibility(View.VISIBLE);
        llNured.setVisibility(View.GONE);
        viewright.setVisibility(View.VISIBLE);
        afterPayAdapter.setAdd(true);
        tvWrite.setSelected(true);
        tvList.setSelected(false);
        afterPayAdapter.setNewData(listAdd);
    }
    private void arcListFilter(String inputMsg){
        List<PdaArcListBean.ArcItemListBean> tempArcList = new ArrayList<>();
        for (int i = 0; i <listArcAll.size() ; i++) {
            if (listArcAll.get(i).getItemDesc().contains(inputMsg)){
                tempArcList.add(listArcAll.get(i));
            }
        }
        afterPayPriceLIstAdapter.setNewData(tempArcList);
        if (tempArcList.size()>0){
            tvTip.setVisibility(View.GONE);
            tvTip1.setVisibility(View.GONE);
        }else {
            tvTip.setVisibility(View.VISIBLE);
            tvTip1.setVisibility(View.VISIBLE);
        }
    }

    private void getAfterPay(){
        setScene("补录记录");
        tvAddNote.setVisibility(View.GONE);
        llNured.setVisibility(View.VISIBLE);
        viewright.setVisibility(View.GONE);
        afterPayAdapter.setAdd(false);
        tvWrite.setSelected(false);
        tvList.setSelected(true);
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap<String,String>();
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        map.put("episodeId", epi);

        BedMapApiManager.getNurOrdList(map, "getNurOrdList", new BedMapApiManager.GetNurOrdListCallback() {
            @Override
            public void onSuccess(NurOrdListBean nurOrdListBean) {
                hideLoadingTip();
                nuredAdapter.setNewData(nurOrdListBean.getOrdList());
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }
        });
    }
    private void saveAfterPay(){
//        7809||1^1^itemDurRowid^itemFreqRowid^!
        String saveItems = "";
        if (afterPayAdapter.getData().size()<1){
            showToast("未录入内容，不可保存");
            return;
        }
        for (int i = 0; i < afterPayAdapter.getData().size(); i++) {
            String ordId = "",ordNum = "",itemDurRowid="",itemFreqRowid="";
            for (int j = 0; j < listArcAll.size(); j++) {
                if (listArcAll.get(j).getItemDesc().equals(afterPayAdapter.getData().get(i).getItemDesc())){
                    ordId = listArcAll.get(j).getItemId();
                    itemDurRowid = listArcAll.get(j).getItemDurRowid();
                    itemFreqRowid = listArcAll.get(j).getItemFreqRowid();
                }
            }
            ordNum= afterPayAdapter.getData().get(i).getItemNum()+"" ;
            saveItems = saveItems+ordId+"^"+ordNum+"^"+itemDurRowid+"^"+itemFreqRowid+"^!";
        }
        if (saveItems.contains("!")){
            saveItems = saveItems.substring(0,saveItems.length()-1);
        }
        showLoadingTip(BaseActivity.LoadingType.FULL);
        HashMap map = new HashMap<String,String>();
        map.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        map.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        map.put("episodeId", epi);
        map.put("parr", saveItems);

        BedMapApiManager.getArcList(map, "saveOrderItems", new BedMapApiManager.GetArcListCallback() {
            @Override
            public void onSuccess(PdaArcListBean pdaArcListBean) {
                hideLoadingTip();
                showToast("保存成功");
                afterPayAdapter.setNewData(new ArrayList<>());
                afterPayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error:"+msg);
            }
        });

    }



    private void addArcByVoice(PdaArcListBean.ArcItemListBean arcItemListBean){
        listArc = new ArrayList<>();
        listArc = afterPayAdapter.getData();
        listArc.add(arcItemListBean);

        Log.i("onResult json56","jsonResult:"+new Gson().toJson(listArc));
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                afterPayAdapter.setNewData(listArc);
            }
        }, 300);
        etOrdName.setText("");
        etNum.setText("");
    }
    private void addArcToList(){
        if (etOrdName.getText().toString().isEmpty()){
            showToast("请输入补录项目");
            return;
        }
        for (int i = 0; i < afterPayAdapter.getData().size(); i++) {
            if (afterPayAdapter.getData().get(i).getItemDesc().equals(etOrdName.getText().toString())){
                showToast("该项已录");
                return;
            }
        }
        Boolean ifItem = false;
        String itemPrice="";
        for (int i = 0; i < listArcAll.size(); i++) {
            if (listArcAll.get(i).getItemDesc().equals(etOrdName.getText().toString())){
                ifItem = true;
                itemPrice = listArcAll.get(i).getItemPrice();
            }
        }

        if (!ifItem){
            showToast("该项目不存在");
            return;
        }

        PdaArcListBean.ArcItemListBean arcItemListBean = new PdaArcListBean.ArcItemListBean();
        arcItemListBean.setItemDesc(etOrdName.getText().toString());
        arcItemListBean.setItemNum(nurArcNum);
        arcItemListBean.setItemPrice(itemPrice);
        listArc = new ArrayList<>();
        listArc = afterPayAdapter.getData();
        listArc.add(arcItemListBean);
        afterPayAdapter.setNewData(listArc);
        etOrdName.setText("");
        etNum.setText("");
    }

}

