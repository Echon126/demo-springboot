package com.example.demo.webserevice;

import java.util.Date;

/**
 * @author admin
 * @date 2019-3-14 15:11
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String user) {
        return user + ":hello" + "(" + new Date() + ")";
    }
}
