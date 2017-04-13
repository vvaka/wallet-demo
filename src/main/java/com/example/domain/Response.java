package com.example.domain;

import com.example.config.BackEndServiceClient;
import com.fasterxml.jackson.annotation.JsonRawValue;
import groovy.transform.builder.Builder;
import lombok.Value;
import org.springframework.util.MultiValueMap;

/**
 * Created by vvaka on 4/12/17.
 */

@Value
@Builder
public class Response {

    BackEndServiceClient backend;

    @JsonRawValue
    String payload;

    MultiValueMap headers;
}
