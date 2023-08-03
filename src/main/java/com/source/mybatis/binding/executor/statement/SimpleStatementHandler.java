package com.source.mybatis.binding.executor.statement;

import com.source.mybatis.binding.executor.Executor;
import com.source.mybatis.binding.mapping.BoundSql;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleStatementHandler extends BaseStatementHandler {

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        // do nothing
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.executeQuery(sql);
        return resultSetHandler.<E>handleResultSets(statement);
    }
}
