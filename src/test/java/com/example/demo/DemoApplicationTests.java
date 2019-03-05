package com.example.demo;


import com.example.demo.aop.Dao;
import com.example.demo.aop.proxy.LinkManDao;
import com.example.demo.configuration.AppPropertySource;
import com.example.demo.tx.service.TxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void TestAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/aop.xml");

        Dao dao = (Dao) ac.getBean("daoImpl");
        dao.select();
    }

    @Autowired
    TxService txService;


    @Test
    public void testTx() throws Exception {
        txService.createData();
    }

    @Autowired
    RestTemplate restTemplate;
    private static final int count = 100;
    private CountDownLatch countDownLatch = new CountDownLatch(count);
    private static final String URL_REQUEST = "http://127.0.0.1:8090/api/tx";


    @Test
    public void TestThread() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "准备....");
                    restTemplate.getForObject(URL_REQUEST, String.class);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("当前线程:" + Thread.currentThread().getName() + "已就绪...., countdown减一.");
                        countDownLatch.countDown();
                    }
                }
            });
        }
        System.out.println("主线程:" + Thread.currentThread().getName() + "等待其他线程，同时到达某一状态，即countdown为0....");
        countDownLatch.await();
        System.out.println("主线程:" + Thread.currentThread().getName() + "等待结束, 开始运行...");

        executorService.shutdown();

    }


    @Test
    public void TestProxy() {
        final LinkManDao linkManDao = new LinkManDao();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(linkManDao.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("记录日志");
                Object result = method.invoke(linkManDao, objects);
                return result;
            }
        });

        // 创建代理对象
        LinkManDao proxy = (LinkManDao) enhancer.create();
        proxy.save();
    }

    @Autowired
    AppPropertySource appPropertySource;

    @Test
    public void testPropertySources() {
        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(appPropertySource.propertyConfiguration().getAppliedPropertySources());
        String ss = propertyResolver.getProperty("interface_head");
        System.out.println(ss);

    }
}
