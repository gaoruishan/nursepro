package com.example.dhcc_nurlink.Utils;

import com.example.dhcc_nurlink.bean.TelphoneUserBean;
import com.example.dhcc_nurlink.bean.UserBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.dhcc_nurlink.Utils
 * <p>
 * author Q
 * Date: 2020/11/27
 * Time:14:21
 */
public class UserGsonFormatUtil<T> {
    private Gson gson = new Gson();
    public T getUserBean(String jsonStr,Class<T> clz){
        TelphoneUserBean telphoneUserBean = new TelphoneUserBean();
        if (jsonStr.isEmpty()){
            for (int i = 0; i < 15; i++) {
                UserBean userBean = new UserBean();
                if (i/4 == 1){
                    userBean.setTelStatus("Y");
                }else {
                    userBean.setTelStatus("N");
                }
                userBean.setUserName("用户"+i);

                telphoneUserBean.getTelListBean().add(userBean);
            }

            jsonStr = gson.toJson(telphoneUserBean);
        }

        T t = gson.fromJson(jsonStr, clz);

        return t;
    }
}
