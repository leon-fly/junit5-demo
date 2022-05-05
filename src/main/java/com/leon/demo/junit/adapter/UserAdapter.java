package com.leon.demo.junit.adapter;

import com.leon.demo.junit.model.User;
import org.springframework.stereotype.Component;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
@Component
public class UserAdapter {
    public User.StudentExtraInfo getStudentExtendInfo(String id){
        // let's suppose that we'll call a third api
        return User.StudentExtraInfo.builder().grade("Adapter-Gread-3").build();
    }

    public User.TeacherExtraInfo getTeacherExtendInfo(String id){
        // let's suppose that we'll call a third api
        return User.TeacherExtraInfo.builder().subject("Adapter-Subject-Chinese").build();
    }
}
