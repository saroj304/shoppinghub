package com.learner.shoppinghub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.repository.UserRepo;
import com.learner.shoppinghub.service.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
UserRepo userrepo;
	@Override
	public void SaveUser(User user) {
      userrepo.save(user);		
	}

}
