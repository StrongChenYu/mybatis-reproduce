package com.source.mybatis.binding.session;

public interface SqlSession {

    <T> T selectOne(String statementId, Object params);

    <T> T selectOne(String statementId);

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
