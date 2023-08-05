package com.source.mybatis.binding.reflection.invoker;

import java.lang.reflect.Field;

public class SetInvoker implements Invoker {

    private Field field;

    @Override
    public Object invoke(Object object, Object[] params) throws Exception {
        field.set(object, params[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
