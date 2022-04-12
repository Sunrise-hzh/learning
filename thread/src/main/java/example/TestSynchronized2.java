package example;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestSynchronized2 {
    public static void main(String[] args) {

        SyncThreadC threadC = new SyncThreadC();
        SyncThreadD threadD = new SyncThreadD();
        threadC.start();
        threadD.start();
    }
}

class StaticObject {
    synchronized public static void test() {
        System.out.println(Thread.currentThread().getName() + " begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void test2() {
        synchronized(StaticObject.class) {
            System.out.println(Thread.currentThread().getName() + " begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }
}

class SyncThreadC extends Thread {
    @Override
    public void run() {
        StaticObject.test();
    }
}


class SyncThreadD extends Thread {
    @Override
    public void run() {
        StaticObject.test2();
    }
}
