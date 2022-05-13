package com.leon.demo.junit.service;

import com.leon.demo.junit.utils.DateUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : LeonWang
 * @Descpriction
 * Junit 5
 * @Date:created 2022/5/13
 */
public class DateUtilsTestV2 {
    public static Stream<Arguments> argsProvider(){
        return Stream.of(
                Arguments.of("2020-06-05","2022-05-05",2),
                Arguments.of("2022-05-05","2022-05-05",1),
                Arguments.of("2022-01-01","2022-05-05",1)
        );
    }
    @ParameterizedTest
    @MethodSource(value = "argsProvider")
    @SneakyThrows
    public void testCalculateAge(String birthday, String today, int age) {
        System.out.println(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int actual = DateUtils.calculateAge(sdf.parse(birthday), sdf.parse(today));
        assertEquals(age, actual);
    }
}
