package other;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentHashMap;

public class Test1 {

    public static ConcurrentHashMap<String,String> map
            = new ConcurrentHashMap<>();

    private Test1(){}

    private static class THolder{
        private static Test1 instance = new Test1();
    }
    public static Test1 getInstance(){
        return THolder.instance;
    }


    public static void main(String[] args) throws InterruptedException {

        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(Test1.getInstance().toString());
        map.put("AAA","aaa");
        System.out.println(Test1.getInstance().map.get("AAA"));

        Thread.sleep(1000 * 90);

        System.out.println("enddddd");
    }

}
