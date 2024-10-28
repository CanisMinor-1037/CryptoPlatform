package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class VideoAssetFabricDto {
    private String id;
    private String name;
    private String ownerId;
    private String policy;
    private String location;
    private String field;
    private String rtspUrl;
    private String aesKey;
    private int encType;
}
