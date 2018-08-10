package com.dhcc.nursepro.config;

import android.util.Log;
import android.widget.Toast;

import com.dhcc.nursepro.config.api.ConfigApiManager;
import com.dhcc.nursepro.config.bean.ConfigBean;

import java.util.List;

public class NurseConfig {

    private static final NurseConfig instance = new NurseConfig();

    private String wardId;
    private String userId;
    private String groupId;

    //护理单据配置列表项
    private List<ConfigBean.NurSheetConfigBean> nurseSheetConfigList;

    //声明体征配置列表项
    private List<ConfigBean.TmpConfigBean> temperatureConfigList;


    public List<ConfigBean.NurSheetConfigBean> getNurseSheetConfigList() {
        return nurseSheetConfigList;
    }

    public void setNurseSheetConfigList(List<ConfigBean.NurSheetConfigBean> nurseSheetConfigList) {
        this.nurseSheetConfigList = nurseSheetConfigList;
    }

    public List<ConfigBean.TmpConfigBean> getTemperatureConfigList() {
        return temperatureConfigList;
    }

    public void setTemperatureConfigList(List<ConfigBean.TmpConfigBean> temperatureConfigList) {
        this.temperatureConfigList = temperatureConfigList;
    }

    public static NurseConfig getInstance() {
        return instance;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public void getConfig(){
        ConfigApiManager.getConfig(new ConfigApiManager.GetConfigCallback() {
            @Override
            public void onSuccess(ConfigBean config) {
                System.out.println(config.toString());
                NurseConfig.getInstance().setNurseSheetConfigList(config.getNurSheetConfig());
                NurseConfig.getInstance().setTemperatureConfigList(config.getTmpConfig());
            }

            @Override
            public void onFail(String code, String msg) {
                Log.v("config error",msg);
            }
        });
    }

}
