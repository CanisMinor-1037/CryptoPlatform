package com.platform.data.controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

import com.platform.data.entity.po.User;
import com.platform.data.service.webService.UserService;

import com.platform.data.util.Result;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> loginController(@RequestParam String uname, @RequestParam String password){
        User user = userService.loginService(uname, password);
        if(user!=null){
                return Result.success(user);
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public Result<User> registerController(@RequestBody User newUser){
        User user = userService.registService(newUser);
        if(user!=null){
            return Result.success(user);
        }else{
            return Result.error("456","用户名已存在！");
        }
    }
}
