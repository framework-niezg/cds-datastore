package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.MetaSqlExecutor;
import com.zjcds.common.datastore.create.Column;
import com.zjcds.common.datastore.create.Table;
import com.zjcds.common.datastore.dml.ColumnValue;
import com.zjcds.common.datastore.dml.ColumnValues;
import com.zjcds.common.datastore.exception.MetaSqlExecutorException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.metamodel.UpdateCallback;
import org.apache.metamodel.UpdateScript;
import org.apache.metamodel.create.ColumnCreationBuilder;
import org.apache.metamodel.create.TableCreationBuilder;
import org.apache.metamodel.delete.RowDeletionBuilder;
import org.apache.metamodel.insert.RowInsertionBuilder;
import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.query.FilterItem;
import org.apache.metamodel.update.RowUpdationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * created date：2018-02-11
  @author niezhegang
 */
public class MetaSqlExecutorImpl implements MetaSqlExecutor{
    private JdbcDataContext jdbcDataContext;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MetaSqlExecutorImpl(JdbcDataContext jdbcDataContext) {
        Assert.notNull(jdbcDataContext,"jdbcDataContext不能为空！");
        this.jdbcDataContext = jdbcDataContext;
    }

    @Override
    public void createTable(Table table) throws MetaSqlExecutorException {
        try {
            jdbcDataContext.executeUpdate(new UpdateScript() {
                @Override
                public void run(UpdateCallback callback) {
                    String schema = table.getSchema();
                    if (StringUtils.isBlank(schema)) {
                        schema = callback.getDataContext().getDefaultSchema().getName();
                    }
                    TableCreationBuilder tableBuilder = callback.createTable(schema, table.getName());
                    ColumnCreationBuilder columnCreationBuilder;
                    for (Column column : table.getColumns()) {
                        columnCreationBuilder = tableBuilder.withColumn(column.getName()).ofType(column.getType());
                        if (column.getSize() != null)
                            columnCreationBuilder.ofSize(column.getSize());
                        if (column.isPk())
                            columnCreationBuilder.asPrimaryKey();
                    }
                    logger.debug("创建表语句为：{}", tableBuilder.toSql());
                    tableBuilder.execute();
                }
            });
        }
        catch (Exception e){
            throw new MetaSqlExecutorException("表创建失败",e);
        }
    }

    @Override
    public void insert(String schema, String table, ColumnValues columnValues) throws MetaSqlExecutorException {
        try {
            //Assert.hasText(schema,"schema不能为空！");
            Assert.hasText(table,"表名不能为空!");
            Assert.isTrue(columnValues != null && CollectionUtils.isNotEmpty(columnValues.getColumnValues()),"插入列值不能为空！");
            jdbcDataContext.executeUpdate(new UpdateScript() {
                @Override
                public void run(UpdateCallback callback) {
                    RowInsertionBuilder rowInsertionBuilder = callback.insertInto(schema,table);
                    if(StringUtils.isBlank(schema))
                        rowInsertionBuilder = callback.insertInto(table);
                    else
                        rowInsertionBuilder = callback.insertInto(schema,table);
                    for(ColumnValue columnValue : columnValues.getColumnValues()){
                        rowInsertionBuilder.value(columnValue.getColumn(),columnValue.getValue());
                    }
                    logger.debug("插入语句为：{}",rowInsertionBuilder.toSql());
                    rowInsertionBuilder.execute();
                }
            });
        }
        catch (Exception e){
            throw new MetaSqlExecutorException("插入数据失败",e);
        }
    }

    @Override
    public void insert(String table, ColumnValues columnValues) throws MetaSqlExecutorException {
        String schema = jdbcDataContext.getDefaultSchemaName();
        insert(schema,table,columnValues);
    }

    @Override
    public void update(String schema, String table, ColumnValues columnValues, FilterItem... filterItems) throws MetaSqlExecutorException {
        try {
            Assert.hasText(table,"表名不能为空!");
            Assert.isTrue(columnValues != null && CollectionUtils.isNotEmpty(columnValues.getColumnValues()),"更新列值不能为空！");
            jdbcDataContext.executeUpdate(new UpdateScript() {
                @Override
                public void run(UpdateCallback callback) {
                    RowUpdationBuilder rowUpdationBuilder ;
                    if(StringUtils.isBlank(schema))
                        rowUpdationBuilder = callback.update(table);
                    else
                        rowUpdationBuilder = callback.update(schema,table);
                    for(ColumnValue columnValue : columnValues.getColumnValues()){
                        rowUpdationBuilder.value(columnValue.getColumn(),columnValue.getValue());
                    }
                    if(filterItems != null){
                        rowUpdationBuilder.where(filterItems);
                    }
                    logger.debug("更新语句为：{}",rowUpdationBuilder.toSql());
                    rowUpdationBuilder.execute();
                }
            });
        }
        catch (Exception e){
            throw new MetaSqlExecutorException("更新数据失败",e);
        }
    }

    @Override
    public void update(String table, ColumnValues columnValues, FilterItem... filterItems) throws MetaSqlExecutorException {
        String schema = jdbcDataContext.getDefaultSchemaName();
        update(schema,table,columnValues,filterItems);
    }

    @Override
    public void delete(String schema, String table, FilterItem... filterItems) throws MetaSqlExecutorException {
        try {
            Assert.hasText(table,"表名不能为空!");
            jdbcDataContext.executeUpdate(new UpdateScript() {
                @Override
                public void run(UpdateCallback callback) {
                    RowDeletionBuilder rowDeletionBuilder;
                    if(StringUtils.isBlank(schema))
                        rowDeletionBuilder = callback.deleteFrom(table);
                    else
                        rowDeletionBuilder = callback.deleteFrom(schema,table);
                    if(filterItems != null){
                        rowDeletionBuilder.where(filterItems);
                    }
                    logger.debug("删除语句为：{}",rowDeletionBuilder.toSql());
                    rowDeletionBuilder.execute();
                }
            });
        }
        catch (Exception e){
            throw new MetaSqlExecutorException("删除数据失败",e);
        }
    }

    @Override
    public void delete(String table, FilterItem... filterItems) throws MetaSqlExecutorException {
        String schema = jdbcDataContext.getDefaultSchemaName();
        delete(schema,table,filterItems);
    }
}
