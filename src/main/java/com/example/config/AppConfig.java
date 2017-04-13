package com.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vvaka on 4/12/17.
 */
@Component
@ConfigurationProperties
@Data
public class AppConfig {

    @JsonProperty("tenants")
    private List<TenantConfig> tenants;
}


