package com.saving.validjsonsdk;

import com.saving.validjsonsdk.utils.ValidRequestParamsUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author saving
 */
@Configuration
@ConfigurationProperties("validjson")
@ComponentScan("com.saving.validjsonsdk")
@Data
public class ValidJsonConfig {

    private String schemaUrl;

    @Bean(name = "ValidRequestParamsUtil")
    public ValidRequestParamsUtil validRequestParamsUtil() {
        return new ValidRequestParamsUtil(schemaUrl);
    }
}
