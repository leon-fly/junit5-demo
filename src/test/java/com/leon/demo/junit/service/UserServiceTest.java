package com.leon.demo.junit.service;

import com.leon.demo.junit.adapter.UserAdapter;
import com.leon.demo.junit.dao.UserDao;
import com.leon.demo.junit.model.Type;
import com.leon.demo.junit.model.User;
import com.leon.demo.junit.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : LeonWang
 * @Descpriction This is a common spring unit tester
 * Junit 4
 * @Date:created 2022/5/4
 */
@SpringBootTest(classes = {UserServiceImpl.class, UserDao.class, UserAdapter.class, ExceptionService.class})
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private UserAdapter userAdapter;

    @Autowired
    private ExceptionService exceptionService;

    @Before
    public void setup() {
        // you can set up a common mock for all test method.
        Mockito.when(userAdapter.getStudentExtendInfo(Mockito.anyString())).thenReturn(User.StudentExtraInfo.builder().grade("Mockito-Grade-3").build());
        Mockito.when(userAdapter.getTeacherExtendInfo(Mockito.anyString())).thenReturn(User.TeacherExtraInfo.builder().subject("Mockito-Subject-Chinese").build());
    }

    @Test
    public void testFindUserById_when_userIsStudent_then_returnUserWithStudentExtendInfo() {
        // you can set up a specific mock for all test method.
        Mockito.when(userDao.findUserById("Student")).thenReturn(buildUserBaseInfo(Type.STUDENT, "Student"));
        User student = userService.findUserById("Student");
        Assert.assertNotNull(student.getStudentExtraInfo());
        Assert.assertNull(student.getTeacherExtraInfo());
    }

    @Test
    public void testFindUserById_when_userIsTeacher_then_returnUserWithTeacherExtendInfo() {
        Mockito.when(userDao.findUserById("Teacher")).thenReturn(buildUserBaseInfo(Type.TEACHER, "Teacher"));
        User teacher = userService.findUserById("Teacher");
        Assert.assertNotNull(teacher.getTeacherExtraInfo());
        Assert.assertNull(teacher.getStudentExtraInfo());
    }

    @Test
    public void testFindUserById_when_userIsInvalidData_then_throwException() {
        Mockito.when(userDao.findUserById("Student")).thenReturn(buildUserBaseInfo(null, "Student"));
        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserById("Student"));
        // verify target method exceptionService.increaseCount was triggered. not a good way
        Assert.assertEquals(1, exceptionService.getErrorMessageCount());
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
