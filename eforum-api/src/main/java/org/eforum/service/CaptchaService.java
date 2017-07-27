package org.eforum.service;

import org.eforum.exception.ServiceException;

public interface CaptchaService {
    /**
     * @return 验证码图片二进制数组
     */
    byte[] getCaptchaImage() throws ServiceException;

    /**
     * @param captcha 验证码
     * @return 是否验证通过
     */
    boolean validate(String captcha) throws ServiceException;
}
