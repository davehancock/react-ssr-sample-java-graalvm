package com.djh.postcode;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author David Hancock
 */
public class PostcodeService {

    private final PostcodeAPIClient postcodeAPIClient;

    public PostcodeService(PostcodeAPIClient postcodeAPIClient) {
        this.postcodeAPIClient = postcodeAPIClient;
    }

    @Cacheable("postcodes")
    public List<Postcode> retrievePostcodesFor(String query) {
        return postcodeAPIClient.getPostcodesFor(query);
    }

}
