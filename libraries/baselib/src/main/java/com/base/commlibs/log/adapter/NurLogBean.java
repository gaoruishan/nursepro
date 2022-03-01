package com.base.commlibs.log.adapter;

/**
 * @author:gaoruishan
 * @date:202021/11/25/11:22
 * @email:grs0515@163.com
 */
public class NurLogBean {

    private String logType;

    private String name;


    public NurLogBean(String logType, String name) {
        this.logType = logType;
        this.name = name;
    }

    public String getLogType() {
        return logType == null ? "" : logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
