package com.leon.demo.junit.dao.impl;

import com.leon.demo.junit.dao.UserDao;
import com.leon.demo.junit.model.Type;
import com.leon.demo.junit.model.User;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
@Repository
public class UserDaoImpl implements UserDao {
    public User findUserById(String id){
        // Let's suppose that this is a database query operation
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return User.builder()
                .id(id)
                .name("King")
                .type(Type.TEACHER)
                .birthday(birthday)
                .build();
    }
}
