package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.KeyBoardUtil;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdRecordAdapter;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.dhcc.module.nurse.task.bean.NurTaskSchBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:15:16
 */
public class NurOrdRecordFragment  extends BaseNurseFragment {

    private RecyclerView recNormalOrd;
    private TaskNurOrdRecordAdapter taskNurOrdRecordAdapter;
    private TextView tvPatinfo,tvTime,tvNur;
    private EditText tvDesc;
    private String recordId,interventionDR,patInfo,recordDesc="";
    private NurOrdRecordTaskBean recordTaskBean;
    private KeyboardView mKeyboard;
    private CheckBox ckNur;
    @Override
    protected void initDatas() {
        super.initDatas();
        hindMap();
        setHindBottm(50);
        setToolbarCenterTitle("录入");
        addToolBarRightTextView("保存", R.color.white);
        if (bundle != null) {
            recordId = bundle.getString("recordId");
            interventionDR = bundle.getString("interventionDR");
            patInfo = bundle.getString("patInfo");
            episodeId = bundle.getString("episodeId");
            tvPatinfo.setText(patInfo);
        }
        getExecuteTaskList();
    }

    @Override
    protected void initViews() {
        super.initViews();
        tvDesc = f(R.id.tv_record_desc,EditText.class);
        tvPatinfo = f(R.id.tv_patinfo,TextView.class);
        tvTime = f(R.id.tv_exec_time,TextView.class);
        tvNur = f(R.id.tv_exec_nur,TextView.class);
        ckNur = f(R.id.ck_nurrecord,CheckBox.class);
        mKeyboard = f(R.id.ky_keyboard,KeyboardView.class);

        recNormalOrd = RecyclerViewHelper.get(mContext, R.id.rv_list_ord);
        taskNurOrdRecordAdapter = AdapterFactory.getTaskNurOrdRecordAdapter();
        recNormalOrd.setAdapter(taskNurOrdRecordAdapter);
        taskNurOrdRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.tv_click){
                        String[] items = new String[taskNurOrdRecordAdapter.getData().get(position).getData().getNoteList().size()];
                        for (int i = 0; i < taskNurOrdRecordAdapter.getData().get(position).getData().getNoteList().size(); i++) {
                            items[i] = taskNurOrdRecordAdapter.getData().get(position).getData().getNoteList().get(i).toString();
                        }
                        if (items.length>0){
                            OptionPicker picker = new OptionPicker((Activity) mContext, items);
                            picker.setCanceledOnTouchOutside(false);
                            picker.setDividerRatio(WheelView.DividerConfig.FILL);
                            picker.setSelectedIndex(0);
                            picker.setCycleDisable(true);
                            picker.setTextSize(20);
                            picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                                @Override
                                public void onOptionPicked(int index, String item) {
                                    taskNurOrdRecordAdapter.getData().get(position).setNoteSelec(item);
                                    taskNurOrdRecordAdapter.notifyDataSetChanged();
                                }
                            });
                            picker.show();
                        }
                }
                if (view.getId()==R.id.tv_click1){
                    if (taskNurOrdRecordAdapter.getData().get(position).getData().getSubItemList().size()>0){

                    }
                }
                if (view.getId()==R.id.et_count){
                    EditText editText = (EditText) view;
                    if (taskNurOrdRecordAdapter.getData().get(position).getItemName().contains("℃")) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(mKeyboard, editText, editText.getId());
                        keyBoardUtil.setOngetNextFocusListener(new KeyBoardUtil.getNextFocusListener() {
                            @Override
                            public void getNextFocus(int id) {
                                editText.clearFocus();
                            }
                        });
                        keyBoardUtil.showKeyboard();
                    } else {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editText, 0);
                        mKeyboard.setVisibility(View.GONE);
                    }

                }
                refreshRecordDesc();
            }
        });
    }
    private void getExecuteTaskList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getExecuteTaskList( recordId,  interventionDR, new CommonCallBack<NurOrdRecordTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurOrdRecordTaskBean bean, String type) {
                hideLoadingTip();
                recordTaskBean= bean;
                recordDesc = bean.getExecuteTemplate();
                tvTime.setText(bean.getCurDate()+" "+bean.getCurTime());
                tvNur.setText(getSpInfo(SharedPreference.USERNAME));
                taskNurOrdRecordAdapter.setNewData(bean.getTaskSetList());
                refreshRecordDesc();
            }
        });
    }
    public static List<Integer> findAllIndex(String string,int index,String findStr){
        List<Integer> list =new ArrayList<>();
        if (index != -1){
            int num = string.indexOf(findStr,index);
            list.add(num);
            //递归进行查找
            List myList = findAllIndex(string,string.indexOf(findStr,num+1),findStr);
            list.addAll(myList);
        }
        return list;
    }

    private void refreshRecordDesc(){
        String strDesc = "";
        recordDesc = recordTaskBean.getExecuteTemplate();
        List<Integer> listFrom = findAllIndex(recordDesc,0,"[");
        List<Integer> listTo = findAllIndex(recordDesc,0,"]");

        for (int i = 0; i < taskNurOrdRecordAdapter.getData().size(); i++) {

            String  strIndex ="["+ (i+1)+"]";
            if (taskNurOrdRecordAdapter.getData().get(i).getWidgetType().equals("4")){
                if (taskNurOrdRecordAdapter.getData().get(i).getItemValue().length()>0){
                    String strItemSel = ":";
                    if (taskNurOrdRecordAdapter.getData().get(i).getNoteSelec()!=null){
                        strItemSel="("+taskNurOrdRecordAdapter.getData().get(i).getNoteSelec()+"):";
                    }
                    recordDesc = recordDesc.replace(strIndex,taskNurOrdRecordAdapter.getData().get(i).getItemValue());
                    strDesc=strDesc+taskNurOrdRecordAdapter.getData().get(i).getItemName()+strItemSel+taskNurOrdRecordAdapter.getData().get(i).getItemValue()+";  ";
                }
            }

            if (taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().size()>0){
                if (taskNurOrdRecordAdapter.getData().get(i).getWidgetType().equals("2") || taskNurOrdRecordAdapter.getData().get(i).getWidgetType().equals("1")){
                    String strSub = "";
                    for (int j = 0; j < taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().size(); j++) {
                        if (taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().get(j).getSubSelec().equals("1")){
                            strSub =strSub+taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().get(j).getSubItemName()+"、";
                        }
                    }
//                    if (strSub.contains("、")){
//                        strSub =strSub.substring(0,strSub.length()-1)+"等";
//                    }
//                    if (!strSub.equals("")){
//                        strSub = "有"+strSub+taskNurOrdRecordAdapter.getData().get(i).getItemName()+"的症状。  ";
//                    }
//                    strDesc = strDesc+strSub;

                    if (strSub.contains("、")){
                        strSub =strSub.substring(0,strSub.length()-1);
                    }
                    recordDesc = recordDesc.replace(strIndex,strSub);
                }
            }
            recordDesc = recordDesc.replace(strIndex,"");
        }
        tvDesc.setText(recordDesc);
    }
    private void executeNurTask(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.executeNurTask(recordId, tvDesc.getText().toString(), getTaskJson(), ckNur.isChecked()?"1":"", getRecordData(), new CommonCallBack<NurTaskSchBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(NurTaskSchBean bean, String type) {
                hideLoadingTip();
                showToast(bean.getMsg());
            }
        });
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_toolbar_right) {
            executeNurTask();
        }
    }
    // [{"itemId":450,"exeNoteList":[""],"itemValue":"","childItemList":[{"childId":"450||1","childValue":""},{"childId":"450||2","childValue":""}]}]
    private String getTaskJson(){
        List list = new ArrayList();
        if (taskNurOrdRecordAdapter.getData().size()>0){
            for (int i = 0; i < taskNurOrdRecordAdapter.getData().size(); i++) {
                NurOrdRecordTaskBean.TaskSetListBean bean = taskNurOrdRecordAdapter.getData().get(i);
                Map map = new HashMap();
                map.put("itemId", bean.getItemId());
                map.put("name", bean.getItemName());
                map.put("name", bean.getItemName());
                map.put("itemValue", bean.getItemValue());
                List list1 = new ArrayList();
                list1.add(bean.getNoteSelec() == null ? "" : bean.getNoteSelec());
                map.put("exeNoteList", list1);
                List list2 = new ArrayList();
                if (bean.getData().getSubItemList().size() > 0) {
                    for (int j = 0; j < bean.getData().getSubItemList().size(); j++) {
                        Map map1 = new HashMap();
                        map1.put("childId", bean.getData().getSubItemList().get(i).getSubItemId());
                        map1.put("childValue", bean.getData().getSubItemList().get(i).getSubItemName());
                        list2.add(map1);
                    }
                }
                map.put("childItemList",list2);
                list.add(map);
            }
        }
        return GsonUtils.toJson(list);
    }
    // {"episodeID":1471,"locID":151,"userID":10211,"exedate":"2020-08-17","exetime":"15:18","exeDesc":"进食中出现咳嗽或呛咳、口中存留多量唾液、等吞咽功能及障碍症状。","emrRecordId":"","record":[{"id":450,"name":"评估吞咽功能","value":"进食中出现咳嗽或呛咳,口中存留多量唾液"}],"exeDescFlag":true}
    private String getRecordData(){
        Map recordDataMap = new HashMap();
        recordDataMap.put("episodeID",episodeId);
        recordDataMap.put("locID",getSpInfo(SharedPreference.LOCID));
        recordDataMap.put("userID",getSpInfo(SharedPreference.USERID));
        recordDataMap.put("exedate",tvTime.getText().toString().substring(0,10));
        recordDataMap.put("exetime",tvTime.getText().toString().substring(11,16));
        recordDataMap.put("exeDesc",tvDesc.getText().toString());
        recordDataMap.put("emrRecordId",recordId);
        recordDataMap.put("record",getTaskJson());
        recordDataMap.put("exeDescFlag",ckNur.isChecked());
        return GsonUtils.toJson(recordDataMap);
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_task_nurordrecord;
    }
}
