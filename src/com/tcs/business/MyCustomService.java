package com.tcs.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcs.dao.UserDaoInterface;
import com.tcs.model.UserRole;

@Service("customService")
public class MyCustomService implements UserDetailsService {
	
	@Autowired
	private UserDaoInterface dao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		com.tcs.model.User user = dao.findUserByUsername(username);
		System.out.println("In Service");
		System.out.println("User is : " + user);
		System.out.println("Before getRoles");
		Set<UserRole> userRoles = user.getUserRoles();
		System.out.println("Before buildUserAuthority");
		List<GrantedAuthority> authorityRoles = buildUserAuthority(userRoles);
		System.out.println("Before buildUserForAuthentication");
		UserDetails userDetails = buildUserForAuthentication(user, authorityRoles);
		System.out.println("After buildUserForAuthentication");
		return userDetails;
	}
	
	public List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles)
	{
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		Iterator<UserRole> itr = userRoles.iterator();
		while(itr.hasNext())
		{
			UserRole userRole = itr.next();
			String role = userRole.getRole();
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		List<GrantedAuthority> authorityRoles = new ArrayList<GrantedAuthority>(roles);
		
		return authorityRoles;
	}
	
	public User buildUserForAuthentication(com.tcs.model.User user, List<GrantedAuthority> authorities)
	{
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

}
