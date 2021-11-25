package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.adapter.SureSoundTypeDialogAdapter;
import com.example.dhcc_nurlink.bean.BedMapBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SureSelectDialog extends Dialog {
    private Context context;
    private TextView tvTitle,tvMsg,tvSure,tvCancle;
    private List<TextView> textViews = new ArrayList<>();
    private RecyclerView recType;
    private SureSoundTypeDialogAdapter sureSoundTypeDialogAdapter;

    private String title="",msg="";
    private int cancleVisible = View.VISIBLE;

//    private TextView tvType1,tvType2,tvType3;


   private List<BedMapBean.TypeListBean> listBeans = new ArrayList<>();

    public void setListBeans(List<BedMapBean.TypeListBean> listBeans) {
        this.listBeans = listBeans;
        if (listBeans.size()>0){
            setSoundType(listBeans.get(0).getVDTCode());
            setSoundTypeDes(listBeans.get(0).getVDTDesc());
            listBeans.get(0).setSelect(1);
        }

    }

    private String soundType = "";
    private String soundTypeDes="";

    public void setSoundTypeDes(String soundTypeDes) {
        this.soundTypeDes = soundTypeDes;
    }

    public String getSoundTypeDes() {
        return soundTypeDes;
    }

    public void setSoundType(String soundType) {
        this.soundType = soundType;
    }

    public String getSoundType() {
        return soundType;
    }

    public void setCancleVisible(int cancleVisible){
        this.cancleVisible = cancleVisible;
        if (tvCancle!=null){
            tvCancle.setVisibility(cancleVisible);
        }
    }
    public void setTitle(String title) {
        this.title = title;
        if (tvTitle!=null){
            tvTitle.setText(title);
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        if (tvMsg!=null){
            tvMsg.setText(msg);
        }
    }

    public SureSelectDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sure_select_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据

        initData();
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvMsg = findViewById(R.id.tv_msg);
        tvSure = findViewById(R.id.tv_sure);
        tvCancle = findViewById(R.id.tv_cancle);
        tvSure.setOnClickListener(v -> callBack.sure());
        tvCancle.setOnClickListener(v -> callBack.cancle());

        recType = findViewById(R.id.rec_sound_type);
        recType.setHasFixedSize(true);
        recType.setLayoutManager(new GridLayoutManager(context,3));

        sureSoundTypeDialogAdapter = new SureSoundTypeDialogAdapter(new ArrayList<>());

        recType.setAdapter(sureSoundTypeDialogAdapter);
        sureSoundTypeDialogAdapter.setNewData(listBeans);
        initAdatper();

//        tvType1=findViewById(R.id.tv_type1);
//        tvType2=findViewById(R.id.tv_type2);
//        tvType3=findViewById(R.id.tv_type3);
//        tvType1.setSelected(true);
//        tvType1.setOnClickListener(v -> changeSelect(tvType1));
//        tvType2.setOnClickListener(v -> changeSelect(tvType2));
//        tvType3.setOnClickListener(v -> changeSelect(tvType3));
    }


    public void changeSelect(TextView view){
//        tvType1.setSelected(false);
//        tvType2.setSelected(false);
//        tvType3.setSelected(false);

        view.setSelected(true);
        setSoundType(view.getText().toString());
    }

    private void initAdatper(){
        sureSoundTypeDialogAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < listBeans.size(); i++) {
                    listBeans.get(i).setSelect(0);
                }
                listBeans.get(position).setSelect(1);
                sureSoundTypeDialogAdapter.setNewData(listBeans);
                setSoundType(listBeans.get(position).getVDTCode());
                setSoundTypeDes(listBeans.get(position).getVDTDesc());
            }
        });
    }

    private void initData(){
        if (!title.isEmpty()){
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (!msg.isEmpty()){
            tvMsg.setText(msg);
        }
        if (tvCancle!=null){
            tvCancle.setVisibility(cancleVisible);
        }
    }
    public CallBack callBack;
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }
    public interface CallBack{
        public void sure();
        public void cancle();
    }


}