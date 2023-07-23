package com.source.mybatis.binding.binding;

import cn.hutool.core.lang.ClassScanner;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> mapperProxyMap = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> mapper, SqlSession sqlSession) {
        if (!mapper.isInterface()) {
            throw new RuntimeException(mapper + " is not the interface type");
        }

        if (! hasMapper(mapper)) {
            throw new RuntimeException("Don't have proxy object corresponding to " + mapper);
        }

        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) mapperProxyMap.get(mapper);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> mapper) {
        if (!mapper.isInterface()) {
            return;
        }

        MapperProxyFactory<?> mapperProxyFactory = new MapperProxyFactory<>(mapper);
        mapperProxyMap.put(mapper, mapperProxyFactory);
    }

    public <T> boolean hasMapper(Class<T> mapper) {
        return mapperProxyMap.containsKey(mapper);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
