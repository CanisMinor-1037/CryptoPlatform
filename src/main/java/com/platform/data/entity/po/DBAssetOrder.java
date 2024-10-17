package com.platform.data.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_db_asset_order")
public class DBAssetOrder {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("fabricId")
    private String fabricId;
    private String applicant;
    private String owner;
    private String name;
    private String policy;
    @TableField("`sql`")
    private String sql;
    @TableField("createTime")
    private LocalDateTime createTime;
}
