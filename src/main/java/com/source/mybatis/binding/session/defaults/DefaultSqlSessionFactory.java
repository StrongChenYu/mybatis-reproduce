package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;
import com.source.mybatis.binding.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
