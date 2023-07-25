package com.source.mybatis.binding.mapping;

import com.source.mybatis.binding.session.Configuration;

public class MappedStatement {

    // 配置项 相互依赖
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;

    // 只能通过建造者模式构造
    private MappedStatement() {}

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql bdSql) {
            //  String parameterType, String resultType, String sql, Map<Integer, String> parameter
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = bdSql;
        }

        // 建造者模式
        public MappedStatement build() {
            return mappedStatement;
        }
    }


    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }
}
