package com.example.demo.controller;

import com.example.demo.annotationeaxmple.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @date 2018-9-17 13:39
 */
@Controller
public class PageIndexController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value="/")
    public String getIndexPage(){
        contentService.doSomething();
        return "index";
    }
}
