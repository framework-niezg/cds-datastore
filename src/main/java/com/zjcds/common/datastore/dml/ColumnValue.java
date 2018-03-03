package com.zjcds.common.datastore.dml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

/**
 * created date：2018-02-28
 * @author niezhegang
 */
@Getter
@Setter
public class ColumnValue {
    private String column;
    private Object value;

    public ColumnValue(String column, Object value) {
        Assert.hasText(column,"字段名不能为空！");
        this.column = column;
        this.value = value;
    }
}
