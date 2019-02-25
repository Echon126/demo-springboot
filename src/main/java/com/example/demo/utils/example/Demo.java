package com.example.demo.utils.example;

import com.example.demo.aop.Dao;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author admin
 * @date 2019-2-20 10:07
 */
public class Demo {
    private final static Logger log =Logger.getLogger(Demo.class);
    private String [] configLocations;

    public String[] getConfigLocations() {
        return configLocations;
    }

    public void setConfigLocations(String... locations) {
        if(locations != null){
            this.configLocations = new String[locations.length];
            for(int i=0;i<locations.length;i++){
                this.configLocations[i]=locations[i];
            }
        }
        this.configLocations = configLocations;
    }

    public static void main1(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/aop.xml");

        Dao dao = (Dao) ac.getBean("daoImpl");
        dao.select();
    }

   // ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF。
  /* ALL	各级包括自定义级别
    DEBUG	指定细粒度信息事件是最有用的应用程序调试
    ERROR	错误事件可能仍然允许应用程序继续运行
    FATAL	指定非常严重的错误事件，这可能导致应用程序中止
    INFO	指定能够突出在粗粒度级别的应用程序运行情况的信息的消息
    OFF	    这是最高等级，为了关闭日志记录
    TRACE	指定细粒度比DEBUG更低的信息事件
    WARN	指定具有潜在危害的情况*/
    public static void main(String[] args) {
        log.setLevel(Level.WARN);

        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }
}
