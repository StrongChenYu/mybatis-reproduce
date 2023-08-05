package com.source.mybatis.binding.reflection.invoker;

import java.lang.reflect.Method;

public class MethodInvoker implements Invoker {

    private Method method;
    private Class<?> type;

    public MethodInvoker(Method method) {
        this.method = method;

        if (method.getParameters().length == 0) {
            type = method.getReturnType();
        } else {
            type = method.getParameters()[0].getType();
        }
    }

    @Override
    public Object invoke(Object object, Object[] params) throws Exception {
        return method.invoke(object, params);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
