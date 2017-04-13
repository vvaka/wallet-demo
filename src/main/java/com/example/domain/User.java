package com.example.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by vvaka on 4/12/17.
 */
@Data
public class User {

    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
