package com.dhcc.nursepro.workarea.nurrecordnew.bean;

import java.util.List;

public class ElementDataBean {

    /**
     * Input : {"ElementBases":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-科室","DefaultValue":"","Disable":"","ElementId":"502","ElementType":"TextElement","FormName":"TextElement_502","IsHide":"","NameText":"科室","OprationItemList":[],"ParentId":"500","SaveField":"Item8","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!D079131CA99B4F7DAA6D9910F09C340D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-床号","DefaultValue":"","Disable":"","ElementId":"504","ElementType":"TextElement","FormName":"TextElement_504","IsHide":"","NameText":"床号","OprationItemList":[],"ParentId":"500","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!681BC3C438EA4038BBC14F68F3FABD5D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-姓名","DefaultValue":"","Disable":"","ElementId":"506","ElementType":"TextElement","FormName":"TextElement_506","IsHide":"","NameText":"姓名","OprationItemList":[],"ParentId":"500","SaveField":"Item10","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!EEC533CCE8214787982304357ABBD365","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-住院号","DefaultValue":"","Disable":"","ElementId":"508","ElementType":"TextElement","FormName":"TextElement_508","IsHide":"","NameText":"住院号","OprationItemList":[],"ParentId":"500","SaveField":"Item11","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!41D46590AB8947D78AB7C7C0DAC96221","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-性别","DefaultValue":"","Disable":"","ElementId":"510","ElementType":"TextElement","FormName":"TextElement_510","IsHide":"","NameText":"性别","OprationItemList":[],"ParentId":"500","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!95707AEE7AD44F8AAE492CDE78716ED4","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"512","ElementType":"TextElement","FormName":"TextElement_512","IsHide":"","NameText":"年龄","OprationItemList":[],"ParentId":"500","SaveField":"Item13","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"515","ElementType":"DateElement","FormName":"DateElement_515","IsHide":"","NameText":"入院时间:","OprationItemList":[],"ParentId":"500","SaveField":"Item15","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"517","ElementType":"TimeElement","FormName":"TimeElement_517","IsHide":"","NameText":"入院时间","OprationItemList":[],"ParentId":"500","SaveField":"Item16","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!1EEAC728336E44F7A81C181C5C02FD2C","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"514","ElementType":"TextElement","FormName":"TextElement_514","IsHide":"","NameText":"诊断","OprationItemList":[],"ParentId":"500","SaveField":"Item14","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"入院途径:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"518","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"急诊","OprationItemList":[{"NumberValue":"1","Text":"急诊","Value":"1"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"520","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"门诊","OprationItemList":[{"NumberValue":"2","Text":"门诊","Value":"2"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"521","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"病房转入","OprationItemList":[{"NumberValue":"3","Text":"病房转入","Value":"3"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"522","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"产房转入","OprationItemList":[{"NumberValue":"4","Text":"产房转入","Value":"4"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"523","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"手术室","OprationItemList":[{"NumberValue":"5","Text":"手术室","Value":"5"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"524","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"院外","OprationItemList":[{"NumberValue":"6","Text":"院外","Value":"6"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item17"},{"ElementType":"RadioElement","NameText":"入院方式:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"525","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"抱入","OprationItemList":[{"NumberValue":"1","Text":"抱入","Value":"1"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"527","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"转运暖箱","OprationItemList":[{"NumberValue":"2","Text":"转运暖箱","Value":"2"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item18"},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"528","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"自费","OprationItemList":[{"NumberValue":"1","Text":"自费","Value":"1"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"530","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"城乡居民医保","OprationItemList":[{"NumberValue":"2","Text":"城乡居民医保","Value":"2"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"531","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"商业保险","OprationItemList":[{"NumberValue":"3","Text":"商业保险","Value":"3"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"532","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"其他","OprationItemList":[{"NumberValue":"4","Text":"其他","Value":"4"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item19"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"inside,S0101T123,16","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"751","ElementType":"TextElement","FormName":"TextElement_751","IsHide":"","NameText":"压疮风险评估","OprationItemList":[],"ParentId":"749","SaveField":"Item78","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"755","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"低度危险","OprationItemList":[{"NumberValue":"1","Text":"低度危险","Value":"1"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"756","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"中度危险","OprationItemList":[{"NumberValue":"2","Text":"中度危险","Value":"2"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"757","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"高度危险","OprationItemList":[{"NumberValue":"3","Text":"高度危险","Value":"3"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"758","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"极高危","OprationItemList":[{"NumberValue":"4","Text":"极高危","Value":"4"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item79"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"780","ElementType":"TextElement","FormName":"TextElement_780","IsHide":"","NameText":"评估护士签名:","OprationItemList":[],"ParentId":"749","SaveField":"Item86","Signature":"CommonNOReplace","SignatureAuto":"true","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"2020-08-10","Disable":"","ElementId":"782","ElementType":"DateElement","FormName":"DateElement_782","IsHide":"","NameText":"评估记录时间:","OprationItemList":[],"ParentId":"749","SaveField":"CareDate","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"19:51:32","Disable":"","ElementId":"784","ElementType":"TimeElement","FormName":"TimeElement_784","IsHide":"","NameText":"评估时间","OprationItemList":[],"ParentId":"749","SaveField":"CareTime","Signature":"","SignatureAuto":"","ToolTipText":""}],"ElementSets":[{"ChangeList":[{"Id":"755","Items":"1","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"5","Val2":"7"},{"ChangeList":[{"Id":"755","Items":"2","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"8","Val2":"10"},{"ChangeList":[{"Id":"755","Items":"3","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"11","Val2":"12"},{"ChangeList":[{"Id":"755","Items":"4","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrText","Val":"13"}],"TemplateIndentity":"","statisticsList":[]}
     * Table : []
     */

