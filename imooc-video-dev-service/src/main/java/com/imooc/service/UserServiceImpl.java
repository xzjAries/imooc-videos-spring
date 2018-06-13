package com.imooc.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.UserMapper;
import com.imooc.pojo.User;

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

}
