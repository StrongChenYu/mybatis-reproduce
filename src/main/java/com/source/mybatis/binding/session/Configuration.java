package com.source.mybatis.binding.session;

import com.source.mybatis.binding.binding.MapperRegistry;
import com.source.mybatis.binding.datasource.druid.DruidDataSourceFactory;
import com.source.mybatis.binding.datasource.pooled.PooledDataSourceFactory;
import com.source.mybatis.binding.datasource.unpooled.UnPooledDataSourceFactory;
import com.source.mybatis.binding.executor.Executor;
import com.source.mybatis.binding.executor.SimpleExecutor;
import com.source.mybatis.binding.executor.resultset.DefaultResultSetHandler;
import com.source.mybatis.binding.executor.resultset.ResultSetHandler;
import com.source.mybatis.binding.executor.statement.PreparedStatementHandler;
import com.source.mybatis.binding.executor.statement.StatementHandler;
import com.source.mybatis.binding.mapping.BoundSql;
import com.source.mybatis.binding.mapping.Environment;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.transaction.Transaction;
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
        typeAliasRegistry.registerAlias("UN_POOLED", UnPooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }
}
