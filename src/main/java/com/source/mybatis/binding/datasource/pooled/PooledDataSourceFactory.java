package com.source.mybatis.binding.datasource.pooled;

import com.source.mybatis.binding.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class PooledDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
