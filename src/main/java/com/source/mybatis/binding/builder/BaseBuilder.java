package com.source.mybatis.binding.builder;

import com.source.mybatis.binding.session.Configuration;
import com.source.mybatis.binding.type.TypeAliasRegistry;

public abstract class BaseBuilder {

    protected Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        typeAliasRegistry = configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
