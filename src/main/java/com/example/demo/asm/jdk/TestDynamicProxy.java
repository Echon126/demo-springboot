package com.example.demo.asm.jdk;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * @author admin
 * @date 2018-10-16 9:59
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        //设置这个值，可以把生成的代理类输出来
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);

        //要代理的真是对象
        Service service = new ServiceImpl();
        //要代理那个真实对象，就将给对象传递进去，最后通过该真实对象来调用其方法
        InvocationHandler handler = new DynamicProxy(service);

        try {
            //添加以下的几段代码, 就可以将代理生成的字节码保存起来
            Field field = System.class.getDeclaredField("props");
            field.setAccessible(true);
            Properties props = (Properties) field.get(null);
            props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
            Package pkg = TestDynamicProxy.class.getPackage();
            if (pkg != null) {
                String packagePath = pkg.getName().replace(".", File.pathSeparator);
                new File(packagePath).mkdirs();
            }

            Service serviceProxy = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),handler);

            System.out.println("ClassName: "+ serviceProxy.getClass().getName());

            serviceProxy.help();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
