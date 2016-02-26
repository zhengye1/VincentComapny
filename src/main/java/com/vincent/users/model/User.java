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
@Table(name="USER", catalog = "vincentcompany")
public class User {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="username", unique=true, nullable=false, length=50)
	private String username;
	
	@Column(name="password", nullable=false, length=100)
	private String password;
	
	@Column(name="email", nullable=false, length=100)
	private String email;
	
	@Column(name="active", nullable=false)
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	public User(Integer id, String username, String password, String email, 
			boolean active, Set<UserRole> userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.active = active;
		this.userRole = userRole;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
