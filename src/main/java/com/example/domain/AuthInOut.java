package com.example.domain;

import com.example.config.BackEndServiceClient;
import com.example.config.TenantConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.Map;

/**
 * Created by vvaka on 4/12/17.
 */

@Value
@Builder
public class AuthInOut {


    private Request request;

    List<Response> authResults;

    @JsonIgnore
    private TenantConfig tenantConfig;

}
