package com.vincent.users.dao;

import com.vincent.users.model.User;

public interface UserDao {
	User findByUsername(String username);
}
