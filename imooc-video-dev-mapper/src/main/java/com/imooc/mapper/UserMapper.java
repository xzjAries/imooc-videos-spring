package com.imooc.mapper;

import org.apache.ibatis.annotations.Param;

import com.imooc.pojo.User;
import com.imooc.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

 /**
  * 用戶受到喜欢数量累加
  * @param userId
  */
  public void addReceiveLikeCount(@Param("userId") String userId);
  
  /**
   * 用戶受到喜欢数量累减
   * @param userId
   */
  public void reduceReceiveLikeCount(@Param("userId")  String userId);
}