package com.dhcc.nursepro.workarea.nurrecordnew.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.CareRecCommListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ElementDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.InWardPatListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ItemConfigbyEmrCodeBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecModelListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.SyncEmrData;
import com.google.gson.Gson;

import java.util.Map;

/**
 * NurRecordOldApiManager
 *
 * @author Devlix126
 * created at 2019/7/5 10:35
 */
public class NurRecordOldApiManager {

    public static void getInWardPatList(final InWardPatListCallback callback) {
        NurRecordOldApiService.getInWardPatList(new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        InWardPatListBean inWardPatListBean = gson.fromJson(jsonStr, InWardPatListBean.class);
                        if (ObjectUtils.isEmpty(inWardPatListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(inWardPatListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(inWardPatListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(inWardPatListBean.getMsgcode(), inWardPatListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getModelList(String episodeID, final RecModelListCallback callback) {
        NurRecordOldApiService.getModelList(episodeID, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
//                jsonStr = "{\"menuList\":[{\"ModelList\":[{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"6dca2de8907646b7b621d24364200f1d\",\"linkModel\":\"\",\"modelCode\":\"DHCNURBarthelLR\",\"modelName\":\"Barthel评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"165a867cfbb94662ae844dad91a14835\",\"linkModel\":\"\",\"modelCode\":\"DHCNURDDFXPGJHLJLDLR\",\"modelName\":\"跌倒评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"09579d9fef1e4cf0b3fc4ae9b99e8768\",\"linkModel\":\"\",\"modelCode\":\"DHCNURGLHTFXYSPGJHLCSLR\",\"modelName\":\"管道评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"\",\"linkModel\":\"\",\"modelCode\":\"DHCNURYCFXPGJHLJLDCRHZLR\",\"modelName\":\"压疮评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"\",\"linkModel\":\"\",\"modelCode\":\"DHCNURRYZKHLPGDCRHZLR\",\"modelName\":\"入院评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"\",\"linkModel\":\"\",\"modelCode\":\"DHCNURHLJLDLR\",\"modelName\":\"护理记录单\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"}],\"menuCode\":\"PGB\",\"menuName\":\"评估表\"}],\"msg\":\"成功\",\"msgcode\":\"999999\",\"status\":\"0\"}";
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecModelListBean recModelListBean = gson.fromJson(jsonStr, RecModelListBean.class);
                        if (ObjectUtils.isEmpty(recModelListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recModelListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recModelListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recModelListBean.getMsgcode(), recModelListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getNewEmrList(String parr, final CareRecCommCallback callback) {
        NurRecordOldApiService.getNewEmrList(parr, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
//                jsonStr = "{\"dataList\":[],\"msg\":\"成功\",\"msgcode\":\"999999\",\"status\":\"0\",\"titleList\":[{\"TitleCode\":\"CareDate\",\"TitleDesc\":\"日期\"},{\"TitleCode\":\"CareTime\",\"TitleDesc\":\"时间\"},{\"TitleCode\":\"CareUser\",\"TitleDesc\":\"记录人\"},{\"TitleCode\":\"CareDesc\",\"TitleDesc\":\"描述\"}]}";
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        CareRecCommListBean careRecCommListBean = gson.fromJson(jsonStr, CareRecCommListBean.class);
                        Map JsonMap = gson.fromJson(jsonStr, Map.class);
                        careRecCommListBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(careRecCommListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(careRecCommListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(careRecCommListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(careRecCommListBean.getMsg(), careRecCommListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getItemConfigByEmrCode(String episodeId, String emrCode, final ItemConfigByEmrCodeCallback callback) {
        NurRecordOldApiService.getItemConfigByEmrCode(episodeId, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ItemConfigbyEmrCodeBean itemConfigbyEmrCodeBean = gson.fromJson(jsonStr, ItemConfigbyEmrCodeBean.class);
                        if (ObjectUtils.isEmpty(itemConfigbyEmrCodeBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(itemConfigbyEmrCodeBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(itemConfigbyEmrCodeBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(itemConfigbyEmrCodeBean.getMsgcode(), itemConfigbyEmrCodeBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void GetXmlValues(String episodeId, String emrCode, String id, final GetXmlValuesCallback callback) {
        NurRecordOldApiService.GetXmlValues(episodeId, emrCode, id, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

//                if ("DHCNURBarthelLR".equals(emrCode)) {
//                    //Barthel评分
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-05-18\",\"ElementId\":\"28\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_28\",\"NameText\":\"日期输入\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"17:06:22\",\"ElementId\":\"26\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_26\",\"NameText\":\"时间输入\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"4\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_4\",\"NameText\":\"进食\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"需极大帮助或完全依赖他人，或留置胃管\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需部分帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可独立进食\",\"Value\":\"3\"}],\"SaveField\":\"Item1\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"6\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_6\",\"NameText\":\"洗澡\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"在洗澡过程中需他人帮助\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"准备好洗澡水后，可自己独立完成洗澡过程\",\"Value\":\"2\"}],\"SaveField\":\"Item2\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"8\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_8\",\"NameText\":\"修饰\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"需他人帮助\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"可自己独立完成\",\"Value\":\"2\"}],\"SaveField\":\"Item3\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"10\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_10\",\"NameText\":\"穿衣\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"需极大帮助或完全依赖他人\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需部分帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可独立完成\",\"Value\":\"3\"}],\"SaveField\":\"Item4\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"12\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_12\",\"NameText\":\"控制大便\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"完全失控\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"偶尔失控，或需要他人提示\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可控制大便\",\"Value\":\"3\"}],\"SaveField\":\"Item5\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"14\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_14\",\"NameText\":\"控制小便\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"完全失控\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"偶尔失控，或需要他人提示\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可控制小便\",\"Value\":\"3\"}],\"SaveField\":\"Item6\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"16\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_16\",\"NameText\":\"如厕\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"需极大帮助或完全依赖他人\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需部分帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可独立完成\",\"Value\":\"3\"}],\"SaveField\":\"Item7\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"18\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_18\",\"NameText\":\"床椅移动\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"完全依赖他人\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需极大帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"需部分帮助\",\"Value\":\"3\"},{\"NumberValue\":\"15\",\"Text\":\"可独立完成\",\"Value\":\"4\"}],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"20\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_20\",\"NameText\":\"平地行走\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"完全依赖他人\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需极大帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"需部分帮助\",\"Value\":\"3\"},{\"NumberValue\":\"15\",\"Text\":\"可独立在平地上行走45m\",\"Value\":\"4\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"22\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_22\",\"NameText\":\"上下楼梯\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"需极大帮助或完全依赖他人\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"需部分帮助\",\"Value\":\"2\"},{\"NumberValue\":\"10\",\"Text\":\"可独立上下楼梯\",\"Value\":\"3\"}],\"SaveField\":\"Item10\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"24\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_24\",\"NameText\":\"Barthel指数总分\",\"OprationItemList\":[],\"SaveField\":\"Item11\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"36\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_36\",\"NameText\":\"自理能力等级\",\"OprationItemList\":[],\"SaveField\":\"Item13\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"32\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_32\",\"NameText\":\"评估护士:\",\"OprationItemList\":[],\"SaveField\":\"Item12\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"msg\":\"\",\"status\":\"0\"}";
//                } else if ("DHCNURDDFXPGJHLJLDLR".equals(emrCode)) {
//                    //跌倒风险
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-05-12\",\"ElementId\":\"3\",\"ElementType\":\"DateElement\",\"NameText\":\"评估日期\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"14:04:16\",\"ElementId\":\"5\",\"ElementType\":\"TimeElement\",\"NameText\":\"评估时间\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"低风险评判\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"69\",\"ElementType\":\"RadioElement\",\"NameText\":\"低风险评判\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"低风险评判\",\"Value\":\"1\"}],\"SaveField\":\"Item32\"}],\"SaveField\":\"Item32\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"72\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"下拉单选\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1.完全瘫痪\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"2.完全行动障碍\",\"Value\":\"2\"},{\"NumberValue\":\"0\",\"Text\":\"3.无\",\"Value\":\"0\"}],\"SaveField\":\"Item33\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"高风险评判\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"70\",\"ElementType\":\"RadioElement\",\"NameText\":\"高风险评判\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"高风险评判\",\"Value\":\"2\"}],\"SaveField\":\"Item32\"}],\"SaveField\":\"Item32\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"74\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"下拉单选\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1.住院前6个月内发生≥2次跌倒\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"2.住院期间发生跌倒\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"3.癫痫史\",\"Value\":\"3\"},{\"NumberValue\":\"0\",\"Text\":\"4.无\",\"Value\":\"0\"}],\"SaveField\":\"Item34\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"评分评判\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"71\",\"ElementType\":\"RadioElement\",\"NameText\":\"评分评判\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"评分评判\",\"Value\":\"3\"}],\"SaveField\":\"Item32\"}],\"SaveField\":\"Item32\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"77\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"年龄\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"60-69岁\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"70-79岁\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"≥80岁\",\"Value\":\"3\"},{\"NumberValue\":\"0\",\"Text\":\"<60岁\",\"Value\":\"0\"}],\"SaveField\":\"Item35\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"79\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"跌倒史\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"近6个月曾有不明原因跌倒经历\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"81\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"排泄、排便和排尿\",\"OprationItemList\":[{\"NumberValue\":\"0\",\"Text\":\"正常\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"失禁\",\"Value\":\"2\"},{\"NumberValue\":\"2\",\"Text\":\"紧迫或频繁的排泄\",\"Value\":\"3\"},{\"NumberValue\":\"4\",\"Text\":\"失禁且紧迫和频繁的排泄\",\"Value\":\"4\"}],\"SaveField\":\"Item37\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"83\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"使用跌倒风险药物：镇痛/麻醉剂、抗癫痫药、降压药、利尿剂、催眠药、泻药、镇静剂和精神药物\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"使用1种跌倒风险药物\",\"Value\":\"1\"},{\"NumberValue\":\"5\",\"Text\":\"使用≥2种跌倒风险药物\",\"Value\":\"2\"},{\"NumberValue\":\"7\",\"Text\":\"在过去24小时内曾有手术镇静史\",\"Value\":\"3\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"4\"}],\"SaveField\":\"Item38\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"85\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"携带导管：指任何与患者相连接的导管，如静脉输液、胸腔引流管、留置导尿等\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"携带1种导管\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"携带2种导管\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"携带≥3种导管\",\"Value\":\"3\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"4\"}],\"SaveField\":\"Item39\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"87\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"辅助或监管\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"移动、转运或行走时需要辅助或监管\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"89\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"步态\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"步态不稳定\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item41\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"91\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"视觉或听觉障碍\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"因视觉或听觉障碍而影响移动\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"93\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"定向力障碍\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"定向力障碍\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item43\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"95\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"烦躁\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"烦躁\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item44\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"97\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"认知限制或障碍\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"认知限制或障碍\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"无\",\"Value\":\"2\"}],\"SaveField\":\"Item45\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"99\",\"ElementType\":\"NumberElement\",\"NameText\":\"总分\",\"OprationItemList\":[],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"37\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"光线充足，照明良好\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item17\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"39\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"地面清洁，无水迹，湿拖地时有防滑警示标识\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item18\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"41\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"活动区域无障碍物\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item19\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"43\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"呼叫器放于患者手可及处，教会使用方法\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item20\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"45\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"常用物品放在患者易取处\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item21\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"47\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"使用床档、床刹\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item22\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"49\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"衣裤长短合适，穿防滑鞋\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item23\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"51\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"必要时适当使用保护性约束\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item24\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"53\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"高风险患者床头挂跌倒警示标识\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item25\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"55\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"高风险患者重点交接，定时巡视\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"57\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"讲解防跌倒相关知识，提高自我照顾技巧\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item27\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"59\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"指导使用特殊药物注意事项\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item28\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"61\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"合理安排陪护人员，陪护人员不可与患者在同一病床休息\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item29\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"63\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"必要时提供辅助具，并教会使用方法\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"65\",\"ElementType\":\"TextElement\",\"NameText\":\"护士签名\",\"OprationItemList\":[],\"SaveField\":\"Item31\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"msg\":\"\",\"status\":\"0\"}";
//                } else if ("DHCNURGLHTFXYSPGJHLCSLR".equals(emrCode)) {
//                    //管道滑脱
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-04-26\",\"ElementId\":\"4\",\"ElementType\":\"DateElement\",\"NameText\":\"填表时间\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"09:40:50\",\"ElementId\":\"6\",\"ElementType\":\"TimeElement\",\"NameText\":\"填表时间\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"7\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"管路类型（根数)\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"2\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"3\",\"Value\":\"3\"},{\"NumberValue\":\"4\",\"Text\":\"4\",\"Value\":\"4\"},{\"NumberValue\":\"5\",\"Text\":\"5\",\"Value\":\"5\"}],\"SaveField\":\"Item1\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"16\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"管路类型（根数)\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"2\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"3\",\"Value\":\"3\"},{\"NumberValue\":\"4\",\"Text\":\"4\",\"Value\":\"4\"},{\"NumberValue\":\"5\",\"Text\":\"5\",\"Value\":\"5\"}],\"SaveField\":\"Item3\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"15\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"管路类型（根数)\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"2\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"3\",\"Value\":\"3\"},{\"NumberValue\":\"4\",\"Text\":\"4\",\"Value\":\"4\"},{\"NumberValue\":\"5\",\"Text\":\"5\",\"Value\":\"5\"}],\"SaveField\":\"Item2\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"18\",\"ElementType\":\"NumberElement\",\"NameText\":\"留置管路总数\",\"OprationItemList\":[],\"SaveField\":\"Item4\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"20\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"床头悬挂防脱管警示标识\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item5\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"22\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"每班床头交接管路固定情况及留置长度\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item6\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"24\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"进行诊疗护理需要移动患者时，妥善固定管路，防止拖、拉、拽\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item7\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"42\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"固定管路应留有合适长度以便于患者翻身\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item15\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"27\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"躁动患者据医嘱使用镇静剂或行保护性约束\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"30\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"定时巡视，及时为患者提供服务\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"32\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"告知患者及家属管路脱落会导致的严重后果及预防措施\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item10\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"34\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"清醒患者进行心理疏导和安全教育\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item11\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"36\",\"ElementType\":\"DropRadioElement\",\"NameText\":\"合理安排\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item12\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"37\",\"ElementType\":\"TextElement\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item13\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"39\",\"ElementType\":\"TextElement\",\"NameText\":\"护士签名\",\"OprationItemList\":[],\"SaveField\":\"Item14\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"msg\":\"\",\"status\":\"0\"}";
//                } else if ("DHCNURYCFXPGJHLJLDCRHZLR".equals(emrCode)) {
//                    //压疮风险评估及护理记录单（成人患者）
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-05-12\",\"ElementId\":\"15\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_15\",\"NameText\":\"日期输入\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"17:43:43\",\"ElementId\":\"19\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_19\",\"NameText\":\"时间输入\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"感知：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"245\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_245\",\"NameText\":\"完全受限（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"完全受限（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item66\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"249\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_245\",\"NameText\":\"大部分受限（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"大部分受限（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item66\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"248\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_245\",\"NameText\":\"轻度受限（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"轻度受限（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item66\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"247\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_245\",\"NameText\":\"没有改变（4分）\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"没有改变（4分）\",\"Value\":\"4\"}],\"SaveField\":\"Item66\"}],\"SaveField\":\"Item66\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"潮湿：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"240\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"持久潮湿（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"持久潮湿（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item65\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"244\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"经常潮湿（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"经常潮湿（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item65\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"243\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"偶尔潮湿（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"偶尔潮湿（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item65\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"242\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"很少潮湿（4分）\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"很少潮湿（4分）\",\"Value\":\"4\"}],\"SaveField\":\"Item65\"}],\"SaveField\":\"Item65\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"活动能力：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"235\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_235\",\"NameText\":\"卧床不起（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"卧床不起（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item64\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"239\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_235\",\"NameText\":\"局限于轮椅活动（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"局限于轮椅活动（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item64\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"238\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_235\",\"NameText\":\"可偶尔步行（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"可偶尔步行（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item64\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"237\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_235\",\"NameText\":\"经常步行（4分）\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"经常步行（4分）\",\"Value\":\"4\"}],\"SaveField\":\"Item64\"}],\"SaveField\":\"Item64\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"移动能力：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"230\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_230\",\"NameText\":\"完全受限（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"完全受限（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item63\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"234\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_230\",\"NameText\":\"严重受限（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"严重受限（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item63\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"233\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_230\",\"NameText\":\"轻度受限（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"轻度受限（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item63\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"232\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_230\",\"NameText\":\"不受限（4分）\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"不受限（4分）\",\"Value\":\"4\"}],\"SaveField\":\"Item63\"}],\"SaveField\":\"Item63\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"营养：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"220\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_220\",\"NameText\":\"重度营养摄入不足（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"重度营养摄入不足（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item62\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"224\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_220\",\"NameText\":\"营养摄入不足（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"营养摄入不足（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item62\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"223\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_220\",\"NameText\":\"营养摄入适当（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"营养摄入适当（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item62\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"222\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_220\",\"NameText\":\"营养摄入良好（4分）\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"营养摄入良好（4分）\",\"Value\":\"4\"}],\"SaveField\":\"Item62\"}],\"SaveField\":\"Item62\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"摩擦力和剪切力：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"216\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_216\",\"NameText\":\"有此问题（1分）\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"有此问题（1分）\",\"Value\":\"1\"}],\"SaveField\":\"Item61\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"219\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_216\",\"NameText\":\"有潜在问题（2分）\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"有潜在问题（2分）\",\"Value\":\"2\"}],\"SaveField\":\"Item61\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"218\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_216\",\"NameText\":\"无明显问题（3分）\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"无明显问题（3分）\",\"Value\":\"3\"}],\"SaveField\":\"Item61\"}],\"SaveField\":\"Item61\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"60\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_60\",\"NameText\":\"总分\",\"OprationItemList\":[],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"179\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_179\",\"NameText\":\"应用气垫床\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item43\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"181\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_181\",\"NameText\":\"使用翻身垫或软垫\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item44\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"183\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_183\",\"NameText\":\"定时翻身，每2h一次\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item45\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"185\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_185\",\"NameText\":\"有压红时翻身时间≤1h\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"187\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_187\",\"NameText\":\"局部使用水胶体/泡沫敷料等减压贴保护\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item47\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"191\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_191\",\"NameText\":\"有减少对特殊受压部位持续压迫的措施（如牵引、石膏、夹板、颈托、约束带、无创面罩、气管插管及其固定架、各类管路及指脉氧夹等），如减压贴或定时变换位置等\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item48\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"192\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_192\",\"NameText\":\"卧床患者落实足跟部悬空\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item49\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"194\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_194\",\"NameText\":\"保持皮肤清洁干爽，禁止用力按摩受压部位\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item50\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"196\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_196\",\"NameText\":\"应用润肤霜（乳/油）等护肤品\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item51\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"197\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_197\",\"NameText\":\"尿失禁患者及时更换纸尿片或纸尿裤，或\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item52\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"198\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_198\",\"NameText\":\"便失禁患者及时清理，防止大便刺激\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item53\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"199\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_199\",\"NameText\":\"长时间刺激物浸润，局部使用皮肤保护剂\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"200\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_200\",\"NameText\":\"保持床单位清洁、干燥、平整、无碎屑\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item55\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"201\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_201\",\"NameText\":\"协助变换体位和移动患者时避免拖、拉、拽等动作\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item56\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"202\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_202\",\"NameText\":\"病情允许床头抬高不高于30°\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item57\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"203\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_203\",\"NameText\":\"病情允许时尽量选择30º侧卧\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item58\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"205\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_205\",\"NameText\":\"指导患者及家属加强营养，摄取高热量、高蛋白、高维生素、高矿物质饮食，必要时少量多餐\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item59\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"206\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_206\",\"NameText\":\"告知家属及患者可能出现压疮的危险性及预防措施\",\"OprationItemList\":[{\"NumberValue\":\"√\",\"Text\":\"√\",\"Value\":\"1\"},{\"NumberValue\":\"0\",\"Text\":\"0\",\"Value\":\"2\"}],\"SaveField\":\"Item60\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"162\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_162\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item39\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"168\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_168\",\"NameText\":\"护士签名\",\"OprationItemList\":[],\"SaveField\":\"Item40\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"msg\":\"\",\"status\":\"0\"}";
//                } else if ("DHCNURRYZKHLPGDCRHZLR".equals(emrCode)) {
//                    //入院评估
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1\",\"DefaultValue\":\"内分泌代谢病科\",\"ElementId\":\"289\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_289\",\"NameText\":\"科室\",\"OprationItemList\":[],\"SaveField\":\"Item64\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!D079131CA99B4F7DAA6D9910F09C340D\",\"DefaultValue\":\"61\",\"ElementId\":\"291\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_291\",\"NameText\":\"床号\",\"OprationItemList\":[],\"SaveField\":\"Item65\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!681BC3C438EA4038BBC14F68F3FABD5D\",\"DefaultValue\":\"王乐\",\"ElementId\":\"293\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_293\",\"NameText\":\"姓名\",\"OprationItemList\":[],\"SaveField\":\"Item66\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!EEC533CCE8214787982304357ABBD365\",\"DefaultValue\":\"1186907\",\"ElementId\":\"295\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_295\",\"NameText\":\"住院号\",\"OprationItemList\":[],\"SaveField\":\"Item67\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!41D46590AB8947D78AB7C7C0DAC96221\",\"DefaultValue\":\"男\",\"ElementId\":\"297\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_297\",\"NameText\":\"性别\",\"OprationItemList\":[],\"SaveField\":\"Item68\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!95707AEE7AD44F8AAE492CDE78716ED4\",\"DefaultValue\":\"40岁\",\"ElementId\":\"299\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_299\",\"NameText\":\"年龄\",\"OprationItemList\":[],\"SaveField\":\"Item69\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!68AD018894534AD7B5249FEEBD6DE436\",\"DefaultValue\":\"2020-05-15\",\"ElementId\":\"23\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_23\",\"NameText\":\"入院日期\",\"OprationItemList\":[],\"SaveField\":\"Item1\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!5B581936E5DA422AB5CFA0748D0D148F\",\"DefaultValue\":\"08:39\",\"ElementId\":\"27\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_27\",\"NameText\":\"入院时间\",\"OprationItemList\":[],\"SaveField\":\"Item2\"},{\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!1EEAC728336E44F7A81C181C5C02FD2C\",\"DefaultValue\":\"2型糖尿病\",\"ElementId\":\"30\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_30\",\"NameText\":\"入院诊断\",\"OprationItemList\":[],\"SaveField\":\"Item3\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"34\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_34\",\"NameText\":\"转入日期\",\"OprationItemList\":[],\"SaveField\":\"Item4\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"38\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_38\",\"NameText\":\"转入时间\",\"OprationItemList\":[],\"SaveField\":\"Item5\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"41\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_41\",\"NameText\":\"转入诊断\",\"OprationItemList\":[],\"SaveField\":\"Item6\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"入院途径\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"44\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_44\",\"NameText\":\"急诊\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"急诊\",\"Value\":\"1\"}],\"SaveField\":\"Item7\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"门诊\",\"ElementId\":\"46\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_44\",\"NameText\":\"门诊\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"门诊\",\"Value\":\"2\"}],\"SaveField\":\"Item7\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"47\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_44\",\"NameText\":\"转入\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"转入\",\"Value\":\"3\"}],\"SaveField\":\"Item7\"}],\"SaveField\":\"Item7\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"入院方式\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"48\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_48\",\"NameText\":\"步行\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"步行\",\"Value\":\"1\"}],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"扶行\",\"ElementId\":\"50\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_48\",\"NameText\":\"扶行\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"扶行\",\"Value\":\"2\"}],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"51\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_48\",\"NameText\":\"轮椅\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"轮椅\",\"Value\":\"3\"}],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"52\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_48\",\"NameText\":\"平车\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"平车\",\"Value\":\"4\"}],\"SaveField\":\"Item8\"}],\"SaveField\":\"Item8\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"费用支付\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"53\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_53\",\"NameText\":\"自费\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"自费\",\"Value\":\"1\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"职工医保\",\"ElementId\":\"55\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_53\",\"NameText\":\"职工医保\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"职工医保\",\"Value\":\"2\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"56\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_53\",\"NameText\":\"城乡居民医保\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"城乡居民医保\",\"Value\":\"3\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"57\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_53\",\"NameText\":\"商业保险\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"商业保险\",\"Value\":\"4\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"58\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_53\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item9\"}],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!0ED50513BEF04F66B9B5AE4C0001F724\",\"DefaultValue\":\"35.8\",\"ElementId\":\"67\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_67\",\"NameText\":\"体温\",\"OprationItemList\":[],\"SaveField\":\"Item10\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!8B372C28D5604FEABE837253B55B8C5C\",\"DefaultValue\":\"110\",\"ElementId\":\"70\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_70\",\"NameText\":\"收缩压\",\"OprationItemList\":[],\"SaveField\":\"Item11\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!CBCEC7452B3F4E0BBB1B0049C4C33464\",\"DefaultValue\":\"80\",\"ElementId\":\"1809\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1809\",\"NameText\":\"舒张压\",\"OprationItemList\":[],\"SaveField\":\"Item133\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!89A8F0CB0CC04B57A9E05D39C0D930A3\",\"DefaultValue\":\"88\",\"ElementId\":\"73\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_73\",\"NameText\":\"脉搏\",\"OprationItemList\":[],\"SaveField\":\"Item12\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!4155E0F6321C459797DAC77B1C48C453\",\"DefaultValue\":\"\",\"ElementId\":\"1811\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1811\",\"NameText\":\"心率\",\"OprationItemList\":[],\"SaveField\":\"Item134\"},{\"DataSourceRef\":\"outside,$C5CF4711AFE844519644273E248DE570,$C5CF4711AFE844519644273E248DE570!F0F4DD02A1944F4C943F6AE9585CA879\",\"DefaultValue\":\"18\",\"ElementId\":\"120\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_120\",\"NameText\":\"呼吸\",\"OprationItemList\":[],\"SaveField\":\"Item23\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"意识状态\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"清醒\",\"ElementId\":\"87\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_87\",\"NameText\":\"清醒\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"清醒\",\"Value\":\"1\"}],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"89\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_87\",\"NameText\":\"嗜睡\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"嗜睡\",\"Value\":\"2\"}],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"90\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_87\",\"NameText\":\"模糊\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"模糊\",\"Value\":\"3\"}],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"91\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_87\",\"NameText\":\"昏迷\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"昏迷\",\"Value\":\"4\"}],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"92\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_87\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item14\"}],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"95\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_95\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item15\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"皮肤黏膜\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"97\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"苍白\",\"ElementId\":\"99\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"苍白\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"苍白\",\"Value\":\"2\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"100\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"潮红\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"潮红\",\"Value\":\"3\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"101\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"黄疸\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"黄疸\",\"Value\":\"4\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"102\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"紫绀\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"紫绀\",\"Value\":\"5\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"103\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_97\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"其他\",\"Value\":\"6\"}],\"SaveField\":\"Item16\"}],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"104\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_104\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item17\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"完整：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"完整：\",\"ElementId\":\"106\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_106\",\"NameText\":\"完整：\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"完整：\",\"Value\":\"1\"}],\"SaveField\":\"Item18\"}],\"SaveField\":\"Item18\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"没有\",\"ElementId\":\"1762\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1762\",\"NameText\":\"手术瘢痕部位\",\"OprationItemList\":[],\"SaveField\":\"Item117\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"不完整：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"完整：\",\"ElementId\":\"1761\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_106\",\"NameText\":\"不完整：\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"不完整：\",\"Value\":\"2\"}],\"SaveField\":\"Item18\"}],\"SaveField\":\"Item18\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1764\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1764\",\"NameText\":\"带入压疮部位\",\"OprationItemList\":[],\"SaveField\":\"Item118\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1766\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1766\",\"NameText\":\"破损部位\",\"OprationItemList\":[],\"SaveField\":\"Item119\"},{\"ElementType\":\"CheckElement\",\"NameText\":\"皮下出血\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"正常\",\"ElementId\":\"1822\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_1822\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item137\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1826\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_1822\",\"NameText\":\"水肿\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"水肿\",\"Value\":\"2\"}],\"SaveField\":\"Item137\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1825\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_1822\",\"NameText\":\"脱水\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"脱水\",\"Value\":\"3\"}],\"SaveField\":\"Item137\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1824\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_1822\",\"NameText\":\"皮疹\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"皮疹\",\"Value\":\"4\"}],\"SaveField\":\"Item137\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"皮下出血\",\"ElementId\":\"1823\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_1822\",\"NameText\":\"皮下出血\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"皮下出血\",\"Value\":\"5\"}],\"SaveField\":\"Item137\"}],\"SaveField\":\"Item137\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"其他\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"其他\",\"ElementId\":\"130\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_130\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"其他\",\"Value\":\"6\"}],\"SaveField\":\"Item24\"}],\"SaveField\":\"Item24\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"给\",\"ElementId\":\"131\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_131\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item25\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"排泄情况: 小便\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"133\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"135\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"失禁\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"失禁\",\"Value\":\"2\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"尿潴留\",\"ElementId\":\"136\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"尿潴留\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"尿潴留\",\"Value\":\"3\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"137\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"保留尿管\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"保留尿管\",\"Value\":\"4\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"138\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"人工瘘管\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"人工瘘管\",\"Value\":\"5\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"139\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"无法评估\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"无法评估\",\"Value\":\"6\"}],\"SaveField\":\"Item26\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"140\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_140\",\"NameText\":\"无法评估\",\"OprationItemList\":[],\"SaveField\":\"Item27\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"其他\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1759\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_133\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"7\",\"Text\":\"其他\",\"Value\":\"7\"}],\"SaveField\":\"Item26\"}],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"144\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_144\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item29\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"排泄情况: 大便\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"146\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"失禁\",\"ElementId\":\"148\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"失禁\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"失禁\",\"Value\":\"2\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"149\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"腹泻\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"腹泻\",\"Value\":\"3\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"150\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"便秘\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"便秘\",\"Value\":\"4\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"151\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"便血\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"便血\",\"Value\":\"5\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"152\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"肠造瘘\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"肠造瘘\",\"Value\":\"6\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"153\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"无法评估\",\"OprationItemList\":[{\"NumberValue\":\"7\",\"Text\":\"无法评估\",\"Value\":\"7\"}],\"SaveField\":\"Item30\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"154\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_154\",\"NameText\":\"无法评估\",\"OprationItemList\":[],\"SaveField\":\"Item31\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"其他\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1760\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_146\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"8\",\"Text\":\"其他\",\"Value\":\"8\"}],\"SaveField\":\"Item30\"}],\"SaveField\":\"Item30\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"158\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_158\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item33\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"管路情况\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"无\",\"ElementId\":\"160\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_160\",\"NameText\":\"无\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"无\",\"Value\":\"1\"}],\"SaveField\":\"Item34\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"162\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_160\",\"NameText\":\"有\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"有\",\"Value\":\"2\"}],\"SaveField\":\"Item34\"}],\"SaveField\":\"Item34\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"163\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_163\",\"NameText\":\"部位及数量\",\"OprationItemList\":[],\"SaveField\":\"Item35\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"外观\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"165\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_165\",\"NameText\":\"情绪稳定\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"情绪稳定\",\"Value\":\"1\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"167\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_165\",\"NameText\":\"焦虑\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"焦虑\",\"Value\":\"2\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"紧张\",\"ElementId\":\"168\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_165\",\"NameText\":\"紧张\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"紧张\",\"Value\":\"3\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"169\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_165\",\"NameText\":\"恐惧\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"恐惧\",\"Value\":\"4\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"170\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_165\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item36\"}],\"SaveField\":\"Item36\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"171\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_171\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item37\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"无法认知\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"173\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_173\",\"NameText\":\"了解\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"了解\",\"Value\":\"1\"}],\"SaveField\":\"Item38\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"175\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_173\",\"NameText\":\"部分了解\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"部分了解\",\"Value\":\"2\"}],\"SaveField\":\"Item38\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"不了解\",\"ElementId\":\"176\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_173\",\"NameText\":\"不了解\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"不了解\",\"Value\":\"3\"}],\"SaveField\":\"Item38\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"177\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_173\",\"NameText\":\"无法认知\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"无法认知\",\"Value\":\"4\"}],\"SaveField\":\"Item38\"}],\"SaveField\":\"Item38\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"178\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_178\",\"NameText\":\"无法认知\",\"OprationItemList\":[],\"SaveField\":\"Item39\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"心理状态\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"183\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_183\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"难入睡\",\"ElementId\":\"185\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_183\",\"NameText\":\"难入睡\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"难入睡\",\"Value\":\"2\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"186\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_183\",\"NameText\":\"易醒\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"易醒\",\"Value\":\"3\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"187\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_183\",\"NameText\":\"服药\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"服药\",\"Value\":\"4\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"188\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_183\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item40\"}],\"SaveField\":\"Item40\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"189\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_189\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item41\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"睡眠习惯\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"191\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_191\",\"NameText\":\"正常\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"正常\",\"Value\":\"1\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"193\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_191\",\"NameText\":\"食欲减退\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"食欲减退\",\"Value\":\"2\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"食欲亢进\",\"ElementId\":\"194\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_191\",\"NameText\":\"食欲亢进\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"食欲亢进\",\"Value\":\"3\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"195\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_191\",\"NameText\":\"吞咽困难\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"吞咽困难\",\"Value\":\"4\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"196\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_191\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item42\"}],\"SaveField\":\"Item42\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"197\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_197\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item43\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"生活习惯: 饮酒\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"199\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_199\",\"NameText\":\"饮\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"饮\",\"Value\":\"1\"}],\"SaveField\":\"Item44\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"不饮\",\"ElementId\":\"201\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_199\",\"NameText\":\"不饮\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"不饮\",\"Value\":\"2\"}],\"SaveField\":\"Item44\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"202\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_199\",\"NameText\":\"已戒\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"已戒\",\"Value\":\"3\"}],\"SaveField\":\"Item44\"}],\"SaveField\":\"Item44\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"生活习惯: 吸烟\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"203\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_203\",\"NameText\":\"吸\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"吸\",\"Value\":\"1\"}],\"SaveField\":\"Item45\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"不吸\",\"ElementId\":\"205\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_203\",\"NameText\":\"不吸\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"不吸\",\"Value\":\"2\"}],\"SaveField\":\"Item45\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"206\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_203\",\"NameText\":\"已戒\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"已戒\",\"Value\":\"3\"}],\"SaveField\":\"Item45\"}],\"SaveField\":\"Item45\"},{\"ElementType\":\"CheckElement\",\"NameText\":\"既往病史\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"216\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"无\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"无\",\"Value\":\"1\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"218\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"高血压\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"高血压\",\"Value\":\"2\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"219\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"心脏病\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"心脏病\",\"Value\":\"3\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"220\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"糖尿病\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"糖尿病\",\"Value\":\"4\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"221\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"脑血管病\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"脑血管病\",\"Value\":\"5\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"222\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"手术史\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"手术史\",\"Value\":\"6\"}],\"SaveField\":\"Item46\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"223\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_216\",\"NameText\":\"精神病\",\"OprationItemList\":[{\"NumberValue\":\"7\",\"Text\":\"精神病\",\"Value\":\"7\"}],\"SaveField\":\"Item46\"}],\"SaveField\":\"Item46\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"其他\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1768\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1768\",\"NameText\":\"其他\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"其他\",\"Value\":\"1\"}],\"SaveField\":\"Item120\"}],\"SaveField\":\"Item120\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1769\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1769\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item121\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"过敏史\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"无\",\"ElementId\":\"227\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_227\",\"NameText\":\"无\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"无\",\"Value\":\"1\"}],\"SaveField\":\"Item48\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"229\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_227\",\"NameText\":\"有\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"有\",\"Value\":\"2\"}],\"SaveField\":\"Item48\"}],\"SaveField\":\"Item48\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"232\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_232\",\"NameText\":\"药物\",\"OprationItemList\":[],\"SaveField\":\"Item49\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"234\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_234\",\"NameText\":\"食物\",\"OprationItemList\":[],\"SaveField\":\"Item50\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"236\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_236\",\"NameText\":\"其他\",\"OprationItemList\":[],\"SaveField\":\"Item51\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"238\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_238\",\"NameText\":\"社会情况：职业\",\"OprationItemList\":[],\"SaveField\":\"Item52\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"离退\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"240\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"在职\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"在职\",\"Value\":\"1\"}],\"SaveField\":\"Item53\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"离退\",\"ElementId\":\"242\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_240\",\"NameText\":\"离退\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"离退\",\"Value\":\"2\"}],\"SaveField\":\"Item53\"}],\"SaveField\":\"Item53\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"文化程度\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"243\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"小学\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"小学\",\"Value\":\"1\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"245\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"初中\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"初中\",\"Value\":\"2\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"高中\",\"ElementId\":\"246\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"高中\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"高中\",\"Value\":\"3\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"247\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"大学\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"大学\",\"Value\":\"4\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"248\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"研究生\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"研究生\",\"Value\":\"5\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"249\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"学龄前\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"学龄前\",\"Value\":\"6\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"250\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_243\",\"NameText\":\"文盲\",\"OprationItemList\":[{\"NumberValue\":\"7\",\"Text\":\"文盲\",\"Value\":\"7\"}],\"SaveField\":\"Item54\"}],\"SaveField\":\"Item54\"},{\"DataSourceRef\":\"inside,S0101T3,22\",\"DefaultValue\":\"40\",\"ElementId\":\"1773\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1773\",\"NameText\":\"日常生活活动能力评定(ADL)评分\",\"OprationItemList\":[],\"SaveField\":\"Item122\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1774\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1774\",\"NameText\":\"无需依赖\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"无需依赖\",\"Value\":\"1\"}],\"SaveField\":\"Item123\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1775\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1774\",\"NameText\":\"轻度依赖\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"轻度依赖\",\"Value\":\"2\"}],\"SaveField\":\"Item123\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1776\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1774\",\"NameText\":\"中度依赖\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"中度依赖\",\"Value\":\"3\"}],\"SaveField\":\"Item123\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"重度依赖\",\"ElementId\":\"1777\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1774\",\"NameText\":\"重度依赖\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"重度依赖\",\"Value\":\"4\"}],\"SaveField\":\"Item123\"}],\"SaveField\":\"Item123\"},{\"DataSourceRef\":\"inside,S0101T16,2\",\"DefaultValue\":\"23\",\"ElementId\":\"1780\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1780\",\"NameText\":\"压疮风险评估(Braden)评分\",\"OprationItemList\":[],\"SaveField\":\"Item124\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"无危险\",\"ElementId\":\"1781\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1781\",\"NameText\":\"无危险\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"无危险\",\"Value\":\"1\"}],\"SaveField\":\"Item125\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1782\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1781\",\"NameText\":\"低危\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"低危\",\"Value\":\"2\"}],\"SaveField\":\"Item125\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1783\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1781\",\"NameText\":\"中危\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"中危\",\"Value\":\"3\"}],\"SaveField\":\"Item125\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1784\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1781\",\"NameText\":\"高危\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"高危\",\"Value\":\"4\"}],\"SaveField\":\"Item125\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1785\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1781\",\"NameText\":\"极高危\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"极高危\",\"Value\":\"5\"}],\"SaveField\":\"Item125\"}],\"SaveField\":\"Item125\"},{\"DataSourceRef\":\"inside,S0101T31,14\",\"DefaultValue\":\"17\",\"ElementId\":\"1788\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1788\",\"NameText\":\"跌倒风险评估量表(约翰霍普金斯评分)\",\"OprationItemList\":[],\"SaveField\":\"Item126\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"直接评定：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1789\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1789\",\"NameText\":\"直接评定：\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"直接评定：\",\"Value\":\"1\"}],\"SaveField\":\"Item127\"}],\"SaveField\":\"Item127\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1815\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1815\",\"NameText\":\"高风险\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"高风险\",\"Value\":\"1\"}],\"SaveField\":\"Item135\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1816\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1815\",\"NameText\":\"低风险\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"低风险\",\"Value\":\"2\"}],\"SaveField\":\"Item135\"}],\"SaveField\":\"Item135\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"评分评估：\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"评分评估：\",\"ElementId\":\"1793\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1789\",\"NameText\":\"评分评估：\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"评分评估：\",\"Value\":\"2\"}],\"SaveField\":\"Item127\"}],\"SaveField\":\"Item127\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1818\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1818\",\"NameText\":\"低风险\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"低风险\",\"Value\":\"1\"}],\"SaveField\":\"Item136\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1820\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1818\",\"NameText\":\"中风险\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"中风险\",\"Value\":\"2\"}],\"SaveField\":\"Item136\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"高风险\",\"ElementId\":\"1821\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1818\",\"NameText\":\"高风险\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"高风险\",\"Value\":\"3\"}],\"SaveField\":\"Item136\"}],\"SaveField\":\"Item136\"},{\"ElementType\":\"RadioElement\",\"NameText\":\"资料来源\",\"RadioElementList\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1798\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1798\",\"NameText\":\"本人\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"本人\",\"Value\":\"1\"}],\"SaveField\":\"Item130\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"家属\",\"ElementId\":\"1799\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1798\",\"NameText\":\"家属\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"家属\",\"Value\":\"2\"}],\"SaveField\":\"Item130\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1800\",\"ElementType\":\"RadioElement\",\"FormName\":\"RadioElement_1798\",\"NameText\":\"其他人\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"其他人\",\"Value\":\"3\"}],\"SaveField\":\"Item130\"}],\"SaveField\":\"Item130\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"1801\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1801\",\"NameText\":\"其他人\",\"OprationItemList\":[],\"SaveField\":\"Item131\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"nurse\",\"ElementId\":\"1803\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_1803\",\"NameText\":\"评估护士签名\",\"OprationItemList\":[],\"SaveField\":\"Item132\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-05-27\",\"ElementId\":\"1805\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_1805\",\"NameText\":\"评估记录时间\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"18:13\",\"ElementId\":\"1806\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_1806\",\"NameText\":\"评估记录时间2\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"firstIdList\":[{\"EmrCode\":\"DHCNURBarthelLR\",\"GuId\":\"6DCA2DE8907646B7B621D24364200F1D\",\"RecId\":\"15237\"},{\"EmrCode\":\"DHCNURDDFXPGJHLJLDLR\",\"GuId\":\"165A867CFBB94662AE844DAD91A14835\",\"RecId\":\"15239\"},{\"EmrCode\":\"DHCNURGLHTFXYSPGJHLCSLR\",\"GuId\":\"09579D9FEF1E4CF0B3FC4AE9B99E8768\",\"RecId\":\"\"},{\"EmrCode\":\"DHCNURYCFXPGJHLJLDCRHZLR\",\"GuId\":\"704FFD85BCBA43DD8550EC2FB961658B\",\"RecId\":\"15238\"}],\"msg\":\"\",\"status\":\"0\"}";
//                } else if ("DHCNURHLJLDLR".equals(emrCode)) {
//                    //护理记录单
//                    jsonStr = "{\"data\":{\"Input\":{\"ElementBases\":[{\"DataSourceRef\":\"\",\"DefaultValue\":\"2020-05-20\",\"ElementId\":\"2\",\"ElementType\":\"DateElement\",\"FormName\":\"DateElement_2\",\"NameText\":\"日期\",\"OprationItemList\":[],\"SaveField\":\"CareDate\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"09:46:35\",\"ElementId\":\"4\",\"ElementType\":\"TimeElement\",\"FormName\":\"TimeElement_4\",\"NameText\":\"时间\",\"OprationItemList\":[],\"SaveField\":\"CareTime\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"53\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_53\",\"NameText\":\"意识：\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"清醒\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"嗜睡\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"模糊\",\"Value\":\"3\"},{\"NumberValue\":\"4\",\"Text\":\"昏迷\",\"Value\":\"4\"},{\"NumberValue\":\"5\",\"Text\":\"其他\",\"Value\":\"5\"}],\"SaveField\":\"Item23\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"84\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_84\",\"NameText\":\"体温\",\"OprationItemList\":[],\"SaveField\":\"Item4\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"86\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_86\",\"NameText\":\"脉搏\",\"OprationItemList\":[],\"SaveField\":\"Item5\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"90\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_90\",\"NameText\":\"心率\",\"OprationItemList\":[],\"SaveField\":\"Item6\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"72\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_72\",\"NameText\":\"单行输入\",\"OprationItemList\":[],\"SaveField\":\"Item100\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"20\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_20\",\"NameText\":\"无创呼吸机\",\"OprationItemList\":[],\"SaveField\":\"Item10\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"92\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_92\",\"NameText\":\"收缩压\",\"OprationItemList\":[],\"SaveField\":\"Item8\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"95\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_95\",\"NameText\":\"舒张压\",\"OprationItemList\":[],\"SaveField\":\"Item24\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"18\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_18\",\"NameText\":\"血氧饱和度\",\"OprationItemList\":[],\"SaveField\":\"Item9\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"47\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_47\",\"NameText\":\"空白\",\"OprationItemList\":[],\"SaveField\":\"Item22\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"75\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_75\",\"NameText\":\"药物名称\",\"OprationItemList\":[],\"SaveField\":\"Item11\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"82\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_82\",\"NameText\":\"液体入量\",\"OprationItemList\":[],\"SaveField\":\"Item12\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"67\",\"ElementType\":\"TextareaElement\",\"FormName\":\"TextareaElement_67\",\"NameText\":\"其他入量名称\",\"OprationItemList\":[],\"SaveField\":\"Item25\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"27\",\"ElementType\":\"TextareaElement\",\"FormName\":\"TextareaElement_27\",\"NameText\":\"其他入量\",\"OprationItemList\":[],\"SaveField\":\"Item13\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"30\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_30\",\"NameText\":\"尿量\",\"OprationItemList\":[],\"SaveField\":\"Item14\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"32\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_32\",\"NameText\":\"引流名称\",\"OprationItemList\":[],\"SaveField\":\"Item15\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"34\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_34\",\"NameText\":\"引流量\",\"OprationItemList\":[],\"SaveField\":\"Item16\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"36\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_36\",\"NameText\":\"引流性状\",\"OprationItemList\":[],\"SaveField\":\"Item17\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"38\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_38\",\"NameText\":\"引流在位\",\"OprationItemList\":[],\"SaveField\":\"Item18\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"69\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_69\",\"NameText\":\"出量名称：\",\"OprationItemList\":[],\"SaveField\":\"Item26\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"40\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_40\",\"NameText\":\"其他出量\",\"OprationItemList\":[],\"SaveField\":\"Item19\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"42\",\"ElementType\":\"TextareaElement\",\"FormName\":\"TextareaElement_42\",\"NameText\":\"病情、治疗、护理\",\"OprationItemList\":[],\"SaveField\":\"Item20\"},{\"DataSourceRef\":\"\",\"DefaultValue\":\"\",\"ElementId\":\"44\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_44\",\"NameText\":\"签名\",\"OprationItemList\":[],\"SaveField\":\"Item21\"}],\"TemplateIndentity\":\"\"},\"Table\":[]},\"msg\":\"\",\"status\":\"0\"}";
//                }

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ElementDataBean elementDataBean = gson.fromJson(jsonStr, ElementDataBean.class);
                        if (ObjectUtils.isEmpty(elementDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(elementDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(elementDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(elementDataBean.getMsg(), elementDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void saveNewEmrData(String guId, String episodeId, String recordId, String parr, final RecDataCallback callback) {
        NurRecordOldApiService.saveNewEmrData(guId, episodeId, recordId, parr, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsg(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }


    public static void getPGDId(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getPGDId(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getPGDVal(String pgdId, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.getPGDVal(pgdId, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getEmrXML(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getEmrXML(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getDateTime(final RecDataCallback callback) {
        NurRecordOldApiService.getDateTime(new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getEmrPatinfo(String episodeID, String emrCode, final RecDataCallback callback) {
        NurRecordOldApiService.getEmrPatinfo(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void linkEmrData(String episodeID, String emrCode, final SyncEmrDataCallback callback) {
        NurRecordOldApiService.linkEmrData(episodeID, emrCode, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        SyncEmrData syncEmrData = gson.fromJson(jsonStr, SyncEmrData.class);
                        if (ObjectUtils.isEmpty(syncEmrData)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(syncEmrData.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(syncEmrData);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(syncEmrData.getMsgcode(), syncEmrData.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void savePGDData(String parr, String pgdId, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.savePGDData(parr, pgdId, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getCareRecComm(String parr, String methodName, final CareRecCommCallback callback) {
        NurRecordOldApiService.getCareRecComm(parr, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        CareRecCommListBean careRecCommListBean = gson.fromJson(jsonStr, CareRecCommListBean.class);
                        Map JsonMap = gson.fromJson(jsonStr, Map.class);
                        careRecCommListBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(careRecCommListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(careRecCommListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(careRecCommListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(careRecCommListBean.getMsgcode(), careRecCommListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getJLDVal(String par, String rw, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.getJLDVal(par, rw, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void saveJLDData(String parr, String episodeID, String emrCode, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.saveJLDData(parr, episodeID, emrCode, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getMPGDList(String parr, String methodName, final CareRecCommCallback callback) {
        NurRecordOldApiService.getMPGDList(parr, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        CareRecCommListBean careRecCommListBean = gson.fromJson(jsonStr, CareRecCommListBean.class);
                        Map JsonMap = gson.fromJson(jsonStr, Map.class);
                        careRecCommListBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(careRecCommListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(careRecCommListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(careRecCommListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(careRecCommListBean.getMsgcode(), careRecCommListBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void getMPGDVal(String par, String rw, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.getMPGDVal(par, rw, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public static void saveMPGDData(String parr, String pgdId, String methodName, final RecDataCallback callback) {
        NurRecordOldApiService.saveMPGDData(parr, pgdId, methodName, new NurRecordOldApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RecDataBean recDataBean = gson.fromJson(jsonStr, RecDataBean.class);
                        if (ObjectUtils.isEmpty(recDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(recDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(recDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(recDataBean.getMsgcode(), recDataBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }

                }
            }
        });
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface InWardPatListCallback extends CommonCallBack {
        void onSuccess(InWardPatListBean inWardPatListBean);
    }

    public interface RecModelListCallback extends CommonCallBack {
        void onSuccess(RecModelListBean recModelListBean);
    }

    public interface RecDataCallback extends CommonCallBack {
        void onSuccess(RecDataBean recDataBean);
    }

    public interface SyncEmrDataCallback extends CommonCallBack {
        void onSuccess(SyncEmrData syncEmrData);
    }

    public interface CareRecCommCallback extends CommonCallBack {
        void onSuccess(CareRecCommListBean careRecCommListBean);
    }

    public interface ItemConfigByEmrCodeCallback extends CommonCallBack {
        void onSuccess(ItemConfigbyEmrCodeBean itemConfigbyEmrCodeBean);
    }

    public interface GetXmlValuesCallback extends CommonCallBack {
        void onSuccess(ElementDataBean elementDataBean);
    }

    public interface getNewEmrListCallback extends CommonCallBack {
        void onSuccess(ElementDataBean elementDataBean);
    }

    public interface saveNewEmrDataCallback extends CommonCallBack {
        void onSuccess(ElementDataBean elementDataBean);
    }
}
