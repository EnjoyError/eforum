package org.eforum.service;

import org.eforum.entity.User;

public interface UserService {
	User addUser(User user);
	User findUserById(Long id);
	User findUserByName(String username);
	User findUserByEmail(String email);
	User findLoginUser(String username, String password);
}