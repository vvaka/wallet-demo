package com.example;

import com.example.domain.AuthInOut;
import com.example.domain.User;
import com.example.service.LoginService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
public class DemoLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLoginApplication.class, args);
    }
}

@RestController
@Slf4j
class DemoLogin {

    private LoginService loginService;

    @Autowired
    public DemoLogin(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    ResponseEntity<AuthInOut> doLogin(@RequestBody User user, @RequestHeader HttpHeaders headers) {

        Optional<AuthInOut> result = loginService.doLogin(user, headers);

        return ResponseEntity.ok(result.orElse(null));
    }


}

