package com.zjcds.common.datastore;

import org.apache.metamodel.UpdateableDataContext;

public interface UpdateableDatastoreConnection extends DatastoreConnection {

    UpdateableDataContext getUpdateableDataContext();

}
