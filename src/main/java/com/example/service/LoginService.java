package com.example.service;

import com.example.config.AppConfig;
import com.example.config.Tenant;
import com.example.config.TenantConfig;
import com.example.domain.AuthInOut;
import com.example.domain.Request;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by vvaka on 4/12/17.
 */
public class LoginService {

    AppConfig appConfig;


    @Autowired
    public LoginService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }


    Optional<TenantConfig> getTenantConfigForTenant(Tenant tenant) {
        return appConfig.getTenants().stream().filter(t -> t.equals(tenant)).findFirst();

    }

    public Optional<AuthInOut> doLogin(User user, HttpHeaders httpHeaders) {

        String countryCode = httpHeaders.getFirst("countryCode");
        String businessCode = httpHeaders.getFirst("businessCode");
        String channelCode = httpHeaders.getFirst("channelID");
        String ts = httpHeaders.getFirst("ts");

        Optional<TenantConfig> tenantConfigOptional = getTenantConfigForTenant(new Tenant(countryCode, businessCode, channelCode, ts));

        TenantConfig tc = tenantConfigOptional.orElseThrow(NoSuchElementException::new);

        AuthInOut out = tc.getAuthN().getStrategy().getFunc()
                .andThen(tc.getAuthZ().getStrategy().getFunc())
                .andThen(tc.getAuthSso().getStrategy().getFunc())
                .andThen(tc.getAuthAfter().getStrategy().getFunc())
                .apply(AuthInOut.builder()
                        .tenantConfig(tc)
                        .currentFlow("AuthN")
                        .request(
                        Request.builder().payload(user).headers(httpHeaders).build()
                ).build());

        return Optional.of(out);
    }
}
