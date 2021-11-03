package com.dhcc.module.nurse.ca.bean;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPStaticUtils;

/**
 * @author:gaoruishan
 * @date:202021/10/29/17:31
 * @email:grs0515@163.com
 */
public class GetLoginQRResultBean extends CaResult{

    /**
     * certCN : 护士01
     * certContainer : 0A6A199CE2004CD290E74812251C42DA-54C88243F84958582E130DB17A91B42E
     * certDN :
     * certNo : 54C88243F84958582E130DB17A91B42E
     * certSN : 54C88243F84958582E130DB17A91B42E
     * expireTime : 2021-10-29 22:29:47
     * signAlg : RSA
     * signCert : MIIDvDCCAqSgAwIBAgIQVMiCQ/hJWFguEw2xepG0LjANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCQ04xDzANBgNVBAgTBlNoYW5YaTEQMA4GA1UEBxMHVGFpWXVhbjEtMCsGA1UEChMkU2hhblhpIERpZ2l0YWwgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MQwwCgYDVQQLEwNSU0ExFDASBgNVBAMTC3l1YW5xdWFueXVuMB4XDTIxMTAyMTA3MzgxMFoXDTIyMTAyMTA3MzgxMFowTjELMAkGA1UEBhMCQ04xDzANBgNVBAsTBjEwMDAwMTEbMBkGA1UECxMSMzcwNjg2MTk4MjA2MjcwMDE4MREwDwYDVQQDHghipFjrADAAMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKtnqLyfg1UT9+CinEmtdD4Krw+hQF/FycmMTY6PYNHdL4PTvPRQVbcJjbJml64N5mCmRFVmGEoP1Nsy/okGdRYbwjOlHVV2wsD4Vq/SUvx1m0VAtSNAxnrdKgRFum8nsMQnI8q0BNcK0yZpbIHhpJWjlhMmasa4UTQAEoBvkU85g6ygdWpJJIZcVoeNuWAqz9Hfh5ALyjcM8h4zCsm1wGtJOYmxOcLKdEnwiTYkHS20JKSxNKMIirYfzggEjtBYY5dV+bkQAGZkS9rk6wM5uoD69cEkJlpjFhngIBXFNfC9/qqG6ESND2KEXQQS7NiyYCFldXy0imX4IaeXfgrB2lcCAwEAAaNgMF4wDgYDVR0PAQH/BAQDAgPIMB0GA1UdDgQWBBRt08xXiHEcfBfYmqSSASpKA88hoTAMBgNVHRMEBTADAQEAMB8GA1UdIwQYMBaAFAXJVyJ421dWq4uErArr3pMS1T6YMA0GCSqGSIb3DQEBCwUAA4IBAQA4xHB8iko1fTVDJwC/t6Nrcu5faK89K4QdPlqbvhq7cK9gSKSfW1tsinRVlESvxxZ0CjHRxdfR0AOexu30r53MVWEQQxLSZddB95Hl5rScyZgRUcOQu0BVVVOPwHApYzzYVnLwkQzPTE0FMW1ZzjNp3karXQAbXra/LnAJYO+5oeDQfElW2onpBTpqPRrIkMVViwRVI3wgMLQmWLB7tDzbaTptsXKwMd1xg9s3fEthP9z82kBfaSmW9X6SEscKxaInVxy//u93/dQRn3Odvn4V8C3kqYPXhqp+Rnw0HqPkKNNdC0D8V8/Wv0TJtZVpSykX4K4p+M2KSI/RfNtoTELT
     * signGUID : e9aae7af95f24f9d97741f6f4f1f5288
     * signResult :
     * signStatus : FINISH
     * signToken : A4E7EAF828843CC7B3F30C6DF57D8B0F
     * subject : CN=护士01,OU=370686198206270018,OU=100001,C=CN
     * userCertCode : 0A6A199CE2004CD290E74812251C42DA
     */
    //CA用户姓名
    private String certCN;
    //证书容器号，是一个逻辑值，可唯一标识某CA用户的某一CA证书，通常是 userCertCode-certNo
    private String certContainer;
    //CA用户信息，比如医院等
    private String certDN;
    //CA证书唯一标识
    private String certNo;
    private String certSN;
    private String expireTime;
    private String signAlg;
    //证书Base64
    private String signCert;
    private String signGUID;
    private String signResult;
    //授权状态，值域：FINISH 完成，REFUSE 拒绝，EXPIRE 超时，TOSIGN 待授权
    private String signStatus;
    //授权后的安全令牌，后续调用静默签名接口要使用该值
    private String signToken;
    private String subject;
    //CA用户唯一标识
    private String userCertCode;

    public void saveInfo() {
        save(certCN,certContainer,certDN,certNo,expireTime,signCert,signStatus,signToken,userCertCode);
    }
    public static void save(String certCN,String certContainer,String certDN,String certNo
            ,String expireTime,String signCert,String signStatus,String signToken,String userCertCode) {
        SPStaticUtils.put(SharedPreference.CA_LOGIN_certCN,certCN);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_certContainer,certContainer);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_certDN,certDN);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_certNo,certNo);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_expireTime,expireTime);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_signCert,signCert);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_signStatus,signStatus);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_signToken,signToken);
        SPStaticUtils.put(SharedPreference.CA_LOGIN_userCertCode,userCertCode);
    }

    public static void clearInfo() {
        save("","","","","","","","","");
    }

    public String getCertCN() {
        return certCN;
    }

    public void setCertCN(String certCN) {
        this.certCN = certCN;
    }

    public String getCertContainer() {
        return certContainer;
    }

    public void setCertContainer(String certContainer) {
        this.certContainer = certContainer;
    }

    public String getCertDN() {
        return certDN;
    }

    public void setCertDN(String certDN) {
        this.certDN = certDN;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertSN() {
        return certSN;
    }

    public void setCertSN(String certSN) {
        this.certSN = certSN;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getSignAlg() {
        return signAlg;
    }

    public void setSignAlg(String signAlg) {
        this.signAlg = signAlg;
    }

    public String getSignCert() {
        return signCert;
    }

    public void setSignCert(String signCert) {
        this.signCert = signCert;
    }

    public String getSignGUID() {
        return signGUID;
    }

    public void setSignGUID(String signGUID) {
        this.signGUID = signGUID;
    }

    public String getSignResult() {
        return signResult;
    }

    public void setSignResult(String signResult) {
        this.signResult = signResult;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getSignToken() {
        return signToken;
    }

    public void setSignToken(String signToken) {
        this.signToken = signToken;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserCertCode() {
        return userCertCode;
    }

    public void setUserCertCode(String userCertCode) {
        this.userCertCode = userCertCode;
    }
}
