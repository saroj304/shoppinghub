package com.learner.shoppinghub.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userservice;
	@Autowired
	BCryptPasswordEncoder passwordencoder;


	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user") User user) {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		}
		user.setPassword(passwordencoder.encode(user.getPassword()));
		userservice.SaveUser(user);
		return "redirect:/shop";
	}
}
