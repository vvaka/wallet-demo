package com.example.config;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by vvaka on 4/12/17.
 */
@Data
public class TenantConfig {

    @Valid
    @NotNull
    private Tenant tenant;
    @Valid
    private AuthConfig authN;
    @Valid
    private AuthConfig authZ;
    @Valid
    private AuthConfig authSso;
    @Valid
    private AuthConfig authAfter;


}
