package com.inu.hackerton.spring.controller;

import com.inu.hackerton.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        System.out.println("회원가입된 아이디: " + username);
        System.out.println("회원가입된 비밀번호: " + password);
        return userService.signup(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println("로그인된 아이디: " + username);
        System.out.println("로그인된 비밀번호: " + password);
        return userService.login(username, password);
    }

}
