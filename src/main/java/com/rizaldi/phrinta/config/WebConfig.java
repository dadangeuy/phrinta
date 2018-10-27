package com.rizaldi.phrinta.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "phrinta.web")
@Data
public class WebConfig {
    private String title;
    private String icon;
}
