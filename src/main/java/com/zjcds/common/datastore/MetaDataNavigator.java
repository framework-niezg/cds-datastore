package com.zjcds.common.datastore;

import com.zjcds.common.datastore.enums.DsType;
import org.apache.metamodel.schema.Table;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public interface MetaDataNavigator {

    String getProductName();

    DsType getDsType();

    Table getTable(String tableName);

    Table getTable(String schemaName, String tableName);
}
