package com.source.mybatis.binding.binding;

import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.mapping.SqlCommandType;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;

import java.lang.reflect.Method;

public class MapperMethod {

    private SqlCommand sqlCommand;

    public MapperMethod(Configuration configuration, Class<?> mapper, Method method) {
        this.sqlCommand = new SqlCommand(configuration, mapper, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (sqlCommand.type) {
            case SELECT:
                result = sqlSession.selectOne(sqlCommand.getName(), args);
                break;
            case DELETE:
                break;
            case INSERT:
                break;
            case UPDATE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + sqlCommand.getName());
        }
        return result;
    }

    public static class SqlCommand {
        private String name;
        private SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapper, Method method) {
            String statementId = mapper.getName() + "." + method.getName();
            MappedStatement mapperStatement = configuration.getMapperStatement(statementId);
            name = mapperStatement.getId();
            type = mapperStatement.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
