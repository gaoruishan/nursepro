package com.dhcc.nursepro.workarea.bedmap.bean;

/**
 * com.dhcc.nursepro.workarea.bedmap.bean
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:10:38
 */
public class AfterPayBean {
    private String ordName;
    private String ordNum;
    private String ordDj;
    private String ordPrice;

    public String getOrdPrice() {
        return ordPrice;
    }

    public void setOrdPrice(String ordPrice) {
        this.ordPrice = ordPrice;
    }

    public void setOrdDj(String ordDj) {
        this.ordDj = ordDj;
    }

    public String getOrdDj() {
        return ordDj;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public String getOrdName() {
        return ordName;
    }

    public void setOrdName(String ordName) {
        this.ordName = ordName;
    }
}
