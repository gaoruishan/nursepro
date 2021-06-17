package com.base.commlibs.voiceUtils.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.base.commlibs.bean
 * <p>
 * author Q
 * Date: 2020/10/20
 * Time:14:03
 */
public class VoiceVisalBean {
    /**
     * datePoint : 2020-08-04
     * leftFilter : [{"code":"temperature","desc":"腋温"},{"code":"pulse","desc":"脉搏"},{"code":"breath","desc":"呼吸"},{"code":"sysPressure","desc":"收缩压"},{"code":"diaPressure","desc":"舒张压"},{"code":"degrBlood","desc":"血氧饱和度"},{"code":"heartbeat","desc":"心率"},{"code":"defFreq","desc":"大便次数"},{"code":"height","desc":"身高"},{"code":"weight","desc":"体重"},{"code":"liquidln","desc":"总入量"},{"code":"uriVolume","desc":"尿 量"},{"code":"liquidOut","desc":"总出量"},{"code":"Item34_Title","desc":"空白栏1标题"},{"code":"Item34","desc":"空白栏1"},{"code":"Reason","desc":"未测原因"}]
     * msg : 成功
     * msgcode : 999999
     * patInfoList : [{"age":"90岁","allOut":"0","arreag":"0","bedCode":"01","careLevel":"一级护理","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1471","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"1","name":"演示","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000682","seq":"1","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"26岁","allOut":"0","arreag":"0","bedCode":"02","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"45","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"住院N2","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000043","seq":"2","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"03","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"279","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"住院N3","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000212","seq":"3","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"45岁","allOut":"0","arreag":"0","bedCode":"04","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"25","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jhm3004","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000013","seq":"4","sex":"未知性别","tempOrd":"","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"0","bedCode":"05","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"62","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"张毅","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000058","seq":"5","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"0","bedCode":"06","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"71","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"沈测试一","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000064","seq":"6","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"07","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"18","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"pu01","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000024","seq":"7","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"08","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"206","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"fftj","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000181","seq":"8","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"09","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"50","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"沈手麻一","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000049","seq":"9","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"10","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"42","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"住院N1","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000042","seq":"10","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"43岁","allOut":"0","arreag":"0","bedCode":"11","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"27","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"zfm041401","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000032","seq":"11","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"45岁","allOut":"0","arreag":"1","bedCode":"12","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"890","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"输血测试1","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000448","seq":"12","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"13","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"82","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"秦倩倩","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000068","seq":"13","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"26岁","allOut":"0","arreag":"1","bedCode":"14","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"72","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"沈测试二","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000066","seq":"14","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"33岁","allOut":"0","arreag":"0","bedCode":"15","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"43","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"灿阳lis","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000031","seq":"15","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"24岁","allOut":"0","arreag":"0","bedCode":"16","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"39","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"唐宋lis","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000029","seq":"16","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"33岁","allOut":"0","arreag":"0","bedCode":"17","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"35","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"繁星lis","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000027","seq":"17","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"70岁","allOut":"0","arreag":"0","bedCode":"18","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"579","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"张八","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000305","seq":"18","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"23岁","allOut":"0","arreag":"0","bedCode":"19","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"171","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"MYH入院2","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000171","seq":"19","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"24岁","allOut":"0","arreag":"0","bedCode":"21","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"382","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"王乾瞩测试用","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000259","seq":"20","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"32岁","allOut":"0","arreag":"1","bedCode":"22","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"385","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"zfm0420","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000261","seq":"21","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"23","careLevel":"三级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"710","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"DY测试(勿动)","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000255","seq":"22","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"1","bedCode":"24","careLevel":"二级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"682","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"杨勤测试2","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000314","seq":"23","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"0","bedCode":"25","careLevel":"二级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"418","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"杨勤测试（勿动）","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000275","seq":"24","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"32岁","allOut":"0","arreag":"1","bedCode":"26","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1140","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"zss32","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000524","seq":"25","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"27","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"645","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"魏占用测","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000358","seq":"26","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"22岁","allOut":"0","arreag":"1","bedCode":"28","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"780","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"杨勤测三","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000411","seq":"27","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"21岁","allOut":"0","arreag":"0","bedCode":"29","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"838","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"杨勤测试","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000426","seq":"28","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"56岁","allOut":"0","arreag":"0","bedCode":"30","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"717","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jhm3017","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000379","seq":"29","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"22岁","allOut":"0","arreag":"1","bedCode":"31","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"964","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"zb0426","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000469","seq":"30","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"31岁","allOut":"0","arreag":"1","bedCode":"32","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"100","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"测智护","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"1","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"1","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"1","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"1","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000083","seq":"31","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"34岁","allOut":"0","arreag":"0","bedCode":"33","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"716","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jhm3016","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000377","seq":"32","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"34","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"879","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"皮试测试勿动1","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000441","seq":"33","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"55岁","allOut":"0","arreag":"0","bedCode":"35","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"995","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"myhtest003","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000485","seq":"34","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"34岁","allOut":"0","arreag":"0","bedCode":"36","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"993","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"myhtest01","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000483","seq":"35","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"36岁","allOut":"0","arreag":"0","bedCode":"37","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"994","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"myhtest002","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000484","seq":"36","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"38","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"344","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"测试001","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000246","seq":"37","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"22岁","allOut":"0","arreag":"0","bedCode":"39","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1083","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"hoo03","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000085","seq":"38","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"40","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"703","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"ZYX测试","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000363","seq":"39","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"41","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1245","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"魏危测","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000582","seq":"40","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"35岁","allOut":"0","arreag":"1","bedCode":"42","careLevel":"一级护理","criticalValue":"0","dangerous":"1","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"16","fever":"0","gotAllergy":"","illState":"病重","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jhm3001","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000010","seq":"41","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"40岁","allOut":"0","arreag":"0","bedCode":"43","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1246","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"林测1","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000584","seq":"42","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"38岁","allOut":"0","arreag":"0","bedCode":"44","careLevel":"","criticalValue":"1","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1254","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"林测2","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000585","seq":"43","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"56岁","allOut":"0","arreag":"0","bedCode":"45","careLevel":"特级护理","criticalValue":"0","dangerous":"1","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"24","fever":"0","gotAllergy":"","illState":"病危","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jhm3002","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000011","seq":"44","sex":"男","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"47","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"157","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"产科","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000162","seq":"45","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"0","bedCode":"48","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1367","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"智护勿动","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000633","seq":"46","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"33岁","allOut":"0","arreag":"1","bedCode":"49","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"287","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"lc2020041701","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000216","seq":"47","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"0","bedCode":"50","careLevel":"二级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1403","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"卢珊珊","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000071","seq":"48","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"1","bedCode":"+4","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"582","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"测试请勿动","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000324","seq":"49","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"28岁","allOut":"0","arreag":"0","bedCode":"+5","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"806","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"间间间测试勿动","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000419","seq":"50","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"+3","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"793","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"lc2020042303","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000386","seq":"51","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"42岁","allOut":"0","arreag":"0","bedCode":"+1","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1454","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"住院演示","needMeasureCode":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"needMeasureInfo":[{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}],"newPatient":"","operation":"0","regNo":"0000000680","seq":"52","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"20岁","allOut":"0","arreag":"1","bedCode":"+2","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"548","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"jl-预住院aa","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000308","seq":"53","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"30岁","allOut":"0","arreag":"0","bedCode":"+6","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1542","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"南京四院","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000688","seq":"54","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"27岁","allOut":"0","arreag":"0","bedCode":"991","careLevel":"一级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"44","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"张二","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000039","seq":"55","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"26岁","allOut":"0","arreag":"0","bedCode":"992","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1520","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"测试01","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000699","seq":"56","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"47岁","allOut":"0","arreag":"0","bedCode":"993","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"1534","fever":"0","gotAllergy":"","illState":"","inBedAll":"1","longOrd":"","manageInBed":"0","name":"南京医大四住院","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000702","seq":"57","sex":"女","tempOrd":"","todayOut":"0","wait":"0"},{"age":"4月26天","allOut":"0","arreag":"0","bedCode":"","careLevel":"特级护理","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"29","fever":"0","gotAllergy":"","illState":"","inBedAll":"0","longOrd":"","manageInBed":"0","name":"zfm041402","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000033","seq":"58","sex":"女","tempOrd":"","todayOut":"0","wait":"1"},{"age":"20岁","allOut":"0","arreag":"0","bedCode":"","careLevel":"","criticalValue":"0","dangerous":"0","docDisch":"","epdFlag":"0","epdNotReport":"","epdReport":"","episodeId":"971","fever":"0","gotAllergy":"","illState":"","inBedAll":"0","longOrd":"","manageInBed":"0","name":"测试皮试01","needMeasureCode":[],"needMeasureInfo":[],"newPatient":"","operation":"0","regNo":"0000000475","seq":"59","sex":"女","tempOrd":"","todayOut":"0","wait":"1"}]
     * status : 0
     * timePoint : 18:00
     * timeSelect : [{"id":"1","time":"02:00"},{"id":"2","time":"06:00"},{"id":"3","time":"10:00"},{"id":"4","time":"14:00"},{"id":"5","time":"18:00"},{"id":"6","time":"22:00"}]
     * topFilter : [{"code":"inBedAll","desc":"全区"},{"code":"manageInBed","desc":"管辖"},{"code":"todayOut","desc":"今出"},{"code":"allOut","desc":"全出"},{"code":"wait","desc":"等候"}]
     */

