package com.base.commlibs.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 事件处理Bus
 * 使用说明:
 * 1,注册:
 *         FindBus.getInstance().register(MessageEvent.class, new IObserver<MessageEvent>() {
 *             @Override
 *             public void update(IObservable o, MessageEvent arg) {
 *                 Log.e("TAG", "(Test.java:29) 3333" + arg.toString());
 *             }
 *         });
 * 2,发送:  
 *         FindBus.getInstance().send(new MessageEvent(result, 3333));
 * @author:gaoruishan
 * @date:202019-10-24/14:50
 * @email:grs0515@163.com
 */
public class FindBus {

    private static final String TAG = FindBus.class.getSimpleName();
    private static FindBus mInstance = new FindBus();
    private Map<String, IObservable> observableMap;

    private FindBus() {
        observableMap = new HashMap<>();
    }

    public static FindBus getInstance() {
        return mInstance;
    }

    /**
     * 注册-观察者
     * [注意更新UI,要在主线程]
     * @param t         指定类型
     * @param iObserver 回调
     * @param <T>       泛型返回类型
     */
    public <T> void register(Class<T> t, IObserver<T> iObserver) {
        IObservable<T> observable = new IObservable<>();
        observable.addObserver(iObserver);
        observableMap.put(t.getSimpleName(), observable);
    }

    /**
     * 取消注册
     * @param t 指定类型
     * @param o 观察者
     * @param <T>
     */
    public <T> void unregister(Class<T> t, IObserver o) {
        if (observableMap != null) {
            IObservable observable = observableMap.get(t.getSimpleName());
            if (observable != null) {
                observable.deleteObserver(o);
            }
        }
    }

    public void unregisterAll() {
        if (observableMap != null) {
            Set<Map.Entry<String, IObservable>> entries = observableMap.entrySet();
            for (Map.Entry<String, IObservable> entry : entries) {
                entry.getValue().deleteObservers();
            }
        }
    }

    /**
     * 发送事件
     * @param t   指定类型
     * @param <T> 泛型返回类型
     */
    public <T> void send(T t) {
        if (observableMap != null && t != null) {
            IObservable observable = observableMap.get(t.getClass().getSimpleName());
            if (observable != null) {
                observable.notifyObserver(t);
            }
        }
    }
}
