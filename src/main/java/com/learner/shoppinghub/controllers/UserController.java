package com.learner.shoppinghub.controllers;

import com.learner.shoppinghub.models.Role;
import com.learner.shoppinghub.models.User;
import com.learner.shoppinghub.repository.RoleRepository;
import com.learner.shoppinghub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	@Autowired
    private RoleRepository rolerepo;

	@GetMapping("/login")
	public String loginPage() {
		// don't let the user to login again if he/she is already logged in
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getName().equalsIgnoreCase("anonymousUser")) {
           System.out.println("anonymous user logging");
			return "login";
		}

		return "index";
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
		List<Role> role=new ArrayList<>();
		user.setPassword(passwordencoder.encode(user.getPassword()));
		role.add(rolerepo.findById(2).get());
		user.setRole(role);
		userservice.SaveUser(user);
		return "redirect:/login";
	}

	@GetMapping("/forgotpassword")
	public String forgotPassword() {

		return "forgotpassword";
	}
	@PostMapping("processforgoutpassword")
	public String savenewPassword(HttpServletRequest req) {
		String oldpassword=(String) req.getParameter("oldpassword");
		String newpassword=(String) req.getParameter("newpassword");
		String confirmnewpassword=(String) req.getParameter("confirmnewpassword");
		String oldpassword1=passwordencoder.encode(oldpassword);
//     	User u=	userrepo.findByPassword(oldpassword1);
   
		return null;
	}
}
