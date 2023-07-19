package com.source.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 把mapper接口
 * 代理生成具体的实现
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;
    private Class<T> mapperClass;
    private Map<String, String> sqlSession;

    public MapperProxy(Class<T> mapperClass, Map<String, String> sqlSession) {
        this.mapperClass = mapperClass;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method has been proxy");
        return null;
    }
}
