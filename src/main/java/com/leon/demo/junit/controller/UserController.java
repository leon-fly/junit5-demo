package com.leon.demo.junit.controller;

import com.leon.demo.junit.model.User;
import com.leon.demo.junit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : LeonWang
 * @Descpriction
 * @Date:created 2022/5/4
 */
@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping(value = "/{id}")
    @ResponseBody
    public User findUserById(@PathVariable String id){
        return userService.findUserById(id);
    }
}
