package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.happy.interceptor.ConfirmInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	private ConfirmInterceptor confirmInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns("/notice/noticelist/**");
	}
}
