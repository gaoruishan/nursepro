package com.dhcc.nursepro.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.setting.adapter.LocalRequestAdapter;
import com.dhcc.nursepro.setting.api.SettingBedsApiManeger;
import com.dhcc.nursepro.setting.bean.LocalOrderExecResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.setting
 * <p>
 * author Q
 * Date: 2019/5/11
 * Time:21:22
 */
public class LocalRequesetFragment extends BaseFragment {
    private SPUtils spUtils = SPUtils.getInstance();
    private RecyclerView recLocReq;
    private LocalRequestAdapter localRequestAdapter;
    private  List<HashMap<String,String>> localList;
    private int allI = 0;
    private int orderIndex = 0;
    private int ifPoint = 0;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_localrequest, container, false);
    }


    private void updateOrders(){
            ifPoint = 1;
            HashMap<String,String> map = new HashMap<String, String>();
            map.putAll(localList.get(orderIndex));
            map.remove("soap_method");
            map.remove("remarks");
            map.remove("orderInfo");
            map.remove("patInfo");
            map.remove("starttime");
            map.remove("failreason");
            SettingBedsApiManeger.getReqResult(map, "execOrSeeOrderByDateTime", new SettingBedsApiManeger.GetReqResultCallback() {
                @Override
                public void onSuccess(LocalOrderExecResultBean localOrderExecResultBean) {
                    String orderidDel1 = map.get("oeoreId");
                    for (HashMap<String,String> map:localList){
                        if (map.get("oeoreId").equals(orderidDel1)){
                            localList.remove(map);
                            localRequestAdapter.setNewData(localList);
                            localRequestAdapter.notifyDataSetChanged();
                            Gson gson = new Gson();
                            String LocJson = gson.toJson(localList);
                            spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                            if (localList.size() == 0 ){
                                showToast("全部上传成功");
                                ifPoint = 0;
                            }
                            if (orderIndex <= localList.size()-1){
                                updateOrders();
                            }
                        }
                    }
                }
                @Override
                public void onFail(String code, String msg) {
                    String orderidDel1 = map.get("oeoreId");
                    for (HashMap<String,String> map:localList){
                        if (map.get("oeoreId").equals(orderidDel1)){
                            map.put("failreason",msg);
                            localRequestAdapter.setNewData(localList);
                            localRequestAdapter.notifyDataSetChanged();
                            Gson gson = new Gson();
                            String LocJson = gson.toJson(localList);
                            spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                            if (orderIndex <= localList.size()-1){
                                orderIndex++;
                                if (orderIndex==localList.size()-1){
                                    ifPoint = 0;
                                    showToast("全部上传失败，请查看失败原因");
                                }else {
                                    updateOrders();
                                }
                            }
                        }
                    }
                }
            });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBarBackgroundViewVisibility(true, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBackground(new ColorDrawable(0xffffffff));
        showToolbarNavigationIcon(R.drawable.icon_back_blue);

        setToolbarCenterTitle("重新请求");
        setToolbarBottomLineVisibility(false);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   全部上传   ");
        textView.setTextColor(getResources().getColor(R.color.blue_dark));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderIndex = 0;
                if (localList.size()>0){
                    if (ifPoint == 0){
                        updateOrders();
                    }
                }
            }
        });
        setToolbarRightCustomView(viewright);

        initView(view);
        initAdapter();
        initData();

    }

    private void initView(View view) {

        recLocReq = view.findViewById(R.id.rec_localreq);

        //提高展示效率
        recLocReq.setHasFixedSize(true);
        //设置的布局管理
        recLocReq.setLayoutManager(new LinearLayoutManager(getActivity()));

        recLocReq.setNestedScrollingEnabled(false);
    }

    private void initAdapter() {
        localRequestAdapter = new LocalRequestAdapter(new ArrayList<>());
        localRequestAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_message_rightmenu){
                    localList.remove(position);
                    localRequestAdapter.setNewData(localList);
                    localRequestAdapter.notifyDataSetChanged();
                    Gson gson = new Gson();
                    String LocJson = gson.toJson(localList);
                    spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                }else if (view.getId() == R.id.ll_message_content){
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.putAll(localList.get(position));
                        map.remove("soap_method");
                        map.remove("remarks");
                        map.remove("orderInfo");
                        map.remove("patInfo");
                        map.remove("starttime");
                        map.remove("failreason");
                    SettingBedsApiManeger.getReqResult(map, "execOrSeeOrderByDateTime", new SettingBedsApiManeger.GetReqResultCallback() {
                        @Override
                        public void onSuccess(LocalOrderExecResultBean localOrderExecResultBean) {
                            showToast(localOrderExecResultBean.getMsg());
                            for (int i = 0 ;i<localList.size();i++){
                                if (localList.get(i).get("oeoreId").equals(localList.get(position).get("oeoreId"))){
                                    localList.remove(position);
                                    localRequestAdapter.setNewData(localList);
                                    localRequestAdapter.notifyDataSetChanged();
                                    Gson gson = new Gson();
                                    String LocJson = gson.toJson(localList);
                                    spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                                }
                            }

                        }

                        @Override
                        public void onFail(String code, String msg) {
                            showToast(code+":"+msg);
                                for (int i = 0 ;i<localList.size();i++){
                                    if (localList.get(i).get("oeoreId").equals(localList.get(position).get("oeoreId"))){
                                        localList.get(i).put("failreason",msg);
                                        localRequestAdapter.setNewData(localList);
                                        localRequestAdapter.notifyDataSetChanged();
                                        Gson gson = new Gson();
                                        String LocJson = gson.toJson(localList);
                                        spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                                    }
                                }
                        }
                    });
                }
            }
        });
        recLocReq.setAdapter(localRequestAdapter);

    }

    private void initData(){
        localList =new ArrayList<>();
        Gson gson1 = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<HashMap<String,String>>>(){}.getType();
        String LocalJson = spUtils.getString(SharedPreference.LOCALREQUEST,"");
        if (LocalJson != ""){
            localList =gson1.fromJson(LocalJson,type);
            localRequestAdapter.setNewData(localList);
        }
    }


}