    private DataBean data;
    /**
     * data : {"Input":{"ElementBases":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-科室","DefaultValue":"","Disable":"","ElementId":"502","ElementType":"TextElement","FormName":"TextElement_502","IsHide":"","NameText":"科室","OprationItemList":[],"ParentId":"500","SaveField":"Item8","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!D079131CA99B4F7DAA6D9910F09C340D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-床号","DefaultValue":"","Disable":"","ElementId":"504","ElementType":"TextElement","FormName":"TextElement_504","IsHide":"","NameText":"床号","OprationItemList":[],"ParentId":"500","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!681BC3C438EA4038BBC14F68F3FABD5D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-姓名","DefaultValue":"","Disable":"","ElementId":"506","ElementType":"TextElement","FormName":"TextElement_506","IsHide":"","NameText":"姓名","OprationItemList":[],"ParentId":"500","SaveField":"Item10","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!EEC533CCE8214787982304357ABBD365","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-住院号","DefaultValue":"","Disable":"","ElementId":"508","ElementType":"TextElement","FormName":"TextElement_508","IsHide":"","NameText":"住院号","OprationItemList":[],"ParentId":"500","SaveField":"Item11","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!41D46590AB8947D78AB7C7C0DAC96221","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-性别","DefaultValue":"","Disable":"","ElementId":"510","ElementType":"TextElement","FormName":"TextElement_510","IsHide":"","NameText":"性别","OprationItemList":[],"ParentId":"500","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!95707AEE7AD44F8AAE492CDE78716ED4","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"512","ElementType":"TextElement","FormName":"TextElement_512","IsHide":"","NameText":"年龄","OprationItemList":[],"ParentId":"500","SaveField":"Item13","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"515","ElementType":"DateElement","FormName":"DateElement_515","IsHide":"","NameText":"入院时间:","OprationItemList":[],"ParentId":"500","SaveField":"Item15","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"517","ElementType":"TimeElement","FormName":"TimeElement_517","IsHide":"","NameText":"入院时间","OprationItemList":[],"ParentId":"500","SaveField":"Item16","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!1EEAC728336E44F7A81C181C5C02FD2C","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"514","ElementType":"TextElement","FormName":"TextElement_514","IsHide":"","NameText":"诊断","OprationItemList":[],"ParentId":"500","SaveField":"Item14","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"入院途径:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"518","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"急诊","OprationItemList":[{"NumberValue":"1","Text":"急诊","Value":"1"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"520","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"门诊","OprationItemList":[{"NumberValue":"2","Text":"门诊","Value":"2"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"521","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"病房转入","OprationItemList":[{"NumberValue":"3","Text":"病房转入","Value":"3"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"522","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"产房转入","OprationItemList":[{"NumberValue":"4","Text":"产房转入","Value":"4"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"523","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"手术室","OprationItemList":[{"NumberValue":"5","Text":"手术室","Value":"5"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"524","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"院外","OprationItemList":[{"NumberValue":"6","Text":"院外","Value":"6"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item17"},{"ElementType":"RadioElement","NameText":"入院方式:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"525","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"抱入","OprationItemList":[{"NumberValue":"1","Text":"抱入","Value":"1"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"527","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"转运暖箱","OprationItemList":[{"NumberValue":"2","Text":"转运暖箱","Value":"2"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item18"},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"528","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"自费","OprationItemList":[{"NumberValue":"1","Text":"自费","Value":"1"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"530","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"城乡居民医保","OprationItemList":[{"NumberValue":"2","Text":"城乡居民医保","Value":"2"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"531","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"商业保险","OprationItemList":[{"NumberValue":"3","Text":"商业保险","Value":"3"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"532","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"其他","OprationItemList":[{"NumberValue":"4","Text":"其他","Value":"4"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item19"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"inside,S0101T123,16","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"751","ElementType":"TextElement","FormName":"TextElement_751","IsHide":"","NameText":"压疮风险评估","OprationItemList":[],"ParentId":"749","SaveField":"Item78","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"755","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"低度危险","OprationItemList":[{"NumberValue":"1","Text":"低度危险","Value":"1"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"756","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"中度危险","OprationItemList":[{"NumberValue":"2","Text":"中度危险","Value":"2"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"757","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"高度危险","OprationItemList":[{"NumberValue":"3","Text":"高度危险","Value":"3"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"758","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"极高危","OprationItemList":[{"NumberValue":"4","Text":"极高危","Value":"4"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item79"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"780","ElementType":"TextElement","FormName":"TextElement_780","IsHide":"","NameText":"评估护士签名:","OprationItemList":[],"ParentId":"749","SaveField":"Item86","Signature":"CommonNOReplace","SignatureAuto":"true","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"2020-08-10","Disable":"","ElementId":"782","ElementType":"DateElement","FormName":"DateElement_782","IsHide":"","NameText":"评估记录时间:","OprationItemList":[],"ParentId":"749","SaveField":"CareDate","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"19:51:32","Disable":"","ElementId":"784","ElementType":"TimeElement","FormName":"TimeElement_784","IsHide":"","NameText":"评估时间","OprationItemList":[],"ParentId":"749","SaveField":"CareTime","Signature":"","SignatureAuto":"","ToolTipText":""}],"ElementSets":[{"ChangeList":[{"Id":"755","Items":"1","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"5","Val2":"7"},{"ChangeList":[{"Id":"755","Items":"2","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"8","Val2":"10"},{"ChangeList":[{"Id":"755","Items":"3","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"11","Val2":"12"},{"ChangeList":[{"Id":"755","Items":"4","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrText","Val":"13"}],"TemplateIndentity":"","statisticsList":[]},"Table":[]}
     * firstIdList : []
     * msg :
     * status : 0
     */

