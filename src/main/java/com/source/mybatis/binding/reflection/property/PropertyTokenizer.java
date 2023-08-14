package com.source.mybatis.binding.reflection.property;

import java.util.Iterator;

public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    // 例子：班级[0].学生.成绩
    // 班级
    private String name;
    // 班级[0]
    private String indexedName;
    // 0
    private String index;
    // 学生.成绩
    private String children;

    public PropertyTokenizer(String fullName) {
        // 例子：班级[0].学生.成绩
        int delim = fullName.indexOf('.');
        if (delim > -1) {
            name = fullName.substring(0, delim);
            children = fullName.substring(delim + 1);
        } else {
            name = fullName;
            children = null;
        }
        indexedName = name;
        delim = name.indexOf('[');
        if (delim > -1) {
            index = name.substring(delim + 1, name.length() - 1);
            name = name.substring(0, delim);
        }
    }

    public String getName() {
        return name;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public String getIndex() {
        return index;
    }

    public String getChildren() {
        return children;
    }

    /**
     * 迭代器模式
     * @return
     */
    @Override
    public Iterator<PropertyTokenizer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }

    public static void main(String[] args) {
        PropertyTokenizer propertyTokenizer = new PropertyTokenizer("班级[0].学生.成绩");
        System.out.println();
    }
}
