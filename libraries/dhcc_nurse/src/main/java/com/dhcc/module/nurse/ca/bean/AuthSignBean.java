package com.dhcc.module.nurse.ca.bean;

/**
 * @author:gaoruishan
 * @date:202021/11/1/16:14
 * @email:grs0515@163.com
 */
public class AuthSignBean extends CaResult{

    /**
     * certCN :
     * certContainer : 0A6A199CE2004CD290E74812251C42DA-54C88243F84958582E130DB17A91B42E
     * certDN :
     * certNo : 54C88243F84958582E130DB17A91B42E
     * retCode : 0
     * retMsg :
     * signAlg :
     * signCert :
     * signGUID :
     * signResult : MIIFwgYJKoZIhvcNAQcCoIIFszCCBa8CAQExCzAJBgUrDgMCGgUAMBUGCSqGSIb3DQEHAaAIBAbmtYvor5WgggPAMIIDvDCCAqSgAwIBAgIQVMiCQ/hJWFguEw2xepG0LjANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCQ04xDzANBgNVBAgTBlNoYW5YaTEQMA4GA1UEBxMHVGFpWXVhbjEtMCsGA1UEChMkU2hhblhpIERpZ2l0YWwgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MQwwCgYDVQQLEwNSU0ExFDASBgNVBAMTC3l1YW5xdWFueXVuMB4XDTIxMTAyMTA3MzgxMFoXDTIyMTAyMTA3MzgxMFowTjELMAkGA1UEBhMCQ04xDzANBgNVBAsTBjEwMDAwMTEbMBkGA1UECxMSMzcwNjg2MTk4MjA2MjcwMDE4MREwDwYDVQQDHghipFjrADAAMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKtnqLyfg1UT9+CinEmtdD4Krw+hQF/FycmMTY6PYNHdL4PTvPRQVbcJjbJml64N5mCmRFVmGEoP1Nsy/okGdRYbwjOlHVV2wsD4Vq/SUvx1m0VAtSNAxnrdKgRFum8nsMQnI8q0BNcK0yZpbIHhpJWjlhMmasa4UTQAEoBvkU85g6ygdWpJJIZcVoeNuWAqz9Hfh5ALyjcM8h4zCsm1wGtJOYmxOcLKdEnwiTYkHS20JKSxNKMIirYfzggEjtBYY5dV+bkQAGZkS9rk6wM5uoD69cEkJlpjFhngIBXFNfC9/qqG6ESND2KEXQQS7NiyYCFldXy0imX4IaeXfgrB2lcCAwEAAaNgMF4wDgYDVR0PAQH/BAQDAgPIMB0GA1UdDgQWBBRt08xXiHEcfBfYmqSSASpKA88hoTAMBgNVHRMEBTADAQEAMB8GA1UdIwQYMBaAFAXJVyJ421dWq4uErArr3pMS1T6YMA0GCSqGSIb3DQEBCwUAA4IBAQA4xHB8iko1fTVDJwC/t6Nrcu5faK89K4QdPlqbvhq7cK9gSKSfW1tsinRVlESvxxZ0CjHRxdfR0AOexu30r53MVWEQQxLSZddB95Hl5rScyZgRUcOQu0BVVVOPwHApYzzYVnLwkQzPTE0FMW1ZzjNp3karXQAbXra/LnAJYO+5oeDQfElW2onpBTpqPRrIkMVViwRVI3wgMLQmWLB7tDzbaTptsXKwMd1xg9s3fEthP9z82kBfaSmW9X6SEscKxaInVxy//u93/dQRn3Odvn4V8C3kqYPXhqp+Rnw0HqPkKNNdC0D8V8/Wv0TJtZVpSykX4K4p+M2KSI/RfNtoTELTMYIBwDCCAbwCAQEwgZgwgYMxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIEwZTaGFuWGkxEDAOBgNVBAcTB1RhaVl1YW4xLTArBgNVBAoTJFNoYW5YaSBEaWdpdGFsIENlcnRpZmljYXRlIEF1dGhvcml0eTEMMAoGA1UECxMDUlNBMRQwEgYDVQQDEwt5dWFucXVhbnl1bgIQVMiCQ/hJWFguEw2xepG0LjAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIIBAAWCtiA4AxShSH/RPWHw60+e25MUEfmThcVwAUYE0/Whafd/pA7BnM6b15l3DqoZbGE1gsbjXpOJsvesX80Oug0GY2b/ZvmJ7ZUOzULWtujZ6a32ZxLqximE66gmI4fHGVjkFAWMeOK5MkpTCV/uw2c5ssi/V+386T9LLAZICdwV9nPs6TBsA2Ag18hHfw3ZqLCqhDVls0FlmvzwoENJbyrQtwVeNkn9uayPlI0MawyOtTTz/QKFW9iVSbYOfvIuuEtchGiQEfyzvqKJY9p73afd4HiliZMcs5O+MgIbk4LAbjMYMxJLuMZX76TswUjHxMc14x2KdHnSInw9Yj/8ONo=
     * signStatus : FINISH
     * userCertCode : 0A6A199CE2004CD290E74812251C42DA
     */

    private String certCN;
    private String certContainer;
    private String certDN;
    private String certNo;
    private String signAlg;
    private String signCert;
    private String signGUID;
    //签名结果[retCode字段为0时，业务系统获取此值，并且此值非空，才是有效结果]
    private String signResult;
    //授权状态，值域：FINISH 完成，REFUSE 拒绝，EXPIRE 超时，TOSIGN 待授权
    private String signStatus;
    private String userCertCode;

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

    public String getUserCertCode() {
        return userCertCode;
    }

    public void setUserCertCode(String userCertCode) {
        this.userCertCode = userCertCode;
    }
}