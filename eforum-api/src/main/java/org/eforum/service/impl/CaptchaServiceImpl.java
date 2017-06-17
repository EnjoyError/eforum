package org.eforum.service.impl;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eforum.exception.ServiceException;
import org.eforum.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private ImageCaptchaService imageCaptchaService;

    @Override
    public byte[] getCaptchaImage() throws ServiceException {
        Subject subject = SecurityUtils.getSubject();
        String captchaId = subject.getSession().getId().toString();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            BufferedImage bufferedImage = imageCaptchaService.getImageChallengeForID(captchaId);
            ImageIO.write(bufferedImage, "jpeg", out);
        } catch (Exception e) {
            throw new ServiceException("转换图片格式出错", e);
        }
        return out.toByteArray();
    }

    @Override
    public boolean validate(String captcha) throws ServiceException {
        Subject subject = SecurityUtils.getSubject();
        String captchaId = subject.getSession().getId().toString();
        boolean result = false;
        try {
            result = imageCaptchaService.validateResponseForID(captchaId, captcha);
        } catch (CaptchaServiceException e) {
            throw new ServiceException("校验图片验证码出错", e);
        }
        return result;
    }
}
