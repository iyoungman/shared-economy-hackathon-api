package com.hackathon.sharedeconomy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by YoungMan on 2019-03-11.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /*
     * file:///C:/testimg/
     * file:///usr/local/tomcat-8.0.53/webapps/imgfile/
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imgfile/**")
//                .addResourceLocations("file:///C:/testimg/");
                .addResourceLocations("file:///usr/local/tomcat-8.0.53/webapps/imgfile/");
    }
}