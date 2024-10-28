package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class DataAssetFabricDto {
    private String id;
    private String name;
    private String ownerId;
    private String policy;
    private String location;
    private String field;
    private String cid;
    private String aesKey;
    private int encType;
}