    private String datePoint;
    private String msg;
    private String msgcode;
    private String status;
    private String timePoint;
    private List<LeftFilterBean> leftFilter;
    private List<PatInfoListBean> patInfoList;
    private List<TimeSelectBean> timeSelect;
    private List<TopFilterBean> topFilter;
    private Map mapAll = new HashMap();

    public Map getMapAll() {
        return mapAll;
    }

    public void setMapAll(Map mapAll) {
        this.mapAll = mapAll;
    }

    public String getDatePoint() {
        return datePoint;
    }

    public void setDatePoint(String datePoint) {
        this.datePoint = datePoint;
    }

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

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
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

    public List<TimeSelectBean> getTimeSelect() {
        return timeSelect;
    }

    public void setTimeSelect(List<TimeSelectBean> timeSelect) {
        this.timeSelect = timeSelect;
    }

    public List<TopFilterBean> getTopFilter() {
        return topFilter;
    }

    public void setTopFilter(List<TopFilterBean> topFilter) {
        this.topFilter = topFilter;
    }

    public static class LeftFilterBean {
        /**
         * code : temperature
         * desc : 腋温
         */

        private String code;
        private String desc;
        private int temNum=0;

        public int getTemNum() {
            return temNum;
        }

        public void setTemNum(int temNum) {
            this.temNum = temNum;
        }

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
         * age : 90岁
         * allOut : 0
         * arreag : 0
         * bedCode : 01
         * careLevel : 一级护理
         * criticalValue : 1
         * dangerous : 0
         * docDisch :
         * epdFlag : 0
         * epdNotReport :
         * epdReport :
         * episodeId : 1471
         * fever : 0
         * gotAllergy :
         * illState :
         * inBedAll : 1
         * longOrd :
         * manageInBed : 1
         * name : 演示
         * needMeasureCode : [{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}]
         * needMeasureInfo : [{"Item34":"0","Item34_Title":"0","Reason":"0","breath":"1","defFreq":"0","degrBlood":"0","diaPressure":"0","heartbeat":"0","height":"0","liquidOut":"0","liquidln":"0","needTimePoint":"18:00","pulse":"1","sysPressure":"0","temperature":"1","uriVolume":"0","weight":"0"}]
         * newPatient :
         * operation : 0
         * regNo : 0000000682
         * seq : 1
         * sex : 女
         * tempOrd :
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
        private String epdFlag;
        private String epdNotReport;
        private String epdReport;
        private String episodeId;
        private String fever;
        private String gotAllergy;
        private String illState;
        private String inBedAll;
        private String longOrd;
        private String manageInBed;
        private String name;
        private String newPatient;
        private String operation;
        private String regNo;
        private String seq;
        private String sex;
        private String tempOrd;
        private String todayOut;
        private String wait;
        private List<NeedMeasureCodeBean> needMeasureCode;
        private List<NeedMeasureInfoBean> needMeasureInfo;
        private Map patMap = new HashMap();
        private Map needMeasureInfoMap = new HashMap();
        public Map getNeedMeasureInfoMap() {
            return needMeasureInfoMap;
        }

