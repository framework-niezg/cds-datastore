package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.MetaDataNavigator;
import com.zjcds.common.datastore.enums.DsType;
import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.schema.Table;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public class MetaDataNavigatorImpl implements MetaDataNavigator {

    private JdbcDataContext dataContext;

    public MetaDataNavigatorImpl(JdbcDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public String getProductName() {
        return dataContext.getDatabaseProductName();
    }

    @Override
    public DsType getDsType() {
        return DsType.getDsType(getProductName());
    }

    @Override
    public Table getTable(String tableName) {
        return getTable(dataContext.getDefaultSchema().getName(),tableName);
    }

    @Override
    public Table getTable(String schemaName, String tableName) {
        return dataContext.getTableByQualifiedLabel(schemaName+"."+tableName);
    }
}
