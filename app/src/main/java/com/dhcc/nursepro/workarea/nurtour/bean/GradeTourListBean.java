package com.dhcc.nursepro.workarea.nurtour.bean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.bean
 * <p>
 * author Q
 * Date: 2019/4/23
 * Time:11:03
 */
public class GradeTourListBean {
    /**
     * leftFilter : [{"code":"one","desc":"全区"},{"code":"two","desc":"二级"},{"code":"three","desc":"三级"},{"code":"need","desc":"需巡"}]
     * msg : 成功
     * msgcode : 999999
     * patInfoList : [{"age":"59岁","allOut":"0","arreag":"1","bedCode":"01","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"35","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{"DHCNurTourDate":"2019-04-23","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","TourDetailList":[{"TourDataName":"体位","TourDataValue":"卧位"},{"TourDataName":"管道","TourDataValue":"通畅"}],"TourTypeCode":"Grade","TourTypeDesc":"分级巡视"},"longOrd":"0","manageInBed":"1","name":"zxw01","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"59岁","balance":"8870.26","bedCode":"01","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"发热","dischargeStatus":"","episodeID":"35","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-23 14:38","inHospDateTime":"2018-03-23 14:38:00","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100025","motherTransLoc":"","name":"zxw01","nation":"汉族","operation":"","orderID":"16","patLinkman":"","patientID":"75","personID":"","regDateTime":"2018-03-23 14:30:22","regNo":"0000000075","roomDesc":"1病室","sex":"女","telphone":"13119325130","totalCost":"1129.74","wardDesc":"内分泌科护理单元"},"regNo":"0000000075","seq":"1","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"1","bedCode":"02","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"94","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"王伟测试","newPatient":"0","operation":"1","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"30岁","balance":"90411.11","bedCode":"02","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管裂孔疝,食管裂孔疝,肺占位性病变,咳嗽病,阳明实证","dischargeStatus":"","episodeID":"94","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 08:46","inHospDateTime":"2018-03-26 08:46:30","insuranceCard":"","mainDoctor":"智亚芹","mainDoctorID":["1671"],"mainNurse":"关畅","mainNurseID":["830"],"medicareNo":"100056","motherTransLoc":"","name":"王伟测试","nation":"汉族","operation":"颈部血管治疗性超声","orderID":"91","patLinkman":"","patientID":"129","personID":"37078419880819641X ","regDateTime":"2018-03-26 08:45:24","regNo":"0000000129","roomDesc":"1病室","sex":"男","telphone":"15336465257","totalCost":"19588.89","wardDesc":"内分泌科护理单元"},"regNo":"0000000129","seq":"2","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"57岁","allOut":"0","arreag":"1","bedCode":"03","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"0","episodeId":"315","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"马亭（演示勿动）","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"57岁","balance":"98200.88","bedCode":"03","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"2型糖尿病,高血压病,糖尿病","dischargeStatus":"","episodeID":"315","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"391","inDeptDateTime":"2018-03-29 14:34","inHospDateTime":"2018-03-29 14:34:12","insuranceCard":"","mainDoctor":"张理英","mainDoctorID":["263"],"mainNurse":"护士02","mainNurseID":["4"],"medicareNo":"100109","motherTransLoc":"","name":"马亭（演示勿动）","nation":"汉族","operation":"","orderID":"293","patLinkman":"","patientID":"290","personID":"110102196112140018 ","regDateTime":"2018-03-29 14:22:26","regNo":"0000000290","roomDesc":"2病室","sex":"男","telphone":"","totalCost":"1799.12","wardDesc":"内分泌科护理单元"},"regNo":"0000000290","seq":"3","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"25岁","allOut":"0","arreag":"1","bedCode":"08","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"0","episodeId":"96","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"智勇双全","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"25岁","balance":"94619.24","bedCode":"08","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"胸膜间皮瘤,食管肿物,食管裂孔疝","dischargeStatus":"","episodeID":"96","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 08:55","inHospDateTime":"2018-03-26 08:55:42","insuranceCard":"","mainDoctor":"陆玉梅","mainDoctorID":["158"],"mainNurse":"郭潘红","mainNurseID":["1119"],"medicareNo":"100057","motherTransLoc":"","name":"智勇双全","nation":"汉族","operation":"","orderID":"92","patLinkman":"","patientID":"133","personID":"210111199307279195 ","regDateTime":"2018-03-26 08:54:05","regNo":"0000000133","roomDesc":"4病室","sex":"男","telphone":"","totalCost":"5380.76","wardDesc":"内分泌科护理单元"},"regNo":"0000000133","seq":"4","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"1","bedCode":"09","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"27","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"MIC023","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"21岁","balance":"1268.38","bedCode":"09","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"高血压病","dischargeStatus":"","episodeID":"27","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-23 14:38","inHospDateTime":"2018-03-23 14:38:00","insuranceCard":"","mainDoctor":"医生02","mainDoctorID":["2"],"mainNurse":"","mainNurseID":[],"medicareNo":"100019","motherTransLoc":"","name":"MIC023","nation":"汉族","operation":"","orderID":"48","patLinkman":"","patientID":"66","personID":"","regDateTime":"2018-03-23 14:16:14","regNo":"0000000066","roomDesc":"4病室","sex":"女","telphone":"","totalCost":"731.62","wardDesc":"内分泌科护理单元"},"regNo":"0000000066","seq":"5","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"1","bedCode":"11","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"179","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"dzy","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"28岁","balance":"9562.22","bedCode":"11","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管恶性肿瘤","dischargeStatus":"","episodeID":"179","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"392","inDeptDateTime":"2018-03-28 09:47","inHospDateTime":"2018-03-28 09:47:50","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100084","motherTransLoc":"","name":"dzy","nation":"汉族","operation":"","orderID":"241","patLinkman":"","patientID":"89","personID":"412726199104176977 ","regDateTime":"2018-03-27 13:57:23","regNo":"0000000089","roomDesc":"5病室","sex":"男","telphone":"","totalCost":"488.78","wardDesc":"内分泌科护理单元"},"regNo":"0000000089","seq":"6","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"1岁0月","allOut":"0","arreag":"1","bedCode":"13","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"134","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lh032603之婴","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"1岁0月","balance":"0.00","bedCode":"13","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管恶性肿瘤","dischargeStatus":"","episodeID":"134","homeAddres":"","ifNewBaby":"Y","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 14:18","inHospDateTime":"2018-03-26 14:15:31","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"","motherTransLoc":"","name":"lh032603之婴","nation":"","operation":"","orderID":"142","patLinkman":"","patientID":"159","personID":"","regDateTime":"2018-03-26 11:00:00","regNo":"0000000159","roomDesc":"5病室","sex":"女","telphone":"","totalCost":"0","wardDesc":"内分泌科护理单元"},"regNo":"0000000159","seq":"7","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"2岁3月","allOut":"0","arreag":"1","bedCode":"15","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"147","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"测试人数","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"2岁3月","balance":"9124.62","bedCode":"15","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"库欣综合征,中暑","dischargeStatus":"","episodeID":"147","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 16:07","inHospDateTime":"2018-03-26 16:07:37","insuranceCard":"","mainDoctor":"汪韶轩","mainDoctorID":["182"],"mainNurse":"","mainNurseID":[],"medicareNo":"100071","motherTransLoc":"","name":"测试人数","nation":"汉族","operation":"","orderID":"143","patLinkman":"","patientID":"168","personID":"111111111111111 ","regDateTime":"2018-03-26 16:05:02","regNo":"0000000168","roomDesc":"6病室","sex":"男","telphone":"","totalCost":"875.38","wardDesc":"内分泌科护理单元"},"regNo":"0000000168","seq":"8","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"1","bedCode":"16","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"111","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"once","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"23岁","balance":"1248.92","bedCode":"16","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"胸痛,手汗症","dischargeStatus":"","episodeID":"111","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 10:22","inHospDateTime":"2018-03-26 10:16:19","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100063","motherTransLoc":"","name":"once","nation":"汉族","operation":"","orderID":"107","patLinkman":"001","patientID":"148","personID":"","regDateTime":"2018-03-26 10:01:12","regNo":"0000000148","roomDesc":"6病室","sex":"女","telphone":"17863827169","totalCost":"751.08","wardDesc":"内分泌科护理单元"},"regNo":"0000000148","seq":"9","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"24岁","allOut":"0","arreag":"0","bedCode":"17","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"148","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"150yh","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"24岁","balance":"99998475.16","bedCode":"17","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"高血压病","dischargeStatus":"","episodeID":"148","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 16:10","inHospDateTime":"2018-03-26 16:10:08","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士02","mainNurseID":["4"],"medicareNo":"100072","motherTransLoc":"","name":"150yh","nation":"汉族","operation":"","orderID":"135","patLinkman":"","patientID":"167","personID":"","regDateTime":"2018-03-26 16:06:52","regNo":"0000000167","roomDesc":"6病室","sex":"女","telphone":"13029292929","totalCost":"1524.84","wardDesc":"内分泌科护理单元"},"regNo":"0000000167","seq":"10","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"42岁","allOut":"0","arreag":"1","bedCode":"18","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"192","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"znjz01","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"42岁","balance":"71427.06","bedCode":"18","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"疼痛","dischargeStatus":"","episodeID":"192","homeAddres":"北京市市辖区东城区朝阳门街道","ifNewBaby":"N","illState":"普通","inDays":"393","inDeptDateTime":"2018-03-27 16:32","inHospDateTime":"2018-03-27 16:32:59","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100089","motherTransLoc":"","name":"znjz01","nation":"汉族","operation":"","orderID":"179","patLinkman":"","patientID":"49","personID":"34040619760917815X ","regDateTime":"2018-03-27 16:30:51","regNo":"0000000049","roomDesc":"6病室","sex":"男","telphone":"13200000000","totalCost":"28571.94","wardDesc":"内分泌科护理单元"},"regNo":"0000000049","seq":"11","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"1岁0月","allOut":"0","arreag":"0","bedCode":"27","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"205","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lh032801之婴","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"1岁0月","balance":"0.00","bedCode":"27","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管肿物","dischargeStatus":"","episodeID":"205","homeAddres":"","ifNewBaby":"Y","illState":"普通","inDays":"392","inDeptDateTime":"2018-03-28 17:03","inHospDateTime":"2018-03-28 09:29:52","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"","motherTransLoc":"","name":"lh032801之婴","nation":"","operation":"","orderID":"194","patLinkman":"","patientID":"219","personID":"","regDateTime":"2018-03-28 01:00:00","regNo":"0000000219","roomDesc":"9病室","sex":"男","telphone":"","totalCost":"0","wardDesc":"内分泌科护理单元"},"regNo":"0000000219","seq":"12","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"1","bedCode":"19","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"185","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"王伟2","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"30岁","balance":"9131.02","bedCode":"19","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管肿物","dischargeStatus":"","episodeID":"185","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"393","inDeptDateTime":"2018-03-27 15:08","inHospDateTime":"2018-03-27 15:08:53","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100086","motherTransLoc":"","name":"王伟2","nation":"汉族","operation":"","orderID":"170","patLinkman":"","patientID":"206","personID":"","regDateTime":"2018-03-27 15:05:17","regNo":"0000000206","roomDesc":"7病室","sex":"男","telphone":"","totalCost":"868.98","wardDesc":"内分泌科护理单元"},"regNo":"0000000206","seq":"13","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"29岁","allOut":"0","arreag":"1","bedCode":"20","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"7","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lh032301","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"29岁","balance":"79366.00","bedCode":"20","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"不适和疲劳","dischargeStatus":"","episodeID":"7","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-28 10:17","inHospDateTime":"2018-03-23 13:51:46","insuranceCard":"","mainDoctor":"陆静","mainDoctorID":["434"],"mainNurse":"李丹丹[2401]","mainNurseID":["374"],"medicareNo":"100004","motherTransLoc":"","name":"lh032301","nation":"汉族","operation":"","orderID":"27","patLinkman":"","patientID":"37","personID":"370983199001175326 ","regDateTime":"2018-03-23 13:48:15","regNo":"0000000037","roomDesc":"7病室","sex":"女","telphone":"18799666666","totalCost":"634","wardDesc":"内分泌科护理单元"},"regNo":"0000000037","seq":"14","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"1","bedCode":"21","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"151","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"ly001","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"23岁","balance":"99107.38","bedCode":"21","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"糖耐量异常,伤寒,尿崩症","dischargeStatus":"","episodeID":"151","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 17:00","inHospDateTime":"2018-03-26 17:00:41","insuranceCard":"","mainDoctor":"医生02","mainDoctorID":["2"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100075","motherTransLoc":"","name":"ly001","nation":"汉族","operation":"","orderID":"144","patLinkman":"","patientID":"131","personID":"150123199508264167 ","regDateTime":"2018-03-26 16:51:31","regNo":"0000000131","roomDesc":"7病室","sex":"女","telphone":"13211111111","totalCost":"892.62","wardDesc":"内分泌科护理单元"},"regNo":"0000000131","seq":"15","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"40岁","allOut":"0","arreag":"1","bedCode":"22","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"154","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"ly002","newPatient":"0","operation":"1","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"40岁","balance":"99332.87","bedCode":"22","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"中暑","dischargeStatus":"","episodeID":"154","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 17:00","inHospDateTime":"2018-03-26 17:00:52","insuranceCard":"","mainDoctor":"医生02","mainDoctorID":["2"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100076","motherTransLoc":"","name":"ly002","nation":"汉族","operation":"经皮交通动脉血管成形术","orderID":"145","patLinkman":"","patientID":"132","personID":"362300197805243440 ","regDateTime":"2018-03-26 16:55:38","regNo":"0000000132","roomDesc":"7病室","sex":"女","telphone":"15622222222","totalCost":"667.13","wardDesc":"内分泌科护理单元"},"regNo":"0000000132","seq":"16","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"37岁","allOut":"0","arreag":"1","bedCode":"23","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"155","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"ly003","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"37岁","balance":"9366.00","bedCode":"23","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"眼眶水肿,尿崩症","dischargeStatus":"","episodeID":"155","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 17:01","inHospDateTime":"2018-03-26 17:01:10","insuranceCard":"","mainDoctor":"医生02","mainDoctorID":["2"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100077","motherTransLoc":"","name":"ly003","nation":"汉族","operation":"","orderID":"146","patLinkman":"","patientID":"134","personID":"350101198108040468 ","regDateTime":"2018-03-26 16:56:58","regNo":"0000000134","roomDesc":"8病室","sex":"女","telphone":"13611111111","totalCost":"634","wardDesc":"内分泌科护理单元"},"regNo":"0000000134","seq":"17","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"38岁","allOut":"0","arreag":"0","bedCode":"24","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"156","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"ly004","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"38岁","balance":"-544.00","bedCode":"24","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"","dischargeStatus":"","episodeID":"156","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 17:01","inHospDateTime":"2018-03-26 17:01:20","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100078","motherTransLoc":"","name":"ly004","nation":"汉族","operation":"","orderID":"147","patLinkman":"","patientID":"136","personID":"450303198012295885 ","regDateTime":"2018-03-26 16:58:17","regNo":"0000000136","roomDesc":"8病室","sex":"女","telphone":"13211111111","totalCost":"544","wardDesc":"内分泌科护理单元"},"regNo":"0000000136","seq":"18","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"20岁","allOut":"0","arreag":"1","bedCode":"25","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"0","episodeId":"157","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"ly005","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"20岁","balance":"9142.42","bedCode":"25","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"中暑病","dischargeStatus":"","episodeID":"157","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"394","inDeptDateTime":"2018-03-26 17:01","inHospDateTime":"2018-03-26 17:01:38","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"汪舒 护士01","mainNurseID":["731","3"],"medicareNo":"100079","motherTransLoc":"","name":"ly005","nation":"汉族","operation":"","orderID":"139","patLinkman":"","patientID":"137","personID":"15263119980717569X ","regDateTime":"2018-03-26 16:58:53","regNo":"0000000137","roomDesc":"8病室","sex":"男","telphone":"15411111111","totalCost":"857.58","wardDesc":"内分泌科护理单元"},"regNo":"0000000137","seq":"19","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"1","bedCode":"26","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"668","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"住院测试","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"30岁","balance":"10992.94","bedCode":"26","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管良性肿瘤","dischargeStatus":"","episodeID":"668","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"324","inDeptDateTime":"2018-06-04 14:18","inHospDateTime":"2018-06-04 14:16:42","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士02","mainNurseID":["4"],"medicareNo":"100175","motherTransLoc":"","name":"住院测试","nation":"汉族","operation":"","orderID":"615","patLinkman":"","patientID":"462","personID":"","regDateTime":"2018-06-04 14:16:42","regNo":"0000000462","roomDesc":"8病室","sex":"男","telphone":"","totalCost":"7.06","wardDesc":"内分泌科护理单元"},"regNo":"0000000462","seq":"20","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"44岁","allOut":"0","arreag":"0","bedCode":"28","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"2","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"znzy01","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"44岁","balance":"992693.88","bedCode":"28","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"头痛,特指多个心瓣膜疾病,胸膜炎,糖尿病,支饮病,暑湿弥漫三焦证","dischargeStatus":"","episodeID":"2","homeAddres":"天津市市辖区东丽区金桥街道","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-23 13:37","inHospDateTime":"2018-03-23 13:37:38","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100001","motherTransLoc":"","name":"znzy01","nation":"汉族","operation":"","orderID":"4","patLinkman":"","patientID":"35","personID":"511501197504144523 ","regDateTime":"2018-03-23 13:16:16","regNo":"0000000035","roomDesc":"9病室","sex":"女","telphone":"18966666666","totalCost":"7305.12","wardDesc":"内分泌科护理单元"},"regNo":"0000000035","seq":"21","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"31岁","allOut":"0","arreag":"0","bedCode":"29","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"0","episodeId":"189","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"韦丹寒","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"31岁","balance":"-2080.00","bedCode":"29","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"孕7周,不适和疲劳","dischargeStatus":"","episodeID":"189","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"393","inDeptDateTime":"2018-03-27 15:49","inHospDateTime":"2018-03-27 15:49:53","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100088","motherTransLoc":"","name":"韦丹寒","nation":"汉族","operation":"","orderID":"176","patLinkman":"","patientID":"208","personID":"210402198803209044 ","regDateTime":"2018-03-27 15:49:00","regNo":"0000000208","roomDesc":"9病室","sex":"女","telphone":"18677889900","totalCost":"4080","wardDesc":"内分泌科护理单元"},"regNo":"0000000208","seq":"22","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"22岁","allOut":"0","arreag":"1","bedCode":"30","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"386","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"王巍然","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"22岁","balance":"1496.94","bedCode":"30","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"沙门菌胃肠炎,不适和疲劳,肺脓肿","dischargeStatus":"","episodeID":"386","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"390","inDeptDateTime":"2018-03-30 16:46","inHospDateTime":"2018-03-30 16:46:49","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100126","motherTransLoc":"","name":"王巍然","nation":"汉族","operation":"","orderID":"347","patLinkman":"","patientID":"329","personID":"450122199703010810 ","regDateTime":"2018-03-30 16:45:57","regNo":"0000000329","roomDesc":"9病室","sex":"男","telphone":"18678776655","totalCost":"503.06","wardDesc":"内分泌科护理单元"},"regNo":"0000000329","seq":"23","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"29岁","allOut":"0","arreag":"1","bedCode":"31","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"28","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lc2018032301","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"29岁","balance":"86.88","bedCode":"31","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"功能性咳嗽","dischargeStatus":"","episodeID":"28","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"396","inDeptDateTime":"2018-03-31 15:24","inHospDateTime":"2018-03-24 10:15:24","insuranceCard":"","mainDoctor":"","mainDoctorID":[],"mainNurse":"","mainNurseID":[],"medicareNo":"100020","motherTransLoc":"","name":"lc2018032301","nation":"汉族","operation":"","orderID":"74","patLinkman":"","patientID":"50","personID":"","regDateTime":"2018-03-23 13:52:08","regNo":"0000000050","roomDesc":"10病室","sex":"女","telphone":"13112345678","totalCost":"913.12","wardDesc":"内分泌科护理单元"},"regNo":"0000000050","seq":"24","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"29岁","allOut":"0","arreag":"1","bedCode":"32","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"406","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lc033101","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"29岁","balance":"616.00","bedCode":"32","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"","dischargeStatus":"","episodeID":"406","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"389","inDeptDateTime":"2018-03-31 17:05","inHospDateTime":"2018-03-31 17:05:22","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100127","motherTransLoc":"","name":"lc033101","nation":"汉族","operation":"","orderID":"373","patLinkman":"","patientID":"337","personID":"","regDateTime":"2018-03-31 17:02:18","regNo":"0000000337","roomDesc":"10病室","sex":"女","telphone":"13112345678","totalCost":"384","wardDesc":"内分泌科护理单元"},"regNo":"0000000337","seq":"25","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"44岁","allOut":"0","arreag":"1","bedCode":"35","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"209","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"yw曹芷若","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"44岁","balance":"7852.00","bedCode":"35","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"不适和疲劳","dischargeStatus":"","episodeID":"209","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"392","inDeptDateTime":"2018-03-28 10:23","inHospDateTime":"2018-03-28 10:23:26","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士01","mainNurseID":["3"],"medicareNo":"100094","motherTransLoc":"","name":"yw曹芷若","nation":"汉族","operation":"","orderID":"198","patLinkman":"","patientID":"140","personID":"440901197501231800 ","regDateTime":"2018-03-28 10:17:42","regNo":"0000000140","roomDesc":"11病室","sex":"女","telphone":"15621374567","totalCost":"2148","wardDesc":"内分泌科护理单元"},"regNo":"0000000140","seq":"26","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"68岁","allOut":"0","arreag":"1","bedCode":"41","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"31","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"zfm03","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"68岁","balance":"2129.30","bedCode":"41","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"高血压病","dischargeStatus":"","episodeID":"31","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-23 14:37","inHospDateTime":"2018-03-23 14:37:00","insuranceCard":"","mainDoctor":"陆玉梅","mainDoctorID":["158"],"mainNurse":"汪丽华","mainNurseID":["306"],"medicareNo":"100023","motherTransLoc":"","name":"zfm03","nation":"汉族","operation":"","orderID":"47","patLinkman":"","patientID":"70","personID":"","regDateTime":"2018-03-23 14:19:39","regNo":"0000000070","roomDesc":"12病室","sex":"女","telphone":"","totalCost":"870.7","wardDesc":"内分泌科护理单元"},"regNo":"0000000070","seq":"27","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"39岁","allOut":"0","arreag":"1","bedCode":"38","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"0","episodeId":"435","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"tyu8901","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"39岁","balance":"1091.12","bedCode":"38","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"肋软骨脱位,不适和疲劳","dischargeStatus":"","episodeID":"435","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"387","inDeptDateTime":"2018-04-02 14:55","inHospDateTime":"2018-04-02 14:55:46","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":" 护士02","mainNurseID":["4"],"medicareNo":"100136","motherTransLoc":"","name":"tyu8901","nation":"汉族","operation":"","orderID":"399","patLinkman":"","patientID":"351","personID":"","regDateTime":"2018-04-02 14:54:34","regNo":"0000000351","roomDesc":"11病室","sex":"女","telphone":"13289098888","totalCost":"1708.88","wardDesc":"内分泌科护理单元"},"regNo":"0000000351","seq":"28","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"29岁","allOut":"0","arreag":"1","bedCode":"39","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"482","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lc2018040301","newPatient":"0","operation":"1","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"29岁","balance":"700.00","bedCode":"39","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"高弓足","dischargeStatus":"","episodeID":"482","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"386","inDeptDateTime":"2018-04-03 15:33","inHospDateTime":"2018-04-03 13:52:23","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100143","motherTransLoc":"","name":"lc2018040301","nation":"汉族","operation":"头部血管治疗性超声","orderID":"446","patLinkman":"","patientID":"370","personID":"","regDateTime":"2018-04-03 13:48:40","regNo":"0000000370","roomDesc":"12病室","sex":"女","telphone":"13112345678","totalCost":"300","wardDesc":"内分泌科护理单元"},"regNo":"0000000370","seq":"29","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"20岁","allOut":"0","arreag":"1","bedCode":"40","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"579","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"119940","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"20岁","balance":"9787.82","bedCode":"40","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"厌食","dischargeStatus":"","episodeID":"579","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"380","inDeptDateTime":"2018-04-09 15:39","inHospDateTime":"2018-04-09 15:39:22","insuranceCard":"","mainDoctor":"汪韶轩","mainDoctorID":["182"],"mainNurse":"","mainNurseID":[],"medicareNo":"100153","motherTransLoc":"","name":"119940","nation":"汉族","operation":"","orderID":"534","patLinkman":"","patientID":"391","personID":"","regDateTime":"2018-04-09 15:37:20","regNo":"0000000391","roomDesc":"12病室","sex":"男","telphone":"16666666666","totalCost":"212.18","wardDesc":"内分泌科护理单元"},"regNo":"0000000391","seq":"30","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"29岁","allOut":"0","arreag":"1","bedCode":"43","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"693","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"测试押金","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"29岁","balance":"9908.22","bedCode":"43","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"伤寒性肝炎","dischargeStatus":"","episodeID":"693","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"323","inDeptDateTime":"2018-06-05 15:02","inHospDateTime":"2018-06-05 11:23:56","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士02","mainNurseID":["4"],"medicareNo":"100179","motherTransLoc":"","name":"测试押金","nation":"汉族","operation":"","orderID":"640","patLinkman":"","patientID":"475","personID":"","regDateTime":"2018-06-05 11:23:56","regNo":"0000000475","roomDesc":"加床","sex":"女","telphone":"18778767876","totalCost":"91.78","wardDesc":"内分泌科护理单元"},"regNo":"0000000475","seq":"31","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"44岁","allOut":"0","arreag":"1","bedCode":"45","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"432","fever":"0","gotAllergy":"1","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"tyu03","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"44岁","balance":"9786.77","bedCode":"45","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"不适和疲劳,肋软骨脱位","dischargeStatus":"","episodeID":"432","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"387","inDeptDateTime":"2018-04-02 14:35","inHospDateTime":"2018-04-02 14:35:48","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"护士02","mainNurseID":["4"],"medicareNo":"100135","motherTransLoc":"","name":"tyu03","nation":"汉族","operation":"","orderID":"396","patLinkman":"","patientID":"143","personID":"","regDateTime":"2018-04-02 14:33:47","regNo":"0000000143","roomDesc":"11病室","sex":"女","telphone":"13267534212","totalCost":"1213.23","wardDesc":"内分泌科护理单元"},"regNo":"0000000143","seq":"32","sex":"女","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"25岁","allOut":"0","arreag":"1","bedCode":"46","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"707","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"李翔","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"25岁","balance":"10000.00","bedCode":"46","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"不适和疲劳","dischargeStatus":"","episodeID":"707","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"268","inDeptDateTime":"2018-07-30 16:45","inHospDateTime":"2018-07-30 16:44:34","insuranceCard":"","mainDoctor":"陆玉梅","mainDoctorID":["158"],"mainNurse":"王娟[3357]","mainNurseID":["671"],"medicareNo":"100185","motherTransLoc":"","name":"李翔","nation":"汉族","operation":"","orderID":"649","patLinkman":"","patientID":"486","personID":"370911199401264414 ","regDateTime":"2018-07-30 16:44:34","regNo":"0000000486","roomDesc":"11病室","sex":"男","telphone":"15610002056","totalCost":"0","wardDesc":"内分泌科护理单元"},"regNo":"0000000486","seq":"33","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"20岁","allOut":"0","arreag":"0","bedCode":"49","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"587","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"119950","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"20岁","balance":"-64.00","bedCode":"49","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"中暑","dischargeStatus":"","episodeID":"587","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"379","inDeptDateTime":"2018-04-10 09:10","inHospDateTime":"2018-04-10 09:10:22","insuranceCard":"","mainDoctor":"汪韶轩","mainDoctorID":["182"],"mainNurse":"","mainNurseID":[],"medicareNo":"100156","motherTransLoc":"","name":"119950","nation":"汉族","operation":"","orderID":"549","patLinkman":"","patientID":"424","personID":"","regDateTime":"2018-04-09 17:15:13","regNo":"0000000424","roomDesc":"11病室","sex":"男","telphone":"16666666666","totalCost":"64","wardDesc":"内分泌科护理单元"},"regNo":"0000000424","seq":"34","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"1","bedCode":"50","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"439","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"绑定测试","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"27岁","balance":"640.10","bedCode":"50","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管恶性肿瘤","dischargeStatus":"","episodeID":"439","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"387","inDeptDateTime":"2018-04-02 15:22","inHospDateTime":"2018-04-02 15:22:28","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100137","motherTransLoc":"","name":"绑定测试","nation":"汉族","operation":"","orderID":"404","patLinkman":"","patientID":"353","personID":"","regDateTime":"2018-04-02 15:19:35","regNo":"0000000353","roomDesc":"11病室","sex":"男","telphone":"","totalCost":"359.9","wardDesc":"内分泌科护理单元"},"regNo":"0000000353","seq":"35","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"1","bedCode":"51","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"444","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"绑测","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"27岁","balance":"675.58","bedCode":"51","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管肿物","dischargeStatus":"","episodeID":"444","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"387","inDeptDateTime":"2018-04-02 16:16","inHospDateTime":"2018-04-02 16:16:26","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100138","motherTransLoc":"","name":"绑测","nation":"汉族","operation":"","orderID":"410","patLinkman":"","patientID":"354","personID":"","regDateTime":"2018-04-02 16:15:55","regNo":"0000000354","roomDesc":"11病室","sex":"男","telphone":"","totalCost":"324.42","wardDesc":"内分泌科护理单元"},"regNo":"0000000354","seq":"36","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"1","bedCode":"52","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"456","fever":"0","gotAllergy":"0","illState":"","inBedAll":"1","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"绑定测试2","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"27岁","balance":"9631.96","bedCode":"52","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管恶性肿瘤","dischargeStatus":"","episodeID":"456","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"387","inDeptDateTime":"2018-04-02 18:35","inHospDateTime":"2018-04-02 18:35:37","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100141","motherTransLoc":"","name":"绑定测试2","nation":"汉族","operation":"","orderID":"419","patLinkman":"","patientID":"363","personID":"","regDateTime":"2018-04-02 18:34:57","regNo":"0000000363","roomDesc":"11病室","sex":"男","telphone":"","totalCost":"368.04","wardDesc":"内分泌科护理单元"},"regNo":"0000000363","seq":"37","sex":"男","tempOrd":"0","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"0","episodeId":"333","fever":"0","gotAllergy":"0","illState":"","inBedAll":"0","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"lh032812","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"23岁","balance":"0.00","bedCode":"","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"","dischargeStatus":"","episodeID":"333","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"0","inDeptDateTime":"","inHospDateTime":" ","insuranceCard":"","mainDoctor":"","mainDoctorID":[],"mainNurse":"","mainNurseID":[],"medicareNo":"100113","motherTransLoc":"","name":"lh032812","nation":"汉族","operation":"","orderID":"","patLinkman":"","patientID":"237","personID":"","regDateTime":"2018-03-29 17:25:09","regNo":"0000000237","roomDesc":"","sex":"女","telphone":"18766127063","totalCost":"0","wardDesc":"内分泌科护理单元"},"regNo":"0000000237","seq":"38","sex":"女","tempOrd":"0","todayOut":"0","wait":"1"},{"age":"30岁","allOut":"0","arreag":"1","bedCode":"","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"1","episodeId":"657","fever":"0","gotAllergy":"0","illState":"","inBedAll":"0","lastTourInfo":{},"longOrd":"0","manageInBed":"0","name":"补丁包","newPatient":"0","operation":"","patInfoDetail":{"PatBMI":"22.87,超过中等","admReason":"全自费","age":"30岁","balance":"958.00","bedCode":"","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"食管良性肿瘤","dischargeStatus":"护士召回","episodeID":"657","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"1","inDeptDateTime":"2018-05-04 20:08","inHospDateTime":"2018-05-04 20:07:59","insuranceCard":"","mainDoctor":"医生02","mainDoctorID":["2"],"mainNurse":"","mainNurseID":[],"medicareNo":"100173","motherTransLoc":"","name":"补丁包","nation":"汉族","operation":"","orderID":"604","patLinkman":"","patientID":"448","personID":"","regDateTime":"2018-05-04 20:07:59","regNo":"0000000448","roomDesc":"","sex":"女","telphone":"15336465257","totalCost":"42","wardDesc":"内分泌科护理单元"},"regNo":"0000000448","seq":"39","sex":"女","tempOrd":"0","todayOut":"0","wait":"1"}]
     * status : 0
     * topFilter : [{"code":"inBedAll","desc":"全区"},{"code":"manageInBed","desc":"管辖"}]
     */