        public void setNeedMeasureInfoMap(Map needMeasureInfoMap) {
            this.needMeasureInfoMap = needMeasureInfoMap;
        }

        public Map getPatMap() {
            return patMap;
        }

        public void setPatMap(Map patMap) {
            this.patMap = patMap;
        }

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

        public String getEpdFlag() {
            return epdFlag;
        }

        public void setEpdFlag(String epdFlag) {
            this.epdFlag = epdFlag;
        }

        public String getEpdNotReport() {
            return epdNotReport;
        }

        public void setEpdNotReport(String epdNotReport) {
            this.epdNotReport = epdNotReport;
        }

        public String getEpdReport() {
            return epdReport;
        }

        public void setEpdReport(String epdReport) {
            this.epdReport = epdReport;
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

        public List<NeedMeasureCodeBean> getNeedMeasureCode() {
            return needMeasureCode;
        }

        public void setNeedMeasureCode(List<NeedMeasureCodeBean> needMeasureCode) {
            this.needMeasureCode = needMeasureCode;
        }

        public List<NeedMeasureInfoBean> getNeedMeasureInfo() {
            return needMeasureInfo;
        }

        public void setNeedMeasureInfo(List<NeedMeasureInfoBean> needMeasureInfo) {
            this.needMeasureInfo = needMeasureInfo;
        }

        public static class NeedMeasureCodeBean {
            /**
             * Item34 : 0
             * Item34_Title : 0
             * Reason : 0
             * breath : 1
             * defFreq : 0
             * degrBlood : 0
             * diaPressure : 0
             * heartbeat : 0
             * height : 0
             * liquidOut : 0
             * liquidln : 0
             * needTimePoint : 18:00
             * pulse : 1
             * sysPressure : 0
             * temperature : 1
             * uriVolume : 0
             * weight : 0
             */

            private String Item34;
            private String Item34_Title;
            private String Reason;
            private String breath;
            private String defFreq;
            private String degrBlood;
            private String diaPressure;
            private String heartbeat;
            private String height;
            private String liquidOut;
            private String liquidln;
            private String needTimePoint;
            private String pulse;
            private String sysPressure;
            private String temperature;
            private String uriVolume;
            private String weight;

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

            public String getBreath() {
                return breath;
            }

            public void setBreath(String breath) {
                this.breath = breath;
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

            public String getLiquidOut() {
                return liquidOut;
            }

            public void setLiquidOut(String liquidOut) {
                this.liquidOut = liquidOut;
            }

            public String getLiquidln() {
                return liquidln;
            }

            public void setLiquidln(String liquidln) {
                this.liquidln = liquidln;
            }

            public String getNeedTimePoint() {
                return needTimePoint;
            }

            public void setNeedTimePoint(String needTimePoint) {
                this.needTimePoint = needTimePoint;
            }

            public String getPulse() {
                return pulse;
            }

            public void setPulse(String pulse) {
                this.pulse = pulse;
            }

            public String getSysPressure() {
                return sysPressure;
            }

            public void setSysPressure(String sysPressure) {
                this.sysPressure = sysPressure;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getUriVolume() {
                return uriVolume;
            }

            public void setUriVolume(String uriVolume) {
                this.uriVolume = uriVolume;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }
        }

        public static class NeedMeasureInfoBean {
            /**
             * Item34 : 0
             * Item34_Title : 0
             * Reason : 0
             * breath : 1
             * defFreq : 0
             * degrBlood : 0
             * diaPressure : 0
             * heartbeat : 0
             * height : 0
             * liquidOut : 0
             * liquidln : 0
             * needTimePoint : 18:00
             * pulse : 1
             * sysPressure : 0
             * temperature : 1
             * uriVolume : 0
             * weight : 0
             */

            private String Item34;
            private String Item34_Title;
            private String Reason;
            private String breath;
            private String defFreq;
            private String degrBlood;
            private String diaPressure;
            private String heartbeat;
            private String height;
            private String liquidOut;
            private String liquidln;
            private String needTimePoint;
            private String pulse;
            private String sysPressure;
            private String temperature;
            private String uriVolume;
            private String weight;

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

            public String getBreath() {
                return breath;
            }

            public void setBreath(String breath) {
                this.breath = breath;
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

            public String getLiquidOut() {
                return liquidOut;
            }

            public void setLiquidOut(String liquidOut) {
                this.liquidOut = liquidOut;
            }

            public String getLiquidln() {
                return liquidln;
            }

            public void setLiquidln(String liquidln) {
                this.liquidln = liquidln;
            }

            public String getNeedTimePoint() {
                return needTimePoint;
            }

            public void setNeedTimePoint(String needTimePoint) {
                this.needTimePoint = needTimePoint;
            }

            public String getPulse() {
                return pulse;
            }

            public void setPulse(String pulse) {
                this.pulse = pulse;
            }

            public String getSysPressure() {
                return sysPressure;
            }

            public void setSysPressure(String sysPressure) {
                this.sysPressure = sysPressure;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getUriVolume() {
                return uriVolume;
            }

            public void setUriVolume(String uriVolume) {
                this.uriVolume = uriVolume;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }
        }
    }

    public static class TimeSelectBean {
        /**
         * id : 1
         * time : 02:00
         */

        private String id;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TopFilterBean {
        /**
         * code : inBedAll
         * desc : 全区
         */

        private String code;
        private String desc;
        private List<PatInfoListBean> patInfoList = new ArrayList<PatInfoListBean>();
        private List<LeftFilterBean> leftFilter= new ArrayList<LeftFilterBean>();

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
