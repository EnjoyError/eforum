package org.eforum.service;

import org.eforum.entity.User;

public interface UserService {
	User getUser(Long id);
	User saveUser(User user);
}