    private String msg;
    private String status;
    private List<FirstIdListBean> firstIdList;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FirstIdListBean> getFirstIdList() {
        return firstIdList;
    }

    public void setFirstIdList(List<FirstIdListBean> firstIdList) {
        this.firstIdList = firstIdList;
    }

    public static class DataBean {
        /**
         * ElementBases : [{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-科室","DefaultValue":"","Disable":"","ElementId":"502","ElementType":"TextElement","FormName":"TextElement_502","IsHide":"","NameText":"科室","OprationItemList":[],"ParentId":"500","SaveField":"Item8","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!D079131CA99B4F7DAA6D9910F09C340D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-床号","DefaultValue":"","Disable":"","ElementId":"504","ElementType":"TextElement","FormName":"TextElement_504","IsHide":"","NameText":"床号","OprationItemList":[],"ParentId":"500","SaveField":"Item9","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!681BC3C438EA4038BBC14F68F3FABD5D","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-姓名","DefaultValue":"","Disable":"","ElementId":"506","ElementType":"TextElement","FormName":"TextElement_506","IsHide":"","NameText":"姓名","OprationItemList":[],"ParentId":"500","SaveField":"Item10","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!EEC533CCE8214787982304357ABBD365","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-住院号","DefaultValue":"","Disable":"","ElementId":"508","ElementType":"TextElement","FormName":"TextElement_508","IsHide":"","NameText":"住院号","OprationItemList":[],"ParentId":"500","SaveField":"Item11","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!41D46590AB8947D78AB7C7C0DAC96221","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-性别","DefaultValue":"","Disable":"","ElementId":"510","ElementType":"TextElement","FormName":"TextElement_510","IsHide":"","NameText":"性别","OprationItemList":[],"ParentId":"500","SaveField":"Item12","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!95707AEE7AD44F8AAE492CDE78716ED4","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"512","ElementType":"TextElement","FormName":"TextElement_512","IsHide":"","NameText":"年龄","OprationItemList":[],"ParentId":"500","SaveField":"Item13","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"515","ElementType":"DateElement","FormName":"DateElement_515","IsHide":"","NameText":"入院时间:","OprationItemList":[],"ParentId":"500","SaveField":"Item15","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-年龄","DefaultValue":"","Disable":"","ElementId":"517","ElementType":"TimeElement","FormName":"TimeElement_517","IsHide":"","NameText":"入院时间","OprationItemList":[],"ParentId":"500","SaveField":"Item16","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!1EEAC728336E44F7A81C181C5C02FD2C","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"514","ElementType":"TextElement","FormName":"TextElement_514","IsHide":"","NameText":"诊断","OprationItemList":[],"ParentId":"500","SaveField":"Item14","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"入院途径:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"518","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"急诊","OprationItemList":[{"NumberValue":"1","Text":"急诊","Value":"1"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"520","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"门诊","OprationItemList":[{"NumberValue":"2","Text":"门诊","Value":"2"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"521","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"病房转入","OprationItemList":[{"NumberValue":"3","Text":"病房转入","Value":"3"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"522","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"产房转入","OprationItemList":[{"NumberValue":"4","Text":"产房转入","Value":"4"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"523","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"手术室","OprationItemList":[{"NumberValue":"5","Text":"手术室","Value":"5"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"524","ElementType":"RadioElement","FormName":"RadioElement_518","IsHide":"","NameText":"院外","OprationItemList":[{"NumberValue":"6","Text":"院外","Value":"6"}],"ParentId":"500","SaveField":"Item17","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item17"},{"ElementType":"RadioElement","NameText":"入院方式:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"525","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"抱入","OprationItemList":[{"NumberValue":"1","Text":"抱入","Value":"1"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"527","ElementType":"RadioElement","FormName":"RadioElement_525","IsHide":"","NameText":"转运暖箱","OprationItemList":[{"NumberValue":"2","Text":"转运暖箱","Value":"2"}],"ParentId":"500","SaveField":"Item18","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item18"},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"528","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"自费","OprationItemList":[{"NumberValue":"1","Text":"自费","Value":"1"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"530","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"城乡居民医保","OprationItemList":[{"NumberValue":"2","Text":"城乡居民医保","Value":"2"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"531","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"商业保险","OprationItemList":[{"NumberValue":"3","Text":"商业保险","Value":"3"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-患者就诊基本信息-诊断","DefaultValue":"","Disable":"","ElementId":"532","ElementType":"RadioElement","FormName":"RadioElement_528","IsHide":"","NameText":"其他","OprationItemList":[{"NumberValue":"4","Text":"其他","Value":"4"}],"ParentId":"500","SaveField":"Item19","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item19"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"inside,S0101T123,16","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"751","ElementType":"TextElement","FormName":"TextElement_751","IsHide":"","NameText":"压疮风险评估","OprationItemList":[],"ParentId":"749","SaveField":"Item78","Signature":"","SignatureAuto":"","ToolTipText":""},{"ElementType":"RadioElement","NameText":"费用支付:","RadioElementList":[{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"755","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"低度危险","OprationItemList":[{"NumberValue":"1","Text":"低度危险","Value":"1"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"756","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"中度危险","OprationItemList":[{"NumberValue":"2","Text":"中度危险","Value":"2"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"757","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"高度危险","OprationItemList":[{"NumberValue":"3","Text":"高度危险","Value":"3"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"758","ElementType":"RadioElement","FormName":"RadioElement_755","IsHide":"","NameText":"极高危","OprationItemList":[{"NumberValue":"4","Text":"极高危","Value":"4"}],"ParentId":"749","SaveField":"Item79","Signature":"","SignatureAuto":"","ToolTipText":""}],"SaveField":"Item79"},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"","Disable":"","ElementId":"780","ElementType":"TextElement","FormName":"TextElement_780","IsHide":"","NameText":"评估护士签名:","OprationItemList":[],"ParentId":"749","SaveField":"Item86","Signature":"CommonNOReplace","SignatureAuto":"true","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"2020-08-10","Disable":"","ElementId":"782","ElementType":"DateElement","FormName":"DateElement_782","IsHide":"","NameText":"评估记录时间:","OprationItemList":[],"ParentId":"749","SaveField":"CareDate","Signature":"","SignatureAuto":"","ToolTipText":""},{"BindingTemplateID":"DHCNURXSRYCLR","CallBackEffects":"","CallBackReturnMapEffects":"","DataSourceRef":"","DataSourceRefInfo":"接收-文本框赋值-新生儿压疮录入-新生儿首次压疮","DefaultValue":"19:51:32","Disable":"","ElementId":"784","ElementType":"TimeElement","FormName":"TimeElement_784","IsHide":"","NameText":"评估时间","OprationItemList":[],"ParentId":"749","SaveField":"CareTime","Signature":"","SignatureAuto":"","ToolTipText":""}]
         * ElementSets : [{"ChangeList":[{"Id":"755","Items":"1","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"5","Val2":"7"},{"ChangeList":[{"Id":"755","Items":"2","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"8","Val2":"10"},{"ChangeList":[{"Id":"755","Items":"3","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrEqText1LeEqText2","Val":"11","Val2":"12"},{"ChangeList":[{"Id":"755","Items":"4","Type":"HasData"}],"FormName":"TextElement_751","Sign":"GrText","Val":"13"}]
         * TemplateIndentity :
         * statisticsList : []
         */