    private String msg;
    private String msgcode;
    private String status;
    private List<LeftFilterBean> leftFilter;
    private List<PatInfoListBean> patInfoList;
    private List<TopFilterBean> topFilter;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LeftFilterBean> getLeftFilter() {
        return leftFilter;
    }

    public void setLeftFilter(List<LeftFilterBean> leftFilter) {
        this.leftFilter = leftFilter;
    }

    public List<PatInfoListBean> getPatInfoList() {
        return patInfoList;
    }

    public void setPatInfoList(List<PatInfoListBean> patInfoList) {
        this.patInfoList = patInfoList;
    }

    public List<TopFilterBean> getTopFilter() {
        return topFilter;
    }

    public void setTopFilter(List<TopFilterBean> topFilter) {
        this.topFilter = topFilter;
    }

    public static class LeftFilterBean {
        /**
         * code : one
         * desc : 全区
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class PatInfoListBean {
        /**
         * age : 59岁
         * allOut : 0
         * arreag : 1
         * bedCode : 01
         * careLevel :
         * criticalValue : 0
         * dangerous : 0
         * docDisch : 0
         * episodeId : 35
         * fever : 0
         * gotAllergy : 1
         * illState :
         * inBedAll : 1
         * lastTourInfo : {"DHCNurTourDate":"2019-04-23","DHCNurTourTime":"11:43:30","DHCNurTourUser":"Demo Group","TourDetailList":[{"TourDataName":"体位","TourDataValue":"卧位"},{"TourDataName":"管道","TourDataValue":"通畅"}],"TourTypeCode":"Grade","TourTypeDesc":"分级巡视"}
         * longOrd : 0
         * manageInBed : 1
         * name : zxw01
         * newPatient : 0
         * operation :
         * patInfoDetail : {"PatBMI":"22.87,超过中等","admReason":"全自费","age":"59岁","balance":"8870.26","bedCode":"01","careLevel":"","ctLocDesc":"内分泌科","currWardID":"5","diagnosis":"发热","dischargeStatus":"","episodeID":"35","homeAddres":"","ifNewBaby":"N","illState":"普通","inDays":"397","inDeptDateTime":"2018-03-23 14:38","inHospDateTime":"2018-03-23 14:38:00","insuranceCard":"","mainDoctor":"医生01","mainDoctorID":["1"],"mainNurse":"","mainNurseID":[],"medicareNo":"100025","motherTransLoc":"","name":"zxw01","nation":"汉族","operation":"","orderID":"16","patLinkman":"","patientID":"75","personID":"","regDateTime":"2018-03-23 14:30:22","regNo":"0000000075","roomDesc":"1病室","sex":"女","telphone":"13119325130","totalCost":"1129.74","wardDesc":"内分泌科护理单元"}
         * regNo : 0000000075
         * seq : 1
         * sex : 女
         * tempOrd : 0
         * todayOut : 0
         * wait : 0
         */

