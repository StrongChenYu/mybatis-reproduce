package com.source.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.source.mybatis.binding.reflection.MetaClass;
import com.source.mybatis.binding.reflection.MetaObject;
import com.source.mybatis.binding.reflection.Reflector;
import com.source.mybatis.binding.reflection.SystemMetaObject;
import com.source.mybatis.test.po.Teacher;
import com.source.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class TestReflector {

    private Logger logger = LoggerFactory.getLogger(TestReflector.class);

    @Test
    public void testConstructor() {
        Reflector reflector = new Reflector(User.class);
    }

    @Test
    public void testMetaClass_FindProperty() {
        MetaClass metaClass = MetaClass.forClass(User.class);
        System.out.println(metaClass.findProperty("user1.id1"));
        System.out.println(metaClass.findProperty("user1.id"));
        // 测试是否拥有默认的构造类
        System.out.println(metaClass.hasDefaultConstructor());
        // 获取属性对应的get和set 对应的Invoker
        System.out.println(metaClass.getGetInvoker("userId"));
        System.out.println(metaClass.getSetInvoker("userId"));

        System.out.println(metaClass.hasGetter("user1.id"));
        System.out.println(metaClass.hasSetter("user1.id1"));
    }

    @Test
    public void test_reflection() {
        Teacher teacher = new Teacher();
        List<Teacher.Student> list = new ArrayList<>();
        list.add(new Teacher.Student());
        teacher.setName("小傅哥");
        teacher.setStudents(list);

        MetaObject metaObject = SystemMetaObject.forObject(teacher);

        logger.info("getGetterNames：{}", JSON.toJSONString(metaObject.getGetterNames()));
        logger.info("getSetterNames：{}", JSON.toJSONString(metaObject.getSetterNames()));
        logger.info("name的get方法返回值：{}", JSON.toJSONString(metaObject.getGetterType("name")));
        logger.info("students的set方法参数值：{}", JSON.toJSONString(metaObject.getGetterType("students")));
        logger.info("name的hasGetter：{}", metaObject.hasGetter("name"));
        logger.info("student.id（属性为对象）的hasGetter：{}", metaObject.hasGetter("student.id"));
        logger.info("获取name的属性值：{}", metaObject.getValue("name"));
        // 重新设置属性值
        metaObject.setValue("name", "小白");
        logger.info("设置name的属性值：{}", metaObject.getValue("name"));
        // 设置属性（集合）的元素值
        metaObject.setValue("students[0].id", "001");
        logger.info("获取students集合的第一个元素的属性值：{}", JSON.toJSONString(metaObject.getValue("students[0].id")));
        logger.info("对象的序列化：{}", JSON.toJSONString(teacher));
    }

}
