package org.eforum.front.controller;

import org.eforum.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CaptchaController extends BaseController {
    @Autowired
    private CaptchaService captchaService;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        out.write(captchaService.getCaptchaImage());
        out.flush();
    }
}
