package com.source.mybatis.binding.session;

public interface SqlSession {

    <T> T selectOne(String statementId, String params);

    <T> T selectOne(String statementId);

    <T> T getMapper(Class<T> type);
}
