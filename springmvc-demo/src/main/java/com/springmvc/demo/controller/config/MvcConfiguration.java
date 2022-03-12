package com.springmvc.demo.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;



@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

//	@Bean
//	public InternalResourceViewResolver viewClass(){
//		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//		/**
//		 * <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
//		 * 		<property name="prefix" value=""/>
//		 * 		<property name="suffix" value=".jsp"/>
//		 */
//		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
//		internalResourceViewResolver.setSuffix(".jsp");
//		return internalResourceViewResolver;
//	}
}

