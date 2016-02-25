package com.vincent.users.service;

import java.util.ArrayList;
import java.util.HashSet;
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
import org.springframework.transaction.annotation.Transactional;

import com.vincent.users.dao.UserDao;
import com.vincent.users.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	// get user from database via hibernate
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		com.vincent.users.model.User user = userDao.findByUsername(username);
		List<GrantedAuthority> authorities =
				buildUserAuthority(user.getUserRole());
		return buildUserAuthentication(user, authorities);
	}

	private UserDetails buildUserAuthentication(com.vincent.users.model.User user, 
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isActive(),
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (UserRole userRole : userRoles){
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}

}
