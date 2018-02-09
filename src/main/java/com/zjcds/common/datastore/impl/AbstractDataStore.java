package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.Datastore;
import lombok.Getter;
import lombok.Setter;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
@Setter
@Getter
public abstract class AbstractDataStore implements Datastore {

    private String description;

    private String name;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
