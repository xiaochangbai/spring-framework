package com.springmvc.demo.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.springmvc.demo" })
@Import({ MvcConfiguration.class })
public class AppConfiguration {

}
