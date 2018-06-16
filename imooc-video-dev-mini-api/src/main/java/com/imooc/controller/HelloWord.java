package com.imooc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
	@RequestMapping("/hello")
 public String helloWord() {
	 return "hellop word~";
 }
	
	@RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }
}
