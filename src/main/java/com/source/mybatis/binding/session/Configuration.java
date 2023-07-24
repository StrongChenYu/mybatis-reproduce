package com.source.mybatis.binding.session;

import com.source.mybatis.binding.binding.MapperRegistry;
import com.source.mybatis.binding.datasource.druid.DruidDataSourceFactory;
import com.source.mybatis.binding.mapping.Environment;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.transaction.jdbc.JdbcTransactionFactory;
import com.source.mybatis.binding.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;


public class Configuration {

    //1. mapper -> mapperProxyFactory 映射
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    //2. 配置文件中关于sql的id到 整个xml文件块的映射 mappedStatement存储着抽象后的sql块
    protected final Map<String, MappedStatement> statementMap = new HashMap<>();

    //3. 执行的环境,SQL连接的上下文
    protected Environment environment;

    //4. 别名 方便处理
    protected TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);

    }

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
