package com.example.domain;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpHeaders;

/**
 * Created by vvaka on 4/12/17.
 */


@Value
@Builder
public class Request {

    @JsonRawValue
    Object payload;

    HttpHeaders headers;
}
