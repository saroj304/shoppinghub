package com.learner.shoppinghub.serviceimpl;

import java.util.List;
import java.util.Optional;

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
	@Override
	public List<User> getUser() {
		return userrepo.findAll();
	}
	@Override
	public void deleteById(int id) {
		userrepo.deleteById(id);
		
	}
	@Override
	public Optional<User> findById(int id) {
		Optional<User> o=userrepo.findById(id);
		return o;
	}

}
