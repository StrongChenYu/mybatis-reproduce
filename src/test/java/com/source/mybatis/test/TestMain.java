package com.source.mybatis.test;

import com.source.mybatis.binding.datasource.unpooled.UnPooledDataSource;
import com.source.mybatis.binding.io.Resources;
import com.source.mybatis.binding.session.SqlSession;
import com.source.mybatis.binding.session.SqlSessionFactory;
import com.source.mybatis.binding.session.SqlSessionFactoryBuilder;
import com.source.mybatis.test.dao.IUserDao;
import com.source.mybatis.test.po.User;
import org.junit.Test;
import sun.reflect.Reflection;

import java.io.IOException;
import java.io.Reader;

public class TestMain {


    @Test
    public void test3() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = userDao.queryUserInfoById(1);
        System.out.println(user);
    }


    @Test
    public void test2() {
//        MapperRegistry mapperRegistry = new MapperRegistry();
//        mapperRegistry.addMappers("com.source.mybatis.test.dao");
//
//        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
//        System.out.println(iUserDao.queryNameById(123));
    }

    public static void main(String[] args) {
        UnPooledDataSource pooledDataSource = new UnPooledDataSource();
        System.out.println(Reflection.getCallerClass(1));
    }
}
