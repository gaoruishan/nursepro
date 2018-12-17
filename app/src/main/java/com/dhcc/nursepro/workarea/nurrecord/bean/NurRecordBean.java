package com.dhcc.nursepro.workarea.nurrecord.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurrecord.bean
 * <p>
 * author Q
 * Date: 2018/12/3
 * Time:9:50
 */
public class NurRecordBean {
    /**
     * modelList : [{"LinkInfo":[],"ModelSort":"1","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label345","itemDesc":"入院综合评估及护理记录单","itemType":"T","itemValue":"入院综合评估及护理记录单","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||376","titleHiddeFlag":"0","toastStr":"你好啊","value":"","width":"261"},{"LinkInfo":[],"ModelSort":"2","PatInfo":"ctLocDesc","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item4","itemDesc":"科室","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||377","titleHiddeFlag":"1","toastStr":"哪个？","value":"","width":"149"},{"LinkInfo":[],"ModelSort":"3","PatInfo":"admReason","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item5","itemDesc":"床号","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||378","titleHiddeFlag":"1","toastStr":"","value":"","width":"46"},{"LinkInfo":[],"ModelSort":"4","PatInfo":"name","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item1","itemDesc":"姓名","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||379","titleHiddeFlag":"1","toastStr":"","value":"","width":"63"},{"LinkInfo":[],"ModelSort":"5","PatInfo":"sex","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item2","itemDesc":"性别","itemType":"R","itemValue":"男!女!","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||380","titleHiddeFlag":"1","toastStr":"","value":"","width":"45"},{"LinkInfo":[],"ModelSort":"6","PatInfo":"age","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item3","itemDesc":"年龄","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||381","titleHiddeFlag":"1","toastStr":"","value":"","width":"39"},{"LinkInfo":[],"ModelSort":"7","PatInfo":"medicareNo","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item6","itemDesc":"病案号","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"1","singleCheck":"1","subRowId":"1||382","titleHiddeFlag":"1","toastStr":"","value":"","width":"109"},{"LinkInfo":[],"ModelSort":"8","PatInfo":"","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"18","itemCode":"_Label35","itemDesc":"一、一般资料","itemType":"T","itemValue":"一、一般资料","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||383","titleHiddeFlag":"0","toastStr":"","value":"","width":"123"},{"LinkInfo":[],"ModelSort":"9","PatInfo":"inHospDateTime","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"22","itemCode":"Item13","itemDesc":"入院日期","itemType":"D","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||384","titleHiddeFlag":"1","toastStr":"","value":"","width":"106"},{"LinkInfo":[],"ModelSort":"10","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"23","itemCode":"Item14","itemDesc":"入院时间","itemType":"Ti","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||385","titleHiddeFlag":"1","toastStr":"","value":"","width":"107"},{"LinkInfo":[],"ModelSort":"11","PatInfo":"diagnosis","Unit":"","editCondition":"","editFlag":"false","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item18","itemDesc":"入院诊断","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||386","titleHiddeFlag":"1","toastStr":"","value":"","width":"398"},{"LinkInfo":[],"ModelSort":"12","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item15","itemDesc":"入院方式","itemType":"R","itemValue":"步行!轮椅!平车!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||387","titleHiddeFlag":"1","toastStr":"","value":"","width":"106"},{"LinkInfo":[],"ModelSort":"13","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item16","itemDesc":"费用支付","itemType":"R","itemValue":"全自费!省医保!省慢病!市医保!市慢病!新型农村合作医疗!异地医保!商业医疗保险!贫困救助!全公费!本院职工!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||388","titleHiddeFlag":"1","toastStr":"支付方式","value":"","width":"107"},{"LinkInfo":[],"ModelSort":"14","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item7","itemDesc":"民族","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||389","titleHiddeFlag":"1","toastStr":"","value":"","width":"95"},{"LinkInfo":[],"ModelSort":"15","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item17","itemDesc":"宗教","itemType":"R","itemValue":"无!佛教!伊斯兰教!基督教!不知晓!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||390","titleHiddeFlag":"1","toastStr":"","value":"","width":"111"},{"LinkInfo":[],"ModelSort":"16","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item8","itemDesc":"职业","itemType":"R","itemValue":"专业技术人员!个体经营者!企业管理人员!农民!教师!国家公务员!学生!工人!无业人员!现役军人!职员!自由职业者!退离休人员!无业!本院!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||391","titleHiddeFlag":"1","toastStr":"","value":"","width":"107"},{"LinkInfo":[],"ModelSort":"17","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item19","itemDesc":"教育","itemType":"R","itemValue":"文盲!小学!中学!高中!大专!大学以上!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||392","titleHiddeFlag":"1","toastStr":"","value":"","width":"107"},{"LinkInfo":[],"ModelSort":"18","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item10","itemDesc":"婚姻状况","itemType":"R","itemValue":"未婚!已婚!离婚!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||393","titleHiddeFlag":"1","toastStr":"","value":"","width":"95"},{"LinkInfo":[],"ModelSort":"19","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item78","itemDesc":"家属:子","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||394","titleHiddeFlag":"1","toastStr":"","value":"","width":"21"},{"LinkInfo":[],"ModelSort":"20","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label206","itemDesc":"人","itemType":"T","itemValue":"人","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||395","titleHiddeFlag":"0","toastStr":"","value":"","width":"19"},{"LinkInfo":[],"ModelSort":"21","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item79","itemDesc":"女","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||396","titleHiddeFlag":"1","toastStr":"","value":"","width":"22"},{"LinkInfo":[],"ModelSort":"22","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label207","itemDesc":"人","itemType":"T","itemValue":"人","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||397","titleHiddeFlag":"0","toastStr":"","value":"","width":"19"},{"LinkInfo":[],"ModelSort":"23","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item11","itemDesc":"联系人姓名","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||398","titleHiddeFlag":"1","toastStr":"","value":"","width":"106"},{"LinkInfo":[],"ModelSort":"24","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item12","itemDesc":"电话","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||399","titleHiddeFlag":"1","toastStr":"","value":"","width":"107"},{"LinkInfo":[],"ModelSort":"25","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item76","itemDesc":"与患者关系","itemType":"R","itemValue":"本人或户主!配偶!子!女!孙子、孙女或外孙子、外孙女!父母!祖父母或外祖父母!兄弟姐妹!同事同学!监护人!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||400","titleHiddeFlag":"1","toastStr":"","value":"","width":"97"},{"LinkInfo":[],"ModelSort":"26","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"23","itemCode":"_Label73","itemDesc":"二、护理评估","itemType":"T","itemValue":"二、护理评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||401","titleHiddeFlag":"0","toastStr":"","value":"","width":"126"},{"LinkInfo":[],"ModelSort":"27","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"9","height":"17","itemCode":"_Label211","itemDesc":"生命体征:","itemType":"T","itemValue":"生命体征:","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||402","titleHiddeFlag":"0","toastStr":"","value":"","width":"66"},{"LinkInfo":[],"ModelSort":"28","PatInfo":"","Unit":"℃","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item67","itemDesc":"T","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||403","titleHiddeFlag":"1","toastStr":"","value":"","width":"43"},{"LinkInfo":[],"ModelSort":"30","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item68","itemDesc":"P（次/分）","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||405","titleHiddeFlag":"1","toastStr":"","value":"","width":"38"},{"LinkInfo":[],"ModelSort":"31","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label167","itemDesc":"次/分","itemType":"T","itemValue":"次/分","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||406","titleHiddeFlag":"0","toastStr":"","value":"","width":"37"},{"LinkInfo":[],"ModelSort":"32","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item69","itemDesc":"R（次/分）","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||407","titleHiddeFlag":"1","toastStr":"","value":"","width":"30"},{"LinkInfo":[],"ModelSort":"33","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label165","itemDesc":"次/分","itemType":"T","itemValue":"次/分","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||408","titleHiddeFlag":"0","toastStr":"","value":"","width":"37"},{"LinkInfo":[],"ModelSort":"34","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item70","itemDesc":"BP（mmHg）","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||409","titleHiddeFlag":"1","toastStr":"","value":"","width":"36"},{"LinkInfo":[],"ModelSort":"35","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"11","itemCode":"_Label162","itemDesc":"/","itemType":"T","itemValue":"/","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||410","titleHiddeFlag":"0","toastStr":"","value":"","width":"10"},{"LinkInfo":[],"ModelSort":"36","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item71","itemDesc":"BP (mmHg)1","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||411","titleHiddeFlag":"0","toastStr":"","value":"","width":"36"},{"LinkInfo":[],"ModelSort":"37","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label166","itemDesc":"mmHg","itemType":"T","itemValue":"mmHg","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||412","titleHiddeFlag":"0","toastStr":"","value":"","width":"34"},{"LinkInfo":[],"ModelSort":"38","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item72","itemDesc":"体重","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||413","titleHiddeFlag":"1","toastStr":"","value":"","width":"37"},{"LinkInfo":[],"ModelSort":"39","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"15","itemCode":"_Label164","itemDesc":"kg","itemType":"T","itemValue":"kg","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||414","titleHiddeFlag":"0","toastStr":"","value":"","width":"32"},{"LinkInfo":[],"ModelSort":"40","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item300","itemDesc":"疼痛id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||415","titleHiddeFlag":"0","toastStr":"","value":"","width":"56"},{"LinkInfo":[],"ModelSort":"41","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"27","itemCode":"Item80","itemDesc":"疼痛评估:","itemType":"C","itemValue":"无;有;","itemdeValue":"无","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||416","titleHiddeFlag":"1","toastStr":"","value":"","width":"116"},{"LinkInfo":[],"ModelSort":"42","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item81","itemDesc":"疼痛评估描述","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||417","titleHiddeFlag":"0","toastStr":"","value":"","width":"331"},{"LinkInfo":[{"linkItemCode":"Item15","linkItemCon":"","linkModel":"DHCNURPGD_AZTTPFK","linkRangeCon":"","linkType":"P","linkTypeCon":"","reDataCondition":"0-10","reValue":"測試數據"}],"ModelSort":"43","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"_Label218","itemDesc":"疼痛程度自评工具（NRS、FPS-R）","itemType":"T","itemValue":"疼痛程度自评工具（NRS、FPS-R）","itemdeValue":"","jModelKey":"DHCNURPGD_AZTTPFK","mustFill":"0","singleCheck":"1","subRowId":"1||418","titleHiddeFlag":"0","toastStr":"","value":"","width":"109"},{"LinkInfo":[],"ModelSort":"44","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"_Label219","itemDesc":"小儿疼痛行为评估工具","itemType":"T","itemValue":"小儿疼痛行为评估工具","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||419","titleHiddeFlag":"0","toastStr":"","value":"","width":"147"},{"LinkInfo":[],"ModelSort":"45","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"_Label220","itemDesc":"重症监护患者疼痛观察工具（CPOT）","itemType":"T","itemValue":"重症监护患者疼痛观察工具（CPOT）","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||420","titleHiddeFlag":"0","toastStr":"","value":"","width":"142"},{"LinkInfo":[],"ModelSort":"46","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item299","itemDesc":"CPOTid","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||421","titleHiddeFlag":"0","toastStr":"","value":"","width":"56"},{"LinkInfo":[],"ModelSort":"47","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item298","itemDesc":"小儿疼痛id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||422","titleHiddeFlag":"0","toastStr":"","value":"","width":"56"},{"LinkInfo":[],"ModelSort":"48","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"26","itemCode":"Item82","itemDesc":"意识状态:","itemType":"C","itemValue":"神清;嗜睡;意识模糊;谵妄;昏睡;深昏迷;浅昏迷;其它;","itemdeValue":"神清,嗜睡,意识模糊,谵妄","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||423","titleHiddeFlag":"1","toastStr":"","value":"","width":"485"},{"LinkInfo":[],"ModelSort":"49","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item129","itemDesc":"意识状态评分","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||424","titleHiddeFlag":"0","toastStr":"","value":"","width":"174"},{"LinkInfo":[],"ModelSort":"50","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item25","itemDesc":"精神状态:","itemType":"R","itemValue":"平静!躁动!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||425","titleHiddeFlag":"1","toastStr":"","value":"","width":"114"},{"LinkInfo":[],"ModelSort":"51","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item32","itemDesc":"语言表达:","itemType":"R","itemValue":"清晰!言语不清!失语!方言!聋哑!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||426","titleHiddeFlag":"1","toastStr":"","value":"","width":"114"},{"LinkInfo":[],"ModelSort":"52","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item29","itemDesc":"视力情况:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||427","titleHiddeFlag":"1","toastStr":"","value":"","width":"89"},{"LinkInfo":[],"ModelSort":"53","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item28","itemDesc":"左眼","itemType":"R","itemValue":"清晰!近视!老花!失明!义眼!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||428","titleHiddeFlag":"1","toastStr":"","value":"","width":"65"},{"LinkInfo":[],"ModelSort":"54","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item130","itemDesc":"右眼:","itemType":"R","itemValue":"清晰!近视!老花!失明!义眼!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||429","titleHiddeFlag":"1","toastStr":"","value":"","width":"65"},{"LinkInfo":[],"ModelSort":"55","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item131","itemDesc":"右眼其他","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||430","titleHiddeFlag":"0","toastStr":"","value":"","width":"90"},{"LinkInfo":[],"ModelSort":"56","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item31","itemDesc":"听力情况:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||431","titleHiddeFlag":"1","toastStr":"","value":"","width":"89"},{"LinkInfo":[],"ModelSort":"57","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item30","itemDesc":"左耳:","itemType":"R","itemValue":"清晰!听力下降!失聪!聋哑!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||432","titleHiddeFlag":"1","toastStr":"","value":"","width":"65"},{"LinkInfo":[],"ModelSort":"58","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"20","itemCode":"Item132","itemDesc":"右耳:","itemType":"R","itemValue":"清晰!听力下降!失聪!聋哑!其它!","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||433","titleHiddeFlag":"1","toastStr":"","value":"","width":"65"},{"LinkInfo":[],"ModelSort":"59","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item133","itemDesc":"右耳其他","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||434","titleHiddeFlag":"0","toastStr":"","value":"","width":"90"},{"LinkInfo":[],"ModelSort":"60","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"15","itemCode":"_Label225","itemDesc":"1.自理能力评估:","itemType":"T","itemValue":"1.自理能力评估:","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||435","titleHiddeFlag":"0","toastStr":"","value":"","width":"105"},{"LinkInfo":[],"ModelSort":"61","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item84","itemDesc":"ADL评分:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||436","titleHiddeFlag":"1","toastStr":"","value":"","width":"532"},{"LinkInfo":[],"ModelSort":"62","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label245","itemDesc":"日常生活能力评定Barthel指数量表","itemType":"T","itemValue":"日常生活能力评定Barthel指数量表","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||437","titleHiddeFlag":"0","toastStr":"","value":"","width":"181"},{"LinkInfo":[],"ModelSort":"63","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"27","itemCode":"Item86","itemDesc":"协助患者完成个人卫生,给予相关指导","itemType":"C","itemValue":"病人指导;家属或自聘护士指导;护理员指导;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||438","titleHiddeFlag":"1","toastStr":"","value":"","width":"336"},{"LinkInfo":[],"ModelSort":"64","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item297","itemDesc":"日常生活id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||439","titleHiddeFlag":"0","toastStr":"","value":"","width":"54"},{"LinkInfo":[],"ModelSort":"65","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item136","itemDesc":"排泄：","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||440","titleHiddeFlag":"1","toastStr":"","value":"","width":"111"},{"LinkInfo":[],"ModelSort":"66","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item134","itemDesc":"小便：","itemType":"C","itemValue":"正常;失禁;尿频;尿潴留;尿少;留置导尿管;其它;","itemdeValue":"正常,失禁,尿频","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||441","titleHiddeFlag":"1","toastStr":"","value":"","width":"441"},{"LinkInfo":[],"ModelSort":"67","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item135","itemDesc":"大便：","itemType":"C","itemValue":"正常;失禁;腹泻;便秘;肠造口;其它;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||442","titleHiddeFlag":"1","toastStr":"","value":"","width":"441"},{"LinkInfo":[],"ModelSort":"68","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item137","itemDesc":"S258","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||443","titleHiddeFlag":"0","toastStr":"","value":"","width":"111"},{"LinkInfo":[],"ModelSort":"69","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"14","itemCode":"_Label228","itemDesc":"2.皮肤完整性评估;","itemType":"T","itemValue":"2.皮肤完整性评估;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||444","titleHiddeFlag":"0","toastStr":"","value":"","width":"112"},{"LinkInfo":[],"ModelSort":"70","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item89","itemDesc":"压疮风险:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||445","titleHiddeFlag":"1","toastStr":"","value":"","width":"431"},{"LinkInfo":[],"ModelSort":"71","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"18","itemCode":"_Label243","itemDesc":"给予防范措施并宣教","itemType":"T","itemValue":"给予防范措施并宣教","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||446","titleHiddeFlag":"0","toastStr":"","value":"","width":"115"},{"LinkInfo":[],"ModelSort":"72","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label241","itemDesc":"压力性损伤危险因子评估表（Waterlow\u2019s）","itemType":"T","itemValue":"压力性损伤危险因子评估表（Waterlow\u2019s）","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||447","titleHiddeFlag":"0","toastStr":"","value":"","width":"190"},{"LinkInfo":[],"ModelSort":"73","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label249","itemDesc":"完整/压疮","itemType":"T","itemValue":"完整/压疮","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||448","titleHiddeFlag":"0","toastStr":"","value":"","width":"72"},{"LinkInfo":[],"ModelSort":"74","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"29","itemCode":"Item138","itemDesc":"M259","itemType":"C","itemValue":"完整;压疮;其它;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||449","titleHiddeFlag":"0","toastStr":"","value":"","width":"191"},{"LinkInfo":[],"ModelSort":"75","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item91","itemDesc":"完整/压疮","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||450","titleHiddeFlag":"0","toastStr":"","value":"","width":"115"},{"LinkInfo":[],"ModelSort":"76","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item296","itemDesc":"压疮成人id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||451","titleHiddeFlag":"0","toastStr":"","value":"","width":"48"},{"LinkInfo":[],"ModelSort":"77","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item295","itemDesc":"压疮儿童id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||452","titleHiddeFlag":"0","toastStr":"","value":"","width":"48"},{"LinkInfo":[],"ModelSort":"78","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label237","itemDesc":"3.安全评估:","itemType":"T","itemValue":"3.安全评估:","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||453","titleHiddeFlag":"0","toastStr":"","value":"","width":"105"},{"LinkInfo":[],"ModelSort":"79","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"26","itemCode":"Item97","itemDesc":"跌倒风险是否高危","itemType":"C","itemValue":"无;有;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||454","titleHiddeFlag":"0","toastStr":"","value":"","width":"88"},{"LinkInfo":[],"ModelSort":"80","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item294","itemDesc":"Morseid","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||455","titleHiddeFlag":"0","toastStr":"","value":"","width":"52"},{"LinkInfo":[],"ModelSort":"81","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item293","itemDesc":"Humid","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||456","titleHiddeFlag":"0","toastStr":"","value":"","width":"52"},{"LinkInfo":[],"ModelSort":"82","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item87","itemDesc":"跌倒风险评估","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||457","titleHiddeFlag":"1","toastStr":"","value":"","width":"336"},{"LinkInfo":[],"ModelSort":"83","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"13","itemCode":"_Label255","itemDesc":"给予防范措施并宣教","itemType":"T","itemValue":"给予防范措施并宣教","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||458","titleHiddeFlag":"0","toastStr":"","value":"","width":"117"},{"LinkInfo":[],"ModelSort":"84","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"15","itemCode":"_Label233","itemDesc":"Morse跌倒风险评估量表","itemType":"T","itemValue":"Morse跌倒风险评估量表","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||459","titleHiddeFlag":"0","toastStr":"","value":"","width":"165"},{"LinkInfo":[],"ModelSort":"85","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"22","itemCode":"_Label236","itemDesc":"坠床风险","itemType":"T","itemValue":"坠床风险","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||460","titleHiddeFlag":"0","toastStr":"","value":"","width":"80"},{"LinkInfo":[],"ModelSort":"86","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"13","itemCode":"_Label256","itemDesc":"给予防范措施并宣教","itemType":"T","itemValue":"给予防范措施并宣教","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||461","titleHiddeFlag":"0","toastStr":"","value":"","width":"123"},{"LinkInfo":[],"ModelSort":"87","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label234","itemDesc":"坠床风险评估","itemType":"T","itemValue":"坠床风险评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||462","titleHiddeFlag":"0","toastStr":"","value":"","width":"94"},{"LinkInfo":[],"ModelSort":"88","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item292","itemDesc":"坠床id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||463","titleHiddeFlag":"0","toastStr":"","value":"","width":"59"},{"LinkInfo":[],"ModelSort":"89","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"18","itemCode":"_Label251","itemDesc":"管路评估及安全指导","itemType":"T","itemValue":"管路评估及安全指导","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||464","titleHiddeFlag":"0","toastStr":"","value":"","width":"114"},{"LinkInfo":[],"ModelSort":"90","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label254","itemDesc":"管路","itemType":"T","itemValue":"管路","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||465","titleHiddeFlag":"0","toastStr":"","value":"","width":"37"},{"LinkInfo":[],"ModelSort":"91","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item96","itemDesc":"管路名称","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||466","titleHiddeFlag":"1","toastStr":"","value":"","width":"264"},{"LinkInfo":[],"ModelSort":"92","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"28","itemCode":"Item147","itemDesc":"M286","itemType":"C","itemValue":"管路通畅;敷料及固定完好;给予防控措施宣教,并协助落实;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||467","titleHiddeFlag":"0","toastStr":"","value":"","width":"420"},{"LinkInfo":[],"ModelSort":"93","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"18","itemCode":"_Label258","itemDesc":"下肢静脉血栓评估与措施","itemType":"T","itemValue":"下肢静脉血栓评估与措施","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||468","titleHiddeFlag":"0","toastStr":"","value":"","width":"141"},{"LinkInfo":[],"ModelSort":"94","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item98","itemDesc":"下肢静脉血栓评估与措施","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||469","titleHiddeFlag":"0","toastStr":"","value":"","width":"532"},{"LinkInfo":[],"ModelSort":"95","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"14","itemCode":"_Label259","itemDesc":" Caprini 深静脉血栓风险评估量表","itemType":"T","itemValue":" Caprini 深静脉血栓风险评估量表","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||470","titleHiddeFlag":"0","toastStr":"","value":"","width":"121"},{"LinkInfo":[],"ModelSort":"96","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"29","itemCode":"Item99","itemDesc":"烫伤风险:","itemType":"C","itemValue":"无;有;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||471","titleHiddeFlag":"1","toastStr":"","value":"","width":"173"},{"LinkInfo":[],"ModelSort":"97","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label341","itemDesc":"给予防范措施并宣教","itemType":"T","itemValue":"给予防范措施并宣教","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||472","titleHiddeFlag":"0","toastStr":"","value":"","width":"121"},{"LinkInfo":[],"ModelSort":"98","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label332","itemDesc":"烫伤风险评估","itemType":"T","itemValue":"烫伤风险评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||473","titleHiddeFlag":"0","toastStr":"","value":"","width":"86"},{"LinkInfo":[],"ModelSort":"99","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item290","itemDesc":"烫伤id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||474","titleHiddeFlag":"0","toastStr":"","value":"","width":"73"},{"LinkInfo":[],"ModelSort":"100","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item291","itemDesc":"Capid","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||475","titleHiddeFlag":"0","toastStr":"","value":"","width":"43"},{"LinkInfo":[],"ModelSort":"101","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"28","itemCode":"Item100","itemDesc":"误吸风险:","itemType":"C","itemValue":"无;有;洼田饮水试验;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||476","titleHiddeFlag":"1","toastStr":"","value":"","width":"175"},{"LinkInfo":[],"ModelSort":"102","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item101","itemDesc":"级","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||477","titleHiddeFlag":"0","toastStr":"","value":"","width":"98"},{"LinkInfo":[],"ModelSort":"103","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item139","itemDesc":"S260","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||478","titleHiddeFlag":"0","toastStr":"","value":"","width":"139"},{"LinkInfo":[],"ModelSort":"104","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label333","itemDesc":"误吸风险评估","itemType":"T","itemValue":"误吸风险评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||479","titleHiddeFlag":"0","toastStr":"","value":"","width":"108"},{"LinkInfo":[],"ModelSort":"105","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item289","itemDesc":"误吸id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||480","titleHiddeFlag":"0","toastStr":"","value":"","width":"69"},{"LinkInfo":[],"ModelSort":"106","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"25","itemCode":"_Label348","itemDesc":"4.老年专项评估","itemType":"T","itemValue":"4.老年专项评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||481","titleHiddeFlag":"0","toastStr":"","value":"","width":"106"},{"LinkInfo":[],"ModelSort":"107","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"23","itemCode":"_Label347","itemDesc":"老年专项评估","itemType":"T","itemValue":"老年专项评估","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||482","titleHiddeFlag":"0","toastStr":"","value":"","width":"91"},{"LinkInfo":[],"ModelSort":"108","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item287","itemDesc":"尿失禁id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||483","titleHiddeFlag":"0","toastStr":"","value":"","width":"47"},{"LinkInfo":[],"ModelSort":"109","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item148","itemDesc":"国际尿失禁咨询委员会尿失禁问卷","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||484","titleHiddeFlag":"1","toastStr":"","value":"","width":"525"},{"LinkInfo":[],"ModelSort":"110","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"19","itemCode":"_Label353","itemDesc":"国际尿失禁咨询委员会尿失禁问卷表简表","itemType":"T","itemValue":"国际尿失禁咨询委员会尿失禁问卷表简表","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||485","titleHiddeFlag":"0","toastStr":"","value":"","width":"118"},{"LinkInfo":[],"ModelSort":"111","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item286","itemDesc":"营养风险id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||486","titleHiddeFlag":"0","toastStr":"","value":"","width":"47"},{"LinkInfo":[],"ModelSort":"112","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item149","itemDesc":"营养风险筛查","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||487","titleHiddeFlag":"1","toastStr":"","value":"","width":"525"},{"LinkInfo":[],"ModelSort":"113","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"19","itemCode":"_Label354","itemDesc":"营养风险筛查表(NRS2002)","itemType":"T","itemValue":"营养风险筛查表(NRS2002)","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||488","titleHiddeFlag":"0","toastStr":"","value":"","width":"118"},{"LinkInfo":[],"ModelSort":"114","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item285","itemDesc":"简易智能id","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||489","titleHiddeFlag":"0","toastStr":"","value":"","width":"47"},{"LinkInfo":[],"ModelSort":"115","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item150","itemDesc":"简易智能精神状态评估表","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||490","titleHiddeFlag":"1","toastStr":"","value":"","width":"525"},{"LinkInfo":[],"ModelSort":"116","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"15","itemCode":"_Label355","itemDesc":"简易智能状态检查量表（MMSE）","itemType":"T","itemValue":"简易智能状态检查量表（MMSE）","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||491","titleHiddeFlag":"0","toastStr":"","value":"","width":"115"},{"LinkInfo":[],"ModelSort":"117","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"26","itemCode":"Item102","itemDesc":"5.病情变化引起的风险:","itemType":"C","itemValue":"无;有;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||492","titleHiddeFlag":"1","toastStr":"","value":"","width":"95"},{"LinkInfo":[],"ModelSort":"118","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"26","itemCode":"Item146","itemDesc":"M279","itemType":"C","itemValue":"心梗;跌倒;晕厥;猝死;其它;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||493","titleHiddeFlag":"0","toastStr":"","value":"","width":"270"},{"LinkInfo":[],"ModelSort":"119","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item103","itemDesc":")","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||494","titleHiddeFlag":"0","toastStr":"","value":"","width":"98"},{"LinkInfo":[],"ModelSort":"120","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item104","itemDesc":"6.生活习惯:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||495","titleHiddeFlag":"1","toastStr":"","value":"","width":"106"},{"LinkInfo":[],"ModelSort":"121","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label302","itemDesc":"饮食(食欲)","itemType":"T","itemValue":"饮食(食欲)","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||496","titleHiddeFlag":"0","toastStr":"","value":"","width":"69"},{"LinkInfo":[],"ModelSort":"122","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item140","itemDesc":"M261","itemType":"C","itemValue":"正常;减低;增加;其它;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||497","titleHiddeFlag":"0","toastStr":"","value":"","width":"278"},{"LinkInfo":[],"ModelSort":"123","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item126","itemDesc":"睡眠习惯","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||498","titleHiddeFlag":"1","toastStr":"","value":"","width":"44"},{"LinkInfo":[],"ModelSort":"124","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item141","itemDesc":"M262","itemType":"C","itemValue":"正常;间断入睡;失眠;服镇定剂;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||499","titleHiddeFlag":"0","toastStr":"","value":"","width":"278"},{"LinkInfo":[],"ModelSort":"125","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"23","itemCode":"_Label320","itemDesc":"小时/天","itemType":"T","itemValue":"小时/天","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||500","titleHiddeFlag":"0","toastStr":"","value":"","width":"53"},{"LinkInfo":[],"ModelSort":"126","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item127","itemDesc":"吸烟","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||501","titleHiddeFlag":"1","toastStr":"","value":"","width":"44"},{"LinkInfo":[],"ModelSort":"127","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item142","itemDesc":"M263","itemType":"C","itemValue":"不吸;吸;已戒烟;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||502","titleHiddeFlag":"0","toastStr":"","value":"","width":"220"},{"LinkInfo":[],"ModelSort":"128","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label322","itemDesc":"每日","itemType":"T","itemValue":"每日","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||503","titleHiddeFlag":"0","toastStr":"","value":"","width":"31"},{"LinkInfo":[],"ModelSort":"129","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label323","itemDesc":"包","itemType":"T","itemValue":"包","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||504","titleHiddeFlag":"0","toastStr":"","value":"","width":"29"},{"LinkInfo":[],"ModelSort":"130","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label324","itemDesc":"已吸","itemType":"T","itemValue":"已吸","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||505","titleHiddeFlag":"0","toastStr":"","value":"","width":"35"},{"LinkInfo":[],"ModelSort":"131","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item143","itemDesc":"S264","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||506","titleHiddeFlag":"0","toastStr":"","value":"","width":"44"},{"LinkInfo":[],"ModelSort":"132","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"17","itemCode":"_Label325","itemDesc":"年","itemType":"T","itemValue":"年","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||507","titleHiddeFlag":"0","toastStr":"","value":"","width":"29"},{"LinkInfo":[],"ModelSort":"133","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item128","itemDesc":"饮酒","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||508","titleHiddeFlag":"1","toastStr":"","value":"","width":"44"},{"LinkInfo":[],"ModelSort":"134","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item144","itemDesc":"M265","itemType":"C","itemValue":"不饮;偶饮;大量;已戒酒;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||509","titleHiddeFlag":"0","toastStr":"","value":"","width":"220"},{"LinkInfo":[],"ModelSort":"135","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label327","itemDesc":"每日","itemType":"T","itemValue":"每日","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||510","titleHiddeFlag":"0","toastStr":"","value":"","width":"32"},{"LinkInfo":[],"ModelSort":"136","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label328","itemDesc":"ml","itemType":"T","itemValue":"ml","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||511","titleHiddeFlag":"0","toastStr":"","value":"","width":"32"},{"LinkInfo":[],"ModelSort":"137","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label329","itemDesc":"已饮","itemType":"T","itemValue":"已饮","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||512","titleHiddeFlag":"0","toastStr":"","value":"","width":"32"},{"LinkInfo":[],"ModelSort":"138","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item145","itemDesc":"S266","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||513","titleHiddeFlag":"0","toastStr":"","value":"","width":"44"},{"LinkInfo":[],"ModelSort":"139","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"16","itemCode":"_Label330","itemDesc":"年","itemType":"T","itemValue":"年","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||514","titleHiddeFlag":"0","toastStr":"","value":"","width":"32"},{"LinkInfo":[],"ModelSort":"140","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item105","itemDesc":"7.过敏史:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||515","titleHiddeFlag":"1","toastStr":"","value":"","width":"712"},{"LinkInfo":[],"ModelSort":"141","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item106","itemDesc":"8.既往史:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||516","titleHiddeFlag":"1","toastStr":"","value":"","width":"712"},{"LinkInfo":[],"ModelSort":"142","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item107","itemDesc":"9.家族史:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||517","titleHiddeFlag":"1","toastStr":"","value":"","width":"712"},{"LinkInfo":[],"ModelSort":"143","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item108","itemDesc":"10.入院原因:","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||518","titleHiddeFlag":"1","toastStr":"","value":"","width":"712"},{"LinkInfo":[],"ModelSort":"144","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"14.25","height":"26","itemCode":"_Label277","itemDesc":"三、入院介绍","itemType":"T","itemValue":"三、入院介绍","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||519","titleHiddeFlag":"0","toastStr":"","value":"","width":"139"},{"LinkInfo":[],"ModelSort":"145","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item109","itemDesc":"病房环境","itemType":"C","itemValue":"病房环境(洗漱间、卫生间、订餐、热水、医护办公室、安全出口等);","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||520","titleHiddeFlag":"0","toastStr":"","value":"","width":"470"},{"LinkInfo":[],"ModelSort":"146","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item110","itemDesc":"制度宣教","itemType":"C","itemValue":"制度宣教(详见住院须知);","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||521","titleHiddeFlag":"0","toastStr":"","value":"","width":"470"},{"LinkInfo":[],"ModelSort":"147","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item111","itemDesc":"用物使用","itemType":"C","itemValue":"用物使用(呼叫器、床单位、灯、空调、治疗带、用氧安全);","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||522","titleHiddeFlag":"0","toastStr":"","value":"","width":"470"},{"LinkInfo":[],"ModelSort":"148","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"30","itemCode":"Item112","itemDesc":"介绍主管医生及责任护士","itemType":"C","itemValue":"介绍主管医生及责任护士;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||523","titleHiddeFlag":"0","toastStr":"","value":"","width":"471"},{"LinkInfo":[],"ModelSort":"149","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"14.25","height":"24","itemCode":"_Label288","itemDesc":"四、入院宣教","itemType":"T","itemValue":"四、入院宣教","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||524","titleHiddeFlag":"0","toastStr":"","value":"","width":"143"},{"LinkInfo":[],"ModelSort":"150","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item113","itemDesc":"体位","itemType":"C","itemValue":"体位:;自主体位;绝对卧床;平卧床;半卧床;端坐位;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||525","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"151","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item114","itemDesc":"活动指导","itemType":"C","itemValue":"活动指导:活动范围(病房内、病室内、床旁、床上),活动量;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||526","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"152","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item115","itemDesc":"饮食指导","itemType":"C","itemValue":"饮食指导:评估患者饮食习惯,根据病情及医嘱给与正确指导;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||527","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"153","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item116","itemDesc":"排便指导","itemType":"C","itemValue":"排便指导:评估患者排便情况,给予相应指导;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||528","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"154","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item117","itemDesc":"正确留取检验标本","itemType":"C","itemValue":"正确留取检验标本,给予相应指导:遵医嘱;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||529","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"155","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"33","itemCode":"Item118","itemDesc":"心理指导","itemType":"C","itemValue":"心理指导;","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"0","subRowId":"1||530","titleHiddeFlag":"0","toastStr":"","value":"","width":"472"},{"LinkInfo":[],"ModelSort":"156","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"21","itemCode":"Item62","itemDesc":"执行护士签字","itemType":"E","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||531","titleHiddeFlag":"1","toastStr":"","value":"","width":"211"},{"LinkInfo":[],"ModelSort":"157","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"23","itemCode":"Item123","itemDesc":"日期","itemType":"D","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||532","titleHiddeFlag":"1","toastStr":"","value":"","width":"137"},{"LinkInfo":[],"ModelSort":"158","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"24","itemCode":"Item124","itemDesc":"时间","itemType":"Ti","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||533","titleHiddeFlag":"1","toastStr":"","value":"","width":"111"},{"LinkInfo":[],"ModelSort":"159","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"24","itemCode":"butSave","itemDesc":"保存","itemType":"T","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||534","titleHiddeFlag":"0","toastStr":"","value":"","width":"68"},{"LinkInfo":[],"ModelSort":"160","PatInfo":"","Unit":"","editCondition":"","editFlag":"1","editItem":"","editType":"","fontSize":"12","height":"24","itemCode":"butPrint","itemDesc":"打印","itemType":"T","itemValue":"","itemdeValue":"","jModelKey":"","mustFill":"0","singleCheck":"1","subRowId":"1||535","titleHiddeFlag":"0","toastStr":"","value":"","width":"65"}]
     * msg : 成功
     * msgcode : 999999
     * patInfo : {"Barthel":"0","Bedsore":"0","Fallbed":"0","Fallrisk":"0","Item34":"0","Item34_Title":"0","Reason":"0","admReason":"全自费","age":"45岁","balance":"-953.22","bedCode":"","breath":"55","careLevel":"","ctLocDesc":"呼吸内科一","currWardID":"1","defFreq":"0","degrBlood":"0","diaPressure":"130","diagnosis":"肋软骨脱位,肺占位性病变","dischargeStatus":"","episodeID":"11","heartbeat":"0","height":"0","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"269","inDeptDateTime":"","inHospDateTime":"2018-03-23 14:00:53","insuranceCard":"","liquidOut":"0","mainDoctor":"","mainDoctorID":[],"mainNurse":"","mainNurseID":[],"medicareNo":"100005","motherTransLoc":"","name":"tyu01","nation":"汉族","operation":"","orderID":"9","painInten":"0","patLinkman":"","patientID":"52","personID":"111111111111111 ","phyCooling":"0","pulse":"100","rectemperature":"38","regDateTime":"2018-03-23 13:59:26","regNo":"0000000052","roomDesc":"转移中","sex":"女","sysPressure":"110","telphone":"13234567890","temperature":"36","totalCost":"5953.22","uriVolume":"0","wardDesc":"呼吸内科一二护理单元","weight":"50"}
     * status : 0
     */

