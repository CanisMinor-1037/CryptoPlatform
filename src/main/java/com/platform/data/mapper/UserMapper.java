package com.platform.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.data.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUname(String uname); //通过用户名uname查找用户，注意要按照JPA的格式使用驼峰命名法
    User findByUnameAndPassword(String uname, String passwd);//通过用户名uname和密码查找用户

    User save(User user);
}

