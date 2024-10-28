package com.platform.data.entity.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DepartmentRanking {
    private ArrayList<String> department;
    private ArrayList<Long> times;
}
