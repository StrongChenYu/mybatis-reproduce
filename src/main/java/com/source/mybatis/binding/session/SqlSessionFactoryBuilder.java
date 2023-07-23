package com.source.mybatis.binding.session;

import com.source.mybatis.binding.builder.xml.XMLConfigurationBuilder;
import com.source.mybatis.binding.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory builder(Reader reader) {
        Configuration configuration = new XMLConfigurationBuilder(reader).parse();
        return new DefaultSqlSessionFactory(configuration);
    }

    public SqlSessionFactory builder(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
