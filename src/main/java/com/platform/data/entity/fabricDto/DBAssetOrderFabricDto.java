package com.platform.data.entity.fabricDto;

import lombok.Data;

@Data
public class DBAssetOrderFabricDto {
    private String id;
    private String dbAssetId;
    private String applicantId;
    private String sql;
}
