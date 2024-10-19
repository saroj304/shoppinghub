package com.learner.shoppinghub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;




@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, unique = true)
	@Size(min = 2, max = 4, message = "role name cannot be empty")
	private String role_name;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "role")
	private List<User> user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}

}
