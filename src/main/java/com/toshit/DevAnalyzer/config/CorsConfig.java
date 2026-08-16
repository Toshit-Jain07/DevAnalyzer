package com.toshit.DevAnalyzer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                         // apply to every endpoint
                .allowedOrigins("http://DevAnalyzerBackend.onrender.com")    // whitelist your frontend's origin
                .allowedMethods("GET")
                .allowedHeaders("*");
    }
}