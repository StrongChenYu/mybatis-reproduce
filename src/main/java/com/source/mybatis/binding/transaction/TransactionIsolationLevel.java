package com.source.mybatis.binding.transaction;

import java.sql.Connection;

/**
 * 事务的隔离级别
 */
public enum TransactionIsolationLevel {

    NONE(Connection.TRANSACTION_NONE),
    READ_COMMIT(Connection.TRANSACTION_READ_COMMITTED),
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int isolationLevel;
    TransactionIsolationLevel(int isolationLevel) {
        this.isolationLevel = isolationLevel;
    }

    public int getLevel() {
        return isolationLevel;
    }
}
