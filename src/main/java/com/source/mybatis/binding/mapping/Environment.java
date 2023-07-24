package com.source.mybatis.binding.mapping;

import com.source.mybatis.binding.transaction.TransactionFactory;

import javax.sql.DataSource;

public class Environment {

    // Environment id
    private String id;
    // TransactionFactory
    private TransactionFactory transactionFactory;
    // DataSource
    private DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public static class Builder {

        // Environment id
        private String id;
        // TransactionFactory
        private TransactionFactory transactionFactory;
        // DataSource
        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(id, transactionFactory, dataSource);
        }
    }

    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
