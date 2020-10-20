package com.dhcc.nursepro.workarea.nurrecordnew;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.adapter.KnowledgeTreeAdapter;
import com.dhcc.nursepro.workarea.nurrecordnew.api.NurRecordNewApiManager;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.NurRecordKnowledgeTreeBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.TreeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NurRecordQuoteDialog
 * 引用知识库弹窗
 *
 * @author DevLix126
 * created at 2020/9/22 16:39
 */


public class NurRecordQuoteDialog extends Dialog {
    private final Context mContext;
    private final ArrayList<MultiItemEntity> treeBeanList = new ArrayList<>();
    private RecyclerView recyKnowledgeTree;
    private EditText edKnowledgeContent;
    private TextView tvPopupSure;
    private TextView tvPopupCancle;
    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;
    private KnowledgeTreeAdapter knowledgeTreeAdapter;


    public NurRecordQuoteDialog(Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public String getKnowledgeContent() {
        return edKnowledgeContent.getText().toString();
    }

    public void setKnowledgeContent(String knowledgeContent) {
        if (edKnowledgeContent != null) {
            edKnowledgeContent.setText(knowledgeContent);
        }

    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setCancelOnclickListener(onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurrecordquote_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }


    private void initView() {

        recyKnowledgeTree = findViewById(R.id.recy_knowledgetree);
        edKnowledgeContent = findViewById(R.id.ed_knowledgecontent);

        //提高展示效率
        recyKnowledgeTree.setHasFixedSize(true);
        //设置的布局管理
        recyKnowledgeTree.setLayoutManager(new LinearLayoutManager(mContext));

        tvPopupSure = findViewById(R.id.tv_popup_sure);
        tvPopupCancle = findViewById(R.id.tv_popup_cancle);

    }

    private void initData() {
        NurRecordNewApiManager.getKnowledgeTree(new NurRecordNewApiManager.GetKnowledgeTreeCallback() {
            @Override
            public void onSuccess(NurRecordKnowledgeTreeBean knowledgeTreeBean, Map knowledgeMap) {
                generateData(treeBeanList, (List<Map>) knowledgeMap.get("knowledgeTree"), 0);

//                Toast.makeText(mContext, "遍历完成", Toast.LENGTH_SHORT).show();
                knowledgeTreeAdapter = new KnowledgeTreeAdapter(treeBeanList);

                recyKnowledgeTree.setAdapter(knowledgeTreeAdapter);
                knowledgeTreeAdapter.expandAll();
            }

            @Override
            public void onFail(String code, String msg) {
                Toast.makeText(mContext, "error" + code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvent() {
        tvPopupSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });

        tvPopupCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
    }

    private void generateData(List<MultiItemEntity> treeBeanListTemp, List<Map> knowledgeMapTree, int type) {

        for (int i = 0; knowledgeMapTree != null && i < knowledgeMapTree.size(); i++) {
            Map map = knowledgeMapTree.get(i);
            if ((map.get("children") == null)) {
                //最后一个节点
                TreeBean treeBean = new TreeBean(map.get("id").toString(), map.get("text").toString(), type);
                treeBeanListTemp.add(treeBean);
            } else {
                TreeBean treeBean = new TreeBean(map.get("id").toString(), map.get("text").toString(), type);
                treeBean.setSubItems(new ArrayList<>());
                treeBeanListTemp.add(treeBean);
                //包含子节点，递归遍历
                List<Map> knowledgeMapTreeChild = (List<Map>) map.get("children");
                generateData(treeBean.getSubItems(), knowledgeMapTreeChild, type + 1);
            }
        }
    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

}