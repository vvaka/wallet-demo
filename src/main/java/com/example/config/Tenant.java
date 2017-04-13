package com.example.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by vvaka on 4/12/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @NotBlank
    private String countryCode;
    @NotBlank
    private String businessCode;
    @NotBlank
    private String channelCode;

    private String ts;

}
