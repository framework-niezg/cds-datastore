package com.zjcds.common.datastore;

import com.zjcds.common.datastore.create.Table;
import com.zjcds.common.datastore.dml.ColumnValues;
import com.zjcds.common.datastore.exception.MetaSqlExecutorException;
import org.apache.metamodel.query.FilterItem;

/**
 * created date：2018-02-11
 * @author niezhegang
 */
public interface MetaSqlExecutor {

    public void createTable(Table table) throws MetaSqlExecutorException;

    public void insert(String schema, String table, ColumnValues columnValues) throws MetaSqlExecutorException;

    public void insert(String table, ColumnValues columnValues) throws MetaSqlExecutorException;

    public void update(String schema,String table, ColumnValues columnValues, FilterItem ... filterItems) throws MetaSqlExecutorException;

    public void update(String table, ColumnValues columnValues, FilterItem ... filterItems) throws MetaSqlExecutorException;

    public void delete(String schema,String table, FilterItem ... filterItems) throws MetaSqlExecutorException;

    public void delete(String table, FilterItem ... filterItems) throws MetaSqlExecutorException;



}
