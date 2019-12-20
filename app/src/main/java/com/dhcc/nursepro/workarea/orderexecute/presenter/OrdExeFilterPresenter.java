package com.dhcc.nursepro.workarea.orderexecute.presenter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.commlibs.bean.CommBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.BaseHelper;
import com.base.commlibs.utils.BasePopWindow;
import com.base.commlibs.utils.DataCache;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.PopWindowUtil;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrdExeFilterAdapter;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医嘱执行帮助类
 * @author:gaoruishan
 * @date:202019-08-16/17:21
 * @email:grs0515@163.com
 */
public class OrdExeFilterPresenter extends BaseHelper {

    private View contentView;
    private List<MainConfigBean.ScreenPartsBean> screenParts;
    private List<MainConfigBean.ScreenPartsBean> screenPartsClear = new ArrayList<>();
    private OnSelectCallBackListener onSelectCallBackListener;
    private String sheetCode;
    private OrdExeFilterAdapter ordExeFilterAdapter;
    private RecyclerView recyclerView;

    /**
     * 获取宿主Activity
     * @param activity
     * @return
     */
    public OrdExeFilterPresenter(Activity activity) {
        super(activity);
    }

    public void setOnSelectCallBackListener(OnSelectCallBackListener onSelectCallBackListener) {
        this.onSelectCallBackListener = onSelectCallBackListener;
    }

    @Override
    protected void initView(Activity mContext) {
        super.initView(mContext);
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_ord_exe_filter_layout, null);
        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        if (json != null) {
            screenParts = json.getScreenParts();
            recyclerView = contentView.findViewById(R.id.rv_ord_exe_filter);
            RecyclerViewHelper.setDefaultRecyclerView(mContext, recyclerView, 0, LinearLayoutManager.VERTICAL);
            ordExeFilterAdapter = new OrdExeFilterAdapter(screenParts);
            recyclerView.setAdapter(ordExeFilterAdapter);
            View ivFinishFilter = contentView.findViewById(R.id.iv_finish_filter);
            ivFinishFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWindowUtil.closePopWindow();
                }
            });
            View tvFilterClearSelect = contentView.findViewById(R.id.tv_filter_clear_select);
            tvFilterClearSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i <screenPartsClear.size() ; i++) {
                        for (int j = 0; j <screenPartsClear.get(i).getListBean().size() ; j++) {
                            screenPartsClear.get(i).getListBean().get(j).setShow(false);
                        }
                    }
                    ordExeFilterAdapter.replaceData(screenPartsClear);
                }
            });
            View tvFilterOk = contentView.findViewById(R.id.tv_filter_ok);
            tvFilterOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWindowUtil.closePopWindow();
                    StringBuilder builder = new StringBuilder();
                    for (MainConfigBean.ScreenPartsBean bean : ordExeFilterAdapter.getData()) {
                        String showStr = "";
                        for (CommBean b : bean.getListBean()) {
                            if (b.isShow()) {
                                showStr += b.getName() + "!";
                            }
                        }
                        if (showStr.length() > 1) {
                            builder.append(bean.getKeyCode()).append("|").append(showStr).append("^");
                        }
                    }
                    String str = builder.toString();
                    if (str.length() > 1) {//去掉尾^
                        str = str.substring(0, str.length() - 1);
                    }
                    if (onSelectCallBackListener != null) {
                        onSelectCallBackListener.onSelect(str);
                    }
                }
            });
        }
    }

    /**
     * 添加筛选按钮
     * @return
     */
    public View getRightTextView() {
//        TextView textView = new TextView(mContext);
        ImageView textView = new ImageView(mContext);
        textView.setImageResource(R.drawable.dhcc_filter_big_write);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
//        textView.setTextColor(getColor(R.color.white));
//        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(20, 0, 20, 0);
//        textView.setTextSize(15);
//        textView.setText("筛选");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopWindow();
            }
        });
        return textView;
    }

    /**
     * 打开PopWindow
     */
    public void openPopWindow() {
        PopWindowUtil.setMask(mContext, View.VISIBLE);
        PopWindowUtil.initPopupWindow(mContext, BasePopWindow.EnumLocation.RIGHT, contentView);
    }

    /**
     * 左侧栏 单据选择
     * @param sheetCode
     */
    public void setOrdTypeSelectedCode(String sheetCode) {
        this.sheetCode = sheetCode;
//        MainConfigBean json = DataCache.getJson(MainConfigBean.class, SharedPreference.DATA_MAIN_CONFIG);
        List<MainConfigBean.ScreenPartsBean> data = new ArrayList<>();
        Map<String, MainConfigBean.ScreenPartsBean> dataMap = new HashMap<>();
        for (MainConfigBean.ScreenPartsBean bean : screenParts) {
            bean.setListBean(null);//恢复默认
            String danjuStr = bean.getDanjuStr();
            //取出所有单据
            List<String> strings = new ArrayList<>();
            if (danjuStr != null && danjuStr.contains("!")) {
                strings = Arrays.asList(danjuStr.split("!"));
            } else {
                strings.add(danjuStr);
            }
            //遍历匹配,添加
            for (String s : strings) {
                if (s.equals(sheetCode)) {
//                    data.add(bean);
                    dataMap.put(bean.getKeyCode(), bean);
                }
            }
            if (bean.isCommonKey()) {
                dataMap.put(bean.getKeyCode(), bean);
            }
        }
        for (Map.Entry<String, MainConfigBean.ScreenPartsBean> entry : dataMap.entrySet()) {
            String mapKey = entry.getKey();
            MainConfigBean.ScreenPartsBean mapValue = entry.getValue();
            System.out.println(mapKey + ":" + mapValue.toString());
        }
        data.addAll(dataMap.values());
//        ordExeFilterAdapter.replaceData(data);
        screenPartsClear = data;
        ordExeFilterAdapter = new OrdExeFilterAdapter(data);
        recyclerView.setAdapter(ordExeFilterAdapter);
    }

    public interface OnSelectCallBackListener {
        void onSelect(String s);
    }


}

