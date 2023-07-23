package com.source.mybatis.binding.session;

import com.source.mybatis.binding.binding.MapperRegistry;
import com.source.mybatis.binding.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;


public class Configuration {

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 配置文件中关于sql的id到 整个xml文件块的映射
    protected final Map<String, MappedStatement> statementMap = new HashMap<>();

    public <T> void addMapper(Class<T> mapper) {
        mapperRegistry.addMapper(mapper);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> T getMapper(Class<T> mapper, SqlSession sqlSession) {
        return mapperRegistry.getMapper(mapper, sqlSession);
    }

    public <T> boolean hasMapper(Class<T> mapper) {
        return mapperRegistry.hasMapper(mapper);
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        statementMap.put(mappedStatement.getId(), mappedStatement);
    }

    public MappedStatement getMapperStatement(String id) {
        return statementMap.get(id);
    }


}
