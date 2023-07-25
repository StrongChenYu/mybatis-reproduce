package com.source.mybatis.binding.session.defaults;

import com.source.mybatis.binding.mapping.BoundSql;
import com.source.mybatis.binding.mapping.Environment;
import com.source.mybatis.binding.mapping.MappedStatement;
import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.session.SqlSession;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object params) {
        try {
            MappedStatement mapperStatement = configuration.getMapperStatement(statementId);
            Environment environment = configuration.getEnvironment();
            DataSource dataSource = environment.getDataSource();
            Connection connection = dataSource.getConnection();

            BoundSql boundSql = mapperStatement.getBoundSql();
            String sql = boundSql.getSql();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            long param = Long.parseLong(((Object[]) params)[0].toString());
            preparedStatement.setLong(1, param);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));

            if (objList.size() > 0) {
                return objList.get(0);
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    System.out.println(setMethod);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Timestamp.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
