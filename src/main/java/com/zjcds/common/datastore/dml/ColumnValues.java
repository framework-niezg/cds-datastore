package com.zjcds.common.datastore.dml;

import java.util.ArrayList;
import java.util.List;

/**
 * created date：2018-02-28
 * @author niezhegang
 */
public class ColumnValues {
    private List<ColumnValue> columnValues = new ArrayList<>();

    private ColumnValues() {
    }

    public List<ColumnValue> getColumnValues() {
        return columnValues;
    }

    public ColumnValues addColumnValue(ColumnValue columnValue){
        columnValues.add(columnValue);
        return this;
    }

    public static ColumnValues newColumnValues(){
        return new ColumnValues();
    }
}
