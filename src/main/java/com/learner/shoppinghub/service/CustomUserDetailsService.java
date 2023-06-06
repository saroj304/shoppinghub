package com.learner.shoppinghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learner.shoppinghub.models.CustomUserDetails;
import com.learner.shoppinghub.models.Role;
import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		User u = userrepo.findByEmail(email);
		if (u == null) {
			System.out.println("email doesnot exists");
			throw new UsernameNotFoundException("user doesnot exits!!");
		}
		for(Role r:u.getRole()) {
			System.out.println(r.getRole_name());
		}
		return new CustomUserDetails(u);
	}
}
