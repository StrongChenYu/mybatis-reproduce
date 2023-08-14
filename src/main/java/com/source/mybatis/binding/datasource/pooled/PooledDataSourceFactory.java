package com.source.mybatis.binding.datasource.pooled;

import com.source.mybatis.binding.datasource.unpooled.UnPooledDataSourceFactory;

public class PooledDataSourceFactory extends UnPooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
