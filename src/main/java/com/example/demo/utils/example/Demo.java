package com.example.demo.utils.example;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.Dao;
import com.example.demo.utils.entity.Student;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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


    private final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //比较两个List中的元素是否完全相同
    public static void ListUtils() {
        long startTime = System.currentTimeMillis();
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

        System.out.println(dateformat.format(new Date(startTime)));
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

    //根据UUID获取唯一订单号
    public static String getOrredingIdUUID() {
        int machineId = new Random(10).nextInt(8) + 1;
        log.info(machineId);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }

        return machineId + String.format("%015d", hashCodeV);
    }

    // 遍历Map集合的常用方法
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

    //对象中数据输出方式，通过这种凡是可以避免++引起的内存溢出，
    public String ListUtil() {
        return new ToStringBuilder(this).append("att1", "att1")
                .append("att2", "att2")
                .append("att3", "att3")
                .append("super", super.toString()).toString();
    }

    //List中对象去重
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

    //有返回值的线程调用
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


    //unicode 转化为中文和字符串转化为unicode
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

    //求最长字符串的程序
    public static int getStrLength(List<String> list) {
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
        return lengthMax;
    }


    public static void main0001(String[] args) throws InterruptedException {
        System.out.println("使用线程池运行 Runable 任务");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<AccumRunnable> tasks = new ArrayList<AccumRunnable>(10);
        for (int i = 0; i < 10; i++) {
            AccumRunnable task = new AccumRunnable(i * 10 + 1, (i + 1) * 10);
            tasks.add(task);

            threadPool.execute(task);// 让线程池执行任务 task
        }
        threadPool.shutdown(); // 向线程池发送关闭的指令，等到已经提交的任务都执行完毕之后，线程池会关闭
        threadPool.awaitTermination(1, TimeUnit.HOURS); // 等待线程池关闭，等待的最大时间为 1 小时

        int total = 0;
        for (AccumRunnable task : tasks) {
            total += task.getResult(); // 调用在 AccumRunnable 定义的 getResult 方法获得返回的结果
        }

        System.out.println("Total: " + total);

        CountDownLatch();
    }

    //TODO CountDownLatch 用来控制一个线程等待多个线程。
    //TODO 维护一个计数器，每次调用countDown时计数器的值减1，减到0的时候，那些因为调用 await()方法而在等待的线程将被唤醒。

    public static void CountDownLatch() throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("run.....");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }


    public static void main00002(String[] args) throws UnsupportedEncodingException {
        /*String str = "hello world";
        System.out.println(str.length());
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println(bytes);

        String[] str001 = new String[]{"dddd", "sdfsdf"};
        System.out.println(str001);*/
       /* byte[] newBytes = new byte[2];
        System.arraycopy(bytes,0,newBytes,0,2);
        System.out.println(newBytes);
        System.out.println(newBytes.length);
        System.out.println(new String(newBytes));
        System.out.println("---------------------------------");
        char[] c = str.toCharArray();
        for(int i=0;i<c.length;i++){
            byte b = (byte) c[i];
            System.out.println(b);
           // System.out.println(Integer.valueOf(c[i]));

        }
        System.out.println(Integer.valueOf('h'));
        System.out.println(new String(bytes,"ascii"));*/

        char a = 'a';
        byte b = 104;
        int c = b;
        float d = 10;
        float f = a;
        float g = b;
        float m = c;
        double sds = 100;

        System.out.println(c);
    }


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    private static final int RUNNING = -1 << COUNT_BITS;


    public static void main0003(String[] args) {
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        System.out.println(ctl.get());
        System.out.println(CAPACITY);
        System.out.println(workerCountOf(CAPACITY));

        Retry();

    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static void Retry() {
        //retry:
        for (int i = 0; i < 10; i++) {
            retry:
            while (i == 5) {
                continue retry;
            }
            System.out.print(i + " ");
        }


    }


    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student(001, "张三", 20));
        list.add(new Student(0002, "lisi", 30));
        list.add(null);

        //集合防空
        List<Student> newList = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        newList.forEach(x -> {
            System.out.println(x);
        });

        //List convert Map
        Map<Integer, Student> map = list.stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(Student::getId, s -> s));
        System.out.println(JSON.toJSON(map));
        System.out.println(map.get(1));

    }


    static final class AccumRunnable implements Runnable {
        private final int begin;
        private final int end;

        private int result;

        public AccumRunnable(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public void run() {
            result = 0;
            try {
                for (int i = begin; i <= end; i++) {
                    result += i;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("(%s) - 运行结束，结果为%d\n", Thread.currentThread().getName(), result);
        }

        public int getResult() {
            return result;
        }
    }

}