        private String age;
        private String allOut;
        private String arreag;
        private String bedCode;
        private String careLevel;
        private String criticalValue;
        private String dangerous;
        private String docDisch;
        private String episodeId;
        private String fever;
        private String gotAllergy;
        private String illState;
        private String inBedAll;
        private LastTourInfoBean lastTourInfo;
        private String longOrd;
        private String manageInBed;
        private String name;
        private String newPatient;
        private String operation;
        private PatInfoDetailBean patInfoDetail;
        private String regNo;
        private String seq;
        private String sex;
        private String tempOrd;
        private String todayOut;
        private String wait;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAllOut() {
            return allOut;
        }

        public void setAllOut(String allOut) {
            this.allOut = allOut;
        }

        public String getArreag() {
            return arreag;
        }

        public void setArreag(String arreag) {
            this.arreag = arreag;
        }

        public String getBedCode() {
            return bedCode;
        }

        public void setBedCode(String bedCode) {
            this.bedCode = bedCode;
        }

        public String getCareLevel() {
            return careLevel;
        }

        public void setCareLevel(String careLevel) {
            this.careLevel = careLevel;
        }

        public String getCriticalValue() {
            return criticalValue;
        }

        public void setCriticalValue(String criticalValue) {
            this.criticalValue = criticalValue;
        }