        private InputBean Input;
        private List<?> Table;

        public InputBean getInput() {
            return Input;
        }

        public void setInput(InputBean Input) {
            this.Input = Input;
        }

        public List<?> getTable() {
            return Table;
        }

        public void setTable(List<?> Table) {
            this.Table = Table;
        }

        public static class InputBean {
            private String TemplateIndentity;
            /**
             * BindingTemplateID :
             * CallBackEffects :
             * CallBackReturnMapEffects :
             * DataSourceRef : outside,$5546AE29F2AD4EB38929EC69F28F4189,$5546AE29F2AD4EB38929EC69F28F4189!9E89131DE2724B219617D7B52D49F7C1
             * DataSourceRefInfo : 接收-文本框赋值-患者就诊基本信息-科室
             * DefaultValue :
             * Disable :
             * ElementId : 502
             * ElementType : TextElement
             * FormName : TextElement_502
             * IsHide :
             * NameText : 科室
             * OprationItemList : []
             * ParentId : 500
             * SaveField : Item8
             * Signature :
             * SignatureAuto :
             * ToolTipText :
             */

            private List<ElementBasesBean> ElementBases;
            /**
             * ChangeList : [{"Id":"755","Items":"1","Type":"HasData"}]
             * FormName : TextElement_751
             * Sign : GrEqText1LeEqText2
             * Val : 5
             * Val2 : 7
             */

