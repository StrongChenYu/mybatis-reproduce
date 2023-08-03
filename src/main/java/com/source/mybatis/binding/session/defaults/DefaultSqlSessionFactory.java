package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.executor.Executor;
import com.source.mybatis.binding.mapping.Environment;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;
import com.source.mybatis.binding.session.SqlSessionFactory;
import com.source.mybatis.binding.transaction.Transaction;
import com.source.mybatis.binding.transaction.TransactionFactory;
import com.source.mybatis.binding.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction transaction = null;
        try {
            Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            DataSource dataSource = environment.getDataSource();
            transaction = transactionFactory.newTransaction(dataSource, TransactionIsolationLevel.READ_COMMIT, true);
            Executor executor = configuration.newExecutor(transaction);
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert transaction != null;
                transaction.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
