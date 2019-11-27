package com.base.commlibs.utils.work;

/**
 * @author cd5160866
 */
public interface Worker {

    /**
     * 执行任务
     *
     * @param current 当前节点
     */
    void doWork(Node current);

}