    private String msg;
    private String msgcode;
    private PatInfoBean patInfo;
    private String status;
    private List<ModelListBean> modelList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public PatInfoBean getPatInfo() {
        return patInfo;
    }

    public void setPatInfo(PatInfoBean patInfo) {
        this.patInfo = patInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ModelListBean> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelListBean> modelList) {
        this.modelList = modelList;
    }

    public static class PatInfoBean {
        /**
         * Barthel : 0
         * Bedsore : 0
         * Fallbed : 0
         * Fallrisk : 0
         * Item34 : 0
         * Item34_Title : 0
         * Reason : 0
         * admReason : 全自费
         * age : 45岁
         * balance : -953.22
         * bedCode :
         * breath : 55
         * careLevel :
         * ctLocDesc : 呼吸内科一
         * currWardID : 1
         * defFreq : 0
         * degrBlood : 0
         * diaPressure : 130
         * diagnosis : 肋软骨脱位,肺占位性病变
         * dischargeStatus :
         * episodeID : 11
         * heartbeat : 0
         * height : 0
         * homeAddres :
         * ifNewBaby : N
         * illState : 普通
         * inDays : 269
         * inDeptDateTime :
         * inHospDateTime : 2018-03-23 14:00:53
         * insuranceCard :
         * liquidOut : 0
         * mainDoctor :
         * mainDoctorID : []
         * mainNurse :
         * mainNurseID : []
         * medicareNo : 100005
         * motherTransLoc :
         * name : tyu01
         * nation : 汉族
         * operation :
         * orderID : 9
         * painInten : 0
         * patLinkman :
         * patientID : 52
         * personID : 111111111111111
         * phyCooling : 0
         * pulse : 100
         * rectemperature : 38
         * regDateTime : 2018-03-23 13:59:26
         * regNo : 0000000052
         * roomDesc : 转移中
         * sex : 女
         * sysPressure : 110
         * telphone : 13234567890
         * temperature : 36
         * totalCost : 5953.22
         * uriVolume : 0
         * wardDesc : 呼吸内科一二护理单元
         * weight : 50
         */

