package com.source.mybatis.test;

import com.source.mybatis.binding.binding.MapperRegistry;
import com.source.mybatis.binding.session.SqlSession;
import com.source.mybatis.binding.session.SqlSessionFactory;
import com.source.mybatis.binding.session.defaults.DefaultSqlSessionFactory;
import com.source.mybatis.test.dao.IUserDao;
import org.junit.Test;

public class TestMain {

    @Test
    public void test() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.source.mybatis.test.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(iUserDao.queryNameById(123));
    }
}