            private List<ElementSetsBean> ElementSets;
            private List<StatisticsListBean> statisticsList;

            public String getTemplateIndentity() {
                return TemplateIndentity;
            }

            public void setTemplateIndentity(String TemplateIndentity) {
                this.TemplateIndentity = TemplateIndentity;
            }

            public List<ElementBasesBean> getElementBases() {
                return ElementBases;
            }

            public void setElementBases(List<ElementBasesBean> ElementBases) {
                this.ElementBases = ElementBases;
            }

            public List<ElementSetsBean> getElementSets() {
                return ElementSets;
            }

            public void setElementSets(List<ElementSetsBean> ElementSets) {
                this.ElementSets = ElementSets;
            }

            public List<StatisticsListBean> getStatisticsList() {
                return statisticsList;
            }

            public void setStatisticsList(List<StatisticsListBean> statisticsList) {
                this.statisticsList = statisticsList;
            }

            public static class ElementBasesBean {
                private String BindingTemplateID;
                private String CallBackEffects;
                private String CallBackReturnMapEffects;
                private String DataSourceRef;
                private String DataSourceRefInfo;
                private String DefaultValue;
                private String Disable;
                private String ElementId;
                private String ElementType;
                private String FormName;
                private String IsHide;
                private String NameText;
                private String ParentId;
                private String Required;
                private String SaveField;
                private String Signature;
                private String SignatureAuto;
                private String ToolTipText;
                private List<OprationItemListBean> OprationItemList;
                private List<RadioElementListBean> RadioElementList;

