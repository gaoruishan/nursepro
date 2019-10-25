package com.base.commlibs.event;

/**
 * 观察者
 * @param <T>
 */
public interface IObserver<T> {
    /**
     * 更新数据
     * @param o
     * @param arg
     */
    void update(IObservable o, T arg);

}
