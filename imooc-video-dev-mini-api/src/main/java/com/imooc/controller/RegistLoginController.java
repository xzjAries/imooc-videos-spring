package com.imooc.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.User;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "用户注册登录接口", tags = "注册和登陆的controller")
public class RegistLoginController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "用户注册", tags = "用户注册")
	@PostMapping("/regist")
	public IMoocJSONResult regist(@RequestBody User user) throws Exception {
		// 1.怕那段用戶名和密碼不能爲空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return IMoocJSONResult.errorMsg("用户名和密码不能为空");
		}
		// 2.判断用户名是否存在
		boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());

		// 3.保存用户，注册信息
		if (!usernameIsExist) {
			user.setNickname(user.getUsername());
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			user.setFansCounts(0);
			user.setReceiveLikeCounts(0);
			user.setFollowCounts(0);
			userService.saveUser(user);
		} else {
			return IMoocJSONResult.errorMsg("用户名已经存在，请换一个再试");
		}
		user.setPassword("");
		return IMoocJSONResult.ok(user);
	}

	@ApiOperation(value="用户登陆",notes="用户登陆接口")
	@PostMapping("/login")
	public IMoocJSONResult login(@RequestBody User user) throws Exception {
		// 1.判断用戶名和密碼不能爲空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return IMoocJSONResult.errorMsg("用户名和密码不能为空");
		}

		// 2.判断用户名是否存在
		User userExist = userService.queryUserForLogin(user.getUsername(),
			  MD5Utils.getMD5Str(user.getPassword()));

		
		// 3.包装用户信息返回前端
        if(userExist != null) {
        	userExist.setPassword("");
          return IMoocJSONResult.ok(userExist);
        }
		return IMoocJSONResult.errorMsg("用户或者密码不正确，请重试!");

	}

}
