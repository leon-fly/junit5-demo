package com.leon.demo.junit.dao;

import com.leon.demo.junit.model.User;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
public interface UserDao {
     User findUserById(String id);
}
