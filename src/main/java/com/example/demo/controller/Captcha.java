package com.example.demo.controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019-1-21 16:10
 */
@RestController
public class Captcha {

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/api/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KaptchaExtend kaptchaExtend = new KaptchaExtend();
        kaptchaExtend.captcha(request, response);
    }

    @RequestMapping(value = "/api/code")
    public String getCode(HttpServletRequest request) {
        KaptchaExtend kaptchaExtend = new KaptchaExtend();
        return kaptchaExtend.getGeneratedKey(request);
    }
    @RequestMapping(value = "/api/str")
    public String getTestAop() {
        List<String> al = new ArrayList<String>();
        al.get(0);
       return "wenjie";
    }
}
