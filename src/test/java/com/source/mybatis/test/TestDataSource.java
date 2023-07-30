package com.source.mybatis.test;

import com.source.mybatis.binding.datasource.pooled.PooledConnection;
import com.source.mybatis.binding.datasource.pooled.PooledDataSource;
import com.source.mybatis.binding.datasource.pooled.PooledDataSourceFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Properties;

public class TestDataSource {


    @Test
    public void testPooledDataSourcePushAndPop() throws SQLException {
        PooledDataSourceFactory dataSourceFactory = new PooledDataSourceFactory();

        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp&useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "chenyu");

        dataSourceFactory.setProperties(properties);

        PooledDataSource dataSource = (PooledDataSource) dataSourceFactory.getDataSource();
        for (int i = 0; i < 20; i++) {
            PooledConnection pooledConnection = dataSource.popConnection();
            dataSource.pushConnection(pooledConnection);
        }

    }
}
