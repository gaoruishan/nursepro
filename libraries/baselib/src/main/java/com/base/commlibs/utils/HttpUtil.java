package com.base.commlibs.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * HttpURLConnection网络请求工具类
 */
public class HttpUtil {

    private static final String TAG = HttpUtil.class.getSimpleName();

    public static <T> void get(String urlString, Class<T> classOfT, SimpleCallBack<T> callBack) {
        get(urlString, new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                LocalTestManager.saveLog(CommFile.getURLNameString(urlString), result);
                Log.e(TAG, "(HttpUtil.java:30) " + urlString + "," + result);
                if (callBack != null && !TextUtils.isEmpty(result)) {
                    T t = new Gson().fromJson(result, classOfT);
                    callBack.call(t, 0);
                }
            }
        });
    }

    public static void get(String urlString, SimpleCallBack<String> callBack) {
        ThreadUtil.execute(new ThreadUtil.Task<String>() {
            @Override
            public String doInBackground() throws Throwable {
                return sendGetRequest(urlString, null);
            }

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "(HttpUtil.java:30) onSuccess=" + result);
                if (callBack != null) {
                    callBack.call(result, 0);
                }
            }
        });
    }
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }	//将所有验证的结果都设为true
    };
    /**
     * 不检查任何证书
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // 创建信任管理器
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkClientTrusted");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkServerTrusted");
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Get请求
     */
    public static String sendGetRequest(final String urlString, final HttpCallbackListener listener) {
        // 因为网络请求是耗时操作，所以需要另外开启一个线程来执行该任务。
        URL url;
        Log.e(TAG,"(HttpUtil.java:67) "+urlString);
        HttpURLConnection httpURLConnection = null;
        HttpsURLConnection httpsURLConnection;
        try {
            // 根据URL地址创建URL对象
            url = new URL(urlString);

            trustAllHosts();
            //判断是https请求还是http请求
            if (url.getProtocol().toLowerCase().equals("https")) {
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setHostnameVerifier(DO_NOT_VERIFY);
                httpURLConnection = httpsURLConnection;
            } else {
                // 获取HttpURLConnection对象
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            // 设置请求方式，默认为GET
            httpURLConnection.setRequestMethod("GET");
            // 设置连接超时
            httpURLConnection.setConnectTimeout(5000);
            // 设置读取超时
            httpURLConnection.setReadTimeout(8000);
                    /*// 如果需要设置apikey，如下设置：
                    httpURLConnection.setRequestProperty(
                    "apikey", "1b18****13f3****729210d6****8e29");*/

            // 响应码为200表示成功，否则失败。
            if (httpURLConnection.getResponseCode() != 200) {
                Log.e(TAG, "(HttpUtil.java:79) " + "请求失败");
            }
            // 获取网络的输入流
            InputStream is = httpURLConnection.getInputStream();
            // 读取输入流中的数据
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            bis.close();
            is.close();
            // 响应的数据
            byte[] response = baos.toByteArray();

            if (listener != null) {
                // 回调onFinish()方法
                listener.onFinish(response);
            }
            return baos.toString();
        } catch (MalformedURLException e) {

            if (listener != null) {
                // 回调onError()方法
                listener.onError(e);
            }
        } catch (IOException e) {
            if (listener != null) {
                // 回调onError()方法
                listener.onError(e);
            }
        } finally {
            if (httpURLConnection != null) {
                // 释放资源
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

    public static Boolean checkUrl(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            //HTTP connect
            try {
                conn.connect();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            int code = conn.getResponseCode();
            if ((code >= 100) && (code < 400)) {
                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Post请求
     */
    public static void sendPostRequest(
            final String urlString, final HttpCallbackListener listener) {

        // 因为网络请求是耗时操作，所以需要另外开启一个线程来执行该任务。
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(urlString);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");

                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(8000);

                    // 设置运行输入
                    httpURLConnection.setDoInput(true);
                    // 设置运行输出
                    httpURLConnection.setDoOutput(true);

                    // 请求的数据
                    String data = "num=" + URLEncoder.encode("10", "UTF-8") +
                            "&page=" + URLEncoder.encode("1", "UTF-8");

                    // 将请求的数据写入输出流中
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    bos.write(data.getBytes());
                    bos.flush();
                    bos.close();
                    os.close();

                    if (httpURLConnection.getResponseCode() == 200) {

                        InputStream is = httpURLConnection.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while ((len = bis.read(bytes)) != -1) {
                            baos.write(bytes, 0, len);
                        }
                        is.close();
                        bis.close();
                        // 响应的数据
                        byte[] response = baos.toByteArray();

                        if (listener != null) {
                            // 回调onFinish()方法
                            listener.onFinish(response);
                        }
                    } else {
                        Log.e(TAG, "(HttpUtil.java:79) " + "请求失败");
                    }
                } catch (MalformedURLException e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (httpURLConnection != null) {
                        // 最后记得关闭连接
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void bindImage(ImageView imageView, String image) {
        if (TextUtils.isEmpty(image) || imageView == null) {
            return;
        }
        String url = BaseWebServiceUtils.getServiceUrl("/" + image);
        HttpUtil.getImageBitmap(url, new SimpleCallBack<Bitmap>() {
            @Override
            public void call(Bitmap result, int type) {
                if (result != null) {
                    imageView.setImageBitmap(result);
                }
            }
        });
    }
    public static void bindImageView(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url) || url == null) {
            return;
        }
        HttpUtil.getImageBitmap(url, new SimpleCallBack<Bitmap>() {
            @Override
            public void call(Bitmap result, int type) {
                if (result != null) {
                    imageView.setImageBitmap(result);
                }
            }
        });
    }

    public static void getImageBitmap(String urlString, SimpleCallBack<Bitmap> callBack) {
        Log.e(TAG, "(HttpUtil.java:253) 000");
        ThreadUtil.execute(new ThreadUtil.Task<Bitmap>() {
            @Override
            public Bitmap doInBackground() throws Throwable {
                Bitmap bitmap = CommFile.readBitmap(urlString);
                if (bitmap != null) {
                    Log.e(TAG, "(HttpUtil.java:258) 1111");
                    return bitmap;
                }
                Log.e(TAG, "(HttpUtil.java:258) 2222");
                bitmap = getImageBitmap(urlString);
                //保存文件
                if (bitmap != null) {
                    CommFile.writeFile(urlString, bitmap);
                }
                return bitmap;
            }

            @Override
            public void onSuccess(Bitmap result) {
                Log.e(TAG, "(HttpUtil.java:30) onSuccess=" + result);
                if (callBack != null) {
                    callBack.call(result, 0);
                }
            }
        });
    }

    private static Bitmap getImageBitmap(String url) {
        URL imgUrl;
        HttpURLConnection conn = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            conn = (HttpURLConnection) imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            if (is != null) {
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "(HttpUtil.java:57) " + e.toString());
        } finally {
            if (conn != null) {
                // 释放资源
                conn.disconnect();
            }
        }
        return bitmap;
    }


    public static boolean isImage(String image) {
        if (!TextUtils.isEmpty(image)) {
            return image.endsWith(".jpg") || image.endsWith(".png")
                    || image.endsWith(".jpeg");
        }
        return false;
    }

    /**
     * HttpURLConnection网络请求返回监听器
     */
    public interface HttpCallbackListener {
        // 网络请求成功
        void onFinish(byte[] response);

        // 网络请求失败
        void onError(Exception e);
    }
}