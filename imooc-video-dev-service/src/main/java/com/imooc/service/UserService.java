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
	/**
	 * 判断登陆用户和密码是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public User queryUserForLogin(String username,String password);
 
	/**
	 * 用户修改信息
	 * @param user
	 */
    public void updateUserInfo(User user);
}
