package com.imooc.service;

import com.imooc.pojo.User;


public interface UserService {
	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean queryUsernameIsExist(String username);

	/**
	 * 保存用户信息（用户注册）
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}
