package com.source.mybatis.test;

import com.source.mybatis.binding.reflection.Reflector;
import com.source.mybatis.test.po.User;
import org.junit.Test;

public class TestReflector {

    @Test
    public void testConstructor() {
        Reflector reflector = new Reflector(User.class);
    }
}
