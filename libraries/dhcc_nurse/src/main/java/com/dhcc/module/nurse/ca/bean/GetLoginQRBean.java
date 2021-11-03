package com.dhcc.module.nurse.ca.bean;

/**
 * @author:gaoruishan
 * @date:202021/10/29/15:11
 * @email:grs0515@163.com
 */
public class GetLoginQRBean extends CaResult {

    /**
     * imageCode : ../CA.Ajax.QRCode.cls?ImageCode=142
     * isImage : 1
     * qrCode : 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
     * retCode : 0
     * retMsg :
     * signGUID : c572e856fe1a48a8a47dcfd13cbd2fae
     */
    //二维码图片代码，为支持ie7、ie8、ie9，可以在前端使用
    //img.src = imageCode 显示相应二维码
    private String imageCode;
    //是否为图片，值域1或0，若为0，则qrCode值为二维码的内容文本
    private String isImage;
    //二维码图片的base64编码
    private String qrCode;

    /**
     * signGUID 唯一标识此次二维码的扫描结果，在展现二维码后，使用signGUID调用获取登录返回值接口，获取此次二维码扫描结果
     */
    private String signGUID;
    //部分厂商自己实现H5页面，前端展示此页面即可，不需要自行实现页面展示二维码等
    private String authUrl;

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getIsImage() {
        return isImage;
    }

    public void setIsImage(String isImage) {
        this.isImage = isImage;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }


    public String getSignGUID() {
        return signGUID;
    }

    public void setSignGUID(String signGUID) {
        this.signGUID = signGUID;
    }
}
