package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_data_asset_orderer_history")
public class OrderHistory {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("fabricId")
    private String fabricId;
    private String applicant;
    private String owner;
    private String name;
    private String policy;
    private Integer status;
    private LocalDateTime time;
}
