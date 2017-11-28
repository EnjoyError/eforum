package org.eforum.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.eforum.constant.Constants;
import org.eforum.entity.Role;
import org.eforum.entity.User;
import org.eforum.entity.relation.UserRoleRelation;
import org.eforum.exception.ServiceException;
import org.eforum.service.FileService;
import org.eforum.service.UserService;
import org.eforum.util.FileUtil;
import org.eforum.util.StringUtils;
import org.eforum.vo.UserVo;
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
    public User saveUser(User user) {
        String username = user.getName();
        String email = user.getEmail();
        if (StringUtils.isNullOrEmpty(email)) {
            throw new ServiceException("邮箱必填！");
        }
        if (StringUtils.isNullOrEmpty(username)) {
            throw new ServiceException("用户名必填!");
        }
        User existUser = findUserByEmail(email);
        if (null != existUser && (user.isNew() || !user.getId().equals(existUser.getId()))) {
            throw new ServiceException("该email已被使用!");
        }
        existUser = findUserByName(username);

        if (null != existUser && (user.isNew() || !user.getId().equals(existUser.getId()))) {
            throw new ServiceException("该用户名已被使用!");
        }
        dao.save(user, user);
        return user;
    }

    @Override
    public User createUser(User user) {
        validateUsername(user.getName());
        validatePassword(user.getPassword());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        saveUser(user);
        return user;
    }

    /**
     * 校验用户名
     *
     * @param username
     */
    private void validateUsername(String username) {
        if (StringUtils.isNullOrEmpty(username)) {
            throw new ServiceException("用户名不能是空的!");
        }
        if (username.length() > 12 || username.length() < 4) {
            throw new ServiceException("用户名长度只能在4到12之间！");
        }
    }


    /**
     * 校验密码
     *
     * @param password
     */
    private void validatePassword(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ServiceException("密码不能为空!");
        }
        if (password.length() > 16 || password.length() < 8) {
            throw new ServiceException("密码长度只能在8到16之间！");
        }
    }

    @Override
    public void changePassword(UserVo userVo) {
        User user = dao.get(User.class, userVo.getId());
        userVo.setOldPassword(DigestUtils.md5Hex(userVo.getOldPassword()));
        String pageOldPassword = userVo.getOldPassword();
        String oldPassword = user.getPassword();
        if (!oldPassword.equals(pageOldPassword)) {
            throw new ServiceException("旧密码输入不正确!");
        }
        String newPassword = userVo.getPassword();
        validatePassword(newPassword);
        newPassword = DigestUtils.md5Hex(newPassword);
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
            user.setHeadPortraitFileName(user.getId() + "." + fileExtensionName);
            dao.save(user);
        } else {
            throw new ServiceException("上传头像失败!");
        }
    }

    @Override
    public void downloadheadPortrait(User user, HttpServletResponse response) {
        String headPortraitFileName = user.getHeadPortraitFileName();
        if (StringUtils.isNullOrEmpty(headPortraitFileName)) {
            headPortraitFileName = Constants.ANONYMOUS_HEAD_PORTRAIT_FILE_NAME;
        }
        File file = fileService.getFile(Constants.HEAD_PORTRAIT_DIR, headPortraitFileName);
        FileUtil.writeFileToResponse(file, response);
    }

    public Boolean userIsJy(User user) {
        if(null == user.getBeShutup() || !user.getBeShutup()){
            return false;
        } else {
            return true;
        }
    }

    public void shutupUser(Long userId) {
        User user = dao.get(User.class, userId);
        if (userIsJy(user)) {
            return;
        }
        user.setBeShutup(true);
        dao.save(user);
    }

    public void ReleaseShutup(Long userId) {
        User user = dao.get(User.class, userId);
        if (!userIsJy(user)) {
            return;
        }
        user.setBeShutup(false);
        dao.save(user);
    }
}