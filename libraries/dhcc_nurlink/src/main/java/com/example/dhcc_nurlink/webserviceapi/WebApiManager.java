package com.example.dhcc_nurlink.webserviceapi;

import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.example.dhcc_nurlink.bean.AllUserBean;
import com.example.dhcc_nurlink.bean.BedMapBean;
import com.example.dhcc_nurlink.bean.HisUsersBean;
import com.example.dhcc_nurlink.bean.MessageListBean;
import com.example.dhcc_nurlink.bean.NoteBean;
import com.example.dhcc_nurlink.bean.PermissionBean;
import com.example.dhcc_nurlink.bean.PhoneBookListBean;
import com.example.dhcc_nurlink.bean.SetDutyBean;
import com.example.dhcc_nurlink.bean.SettingBedListBean;
import com.example.dhcc_nurlink.bean.UpdateBean;
import com.example.dhcc_nurlink.bean.UserInfoByDevIdBean;
import com.example.dhcc_nurlink.bean.LocksListBean;
import com.example.dhcc_nurlink.bean.UserBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-13/15:50
 * @email:grs0515@163.com
 */
public class WebApiManager {
    /**
     *
     * @param regNo
     * @param stDate
     * @param enDate
     * @param callBack
     */
    public static void getNurLinkUserList(String regNo, String stDate, String enDate,String exeFlag, final CommonCallBack<UserBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addHospitalId(properties);
        properties.put("regNo", regNo);
        properties.put("exeFlag", exeFlag);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.call("getLabOrdListNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<UserBean> parserUtil = new ParserUtil<>();
                UserBean bean = parserUtil.parserResult(jsonStr, callBack, UserBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     *获取科室列表
     */
    public static void getLocList(final CommonCallBack<LocksListBean> callBack){
        CommWebService.call("getHisLocsNURLINK", new HashMap<>(), new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<LocksListBean> parserUtil = new ParserUtil<>();
                LocksListBean bean = parserUtil.parserResult(jsonStr, callBack, LocksListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     *获取设备绑定用户信息
     */
    public static void getUserInfoByDevId(String devId,final CommonCallBack<UserInfoByDevIdBean> callBack){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("devId",devId);
        CommWebService.call("getUserInfoByDevIdNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<UserInfoByDevIdBean> parserUtil = new ParserUtil<>();
                UserInfoByDevIdBean bean = parserUtil.parserResult(jsonStr, callBack, UserInfoByDevIdBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     *获取登陆用户类型--判断是否病人
     */
    public static void getUserPermission(String userCode,final CommonCallBack<PermissionBean> callBack){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userCode",userCode);
        CommWebService.call("getUserPermissionNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<PermissionBean> parserUtil = new ParserUtil<>();
                PermissionBean bean = parserUtil.parserResult(jsonStr, callBack, PermissionBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     *设置值班
     */
    public static void setDutyUser(String userCode,String flag,final CommonCallBack<PermissionBean> callBack){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userCode",userCode);
        properties.put("flag",flag);
        CommWebService.call("setDutyUserNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<PermissionBean> parserUtil = new ParserUtil<>();
                PermissionBean bean = parserUtil.parserResult(jsonStr, callBack, PermissionBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     *获取值班
     */
    public static void getDutyinfo(String userCode,final CommonCallBack<SetDutyBean> callBack){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userCode",userCode);
        CommWebService.call("getDutyUserNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<SetDutyBean> parserUtil = new ParserUtil<>();
                SetDutyBean bean = parserUtil.parserResult(jsonStr, callBack, SetDutyBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 获取科室用户
     *
     */
    public static void getHisUsers(String locId, final CommonCallBack<HisUsersBean> callBack){
        HashMap<String,String> properties = CommWebService.addLocId(null);
        CommWebService.call("getHisUsersNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<HisUsersBean> parserUtil = new ParserUtil<>();
                HisUsersBean bean = parserUtil.parserResult(jsonStr, callBack, HisUsersBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 获取通讯录
     *
     */
    public static void getPhoneBook(String locId,String userId, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("locId",locId);
        properties.put("userId",userId);
        CommWebService.call("getPhoneBookNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                SPUtils.getInstance().put(SharedPreference.NUR_LINK_PHONE_JSON,jsonStr);
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 获取通讯录
     *
     */
    public static void getUserInfoByGroupId(String groupId, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("groupId",groupId);
        CommWebService.call("getUserInfoByGroupIdNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }



    /**
     * 获取消息列表
     *
     */
    public static void getMessageList(final CommonCallBack<MessageListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        CommWebService.call("getMessageListNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<MessageListBean> parserUtil = new ParserUtil<>();
                MessageListBean bean = parserUtil.parserResult(jsonStr, callBack, MessageListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 读取消息
     *
     */
    public static void readMessage(String rowId, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        properties.put("rowId",rowId);
        CommWebService.call("readMessageNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 审核消息
     *
     */
    public static void auditMessage(String rowId, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        properties.put("rowId",rowId);
        CommWebService.call("auditMessageNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 拒绝消息
     *
     */
    public static void refuseMessage(String rowId, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        properties.put("rowId",rowId);
        CommWebService.call("refuseMessageNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 登陆验证
     *
     */
    public static void Logon(String pass, final CommonCallBack<PhoneBookListBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        properties.put("password",pass);
        CommWebService.call("LogonNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PhoneBookListBean> parserUtil = new ParserUtil<>();
                PhoneBookListBean bean = parserUtil.parserResult(jsonStr, callBack, PhoneBookListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 获取备忘录列表
     *
     */
    public static void getSoundList(String userId, final CommonCallBack<NoteBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userId",SPUtils.getInstance().getString(SharedPreference.USERID));

        CommWebService.call("getSoundDataNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NoteBean> parserUtil = new ParserUtil<>();
                NoteBean bean = parserUtil.parserResult(jsonStr, callBack, NoteBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 获取备忘录列表
     *
     */
    public static void getPatList(String type,final CommonCallBack<BedMapBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("userCode",SPUtils.getInstance().getString(SharedPreference.USERCODE));
        properties.put("type",type);

        CommWebService.call("getPatListNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                SPUtils.getInstance().put(SharedPreference.PATLISTJSON,jsonStr);
                ParserUtil<BedMapBean> parserUtil = new ParserUtil<>();
                BedMapBean bean = parserUtil.parserResult(jsonStr, callBack, BedMapBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 删除备忘录
     *
     */
    public static void deleteSoundData(String rowId, final CommonCallBack<NoteBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("rowId",rowId);

        CommWebService.call("deleteSoundDataNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NoteBean> parserUtil = new ParserUtil<>();
                NoteBean bean = parserUtil.parserResult(jsonStr, callBack, NoteBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 删除语音留言消息
     *
     */
    public static void deleteMsgData(String rowId, final CommonCallBack<NoteBean> callBack){
        HashMap<String,String> properties = new HashMap<>();
        properties.put("rowId",rowId);

        CommWebService.call("deleteMsgDataNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NoteBean> parserUtil = new ParserUtil<>();
                NoteBean bean = parserUtil.parserResult(jsonStr, callBack, NoteBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     * 删除备忘录
     *
     */
    public static void setSoundData(HashMap properties, final CommonCallBack<NoteBean> callBack){
        CommWebService.call("setSoundDataNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NoteBean> parserUtil = new ParserUtil<>();
                NoteBean bean = parserUtil.parserResult(jsonStr, callBack, NoteBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 发送留言消息
     *
     */
    public static void recYYMessage(HashMap properties, final CommonCallBack<NoteBean> callBack){
        Log.e("dddddd", "recYYMessage: "+properties.toString() );
        CommWebService.call("recYYMessageNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<NoteBean> parserUtil = new ParserUtil<>();
                NoteBean bean = parserUtil.parserResult(jsonStr, callBack, NoteBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     *获取科室列表
     */
    public static void getAllPhoneBook(final CommonCallBack<AllUserBean> callBack){
        HashMap<String, String> properties = new HashMap<>();
        CommWebService.call("getAllPhoneBookNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<AllUserBean> parserUtil = new ParserUtil<>();
                AllUserBean bean = parserUtil.parserResult(jsonStr, callBack, AllUserBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     *获取所有床位
     */
    public static void getBedList(HashMap properties,final CommonCallBack<SettingBedListBean> callBack){
        CommWebService.call("getBedListNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<SettingBedListBean> parserUtil = new ParserUtil<>();
                SettingBedListBean bean = parserUtil.parserResult(jsonStr, callBack, SettingBedListBean.class);
                if (bean == null) {
                    return;
                }
                if (bean.getBedList()!=null){
                    for (int i = 0; i < bean.getBedList().size(); i++) {
                        bean.getBedList().get(i).setSelect("1");
                        for (int j = 0; j < bean.getBedList().get(i).getGroupList().size(); j++) {
                            if (bean.getBedList().get(i).getGroupList().get(j).getSelect().equals("0")){
                                bean.getBedList().get(i).setSelect("0");
                            }
                        }
                    }

                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
    /**
     *设置所管床
     */
    public static void setManagedBeds(HashMap properties,final CommonCallBack<SettingBedListBean> callBack){
        CommWebService.call("setManagedBedsNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<SettingBedListBean> parserUtil = new ParserUtil<>();
                SettingBedListBean bean = parserUtil.parserResult(jsonStr, callBack, SettingBedListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     *更新
     */
    public static void getNewVersion(HashMap properties,final CommonCallBack<UpdateBean> callBack){
        CommWebService.call("ifNewVersionNURLINK", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
//                if (jsonStr.isEmpty()){
//                    jsonStr = SPUtils.getInstance().getString("getUserInfoByDevId_json","");
//                }else {
//                    SPUtils.getInstance().put("getUserInfoByDevId_json",jsonStr);
//                }
                ParserUtil<UpdateBean> parserUtil = new ParserUtil<>();
                UpdateBean bean = parserUtil.parserResult(jsonStr, callBack, UpdateBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
