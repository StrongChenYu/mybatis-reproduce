package com.source.mybatis.binding.transaction.jdbc;

import com.source.mybatis.binding.transaction.Transaction;
import com.source.mybatis.binding.transaction.TransactionFactory;
import com.source.mybatis.binding.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel isolationLevel, boolean autoCommit) {
        return new JdbcTransaction(dataSource, isolationLevel, autoCommit);
    }
}
