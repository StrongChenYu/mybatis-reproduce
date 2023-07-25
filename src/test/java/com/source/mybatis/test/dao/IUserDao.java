package com.source.mybatis.test.dao;

import com.source.mybatis.test.po.User;

public interface IUserDao {

    User queryUserInfoById(int id);
}
