package zm01;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main begin");
        ThreadA threadA = new ThreadA();
        threadA.start();
        ThreadB threadB = new ThreadB();
        threadB.start();
        System.out.println("main join");
        threadA.join();
        threadB.join();
        System.out.println("main end");
    }

    public static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("threadA end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("threadB end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
