package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object params) {
        return (T) ("你被代理了！" + "方法：" + statementId + " 入参：" + params);
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
