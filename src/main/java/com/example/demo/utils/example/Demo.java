package com.example.demo.utils.example;

import com.example.demo.aop.Dao;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author admin
 * @date 2019-2-20 10:07
 */
public class Demo {
    private final static Logger log = Logger.getLogger(Demo.class);
    private String[] configLocations;

    public String[] getConfigLocations() {
        return configLocations;
    }

    public void setConfigLocations(String... locations) {
        if (locations != null) {
            this.configLocations = new String[locations.length];
            for (int i = 0; i < locations.length; i++) {
                this.configLocations[i] = locations[i];
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
    public static void main11(String[] args) {
        log.setLevel(Level.WARN);

        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }


    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void ListUtils() {
        List<String> list001 = new ArrayList<>();
        List<String> list002 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list001.add("000" + i);
        }

        for (int i = 0; i < 100; i++) {
            list002.add("000" + i);
        }
        int count = 0;
        List<String> comList1;
        List<String> comList2;
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; j++) {
            for (int i = 0; i < list001.size(); i++) {
                if (i + 10 <= list001.size()) {
                    count++;
                    comList1 = list001.subList(i, i + 10);
                    comList2 = list002.subList(i, i + 10);
                    eqList(comList1, comList2);
                    //System.out.println("比较结果----" + eqList(comList1, comList2));

                }


            }
        }
        System.out.println("校验一千封试卷所需时间" + (System.currentTimeMillis() - start));
        System.out.println("比较次数 " + count);


    }


    /**
     * 比较两个List 是否完全相同，包括类型和在list中的排序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean eqList(List<String> list1, List<String> list2) {
        boolean bl = true;
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if ((list1.get(i)).equals(list2.get(i))) {
                continue;
            } else {
                bl = false;
                break;
            }
        }

        return bl;
    }


    public static String getOrredingIdUUID() {
        int machineId = new Random(10).nextInt(8) + 1;
        log.info(machineId);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }

        return machineId + String.format("%015d", hashCodeV);
    }

    public void IteratorMap(Map<String, String> map) {
        //TODO 001
        map.forEach((key, value) -> {
            System.out.println("key--" + key + "----value----" + value);
        });

        //TODO 002
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key----" + entry.getKey() + "------value-----" + entry.getValue());
        }

        //TODO 003
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key----" + entry.getKey() + "------value-----" + entry.getValue());
        }
    }


    public String ListUtil() {
        return new ToStringBuilder(this).append("att1", "att1")
                .append("att2", "att2")
                .append("att3", "att3")
                .append("super", super.toString()).toString();
    }

    public static void main001(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("0001");
            add("0002");
            add("0003");
            add("0001");
        }};


        list.forEach(x -> {
            System.out.println(x);
        });
        System.out.println("-------------------");
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        List<String> newList = new ArrayList<String>();
        newList.addAll(set);

        newList.forEach(x -> {
            System.out.println("去重 " + x);
        });

    }

    public static boolean getInfo() throws InterruptedException, ExecutionException, TimeoutException {
        Boolean result = false;
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return currentInfo();
            }
        };

        Future<Boolean> future = executorService.submit(call);
        result = future.get(120, TimeUnit.SECONDS);
        return result;

    }

    public static boolean currentInfo() {
        return true;
    }


    public static String uniCode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] sources = str.toCharArray();
        String unicode = null;

        for (int i = 0; i < sources.length; i++) {
            unicode = Integer.toHexString(sources[i]);

            if (unicode.length() <= 2) {
                unicode = "00" + unicode;
            }
            sb.append("\\u" + unicode);

        }
        return sb.toString();
    }


    public static void main(String[] args) {
      /*  String actors = "埃兹拉·米勒|蒂尔达·斯文顿|约翰·C·赖利|Siobhan Fallon|Ashley Gerasimovich";
       String s =StringEscapeUtils.unescapeJava("\\u57c3\\u5179\\u62c9\\u00b7\\u7c73\\u52d2\\u007c\\u8482\\u5c14\\u8fbe\\u00b7\\u65af\\u6587\\u987f\\u007c\\u7ea6\\u7ff0\\u00b7\\u0043\\u00b7\\u8d56\\u5229\\u007c\\u0053\\u0069\\u006f\\u0062\\u0068\\u0061\\u006e\\u0020\\u0046\\u0061\\u006c\\u006c\\u006f\\u006e\\u007c\\u0041\\u0073\\u0068\\u006c\\u0065\\u0079\\u0020\\u0047\\u0065\\u0072\\u0061\\u0073\\u0069\\u006d\\u006f\\u0076\\u0069\\u0063\\u0068");

       System.out.println(s);*/


        //求最长字符串的程序

        List<String> list = Arrays.asList("Aaa", "Abbasdasda", "Axcvwewrw");

        //0001 方法1
        OptionalInt length = list.stream().filter(s -> s.startsWith("A")).mapToInt(String::length).max();
        System.out.println(length.getAsInt());

        //0002 方法二
        int lengthMax = 0;
        for (String str : list) {
            if (str.startsWith("A")) {
                int len = str.length();
                lengthMax = Math.max(len, lengthMax);
            }
        }
        System.out.println(lengthMax);

    }


}


