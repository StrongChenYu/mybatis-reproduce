package com.source.mybatis.binding.binding;

import com.source.mybatis.binding.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 把mapper接口
 * 代理生成具体的实现
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;
    private Class<T> mapperClass;
    private SqlSession sqlSession;

    public MapperProxy(Class<T> mapperClass, SqlSession sqlSession) {
        this.mapperClass = mapperClass;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return sqlSession.selectOne("queryByName", "123");
    }
}
