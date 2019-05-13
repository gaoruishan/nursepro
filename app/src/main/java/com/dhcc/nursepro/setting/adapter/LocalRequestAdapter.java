package com.dhcc.nursepro.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.bean.SettingBedListBean;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.setting.adapter
 * <p>
 * author Q
 * Date: 2019/5/11
 * Time:21:26
 */
public class LocalRequestAdapter extends BaseQuickAdapter<HashMap<String,String>, BaseViewHolder> {

    public LocalRequestAdapter(@Nullable List<HashMap<String,String>> data) {
        super(R.layout.item_setting_localrq, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String,String> item) {
        helper.setText(R.id.tv_req_patinfo,item.get("patInfo"))
                .setText(R.id.tv_req_orderid,"("+item.get("oeoreId")+")")
                .setText(R.id.tv_req_starttime,item.get("starttime"))
                .setText(R.id.tv_req_order,item.get("orderInfo"))
                .addOnClickListener(R.id.ll_message_content)
                .addOnClickListener(R.id.ll_message_rightmenu)
                .setText(R.id.tv_req_failreason,item.get("failreason"))
                .setText(R.id.tv_req_failtime,item.get("execDate")+" "+item.get("execTime"))
                .setTextColor(R.id.tv_req_failreason,mContext.getResources().getColor(R.color.lab_warning_red));
//        item.remove("soap_method");
//        item.remove("remarks");
//        tvBedSelectBed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WebServiceUtils.callWebService("execOrSeeOrder", item, new WebServiceUtils.WebServiceCallBack() {
//                    @Override
//                    public void callBack(String result) {
//                        showToast(result);
//                    }
//                });
//            }
//        });

    }
}