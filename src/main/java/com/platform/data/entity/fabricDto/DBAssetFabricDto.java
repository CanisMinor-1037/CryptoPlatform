package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class DBAssetFabricDto {
    private String id;
    private String name;
    private String ownerId;
    private String policy;
    private String location;
    private String field;
    private String jdbcUrl;
    private String username;
    private String password;
    private String aesKey;
    private int encType;
}
