package com.source.mybatis.test;

import com.source.mybatis.binding.datasource.pooled.PooledDataSource;
import com.source.mybatis.binding.datasource.pooled.PooledDataSourceFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class TestDataSource {


    @Test
    public void testPooledDataSourcePushAndPop() throws SQLException, InterruptedException {
        PooledDataSourceFactory dataSourceFactory = new PooledDataSourceFactory();

        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp&useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "chenyu");

        dataSourceFactory.setProperties(properties);

        PooledDataSource dataSource = (PooledDataSource) dataSourceFactory.getDataSource();
        int nThread = 100;
        CountDownLatch countDownLatch = new CountDownLatch(nThread);
        Thread[] threads = new Thread[nThread];
        for (int i = 0; i < nThread; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Connection connection = dataSource.getConnection();
                    Thread.sleep(1000);
                    connection.close();
                    countDownLatch.countDown();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (int i = 0; i < nThread; i++) {
            threads[i].start();
        }

        countDownLatch.await();

        System.out.println("execute end");
    }
}
