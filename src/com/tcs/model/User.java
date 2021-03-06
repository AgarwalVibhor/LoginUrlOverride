package com.tcs.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users_sample", catalog = "test")
public class User {
	
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRoles;
	
	public User() {
		super();
	}
	
	public User(String username, String password, boolean enabled,
			Set<UserRole> userRoles) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
	}
	
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 64)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password", nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Fetch(FetchMode.SELECT)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", enabled=" + enabled + ", userRoles=" + userRoles + "]";
	}
	
	
	
	

}
