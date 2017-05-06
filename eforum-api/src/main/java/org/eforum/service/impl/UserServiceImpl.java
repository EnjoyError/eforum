package org.eforum.service.impl;

import org.eforum.entity.User;
import org.eforum.repository.UserRepository;
import org.eforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findUserByName(String username) {
		return userRepository.findByName(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findLoginUser(String username, String password) {
		return userRepository.findByNameAndPassword(username, password);
	}
}