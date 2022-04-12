package zm01;

/**
 * @author huangzihua
 * @date 2021-03-31
 */
public class TestThreadLocal {
    public static ThreadLocal<String> localVariable = new ThreadLocal<>();
    public static String testStr;

    public static String getTestStr() {
        return testStr;
    }

    public static void setTestStr(String testStr) {
        TestThreadLocal.testStr = testStr;
    }

    public static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
    }

    public static void print2(String str) {
        System.out.println(str + ":" + testStr);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            localVariable.set("t1 local variable，t1设置的threadlocal值");
            print("t1");
            System.out.println("t1 remove after :" + localVariable.get());

            setTestStr("t1设置了testStr值");
            print2("t1");
        });

        Thread t2 = new Thread(() -> {
            localVariable.set("t2 local variable，t2设置的threadlocal值");
            print("t2");
            System.out.println("t2 remove after :" + localVariable.get());

            setTestStr("t2设置了testStr值");
            print2("t2");
        });

        t1.start();
        t2.start();
    }
}
