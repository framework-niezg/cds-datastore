package com.zjcds.common.datastore.impl;

import com.zjcds.common.datastore.NativeSqlExecutor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * created date：2017-08-06
 * @author niezhegang
 */
@Getter
@Setter
public class NativeSqlExecutorImpl implements NativeSqlExecutor {

    private DataSource dataSource;

    private JdbcDatastore jdbcDatastore;

    private QueryRunner queryRunner = null;

    public NativeSqlExecutorImpl(JdbcDatastore jdbcDatastore) {
        this.jdbcDatastore = jdbcDatastore;
        this.dataSource = jdbcDatastore.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    @Override
    public int update(String sql) throws SQLException {
        return queryRunner.update(sql);
    }

    @Override
    public int update(List<String> sqls) throws SQLException {
        Assert.notNull(sqls,"传入的sql脚本集不能为空！");
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            int count = 0;
            for(String sql : sqls){
                count += queryRunner.update(sql);
            }
            connection.commit();
            connection.setAutoCommit(autoCommit);
            return count;
        }
        catch (SQLException e){
            DbUtils.rollback(connection);
            throw e;
        }
        finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        return queryRunner.update(sql,params);
    }
}
