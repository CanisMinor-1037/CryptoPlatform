package com.platform.data.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "department")
@Data
public class MyDepartment {
    private String name;
    private String fabricId;
}
