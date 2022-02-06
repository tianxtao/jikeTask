/**
 * @Description :
 * @Date : 2022-02-06
 */

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private static final Object lock = new Object();
    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //int result = asyncCount1();
        //int result = asyncCount2();
        //int result = asyncCount3();
        //int result = asyncCount4();
        //int result = asyncCount5();
        //int result = asyncCount6();
        //int result = asyncCount7();
        //int result = asyncCount8();
        //int result = asyncCount9();
        int result = asyncCount10();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+ result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public static int asyncCount1(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final int[] tmp = {0};
        try {
            new Thread(() -> {
                try {
                    Thread.currentThread().sleep(1000);
                    tmp[0] = sum();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tmp[0];
    }

    public static int asyncCount2(){
        final int[] tmp = {0};
        Thread thread = new Thread(() -> {
            try {
                tmp[0] = sum();
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tmp[0];
    }

    public static int asyncCount3(){
        CountThread3 thread = new CountThread3();
        thread.start();
        while(!thread.isFinish()) {
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return thread.getResult();
    }

    public static int asyncCount4(){
        final int[] tmp = {0};
        new Thread(() -> {
            try {
                synchronized (lock){
                    tmp[0] = sum();
                    Thread.currentThread().sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){}

        return tmp[0];
    }

    public static int asyncCount5(){
        final int[] tmp = {0};
        new Thread(() -> {
            try {
                synchronized (lock){
                    tmp[0] = sum();
                    Thread.currentThread().sleep(1000);
                    //lock.notifyAll();
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return tmp[0];
    }

    public static int asyncCount6(){
        CountThread6 countThread6 = new CountThread6();
        countThread6.setMainThread(Thread.currentThread());
        countThread6.start();
        LockSupport.park();
        return countThread6.getResult();
    }

    public static int asyncCount7(){
        final int[] tmp = {0};
        new Thread(() -> {
            try {
                Thread.currentThread().sleep(1000);
                tmp[0] = sum();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        return tmp[0];
    }

    public static int asyncCount8(){
        Semaphore semaphore = new Semaphore(0);
        final int[] tmp = {0};
        new Thread(() -> {
            try {
                Thread.currentThread().sleep(1000);
                tmp[0] = sum();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    public static int asyncCount9(){
        final int[] tmp = {0};
        Thread thread = new Thread(() -> {
            try {
                tmp[0] = sum();
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        do{
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (thread.isAlive());

        return tmp[0];
    }

    public static int asyncCount10(){
        int result = 0;
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        };
        Future<Integer> future = executorService.submit(task);
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return result;
    }


    static class CountThread3 extends Thread {

        private Integer result;
        private volatile boolean finish = false;

        public boolean isFinish() {
            return finish;
        }

        public Integer getResult() {
            return result;
        }

        @Override
        public void run() {
            result = sum();
            finish = true;
        }
    }


    static class CountThread6 extends Thread {

        private Thread mainThread;

        private Integer result;

        public Integer getResult() {
            return result;
        }

        public Thread getMainThread() {
            return mainThread;
        }

        public void setMainThread(Thread mainThread) {
            this.mainThread = mainThread;
        }

        @Override
        public void run() {
            result = sum();
            LockSupport.unpark(getMainThread());
        }
    }
}
