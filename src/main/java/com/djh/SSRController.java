package com.djh;

import com.djh.postcode.Postcode;
import com.djh.postcode.PostcodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Hancock
 */
@Controller
public class SSRController {

    private static final Logger log = LoggerFactory.getLogger(SSRController.class);

    private final PostcodeService postcodeService;
    private final ObjectMapper objectMapper;

    public SSRController(PostcodeService postcodeService, ObjectMapper objectMapper) {
        this.postcodeService = postcodeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{path:(?!.*.js|.*.css|.*.jpg).*$}")
    public String render(Model model, HttpServletRequest request) {

        addCurrentPath(model, request);
        addServerSideContent(model);
        return "index";
    }

    private void addCurrentPath(Model model, HttpServletRequest request) {

        String path = request.getServletPath();
        if (request.getServletPath().equals("/index.html")) {
            path = "/";
        }

        if (request.getQueryString() != null) {
            path = String.format("%s?%s", path, request.getQueryString());
        }
        model.addAttribute("currentPath", path);
    }

    private void addServerSideContent(Model model) {

        String initialPostcodeQuery = "ST3";
        List<Postcode> postcodes = postcodeService.retrievePostcodesFor(initialPostcodeQuery);

        Map<String, Object> serverSideState = new HashMap<>();
        serverSideState.put("postcodeQuery", initialPostcodeQuery);
        serverSideState.put("postcodes", postcodes);
        try {
            model.addAttribute("serverSideState", objectMapper.writeValueAsString(serverSideState));
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize image posts", e);
        }
    }

}
