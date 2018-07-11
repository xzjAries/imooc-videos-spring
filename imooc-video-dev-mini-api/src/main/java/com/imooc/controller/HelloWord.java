package com.imooc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.ApiImplicitParam;

@RestController
public class HelloWord {
	@PostMapping("/helloWord")
	@ApiImplicitParam(name = "hello", value = "hello", required = true, dataType = "int", paramType = "query")
 public IMoocJSONResult helloWord(int hello) {
	 return IMoocJSONResult.ok(hello);
 }
	
}
