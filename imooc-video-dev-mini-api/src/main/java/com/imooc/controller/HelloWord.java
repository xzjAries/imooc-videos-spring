package com.imooc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.utils.IMoocJSONResult;

@RestController
public class HelloWord {
	@RequestMapping("/hello")
 public IMoocJSONResult helloWord() {
	 return IMoocJSONResult.ok("zhengque");
 }
	
}
