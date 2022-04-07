package com.dhcc.nursepro.workarea.nurrecordnew.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.CareRecCommListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.DataSourceBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.ElementDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.GetOutSideDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.InWardPatListBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.NurRecordKnowledgeContentBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.NurRecordKnowledgeTreeBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecDataBean;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.RecModelListBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * NurRecordOldApiManager
 *
 * @author Devlix126
 * created at 2019/7/5 10:35
 */
public class NurRecordNewApiManager {

    public static void getInWardPatList(final InWardPatListCallback callback) {
        NurRecordNewApiService.getInWardPatList(new NurRecordNewApiService.ServiceCallBack() {
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
        NurRecordNewApiService.getModelList(episodeID, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
//                jsonStr = "{\"menuList\":[{\"ModelList\":[{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"dc863a50617045d09d7b9bd41223dde7\",\"linkModel\":\"\",\"modelCode\":\"DHCNURRYZKHLPGDCRHZLR\",\"modelName\":\"入院/转科评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"6dca2de8907646b7b621d24364200f1d\",\"linkModel\":\"\",\"modelCode\":\"DHCNURBarthelLR\",\"modelName\":\"日常生活ADL评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"704ffd85bcba43dd8550ec2fb961658b\",\"linkModel\":\"\",\"modelCode\":\"DHCNURYCFXPGJHLJLDCRHZLR\",\"modelName\":\"压疮评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"165a867cfbb94662ae844dad91a14835\",\"linkModel\":\"\",\"modelCode\":\"DHCNURDDFXPGJHLJLDLR\",\"modelName\":\"跌倒评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"09579d9fef1e4cf0b3fc4ae9b99e8768\",\"linkModel\":\"\",\"modelCode\":\"DHCNURGLHTFXYSPGJHLCSLR\",\"modelName\":\"管道评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"b0415883177f40c19640044d0b7dc509\",\"linkModel\":\"\",\"modelCode\":\"DHCNURFKPGDLR\",\"modelName\":\"产科入院评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"30ed6ca8f3e8427492ddd90df14464a1\",\"linkModel\":\"\",\"modelCode\":\"DHCNUREKHLPGDLR\",\"modelName\":\"儿科入院评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"588cc790d57a41a39a8e41cb4522f6f0\",\"linkModel\":\"\",\"modelCode\":\"DHCNUREKDDFXPGLBHDLR\",\"modelName\":\"儿科跌倒评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"c886511c4a7c4010bc089c4a56b3b3a6\",\"linkModel\":\"\",\"modelCode\":\"DHCNUREKYCHLJL\",\"modelName\":\"儿科压疮评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"},{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"65b771f844144daa8bc35a42c03d1f22\",\"linkModel\":\"\",\"modelCode\":\"DHCNUREKRCSHNLADLLBBZSLR\",\"modelName\":\"儿科日常生活ADL评估\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"}],\"menuCode\":\"PGD\",\"menuName\":\"评估单\"},{\"ModelList\":[{\"getListMth\":\"\",\"getValMth\":\"\",\"guId\":\"2e173fe7fc19410093dc2ee1d5cc64c4\",\"linkModel\":\"\",\"modelCode\":\"DHCNURHLJLDLR\",\"modelName\":\"护理记录单\",\"modelNum\":\"\",\"modelType\":\"3\",\"saveMth\":\"\",\"wStatus\":\"0\"}],\"menuCode\":\"JLD\",\"menuName\":\"记录单\"}],\"msg\":\"成功\",\"msgcode\":\"999999\",\"status\":\"0\"}";
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
        NurRecordNewApiService.getNewEmrList(parr, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
//                jsonStr = "{\"dataList\":[],\"msg\":\"成功\",\"msgcode\":\"999999\",\"status\":\"0\",\"titleList\":[{\"TitleCode\":\"CareDate\",\"TitleDesc\":\"日期\"},{\"TitleCode\":\"CareTime\",\"TitleDesc\":\"时间\"},{\"TitleCode\":\"CareUser\",\"TitleDesc\":\"记录人\"},{\"TitleCode\":\"CareDesc\",\"TitleDesc\":\"描述\"}]}";
//                jsonStr = "{\"dataList\":[{\"CareDate\":\"2020-07-17\",\"CareDesc\":\"11\",\"CareTime\":\" 10:26\",\"CareUser\":\"刘皓\",\"par\":\"59468\",\"rw\":\"\"}],\"msg\":\"成功\",\"msgcode\":\"999999\",\"status\":\"0\",\"titleList\":[{\"TitleCode\":\"CareDate\",\"TitleDesc\":\"日期\"},{\"TitleCode\":\"CareTime\",\"TitleDesc\":\"时间\"},{\"TitleCode\":\"CareDesc\",\"TitleDesc\":\"分值\"},{\"TitleCode\":\"CareUser\",\"TitleDesc\":\"记录人\"}]}";
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

    public static void GetXmlValues(String episodeId, String emrCode, String id, final GetXmlValuesCallback callback) {
        NurRecordNewApiService.GetXmlValues(episodeId, emrCode, id, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

//                jsonStr = "{\"StrictCodeList\":[],\"data\":{\"Input\":{\"ElementBases\":[{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"73\",\"ElementType\":\"ContainerElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"报告名\",\"OprationItemList\":[],\"ParentId\":\"\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"1\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"75\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"入 院 评 估 单\",\"OprationItemList\":[],\"ParentId\":\"73\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"73\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"46\",\"ElementType\":\"ContainerElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"患者信息\",\"OprationItemList\":[],\"ParentId\":\"\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"1\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"130\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"基本信息\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"47\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"科室：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"48\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_48\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"科室：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item16\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"49\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"病区：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!F38B6CA2D82444A0B2C88030C571571B\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"50\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_50\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"病区：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item17\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"51\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"住院号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!EEC533CCE8214787982304357ABBD365\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"52\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_52\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"住院号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item18\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"53\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"床号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!D079131CA99B4F7DAA6D9910F09C340D\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"54\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_54\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"床号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item19\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"55\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"姓名：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!681BC3C438EA4038BBC14F68F3FABD5D\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"56\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_56\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"姓名：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item20\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"57\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"性别：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!41D46590AB8947D78AB7C7C0DAC96221\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"58\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_58\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"性别：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item21\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"59\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"年龄：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!95707AEE7AD44F8AAE492CDE78716ED4\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"60\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_60\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"年龄：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item22\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"61\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"登记号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!0245B72A18284D6FA6032B3FE1F8C787\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"62\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_62\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"登记号：\",\"OprationItemList\":[],\"ParentId\":\"46\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item23\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"46\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"79\",\"ElementType\":\"ContainerElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"一般情况\",\"OprationItemList\":[],\"ParentId\":\"\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"1\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"80\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"一般情况\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"\",\"DoctorAdviceRuleLinkNo\":\"\",\"ElementId\":\"82\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"身高：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!1DB6B840B4D94C379ABB6759F82BC165\",\"DoctorAdviceRuleLinkNo\":\"1DB6B840B4D94C379ABB6759F82BC165\",\"ElementId\":\"83\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_83\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"身高：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item24\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!1DB6B840B4D94C379ABB6759F82BC165\",\"DoctorAdviceRuleLinkNo\":\"1DB6B840B4D94C379ABB6759F82BC165\",\"ElementId\":\"84\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"cm\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!1DB6B840B4D94C379ABB6759F82BC165\",\"DoctorAdviceRuleLinkNo\":\"1DB6B840B4D94C379ABB6759F82BC165\",\"ElementId\":\"85\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"体重：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2371F7A2B0E248F5A8B312A7D5C66DC8\",\"DoctorAdviceRuleLinkNo\":\"2371F7A2B0E248F5A8B312A7D5C66DC8\",\"ElementId\":\"88\",\"ElementType\":\"NumberElement\",\"FormName\":\"NumberElement_88\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"体重：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item25\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2371F7A2B0E248F5A8B312A7D5C66DC8\",\"DoctorAdviceRuleLinkNo\":\"2371F7A2B0E248F5A8B312A7D5C66DC8\",\"ElementId\":\"90\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"KG\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2371F7A2B0E248F5A8B312A7D5C66DC8\",\"DoctorAdviceRuleLinkNo\":\"2371F7A2B0E248F5A8B312A7D5C66DC8\",\"ElementId\":\"91\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"体温：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!15472224AC2C480CA17D414CC9A64CB4\",\"DoctorAdviceRuleLinkNo\":\"15472224AC2C480CA17D414CC9A64CB4\",\"ElementId\":\"92\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_92\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"体温：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item26\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!15472224AC2C480CA17D414CC9A64CB4\",\"DoctorAdviceRuleLinkNo\":\"15472224AC2C480CA17D414CC9A64CB4\",\"ElementId\":\"93\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"℃\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!15472224AC2C480CA17D414CC9A64CB4\",\"DoctorAdviceRuleLinkNo\":\"15472224AC2C480CA17D414CC9A64CB4\",\"ElementId\":\"94\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"脉搏：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!F59BB63B476245A094A6AC7F43E9B85F\",\"DoctorAdviceRuleLinkNo\":\"F59BB63B476245A094A6AC7F43E9B85F\",\"ElementId\":\"95\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_95\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"脉搏：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item27\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!F59BB63B476245A094A6AC7F43E9B85F\",\"DoctorAdviceRuleLinkNo\":\"F59BB63B476245A094A6AC7F43E9B85F\",\"ElementId\":\"96\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"次/分\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!F59BB63B476245A094A6AC7F43E9B85F\",\"DoctorAdviceRuleLinkNo\":\"F59BB63B476245A094A6AC7F43E9B85F\",\"ElementId\":\"97\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"BP：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!91695486954847888A7D9D79FB13426D\",\"DoctorAdviceRuleLinkNo\":\"91695486954847888A7D9D79FB13426D\",\"ElementId\":\"98\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_98\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"BP：\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item28\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!91695486954847888A7D9D79FB13426D\",\"DoctorAdviceRuleLinkNo\":\"91695486954847888A7D9D79FB13426D\",\"ElementId\":\"99\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"/\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"100\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_100\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item29\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"101\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"mmHg\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"DHCNURTemRecData\",\"CallBackEffects\":\"83,88,92,95,98,100\",\"CallBackReturnMapEffects\":\"NumberElement_83^,NumberElement_88^,TextElement_92^,TextElement_95^,TextElement_98^,TextElement_100^\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"131\",\"ElementType\":\"ButtonElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"导入生命体征\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"103\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"下拉\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"104\",\"ElementType\":\"DropRadioElement\",\"FormName\":\"DropRadioElement_104\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"下拉\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"是\",\"Value\":\"1\"},{\"NumberValue\":\"2\",\"Text\":\"否\",\"Value\":\"2\"},{\"NumberValue\":\"3\",\"Text\":\"不详\",\"Value\":\"3\"}],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item30\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"105\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"结果区显示\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"106\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_106\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"结果区显示\",\"OprationItemList\":[],\"ParentId\":\"79\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item31\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"79\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"107\",\"ElementType\":\"ContainerElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"总分：\",\"OprationItemList\":[],\"ParentId\":\"\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"1\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"108\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"总分：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"109\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_109\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"总分：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item32\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"110\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"分\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"DHCNURggq2\",\"CallBackEffects\":\"109,112,114\",\"CallBackReturnMapEffects\":\"TextElement_109^TextElement_39,TextElement_112^,TextElement_114^TextElement_41\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"111\",\"ElementType\":\"ButtonElement\",\"FormName\":\"\",\"GatherEffects\":\"112\",\"GatherImportMapEffects\":\"TextElement_112^\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"现在去评估\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"112\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_112\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"true\",\"MEName\":\"\",\"NameText\":\"流水号\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item33\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"113\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"自理能力等级：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"114\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_114\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"自理能力等级：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item34\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"115\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"多选：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"ElementType\":\"CheckElement\",\"NameText\":\"6.拔火罐\",\"RadioElementList\":[{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"121\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"1.穴位按摩\",\"OprationItemList\":[{\"NumberValue\":\"1\",\"Text\":\"1.穴位按摩\",\"Value\":\"1\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"120\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"2.穴位贴敷\",\"OprationItemList\":[{\"NumberValue\":\"2\",\"Text\":\"2.穴位贴敷\",\"Value\":\"2\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"119\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"3.艾灸\",\"OprationItemList\":[{\"NumberValue\":\"3\",\"Text\":\"3.艾灸\",\"Value\":\"3\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"118\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"4.药熨法\",\"OprationItemList\":[{\"NumberValue\":\"4\",\"Text\":\"4.药熨法\",\"Value\":\"4\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"117\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"5.耳穴贴压\",\"OprationItemList\":[{\"NumberValue\":\"5\",\"Text\":\"5.耳穴贴压\",\"Value\":\"5\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"116\",\"ElementType\":\"CheckElement\",\"FormName\":\"CheckElement_116\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"6.拔火罐\",\"OprationItemList\":[{\"NumberValue\":\"6\",\"Text\":\"6.拔火罐\",\"Value\":\"6\"}],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item35\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"}],\"SaveField\":\"Item35\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"122\",\"ElementType\":\"LableElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"签名：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"123\",\"ElementType\":\"TextElement\",\"FormName\":\"TextElement_123\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"单框双签：\",\"OprationItemList\":[],\"ParentId\":\"107\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"Item36\",\"Signature\":\"Double\",\"SignatureAuto\":\"true\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"107\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"124\",\"ElementType\":\"ContainerElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"保存\",\"OprationItemList\":[],\"ParentId\":\"\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"1\"},{\"BindingTemplateID\":\"\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"125\",\"ElementType\":\"ButtonElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"保存\",\"OprationItemList\":[],\"ParentId\":\"124\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"124\"},{\"BindingTemplateID\":\"DHCNURMoudPrnggq1\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"126\",\"ElementType\":\"ButtonElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"生成图片\",\"OprationItemList\":[],\"ParentId\":\"124\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"124\"},{\"BindingTemplateID\":\"DHCNURMoudPrnggq4\",\"CallBackEffects\":\"\",\"CallBackReturnMapEffects\":\"\",\"DataSourceRef\":\"\",\"DataSourceRefInfo\":\"\",\"DefaultValue\":\"\",\"Disable\":\"\",\"DoctorAdviceRule\":\"outside,$A72DA90F7B0C4559906299B5CE025D0C!2E0954B847C943518134268B4947A7F4\",\"DoctorAdviceRuleLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"ElementId\":\"127\",\"ElementType\":\"ButtonElement\",\"FormName\":\"\",\"GatherEffects\":\"\",\"GatherImportMapEffects\":\"\",\"IsHide\":\"\",\"MEName\":\"\",\"NameText\":\"打印\",\"OprationItemList\":[],\"ParentId\":\"124\",\"PointLen\":\"\",\"Required\":\"\",\"SaveField\":\"\",\"Signature\":\"\",\"SignatureAuto\":\"\",\"ToolTipText\":\"\",\"containerFlag\":\"\",\"containerId\":\"124\"}],\"ElementSets\":[{\"AllSatisfyFire\":\"false\",\"FormName\":\"DropRadioElement_104\",\"OnlySatisfyFire\":\"true\",\"SetDataList\":[{\"ChangeList\":[{\"Id\":\"106\",\"Type\":\"HasData\",\"Val\":\"确有药物过敏\"}],\"FormName\":\"DropRadioElement_104\",\"Val\":\"1\"},{\"ChangeList\":[{\"Id\":\"106\",\"Type\":\"HasData\",\"Val\":\"无药物过敏\"}],\"FormName\":\"DropRadioElement_104\",\"Val\":\"2\"},{\"ChangeList\":[{\"Id\":\"106\",\"Type\":\"HasData\",\"Val\":\"需要护士进行皮试\"}],\"FormName\":\"DropRadioElement_104\",\"Val\":\"3\"}]}],\"TemplateIndentity\":\"\",\"statisticsList\":[]},\"Table\":[]},\"firstIdList\":[],\"funList\":[{\"Content\":\"item2\",\"DataType\":\"\",\"DescriptionItems\":{\"item2\":\"测试\"},\"FormatFun\":\"\",\"Id\":\"1\",\"Name\":\"1\",\"RepId\":\"2700126\"},{\"Content\":\"item1/(item2*item2)\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"体重\",\"item2\":\"身高\"},\"FormatFun\":\"\",\"Id\":\"2\",\"Name\":\"BMI\",\"RepId\":\"2700126\"},{\"Content\":\"(item1+(item1+item2)-(item3+item4))*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"8衣着保持整洁\",\"item2\":\"30能保持自己卫生\",\"item3\":\"1肮\"},\"FormatFun\":\"\",\"Id\":\"3\",\"Name\":\"个人整洁\",\"RepId\":\"2700126\"},{\"Content\":\"item1/item2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"疗效\",\"item2\":\"不良\"},\"FormatFun\":\"\",\"Id\":\"4\",\"Name\":\"疗效指数\",\"RepId\":\"2700126\"},{\"Content\":\"item1/item2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"疗效\",\"item2\":\"不良\"},\"FormatFun\":\"\",\"Id\":\"5\",\"Name\":\"疗效指数1\",\"RepId\":\"2700126\"},{\"Content\":\"(item1+item2)/item3*item4\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"了啥空间很大萨克计划\",\"item2\":\"奥速度阿斯顿有\",\"item3\":\"爱\"},\"FormatFun\":\"\",\"Id\":\"6\",\"Name\":\"个人整洁1\",\"RepId\":\"2700126\"},{\"Content\":\"(8+item1/item2)*item3+item4\",\"DataType\":\"\",\"DescriptionItems\":{\"item\":\"\",\"item1\":\"iu啊所以丢牙髓炎对于\",\"item2\":\"i啊uiu随意丢啊于是\"},\"FormatFun\":\"\",\"Id\":\"7\",\"Name\":\"个人整洁a\",\"RepId\":\"2700126\"},{\"Content\":\"[8+（item1+item2）-（item3+item4）]*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"衣着\",\"item2\":\"卫生\",\"item3\":\"不洁\",\"item4\":\"吃\"},\"FormatFun\":\"\",\"Id\":\"8\",\"Name\":\"个人整洁2\",\"RepId\":\"2700126\"},{\"Content\":\"(8+(item1+item2)-(item3+item4))*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"12\",\"item2\":\"23\",\"item3\":\"34\",\"item4\":\"4\"},\"FormatFun\":\"\",\"Id\":\"9\",\"Name\":\"测试\",\"RepId\":\"2700126\"},{\"Content\":\"(item1+item2+item3+item4+item5+item6+item7)*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"1\",\"item2\":\"2\",\"item3\":\"3\",\"item4\":\"4\"},\"FormatFun\":\"\",\"Id\":\"10\",\"Name\":\"测试7\",\"RepId\":\"2700126\"},{\"Content\":\"item1*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"各项和\"},\"FormatFun\":\"\",\"Id\":\"11\",\"Name\":\"各项和\",\"RepId\":\"2700126\"},{\"Content\":\"(item1+item2+item3)*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"第一项\",\"item2\":\"第二项\",\"item3\":\"第三项\"},\"FormatFun\":\"\",\"Id\":\"12\",\"Name\":\"各项和*2\",\"RepId\":\"2700126\"},{\"Content\":\"(item1+item2+item3+item4)*2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"第一项\",\"item2\":\"第二项\",\"item3\":\"第三项\",\"item4\":\"\"},\"FormatFun\":\"\",\"Id\":\"13\",\"Name\":\"各项和*2.1\",\"RepId\":\"2700126\"},{\"Content\":\"128+item1-item2\",\"DataType\":\"\",\"DescriptionItems\":{\"item1\":\"积极因素\",\"item2\":\"消极因素\"},\"FormatFun\":\"\",\"Id\":\"14\",\"Name\":\"病情总评估\",\"RepId\":\"2700126\"}],\"msg\":\"\",\"status\":\"0\"}";

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    ElementDataBean elementDataBean = null;
                    try {
                        elementDataBean = gson.fromJson(jsonStr, ElementDataBean.class);

                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
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
                }
            }
        });
    }

    public static void getDataSource(String dataSourceRef, String episodeId, final GetDataSourceCallback callback) {
        NurRecordNewApiService.getDataSource(dataSourceRef, episodeId, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DataSourceBean dataSourceBean = gson.fromJson(jsonStr, DataSourceBean.class);
                        if (ObjectUtils.isEmpty(dataSourceBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(dataSourceBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(dataSourceBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(dataSourceBean.getMsg(), dataSourceBean.getMsg());
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

    public static void saveNewEmrData(String guId, String episodeId, String recordId, String parr, String printTemplateEmrCode, final RecDataCallback callback) {
        NurRecordNewApiService.saveNewEmrData(guId, episodeId, recordId, parr, printTemplateEmrCode, new NurRecordNewApiService.ServiceCallBack() {
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

    public static void getKnowledgeTree(final GetKnowledgeTreeCallback callback) {
        NurRecordNewApiService.getKnowledgeTree(new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        NurRecordKnowledgeTreeBean knowledgeTreeBean = gson.fromJson(jsonStr, NurRecordKnowledgeTreeBean.class);
                        if (ObjectUtils.isEmpty(knowledgeTreeBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(knowledgeTreeBean.getStatus())) {
                                if (callback != null) {
                                    Map KnowledgeMap = gson.fromJson(jsonStr, Map.class);
                                    callback.onSuccess(knowledgeTreeBean, KnowledgeMap);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(knowledgeTreeBean.getMsg(), knowledgeTreeBean.getMsg());
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

    public static void getKnowledgeContent(String knowledgeId, final GetKnowledgeContentCallback callback) {
        NurRecordNewApiService.getKnowledgeContent(knowledgeId, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        NurRecordKnowledgeContentBean knowledgeContentBean = gson.fromJson(jsonStr, NurRecordKnowledgeContentBean.class);
                        if (ObjectUtils.isEmpty(knowledgeContentBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(knowledgeContentBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(knowledgeContentBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(knowledgeContentBean.getMsg(), knowledgeContentBean.getMsg());
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

    public static void editTextConvert(String code, String text, String event, final EditTextConvertCallback callback) {
        NurRecordNewApiService.editTextConvert(code, text, event, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        com.dhcc.nursepro.workarea.nurrecordnew.bean.EditTextConvertBean editTextConvertBean = gson.fromJson(jsonStr, com.dhcc.nursepro.workarea.nurrecordnew.bean.EditTextConvertBean.class);
                        if (ObjectUtils.isEmpty(editTextConvertBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(editTextConvertBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(editTextConvertBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(editTextConvertBean.getMsg(), editTextConvertBean.getMsg());
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

    public static void GetOutSideData(String episodeId, String stDate, String endDate, final GetOutSideDataCallback callback) {
        NurRecordNewApiService.GetOutSideData(episodeId, stDate, endDate, new NurRecordNewApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

//                jsonStr = "{\"Status\":\"0\",\"fieldList\":[{\"fieldDesc\":\"日期\",\"fieldLinkNo\":\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\",\"fieldValue\":\"Date\"},{\"fieldDesc\":\"时间\",\"fieldLinkNo\":\"D02771674C9F4BD781A0473E71DE5CCA\",\"fieldValue\":\"Time\"},{\"fieldDesc\":\"体温\",\"fieldLinkNo\":\"15472224AC2C480CA17D414CC9A64CB4\",\"fieldValue\":\"temperature\"},{\"fieldDesc\":\"脉搏\",\"fieldLinkNo\":\"F59BB63B476245A094A6AC7F43E9B85F\",\"fieldValue\":\"pulse\"},{\"fieldDesc\":\"呼吸\",\"fieldLinkNo\":\"A074B2F25C844679B0B7D0A6D417EF94\",\"fieldValue\":\"breath\"},{\"fieldDesc\":\"收缩压\",\"fieldLinkNo\":\"91695486954847888A7D9D79FB13426D\",\"fieldValue\":\"sysPressure\"},{\"fieldDesc\":\"舒张压\",\"fieldLinkNo\":\"2E0954B847C943518134268B4947A7F4\",\"fieldValue\":\"diaPressure\"},{\"fieldDesc\":\"身高\",\"fieldLinkNo\":\"1DB6B840B4D94C379ABB6759F82BC165\",\"fieldValue\":\"hight\"},{\"fieldDesc\":\"体重\",\"fieldLinkNo\":\"2371F7A2B0E248F5A8B312A7D5C66DC8\",\"fieldValue\":\"weight\"},{\"fieldDesc\":\"疼痛\",\"fieldLinkNo\":\"45F40D8BD374408285EA3148AF5E1B32\",\"fieldValue\":\"pain\"}],\"msg\":\"成功\",\"msgcode\":\"999999\",\"tempList\":{\"rows\":[{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-02\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"16:50:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-02\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"16:56:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-02\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"17:02:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-02\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"17:07:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-02\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"17:08:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-08\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"06:29:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-08\",\"91695486954847888A7D9D79FB13426D\":\"\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"17:53:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"37\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-09\",\"91695486954847888A7D9D79FB13426D\":\"100\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"20\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"10:00:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"60\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}},{\"15472224AC2C480CA17D414CC9A64CB4\":\"36\",\"1DB6B840B4D94C379ABB6759F82BC165\":\"\",\"2371F7A2B0E248F5A8B312A7D5C66DC8\":\"\",\"2E0954B847C943518134268B4947A7F4\":\"\",\"45F40D8BD374408285EA3148AF5E1B32\":\"\",\"5F7D72D3E4FD4DBB9F8BDF599E54FA59\":\"2022-03-09\",\"91695486954847888A7D9D79FB13426D\":\"99\",\"A074B2F25C844679B0B7D0A6D417EF94\":\"33\",\"D02771674C9F4BD781A0473E71DE5CCA\":\"14:00:00\",\"F59BB63B476245A094A6AC7F43E9B85F\":\"88\",\"ID\":\"4113\",\"StatisticsInfo\":{\"color\":\"\",\"type\":\"\"}}],\"total\":\"9\"}}";

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetOutSideDataBean getOutSideDataBean = gson.fromJson(jsonStr, GetOutSideDataBean.class);

                        Map<String, Object> JsonMap = new HashMap<String, Object>();
                        JsonMap = (Map<String, Object>) gson.fromJson(jsonStr, JsonMap.getClass());

                        getOutSideDataBean.setMap(JsonMap);
                        if (ObjectUtils.isEmpty(getOutSideDataBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getOutSideDataBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getOutSideDataBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getOutSideDataBean.getMsg(), getOutSideDataBean.getMsg());
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

    public interface CareRecCommCallback extends CommonCallBack {
        void onSuccess(CareRecCommListBean careRecCommListBean);
    }

    public interface GetXmlValuesCallback extends CommonCallBack {
        void onSuccess(ElementDataBean elementDataBean);
    }

    public interface GetDataSourceCallback extends CommonCallBack {
        void onSuccess(DataSourceBean dataSourceBean);
    }

    public interface GetKnowledgeTreeCallback extends CommonCallBack {
        void onSuccess(NurRecordKnowledgeTreeBean knowledgeTreeBean, Map<String, String> knowledgeMap);
    }

    public interface GetKnowledgeContentCallback extends CommonCallBack {
        void onSuccess(NurRecordKnowledgeContentBean knowledgeContentBean);
    }

    public interface RecDataCallback extends CommonCallBack {
        void onSuccess(RecDataBean recDataBean);
    }

    public interface EditTextConvertCallback extends CommonCallBack {
        void onSuccess(com.dhcc.nursepro.workarea.nurrecordnew.bean.EditTextConvertBean editTextConvertBean);
    }

    public interface GetOutSideDataCallback extends CommonCallBack {
        void onSuccess(GetOutSideDataBean getOutSideDataBean);
    }

}
