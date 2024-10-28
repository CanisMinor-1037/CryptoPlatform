package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_db_asset")
public class DBAsset {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("fabricId")
    private String fabricId;
    private String name;
    private String owner;
    private String policy;
    private String location;
    private String field;
    private String url;
    private String username;
    private String password;
    @TableField("AESkey")
    private String aesKey;
    @TableField("encType")
    private int encType;
    @TableField("createTime")
    private LocalDateTime createTime;
}
