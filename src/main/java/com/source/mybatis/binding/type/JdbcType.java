package com.source.mybatis.binding.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP);

    private final int TYPE_CODE;
    private static Map<Integer, JdbcType> typeMap = new HashMap<>();

    static {
        for (JdbcType type : JdbcType.values()) {
            typeMap.put(type.TYPE_CODE, type);
        }
    }

    JdbcType(int type) {
        this.TYPE_CODE = type;
    }

    public static JdbcType forCode(int code)  {
        return typeMap.get(code);
    }

}
