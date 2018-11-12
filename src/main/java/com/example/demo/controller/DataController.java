package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2018-9-25 15:33
 */

@RestController
public class DataController {
    private final static Logger logger = LoggerFactory.getLogger(DataController.class);
    @RequestMapping(value="/info")
    public String GetInfo(){
        logger.info("sssssss");
        return "String";
    }

}
