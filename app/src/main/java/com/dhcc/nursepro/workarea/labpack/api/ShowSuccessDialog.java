package com.dhcc.nursepro.workarea.labpack.api;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * 创建自定义的dialog
     * Created by Q on 2018/3/22.
     */
    public class ShowSuccessDialog extends Dialog {

//        private Button yes;//确定按钮
//        private Button no;//取消按钮
//        private TextView titleTv;//消息标题文本
//        private TextView messageTv;//消息提示文本
        private String titleStr;//从外界设置的title文本
        private String messageStr;//从外界设置的消息文本
        private RecyclerView recbedset;
        //确定文本和取消文本的显示内容
        private String yesStr, noStr;


        Boolean b ;



        public ShowSuccessDialog(Context context) {
            super(context, R.style.MyDialog);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.labpacksuccess_dialog_layout);
            //按空白处不能取消动画
//            setCanceledOnTouchOutside(false);
            setCanceledOnTouchOutside(true);
            //初始化界面控件
        }

    }