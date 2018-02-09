package com.zjcds.common.datastore.enums;

/**
 * created date：2017-08-05
 * @author niezhegang
 */
public enum DsType {

    H2("H2"),
    MySQL("MySQL"),
    MsSQL("Microsoft SQL Server"),
    Oracle("Oracle"),
    PostgreSQL("PostgreSQL"),
    DB2("DB2");

    private String productName;

    DsType(String productName) {
        this.productName = productName;
    }

    public static DsType getDsType(String productName){
        DsType matched = null;
        for(DsType dsType : values()){
            if(dsType.getProductName().equals(productName)){
                matched = dsType;
                break;
            }
        }
        if(matched == null)
            throw new IllegalArgumentException("未找到"+productName+"对应的数据源类型！");
        return matched;
    }

    public String getProductName() {
        return productName;
    }

}
