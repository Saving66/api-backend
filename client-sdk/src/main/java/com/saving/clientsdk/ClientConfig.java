package com.saving.clientsdk;

import com.saving.clientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("api.client")
@ComponentScan("com.saving.clientsdk")
@Data
public class ClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean(name = "apiClient2")
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }
}
