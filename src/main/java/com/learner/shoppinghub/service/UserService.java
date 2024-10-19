package com.learner.shoppinghub.service;

import java.util.List;
import java.util.Optional;

import com.learner.shoppinghub.models.User;

public interface UserService {

	public void SaveUser(User user);
	public List<User> getUser();
	public void deleteById(int id);
	public Optional<User> findById(int id);
}
