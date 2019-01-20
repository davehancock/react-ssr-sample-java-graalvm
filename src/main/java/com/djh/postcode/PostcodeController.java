package com.djh.postcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author David Hancock
 */
@RestController
public class PostcodeController {

    private final PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/postcode/{postcode}")
    public List<Postcode> getPostcodes(@PathVariable String postcode) {
        return postcodeService.retrievePostcodesFor(postcode);
    }

}
