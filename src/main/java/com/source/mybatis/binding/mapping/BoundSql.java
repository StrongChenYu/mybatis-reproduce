package com.source.mybatis.binding.mapping;

import java.util.Map;

public class BoundSql {

    private String parameterType;
    private String resultType;
    private String sql;
    private Map<Integer, String> parameter;

    public BoundSql(String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.sql = sql;
        this.parameter = parameter;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public String getSql() {
        return sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }
}