                public String getBindingTemplateID() {
                    return BindingTemplateID;
                }

                public void setBindingTemplateID(String BindingTemplateID) {
                    this.BindingTemplateID = BindingTemplateID;
                }

                public String getCallBackEffects() {
                    return CallBackEffects;
                }

                public void setCallBackEffects(String CallBackEffects) {
                    this.CallBackEffects = CallBackEffects;
                }

                public String getCallBackReturnMapEffects() {
                    return CallBackReturnMapEffects;
                }

                public void setCallBackReturnMapEffects(String CallBackReturnMapEffects) {
                    this.CallBackReturnMapEffects = CallBackReturnMapEffects;
                }

                public String getDataSourceRef() {
                    return DataSourceRef;
                }

                public void setDataSourceRef(String DataSourceRef) {
                    this.DataSourceRef = DataSourceRef;
                }

                public String getDataSourceRefInfo() {
                    return DataSourceRefInfo;
                }

                public void setDataSourceRefInfo(String DataSourceRefInfo) {
                    this.DataSourceRefInfo = DataSourceRefInfo;
                }

                public String getDefaultValue() {
                    return DefaultValue;
                }

                public void setDefaultValue(String DefaultValue) {
                    this.DefaultValue = DefaultValue;
                }

                public String getDisable() {
                    return Disable;
                }

                public void setDisable(String Disable) {
                    this.Disable = Disable;
                }

                public String getElementId() {
                    return ElementId;
                }

                public void setElementId(String ElementId) {
                    this.ElementId = ElementId;
                }

                public String getElementType() {
                    return ElementType;
                }

                public void setElementType(String ElementType) {
                    this.ElementType = ElementType;
                }

                public String getFormName() {
                    return FormName;
                }

                public void setFormName(String FormName) {
                    this.FormName = FormName;
                }

                public String getIsHide() {
                    return IsHide;
                }

                public void setIsHide(String IsHide) {
                    this.IsHide = IsHide;
                }

                public String getNameText() {
                    return NameText;
                }

                public void setNameText(String NameText) {
                    this.NameText = NameText;
                }

                public String getParentId() {
                    return ParentId;
                }

                public void setParentId(String ParentId) {
                    this.ParentId = ParentId;
                }

                public String getRequired() {
                    return Required;
                }

                public void setRequired(String Required) {
                    this.Required = Required;
                }

                public String getSaveField() {
                    return SaveField;
                }

                public void setSaveField(String SaveField) {
                    this.SaveField = SaveField;
                }

                public String getSignature() {
                    return Signature;
                }

                public void setSignature(String Signature) {
                    this.Signature = Signature;
                }

                public String getSignatureAuto() {
                    return SignatureAuto;
                }

                public void setSignatureAuto(String SignatureAuto) {
                    this.SignatureAuto = SignatureAuto;
                }

                public String getToolTipText() {
                    return ToolTipText;
                }

                public void setToolTipText(String ToolTipText) {
                    this.ToolTipText = ToolTipText;
                }

                public List<OprationItemListBean> getOprationItemList() {
                    return OprationItemList;
                }

                public void setOprationItemList(List<OprationItemListBean> OprationItemList) {
                    this.OprationItemList = OprationItemList;
                }

                public List<RadioElementListBean> getRadioElementList() {
                    return RadioElementList;
                }

                public void setRadioElementList(List<RadioElementListBean> RadioElementList) {
                    this.RadioElementList = RadioElementList;
                }

