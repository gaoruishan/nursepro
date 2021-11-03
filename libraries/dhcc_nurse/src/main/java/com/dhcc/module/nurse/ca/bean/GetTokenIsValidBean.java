package com.dhcc.module.nurse.ca.bean;

/**
 * @author:gaoruishan
 * @date:202021/11/1/10:20
 * @email:grs0515@163.com
 */
public class GetTokenIsValidBean extends CaResult{

    /**
     * isTokenValid : true
     */
    //token是否有效，是否仍然可以继续进行静默签，值域true或false，若为fasle，需要业务重新进行认证
    private String isTokenValid;

    public String getIsTokenValid() {
        return isTokenValid;
    }

    public void setIsTokenValid(String isTokenValid) {
        this.isTokenValid = isTokenValid;
    }
}
