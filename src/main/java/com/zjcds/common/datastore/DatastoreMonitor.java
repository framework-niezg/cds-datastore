package com.zjcds.common.datastore;

import com.zjcds.common.datastore.exception.ConnectionFailException;

/**
 * created date：2017-08-05
 *
 * @author niezhegang
 */
public interface DatastoreMonitor {

    /**
     * 测试数据源的连接状态，失败抛出异常
     * @throws ConnectionFailException
     */
    public void testConnection() throws ConnectionFailException;

}
