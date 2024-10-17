package com.platform.data.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ipfs")
@Data
public class IpfsComponent {
    private String ip;
    private int port;
    private String tmpFileBasePath;
}
