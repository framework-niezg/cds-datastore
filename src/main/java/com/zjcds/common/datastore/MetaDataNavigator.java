package com.zjcds.common.datastore;

import org.apache.metamodel.schema.Table;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public interface MetaDataNavigator {

    Table getTable(String tableName);

    Table getTable(String schemaName, String tableName);
}
