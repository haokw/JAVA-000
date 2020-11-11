package solution;

import java.util.concurrent.atomic.AtomicReference;


public class Solution01 {
    public static void main(String[] args) throws Exception {
        goSolution1();
        goSolution2();
        goSolution3();

        System.out.println("main return");
    }

    public static void goSolution1() throws Exception {
        long start = System.currentTimeMillis();

        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        t.join();
        System.out.printf("result : %s\n", r.getResult());
        System.out.println("goSolution1 return");
        // System.out.printf("%s return\n", Thread.currentThread().getStackTrace()[2].getMethodName());
        // System.out.printf("%s return\n", (new Exception()).getStackTrace()[1].getMethodName());

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }

    public static void goSolution2() throws Exception {
        long start = System.currentTimeMillis();

        MyMethod m = new MyMethod();
        Thread t = new Thread(()->{
            m.run();
        });
        t.start();
        t.join();
        System.out.printf("result : %s\n", m.getResult());
        System.out.println("goSolution2 return");

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }

    public static void goSolution3() throws Exception {
        long start = System.currentTimeMillis();

        AtomicReference a = new AtomicReference();
        MyMethod m = new MyMethod();
        Thread t = new Thread(()->{
            a.set(m.run());
        });
        t.start();
        t.join();
        System.out.printf("result : %s\n", a.get());
        System.out.println("goSolution3 return");

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }
}

class MyMethod {
    private String result;

    public String run() {
        result = "run thread";
        System.out.println("thread return");
        return result;
    }

    public String getResult() {
        return result;
    }
}

class MyRunnable implements Runnable {
    private String result;

    @Override
    public void run() {
        result = "run runnable";
        System.out.println("runnable return");
    }

    public String getResult() {
        return result;
    }
}
