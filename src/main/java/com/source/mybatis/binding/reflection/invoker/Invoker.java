package com.source.mybatis.binding.reflection.invoker;

public interface Invoker {

    Object invoke(Object object, Object[] params) throws Exception;

    Class<?> getType();
}
