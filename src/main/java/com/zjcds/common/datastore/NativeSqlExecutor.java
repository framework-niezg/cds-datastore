package com.zjcds.common.datastore;


import java.sql.SQLException;
import java.util.List;

/**
 * created date：2017-08-06
 *
 * @author niezhegang
 */
public interface NativeSqlExecutor {

    public int update(String sql) throws SQLException;

    public int update(List<String> sqls) throws SQLException;

    public int update(String sql, Object... params) throws SQLException;



}
