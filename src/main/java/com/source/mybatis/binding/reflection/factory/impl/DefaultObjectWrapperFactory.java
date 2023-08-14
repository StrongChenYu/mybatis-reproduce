package com.source.mybatis.binding.reflection.factory.impl;

import com.source.mybatis.binding.reflection.MetaObject;
import com.source.mybatis.binding.reflection.factory.ObjectWrapperFactory;
import com.source.mybatis.binding.reflection.wrapper.ObjectWrapper;

public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }

}
