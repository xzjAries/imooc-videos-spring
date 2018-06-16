package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.imooc.mapper")
@ComponentScan(basePackages= {"com.imooc","org.n3r.idworker"})
@EnableSwagger2
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); 
	}
	
	
}
