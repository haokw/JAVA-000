package solution;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Solution01 {
    public static void main(String[] args) throws Exception {
        goSolution1();
        goSolution2();
        goSolution3();
        goSolution4();
        goSolution5();
        goSolution6();
        goSolution7();

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

    public static void goSolution4() throws InterruptedException {
        long start = System.currentTimeMillis();

        final SynchronizedMethod sm = new SynchronizedMethod();
        Thread t = new Thread(()->{
            sm.run();
        });
        t.start();
        System.out.printf("result : %s\n", sm.getResult());
        System.out.println("goSolution4 return");

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }

    public static void goSolution5() throws InterruptedException {
        long start = System.currentTimeMillis();

        final SynchronizedMethod sm = new SynchronizedMethod();
        Thread t = new Thread(()->{
            sm.run();
        });
        t.start();
        System.out.printf("result : %s\n", sm.getResult());
        System.out.println("goSolution5 return");

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }

    public static void goSolution6() throws InterruptedException {
        long start = System.currentTimeMillis();

        final SemaphoreMethod sm = new SemaphoreMethod();
        Thread t = new Thread(()->{
            try {
                sm.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.printf("result : %s\n", sm.getResult());
        System.out.println("goSolution6 return");

        long end = System.currentTimeMillis();
        System.out.printf("time consuming : %s ms\n", (end - start));
    }

    public static void goSolution7() throws InterruptedException {
        long start = System.currentTimeMillis();

        final LockConditionMethod lc = new LockConditionMethod();
        Thread t = new Thread(()->{
            try {
                lc.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.printf("result : %s\n", lc.getResult());
        System.out.println("goSolution7 return");

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

class SynchronizedMethod {
    private volatile String result = null;

    synchronized public String run() {
        result = "run thread";
        System.out.println("thread return");
        notifyAll();
        return result;
    }

    synchronized public String getResult() throws InterruptedException {
        while (result == null) {
            wait();
        }
        return result;
    }
}

class SemaphoreMethod {
    final Semaphore s = new Semaphore(1);
    private volatile String result = null;

    public String run() throws InterruptedException {
        s.acquire();
        // Thread.sleep(1000);
        result = "run thread";
        System.out.println("thread return");
        s.release();
        return result;
    }

    public String getResult() throws InterruptedException {
        try {
            s.acquire();
            return result;
        } finally {
            s.release();
        }
    }
}

class LockConditionMethod {
    private Lock lock = new ReentrantLock();
    private Condition con = lock.newCondition();
    private volatile String result = null;

    public String run() throws InterruptedException {
        lock.lock();
        try {
            result = "run thread";
            System.out.println("thread return");
        } finally {
            lock.unlock();
        }
        return result;
    }

    public String getResult() throws InterruptedException {
        lock.lock();
        try {
            while (result == null) {
                con.await();
            }
        } finally {
            lock.unlock();
        }
        return result;
    }
}