        private String Barthel;
        private String Bedsore;
        private String Fallbed;
        private String Fallrisk;
        private String Item34;
        private String Item34_Title;
        private String Reason;
        private String admReason;
        private String age;
        private String balance;
        private String bedCode;
        private String breath;
        private String careLevel;
        private String ctLocDesc;
        private String currWardID;
        private String defFreq;
        private String degrBlood;
        private String diaPressure;
        private String diagnosis;
        private String dischargeStatus;
        private String episodeID;
        private String heartbeat;
        private String height;
        private String homeAddres;
        private String ifNewBaby;
        private String illState;
        private String inDays;
        private String inDeptDateTime;
        private String inHospDateTime;
        private String insuranceCard;
        private String liquidOut;
        private String mainDoctor;
        private String mainNurse;
        private String medicareNo;
        private String motherTransLoc;
        private String name;
        private String nation;
        private String operation;
        private String orderID;
        private String painInten;
        private String patLinkman;
        private String patientID;
        private String personID;
        private String phyCooling;
        private String pulse;
        private String rectemperature;
        private String regDateTime;
        private String regNo;
        private String roomDesc;
        private String sex;
        private String sysPressure;
        private String telphone;
        private String temperature;
        private String totalCost;
        private String uriVolume;
        private String wardDesc;
        private String weight;
        private List<?> mainDoctorID;
        private List<?> mainNurseID;

