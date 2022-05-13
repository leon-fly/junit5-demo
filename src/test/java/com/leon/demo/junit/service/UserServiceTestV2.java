package com.leon.demo.junit.service;

import com.leon.demo.junit.adapter.UserAdapter;
import com.leon.demo.junit.dao.UserDao;
import com.leon.demo.junit.model.Type;
import com.leon.demo.junit.model.User;
import com.leon.demo.junit.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * @Author : LeonWang
 * @Descpriction This is a common mockito unit tester
 * Junit 5
 * @Date:created 2022/5/9
 */
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTestV2 {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserAdapter userAdapter;

    @Mock
    private ExceptionService exceptionService;

    @BeforeEach
    public void setup() {
        // you can set up a common mock for all test method.
        when(userAdapter.getStudentExtendInfo(anyString())).thenReturn(User.StudentExtraInfo.builder().grade("Mockito-Grade-3").build());
        when(userAdapter.getTeacherExtendInfo(anyString())).thenReturn(User.TeacherExtraInfo.builder().subject("Mockito-Subject-Chinese").build());
    }

    @Test
    public void testFindUserById_when_userIsStudent_then_returnUserWithStudentExtendInfo() {
        when(userAdapter.getStudentExtendInfo(anyString())).thenReturn(User.StudentExtraInfo.builder().grade("Mockito-Grade-3").build());
        when(userAdapter.getTeacherExtendInfo(anyString())).thenReturn(User.TeacherExtraInfo.builder().subject("Mockito-Subject-Chinese").build());

        //given
        String userId = "Student";
        // you can set up a specific mock for all test method.
        when(userDao.findUserById("Student")).thenReturn(buildUserBaseInfo(Type.STUDENT, "Student"));

        // when
        User student = userService.findUserById("Student");

        // then
        Assert.assertNotNull(student.getStudentExtraInfo());
        Assert.assertNull(student.getTeacherExtraInfo());
    }

    @Test
    public void testFindUserById_when_userIsTeacher_then_returnUserWithTeacherExtendInfo() {
        when(userDao.findUserById("Teacher")).thenReturn(buildUserBaseInfo(Type.TEACHER, "Teacher"));
        User teacher = userService.findUserById("Teacher");
        Assert.assertNotNull(teacher.getTeacherExtraInfo());
        Assert.assertNull(teacher.getStudentExtraInfo());
    }

    @Test
    public void testFindUserById_when_userIsInvalidData_then_throwException() {
        when(userDao.findUserById("Student")).thenReturn(buildUserBaseInfo(null, "Student"));
        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserById("Student"));
        // verify target method exceptionService.increaseCount was triggered
        verify(exceptionService).increaseCount();
    }


    private User buildUserBaseInfo(Type type, String id) {
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return User.builder()
                .id(id)
                .name("King")
                .type(type)
                .birthday(birthday)
                .build();
    }
}
