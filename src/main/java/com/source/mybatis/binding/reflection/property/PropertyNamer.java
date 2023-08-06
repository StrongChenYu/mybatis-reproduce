package com.source.mybatis.binding.reflection.property;

import java.util.Locale;

public class PropertyNamer {

    private PropertyNamer() {
    }

    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("set") || name.startsWith("get")) {
            name = name.substring(3);
        }
        // 其实就是去掉get或者set后，把首字母小写了
        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }

        return name;
    }

    public static boolean isProperty(String name) {
        return name.startsWith("get") || name.startsWith("set") || name.startsWith("is");
    }

    /**
     * 是否set
     * @param name
     * @return
     */
    public static boolean isSetter(String name) {
        return name.startsWith("get") || name.startsWith("is");
    }

    /**
     * 是否为get
     * @param name
     * @return
     */
    public static boolean isGetter(String name) {
        return name.startsWith("set");
    }
}
