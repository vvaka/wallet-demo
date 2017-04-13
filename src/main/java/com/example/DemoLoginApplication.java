package com.example;

import com.example.config.AppConfig;
import com.example.domain.User;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLoginApplication.class, args);
    }
}

@RestController
@Slf4j
class DemoLogin {

    private AppConfig appConfig;

    @Autowired
    public DemoLogin(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    ResponseEntity<Void> doLogin(@RequestBody User user) {

        this.validateUser(user.getUserId());

        return ResponseEntity.ok(null);
    }

    private void validateUser(String userId) {

    }


}

