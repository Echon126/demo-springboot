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
    public ModelAndView info() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }
}
