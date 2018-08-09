package com.dhcc.nursepro.utils.wsutils;

import android.util.Log;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WSPresenter {
    private List<String> provinceList = new ArrayList<String>();
    // View
    BaseView mView;
    // 初始化Vieww
    public WSPresenter(BaseView mStudentView) {
        this.mView = mView;
    }
    public void init() {
        mView.showLoading();// 显示进度

        //显示进度条
//        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
        //添加参数
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("curVersion", "7.2");
        //通过工具类调用WebService接口getSupportProvince
        WebServiceUtils.callWebService("GetNewVersion", properties, new WebServiceUtils.WebServiceCallBack() {

            //WebService接口返回的数据回调到这个方法中
            @Override
            public void callBack(String result) {
                //关闭进度条
//                ProgressDialogUtils.dismissProgressDialog();
                if(result != null){
//                    provinceList = parseSoapObject(result);
                    Log.v("111122222re",result.toString());
                    Log.v("111122222",provinceList.toString());
                    mView.showStudents(provinceList,"getVer");
                }else{
                    mView.showStudents(null,"getNull");
                }
            }
        });
    }

//    private void jsonget(String data){
//        try {
//            JSONTokener jsonParser = new JSONTokener(RetData);
//            JSONObject js = (JSONObject) jsonParser.nextValue();
//            String err = js.getString("ErrorInfo");
//            if (err.length() != 0) {
//                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
//                return;
//            }
//            LoginUser.UserDR = js.getString("UserID");
//            LoginUser.UserID = NurseId.getText().toString();
//            LoginUser.UserName = js.getString("UserName");
//            list = JsonUtils.parsedata(js, "Locs");
//
//            if (list.size() > 0) {
//                Map itm = (Map) list.get(0);
//                LogonLoc.setText(itm.get("LocDesc").toString());
//                LoginUser.UserGroupID = itm.get("GroupID").toString();
//                LoginUser.UserLoc = itm.get("LocID").toString();
//                LoginUser.WardID = itm.get("WardID").toString();
//                Drawable draw = getResources().getDrawable(R.drawable.loc_item);
//                // LogonLoc.setBackground(draw);
//                LogonLoc.setBackgroundDrawable(draw);
//
//            }
//        } catch (JSONException ex) {
//            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
//            System.out.println(ex.toString());
//            // 异常处理代码
//        }
//    }
//


    private List<String> parseSoapObject(SoapObject result){
        List<String> list = new ArrayList<String>();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
        Log.v("111122222pro",provinceSoapObject.toString());
        if(provinceSoapObject == null) {
            return null;
        }
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
            list.add(provinceSoapObject.getProperty(i).toString());
        }

        return list;
    }

}
