package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class DataAssetOrderFabricDto {
    private String id;
    private String dataAssetId;
    private String applicantId;
    private int status;
}
