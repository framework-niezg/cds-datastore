package com.zjcds.common.datastore;


public interface UpdateableDatastore<T extends UpdateableDatastoreConnection> extends Datastore<T> {


    T getUpdateableDatastoreConnection() ;

}
