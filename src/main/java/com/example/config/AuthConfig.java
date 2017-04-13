package com.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by vvaka on 4/12/17.
 */
@Data
public class AuthConfig {
    @NotNull
    private StrategyBiFunctionsEnum strategy;
    @Valid
    @JsonProperty("backends")
    private List<BackEndServiceClient> backEnds;
}
