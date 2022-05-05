package com.leon.demo.junit.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/5
 */
public final class DateUtils {
    public static int calculateAge(Date birthday, Date today){
        Calendar birthDayC = Calendar.getInstance();
        birthDayC.setTime(birthday);

        Calendar todayC = Calendar.getInstance();
        todayC.setTime(today);

        int age = todayC.get(Calendar.YEAR) - birthDayC.get(Calendar.YEAR);

        birthDayC.add(Calendar.YEAR, age);
        if (birthDayC.compareTo(todayC)<=0){
            age++;
        }
        return age;
    }
}
