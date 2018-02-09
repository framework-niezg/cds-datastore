package com.zjcds.common.datastore;


import org.apache.metamodel.UpdateableDataContext;

public interface UpdateableDatastore extends Datastore {


    UpdateableDataContext getUpdateableDataContext();

}
