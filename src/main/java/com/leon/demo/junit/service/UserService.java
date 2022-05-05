package com.leon.demo.junit.service;

import com.leon.demo.junit.model.User;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
public interface UserService {

    User findUserById(String id);
}
