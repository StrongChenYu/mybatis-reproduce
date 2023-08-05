package com.source.mybatis.binding.reflection;

import com.source.mybatis.binding.reflection.invoker.Invoker;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Reflector {

    private static boolean classCacheEnabled = true;

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static final Map<Class<?>, Reflector> REFLECTOR_MAP = new ConcurrentHashMap<>();
    private Class<?> type;

    // get 属性列表
    private String[] readablePropertyNames = EMPTY_STRING_ARRAY;
    // set 属性列表
    private String[] writeablePropertyNames = EMPTY_STRING_ARRAY;

    private Map<String, Invoker> setMethods = new HashMap<>();
    private Map<String, Invoker> getMethods = new HashMap<>();
    private Map<String, Class<?>> setTypes = new HashMap<>();
    private Map<String, Class<?>> getTypes = new HashMap<>();
    private Constructor<?> defaultConstructor;
    private Map<String, String> caseInsensitivePropertyMap = new HashMap<>();

}
