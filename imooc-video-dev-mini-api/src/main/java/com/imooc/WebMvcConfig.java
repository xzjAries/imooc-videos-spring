package com.imooc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.controller.interceptor.MiniInterceptor;

@Configuration

public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/**").addResourceLocations("file:C://imooc_videos_dev");

	}
	
	@Bean
	public MiniInterceptor miniInterceptor() {	
		return new MiniInterceptor(); 
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
		                                          .addPathPatterns("/video/uploadCover","/video/upload")
		                                          .addPathPatterns("/video/userLike","/video/userUnLike")
		                                          .addPathPatterns("/bgm/**")
		                                          .excludePathPatterns("/user/queryPublisher");
		
		super.addInterceptors(registry);
	}
	
}
