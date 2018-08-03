package com.dhcc.nursepro.WsTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dhcc.nursepro.R;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    private List<String> provinceList = new ArrayList<String>();
    private List<String> cityStringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
//        Intent i = new Intent(MainActivity1.this, MainActivityWeather.class);
//        startActivity(i);

        init2();
        init2();
    }

    private void init2() {
        ListView mProvinceList = (ListView) findViewById(R.id.province_list);
        ListView mProvinceList1 = (ListView) findViewById(R.id.province_list1);
        ListView mProvinceList2 = (ListView) findViewById(R.id.province_list2);
        ListView mProvinceList3 = (ListView) findViewById(R.id.province_list3);
        ListView mProvinceList4 = (ListView) findViewById(R.id.province_list4);
        init1("欧洲",mProvinceList);
        init1("黑龙江",mProvinceList1);
        init1("山东",mProvinceList2);
        init1("江苏",mProvinceList3);
        init1("广东",mProvinceList4);
    }
    private void init() {
        final ListView mProvinceList = (ListView) findViewById(R.id.province_list);

        //显示进度条
//        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");

        //通过工具类调用WebService接口
        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getSupportProvince", null, new WebServiceUtils.WebServiceCallBack() {

            //WebService接口返回的数据回调到这个方法中
            @Override
            public void callBack(SoapObject result) {
                //关闭进度条
//                ProgressDialogUtils.dismissProgressDialog();
                if(result != null){
                    provinceList = parseSoapObject(result);
                    mProvinceList.setAdapter(new ArrayAdapter<String>(MainActivity1.this, android.R.layout.simple_list_item_1, provinceList));
                }else{
                    Toast.makeText(MainActivity1.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mProvinceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Intent intent = new Intent(MainActivity1.this, CityActivity.class);
//                intent.putExtra("province", provinceList.get(position));
//                startActivity(intent);
                Log.v("1111111122222",provinceList.get(position));
//                    init1(provinceList.get(position));
            }
        });


    }
    private List<String> parseSoapObject(SoapObject result){
        List<String> list = new ArrayList<String>();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
        if(provinceSoapObject == null) {
            return null;
        }
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
            list.add(provinceSoapObject.getProperty(i).toString());
        }

        return list;
    }




    private void init1(String str,ListView view) {
        final ListView mCityList = view;

        //显示进度条
//        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");

        //添加参数
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("byProvinceName", str);

        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getSupportCity", properties, new WebServiceUtils.WebServiceCallBack() {

            @Override
            public void callBack(SoapObject result) {
//                ProgressDialogUtils.dismissProgressDialog();
                if(result != null){
                    cityStringList = parseSoapObject1(result);
                    mCityList.setAdapter(new ArrayAdapter<String>(MainActivity1.this, android.R.layout.simple_list_item_1, cityStringList));
                    Toast.makeText(MainActivity1.this, "获取"+cityStringList, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity1.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Intent intent = new Intent(CityActivity.this, WeatherActivity.class);
//                intent.putExtra("city", cityStringList.get(position));
//                startActivity(intent);
            }
        });
    }


    private List<String> parseSoapObject1(SoapObject result){
        List<String> list = new ArrayList<String>();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportCityResult");
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
            String cityString = provinceSoapObject.getProperty(i).toString();
            list.add(cityString.substring(0, cityString.indexOf("(")).trim());
        }

        return list;
    }
}
