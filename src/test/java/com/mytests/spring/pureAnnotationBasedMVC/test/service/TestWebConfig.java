package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import com.mytests.spring.pureAnnotationBasedMVC.test.web.IndexController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@PropertySource("messages.properties")
public class TestWebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp").cache(true);
    }

    @Bean
    public IndexController indexController() {
        return new IndexController();
    }
}