package com.platform.data.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ca")
@Data
public class CaServerComponent {
    private String publicKeyPath;
    private String masterKeyPath;
    private String privateKeyPath;
}
