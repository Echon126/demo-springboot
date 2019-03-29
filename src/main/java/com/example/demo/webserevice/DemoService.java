package com.example.demo.webserevice;

import javax.jws.WebService;

/**
 * @author admin
 * @date 2019-3-14 15:01
 */
@WebService
public interface DemoService {
    String sayHello(String user);

}
