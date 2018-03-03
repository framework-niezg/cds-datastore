package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.DatastoreMonitor;
import com.zjcds.common.datastore.exception.ConnectionFailException;
import org.springframework.util.Assert;

/**
 * 对数据库状态进行监控
 * created date：2018-02-09
 * @author niezhegang
 */
public class DatastoreMonitorImpl implements DatastoreMonitor{

    private JdbcDatastore jdbcDatastore;

    public DatastoreMonitorImpl(JdbcDatastore jdbcDatastore) {
        Assert.notNull(jdbcDatastore,"jdbcDatastore不能为空！");
        this.jdbcDatastore = jdbcDatastore;
    }

    @Override
    public void testConnection() throws ConnectionFailException {

    }
}
