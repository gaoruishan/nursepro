package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.constant.Action;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.PatIconView;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

/**
 * BedMapPatientAdapter
 * 床位图-患者adapter
 *
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapPatientAdapter extends BaseQuickAdapter<BedMapBean.PatInfoListBean, BaseViewHolder> {

    public BedMapPatientAdapter(@Nullable List<BedMapBean.PatInfoListBean> data) {
        super(R.layout.item_bedmap_patient, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.PatInfoListBean item) {
        helper.setText(R.id.tv_bedmap_patient_bedno, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode().replaceAll("床","")+"床")
                .setText(R.id.tv_bedmap_patient_name, item.getName())
                .setText(R.id.tv_bedmap_patient_carelevel,item.getCareLevel());
        View line = helper.getView(R.id.line_bedmap_patient);
        ImageView patientSex = helper.getView(R.id.img_bedmap_patient_sex);

        LinearLayout llSkinOrder = helper.getView(R.id.ll_bedmap_patient_skinorder);
        RecyclerView recySkinOrder = helper.getView(R.id.recy_bedmap_patient_skinorder);
        //提高展示效率
        recySkinOrder.setHasFixedSize(true);
        //设置的布局管理
        recySkinOrder.setLayoutManager(new LinearLayoutManager(mContext));

        if ("男".equals(item.getSex())) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }

        PatIconView patIcon = helper.getView(R.id.pacicon_bedmap);
        patIcon.setIconShow(item);

        List<BedMapBean.PatInfoListBean.SkinOrdBean> skinOrdBeanList = item.getSkinOrd();
        if (skinOrdBeanList == null || skinOrdBeanList.size() < 1) {
            line.setVisibility(View.GONE);
            llSkinOrder.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            llSkinOrder.setVisibility(View.VISIBLE);

            BedMapPatientSkinOrderAdapter skinOrderAdapter = new BedMapPatientSkinOrderAdapter(skinOrdBeanList);
            recySkinOrder.setAdapter(skinOrderAdapter);
        }

        if (SPUtils.getInstance().getString(Action.SINGLEMODEL).equals("1")){
            line.setVisibility(View.GONE);
            llSkinOrder.setVisibility(View.GONE);
        }else{
            line.setVisibility(View.VISIBLE);
            llSkinOrder.setVisibility(View.VISIBLE);
        }
    }
}
