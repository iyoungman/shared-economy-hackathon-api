package com.hackathon.sharedeconomy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by YoungMan on 2019-03-11.
 * file:///C:/testimg/
 * file:///usr/local/tomcat-8.0.53/webapps/imgfile/
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imgfile/**")
				.addResourceLocations("file:///home/ec2-user/project/shared-img-file/");
	}
}