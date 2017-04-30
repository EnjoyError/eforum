package org.eforum.service;

import org.eforum.entity.User;

public interface UserService {
	User saveUser(User user);
	User findUserById(Long id);
	User findUserByName(String username);
	User findLoginUser(String username, String password);
}