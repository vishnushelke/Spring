package com.bridgelabz.fundoo.user.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.response.Response;

public class RedisRepository implements IRadisRepo {

	@Autowired
	private RedisTemplate<String, User> template;
	
	@Autowired
	private HashOperations<String, Integer, User> hashoperations;
	
//	@PostConstruct
//	public void initializeHashOperations()
//	{
//		hashoperations = template.ha
//	}
	@Override
	public Response saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
