package com.platform.data.service.webService.impl;

import com.platform.data.entity.po.User;
import com.platform.data.mapper.UserMapper;
import com.platform.data.service.webService.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User loginService(String uname, String passwd) {
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        User user = userMapper.findByUnameAndPassword(uname, passwd);
        // 重要信息置空
        if(user != null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User registService(User user) {
        //当新用户的用户名已存在时
        if(userMapper.findByUname(user.getUname())!=null){
            // 无法注册
            return null;
        }else{
            //返回创建好的用户对象(带uid)
            User newUser = userMapper.save(user);
            if(newUser != null){
                newUser.setPassword("");
            }
            return newUser;
        }
    }
}
