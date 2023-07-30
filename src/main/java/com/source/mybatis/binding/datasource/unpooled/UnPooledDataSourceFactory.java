package com.source.mybatis.binding.datasource.unpooled;

import com.source.mybatis.binding.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class UnPooledDataSourceFactory implements DataSourceFactory {

    protected Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        UnPooledDataSource dataSource = new UnPooledDataSource();
        dataSource.setDriver(properties.getProperty("driver"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("url"));
        return dataSource;
    }
}
