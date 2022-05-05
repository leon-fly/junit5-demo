package com.leon.demo.junit.service;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
@Data
@Builder
@Component
@Slf4j
public class ExceptionService {
    private int errorMessageCount = 0;
    public synchronized void increaseCount(){
        //let's suppose we call an api to save the info
        errorMessageCount++;
    }
}
