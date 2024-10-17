package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class VideoAssetOrderFabricDto {
    private String id;
    private String videoAssetId;
    private String applicantId;
    private int status;
}
