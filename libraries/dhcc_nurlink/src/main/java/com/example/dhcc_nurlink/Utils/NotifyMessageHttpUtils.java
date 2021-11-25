package com.example.dhcc_nurlink.Utils;

/**
 * com.example.nurlink
 * <p>
 * author Q
 * Date: 2020/12/18
 * Time:11:16
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.raisound.speech.utils.L;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotifyMessageHttpUtils {


    public static void getMsg(String msg,callBack msgCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    msgCallBack.msgBack(sendPostMessage(msg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    };
    public callBack callBack;
    public interface callBack{
        public void msgBack(String msg);
    }
    /**
     * @return
     */
    public static String sendPostMessage(String msg){

        String PATH = "http://10.1.21.88:8088/api/test/test";
        String encode = "utf-8";
        Map<String, String> params = new HashMap<>();
        params.put("user","zhang");
        params.put("deviceid", SPUtils.getInstance().getString(SharedPreference.USERCODE));
        params.put("msg","来自"+SPUtils.getInstance().getString(SharedPreference.USERCODE)+"的消息："+msg);
        URL url = null;
        try {
            url = new URL(PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        try {//把请求的主体写入正文！！
            if(params != null&&!params.isEmpty()){
                //迭代器 　　　　　　　　　 //Map.Entry 是Map中的一个接口，他的用途是表示一个映射项（里面有Key和Value）　　　　
                for(Map.Entry<String, String> entry : params.entrySet()){
                buffer.append(entry.getKey()).append("=").
                        append(URLEncoder.encode(entry.getValue(),encode)).
                        append("&");
            }
        }
        //            System.out.println(buffer.toString());
        //删除最后一个字符&，多了一个;主体设置完毕
        buffer.deleteCharAt(buffer.length()-1);
        byte[] mydata = buffer.toString().getBytes();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(3000);
        connection.setDoInput(true);//表示从服务器获取数据
        connection.setDoOutput(true);//表示向服务器写数据

        connection.setRequestMethod("POST");
        //是否使用缓存
        connection.setUseCaches(false);
        //表示设置请求体的类型是文本类型
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-Length", String.valueOf(mydata.length));
        connection.connect();   //连接，不写也可以。。？？有待了解

        //获得输出流，向服务器输出数据
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(mydata,0,mydata.length);
        //获得服务器响应的结果和状态码
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            return changeInputeStream(connection.getInputStream(),encode);

        }
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        return "消息发送失败";
}

    private static final int REQUEST_TIMEOUT = 60 * 1000;//设置超时60秒
    public static final int HAND_REQUEST_SUCCESS = 300;
    public static final int HAND_REQUEST_FAILURE = 400;
    /**
     * post请求
     *
     * @param reqUrl
     * @param jsonParam json对象的String参数
     */
    public static void postJsonData(final String reqUrl, final String jsonParam) {
//        if (reqUrl == null) return;
        Log.e("2222223111", "postJsonData: "+reqUrl+jsonParam );
//        L.e("postJsonData请求地址：" + reqUrl);
//        L.e("postJsonData请求参数：" + jsonParam);
        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedReader reader = null;
                try {
                    URL url = new URL(reqUrl);// 创建连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(REQUEST_TIMEOUT);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestMethod("POST"); // 设置请求方式
                    connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
                    //设置发送数据长度（用于发送大量数据使用）
//                    connection.setRequestProperty("Content-Length", String.valueOf(jsonParam.length()));
                    //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
                    out.append(jsonParam);
                    out.flush();
                    out.close();
                    L.d(String.valueOf(connection.getResponseCode()));
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // 读取响应
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        String line;
                        String res = "";
                        while ((line = reader.readLine()) != null) {
                            res += line;
                        }
                        Log.e("2222223111", "run: "+res);
                        reader.close();
                        //通过handler来回传返回数据
//                        Message msg = new Message();
//                        msg.obj = res;
//                        msg.arg1 = HAND_REQUEST_SUCCESS;
//                        msg.what = type;
//                        handler.sendMessage(msg);
                    } else {
//                        Message msg = new Message();
//                        msg.obj = "请求错误," + connection.getResponseCode();
//                        msg.arg1 = HAND_REQUEST_FAILURE;
//                        msg.what = type;
//                        handler.sendMessage(msg);
                        Log.e("2222223111", "run: "+connection.getResponseCode());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
//                    Message msg = new Message();
//                    msg.obj = "请求异常,请检查网络";
//                    msg.arg1 = HAND_REQUEST_FAILURE;
//                    msg.what = type;
//                    handler.sendMessage(msg);
                    Log.e("2222223111", "runPOST IOException", e);
                }
            }
        }.start();
    }

    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
            .build();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            Log.e("222", "post: "+response.body().source().readByteString() );
            throw new IOException("Unexpected code " + response);
        }
    }





    /**
     * 将一个输入流转换成字符串
     * @param inputStream
     * @param encode
     * @return
     */
    private static String changeInputeStream(InputStream inputStream,String encode) {
        //通常叫做内存流，写在内存中的
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if(inputStream != null){
            try {
                while((len = inputStream.read(data))!=-1){
                    data.toString();

                    outputStream.write(data, 0, len);
                }
                //result是在服务器端设置的doPost函数中的
                result = new String(outputStream.toByteArray(),encode);
                outputStream.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Map map = GsonUtils.fromJson(result,Map.class);
        if (map.get("status").toString().contains("0")){
            return "消息发送成功";
        }else {
            return "消息发送失败";
        }
    }


    public void syc(String[] str1) {
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
//                String userName = params[0];
//                String userPasswd = params[1];
                String interface_url = "http://10.1.21.88:8088/api/test/test";
                String strResult = null;
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(interface_url).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);// 是否输入参数
//                    String sbParams = "userName="+userName+"&userPasswd="+userPasswd;
//                    byte[] bypes = sbParams.getBytes();
//                    connection.getOutputStream().write(bypes);// 输入参数
                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        is.close();
                        baos.flush();
                        baos.close();
                        strResult = new String(baos.toByteArray(), "UTF-8");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String message = "";
                //如果strrusult为空，jsonObject不会执行
//                    Toast.makeText(context,strResult+"--",Toast.LENGTH_LONG).show();
                if (strResult == null) {
                    message = "wrong";
                } else {
                    message = strResult;
                }
                return message;
            }

            @Override
            protected void onPostExecute(String s) {
                ToastUtils.showLong(s);
            }
        };
//要先有asyncTask，才能调用下面的方法。将外部产来的数组str1放入方法中
        asyncTask.execute(str1);

    }

}