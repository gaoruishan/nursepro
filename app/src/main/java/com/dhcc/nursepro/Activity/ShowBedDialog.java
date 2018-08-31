package com.dhcc.nursepro.Activity;


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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.checkresult.adapter.CheckPatListAdapter;
import com.dhcc.nursepro.workarea.labresult.api.LabApiManager;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * 创建自定义的dialog
     * Created by Q on 2018/3/22.
     */
    public class ShowBedDialog extends Dialog {

        private Button yes;//确定按钮
        private Button no;//取消按钮
        private TextView titleTv;//消息标题文本
        private TextView messageTv;//消息提示文本
        private String titleStr;//从外界设置的title文本
        private String messageStr;//从外界设置的消息文本
        private RecyclerView recbedset;
        //确定文本和取消文本的显示内容
        private String yesStr, noStr;

        private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
        private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

        private List<Map<String,String>>  list = new ArrayList<Map<String,String>>();;
        private BedSetListAdapter bedSetListAdapter;

        Boolean b ;

        /**
         * 设置取消按钮的显示内容和监听
         *
         * @param str
         * @param onNoOnclickListener
         */
        public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
            if (str != null) {
                noStr = str;
            }
            this.noOnclickListener = onNoOnclickListener;
        }

        /**
         * 设置确定按钮的显示内容和监听
         *
         * @param str
         * @param onYesOnclickListener
         */
        public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
            if (str != null) {
                yesStr = str;
            }
            this.yesOnclickListener = onYesOnclickListener;
        }

        public ShowBedDialog(Context context) {
            super(context, R.style.MyDialog);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bed_set_dialog_layout);
            //按空白处不能取消动画
//            setCanceledOnTouchOutside(false);
            setCanceledOnTouchOutside(true);
            //初始化界面控件
            initView();
            //初始化界面数据
            initData();
            //初始化界面控件的事件
            initEvent();

        }

        /**
         * 初始化界面的确定和取消监听器
         */
        private void initEvent() {
            //设置确定按钮被点击后，向外界提供监听
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (yesOnclickListener != null) {
                        yesOnclickListener.onYesClick();
                    }
                }
            });
            //设置取消按钮被点击后，向外界提供监听
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noOnclickListener != null) {
                        noOnclickListener.onNoClick();
                    }
                }
            });
        }

        /**
         * 初始化界面控件的显示数据
         */
        private void initData() {
            //如果用户自定了title和message
            if (titleStr != null) {
                titleTv.setText(titleStr);
            }
            if (messageStr != null) {
                messageTv.setText(messageStr);
            }
            //如果设置按钮的文字
            if (yesStr != null) {
                yes.setText(yesStr);
            }
            if (noStr != null) {
                no.setText(noStr);
            }

            Map<String,String> m = new HashMap<String,String>();
            m.put("bedNo","全选");
            m.put("ck","true");
            for (int i = 0;i<list.size();i++){
                if (list.get(i).get("ck").equals("false")){
                    m.put("ck","false");
                }
            }
            list.add(m);
            bedSetListAdapter.setNewData(list);
            bedSetListAdapter.notifyDataSetChanged();

        }

        /**
         * 初始化界面控件
         */
        private void initView() {
            yes = (Button) findViewById(R.id.yes);
            no = (Button) findViewById(R.id.no);
            titleTv = (TextView) findViewById(R.id.title);
            messageTv = (TextView) findViewById(R.id.message);
            recbedset = findViewById(R.id.rec_bed_set);



            recbedset.setHasFixedSize(true);
            recbedset.setLayoutManager(new LinearLayoutManager(getContext()));
            bedSetListAdapter = new BedSetListAdapter(new ArrayList<Map<String, String>>());
//            bedSetListAdapter.setOnItemClickListener(this);
            recbedset.setAdapter(bedSetListAdapter);

        }

        /**
         * 从外界Activity为Dialog设置标题
         *
         * @param title
         */
        public void setTitle(String title) {
            titleStr = title;
        }

        /**
         * 从外界Activity为Dialog设置dialog的message
         *
         * @param message
         */
        public void setMessage(String message) {
            messageStr = message;
        }

        public void setList(List<Map<String,String>> list){
            this.list = list;
        }
        public List<Map<String,String>> getlist(){
            return this.list;
        }
//    public void changebedset(){
//
//        HashMap<String,String> map = new HashMap<String, String>();
//        map.put("episodeId","96");
//        LabApiManager.getLabListMsg(map, "getLabOrdList", new LabApiManager.GeLabListCallback() {
//            @Override
//            public void onSuccess(LabResultListBean labResultListBean) {
//                b = true;
//            }
//
//            @Override
//            public void onFail(String code, String msg) {
//                b = false;
//
//            }
//        });
//    }

        /**
         * 设置确定按钮和取消被点击的接口
         */
        public interface onYesOnclickListener {
            public void onYesClick();
        }

        public interface onNoOnclickListener {
            public void onNoClick();
        }

    public class BedSetListAdapter extends BaseQuickAdapter<Map<String,String>,BaseViewHolder> {

        public BedSetListAdapter(@Nullable List<Map<String,String>> data) {
            super(R.layout.item_bed_set,data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final Map<String,String> item) {

            helper.setText(R.id.tv_bedno_set,item.get("bedNo"));
            final CheckBox ck = helper.getView(R.id.ck_bed_set);
            if (item.get("ck").equals("true")){
                ck.setChecked(true);
            }else {
                ck.setChecked(false);
            }
            ck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("bedNo", list.get(helper.getLayoutPosition()).get("bedNo"));
                    map.put("ck",ck.isChecked()+"");
                    list.set(helper.getPosition(),map);

                   if (helper.getPosition()+1 == list.size()){
                           for (int i = 0;i<list.size();i++){
                               Map<String,String> maptemp = new HashMap<String, String>();
                               maptemp.put("ck",ck.isChecked()+"");
                               maptemp.put("bedNo",list.get(i).get("bedNo"));
                               list.set(i,maptemp);
                           }
                       bedSetListAdapter.setNewData(list);
                       bedSetListAdapter.notifyDataSetChanged();
                       }

                   }

            });


//        TextView tvresult = helper.getView(R.id.tv_checklist_result);
//        TextView tvreport = helper.getView(R.id.tv_checklist_report);
//        if (item.getReportStat().equals("Y")){
//            tvreport.setTextColor(mContext.getResources().getColor(R.color.blue));
//            tvresult.setText("已出");
//            tvresult.setBackground(mContext.getResources().getDrawable(R.drawable.bg_cheklab_getresult));
//        }else {
//            tvreport.setTextColor(mContext.getResources().getColor(R.color.lab_un_report));
//            tvresult.setBackground(mContext.getResources().getDrawable(R.drawable.bg_checklab_noresult));
//            tvresult.setText("未出");
            TextView tv = helper.getView(R.id.tv_bedno_set);
            if (item.equals("全选")){
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        list.add("---");
//                        bedSetListAdapter.setNewData(list);
                        bedSetListAdapter.notifyDataSetChanged();
                    }
                });
            }
//        }

        }
    }
    }