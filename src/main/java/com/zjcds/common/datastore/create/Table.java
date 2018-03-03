package com.zjcds.common.datastore.create;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * created date：2018-02-11
 * @author niezhegang
 */
public class Table {
    private String schema;
    private String name;
    private List<Column> columns = new ArrayList<>();

    private Table() {

    }

    public String getSchema() {
        return schema;
    }

    private void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        if(columns == null)
            columns = new ArrayList<>();
        return columns;
    }

    public static TableBuilder newBuilder(){
        return new TableBuilder();
    }

    private void addColumn(Column column) {
        columns.add(column);
    }

    public static class TableBuilder {

        private String schema;

        private String name;

        private List<Column.ColumnBuilder> columnBuilders = new ArrayList<>();

        private TableBuilder() {
        }

        public TableBuilder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public TableBuilder tableName(String tableName) {
            this.name = tableName;
            return this;
        }

        public TableBuilder addColumn(Column.ColumnBuilder columnBuilder){
            columnBuilders.add(columnBuilder);
            return this;
        }

        public Table build() {
            Assert.hasText(name,"表名不能为空！");
            Table metaTable = new Table();
            metaTable.setSchema(schema);
            metaTable.setName(name);
            Assert.isTrue(!CollectionUtils.isEmpty(columnBuilders),"表字段定义不能为空！");
            for(Column.ColumnBuilder columnBuilder : columnBuilders){
                metaTable.addColumn(columnBuilder.build());
            }
            return metaTable;
        }
    }
}
