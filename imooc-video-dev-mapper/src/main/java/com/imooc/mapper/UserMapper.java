package com.imooc.mapper;

import org.apache.ibatis.annotations.Param;

import com.imooc.pojo.User;
import com.imooc.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

 /**
  * 用戶受到喜欢数量累加
  */
  public void addReceiveLikeCount(@Param("userId") String userId);
  
  /**
   * 用戶受到喜欢数量累减
   */
  public void reduceReceiveLikeCount(@Param("userId")  String userId);

  /**
   * 增加粉丝的数量
   */
  public void addFansCount(@Param("userId")  String userId);
  
  /**
   * 增加关注的数量
   */
  public void addFollersCount(@Param("userId")  String userId);
  
  /**
   * 减少粉丝的数量
   */
  public void reduceFansCount(@Param("userId")  String userId);
  
  /**
   * 减少关注的数量
   */
  public void reduceFollersCount(@Param("userId")  String userId);
  
}