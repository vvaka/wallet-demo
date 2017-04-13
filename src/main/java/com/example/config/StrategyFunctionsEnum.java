package com.example.config;

import com.example.domain.AuthInOut;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vvaka on 4/12/17.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StrategyFunctionsEnum {


    AnyOfByServiceId(it -> AuthInOut.builder().build()),
    AllOfByServiceId(it -> AuthInOut.builder().build()),
    AllOfByCustomerType(it -> AuthInOut.builder().build()),

    AnyOfByCustomerType(it -> AuthInOut.builder().build()),


    AnyOf(it -> {
        TenantConfig tc = it.getTenantConfig();

        List<BackEndServiceClient> backEndServiceClientList = null;
        String nextFlow = "AuthZ";
        ;
        switch (it.getCurrentFlow()) {
            case "AuthN":
                backEndServiceClientList = tc.getAuthN().getBackEnds();
                nextFlow = "AuthZ";

                break;
            case "AuthZ":
                backEndServiceClientList = tc.getAuthZ().getBackEnds();
                nextFlow = "AuthSso";
                break;
            case "AuthSso":
                backEndServiceClientList = tc.getAuthSso().getBackEnds();
                nextFlow = "AuthAfter";
                break;
            case "AuthAfter":
                backEndServiceClientList = tc.getAuthAfter().getBackEnds();
                break;

        }

        backEndServiceClientList.stream().map(
                b -> makeRestCalltoTheBackend(b)
        ).collect(Collectors.toList());

        return AuthInOut.builder()
                .currentFlow(nextFlow)
                .authNResults(null)
                .build();
    }),

    AllOf(it -> {
        return AuthInOut.builder().build();
    }),

    NoOp(it -> AuthInOut.builder().build());

    private static Optional<AuthInOut> makeRestCalltoTheBackend(BackEndServiceClient b) {

        return Optional.empty();
    }


    private Function<AuthInOut, AuthInOut> func;

    @Autowired
    AppConfig appConfig;

    StrategyFunctionsEnum(Function<AuthInOut, AuthInOut> func) {
        this.func = func;
    }


    public Function<AuthInOut, AuthInOut> getFunc() {
        return this.func;
    }
}
