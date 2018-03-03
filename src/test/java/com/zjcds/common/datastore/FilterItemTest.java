package com.zjcds.common.datastore;

import org.apache.metamodel.query.FilterItem;
import org.apache.metamodel.query.OperatorType;
import org.apache.metamodel.query.SelectItem;
import org.apache.metamodel.schema.MutableColumn;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * created date：2018-03-01
 *
 * @author niezhegang
 */
public class FilterItemTest {

    @Test
    public void name1() {
        ArrayList<FilterItem> filterItems = new ArrayList<>();
        FilterItem filterItem1 = new FilterItem(new SelectItem(new MutableColumn("foo")), OperatorType.EQUALS_TO,"tt");
        FilterItem filterItem2 = new FilterItem(new SelectItem(new MutableColumn("foo")), OperatorType.EQUALS_TO,"cc");
        filterItems.add(filterItem1);
        filterItems.add(filterItem2);
        FilterItem filterItem = new FilterItem(filterItems);
        Assert.assertEquals("(foo = 'tt' OR foo = 'cc')",filterItem.toSql());
    }
}
