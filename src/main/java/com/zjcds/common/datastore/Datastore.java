package com.zjcds.common.datastore;

import org.apache.metamodel.DataContext;

import java.io.Serializable;

public interface Datastore extends Serializable {

    String getName();

    String getDescription();

    DataContext getDataContext();


}
