package com.source.mybatis.binding.builder;

import com.source.mybatis.binding.session.Configuration;

public abstract class BaseBuilder {

    protected Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
