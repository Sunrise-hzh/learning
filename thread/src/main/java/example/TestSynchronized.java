package example;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestSynchronized {
    public static void main(String[] args) {
        LockObject lockObj = new LockObject();
        Thread syncThreadA = new SyncThreadA(lockObj);
        Thread syncThreadB = new SyncThreadB(lockObj);
        syncThreadA.start();
        syncThreadB.start();
    }
}

/**
 * 竞争资源对象
 */
class LockObject {
    /**
     * synchronized直接使用在整个方法上，此时锁只能是调用该方法的实例对象
     * @throws InterruptedException
     */
    synchronized public void methodA() throws InterruptedException {
        System.out.println("method A : begin");
        Thread.sleep(3000);
        System.out.println("exception");
        System.out.println(1/0);
        Thread.sleep(3000);
        System.out.println("method A : end");
    }

    /**
     * synchronized代码块中，锁是this时，也指调用该方法的实例对象。
     * 这里synchronized代码块达到的效果和直接用在方法上是一样的
     * @throws InterruptedException
     */
    public void methodB() throws InterruptedException {
        // 其他代码...
        // 同步代码块
        synchronized (this) {
            System.out.println("method b : begin");
            Thread.sleep(3000);
            System.out.println("method b : end");
        }
        // 其他代码...
    }
}

/**
 * 线程A
 */
class SyncThreadA extends Thread {
    public LockObject lockObj;

    public SyncThreadA(LockObject lockObj) {
        this.lockObj = lockObj;
    }
    @Override
    public void run() {
        try {
            lockObj.methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 线程B
 */
class SyncThreadB extends Thread {
    public LockObject lockObj;

    public SyncThreadB(LockObject lockObj) {
        this.lockObj = lockObj;
    }
    @Override
    public void run() {
        try {
            lockObj.methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}