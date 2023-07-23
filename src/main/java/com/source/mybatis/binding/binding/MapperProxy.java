package com.source.mybatis.binding.binding;

import com.source.mybatis.binding.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 把mapper接口
 * 代理生成具体的实现
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;
    private Class<T> mapperClass;
    private Map<Method, MapperMethod> mapperMethodMap = new HashMap<>();
    private SqlSession sqlSession;

    public MapperProxy(Class<T> mapperClass, SqlSession sqlSession) {
        this.mapperClass = mapperClass;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperMethod mapperMethod = cachedMapperMethod(method);
        Object execute = mapperMethod.execute(sqlSession, args);
        System.out.println(execute);
        return execute;
    }

    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = mapperMethodMap.get(method);
        if (mapperMethod == null) {
            mapperMethod = new MapperMethod(sqlSession.getConfiguration(), mapperClass, method);
            mapperMethodMap.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
