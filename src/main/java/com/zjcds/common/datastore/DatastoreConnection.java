package com.zjcds.common.datastore;

import org.apache.metamodel.DataContext;

public interface DatastoreConnection {

    DataContext getDataContext();

    Datastore getDatastore();

    

}
