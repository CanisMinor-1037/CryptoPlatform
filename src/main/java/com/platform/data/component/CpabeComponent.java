package com.platform.data.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cpabe")
@Data
public class CpabeComponent {
    private String publicKeyPath;
    private String privateKeyPath;
    private String inputFilePath;
    private String encFilePath;
    private String decFilePath;
}

