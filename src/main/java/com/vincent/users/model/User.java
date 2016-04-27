package com.vincent.users.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users", catalog = "vincentcompany")
public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private boolean active;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	public User(Integer id, String username, String password, String email, 
			boolean active, Set<UserRole> userRole) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.active = active;
		this.userRole = userRole;
	}
	
	@Id
	@Column(name="ID")
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="username", unique=true, nullable=false, length=50)
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password", nullable=false, length=100)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="email", nullable=false, length=100)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="active", nullable=false)
	public boolean isActive() {
		return this.active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
