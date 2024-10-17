package com.platform.data.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RealTime {
    private String fabricId;
    private String applicant;
    private String owner;
    private String name;
    private LocalDateTime time;
    private String status;

}
