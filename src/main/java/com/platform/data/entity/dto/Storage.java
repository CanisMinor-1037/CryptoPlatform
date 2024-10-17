package com.platform.data.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Storage {
    private String name;
    private String owner;
    private LocalDateTime createTime;
}
