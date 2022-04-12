package example;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestSuspendThread {

    public static void main(String[] args) {
        final ThreadSuspend2 thread = new ThreadSuspend2();


    }

//    public static void main(String[] args) {
//        try {
//            ThreadSuspend thread = new ThreadSuspend();
//            thread.start();
//            Thread.sleep(2000);
//            thread.suspend();
//            System.out.println("\nmain end!");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}

class ThreadSuspend2 extends Thread {
    private String username = "1";
    private String password = "11";

    public void setValue(String u, String p) {
        this.username = u;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("停止a线程");
            Thread.currentThread().suspend();
        }
        this.password = p;
    }

    public void printUP() {
        System.out.println(username + " " + password);
    }
}

class ThreadSuspend extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}