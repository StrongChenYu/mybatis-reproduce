package com.source.mybatis.binding.binding;

import cn.hutool.core.lang.ClassScanner;
import com.source.mybatis.binding.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> mapperProxyMap = new HashMap<>();

    public <T> T getMapper(Class<T> mapper, SqlSession sqlSession) {
        if (!mapper.isInterface()) {
            throw new RuntimeException(mapper + " is not the interface type");
        }

        if (! mapperProxyMap.containsKey(mapper)) {
            throw new RuntimeException("Don't have proxy object corresponding to " + mapper);
        }

        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) mapperProxyMap.get(mapper);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public void addMapper(Class<?> mapper) {
        if (!mapper.isInterface()) {
            return;
        }

        MapperProxyFactory<?> mapperProxyFactory = new MapperProxyFactory<>(mapper);
        mapperProxyMap.put(mapper, mapperProxyFactory);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
