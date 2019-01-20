package com.djh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

/**
 * @author David Hancock
 */
@Configuration
public class SSRConfiguration {

    @Bean
    public ViewResolver reactViewResolver() {
        return new ScriptTemplateViewResolver("/public/", ".html");
    }

    @Bean
    public ScriptTemplateConfigurer templateConfigurer() {

        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("graal.js");
        configurer.setScripts(
                "public/server.js"
        );
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(false);
        return configurer;
    }

}
