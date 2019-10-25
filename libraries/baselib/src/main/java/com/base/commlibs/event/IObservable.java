package com.base.commlibs.event;

import java.util.Vector;

public class IObservable<T> {
    private boolean changed = false;
    private Vector<IObserver<T>> obs;


    public IObservable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(IObserver<T> o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(IObserver o) {
        obs.removeElement(o);
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    public synchronized int countObservers() {
        return obs.size();
    }

    /**
     * 刷新数据-调用notifyObservers通知观察者
     * @param t
     */
    public synchronized void notifyObserver(T t) {
        //改变状态
        changed = true;
        //通知所有观察者
        notifyObservers(t);
    }

    private void notifyObservers(T arg) {

        synchronized (this) {
            if (!hasChanged()) {
                return;
            }
            clearChanged();
        }
        for (IObserver<T> ob : obs) {
            ob.update(this, arg);
        }

    }

    private synchronized boolean hasChanged() {
        return changed;
    }

    private synchronized void clearChanged() {
        changed = false;
    }

    public synchronized void setChanged() {
        changed = true;
    }
}
