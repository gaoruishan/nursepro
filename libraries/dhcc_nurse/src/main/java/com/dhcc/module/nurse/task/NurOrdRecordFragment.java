package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.KeyBoardUtil;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdRecordAdapter;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
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
    private TextView tvDesc;
    private String recordId,interventionDR;
    private KeyboardView mKeyboard;
    @Override
    protected void initDatas() {
        super.initDatas();
        setHindBottm(50);
        setToolbarCenterTitle("护嘱任务执行");
        if (bundle != null) {
            recordId = bundle.getString("recordId");
            interventionDR = bundle.getString("interventionDR");
        }
        getExecuteTaskList();
    }

    @Override
    protected void initViews() {
        super.initViews();
        tvDesc = f(R.id.tv_record_desc,TextView.class);
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
                taskNurOrdRecordAdapter.setNewData(bean.getTaskSetList());
                refreshRecordDesc();
            }
        });
    }

    private void refreshRecordDesc(){
        String strDesc = "";
        for (int i = 0; i < taskNurOrdRecordAdapter.getData().size(); i++) {
            if (taskNurOrdRecordAdapter.getData().get(i).getWidgetType().equals("4")){
                if (taskNurOrdRecordAdapter.getData().get(i).getItemValue().length()>0){
                    String strItemSel = ":";
                    if (taskNurOrdRecordAdapter.getData().get(i).getNoteSelec()!=null){
                        strItemSel="("+taskNurOrdRecordAdapter.getData().get(i).getNoteSelec()+"):";
                    }
                    strDesc=strDesc+taskNurOrdRecordAdapter.getData().get(i).getItemName()+strItemSel+taskNurOrdRecordAdapter.getData().get(i).getItemValue()+";  ";
                }
            }

            if (taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().size()>0){
                if (taskNurOrdRecordAdapter.getData().get(i).getWidgetType().equals("2")){
                    String strSub = "";
                    for (int j = 0; j < taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().size(); j++) {
                        if (taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().get(j).getSubSelec().equals("1")){
                            strSub =strSub+taskNurOrdRecordAdapter.getData().get(i).getData().getSubItemList().get(j).getSubItemName()+"、";
                        }
                    }
                    if (strSub.contains("、")){
                        strSub =strSub.substring(0,strSub.length()-1)+"等";
                    }
                    if (!strSub.equals("")){
                        strSub = "有"+strSub+taskNurOrdRecordAdapter.getData().get(i).getItemName()+"的症状。  ";
                    }
                    strDesc = strDesc+strSub;

                }
            }
        }
        tvDesc.setText(strDesc);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right1) {
            setMaskShow();
        }
        if (v.getId() == R.id.img_toolbar_right2) {
            try {
                Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.bedselect.BedSelectFragment");
                startFragment(BedFragmentClass,1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_nurordrecord;
    }
}
