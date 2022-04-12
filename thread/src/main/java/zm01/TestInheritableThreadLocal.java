package zm01;

/**
 * @author huangzihua
 * @date 2021-03-31
 */
public class TestInheritableThreadLocal {

    public static void main(String[] args) {
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                inheritableThreadLocal.
            }
        });
    }
}
