package com.imooc.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.UserMapper;
import com.imooc.pojo.User;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
	
    @Autowired
    private Sid sid;
    
    @Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		User user = new User();
		user.setUsername(username);
		User result = userMapper.selectOne(user);
	
		return result==null?false:true;
	}

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveUser(User user) {
		String userId = sid.nextShort();
		user.setId(userId);
		userMapper.insert(user);
	}

    @Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public User queryUserForLogin(String username, String password) {
		Example userExample = new Example(User.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("username",username);
		criteria.andEqualTo("password",password);
		User result = userMapper.selectOneByExample(userExample);
		return result;
	}

}
