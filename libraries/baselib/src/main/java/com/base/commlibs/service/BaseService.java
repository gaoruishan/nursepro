package com.base.commlibs.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ServiceUtils;

/**
 * 基本的Service类
 * @author:gaoruishan
 * @date:202020-04-15/09:38
 * @email:grs0515@163.com
 */
public abstract class BaseService extends Service {

    public interface ServiceCallBack {
        void call(BaseService service);
    }

    /**
     * 绑定Service
     * @param cls
     * @param callBack
     */
    public static void bindService(final Class<?> cls,ServiceCallBack callBack) {
        ServiceUtils.bindService(cls, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //程序走到这里之后，就得到了Service对象
                BaseService mService = ((BaseService.ServiceBinder) service).getInstance();
                if (callBack != null) {
                    callBack.call(mService);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public class ServiceBinder extends Binder {

        public BaseService getInstance() {
            return BaseService .this;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    /**
     * 输血开始计时
     */
    public void startBloodTime(){

    }

}
