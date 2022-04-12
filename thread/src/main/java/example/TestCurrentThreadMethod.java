package example;

/**
 * @author huangzihua
 * @date 2021-03-29
 */
public class TestCurrentThreadMethod {
    public static void main(String[] args) {
        System.out.println("main:" + Thread.currentThread().getName());

        Thread1 thread1 = new Thread1();
        new Thread(thread1).start();
//        thread1.start();
    }
}

class Thread1 extends Thread {
    public Thread1() {
        System.out.println("线程构造方法:" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("线程run方法:" + Thread.currentThread().getName());
        System.out.println("线程run方法:" + this.getName());
        System.out.println("线程run方法:" + this.getId());
    }
}