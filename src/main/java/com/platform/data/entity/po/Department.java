package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_department")
public class Department {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("fabricId")
    private String fabricId;
    private String name;
    private String attribute;

}
