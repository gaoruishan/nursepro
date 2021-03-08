package com.dhcc.res;

import java.io.Serializable;

/**
 * @author:gaoruishan
 * @date:202020/12/10/09:40
 * @email:grs0515@163.com
 */
public class CustomBean implements Serializable {
    private String select;

    public String getSelect() {
        return select == null ? "" : select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
