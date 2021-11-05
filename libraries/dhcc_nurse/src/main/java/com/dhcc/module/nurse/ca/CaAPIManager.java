package com.dhcc.module.nurse.ca;

import android.text.TextUtils;
import android.util.Base64;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPStaticUtils;
import com.dhcc.module.nurse.ca.bean.AuthSignBean;
import com.dhcc.module.nurse.ca.bean.GetLoginQRBean;
import com.dhcc.module.nurse.ca.bean.GetLoginQRResultBean;
import com.dhcc.module.nurse.ca.bean.GetTokenIsValidBean;
import com.dhcc.module.nurse.ca.bean.HashDataBean;
import com.dhcc.module.nurse.ca.bean.Login2Bean;
import com.dhcc.module.nurse.ca.bean.ScanLogonBean;
import com.dhcc.module.nurse.ca.bean.SignBean;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/10/29/14:33
 * @email:grs0515@163.com
 */
public class CaAPIManager {
    //统一GetData接口
    public static final String GetData = "GetData";
    public static final String Login2 = "Login2";
    public static final String Sign = "Sign";
    //默认厂商-吉大正元
    public static String VenderCode = "JITCA";
    public static final String SignType = "PHONE";

    /**
     * Func,
     * VenderCode,
     * SignType,
     * P1 As %String = "",
     * P2 As %String = "",
     * P3 As %String = "",
     * P4 As %String = "",
     * P5 As %String = "",
     * P6 As %String = "",
     * P7 As %String = "",
     * P8 As %String = "",
     * P9 As %String = ""
     * @return
     */
    public static HashMap<String, String> getProperties(String Func, String VenderCode, String SignType,
                                                        String P1, String P2, String P3, String P4, String P5, String P6, String P7, String P8, String P9) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("Func", Func);
        properties.put("VenderCode", VenderCode);
        properties.put("SignType", SignType);
        if (!TextUtils.isEmpty(P1)) {
            properties.put("P1", P1);
        }
        if (!TextUtils.isEmpty(P2)) {
            properties.put("P2", P2);
        }
        if (!TextUtils.isEmpty(P3)) {
            properties.put("P3", P3);
        }
        if (!TextUtils.isEmpty(P4)) {
            properties.put("P4", P4);
        }
        if (!TextUtils.isEmpty(P5)) {
            properties.put("P5", P5);
        }
        if (!TextUtils.isEmpty(P6)) {
            properties.put("P6", P6);
        }
        if (!TextUtils.isEmpty(P7)) {
            properties.put("P7", P7);
        }
        if (!TextUtils.isEmpty(P8)) {
            properties.put("P8", P8);
        }
        if (!TextUtils.isEmpty(P9)) {
            properties.put("P9", P9);
        }
        return properties;
    }

    public static HashMap<String, String> getCommProperties(String Func, String venderCode, String SignType) {
        //更新一下VenderCode
        String mVenderCode = SPStaticUtils.getString(SharedPreference.CA_VenderCode, "JITCA");
        if (!TextUtils.isEmpty(mVenderCode)) {
            VenderCode = mVenderCode;
            venderCode = mVenderCode;
        }
        return getProperties(Func, venderCode, SignType, "", "", "", "", "", "", "", "", "");
    }

    /**
     * 2.1.获取登录二维码
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("GetLoginQR",venderCode,signType,random,guid)
     * "GetLoginQR"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * random	String	否	随机数，默认为空
     * guid	String	否	业务guid，默认为空
     * w ##Class(CA.Ajax.Webservice).GetData("GetLoginQR","JITCA","PHONE")
     */
    public static void GetLoginQR(CommonCallBack<GetLoginQRBean> callBack) {
        HashMap<String, String> properties = getCommProperties("GetLoginQR", VenderCode, SignType);
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<GetLoginQRBean> parserUtil = new ParserUtil<>();
                GetLoginQRBean bean = parserUtil.parserResult(jsonStr, callBack, GetLoginQRBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 2.2.获取登录二维码授权结果
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("GetLoginQRResult","venderCode",signType,signGUID)
     * "GetLoginQRResult"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * signGUID	String	是	二维码唯一标识，其值为【2.1获取登录二维码】返回的signGUID
     * w ##Class(CA.Ajax.Webservice).GetData("GetLoginQRResult","JITCA","PHONE","d871c9eb0f874de2b7d3f73e77f48df0")
     */
    public static void GetLoginQRResult(String signGUID, CommonCallBack<GetLoginQRResultBean> callBack) {
        HashMap<String, String> properties = getProperties("GetLoginQRResult", VenderCode, SignType, signGUID, "", "", "", "", "", "", "", "");
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<GetLoginQRResultBean> parserUtil = new ParserUtil<>();
                GetLoginQRResultBean bean = parserUtil.parserResult(jsonStr, callBack, GetLoginQRResultBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 证书登录
     * 接口名称	##Class(CA.Ajax.Webservice).Login2(usrID,UsrCertCode,serverRan,userSignedData,certNo,userCert,signType,venderCode)
     * usrID	String	否	HIS的UserID, 证书登录时此值【留空】
     * UsrCertCode	String	是	CA用户唯一标识
     * serverRan	String	否	服务器随机数，证书登录时用以校验客户端证书签名。手机登录时，此值【留空】
     * userSignedData	String	否	客户端证书对服务器随机数的签名值，证书登录时用以校验客户端签名。手机登录时，此值【留空】
     * certNo	String	是	CA证书唯一标识
     * userCert	String	是	Base64格式的用户数字证书
     * signType	String	是	签名方式代码
     * venderCode	String	是	厂商代码
     * w ##Class(CA.Ajax.Webservice).Login2("","0A6A199CE2004CD290E74812251C42DA","54C88243F84958582E130DB17A91B42E","MIIDvDCCAqSgAwIBAgIQVMiCQ/hJWFguEw2xepG0LjANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCQ04xDzANBgNVBAgTBlNoYW5YaTEQMA4GA1UEBxMHVGFpWXVhbjEtMCsGA1UEChMkU2hhblhpIERpZ2l0YWwgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MQwwCgYDVQQLEwNSU0ExFDASBgNVBAMTC3l1YW5xdWFueXVuMB4XDTIxMTAyMTA3MzgxMFoXDTIyMTAyMTA3MzgxMFowTjELMAkGA1UEBhMCQ04xDzANBgNVBAsTBjEwMDAwMTEbMBkGA1UECxMSMzcwNjg2MTk4MjA2MjcwMDE4MREwDwYDVQQDHghipFjrADAAMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKtnqLyfg1UT9+CinEmtdD4Krw+hQF/FycmMTY6PYNHdL4PTvPRQVbcJjbJml64N5mCmRFVmGEoP1Nsy/okGdRYbwjOlHVV2wsD4Vq/SUvx1m0VAtSNAxnrdKgRFum8nsMQnI8q0BNcK0yZpbIHhpJWjlhMmasa4UTQAEoBvkU85g6ygdWpJJIZcVoeNuWAqz9Hfh5ALyjcM8h4zCsm1wGtJOYmxOcLKdEnwiTYkHS20JKSxNKMIirYfzggEjtBYY5dV+bkQAGZkS9rk6wM5uoD69cEkJlpjFhngIBXFNfC9/qqG6ESND2KEXQQS7NiyYCFldXy0imX4IaeXfgrB2lcCAwEAAaNgMF4wDgYDVR0PAQH/BAQDAgPIMB0GA1UdDgQWBBRt08xXiHEcfBfYmqSSASpKA88hoTAMBgNVHRMEBTADAQEAMB8GA1UdIwQYMBaAFAXJVyJ421dWq4uErArr3pMS1T6YMA0GCSqGSIb3DQEBCwUAA4IBAQA4xHB8iko1fTVDJwC/t6Nrcu5faK89K4QdPlqbvhq7cK9gSKSfW1tsinRVlESvxxZ0CjHRxdfR0AOexu30r53MVWEQQxLSZddB95Hl5rScyZgRUcOQu0BVVVOPwHApYzzYVnLwkQzPTE0FMW1ZzjNp3karXQAbXra/LnAJYO+5oeDQfElW2onpBTpqPRrIkMVViwRVI3wgMLQmWLB7tDzbaTptsXKwMd1xg9s3fEthP9z82kBfaSmW9X6SEscKxaInVxy//u93/dQRn3Odvn4V8C3kqYPXhqp+Rnw0HqPkKNNdC0D8V8/Wv0TJtZVpSykX4K4p+M2KSI/RfNtoTELT","PHONE","JITCA")
     */
    public static void Login2(CommonCallBack<Login2Bean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        String userId = SPStaticUtils.getString(SharedPreference.USERID);
        if (!TextUtils.isEmpty(userId)) {
            properties.put("UsrID", SPStaticUtils.getString(SharedPreference.USERID));
        }
        properties.put("UsrCertCode", SPStaticUtils.getString(SharedPreference.CA_LOGIN_userCertCode));
        properties.put("CertNo", SPStaticUtils.getString(SharedPreference.CA_LOGIN_certNo));
        properties.put("UserCert", SPStaticUtils.getString(SharedPreference.CA_LOGIN_signCert));
        properties.put("SignType", SignType);
        properties.put("VenderCode", VenderCode);
        CommWebService.callCa(Login2, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<Login2Bean> parserUtil = new ParserUtil<>();
                Login2Bean bean = parserUtil.parserResult(jsonStr, callBack, Login2Bean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 判断token是否失效
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("GetTokenIsValid",venderCode,signType,certContainer,signToken)
     * 入参	名称	类型	必填	描述
     * "GetTokenIsValid"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * certContainer	String	是	证书容器号，是一个逻辑值，可唯一标识某CA用户的某一CA证书，通常是 userCertCode-certNo
     * signToken	String	是	授权后的安全令牌，后续调用静默签名接口要使用该值
     * w ##Class(CA.Ajax.Webservice).GetData("GetTokenIsValid","JITCA","PHONE","0A6A199CE2004CD290E74812251C42DA-54C88243F84958582E130DB17A91B42E","E5E85B86D8C76604651DC76488E938AC")
     */
    public static void GetTokenIsValid(CommonCallBack<GetTokenIsValidBean> callBack) {
        HashMap<String, String> properties = getProperties("GetTokenIsValid", VenderCode, SignType
                , SPStaticUtils.getString(SharedPreference.CA_LOGIN_certContainer), SPStaticUtils.getString(SharedPreference.CA_LOGIN_signToken), "", "", "", "", "", "", "");
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<GetTokenIsValidBean> parserUtil = new ParserUtil<>();
                GetTokenIsValidBean bean = parserUtil.parserResult(jsonStr, callBack, GetTokenIsValidBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 2.6.数据Hash
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("HashData",venderCode,signType,inData)
     * 入参	名称	类型	必填	描述
     * "HashData"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * inData	String	是	待摘要数据
     * w ##Class(CA.Ajax.Webservice).GetData("HashData","JITCA","PHONE","测试")
     */
    public static void HashData(String inData, CommonCallBack<HashDataBean> callBack) {
        HashMap<String, String> properties = getProperties("HashData", VenderCode, SignType
                , inData, "", "", "", "", "", "", "", "");
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<HashDataBean> parserUtil = new ParserUtil<>();
                HashDataBean bean = parserUtil.parserResult(jsonStr, callBack, HashDataBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 2.5.静默签名
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("AuthSign",venderCode,signType,inData, certContainer, signToken, episodeID)
     * 入参	名称	类型	必填	描述
     * "AuthSign"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * inData	String	是	待签数据
     * certContainer	String	是	证书容器号，是一个逻辑值，可唯一标识某CA用户的某一CA证书，其值为【2.2获取登录二维码授权结果】返回的certContainer
     * signToken	String	是	静默签名安全令牌，其值为【2.2获取登录二维码授权结果】返回的signToken
     * episodeID	String	否	患者就诊rowid，北京医信[厂商代码BJCA2]需要传入
     * arrJson	String	否	部分厂商要求传入签名文档名称，举例如{"subject":"入院病历"}
     * w ##Class(CA.Ajax.Webservice).GetData("AuthSign","JITCA","PHONE","测试","0A6A199CE2004CD290E74812251C42DA-54C88243F84958582E130DB17A91B42E","E5E85B86D8C76604651DC76488E938AC")
     */
    public static void AuthSign(String inData, String certContainer, String signToken, CommonCallBack<AuthSignBean> callBack) {
        HashMap<String, String> properties = getProperties("AuthSign", VenderCode, SignType
                , inData, certContainer, signToken, "", "", "", "", "", "");
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<AuthSignBean> parserUtil = new ParserUtil<>();
                AuthSignBean bean = parserUtil.parserResult(jsonStr, callBack, AuthSignBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 2.13. 验证签名数据，成功后返回时间戳值以及签名数据公共存储表ID
     * 接口名称	##Class(CA.Ajax.Webservice).Sign(UsrCertCode, code, contentHash, signValue, certNo)
     * 接口描述	由于CA厂商方面不保存任何签名过程中的信息和数据，所以在此封装了统一的签名存储方法。此方法中会再次验证证书、签名等信息合法性（如证书吊销、未关联以及签名值验证失败等），生成时间戳，并对签名数据进行存储。
     * 公共CA.DigitalSignature记录表中不存储送签原文（原始送签数据的Hash值）和原始送签数据，产品组需要自行记录日志，主要需要存储contentHash（签名原文——送签数据），以及此方法中的返回signID，以及其他索引信息。
     * 入参	名称	类型	必填	描述
     * UsrCertCode	String	是	CA用户唯一标识
     * code	String	是	产品组标识，目前病历为EPR/EMR,医生站为DOC,护理为NUR...
     * contentHash	String	是	实际送签数据(原始待签数据产生的HASH)
     * signValue	String	是	签名值(签名接口返回)
     * certNo	String	是	证书唯一标识(证书流水号)
     * w ##Class(CA.Ajax.Webservice).Sign("0A6A199CE2004CD290E74812251C42DA","NUR","B0F0C89244C11CEC5ED75559513137E12EDFDF4BCF0C61BB17011332686AB7E8","MIIFwgYJKoZIhvcNAQcCoIIFszCCBa8CAQExCzAJBgUrDgMCGgUAMBUGCSqGSIb3DQEHAaAIBAbmtYvor5WgggPAMIIDvDCCAqSgAwIBAgIQVMiCQ/hJWFguEw2xepG0LjANBgkqhkiG9w0BAQsFADCBgzELMAkGA1UEBhMCQ04xDzANBgNVBAgTBlNoYW5YaTEQMA4GA1UEBxMHVGFpWXVhbjEtMCsGA1UEChMkU2hhblhpIERpZ2l0YWwgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MQwwCgYDVQQLEwNSU0ExFDASBgNVBAMTC3l1YW5xdWFueXVuMB4XDTIxMTAyMTA3MzgxMFoXDTIyMTAyMTA3MzgxMFowTjELMAkGA1UEBhMCQ04xDzANBgNVBAsTBjEwMDAwMTEbMBkGA1UECxMSMzcwNjg2MTk4MjA2MjcwMDE4MREwDwYDVQQDHghipFjrADAAMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKtnqLyfg1UT9+CinEmtdD4Krw+hQF/FycmMTY6PYNHdL4PTvPRQVbcJjbJml64N5mCmRFVmGEoP1Nsy/okGdRYbwjOlHVV2wsD4Vq/SUvx1m0VAtSNAxnrdKgRFum8nsMQnI8q0BNcK0yZpbIHhpJWjlhMmasa4UTQAEoBvkU85g6ygdWpJJIZcVoeNuWAqz9Hfh5ALyjcM8h4zCsm1wGtJOYmxOcLKdEnwiTYkHS20JKSxNKMIirYfzggEjtBYY5dV+bkQAGZkS9rk6wM5uoD69cEkJlpjFhngIBXFNfC9/qqG6ESND2KEXQQS7NiyYCFldXy0imX4IaeXfgrB2lcCAwEAAaNgMF4wDgYDVR0PAQH/BAQDAgPIMB0GA1UdDgQWBBRt08xXiHEcfBfYmqSSASpKA88hoTAMBgNVHRMEBTADAQEAMB8GA1UdIwQYMBaAFAXJVyJ421dWq4uErArr3pMS1T6YMA0GCSqGSIb3DQEBCwUAA4IBAQA4xHB8iko1fTVDJwC/t6Nrcu5faK89K4QdPlqbvhq7cK9gSKSfW1tsinRVlESvxxZ0CjHRxdfR0AOexu30r53MVWEQQxLSZddB95Hl5rScyZgRUcOQu0BVVVOPwHApYzzYVnLwkQzPTE0FMW1ZzjNp3karXQAbXra/LnAJYO+5oeDQfElW2onpBTpqPRrIkMVViwRVI3wgMLQmWLB7tDzbaTptsXKwMd1xg9s3fEthP9z82kBfaSmW9X6SEscKxaInVxy//u93/dQRn3Odvn4V8C3kqYPXhqp+Rnw0HqPkKNNdC0D8V8/Wv0TJtZVpSykX4K4p+M2KSI/RfNtoTELTMYIBwDCCAbwCAQEwgZgwgYMxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIEwZTaGFuWGkxEDAOBgNVBAcTB1RhaVl1YW4xLTArBgNVBAoTJFNoYW5YaSBEaWdpdGFsIENlcnRpZmljYXRlIEF1dGhvcml0eTEMMAoGA1UECxMDUlNBMRQwEgYDVQQDEwt5dWFucXVhbnl1bgIQVMiCQ/hJWFguEw2xepG0LjAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIIBAAWCtiA4AxShSH/RPWHw60+e25MUEfmThcVwAUYE0/Whafd/pA7BnM6b15l3DqoZbGE1gsbjXpOJsvesX80Oug0GY2b/ZvmJ7ZUOzULWtujZ6a32ZxLqximE66gmI4fHGVjkFAWMeOK5MkpTCV/uw2c5ssi/V+386T9LLAZICdwV9nPs6TBsA2Ag18hHfw3ZqLCqhDVls0FlmvzwoENJbyrQtwVeNkn9uayPlI0MawyOtTTz/QKFW9iVSbYOfvIuuEtchGiQEfyzvqKJY9p73afd4HiliZMcs5O+MgIbk4LAbjMYMxJLuMZX76TswUjHxMc14x2KdHnSInw9Yj/8ONo=","54C88243F84958582E130DB17A91B42E")
     */
    public static void Sign(String ContentHash, String SignValue, String userCertCode, String certNo, CommonCallBack<SignBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("UsrCertCode", userCertCode);
        properties.put("Code", "NUR");
        properties.put("ContentHash", ContentHash);
        properties.put("SignValue", SignValue);
        properties.put("CertNo", certNo);
        CommWebService.callCa(Sign, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<SignBean> parserUtil = new ParserUtil<>();
                SignBean bean = parserUtil.parserResult(jsonStr, callBack, SignBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 2.9.手机证书PIN码登录并授权静默签名
     * 接口名称	##Class(CA.Ajax.Webservice).GetData("PhonePinLogin",venderCode,signType,userCertCode,pin)
     * 入参	名称	类型	必填	描述
     * "PhonePinLogin"	String	是	服务代码（常量）
     * venderCode	String	是	厂商代码
     * signType	String	是	签名方式代码
     * userCertCode	String	是	CA用户唯一标识，此处【通常就是HIS的工号】
     * pin	String	是	手机证书PIN码的Base64
     * w ##Class(CA.Ajax.Webservice).GetData("PhonePinLogin","JITCA","PHONE","测试","0A6A199CE2004CD290E74812251C42DA-54C88243F84958582E130DB17A91B42E","E5E85B86D8C76604651DC76488E938AC")
     */
    public static void PhonePinLogin(String userCertCode, String pin, CommonCallBack<GetLoginQRResultBean> callBack) {
        pin = Base64.encodeToString(pin.getBytes(), Base64.DEFAULT);
        HashMap<String, String> properties = getProperties("PhonePinLogin", VenderCode, SignType
                , userCertCode.toUpperCase(), pin, "", "", "", "", "", "", "");
        CommWebService.callCa(GetData, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<GetLoginQRResultBean> parserUtil = new ParserUtil<>();
                GetLoginQRResultBean bean = parserUtil.parserResult(jsonStr, callBack, GetLoginQRResultBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 扫码登陆
     * @param hisUserName
     * @param hisUserID
     * @param callBack
     */
    public static void ScanLogon(String hisUserName, String hisUserID, CommonCallBack<ScanLogonBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userName", hisUserName);
        properties.put("userId", hisUserID);
        CommWebService.call("Scanlogon", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanLogonBean> parserUtil = new ParserUtil<>();
                ScanLogonBean bean = parserUtil.parserResult(jsonStr, callBack, ScanLogonBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 医嘱执行-签名
     * @param currOrderItemStr
     * @param callBack
     */
    public static void ExecCaSignData(String ordItemsHashVal, String signID, String mainSignTimeStamp, String currOrderItemStr, String userId, String mainSignCert, String mainSignValue, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = getSignProperties(signID, mainSignTimeStamp, userId, ordItemsHashVal, mainSignCert, mainSignValue);
        properties.put("currOrderItemStr", currOrderItemStr);
        CommWebService.call("ExecCaSignData", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, null);
            }
        });
    }

    /**
     * 护理病历-签名
     * @param EpisodeID
     * @param callBack
     */
    public static void RecordCaSignData(String ordItemsHashVal, String signID, String mainSignTimeStamp, String EpisodeID, String recId, String userId, String mainSignCert, String mainSignValue, CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = getSignProperties(signID, mainSignTimeStamp, userId, ordItemsHashVal, mainSignCert, mainSignValue);
        properties.put("EpisodeID", EpisodeID);
        properties.put("recId", recId);
        CommWebService.call("RecordCaSignData", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, null);
            }
        });
    }

    public static HashMap<String, String> getSignProperties(String signID, String mainSignTimeStamp, String userId, String ordItemsHashVal, String mainSignCert, String mainSignValue) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("signID", signID);
        properties.put("userId", userId); //会有特殊处理-工号^姓名
        properties.put("operationType", "PDA");
        properties.put("ordItemsHashVal", ordItemsHashVal);
        properties.put("mainSignCert", mainSignCert);
        properties.put("mainSignValue", mainSignValue);
        properties.put("mainSignTimeStamp", mainSignTimeStamp);
        return properties;
    }


}
