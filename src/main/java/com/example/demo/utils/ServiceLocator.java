package com.example.demo.utils;

import com.wen.excel.Parser;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author admin
 * @date 2019-2-19 15:13
 */
@Component
public class ServiceLocator implements ApplicationContextAware {
    private Map<String, Parser> map;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Parser> map = applicationContext.getBeansOfType(Parser.class);
        ServiceLoader<Parser> loader = ServiceLoader.load(Parser.class);
        for (Parser loaderObject : loader) {
            System.out.println(" xxxxxxx"+ loaderObject);
        }
    }


    public Map<String, Parser> getMap() {
        return map;
    }

}
