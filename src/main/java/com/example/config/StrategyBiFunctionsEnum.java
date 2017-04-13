package com.example.config;

import com.example.domain.AuthInOut;
import com.example.domain.Response;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.example.utils.FunctionUtils.makeRestCallToBackend;

/**
 * Created by vvaka on 4/12/17.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StrategyBiFunctionsEnum {


    AnyOfByServiceId((backendList, it) -> AuthInOut.builder().build()),
    AllOfByServiceId((backendList, it) -> AuthInOut.builder().build()),
    AllOfByCustomerType((backendList, it) -> AuthInOut.builder().build()),

    AnyOfByCustomerType((backendList, it) -> AuthInOut.builder().build()),


    AnyOf((backendList, it) -> {
        List<Response> responses = backendList.stream().map(
                b -> makeRestCallToBackend(b, it)
        ).collect(Collectors.toList());
        return AuthInOut.builder()
                .authResults(responses)
                .build();
    }),

    AllOf((backendList, it) -> {
        List<Response> responses = backendList.stream().map(
                b -> makeRestCallToBackend(b, it)
        ).collect(Collectors.toList());
        return AuthInOut.builder()
                .authResults(responses)
                .build();
    }),

    NoOp((backendList, it) -> AuthInOut.builder().build());


    private BiFunction<List<BackEndServiceClient>, AuthInOut, AuthInOut> func;

    @Autowired
    AppConfig appConfig;

    StrategyBiFunctionsEnum(BiFunction<List<BackEndServiceClient>, AuthInOut, AuthInOut> func) {
        this.func = func;
    }


    public BiFunction<List<BackEndServiceClient>, AuthInOut, AuthInOut> getFunc() {
        return this.func;
    }
}
