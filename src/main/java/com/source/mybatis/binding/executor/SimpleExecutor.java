package com.source.mybatis.binding.executor;

import com.source.mybatis.binding.executor.statement.StatementHandler;
import com.source.mybatis.binding.mapping.BoundSql;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.ResultHandler;
import com.source.mybatis.binding.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
