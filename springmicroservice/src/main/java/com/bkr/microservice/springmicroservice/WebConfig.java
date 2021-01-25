package com.bkr.microservice.springmicroservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**") //(/user) the controllers.
                .allowedMethods("*") //(/PUT) the methodes.
                .allowedOrigins("*"); //(http:localhost:4200.com) the path for UI application.
    }

}
