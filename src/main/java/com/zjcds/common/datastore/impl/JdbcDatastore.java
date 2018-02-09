package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.enums.DsType;
import com.zjcds.common.datastore.exception.ConnectionFailException;
import com.zjcds.common.datastore.MetaDataNavigator;
import com.zjcds.common.datastore.NativeSqlExecutor;
import com.zjcds.common.datastore.UpdateableDatastore;
import com.zjcds.common.datastore.UpdateableDatastoreConnection;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
@Getter
@Setter
public class JdbcDatastore extends AbstractDataStore<UpdateableDatastoreConnection> implements UpdateableDatastore<UpdateableDatastoreConnection>,NativeSqlExecutor {

    private DataSource dataSource;

    private JdbcDatastoreConnection jdbcDatastoreConnection ;

    private DsType datastoreType;

    private MetaDataNavigator metaDataNavigator;

    private NativeSqlExecutor nativeSqlExecutorDelegator;

    @Override
    public UpdateableDatastoreConnection getDatastoreConnection() {
        return jdbcDatastoreConnection;
    }

    @Override
    public UpdateableDatastoreConnection getUpdateableDatastoreConnection() {
        return jdbcDatastoreConnection;
    }

    @Override
    public DsType getDatastoreType() {
        return datastoreType;
    }

    @Override
    public MetaDataNavigator getMetaDataNavigator() {
        return metaDataNavigator;
    }

    @Override
    public int update(String sql) throws SQLException {
        return nativeSqlExecutorDelegator.update(sql);
    }

    @Override
    public int update(List<String> sqls) throws SQLException {
        return nativeSqlExecutorDelegator.update(sqls);
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        return nativeSqlExecutorDelegator.update(sql,params);
    }

    @Override
    public void testConnection() throws ConnectionFailException {
        Connection connection = null;
        try {
            Assert.notNull(dataSource,"传入的数据源参数不能为空！");
            connection = dataSource.getConnection();
        }
        catch (SQLException e){
            throw new ConnectionFailException("不能建立数据库连接",e);
        }
        finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public static class Builder{

        private DataSource dataSource;

        private String name;

        private String description;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder dataSource(DataSource dataSource){
            this.dataSource = dataSource;
            return this;
        }

        public JdbcDatastore build(){
            JdbcDatastore jdbcDatastore = new JdbcDatastore();
            Assert.notNull(dataSource,"数据源不能为空！");
            jdbcDatastore.setDataSource(dataSource);
            Assert.hasText(name,"数据源名称不能为空！");
            jdbcDatastore.setName(name);
            jdbcDatastore.setDescription(description);
            JdbcDatastoreConnection jdbcDatastoreConnection = new JdbcDatastoreConnection(dataSource,jdbcDatastore);
            jdbcDatastore.setJdbcDatastoreConnection(jdbcDatastoreConnection);
            jdbcDatastore.setDatastoreType(DsType.getDsType(jdbcDatastoreConnection.getProductName()));
            jdbcDatastore.setMetaDataNavigator(new MetaDataNavigatorImpl(jdbcDatastoreConnection));
            jdbcDatastore.setNativeSqlExecutorDelegator(new NativeSqlExecutorImpl(jdbcDatastore));
            return jdbcDatastore;
        }
    }
}
