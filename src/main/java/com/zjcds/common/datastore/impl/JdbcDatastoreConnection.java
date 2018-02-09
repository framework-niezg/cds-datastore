package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.Datastore;
import com.zjcds.common.datastore.UpdateableDatastoreConnection;
import org.apache.metamodel.UpdateableDataContext;
import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.schema.TableType;

import javax.sql.DataSource;


public class JdbcDatastoreConnection extends AbstractDatastoreConnection implements UpdateableDatastoreConnection {

    private final JdbcDataContext dataContext;



    public JdbcDatastoreConnection(final DataSource ds, final Datastore datastore) {
        this(ds, TableType.DEFAULT_TABLE_TYPES, null, datastore);
    }

    public JdbcDatastoreConnection(final DataSource ds, final TableType[] tableTypes, final String catalogName,
                                   final Datastore datastore) {
        super(datastore);
        dataContext = new JdbcDataContext(ds, tableTypes, catalogName);
    }

    @Override
    public UpdateableDataContext getUpdateableDataContext() {
        return dataContext;
    }

    @Override
    public UpdateableDataContext getDataContext() {
        return dataContext;
    }

    public String getProductName(){
        return dataContext.getDatabaseProductName();
    }

}
