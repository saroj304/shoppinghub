package com.learner.shoppinghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learner.shoppinghub.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(String email);

}
