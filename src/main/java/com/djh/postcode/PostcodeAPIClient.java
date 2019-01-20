package com.djh.postcode;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hancock
 */
public class PostcodeAPIClient {

    private static final String API_ENDPOINT = "https://api.postcodes.io";
    private static final String QUERY_STRING = "/postcodes/?q=";

    private final RestTemplate restTemplate;

    public PostcodeAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Postcode> getPostcodesFor(String query) {

        PostcodesResponse postcodesResponse = restTemplate.getForObject(
                API_ENDPOINT + QUERY_STRING + query,
                PostcodesResponse.class);

        List<Postcode> postcodes = postcodesResponse.getResult();
        if (postcodes == null) {
            return new ArrayList<>();
        } else {
            return postcodes;
        }
    }

    private static class PostcodesResponse {

        private List<Postcode> result;

        public PostcodesResponse() {
        }

        public List<Postcode> getResult() {
            return result;
        }
    }

}
