package com.platform.data.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class SQLResult {
    private List<String> column;
    private List<List<Object>> value;
}
