package com.example.demo.aop.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author admin
 * @date 2019-1-24 11:20
 */
public class LinkManDao {
    private static final Logger logger = LoggerFactory.getLogger(LinkManDao.class);
    public void save(){
        logger.info("save data.........");
    }
}
