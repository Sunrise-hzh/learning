package example;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestInterruptThread {
    public static void main(String[] args) {
//        ThreadForInterrupt thread = new ThreadForInterrupt();
//        thread.start();
//        System.out.println(thread.getName() + "(main_t1): " + thread.isInterrupted());
//        System.out.println("中断");
//        thread.interrupt();
//
//        System.out.println(thread.getName() + "(main_t2): " + thread.isInterrupted());
//        System.out.println(Thread.currentThread().getName() + ": " + Thread.interrupted());

        // thread.interrupt();该方法不是立即中断线程，而是把线程的中断状态设置为true
        // thread.isInterrupted();该方法是获取线程自己的中断状态，不会重置状态为false
        // Thread.interrupted();该方法是获取执行当前代码段的线程的中断状态，会重置为false

        /* 测试sleep()过程中中断 */
        InterThread2 thread2 = new InterThread2();
        thread2.start();
        thread2.interrupt();

        // sleep()过程中线程被中断的话，该线程会在sleep()位置抛出InterruptedException异常。

        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000000; i++) {
                        // 此处通过isInterrupted()判断线程是否被中断，是则抛出异常
                        if (this.isInterrupted()) {
                            throw new InterruptedException();
                        }
                    }
                } catch (InterruptedException e) {
                    // 处理异常
                    e.printStackTrace();
                }
            }
        }.start();




    }
}

class ThreadForInterrupt extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "(thread_t1): " + Thread.interrupted());
        for (int i = 0; i<2000000; i++) {
            if (i % 100000 == 0) {
                System.out.println("i = " + i);
            }

        }
        System.out.println(Thread.currentThread().getName() + "（thread_t2）: " + Thread.interrupted());
    }
}

class InterThread2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("sleep中被中断");
            e.printStackTrace();
        }

    }
}
