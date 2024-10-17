package com.platform.data.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "fabric")
@Data
public class HyperLedgerFabricProperties {
    private String mspId;
    private String certificatePath;
    private String privateKeyPath;
    private String tlsCertPath;
    private String channel;
    private String contract;
    private String ip;
    private String name;
    private Boolean sync;
}
