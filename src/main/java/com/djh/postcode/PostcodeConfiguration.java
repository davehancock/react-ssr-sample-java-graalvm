package com.djh.postcode;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author David Hancock
 */
@EnableCaching
@Configuration
public class PostcodeConfiguration {

    @Bean
    public PostcodeService postcodeService(PostcodeAPIClient postcodeAPIClient) {
        return new PostcodeService(postcodeAPIClient);
    }

    @Bean
    public PostcodeAPIClient postcodeAPIClient(RestTemplate restTemplate) {
        return new PostcodeAPIClient(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
