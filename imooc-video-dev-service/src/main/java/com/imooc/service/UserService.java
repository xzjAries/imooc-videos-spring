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
    
    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public User queryUserInfo(String userId);
    
    /*
     * 查询用户和视频的关系是否喜欢视频
     */
    public boolean isUserLikeVideo(String userId,String videoId);
    
    /**
     * 增加用户和粉丝的关系
     * @param userId
     * @param fanId
     */
    public void saveUserFanRelation(String userId,String fanId);
    
    /**
     * 删除用户和粉丝的关系
     * @param userId
     * @param fanId
     */
    public void deleteUserFanRelation(String userId,String fanId);
    
    /**
     * 查询用户是否被关注
     */
    public boolean queryIfFollow(String userId,String fanId);
}
