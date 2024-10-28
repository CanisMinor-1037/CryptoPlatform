package com.platform.data.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Database {
    private String url;
    private String username;
    private String password;
}
