package com.dhcc.nursepro.workarea.vitalsign.bean;

import java.io.Serializable;

/**
 * Created by Gaopingdong on 2018/11/19.
 */
public class PatientInfo  implements Serializable{
  public String title="体征测量";   // 测量界面 标题
  public String name="未知";    //患者名字
  public String info="";     //患者附件信息
  public String bedNo="?";    //床号
  public String levelOfCare="";  //护理等级 最好 四个字
  public String levelBgColor="#fd6f68"; //护理等级背景色
  public boolean needSaveRawData = false;//是否需要保存原始数据
}