        public String getDangerous() {
            return dangerous;
        }

        public void setDangerous(String dangerous) {
            this.dangerous = dangerous;
        }

        public String getDocDisch() {
            return docDisch;
        }

        public void setDocDisch(String docDisch) {
            this.docDisch = docDisch;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getFever() {
            return fever;
        }

        public void setFever(String fever) {
            this.fever = fever;
        }

        public String getGotAllergy() {
            return gotAllergy;
        }

        public void setGotAllergy(String gotAllergy) {
            this.gotAllergy = gotAllergy;
        }

        public String getIllState() {
            return illState;
        }

        public void setIllState(String illState) {
            this.illState = illState;
        }

        public String getInBedAll() {
            return inBedAll;
        }

        public void setInBedAll(String inBedAll) {
            this.inBedAll = inBedAll;
        }

        public LastTourInfoBean getLastTourInfo() {
            return lastTourInfo;
        }

        public void setLastTourInfo(LastTourInfoBean lastTourInfo) {
            this.lastTourInfo = lastTourInfo;
        }

        public String getLongOrd() {
            return longOrd;
        }

        public void setLongOrd(String longOrd) {
            this.longOrd = longOrd;
        }

        public String getManageInBed() {
            return manageInBed;
        }

        public void setManageInBed(String manageInBed) {
            this.manageInBed = manageInBed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNewPatient() {
            return newPatient;
        }

        public void setNewPatient(String newPatient) {
            this.newPatient = newPatient;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public PatInfoDetailBean getPatInfoDetail() {
            return patInfoDetail;
        }

        public void setPatInfoDetail(PatInfoDetailBean patInfoDetail) {
            this.patInfoDetail = patInfoDetail;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTempOrd() {
            return tempOrd;
        }

        public void setTempOrd(String tempOrd) {
            this.tempOrd = tempOrd;
        }

        public String getTodayOut() {
            return todayOut;
        }

        public void setTodayOut(String todayOut) {
            this.todayOut = todayOut;
        }

        public String getWait() {
            return wait;
        }

        public void setWait(String wait) {
            this.wait = wait;
        }

        public static class LastTourInfoBean {
            /**
             * DHCNurTourDate : 2019-04-23
             * DHCNurTourTime : 11:43:30
             * DHCNurTourUser : Demo Group
             * TourDetailList : [{"TourDataName":"体位","TourDataValue":"卧位"},{"TourDataName":"管道","TourDataValue":"通畅"}]
             * TourTypeCode : Grade
             * TourTypeDesc : 分级巡视
             */

            private String DHCNurTourDate;
            private String DHCNurTourTime;
            private String DHCNurTourUser;
            private String TourTypeCode;
            private String TourTypeDesc;
            private List<TourDetailListBean> TourDetailList;

            public String getDHCNurTourDate() {
                return DHCNurTourDate;
            }

            public void setDHCNurTourDate(String DHCNurTourDate) {
                this.DHCNurTourDate = DHCNurTourDate;
            }

            public String getDHCNurTourTime() {
                return DHCNurTourTime;
            }

            public void setDHCNurTourTime(String DHCNurTourTime) {
                this.DHCNurTourTime = DHCNurTourTime;
            }

            public String getDHCNurTourUser() {
                return DHCNurTourUser;
            }

            public void setDHCNurTourUser(String DHCNurTourUser) {
                this.DHCNurTourUser = DHCNurTourUser;
            }

            public String getTourTypeCode() {
                return TourTypeCode;
            }

            public void setTourTypeCode(String TourTypeCode) {
                this.TourTypeCode = TourTypeCode;
            }

            public String getTourTypeDesc() {
                return TourTypeDesc;
            }

            public void setTourTypeDesc(String TourTypeDesc) {
                this.TourTypeDesc = TourTypeDesc;
            }

            public List<TourDetailListBean> getTourDetailList() {
                return TourDetailList;
            }

            public void setTourDetailList(List<TourDetailListBean> TourDetailList) {
                this.TourDetailList = TourDetailList;
            }

            public static class TourDetailListBean {
                /**
                 * TourDataName : 体位
                 * TourDataValue : 卧位
                 */

                private String TourDataName;
                private String TourDataValue;

                public String getTourDataName() {
                    return TourDataName;
                }

                public void setTourDataName(String TourDataName) {
                    this.TourDataName = TourDataName;
                }

                public String getTourDataValue() {
                    return TourDataValue;
                }

                public void setTourDataValue(String TourDataValue) {
                    this.TourDataValue = TourDataValue;
                }
            }
        }

        public static class PatInfoDetailBean {
            /**
             * PatBMI : 22.87,超过中等
             * admReason : 全自费
             * age : 59岁
             * balance : 8870.26
             * bedCode : 01
             * careLevel :
             * ctLocDesc : 内分泌科
             * currWardID : 5
             * diagnosis : 发热
             * dischargeStatus :
             * episodeID : 35
             * homeAddres :
             * ifNewBaby : N
             * illState : 普通
             * inDays : 397
             * inDeptDateTime : 2018-03-23 14:38
             * inHospDateTime : 2018-03-23 14:38:00
             * insuranceCard :
             * mainDoctor : 医生01
             * mainDoctorID : ["1"]
             * mainNurse :
             * mainNurseID : []
             * medicareNo : 100025
             * motherTransLoc :
             * name : zxw01
             * nation : 汉族
             * operation :
             * orderID : 16
             * patLinkman :
             * patientID : 75
             * personID :
             * regDateTime : 2018-03-23 14:30:22
             * regNo : 0000000075
             * roomDesc : 1病室
             * sex : 女
             * telphone : 13119325130
             * totalCost : 1129.74
             * wardDesc : 内分泌科护理单元
             */

            private String PatBMI;
            private String admReason;
            private String age;
            private String balance;
            private String bedCode;
            private String careLevel;
            private String ctLocDesc;
            private String currWardID;
            private String diagnosis;
            private String dischargeStatus;
            private String episodeID;
            private String homeAddres;
            private String ifNewBaby;
            private String illState;
            private String inDays;
            private String inDeptDateTime;
            private String inHospDateTime;
            private String insuranceCard;
            private String mainDoctor;
            private String mainNurse;
            private String medicareNo;
            private String motherTransLoc;
            private String name;
            private String nation;
            private String operation;
            private String orderID;
            private String patLinkman;
            private String patientID;
            private String personID;
            private String regDateTime;
            private String regNo;
            private String roomDesc;
            private String sex;
            private String telphone;
            private String totalCost;
            private String wardDesc;
            private List<String> mainDoctorID;
            private List<?> mainNurseID;

            public String getPatBMI() {
                return PatBMI;
            }

            public void setPatBMI(String PatBMI) {
                this.PatBMI = PatBMI;
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

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getTotalCost() {
                return totalCost;
            }

            public void setTotalCost(String totalCost) {
                this.totalCost = totalCost;
            }

            public String getWardDesc() {
                return wardDesc;
            }

            public void setWardDesc(String wardDesc) {
                this.wardDesc = wardDesc;
            }

            public List<String> getMainDoctorID() {
                return mainDoctorID;
            }

            public void setMainDoctorID(List<String> mainDoctorID) {
                this.mainDoctorID = mainDoctorID;
            }

            public List<?> getMainNurseID() {
                return mainNurseID;
            }

            public void setMainNurseID(List<?> mainNurseID) {
                this.mainNurseID = mainNurseID;
            }
        }
    }

    public static class TopFilterBean {
        /**
         * code : inBedAll
         * desc : 全区
         */

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
