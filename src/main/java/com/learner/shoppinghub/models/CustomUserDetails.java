package com.learner.shoppinghub.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetails {
	@Autowired
	private User u;

	CustomUserDetails() {

	}

////set the user info after fetching from UserDetailservice
	public CustomUserDetails(User u) {

		this.u = u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authoritylist = new ArrayList<>();
//		get the list of roles and add each to the grantedAuthority
		List<Role> authority = u.getRole();
		for (Role r : authority) {
//		convert the authority in the form of implementation of GrantedAuthority  i.e simple granted authority and it take the rolename as the argument 
			authoritylist.add(new SimpleGrantedAuthority(r.getRole_name()));
		}
		return authoritylist;
	}

	@Override
	public String getUsername() {
		return u.getEmail();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
