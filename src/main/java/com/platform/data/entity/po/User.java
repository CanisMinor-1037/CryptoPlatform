package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    // 注意属性名要与数据表中的字段名一致
    // 主键自增int(10)对应long
    @TableId(value = "uid", type= IdType.AUTO)
    private Long uid;
    private String uname;
    private String password;
    @TableField("usertype")
    private String usertype;
}
