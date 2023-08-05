package com.source.mybatis.binding.reflection.invoker;

import java.lang.reflect.Field;

/**
 * 对field设置属性
 */
public class GetInvoker implements Invoker {

    private Field field;

    public GetInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object object, Object[] params) throws Exception {
        return field.get(object);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    public static void main(String[] args) throws NoSuchFieldException {
        String a = "1231";
        Field value = String.class.getDeclaredField("hash");
        System.out.println(value.getType());
    }
}
