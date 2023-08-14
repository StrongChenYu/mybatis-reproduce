package com.source.mybatis.binding.reflection.factory;

import com.source.mybatis.binding.reflection.MetaObject;
import com.source.mybatis.binding.reflection.wrapper.ObjectWrapper;

public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
