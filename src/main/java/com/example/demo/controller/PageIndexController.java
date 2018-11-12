package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @date 2018-9-17 13:39
 */
@Controller
public class PageIndexController {


    @RequestMapping(value="/")
    public String getIndexPage(){
        return "index";
    }
}