                public static class OprationItemListBean {
                    /**
                     * NumberValue : 1
                     * Text : 完全受限（1分）
                     * Value : 1
                     */

                    private String IsSelect;
                    private String NumberValue;
                    private String Text;
                    private String Value;

                    public String getIsSelect() {
                        return IsSelect;
                    }

                    public void setIsSelect(String isSelect) {
                        IsSelect = isSelect;
                    }

                    public String getNumberValue() {
                        return NumberValue;
                    }

                    public void setNumberValue(String NumberValue) {
                        this.NumberValue = NumberValue;
                    }

                    public String getText() {
                        return Text;
                    }

                    public void setText(String Text) {
                        this.Text = Text;
                    }

                    public String getValue() {
                        return Value;
                    }

                    public void setValue(String Value) {
                        this.Value = Value;
                    }
                }

                public static class RadioElementListBean {
                    /**
                     * BindingTemplateID :
                     * CallBackEffects :
                     * CallBackReturnMapEffects :
                     * DataSourceRef :
                     * DefaultValue :
                     * Disable :
                     * ElementId : 44
                     * ElementType : RadioElement
                     * FormName : RadioElement_44
                     * IsHide :
                     * NameText : 急诊
                     * OprationItemList : [{"NumberValue":"1","Text":"急诊","Value":"1"}]
                     * ParentId : 40
                     * Required : true
                     * SaveField : Item7
                     * ToolTipText :
                     */

                    private String BindingTemplateID;
                    private String CallBackEffects;
                    private String CallBackReturnMapEffects;
                    private String DataSourceRef;
                    private String DefaultValue;
                    private String Disable;
                    private String ElementId;
                    private String ElementType;
                    private String FormName;
                    private String IsHide;
                    private String NameText;
                    private String ParentId;
                    private String IsSelect;
                    private String Required;
                    private String SaveField;
                    private String ToolTipText;
                    private List<OprationItemListBean> OprationItemList;

                    public String getBindingTemplateID() {
                        return BindingTemplateID;
                    }

                    public void setBindingTemplateID(String BindingTemplateID) {
                        this.BindingTemplateID = BindingTemplateID;
                    }

                    public String getCallBackEffects() {
                        return CallBackEffects;
                    }

                    public void setCallBackEffects(String CallBackEffects) {
                        this.CallBackEffects = CallBackEffects;
                    }

                    public String getCallBackReturnMapEffects() {
                        return CallBackReturnMapEffects;
                    }

                    public void setCallBackReturnMapEffects(String CallBackReturnMapEffects) {
                        this.CallBackReturnMapEffects = CallBackReturnMapEffects;
                    }

                    public String getDataSourceRef() {
                        return DataSourceRef;
                    }

                    public void setDataSourceRef(String DataSourceRef) {
                        this.DataSourceRef = DataSourceRef;
                    }

                    public String getDefaultValue() {
                        return DefaultValue;
                    }

                    public void setDefaultValue(String DefaultValue) {
                        this.DefaultValue = DefaultValue;
                    }

                    public String getDisable() {
                        return Disable;
                    }

                    public void setDisable(String Disable) {
                        this.Disable = Disable;
                    }

                    public String getElementId() {
                        return ElementId;
                    }

                    public void setElementId(String ElementId) {
                        this.ElementId = ElementId;
                    }

                    public String getElementType() {
                        return ElementType;
                    }

                    public void setElementType(String ElementType) {
                        this.ElementType = ElementType;
                    }

                    public String getFormName() {
                        return FormName;
                    }

                    public void setFormName(String FormName) {
                        this.FormName = FormName;
                    }

                    public String getIsHide() {
                        return IsHide;
                    }

                    public void setIsHide(String IsHide) {
                        this.IsHide = IsHide;
                    }

                    public String getNameText() {
                        return NameText;
                    }

                    public void setNameText(String NameText) {
                        this.NameText = NameText;
                    }

                    public String getParentId() {
                        return ParentId;
                    }

                    public void setParentId(String ParentId) {
                        this.ParentId = ParentId;
                    }

                    public String getIsSelect() {
                        return IsSelect;
                    }

