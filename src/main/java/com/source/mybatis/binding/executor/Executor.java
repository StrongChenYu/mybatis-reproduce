package com.source.mybatis.binding.executor;

import com.source.mybatis.binding.mapping.BoundSql;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.ResultHandler;
import com.source.mybatis.binding.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
