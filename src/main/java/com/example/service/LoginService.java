package com.example.service;

import com.example.config.AppConfig;
import com.example.config.Tenant;
import com.example.config.TenantConfig;
import com.example.domain.AuthInOut;
import com.example.domain.Request;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by vvaka on 4/12/17.
 */
@Component
public class LoginService {

    private AppConfig appConfig;


    @Autowired
    public LoginService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }


    Optional<TenantConfig> getTenantConfigForTenant(final Tenant tenant) {
        return appConfig.getTenants().stream()
                .filter(tc -> tc.getTenant().equals(tenant)).findFirst();

    }

    public Optional<AuthInOut> doLogin(User user, HttpHeaders httpHeaders) {

        String countryCode = httpHeaders.getFirst("countryCode");
        String businessCode = httpHeaders.getFirst("businessCode");
        String channelCode = httpHeaders.getFirst("channelID");
        String ts = httpHeaders.getFirst("ts");

        Optional<TenantConfig> tenantConfigOptional = getTenantConfigForTenant(new Tenant(countryCode, businessCode, channelCode, ts));

        TenantConfig tc = tenantConfigOptional.orElseThrow(NoSuchElementException::new);

        AuthInOut authNResult = tc.getAuthN().getStrategy().getFunc()
                .apply(tc.getAuthN().getBackEnds(),
                        AuthInOut.builder()
                                .tenantConfig(tc)
                                .request(
                                        Request.builder()
                                                .payload(user)
                                                .headers(httpHeaders)
                                                .build()
                                ).build());


        AuthInOut authZResult = tc.getAuthZ().getStrategy().getFunc()
                .apply(tc.getAuthZ().getBackEnds(),
                        authNResult);

        AuthInOut authSsoResult = tc.getAuthSso().getStrategy().getFunc()
                .apply(tc.getAuthSso().getBackEnds(),
                        authZResult);


        AuthInOut authPostLoginResult = tc.getAuthAfter().getStrategy().getFunc()
                .apply(tc.getAuthAfter().getBackEnds(),
                        authSsoResult);


        return Optional.of(authPostLoginResult);
    }
}
