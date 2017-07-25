package org.eforum.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.eforum.constant.Constants;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.service.FileService;
import org.eforum.service.UserService;
import org.eforum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private FileService fileService;

	@Override
	public User findUserById(Long id) {
		return dao.get(User.class, id);
	}

	@Override
	public User findUserByName(String username) {
		String hql = "obj.name = :name";
		return dao.findUniqueByHql(User.class, hql, "name", username);
	}

	@Override
	public User findUserByEmail(String email) {
		String hql = "obj.email = :email";
		return dao.findUniqueByHql(User.class, hql, "email", email);
	}

	@Override
	public User addUser(User user) {
		String username = user.getName();
		String email = user.getEmail();
		if (StringUtils.isNullOrEmpty(email)) {
			throw new ServiceException("邮箱必填！");
		}
		if (StringUtils.isNullOrEmpty(username)) {
			throw new ServiceException("用户名必填!");
		}
		User existUser = findUserByEmail(email);
		if (null != existUser) {
			throw new ServiceException("该email已被使用!");
		}
		existUser = findUserByName(username);

		if (null != existUser) {
			throw new ServiceException("该用户名已被使用!");
		}
		dao.save(user, user);
		return user;
	}

	@Override
	public User findLoginUser(String username, String password) {
		String hql = "obj.name = :name AND obj.password = :password";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", username);
		map.put("password", password);
		User user = dao.findUniqueByHql(User.class, hql, map);
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
		user = dao.get(User.class, user.getId());
		user.setPassword(newPassword);
		dao.save(user);
	}

	@Override
	public void uploadHeadPortrait(User user, String base64Str) {
		String fileExtensionName = base64Str.substring(base64Str.indexOf("/") + 1, base64Str.indexOf(";"));
		String path = fileService.getFileSavePath(Constants.HEAD_PORTRAIT_DIR) + File.separator + user.getId() + "."
				+ fileExtensionName;
		fileService.deleteFileIfExist(path);
		fileService.mkDirIfNoExist(Constants.HEAD_PORTRAIT_DIR);
		base64Str = base64Str.substring(base64Str.indexOf(",") + 1);
		boolean hasSaved = fileService.saveBase64ImageFile(base64Str, path);
		if (hasSaved) {
			user.setHeadPortraitFileName(user.getId() + fileExtensionName);
			dao.save(user);
		} else {
			throw new ServiceException("上传头像失败!");
		}
	}

	@Override
	public void downloadheadPortrait(User user, HttpServletResponse response) {
		File file = fileService.getFile(Constants.HEAD_PORTRAIT_DIR, user.getHeadPortraitFileName());
		try {
			InputStream is = new FileInputStream(file);
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			byte[] data = new byte[1024];
			int length;
			while ((length = is.read(data)) != -1) {
				os.write(data, 0, length);
			}
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}