        public String getBarthel() {
            return Barthel;
        }

        public void setBarthel(String Barthel) {
            this.Barthel = Barthel;
        }

        public String getBedsore() {
            return Bedsore;
        }

        public void setBedsore(String Bedsore) {
            this.Bedsore = Bedsore;
        }

        public String getFallbed() {
            return Fallbed;
        }

        public void setFallbed(String Fallbed) {
            this.Fallbed = Fallbed;
        }

        public String getFallrisk() {
            return Fallrisk;
        }

        public void setFallrisk(String Fallrisk) {
            this.Fallrisk = Fallrisk;
        }

        public String getItem34() {
            return Item34;
        }

        public void setItem34(String Item34) {
            this.Item34 = Item34;
        }

        public String getItem34_Title() {
            return Item34_Title;
        }

        public void setItem34_Title(String Item34_Title) {
            this.Item34_Title = Item34_Title;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getAdmReason() {
            return admReason;
        }

        public void setAdmReason(String admReason) {
            this.admReason = admReason;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getBreath() {
            return breath;
        }

        public void setBreath(String breath) {
            this.breath = breath;
        }

        public String getCareLevel() {
            return careLevel;
        }

        public void setCareLevel(String careLevel) {
            this.careLevel = careLevel;
        }

        public String getCtLocDesc() {
            return ctLocDesc;
        }

        public void setCtLocDesc(String ctLocDesc) {
            this.ctLocDesc = ctLocDesc;
        }

        public String getCurrWardID() {
            return currWardID;
        }

        public void setCurrWardID(String currWardID) {
            this.currWardID = currWardID;
        }

        public String getDefFreq() {
            return defFreq;
        }

        public void setDefFreq(String defFreq) {
            this.defFreq = defFreq;
        }

        public String getDegrBlood() {
            return degrBlood;
        }

        public void setDegrBlood(String degrBlood) {
            this.degrBlood = degrBlood;
        }

        public String getDiaPressure() {
            return diaPressure;
        }

        public void setDiaPressure(String diaPressure) {
            this.diaPressure = diaPressure;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getDischargeStatus() {
            return dischargeStatus;
        }

        public void setDischargeStatus(String dischargeStatus) {
            this.dischargeStatus = dischargeStatus;
        }

        public String getEpisodeID() {
            return episodeID;
        }

        public void setEpisodeID(String episodeID) {
            this.episodeID = episodeID;
        }

        public String getHeartbeat() {
            return heartbeat;
        }

        public void setHeartbeat(String heartbeat) {
            this.heartbeat = heartbeat;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHomeAddres() {
            return homeAddres;
        }

        public void setHomeAddres(String homeAddres) {
            this.homeAddres = homeAddres;
        }

        public String getIfNewBaby() {
            return ifNewBaby;
        }

        public void setIfNewBaby(String ifNewBaby) {
            this.ifNewBaby = ifNewBaby;
        }

        public String getIllState() {
            return illState;
        }

        public void setIllState(String illState) {
            this.illState = illState;
        }

        public String getInDays() {
            return inDays;
        }

        public void setInDays(String inDays) {
            this.inDays = inDays;
        }

        public String getInDeptDateTime() {
            return inDeptDateTime;
        }

        public void setInDeptDateTime(String inDeptDateTime) {
            this.inDeptDateTime = inDeptDateTime;
        }

        public String getInHospDateTime() {
            return inHospDateTime;
        }

        public void setInHospDateTime(String inHospDateTime) {
            this.inHospDateTime = inHospDateTime;
        }

        public String getInsuranceCard() {
            return insuranceCard;
        }

        public void setInsuranceCard(String insuranceCard) {
            this.insuranceCard = insuranceCard;
        }

        public String getLiquidOut() {
            return liquidOut;
        }

        public void setLiquidOut(String liquidOut) {
            this.liquidOut = liquidOut;
        }

        public String getMainDoctor() {
            return mainDoctor;
        }

        public void setMainDoctor(String mainDoctor) {
            this.mainDoctor = mainDoctor;
        }

        public String getMainNurse() {
            return mainNurse;
        }

        public void setMainNurse(String mainNurse) {
            this.mainNurse = mainNurse;
        }

        public String getMedicareNo() {
            return medicareNo;
        }

        public void setMedicareNo(String medicareNo) {
            this.medicareNo = medicareNo;
        }

        public String getMotherTransLoc() {
            return motherTransLoc;
        }

        public void setMotherTransLoc(String motherTransLoc) {
            this.motherTransLoc = motherTransLoc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getPainInten() {
            return painInten;
        }

        public void setPainInten(String painInten) {
            this.painInten = painInten;
        }

        public String getPatLinkman() {
            return patLinkman;
        }

        public void setPatLinkman(String patLinkman) {
            this.patLinkman = patLinkman;
        }

        public String getPatientID() {
            return patientID;
        }

        public void setPatientID(String patientID) {
            this.patientID = patientID;
        }

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
        }

        public String getPhyCooling() {
            return phyCooling;
        }

        public void setPhyCooling(String phyCooling) {
            this.phyCooling = phyCooling;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getRectemperature() {
            return rectemperature;
        }

        public void setRectemperature(String rectemperature) {
            this.rectemperature = rectemperature;
        }

        public String getRegDateTime() {
            return regDateTime;
        }

        public void setRegDateTime(String regDateTime) {
            this.regDateTime = regDateTime;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getRoomDesc() {
            return roomDesc;
        }

        public void setRoomDesc(String roomDesc) {
            this.roomDesc = roomDesc;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSysPressure() {
            return sysPressure;
        }

        public void setSysPressure(String sysPressure) {
            this.sysPressure = sysPressure;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(String totalCost) {
            this.totalCost = totalCost;
        }

        public String getUriVolume() {
            return uriVolume;
        }

        public void setUriVolume(String uriVolume) {
            this.uriVolume = uriVolume;
        }

        public String getWardDesc() {
            return wardDesc;
        }

        public void setWardDesc(String wardDesc) {
            this.wardDesc = wardDesc;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public List<?> getMainDoctorID() {
            return mainDoctorID;
        }

        public void setMainDoctorID(List<?> mainDoctorID) {
            this.mainDoctorID = mainDoctorID;
        }

        public List<?> getMainNurseID() {
            return mainNurseID;
        }

        public void setMainNurseID(List<?> mainNurseID) {
            this.mainNurseID = mainNurseID;
        }
    }

    public static class ModelListBean {
        /**
         * LinkInfo : []
         * ModelSort : 1
         * PatInfo :
         * Unit :
         * editCondition :
         * editFlag : 1
         * editItem :
         * editType :
         * fontSize : 12
         * height : 17
         * itemCode : _Label345
         * itemDesc : 入院综合评估及护理记录单
         * itemType : T
         * itemValue : 入院综合评估及护理记录单
         * itemdeValue :
         * jModelKey :
         * mustFill : 0
         * singleCheck : 1
         * subRowId : 1||376
         * titleHiddeFlag : 0
         * toastStr : 你好啊
         * value :
         * width : 261
         */

        private String ModelSort;
        private String PatInfo;
        private String Unit;
        private String editCondition;
        private String editFlag;
        private String editItem;
        private String editType;
        private String fontSize;
        private String height;
        private String itemCode;
        private String itemDesc;
        private String itemType;
        private String itemValue;
        private String itemdeValue;
        private String jModelKey;
        private String mustFill;
        private String singleCheck;
        private String subRowId;
        private String titleHiddeFlag;
        private String toastStr;
        private String value;
        private String width;
        private List<LinkInfoBean> LinkInfo;
        private String sendValue;

        public String getSendValue() {
            return sendValue;
        }

        public void setSendValue(String sendValue) {
            this.sendValue = sendValue;
        }

        public String getModelSort() {
            return ModelSort;
        }

        public void setModelSort(String ModelSort) {
            this.ModelSort = ModelSort;
        }

        public String getPatInfo() {
            return PatInfo;
        }

        public void setPatInfo(String PatInfo) {
            this.PatInfo = PatInfo;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public String getEditCondition() {
            return editCondition;
        }

        public void setEditCondition(String editCondition) {
            this.editCondition = editCondition;
        }

        public String getEditFlag() {
            return editFlag;
        }

        public void setEditFlag(String editFlag) {
            this.editFlag = editFlag;
        }

        public String getEditItem() {
            return editItem;
        }

        public void setEditItem(String editItem) {
            this.editItem = editItem;
        }

        public String getEditType() {
            return editType;
        }

        public void setEditType(String editType) {
            this.editType = editType;
        }

        public String getFontSize() {
            return fontSize;
        }

        public void setFontSize(String fontSize) {
            this.fontSize = fontSize;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemValue() {
            return itemValue;
        }

        public void setItemValue(String itemValue) {
            this.itemValue = itemValue;
        }

        public String getItemdeValue() {
            return itemdeValue;
        }

        public void setItemdeValue(String itemdeValue) {
            this.itemdeValue = itemdeValue;
        }

        public String getJModelKey() {
            return jModelKey;
        }

        public void setJModelKey(String jModelKey) {
            this.jModelKey = jModelKey;
        }

        public String getMustFill() {
            return mustFill;
        }

        public void setMustFill(String mustFill) {
            this.mustFill = mustFill;
        }

        public String getSingleCheck() {
            return singleCheck;
        }

        public void setSingleCheck(String singleCheck) {
            this.singleCheck = singleCheck;
        }

        public String getSubRowId() {
            return subRowId;
        }

        public void setSubRowId(String subRowId) {
            this.subRowId = subRowId;
        }

        public String getTitleHiddeFlag() {
            return titleHiddeFlag;
        }

        public void setTitleHiddeFlag(String titleHiddeFlag) {
            this.titleHiddeFlag = titleHiddeFlag;
        }

        public String getToastStr() {
            return toastStr;
        }

        public void setToastStr(String toastStr) {
            this.toastStr = toastStr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public List<LinkInfoBean> getLinkInfo() {
            return LinkInfo;
        }

        public void setLinkInfo(List<LinkInfoBean> LinkInfo) {
            this.LinkInfo = LinkInfo;
        }

        public static class LinkInfoBean {
            /**
             * linkItemCode : Item15
             * linkItemCon :
             * linkModel : DHCNURPGD_AZTTPFK
             * linkRangeCon :
             * linkType : P
             * linkTypeCon :
             * reDataCondition : 0-10
             * reValue : 測試數據
             */

            private String linkItemCode;
            private String linkItemCon;
            private String linkModel;
            private String linkRangeCon;
            private String linkType;
            private String linkTypeCon;
            private String reDataCondition;
            private String reValue;

            public String getLinkItemCode() {
                return linkItemCode;
            }

            public void setLinkItemCode(String linkItemCode) {
                this.linkItemCode = linkItemCode;
            }

            public String getLinkItemCon() {
                return linkItemCon;
            }

            public void setLinkItemCon(String linkItemCon) {
                this.linkItemCon = linkItemCon;
            }

            public String getLinkModel() {
                return linkModel;
            }

            public void setLinkModel(String linkModel) {
                this.linkModel = linkModel;
            }

            public String getLinkRangeCon() {
                return linkRangeCon;
            }

            public void setLinkRangeCon(String linkRangeCon) {
                this.linkRangeCon = linkRangeCon;
            }

            public String getLinkType() {
                return linkType;
            }

            public void setLinkType(String linkType) {
                this.linkType = linkType;
            }

            public String getLinkTypeCon() {
                return linkTypeCon;
            }

            public void setLinkTypeCon(String linkTypeCon) {
                this.linkTypeCon = linkTypeCon;
            }

            public String getReDataCondition() {
                return reDataCondition;
            }

            public void setReDataCondition(String reDataCondition) {
                this.reDataCondition = reDataCondition;
            }

            public String getReValue() {
                return reValue;
            }

            public void setReValue(String reValue) {
                this.reValue = reValue;
            }
        }
    }
}
