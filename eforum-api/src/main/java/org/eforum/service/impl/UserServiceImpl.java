package org.eforum.service.impl;

import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.repository.UserRepository;
import org.eforum.service.UserService;
import org.eforum.util.StringUtils;
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
		String username = user.getName();
		String email = user.getEmail();
		if (StringUtils.isNullOrEmpty(email)) {
			throw new ServiceException("邮箱必填！");
		}
		if (StringUtils.isNullOrEmpty(username)) {
			throw new ServiceException("用户名必填!");
		}
		User existUser = userRepository.findByEmail(email);
		if (null != existUser) {
			throw new ServiceException("该email已被使用!");
		}
		existUser = userRepository.findByName(username);
		if (null != existUser) {
			throw new ServiceException("该用户名已被使用!");
		}
		return userRepository.save(user);
	}

	@Override
	public User findLoginUser(String username, String password) {
		User user = userRepository.findByNameAndPassword(username, password);
		if (null == user) {
			throw new ServiceException("密码错误或用户名不存在");
		}
		return user;
	}

	@Override
	public void changePassword(User user, String newPassword) {
		if (StringUtils.isNullOrEmpty(newPassword)) {
			throw new ServiceException("新密码不能为空!");
		}
		user = userRepository.findOne(user.getId());
		user.setPassword(newPassword);
		userRepository.save(user);
	}
}