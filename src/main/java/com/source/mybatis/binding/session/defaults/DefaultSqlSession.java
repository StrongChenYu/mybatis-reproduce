package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.binding.MapperRegistry;
import com.source.mybatis.binding.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement, String params) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + params);
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
