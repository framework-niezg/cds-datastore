package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.*;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.UpdateableDataContext;
import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.schema.TableType;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public class JdbcDatastore extends AbstractDataStore implements UpdateableDatastore {

    private DataSource dataSource;

    private JdbcDataContext jdbcDataContext;

    private MetaDataNavigator metaDataNavigator;

    private MetaSqlExecutor metaSqlExecutor;

    public MetaSqlExecutor getMetaSqlExecutor() {
        return metaSqlExecutor;
    }

    private void setMetaSqlExecutor(MetaSqlExecutor metaSqlExecutor) {
        this.metaSqlExecutor = metaSqlExecutor;
    }

    private NativeSqlExecutor nativeSqlExecutor;

    private DatastoreMonitor datastoreMonitor;

    @Override
    public UpdateableDataContext getUpdateableDataContext() {
        return jdbcDataContext;
    }

    @Override
    public DataContext getDataContext() {
        return jdbcDataContext;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public MetaDataNavigator getMetaDataNavigator() {
        return metaDataNavigator;
    }

    public NativeSqlExecutor getNativeSqlExecutor() {
        return nativeSqlExecutor;
    }

    public DatastoreMonitor getDatastoreMonitor() {
        return datastoreMonitor;
    }

    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void setJdbcDataContext(JdbcDataContext jdbcDataContext) {
        this.jdbcDataContext = jdbcDataContext;
    }

    private void setMetaDataNavigator(MetaDataNavigator metaDataNavigator) {
        this.metaDataNavigator = metaDataNavigator;
    }

    private void setNativeSqlExecutor(NativeSqlExecutor nativeSqlExecutor) {
        this.nativeSqlExecutor = nativeSqlExecutor;
    }

    private void setDatastoreMonitor(DatastoreMonitor datastoreMonitor) {
        this.datastoreMonitor = datastoreMonitor;
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
            //配置jdbc数据上下文对象
            JdbcDataContext jdbcDataContext = new JdbcDataContext(dataSource, TableType.DEFAULT_TABLE_TYPES, null);
            jdbcDatastore.setJdbcDataContext(jdbcDataContext);
            //配置元数据导航对象
            jdbcDatastore.setMetaDataNavigator(new MetaDataNavigatorImpl(jdbcDataContext));
            //配置元sql执行器
            jdbcDatastore.setMetaSqlExecutor(new MetaSqlExecutorImpl(jdbcDataContext));
            //配置原生sql执行对象
            jdbcDatastore.setNativeSqlExecutor(new NativeSqlExecutorImpl(jdbcDatastore));
            //配置数据监控对象
            DatastoreMonitor datastoreMonitor = new DatastoreMonitorImpl(jdbcDatastore);
            jdbcDatastore.setDatastoreMonitor(datastoreMonitor);
            return jdbcDatastore;
        }
    }
}
