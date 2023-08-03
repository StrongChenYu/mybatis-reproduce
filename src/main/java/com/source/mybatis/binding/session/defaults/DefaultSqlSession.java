package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.executor.Executor;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statementId, Object params) {
        MappedStatement mapperStatement = configuration.getMapperStatement(statementId);
        List<T> result = executor.query(mapperStatement, params, Executor.NO_RESULT_HANDLER, mapperStatement.getBoundSql());
        return result.size() == 0 ? null : result.get(0);
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
