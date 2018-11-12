package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author admin
 * @date 2018-10-29 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestFullTest {
    private static final String url = "http://localhost:8090/info";

    @Test
    public void TestRestFull() {
        RestTemplate restTemplate = new RestTemplate();
        String info = restTemplate.getForObject(url, String.class);
        System.out.println("RestTemplate 请求结果 " + info);
    }
}
