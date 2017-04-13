package com.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by vvaka on 4/12/17.
 */
@Data
public class BackEndServiceClient{

    private String serviceId;

    private String path;

    @JsonProperty("class")
    private String clazz;



}
