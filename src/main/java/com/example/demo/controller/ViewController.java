package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author admin
 * @date 2019-2-13 10:28
 */

@Controller
public class ViewController {

    @GetMapping("/info")
    public void info(String flag) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                asynchronous(flag);
            }
        });
        t.setName(flag);
        t.start();
    }

    public void asynchronous(String flag) {
        for (int i = 0; i < 10; i++) {
            System.out.println(flag + "------ " + i);
        }
    }
}
