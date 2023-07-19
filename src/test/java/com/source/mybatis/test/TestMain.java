package com.source.mybatis.test;

import com.source.mybatis.binding.MapperProxyFactory;
import com.source.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMain {

    @Test
    public void test() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();


        IUserDao iUserDao = factory.newInstance(sqlSession);
        iUserDao.queryNameById(1);
    }
}
