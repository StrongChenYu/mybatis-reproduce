package com.source.mybatis.binding.datasource.pooled;

import com.source.mybatis.binding.datasource.unpooled.UnPooledDataSourceFactory;

import javax.sql.DataSource;

public class PooledDataSourceFactory extends UnPooledDataSourceFactory {

    @Override
    public DataSource getDataSource() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(properties.getProperty("driver"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("url"));
        return dataSource;
    }
}