                    public void setIsSelect(String isSelect) {
                        IsSelect = isSelect;
                    }

                    public String getRequired() {
                        return Required;
                    }

                    public void setRequired(String Required) {
                        this.Required = Required;
                    }

                    public String getSaveField() {
                        return SaveField;
                    }

                    public void setSaveField(String SaveField) {
                        this.SaveField = SaveField;
                    }

                    public String getToolTipText() {
                        return ToolTipText;
                    }

                    public void setToolTipText(String ToolTipText) {
                        this.ToolTipText = ToolTipText;
                    }

                    public List<OprationItemListBean> getOprationItemList() {
                        return OprationItemList;
                    }

                    public void setOprationItemList(List<OprationItemListBean> OprationItemList) {
                        this.OprationItemList = OprationItemList;
                    }

                    public static class OprationItemListBean {
                        /**
                         * NumberValue : 1
                         * Text : 急诊
                         * Value : 1
                         */

                        private String NumberValue;
                        private String Text;
                        private String Value;

                        public String getNumberValue() {
                            return NumberValue;
                        }

                        public void setNumberValue(String NumberValue) {
                            this.NumberValue = NumberValue;
                        }

                        public String getText() {
                            return Text;
                        }

                        public void setText(String Text) {
                            this.Text = Text;
                        }

                        public String getValue() {
                            return Value;
                        }

                        public void setValue(String Value) {
                            this.Value = Value;
                        }
                    }
                }
            }

            public static class ElementSetsBean {
                private String FormName;
                private String Sign;
                private String Val;
                private String Val2;
                /**
                 * Id : 755
                 * Items : 1
                 * Type : HasData
                 */

                private List<ChangeListBean> ChangeList;

                public String getFormName() {
                    return FormName;
                }

                public void setFormName(String FormName) {
                    this.FormName = FormName;
                }

                public String getSign() {
                    return Sign;
                }

                public void setSign(String Sign) {
                    this.Sign = Sign;
                }

                public String getVal() {
                    return Val;
                }

                public void setVal(String Val) {
                    this.Val = Val;
                }

                public String getVal2() {
                    return Val2;
                }

                public void setVal2(String Val2) {
                    this.Val2 = Val2;
                }

                public List<ChangeListBean> getChangeList() {
                    return ChangeList;
                }

                public void setChangeList(List<ChangeListBean> ChangeList) {
                    this.ChangeList = ChangeList;
                }

                public static class ChangeListBean {
                    private String Id;
                    private String Items;
                    private String Type;
                    private String Val;

                    public String getId() {
                        return Id;
                    }

                    public void setId(String Id) {
                        this.Id = Id;
                    }

                    public String getItems() {
                        return Items;
                    }

                    public void setItems(String Items) {
                        this.Items = Items;
                    }

                    public String getType() {
                        return Type;
                    }

                    public void setType(String Type) {
                        this.Type = Type;
                    }

                    public String getVal() {
                        return Val;
                    }

                    public void setVal(String val) {
                        Val = val;
                    }
                }
            }

            public static class StatisticsListBean {
                /**
                 * CalType : Sum
                 * Effects : 77,79,81,83,85,87,89,91,93,95,97
                 * Id : 99
                 */

                private String CalType;
                private String Effects;
                private String Id;

                public String getCalType() {
                    return CalType;
                }

                public void setCalType(String CalType) {
                    this.CalType = CalType;
                }

                public String getEffects() {
                    return Effects;
                }

                public void setEffects(String Effects) {
                    this.Effects = Effects;
                }

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }
            }
        }
    }

    public static class FirstIdListBean {
        /**
         * EmrCode : DHCNURBarthelLR
         * GuId : 6DCA2DE8907646B7B621D24364200F1D
         * RecId : 7746
         */

        private String EmrCode;
        private String GuId;
        private String RecId;

        public String getEmrCode() {
            return EmrCode;
        }

        public void setEmrCode(String EmrCode) {
            this.EmrCode = EmrCode;
        }

        public String getGuId() {
            return GuId;
        }

        public void setGuId(String GuId) {
            this.GuId = GuId;
        }

        public String getRecId() {
            return RecId;
        }

        public void setRecId(String RecId) {
            this.RecId = RecId;
        }
    }
}
