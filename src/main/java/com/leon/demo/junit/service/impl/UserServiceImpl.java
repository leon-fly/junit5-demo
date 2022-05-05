package com.leon.demo.junit.service.impl;

import com.leon.demo.junit.adapter.UserAdapter;
import com.leon.demo.junit.dao.UserDao;
import com.leon.demo.junit.model.Type;
import com.leon.demo.junit.model.User;
import com.leon.demo.junit.service.ExceptionService;
import com.leon.demo.junit.service.UserService;
import com.leon.demo.junit.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserAdapter userAdapter;
    private UserDao userDao;
    private ExceptionService exceptionService;

    public User findUserById(String id) {
        log.info("request id:{}", id);
        User user = userDao.findUserById(id);
        user.setAge(DateUtils.calculateAge(user.getBirthday(), new Date()));
        if (Type.STUDENT == user.getType()) {
            user.setStudentExtraInfo(userAdapter.getStudentExtendInfo(id));
        } else if (Type.TEACHER == user.getType()) {
            user.setTeacherExtraInfo(userAdapter.getTeacherExtendInfo(id));
        } else {
            exceptionService.increaseCount();
            throw new RuntimeException("User Type is missing");
        }
        return user;
    }
}
