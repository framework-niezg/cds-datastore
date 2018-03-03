package com.zjcds.common.datastore.create;

import lombok.Getter;
import org.apache.metamodel.schema.ColumnType;
import org.springframework.util.Assert;

/**
 * created date：2018-02-11
 * @author niezhegang
 */
@Getter
public class Column {

    private ColumnType type;
    private String name;
    private Integer size;
    private boolean pk;

    private void setType(ColumnType type) {
        this.type = type;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSize(Integer size) {
        this.size = size;
    }

    private void setPk(boolean pk) {
        this.pk = pk;
    }

    public static ColumnBuilder newBuilder(){
        return new ColumnBuilder();
    }

    public static class ColumnBuilder {

        private ColumnType type;
        private String name;
        private Integer size;
        private boolean pk = false;

        private ColumnBuilder() {
        }

        public ColumnBuilder type(ColumnType type){
            this.type = type;
            return this;
        }

        public ColumnBuilder name(String name){
            this.name = name;
            return this;
        }

        public ColumnBuilder size(Integer size){
            this.size = size;
            return this;
        }

        public ColumnBuilder primaryKey(boolean pk) {
            this.pk = pk;
            return this;
        }

        public Column build(){
            Column column = new Column();
            Assert.notNull(name,"字段名不能为空！");
            column.setName(name);
            Assert.notNull(type,"字段类型不能为空！");
            column.setType(type);
            column.setPk(pk);
            if(size != null)
                column.setSize(size);
            return column;
        }

    }

}
