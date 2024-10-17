package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_department_attribute_history")
public class AttributeHistory {
    @TableId(type=IdType.AUTO)
    private Long id;
    @TableField("fabricId")
    private String fabricId;
    private String attribute;
    private LocalDateTime time;

}
