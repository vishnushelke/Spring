package com.bridgelabz.fundoo.user.repository;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.response.Response;

public interface IRadisRepo {
	Response saveUser(User user);
	
	Response findUser(int userId);
}
