package com.leon.demo.junit.service;

import com.leon.demo.junit.utils.DateUtils;
import lombok.Getter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * @Author : LeonWang
 * @Descpriction  when the test case for one method is too many , we can run test with Parameterized runner
 * @Date:created 2022/5/5
 */
@Getter
@RunWith(value = Parameterized.class)
@ToString
public class DateUtilsTest {
    private String birthday;
    private String today;
    private int age;

    public DateUtilsTest(String birthday, String today, int age){
        this.birthday = birthday;
        this.today = today;
        this.age = age;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data = new Object[][]{
                {"2020-06-05","2022-05-05",2},
                {"2022-05-05","2022-05-05",1},
                {"2022-01-01","2022-05-05",1}

        };
        return Arrays.asList(data);
    }
    @Test
    public void testCalculateAge() throws ParseException {
        System.out.println(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int actual = DateUtils.calculateAge(sdf.parse(birthday), sdf.parse(today));
        Assert.assertEquals(age, actual);
    }
}
