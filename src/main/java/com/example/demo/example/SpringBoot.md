****SpringBoot自动配置源码分析****

1.自动配置主要是通过@EnableAutoConfiguration、@Conditional、@EnableConfigurationProperties
或者@ConfigurationProperties等注解来进行自动配置。

@EnableAutoConfiguration 开启自动配置，主要作用就是调用 Spring-Core 包里的 loadFactoryNames()，
将 autoconfig 包里的已经写好的自动配置加载进来。

@Conditional 条件注解，通过判断类路径下有没有相应配置的 jar 包来确定是否加载和自动配置这个类。

EnableConfigurationProperties 的作用就是，给自动配置提供具体的配置参数，只需要写在 application.properties 中，
就可以通过映射写入配置类的 POJO 属性中。


https://www.cnblogs.com/leihuazhe/p/7743479.html

############################拦截器和过滤器的区别##################################
第一 概念和作用的不同
1.拦截器是基于java反射机制实现，过滤器是基于函数回调
2.拦截器不依赖是servlet容器，而过滤器依赖servlet容器
3.拦截器只能对aciton器作用，过滤器几乎可以对所有的请求起作用
4.拦截器可以访问action上下文、值栈里的值，过滤器不能访问
5.在action的生命周期里，拦截器可以被多次调用，而过滤器只能在容器初始化是调用一次
6.拦截器可以获取IOC容器中的bean，而过滤器不行，这一点很重要，在拦截器里注入一个service，可以调用业务逻辑

第二 触发不同
1.触发时机不同
过滤器是在请求进入容器后，但是请求进入servlet之前进行处理的，请求结束返回也是，是在servlet处理完后，返回给前端之前。

2.触发时间和地点不一样
过滤器是在请求进入容器后，但请求进入servlet之前进行预处理的。请求结束返回也是，是在servlet处理完后，返回给前端之前。

过滤器包裹住servlet，servlet包裹住拦截器。
















